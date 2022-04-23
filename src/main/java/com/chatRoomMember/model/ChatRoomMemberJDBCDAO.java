package com.chatRoomMember.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ChatRoomMemberJDBCDAO implements ChatRoomMemberDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://database-1.cqm5mb4z5ril.ap-northeast-1.rds.amazonaws.com:3306/CGA101-03?serverTimezone=Asia/Taipei";
	String userid = "cga101-03";
	String passwd = "cga101-03";
	
	private static final String INSERT_STMT = "INSERT INTO chat_room_member (chat_room_member_id,chat_room_id) VALUES (?, ?)";
//	private static final String GET_ALL_STMT = "SELECT chat_room_member_id,chat_room_id FROM chat_room_member order by chat_room_id";
	private static final String GET_ONE_STMT = "SELECT chat_room_member_id,chat_room_id FROM chat_room_member where chat_room_member_id = ? and second_hand_id = ?";
//	private static final String DELETE = "DELETE FROM chat_room_member where chat_room_member_id = ? and chat_room_id = ?";
//	private static final String UPDATE = "UPDATE chat_room_member set chat_room_member_id=?, chat_room_id=? where chat_room_member_id = ? and chat_room_id = ?";

	@Override
	public void insert(ChatRoomMemberVO chatroommemberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, chatroommemberVO.getChat_room_member_id());
			pstmt.setInt(2, chatroommemberVO.getChat_room_id());
			
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
//	public void update(ChatRoomMemberVO chatroommemberVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//			
//			pstmt.setInt(1, chatroommemberVO.getChat_room_member_id());
//			pstmt.setInt(2, chatroommemberVO.getChat_room_id());
//			
//			pstmt.executeUpdate();
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
//	public void delete(ChatRoomMemberVO chatroommemberVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(DELETE);
//			
//			pstmt.setInt(1, chatroommemberVO.getChat_room_member_id());
//			pstmt.setInt(2, chatroommemberVO.getChat_room_id());
//			
//			pstmt.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//
//	}

	@Override
	public ChatRoomMemberVO findByPrimaryKey(Integer chat_room_member_id, Integer chat_room_id) {
		ChatRoomMemberVO chatroommemberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, chat_room_member_id);
			pstmt.setInt(2, chat_room_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				chatroommemberVO = new ChatRoomMemberVO();
				chatroommemberVO.setChat_room_member_id(rs.getInt("chat_room_member_id"));
				chatroommemberVO.setChat_room_id(rs.getInt("chat_room_id"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return chatroommemberVO;
	}

//	@Override
//	public List<ChatRoomMemberVO> getAll() {
//		List<ChatRoomMemberVO> list = new ArrayList<ChatRoomMemberVO>();
//		ChatRoomMemberVO chatroommemberVO;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				chatroommemberVO = new ChatRoomMemberVO();
//				chatroommemberVO.setChat_room_member_id(rs.getInt("chat_room_member_id"));
//				chatroommemberVO.setChat_room_id(rs.getInt("chat_room_id"));
//				list.add(chatroommemberVO);
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if(pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//			if(rs != null) {
//				try {
//					rs.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		
//		return list;
//	}
	
	public static void main(String[] args) {

		ChatRoomMemberJDBCDAO dao = new ChatRoomMemberJDBCDAO();

		// 新增
//		ChatRoomMemberVO chatroommemberVO1 = new ChatRoomMemberVO();
//		chatroommemberVO1.setChat_room_member_id(1013);
//		chatroommemberVO1.setChat_room_id(10007);
//		dao.insert(chatroommemberVO1);
		
		// 新增
//		ChatRoomMemberVO chatroommemberVO2 = new ChatRoomMemberVO();
//		chatroommemberVO2.setChat_room_member_id(1014);
//		chatroommemberVO2.setChat_room_id(10007);
//		dao.insert(chatroommemberVO2);

		// 修改
//		ChatRoomMemberVO chatroommemberVO2 = new ChatRoomMemberVO();
//		chatroommemberVO2.setChat_room_id(10007);
//		chatroommemberVO2.setChat_room_member_id(1014);
//		dao.update(chatroommemberVO2);

		// 刪除
//		dao.delete(1009);

		// 查詢
//		ChatRoomMemberVO chatroommemberVO3 = dao.findByPrimaryKey(1014, 10006);
//		System.out.print(chatroommemberVO3.getChat_room_member_id() + ",");
//		System.out.println(chatroommemberVO3.getChat_room_id());
//		System.out.println("---------------------");

		// 查詢
//		List<ChatRoomMemberVO> list = dao.getAll();
//		for (ChatRoomMemberVO listChatRoomMemberVO : list) {
//			System.out.print(listChatRoomMemberVO.getChat_room_member_id() + ",");
//			System.out.println(listChatRoomMemberVO.getChat_room_id());
//		}
	}

}
