package com.groupbuylist.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.groupbuylist.model.GroupBuyListService;
import com.groupbuylist.model.GroupBuyListVO;

@WebServlet("/groupbuylist/addGBList")
public class AddGBListServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);
		
		// 在該團選購品項下單
		if ("insertAll".equals(action)) { // 來自buyer_joinGB.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				//備註
				String remark = req.getParameter("remark");
				String remarkReg ="^[(\u4e00-\u9fa5)(\u0800-\u4e00)a-zA-Z0-9_\\(\\-\\)]*$";
				if (!remark.trim().matches(remarkReg)) { // 正則(規)表示式(regular-expression)
					errorMsgs.put("remark", "備註: 只能是中、日、英文字母、數字、_、-和()");
				}
				
				// 其他參數不須輸入格式的錯誤處理
				//From groupbuyVO
				Integer gb_id = Integer.valueOf(req.getParameter("gb_id"));
				//From empVO
				Integer buyer = Integer.valueOf(req.getParameter("buyer"));
//				String buyer_name = req.getParameter("buyer_name");
				//From menu
				Integer menu_id = Integer.valueOf(req.getParameter("menu_id"));
//				String item = req.getParameter("item");
//				Integer price = Integer.valueOf(req.getParameter("price"));
				//點擊新增
				Integer qty = Integer.valueOf(req.getParameter("qty"));
				System.out.println("接收請求參數");
				System.out.println("gb_id: " + gb_id);
				System.out.println("buyer: " + buyer);
//				System.out.println("buyer_name: " + buyer_name);
				System.out.println("menu_id: " + menu_id);
//				System.out.println("item: " + item);
//				System.out.println("price: " + price);
				System.out.println("qty: " + qty);
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/groupbuylist/buyer_joinGB.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}

				/*************************** 2.開始新增資料 *****************************************/
				GroupBuyListService gblistSvc = new GroupBuyListService();
				GroupBuyListVO groupBuyListVO = gblistSvc.addGbItem(gb_id, buyer, buyer_name, menu_id, item, price, qty, remark);
				System.out.println("新增的值: " + groupBuyListVO.toString());
				
				/*************************** 3.新增完成,準備轉交(Send the Success view) *************/
				String url = "/groupbuy/gbHome.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listMenuByShop.jsp
				successView.forward(req, res);
				System.out.println(gb_id + "參團新增成功!");
		}

	}

}
