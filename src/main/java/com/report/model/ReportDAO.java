package com.report.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.report_comment.model.Report_CommentVO;

public class ReportDAO implements ReportDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO report (reporter,handler,starttime,updatetime,endtime,content,status,report_image,report_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM report";
	private static final String GET_ONE_STMT = "SELECT report_id,reporter,handler,starttime,updatetime,endtime,content,status,report_image,report_type FROM report where report_id = ?";
	private static final String UPDATE = "UPDATE report set reporter=?,handler=?,starttime=?,updatetime=?,endtime=?,content=?,status=?,report_image=?,report_type=? where report_id = ?";

	@Override
	public void insert(ReportVO reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, reportVO.getReporter());
			pstmt.setInt(2, reportVO.getHandler());
			pstmt.setTimestamp(3, reportVO.getStarttime());
			pstmt.setTimestamp(4, reportVO.getUpdatetime());
			pstmt.setTimestamp(5, reportVO.getEndtime());
			pstmt.setString(6, reportVO.getContent());
			pstmt.setInt(7, 0);
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
	public void update(ReportVO reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, reportVO.getReporter());
			pstmt.setInt(2, reportVO.getHandler());
			pstmt.setTimestamp(3, reportVO.getStarttime());
			pstmt.setTimestamp(4, reportVO.getUpdatetime());
			pstmt.setTimestamp(5, reportVO.getEndtime());
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
	public ReportVO findByPrimaryKey(Integer report_id) {
		ReportVO reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, report_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				reportVO = new ReportVO();
				reportVO.setReport_id(rs.getInt("report_id"));
				reportVO.setReporter(rs.getInt("reporter"));
				reportVO.setHandler(rs.getInt("handler"));
				reportVO.setStarttime(rs.getTimestamp("starttime"));
				reportVO.setUpdatetime(rs.getTimestamp("updatetime"));
				reportVO.setEndtime(rs.getTimestamp("endtime"));
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
	public List<ReportVO> getAll() {
		List<ReportVO> list = new ArrayList<ReportVO>();
		ReportVO reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				reportVO = new ReportVO();
				reportVO.setReport_id(rs.getInt("report_id"));
				reportVO.setReporter(rs.getInt("reporter"));
				reportVO.setHandler(rs.getInt("handler"));
				reportVO.setStarttime(rs.getTimestamp("starttime"));
				reportVO.setUpdatetime(rs.getTimestamp("updatetime"));
				reportVO.setEndtime(rs.getTimestamp("endtime"));
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

	@Override
	public List<ReportVO> find(Integer handler, Integer status, Integer report_type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReportVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReportVO> getHandler(Integer handler) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void handleComment(ReportVO reportVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertComment(ReportVO reportVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Report_CommentVO> TestComment(Integer report_id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ReportVO findReport(Integer report_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReportVO> getOwnReport(Integer reporter) {
		// TODO Auto-generated method stub
		return null;
	}

}
 