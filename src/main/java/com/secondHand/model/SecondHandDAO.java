package com.secondHand.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SecondHandDAO implements SecondHandDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CGA101G3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO second_hand (saler,name,bottom_price,top_price,start_time,end_time,img1,img2,img3) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
//	private static final String DELETE = "DELETE FROM second_hand where second_hand_id = ?";
	private static final String UPDATE = "UPDATE second_hand set bid_winner=?, deal_price=?, name=?, bottom_price=?, top_price=?, start_time=?, end_time=?, is_deal=?, img1=?, img2=?, img3=? where second_hand_id = ?";
//	private static final String UPDATE = "UPDATE second_hand set";
	private static final String GET_BY_ID = "SELECT second_hand_id,saler,bid_winner,deal_price,name,bottom_price,top_price,start_time,end_time,is_deal,img1,img2,img3,create_time,update_time FROM second_hand where second_hand_id = ?";
	private static final String GET_BY_NAME = "SELECT second_hand_id,saler,bid_winner,deal_price,name,bottom_price,top_price,start_time,end_time,is_deal,img1,img2,img3,create_time,update_time FROM second_hand where name like \"%\"?\"%\"";
	private static final String GET_ALL_STMT = "SELECT second_hand_id,saler,bid_winner,deal_price,name,bottom_price,top_price,start_time,end_time,is_deal,img1,img2,img3,create_time,update_time FROM second_hand order by second_hand_id";

	@Override
	public void insert(SecondHandVO secondHandVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, secondHandVO.getSaler());
			pstmt.setString(2, secondHandVO.getName());
			pstmt.setInt(3, secondHandVO.getBottom_price());
			pstmt.setInt(4, secondHandVO.getTop_price());
			pstmt.setTimestamp(5, secondHandVO.getStart_time());
			pstmt.setTimestamp(6, secondHandVO.getEnd_time());
			pstmt.setString(7, secondHandVO.getImg1());
			pstmt.setString(8, secondHandVO.getImg2());
			pstmt.setString(9, secondHandVO.getImg3());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
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

