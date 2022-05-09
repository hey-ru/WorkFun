package com.menu.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.groupbuylist.model.GroupBuyListVO;

import java.sql.*;

public class MenuDAO implements MenuDAO_interface {
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CGA101G3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	// 前台 新增店家單品項目
	private static final String INSERT_STMT = "INSERT INTO menu (shop_id,item,price) VALUES (?, ?, ?)";
	// 前台 修改店家單品項目(串接寫法)
	private static final String UPDATE = "UPDATE menu set ";
	// 後台 由下架修改為上架
	private static final String UPDATESTATUS = "UPDATE menu set is_item = 1 where menu_id = ? AND shop_id = ?";
	// 前台 查詢一間店家菜單(上架中)
	private static final String GET_ByShopId = "SELECT menu_id, item, price, is_item, menu_upd FROM menu where shop_id = ? and is_item=1 ";
	// 前台 查詢一間店家菜單(下架)
	private static final String GET_ByShopId_Disable = "SELECT * FROM menu where shop_id = ? and is_item=0 ";
	// 後台 查詢各店家菜單
	private static final String GET_ALL_STMT = "SELECT * FROM menu ";
	//查詢一筆菜單項目
	private static final String GET_ONE_STMT = " SELECT menu_id, shop_id, item, price, is_item, menu_upd FROM menu where menu_id = ?";

	//===================================================================================================	

//	主揪一次新增多筆菜單
	@Override
	public void insertMany(List<MenuVO> listMenu) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			// 新增多筆項目
			for (MenuVO menuVO : listMenu) {
				pstmt.setInt(1, menuVO.getShop_id());
				pstmt.setString(2, menuVO.getItem());
				pstmt.setInt(3, menuVO.getPrice());
				pstmt.executeUpdate();
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}
	}
	
	// 前台 新增店家單品項目
	@Override
	public void insert(MenuVO menuVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, menuVO.getShop_id());
			pstmt.setString(2, menuVO.getItem());
			pstmt.setInt(3, menuVO.getPrice());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void update(MenuVO newmenu) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			MenuVO oldmenu = findByPrimaryKey(newmenu.getMenu_id());
		StringBuilder sb=new StringBuilder();
			sb.append(UPDATE);	
			
			if(newmenu.getShop_id() != null) {
				sb.append(" shop_id=?, ");
			}
			if(newmenu.getItem() != null) {
				sb.append(" item=?, ");
			}
			if(newmenu.getPrice() != null) {
				sb.append(" price=?, ");
			}
			if(newmenu.getIs_item() != null) {
				sb.append(" is_item=?, ");
			}
			sb.append(" menu_id = ? ");
			sb.append(" where menu_id = ? ");
			
			pstmt = con.prepareStatement(sb.toString());
			
			if(newmenu.getShop_id() != null) {
				count++;
				pstmt.setInt(count, newmenu.getShop_id());
			}
			if(newmenu.getItem() != null) {
				count++;
				pstmt.setString(count, newmenu.getItem());
			}
			if(newmenu.getPrice() != null) {
				count++;
				pstmt.setInt(count, newmenu.getPrice());
			}
			if(newmenu.getIs_item() != null) {
				count++;
				pstmt.setInt(count, newmenu.getIs_item());
			}
			
			count++;
			pstmt.setInt(count, newmenu.getMenu_id()); 
			count++;
			pstmt.setInt(count, newmenu.getMenu_id()); 
			
			pstmt.executeUpdate();
			System.out.println(count);
			
			// Handle any driver errors
			
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATESTATUS);

			pstmt.setInt(1, menuVO.getMenu_id());
			pstmt.setInt(2, menuVO.getShop_id());

			pstmt.executeUpdate();

			// Handle any driver errors
			
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
			
			con = ds.getConnection();
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

	//後台 以店家查詢菜單明細(下架)
	@Override
	public List<MenuVO> getByShopIdDisable(Integer shop_id) {
			List<MenuVO> list = new ArrayList<MenuVO>();
			MenuVO menuVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ByShopId_Disable);

				pstmt.setInt(1, shop_id);

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
			
			con = ds.getConnection();
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
			
			con = ds.getConnection();
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
