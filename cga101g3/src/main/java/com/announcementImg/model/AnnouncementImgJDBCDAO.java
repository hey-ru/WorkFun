package com.announcementImg.model;

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

public class AnnouncementImgJDBCDAO implements AnnouncementImgDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://cga101-03@database-1.cqm5mb4z5ril.ap-northeast-1.rds.amazonaws.com:3306/CGA101-03?serverTimezone=Asia/Taipei";
	String userid = "cga101-03";
	String passwd = "cga101-03";

	private static final String INSERT_STMT = "INSERT INTO permission_mapping (emp_id,permission_id) VALUES (?,?) ";
	private static final String GET_ALL_STMT = "select emp_id,permission_id FROM permission_mapping order by emp_id ";
	private static final String GET_ONE_STMT = "SELECT emp_id,permission_id FROM permission_mapping where emp_id = ? ";
	private static final String DELETE = "DELETE FROM permission_mapping where emp_id = ? and permission_id = ? ;";
	private static final String UPDATE = "UPDATE permission_mapping set permission_name=?  where permission_id = ? ";

	@Override
	public void insert(AnnouncementImgVO announcementImgVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setInt(1, announcementImgVO.getAnnouncementId());
			pstmt.setBytes(2, announcementImgVO.getAnnouncementImg());

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

	public int update(AnnouncementImgVO newPermission) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			AnnouncementImgVO oldPermission = findByPrimaryKey(newPermission.getAnnouncementImgId());

			if (newPermission.getAnnouncementImg() != null) {
				pstmt.setBytes(1, newPermission.getAnnouncementImg());
			} else {
				pstmt.setBytes(1, oldPermission.getAnnouncementImg());

			}

			pstmt.setInt(2, newPermission.getAnnouncementImgId());

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

	public void delete(Integer announcementId,Integer announcementImgId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, announcementId);

			pstmt.setInt(2, announcementImgId);

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

	public AnnouncementImgVO findByPrimaryKey(Integer announcementImgId) {
		
		AnnouncementImgVO announcementImgVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, announcementImgId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				announcementImgVO = new AnnouncementImgVO();
								
								announcementImgVO.setAnnouncementImgId(announcementImgId);
								announcementImgVO.setAnnouncementId(rs.getInt("announcement_id"));

								announcementImgVO.setAnnouncementImg(rs.getBytes("announcement_img"));
								announcementImgVO.setAnnouncementUptime(rs.getTimestamp("announcement_uptime"));

							
								
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
		return announcementImgVO;
	}

	public List<AnnouncementImgVO> getAll() {
		List<AnnouncementImgVO> list = new ArrayList<AnnouncementImgVO>();
		AnnouncementImgVO announcementImgVO = null;

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

announcementImgVO = new AnnouncementImgVO();
				

announcementImgVO.setAnnouncementImgId(rs.getInt("announcementImg_id"));
announcementImgVO.setAnnouncementId(rs.getInt("announcement_id"));

announcementImgVO.setAnnouncementImg(rs.getBytes("announcement_img"));
announcementImgVO.setAnnouncementUptime(rs.getTimestamp("announcement_uptime"));
				list.add(announcementImgVO); // Store the row in the list
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

		AnnouncementImgJDBCDAO dao = new AnnouncementImgJDBCDAO();
//		AnnouncementImgVO AnnouncementImgVO1 = new AnnouncementImgVO();
//
//		AnnouncementImgVO1.setPermissionId(3);
//		AnnouncementImgVO1.setEmpId(1007);
//		dao.insert(AnnouncementImgVO1);
//		AnnouncementImgVO1.setHiredate(new Date(1991-1900,6,19));
////		AnnouncementImgVO1.setResigndate(4, AnnouncementImgVO.getResign_date());
//		AnnouncementImgVO1.setPhone("0912323234");
//		AnnouncementImgVO1.setExtension("043098753");
//		AnnouncementImgVO1.setEmpPassword("19940619");
//		AnnouncementImgVO1.setHobby("playtheball2");
//		AnnouncementImgVO1.setSkill("sleepuntilafternoon2");
//		AnnouncementImgVO1.setEmpProfile(buf);
//		AnnouncementImgVO1.setMail("oldma123n2@gmail.com");
//		AnnouncementImgVO1.setBirthday(new Date(1971-1900,3,12));
//		AnnouncementImgVO1.setEmpStatus((byte)1);
		
//		System.out.println("成功");

//		AnnouncementImgVO1.setEname("");
//		AnnouncementImgVO1.setJob("MANAGER");
//		AnnouncementImgVO1.setHiredate(java.sql.Date.valueOf("2005-01-01"));
//		AnnouncementImgVO1.setSal(new Double(50000));
//		AnnouncementImgVO1.setComm(new Double(500));
//		AnnouncementImgVO1.setDeptno(10);

		// private static final String UPDATE = "UPDATE emp set dep_id=?, emp_name=?,
		// hire_date=?, resign_date=?, phone=?, extension=?, emp_password=?, hobby=?,
		// skill=?, emp_profile=?, mail=?, birthday=?, emp_status=?, where emp_id = ?";

//		
//		AnnouncementImgVO AnnouncementImgVO2 = new AnnouncementImgVO();
//		AnnouncementImgVO2.setPermissionId(1);
////		
//		AnnouncementImgVO2.setPermissionName("田園風格");
////	
//		System.out.println("成功");
////		
//		dao.update(AnnouncementImgVO2);

//		
//		dao.delete(1007,3);

//		AnnouncementImgVO AnnouncementImgVO3 = dao.findByPrimaryKey(2);
//		System.out.print(AnnouncementImgVO3.getPermissionId());
//		System.out.print(AnnouncementImgVO3.getPermissionName() + ",");
//		System.out.print(AnnouncementImgVO3.getEmpPassword() + ",");
//		System.out.print(AnnouncementImgVO3.getHiredate() + ",");
//		System.out.print(AnnouncementImgVO3.getResigndate() + ",");
//		System.out.print(AnnouncementImgVO3.getPhone() + ",");
//		System.out.print(AnnouncementImgVO3.getEmpProfile() + "Test,");
//		System.out.print(AnnouncementImgVO3.getExtension() + ",");
//		System.out.println(AnnouncementImgVO3.getMail());
//		System.out.println("---------------------");
//	FileOutputStream fout=new FileOutputStream("/Users/lin/tomcat.gif");
//	fout.write(AnnouncementImgVO3.getEmpProfile());
//	fout.close();
//		Set<AnnouncementImgVO> set=dao.findByPrimaryKey(1001);
//	
//		for (AnnouncementImgVO aEmp : set) {
//			System.out.print(aEmp.getEmpId() + ",");
//			System.out.print(aEmp.getPermissionId() + ",");
//			
//		
//			System.out.println();
//		}
//		List<AnnouncementImgVO> list = dao.getAll();
//		for (AnnouncementImgVO aEmp : list) {
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