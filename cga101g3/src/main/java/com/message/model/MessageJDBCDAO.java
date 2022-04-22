package com.message.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MessageJDBCDAO implements MessageDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://database-1.cqm5mb4z5ril.ap-northeast-1.rds.amazonaws.com:3306/CGA101-03?serverTimezone=Asia/Taipei";
	String userid = "cga101-03";
	String passwd = "cga101-03";

	private static final String INSERT_STMT = "INSERT INTO message (chat_room_id,chat_room_member_id,content) VALUES (?, ?, ?)";
//	private static final String UPDATE = "UPDATE message set content=? where message_id = ?";
//	private static final String DELETE = "DELETE FROM message where message_id = ?";
	private static final String GET_ONE_STMT = "SELECT message_id,chat_room_id,chat_room_member_id,content,create_time FROM message where message_id = ?";
//	private static final String GET_ALL_STMT = "SELECT message_id,chat_room_id,chat_room_member_id,content,create_time FROM message order by message_id";

	@Override
	public void insert(MessageVO messageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, messageVO.getMessage_id());
			pstmt.setInt(2, messageVO.getChat_room_id());
			pstmt.setInt(3, messageVO.getChat_room_member_id());
			pstmt.setString(4, messageVO.getContent());
			pstmt.setTimestamp(5, messageVO.getCreate_time());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

//	@Override
//	public void update(MessageVO messageVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setString(1, messageVO.getContent());
//
//			pstmt.executeUpdate();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}

//	@Override
//	public void delete(Integer message_id) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setInt(1, message_id);
//
//			pstmt.executeUpdate();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}

	@Override
	public MessageVO findByPrimaryKey(Integer message_id) {
		MessageVO messageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, message_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				messageVO = new MessageVO();
				messageVO.setMessage_id(rs.getInt("message_id"));
				messageVO.setChat_room_id(rs.getInt("chat_room_id"));
				messageVO.setChat_room_member_id(rs.getInt("chat_room_member_id"));
				messageVO.setContent(rs.getString("content"));
				messageVO.setCreate_time(rs.getTimestamp("create_time"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return messageVO;
	}

//	@Override
//	public List<MessageVO> getAll() {
//		List<MessageVO> list = new ArrayList<MessageVO>();
//		MessageVO messageVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				messageVO = new MessageVO();
//				messageVO.setMessage_id(rs.getInt("message_id"));
//				messageVO.setChat_room_id(rs.getInt("chat_room_id"));
//				messageVO.setChat_room_member_id(rs.getInt("chat_room_member_id"));
//				messageVO.setContent(rs.getString("content"));
//				messageVO.setCreate_time(rs.getTimestamp("create_time"));
//				list.add(messageVO);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return list;
//	}

	
	public static void main(String[] args) {

		MessageJDBCDAO dao = new MessageJDBCDAO();

		// 新增
//		MessageVO messageVO1 = new MessageVO();
//		messageVO1.setChat_room_id(10006);
//		messageVO1.setChat_room_member_id(1012);
//		messageVO1.setContent("有加薪嗎?");
//		dao.insert(messageVO1);

		// 修改
//		MessageVO messageVO2 = new MessageVO();
//		messageVO2.setContent("有放假嗎?");
//		dao.update(messageVO2);

		// 刪除
//		dao.delete(1000013);

		// 查詢
//		MessageVO messageVO3 = dao.findByPrimaryKey(1000012);
//		System.out.print(messageVO3.getMessage_id() + ",");
//		System.out.print(messageVO3.getChat_room_id() + ",");
//		System.out.print(messageVO3.getChat_room_member_id() + ",");
//		System.out.print(messageVO3.getContent() + ",");
//		System.out.println(messageVO3.getCreate_time());
//		System.out.println("---------------------");

		// 查詢
//		List<MessageVO> list = dao.getAll();
//		for (MessageVO listMessageVO : list) {
//			System.out.print(listMessageVO.getMessage_id() + ",");
//			System.out.print(listMessageVO.getChat_room_id() + ",");
//			System.out.print(listMessageVO.getChat_room_member_id() + ",");
//			System.out.print(listMessageVO.getContent() + ",");
//			System.out.println(listMessageVO.getCreate_time());
//		}
	}
	
}
