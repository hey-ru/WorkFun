package com.bid.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BidJDBCDAO implements BidDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://database-1.cqm5mb4z5ril.ap-northeast-1.rds.amazonaws.com:3306/CGA101-03?serverTimezone=Asia/Taipei";
	String userid = "cga101-03";
	String passwd = "cga101-03";

	private static final String INSERT_STMT = "INSERT INTO bid (second_hand_id,price) VALUES (?, ?)";
	private static final String UPDATE = "UPDATE bid set ";
	private static final String GET_ONE_STMT = "SELECT bid_id,second_hand_id,bidder,price,create_time,update_time FROM bid where bid_id = ?";
	private static final String GET_ALL_STMT = "SELECT bid_id,second_hand_id,bidder,price,create_time,update_time FROM bid order by bid_id";

	@Override
	public void insert(BidVO bidVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, bidVO.getsecond_hand_id());
			pstmt.setInt(2, 0);

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
	public void insertBySecondHand(BidVO bidVO, Connection con) {

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, bidVO.getsecond_hand_id());
			pstmt.setInt(2, 0);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-bid");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
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
		}

	}

	@Override
	public void update(BidVO newBidVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			BidVO oldBidVO = getById(newBidVO.getBid_id());
			StringBuilder sb = new StringBuilder().append(UPDATE);
			
			if (newBidVO.getBidder() != null) {
				sb.append("bidder=?, ");
			}
			if (newBidVO.getPrice() != null) {
				sb.append("price=?, ");
			}
			sb.append("bid_id=? where bid_id = ?");

			pstmt = con.prepareStatement(sb.toString());
			
			if (newBidVO.getBidder() != null) {
				count++;
				pstmt.setInt(count, newBidVO.getBidder());
			}
			if (newBidVO.getPrice() != null) {
				count++;
				pstmt.setInt(count, newBidVO.getPrice());
			}
			count++;
			pstmt.setInt(count, newBidVO.getBid_id());
			count++;// where
			pstmt.setInt(count, newBidVO.getBid_id());

			pstmt.executeUpdate();
			System.out.println("update " + (count - 2) + " data!");

			// Handle any driver errors
		} catch (Exception e) {
			e.printStackTrace();
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

//		@Override
//		public void delete(Integer bid_id) {
//			Connection con = null;
//			PreparedStatement pstmt = null;
//
//			try {
//
//				Class.forName(driver);
//				con = DriverManager.getConnection(url, userid, passwd);
//				pstmt = con.prepareStatement(DELETE);
//
//				pstmt.setInt(1, bid_id);
//
//				pstmt.executeUpdate();
//
//				// Handle any driver errors
//			} catch (ClassNotFoundException e) {
//				throw new RuntimeException("Couldn't load database driver. "
//						+ e.getMessage());
//				// Handle any SQL errors
//			} catch (SQLException se) {
//				throw new RuntimeException("A database error occured. "
//						+ se.getMessage());
//				// Clean up JDBC resources
//			} finally {
//				if (pstmt != null) {
//					try {
//						pstmt.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//				if (con != null) {
//					try {
//						con.close();
//					} catch (Exception e) {
//						e.printStackTrace(System.err);
//					}
//				}
//			}
//		}

	@Override
	public BidVO getById(Integer bid_id) {
		BidVO bidVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, bid_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// bidVo 也稱為 Domain objects
				bidVO = new BidVO();
				bidVO.setBid_id(rs.getInt("bid_id"));
				bidVO.setsecond_hand_id(rs.getInt("second_hand_id"));
				bidVO.setBidder(rs.getInt("bidder"));
				bidVO.setPrice(rs.getInt("price"));
				bidVO.setCreate_time(rs.getTimestamp("create_time"));
				bidVO.setUpdate_time(rs.getTimestamp("update_time"));
			}

			// Handle any driver errors

		} catch (Exception se) {
			se.printStackTrace();
			// Clean up JDBC resources
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bidVO;
	}

	@Override
	public List<BidVO> getAll() {
		List<BidVO> list = new ArrayList<BidVO>();
		BidVO bidVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				bidVO = new BidVO();
				bidVO.setBid_id(rs.getInt("bid_id"));
				bidVO.setsecond_hand_id(rs.getInt("second_hand_id"));
				bidVO.setBidder(rs.getInt("bidder"));
				bidVO.setPrice(rs.getInt("price"));
				bidVO.setCreate_time(rs.getTimestamp("create_time"));
				bidVO.setUpdate_time(rs.getTimestamp("update_time"));
				list.add(bidVO); // Store the row in the list
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

		BidJDBCDAO dao = new BidJDBCDAO();

//			// 新增
//			BidVO bidVO1 = new BidVO();
//			bidVO1.setsecond_hand_id(1004);
//			bidVO1.setBidder(1012);
//			bidVO1.setPrice(20);
//			dao.insert(bidVO1);

		// 修改
//			BidVO bidVO2 = new BidVO();
//			bidVO2.setBid_id(100014);
//			bidVO2.setPrice(30);
//			dao.update(bidVO2);

		// 刪除
//			dao.delete(100014);

		// 查詢
//			BidVO bidVO3 = dao.findByPrimaryKey(100013);
//			System.out.print(bidVO3.getBid_id() + ",");
//			System.out.print(bidVO3.getsecond_hand_id() + ",");
//			System.out.print(bidVO3.getBidder() + ",");
//			System.out.print(bidVO3.getPrice() + ",");
//			System.out.print(bidVO3.getCreate_time() + ",");
//			System.out.println(bidVO3.getUpdate_time());
//			System.out.println("---------------------");

		// 查詢
//			List<BidVO> list = dao.getAll();
//			for (BidVO listBidVO : list) {
//				System.out.print(listBidVO.getBid_id() + ",");
//				System.out.print(listBidVO.getsecond_hand_id() + ",");
//				System.out.print(listBidVO.getBidder() + ",");
//				System.out.print(listBidVO.getPrice() + ",");
//				System.out.print(listBidVO.getCreate_time() + ",");
//				System.out.println(listBidVO.getUpdate_time());
//			}
	}

}
