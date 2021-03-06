package com.chatRoom.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ChatRoomJDBCDAO implements ChatRoomDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://database-1.cqm5mb4z5ril.ap-northeast-1.rds.amazonaws.com:3306/CGA101-03?serverTimezone=Asia/Taipei";
	String userid = "cga101-03";
	String passwd = "cga101-03";

	private static final String INSERT_STMT = "INSERT INTO chat_room (is_group) VALUES (?)";
//	private static final String GET_ALL_STMT = "SELECT chat_room_id,is_group FROM chat_room order by chat_room_id";
	private static final String GET_ONE_STMT = "SELECT chat_room_id,is_group FROM chat_room where chat_room_id = ?";
//	private static final String DELETE = "DELETE FROM secondHand where second_hand_id = ?";
//	private static final String UPDATE = "UPDATE chat_room set is_group=? where chat_room_id = ?";

	@Override
	public void insert(ChatRoomVO chatroomVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, chatroomVO.getIs_group());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
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

//	@Override
//	public void update(ChatRoomVO chatroomVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setInt(1, chatroomVO.getIs_group());
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
//	public void delete(Integer chat_room_id) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setInt(1, chat_room_id);
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
//	}

	@Override
	public ChatRoomVO findByPrimaryKey(Integer chat_room_id) {
		ChatRoomVO chatroomVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, chat_room_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				chatroomVO = new ChatRoomVO();
				chatroomVO.setChat_room_id(rs.getInt("chat_room_id"));
				chatroomVO.setIs_group(rs.getInt("is_group"));
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
		return chatroomVO;
	}

//	@Override
//	public List<ChatRoomVO> getAll() {
//		List<ChatRoomVO> list = new ArrayList<ChatRoomVO>();
//		ChatRoomVO chatroomVO = null;
//
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
//				// empVO ????????? Domain objects
//				chatroomVO = new ChatRoomVO();
//				chatroomVO.setChat_room_id(rs.getInt("chat_room_id"));
//				chatroomVO.setIs_group(rs.getInt("is_group"));
//				list.add(chatroomVO); // Store the row in the list
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
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

		ChatRoomJDBCDAO dao = new ChatRoomJDBCDAO();

		// ??????
//		ChatRoomVO chatRoomVO1 = new ChatRoomVO();
//		chatRoomVO1.setIs_group(0);
//		dao.insert(chatRoomVO1);

		// ??????
//		ChatRoomVO chatRoomVO2 = new ChatRoomVO();
//		chatRoomVO2.setIs_group(1);
//		dao.update(chatRoomVO2);

		// ??????
//			dao.delete(10007);

		// ??????
//		ChatRoomVO chatRoomVO3 = dao.findByPrimaryKey(10006);
//		System.out.print(chatRoomVO3.getChat_room_id() + ",");
//		System.out.println(chatRoomVO3.getIs_group());
//		System.out.println("---------------------");

		// ??????
//		List<ChatRoomVO> list = dao.getAll();
//		for (ChatRoomVO listChatRoomVO : list) {
//			System.out.print(listChatRoomVO.getChat_room_id() + ",");
//			System.out.println(listChatRoomVO.getIs_group());
//		}
	}

}
