package com.announcement.model;

import java.util.*;

import com.announcement_mapping.model.Announcement_mappingJDBCDAO;
import com.announcement_mapping.model.Announcement_mappingVO;

import static com.util.ConnectionPool.getConectPool;

//import org.graalvm.compiler.core.common.alloc.Trace;


import java.io.IOException;

import java.sql.*;


public class AnnouncementDAO implements AnnouncementDAO_interface {
//連線池未完成

	private static final String INSERT_STMT = "INSERT INTO announcement (announcer,announcement_title,announcement_content) VALUES (?,?,?) ";
	private static final String GET_ALL_STMT = "select announcement_id,announcer,announcement_title,announcement_content,announcement_time,announcement_status FROM announcement order by announcement_id ";
	private static final String GET_ONE_STMT = "select announcer,announcement_title,announcement_content,announcement_time,announcement_status FROM announcement where announcement_id = ? ";
	private static final String DELETE = "DELETE FROM announcement where announcement_id = ?  ;";
	private static final String UPDATE = "UPDATE announcement set ";

// insert ok
	//
	@Override
	public void insert(AnnouncementVO announcementVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = getConectPool().getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
		

			
			pstmt.setInt(1, announcementVO.getAnnouncer());
			pstmt.setString(2, announcementVO.getAnnouncement_title());
			pstmt.setString(3, announcementVO.getAnnouncement_content());

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
		}  finally {
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
	public void insertWithImg(AnnouncementVO announcementVO, List<Announcement_mappingVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {


			con = getConectPool().getConnection();
			

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

		
			String cols[] = { "announcement_id" };// for 複合主鍵

			pstmt = con.prepareStatement(INSERT_STMT, cols);

			pstmt.setInt(1, announcementVO.getAnnouncer());
			pstmt.setString(2, announcementVO.getAnnouncement_title());
			pstmt.setString(3, announcementVO.getAnnouncement_content());
			
//			Statement stmt = con.createStatement();

			pstmt.executeUpdate();
			System.out.println("before");
			Integer announcement_id = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				announcement_id = rs.getInt(1);
			
			} 
			rs.close();
				
			
			// 再同時新增競標
			
			Announcement_mappingJDBCDAO mapDao=new Announcement_mappingJDBCDAO();
			Iterator<Announcement_mappingVO> iterator=list.iterator();
			while (iterator.hasNext()) {
				 
				 Announcement_mappingVO	announcement_mappingVO=iterator.next();
				announcement_mappingVO.setAnnouncement_id(announcement_id);
				
			
				mapDao.insert(announcement_mappingVO,con);
			
				
			}
				
			
			
			
			
			
			
		
	

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
		

			// Handle any driver errors
		}  catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-secondHand");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public int update(AnnouncementVO announcementVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		int count=0;
		try {
			con = getConectPool().getConnection();
		
			
			StringBuilder sb=new StringBuilder();
			sb.append(UPDATE);
			
			if (announcementVO.getAnnouncement_title() != null) {
				sb.append("announcement_title=?, ");
			}
			if (announcementVO.getAnnouncement_content() != null) {
				sb.append("announcement_content=?, ");
			}
			
sb.append("announcement_id=? ");
			
			sb.append("where announcement_id = ? ");
			pstmt = con.prepareStatement(sb.toString());
			if (announcementVO.getAnnouncement_title() != null) {
				count++;
				pstmt.setString(count, announcementVO.getAnnouncement_title());
			}
			if (announcementVO.getAnnouncement_content() != null) {
				count++;
				pstmt.setString(count, announcementVO.getAnnouncement_content());
			}
			count++;
			
			pstmt.setInt(count, announcementVO.getAnnouncement_id());
	count++;
			
			pstmt.setInt(count, announcementVO.getAnnouncement_id());
			pstmt.executeUpdate();

			// Handle any driver errors

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}  finally {
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

	public void delete(Integer Announcement_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = getConectPool().getConnection();
			pstmt = con.prepareStatement(DELETE);
	

			pstmt.setInt(1, Announcement_id);

		

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}  finally {
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

	public AnnouncementVO findByPrimaryKey(Integer announcement_id) {
		
		AnnouncementVO announcementVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConectPool().getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			

			pstmt.setInt(1, announcement_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				announcementVO = new AnnouncementVO();
								
								announcementVO.setAnnouncement_id(announcement_id);
								announcementVO.setAnnouncer(rs.getInt("announcer"));
								announcementVO.setAnnouncement_title(rs.getString("announcement_title"));
								announcementVO.setAnnouncement_content(rs.getString("announcement_content"));
								announcementVO.setAnnouncement_time(rs.getTimestamp("announcement_time"));
								announcementVO.setAnnouncement_status(rs.getByte("announcement_status"));

								
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

			con = getConectPool().getConnection();
		
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

announcementVO = new AnnouncementVO();
announcementVO.setAnnouncement_id(rs.getInt("announcement_id"));
announcementVO.setAnnouncer(rs.getInt("announcer"));
announcementVO.setAnnouncement_title(rs.getString("announcement_title"));
announcementVO.setAnnouncement_content(rs.getString("announcement_content"));
announcementVO.setAnnouncement_time(rs.getTimestamp("announcement_time"));
announcementVO.setAnnouncement_status(rs.getByte("announcement_status"));


				list.add(announcementVO); // Store the row in the list
			}

			// Handle any driver errors

		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}  finally {
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
		AnnouncementVO AnnouncementVO1 = new AnnouncementVO();
//AnnouncementVO1.setAnnouncer(1001);
//AnnouncementVO1.setAnnouncement_content("我就知道是你又是你");
//AnnouncementVO1.setAnnouncement_title("狗蛋大兵");
//dao.insert(AnnouncementVO1);
	
//		AnnouncementVO AnnouncementVO2=dao.findByPrimaryKey(1001);
//		System.out.println(AnnouncementVO2.getAnnouncement_content().toString());
		List<AnnouncementVO> list=dao.getAll();
		System.out.println(list);
		
		
		
		
		
		
		
		
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
	@Override
	public List<AnnouncementVO> getAllWithImg(Integer announcement_id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int updateWithImg(AnnouncementVO announcementVO, List<Announcement_mappingVO> list) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<AnnouncementVO> getAllSelectByStatus() {
		// TODO Auto-generated method stub
		return null;
	}
}