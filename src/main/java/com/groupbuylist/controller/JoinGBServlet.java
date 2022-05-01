package com.groupbuylist.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.menu.model.MenuService;
import com.menu.model.MenuVO;

@WebServlet("/groupbuylist/JoinGB")
public class JoinGBServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getmenu".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("shop_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入店家編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/menu/selectMenu_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				Integer shop_id = null;
				try {
					shop_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("店家編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/menu/selectMenu_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				MenuService menuService = new MenuService();

				List<MenuVO> menuList = menuService.getByShopId(shop_id);
				if (menuList == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/menu/selectMenu_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				// 資料庫取出的menuVO物件集合,存入req
				req.setAttribute("menuList", menuList);
				String url = "/menu/listMenuByShop.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ************************************/

			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/menu/selectMenu_page.jsp");
				failureView.forward(req, res);
			}
		}

		
		


	}

}
