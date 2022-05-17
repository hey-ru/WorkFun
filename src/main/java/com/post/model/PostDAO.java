package com.post.model;

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

import com.post_comment.model.Post_CommentVO;
import com.post_image.model.Post_ImageVO;

public class PostDAO implements PostDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO post (emp_id,post_title,post_content,post_video,post_createtime,post_updatetime,is_disable) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM post";
	private static final String GET_ONE_STMT = "SELECT post_id,emp_id,post_title,post_content,post_video,post_createtime,post_updatetime,is_disable FROM post where post_id = ?";
	private static final String UPDATE = "UPDATE post set emp_id=?,post_title=?,post_content=?,post_video=?,post_createtime=?,post_updatetime=?,is_disable=? where post_id = ?";

	@Override
	public void insert(PostVO postVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, postVO.getEmp_id());
			pstmt.setString(2, postVO.getPost_title());
			pstmt.setString(3, postVO.getPost_content());
			pstmt.setBytes(4, postVO.getPost_video());
			pstmt.setTimestamp(5, postVO.getPost_createtime());
			pstmt.setTimestamp(6, postVO.getPost_updatetime());
			pstmt.setInt(7, postVO.getIs_disable());

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
	public void update(PostVO postVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, postVO.getEmp_id());
			pstmt.setString(2, postVO.getPost_title());
			pstmt.setString(3, postVO.getPost_content());
			pstmt.setBytes(4, postVO.getPost_video());
			pstmt.setTimestamp(5, postVO.getPost_createtime());
			pstmt.setTimestamp(6, postVO.getPost_updatetime());
			pstmt.setInt(7, postVO.getIs_disable());

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
	public PostVO findByPrimaryKey(Integer post_id) {
		PostVO postVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, post_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				postVO = new PostVO();
				postVO.setPost_id(rs.getInt("post_id"));
				postVO.setEmp_id(rs.getInt("emp_id"));
				postVO.setPost_title(rs.getString("post_title"));
				postVO.setPost_content(rs.getString("post_content"));
				postVO.setPost_video(rs.getBytes("post_video"));
				postVO.setPost_createtime(rs.getTimestamp("post_createtime"));
				postVO.setPost_updatetime(rs.getTimestamp("post_updatetime"));
				postVO.setIs_disable(rs.getInt("is_disable"));
				
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
		return postVO;
	}

	@Override
	public List<PostVO> getAll() {
		List<PostVO> list = new ArrayList<PostVO>();
		PostVO postVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				postVO = new PostVO();
				postVO.setPost_id(rs.getInt("post_id"));
				postVO.setEmp_id(rs.getInt("emp_id"));
				postVO.setPost_title(rs.getString("post_title"));
				postVO.setPost_content(rs.getString("post_content"));
				postVO.setPost_video(rs.getBytes("post_video"));
				postVO.setPost_createtime(rs.getTimestamp("post_createtime"));
				postVO.setPost_updatetime(rs.getTimestamp("post_updatetime"));
				postVO.setIs_disable(rs.getInt("is_disable"));
				list.add(postVO);
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
	public List<Post_ImageVO> getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post_CommentVO> getComment() {
		// TODO Auto-generated method stub
		return null;
	}

}
