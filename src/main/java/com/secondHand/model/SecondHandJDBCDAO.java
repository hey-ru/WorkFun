package com.secondHand.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bid.model.BidJDBCDAO;
import com.bid.model.BidVO;
import com.emp.model.EmpVO;
import com.util.jdbcUtil_CompositeQuery;

public class SecondHandJDBCDAO implements SecondHandDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://database-1.cqm5mb4z5ril.ap-northeast-1.rds.amazonaws.com:3306/CGA101-03?serverTimezone=Asia/Taipei";
	String userid = "cga101-03";
	String passwd = "cga101-03";

	private static final String INSERT_STMT = "INSERT INTO second_hand (saler,name,bottom_price,top_price,start_time,end_time,img1,img2,img3) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
//	private static final String UPDATE = "UPDATE second_hand set bid_winner=?, deal_price=?, name=?, bottom_price=?, top_price=?, start_time=?, end_time=?, is_deal=?, img1=?, img2=?, img3=? where second_hand_id = ?";
	private static final String UPDATE = "UPDATE second_hand set ";// StringBuilder
//	private static final String GET_BY_ID = "SELECT second_hand_id,saler,bid_winner,deal_price,name,bottom_price,top_price,start_time,end_time,is_deal,img1,img2,img3,create_time,update_time FROM second_hand where second_hand_id = ?";
//	private static final String GET_BY_ID = "SELECT e.emp_name as saler_name,b.bid_id as bid_id,b.bidder as bidder,b.price as bid_price,sh.second_hand_id,sh.saler,sh.bid_winner,sh.deal_price,sh.name,sh.bottom_price,sh.top_price,sh.start_time,sh.end_time,sh.is_deal,sh.img1,sh.img2,sh.img3,sh.create_time,sh.update_time FROM second_hand sh join emp e on sh.saler = e.emp_id join bid b on sh.second_hand_id = b.second_hand_id where sh.second_hand_id = ?";
	private static final String GET_BY_ID = "SELECT e1.emp_name as saler_name,e1.phone as saler_phone,e1.extension as saler_extension,e2.emp_name as bidder_name,b.bid_id as bid_id,b.bidder as bidder,b.price as bid_price,sh.* FROM second_hand sh JOIN emp e1 on e1.emp_id = sh.saler JOIN bid b on sh.second_hand_id = b.second_hand_id LEFT JOIN emp e2 on e2.emp_id = b.bidder WHERE sh.is_enable <> 0 AND sh.second_hand_id = ?";
	private static final String GET_BY_SALER = "SELECT * FROM second_hand WHERE is_enable <> 0 AND saler = ?";
	private static final String GET_BY_BID_WINNER = "SELECT * FROM second_hand where is_enable <> 0 AND bid_winner = ?";
	private static final String GET_BY_NAME = "SELECT second_hand_id,saler,bid_winner,deal_price,name,bottom_price,top_price,start_time,end_time,is_deal,img1,img2,img3,create_time,update_time FROM second_hand where is_enable <> 0 AND name like \"%\"?\"%\"";
	private static final String GET_BY_IS_DEAL = "SELECT second_hand_id,saler,name,start_time,end_time,is_deal,img1 FROM second_hand where is_enable <> 0 and is_deal = ?";
	private static final String GET_ALL_STMT = "SELECT second_hand_id,saler,bid_winner,deal_price,name,bottom_price,top_price,start_time,end_time,is_deal,img1,img2,img3,create_time,update_time FROM second_hand WHERE is_enable <> 0 order by second_hand_id";
	private static final String GET_ALL_DATE_STMT = "SELECT second_hand_id,is_deal,start_time,end_time FROM second_hand where is_enable <> 0 order by second_hand_id";

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
//			pstmt.setString(7, secondHandVO.getImg1());
			pstmt.setBytes(8, secondHandVO.getImg2());
			pstmt.setBytes(9, secondHandVO.getImg3());

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
	public void insertWithBid(SecondHandVO secondHandVO, BidVO bidVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			// 1???????????? pstm.executeUpdate()??????
			con.setAutoCommit(false);

			// ?????????????????????
//			String cols[] = { "second_hand_id" };// for ????????????
//			String cols[] = {"second_hand_id", "XXX"};// for ????????????

//			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS); //for????????????

			pstmt.setInt(1, secondHandVO.getSaler());
			pstmt.setString(2, secondHandVO.getName());
			pstmt.setInt(3, secondHandVO.getBottom_price());
			pstmt.setInt(4, secondHandVO.getTop_price());
			pstmt.setTimestamp(5, secondHandVO.getStart_time());
			pstmt.setTimestamp(6, secondHandVO.getEnd_time());
			pstmt.setBytes(7, secondHandVO.getImg1());
			pstmt.setBytes(8, secondHandVO.getImg2());
			pstmt.setBytes(9, secondHandVO.getImg3());

//			Statement stmt = con.createStatement();

			pstmt.executeUpdate();

			String next_second_hand_id = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_second_hand_id = rs.getString(1);
//				next_XXX = rs.getString(2); //for????????????
				System.out.println("???????????????= " + next_second_hand_id + "(??????????????????????????????)");
			} else {
				System.out.println("????????????????????????");
			}
			rs.close();
			// ?????????????????????
			BidJDBCDAO dao = new BidJDBCDAO();
			bidVO.setsecond_hand_id(new Integer(next_second_hand_id));
