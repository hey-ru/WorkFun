package com.notification.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//import org.graalvm.compiler.core.common.alloc.Trace;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;
import java.sql.Date;

public class NotificationJDBCDAO implements NotificationDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://cga101-03@database-1.cqm5mb4z5ril.ap-northeast-1.rds.amazonaws.com:3306/CGA101-03?serverTimezone=Asia/Taipei";
	String userid = "cga101-03";
	String passwd = "cga101-03";

	private static final String INSERT_STMT = "INSERt INTO notification (emp_id,content) VALUES (?,?) ";
    private static final String GET_ALL_STMT = "select notification_id,emp_id,content FROM notification order by notification_id ";
	private static final String GET_ONE_STMT = "SELECT notification_id,emp_id,content FROM notification where Notification_id = ? ";
	private static final String DELETE = "DELETE FROM notification where Notification_id = ? ;";
	//private static final String UPDATE = "UPDATE permission_mapping set permission_name=?  where permission_id = ? ";

	@Override
	public void insert(NotificationVO notificationVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			
			pstmt.setInt(1, notificationVO.getEmpId());
			pstmt.setString(2, notificationVO.getContent());

			pstmt.executeUpdate();
//			ResultSet rs = pstmt.getGeneratedKeys();
//			if (rs.next()) {
//				employee_id = rs.getInt(1);
//				System.out.println(rowCount + " row inserted; order ID: " + employee_id);
//			}

			// Handle any driver errors

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

//	public int update(NotificationVO newPermission) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//			NotificationVO oldPermission = findByPrimaryKey(newPermission.getPermissionId());
//
//			if (newPermission.getPermissionName() != null) {
//				pstmt.setString(1, newPermission.getPermissionName());
//			} else {
//				pstmt.setString(1, oldPermission.getPermissionName());
//
//			}
//
//			pstmt.setInt(2, newPermission.getPermissionId());
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			try {
//				con.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
////			if (pstmt != null) {
////				try {
////					pstmt.close();
////				} catch (SQLException se) {
////					se.printStackTrace(System.err);
////				}
////			}
////			if (con != null) {
////				try {
////					con.close();
////				} catch (Exception e) {
////					e.printStackTrace(System.err);
////				}
////			}
//		}
//		return 1;
//	}

	public void delete(Integer notificationId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, notificationId);

		

			pstmt.executeUpdate();

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

	public NotificationVO findByPrimaryKey(Integer notificationVOId) {
		
		NotificationVO notificationVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, notificationVOId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				notificationVO = new NotificationVO();
								
								notificationVO.setNotificationId(rs.getInt("notification_id"));
								notificationVO.setEmpId(rs.getInt("emp_id"));
								notificationVO.setContent(rs.getString("content"));

								
							}
			
			
			

			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			se.printStackTrace();
//					"A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (Exception e) {
			// TODO: handle exception

		}

		finally {

			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}

		}
		return notificationVO;
	}

	public List<NotificationVO> getAll() {
		List<NotificationVO> list = new ArrayList<NotificationVO>();
		NotificationVO notificationVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// emp_id,dep_id,emp_name,hire_date,phone,extension,emp_password,mail,emp_status
			// FROM emp order by emp_id";

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

notificationVO = new NotificationVO();
				
notificationVO.setNotificationId(rs.getInt("notification_id"));
notificationVO.setEmpId(rs.getInt("emp_id"));
notificationVO.setContent(rs.getString("content"));
				list.add(notificationVO); // Store the row in the list
			}

			// Handle any driver errors

		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	public static void main(String[] args) throws IOException {

		NotificationJDBCDAO dao = new NotificationJDBCDAO();
		NotificationVO notificationVO1 = new NotificationVO();
//
		notificationVO1.setEmpId(1002);
		notificationVO1.setContent("嘿嘿");
		dao.insert(notificationVO1);
		
		
//		NotificationVO1.setHiredate(new Date(1991-1900,6,19));
////		NotificationVO1.setResigndate(4, NotificationVO.getResign_date());
//		NotificationVO1.setPhone("0912323234");
//		NotificationVO1.setExtension("043098753");
//		NotificationVO1.setEmpPassword("19940619");
//		NotificationVO1.setHobby("playtheball2");
//		NotificationVO1.setSkill("sleepuntilafternoon2");
//		NotificationVO1.setEmpProfile(buf);
//		NotificationVO1.setMail("oldma123n2@gmail.com");
//		NotificationVO1.setBirthday(new Date(1971-1900,3,12));
//		NotificationVO1.setEmpStatus((byte)1);
		
//		System.out.println("成功");

//		NotificationVO1.setEname("");
//		NotificationVO1.setJob("MANAGER");
//		NotificationVO1.setHiredate(java.sql.Date.valueOf("2005-01-01"));
//		NotificationVO1.setSal(new Double(50000));
//		NotificationVO1.setComm(new Double(500));
//		NotificationVO1.setDeptno(10);

		// private static final String UPDATE = "UPDATE emp set dep_id=?, emp_name=?,
		// hire_date=?, resign_date=?, phone=?, extension=?, emp_password=?, hobby=?,
		// skill=?, emp_profile=?, mail=?, birthday=?, emp_status=?, where emp_id = ?";

//		
//		NotificationVO NotificationVO2 = new NotificationVO();
//		NotificationVO2.setPermissionId(1);
////		
//		NotificationVO2.setPermissionName("田園風格");
////	
//		System.out.println("成功");
////		
//		dao.update(NotificationVO2);

//		
//		dao.delete(100001);

//		NotificationVO NotificationVO3 = dao.findByPrimaryKey(2);
//		System.out.print(NotificationVO3.getPermissionId());
//		System.out.print(NotificationVO3.getPermissionName() + ",");
//		System.out.print(NotificationVO3.getEmpPassword() + ",");
//		System.out.print(NotificationVO3.getHiredate() + ",");
//		System.out.print(NotificationVO3.getResigndate() + ",");
//		System.out.print(NotificationVO3.getPhone() + ",");
//		System.out.print(NotificationVO3.getEmpProfile() + "Test,");
//		System.out.print(NotificationVO3.getExtension() + ",");
//		System.out.println(NotificationVO3.getMail());
//		System.out.println("---------------------");
//	FileOutputStream fout=new FileOutputStream("/Users/lin/tomcat.gif");
//	fout.write(NotificationVO3.getEmpProfile());
//	fout.close();
		
		
//		NotificationVO notificationVO3=dao.findByPrimaryKey(100002);
//	
//		System.out.print(notificationVO3.getEmpId());
//		System.out.print(notificationVO3.getContent() + ",");
//	
//	
//	
//		List<NotificationVO> list = dao.getAll();
//		for (NotificationVO aEmp : list) {
//			System.out.print(aEmp.getNotificationId() + ",");
//			System.out.print(aEmp.getEmpId() + ",");
//			System.out.print(aEmp.getContent() + ",");
//		
//		
//			System.out.println();
//		}
	}
}