package com.report_comment.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.report.model.ReportVO;

public class Report_CommentJDBCDAO implements Report_CommentDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://cga101-03@database-1.cqm5mb4z5ril.ap-northeast-1.rds.amazonaws.com:3306/CGA101-03?zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Taipei";
	String userid = "cga101-03";
	String passwd = "cga101-03";
	private static final String INSERT_STMT = "INSERT INTO report_comment (report_id,comment,report_comment_image) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = "select e1.emp_name as reporterName, e2.emp_name as handlerName,r.report_id, rc.report_id, reporter, handler, starttime, updatetime, endtime, content, status,report_image, report_type, title, comment, report_comment_image from report r join emp e1 on e1.emp_id = r.reporter join emp e2 on e2.emp_id = r.handler join report_comment rc on r.report_id  = rc.report_id ORDER BY starttime DESC";
	private static final String GET_ONE_STMT = "SELECT * FROM report_comment where report_comment_id = ?";
	private static final String UPDATE = "UPDATE report_comment set ";
	
	
	
	@Override
	public List<ReportVO> getHandler() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void insert(Report_CommentVO report_commentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, report_commentVO.getReport_id());
			pstmt.setString(2, report_commentVO.getComment());
			pstmt.setBytes(3, report_commentVO.getReport_comment_image());
				
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
	public void update(Report_CommentVO report_commentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			Report_CommentVO oldreport_comment = findByPrimaryKey(report_commentVO.getReport_comment_id());
			System.out.println(oldreport_comment.getReport_comment_id());
			StringBuilder sb=new StringBuilder();
			sb.append(UPDATE);

			if(report_commentVO.getReport_comment_image() !=null) {
				sb.append("comment_image=?,");
			}
			
			if(report_commentVO.getComment() !=null) {
				sb.append("comment=?,");
			}
			
			sb.append("report_comment_id=?");
			sb.append(" where report_comment_id=?");
			
			pstmt = con.prepareStatement(sb.toString());
			
			if(report_commentVO.getComment() !=null) {
				count++;
				pstmt.setString(count, report_commentVO.getComment());
			}
			if(report_commentVO.getReport_comment_image() !=null) {
				count++;
				pstmt.setBytes(count, report_commentVO.getReport_comment_image());
			}
			count++;
			pstmt.setInt(count,report_commentVO.getReport_comment_id()); 
			count++;
			pstmt.setInt(count,report_commentVO.getReport_comment_id()); 

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
	public Report_CommentVO findByPrimaryKey(Integer report_comment_id) {
		Report_CommentVO report_commentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				report_commentVO = new Report_CommentVO();
				report_commentVO.setReport_comment_id(rs.getInt("report_comment_id"));
				report_commentVO.setReport_id(rs.getInt("report_id"));
				report_commentVO.setComment(rs.getString("comment"));
				report_commentVO.setCreatetime(rs.getTimestamp("createtime"));
				report_commentVO.setReport_comment_image(rs.getBytes("report_comment_image"));
				list.add(report_commentVO);
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


//	public static void main(String[] args) throws Exception {
//		report_commentJDBCDAO dao = new report_commentJDBCDAO();
//		report_commentVO repVO = new report_commentVO();
//
//		Long datetime = System.currentTimeMillis();
//		Timestamp timestamp = new Timestamp(datetime);
//		FileInputStream in = new FileInputStream("C:\\Users\\Tibame_T14\\Desktop\\tomcat2.gif");
//		byte[] b = new byte[in.available()];
//		in.read(b);
//		Insert
//		repVO.setReport_id(1002);
//		repVO.setComment("近期會找相關業者幫忙美化辦公室環境");
//		repVO.setCreatetime(timestamp);
//		repVO.setReport_comment_image(b);
//		dao.insert(repVO);
		

// 		Update-------------------------------------------------------
//		report_commentVO repVO2 = new report_commentVO();
//		repVO2.setReport_comment_id(100001);
//		repVO2.setComment("測試TEST");
//		dao.update(repVO2);
//		
//		in.close();

//-查詢------------------------------------------
//		report_commentVO report_commentVO3 = dao.findByPrimaryKey(100001);
//		System.out.println(report_commentVO3.getReport_comment_id());
//		System.out.println(report_commentVO3.getReport_comment_image());
//		System.out.println(report_commentVO3.getCreatetime());
//		System.out.println(report_commentVO3.getComment());
//		System.out.println(report_commentVO3.getReport_id());

//-查詢全部-------------------------------
//		List<report_commentVO> list = dao.getAll();
//		for (report_commentVO report_commentVO : list) {
//			System.out.println(report_commentVO.getReport_comment_id());
//			System.out.println(report_commentVO.getReport_comment_image());
//			System.out.println(report_commentVO.getCreatetime());
//			System.out.println(report_commentVO.getComment());
//			System.out.println(report_commentVO.getReport_id());
		

}


