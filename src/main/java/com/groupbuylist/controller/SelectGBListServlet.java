package com.groupbuylist.controller;

import java.io.IOException;
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

import com.groupbuy.model.GroupBuyService;
import com.groupbuy.model.GroupBuyVO;
import com.groupbuylist.model.GroupBuyListService;
import com.groupbuylist.model.GroupBuyListVO;
import com.menu.model.MenuService;
import com.menu.model.MenuVO;

@WebServlet("/groupbuylist/selectGBList")
public class SelectGBListServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);

		// 查看個人參團明細, Session
//		if ("get_buyerGB".equals(action)) { // 來自selectMenu_page.jsp的請求
//
//			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/*************************** 1.接收請求參數 ****************************************/
//			Integer buyer = Integer.valueOf(req.getParameter("buyer"));
//			Integer gb_id = Integer.valueOf(req.getParameter("gb_id"));
//			
//			/***************************2.開始查詢資料***************************************/
//			GroupBuyListService gbListSvc = new GroupBuyListService();
//			List<GroupBuyListVO> buyer_gbList  = gbListSvc.getMyGB(buyer);
//			GroupBuyService gbSvc = new GroupBuyService();
//			GroupBuyVO groupBuyVO = gbSvc.getOneGB(gb_id);
//			
//			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//			// 資料庫取出的menuVO物件集合,存入session(修改或新增回上一頁可取得)
//			HttpSession session = req.getSession();
//			session.setAttribute("buyer_gbList", buyer_gbList);
//			
//			//再取得一次揪團資訊
//			
//			session.setAttribute("groupBuyVO", groupBuyVO);
//			System.out.println(groupBuyVO.toString());
//			
//
//			String url = "/groupbuylist/buyer_selectGB.jsp";
//			// 成功轉交
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
//		}

	}

}
