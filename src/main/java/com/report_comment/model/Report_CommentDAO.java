package com.report_comment.model;

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

import com.report.model.ReportVO;

public class Report_CommentDAO implements Report_CommentDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO report_comment (report_id,comment,createtime,report_comment_image) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM report_comment";
	private static final String GET_ONE_STMT = "SELECT report_comment_id,report_id,comment,createtime,report_comment_image FROM report_comment where report_comment_id = ?";
	private static final String UPDATE = "UPDATE report_comment set report_id=?,comment=?,createtime=?,report_comment_image=? where report_comment_id = ?";
	@Override
	public void insert(Report_CommentVO report_commentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, report_commentVO.getReport_id());
			pstmt.setString(2, report_commentVO.getComment());
			pstmt.setTimestamp(3, report_commentVO.getCreatetime());
			pstmt.setBytes(4, report_commentVO.getReport_comment_image());
			
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
	public Report_CommentVO findByPrimaryKey(Integer report_comment_id) {
		Report_CommentVO report_commentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, report_comment_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				report_commentVO = new Report_CommentVO();
				report_commentVO.setReport_comment_id(rs.getInt("report_comment_id"));
				report_commentVO.setReport_id(rs.getInt("report_id"));
				report_commentVO.setComment(rs.getString("comment"));
				report_commentVO.setCreatetime(rs.getTimestamp("createtime"));
				report_commentVO.setReport_comment_image(rs.getBytes("report_comment_image"));
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
		return report_commentVO;
	}
	@Override
	public List<Report_CommentVO> getAll() {
		List<Report_CommentVO> list = new ArrayList<Report_CommentVO>();
		Report_CommentVO report_commentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				report_commentVO = new Report_CommentVO();
				report_commentVO.setReport_comment_id(rs.getInt("report_comment_id"));
				report_commentVO.setReport_id(rs.getInt("report_id"));
				report_commentVO.setComment(rs.getString("comment"));
				report_commentVO.setCreatetime(rs.getTimestamp("createtime"));
				report_commentVO.setReport_comment_image(rs.getBytes("report_comment_image"));
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
	public void changeType(ReportVO reportVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forward(Report_CommentVO report_CommentVO) {
		// TODO Auto-generated method stub
		
	}

	
}
