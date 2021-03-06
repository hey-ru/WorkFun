package com.post_comment.model;

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

public class Post_CommentDAO implements Post_CommentDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO post_comment (post_id,emp_id,post_comment,comment_createtime,comment_updatetime) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM post_comment";
	private static final String GET_ONE_STMT = "SELECT post_comment_id,post_id,emp_id,post_comment,comment_createtime,comment_updatetime FROM post_comment where post_comment_id = ?";
	private static final String UPDATE = "UPDATE post_comment set post_id=?,emp_id=?,post_comment=?,comment_createtime=?,comment_updatetime=? where post_comment_id = ?";
	@Override
	public void insert(Post_CommentVO post_commentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, post_commentVO.getPost_id());
			pstmt.setInt(2, post_commentVO.getEmp_id());
			pstmt.setString(3, post_commentVO.getPost_comment());
			pstmt.setTimestamp(4, post_commentVO.getComment_createtime());
			pstmt.setTimestamp(5, post_commentVO.getComment_updatetime());

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
	public void update(Post_CommentVO post_commentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, post_commentVO.getPost_id());
			pstmt.setInt(2, post_commentVO.getEmp_id());
			pstmt.setString(3, post_commentVO.getPost_comment());
			pstmt.setTimestamp(4, post_commentVO.getComment_createtime());
			pstmt.setTimestamp(5, post_commentVO.getComment_updatetime());

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
	public Post_CommentVO findByPrimaryKey(Integer post_comment_id) {
		Post_CommentVO post_commentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, post_comment_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				post_commentVO = new Post_CommentVO();
				post_commentVO.setPost_comment_id(rs.getInt("post_comment_id"));
				post_commentVO.setPost_id(rs.getInt("post_id"));
				post_commentVO.setEmp_id(rs.getInt("emp_id"));							
				post_commentVO.setPost_comment(rs.getString("post_comment"));
				post_commentVO.setComment_createtime(rs.getTimestamp("comment_createtime"));
				post_commentVO.setComment_updatetime(rs.getTimestamp("comment_updatetime"));
				
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
		return post_commentVO;
	}

	@Override
	public List<Post_CommentVO> getAll() {
		List<Post_CommentVO> list = new ArrayList<Post_CommentVO>();
		Post_CommentVO post_commentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				post_commentVO = new Post_CommentVO();
				post_commentVO.setPost_comment_id(rs.getInt("post_comment_id"));
				post_commentVO.setPost_id(rs.getInt("post_id"));
				post_commentVO.setEmp_id(rs.getInt("emp_id"));							
				post_commentVO.setPost_comment(rs.getString("post_comment"));
				post_commentVO.setComment_createtime(rs.getTimestamp("comment_createtime"));
				post_commentVO.setComment_updatetime(rs.getTimestamp("comment_updatetime"));
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
