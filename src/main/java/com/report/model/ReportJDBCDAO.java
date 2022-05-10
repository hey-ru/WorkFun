package com.report.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.emp.model.EmpVO;
import com.report_comment.model.Report_CommentVO;

public class ReportJDBCDAO implements ReportDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://cga101-03@database-1.cqm5mb4z5ril.ap-northeast-1.rds.amazonaws.com:3306/CGA101-03?zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Taipei";
	String userid = "cga101-03";
	String passwd = "cga101-03";
	private static final String INSERT_STMT = "INSERT INTO report (reporter,handler,content,report_image,report_type,title) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "select e1.emp_name as reporterName, e2.emp_name as handlerName, report_id, reporter, handler, starttime, updatetime, endtime, content, status, report_image, report_type, title from report r join emp e1 on e1.emp_id = r.reporter join emp e2 on e2.emp_id = r.handler ORDER BY starttime DESC";
	private static final String GET_OWN = "select e1.emp_name as reporterName, e2.emp_name as handlerName, report_id, reporter, handler, starttime, updatetime, endtime, content, status, report_image, report_type, title from report r join emp e1 on e1.emp_id = r.reporter join emp e2 on e2.emp_id = r.handler WHERE reporter = ? ORDER BY starttime DESC";
	
	private static final String GET_ONE_STMT = "SELECT e1.emp_name as reporterName, e2.emp_name as handlerName, report_id, reporter, handler, starttime, updatetime, endtime, content, status, report_image, report_type, title from report r join emp e1 on e1.emp_id = r.reporter join emp e2 on e2.emp_id = r.handler where report_id = ?";
	private static final String UPDATE = "UPDATE report set title=?,report_type=?,reporter=?,handler=?,content=?,report_image=? where report_id= ?";
	private static final String GET_KEYWORD = "SELECT e1.emp_name as reporterName, e2.emp_name as handlerName, report_id, reporter, handler, starttime, updatetime, endtime, content, status, report_image, report_type, title from report r join emp e1 on e1.emp_id = r.reporter join emp e2 on e2.emp_id = r.handler ORDER BY starttime DESC";
	private static final String GET_HANDLER = "SELECT e1.emp_name as reporterName, e2.emp_name as handlerName, report_id, reporter, handler, starttime, updatetime, endtime, content, status, report_image, report_type, title from report r join emp e1 on e1.emp_id = r.reporter join emp e2 on e2.emp_id = r.handler Where r.handler = ? ORDER BY starttime DESC";
	private static final String HANDLE_COMMENT = "select e1.emp_name as reporterName, e2.emp_name as handlerName,r.report_id, rc.report_id, reporter, handler, starttime, updatetime, endtime, content, status,report_image, report_type, title, comment, report_comment_image from report r join emp e1 on e1.emp_id = r.reporter join emp e2 on e2.emp_id = r.handler join report_comment rc on r.report_id  = rc.report_id ORDER BY starttime DESC";
	private static final String INSERT_COMMENT = "INSERT INTO report_comment (report_id,comment,report_comment_image) VALUES (?, ?, ?)";
	
	private static final String GET_ONE_COMMENT = "select r.report_id , e1.emp_name as reporterName, e2.emp_name as handlerName, reporter, handler, starttime, updatetime, endtime, content, status,report_image, report_type, title from report r join emp e1 on e1.emp_id = r.reporter join emp e2 on e2.emp_id = r.handler Where r.report_id = ?";
	private static final String GET_COMMENT = "select comment , report_comment_image , createtime from report_comment where report_id = ?";
	
