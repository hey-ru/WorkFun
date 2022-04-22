package com.report_comment.model;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class report_commentJDBCDAO implements report_commentDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://cga101-03@database-1.cqm5mb4z5ril.ap-northeast-1.rds.amazonaws.com:3306/CGA101-03?zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Taipei";
	String userid = "cga101-03";
	String passwd = "cga101-03";
	private static final String INSERT_STMT = "INSERT INTO report_comment (report_id,comment,createtime,report_comment_image) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM report_comment";
	private static final String GET_ONE_STMT = "SELECT report_comment_id,report_id,comment,createtime,report_comment_image FROM report_comment where report_comment_id = ?";
	private static final String UPDATE = "UPDATE report_comment set report_id=?,comment=?,createtime=?,report_comment_image=? where report_comment_id = ?";

	@Override
	public void insert(report_commentVO report_commentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void update(report_commentVO report_commentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, report_commentVO.getReport_id());
			pstmt.setString(2, report_commentVO.getComment());
			pstmt.setTimestamp(3, report_commentVO.getCreatetime());
			pstmt.setBytes(4, report_commentVO.getReport_comment_image());
			pstmt.setInt(5, report_commentVO.getReport_comment_id());
			
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
	public report_commentVO findByPrimaryKey(Integer report_comment_id) {
		report_commentVO report_commentVO = null;
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
				report_commentVO = new report_commentVO();
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
	public List<report_commentVO> getAll() {
		List<report_commentVO> list = new ArrayList<report_commentVO>();
		report_commentVO report_commentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				report_commentVO = new report_commentVO();
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

	public static void main(String[] args) throws Exception {
		report_commentJDBCDAO dao = new report_commentJDBCDAO();
		report_commentVO repVO = new report_commentVO();

		Long datetime = System.currentTimeMillis();
		Timestamp timestamp = new Timestamp(datetime);
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
//		repVO2.setReport_id(1004);
//		repVO2.setComment("已經送回廠商維修，維修完成後會再更新並張貼在公告上");
//		repVO2.setCreatetime(timestamp);
//		repVO2.setReport_comment_image(null);
//		repVO2.setReport_comment_id(100001);
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
		List<report_commentVO> list = dao.getAll();
		for (report_commentVO report_commentVO : list) {
			System.out.println(report_commentVO.getReport_comment_id());
			System.out.println(report_commentVO.getReport_comment_image());
			System.out.println(report_commentVO.getCreatetime());
			System.out.println(report_commentVO.getComment());
			System.out.println(report_commentVO.getReport_id());
		}

	}
}

