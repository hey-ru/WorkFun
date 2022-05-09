package com.bid.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.bid.model.BidService;
import com.bid.model.BidVO;
import com.secondHand.model.SecondHandService;
import com.secondHand.model.SecondHandVO;

@WebServlet("/bid/BidServlet")
public class BidServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
//		System.out.println(action);
		
		if ("update".equals(action)) { // 來自bidHome.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			Integer price = null;
			if (req.getParameter("price").trim().length()>10) {
				errorMsgs.put("price","請輸入正確的價格");
			} else {
				try {
					price = Integer.valueOf(req.getParameter("price").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("price","價格請填數字");
				}
				if (price <= 0) {
					errorMsgs.put("price", "請輸入大於0的數字");
				}
				if (price >= Integer.valueOf(req.getParameter("top_price"))) {
					price = Integer.valueOf(req.getParameter("top_price").trim());
					Integer bidder = Integer.valueOf(req.getParameter("bidder").trim());
					Integer bid_id = Integer.valueOf(req.getParameter("bid_id").trim());
					Integer second_hand_id = Integer.valueOf(req.getParameter("second_hand_id").trim());
					
					BidVO newBidVO = new BidVO();
					newBidVO.setBidder(bidder);
					newBidVO.setPrice(price);
					newBidVO.setBid_id(bid_id);
					
					SecondHandVO newSecondHandVO = new SecondHandVO();
					newSecondHandVO.setsecond_hand_id(second_hand_id);
					newSecondHandVO.setBid_winner(bidder);
					newSecondHandVO.setDeal_price(price);
					newSecondHandVO.setIs_deal(2);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req.getRequestDispatcher("/bid/bidHome.jsp");
						failureView.forward(req, res);
						return; // 程式中斷
					}

					/*************************** 2.開始修改資料 *****************************************/
					BidService bidService = new BidService();
					bidService.updateBid(newBidVO);
					
					SecondHandService secondHandService = new SecondHandService();
					secondHandService.updateSecondHand(newSecondHandVO);

					/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
					String url = "/bid/bidViewOnly.jsp";
					req.setAttribute("second_hand_id", second_hand_id);
					RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交bidHome.jsp
					successView.forward(req, res);
					
					return;
				}
			}

			Integer bidder = Integer.valueOf(req.getParameter("bidder").trim());
			Integer bid_id = Integer.valueOf(req.getParameter("bid_id").trim());
			Integer second_hand_id = Integer.valueOf(req.getParameter("second_hand_id").trim());
			
			BidVO newBidVO = new BidVO();
			newBidVO.setBidder(bidder);
			newBidVO.setPrice(price);
			newBidVO.setBid_id(bid_id);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/bid/bidHome.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
//			BidService bidService = new BidService();
//			BidVO bidVO = bidService.updateBid(bidder, price, bid_id);
			
			BidService bidService = new BidService();
			bidService.updateBid(newBidVO);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			String url = "/bid/bidHome.jsp";
			req.setAttribute("second_hand_id", second_hand_id);
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交bidHome.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.put("修改資料失敗",e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/bid/bidHome.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		if ("updateWithTopPrice".equals(action)) { // 來自bidHome.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			Integer price = Integer.valueOf(req.getParameter("top_price").trim());
			Integer bidder = Integer.valueOf(req.getParameter("bidder").trim());
			Integer bid_id = Integer.valueOf(req.getParameter("bid_id").trim());
			Integer second_hand_id = Integer.valueOf(req.getParameter("second_hand_id").trim());
			
			BidVO newBidVO = new BidVO();
			newBidVO.setBidder(bidder);
			newBidVO.setPrice(price);
			newBidVO.setBid_id(bid_id);
			
			SecondHandVO newSecondHandVO = new SecondHandVO();
			newSecondHandVO.setsecond_hand_id(second_hand_id);
			newSecondHandVO.setBid_winner(bidder);
			newSecondHandVO.setDeal_price(price);
			newSecondHandVO.setIs_deal(2);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/bid/bidHome.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			BidService bidService = new BidService();
			bidService.updateBid(newBidVO);
			
			SecondHandService secondHandService = new SecondHandService();
			secondHandService.updateSecondHand(newSecondHandVO);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			String url = "/bid/bidViewOnly.jsp";
			req.setAttribute("second_hand_id", second_hand_id);
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交bidHome.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.put("修改資料失敗",e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/bid/bidHome.jsp");
//				failureView.forward(req, res);
//			}
		}
	}
}