//	@Override
//	public void update(SecondHandVO secondHandVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setInt(1, secondHandVO.getBid_winner());
//			pstmt.setInt(2, secondHandVO.getDeal_price());
//			pstmt.setString(3, secondHandVO.getName());
//			pstmt.setInt(4, secondHandVO.getBottom_price());
//			pstmt.setInt(5, secondHandVO.getTop_price());
//			pstmt.setTimestamp(6, secondHandVO.getStart_time());
//			pstmt.setTimestamp(7, secondHandVO.getEnd_time());
//			pstmt.setInt(8, secondHandVO.getIs_deal());
//			pstmt.setString(9, secondHandVO.getImg1());
//			pstmt.setString(10, secondHandVO.getImg2());
//			pstmt.setString(11, secondHandVO.getImg3());
//			pstmt.setInt(12, secondHandVO.getsecond_hand_id());
//
//			pstmt.executeUpdate();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}

	@Override
	public void update(SecondHandVO secondHandVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, secondHandVO.getBid_winner());
			pstmt.setInt(2, secondHandVO.getDeal_price());
			pstmt.setString(3, secondHandVO.getName());
			pstmt.setInt(4, secondHandVO.getBottom_price());
			pstmt.setInt(5, secondHandVO.getTop_price());
			pstmt.setTimestamp(6, secondHandVO.getStart_time());
			pstmt.setTimestamp(7, secondHandVO.getEnd_time());
			pstmt.setInt(8, secondHandVO.getIs_deal());
			pstmt.setString(9, secondHandVO.getImg1());
			pstmt.setString(10, secondHandVO.getImg2());
			pstmt.setString(11, secondHandVO.getImg3());
			pstmt.setInt(12, secondHandVO.getsecond_hand_id());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
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
	public SecondHandVO getById(Integer second_hand_id) {
		SecondHandVO secondHandVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_ID);

			pstmt.setInt(1, second_hand_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				secondHandVO = new SecondHandVO();
				secondHandVO.setsecond_hand_id(rs.getInt("second_hand_id"));
				secondHandVO.setSaler(rs.getInt("saler"));
				secondHandVO.setBid_winner(rs.getInt("bid_winner"));
				secondHandVO.setDeal_price(rs.getInt("deal_price"));
				secondHandVO.setName(rs.getString("name"));
				secondHandVO.setBottom_price(rs.getInt("bottom_price"));
				secondHandVO.setTop_price(rs.getInt("top_price"));
				secondHandVO.setStart_time(rs.getTimestamp("start_time"));
				secondHandVO.setEnd_time(rs.getTimestamp("end_time"));
				secondHandVO.setIs_deal(rs.getInt("is_deal"));
				secondHandVO.setImg1(rs.getString("img1"));
				secondHandVO.setImg2(rs.getString("img2"));
				secondHandVO.setImg3(rs.getString("img3"));
				secondHandVO.setCreate_time(rs.getTimestamp("create_time"));
				secondHandVO.setUpdate_time(rs.getTimestamp("update_time"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return secondHandVO;
	}

	@Override
	public List<SecondHandVO> getByName(String name) {
		List<SecondHandVO> list = new ArrayList<SecondHandVO>();
		SecondHandVO secondHandVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_NAME);

			pstmt.setString(1, name);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				secondHandVO = new SecondHandVO();
				secondHandVO.setsecond_hand_id(rs.getInt("second_hand_id"));
				secondHandVO.setSaler(rs.getInt("saler"));
				secondHandVO.setBid_winner(rs.getInt("bid_winner"));
				secondHandVO.setDeal_price(rs.getInt("deal_price"));
				secondHandVO.setName(rs.getString("name"));
				secondHandVO.setBottom_price(rs.getInt("bottom_price"));
				secondHandVO.setTop_price(rs.getInt("top_price"));
				secondHandVO.setStart_time(rs.getTimestamp("start_time"));
				secondHandVO.setEnd_time(rs.getTimestamp("end_time"));
				secondHandVO.setIs_deal(rs.getInt("is_deal"));
				secondHandVO.setImg1(rs.getString("img1"));
				secondHandVO.setImg2(rs.getString("img2"));
				secondHandVO.setImg3(rs.getString("img3"));
				secondHandVO.setCreate_time(rs.getTimestamp("create_time"));
				secondHandVO.setUpdate_time(rs.getTimestamp("update_time"));
				list.add(secondHandVO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
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
	public List<SecondHandVO> getAll() {
		List<SecondHandVO> list = new ArrayList<SecondHandVO>();
		SecondHandVO secondHandVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				secondHandVO = new SecondHandVO();
				secondHandVO.setsecond_hand_id(rs.getInt("second_hand_id"));
				secondHandVO.setSaler(rs.getInt("saler"));
				secondHandVO.setBid_winner(rs.getInt("bid_winner"));
				secondHandVO.setDeal_price(rs.getInt("deal_price"));
				secondHandVO.setName(rs.getString("name"));
				secondHandVO.setBottom_price(rs.getInt("bottom_price"));
				secondHandVO.setTop_price(rs.getInt("top_price"));
				secondHandVO.setStart_time(rs.getTimestamp("start_time"));
				secondHandVO.setEnd_time(rs.getTimestamp("end_time"));
				secondHandVO.setIs_deal(rs.getInt("is_deal"));
				secondHandVO.setImg1(rs.getString("img1"));
				secondHandVO.setImg2(rs.getString("img2"));
				secondHandVO.setImg3(rs.getString("img3"));
				secondHandVO.setCreate_time(rs.getTimestamp("create_time"));
				secondHandVO.setUpdate_time(rs.getTimestamp("update_time"));
				list.add(secondHandVO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
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

		SecondHandDAO dao = new SecondHandDAO();

		// 新增
//		SecondHandVO secondHandVO1 = new SecondHandVO();
//		secondHandVO1.setSaler(1015);
//		secondHandVO1.setName("埼玉的頭髮");
//		secondHandVO1.setBottom_price(10);
//		secondHandVO1.setTop_price(100);
//		secondHandVO1.setStart_time(java.sql.Timestamp.valueOf("2022-04-01 00:00:00"));
//		secondHandVO1.setEnd_time(java.sql.Timestamp.valueOf("2022-04-01 00:30:00"));
//		secondHandVO1.setImg1(null);
//		secondHandVO1.setImg2(null);
//		secondHandVO1.setImg3(null);
//		dao.insert(secondHandVO1);

		// 修改
//		SecondHandVO secondHandVO2 = new SecondHandVO();
//		secondHandVO2.setsecond_hand_id(1009);
//		secondHandVO2.setBid_winner(1014);
//		secondHandVO2.setDeal_price(50);
//		secondHandVO2.setName("埼玉的兩根頭髮");
//		secondHandVO2.setBottom_price(20);
//		secondHandVO2.setTop_price(200);
//		secondHandVO2.setStart_time(java.sql.Timestamp.valueOf("2022-04-01 01:00:00"));
//		secondHandVO2.setEnd_time(java.sql.Timestamp.valueOf("2022-04-01 01:30:00"));
//		secondHandVO2.setIs_deal(1);
//		secondHandVO2.setImg1(null);
//		secondHandVO2.setImg2(null);
//		secondHandVO2.setImg3(null);
//		dao.update(secondHandVO2);

		// 查詢 by id
//		SecondHandVO secondHandVO3 = dao.getById(1005);
//		System.out.print(secondHandVO3.getsecond_hand_id() + ",");
//		System.out.print(secondHandVO3.getSaler() + ",");
//		System.out.print(secondHandVO3.getBid_winner() + ",");
//		System.out.print(secondHandVO3.getDeal_price() + ",");
//		System.out.print(secondHandVO3.getName() + ",");
//		System.out.print(secondHandVO3.getBottom_price() + ",");
//		System.out.print(secondHandVO3.getTop_price() + ",");
//		System.out.print(secondHandVO3.getStart_time() + ",");
//		System.out.print(secondHandVO3.getEnd_time() + ",");
//		System.out.print(secondHandVO3.getIs_deal() + ",");
//		System.out.print(secondHandVO3.getImg1() + "test1,");
//		System.out.print(secondHandVO3.getImg2() + "2,");
//		System.out.print(secondHandVO3.getImg3() + "3,");
//		System.out.print(secondHandVO3.getCreate_time() + ",");
//		System.out.println(secondHandVO3.getUpdate_time());

		// 查詢 by name
//		List<SecondHandVO> list = dao.getByName("PTCG");
//		for (SecondHandVO listSecondHandVO : list) {
//			System.out.print(listSecondHandVO.getsecond_hand_id() + ",");
//			System.out.print(listSecondHandVO.getSaler() + ",");
//			System.out.print(listSecondHandVO.getBid_winner() + ",");
//			System.out.print(listSecondHandVO.getDeal_price() + ",");
//			System.out.print(listSecondHandVO.getName() + ",");
//			System.out.print(listSecondHandVO.getBottom_price() + ",");
//			System.out.print(listSecondHandVO.getTop_price() + ",");
//			System.out.print(listSecondHandVO.getStart_time() + ",");
//			System.out.print(listSecondHandVO.getEnd_time() + ",");
//			System.out.print(listSecondHandVO.getIs_deal() + ",");
//			System.out.print(listSecondHandVO.getImg1() + ",");
//			System.out.print(listSecondHandVO.getImg2() + ",");
//			System.out.print(listSecondHandVO.getImg3() + ",");
//			System.out.print(listSecondHandVO.getCreate_time() + ",");
//			System.out.println(listSecondHandVO.getUpdate_time());
//		}

		// 查詢
//		List<SecondHandVO> list1 = dao.getAll();
//		for (SecondHandVO listSecondHandVO : list1) {
//			System.out.print(listSecondHandVO.getsecond_hand_id() + ",");
//			System.out.print(listSecondHandVO.getSaler() + ",");
//			System.out.print(listSecondHandVO.getBid_winner() + ",");
//			System.out.print(listSecondHandVO.getDeal_price() + ",");
//			System.out.print(listSecondHandVO.getName() + ",");
//			System.out.print(listSecondHandVO.getBottom_price() + ",");
//			System.out.print(listSecondHandVO.getTop_price() + ",");
//			System.out.print(listSecondHandVO.getStart_time() + ",");
//			System.out.print(listSecondHandVO.getEnd_time() + ",");
//			System.out.print(listSecondHandVO.getIs_deal() + ",");
//			System.out.print(listSecondHandVO.getImg1() + ",");
//			System.out.print(listSecondHandVO.getImg2() + ",");
//			System.out.print(listSecondHandVO.getImg3() + ",");
//			System.out.print(listSecondHandVO.getCreate_time() + ",");
//			System.out.println(listSecondHandVO.getUpdate_time());
//		}
	}
}
