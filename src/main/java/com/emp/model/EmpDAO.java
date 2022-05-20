package com.emp.model;

import java.util.*;

import static com.util.ConnectionPool.*;

//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;

//import org.graalvm.compiler.core.common.alloc.Trace;

import java.io.FileInputStream;

import java.io.IOException;

import java.sql.*;



public class EmpDAO implements EmpDAO_interface { 
	private static final String SELECT_PHONEWITHID = "select phone from emp where phone = ? and emp_id != ? limit 1 ;  ";
	
	

	private static final String SELECT_PHONE = "select phone from emp where phone = ? limit 1 ;  ";
	
	private static final String LIKE_EXTENSION = "select emp_name ,extension,dep_id,mail  from emp where  extension =  ?  ;  ";
	private static final String SELECT_EXTENSIONWITHID = "select extension from emp where extension = ? and emp_id != ? limit 1 ;  ";
private static final String SELECT_EXTENSION = "select extension from emp where extension = ? limit 1 ;  ";
	private static final String SELECT_MAIL = "select mail from emp where mail = ? limit 1 ;  ";
	private static final String SELECT_MAILWITHID = "select mail from emp where mail = ? and emp_id != ? limit 1 ;  ";
	private static final String LOGIN_STMT = "select * from emp where mail = ? and emp_password = ? ";
	private static final String FORGOT_STMT = "select * from emp where mail = ? and birthday = ? ";
	private static final String INSERT_STMT = "INSERT INTO emp (dep_id,emp_name,hire_date,resign_date,phone,extension,emp_password,hobby,skill,emp_profile,mail,birthday) VALUES (?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT emp_id,dep_id,emp_name,hire_date,resign_date,phone,extension,emp_password,hobby,skill,emp_profile,mail,birthday,emp_status FROM emp order by emp_id ";
	private static final String GET_ONE_STMT = "SELECT dep_id,emp_name,hire_date,resign_date,phone,extension,emp_password,hobby,skill,emp_profile,mail,birthday,emp_status FROM emp where emp_id = ?";
	private static final String DELETE = "DELETE FROM emp where emp_id = ?";
//	private static final String UPDATE = "UPDATE emp set dep_id=?, emp_name=?, hire_date=?, resign_date=?, phone=?, extension=?, emp_password=?, hobby=?, skill=?, emp_profile=?, mail=?, birthday=?, emp_status=? where emp_id = ? ";
	private static final String UPDATE = "UPDATE emp set ";
	
