package com.post_keep.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Post_KeepJDBCDAO implements Post_KeepDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://cga101-03@database-1.cqm5mb4z5ril.ap-northeast-1.rds.amazonaws.com:3306/CGA101-03?zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Taipei";
	String userid = "cga101-03";
	String passwd = "cga101-03";
	
	private static final String INSERT_STMT = "INSERT INTO post_keep (post_id,emp_id) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM post_keep";
	private static final String GET_ONE_STMT = "SELECT post_id,emp_id FROM post_keep where post_id = ? AND emp_id = ?";
	private static final String DELETE = "DELETE FROM post_keep where post_id = ? AND emp_id = ?";
	@Override
	public void insert(Post_KeepVO post_keepVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, post_keepVO.getPost_id());
			pstmt.setInt(2, post_keepVO.getEmp_id());

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
	public Post_KeepVO findByPrimaryKey(Integer post_id, Integer emp_id) {
		Post_KeepVO post_keepVO = null;
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
				post_keepVO = new Post_KeepVO();
				post_keepVO.setPost_id(rs.getInt("post_id"));
				post_keepVO.setEmp_id(rs.getInt("emp_id"));
				
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
		return post_keepVO;
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
	public List<Post_KeepVO> getAll() {
		List<Post_KeepVO> list = new ArrayList<Post_KeepVO>();
		Post_KeepVO post_keepVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				post_keepVO = new Post_KeepVO();
				post_keepVO.setPost_id(rs.getInt("post_id"));
				post_keepVO.setEmp_id(rs.getInt("emp_id"));
				list.add(post_keepVO);
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
//		Post_KeepJDBCDAO dao = new Post_KeepJDBCDAO();
//		Post_KeepVO post_keepVO = new Post_KeepVO();
		
//		Insert
//		post_keepVO.setPost_id(1003);
//		post_keepVO.setEmp_id(1015);
//		dao.insert(post_keepVO);
		
// 		Delete-------------------------------------------------------
//		dao.delete(1003,1005);
		
//		select------------------------------------------
//		post_keepVO post_keepVO2 = dao.findByPrimaryKey(1003,1015);
//		System.out.println(post_keepVO2.getPost_id());
//		System.out.println(post_keepVO2.getEmp_id());
		
//		selectALL-------------------------------
//		List<Post_KeepVO> list = dao.getAll();
//		for(Post_KeepVO post_keep : list) {
//			System.out.println(post_keep.getPost_id());
//			System.out.println(post_keep.getEmp_id());
//		}
//}
}

	

