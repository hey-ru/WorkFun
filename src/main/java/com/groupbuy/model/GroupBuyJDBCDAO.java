package com.groupbuy.model;

import java.util.*;

import com.groupbuylist.model.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class GroupBuyJDBCDAO implements GroupBuyDAO_interface {
//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/dbcga101g3?serverTimezone=Asia/Taipei";
//	String userid = "root";
//	String passwd = "1qaz2wsx";
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://database-1.cqm5mb4z5ril.ap-northeast-1.rds.amazonaws.com:3306/CGA101-03?serverTimezone=Asia/Taipei";
	String userid = "cga101-03";
	String passwd = "cga101-03";

	private static final String INSERT_STMT = "INSERT INTO groupbuy (shop_id,shop_name,gb_owner,start_time,end_time,arr_time,min_amt) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM groupbuy order by gb_id";
	private static final String UPDATEARRTIME = "UPDATE groupbuy set arr_time=? where gb_id = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM groupbuy where gb_id = ?";
	private static final String GET_GBLIST_BYGB_ID_STMT = "SELECT * FROM groupbuylist WHERE gb_id = ?";
	private static final String GET_NOW_ALL_STMT = "SELECT * FROM groupbuy WHERE gb_status = 0 ORDER BY end_time";
	private static final String GET_MY_ALL_STMT = "SELECT * FROM groupbuy WHERE gb_owner=? ORDER BY gb_status";
	private static final String GET_BUYER_BYGB_ID_STMT = "SELECT buyer, buyer_name, sum(price*qty) AS total, is_pay, is_pickup FROM groupbuylist WHERE gb_id = ? GROUP BY buyer";
	
	@Override
	public Set<GroupBuyListVO> getBuyerBygbid(Integer gb_id) {
		Set<GroupBuyListVO> set = new LinkedHashSet<GroupBuyListVO>();
		GroupBuyListVO groupBuyListVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BUYER_BYGB_ID_STMT);
			pstmt.setInt(1, gb_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				groupBuyListVO = new GroupBuyListVO();
				groupBuyListVO.setBuyer(rs.getInt("buyer"));
				groupBuyListVO.setBuyer_name(rs.getString("buyer_name"));
				groupBuyListVO.setTotal(rs.getInt("total"));
				groupBuyListVO.setIs_pay(rs.getInt("is_pay"));
				groupBuyListVO.setIs_pickup(rs.getInt("is_pickup"));
				set.add(groupBuyListVO);
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
		return set;
	}
	
	
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
			if(groupBuyVO.getArr_time() != null) {
				pstmt.setTimestamp(6, groupBuyVO.getArr_time());
			}else {
				pstmt.setNull(6, java.sql.Types.TIMESTAMP);
			}
			pstmt.setInt(7, groupBuyVO.getMin_amt());

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
	public void updateArrTime(GroupBuyVO groupBuyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATEARRTIME);
			
			pstmt.setTimestamp(1, groupBuyVO.getArr_time());
			pstmt.setInt(2, groupBuyVO.getGb_id());

//			pstmt.setInt(1, groupBuyVO.getShop_id());
//			pstmt.setString(2, groupBuyVO.getShop_name());
//			pstmt.setInt(3, groupBuyVO.getGb_owner());
//			pstmt.setTimestamp(4, groupBuyVO.getStart_time());
//			pstmt.setTimestamp(5, groupBuyVO.getEnd_time());
//			pstmt.setInt(7, groupBuyVO.getMin_amt());
			

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
	public GroupBuyVO findByPrimaryKey(Integer gb_id) {
		GroupBuyVO groupBuyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, gb_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				groupBuyVO = new GroupBuyVO();
				groupBuyVO.setGb_id(rs.getInt("gb_id"));
				groupBuyVO.setShop_id(rs.getInt("shop_id"));
				groupBuyVO.setShop_name(rs.getString("shop_name"));
				groupBuyVO.setGb_owner(rs.getInt("gb_owner"));
				groupBuyVO.setStart_time(rs.getTimestamp("start_time"));
				groupBuyVO.setEnd_time(rs.getTimestamp("end_time"));
				groupBuyVO.setArr_time(rs.getTimestamp("arr_time"));
				groupBuyVO.setGb_status(rs.getInt("gb_status"));
				groupBuyVO.setMin_amt(rs.getInt("min_amt"));

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
		return groupBuyVO;
	}

	@Override
	public List<GroupBuyVO> getAll() {
		List<GroupBuyVO> list = new ArrayList<GroupBuyVO>();
		GroupBuyVO groupBuyVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				groupBuyVO = new GroupBuyVO();
				groupBuyVO.setGb_id(rs.getInt("gb_id"));
				groupBuyVO.setShop_id(rs.getInt("shop_id"));
				groupBuyVO.setShop_name(rs.getString("shop_name"));
				groupBuyVO.setGb_owner(rs.getInt("gb_owner"));
				groupBuyVO.setStart_time(rs.getTimestamp("start_time"));
				groupBuyVO.setEnd_time(rs.getTimestamp("end_time"));
				groupBuyVO.setArr_time(rs.getTimestamp("arr_time"));
				groupBuyVO.setGb_status(rs.getInt("gb_status"));
				groupBuyVO.setMin_amt(rs.getInt("min_amt"));
				list.add(groupBuyVO);
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
	public List<GroupBuyVO> getNowAll() {
		List<GroupBuyVO> list = new ArrayList<GroupBuyVO>();
		GroupBuyVO groupBuyVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_NOW_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				groupBuyVO = new GroupBuyVO();
				groupBuyVO.setGb_id(rs.getInt("gb_id"));
				groupBuyVO.setShop_id(rs.getInt("shop_id"));
				groupBuyVO.setShop_name(rs.getString("shop_name"));
				groupBuyVO.setGb_owner(rs.getInt("gb_owner"));
				groupBuyVO.setStart_time(rs.getTimestamp("start_time"));
				groupBuyVO.setEnd_time(rs.getTimestamp("end_time"));
				groupBuyVO.setArr_time(rs.getTimestamp("arr_time"));
				groupBuyVO.setGb_status(rs.getInt("gb_status"));
				groupBuyVO.setMin_amt(rs.getInt("min_amt"));
				list.add(groupBuyVO);
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
	public List<GroupBuyVO> getMyGBAll(Integer gb_owner) {
		
		List<GroupBuyVO> list = new ArrayList<GroupBuyVO>();
		GroupBuyVO groupBuyVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_MY_ALL_STMT);
			pstmt.setInt(1, gb_owner);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				groupBuyVO = new GroupBuyVO();
				groupBuyVO.setGb_id(rs.getInt("gb_id"));
				groupBuyVO.setShop_id(rs.getInt("shop_id"));
				groupBuyVO.setShop_name(rs.getString("shop_name"));
				groupBuyVO.setGb_owner(rs.getInt("gb_owner"));
				groupBuyVO.setStart_time(rs.getTimestamp("start_time"));
				groupBuyVO.setEnd_time(rs.getTimestamp("end_time"));
				groupBuyVO.setArr_time(rs.getTimestamp("arr_time"));
				groupBuyVO.setGb_status(rs.getInt("gb_status"));
				groupBuyVO.setMin_amt(rs.getInt("min_amt"));
				list.add(groupBuyVO);
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
	public Set<GroupBuyListVO> getGBListBygbid(Integer gb_id) {
		Set<GroupBuyListVO> set = new LinkedHashSet<GroupBuyListVO>();
		GroupBuyListVO groupBuyListVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_GBLIST_BYGB_ID_STMT);
			pstmt.setInt(1, gb_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				groupBuyListVO = new GroupBuyListVO();
				groupBuyListVO.setGbList_id(rs.getInt("gbList_id"));
				groupBuyListVO.setGb_id(rs.getInt("gb_id"));
				groupBuyListVO.setBuyer(rs.getInt("buyer"));
				groupBuyListVO.setBuyer_name(rs.getString("buyer_name"));
				groupBuyListVO.setMenu_id(rs.getInt("menu_id"));
				groupBuyListVO.setItem(rs.getString("item"));
				groupBuyListVO.setPrice(rs.getInt("price"));
				groupBuyListVO.setQty(rs.getInt("qty"));
				groupBuyListVO.setRemark(rs.getString("remark"));
				groupBuyListVO.setIs_pay(rs.getInt("is_pay"));
				groupBuyListVO.setIs_pickup(rs.getInt("is_pickup"));
				groupBuyListVO.setGbList_upd(rs.getTimestamp("gbList_upd"));
				set.add(groupBuyListVO);
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
		return set;
	}

	public static void main(String[] args) throws IOException {

		GroupBuyJDBCDAO dao = new GroupBuyJDBCDAO();
		
		// 查詢參團資訊
		Set<GroupBuyListVO> set = dao.getBuyerBygbid(1001);
		for (GroupBuyListVO aGroupBuyList : set) {
			System.out.println(aGroupBuyList.toString());
			System.out.println();
	}

		// 新增
//		GroupBuyVO groupBuyVO1 = new GroupBuyVO();
//		groupBuyVO1.setShop_id(107);
//		groupBuyVO1.setShop_name("可不可熟成紅茶-中壢中山店");
//		groupBuyVO1.setGb_owner(1001);
//		groupBuyVO1.setStart_time(new Timestamp(System.currentTimeMillis()));
//		groupBuyVO1.setEnd_time(Timestamp.valueOf("2022-05-20 11:00:00.0"));
//		groupBuyVO1.setArr_time(Timestamp.valueOf("2022-05-30 13:00:00.0"));
//		groupBuyVO1.setMin_amt(0);
//		dao.insert(groupBuyVO1);

		// 修改
//		GroupBuyVO groupBuyVO2 = new GroupBuyVO();
//		groupBuyVO2.setGb_id(1002);
//		groupBuyVO2.setShop_id(101);
//		groupBuyVO2.setShop_name("思茶MissingTea手作飲品-內壢忠孝店");
//		groupBuyVO2.setGb_owner(1002);
//		groupBuyVO2.setStart_time(Timestamp.valueOf("2022-04-21 10:50:00.0"));
//		groupBuyVO2.setEnd_time(Timestamp.valueOf("2022-04-22 15:50:00.0"));
//		groupBuyVO2.setArr_time(Timestamp.valueOf("2022-05-30 08:50:00.0"));
//		groupBuyVO2.setMin_amt(100);
//		dao.update(groupBuyVO2);

		// 查詢
//		GroupBuyVO groupBuyVO3 = dao.findByPrimaryKey(1001);
//		System.out.println(groupBuyVO3.toString());
//		System.out.println("---------------------");
//
//		// 查詢
//		List<GroupBuyVO> list = dao.getAll();
//		for (GroupBuyVO aGroupBuy : list) {
//			System.out.println(aGroupBuy.toString());
//			System.out.println();
//		}
		
		// 查詢
//		Set<GroupBuyListVO> set = dao.getGBListBygbid(1001);
//		for (GroupBuyListVO aGroupBuyList : set) {
//			System.out.println(aGroupBuyList.toString());
//			System.out.println();
//	}
		// 查詢開團中
//		List<GroupBuyVO> list = dao.getNowAll();
//		for (GroupBuyVO aGroupBuy : list) {
//			System.out.println(aGroupBuy.toString());
//			System.out.println();
//		}
		
		// 查詢我的團
//		List<GroupBuyVO> list = dao.getMyGBAll(1010);
//		for (GroupBuyVO aGroupBuy : list) {
//			System.out.println(aGroupBuy.toString());
//			System.out.println();
//		}
		
	}

	
}
