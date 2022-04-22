package com.emp.model;

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

public class EmpJDBCDAO implements EmpDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://cga101-03@database-1.cqm5mb4z5ril.ap-northeast-1.rds.amazonaws.com:3306/CGA101-03?serverTimezone=Asia/Taipei";
	String userid = "cga101-03";
	String passwd = "cga101-03";

	private static final String INSERT_STMT = "INSERT INTO emp (dep_id,emp_name,hire_date,resign_date,phone,extension,emp_password,hobby,skill,emp_profile,mail,birthday,emp_status) VALUES (?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?,?)";
	private static final String GET_ALL_STMT = "select emp_id,dep_id,emp_name,hire_date,phone,extension,emp_password,mail,emp_status FROM emp order by emp_id ";
	private static final String GET_ONE_STMT = "SELECT dep_id,emp_name,hire_date,resign_date,phone,extension,emp_password,hobby,skill,emp_profile,mail,birthday,emp_status FROM emp where emp_id = ?";
	private static final String DELETE = "DELETE FROM emp where emp_id = ?";
//	private static final String UPDATE = "UPDATE emp set dep_id=?, emp_name=?, hire_date=?, resign_date=?, phone=?, extension=?, emp_password=?, hobby=?, skill=?, emp_profile=?, mail=?, birthday=?, emp_status=? where emp_id = ? ";
	private static final String UPDATE = "UPDATE emp set ";
	@Override
	public void insert(EmpVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, empVO.getDepId());
			pstmt.setString(2, empVO.getEmpName());
			pstmt.setDate(3, empVO.getHiredate());
			pstmt.setDate(4, empVO.getResigndate());
			pstmt.setString(5, empVO.getPhone());
			pstmt.setString(6, empVO.getExtension());
			pstmt.setString(7, empVO.getEmpPassword());
			pstmt.setString(8, empVO.getHobby());
			pstmt.setString(9, empVO.getSkill());
			pstmt.setBytes(10, empVO.getEmpProfile());
			pstmt.setString(11, empVO.getMail());
			pstmt.setDate(12, empVO.getBirthday());
			pstmt.setByte(13, empVO.getEmpStatus());
