package com.booking.model;

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

public class BookingDAO implements BookingDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	// 新增一筆預約單
	private static final String INSERT = "INSERT INTO booking (emp_id,equipment_id,start_date,end_date,return_status,overdue_date,overdue_price) VALUES (?,?,?,?,?,?,?)";

	// 查詢預約單所有欄位
	private static final String GET_ALL = "SELECT booking_id,emp_id,equipment_id,start_date,end_date,return_status,overdue_date,overdue_price from booking";

	// 後台查詢預約單 by 訂單編號
	private static final String GET_BY_BOOKINGID = "SELECT booking_id,emp_id,equipment_id,start_date,end_date,return_status,overdue_date,overdue_price from booking where booking_id=?";

	// 刪除預約單
	private static final String DELETE = "DELETE FROM booking where booking_id=?";

	// 員工更改已預約日期
	private static final String UPDATE_DATE = "UPDATE booking set start_date=?,end_date=? where booking_id=?";

	@Override
	public void insert(BookingVO bookingVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			pstmt.setInt(1, bookingVO.getEquipmentId());
			pstmt.setInt(2, bookingVO.getEmpId());
			pstmt.setTimestamp(3, bookingVO.getStartDate());
			pstmt.setTimestamp(4, bookingVO.getEndDate());
			pstmt.setInt(5, bookingVO.getReturnStatus());
			pstmt.setTimestamp(6, bookingVO.getOverdueDate());
			pstmt.setInt(7, bookingVO.getOverduePrice());

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
	public void updateDate(BookingVO bookingVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_DATE);

			pstmt.setTimestamp(1, bookingVO.getStartDate());
			pstmt.setTimestamp(2, bookingVO.getEndDate());

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
	public void delete(Integer bookingId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, bookingId);

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
	public BookingVO getByBookingId(Integer bookingId) {

		BookingVO bookingVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
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

			con = ds.getConnection();
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
	public BookingVO getByEmpId(Integer empId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookingVO getbyReturnStatus(Integer returnStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		BookingDAO dao = new BookingDAO();

		BookingVO bookingVO = dao.getByBookingId(1001);
		System.out.println(bookingVO.getBookingId());
		System.out.println(bookingVO.getEquipmentId());
		System.out.println(bookingVO.getEmpId());
		System.out.println(bookingVO.getStartDate());
		System.out.println(bookingVO.getEndDate());
		System.out.println(bookingVO.getReturnStatus());
		System.out.println(bookingVO.getOverdueDate());
		System.out.println(bookingVO.getOverduePrice());
	}
}
