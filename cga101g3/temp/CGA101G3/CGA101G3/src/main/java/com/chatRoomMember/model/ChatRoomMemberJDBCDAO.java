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
//	public void update(chatRoomMemberVO chatroommemberVO) {
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
//		}
//
//	}

//	@Override
//	public void delete(chatRoomMemberVO chatroommemberVO) {
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
//	public List<chatRoomMemberVO> getAll() {
//		List<chatRoomMemberVO> list = new ArrayList<chatRoomMemberVO>();
//		chatRoomMemberVO chatroommemberVO;
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
//				chatroommemberVO = new chatRoomMemberVO();
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
		ChatRoomMemberVO chatroommemberVO1 = new ChatRoomMemberVO();
		chatroommemberVO1.setChat_room_member_id(1013);
		chatroommemberVO1.setChat_room_id(10007);
		dao.insert(chatroommemberVO1);
		
		// 新增
//		chatRoomMemberVO chatroommemberVO2 = new chatRoomMemberVO();
//		chatroommemberVO2.setChat_room_member_id(1014);
//		chatroommemberVO2.setChat_room_id(10007);
//		dao.insert(chatroommemberVO2);

		// 修改
//		chatRoomMemberVO chatroommemberVO2 = new chatRoomMemberVO();
//		chatroommemberVO2.setChat_room_id(10007);
//		chatroommemberVO2.setChat_room_member_id(1014);
//		dao.update(chatroommemberVO2);

		// 刪除
//		dao.delete(1009);

		// 查詢
//		secondHandVO secondhandVO3 = dao.findByPrimaryKey(1005);
//		System.out.print(secondhandVO3.getsecond_hand_id() + ",");
//		System.out.print(secondhandVO3.getSaler() + ",");
//		System.out.print(secondhandVO3.getBid_winner() + ",");
//		System.out.print(secondhandVO3.getDeal_price() + ",");
//		System.out.print(secondhandVO3.getName() + ",");
//		System.out.print(secondhandVO3.getBottom_price() + ",");
//		System.out.print(secondhandVO3.getTop_price() + ",");
//		System.out.print(secondhandVO3.getStart_time() + ",");
//		System.out.print(secondhandVO3.getEnd_time() + ",");
//		System.out.print(secondhandVO3.getIs_deal() + ",");
//		System.out.print(secondhandVO3.getImg1() + "test1,");
//		System.out.print(secondhandVO3.getImg2() + "2,");
//		System.out.print(secondhandVO3.getImg3() + "3,");
//		System.out.print(secondhandVO3.getCreate_time() + ",");
//		System.out.println(secondhandVO3.getUpdate_time());
//		System.out.println("---------------------");

		// 查詢
//		List<secondHandVO> list = dao.getAll();
//		for (secondHandVO listSecondHandVO : list) {
//			System.out.print(listSecondHandVO.getsecond_hand_id() + ",");
//			System.out.print(listSecondHandVO.getSaler() + ",");
//			System.out.print(listSecondHandVO.getBid_winner() + ",");
//			System.out.print(listSecondHandVO.getDeal_price() + ",");
//			System.out.print(listSecondHandVO.getName() + ",");
//			System.out.print(listSecondHandVO.getBottom_price() + ",");
//			System.out.print(listSecondHandVO.getTop_price() + ",");
//			System.out.print(listSecondHandVO.getStart_time() + ",");
//			System.out.print(listSecondHandVO.getEnd_time() + ",");
//			System.out.print(listSecondHandVO.getIs_deal() + ",");
//			System.out.print(listSecondHandVO.getImg1() + ",");
//			System.out.print(listSecondHandVO.getImg2() + ",");
//			System.out.print(listSecondHandVO.getImg3() + ",");
//			System.out.print(listSecondHandVO.getCreate_time() + ",");
//			System.out.println(listSecondHandVO.getUpdate_time());
//		}
	}

}