//			bidVO.setXXX(new Integer(next_XXX)); //for????????????
			dao.insertBySecondHand(bidVO, con);

			// 2???????????? pstm.executeUpdate()??????
			con.commit();
			con.setAutoCommit(true);
			System.out.println("??????????????????" + next_second_hand_id + "???,?????????????????????");

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3??????????????????exception????????????catch?????????
					System.err.print("Transaction is being ");
					System.err.println("rolled back-???-secondHand");
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
	public void update(SecondHandVO newSecondHandVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			SecondHandVO oldSecondHandVO = getById(newSecondHandVO.getsecond_hand_id());
			StringBuilder sb = new StringBuilder().append(UPDATE);

			if (newSecondHandVO.getBid_winner() != null) {
				sb.append("bid_winner=?, ");
			}
			if (newSecondHandVO.getDeal_price() != null) {
				sb.append("deal_price=?, ");
			}
			if (newSecondHandVO.getName() != null) {
				sb.append("name=?, ");
			}
			if (newSecondHandVO.getBottom_price() != null) {
				sb.append("bottom_price=?, ");
			}
			if (newSecondHandVO.getTop_price() != null) {
				sb.append("top_price=?, ");
			}
			if (newSecondHandVO.getStart_time() != null) {
				sb.append("start_time=?, ");
			}
			if (newSecondHandVO.getEnd_time() != null) {
				sb.append("end_time=?, ");
			}
			if (newSecondHandVO.getIs_deal() != null) {
				sb.append("is_deal=?, ");
			}
			if (newSecondHandVO.getIs_enable() != null) {
				sb.append("is_enable=?, ");
			}
			if (newSecondHandVO.getImg1() != null) {
				sb.append("img1=?, ");
			}
			if (newSecondHandVO.getImg2() != null) {
				sb.append("img2=?, ");
			}
			if (newSecondHandVO.getImg3() != null) {
				sb.append("img3=?, ");
			}
			sb.append("second_hand_id=? where second_hand_id = ?");

			pstmt = con.prepareStatement(sb.toString());

			if (newSecondHandVO.getBid_winner() != null) {
				count++;
				pstmt.setInt(count, newSecondHandVO.getBid_winner());
			}
			if (newSecondHandVO.getDeal_price() != null) {
				count++;
				pstmt.setInt(count, newSecondHandVO.getDeal_price());
			}
			if (newSecondHandVO.getName() != null) {
				count++;
				pstmt.setString(count, newSecondHandVO.getName());
			}
			if (newSecondHandVO.getBottom_price() != null) {
				count++;
				pstmt.setInt(count, newSecondHandVO.getBottom_price());
			}
			if (newSecondHandVO.getTop_price() != null) {
				count++;
				pstmt.setInt(count, newSecondHandVO.getTop_price());
			}
			if (newSecondHandVO.getStart_time() != null) {
				count++;
				pstmt.setTimestamp(count, newSecondHandVO.getStart_time());
			}
			if (newSecondHandVO.getEnd_time() != null) {
				count++;
				pstmt.setTimestamp(count, newSecondHandVO.getEnd_time());
			}
			if (newSecondHandVO.getIs_deal() != null) {
				count++;
				pstmt.setInt(count, newSecondHandVO.getIs_deal());
			}
			if (newSecondHandVO.getIs_enable() != null) {
				count++;
				pstmt.setInt(count, newSecondHandVO.getIs_enable());
			}
			if (newSecondHandVO.getImg1() != null) {
				count++;
				pstmt.setBytes(count, newSecondHandVO.getImg1());
			}
//			if (newSecondHandVO.getImg1() != null) {
//				count++;
//				pstmt.setString(count, newSecondHandVO.getImg1());
//			}
			if (newSecondHandVO.getImg2() != null) {
				count++;
				pstmt.setBytes(count, newSecondHandVO.getImg2());
			}
			if (newSecondHandVO.getImg3() != null) {
				count++;
				pstmt.setBytes(count, newSecondHandVO.getImg3());
			}
			count++;
			pstmt.setInt(count, newSecondHandVO.getsecond_hand_id());
			count++;// where
			pstmt.setInt(count, newSecondHandVO.getsecond_hand_id());

			pstmt.executeUpdate();
//			System.out.println("update " + (count - 2) + " data!");

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
	public SecondHandVO getById(Integer second_hand_id) {
		SecondHandVO secondHandVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_ID);

			pstmt.setInt(1, second_hand_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				EmpVO empVO1 = new EmpVO();
				empVO1.setEmpName(rs.getString("saler_name"));
				empVO1.setPhone(rs.getString("saler_phone"));
				empVO1.setExtension(rs.getString("saler_extension"));
				
				EmpVO empVO2 = new EmpVO();
				empVO2.setEmpName(rs.getString("bidder_name"));
				
				BidVO bidVO = new BidVO();
				bidVO.setBid_id(rs.getInt("bid_id"));
				bidVO.setBidder(rs.getInt("bidder"));
				bidVO.setPrice(rs.getInt("bid_price"));
				
				secondHandVO = new SecondHandVO();
				secondHandVO.setEmpVO1(empVO1);
				secondHandVO.setEmpVO2(empVO2);
				secondHandVO.setBidVO(bidVO);
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
//				secondHandVO.setImg1(rs.getString("img1"));
				secondHandVO.setImg2(rs.getBytes("img2"));
				secondHandVO.setImg3(rs.getBytes("img3"));
				secondHandVO.setCreate_time(rs.getTimestamp("create_time"));
				secondHandVO.setUpdate_time(rs.getTimestamp("update_time"));
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
		return secondHandVO;
	}

	@Override
	public List<SecondHandVO> getBySaler(Integer saler) {
		List<SecondHandVO> list = new ArrayList<SecondHandVO>();
		SecondHandVO secondHandVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_SALER);

			pstmt.setInt(1, saler);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				secondHandVO = new SecondHandVO();
				secondHandVO.setsecond_hand_id(rs.getInt("second_hand_id"));
				secondHandVO.setSaler(rs.getInt("saler"));
				secondHandVO.setName(rs.getString("name"));
				secondHandVO.setStart_time(rs.getTimestamp("start_time"));
				secondHandVO.setEnd_time(rs.getTimestamp("end_time"));
				secondHandVO.setIs_deal(rs.getInt("is_deal"));
				secondHandVO.setImg1(rs.getBytes("img1"));
				list.add(secondHandVO);
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
	public List<SecondHandVO> getByBidWinner(Integer bid_winner) {
		List<SecondHandVO> list = new ArrayList<SecondHandVO>();
		SecondHandVO secondHandVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_BID_WINNER);

			pstmt.setInt(1, bid_winner);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				secondHandVO = new SecondHandVO();
				secondHandVO.setsecond_hand_id(rs.getInt("second_hand_id"));
				secondHandVO.setSaler(rs.getInt("saler"));
				secondHandVO.setName(rs.getString("name"));
				secondHandVO.setStart_time(rs.getTimestamp("start_time"));
				secondHandVO.setEnd_time(rs.getTimestamp("end_time"));
				secondHandVO.setIs_deal(rs.getInt("is_deal"));
				secondHandVO.setImg1(rs.getBytes("img1"));
				list.add(secondHandVO);
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
	public List<SecondHandVO> getByName(String name) {
		List<SecondHandVO> list = new ArrayList<SecondHandVO>();
		SecondHandVO secondHandVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_NAME);

			pstmt.setString(1, name);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				secondHandVO = new SecondHandVO();
				secondHandVO.setsecond_hand_id(rs.getInt("second_hand_id"));
				secondHandVO.setSaler(rs.getInt("saler"));
				secondHandVO.setName(rs.getString("name"));
				secondHandVO.setStart_time(rs.getTimestamp("start_time"));
				secondHandVO.setEnd_time(rs.getTimestamp("end_time"));
				secondHandVO.setIs_deal(rs.getInt("is_deal"));
				secondHandVO.setImg1(rs.getBytes("img1"));
				list.add(secondHandVO);
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
	public List<SecondHandVO> getByIsDeal(Integer is_deal) {
		List<SecondHandVO> list = new ArrayList<SecondHandVO>();
		SecondHandVO secondHandVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_IS_DEAL);
			
			pstmt.setInt(1, is_deal);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				secondHandVO = new SecondHandVO();
				secondHandVO.setsecond_hand_id(rs.getInt("second_hand_id"));
				secondHandVO.setSaler(rs.getInt("saler"));
				secondHandVO.setName(rs.getString("name"));
				secondHandVO.setStart_time(rs.getTimestamp("start_time"));
				secondHandVO.setEnd_time(rs.getTimestamp("end_time"));
				secondHandVO.setIs_deal(rs.getInt("is_deal"));
				secondHandVO.setImg1(rs.getBytes("img1"));
				list.add(secondHandVO);
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
//				secondHandVO.setImg1(rs.getString("img1"));
				secondHandVO.setImg2(rs.getBytes("img2"));
				secondHandVO.setImg3(rs.getBytes("img3"));
				secondHandVO.setCreate_time(rs.getTimestamp("create_time"));
				secondHandVO.setUpdate_time(rs.getTimestamp("update_time"));
				list.add(secondHandVO);
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
	public List<SecondHandVO> getAllBySaler(Map<String, String[]> map) {
		List<SecondHandVO> list = new ArrayList<SecondHandVO>();
		SecondHandVO secondHandVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String finalSQL = "select * from second_hand " + jdbcUtil_CompositeQuery.get_WhereCondition(map)
					+ "and is_enable <> 0 order by second_hand_id";
//			System.out.println(finalSQL.substring(30, 39));
//			System.out.println("is_enable".equals(finalSQL.substring(30, 39)));
			if ("is_enable".equals(finalSQL.substring(30, 39))) {
				finalSQL = "select * from second_hand where is_enable <> 0 order by second_hand_id";
			}
			pstmt = con.prepareStatement(finalSQL);
//			System.out.println("??????finalSQL(by DAO) = " + finalSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				secondHandVO = new SecondHandVO();
				secondHandVO.setsecond_hand_id(rs.getInt("second_hand_id"));
				secondHandVO.setSaler(rs.getInt("saler"));
				secondHandVO.setName(rs.getString("name"));
				secondHandVO.setStart_time(rs.getTimestamp("start_time"));
				secondHandVO.setEnd_time(rs.getTimestamp("end_time"));
				secondHandVO.setIs_deal(rs.getInt("is_deal"));
				secondHandVO.setImg1(rs.getBytes("img1"));
				list.add(secondHandVO);
			}

			// Handle any SQL errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<SecondHandVO> getAllByBidWinner(Map<String, String[]> map) {
		List<SecondHandVO> list = new ArrayList<SecondHandVO>();
		SecondHandVO secondHandVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String finalSQL = "select * from second_hand " + jdbcUtil_CompositeQuery.get_WhereCondition(map)
					+ "and is_enable <> 0 order by second_hand_id";
//			System.out.println(finalSQL.substring(30, 39));
//			System.out.println("is_enable".equals(finalSQL.substring(30, 39)));
			if ("is_enable".equals(finalSQL.substring(30, 39))) {
				finalSQL = "select * from second_hand where is_enable <> 0 order by second_hand_id";
			}
			pstmt = con.prepareStatement(finalSQL);
//			System.out.println("??????finalSQL(by DAO) = " + finalSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				secondHandVO = new SecondHandVO();
				secondHandVO.setsecond_hand_id(rs.getInt("second_hand_id"));
				secondHandVO.setSaler(rs.getInt("saler"));
				secondHandVO.setName(rs.getString("name"));
				secondHandVO.setStart_time(rs.getTimestamp("start_time"));
				secondHandVO.setEnd_time(rs.getTimestamp("end_time"));
				secondHandVO.setIs_deal(rs.getInt("is_deal"));
				secondHandVO.setImg1(rs.getBytes("img1"));
				list.add(secondHandVO);
			}

			// Handle any SQL errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<SecondHandVO> getAll(Map<String, String[]> map) {
		List<SecondHandVO> list = new ArrayList<SecondHandVO>();
		SecondHandVO secondHandVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String finalSQL = "select * from second_hand " + jdbcUtil_CompositeQuery.get_WhereCondition(map)
					+ "and is_enable <> 0 order by second_hand_id";
