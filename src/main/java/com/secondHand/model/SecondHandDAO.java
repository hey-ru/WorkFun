package com.secondHand.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.bid.model.BidJDBCDAO;
import com.bid.model.BidVO;
import com.util.jdbcUtil_CompositeQuery;

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
	private static final String GET_ALL_DATE_STMT = "SELECT second_hand_id,create_time,update_time FROM second_hand order by second_hand_id";

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
	
	@Override
	public void insertWithBid(SecondHandVO secondHandVO, BidVO bidVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			// 先新增部門
			String cols[] = { "second_hand_id" };// for 複合主鍵

			pstmt = con.prepareStatement(INSERT_STMT, cols);

			pstmt.setInt(1, secondHandVO.getSaler());
			pstmt.setString(2, secondHandVO.getName());
			pstmt.setInt(3, secondHandVO.getBottom_price());
			pstmt.setInt(4, secondHandVO.getTop_price());
			pstmt.setTimestamp(5, secondHandVO.getStart_time());
			pstmt.setTimestamp(6, secondHandVO.getEnd_time());
			pstmt.setBytes(7, secondHandVO.getImg1());
			pstmt.setBytes(8, secondHandVO.getImg2());
			pstmt.setBytes(9, secondHandVO.getImg3());

			Statement stmt = con.createStatement();

			pstmt.executeUpdate();

			String next_second_hand_id = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_second_hand_id = rs.getString(1);
				System.out.println("自增主鍵值= " + next_second_hand_id + "(剛新增成功的二手編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增競標
			BidJDBCDAO dao = new BidJDBCDAO();
			bidVO.setsecond_hand_id(new Integer(next_second_hand_id));
			dao.insertBySecondHand(bidVO, con);

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("新增二手編號" + next_second_hand_id + "時,競標同時被新增");

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-secondHand");
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
	public void update(SecondHandVO newSecondHandVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;

		try {
			con = ds.getConnection();
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
			if (newSecondHandVO.getImg1() != null) {
				count++;
				pstmt.setBytes(count, newSecondHandVO.getImg1());
			}
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
			System.out.println("update " + (count - 2) + " data!");

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
				secondHandVO.setImg1(rs.getBytes("img1"));
				secondHandVO.setImg2(rs.getBytes("img2"));
				secondHandVO.setImg3(rs.getBytes("img3"));
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
				secondHandVO.setImg1(rs.getBytes("img1"));
				secondHandVO.setImg2(rs.getBytes("img2"));
				secondHandVO.setImg3(rs.getBytes("img3"));
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
	public List<SecondHandVO> getAll(Map<String, String[]> map) {
		List<SecondHandVO> list = new ArrayList<SecondHandVO>();
		SecondHandVO secondHandVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			con = ds.getConnection();
			String finalSQL = "select * from second_hand "
		          + jdbcUtil_CompositeQuery.get_WhereCondition(map)
		          + "order by second_hand_id";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
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
		return list;
	}

	
	
	
	public List<SecondHandVO> getAllDate() {
		List<SecondHandVO> list = new ArrayList<SecondHandVO>();
		SecondHandVO secondHandVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_DATE_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				secondHandVO = new SecondHandVO();
				secondHandVO.setsecond_hand_id(rs.getInt("second_hand_id"));
				
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
	
	
	
	
	
	public static void main(String[] args) throws Exception {

		SecondHandJDBCDAO dao = new SecondHandJDBCDAO();

		String fileName = "/Users/ryan/Coding/CGA101/secondHand_pic/燒燒果實_1.jpg";
		File file = new File(fileName);
		FileInputStream fis = new FileInputStream(file);
		byte[] buffer = new byte[(int) file.length()];
		fis.read(buffer);
		fis.close();

		ByteBuffer src = ByteBuffer.wrap(buffer);
		ByteBuffer base64encoded = Base64.getEncoder().encode(src);

		String fileName1 = "/Users/ryan/Coding/CGA101/secondHand_pic/燒燒果實_2.jpg";
		File file1 = new File(fileName1);
		FileInputStream fis1 = new FileInputStream(file1);
		byte[] buffer1 = new byte[(int)file1.length()];
		fis1.read(buffer1);
		fis1.close();
		
		ByteBuffer src1 = ByteBuffer.wrap (buffer1);
		ByteBuffer base64encoded1 = Base64.getEncoder().encode(src1);
//		
//		
//		String fileName2 = "/Users/ryan/Coding/CGA101/secondHand_pic/火紅眼_3.jpg";
//		File file2 = new File(fileName2);
//		FileInputStream fis2 = new FileInputStream(file2);
//		byte[] buffer2 = new byte[(int)file2.length()];
//		fis2.read(buffer2);
//		fis2.close();
//		
//		ByteBuffer src2 = ByteBuffer.wrap (buffer2);
//		ByteBuffer base64encoded2 = Base64.getEncoder().encode(src2);

		// 新增
//		SecondHandVO secondHandVO1 = new SecondHandVO();
//		secondHandVO1.setSaler(1015);
//		secondHandVO1.setName("埼玉的頭髮");
//		secondHandVO1.setBottom_price(10);
//		secondHandVO1.setTop_price(100);
//		secondHandVO1.setStart_time(java.sql.Timestamp.valueOf("2022-04-01 00:00:00"));
//		secondHandVO1.setEnd_time(java.sql.Timestamp.valueOf("2022-04-01 00:30:00"));
////		secondHandVO1.setImg1(buffer);//原本的
//		secondHandVO1.setImg1(new String(base64encoded.array()));
//		secondHandVO1.setImg2(null);
//		secondHandVO1.setImg3(null);
//		dao.insert(secondHandVO1);

		// 修改
//		SecondHandVO secondHandVO2 = new SecondHandVO();
//		secondHandVO2.setsecond_hand_id(1008);
//		secondHandVO2.setBid_winner(null);
//		secondHandVO2.setDeal_price(null);
//		secondHandVO2.setName("燒燒果實");
//		secondHandVO2.setBottom_price(10000000);
//		secondHandVO2.setTop_price(99999999);
//		secondHandVO2.setStart_time(java.sql.Timestamp.valueOf("2022-09-01 10:30:00"));
//		secondHandVO2.setEnd_time(java.sql.Timestamp.valueOf("2022-09-20 18:30:00"));
//		secondHandVO2.setIs_deal(1);
//		secondHandVO2.setImg1(buffer);
//		secondHandVO2.setImg2(buffer1);
//		secondHandVO2.setImg3(buffer2);
////		secondHandVO2.setImg1(new String(base64encoded.array()));
////		secondHandVO2.setImg2(new String(base64encoded1.array()));
////		secondHandVO2.setImg3(new String(base64encoded2.array()));
//		dao.update(secondHandVO2);

		// 查詢 by id
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
