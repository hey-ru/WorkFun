package com.post_like.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Post_LikeJDBCDAO implements Post_LikeDAO_interface{
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://cga101-03@database-1.cqm5mb4z5ril.ap-northeast-1.rds.amazonaws.com:3306/CGA101-03?zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Taipei";
	String userid = "cga101-03";
	String passwd = "cga101-03";
	
	private static final String INSERT_STMT = "INSERT INTO post_like (post_id,emp_id) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM post_like";
	private static final String GET_ONE_STMT = "SELECT post_id,emp_id FROM post_like where post_id = ? AND emp_id = ?";
	private static final String DELETE = "DELETE FROM post_like where post_id = ? AND emp_id = ?";
	@Override
	public void insert(Post_LikeVO post_likeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, post_likeVO.getPost_id());
			pstmt.setInt(2, post_likeVO.getEmp_id());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void delete(Integer post_id, Integer emp_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, post_id);
			pstmt.setInt(2, emp_id);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public Post_LikeVO findByPrimaryKey(Integer post_id, Integer emp_id) {
		Post_LikeVO post_likeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, post_id);
			pstmt.setInt(2, emp_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				post_likeVO = new Post_LikeVO();
				post_likeVO.setPost_id(rs.getInt("post_id"));
				post_likeVO.setEmp_id(rs.getInt("emp_id"));
				
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return post_likeVO;
	}

	@Override
	public List<Post_LikeVO> getAll() {
		List<Post_LikeVO> list = new ArrayList<Post_LikeVO>();
		Post_LikeVO post_likeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				post_likeVO = new Post_LikeVO();
				post_likeVO.setPost_id(rs.getInt("post_id"));
				post_likeVO.setEmp_id(rs.getInt("emp_id"));
				list.add(post_likeVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
//	public static void main(String[] args) {
//		Post_LikeJDBCDAO dao = new Post_LikeJDBCDAO();
//		Post_LikeVO post_likeVO = new Post_LikeVO();
		
//		Insert
//		post_likeVO.setPost_id(1002);
//		post_likeVO.setEmp_id(1011);
//		dao.insert(post_likeVO);
		
// 		Delete-------------------------------------------------------
//		dao.delete(1002,1005);
		
//		select------------------------------------------
//		post_likeVO post_likeVO2 = dao.findByPrimaryKey(1002,1009);
//		System.out.println(post_likeVO2.getPost_id());
//		System.out.println(post_likeVO2.getEmp_id());
		
//		selectALL-------------------------------
//		List<Post_LikeVO> list = dao.getAll();
//		for(Post_LikeVO post_like : list) {
//			System.out.println(post_like.getPost_id());
//			System.out.println(post_like.getEmp_id());
//		}
//	}
}
