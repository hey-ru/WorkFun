package com.bid.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
import com.secondHand.model.SecondHandService;

@ServerEndpoint("/BidWS/{userId}/{userName}/{bidId}")
public class BidWS {
	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());

	/*
	 * 如果想取得HttpSession與ServletContext必須實作
	 * ServerEndpointConfig.Configurator.modifyHandshake()，
	 * 參考https://stackoverflow.com/questions/21888425/accessing-servletcontext-and-httpsession-in-onmessage-of-a-jsr-356-serverendpoint
	 */
	@OnOpen
	public void onOpen(@PathParam("userId") String userId,
					   @PathParam("userName") String userName,
					   @PathParam("bidId") String bidId,
					   Session userSession) throws IOException {
		connectedSessions.add(userSession);
		String text = String.format("Session ID = %s, connected; userId = %s", userSession.getId(), userId);
		System.out.println(text);
		System.out.println("(servlet-open) Username : " + userName);
		System.out.println("(servlet-open) Bid_id : " + bidId);
	}

	@OnMessage
	public void onMessage(Session userSession, String data) {
//	public void onMessage(Session userSession, String userId, String userName, String bidId, String price) {
		for (Session session : connectedSessions) {
			if (session.isOpen())
				session.getAsyncRemote().sendText(data);
//				推送資訊同時,可以在這邊new一個service,將資訊更新至資料庫
		}
		System.out.println("Data received: " + data);
		
		String bidId;
		bidId = StringUtils.substringAfter(data, "bidId\":");
		bidId = StringUtils.substringBefore(bidId, ",");
		System.out.println("(servlet-message) bidId : " + bidId);
		
		String userId;
		userId = StringUtils.substringAfter(data, "userId\":");
		userId = StringUtils.substringBefore(userId, ",");
		System.out.println("(servlet-message) userId : " + userId);
		
//		String userName;
//		userName = StringUtils.substringAfter(data, "userName\":\"");
//		userName = StringUtils.substringBefore(userName, "\",");
//		System.out.println("(servlet-message) userName : " + userName);

		String price;
		price = StringUtils.substringAfter(data, "price\":\"");
		price = StringUtils.substringBefore(price, "\"");
		System.out.println("(servlet-message) price : " + price);

		
		
		BidVO bidVO = new BidVO();
		bidVO.setBid_id(Integer.parseInt(bidId));
		bidVO.setBidder(Integer.parseInt(userId));
		bidVO.setPrice(Integer.parseInt(price));
		
		
		BidService bidService = new BidService();
		bidService.updateBid(bidVO);
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		connectedSessions.remove(userSession);
		String text = String.format("session ID = %s, disconnected; close code = %d; reason phrase = %s",
				userSession.getId(), reason.getCloseCode().getCode(), reason.getReasonPhrase());
		System.out.println(text);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

}