//			System.out.println(finalSQL.substring(30, 39));
//			System.out.println("is_enable".equals(finalSQL.substring(30, 39)));
			if ("is_enable".equals(finalSQL.substring(30, 39))) {
				finalSQL = "select * from second_hand where is_enable <> 0 order by second_hand_id";
			}
			pstmt = con.prepareStatement(finalSQL);
//			System.out.println("??????finalSQL(by DAO) = " + finalSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				secondHandVO = new SecondHandVO();
				secondHandVO.setsecond_hand_id(rs.getInt("second_hand_id"));
				secondHandVO.setSaler(rs.getInt("saler"));
//				secondHandVO.setBid_winner(rs.getInt("bid_winner"));
//				secondHandVO.setDeal_price(rs.getInt("deal_price"));
				secondHandVO.setName(rs.getString("name"));
//				secondHandVO.setBottom_price(rs.getInt("bottom_price"));
//				secondHandVO.setTop_price(rs.getInt("top_price"));
				secondHandVO.setStart_time(rs.getTimestamp("start_time"));
				secondHandVO.setEnd_time(rs.getTimestamp("end_time"));
				secondHandVO.setIs_deal(rs.getInt("is_deal"));
				secondHandVO.setImg1(rs.getBytes("img1"));
//				secondHandVO.setImg1(rs.getString("img1"));
//				secondHandVO.setImg2(rs.getBytes("img2"));
//				secondHandVO.setImg3(rs.getBytes("img3"));
//				secondHandVO.setCreate_time(rs.getTimestamp("create_time"));
//				secondHandVO.setUpdate_time(rs.getTimestamp("update_time"));
				list.add(secondHandVO);
			}

			// Handle any SQL errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	public List<SecondHandVO> getAllDate() {
		List<SecondHandVO> list = new ArrayList<SecondHandVO>();
		SecondHandVO secondHandVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_DATE_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				secondHandVO = new SecondHandVO();
				secondHandVO.setsecond_hand_id(rs.getInt("second_hand_id"));
				secondHandVO.setIs_deal(rs.getInt("is_deal"));
				secondHandVO.setStart_time(rs.getTimestamp("start_time"));
				secondHandVO.setEnd_time(rs.getTimestamp("end_time"));
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
	
