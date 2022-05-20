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


@WebServlet("/groupbuy/AddGBServlet")
public class AddGBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		
				//主揪揪團前先判斷有沒有菜單
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer shop_id = Integer.valueOf(req.getParameter("shop_id"));
				System.out.println(shop_id);
				
				/***************************2.開始查詢資料*****************************************/
				MenuService menuSvc = new MenuService();
				List<MenuVO> list = menuSvc.getByShopId(shop_id);
			
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				
				if(list.isEmpty()) {
					req.setAttribute("shop_id", shop_id);
					System.out.println(shop_id);
					String url = "/menu/addMenu.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);
					
				}else {
					req.setAttribute("shop_id", shop_id);
					System.out.println(shop_id);
					String url = "/groupbuy/owner_addGB.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);
				}
	}
}
