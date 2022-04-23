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
	
//	-- 2-1.�ק�ڪ����γ浧: �ƶq(+-�Ʀr)&�Ƶ�(��r)
//	update groupBuyList set qty = 2, remark = '���n�Y'
//	where buyer_name="�f�f" and gbList_id= 10001;
	private static final String UPDATE = "UPDATE groupBuyList set qty=?, remark=? where buyer=? and gbList_id=?";

//	-- 2-2.�R�����s: �R����ӫ~��
//	DELETE FROM groupBuyList 
//	where buyer_name="�f�f" and gbList_id= 10008;
	private static final String DELETE = "DELETE FROM groupBuyList where gbList_id = ?";

//	-- 1-1. �h�X���s--(�����R���ӵ�) 
//	DELETE FROM groupbuy 
//	where buyer_name="�f�f" and gb_id= 1003;	
	private static final String DELETEMYGB = "DELETE FROM groupbuy where buyer = ? and gb_id = ?";
	
//	-- 2.�s����s-->�d�ߧڪ��浧����
//	SELECT gbList_id, item "�~��" , price '���', qty '�ƶq', (price*qty) as '���B', remark '�Ƶ�'
//	FROM groupBuyList where buyer_name="�f�f" and gb_id= 1001 group by gbList_id;	
	private static final String GET_ONE_STMT = "SELECT gbList_id, gb_id, item, price, qty, (price*qty), remark "
			+ "FROM groupBuyList where buyer = ? and gb_id= ? group by gbList_id";

	private static final String GET_ALL_STMT = "SELECT * FROM groupBuyList order by gb_id";

	@Override
	public void insert(GroupBuyListVO groupBuyListVO) {
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pstmt = con.prepareStatement(INSERT_STMT)) {
			// �s�W�浧����			
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

	// �ק�ƶq�γƵ�
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

	// �R����ӫ~��
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
	
	//�Y�H�h�X����(�R���Y�H�Ҧ�����)
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
	
	//�d�ߧڪ��浧�ѹΩ���
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
			ResultSet rs = pstmt.executeQuery(); // ��Statement�����AResultSet�]�|�۰������A���ݱNResultSet�ŧi�m�Jtry with resource

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
		
		//��s�Y�H��~��
		GroupBuyListVO gblVO2 = new GroupBuyListVO();
		
		gblVO2.setQty(10);
		gblVO2.setRemark("���");
		gblVO2.setBuyer(1002);
		gblVO2.setGbList_id(10008);
//		gblVO2.setBuyer_name("�f�f");
//		gblVO2.setMenu_id(1021);
//		gblVO2.setItem("�㥽���");
//		gblVO2.setPrice(45);
//		gblVO2.setTotal(null);
//		gblVO2.setIs_pay(0);
//		gblVO2.setIs_pickup(0);
		dao.update(gblVO2);
		
		
		// �R����ӭq�ʶ�
//		dao.delete(10012);
		
		// �Y�H�h�X����
//		dao.delete(1002, 1002);	
		
		//�d�ݧڪ��浧�ѹΩ���
//		List<GroupBuyListVO> mygblist = dao.findMyGb();
//		int countList = 1;
//		for (GroupBuyListVO m_List : mygblist) {
//			System.out.println(m_List);
//			System.out.println("---------------------����:" + countList++);
//		}
		
		// �d�ߥ������Ω���
//		List<GroupBuyListVO> list = dao.getAll();
//		int countList = 1;
//		for (GroupBuyListVO allList : list) {
//			System.out.println(allList.toString());
//			System.out.println("---------------------����:" + countList++);
//		}

		// �s�W�浧���(�[�J����)
//		GroupBuyListVO gblVO1 = new GroupBuyListVO();
//		gblVO1.setGb_id(1002);
//		gblVO1.setBuyer(1013);
//		gblVO1.setBuyer_name("william");
//		gblVO1.setMenu_id(1004);
//		gblVO1.setItem("���d�@����");
//		gblVO1.setPrice(90);
//		gblVO1.setQty(3);
//		gblVO1.setRemark("���h");
//		gblVO1.setIs_pay(0);
//		gblVO1.setIs_pickup(0);
//		dao.insert(gblVO1);


	}


}
