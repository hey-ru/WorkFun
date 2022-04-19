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
	private static final String GET_ALL_STMT = "SELECT second_hand_id,saler,bid_winner,deal_price,name,bottom_price,top_price,start_time,end_time,is_deal,img1,img2,img3,create_time,update_time FROM secondHand order by second_hand_id";
	private static final String GET_ONE_STMT = "SELECT second_hand_id,saler,bid_winner,deal_price,name,bottom_price,top_price,start_time,end_time,is_deal,img1,img2,img3,create_time,update_time FROM secondHand where second_hand_id = ?";
//	private static final String DELETE = "DELETE FROM second_hand where second_hand_id = ?";
	private static final String UPDATE = "UPDATE second_hand set bid_winner=?, deal_price=?, name=?, bottom_price=?, top_price=?, start_time=?, end_time=?, is_deal=?, img1=?, img2=?, img3=? where second_hand_id = ?";
//	private static final String UPDATE = "UPDATE second_hand set";

	@Override
	public void insert(SecondHandVO secondhandVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, secondhandVO.getSaler());
			pstmt.setString(2, secondhandVO.getName());
			pstmt.setInt(3, secondhandVO.getBottom_price());
			pstmt.setInt(4, secondhandVO.getTop_price());
			pstmt.setTimestamp(5, secondhandVO.getStart_time());
			pstmt.setTimestamp(6, secondhandVO.getEnd_time());
			pstmt.setBytes(7, secondhandVO.getImg1());
			pstmt.setBytes(8, secondhandVO.getImg2());
			pstmt.setBytes(9, secondhandVO.getImg3());

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
//	public void update(SecondHandVO secondhandVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setInt(1, secondhandVO.getBid_winner());
//			pstmt.setInt(2, secondhandVO.getDeal_price());
//			pstmt.setString(3, secondhandVO.getName());
//			pstmt.setInt(4, secondhandVO.getBottom_price());
//			pstmt.setInt(5, secondhandVO.getTop_price());
//			pstmt.setTimestamp(6, secondhandVO.getStart_time());
//			pstmt.setTimestamp(7, secondhandVO.getEnd_time());
//			pstmt.setInt(8, secondhandVO.getIs_deal());
//			pstmt.setBytes(9, secondhandVO.getImg1());
//			pstmt.setBytes(10, secondhandVO.getImg2());
//			pstmt.setBytes(11, secondhandVO.getImg3());
//			pstmt.setInt(12, secondhandVO.getsecond_hand_id());
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
	public void update(SecondHandVO secondhandVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, secondhandVO.getBid_winner());
			pstmt.setInt(2, secondhandVO.getDeal_price());
			pstmt.setString(3, secondhandVO.getName());
			pstmt.setInt(4, secondhandVO.getBottom_price());
			pstmt.setInt(5, secondhandVO.getTop_price());
			pstmt.setTimestamp(6, secondhandVO.getStart_time());
			pstmt.setTimestamp(7, secondhandVO.getEnd_time());
			pstmt.setInt(8, secondhandVO.getIs_deal());
			pstmt.setBytes(9, secondhandVO.getImg1());
			pstmt.setBytes(10, secondhandVO.getImg2());
			pstmt.setBytes(11, secondhandVO.getImg3());
			pstmt.setInt(12, secondhandVO.getsecond_hand_id());

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
		SecondHandVO secondhandVO = null;
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
				secondhandVO = new SecondHandVO();
				secondhandVO.setsecond_hand_id(rs.getInt("second_hand_id"));
				secondhandVO.setSaler(rs.getInt("saler"));
				secondhandVO.setBid_winner(rs.getInt("bid_winner"));
				secondhandVO.setDeal_price(rs.getInt("deal_price"));
				secondhandVO.setName(rs.getString("name"));
				secondhandVO.setBottom_price(rs.getInt("bottom_price"));
				secondhandVO.setTop_price(rs.getInt("top_price"));
				secondhandVO.setStart_time(rs.getTimestamp("start_time"));
				secondhandVO.setEnd_time(rs.getTimestamp("end_time"));
				secondhandVO.setIs_deal(rs.getInt("is_deal"));
				secondhandVO.setImg1(rs.getBytes("img1"));
				secondhandVO.setImg2(rs.getBytes("img2"));
				secondhandVO.setImg3(rs.getBytes("img3"));
				secondhandVO.setCreate_time(rs.getTimestamp("create_time"));
				secondhandVO.setUpdate_time(rs.getTimestamp("update_time"));
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
		return secondhandVO;
	}

	@Override
	public List<SecondHandVO> getAll() {
		List<SecondHandVO> list = new ArrayList<SecondHandVO>();
		SecondHandVO secondhandVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				secondhandVO = new SecondHandVO();
				secondhandVO.setsecond_hand_id(rs.getInt("second_hand_id"));
				secondhandVO.setSaler(rs.getInt("saler"));
				secondhandVO.setBid_winner(rs.getInt("bid_winner"));
				secondhandVO.setDeal_price(rs.getInt("deal_price"));
				secondhandVO.setName(rs.getString("name"));
				secondhandVO.setBottom_price(rs.getInt("bottom_price"));
				secondhandVO.setTop_price(rs.getInt("top_price"));
				secondhandVO.setStart_time(rs.getTimestamp("start_time"));
				secondhandVO.setEnd_time(rs.getTimestamp("end_time"));
				secondhandVO.setIs_deal(rs.getInt("is_deal"));
				secondhandVO.setImg1(rs.getBytes("img1"));
				secondhandVO.setImg2(rs.getBytes("img2"));
				secondhandVO.setImg3(rs.getBytes("img3"));
				secondhandVO.setCreate_time(rs.getTimestamp("create_time"));
				secondhandVO.setUpdate_time(rs.getTimestamp("update_time"));
				list.add(secondhandVO); // Store the row in the list
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
//		secondHandVO secondhandVO1 = new secondHandVO();
//		secondhandVO1.setSaler(1015);
//		secondhandVO1.setName("埼玉的頭髮");
//		secondhandVO1.setBottom_price(10);
//		secondhandVO1.setTop_price(100);
//		secondhandVO1.setStart_time(java.sql.Timestamp.valueOf("2022-04-01 00:00:00"));
//		secondhandVO1.setEnd_time(java.sql.Timestamp.valueOf("2022-04-01 00:30:00"));
//		secondhandVO1.setImg1(null);
//		secondhandVO1.setImg2(null);
//		secondhandVO1.setImg3(null);
//		dao.insert(secondhandVO1);

		// 修改
//		secondHandVO secondhandVO2 = new secondHandVO();
//		secondhandVO2.setsecond_hand_id(1009);
//		secondhandVO2.setBid_winner(1014);
//		secondhandVO2.setDeal_price(50);
//		secondhandVO2.setName("埼玉的兩根頭髮");
//		secondhandVO2.setBottom_price(20);
//		secondhandVO2.setTop_price(200);
//		secondhandVO2.setStart_time(java.sql.Timestamp.valueOf("2022-04-01 01:00:00"));
//		secondhandVO2.setEnd_time(java.sql.Timestamp.valueOf("2022-04-01 01:30:00"));
//		secondhandVO2.setIs_deal(1);
//		secondhandVO2.setImg1(null);
//		secondhandVO2.setImg2(null);
//		secondhandVO2.setImg3(null);
//		dao.update(secondhandVO2);

		// 刪除
//		dao.delete(1009);

		// 查詢
//		secondHandVO secondhandVO3 = dao.findByPrimaryKey(1005);
//		System.out.print(secondhandVO3.getsecond_hand_id() + ",");
//		System.out.print(secondhandVO3.getSaler() + ",");
//		System.out.print(secondhandVO3.getBid_winner() + ",");
//		System.out.print(secondhandVO3.getDeal_price() + ",");
//		System.out.print(secondhandVO3.getName() + ",");
//		System.out.print(secondhandVO3.getBottom_price() + ",");
//		System.out.print(secondhandVO3.getTop_price() + ",");
//		System.out.print(secondhandVO3.getStart_time() + ",");
//		System.out.print(secondhandVO3.getEnd_time() + ",");
//		System.out.print(secondhandVO3.getIs_deal() + ",");
//		System.out.print(secondhandVO3.getImg1() + "test1,");
//		System.out.print(secondhandVO3.getImg2() + "2,");
//		System.out.print(secondhandVO3.getImg3() + "3,");
//		System.out.print(secondhandVO3.getCreate_time() + ",");
//		System.out.println(secondhandVO3.getUpdate_time());
//		System.out.println("---------------------");

		// 查詢
//		List<secondHandVO> list = dao.getAll();
//		for (secondHandVO listSecondHandVO : list) {
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
