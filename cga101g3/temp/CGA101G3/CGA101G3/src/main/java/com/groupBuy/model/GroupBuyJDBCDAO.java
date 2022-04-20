package com.groupBuy.model;

import java.util.*;
import java.sql.*;


public class GroupBuyJDBCDAO implements GroupBuyDAO_Interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://database-1.cqm5mb4z5ril.ap-northeast-1.rds.amazonaws.com:3306/CGA101-03?serverTimezone=Asia/Taipei";
	String userid = "cga101-03";
	String passwd = "cga101-03";

	private static final String INSERT_STMT = 
			"INSERT INTO groupbuy (shop_id, shop_name, gb_owner, start_time, end_time, arr_time, min_amt) " 
	+ "VALUES ( ?, ?, ?, ?, ?, ?, ?)";
	

	
	@Override
	public void insert(GroupBuyVO groupBuyVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, groupBuyVO.getShop_id());
			pstmt.setString(2, groupBuyVO.getShop_name());
			pstmt.setInt(3, groupBuyVO.getGb_owner());
			pstmt.setTimestamp(4, groupBuyVO.getStart_time());
			pstmt.setTimestamp(5, groupBuyVO.getEnd_time());
			pstmt.setTimestamp(6, groupBuyVO.getArr_time());
			pstmt.setInt(7, groupBuyVO.getMin_amt());

			pstmt.executeUpdate();

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

	
	//後台隱藏揪團單
	@Override
	public void update(GroupBuyVO groupBuyVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GroupBuyVO findByPrimaryKey(Integer gb_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GroupBuyVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		
		GroupBuyJDBCDAO dao = new GroupBuyJDBCDAO();

//新增一筆揪團
		GroupBuyVO groupBuyVO1 = new GroupBuyVO();
		groupBuyVO1.setShop_id(110); 
		groupBuyVO1.setShop_name("劉家現做潤餅");
		groupBuyVO1.setGb_owner(1010);
		
		//設定起始,結束,到貨時間	
		groupBuyVO1.setStart_time(Timestamp.valueOf("2022-05-20 08:30:00"));
		groupBuyVO1.setEnd_time(Timestamp.valueOf("2022-05-20 11:00:00"));
		groupBuyVO1.setArr_time(Timestamp.valueOf("2022-05-20 12:30:00"));
		groupBuyVO1.setMin_amt(100);
		dao.insert(groupBuyVO1);	
		
		
	}



}
