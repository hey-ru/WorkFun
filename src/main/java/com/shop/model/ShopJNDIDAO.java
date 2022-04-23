package com.shop.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ShopJNDIDAO implements ShopDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO shop (shop_name,shop_type,address,tel,website,min_amt,shop_img1,shop_img2,shop_img3) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT shop_id,shop_name,shop_type,address,tel,website,min_amt,shop_img1,shop_img2,shop_img3,is_disable,shop_upd FROM shop order by shop_id";
	private static final String UPDATE = "UPDATE shop set shop_name=?, shop_type=?, address=?, tel=?, website=?, min_amt=?, shop_img1=?, shop_img2=?, shop_img3=? where shop_id = ?";
	private static final String GET_ONE_STMT = "SELECT shop_id,shop_name,shop_type,address,tel,website,min_amt,shop_img1,shop_img2,shop_img3,is_disable,shop_upd FROM shop where shop_id = ?";

	@Override
	public void insert(ShopVO shopVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, shopVO.getShop_name());
			pstmt.setInt(2, shopVO.getShop_type());
			pstmt.setString(3, shopVO.getAddress());
			pstmt.setString(4, shopVO.getTel());
			pstmt.setString(5, shopVO.getWebsite());
			pstmt.setInt(6, shopVO.getMin_amt());
			pstmt.setBytes(7, shopVO.getShop_img1());
			pstmt.setBytes(8, shopVO.getShop_img2());
			pstmt.setBytes(9, shopVO.getShop_img3());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

	@Override
	public void update(ShopVO shopVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, shopVO.getShop_name());
			pstmt.setInt(2, shopVO.getShop_type());
			pstmt.setString(3, shopVO.getAddress());
			pstmt.setString(4, shopVO.getTel());
			pstmt.setString(5, shopVO.getWebsite());
			pstmt.setInt(6, shopVO.getMin_amt());
			pstmt.setBytes(7, shopVO.getShop_img1());
			pstmt.setBytes(8, shopVO.getShop_img2());
			pstmt.setBytes(9, shopVO.getShop_img3());
			pstmt.setInt(10, shopVO.getShop_id());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

	@Override
	public ShopVO findByPrimaryKey(Integer shop_id) {

		ShopVO shopVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, shop_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				shopVO = new ShopVO();
				shopVO.setShop_id(rs.getInt("shop_id"));
				shopVO.setShop_name(rs.getString("shop_name"));
				shopVO.setShop_type(rs.getInt("shop_type"));
				shopVO.setAddress(rs.getString("address"));
				shopVO.setTel(rs.getString("tel"));
				shopVO.setWebsite(rs.getString("website"));
				shopVO.setMin_amt(rs.getInt("min_amt"));
				shopVO.setShop_img1(rs.getBytes("shop_img1"));
				shopVO.setShop_img2(rs.getBytes("shop_img2"));
				shopVO.setShop_img3(rs.getBytes("shop_img3"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return shopVO;
	}

	@Override
	public List<ShopVO> getAll() {
		List<ShopVO> list = new ArrayList<ShopVO>();
		ShopVO shopVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				shopVO = new ShopVO();
				shopVO.setShop_id(rs.getInt("shop_id"));
				shopVO.setShop_name(rs.getString("shop_name"));
				shopVO.setShop_type(rs.getInt("shop_type"));
				shopVO.setAddress(rs.getString("address"));
				shopVO.setTel(rs.getString("tel"));
				shopVO.setWebsite(rs.getString("website"));
				shopVO.setMin_amt(rs.getInt("min_amt"));
				shopVO.setShop_img1(rs.getBytes("shop_img1"));
				shopVO.setShop_img2(rs.getBytes("shop_img2"));
				shopVO.setShop_img3(rs.getBytes("shop_img3"));
				list.add(shopVO);
			}


			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return list;
	}
}