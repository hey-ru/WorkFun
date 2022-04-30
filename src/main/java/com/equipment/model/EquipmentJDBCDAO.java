package com.equipment.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentJDBCDAO implements EquipmentDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://database-1.cqm5mb4z5ril.ap-northeast-1.rds.amazonaws.com:3306/CGA101-03?useUnicode=yes&characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Taipei";
	String userid = "cga101-03";
	String passwd = "cga101-03";

	private static final String INSERT = "INSERT INTO equipment (eq_name,price,eq_status,introduction,spec) VALUES (?,?,?,?,?)";
	private static final String UPDATE = "UPDATE equipment set ";
	private static final String DELETE_BY_EQID = "DELETE FROM equipment where equipment_id = ?";
	private static final String DELETE_BY_EQNAME = "DELETE FROM equipment where eq_name = ?";
	private static final String GET_LAST = "SELECT * FROM equipment ORDER BY equipment_id DESC LIMIT 0 , 1";
	private static final String GET_ALL_BY_EQNAME = "SELECT equipment_id,eq_name,price,eq_status,introduction,spec FROM equipment where eq_name like ?";
	private static final String GET_BY_EQUIPMENTID = "SELECT equipment_id,eq_name,price,eq_status,introduction,spec FROM equipment where equipment_id = ?";
	private static final String GET_BY_EQSTATUS = "SELECT equipment_id,eq_name,price,eq_status,introduction,spec FROM equipment where eq_status = ?";
	private static final String GET_ALL = "SELECT equipment_id,eq_name,price,eq_status,introduction,spec FROM equipment";

	@Override
	public void insert(EquipmentVO equipmentVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, equipmentVO.getEqName());
			pstmt.setInt(2, equipmentVO.getPrice());
			pstmt.setInt(3, equipmentVO.getEqStatus());
			pstmt.setString(4, equipmentVO.getIntroduction());
			pstmt.setString(5, equipmentVO.getSpec());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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

	@Override
	public int update(EquipmentVO newequipment) {

		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			EquipmentVO oldEquipment = getByEqId(newequipment.getEqId());
			System.out.println(oldEquipment.getEqId());
			StringBuilder sb = new StringBuilder();

			sb.append(UPDATE);

//			if (newequipment.getEquipmentId() != null) {
//				sb.append("equipment_id=?, ");
//			}
			if (newequipment.getEqName() != null) {
				sb.append("eq_name=?, ");
			}
			if (newequipment.getPrice() != null) {
				sb.append("price=?, ");
			}
			if (newequipment.getEqStatus() != null) {
				sb.append("eq_status=?, ");
			}
			if (newequipment.getIntroduction() != null) {
				sb.append("introduction=?, ");
			}
			if (newequipment.getSpec() != null) {
				sb.append("spec=?, ");
			}

			sb.append("equipment_id=? ");
			sb.append("where equipment_id =? ");

			pstmt = con.prepareStatement(sb.toString());

//			if (newequipment.getEquipmentId() != null) {
//				count++;
//				pstmt.setInt(count, newequipment.getEquipmentId());
//			}

			if (newequipment.getEqName() != null) {
				count++;
				pstmt.setString(count, newequipment.getEqName());
			}

			if (newequipment.getPrice() != null) {
				count++;
				pstmt.setInt(count, newequipment.getPrice());
			}

			if (newequipment.getEqStatus() != null) {
				count++;
				pstmt.setInt(count, newequipment.getEqStatus());
			}

			if (newequipment.getIntroduction() != null) {
				count++;
				pstmt.setString(count, newequipment.getIntroduction());
			}

			if (newequipment.getSpec() != null) {
				count++;
				pstmt.setString(count, newequipment.getSpec());
			}

			count++;
			pstmt.setInt(count, newequipment.getEquipmentId());
			count++;
			pstmt.setInt(count, newequipment.getEquipmentId());

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

	@Override
	public void deleteByEqID(Integer equipmentId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_BY_EQID);

			pstmt.setInt(1, equipmentId);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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

	@Override
	public void deleteByEqName(String eqName) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_BY_EQNAME);

			pstmt.setString(1, eqName);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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

	@Override
	public List<EquipmentVO> getAllByEqName(String eqName) {

		List<EquipmentVO> list = new ArrayList<EquipmentVO>();
		EquipmentVO equipmentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_BY_EQNAME);

			pstmt.setString(1, "%" + eqName + "%");

			rs = pstmt.executeQuery();

			while (rs.next()) {
				equipmentVO = new EquipmentVO();
				equipmentVO.setEquipmentId(rs.getInt("equipment_id"));
				equipmentVO.setEqName(rs.getString("eq_name"));
				equipmentVO.setPrice(rs.getInt("price"));
				equipmentVO.setEqStatus(rs.getInt("eq_status"));
				equipmentVO.setIntroduction(rs.getString("introduction"));
				equipmentVO.setSpec(rs.getString("spec"));
				list.add(equipmentVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public List<EquipmentVO> getALL() {
		List<EquipmentVO> list = new ArrayList<EquipmentVO>();
		EquipmentVO equipmentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				equipmentVO = new EquipmentVO();
				equipmentVO.setEquipmentId(rs.getInt("Equipment_id"));
				equipmentVO.setEqName(rs.getString("eq_name"));
				equipmentVO.setPrice(rs.getInt("price"));
				equipmentVO.setEqStatus(rs.getInt("eq_status"));
				equipmentVO.setIntroduction(rs.getString("introduction"));
				equipmentVO.setSpec(rs.getString("spec"));
				list.add(equipmentVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public EquipmentVO getByEqId(Integer equipmentId) {

		EquipmentVO equipmentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_EQUIPMENTID);

			pstmt.setInt(1, equipmentId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				equipmentVO = new EquipmentVO();
				equipmentVO.setEquipmentId(rs.getInt("equipment_id"));
				equipmentVO.setEqName(rs.getString("eq_name"));
				equipmentVO.setPrice(rs.getInt("price"));
				equipmentVO.setEqStatus(rs.getInt("eq_status"));
				equipmentVO.setIntroduction(rs.getString("introduction"));
				equipmentVO.setSpec(rs.getString("spec"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
		return equipmentVO;
	}

	@Override
	public EquipmentVO getByEqStatus(Integer eqStatus) {
		EquipmentVO equipmentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_EQSTATUS);

			pstmt.setInt(1, eqStatus);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				equipmentVO = new EquipmentVO();
				equipmentVO.setEquipmentId(rs.getInt("equipment_id"));
				equipmentVO.setEqName(rs.getString("eq_name"));
				equipmentVO.setPrice(rs.getInt("price"));
				equipmentVO.setEqStatus(rs.getInt("eq_status"));
				equipmentVO.setIntroduction(rs.getString("introduction"));
				equipmentVO.setSpec(rs.getString("spec"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
		return equipmentVO;
	}

	public static void main(String[] args) {
		EquipmentJDBCDAO dao = new EquipmentJDBCDAO();

		// 新增
//		EquipmentVO equipmentVO1 = new EquipmentVO();
//		equipmentVO1.setEqName("GAMEBOY");
//		equipmentVO1.setPrice(Integer.valueOf(2800));
//		equipmentVO1.setEqStatus(3);
//		equipmentVO1.setIntroduction("初代 GAMEBOY 1989年產的初代GAMEBOY");
//		equipmentVO1.setSpec("瑪利歐");
//		dao.insert(equipmentVO1);

		// 修改

		EquipmentVO equipmentVO2 = new EquipmentVO();
		equipmentVO2.setEquipmentId(115);

		equipmentVO2.setEqName("PSPPRO");
		equipmentVO2.setPrice(Integer.valueOf(3));
		equipmentVO2.setEqStatus(1);
//		equipmentVO2.setIntroduction("1.新的PSP重量約189克(原為280克) 厚度18.6mm(原為23.0mm)");
//		equipmentVO2.setSpec("●(480×272，1677萬色");
		dao.update(equipmentVO2);

		// 刪除 by eqID
//		dao.deleteByEqID(102);

		// 刪除 by eqName
//		dao.deleteByEqName("PSP");

		// 查詢 All by EqName
//		List<EquipmentVO> list = dao.getAllByEqName("s");
//		for (EquipmentVO aEquipment : list) {
//			System.out.println(aEquipment.toString());
//		}

		// 查詢 by EQUIPMENTID
//		EquipmentVO equipmentVO4 = dao.getByEqId(104);
//		System.out.print(equipmentVO4.getEquipmentId() + ", ");
//		System.out.print(equipmentVO4.getEqName() + ", ");
//		System.out.print(equipmentVO4.getPrice() + ", ");
//		System.out.print(equipmentVO4.getEqStatus() + ", ");
//		System.out.print(equipmentVO4.getIntroduction() + ", ");
//		System.out.println(equipmentVO4.getSpec() + ", ");
//		System.out.println("-------------------------------------");

		// 查詢 by EQUIPMENTID
//		EquipmentVO equipmentVO5 = dao.getByEqStatus(3);
//		System.out.print(equipmentVO5.getEquipmentId() + ", ");
//		System.out.print(equipmentVO5.getEqName() + ", ");
//		System.out.print(equipmentVO5.getPrice() + ", ");
//		System.out.print(equipmentVO5.getEqStatus() + ", ");
//		System.out.print(equipmentVO5.getIntroduction() + ", ");
//		System.out.println(equipmentVO5.getSpec() + ", ");
//		System.out.println("-------------------------------------");

		// 查詢 ALL
//		List<EquipmentVO> list = dao.getALL();
//		for (EquipmentVO aEquipment : list) {
//			System.out.print(aEquipment.getEquipmentId() + ", ");
//			System.out.print(aEquipment.getEqName() + ", ");
//			System.out.print(aEquipment.getPrice() + ", ");
//			System.out.print(aEquipment.getEqStatus() + ", ");
//			System.out.print(aEquipment.getIntroduction() + ", ");
//			System.out.println(aEquipment.getSpec() + ", ");
//			System.out.println();
//		}
	}

	@Override
	public EquipmentVO getLast() {

		EquipmentVO equipmentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_LAST);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				equipmentVO = new EquipmentVO();
				equipmentVO.setEquipmentId(rs.getInt("equipment_id"));
				equipmentVO.setEqName(rs.getString("eq_name"));
				equipmentVO.setPrice(rs.getInt("price"));
				equipmentVO.setEqStatus(rs.getInt("eq_status"));
				equipmentVO.setIntroduction(rs.getString("introduction"));
				equipmentVO.setSpec(rs.getString("spec"));
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
		return equipmentVO;
	}
}