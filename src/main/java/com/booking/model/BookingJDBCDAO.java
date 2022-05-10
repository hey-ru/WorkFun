package com.booking.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BookingJDBCDAO implements BookingDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://database-1.cqm5mb4z5ril.ap-northeast-1.rds.amazonaws.com:3306/CGA101-03?useUnicode=yes&characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Taipei";
	// database-1.cqm5mb4z5ril.ap-northeast-1.rds.amazonaws.com --> 資料庫連線
	String userid = "cga101-03";
	String passwd = "cga101-03";

	// 新增一筆預約單
	private static final String INSERT_BOOKING = "INSERT INTO booking (emp_id,equipment_id,start_date,end_date,return_status) VALUES (?,?,?,?,?)";

	// 刪除預約單
	private static final String DELETE = "DELETE FROM booking where booking_id=?";

	// 後台查詢預約單 by 訂單編號
	private static final String GET_BY_BOOKINGID = "SELECT booking_id,emp_id,equipment_id,start_date,end_date,return_status,overdue_date,overdue_price from booking where booking_id=?";

	// 後台查詢狀態
	private static final String GET_BY_STATUS = "SELECT booking_id,emp_id,equipment_id,start_date,end_date,return_status,overdue_date,overdue_price from booking where return_status=?";

	// 員工查詢自己預約單
	private static final String GET_BY_EMPID = "SELECT booking_id,equipment_id,emp_id,start_date,end_date,return_status, overdue_date, overdue_price, timestampdiff(day,end_date , current_timestamp()) as dateDiff from booking  where emp_id = ? order by start_date desc;";

	// 查詢預約單所有欄位
	private static final String GET_ALL = "SELECT * from booking  order by return_status desc , start_date>=now()";

	// 員工查詢自己預約單
	private static final String GET_BY_RETURNSTATUS = "SELECT booking_id,start_date,end_date,return_status from booking where return_status=?";

	// 員工更改已預約日期
	private static final String UPDATE_DATE = "UPDATE booking set start_date=?,end_date=? where booking_id=?";

	// 後台員工修改狀態
	private static final String UPDATE_RETURNSTATUS = "UPDATE booking set ";

	// 查詢開始租借時間
	private static final String GET_STARTDATE_BY_EQUPTID = "select equipment_id , start_date from booking where start_date >= now() and equipment_id = ?";

	// 計算逾時期間
	private static final String GET_OVERDUE_DATE = "select DATEDIFF(current_timestamp(),end_date) as dateDiff from booking where emp_id =?";

