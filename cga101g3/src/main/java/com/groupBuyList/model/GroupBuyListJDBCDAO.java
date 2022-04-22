package com.groupBuyList.model;

import java.util.*;
import java.sql.*;

public class GroupBuyListJDBCDAO implements GroupBuyListDAO_Interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://database-1.cqm5mb4z5ril.ap-northeast-1.rds.amazonaws.com:3306/CGA101-03?serverTimezone=Asia/Taipei";
	String userid = "cga101-03";
	String passwd = "cga101-03";

	private static final String INSERT_STMT = "INSERT INTO groupBuyList (gb_id, buyer, buyer_name, menu_id, item, "
			+ "price, qty, remark, is_pay, is_pickup) " + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
//	-- 2-1.修改我的揪團單筆: 數量(+-數字)&備註(文字)
//	update groupBuyList set qty = 2, remark = '蔥好吃'
//	where buyer_name="宸宸" and gbList_id= 10001;
	private static final String UPDATE = "UPDATE groupBuyList set qty=?, remark=? where buyer=? and gbList_id=?";

//	-- 2-2.刪除按鈕: 刪除單個品項
//	DELETE FROM groupBuyList 
//	where buyer_name="宸宸" and gbList_id= 10008;
	private static final String DELETE = "DELETE FROM groupBuyList where gbList_id = ?";

//	-- 1-1. 退出按鈕--(直接刪除該筆) 
//	DELETE FROM groupbuy 
//	where buyer_name="宸宸" and gb_id= 1003;	
	private static final String DELETEMYGB = "DELETE FROM groupbuy where buyer = ? and gb_id = ?";
	