//			pstmt.setInt(14, empVO.getEmpId());

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

	public int update(EmpVO newemp) {

		Connection con = null;
		PreparedStatement pstmt = null;
int count=0;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
			EmpVO oldemp = findByPrimaryKey(newemp.getEmpId());
			System.out.println(oldemp.getEmpId());
		StringBuilder sb=new StringBuilder();
			sb.append(UPDATE);
			if (newemp.getDepId() != null) {
				sb.append("dep_id=?, ");
			}
			if (newemp.getEmpName() != null) {
				sb.append("emp_name=?, ");
			}
			if (newemp.getHiredate() != null) {
				sb.append("hire_date=?, ");
			}
			if (newemp.getResigndate() != null) {
				sb.append("resign_date=?, ");
			}
			if (newemp.getPhone() != null) {
				sb.append("phone=?, ");
			}
			if (newemp.getExtension() != null) {
				sb.append("extension=?, ");
			}
			if (newemp.getEmpPassword() != null) {
				sb.append("emp_password=?, ");
			}
			if (newemp.getHobby() != null) {
				sb.append("hobby=?, ");
			}
			if (newemp.getSkill() != null) {
				sb.append("skill=?, ");
			}
			if (newemp.getEmpProfile() != null) {
				sb.append("emp_profile=?, ");
			}
			if (newemp.getMail() != null) {
				sb.append("mail=?, ");
			}
			if (newemp.getBirthday() != null) {
				sb.append("birthday=?, ");
			}
			if (newemp.getEmpStatus() != null) {
				sb.append("emp_status=?, ");
			}
	
				sb.append("emp_id=? ");
			
			sb.append("where emp_id = ? ");
			
			
			pstmt = con.prepareStatement(sb.toString());
			System.out.println(sb);
			
			if (newemp.getDepId() != null) {
				count++;
				pstmt.setInt(count, newemp.getDepId());
			} 
			
			if (newemp.getEmpName() != null) {
				count++;
				pstmt.setString(count, newemp.getEmpName());
			} 
			
			if (newemp.getHiredate() != null) {
				count++;
				pstmt.setDate(count, newemp.getHiredate());
			} 
			
			if (newemp.getResigndate() != null) {
				count++;
				pstmt.setDate(count, newemp.getResigndate());
			} 
			
			if (newemp.getPhone() != null) {
				count++;
				pstmt.setString(count, newemp.getPhone());
			}
		
			if (newemp.getExtension() != null) {
				count++;
				pstmt.setString(count, newemp.getExtension());
			} 
			
			if (newemp.getEmpPassword() != null) {
				count++;
				pstmt.setString(count, newemp.getEmpPassword());
			} 
			
			if (newemp.getHobby() != null) {
				count++;
				pstmt.setString(count, newemp.getHobby());
			} 
			if (newemp.getSkill() != null) {
				count++;
				pstmt.setString(count, newemp.getSkill());
			} 
			
			if (newemp.getEmpProfile() != null) {
				count++;
				pstmt.setBytes(count, newemp.getEmpProfile());
			} 
			
			if (newemp.getMail() != null) {
				count++;
				pstmt.setString(count, newemp.getMail());
			} 
			
			if (newemp.getBirthday() != null) {
				count++;
				pstmt.setDate(count, newemp.getBirthday());
			} 
			if (newemp.getEmpStatus() != null) {
				count++;
				pstmt.setInt(count, newemp.getEmpStatus());
			} 
			
		
			
			count++;
			pstmt.setInt(count, newemp.getEmpId()); 
			count++;
			pstmt.setInt(count, newemp.getEmpId()); 
			

			pstmt.executeUpdate();
			System.out.println(count);

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

	public void delete(Integer EmpId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, EmpId);

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

	public EmpVO findByPrimaryKey(Integer empno) {

		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, empno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo Domain objects
				empVO = new EmpVO();
				// dep_id,emp_name,hire_date,phone,extension,hobby FROM emp where emp_id = ?";
				empVO.setEmpId(empno);
				empVO.setDepId(rs.getInt("dep_id"));
				empVO.setEmpName(rs.getString("emp_name"));
				empVO.setHiredate(rs.getDate("hire_date"));
				empVO.setResigndate(rs.getDate("resign_date"));
				empVO.setPhone(rs.getString("phone"));
				empVO.setExtension(rs.getString("extension"));
				empVO.setHobby(rs.getString("hobby"));
				empVO.setEmpPassword(rs.getString("emp_password"));
				empVO.setSkill(rs.getString("skill"));
				empVO.setEmpProfile(rs.getBytes("emp_profile"));
				empVO.setMail(rs.getString("mail"));
				empVO.setBirthday(rs.getDate("birthday"));
				empVO.setEmpStatus(rs.getByte("emp_status"));

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
		return empVO;
	}

	public List<EmpVO> getAll() {
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;

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

				empVO = new EmpVO();
				empVO.setEmpId(rs.getInt("emp_id"));
				empVO.setDepId(rs.getInt("dep_id"));
				empVO.setEmpName(rs.getString("emp_name"));
				empVO.setHiredate(rs.getDate("hire_date"));

				empVO.setPhone(rs.getString("phone"));
				empVO.setExtension(rs.getString("extension"));
				empVO.setEmpPassword(rs.getString("emp_password"));
//				empVO.setHobby(rs.getString("hobby"));
//				empVO.setSkill(rs.getString("skill"));
				empVO.setMail(rs.getString("mail"));
//				empVO.setBirthday(rs.getDate("birthday"));
				empVO.setEmpStatus(rs.getByte("emp_status"));
//				empVO.setEmpProfile(rs.getBytes("emp_profile"));
				list.add(empVO); // Store the row in the list
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

		EmpJDBCDAO dao = new EmpJDBCDAO();
		EmpVO empVO1 = new EmpVO();
		FileInputStream in = new FileInputStream("/Users/lin/Downloads/tomcat.gif");
		byte[] buf = new byte[in.available()];
		in.read(buf);
		in.close();
//		empVO1.setDepId(10);
//		empVO1.setEmpName("back");
//		empVO1.setHiredate(new Date(1991-1900,6,19));
////		empVO1.setResigndate(4, empVO.getResign_date());
//		empVO1.setPhone("0912323234");
//		empVO1.setExtension("043098753");
//		empVO1.setEmpPassword("19940619");
//		empVO1.setHobby("playtheball2");
//		empVO1.setSkill("sleepuntilafternoon2");
//		empVO1.setEmpProfile(buf);
//		empVO1.setMail("oldma123n2@gmail.com");
//		empVO1.setBirthday(new Date(1971-1900,3,12));
//		empVO1.setEmpStatus((byte)1);
//		dao.insert(empVO1);
//		System.out.println("成功");

//		empVO1.setEname("");
//		empVO1.setJob("MANAGER");
//		empVO1.setHiredate(java.sql.Date.valueOf("2005-01-01"));
//		empVO1.setSal(new Double(50000));
//		empVO1.setComm(new Double(500));
//		empVO1.setDeptno(10);

		// private static final String UPDATE = "UPDATE emp set dep_id=?, emp_name=?,
		// hire_date=?, resign_date=?, phone=?, extension=?, emp_password=?, hobby=?,
		// skill=?, emp_profile=?, mail=?, birthday=?, emp_status=?, where emp_id = ?";

//		
		EmpVO empVO2 = new EmpVO();
		empVO2.setEmpId(1001);
//		
		empVO2.setDepId(10);
		empVO2.setEmpName("張尾杭");
//		empVO2.setHiredate(new Date(1994-1900,6,19));
//		empVO2.setResigndate(4, emp.getResign_date());
		empVO2.setPhone("0987612345");
//		empVO2.setExtension("0424991212");
//		empVO2.setEmpPassword("19940619");
//		empVO2.setHobby("playball");
//		empVO2.setSkill("sleepuntilafternoon");
//		empVO2.setEmpProfile(buf);
		empVO2.setMail("asiagodtone87@gmail.com");
//		empVO2.setBirthday(new Date(1972-1900,3,12));
//		empVO2.setEmpStatus((byte)1);
		System.out.println("成功");

		dao.update(empVO2);

//		
//		dao.delete(new Integer (1000));

//		EmpVO empVO3 = dao.findByPrimaryKey(1001);
//		System.out.print(empVO3.getEmpName() + ",");
//		System.out.print(empVO3.getDepId() + ",");
//		System.out.print(empVO3.getEmpPassword() + ",");
//		System.out.print(empVO3.getHiredate() + ",");
//		System.out.print(empVO3.getResigndate() + ",");
//		System.out.print(empVO3.getPhone() + ",");
//		System.out.print(empVO3.getEmpProfile() + "Test,");
//		System.out.print(empVO3.getExtension() + ",");
//		System.out.println(empVO3.getMail());
//		System.out.println("---------------------");
//	FileOutputStream fout=new FileOutputStream("/Users/lin/tomcat.gif");
//	fout.write(empVO3.getEmpProfile());
//	fout.close();

//		List<EmpVO> list = dao.getAll();
//		for (EmpVO aEmp : list) {
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