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
	private static final String UPDATE = "UPDATE report set title=?,content=?,report_image=? where report_id= ?";
	private static final String GET_KEYWORD = "SELECT e1.emp_name as reporterName, e2.emp_name as handlerName, report_id, reporter, handler, starttime, updatetime, endtime, content, status, report_image, report_type, title from report r join emp e1 on e1.emp_id = r.reporter join emp e2 on e2.emp_id = r.handler ORDER BY starttime DESC";
	private static final String GET_HANDLER = "SELECT e1.emp_name as reporterName, e2.emp_name as handlerName, report_id, reporter, handler, starttime, updatetime, endtime, content, status, report_image, report_type, title from report r join emp e1 on e1.emp_id = r.reporter join emp e2 on e2.emp_id = r.handler Where r.handler = ? ORDER BY starttime DESC";
	private static final String HANDLE_COMMENT = "select e1.emp_name as reporterName, e2.emp_name as handlerName,r.report_id, rc.report_id, reporter, handler, starttime, updatetime, endtime, content, status,report_image, report_type, title, comment, report_comment_image from report r join emp e1 on e1.emp_id = r.reporter join emp e2 on e2.emp_id = r.handler join report_comment rc on r.report_id  = rc.report_id ORDER BY starttime DESC";
	private static final String INSERT_COMMENT = "INSERT INTO report_comment (report_id,comment,report_comment_image) VALUES (?, ?, ?)";
	
	private static final String GET_ONE_COMMENT = "select r.report_id , e1.emp_name as reporterName, e2.emp_name as handlerName, reporter, handler, starttime, updatetime, endtime, content, status,report_image, report_type, title from report r join emp e1 on e1.emp_id = r.reporter join emp e2 on e2.emp_id = r.handler Where r.report_id = ?";
	private static final String GET_COMMENT = "select report_comment_id , comment , report_comment_image , createtime from report_comment where report_id = ?";
	
	private static final String REJECT = "update report set status = 1 where report_id = ?";
	private static final String COMPLETE = "update report set status = 2 where report_id = ?";
	private static final String CANCEL = "update report set status = 3 where report_id = ?";
	private static final String ALL_COMPLETED = "update report set status = 4 where report_id = ?";
	
//	private static final String HANDLE_REPORT = "select e1.emp_name as reporterName, e2.emp_name as handlerName,r.report_id, rc.report_id, reporter, handler, starttime, updatetime, endtime, content, status,report_image, report_type, title , comment , report_comment_image , createtime from report r join emp e1 on e1.emp_id = r.reporter join emp e2 on e2.emp_id = r.handler left join report_comment rc on r.report_id = rc.report_id Where r.report_id = ?";
	
	@Override
	public void reject(ReportVO reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(REJECT);
			
			pstmt.setInt(1, reportVO.getReport_id());
		
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
	public void complete(ReportVO reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(COMPLETE);
			
			pstmt.setInt(1, reportVO.getReport_id());
		
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
	public void cancel(ReportVO reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(CANCEL);
			
			pstmt.setInt(1, reportVO.getReport_id());
		
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
	public void allCompleted(ReportVO reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(ALL_COMPLETED);
			
			pstmt.setInt(1, reportVO.getReport_id());
		
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
				recVO.setReport_comment_id(rs.getInt("report_comment_id"));
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
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, reportVO.getTitle());
			pstmt.setString(2, reportVO.getContent());
			pstmt.setBytes(3, reportVO.getReport_image());
			pstmt.setInt(4, reportVO.getReport_id());
		
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
		
}