//	-- 2.編輯按鈕-->查詢我的單筆明細
//	SELECT gbList_id, item "品項" , price '單價', qty '數量', (price*qty) as '金額', remark '備註'
//	FROM groupBuyList where buyer_name="宸宸" and gb_id= 1001 group by gbList_id;	
	private static final String GET_ONE_STMT = "SELECT gbList_id, gb_id, item, price, qty, (price*qty), remark "
			+ "FROM groupBuyList where buyer = ? and gb_id= ? group by gbList_id";

	private static final String GET_ALL_STMT = "SELECT * FROM groupBuyList order by gb_id";

	@Override
	public void insert(GroupBuyListVO groupBuyListVO) {
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pstmt = con.prepareStatement(INSERT_STMT)) {
			// 新增單筆項目			
			pstmt.setInt(1, groupBuyListVO.getGb_id());
			pstmt.setInt(2, groupBuyListVO.getBuyer());
			pstmt.setString(3, groupBuyListVO.getBuyer_name());
			pstmt.setInt(4, groupBuyListVO.getMenu_id());
			pstmt.setString(5, groupBuyListVO.getItem());
			pstmt.setInt(6, groupBuyListVO.getPrice());
			pstmt.setInt(7, groupBuyListVO.getQty());
			pstmt.setString(8, groupBuyListVO.getRemark());
			pstmt.setInt(9, groupBuyListVO.getIs_pay());
			pstmt.setInt(10, groupBuyListVO.getIs_pickup());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	// 修改數量及備註
	@Override
	public void update(GroupBuyListVO groupBuyListVO) {
		
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pstmt = con.prepareStatement(UPDATE)) {
			
			pstmt.setInt(1, groupBuyListVO.getQty());
			pstmt.setString(2, groupBuyListVO.getRemark());
			pstmt.setInt(3, groupBuyListVO.getBuyer());
			pstmt.setInt(4, groupBuyListVO.getGbList_id());			
			
//			pstmt.setInt(1, groupBuyListVO.getGb_id());
//			pstmt.setInt(2, groupBuyListVO.getBuyer());
//			pstmt.setString(3, groupBuyListVO.getBuyer_name());
//			pstmt.setInt(4, groupBuyListVO.getMenu_id());
//			pstmt.setString(5, groupBuyListVO.getItem());
//			pstmt.setInt(6, groupBuyListVO.getPrice());
//			pstmt.setInt(7, groupBuyListVO.getQty());
//			pstmt.setString(8, groupBuyListVO.getRemark());
//			pstmt.setInt(9, groupBuyListVO.getIs_pay());
//			pstmt.setInt(10, groupBuyListVO.getIs_pickup());

			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	// 刪除單個品項
	@Override
	public void delete(Integer gbList_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, gbList_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	//某人退出揪團(刪除某人所有項目)
	@Override
	public void delete(Integer buyer, Integer gb_id) {
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pstmt = con.prepareStatement(DELETEMYGB)) {
			pstmt.setInt(1, buyer);
			pstmt.setInt(2, gb_id);

			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public GroupBuyListVO findByPrimaryKey(Integer gbList_id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//查詢我的單筆參團明細
	@Override
	public List<GroupBuyListVO> findMyGb() {
		List<GroupBuyListVO> mygblist = new ArrayList<GroupBuyListVO>();

		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pstmt = con.prepareStatement(GET_ONE_STMT)) {
					pstmt.setInt(1, 1010); //buyer = ?
					pstmt.setInt(2, 1002); //gb_id= ?
					
					ResultSet rs = pstmt.executeQuery();
			
					while (rs.next()) {
						GroupBuyListVO groupBuyListVO = new GroupBuyListVO();
						groupBuyListVO.setGbList_id(rs.getInt(1));
						groupBuyListVO.setGb_id(rs.getInt(2));
						groupBuyListVO.setItem(rs.getString(3));
						groupBuyListVO.setPrice(rs.getInt(4));
						groupBuyListVO.setQty(rs.getInt(5));
						groupBuyListVO.setTotal(rs.getInt(6));
						groupBuyListVO.setRemark(rs.getString(7));
						
						mygblist.add(groupBuyListVO);
					}
					
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return mygblist;
	}
	

	@Override
	public List<GroupBuyListVO> getAll() {
		List<GroupBuyListVO> list = new ArrayList<GroupBuyListVO>();

		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT)) {
			ResultSet rs = pstmt.executeQuery(); // 當Statement關閉，ResultSet也會自動關閉，不需將ResultSet宣告置入try with resource

			while (rs.next()) {
				GroupBuyListVO groupBuyListVO = new GroupBuyListVO();
				groupBuyListVO.setGbList_id(rs.getInt(1));
				groupBuyListVO.setGbList_id(rs.getInt(1));
				groupBuyListVO.setGb_id(rs.getInt(2));
				groupBuyListVO.setBuyer(rs.getInt(3));
				groupBuyListVO.setBuyer_name(rs.getString(4));
				groupBuyListVO.setMenu_id(rs.getInt(5));
				groupBuyListVO.setItem(rs.getString(6));
				groupBuyListVO.setPrice(rs.getInt(7));
				groupBuyListVO.setQty(rs.getInt(8));
				groupBuyListVO.setRemark(rs.getString(9));
				groupBuyListVO.setIs_pay(rs.getInt(10));
				groupBuyListVO.setIs_pickup(rs.getInt(11));
				groupBuyListVO.setGbList_upd(rs.getTimestamp(12));

				list.add(groupBuyListVO);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return list;
	}

	public static void main(String[] args) {

		GroupBuyListJDBCDAO dao = new GroupBuyListJDBCDAO();	
		
		//更新某人單品項
		GroupBuyListVO gblVO2 = new GroupBuyListVO();
		
		gblVO2.setQty(10);
		gblVO2.setRemark("醬少");
		gblVO2.setBuyer(1002);
		gblVO2.setGbList_id(10008);
//		gblVO2.setBuyer_name("宸宸");
//		gblVO2.setMenu_id(1021);
//		gblVO2.setItem("芥末潤餅");
//		gblVO2.setPrice(45);
//		gblVO2.setTotal(null);
//		gblVO2.setIs_pay(0);
//		gblVO2.setIs_pickup(0);
		dao.update(gblVO2);
		
		
		// 刪除單個訂購項
//		dao.delete(10012);
		
		// 某人退出揪團
//		dao.delete(1002, 1002);	
		
		//查看我的單筆參團明細
//		List<GroupBuyListVO> mygblist = dao.findMyGb();
//		int countList = 1;
//		for (GroupBuyListVO m_List : mygblist) {
//			System.out.println(m_List);
//			System.out.println("---------------------筆數:" + countList++);
//		}
		
		// 查詢全部揪團明細
//		List<GroupBuyListVO> list = dao.getAll();
//		int countList = 1;
//		for (GroupBuyListVO allList : list) {
//			System.out.println(allList.toString());
//			System.out.println("---------------------筆數:" + countList++);
//		}

		// 新增單筆資料(加入揪團)
//		GroupBuyListVO gblVO1 = new GroupBuyListVO();
//		gblVO1.setGb_id(1002);
//		gblVO1.setBuyer(1013);
//		gblVO1.setBuyer_name("william");
//		gblVO1.setMenu_id(1004);
//		gblVO1.setItem("牛丼咖哩飯");
//		gblVO1.setPrice(90);
//		gblVO1.setQty(3);
//		gblVO1.setRemark("飯多");
//		gblVO1.setIs_pay(0);
//		gblVO1.setIs_pickup(0);
//		dao.insert(gblVO1);


	}


}
