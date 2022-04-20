package com.menu.model;

import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class MenuJDBCDAO implements MenuDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/dbcga101g3?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "1qaz2wsx";

	private static final String INSERT_STMT = "INSERT INTO menu (shop_id,item,price) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT menu_id,shop_id,item,price,is_item,menu_upd FROM menu order by menu_id";
	private static final String UPDATE = "UPDATE menu set shop_id=?, item=?, price=?, is_item=? where menu_id = ?";
	private static final String GET_ONE_STMT = "SELECT menu_id,shop_id,item,price,is_item,menu_upd FROM menu where menu_id = ?";

	@Override
	public void insert(MenuVO menuVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, menuVO.getShop_id());
			pstmt.setString(2, menuVO.getItem());
			pstmt.setInt(3, menuVO.getPrice());

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

	@Override
	public void update(MenuVO menuVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, menuVO.getShop_id());
			pstmt.setString(2, menuVO.getItem());
			pstmt.setInt(3, menuVO.getPrice());
			pstmt.setInt(4, menuVO.getIs_item());
			pstmt.setInt(5, menuVO.getMenu_id());

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

	@Override
	public MenuVO findByPrimaryKey(Integer menu_id) {
		MenuVO menuVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, menu_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				menuVO = new MenuVO();
				menuVO.setMenu_id(rs.getInt("menu_id"));
				menuVO.setShop_id(rs.getInt("shop_id"));
				menuVO.setItem(rs.getString("item"));
				menuVO.setPrice(rs.getInt("price"));
				menuVO.setIs_item(rs.getInt("is_item"));
				menuVO.setMenu_upd(rs.getTimestamp("menu_upd"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return menuVO;
	}

	@Override
	public List<MenuVO> getAll() {
		List<MenuVO> list = new ArrayList<MenuVO>();
		MenuVO menuVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				menuVO = new MenuVO();
				menuVO.setMenu_id(rs.getInt("menu_id"));
				menuVO.setShop_id(rs.getInt("shop_id"));
				menuVO.setItem(rs.getString("item"));
				menuVO.setPrice(rs.getInt("price"));
				menuVO.setIs_item(rs.getInt("is_item"));
				menuVO.setMenu_upd(rs.getTimestamp("menu_upd"));
				list.add(menuVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	public static void main(String[] args) throws IOException {

		MenuJDBCDAO dao = new MenuJDBCDAO();

		// ·s¼W
//		MenuVO menuVO1 = new MenuVO();
//		menuVO1.setShop_id(102);
//		menuVO1.setItem("¤ûÉd©@­ù¶º");
//		menuVO1.setPrice(90);
//		dao.insert(menuVO1);

		// ­×§ï
//		MenuVO menuVO2 = new MenuVO();
//		menuVO2.setMenu_id(1001);
//		menuVO2.setShop_id(102);
//		menuVO2.setItem("¤ûÉd+½Þ±Æ©@­ù");
//		menuVO2.setPrice(150);
//		menuVO2.setIs_item(1);
//		dao.update(menuVO2);

		// ¬d¸ß
		MenuVO menuVO3 = dao.findByPrimaryKey(1001);
		System.out.println(menuVO3.toString());
		System.out.println("---------------------");

		// ¬d¸ß
//		List<MenuVO> list = dao.getAll();
//		for (MenuVO aMenu : list) {
//			System.out.println(aMenu.toString());
//			System.out.println();
//		 }
	}

}
