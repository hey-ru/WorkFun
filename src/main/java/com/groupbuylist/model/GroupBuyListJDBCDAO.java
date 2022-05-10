package com.groupbuylist.model;

import java.util.*;

import com.groupbuy.model.GroupBuyJDBCDAO;
import com.groupbuylist.controller.jdbcUtil_CompositeQuery;

import java.io.IOException;
import java.sql.*;

public class GroupBuyListJDBCDAO implements GroupBuyListDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://database-1.cqm5mb4z5ril.ap-northeast-1.rds.amazonaws.com:3306/CGA101-03?serverTimezone=Asia/Taipei";
	String userid = "cga101-03";
	String passwd = "cga101-03";

//  <<參團管理>>
//	參團者新增一筆參團ok
	private static final String INSERT_STMT = "INSERT INTO groupbuylist (gb_id, buyer, buyer_name, menu_id, item, "
			+ "price, qty, remark) " + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";

//	-- 1.查詢我的參團: 查詢各團總額及狀態(主頁查詢畫面, 依結束時間降冪排序)-->join groupbuy
	private static final String GET_MYGB = "SELECT * "
			+ "FROM groupbuylist WHERE buyer=? GROUP BY gb_id ORDER BY gb_id ";

//	-- 1-1. 退出按鈕: (揪團截止前)刪除 訂單所有項目ok	
	private static final String DELETEMYGB = "DELETE FROM groupbuy where buyer = ? and gb_id = ?";

//	-- 2. 檢視按鈕: 查詢 我的單筆明細ok
	private static final String GET_ONE_BYBUYER = "SELECT * FROM groupbuylist WHERE buyer = ? AND gb_id= ? GROUP BY gbList_id ";

//	-- 2-1. 修改按鈕: (揪團截止前)修改 單筆項目的數量&備註ok
	private static final String UPDATE = "UPDATE groupbuylist set qty=?, remark=? where buyer=? and gbList_id=?";

//	-- 2-2. 刪除按鈕: (揪團截止前)刪除 單個品項ok
	private static final String DELETE = "DELETE FROM groupbuylist where gbList_id = ?";

//	[後台]: 查詢所有參團明細ok
	private static final String GET_ALL_STMT = "SELECT * FROM groupbuylist order by gb_id";

	// PK ok
	private static final String GET_ONE_STMT = "SELECT * FROM groupbuylist where gbList_id = ?";

//	<<揪團管理>>
//	修改取貨付款
	private static final String UPDATE_FROMGBOWNER_STMT = "UPDATE groupbuylist SET is_pay = ?, is_pickup = ? WHERE gb_id = ? AND buyer = ?";

//===================================================================================================	

//	參團者修改多筆  ok
	@Override
	public void updateMany(List<GroupBuyListVO> listGBorder) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			// 新增多筆項目
			for (GroupBuyListVO groupBuyListVO : listGBorder) {

				pstmt.setInt(1, groupBuyListVO.getQty());
				pstmt.setString(2, groupBuyListVO.getRemark());
				pstmt.setInt(3, groupBuyListVO.getBuyer());
				pstmt.setInt(4, groupBuyListVO.getGbList_id());
				pstmt.addBatch();
			}
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

//	參團者新增多筆  ok
	@Override
	public void insertMany(List<GroupBuyListVO> listGBorder) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			// 新增多筆項目
			for (GroupBuyListVO groupBuyListVO : listGBorder) {
				pstmt.setInt(1, groupBuyListVO.getGb_id());
				pstmt.setInt(2, groupBuyListVO.getBuyer());
				pstmt.setString(3, groupBuyListVO.getBuyer_name());
				pstmt.setInt(4, groupBuyListVO.getMenu_id());
				pstmt.setString(5, groupBuyListVO.getItem());
				pstmt.setInt(6, groupBuyListVO.getPrice());
				pstmt.setInt(7, groupBuyListVO.getQty());
				pstmt.setString(8, groupBuyListVO.getRemark());
				pstmt.executeUpdate();
			}

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

//	[揪團]: 修改取貨付款
	@Override
	public void updateIsPayIsPickUp(GroupBuyListVO groupBuyListVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_FROMGBOWNER_STMT);

			pstmt.setInt(1, groupBuyListVO.getIs_pay());
			pstmt.setInt(2, groupBuyListVO.getIs_pickup());
			pstmt.setInt(3, groupBuyListVO.getGb_id());
			pstmt.setInt(4, groupBuyListVO.getBuyer());

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

//	參團者新增一筆參團 ok
	@Override
	public void insertItem(GroupBuyListVO groupBuyListVO) {
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
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}
	}

