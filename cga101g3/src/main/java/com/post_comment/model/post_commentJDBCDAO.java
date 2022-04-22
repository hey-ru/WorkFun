package com.post_comment.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class post_commentJDBCDAO implements post_commentDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://cga101-03@database-1.cqm5mb4z5ril.ap-northeast-1.rds.amazonaws.com:3306/CGA101-03?zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Taipei";
	String userid = "cga101-03";
	String passwd = "cga101-03";
	
	private static final String INSERT_STMT = "INSERT INTO post_comment (post_id,emp_id,post_comment) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM post_comment";
	private static final String GET_ONE_STMT = "SELECT post_comment_id,post_id,emp_id,post_comment,comment_createtime,comment_updatetime FROM post_comment where post_comment_id = ?";
	private static final String UPDATE = "UPDATE post_comment set post_id=?,emp_id=?,post_comment=?,comment_updatetime=? where post_comment_id = ?";
	@Override
	public void insert(post_commentVO post_commentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, post_commentVO.getPost_id());
			pstmt.setInt(2, post_commentVO.getEmp_id());
			pstmt.setString(3, post_commentVO.getPost_comment());

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
	public void update(post_commentVO post_commentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, post_commentVO.getPost_id());
			pstmt.setInt(2, post_commentVO.getEmp_id());
			pstmt.setString(3, post_commentVO.getPost_comment());
			pstmt.setTimestamp(4, post_commentVO.getComment_updatetime());
			pstmt.setInt(5, post_commentVO.getPost_comment_id());

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
	public post_commentVO findByPrimaryKey(Integer post_comment_id) {
		post_commentVO post_commentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, post_comment_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				post_commentVO = new post_commentVO();
				post_commentVO.setPost_comment_id(rs.getInt("post_comment_id"));
				post_commentVO.setPost_id(rs.getInt("post_id"));
				post_commentVO.setEmp_id(rs.getInt("emp_id"));							
				post_commentVO.setPost_comment(rs.getString("post_comment"));
				post_commentVO.setComment_createtime(rs.getTimestamp("comment_createtime"));
				post_commentVO.setComment_updatetime(rs.getTimestamp("comment_updatetime"));
				
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
		return post_commentVO;
	}

	@Override
	public List<post_commentVO> getAll() {
		List<post_commentVO> list = new ArrayList<post_commentVO>();
		post_commentVO post_commentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				post_commentVO = new post_commentVO();
				post_commentVO.setPost_comment_id(rs.getInt("post_comment_id"));
				post_commentVO.setPost_id(rs.getInt("post_id"));
				post_commentVO.setEmp_id(rs.getInt("emp_id"));							
				post_commentVO.setPost_comment(rs.getString("post_comment"));
				post_commentVO.setComment_createtime(rs.getTimestamp("comment_createtime"));
				post_commentVO.setComment_updatetime(rs.getTimestamp("comment_updatetime"));
				list.add(post_commentVO);
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
	public static void main(String[] args) throws Exception {
		post_commentJDBCDAO dao = new post_commentJDBCDAO();
		post_commentVO post_commentVO = new post_commentVO();
		Long datetime = System.currentTimeMillis();
		Timestamp timestamp = new Timestamp(datetime);
		
//		Insert
//		post_commentVO.setPost_id(1002);
//		post_commentVO.setEmp_id(1012);
//		post_commentVO.setPost_comment("我也要吃");
//		dao.insert(post_commentVO);
		
// 		Update-------------------------------------------------------
//		post_commentVO post_commentVO2 = new post_commentVO();
//		post_commentVO2.setPost_comment_id(10003);
//		post_commentVO2.setPost_id(1002);
//		post_commentVO2.setEmp_id(1012);
//		post_commentVO2.setPost_comment("=____=!!!");
//		post_commentVO2.setComment_updatetime(timestamp);
//		dao.update(post_commentVO2);
		
		// -select------------------------------------------
//		post_commentVO post_commentVO3 = dao.findByPrimaryKey(10002);
//		System.out.println(post_commentVO3.getPost_id());
//		System.out.println(post_commentVO3.getEmp_id());
//		System.out.println(post_commentVO3.getPost_comment());
//		System.out.println(post_commentVO3.getComment_updatetime());
//		System.out.println(post_commentVO3.getComment_createtime());
		
	// selectALL----------------------------------------
		
		List<post_commentVO> list = dao.getAll();
		for (post_commentVO postCom : list) {
			System.out.println(postCom.getPost_comment_id());
			System.out.println(postCom.getPost_id());
			System.out.println(postCom.getEmp_id());
			System.out.println(postCom.getPost_comment());
			System.out.println(postCom.getComment_createtime());
			System.out.println(postCom.getComment_updatetime());
		
	}
}
}
