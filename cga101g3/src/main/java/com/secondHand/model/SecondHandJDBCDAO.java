package com.secondHand.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SecondHandJDBCDAO implements SecondHandDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://database-1.cqm5mb4z5ril.ap-northeast-1.rds.amazonaws.com:3306/CGA101-03?serverTimezone=Asia/Taipei";
	String userid = "cga101-03";
	String passwd = "cga101-03";

	private static final String INSERT_STMT = "INSERT INTO second_hand (saler,name,bottom_price,top_price,start_time,end_time,img1,img2,img3) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT second_hand_id,saler,bid_winner,deal_price,name,bottom_price,top_price,start_time,end_time,is_deal,img1,img2,img3,create_time,update_time FROM second_hand order by second_hand_id";
	private static final String GET_ONE_STMT = "SELECT second_hand_id,saler,bid_winner,deal_price,name,bottom_price,top_price,start_time,end_time,is_deal,img1,img2,img3,create_time,update_time FROM second_hand where second_hand_id = ?";
//	private static final String DELETE = "DELETE FROM second_hand where second_hand_id = ?";
	private static final String UPDATE = "UPDATE second_hand set bid_winner=?, deal_price=?, name=?, bottom_price=?, top_price=?, start_time=?, end_time=?, is_deal=?, img1=?, img2=?, img3=? where second_hand_id = ?";
//	private static final String UPDATE = "UPDATE second_hand set";

	@Override
	public void insert(SecondHandVO secondHandVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, secondHandVO.getSaler());
			pstmt.setString(2, secondHandVO.getName());
			pstmt.setInt(3, secondHandVO.getBottom_price());
			pstmt.setInt(4, secondHandVO.getTop_price());
			pstmt.setTimestamp(5, secondHandVO.getStart_time());
			pstmt.setTimestamp(6, secondHandVO.getEnd_time());
			pstmt.setBytes(7, secondHandVO.getImg1());
			pstmt.setBytes(8, secondHandVO.getImg2());
			pstmt.setBytes(9, secondHandVO.getImg3());

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
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
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
//			pstmt.setBytes(9, secondHandVO.getImg1());
//			pstmt.setBytes(10, secondHandVO.getImg2());
//			pstmt.setBytes(11, secondHandVO.getImg3());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, secondHandVO.getBid_winner());
			pstmt.setInt(2, secondHandVO.getDeal_price());
			pstmt.setString(3, secondHandVO.getName());
			pstmt.setInt(4, secondHandVO.getBottom_price());
			pstmt.setInt(5, secondHandVO.getTop_price());
			pstmt.setTimestamp(6, secondHandVO.getStart_time());
			pstmt.setTimestamp(7, secondHandVO.getEnd_time());
			pstmt.setInt(8, secondHandVO.getIs_deal());
			pstmt.setBytes(9, secondHandVO.getImg1());
			pstmt.setBytes(10, secondHandVO.getImg2());
			pstmt.setBytes(11, secondHandVO.getImg3());
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

//	@Override
//	public void delete(Integer second_hand_id) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setInt(1, second_hand_id);
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
//	}

	@Override
	public SecondHandVO findByPrimaryKey(Integer second_hand_id) {
		SecondHandVO secondHandVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

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
				secondHandVO.setImg1(rs.getBytes("img1"));
				secondHandVO.setImg2(rs.getBytes("img2"));
				secondHandVO.setImg3(rs.getBytes("img3"));
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
	public List<SecondHandVO> getAll() {
		List<SecondHandVO> list = new ArrayList<SecondHandVO>();
		SecondHandVO secondHandVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				secondHandVO.setImg1(rs.getBytes("img1"));
				secondHandVO.setImg2(rs.getBytes("img2"));
				secondHandVO.setImg3(rs.getBytes("img3"));
				secondHandVO.setCreate_time(rs.getTimestamp("create_time"));
				secondHandVO.setUpdate_time(rs.getTimestamp("update_time"));
				list.add(secondHandVO); // Store the row in the list
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

		SecondHandJDBCDAO dao = new SecondHandJDBCDAO();

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

		// 刪除
//		dao.delete(1009);

		// 查詢
//		SecondHandVO secondHandVO3 = dao.findByPrimaryKey(1005);
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
//		System.out.println("---------------------");

		// 查詢
		List<SecondHandVO> list = dao.getAll();
		for (SecondHandVO listSecondHandVO : list) {
			System.out.print(listSecondHandVO.getsecond_hand_id() + ",");
			System.out.print(listSecondHandVO.getSaler() + ",");
			System.out.print(listSecondHandVO.getBid_winner() + ",");
			System.out.print(listSecondHandVO.getDeal_price() + ",");
			System.out.print(listSecondHandVO.getName() + ",");
			System.out.print(listSecondHandVO.getBottom_price() + ",");
			System.out.print(listSecondHandVO.getTop_price() + ",");
			System.out.print(listSecondHandVO.getStart_time() + ",");
			System.out.print(listSecondHandVO.getEnd_time() + ",");
			System.out.print(listSecondHandVO.getIs_deal() + ",");
			System.out.print(listSecondHandVO.getImg1() + ",");
			System.out.print(listSecondHandVO.getImg2() + ",");
			System.out.print(listSecondHandVO.getImg3() + ",");
			System.out.print(listSecondHandVO.getCreate_time() + ",");
			System.out.println(listSecondHandVO.getUpdate_time());
		}
	}
}
