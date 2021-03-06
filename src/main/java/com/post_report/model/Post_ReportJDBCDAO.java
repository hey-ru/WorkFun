package com.post_report.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Post_ReportJDBCDAO implements Post_ReportDAO_interface{
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://cga101-03@database-1.cqm5mb4z5ril.ap-northeast-1.rds.amazonaws.com:3306/CGA101-03?zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Taipei";
	String userid = "cga101-03";
	String passwd = "cga101-03";
	
	private static final String INSERT_STMT = "INSERT INTO post_report (post_id,emp_id,reason,status) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM post_report";
	private static final String GET_ONE_STMT = "SELECT post_report_id,post_id,emp_id,reason,status FROM post_report where post_report_id = ?";
	private static final String UPDATE = "UPDATE post_report set ";

	@Override
	public void insert(Post_ReportVO post_reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, post_reportVO.getPost_id());
			pstmt.setInt(2, post_reportVO.getEmp_id());
			pstmt.setString(3, post_reportVO.getReason());
			pstmt.setInt(4, post_reportVO.getStatus());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (ClassNotFoundException e) {
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
	public void update(Post_ReportVO post_reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0 ;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			Post_ReportVO oldpost_rep = findByPrimaryKey(post_reportVO.getPost_report_id());
			System.out.println(oldpost_rep.getPost_report_id());
			
			StringBuilder sb=new StringBuilder();
			sb.append(UPDATE);

			if(post_reportVO.getReason() !=null) {
				sb.append("reason=?,");
			}
			
			if(post_reportVO.getStatus() !=null) {
				sb.append("status=?,");
			}

			sb.append("post_report_id=?");
			sb.append(" where post_report_id=?");
			
			pstmt = con.prepareStatement(sb.toString());
			
			if(post_reportVO.getReason() !=null) {
				count++;
				pstmt.setString(count, post_reportVO.getReason());
			}
			
			if(post_reportVO.getStatus() !=null) {
				count++;
				pstmt.setInt(count, post_reportVO.getStatus());
			}
			
			count++;
			pstmt.setInt(count, post_reportVO.getPost_report_id());
			count++;
			pstmt.setInt(count, post_reportVO.getPost_report_id());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (ClassNotFoundException e) {
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
	public Post_ReportVO findByPrimaryKey(Integer post_report_id) {
		Post_ReportVO post_reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				post_reportVO = new Post_ReportVO();
				post_reportVO.setPost_report_id(rs.getInt("pos)t_report_id"));
				post_reportVO.setPost_id(rs.getInt("post_id"));
				post_reportVO.setEmp_id(rs.getInt("emp_id"));							
				post_reportVO.setReason(rs.getString("reason"));
				post_reportVO.setStatus(rs.getInt("status"));
				list.add(post_reportVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (ClassNotFoundException e) {
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
//	public static void main(String[] args) throws Exception {
//		post_reportJDBCDAO dao = new post_reportJDBCDAO();
//		post_reportVO post_reportVO = new post_reportVO();
		
//		Insert
//		post_reportVO.setPost_id(1002);
//		post_reportVO.setEmp_id(1011);
//		post_reportVO.setReason("123");
//		post_reportVO.setStatus(0);
//		dao.insert(post_reportVO);
		
// 		Update-------------------------------------------------------
//		post_reportVO post_reportVO2 = new post_reportVO();
//		post_reportVO2.setPost_report_id(102);
//		post_reportVO2.setReason("Test");
//		dao.update(post_reportVO2);

		// -select------------------------------------------
//		post_reportVO post_reportVO3 = dao.findByPrimaryKey(102);
//		System.out.println(post_reportVO3.getPost_id());
//		System.out.println(post_reportVO3.getEmp_id());
//		System.out.println(post_reportVO3.getReason());
//		System.out.println(post_reportVO3.getStatus());
//		System.out.println(post_reportVO3.toString());
		
		// selectALL----------------------------------------
		
//		List<post_reportVO> list = dao.getAll();
//		for (post_reportVO postRe : list) {
//			System.out.println(postRe.getPost_report_id());
//			System.out.println(postRe.getPost_id());
//			System.out.println(postRe.getEmp_id());
//			System.out.println(postRe.getReason());
//			System.out.println(postRe.getStatus());
//		
//	}
	
//	}
}
