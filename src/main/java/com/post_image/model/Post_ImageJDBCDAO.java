package com.post_image.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Post_ImageJDBCDAO implements Post_ImageDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://cga101-03@database-1.cqm5mb4z5ril.ap-northeast-1.rds.amazonaws.com:3306/CGA101-03?zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Taipei";
	String userid = "cga101-03";
	String passwd = "cga101-03";
	
	private static final String INSERT_STMT = "INSERT INTO post_image (post_id,image) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM post_image";
	private static final String GET_ONE_STMT = "SELECT post_id,image FROM post_image where post_image_id = ?";
	private static final String UPDATE = "UPDATE post_image set ";

	@Override
	public void insert(Post_ImageVO post_imageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, post_imageVO.getPost_id());
			pstmt.setBytes(2, post_imageVO.getImage());

			pstmt.executeUpdate();

			// Handle any SQL errors
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
	public void update(Post_ImageVO post_imageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			StringBuilder sb = new StringBuilder();
			sb.append(UPDATE);
			
			if(post_imageVO.getImage()!=null) {
				sb.append("image=?,");
			}

			sb.append("post_image_id=?");
			sb.append(" where post_image_id=?");
			
			pstmt = con.prepareStatement(sb.toString());
			
			if(post_imageVO.getImage()!=null) {
				count++;
				pstmt.setBytes(count, post_imageVO.getImage());
			}
			count++;
			pstmt.setInt(count, post_imageVO.getPost_image_id());
			count++;
			pstmt.setInt(count, post_imageVO.getPost_image_id());
			pstmt.executeUpdate();

			// Handle any SQL errors
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
	public Post_ImageVO findByPrimaryKey(Integer post_image_id) {
		Post_ImageVO post_imageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, post_image_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				post_imageVO = new Post_ImageVO();
				post_imageVO.setPost_id(rs.getInt("post_id"));
				post_imageVO.setImage(rs.getBytes("image"));
				
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return post_imageVO;
	}

	@Override
	public List<Post_ImageVO> getAll() {
		List<Post_ImageVO> list = new ArrayList<Post_ImageVO>();
		Post_ImageVO post_imageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				post_imageVO = new Post_ImageVO();
				post_imageVO.setPost_image_id(rs.getInt("post_image_id"));
				post_imageVO.setPost_id(rs.getInt("post_id"));
				post_imageVO.setImage(rs.getBytes("image"));
				list.add(post_imageVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

//	public static void main(String[] args) throws IOException {
//		post_imageJDBCDAO dao = new post_imageJDBCDAO();
//		post_imageVO post_imageVO = new post_imageVO();
//		FileInputStream in = new FileInputStream("C:\\Users\\Tibame_T14\\Desktop\\image\\5.png");
//		byte[] buf = new byte[in.available()];
//		in.read(buf);
		
//		Insert
		
//		post_imageVO.setPost_id(1003);
//		post_imageVO.setImage(buf);
//		dao.insert(post_imageVO);
		
// 		Update-------------------------------------------------------
//		post_imageVO post_imageVO2 = new post_imageVO();
//		post_imageVO2.setPost_image_id(1);
//		post_imageVO2.setImage(buf);
//		dao.update(post_imageVO2);

		// -select------------------------------------------
//		post_imageVO post_imageVO3 = dao.findByPrimaryKey(1);
//		System.out.println(post_imageVO3.getPost_id());
//		System.out.println(post_imageVO3.getImage());
		
		// selectALL----------------------------------------
		
//		List<post_imageVO> list = dao.getAll();
//		for (post_imageVO postImg : list) {
//			System.out.println(postImg.getPost_image_id());
//			System.out.println(postImg.getPost_id());
//			System.out.println(postImg.getImage());

		
//	}
//	}
}
