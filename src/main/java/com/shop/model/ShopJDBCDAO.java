package com.shop.model;

import java.util.*;

import com.menu.model.*;
import com.util.jdbcUtil_CompositeQuery;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class ShopJDBCDAO implements ShopDAO_interface {
//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/dbcga101g3?serverTimezone=Asia/Taipei";
//	String userid = "root";
//	String passwd = "1qaz2wsx";
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://cga101-03@database-1.cqm5mb4z5ril.ap-northeast-1.rds.amazonaws.com:3306/CGA101-03?serverTimezone=Asia/Taipei";
	String userid = "cga101-03";
	String passwd = "cga101-03";

	private static final String INSERT_STMT = "INSERT INTO shop (shop_name,shop_type,address,tel,website,min_amt,shop_img1,shop_img2,shop_img3) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM shop ORDER BY shop_id DESC";
	private static final String GET_ALL_FRONT_STMT = "SELECT * FROM shop WHERE is_disable = 0 ORDER BY shop_id DESC";
	private static final String UPDATE = "UPDATE shop set shop_name=?, shop_type=?, address=?, tel=?, website=?, min_amt=?, shop_img1=?, shop_img2=?, shop_img3=? WHERE shop_id = ?";
	private static final String GET_ONE_STMT = "SELECT shop_id,shop_name,shop_type,address,tel,website,min_amt,shop_img1,shop_img2,shop_img3,is_disable,shop_upd FROM shop where shop_id = ?";
	private static final String GET_BY_SETWHERE = "SELECT * FROM shop ";
	private static final String GET_Menus_ByShop_id_STMT = "SELECT * FROM menu WHERE shop_id = ? ORDER BY menu_id";
	private static final String GET_BY_SETWHERE_STMT = "SELECT * FROM shop s ";

	@Override
	public void insert(ShopVO shopVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void update(ShopVO shopVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public ShopVO findByPrimaryKey(Integer shop_id) {
		ShopVO shopVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	
	@Override
	public List<ShopVO> getNOWAll() {
		List<ShopVO> list = new ArrayList<ShopVO>();
		ShopVO shopVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_FRONT_STMT);
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

	public List<ShopVO> findByShopName(String shop_name) {

		List<ShopVO> list = new ArrayList<ShopVO>();
		ShopVO shopVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String str = GET_BY_SETWHERE + " where shop_name like ? ";
			pstmt = con.prepareStatement(str);

			pstmt.setString(1, "%" + shop_name + "%");
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

	public List<ShopVO> findByShopType(Integer shop_type) {

		List<ShopVO> list = new ArrayList<ShopVO>();
		ShopVO shopVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String str = GET_BY_SETWHERE + " where shop_type = ? ";
			pstmt = con.prepareStatement(str);

			pstmt.setInt(1, shop_type);

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
	
	@Override
	public Set<MenuVO> getMenusByShopid(Integer shop_id) {
		Set<MenuVO> set = new LinkedHashSet<MenuVO>();
		MenuVO menuVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_Menus_ByShop_id_STMT);
			pstmt.setInt(1, shop_id);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				menuVO = new MenuVO();
				menuVO.setMenu_id(rs.getInt("menu_id"));
				menuVO.setShop_id(rs.getInt("shop_id"));
				menuVO.setItem(rs.getString("item"));
				menuVO.setPrice(rs.getInt("price"));
				menuVO.setIs_item(rs.getInt("is_item"));
				menuVO.setMenu_upd(rs.getTimestamp("menu_upd"));
				set.add(menuVO); 
			}
			// Handle any driver errors
					} catch (ClassNotFoundException e) {
						throw new RuntimeException("Couldn't load database driver. "
								+ e.getMessage());
						// Handle any SQL errors
					} catch (SQLException se) {
						throw new RuntimeException("A database error occured. "
								+ se.getMessage());
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
					return set;
				}
	
	//同時新增店家菜單 未完成
	@Override
	public void insertWithMenus(ShopVO shopVO, List<MenuVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先新增店家
    		String cols[] = {"SHOP_ID"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);
			pstmt.setString(1, shopVO.getShop_name());
			pstmt.setInt(2, shopVO.getShop_type());
			pstmt.setString(3, shopVO.getAddress());
			pstmt.setString(4, shopVO.getTel());
			pstmt.setString(5, shopVO.getWebsite());
			pstmt.setInt(6, shopVO.getMin_amt());
			pstmt.setBytes(7, shopVO.getShop_img1());
			pstmt.setBytes(8, shopVO.getShop_img2());
			pstmt.setBytes(9, shopVO.getShop_img3());
			Statement stmt=	con.createStatement();
			pstmt.executeUpdate();
			//掘取對應的自增主鍵值
			String next_shop_id = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_shop_id = rs.getString(1);
				System.out.println("自增主鍵值= " + next_shop_id +"(剛新增成功的部門編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增菜單
						MenuJDBCDAO dao = new MenuJDBCDAO();
						System.out.println("list.size()-A="+list.size());
						for (MenuVO aMenu : list) {
							aMenu.setShop_id(new Integer(next_shop_id)) ;
							//dao.insertWithShop(aMenu,con);
						}
						// 2●設定於 pstm.executeUpdate()之後
						con.commit();
						con.setAutoCommit(true);
						
						// Handle any driver errors
					} catch (ClassNotFoundException e) {
						throw new RuntimeException("Couldn't load database driver. "
								+ e.getMessage());
						// Handle any SQL errors
					} catch (SQLException se) {
						if (con != null) {
							try {
								// 3●設定於當有exception發生時之catch區塊內
								System.err.print("Transaction is being ");
								System.err.println("rolled back-由-shop");
								con.rollback();
							} catch (SQLException excep) {
								throw new RuntimeException("rollback error occured. "
										+ excep.getMessage());
							}
						}
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
	public List<ShopVO> getAll(Map<String, String[]> map) {
		List<ShopVO> list = new ArrayList<ShopVO>();
		ShopVO shopVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			String finalSQL = GET_BY_SETWHERE_STMT
		          + jdbcUtil_CompositeQuery.get_WhereCondition(map)
		          + " ORDER BY shop_id DESC ";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
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

	public static void main(String[] args) {

		ShopJDBCDAO dao = new ShopJDBCDAO();

		// 新增
//		ShopVO shopVO1 = new ShopVO();
//		shopVO1.setShop_name("大同町");
//		shopVO1.setShop_type(2);
//		shopVO1.setAddress("320桃園市中壢區大同路70號");
//		shopVO1.setTel("03-4224695");
//		shopVO1.setWebsite(
//				"https://www.facebook.com/%E5%A4%A7%E5%90%8C%E7%94%BA-%E5%B9%B3%E5%83%B9%E6%97%A5%E5%BC%8F%E8%B1%AC%E6%8E%92%E7%82%B8%E9%9B%9E%E5%A1%8A%E4%B8%BC%E9%A3%AF-%E5%B0%88%E8%B3%A3-2019007434995879/");
//		shopVO1.setMin_amt(0);
//		byte[] vo1_img1 = null;
//		byte[] vo1_img2 = null;
//		byte[] vo1_img3 = null;
//		try {
//			vo1_img1 = getPictureByteArray("C:\\images\\FC_Barcelona.png");
//			vo1_img2 = getPictureByteArray("C:\\images\\FC_Bayern.png");
//			vo1_img3 = getPictureByteArray("C:\\images\\FC_Real_Madrid.png");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		shopVO1.setShop_img1(vo1_img1);
//		shopVO1.setShop_img2(vo1_img2);
//		shopVO1.setShop_img3(vo1_img3);
//		dao.insert(shopVO1);

		// 修改
//		ShopVO shopVO2 = new ShopVO();
//		shopVO2.setShop_id(102);
//		shopVO2.setShop_name("可不可熟成紅茶-中壢中山店");
//		shopVO2.setShop_type(0);
//		shopVO2.setAddress("320桃園市中壢區中正路29號");
//		shopVO2.setTel("03-4224008");
//		shopVO2.setWebsite("https://www.facebook.com/kebuke4224008/");
//		shopVO2.setMin_amt(100);
//		byte[] vo2_img1 = null;
//		byte[] vo2_img2 = null;
//		byte[] vo2_img3 = null;
//		try {
//			vo2_img1 = getPictureByteArray("C:\\images\\boss.jpg");
//			// vo2_img2 = getPictureByteArray("C:\\images\\boss.jpg");
//			// vo2_img3 = getPictureByteArray("C:\\images\\drink2-2.jpg");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		shopVO2.setShop_img1(vo2_img1);
//		shopVO2.setShop_img2(vo2_img2);
//		shopVO2.setShop_img3(vo2_img3);
//		dao.update(shopVO2);

		// 查詢
//		ShopVO shopVO3 = dao.findByPrimaryKey(101);
//		System.out.println(shopVO3.toString());
		
//		System.out.print(shopVO3.getShop_id() + ",");
//		System.out.print(shopVO3.getShop_name() + ",");
//		System.out.print(shopVO3.getShop_type() + ",");
//		System.out.print(shopVO3.getAddress() + ",");
//		System.out.print(shopVO3.getTel() + ",");
//		System.out.print(shopVO3.getWebsite() + ",");
//		System.out.print(shopVO3.getMin_amt() + ",");
//		System.out.print(shopVO3.getShop_img1() + ",");
//		System.out.print(shopVO3.getShop_img2() + ",");
//		System.out.println(shopVO3.getShop_img3());
//		System.out.println("---------------------");
//
		
		// 查詢
//		List<ShopVO> listShopName = dao.findByShopName("可");
//		for (ShopVO aShopByName : listShopName) {
//			System.out.print(aShopByName.toString());
//			System.out.println();
//		}
		
		// 查詢
//		List<ShopVO> listShopType = dao.findByShopType(0);
//		for (ShopVO aShopByType : listShopType) {
//			System.out.print(aShopByType.toString());
//			System.out.println();
//		}

		// 查詢
//		List<ShopVO> list = dao.getAll();
//		for (ShopVO aShop : list) {
//			System.out.print(aShop.toString());
//			System.out.println();

		
//			System.out.print(aShop.getShop_id() + ",");
//			System.out.print(aShop.getShop_name() + ",");
//			System.out.print(aShop.getShop_type() + ",");
//			System.out.print(aShop.getAddress() + ",");
//			System.out.print(aShop.getTel() + ",");
//			System.out.print(aShop.getWebsite() + ",");
//			System.out.print(aShop.getMin_amt() + ",");
//			System.out.print(aShop.getShop_img1() + ",");
//			System.out.print(aShop.getShop_img2() + ",");
//			System.out.println(aShop.getShop_img3());
//			System.out.println();
//		}
		
		// 查詢某家電店的菜單
//				Set<MenuVO> set = dao.getMenusByShopid(101);
//				for (MenuVO aMenu : set) {
//					System.out.println(aMenu.toString());
//				}
//				System.out.println("完成");
	   //同時新增店家菜單 未完成
				
//				ShopVO shopVO1 = new ShopVO();
//				shopVO1.setShop_name("一二三");
//				shopVO1.setShop_type(2);
//				shopVO1.setAddress("320桃園市中壢區一二三路123號");
//				shopVO1.setTel("03-0987654");
//				shopVO1.setWebsite(" ");
//				shopVO1.setMin_amt(0);
//				byte[] vo1_img1 = null;
//				byte[] vo1_img2 = null;
//				byte[] vo1_img3 = null;
//				
//				shopVO1.setShop_img1(vo1_img1);
//				shopVO1.setShop_img2(vo1_img2);
//				shopVO1.setShop_img3(vo1_img3);
//				dao.insert(shopVO1);
	
	}

	
	
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}





	
}