//	for img
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
	

//	public static void main(String[] args) throws Exception {
//
//		SecondHandJDBCDAO dao = new SecondHandJDBCDAO();

//		String fileName = "/Users/ryan/Coding/CGA101/secondHand_pic/?????????.jpeg";
//		File file = new File(fileName);
//		FileInputStream fis = new FileInputStream(file);
//		byte[] buffer = new byte[fis.available()];
//		fis.read(buffer);
//		fis.close();
//
//
//		ByteBuffer bb = ByteBuffer.wrap(buffer);//base64
//		ByteBuffer base64encoded = Base64.getEncoder().encode(bb);//base64
//
//		String fileName1 = "/Users/ryan/Coding/CGA101/secondHand_pic/?????????_2.jpg";
//		File file1 = new File(fileName1);
//		FileInputStream fis1 = new FileInputStream(file1);
//		byte[] buffer1 = new byte[(int)file1.length()];
//		fis1.read(buffer1);
//		fis1.close();
//		
//		ByteBuffer src1 = ByteBuffer.wrap (buffer1);//base64
//		ByteBuffer base64encoded1 = Base64.getEncoder().encode(src1);//base64
//		
//		
//		String fileName2 = "/Users/ryan/Coding/CGA101/secondHand_pic/?????????_3.jpg";
//		File file2 = new File(fileName2);
//		FileInputStream fis2 = new FileInputStream(file2);
//		byte[] buffer2 = new byte[(int)file2.length()];
//		fis2.read(buffer2);
//		fis2.close();
//		
//		ByteBuffer src2 = ByteBuffer.wrap (buffer2);//base64
//		ByteBuffer base64encoded2 = Base64.getEncoder().encode(src2);//base64

		// ??????
