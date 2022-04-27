package com.post_report.model;

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

public class Post_ReportDAO implements Post_ReportDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO post_report (post_id,emp_id,reason,status) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM post_report";
	private static final String GET_ONE_STMT = "SELECT post_report_id,post_id,emp_id,reason,status where post_report_id = ?";
	private static final String UPDATE = "UPDATE post_report set post_id=?,emp_id=?,reason=?,status=? where post_report_id = ?";

	@Override
	public void insert(Post_ReportVO post_reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, post_reportVO.getPost_id());
			pstmt.setInt(2, post_reportVO.getEmp_id());
			pstmt.setString(3, post_reportVO.getReason());
			pstmt.setInt(4, post_reportVO.getStatus());

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
	public void update(Post_ReportVO post_reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, post_reportVO.getPost_report_id());
			pstmt.setInt(2, post_reportVO.getPost_id());
			pstmt.setInt(3, post_reportVO.getEmp_id());
			pstmt.setString(4, post_reportVO.getReason());
			pstmt.setInt(5, post_reportVO.getStatus());

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
	public Post_ReportVO findByPrimaryKey(Integer post_report_id) {
		Post_ReportVO post_reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, post_report_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				post_reportVO = new Post_ReportVO();
				post_reportVO.setPost_report_id(rs.getInt("post_report_id"));
				post_reportVO.setPost_id(rs.getInt("post_id"));
				post_reportVO.setEmp_id(rs.getInt("emp_id"));							
				post_reportVO.setReason(rs.getString("reason"));
				post_reportVO.setStatus(rs.getInt("status"));
				
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
		return post_reportVO;
	}

	@Override
	public List<Post_ReportVO> getAll() {
		List<Post_ReportVO> list = new ArrayList<Post_ReportVO>();
		Post_ReportVO post_reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				post_reportVO = new Post_ReportVO();
				post_reportVO.setPost_report_id(rs.getInt("post_report_id"));
				post_reportVO.setPost_id(rs.getInt("post_id"));
				post_reportVO.setEmp_id(rs.getInt("emp_id"));							
				post_reportVO.setReason(rs.getString("reason"));
				post_reportVO.setStatus(rs.getInt("status"));
				list.add(post_reportVO);
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
