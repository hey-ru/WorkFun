package com.bid.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.lang3.StringUtils;

import com.bid.model.BidService;
import com.bid.model.BidVO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.secondHand.model.SecondHandService;
import com.secondHand.model.SecondHandVO;

@ServerEndpoint("/BidWS/{bidRoomId}/{userId}")
public class BidWS {
//	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());
	private static final Map<String, Set<Session>> bidRooms = new ConcurrentHashMap();

	/*
	 * 如果想取得HttpSession與ServletContext必須實作
	 * ServerEndpointConfig.Configurator.modifyHandshake()，
	 * 參考https://stackoverflow.com/questions/21888425/accessing-servletcontext-and-httpsession-in-onmessage-of-a-jsr-356-serverendpoint
	 */
	@OnOpen
	public void onOpen(@PathParam("bidRoomId") String bidRoomId, @PathParam("userId") String userId, Session userSession) throws IOException {
		bidRooms.containsKey(bidRoomId);
		
//		將session按照bidRoomId(bidId)來儲存，將各個競標(房間)的使用者區隔出來
        if (!bidRooms.containsKey(bidRoomId)) {
//            房間不存在時，創建房間
            Set<Session> bidroom = new HashSet<>();
//            將userSession存入
            bidroom.add(userSession);
            bidRooms.put(bidRoomId, bidroom);
        } else {
//        	如果房間已存在，將user存入該房間
        	bidRooms.get(bidRoomId).add(userSession);
        }
//        System.out.println("A client has connected!");
    
		
//		String text = String.format("Session ID = %s, connected; userId = %s", userSession.getId(), userId);
//		System.out.println(text);
	}

	@OnMessage
	public void onMessage(@PathParam("bidRoomId") String bidRoomId, Session userSession, String data) {

		for (Session session : bidRooms.get(bidRoomId)) {
			if (session.isOpen())
				session.getAsyncRemote().sendText(data);
//				推送資訊同時,可以在這邊new一個service,將資訊更新至資料庫
		}
		System.out.println("Data received: " + data);
		
		Gson gson = new Gson();
		JsonObject jsonObject = gson.fromJson(data, JsonObject.class);
		
		Integer userId = jsonObject.get("userId").getAsInt();
//		System.out.println("(servlet-message) userId : " + userId);		

		Integer secondHandId = jsonObject.get("secondHandId").getAsInt();
//		System.out.println("(servlet-message) secondHandId : " + secondHandId);
		
		Integer bidId = jsonObject.get("bidId").getAsInt();
//		System.out.println("(servlet-message) bidId : " + bidId);
		
		Integer topPrice = jsonObject.get("topPrice").getAsInt();
//		System.out.println("(servlet-message) topPrice : " + topPrice);
		
		Integer price = jsonObject.get("price").getAsInt();
//		System.out.println("(servlet-message) price : " + price);

//		String userId; // 神奇取字串方式
//		userId = StringUtils.substringAfter(data, "userId\":");
//		userId = StringUtils.substringBefore(userId, ",");
//		System.out.println("(servlet-message) userId : " + userId);
		
		if(topPrice.compareTo(price) == 0) {
			SecondHandVO secondHandVO = new SecondHandVO();
			secondHandVO.setBid_winner(userId);
			secondHandVO.setDeal_price(topPrice);
			secondHandVO.setIs_deal(2);
			secondHandVO.setsecond_hand_id(secondHandId);
			
			SecondHandService secondHandService = new SecondHandService();
			secondHandService.updateSecondHand(secondHandVO);
		}
		
		BidVO bidVO = new BidVO();
		bidVO.setBid_id(bidId);
		bidVO.setBidder(userId);
		bidVO.setPrice(price);
		
		BidService bidService = new BidService();
		bidService.updateBid(bidVO);
	}

	@OnClose
//	public void onClose(@PathParam("bidRoomId") String bidId, Session userSession, CloseReason reason) {
	public void onClose(@PathParam("bidRoomId") String bidId, Session userSession) {
//		使用者離開時，取得該房間，並將使用者移出
		bidRooms.get(bidId).remove(userSession);
//        System.out.println("a client has disconnected!");
//		String text = String.format("session ID = %s, disconnected; close code = %d; reason phrase = %s",
//				userSession.getId(), reason.getCloseCode().getCode(), reason.getReasonPhrase());
//		System.out.println(text);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

}
