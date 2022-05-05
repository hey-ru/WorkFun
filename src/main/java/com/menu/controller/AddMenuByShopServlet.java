package com.menu.controller;

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

import com.menu.model.MenuService;
import com.menu.model.MenuVO;

@WebServlet("/menu/addmenubyshop")
public class AddMenuByShopServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);
		
		// 在該店家新增菜單
		//join shop
		if ("getShop_For_AddMenu".equals(action)) { // 來自listMenuByShop.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/*************************** 1.取得店家參數, 以對該店家新增菜單***********************/
			Integer shop_id = Integer.valueOf(req.getParameter("shop_id"));
			System.out.println("增加菜單的店家id: " + shop_id);

			/*************************** 2.轉交給新增畫面 *****************************************/
			req.setAttribute("shop_id", shop_id);
			String url = "/menu/addMenu.jsp";
			
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			System.out.println("跳至新增畫面");
		}
		
		
		if ("insert".equals(action)) { // 來自listmenubyshop.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				//品項
				String item = req.getParameter("item");
				String itemReg ="^[(\u4e00-\u9fa5)(\u0800-\u4e00)a-zA-Z0-9_\\(\\-\\)]*$";
				if (item == null || item.trim().length() == 0) {
					errorMsgs.put("item", "品項: 請勿空白");
				} else if (!item.trim().matches(itemReg)) { // 正則(規)表示式(regular-expression)
					errorMsgs.put("item", "品項: 只能是中、日、英文字母、數字、_、-和()");
				}
				//價格
				Integer price = null;
				try {
					price = Integer.valueOf(req.getParameter("price").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("price", "價格請填數字");
				}
				
				// 店家編號FK shop_id
				Integer shop_id = Integer.valueOf(req.getParameter("shop_id"));
				System.out.println(shop_id);
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/menu/addMenu.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}

				/*************************** 2.開始新增資料 *****************************************/
				MenuService menuService = new MenuService();
				menuService.addMenuItem(item, price, shop_id);
				System.out.println("新增的值: " + item +  price + shop_id);
				
				/*************************** 3.新增完成,準備轉交(Send the Success view) *************/
				//再取得一次店家菜單物件集合回應,以顯示於店家菜單畫面
				List<MenuVO> menuList = menuService.getByShopId(shop_id);
				req.setAttribute("menuList", menuList);
				req.setAttribute("shop_id", shop_id);
				
				String url = "/menu/listMenuByShop.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listMenuByShop.jsp
				successView.forward(req, res);
				System.out.println(shop_id + "店家菜單新增成功!");
		}

	}

}