	 public List<EmpVO> selectByExtension(String extension){
		 List<EmpVO> list = new ArrayList<EmpVO>();
			EmpVO empVO = null;

		

			Connection con = null;
			PreparedStatement pstmt = null;

			
			
			ResultSet rs = null;

			try {
				// emp_id,dep_id,emp_name,hire_date,phone,extension,emp_password,mail,emp_status
				// FROM emp order by emp_id";

				con = getConectPool().getConnection();
				  String LIKE_EXTENSION1 = "select emp_name,phone ,extension ,dep_id,mail from emp where  extension like  '%"+
						  extension+
					  		"%'";
				pstmt = con.prepareStatement(LIKE_EXTENSION1);
			
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					


					empVO = new EmpVO();
					
					empVO.setEmpName(rs.getString("emp_name"));
					empVO.setPhone(rs.getString("phone"));
					empVO.setExtension(rs.getString("extension"));
					empVO.setDepId(rs.getInt("dep_id"));
					empVO.setMail(rs.getString("mail"));
					list.add(empVO); // Store the row in the list
				}

				// Handle any driver errors

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
			
			}
	return list;
			
		 
	 }
	 
	 
	 
	 public List<EmpVO> selectByEmpName(String empName){
		 List<EmpVO> list = new ArrayList<EmpVO>();
			EmpVO empVO = null;

			  String LIKE_EMP_NAME = "select emp_name,phone ,extension ,dep_id,mail from emp where  emp_name like  '%"+
			  		 empName+
			  		"%'";

			Connection con = null;
			PreparedStatement pstmt = null;

			
			
			ResultSet rs = null;

			try {
				// emp_id,dep_id,emp_name,hire_date,phone,extension,emp_password,mail,emp_status
				// FROM emp order by emp_id";

				con = getConectPool().getConnection();
				pstmt = con.prepareStatement(LIKE_EMP_NAME);
				
				rs = pstmt.executeQuery();
			
				while (rs.next()) {
					


					empVO = new EmpVO();
					
					empVO.setEmpName(rs.getString("emp_name"));
					empVO.setPhone(rs.getString("phone"));
					empVO.setExtension(rs.getString("extension"));
					empVO.setDepId(rs.getInt("dep_id"));
					empVO.setMail(rs.getString("mail"));
					list.add(empVO); // Store the row in the list
				}

				// Handle any driver errors

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
			
			}
	return list;
			
		 
	 }
	
	
		
		
	
	
	
	
	
	
	@Override
	public int insert(EmpVO empVO) {


		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = getConectPool().getConnection();
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
//			pstmt.setByte(13, empVO.getEmpStatus());
//			pstmt.setInt(14, empVO.getEmpId());

			pstmt.executeUpdate();
//			ResultSet rs = pstmt.getGeneratedKeys();
//			if (rs.next()) {
//				employee_id = rs.getInt(1);
//				System.out.println(rowCount + " row inserted; order ID: " + employee_id);
//			}

			// Handle any driver errors

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
return 1;
	}

	public int insert(EmpVO empVO,Connection con) {


	
		PreparedStatement pstmt = null;

		try {

			
	
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
//			pstmt.setByte(13, empVO.getEmpStatus());
//			pstmt.setInt(14, empVO.getEmpId());

			pstmt.executeUpdate();
//			ResultSet rs = pstmt.getGeneratedKeys();
//			if (rs.next()) {
//				employee_id = rs.getInt(1);
//				System.out.println(rowCount + " row inserted; order ID: " + employee_id);
//			}

			// Handle any driver errors

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} 
		finally {
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
return 1;
	}
	public int update(EmpVO newemp) {

		Connection con = null;
		PreparedStatement pstmt = null;

int count=0;
		try {

			con = getConectPool().getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			
//			pstmt = con.prepareStatement(UPDATE);
			EmpVO oldemp = findByPrimaryKey(newemp.getEmpId());
	
		StringBuilder sb=new StringBuilder();
			sb.append(UPDATE);
			if (newemp.getDepId() != null ) {
				sb.append("dep_id=?, ");
			}
			if (newemp.getEmpName() != null) {
				sb.append("emp_name=?, ");
			}
			if (newemp.getHiredate() != null) {
				sb.append("hire_date=?, ");
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
			if (newemp.getEmpProfile() != null && newemp.getEmpProfile().length!=0) {
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
			if(newemp.getResigndate()!=null)
			{
				sb.append("resign_date=?, ");
			}	
			else {
				sb.append("resign_date=NULL, ");
			}
	
				sb.append("emp_id=? ");
			
			sb.append("where emp_id = ? ");
			
			
			pstmt = con.prepareStatement(sb.toString());
//			System.out.println(sb);
			
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
			
			if (newemp.getEmpProfile() != null && newemp.getEmpProfile().length!=0) {
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
			if (newemp.getResigndate() != null) {
				count++;
				pstmt.setDate(count, newemp.getResigndate());
			} 
			else {

			}
		
			
			count++;
			pstmt.setInt(count, newemp.getEmpId()); 
			count++;
			pstmt.setInt(count, newemp.getEmpId()); 
			

			pstmt.executeUpdate();
			System.out.println(count);

			// Handle any driver errors

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} 
		finally {
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
return 1;
	}
	public int update(EmpVO newemp,Connection con) {

	
		PreparedStatement pstmt = null;

int count=0;
		try {

		
			pstmt = con.prepareStatement(UPDATE);
			
			
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
//			System.out.println(sb);
			
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
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} 
		finally {
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
return 1;
	}
	
	
	
	
	
	
	
	
	public Integer selectMail(String mail,Integer empId) {
		Integer col;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConectPool().getConnection();
			pstmt = con.prepareStatement(SELECT_MAILWITHID, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

		
			

			pstmt.setString(1, mail);
			pstmt.setInt(2, empId);

			rs = pstmt.executeQuery();
			
			
			rs.last();
		
			col = rs.getRow();
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
return col;
	}
	public Integer selectMail(String mail) {
		Integer col;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConectPool().getConnection();
			pstmt = con.prepareStatement(SELECT_MAIL, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

		
			

			pstmt.setString(1, mail);

			rs = pstmt.executeQuery();
			
			
			rs.last();
		
			col = rs.getRow();
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
return col;
	}
	
	
	
	
	
	
	
	
	
	public int delete(Integer EmpId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConectPool().getConnection();
			pstmt = con.prepareStatement(DELETE);
			

			pstmt.setInt(1, EmpId);

			pstmt.executeUpdate();

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
return 1;
	}
	public int delete(Integer EmpId,Connection con) {

	
		PreparedStatement pstmt = null;

		try {
			
			pstmt = con.prepareStatement(DELETE);
			

			pstmt.setInt(1, EmpId);

			pstmt.executeUpdate();

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
		
		}
return 1;
	}
	
	public Integer selectExtension(String extension) {

		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
Integer col = null;
		try {
			
			con = getConectPool().getConnection();
			pstmt = con.prepareStatement(SELECT_EXTENSION, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			

		

			pstmt.setString(1, extension);

			rs = pstmt.executeQuery();
			
			
			rs.last();
		
			col = rs.getRow();

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
return col;
	}
	
public Integer selectPhone(String phone) {

		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
Integer col = null;
		try {
			
			con = getConectPool().getConnection();
			pstmt = con.prepareStatement(SELECT_PHONE, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			

		

			pstmt.setString(1, phone);

			rs = pstmt.executeQuery();
			
			
			rs.last();
		
			col = rs.getRow();

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
return col;
	}

public Integer selectPhone(String phone,Integer empId) {

	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
Integer col = null;
	try {
		
		con = getConectPool().getConnection();
		pstmt = con.prepareStatement(SELECT_PHONEWITHID, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		

		pstmt.setString(1, phone);

		pstmt.setInt(2, empId);

		rs = pstmt.executeQuery();
		
		
		rs.last();
	
		col = rs.getRow();

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
return col;
}


	public Integer selectExtension(String extension,Integer empId) {

		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
Integer col = null;
		try {
			
			con = getConectPool().getConnection();
			pstmt = con.prepareStatement(SELECT_EXTENSIONWITHID, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			

			pstmt.setString(1, extension);

			pstmt.setInt(2, empId);

			rs = pstmt.executeQuery();
			
			
			rs.last();
		
			col = rs.getRow();

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
return col;
	}
	
	
	public String findEmpNameByExtension(String extension) {
List<EmpVO> list=new ArrayList<EmpVO>();
		EmpVO empVO=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
String empName = null;
		try {
			
			con = getConectPool().getConnection();
			pstmt = con.prepareStatement(LIKE_EXTENSION);
			

			pstmt.setString(1, extension);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo Domain objects
				empVO = new EmpVO();
				// dep_id,emp_name,hire_date,phone,extension,hobby FROM emp where emp_id = ?";
			empVO.setEmpName(rs.getString("emp_name"));
			empVO.setExtension(rs.getString("extension"));
			

			}

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
return empName;
	}
	
	
	
	
	

	public EmpVO findByPrimaryKey(Integer empno) {

		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = getConectPool().getConnection();
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
return empVO;
	}
	public EmpVO findByPrimaryKey(Integer empno,Connection con) {

		EmpVO empVO = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
		
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
		
		}
return empVO;
	}
	public EmpVO selectForLogin(String mail, String password,Connection con) {
		
		PreparedStatement pstmt = null;
		EmpVO empVO = null;
		ResultSet rs = null;
		try {
			
			pstmt = con.prepareStatement(LOGIN_STMT);
		
			

			pstmt.setString(1, mail);
			pstmt.setString(2, password);
		
//			pstmt.setInt(14, empVO.getEmpId());


			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo Domain objects
				empVO = new EmpVO();
				// dep_id,emp_name,hire_date,phone,extension,hobby FROM emp where emp_id = ?";
				empVO.setEmpId(rs.getInt("emp_id"));
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
		
		}
return empVO;
	}
	 public EmpVO findbymailandbirthday(String mail,java.sql.Date birthday){
		Connection con = null;
		PreparedStatement pstmt = null;
		EmpVO empVO = null;
		ResultSet rs = null;
		try {
			con = getConectPool().getConnection();
			pstmt = con.prepareStatement(FORGOT_STMT);
		
			

			pstmt.setString(1, mail);
			pstmt.setDate(2, birthday);
		
//			pstmt.setInt(14, empVO.getEmpId());


			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo Domain objects
				empVO = new EmpVO();
				// dep_id,emp_name,hire_date,phone,extension,hobby FROM emp where emp_id = ?";
				empVO.setEmpId(rs.getInt("emp_id"));
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

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} 
		finally {
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
return empVO;
	}
	public EmpVO selectForLogin(String mail, String password) {
	Connection con=null;
		PreparedStatement pstmt = null;
		EmpVO empVO = null;
		ResultSet rs = null;
		try {
			con = getConectPool().getConnection();
			pstmt = con.prepareStatement(LOGIN_STMT);
		
			

			pstmt.setString(1, mail);
			pstmt.setString(2, password);
		
//			pstmt.setInt(14, empVO.getEmpId());


			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo Domain objects
				empVO = new EmpVO();
				// dep_id,emp_name,hire_date,phone,extension,hobby FROM emp where emp_id = ?";
				empVO.setEmpId(rs.getInt("emp_id"));
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
return empVO;
	}
	 public EmpVO findbymailandbirthday(String mail,java.sql.Date birthday,Connection con){
		
			PreparedStatement pstmt = null;
			EmpVO empVO = null;
			ResultSet rs = null;
			try {
				
				pstmt = con.prepareStatement(FORGOT_STMT);
			
				

				pstmt.setString(1, mail);
				pstmt.setDate(2, birthday);
			
//				pstmt.setInt(14, empVO.getEmpId());


				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo Domain objects
					empVO = new EmpVO();
					// dep_id,emp_name,hire_date,phone,extension,hobby FROM emp where emp_id = ?";
					empVO.setEmpId(rs.getInt("emp_id"));
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
			
			}
	return empVO;
		}

	public List<EmpVO> getAllDAO() {
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// emp_id,dep_id,emp_name,hire_date,phone,extension,emp_password,mail,emp_status
			// FROM emp order by emp_id";

			con = getConectPool().getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
		
			rs = pstmt.executeQuery();

			while (rs.next()) {


				empVO = new EmpVO();
				empVO.setEmpId(rs.getInt("emp_id"));
				empVO.setDepId(rs.getInt("dep_id"));
				empVO.setEmpName(rs.getString("emp_name"));
				empVO.setHiredate(rs.getDate("hire_date"));
//				empVO.setResigndate(rs.getDate("resign_date"));
				empVO.setResigndate(rs.getDate("resign_date"));				
				empVO.setPhone(rs.getString("phone"));
				empVO.setExtension(rs.getString("extension"));
				empVO.setEmpPassword(rs.getString("emp_password"));
				empVO.setHobby(rs.getString("hobby"));
				empVO.setSkill(rs.getString("skill"));
				empVO.setMail(rs.getString("mail"));
				empVO.setBirthday(rs.getDate("birthday"));
				empVO.setEmpStatus(rs.getByte("emp_status"));
				empVO.setEmpProfile(rs.getBytes("emp_profile"));
				list.add(empVO); // Store the row in the list
			}

			// Handle any driver errors

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
return list;
	}

	public List<EmpVO> getAllDAO(Connection con) {
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;

		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// emp_id,dep_id,emp_name,hire_date,phone,extension,emp_password,mail,emp_status
			// FROM emp order by emp_id";

			con = getConectPool().getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
		
			rs = pstmt.executeQuery();

			while (rs.next()) {


				empVO = new EmpVO();
				empVO.setEmpId(rs.getInt("emp_id"));
				empVO.setDepId(rs.getInt("dep_id"));
				empVO.setEmpName(rs.getString("emp_name"));
				empVO.setHiredate(rs.getDate("hire_date"));
//				empVO.setResigndate(rs.getDate("resign_date"));
				empVO.setResigndate(rs.getDate("resign_date"));				
				empVO.setPhone(rs.getString("phone"));
				empVO.setExtension(rs.getString("extension"));
				empVO.setEmpPassword(rs.getString("emp_password"));
				empVO.setHobby(rs.getString("hobby"));
				empVO.setSkill(rs.getString("skill"));
				empVO.setMail(rs.getString("mail"));
				empVO.setBirthday(rs.getDate("birthday"));
				empVO.setEmpStatus(rs.getByte("emp_status"));
				empVO.setEmpProfile(rs.getBytes("emp_profile"));
				list.add(empVO); // Store the row in the list
			}

			// Handle any driver errors

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
		
		}
return list;
	}
	
	
	
	
	
	
	
	public static String get_aCondition_For_myDB(String columnName, String value) {

		String aCondition = null;
		StringBuilder sb=new StringBuilder();
		
if(columnName.contains("emp")||columnName.contains("dep")||columnName.contains("emp") )		
{
sb.append(columnName);
sb.insert(3,"_");
}
else if(columnName.contains("hire"))	
{
	
	sb.append(columnName);
	sb.insert(4,"_");
}
else if(columnName.contains("resign"))	
{
	sb.append(columnName);
	sb.insert(5,"_");
}
else {
	sb.append(columnName);
}
	
String colString=sb.toString();
System.out.println(colString);
System.out.println(value);


		if ("emp_Id".equals(colString)|| "dep_Id".equals(colString) ) // 用於其他
			aCondition = colString + "=" + value;
		else if ("emp_Name".equals(colString)||"phone".equals(colString)||"extension".equals(colString)||"mail".equals(colString) ) // 用於varchar
			aCondition = colString + " like '%" + value + "%'";
		
		else if ("hire_date".equals(colString))                          // 用於date
			aCondition = colString + "=" + "'"+ value +"'";  
			// 用於date
//			aCondition = colString + "=" + "'"+ value +"'";                          //for 其它DB  的 date
//		    aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";  //for Oracle 的 date
		
		return aCondition + " ";
	}

	
	
//	public  String get_aCondition_For_myDB(String columnName, String value) {
//
//		String aCondition = null;
//
//		if ("empno".equals(columnName) || "sal".equals(columnName) || "comm".equals(columnName) || "deptno".equals(columnName)) // 用於其他
//			aCondition = columnName + "=" + value;
//		else if ("ename".equals(columnName) || "job".equals(columnName)) // 用於varchar
//			aCondition = columnName + " like '%" + value + "%'";
//		else if ("hiredate".equals(columnName))                          // 用於date
//			aCondition = columnName + "=" + "'"+ value +"'";                          //for 其它DB  的 date
////		    aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";  //for Oracle 的 date
//		
//		return aCondition + " ";
//	}
	
	
	public static String get_aCondition_For_myDBFront(String columnName, String value) {

		String aCondition = null;
		StringBuilder sb=new StringBuilder();
		
if(columnName.contains("emp")||columnName.contains("dep")||columnName.contains("emp") )		
{
sb.append(columnName);
sb.insert(3,"_");
}

else if(columnName.contains("hire"))	
{
	sb.append(columnName);
	sb.insert(4,"_");
}
else if(columnName.contains("resign"))	
{
	sb.append(columnName);
	sb.insert(5,"_");
}
else {
	sb.append(columnName);
}
	
String colString=sb.toString();
System.out.println(colString);
System.out.println(value);


		if ("emp_Id".equals(colString)|| "dep_Id".equals(colString) ) // 用於其他
			aCondition = colString + "=" + value;
		else if ("emp_Name".equals(colString)||"phone".equals(colString)||"extension".equals(colString)||"mail".equals(colString) ) // 用於varchar
			aCondition = colString + " like '%" + value + "%'";
		
		else if ("hiredate".equals(columnName))                          // 用於date
			aCondition = columnName + "=" + "'"+ value +"'";  
			// 用於date
//			aCondition = colString + "=" + "'"+ value +"'";                          //for 其它DB  的 date
//		    aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";  //for Oracle 的 date
		
		return aCondition + " ";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_myDB(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		
		return whereCondition.toString();
	}
	

	public String get_WhereConditionFront(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_myDBFront(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		
		return whereCondition.toString();
	}
	
	
	
	
	
	
	
	@Override
	public List<EmpVO> getAllDAOFront(Map<String, String[]> map) {
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	EmpDAO dao =new EmpDAO();
		try {
			

			con = getConectPool().getConnection();
			String finalSQL = "select emp_name ,extension,dep_id from emp "
		          + dao.get_WhereConditionFront(map)
		          + "order by emp_Id";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				empVO = new EmpVO();
				empVO.setEmpId(rs.getInt("emp_id"));
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

				list.add(empVO); // Store the row in the List
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<EmpVO> getAllDAO(Map<String, String[]> map) {
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	EmpDAO dao =new EmpDAO();
		try {
			

			con = getConectPool().getConnection();
			String finalSQL = "select * from emp "
		          + dao.get_WhereCondition(map)
		          + "order by emp_Id";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				empVO = new EmpVO();
				empVO.setEmpId(rs.getInt("emp_id"));
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

				list.add(empVO); // Store the row in the List
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

//		EmpDAO dao = new EmpDAO();
//		EmpVO empVO1 = new EmpVO();
//		FileInputStream in = new FileInputStream("/Users/lin/tomcat.gif");
//		byte[] buf = new byte[in.available()];
//		in.read(buf);
		
//		empVO1.setDepId(10);
//		empVO1.setEmpName("張偉航");
//		empVO1.setHiredate(new Date(1991-1900,6,19));
////		empVO1.setResigndate(4, empVO.getResign_date());
//		empVO1.setPhone("0912323255");
//		empVO1.setExtension("043098766");
//		empVO1.setEmpPassword("19940619");
//		empVO1.setHobby("playtheball2");
//		empVO1.setSkill("sleepuntilafternoon2");
//		empVO1.setEmpProfile(buf);
//		empVO1.setMail("oldma1245@gmail.com");
//		empVO1.setBirthday(new Date(1971-1900,3,12));
//		empVO1.setEmpStatus((byte)1);
//		dao.insert(empVO1);
//		System.out.println("成功");
		
//		EmpVO empVO6 = new EmpVO();
//		
//	
//		empVO6.setDepId(11);
//		empVO6.setEmpName("張嘉航");
//		empVO6.setHiredate(new Date(1991-1900,6,19));
////		empVO1.setResigndate(4, empVO.getResign_date());
//		empVO6.setPhone("0912343234");
//		empVO6.setExtension("043097953");
//		empVO6.setEmpPassword("19940619");
//		empVO6.setHobby("playtheball2");
//		empVO6.setSkill("sleepuntilafternoon2");
//		empVO6.setEmpProfile(buf);
//		empVO6.setMail("oldma133@gmail.com");
//		empVO6.setBirthday(new Date(1971-1900,3,12));
//		empVO6.setEmpStatus((byte)1);
//		dao.insert(empVO6);
//		
//		
//		
//		EmpVO empVO7 = new EmpVO();
//		
//		
//		empVO7.setDepId(12);
//		empVO7.setEmpName("瑞斯叔叔");
//		empVO7.setHiredate(new Date(1991-1900,6,19));
////		empVO1.setResigndate(4, empVO.getResign_date());
//		empVO7.setPhone("0912321134");
//		empVO7.setExtension("043047753");
//		empVO7.setEmpPassword("19940619");
//		empVO7.setHobby("playtheball2");
//		empVO7.setSkill("sleepuntilafternoon2");
//		empVO7.setEmpProfile(buf);
//		empVO7.setMail("oldma123n2sd@gmail.com");
//		empVO7.setBirthday(new Date(1971-1900,3,12));
//		empVO7.setEmpStatus((byte)1);
//		dao.insert(empVO7);
//		
//		
//		
//		
//		
//		
//		EmpVO empVO8 = new EmpVO();
//		
	
//		empVO8.setDepId(13);
//		empVO8.setEmpName("特哥");
//		empVO8.setHiredate(new Date(1991-1900,6,19));
////		empVO1.setResigndate(4, empVO.getResign_date());
//		empVO8.setPhone("0912323634");
//		empVO8.setExtension("043010753");
//		empVO8.setEmpPassword("19940619");
//		empVO8.setHobby("playtheball2");
//		empVO8.setSkill("sleepuntilafternoon2");
//		empVO8.setEmpProfile(buf);
//		empVO8.setMail("oldma123n2asdq@gmail.com");
//		empVO8.setBirthday(new Date(1971-1900,3,12));
//		empVO8.setEmpStatus((byte)1);
//		dao.insert(empVO8);
		
		
		
		
		
		
		
//		EmpVO empVO10 = new EmpVO();
//		
//		
//		empVO10.setDepId(15);
//		empVO10.setEmpName("大楷");
//		empVO10.setHiredate(new Date(1991-1900,6,19));
////		empVO1.setResigndate(4, empVO.getResign_date());
//		empVO10.setPhone("0122323234");
//		empVO10.setExtension("043098746");
//		empVO10.setEmpPassword("19940619");
//		empVO10.setHobby("playtheball2");
//		empVO10.setSkill("sleepuntilafternoon2");
//		empVO10.setEmpProfile(buf);
//		empVO10.setMail("oldma123n2fgc@gmail.com");
//		empVO10.setBirthday(new Date(1971-1900,3,12));
//	
//		dao.insert(empVO10);
		
		
		
		
		
		
		
//		EmpVO empVO9 = new EmpVO();
	
		
	
//		empVO9.setDepId(14);
//		empVO9.setEmpName("尬得接接");
//		empVO9.setHiredate(new Date(1991-1900,6,19));
//		empVO1.setResigndate(4, empVO.getResign_date());
//		empVO9.setPhone("0912323220");
//		empVO9.setExtension("043098753");
//		empVO9.setEmpPassword("19940619");
//		empVO9.setHobby("playtheball2");
//		empVO9.setSkill("sleepuntilafternoon2");
//		empVO9.setEmpProfile(buf);
//		empVO9.setMail("oldma123n2jj@gmail.com");
//		empVO9.setBirthday(new Date(1971-1900,3,12));
//		empVO9.setEmpStatus((byte)1);
//		dao.insert(empVO9);
//		in.close();

//		empVO1.setEname("");
//		empVO1.setJob("MANAGER");
//		empVO1.setHiredate(java.sql.Date.valueOf("2005-01-01"));
//		empVO1.setSal(new Double(50000));
//		empVO1.setComm(new Double(500));
//		empVO1.setDeptno(10);

		// private static final String UPDATE = "UPDATE emp set dep_id=?, emp_name=?,
		// hire_date=?, resign_date=?, phone=?, extension=?, emp_password=?, hobby=?,
		// skill=?, emp_profile=?, mail=?, birthday=?, emp_status=?, where emp_id = ?";
//EmpService empser=new EmpService();
//		EmpDAO empser=new EmpDAO();
//		EmpVO empVO2 = new EmpVO();
//		
//		empVO2.setEmpId(1001);
////		
//		empVO2.setDepId(12);
//		empVO2.setEmpName("張加");
//		empVO2.setHiredate(new Date(1995-1900,6,19));
////		empVO2.setResigndate(4, emp.getResign_date());
//		empVO2.setPhone("0987612345");
//		empVO2.setExtension("0424991212");
//		empVO2.setEmpPassword("19940619");
//		empVO2.setHobby("playball");
//		empVO2.setSkill("sleepuntilafternoon");
////		empVO2.setEmpProfile(buf);
////		empVO2.setMail("asiagod@gmail.com");
////		empVO2.setBirthday(new Date(1978-1900,3,12));
////		empVO2.setEmpStatus((byte)2);
//		int x=empser.update(empVO2);
//		System.out.println(x);
		
//		System.out.println("成功");

//		dao.update(empVO2);
//		dao.selectForLogin(1001, "19940619");

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

//		List<EmpVO> list = dao.getAllDAO();
//		for (EmpVO aEmp : list) {
//			System.out.println(aEmp.getEmpId() + ",");
//			System.out.println(aEmp.getDepId() + ",");
//			System.out.println(aEmp.getResigndate() + ",");
//			System.out.println(aEmp.getExtension() + ",");
//			System.out.println(aEmp.getEmpName() + ",");
//			System.out.println(aEmp.getHiredate() + ",");
//			System.out.println(aEmp.getPhone() + ",");
//			System.out.println(aEmp.getExtension() + ",");
//	
//		
//	
//		}
		
	}

	
}