//	========================================================================
	@Override
	public void insertBooking(BookingVO bookingVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_BOOKING);

			pstmt.setInt(1, bookingVO.getEmpId());
			pstmt.setInt(2, bookingVO.getEquipmentId());
			pstmt.setTimestamp(3, bookingVO.getStartDate());
			pstmt.setTimestamp(4, bookingVO.getEndDate());
			pstmt.setInt(5, bookingVO.getReturnStatus());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public int updateReturnStatus(BookingVO newbooking) {

		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			BookingVO oldBooking = getByBookingId(newbooking.getBookingId());
			StringBuilder sb = new StringBuilder();

			sb.append(UPDATE_RETURNSTATUS);

			if (newbooking.getReturnStatus() != null) {
				sb.append("return_status=?, ");
			}

			sb.append("booking_id=? ");
			sb.append("where booking_id=? ");

			pstmt = con.prepareStatement(sb.toString());

			if (newbooking.getReturnStatus() != null) {
				count++;
				pstmt.setInt(count, newbooking.getReturnStatus());
			}

			count++;
			pstmt.setInt(count, newbooking.getBookingId());
			count++;
			pstmt.setInt(count, newbooking.getBookingId());
			pstmt.executeUpdate();
//			System.out.println(count);

			// Handle any driver errors

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

//		if (pstmt != null) {
//			try {
//				pstmt.close();
//			} catch (SQLException se) {
//				se.printStackTrace(System.err);
//			}
//		}
//		if (con != null) {
//			try {
//				con.close();
//			} catch (Exception e) {
//				e.printStackTrace(System.err);
//			}
//		}
		}
		return 1;
	}

	@Override
	public void updateDate(BookingVO bookingVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_DATE);

			pstmt.setTimestamp(1, bookingVO.getStartDate());
			pstmt.setTimestamp(2, bookingVO.getEndDate());
			pstmt.setInt(3, bookingVO.getBookingId());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(Integer bookingId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, bookingId);

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public BookingVO getByBookingId(Integer bookingId) {

		BookingVO bookingVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_BOOKINGID);

			pstmt.setInt(1, bookingId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				bookingVO = new BookingVO();
				bookingVO.setBookingId(rs.getInt("booking_id"));
				bookingVO.setEquipmentId(rs.getInt("equipment_id"));
				bookingVO.setEmpId(rs.getInt("emp_id"));
				bookingVO.setStartDate(rs.getTimestamp("start_date"));
				bookingVO.setEndDate(rs.getTimestamp("end_date"));
				bookingVO.setReturnStatus(rs.getInt("return_status"));
				bookingVO.setOverdueDate(rs.getTimestamp("overdue_date"));
				bookingVO.setOverduePrice(rs.getInt("overdue_price"));
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		return bookingVO;
	}

	@Override
	public List<BookingVO> getByEmpId(Integer empId) {

		List<BookingVO> list = new ArrayList<BookingVO>();
		BookingVO bookingVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_EMPID);

			pstmt.setInt(1, empId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				bookingVO = new BookingVO();
				bookingVO.setBookingId(rs.getInt("booking_id"));
				bookingVO.setEquipmentId(rs.getInt("equipment_id"));
				bookingVO.setEmpId(rs.getInt("emp_id"));
				bookingVO.setStartDate(rs.getTimestamp("start_date"));
				bookingVO.setEndDate(rs.getTimestamp("end_date"));
				bookingVO.setReturnStatus(rs.getInt("return_status"));
				bookingVO.setOverdueDate(rs.getTimestamp("overdue_date"));
				bookingVO.setOverduePrice(rs.getInt("overdue_price"));
				bookingVO.setDateDiff(rs.getLong("dateDiff"));//差異天數
				list.add(bookingVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public List<BookingVO> getStartDateByEqptId(Integer equipmentId) {

		List<BookingVO> list = new ArrayList<BookingVO>();
		BookingVO bookingVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_STARTDATE_BY_EQUPTID);
			rs = pstmt.executeQuery();

			pstmt.setInt(1, equipmentId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				bookingVO = new BookingVO();
				bookingVO.setEquipmentId(rs.getInt("equipment_id"));
				bookingVO.setStartDate(rs.getTimestamp("start_date"));
				list.add(bookingVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public List<BookingVO> getAll() {
		List<BookingVO> list = new ArrayList<BookingVO>();
		BookingVO bookingVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				bookingVO = new BookingVO();
				bookingVO.setBookingId(rs.getInt("booking_id"));
				bookingVO.setEquipmentId(rs.getInt("equipment_id"));
				bookingVO.setEmpId(rs.getInt("emp_id"));
				bookingVO.setStartDate(rs.getTimestamp("start_date"));
				bookingVO.setEndDate(rs.getTimestamp("end_date"));
				bookingVO.setReturnStatus(rs.getInt("return_status"));
				bookingVO.setOverdueDate(rs.getTimestamp("overdue_date"));
				bookingVO.setOverduePrice(rs.getInt("overdue_price"));
				list.add(bookingVO);
			}
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
	public BookingVO getbyReturnStatus(Integer returnStatus) {

		BookingVO bookingVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_RETURNSTATUS);

			pstmt.setInt(1, returnStatus);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				bookingVO = new BookingVO();
				bookingVO.setBookingId(rs.getInt("booking_id"));
				bookingVO.setStartDate(rs.getTimestamp("start_date"));
				bookingVO.setEndDate(rs.getTimestamp("end_date"));
				bookingVO.setReturnStatus(rs.getInt("return_status"));
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		return bookingVO;
	}

	@Override
	public List<BookingVO> getOverdueDate(Integer empId) {

		List<BookingVO> list = new ArrayList<BookingVO>();
		BookingVO bookingVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_OVERDUE_DATE);

			pstmt.setInt(1, empId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				bookingVO = new BookingVO();
				bookingVO.setEmpId(rs.getInt("empId"));
				//bookingVO.setDateDiff(rs.getTimestamp("dateDiff"));
				bookingVO.setOverdueDate(rs.getTimestamp("overdue_date"));
				list.add(bookingVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

	public static void main(String[] args) {

		BookingJDBCDAO dao = new BookingJDBCDAO();

		// 新增
//		BookingVO bookingVO1 = new BookingVO();
//		bookingVO1.setEmpId(1011);
//		bookingVO1.setEquipmentId(105);
//		bookingVO1.setStartDate(java.sql.Timestamp.valueOf("2022-03-29 17:24:57"));
//		bookingVO1.setEndDate(java.sql.Timestamp.valueOf("2022-04-03 09:57:01"));
//		bookingVO1.setReturnStatus(0);
//		bookingVO1.setOverdueDate(java.sql.Timestamp.valueOf("2022-03-08 13:22:01"));
//		bookingVO1.setOverduePrice(new Integer(203022220));
//		dao.insert(bookingVO1);

		// 修改
//		BookingVO bookingVO2 = new BookingVO();
//		bookingVO2.setStartDate(java.sql.Timestamp.valueOf("2022-04-04 17:24:57"));
//		bookingVO2.setEndDate(java.sql.Timestamp.valueOf("2022-04-10 09:57:10"));
//		bookingVO2.setBookingId(1006);
//		dao.updateDate(bookingVO2);

		// 刪除
//		dao.delete(1002);

		// 查詢預約單 by Booking Id
//		BookingVO bookingVO3 = dao.getByBookingId(1006);
//		System.out.println(bookingVO3.toString());

		// 查詢預約單 by empId
//		BookingVO bookingVO4 = dao.getByEmpId(1005);
//		System.out.println(bookingVO4.toString());

		// 查詢
//		List<BookingVO> list = dao.getAll();
//		for (BookingVO aBooking : list) {
//			System.out.print(aBooking.getBookingId() + ",");
//			System.out.print(aBooking.getEmpId() + ",");
//			System.out.print(aBooking.getEquipmentId() + ",");
//			System.out.print(aBooking.getStartDate() + ",");
//			System.out.print(aBooking.getEndDate() + ",");
//			System.out.print(aBooking.getReturnStatus() + ",");
//			System.out.print(aBooking.getOverdueDate() + ",");
//			System.out.print(aBooking.getOverduePrice() + ",");
//			System.out.println();
//		}

		// 查詢自己預約單 by 狀態
//		BookingVO bookingVO5 = dao.getbyReturnStatus(0);
//		System.out.println(bookingVO5.toString());

		// 修改 串接英雄
//		BookingVO bookingVO6 = new BookingVO();
//		bookingVO6.setBookingId(1001);
//
//		bookingVO6.setReturnStatus(3);
//		dao.updateReturnStatus(bookingVO6);

		List<BookingVO> list = dao.getOverdueDate(1069);
		for (BookingVO aBooking : list) {
			System.out.println(aBooking.getEmpId());
			System.out.println(aBooking.getOverdueDate());
		}
	}
}