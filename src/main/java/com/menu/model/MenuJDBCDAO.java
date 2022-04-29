package com.menu.model;

import java.util.*;
import java.io.*;
import java.sql.*;

public class MenuJDBCDAO implements MenuDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://database-1.cqm5mb4z5ril.ap-northeast-1.rds.amazonaws.com:3306/CGA101-03?serverTimezone=Asia/Taipei";
	String userid = "cga101-03";
	String passwd = "cga101-03";

	// 前台 新增店家單品項目
	private static final String INSERT_STMT = "INSERT INTO menu (shop_id,item,price) VALUES (?, ?, ?)";
	// 前台 修改店家單品項目
	private static final String UPDATE = "UPDATE menu set shop_id=?, item=?, price=?, is_item=? where menu_id = ?";
	
	// 前台 由下架修改為上架
	private static final String UPDATESTATUS = "UPDATE menu set is_item = 1 where menu_id = ?";
		
	// 前台 查詢一間店家菜單(上架中)
	private static final String GET_ByShopId = "SELECT menu_id, item, price, is_item, menu_upd FROM menu where shop_id = ? and is_item=1 ";
	// 前台 查詢一間店家菜單(下架)
	private static final String GET_ByShopId_Disable = "SELECT menu_id, item, price, is_item, menu_upd FROM menu where shop_id = ? and is_item=0 ";
	
	// 後台 查詢各店家菜單
	private static final String GET_ALL_STMT = "SELECT * FROM menu ";
	
	//查詢一筆菜單項目
	private static final String GET_ONE_STMT = " SELECT menu_id, shop_id, item, price, is_item, menu_upd FROM menu where menu_id = ?";


	// 前台 新增店家單品項目
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

	// 前台 修改店家單品項目
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

	// 前台 下架改上架
	@Override
	public void updatestatus(MenuVO menuVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATESTATUS);

			pstmt.setInt(1, menuVO.getMenu_id());

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

	
	//前台 以店家查詢菜單明細(上架)
	@Override
	public List<MenuVO> getByShopId(Integer shop_id) {
		List<MenuVO> list = new ArrayList<MenuVO>();
		MenuVO menuVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ByShopId);

			pstmt.setInt(1, shop_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				menuVO = new MenuVO();
				menuVO.setMenu_id(rs.getInt(1));
				menuVO.setItem(rs.getString(2));
				menuVO.setPrice(rs.getInt(3));
				menuVO.setIs_item(rs.getInt(4));
				menuVO.setMenu_upd(rs.getTimestamp(5));
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

	//前台 以店家查詢菜單明細(下架)
	@Override
	public List<MenuVO> getByShopIdDisable(Integer shop_id) {
			List<MenuVO> list = new ArrayList<MenuVO>();
			MenuVO menuVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ByShopId_Disable);

				pstmt.setInt(1, shop_id);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					menuVO = new MenuVO();
					menuVO.setMenu_id(rs.getInt(1));
					menuVO.setItem(rs.getString(2));
					menuVO.setPrice(rs.getInt(3));
					menuVO.setIs_item(rs.getInt(4));
					menuVO.setMenu_upd(rs.getTimestamp(5));
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

	// 後台 查詢各店家菜單
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
				menuVO.setMenu_id(rs.getInt(1));
				menuVO.setShop_id(rs.getInt(2));
				menuVO.setItem(rs.getString(3));
				menuVO.setPrice(rs.getInt(4));
				menuVO.setIs_item(rs.getInt(5));
				menuVO.setMenu_upd(rs.getTimestamp(6));
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
	
	// 查詢一筆菜單項目
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
	

}
