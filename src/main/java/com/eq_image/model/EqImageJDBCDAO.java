package com.eq_image.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EqImageJDBCDAO implements EqImageDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://database-1.cqm5mb4z5ril.ap-northeast-1.rds.amazonaws.com:3306/CGA101-03?useUnicode=yes&characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Taipei";
	// database-1.cqm5mb4z5ril.ap-northeast-1.rds.amazonaws.com --> 資料庫連線
	String userid = "cga101-03";
	String passwd = "cga101-03";

	private static final String INSERT = "INSERT INTO eq_image(equipment_id,eq_image) VALUES (?,?)";

	private static final String GET_ONE = "SELECT image_id,equipment_id,eq_image FROM eq_image where image_id = ?";

	private static final String GET_ALL = "SELECT image_id,equipment_id,eq_image FROM eq_image";

	@Override
	public void insert(EqImageVO eqImageVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);

			pstmt.setInt(1, eqImageVO.getEquipmentId());
			pstmt.setBytes(2, eqImageVO.getEqImage());

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
	public EqImageVO findByPrimaryKey(Integer imageId) {

		EqImageVO eqImageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE);

			pstmt.setInt(1, imageId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				eqImageVO = new EqImageVO();
				eqImageVO.setImageId(rs.getInt("image_id"));
				eqImageVO.setEquipmentId(rs.getInt("equipment_id"));
				eqImageVO.setEqImage(rs.getBytes("eq_image"));
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
		return eqImageVO;
	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}

	@Override
	public List<EqImageVO> getAll() {

		List<EqImageVO> list = new ArrayList<EqImageVO>();
		EqImageVO eqImageVO = null;

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
				eqImageVO = new EqImageVO();
				eqImageVO = new EqImageVO();
				eqImageVO.setImageId(rs.getInt("image_id"));
				eqImageVO.setEquipmentId(rs.getInt("equipment_id"));
				eqImageVO.setEqImage(rs.getBytes("eq_image"));
				list.add(eqImageVO); // Store the row in the list
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

	public static void main(String[] args) throws Exception {
		EqImageJDBCDAO dao = new EqImageJDBCDAO();

		// 新增
//		EqImageVO eqImageVO1 = new EqImageVO();
//		eqImageVO1.setEquipmentId(104);
//		byte[] image1 = getPictureByteArray("C:\\Users\\Tibame_T14\\Desktop\\照片\\圖片1.jpg");
//		eqImageVO1.setEqImage(image1);
//		dao.insert(eqImageVO1);

		// 查詢
//		EqImageVO eqImageVO2 = dao.findByPrimaryKey(11);
//		System.out.println(eqImageVO2.getImageId());
//		System.out.println(eqImageVO2.getEquipmentId());
//		System.out.println(eqImageVO2.getEqImage());
	
		// 查詢
		List<EqImageVO> list = dao.getAll();
		for(EqImageVO aeqImageVO : list) {
			System.out.println(aeqImageVO.toString());
		}
	}
}
