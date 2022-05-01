package com.permissionmapping.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.emp.model.EmpVO;

//import org.graalvm.compiler.core.common.alloc.Trace;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;
import java.sql.Date;

public class PermissionMappingJDBCDAO implements PermissionMappingDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://cga101-03@database-1.cqm5mb4z5ril.ap-northeast-1.rds.amazonaws.com:3306/CGA101-03?serverTimezone=Asia/Taipei";
	String userid = "cga101-03";
	String passwd = "cga101-03";

	private static final String INSERT_STMT = "INSERT INTO permission_mapping (emp_id,permission_id) VALUES (?,?) ";
	private static final String GET_ALL_STMT = "select emp_id,permission_id FROM permission_mapping order by emp_id ";
	private static final String GET_ONE_STMT = "SELECT emp_id,permission_id FROM permission_mapping where emp_id = ? ";
	private static final String DELETE = "DELETE FROM permission_mapping where emp_id = ? and permission_id = ? ;";
	//private static final String UPDATE = "UPDATE permission_mapping set permission_name=?  where permission_id = ? ";

	@Override
	public void insert(PermissionMappingVO permissionMappingVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setInt(1, permissionMappingVO.getEmpId());
			pstmt.setInt(2, permissionMappingVO.getPermissionId());

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

//	public int update(PermissionMappingVO newPermission) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//			PermissionMappingVO oldPermission = findByPrimaryKey(newPermission.getPermissionId());
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

	public void delete(Integer empId,Integer permissionId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, empId);

			pstmt.setInt(2, permissionId);

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

	public List<Integer> findByPrimaryKey(Integer empId) {
		List<Integer> list = new ArrayList<Integer>();
		PermissionMappingVO permissionMappingVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, empId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				

								list.add(rs.getInt("permission_id")); // Store the row in the list
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

	public List<PermissionMappingVO> getAllDAO() {
		List<PermissionMappingVO> list = new ArrayList<PermissionMappingVO>();
		PermissionMappingVO permissionMappingVO = null;

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

permissionMappingVO = new PermissionMappingVO();
				
				permissionMappingVO.setEmpId(rs.getInt("emp_id"));
				permissionMappingVO.setPermissionId(rs.getInt("permission_id"));

				list.add(permissionMappingVO); // Store the row in the list
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

		PermissionMappingJDBCDAO dao = new PermissionMappingJDBCDAO();
//		PermissionMappingVO PermissionMappingVO1 = new PermissionMappingVO();
//
//		PermissionMappingVO1.setPermissionId(3);
//		PermissionMappingVO1.setEmpId(1007);
//		dao.insert(PermissionMappingVO1);
//		PermissionMappingVO1.setHiredate(new Date(1991-1900,6,19));
////		PermissionMappingVO1.setResigndate(4, PermissionMappingVO.getResign_date());
//		PermissionMappingVO1.setPhone("0912323234");
//		PermissionMappingVO1.setExtension("043098753");
//		PermissionMappingVO1.setEmpPassword("19940619");
//		PermissionMappingVO1.setHobby("playtheball2");
//		PermissionMappingVO1.setSkill("sleepuntilafternoon2");
//		PermissionMappingVO1.setEmpProfile(buf);
//		PermissionMappingVO1.setMail("oldma123n2@gmail.com");
//		PermissionMappingVO1.setBirthday(new Date(1971-1900,3,12));
//		PermissionMappingVO1.setEmpStatus((byte)1);
		
//		System.out.println("成功");

//		PermissionMappingVO1.setEname("");
//		PermissionMappingVO1.setJob("MANAGER");
//		PermissionMappingVO1.setHiredate(java.sql.Date.valueOf("2005-01-01"));
//		PermissionMappingVO1.setSal(new Double(50000));
//		PermissionMappingVO1.setComm(new Double(500));
//		PermissionMappingVO1.setDeptno(10);

		// private static final String UPDATE = "UPDATE emp set dep_id=?, emp_name=?,
		// hire_date=?, resign_date=?, phone=?, extension=?, emp_password=?, hobby=?,
		// skill=?, emp_profile=?, mail=?, birthday=?, emp_status=?, where emp_id = ?";

//		
//		PermissionMappingVO PermissionMappingVO2 = new PermissionMappingVO();
//		PermissionMappingVO2.setPermissionId(1);
////		
//		PermissionMappingVO2.setPermissionName("田園風格");
////	
//		System.out.println("成功");
////		
//		dao.update(PermissionMappingVO2);

//		
//		dao.delete(1007,3);
//
		List<Integer> list = dao.findByPrimaryKey(1001);
		System.out.println(list);
	
		
//		System.out.print(PermissionMappingVO3.getPermissionId());
//		System.out.print(PermissionMappingVO3.getPermissionName() + ",");
//		System.out.print(PermissionMappingVO3.getEmpPassword() + ",");
//		System.out.print(PermissionMappingVO3.getHiredate() + ",");
//		System.out.print(PermissionMappingVO3.getResigndate() + ",");
//		System.out.print(PermissionMappingVO3.getPhone() + ",");
//		System.out.print(PermissionMappingVO3.getEmpProfile() + "Test,");
//		System.out.print(PermissionMappingVO3.getExtension() + ",");
//		System.out.println(PermissionMappingVO3.getMail());
//		System.out.println("---------------------");
//	FileOutputStream fout=new FileOutputStream("/Users/lin/tomcat.gif");
//	fout.write(PermissionMappingVO3.getEmpProfile());
//	fout.close();
//		Set<PermissionMappingVO> set=dao.findByPrimaryKey(1001);
//	
//		for (PermissionMappingVO aEmp : set) {
//			System.out.print(aEmp.getEmpId() + ",");
//			System.out.print(aEmp.getPermissionId() + ",");
//			
//		
//			System.out.println();
//		}
//		List<PermissionMappingVO> list = dao.getAllDAO();
//		for (PermissionMappingVO aEmp : list) {
//			System.out.print(aEmp.getEmpId() + ",");
//			System.out.print(aEmp.getPermissionId() + ",");
//		
//			System.out.println();
//		}
//		PermissionMappingVO pmVO=new PermissionMappingVO();
//		pmVO.setEmpId(1001);
//		EmpVO empVO=pmVO.getEmpVO();
//		System.out.println(empVO.getEmpName()+empVO.getMail());
		
		
		
		
		
		
		
	}
}