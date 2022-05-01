package com.groupbuylist.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class GroupBuyListDAO implements GroupBuyListDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CGA101G3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

//  <<參團管理>>
//	參團者新增一筆參團
	private static final String INSERT_STMT = "INSERT INTO groupbuylist (gb_id, buyer, buyer_name, menu_id, item, "
			+ "price, qty, remark) " + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
	
//	-- 1.查詢我的參團: 查詢各團總額及狀態(主頁查詢畫面, 依結束時間降冪排序)-->join groupbuy
	
//	-- 1-1. 退出按鈕: (揪團截止前)刪除 訂單所有項目 	
	private static final String DELETEMYGB = "DELETE FROM groupbuy where buyer = ? and gb_id = ?";

//	-- 2. 檢視按鈕: 查詢 我的單筆明細
	private static final String GET_ONE_BYBUYER = "SELECT gbList_id, gb_id, item, price, qty, (price*qty), remark "
			+ "FROM groupbuylist where buyer = ? and gb_id= ? group by gbList_id ";
	
//	-- 2-1. 修改按鈕: (揪團截止前)修改 單筆項目的數量&備註
	private static final String UPDATE = "UPDATE groupbuylist set qty=?, remark=? where buyer=? and gbList_id=?";

//	-- 2-2. 刪除按鈕: (揪團截止前)刪除 單個品項
	private static final String DELETE = "DELETE FROM groupbuylist where gbList_id = ?";
	

//	[後台]: 查詢所有揪團明細
	private static final String GET_ALL_STMT = "SELECT * FROM groupbuylist order by gb_id";

	//PK
	private static final String GET_ONE_STMT = "SELECT * FROM groupbuylist where gbList_id = ?";

	
//	參團者新增一筆參團	
	@Override
	public void insertItem(GroupBuyListVO groupBuyListVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
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

//	-- 2-1. 修改按鈕: (揪團截止前)修改 單筆項目的數量&備註
	@Override
	public void updateItem(GroupBuyListVO groupBuyListVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
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

//	-- 2-2. 刪除按鈕: (揪團截止前)刪除 單個品項
	@Override
	public void deleteItem(Integer gbList_id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
		
			pstmt.setInt(1, gbList_id);
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} 		
	}
	
//	-- 1-1. 退出按鈕: (揪團截止前)刪除 訂單所有項目 
	public void deleteMyGb(Integer buyer, Integer gb_id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETEMYGB);
		
			pstmt.setInt(1, buyer);
			pstmt.setInt(2, gb_id);

			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} 	
	}
	
//	-- 2. 檢視按鈕: 查詢 我的單筆明細
	@Override
	public List<GroupBuyListVO> getMyGb(Integer buyer, Integer gb_id) {
		List<GroupBuyListVO> mygblist = new ArrayList<GroupBuyListVO>();
		
		GroupBuyListVO groupBuyListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BYBUYER);
		
					pstmt.setInt(1, buyer); //buyer = ?
					pstmt.setInt(2, gb_id); //gb_id= ?
					
					rs = pstmt.executeQuery();
			
					while (rs.next()) {
						groupBuyListVO = new GroupBuyListVO();
						groupBuyListVO.setGbList_id(rs.getInt(1));
						groupBuyListVO.setGb_id(rs.getInt(2));
						groupBuyListVO.setItem(rs.getString(3));
						groupBuyListVO.setPrice(rs.getInt(4));
						groupBuyListVO.setQty(rs.getInt(5));
						groupBuyListVO.setTotal(rs.getInt(6));
						groupBuyListVO.setRemark(rs.getString(7));
						
						mygblist.add(groupBuyListVO);
					}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} 
		return mygblist;
	}
	
	
//	[後台]: 查詢所有揪團明細
	@Override
	public List<GroupBuyListVO> getAll() {
		List<GroupBuyListVO> list = new ArrayList<GroupBuyListVO>();
		
		GroupBuyListVO groupBuyListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

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

				list.add(groupBuyListVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} 
		return list;
	}

	@Override
	public GroupBuyListVO findByPrimaryKey(Integer gbList_id) {
		
		GroupBuyListVO groupBuyListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			pstmt.setInt(1, gbList_id);
			rs = pstmt.executeQuery();
			
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


}