//	private static final String HANDLE_REPORT = "select e1.emp_name as reporterName, e2.emp_name as handlerName,r.report_id, rc.report_id, reporter, handler, starttime, updatetime, endtime, content, status,report_image, report_type, title , comment , report_comment_image , createtime from report r join emp e1 on e1.emp_id = r.reporter join emp e2 on e2.emp_id = r.handler left join report_comment rc on r.report_id = rc.report_id Where r.report_id = ?";
	
	@Override
	public List<ReportVO> getOwnReport(Integer reporter) {
		List<ReportVO> list = new ArrayList<ReportVO>();
		ReportVO reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_OWN);
			pstmt.setInt(1, reporter);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				EmpVO empVO1 = new EmpVO();
				EmpVO empVO2 = new EmpVO();
				empVO1.setEmpName(rs.getString("reporterName"));
				empVO2.setEmpName(rs.getString("handlerName"));
				reportVO = new ReportVO();
				reportVO.setEmpVO1(empVO1);
				reportVO.setEmpVO2(empVO2);
				reportVO.setReport_id(rs.getInt("report_id"));
				reportVO.setReporter(rs.getInt("reporter"));
				reportVO.setHandler(rs.getInt("handler"));
				reportVO.setStarttime(rs.getTimestamp("starttime"));
				reportVO.setUpdatetime(rs.getTimestamp("updatetime"));
				reportVO.setEndtime(rs.getTimestamp("endtime"));
				reportVO.setContent(rs.getString("content"));
				reportVO.setStatus(rs.getInt("status"));
				reportVO.setReport_image(rs.getBytes("report_image"));
				reportVO.setReport_type(rs.getInt("report_type"));
				reportVO.setTitle(rs.getString("title"));
				
				list.add(reportVO);
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
	
	@Override
	public List<Report_CommentVO> TestComment(Integer report_id) {
		List<Report_CommentVO> list = new ArrayList<Report_CommentVO>();
		Report_CommentVO recVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_COMMENT);

			pstmt.setInt(1, report_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				recVO = new Report_CommentVO();
				recVO.setComment(rs.getString("comment"));
				recVO.setReport_comment_image(rs.getBytes("report_comment_image"));
				recVO.setCreatetime(rs.getTimestamp("createtime"));
			
				list.add(recVO);
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
		return list;
	}
	
	@Override
	public ReportVO findReport(Integer report_id) {
		ReportVO reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_COMMENT);

			pstmt.setInt(1, report_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				reportVO = new ReportVO();
				EmpVO empVO1 = new EmpVO();
				EmpVO empVO2 = new EmpVO();
				empVO1.setEmpName(rs.getString("reporterName"));
				empVO2.setEmpName(rs.getString("handlerName"));
				reportVO.setEmpVO1(empVO1);
				reportVO.setEmpVO2(empVO2);
				reportVO.setReport_id(rs.getInt("report_id"));
				reportVO.setReporter(rs.getInt("reporter"));
				reportVO.setHandler(rs.getInt("handler"));
				reportVO.setStarttime(rs.getTimestamp("starttime"));
				reportVO.setUpdatetime(rs.getTimestamp("updatetime"));
				reportVO.setEndtime(rs.getTimestamp("endtime"));
				reportVO.setContent(rs.getString("content"));
				reportVO.setStatus(rs.getInt("status"));
				reportVO.setReport_image(rs.getBytes("report_image"));
				reportVO.setReport_type(rs.getInt("report_type"));
				reportVO.setTitle(rs.getString("title"));
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
		return reportVO;
	}
	
	@Override
	public void insertComment(ReportVO reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_COMMENT);
			
			pstmt.setInt(1, reportVO.getReport_id());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void handleComment(ReportVO reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(HANDLE_COMMENT);
		Report_CommentVO recVO = new Report_CommentVO();
	
		pstmt.setString(1, reportVO.getTitle());
	
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
	public List<ReportVO> getHandler(Integer handler) {
		List<ReportVO> list = new ArrayList<ReportVO>();
		ReportVO reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_HANDLER);
			pstmt.setInt(1, handler);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				EmpVO empVO1 = new EmpVO();
				EmpVO empVO2 = new EmpVO();
				empVO1.setEmpName(rs.getString("reporterName"));
				empVO2.setEmpName(rs.getString("handlerName"));
				reportVO = new ReportVO();
				reportVO.setEmpVO1(empVO1);
				reportVO.setEmpVO2(empVO2);
				reportVO.setReport_id(rs.getInt("report_id"));
				reportVO.setReporter(rs.getInt("reporter"));
				reportVO.setHandler(rs.getInt("handler"));
				reportVO.setStarttime(rs.getTimestamp("starttime"));
				reportVO.setUpdatetime(rs.getTimestamp("updatetime"));
				reportVO.setEndtime(rs.getTimestamp("endtime"));
				reportVO.setContent(rs.getString("content"));
				reportVO.setStatus(rs.getInt("status"));
				reportVO.setReport_image(rs.getBytes("report_image"));
				reportVO.setReport_type(rs.getInt("report_type"));
				reportVO.setTitle(rs.getString("title"));
				
				list.add(reportVO);
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
	@Override
	public void insert(ReportVO reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, reportVO.getReporter());
			pstmt.setInt(2, reportVO.getHandler());
			pstmt.setString(3, reportVO.getContent());
			pstmt.setBytes(4, reportVO.getReport_image());
			pstmt.setInt(5, reportVO.getReport_type());
			pstmt.setString(6, reportVO.getTitle());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void update(ReportVO reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
//		int count = 0;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

//			ReportVO oldreport = findByPrimaryKey(reportVO.getReport_id());
//			System.out.println(oldreport.getReport_id());
//			StringBuilder sb = new StringBuilder();
//			sb.append(UPDATE);
//
//			if (reportVO.getHandler() != null) {
//				sb.append("handler=?, ");
//			}
//			if (reportVO.getUpdatetime() != null) {
//				sb.append("updatetime=?,");
//			}
//			if (reportVO.getEndtime() != null) {
//				sb.append("endtime=?, ");
//			}
//			if (reportVO.getContent() != null) {
//				sb.append("content=?, ");
//			}
//			if (reportVO.getStatus() != null) {
//				sb.append("status=?, ");
//			}
//			if (reportVO.getReport_image() != null) {
//				sb.append("report_image=?, ");
//			}
//			if (reportVO.getReport_type() != null) {
//				sb.append("report_type=?, ");
//			}
//			if (reportVO.getTitle() != null) {
//				sb.append("title=?, ");
//			}
//			sb.append("report_id =?");
//			sb.append(" where report_id=?");
//
//			pstmt = con.prepareStatement(sb.toString());
//
//			if (reportVO.getHandler() != null) {
//				count++;
//				pstmt.setInt(count, reportVO.getHandler());
//			}
//
//			if (reportVO.getUpdatetime() != null) {
//				count++;
//				pstmt.setTimestamp(count, reportVO.getUpdatetime());
//			}
//			if (reportVO.getEndtime() != null) {
//				count++;
//				pstmt.setTimestamp(count, reportVO.getEndtime());
//			}
//
//			if (reportVO.getContent() != null) {
//				count++;
//				pstmt.setString(count, reportVO.getContent());
//			}
//
//			if (reportVO.getStatus() != null) {
//				count++;
//				pstmt.setInt(count, reportVO.getStatus());
//			}
//
//			if (reportVO.getReport_image() != null) {
//				count++;
//				pstmt.setBytes(count, reportVO.getReport_image());
//			}
//
//			if (reportVO.getReport_type() != null) {
//				count++;
//				pstmt.setInt(count, reportVO.getReport_type());
//			}
//			
//			if (reportVO.getTitle() != null) {
//				count++;
//				pstmt.setString(count,reportVO.getTitle());
//			}
//
//			count++;
//			pstmt.setInt(count, reportVO.getReport_id());
//			count++;
//			pstmt.setInt(count, reportVO.getReport_id());

			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, reportVO.getTitle());
			pstmt.setInt(2, reportVO.getReport_type());
			pstmt.setInt(3, reportVO.getReporter());
			pstmt.setInt(4, reportVO.getHandler());
			pstmt.setString(5, reportVO.getContent());
			pstmt.setBytes(6, reportVO.getReport_image());
			pstmt.setInt(7, reportVO.getReport_id());
		
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
	public ReportVO findByPrimaryKey(Integer report_id) {
		ReportVO reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, report_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				reportVO = new ReportVO();
				EmpVO empVO1 = new EmpVO();
				EmpVO empVO2 = new EmpVO();
				empVO1.setEmpName(rs.getString("reporterName"));
				empVO2.setEmpName(rs.getString("handlerName"));
				reportVO.setEmpVO1(empVO1);
				reportVO.setEmpVO2(empVO2);
				reportVO.setReport_id(rs.getInt("report_id"));
				reportVO.setReporter(rs.getInt("reporter"));
				reportVO.setHandler(rs.getInt("handler"));
				reportVO.setStarttime(rs.getTimestamp("starttime"));
				reportVO.setUpdatetime(rs.getTimestamp("updatetime"));
				reportVO.setEndtime(rs.getTimestamp("endtime"));
				reportVO.setContent(rs.getString("content"));
				reportVO.setStatus(rs.getInt("status"));
				reportVO.setReport_image(rs.getBytes("report_image"));
				reportVO.setReport_type(rs.getInt("report_type"));
				reportVO.setTitle(rs.getString("title"));
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
		return reportVO;
	}

	@Override
	public List<ReportVO> getAll() {
		List<ReportVO> list = new ArrayList<ReportVO>();
		ReportVO reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				EmpVO empVO1 = new EmpVO();
				EmpVO empVO2 = new EmpVO();
				empVO1.setEmpName(rs.getString("reporterName"));
				empVO2.setEmpName(rs.getString("handlerName"));
				reportVO = new ReportVO();
				reportVO.setEmpVO1(empVO1);
				reportVO.setEmpVO2(empVO2);
				reportVO.setReport_id(rs.getInt("report_id"));
				reportVO.setReporter(rs.getInt("reporter"));
				reportVO.setHandler(rs.getInt("handler"));
				reportVO.setStarttime(rs.getTimestamp("starttime"));
				reportVO.setUpdatetime(rs.getTimestamp("updatetime"));
				reportVO.setEndtime(rs.getTimestamp("endtime"));
				reportVO.setContent(rs.getString("content"));
				reportVO.setStatus(rs.getInt("status"));
				reportVO.setReport_image(rs.getBytes("report_image"));
				reportVO.setReport_type(rs.getInt("report_type"));
				reportVO.setTitle(rs.getString("title"));
				
				list.add(reportVO);
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

	@Override
	public List<ReportVO> find(Integer handler,Integer status,Integer report_type) {
		List<ReportVO> list = new ArrayList<ReportVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			StringBuilder sb = new StringBuilder();
			sb.append(GET_KEYWORD);

			//List<String> list2 = new ArrayList<String>();
			if ((handler != null) && (status != null) && (report_type != null)) {
				sb.append(" Where ");
			}
			
			if (handler != null) {
				sb.append("handler=? and ");
			}
			if (status != null) {
				sb.append("status=? and ");
			}
			if (report_type != null) {
				sb.append("report_type=? ");
			}
			
//			for (int i = 0; i < list2.size(); i++) {
//				sb.append(i == 0 ? list2.get(i) : " and " + list2.get(i));
//			}
			System.out.println(sb);
			//System.out.println(list2);
//			List<String> list2 = new ArrayList<String>();
//			for (int i = 0; i < params.size(); i  ) {
//				String str = params.get(i);
//				String[] arr = str.split(",");
			pstmt = con.prepareStatement(sb.toString());
			

			if (handler != null) {
				count++;
				pstmt.setInt(count, handler);
			}

			if (status != null) {
				count++;
				pstmt.setInt(count, status);
			}
			if (report_type != null) {
				count++;
				pstmt.setInt(count, report_type);
			}
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ReportVO reportVO = new ReportVO();
				reportVO.setReport_id(rs.getInt("report_id"));
				reportVO.setReporter(rs.getInt("reporter"));
				reportVO.setHandler(rs.getInt("handler"));
				reportVO.setStarttime(rs.getTimestamp("starttime"));
				reportVO.setUpdatetime(rs.getTimestamp("updatetime"));
				reportVO.setEndtime(rs.getTimestamp("endtime"));
				reportVO.setContent(rs.getString("content"));
				reportVO.setStatus(rs.getInt("status"));
				reportVO.setReport_image(rs.getBytes("report_image"));
				reportVO.setReport_type(rs.getInt("report_type"));
				list.add(reportVO);
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

	@Override
	public List<ReportVO> getAll(Map<String, String[]> map) {
		List<ReportVO> list = new ArrayList<ReportVO>();
		ReportVO reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	try {			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String finalSQL = "select e1.emp_name as reporterName, e2.emp_name as handlerName, report_id, reporter, handler, starttime, updatetime, endtime, content, status, report_image, report_type, title from report r join emp e1 on e1.emp_id = r.reporter join emp e2 on e2.emp_id = r.handler"
		          + QueryReport.get_WhereCondition(map)
		          + "order by starttime DESC";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				reportVO = new ReportVO();
				EmpVO empVO1 = new EmpVO();
				EmpVO empVO2 = new EmpVO();
				empVO1.setEmpName(rs.getString("reporterName"));
				empVO2.setEmpName(rs.getString("handlerName"));
				reportVO.setEmpVO1(empVO1);
				reportVO.setEmpVO2(empVO2);
				reportVO.setReport_id(rs.getInt("report_id"));
				reportVO.setReporter(rs.getInt("reporter"));
				reportVO.setHandler(rs.getInt("handler"));
				reportVO.setStarttime(rs.getTimestamp("starttime"));
				reportVO.setUpdatetime(rs.getTimestamp("updatetime"));
				reportVO.setEndtime(rs.getTimestamp("endtime"));
				reportVO.setContent(rs.getString("content"));
				reportVO.setStatus(rs.getInt("status"));
				reportVO.setReport_image(rs.getBytes("report_image"));
				reportVO.setReport_type(rs.getInt("report_type"));
				reportVO.setTitle(rs.getString("title"));
				list.add(reportVO);
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
//		ReportJDBCDAO rep = new ReportJDBCDAO();
//		 ReportService repSvc = new ReportService();
//		    Set<ReportVO> set = rep.handleReport(1032);
		    
//		    for(ReportVO repa : set) {
//		    	for(Report_CommentVO rec : repa.getRecVO2()) {
//				System.out.println(rec.getComment());
//				System.out.println(rec.getCreatetime());
//				System.out.println(rec.getReport_comment_image());
//		    	}
//		    }
//	}
		
//		reportVO repVO = new reportVO();
//
//		Long datetime = System.currentTimeMillis();
//        Timestamp timestamp = new Timestamp(datetime);
//        FileInputStream in = new FileInputStream("C:\\Users\\Tibame_T14\\Desktop\\tomcat2.gif");
//		byte[] buf = new byte[in.available()];
//		in.read(buf);
//		Insert
//      repVO.setReporter(1002);
//		repVO.setHandler(1017);
//		repVO.setStarttime(timestamp);
//		repVO.setUpdatetime(timestamp);
//		repVO.setEndtime(null);
//		repVO.setContent("");
//		repVO.setStatus(0);
//		repVO.setReport_image(null);
//		repVO.setReport_type(1);
//		dao.insert(repVO);
//		
//		in.close();

// 		Update-------------------------------------------------------
//		ReportVO repVO = new ReportVO();
//		repVO.setTitle("測試更新");
//		repVO.setReporter(1005);
//		repVO.setHandler(1020);
//		repVO.setStarttime(timestamp);
//		repVO.setUpdatetime(timestamp);
//		repVO.setEndtime(null);
//		repVO.setContent("Test");
//		repVO.setStatus(1);
//		repVO.setReport_image(null);
//		repVO.setReport_type(2);
//		repVO.setReport_id(1001);
//		dao.update(repVO);

//		in.close();

//-select------------------------------------------
//		reportVO reportVO3 = dao.findByPrimaryKey(1001);
//		System.out.println(reportVO3.getHandler());
//		System.out.println(reportVO3.getReporter());
//		System.out.println(reportVO3.getContent());
//		System.out.println(reportVO3.getStarttime());
//		System.out.println(reportVO3.getUpdatetime());
//		System.out.println(reportVO3.getEndtime());
//		System.out.println(reportVO3.getStatus());
//		System.out.println(reportVO3.getReport_type());
//		System.out.println(reportVO3.getReport_image());
//		System.out.println(reportVO3.getReport_id());

//-selectALL-------------------------------
//		List<reportVO> list = dao.getAll();
//		for(reportVO rep : list) {
//			System.out.println(rep.getHandler());
//			System.out.println(rep.getReporter());
//			System.out.println(rep.getContent());
//			System.out.println(rep.getStarttime());
//			System.out.println(rep.getUpdatetime());
//			System.out.println(rep.getEndtime());
//			System.out.println(rep.getStatus());
//			System.out.println(rep.getReport_type());
//			System.out.println(rep.getReport_image());
//			System.out.println(rep.getReport_id());
//		}

//----getKEYWORD------------------------------------	
		//repVO.setHandler(1017);
//		
//		List<reportVO> list = dao.find(1017,0,0);
//		for (reportVO rep1 : list) {
//			System.out.println(rep1.getHandler());
//			System.out.println(rep1.getReporter());
//			System.out.println(rep1.getContent());
//			System.out.println(rep1.getStarttime());
//			System.out.println(rep1.getUpdatetime());
//			System.out.println(rep1.getEndtime());
//			System.out.println(rep1.getStatus());
//			System.out.println(rep1.getReport_type());
//			System.out.println(rep1.getReport_image());
//			System.out.println(rep1.getReport_id());
//		}
//	}
		
}

