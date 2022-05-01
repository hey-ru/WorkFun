package com.groupbuy.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.groupbuylist.model.*;


public class GroupBuyDAO implements GroupBuyDAO_interface {

	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CGA101G3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO groupbuy (shop_id,shop_name,gb_owner,start_time,end_time,arr_time,min_amt) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM groupbuy order by gb_id";
	private static final String UPDATE = "UPDATE groupbuy set shop_id=?, shop_name=?, gb_owner=?, start_time=?, end_time=?, arr_time=?, min_amt=? where gb_id = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM groupbuy where gb_id = ?";
	private static final String GET_GBLIST_BYGB_ID_STMT = "SELECT * FROM groupbuylist WHERE gb_id = ?";
	private static final String GET_NOW_ALL_STMT = "SELECT * FROM groupbuy WHERE gb_status = 1 ORDER BY end_time";
	
	
	
	@Override
	public void insert(GroupBuyVO groupBuyVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, groupBuyVO.getShop_id());
			pstmt.setString(2, groupBuyVO.getShop_name());
			pstmt.setInt(3, groupBuyVO.getGb_owner());
			pstmt.setTimestamp(4, groupBuyVO.getStart_time());
			pstmt.setTimestamp(5, groupBuyVO.getEnd_time());
			pstmt.setTimestamp(6, groupBuyVO.getArr_time());
			pstmt.setInt(7, groupBuyVO.getMin_amt());

			pstmt.executeUpdate();

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
	public void update(GroupBuyVO groupBuyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, groupBuyVO.getShop_id());
			pstmt.setString(2, groupBuyVO.getShop_name());
			pstmt.setInt(3, groupBuyVO.getGb_owner());
			pstmt.setTimestamp(4, groupBuyVO.getStart_time());
			pstmt.setTimestamp(5, groupBuyVO.getEnd_time());
			pstmt.setTimestamp(6, groupBuyVO.getArr_time());
			pstmt.setInt(7, groupBuyVO.getMin_amt());
			pstmt.setInt(8, groupBuyVO.getGb_id());

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

	@Override
	public GroupBuyVO findByPrimaryKey(Integer gb_id) {
		GroupBuyVO groupBuyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
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

	public List<GroupBuyVO> getNowAll() {
		List<GroupBuyVO> list = new ArrayList<GroupBuyVO>();
		GroupBuyVO groupBuyVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
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

	}


