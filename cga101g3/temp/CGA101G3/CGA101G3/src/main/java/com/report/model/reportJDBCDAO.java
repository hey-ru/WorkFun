package com.report.model;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class reportJDBCDAO implements reportDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://cga101-03@database-1.cqm5mb4z5ril.ap-northeast-1.rds.amazonaws.com:3306/CGA101-03?zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Taipei";
	String userid = "cga101-03";
	String passwd = "cga101-03";
	private static final String INSERT_STMT = "INSERT INTO report (reporter,handler,starttime,updatetime,endtime,content,status,report_image,report_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM report";
	private static final String GET_ONE_STMT = "SELECT report_id,reporter,handler,starttime,updatetime,endtime,content,status,report_image,report_type FROM report where report_id = ?";
	private static final String UPDATE = "UPDATE report set ";

	@Override
	public void insert(reportVO reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, reportVO.getReporter());
			pstmt.setInt(2, reportVO.getHandler());
			pstmt.setTimestamp(3, reportVO.getStarttime());
			pstmt.setTimestamp(4, reportVO.getUpdatetime());
			pstmt.setTimestamp(5, reportVO.getEndtime());
			pstmt.setString(6, reportVO.getContent());
			pstmt.setInt(7, reportVO.getStatus());
			pstmt.setBytes(8, reportVO.getReport_image());
			pstmt.setInt(9, reportVO.getReport_type());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(reportVO reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			reportVO oldreport = findByPrimaryKey(reportVO.getReport_id());
			System.out.println(oldreport.getReport_id());
			StringBuilder sb=new StringBuilder();
			sb.append(UPDATE);
			
			if(reportVO.getHandler() !=null) {
				sb.append("handler=?, ");
			}
			if(reportVO.getUpdatetime() !=null) {
				sb.append("updatetime=?,");
			}
			if(reportVO.getEndtime() !=null) {
				sb.append("endtime=?, ");
			}
			if(reportVO.getContent() !=null) {
				sb.append("content=?, ");
			}
			if(reportVO.getStatus() !=null) {
				sb.append("status=?, ");
			}
			if(reportVO.getReport_image() !=null) {
				sb.append("report_image=?, ");
			}
			if(reportVO.getReport_type() !=null) {
				sb.append("report_type=?, ");
			}
			sb.append("report_id =?");
			sb.append(" where report_id=?");
			
			
			pstmt = con.prepareStatement(sb.toString());
			System.out.println(sb);
			
			if(reportVO.getHandler() !=null) {
				count++;
				pstmt.setInt(count, reportVO.getHandler());
			}
			
			if(reportVO.getUpdatetime() !=null) {
				count++;
				pstmt.setTimestamp(count, reportVO.getUpdatetime());
			}
			if(reportVO.getEndtime() !=null) {
				count++;
				pstmt.setTimestamp(count, reportVO.getEndtime());
			}
			
			if(reportVO.getContent() !=null) {
				count++;
				pstmt.setString(count, reportVO.getContent());
			}
			
			if(reportVO.getStatus() !=null) {
				count++;
				pstmt.setInt(count, reportVO.getStatus());
			}
			
			if(reportVO.getReport_image() !=null) {
				count++;
				pstmt.setBytes(count, reportVO.getReport_image());
			}
			
			if(reportVO.getReport_type() !=null) {
				count++;
				pstmt.setInt(count, reportVO.getReport_type());
			}
			
			
			
			count++;
			pstmt.setInt(count,reportVO.getReport_id()); 
			count++;
			pstmt.setInt(count,reportVO.getReport_id()); 
			
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setInt(1, reportVO.getReporter());
//			pstmt.setInt(2, reportVO.getHandler());
//			pstmt.setTimestamp(3, reportVO.getStarttime());
//			pstmt.setTimestamp(4, reportVO.getUpdatetime());
//			pstmt.setTimestamp(5, reportVO.getEndtime());
//			pstmt.setString(6, reportVO.getContent());
//			pstmt.setInt(7, reportVO.getStatus());
//			pstmt.setBytes(8, reportVO.getReport_image());
//			pstmt.setInt(9, reportVO.getReport_type());
//			pstmt.setInt(10, reportVO.getReport_id());
//			
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
	public reportVO findByPrimaryKey(Integer report_id) {
		reportVO reportVO = null;
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
				reportVO = new reportVO();
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
	public List<reportVO> getAll() {
		List<reportVO> list = new ArrayList<reportVO>();
		reportVO reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				reportVO = new reportVO();
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
	
	public static void main(String[] args) throws Exception {
		reportJDBCDAO dao = new reportJDBCDAO();
		reportVO repVO = new reportVO();
		
		Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
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
		reportVO reportVO2 = new reportVO();
//		repVO.setReport_id(1005);
//		repVO.setReporter(1005);
//		repVO.setHandler(1020);
//		repVO.setStarttime(timestamp);
//		repVO.setUpdatetime(timestamp);
//		repVO.setEndtime(null);
//		repVO.setContent("");
//		repVO.setStatus(0);
//		repVO.setReport_image(null);
//		repVO.setReport_type(2);
//		dao.update(repVO);
		
		reportVO2.setReport_id(1007);
		reportVO2.setContent("Test");
		reportVO2.setUpdatetime(timestamp);
		dao.update(reportVO2);
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
		}
	}
//}
 