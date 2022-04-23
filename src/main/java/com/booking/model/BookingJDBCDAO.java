package com.booking.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingJDBCDAO implements BookingDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://database-1.cqm5mb4z5ril.ap-northeast-1.rds.amazonaws.com:3306/CGA101-03?useUnicode=yes&characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Taipei";
	// database-1.cqm5mb4z5ril.ap-northeast-1.rds.amazonaws.com --> 資料庫連線
	String userid = "cga101-03";
	String passwd = "cga101-03";

	// 新增一筆預約單
	private static final String INSERT = "INSERT INTO booking (emp_id,equipment_id,start_date,end_date,return_status,overdue_date,overdue_price) VALUES (?,?,?,?,?,?,?)";

	// 刪除預約單
	private static final String DELETE = "DELETE FROM booking where booking_id=?";

	// 後台查詢預約單 by 訂單編號
	private static final String GET_BY_BOOKINGID = "SELECT booking_id,emp_id,equipment_id,start_date,end_date,return_status,overdue_date,overdue_price from booking where booking_id=?";

	// 後台查詢預約單 by 員工
	private static final String GET_BY_EMPID = "SELECT booking_id,emp_id,equipment_id,start_date,end_date,return_status,overdue_date,overdue_price from booking where emp_id=?";

	// 查詢預約單所有欄位
	private static final String GET_ALL = "SELECT booking_id,emp_id,equipment_id,start_date,end_date,return_status,overdue_date,overdue_price from booking";

	// 員工查詢自己預約單
	private static final String GET_BY_RETURNSTATUS = "SELECT booking_id,start_date,end_date,return_status from booking where return_status=?";

	// 員工更改已預約日期
	private static final String UPDATE_DATE = "UPDATE booking set start_date=?,end_date=? where booking_id=?";

	@Override
	public void insert(BookingVO bookingVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);

			pstmt.setInt(1, bookingVO.getEmpId());
			pstmt.setInt(2, bookingVO.getEquipmentId());
			pstmt.setTimestamp(3, bookingVO.getStartDate());
			pstmt.setTimestamp(4, bookingVO.getEndDate());
			pstmt.setInt(5, bookingVO.getReturnStatus());
			pstmt.setTimestamp(6, bookingVO.getOverdueDate());
			pstmt.setInt(7, bookingVO.getOverduePrice());

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
	public BookingVO getByEmpId(Integer empId) {

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

	public static void main(String[] args) {

		BookingJDBCDAO dao = new BookingJDBCDAO();

		// 新增
//		BookingVO bookingVO1 = new BookingVO();
//		bookingVO1.setEmpId(1005);
//		bookingVO1.setEquipmentId(105);
//		bookingVO1.setStartDate(java.sql.Timestamp.valueOf("2022-03-29 17:24:57"));
//		bookingVO1.setEndDate(java.sql.Timestamp.valueOf("2022-04-03 09:57:01"));
//		bookingVO1.setReturnStatus(0);
//		bookingVO1.setOverdueDate(java.sql.Timestamp.valueOf("2022-04-04 13:22:01"));
//		bookingVO1.setOverduePrice(new Integer(20300));
//		dao.insert(bookingVO1);

		// 修改
//		BookingVO bookingVO2 = new BookingVO();
//		bookingVO2.setStartDate(java.sql.Timestamp.valueOf("2022-04-01 17:24:57"));
//		bookingVO2.setEndDate(java.sql.Timestamp.valueOf("2022-04-10 09:57:10"));
//		bookingVO2.setBookingId(1002);
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
		BookingVO bookingVO5 = dao.getbyReturnStatus(0);
		System.out.println(bookingVO5.toString());
	
	
	}
}