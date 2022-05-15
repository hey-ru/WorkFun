package com.groupbuylist.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.groupbuylist.model.GroupBuyListService;
import com.groupbuylist.model.GroupBuyListVO;

@WebServlet("/groupbuylist/addGBList")
public class AddGBListServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=utf-8");
		String action = req.getParameter("action");
		System.out.println(action);

		if ("insert2GBlist".equals(action)) { // 來自buyer_joinGB.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			// 取得參數
			String gb_id = req.getParameter("gb_id");
			String buyer = req.getParameter("buyer");
			String buyer_name = req.getParameter("buyer_name");

			String[] menu_id = req.getParameterValues("menu_id");
			String[] item = req.getParameterValues("item");
			String[] price = req.getParameterValues("price");
			String[] qty = req.getParameterValues("qty");
			
			// 備註例外處理
			String[] remark = req.getParameterValues("remark");
			String remarkReg = "^[(\u4e00-\u9fa5)(\u0800-\u4e00)a-zA-Z0-9_+\\s\\(\\-\\)]*$";
			for (String str : remark) {
				if ((str.trim().length() != 0) && !(str.trim().matches(remarkReg))) {
					errorMsgs.put("remark", "備註:只能填中、日、英文字母、數字、_、-、+空格和()");
				}
			}

			int sumQty = 0;
			for (String qtyString : qty) {
				sumQty += Integer.valueOf(qtyString);
				System.out.println(sumQty);
			}
			if (sumQty == 0) {
				errorMsgs.put("msgQty", "😵請選擇數量再下單‼");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/groupbuylist/buyer_joinGB.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始新增資料 ***************************/
			GroupBuyListService gblistSvc = new GroupBuyListService();
			gblistSvc.insertMany(gb_id, buyer, buyer_name, menu_id, item, price, qty, remark);

			/********************* 3.新增完成,準備轉交(Send the Success view) *************/
			List<GroupBuyListVO> getOneList = gblistSvc.getOne(Integer.valueOf(buyer), Integer.valueOf(gb_id));
			HttpSession session = req.getSession();
			session.setAttribute("getOneList", getOneList);

			String url = "/groupbuylist/buyer_selectGB.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listMenuByShop.jsp
			successView.forward(req, res);

		}

	}

}
