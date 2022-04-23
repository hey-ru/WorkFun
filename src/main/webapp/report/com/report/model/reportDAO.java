package com.report.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class reportDAO implements reportDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO report (reporter,handler,starttime,updatetime,endtime,content,status,report_image,report_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM report";
	private static final String GET_ONE_STMT = "SELECT report_id,reporter,handler,starttime,updatetime,endtime,content,status,report_image,report_type FROM report where report_id = ?";
	private static final String UPDATE = "UPDATE report set reporter=?,handler=?,starttime=?,updatetime=?,endtime=?,content=?,status=?,report_image=?,report_type=? where report_id = ?";

	@Override
	public void insert(reportVO reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, reportVO.getReporter());
			pstmt.setInt(2, reportVO.getHandler());
			pstmt.setDate(3, reportVO.getStarttime());
			pstmt.setDate(4, reportVO.getUpdatetime());
			pstmt.setDate(5, reportVO.getEndtime());
			pstmt.setString(6, reportVO.getContent());
			pstmt.setInt(7, reportVO.getStatus());
			pstmt.setBytes(8, reportVO.getReport_image());
			pstmt.setInt(9, reportVO.getReport_type());

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
	public void update(reportVO reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, reportVO.getReporter());
			pstmt.setInt(2, reportVO.getHandler());
			pstmt.setDate(3, reportVO.getStarttime());
			pstmt.setDate(4, reportVO.getUpdatetime());
			pstmt.setDate(5, reportVO.getEndtime());
			pstmt.setString(6, reportVO.getContent());
			pstmt.setInt(7, reportVO.getStatus());
			pstmt.setBytes(8, reportVO.getReport_image());
			pstmt.setInt(9, reportVO.getReport_type());

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
	public reportVO findByPrimaryKey(Integer report_id) {
		reportVO reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, report_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				reportVO = new reportVO();
				reportVO.setReport_id(rs.getInt("report_id"));
				reportVO.setReporter(rs.getInt("reporter"));
				reportVO.setHandler(rs.getInt("handler"));
				reportVO.setStarttime(rs.getDate("starttime"));
				reportVO.setUpdatetime(rs.getDate("updatetime"));
				reportVO.setEndtime(rs.getDate("endtime"));
				reportVO.setContent(rs.getString("content"));
				reportVO.setStatus(rs.getInt("status"));
				reportVO.setReport_image(rs.getBytes("report_image"));
				reportVO.setReport_type(rs.getInt("report_type"));
			}

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
		return reportVO;
	}

	@Override
	public List<reportVO> getAll() {
		List<reportVO> list = new ArrayList<reportVO>();
		reportVO reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				reportVO = new reportVO();
				reportVO.setReport_id(rs.getInt("report_id"));
				reportVO.setReporter(rs.getInt("reporter"));
				reportVO.setHandler(rs.getInt("handler"));
				reportVO.setStarttime(rs.getDate("starttime"));
				reportVO.setUpdatetime(rs.getDate("updatetime"));
				reportVO.setEndtime(rs.getDate("endtime"));
				reportVO.setContent(rs.getString("content"));
				reportVO.setStatus(rs.getInt("status"));
				reportVO.setReport_image(rs.getBytes("report_image"));
				reportVO.setReport_type(rs.getInt("report_type"));
			}
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
}
