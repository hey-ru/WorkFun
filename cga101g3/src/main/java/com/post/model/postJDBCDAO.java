package com.post.model;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.report.model.reportVO;

public class postJDBCDAO implements postDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://cga101-03@database-1.cqm5mb4z5ril.ap-northeast-1.rds.amazonaws.com:3306/CGA101-03?zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Taipei";
	String userid = "cga101-03";
	String passwd = "cga101-03";

	private static final String INSERT_STMT = "INSERT INTO post (emp_id,post_title,post_content,post_video,post_createtime,post_updatetime,is_disable) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM post";
	private static final String GET_ONE_STMT = "SELECT post_id,emp_id,post_title,post_content,post_video,post_createtime,post_updatetime,is_disable FROM post where post_id = ?";
	private static final String UPDATE = "UPDATE post set emp_id=?,post_title=?,post_content=?,post_video=?,post_createtime=?,post_updatetime=?,is_disable=? where post_id = ?";

	@Override
	public void insert(postVO postVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, postVO.getEmp_id());
			pstmt.setString(2, postVO.getPost_title());
			pstmt.setString(3, postVO.getPost_content());
			pstmt.setBytes(4, postVO.getPost_video());
			pstmt.setTimestamp(5, postVO.getPost_createtime());
			pstmt.setTimestamp(6, postVO.getPost_updatetime());
			pstmt.setInt(7, postVO.getIs_disable());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (ClassNotFoundException e) {
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
	public void update(postVO postVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, postVO.getEmp_id());
			pstmt.setString(2, postVO.getPost_title());
			pstmt.setString(3, postVO.getPost_content());
			pstmt.setBytes(4, postVO.getPost_video());
			pstmt.setTimestamp(5, postVO.getPost_createtime());
			pstmt.setTimestamp(6, postVO.getPost_updatetime());
			pstmt.setInt(7, postVO.getIs_disable());
			pstmt.setInt(8, postVO.getPost_id());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (ClassNotFoundException e) {
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
	public postVO findByPrimaryKey(Integer post_id) {
		postVO postVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, post_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				postVO = new postVO();
				postVO.setPost_id(rs.getInt("post_id"));
				postVO.setEmp_id(rs.getInt("emp_id"));
				postVO.setPost_title(rs.getString("post_title"));
				postVO.setPost_content(rs.getString("post_content"));
				postVO.setPost_video(rs.getBytes("post_video"));
				postVO.setPost_createtime(rs.getTimestamp("post_createtime"));
				postVO.setPost_updatetime(rs.getTimestamp("post_updatetime"));
				postVO.setIs_disable(rs.getInt("is_disable"));

			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (ClassNotFoundException e) {
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
		return postVO;
	}

	@Override
	public List<postVO> getAll() {
		List<postVO> list = new ArrayList<postVO>();
		postVO postVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				postVO = new postVO();
				postVO.setPost_id(rs.getInt("post_id"));
				postVO.setEmp_id(rs.getInt("emp_id"));
				postVO.setPost_title(rs.getString("post_title"));
				postVO.setPost_content(rs.getString("post_content"));
				postVO.setPost_video(rs.getBytes("post_video"));
				postVO.setPost_createtime(rs.getTimestamp("post_createtime"));
				postVO.setPost_updatetime(rs.getTimestamp("post_updatetime"));
				postVO.setIs_disable(rs.getInt("is_disable"));
				list.add(postVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (ClassNotFoundException e) {
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

	public static void main(String[] args) throws Exception {
		postJDBCDAO dao = new postJDBCDAO();
		postVO postVO = new postVO();
		Long datetime = System.currentTimeMillis();
		Timestamp timestamp = new Timestamp(datetime);
		FileInputStream in = new FileInputStream("C:\\Users\\Tibame_T14\\Desktop\\tomcat2.gif");
		byte[] buf = new byte[in.available()];
		in.read(buf);

//		Insert
//      postVO.setEmp_id(1009);
//		postVO.setPost_title("誰要一起吃披薩");
//		postVO.setPost_content("小港國王下詔：徵求3位一起share披薩歡樂餐");
//		postVO.setPost_video(null);
//		postVO.setPost_createtime(timestamp);
//		postVO.setPost_updatetime(null);
//		postVO.setIs_disable(0);
//		dao.insert(postVO);
//		
//		in.close();

// 		Update-------------------------------------------------------
//		postVO postVO2 = new postVO();
//		postVO2.setPost_id(1001);
//		postVO2.setEmp_id(1009);
//		postVO2.setPost_title("Oh我的天");
//		postVO2.setPost_content("機車被拖吊啦!!!");
//		postVO2.setPost_video(null);
//		postVO2.setPost_createtime(timestamp);
//		postVO2.setPost_updatetime(null);
//		postVO2.setIs_disable(0);
//		dao.update(postVO2);
//		
//		in.close();

		// -select------------------------------------------
//		postVO postVO3 = dao.findByPrimaryKey(1001);
//		System.out.println(postVO3.getEmp_id());
//		System.out.println(postVO3.getPost_title());
//		System.out.println(postVO3.getPost_content());
//		System.out.println(postVO3.getPost_createtime());
//		System.out.println(postVO3.getPost_updatetime());
//		System.out.println(postVO3.getIs_disable());
//		System.out.println(postVO3.getPost_video());
		// -selectALL-------------------------------
		List<postVO> list = dao.getAll();
		for (postVO po : list) {
			System.out.println(po.getPost_id());
			System.out.println(po.getEmp_id());
			System.out.println(po.getPost_title());
			System.out.println(po.getPost_content());
			System.out.println(po.getPost_createtime());
			System.out.println(po.getPost_updatetime());
			System.out.println(po.getIs_disable());
			System.out.println(po.getPost_video());
		}
	}
}
