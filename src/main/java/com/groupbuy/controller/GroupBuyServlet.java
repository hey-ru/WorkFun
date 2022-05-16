package com.groupbuy.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


import com.groupbuy.model.*;
import com.groupbuylist.model.GroupBuyListService;
import com.groupbuylist.model.GroupBuyListVO;
import com.menu.model.*;
import com.shop.model.*;


@WebServlet("/groupbuy/GroupBuyServlet")
public class GroupBuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		//參團者加入揪團並新增修改刪除品項(加入菜單搜尋結果)
		if ("showGB".equals(action)) { 

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer gb_id = Integer.valueOf(req.getParameter("gb_id"));
				Integer shop_id = Integer.valueOf(req.getParameter("shop_id")); //以店家搜尋出菜單
				
				/***************************2.開始查詢資料*****************************************/
				GroupBuyService gbSvc = new GroupBuyService();
				GroupBuyVO groupBuyVO = gbSvc.getOneGB(gb_id);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				HttpSession session = req.getSession();
				session.setAttribute("groupBuyVO", groupBuyVO);
				System.out.println(groupBuyVO.toString());
				
				//再取得一次店家菜單物件集合,以顯示於填寫揪團單畫面
				MenuService menuService = new MenuService();
				List<MenuVO> menuList = menuService.getByShopId(shop_id);
				HttpSession session1 = req.getSession();
				session1.setAttribute("menuList", menuList);
				
				//轉交
				String url = "/groupbuylist/buyer_joinGB.jsp";
				
				// 成功轉交 buyer_joinGB.jsp
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
		}
		
		
		//從主揪的list選一個查看詳細	
		if ("getOne_For_Display".equals(action)) { // 來自owner_selectGb.jsp的請求

//			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
//			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer gb_id = Integer.valueOf(req.getParameter("gb_id"));
				
				/***************************2.開始查詢資料*****************************************/
				GroupBuyService groupBuySvc = new GroupBuyService();
				GroupBuyVO groupBuyVO = groupBuySvc.getOneGB(gb_id);
				Set<GroupBuyListVO> GBbuyers = groupBuySvc.getBuyerBygbid(gb_id);
				Integer sumTotal;
				sumTotal = GBbuyers.stream()
		    			.mapToInt(e -> e.getTotal() )
		    			.sum();
				long payCount = GBbuyers.stream()
						.filter(e -> e.getIs_pay().equals(0))
						.count();
				long pickCount = GBbuyers.stream()
						.filter(e -> e.getIs_pickup().equals(0))
						.count();
				String closeGB;
				if(payCount+pickCount == 0) {
					closeGB = "close";
				}else {
					closeGB = "open";
				}
				
				
				Set<GroupBuyListVO> groupBuyListVOs = groupBuySvc.getGroupBuyListBygbid(gb_id);
				
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("groupBuyVO", groupBuyVO);
				req.setAttribute("GBbuyers", GBbuyers);
				req.setAttribute("groupBuyListVOs", groupBuyListVOs);
				req.setAttribute("sumTotal", sumTotal);
				req.setAttribute("closeGB", closeGB);
				String url = "/groupbuy/owner_selectOneGB.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

		}
		
		
		//owner_selectOneGB.jsp中修改到貨時間
		if ("updateArrTime".equals(action)) { // 來自owner_selectOneGB.jsp的請求
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			

			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			
			Integer gb_id = Integer.valueOf(req.getParameter("gb_id").trim());
			Timestamp end_time = Timestamp.valueOf(req.getParameter("end_time"));;
			Timestamp arr_time = null;
			try {
				arr_time = Timestamp.valueOf(req.getParameter("arr_time"));
				if(arr_time.compareTo(end_time)<=0) {
					arr_time = null;
					errorMsgs.put("arr_time","請再確認到貨時間必須晚於截止時間!");
				}
			} catch (IllegalArgumentException e) {
				arr_time = null;
			}
			
			
			/***************************2.開始修改資料*****************************************/
			GroupBuyService groupBuySvc = new GroupBuyService();
			groupBuySvc.updateArrTime(gb_id, arr_time);
			GroupBuyVO groupBuyVO = groupBuySvc.getOneGB(gb_id);
			Set<GroupBuyListVO> GBbuyers = groupBuySvc.getBuyerBygbid(gb_id);
			Integer sumTotal;
			sumTotal = GBbuyers.stream()
	    			.mapToInt(e -> e.getTotal() )
	    			.sum();
			long payCount = GBbuyers.stream()
					.filter(e -> e.getIs_pay().equals(0))
					.count();
			long pickCount = GBbuyers.stream()
					.filter(e -> e.getIs_pickup().equals(0))
					.count();
			String closeGB;
			if(payCount+pickCount == 0) {
				closeGB = "close";
			}else {
				closeGB = "open";
			}
			
			Set<GroupBuyListVO> groupBuyListVOs = groupBuySvc.getGroupBuyListBygbid(gb_id);
			/***************************3.修改完成,準備轉交(Send the Success view)*************/
			req.setAttribute("groupBuyVO", groupBuyVO);
			req.setAttribute("GBbuyers", GBbuyers);
			req.setAttribute("sumTotal", sumTotal);
			req.setAttribute("closeGB", closeGB);
			req.setAttribute("groupBuyListVOs", groupBuyListVOs); 
			String url = "/groupbuy/owner_selectOneGB.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);

	}
		
			//gbBack.jsp揪團管理(後台) 修改團狀態
			if ("updateGBStatus".equals(action)) { 
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			

			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			
			Integer gb_id = Integer.valueOf(req.getParameter("gb_id").trim());
			Integer gb_status = Integer.valueOf(req.getParameter("gb_status").trim());
			
			/***************************2.開始修改資料*****************************************/
			GroupBuyService groupBuySvc = new GroupBuyService();
			groupBuySvc.updateGBStatusBygbId(gb_id, gb_status);
			
			/***************************3.修改完成,準備轉交(Send the Success view)*************/
			String front = req.getParameter("front");
			String url = "/groupbuy/gbBack.jsp";
			
			if("front".equals(front)) {
				url = "/groupbuy/owner_selectGB.jsp";
			}
			
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);

	}
		
		//主揪修改團員取貨付款狀態
		if ("updatePayPickUp".equals(action)) { // 來自owner_selectOneGB.jsp的請求
		

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				Integer buyer = Integer.valueOf(req.getParameter("buyer").trim());
				Integer gb_id = Integer.valueOf(req.getParameter("gb_id").trim());
				Integer is_pay = Integer.valueOf(req.getParameter("is_pay").trim());		
				Integer is_pickup = Integer.valueOf(req.getParameter("is_pickup").trim());
								
			
				
				/***************************2.開始修改資料*****************************************/
				GroupBuyListService groupBuyListSvc = new GroupBuyListService();
				groupBuyListSvc.updatePayPickUP(gb_id, buyer, is_pay, is_pickup);
				GroupBuyService groupBuySvc = new GroupBuyService();
				GroupBuyVO groupBuyVO = groupBuySvc.getOneGB(gb_id);
				Set<GroupBuyListVO> GBbuyers = groupBuySvc.getBuyerBygbid(gb_id);
				Integer sumTotal;
				sumTotal = GBbuyers.stream()
		    			.mapToInt(e -> e.getTotal() )
		    			.sum();
				long payCount = GBbuyers.stream()
						.filter(e -> e.getIs_pay().equals(0))
						.count();
				long pickCount = GBbuyers.stream()
						.filter(e -> e.getIs_pickup().equals(0))
						.count();
				String closeGB;
				if(payCount+pickCount == 0) {
					closeGB = "close";
				}else {
					closeGB = "open";
				}
				Set<GroupBuyListVO> groupBuyListVOs = groupBuySvc.getGroupBuyListBygbid(gb_id);

				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("groupBuyVO", groupBuyVO);
				req.setAttribute("GBbuyers", GBbuyers);
				req.setAttribute("sumTotal", sumTotal);
				req.setAttribute("closeGB", closeGB);
				req.setAttribute("groupBuyListVOs", groupBuyListVOs);
				String url = "/groupbuy/owner_selectOneGB.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

		}

		//主揪新增一個團
        if ("insert".equals(action)) { // 來自listALLShop.jsp listOneShop.jsp的請求  
			
        	Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
	
				Integer shop_id = Integer.valueOf(req.getParameter("shop_id").trim());
				String shop_name = req.getParameter("shop_name");
				System.out.println(shop_name);
				Integer gb_owner = Integer.valueOf(req.getParameter("gb_owner").trim());
				
				Timestamp start_time = null;
				try {
					start_time = Timestamp.valueOf(req.getParameter("start_time"));
				} catch (IllegalArgumentException e) {
					start_time = new java.sql.Timestamp((long)(System.currentTimeMillis()));
				}
				
				Timestamp end_time = null;
				try {
					end_time = Timestamp.valueOf(req.getParameter("end_time"));
					if(end_time.compareTo(start_time)<=0) {
						end_time = new java.sql.Timestamp((long)(System.currentTimeMillis()));
						errorMsgs.put("end_time","請再確認截止時間必須晚於開始時間!");
					}
				} catch (IllegalArgumentException e) {
					end_time = new java.sql.Timestamp((long)(System.currentTimeMillis()));
					errorMsgs.put("end_time","請輸入截止時間!");
				}
				
				Timestamp arr_time = null;
				try {
					arr_time = Timestamp.valueOf(req.getParameter("arr_time"));
					if(arr_time.compareTo(end_time)<=0) {
						arr_time = null;
						errorMsgs.put("arr_time","請再確認到貨時間必須晚於截止時間!");
					}
				} catch (IllegalArgumentException e) {
					arr_time = null;
				}
				
								
				
				Integer min_amt = 0;
				String min_amtstr = req.getParameter("min_amt").trim();
				if(min_amtstr.trim().length() != 0) {
					try {
						min_amt = Integer.valueOf(min_amtstr);
					} catch (NumberFormatException e) {
						errorMsgs.put("min_amt","外送低消請填數字");
					}
				}
				
				GroupBuyVO groupBuyVO = new GroupBuyVO();
				groupBuyVO.setShop_id(shop_id);
				groupBuyVO.setShop_name(shop_name);
				groupBuyVO.setGb_owner(gb_owner);
				groupBuyVO.setStart_time(start_time);
				groupBuyVO.setEnd_time(end_time);
				groupBuyVO.setArr_time(arr_time);
				groupBuyVO.setMin_amt(min_amt);
				
				groupBuyVO.getShopVO();


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/groupbuy/owner_addGB.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				GroupBuyService groupBuySvc = new GroupBuyService();
				groupBuySvc.addGroupBuy(shop_id, shop_name, gb_owner, start_time, end_time, arr_time, gb_owner, min_amt);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/groupbuy/gbHome.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
}
        
      //複合查詢gbHome
    	if ("listByCompositeQuery".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();
			
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				
				// 以下的 if 區塊只對第一次執行時有效
				if (req.getParameter("whichPage") == null){
					Map<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
					session.setAttribute("map",map1);
					map = map1;
				} 
				
				/***************************2.開始複合查詢***************************************/
				GroupBuyService groupBuySvc = new GroupBuyService();
				List<GroupBuyVO> list  = groupBuySvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listByCompositeQuery", list); // 資料庫取出的list物件,存入request
//				session.setAttribute("listByCompositeQuery", list);
				RequestDispatcher successView = req.getRequestDispatcher("/groupbuy/listGBCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
		}
    	
    	 //複合查詢(後台)gbBack
    	if ("listByCompositeQueryBack".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();
			
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				
				// 以下的 if 區塊只對第一次執行時有效
				if (req.getParameter("whichPage") == null){
					Map<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
					session.setAttribute("map",map1);
					map = map1;
				} 
				
				/***************************2.開始複合查詢***************************************/
				GroupBuyService groupBuySvc = new GroupBuyService();
				List<GroupBuyVO> list  = groupBuySvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listByCompositeQuery", list); // 資料庫取出的list物件,存入request
//				session.setAttribute("listByCompositeQuery", list);
				RequestDispatcher successView = req.getRequestDispatcher("/groupbuy/gbBackCQ.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
		}
	
	}

}