//		SecondHandVO secondHandVO1 = new SecondHandVO();
//		secondHandVO1.setSaler(1015);
//		secondHandVO1.setName("??????");
//		secondHandVO1.setBottom_price(10);
//		secondHandVO1.setTop_price(100);
//		secondHandVO1.setStart_time(java.sql.Timestamp.valueOf("2022-04-01 00:00:00"));
//		secondHandVO1.setEnd_time(java.sql.Timestamp.valueOf("2022-04-01 00:30:00"));
//		secondHandVO1.setImg1(buffer);//?????????
//		secondHandVO1.setImg1(getPictureByteArray(fileName));//?????????
//		secondHandVO1.setImg1(new String(base64encoded.array()));//base64
//		secondHandVO1.setImg2(null);
//		secondHandVO1.setImg3(null);
//		dao.insert(secondHandVO1);

		// ??????
//		SecondHandVO secondHandVO2 = new SecondHandVO();
//		secondHandVO2.setsecond_hand_id(1004);
//		secondHandVO2.setBid_winner(null);
//		secondHandVO2.setDeal_price(null);
//		secondHandVO2.setName(null);
//		secondHandVO2.setBottom_price(100000);
//		secondHandVO2.setTop_price(5000000);
//		secondHandVO2.setStart_time(java.sql.Timestamp.valueOf(unll));
//		secondHandVO2.setEnd_time(java.sql.Timestamp.valueOf("2022-03-20 18:30:00"));
//		secondHandVO2.setIs_deal(1);
//		secondHandVO2.setImg1(new String(base64encoded.array()));
//		secondHandVO2.setImg2(new String(base64encoded1.array()));
//		secondHandVO2.setImg3(new String(base64encoded2.array()));
//		dao.update(secondHandVO2);

		// ?????? by id
