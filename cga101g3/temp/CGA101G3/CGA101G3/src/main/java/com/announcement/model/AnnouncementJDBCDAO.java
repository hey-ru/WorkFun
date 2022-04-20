package com.announcement.model;

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

public class AnnouncementJDBCDAO implements AnnouncementDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://cga101-03@database-1.cqm5mb4z5ril.ap-northeast-1.rds.amazonaws.com:3306/CGA101-03?serverTimezone=Asia/Taipei";
	String userid = "cga101-03";
	String passwd = "cga101-03";

	private static final String INSERT_STMT = "INSERT INTO announcement (announcer,announcement_title,announcement_content) VALUES (?,?,?) ";
	private static final String GET_ALL_STMT = "select announcement_id,announcer,announcement_title,announcement_content,announcement_time,announcement_status FROM announcement order by announcement_id ";
	private static final String GET_ONE_STMT = "SELECT announcement_id,announcer,announcement_title,announcement_content,announcement_time,announcement_status FROM announcement where announcement_id = ? ";
	private static final String DELETE = "DELETE FROM announcement where announcement_id = ?  ;";
	private static final String UPDATE = "UPDATE announcement set announcement_title=?  where announcement_content = ? ";

	@Override
	public void insert(AnnouncementVO announcementVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setInt(1, announcementVO.getAnnouncerId());
			pstmt.setString(2, announcementVO.getAnnouncementTitle());
			pstmt.setString(3, announcementVO.getAnnouncementContent());

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

	public int update(AnnouncementVO announcement) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			AnnouncementVO oldPermission = findByPrimaryKey(announcement.getAnnouncementId());

			if (announcement.getAnnouncementTitle() != null) {
				pstmt.setString(1, announcement.getAnnouncementTitle());
			} else {
				pstmt.setString(1, oldPermission.getAnnouncementTitle());

			}
			if (announcement.getAnnouncementContent() != null) {
				pstmt.setString(2, announcement.getAnnouncementContent());
			} else {
				pstmt.setString(2, oldPermission.getAnnouncementContent());
			}
			

			pstmt.setInt(3, announcement.getAnnouncementId());

			pstmt.executeUpdate();

			// Handle any driver errors

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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
		return 1;
	}

	public void delete(Integer announcementId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, announcementId);

		

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

	public AnnouncementVO findByPrimaryKey(Integer announcementId) {
		
		AnnouncementVO announcementVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, announcementId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				announcementVO = new AnnouncementVO();
								
								announcementVO.setAnnouncementId(rs.getInt("announcement_id"));
								announcementVO.setAnnouncerId(rs.getInt("announcer_id"));
								announcementVO.setAnnouncementTitle(rs.getString("announcer_title"));
								announcementVO.setAnnouncementContent(rs.getString("announcer_content"));
								announcementVO.setAnnouncementTime(rs.getTimestamp("announcer_time"));
								announcementVO.setAnnouncementStatus(rs.getByte("announcer_status"));

								
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
		return announcementVO;
	}

	public List<AnnouncementVO> getAll() {
		List<AnnouncementVO> list = new ArrayList<AnnouncementVO>();
		AnnouncementVO announcementVO = null;

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

announcementVO = new AnnouncementVO();
announcementVO.setAnnouncementId(rs.getInt("announcement_id"));
announcementVO.setAnnouncerId(rs.getInt("announcer_id"));
announcementVO.setAnnouncementTitle(rs.getString("announcer_title"));
announcementVO.setAnnouncementContent(rs.getString("announcer_content"));
announcementVO.setAnnouncementTime(rs.getTimestamp("announcer_time"));
announcementVO.setAnnouncementStatus(rs.getByte("announcer_status"));


				list.add(announcementVO); // Store the row in the list
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

		AnnouncementJDBCDAO dao = new AnnouncementJDBCDAO();
//		AnnouncementVO AnnouncementVO1 = new AnnouncementVO();
//
//		AnnouncementVO1.setPermissionId(3);
//		AnnouncementVO1.setEmpId(1007);
//		dao.insert(AnnouncementVO1);
//		AnnouncementVO1.setHiredate(new Date(1991-1900,6,19));
////		AnnouncementVO1.setResigndate(4, AnnouncementVO.getResign_date());
//		AnnouncementVO1.setPhone("0912323234");
//		AnnouncementVO1.setExtension("043098753");
//		AnnouncementVO1.setEmpPassword("19940619");
//		AnnouncementVO1.setHobby("playtheball2");
//		AnnouncementVO1.setSkill("sleepuntilafternoon2");
//		AnnouncementVO1.setEmpProfile(buf);
//		AnnouncementVO1.setMail("oldma123n2@gmail.com");
//		AnnouncementVO1.setBirthday(new Date(1971-1900,3,12));
//		AnnouncementVO1.setEmpStatus((byte)1);
		
//		System.out.println("成功");

//		AnnouncementVO1.setEname("");
//		AnnouncementVO1.setJob("MANAGER");
//		AnnouncementVO1.setHiredate(java.sql.Date.valueOf("2005-01-01"));
//		AnnouncementVO1.setSal(new Double(50000));
//		AnnouncementVO1.setComm(new Double(500));
//		AnnouncementVO1.setDeptno(10);

		// private static final String UPDATE = "UPDATE emp set dep_id=?, emp_name=?,
		// hire_date=?, resign_date=?, phone=?, extension=?, emp_password=?, hobby=?,
		// skill=?, emp_profile=?, mail=?, birthday=?, emp_status=?, where emp_id = ?";

//		
//		AnnouncementVO AnnouncementVO2 = new AnnouncementVO();
//		AnnouncementVO2.setPermissionId(1);
////		
//		AnnouncementVO2.setPermissionName("田園風格");
////	
//		System.out.println("成功");
////		
//		dao.update(AnnouncementVO2);

//		
//		dao.delete(1007,3);

//		AnnouncementVO AnnouncementVO3 = dao.findByPrimaryKey(2);
//		System.out.print(AnnouncementVO3.getPermissionId());
//		System.out.print(AnnouncementVO3.getPermissionName() + ",");
//		System.out.print(AnnouncementVO3.getEmpPassword() + ",");
//		System.out.print(AnnouncementVO3.getHiredate() + ",");
//		System.out.print(AnnouncementVO3.getResigndate() + ",");
//		System.out.print(AnnouncementVO3.getPhone() + ",");
//		System.out.print(AnnouncementVO3.getEmpProfile() + "Test,");
//		System.out.print(AnnouncementVO3.getExtension() + ",");
//		System.out.println(AnnouncementVO3.getMail());
//		System.out.println("---------------------");
//	FileOutputStream fout=new FileOutputStream("/Users/lin/tomcat.gif");
//	fout.write(AnnouncementVO3.getEmpProfile());
//	fout.close();
//		Set<AnnouncementVO> set=dao.findByPrimaryKey(1001);
//	
//		for (AnnouncementVO aEmp : set) {
//			System.out.print(aEmp.getEmpId() + ",");
//			System.out.print(aEmp.getPermissionId() + ",");
//			
//		
//			System.out.println();
//		}
//		List<AnnouncementVO> list = dao.getAll();
//		for (AnnouncementVO aEmp : list) {
//			System.out.print(aEmp.getEmpId() + ",");
//			System.out.print(aEmp.getDepId() + ",");
//			System.out.print(aEmp.getEmpName() + ",");
//			System.out.print(aEmp.getHiredate() + ",");
//			System.out.print(aEmp.getPhone() + ",");
//			System.out.print(aEmp.getExtension() + ",");
//		
//			System.out.println();
//		}
	}
}