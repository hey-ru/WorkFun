package com.dep.model;

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

public class DepJDBCDAO implements DepDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://cga101-03@database-1.cqm5mb4z5ril.ap-northeast-1.rds.amazonaws.com:3306/CGA101-03?serverTimezone=Asia/Taipei";
	String userid = "cga101-03";
	String passwd = "cga101-03";

	private static final String INSERT_STMT = "INSERT INTO dep (dep_name) VALUES (?) ";
	private static final String GET_ALL_STMT = "select dep_id,dep_name FROM dep order by dep_id ";
	private static final String GET_ONE_STMT = "SELECT dep_id,dep_name FROM dep where dep_id = ? ";
	private static final String DELETE = "DELETE FROM dep where dep_id = ?";
	private static final String UPDATE = "UPDATE dep set dep_name=?  where dep_id = ? ";

	@Override
	public void insert(DepVO depVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, depVO.getDepName());

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

	public int update(DepVO newDep) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
	

			if (newDep.getDepName() != null) {
				pstmt.setString(1, newDep.getDepName());
			} 

			pstmt.setInt(2, newDep.getDepId());

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

	public void delete(Integer depId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, depId);

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

	public DepVO findByPrimaryKey(Integer depId) {

		DepVO depVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, depId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// DepVO Domain objects
				depVO = new DepVO();
				// dep_id,emp_name,hire_date,phone,extension,hobby FROM emp where emp_id = ?";
				depVO.setDepId(rs.getInt("dep_id"));
				depVO.setDepName(rs.getString("dep_name"));

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
		return depVO;
	}

	public List<DepVO> getAll() {
		List<DepVO> list = new ArrayList<DepVO>();
		DepVO depVO = null;

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

				depVO = new DepVO();
				depVO.setDepId(rs.getInt("dep_id"));
				depVO.setDepName(rs.getString("dep_name"));

				list.add(depVO); // Store the row in the list
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

		DepJDBCDAO dao = new DepJDBCDAO();
		DepVO depVO1 = new DepVO();

//		DepVO1.setPermissionId(10);
		depVO1.setDepName("哭哭");
//		DepVO1.setHiredate(new Date(1991-1900,6,19));

//		dao.insert(depVO1);
//		System.out.println("成功");


//		
		DepVO depVO2 = new DepVO();
		depVO2.setDepId(10);
		depVO2.setDepName("資訊");
//		depVO2.setDepName("資訊");

	System.out.println("成功");
	
		dao.update(depVO2);

		
		
//		
//		dao.delete(17);

		
		
		
		
		
		DepVO DepVO3 = dao.findByPrimaryKey(11);
		System.out.print(DepVO3.getDepId()+",");
		System.out.print(DepVO3.getDepName() + ",");

//		System.out.println("---------------------");
//	FileOutputStream fout=new FileOutputStream("/Users/lin/tomcat.gif");
//	fout.write(DepVO3.getEmpProfile());
//	fout.close();

		
		
		
		
		
//		List<DepVO> list = dao.getAll();
//		for (DepVO aEmp : set) {
//			System.out.print(aEmp.getDepId() + ",");
//			System.out.print(aEmp.getDepName() + ",");
//			
//			System.out.println();
//		}
	}
}