//		SecondHandVO secondHandVO3 = dao.getById(1004);
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
//		System.out.print(secondHandVO3.getImg1() + ",");
//		System.out.print(secondHandVO3.getImg2() + ",");
//		System.out.print(secondHandVO3.getImg3() + ",");
//		System.out.print(secondHandVO3.getCreate_time() + ",");
//		System.out.println(secondHandVO3.getUpdate_time());
	
		// ?????? by id
//		List<SecondHandVO> secondHandVO3 = dao.getBySaler(1015);
//		for (SecondHandVO secondHandVO : secondHandVO3) {
//			System.out.print(secondHandVO.getsecond_hand_id() + ",");
//			System.out.print(secondHandVO.getSaler() + ",");
//			System.out.print(secondHandVO.getBid_winner() + ",");
//			System.out.print(secondHandVO.getDeal_price() + ",");
//			System.out.print(secondHandVO.getName() + ",");
//			System.out.print(secondHandVO.getBottom_price() + ",");
//			System.out.print(secondHandVO.getTop_price() + ",");
//			System.out.print(secondHandVO.getStart_time() + ",");
//			System.out.print(secondHandVO.getEnd_time() + ",");
//			System.out.print(secondHandVO.getIs_deal() + ",");
//			System.out.print(secondHandVO.getImg1() + ",");
//			System.out.print(secondHandVO.getImg2() + ",");
//			System.out.print(secondHandVO.getImg3() + ",");
//			System.out.print(secondHandVO.getCreate_time() + ",");
//			System.out.println(secondHandVO.getUpdate_time());
//		}

		// ?????? by name
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

		// ??????
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
//		SecondHandVO secondHandVO=new SecondHandVO();
//		SecondHandJDBCDAO secondHandJDBCDAO=new SecondHandJDBCDAO();
//		System.out.println(secondHandJDBCDAO.getById(1012).getBidVO().getBidder());
//		System.out.println(secondHandJDBCDAO.getById(1012).getBidVO().getBidder()==0);
//	}

}
