package com.announcement_mapping.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//import org.graalvm.compiler.core.common.alloc.Trace;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.sql.Date;

public class Announcement_mappingJDBCDAO implements Announcement_mappingDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://cga101-03@database-1.cqm5mb4z5ril.ap-northeast-1.rds.amazonaws.com:3306/CGA101-03?serverTimezone=Asia/Taipei";
	String userid = "cga101-03";
	String passwd = "cga101-03";

	private static final String INSERT_STMT = "call InsertRecord(?,?) ";
	private static final String GET_ALL_STMT = "select announcement_id,announcementImg_id,announcementImg FROM announcement_mapping order by announcement_id  ";
	private static final String GET_ONE_STMT = "SELECT *  FROM announcement_mapping where announcement_id = ? ";
	private static final String DELETE = "DELETE FROM announcement_mapping where announcement_id = ? and announcementImg_id = ? ;";
	//private static final String UPDATE = "UPDATE permission_mapping set announcementImg=?  where announcement_id = ? and announcementImg_id = ? ";
	private static final String UPDATE = "UPDATE announcement_mapping set ";
	
	@Override
	public void insert(Announcement_mappingVO announcement_mappingVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setInt(1, announcement_mappingVO.getAnnouncement_id());
		
			pstmt.setBytes(2, announcement_mappingVO.getAnnouncementImg());
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

	@Override
	public void insert(Announcement_mappingVO announcement_mappingVO,Connection con) {


		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setInt(1, announcement_mappingVO.getAnnouncement_id());
		
			pstmt.setBytes(2, announcement_mappingVO.getAnnouncementImg());
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
			
		}

	}

	public int update(Announcement_mappingVO announcement_mappingVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			StringBuilder sb=new StringBuilder();
			sb.append(UPDATE);

			int count=0;
			if (announcement_mappingVO.getAnnouncementImg() != null) {
				sb.append("announcementImg=? ");
			
			sb.append( "  where announcement_id = ? and announcementImg_id = ? ");
			
			
			pstmt = con.prepareStatement(sb.toString());
			
			
			if (announcement_mappingVO.getAnnouncementImg() != null) {
				count++;
			pstmt.setBytes(count, announcement_mappingVO.getAnnouncementImg());
			
			}
			
			count++;
			pstmt.setInt(count, announcement_mappingVO.getAnnouncement_id());
			count++;
			pstmt.setInt(count, announcement_mappingVO.getAnnouncementImg_id());
			

			pstmt.executeUpdate();
			}else {
				
				
			}

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

	public void delete(Integer announcement_id,Integer announcementImg_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, announcement_id);

			pstmt.setInt(2, announcementImg_id);

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
//
	public List<Integer> findByPrimaryKey(Integer announcement_id) {
		List<Integer> list= new ArrayList<Integer>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, announcement_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				

								
								list.add(rs.getInt("announcementImg_id"));

							
								
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
		return list;
	}

	public List<Announcement_mappingVO> getAll() {
		List<Announcement_mappingVO> list = new ArrayList<Announcement_mappingVO>();
		Announcement_mappingVO Announcement_mappingVO = null;

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

Announcement_mappingVO = new Announcement_mappingVO();
				

Announcement_mappingVO.setAnnouncementImg_id(rs.getInt("announcementImg_id"));
Announcement_mappingVO.setAnnouncement_id(rs.getInt("announcement_id"));

Announcement_mappingVO.setAnnouncementImg(rs.getBytes("announcementImg"));

				list.add(Announcement_mappingVO); // Store the row in the list
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

		Announcement_mappingJDBCDAO dao = new Announcement_mappingJDBCDAO();
		Announcement_mappingVO announcement_mappingVO1 = new Announcement_mappingVO();

		announcement_mappingVO1.setAnnouncement_id(2010);
	//	/Users/lin/Downloads/FRjF7siagAA9CQh.jpeg
		FileInputStream fileInputStream=new FileInputStream("/Users/lin/chisato/96419655_p19.png");
		byte[] a=new byte[fileInputStream.available()];
		announcement_mappingVO1.setAnnouncementImg_id(1);
	
		fileInputStream.read(a);
		announcement_mappingVO1.setAnnouncementImg(a);
		dao.insert(announcement_mappingVO1);
	//	dao.update(announcement_mappingVO1);
		
		
	
	
		
		
		
//		Announcement_mappingVO1.setEmpId(1007);
//		dao.insert(Announcement_mappingVO1);
//		Announcement_mappingVO1.setHiredate(new Date(1991-1900,6,19));
////		Announcement_mappingVO1.setResigndate(4, Announcement_mappingVO.getResign_date());
//		Announcement_mappingVO1.setPhone("0912323234");
//		Announcement_mappingVO1.setExtension("043098753");
//		Announcement_mappingVO1.setEmpPassword("19940619");
//		Announcement_mappingVO1.setHobby("playtheball2");
//		Announcement_mappingVO1.setSkill("sleepuntilafternoon2");
//		Announcement_mappingVO1.setEmpProfile(buf);
//		Announcement_mappingVO1.setMail("oldma123n2@gmail.com");
//		Announcement_mappingVO1.setBirthday(new Date(1971-1900,3,12));
//		Announcement_mappingVO1.setEmpStatus((byte)1);
		
//		System.out.println("成功");

//		Announcement_mappingVO1.setEname("");
//		Announcement_mappingVO1.setJob("MANAGER");
//		Announcement_mappingVO1.setHiredate(java.sql.Date.valueOf("2005-01-01"));
//		Announcement_mappingVO1.setSal(new Double(50000));
//		Announcement_mappingVO1.setComm(new Double(500));
//		Announcement_mappingVO1.setDeptno(10);

		// private static final String UPDATE = "UPDATE emp set dep_id=?, emp_name=?,
		// hire_date=?, resign_date=?, phone=?, extension=?, emp_password=?, hobby=?,
		// skill=?, emp_profile=?, mail=?, birthday=?, emp_status=?, where emp_id = ?";

//		
//		Announcement_mappingVO Announcement_mappingVO2 = new Announcement_mappingVO();
//		Announcement_mappingVO2.setPermissionId(1);
////		
//		Announcement_mappingVO2.setPermissionName("田園風格");
////	
//		System.out.println("成功");
////		
//		dao.update(Announcement_mappingVO2);

//		
//		dao.delete(1007,3);

//		Announcement_mappingVO Announcement_mappingVO3 = dao.findByPrimaryKey(2);
//		System.out.print(Announcement_mappingVO3.getPermissionId());
//		System.out.print(Announcement_mappingVO3.getPermissionName() + ",");
//		System.out.print(Announcement_mappingVO3.getEmpPassword() + ",");
//		System.out.print(Announcement_mappingVO3.getHiredate() + ",");
//		System.out.print(Announcement_mappingVO3.getResigndate() + ",");
//		System.out.print(Announcement_mappingVO3.getPhone() + ",");
//		System.out.print(Announcement_mappingVO3.getEmpProfile() + "Test,");
//		System.out.print(Announcement_mappingVO3.getExtension() + ",");
//		System.out.println(Announcement_mappingVO3.getMail());
//		System.out.println("---------------------");
//	FileOutputStream fout=new FileOutputStream("/Users/lin/tomcat.gif");
//	fout.write(Announcement_mappingVO3.getEmpProfile());
//	fout.close();
//		Set<Announcement_mappingVO> set=dao.findByPrimaryKey(1001);
//	
//		for (Announcement_mappingVO aEmp : set) {
//			System.out.print(aEmp.getEmpId() + ",");
//			System.out.print(aEmp.getPermissionId() + ",");
//			
//		
//			System.out.println();
//		}
//		List<Announcement_mappingVO> list = dao.getAll();
//		for (Announcement_mappingVO aEmp : list) {
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