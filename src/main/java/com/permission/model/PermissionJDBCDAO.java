package com.permission.model;

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

public class PermissionJDBCDAO implements PermissionDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://cga101-03@database-1.cqm5mb4z5ril.ap-northeast-1.rds.amazonaws.com:3306/CGA101-03?serverTimezone=Asia/Taipei";
	String userid = "cga101-03";
	String passwd = "cga101-03";
	private static final String GET_ALL_PERMISSIONNAME = "select permission_name FROM permission ";
	private static final String INSERT_STMT = "INSERT INTO permission (permission_name) VALUES (?) ";
	private static final String GET_ALL_STMT = "select permission_id,permission_name FROM permission order by permission_id ";
	private static final String GET_ONE_STMT = "SELECT permission_id,permission_name FROM permission where permission_id = ? ";
	private static final String DELETE = "DELETE FROM permission where permission_id = ?";
	private static final String UPDATE = "UPDATE permission set permission_name=?  where permission_id = ? ";

	@Override
	public void insert(PermissionVO permissionVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, permissionVO.getPermissionName());

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

	public int update(PermissionVO newPermission) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			String permission=findByPrimaryKey(newPermission.getPermissionId());
			
			PermissionVO oldPermission = null;
			oldPermission.setPermissionId(newPermission.getPermissionId());
			oldPermission.setPermissionName(permission);
			if (newPermission.getPermissionName() != null) {
				pstmt.setString(1, newPermission.getPermissionName());
			} else {
				pstmt.setString(1, oldPermission.getPermissionName());

			}

			pstmt.setInt(2, newPermission.getPermissionId());

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

	public void delete(Integer permissionId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, permissionId);

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

	public String findByPrimaryKey(Integer permissionId) {

		String permissionName=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, permissionId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				// dep_id,emp_name,hire_date,phone,extension,hobby FROM emp where emp_id = ?";
				permissionName=(rs.getString("permission_name"));
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
		return permissionName;
	}

	public List<PermissionVO> getAllDAO() {
		List<PermissionVO> list = new ArrayList<PermissionVO>();
		PermissionVO permissionVO = null;

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

				permissionVO = new PermissionVO();
				permissionVO.setPermissionId(rs.getInt("permission_id"));
				permissionVO.setPermissionName(rs.getString("permission_name"));

				list.add(permissionVO); // Store the row in the list
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
	
	

	@Override
	public List<String> getAllPermissionNameDAO() {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
	

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// emp_id,dep_id,emp_name,hire_date,phone,extension,emp_password,mail,emp_status
			// FROM emp order by emp_id";

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_PERMISSIONNAME);
			rs = pstmt.executeQuery();

			while (rs.next()) {

			
				

				list.add(rs.getString("permission_name")); // Store the row in the list
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

		PermissionJDBCDAO dao = new PermissionJDBCDAO();
//		PermissionVO permissionVO1 = new PermissionVO();
	List<String> aList=dao.getAllPermissionNameDAO();
	Iterator<String> it=aList.iterator();
	while (it.hasNext()) {
		String aString=it.next();
		System.out.println(aString);
	}
	
	
	

//		permissionVO1.setPermissionId(10);
//		permissionVO1.setPermissionName("backper");
//		PermissionVO1.setHiredate(new Date(1991-1900,6,19));
////		PermissionVO1.setResigndate(4, PermissionVO.getResign_date());
//		PermissionVO1.setPhone("0912323234");
//		PermissionVO1.setExtension("043098753");
//		PermissionVO1.setEmpPassword("19940619");
//		PermissionVO1.setHobby("playtheball2");
//		PermissionVO1.setSkill("sleepuntilafternoon2");
//		PermissionVO1.setEmpProfile(buf);
//		PermissionVO1.setMail("oldma123n2@gmail.com");
//		PermissionVO1.setBirthday(new Date(1971-1900,3,12));
//		PermissionVO1.setEmpStatus((byte)1);
//		dao.insert(permissionVO1);
//		System.out.println("成功");

//		PermissionVO1.setEname("");
//		PermissionVO1.setJob("MANAGER");
//		PermissionVO1.setHiredate(java.sql.Date.valueOf("2005-01-01"));
//		PermissionVO1.setSal(new Double(50000));
//		PermissionVO1.setComm(new Double(500));
//		PermissionVO1.setDeptno(10);

		// private static final String UPDATE = "UPDATE emp set dep_id=?, emp_name=?,
		// hire_date=?, resign_date=?, phone=?, extension=?, emp_password=?, hobby=?,
		// skill=?, emp_profile=?, mail=?, birthday=?, emp_status=?, where emp_id = ?";

//		
//		PermissionVO permissionVO2 = new PermissionVO();
//		permissionVO2.setPermissionId(1);
////		
//		permissionVO2.setPermissionName("田園風格");
////	
//		System.out.println("成功");
////		
//		dao.update(permissionVO2);

//		
//		dao.delete(10);

//		PermissionVO permissionVO3 = dao.findByPrimaryKey(2);
//		System.out.print(permissionVO3.getPermissionId());
//		System.out.print(permissionVO3.getPermissionName() + ",");
//		System.out.print(PermissionVO3.getEmpPassword() + ",");
//		System.out.print(PermissionVO3.getHiredate() + ",");
//		System.out.print(PermissionVO3.getResigndate() + ",");
//		System.out.print(PermissionVO3.getPhone() + ",");
//		System.out.print(PermissionVO3.getEmpProfile() + "Test,");
//		System.out.print(PermissionVO3.getExtension() + ",");
//		System.out.println(PermissionVO3.getMail());
//		System.out.println("---------------------");
//	FileOutputStream fout=new FileOutputStream("/Users/lin/tomcat.gif");
//	fout.write(PermissionVO3.getEmpProfile());
//	fout.close();

		List<PermissionVO> list = dao.getAllDAO();
		for (PermissionVO aEmp : list) {
			System.out.print(aEmp.getPermissionId() + ",");
			System.out.print(aEmp.getPermissionName() + ",");
			
		
			System.out.println();
		}
	}


}