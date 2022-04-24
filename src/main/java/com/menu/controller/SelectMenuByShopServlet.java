package com.menu.controller;

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

@WebServlet("/menu/menu.do")
public class SelectMenuByShopServlet extends HttpServlet {

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

		
		
		
		// 修改單項
		if ("getMenuItem_For_Update".equals(action)) { // 來自listMenuByShop.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer menu_id = new Integer(req.getParameter("menu_id"));
			
				/*************************** 2.開始查詢資料 ****************************************/
				MenuService menuService = new MenuService();
				MenuVO menuVO = menuService.getOneMenuItem(menu_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("menuVO", menuVO); // 資料庫取出的menuVO物件,存入req
				String url = "/menu/update_menu_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_menu_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/menu/listMenuByShop.jsp");
				failureView.forward(req, res);
			}
		}
//無法取得要修改的資料:????????
		

		// 跳頁修改
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer menu_id = new Integer(req.getParameter("menu_id").trim());
				
				String item = req.getParameter("item");
				String itemReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (item == null || item.trim().length() == 0) {
					errorMsgs.add("品項: 請勿空白");
				} else if (!item.trim().matches(itemReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("品項: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				Integer price = null;
				try {
					price = new Integer(req.getParameter("price").trim());
				} catch (NumberFormatException e) {
					price = 0;
					errorMsgs.add("價格請填數字.");
				}
				Integer is_item = null;
				try {
					is_item = new Integer(req.getParameter("is_item").trim());
				} catch (NumberFormatException e) {
					is_item = 0;
					errorMsgs.add("品項狀態請填數字(1: 項目隱藏, 0: 項目不隱藏)");
				}

				MenuVO menuVO = new MenuVO();
				menuVO.setMenu_id(menu_id);
				menuVO.setItem(item);
				menuVO.setPrice(price);
				menuVO.setIs_item(is_item);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("menuVO", menuVO); // 含有輸入格式錯誤的menuVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/menu/update_menu_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				MenuService menuService = new MenuService();
				menuVO = menuService.updateMenuItem(menu_id, item, price, is_item);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("menuVO", menuVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/menu/listMenuByShop.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/menu/update_menu_input.jsp");
				failureView.forward(req, res);
			}
		}

	}

}