//	-- 1.查詢我的參團: 查詢各團總額及狀態(主頁查詢畫面, 依結束時間降冪排序)-->join groupbuy
	@Override
	public List<GroupBuyListVO> getMyGB(Integer buyer) {
		List<GroupBuyListVO> mygblist = new ArrayList<GroupBuyListVO>();

		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pstmt = con.prepareStatement(GET_MYGB)) {

			pstmt.setInt(1, buyer); // buyer = ?

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				GroupBuyListVO groupBuyListVO = new GroupBuyListVO();
//						groupBuyListVO.setGb_id(rs.getInt(1));
//						groupBuyListVO.setTotal(rs.getInt(2));
//						groupBuyListVO.setIs_pay(rs.getInt(3));
//						groupBuyListVO.setIs_pickup(rs.getInt(4));
//						groupBuyListVO.setTotal(rs.getInt(5));
//						groupBuyListVO.setGbList_upd(rs.getTimestamp(6));
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

				mygblist.add(groupBuyListVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}
		return mygblist;
	}

//	-- 1-1. 退出按鈕: (揪團截止前)刪除 訂單所有項目 ok
	public void deleteMyGb(Integer buyer, Integer gb_id) {

		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pstmt = con.prepareStatement(DELETEMYGB)) {
			pstmt.setInt(1, buyer);
			pstmt.setInt(2, gb_id);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}
	}

//	-- 2. 檢視按鈕: 查詢 我的單筆明細 ok
	@Override
	public List<GroupBuyListVO> getOne(Integer buyer, Integer gb_id) {
		List<GroupBuyListVO> onelist = new ArrayList<GroupBuyListVO>();

		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pstmt = con.prepareStatement(GET_ONE_BYBUYER)) {
			pstmt.setInt(1, buyer); // buyer = ?
			pstmt.setInt(2, gb_id); // gb_id= ?

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				GroupBuyListVO groupBuyListVO = new GroupBuyListVO();

				groupBuyListVO = new GroupBuyListVO();
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

				onelist.add(groupBuyListVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}
		return onelist;
	}

//	-- 2-1. 修改按鈕: (揪團截止前)修改 單筆項目的數量&備註 ok
	@Override
	public void updateItem(GroupBuyListVO groupBuyListVO) {

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

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}
	}

//	-- 2-2. 刪除按鈕: (揪團截止前)刪除 單個品項 ok
	@Override
	public void deleteItem(Integer gbList_id) {

		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pstmt = con.prepareStatement(DELETE)) {
			pstmt.setInt(1, gbList_id);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}
	}

//	[後台]: 查詢所有參團明細 ok
	@Override
	public List<GroupBuyListVO> getAll() {
		List<GroupBuyListVO> list = new ArrayList<GroupBuyListVO>();

		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT)) {
			ResultSet rs = pstmt.executeQuery(); // 當Statement關閉，ResultSet也會自動關閉，不需將ResultSet宣告置入try with resource

			while (rs.next()) {
				GroupBuyListVO groupBuyListVO = new GroupBuyListVO();
				groupBuyListVO = new GroupBuyListVO();
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
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}
		return list;
	}

//	PK OK
	@Override
	public GroupBuyListVO findByPrimaryKey(Integer gbList_id) {
		GroupBuyListVO groupBuyListVO = null;

		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pstmt = con.prepareStatement(GET_ONE_STMT)) {

			pstmt.setInt(1, gbList_id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				groupBuyListVO = new GroupBuyListVO();

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

			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}
		return groupBuyListVO;
	}

	// 萬用複合查詢
	@Override
	public List<GroupBuyListVO> getAll(Map<String, String[]> map) {
		List<GroupBuyListVO> list = new ArrayList<GroupBuyListVO>();
		GroupBuyListVO groupBuyListVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String finalSQL = "select * from groupbuylist " + jdbcUtil_CompositeQuery.get_WhereCondition(map)
					+ "order by gblist_id";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = " + finalSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				groupBuyListVO = new GroupBuyListVO();
				groupBuyListVO.setGbList_id(rs.getInt("gblist_id"));
				groupBuyListVO.setGb_id(rs.getInt("gb_id"));
				groupBuyListVO.setBuyer(rs.getInt("buyer"));
				groupBuyListVO.setBuyer_name(rs.getString("buyer_name"));
				groupBuyListVO.setMenu_id(rs.getInt("menu_id"));
				groupBuyListVO.setItem(rs.getString("item"));
				groupBuyListVO.setPrice(rs.getInt("price"));
				groupBuyListVO.setQty(rs.getInt("qty"));
				groupBuyListVO.setTotal(rs.getInt("total"));
				groupBuyListVO.setRemark(rs.getString("remark"));
				groupBuyListVO.setIs_pay(rs.getInt("is_pay"));
				groupBuyListVO.setIs_pickup(rs.getInt("is_pickup"));
				groupBuyListVO.setGbList_upd(rs.getTimestamp("gbList_upd"));

				list.add(groupBuyListVO); // Store the row in the List
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return list;
	}

	// main方法
	public static void main(String[] args) throws IOException {
		GroupBuyListJDBCDAO dao = new GroupBuyListJDBCDAO();

		// 測試修改付款狀況
		GroupBuyListVO groupBuyListVO5 = new GroupBuyListVO();
		groupBuyListVO5.setBuyer(1002);
		groupBuyListVO5.setGb_id(1001);
		groupBuyListVO5.setIs_pay(0);
		groupBuyListVO5.setIs_pickup(1);
		dao.updateIsPayIsPickUp(groupBuyListVO5);
	}

}
