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
import javax.servlet.http.HttpSession;

import com.menu.model.MenuService;
import com.menu.model.MenuVO;

@WebServlet("/menu/updatemenubyshop")
public class UpdateMenuByShopServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);

		// 修改菜單品項
		if ("getMenuItem_For_Update".equals(action)) { // 來自listMenuByShop.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/*************************** 1.接收請求參數 ****************************************/
				Integer menu_id = Integer.valueOf(req.getParameter("menu_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				MenuService menuService = new MenuService();
				MenuVO menuVO = menuService.getOneMenuItem(menu_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String param = "?menu_id="  +menuVO.getMenu_id()+
					       	   "&item="     +menuVO.getItem()+
					       	   "&price="    +menuVO.getPrice()+
					       	   "&is_item="   +menuVO.getIs_item()+  
					       	   "&shop_id="  +menuVO.getShop_id();
						
				String url = "/menu/update_menu_input.jsp"+param;
				// 成功轉交
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("點擊修改, 參數: " + param);
		}
		

		// 成功轉交後跳頁修改
		if ("update".equals(action)) { // 來自update_menu_input.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				//菜單編號
				Integer menu_id = Integer.valueOf(req.getParameter("menu_id").trim());
				
				// 品項例外處理
				String item = req.getParameter("item");
				String itemReg ="^[(\u4e00-\u9fa5)(\u0800-\u4e00)a-zA-Z0-9_+\\s\\(\\-\\)]*$";
				if (item == null || item.trim().length() == 0) {
					errorMsgs.put("item", "品項請勿空白");
				} else if (!item.trim().matches(itemReg)) { // 正則(規)表示式(regular-expression)
					errorMsgs.put("item", "輸入格式錯誤😵 格式:中、日、英文、數字、空格() + - _");
				}
				
				//價格例外處理
				Integer price = null;
				try {
					price = Integer.valueOf(req.getParameter("price").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("price", "請填入數字");
				}
				if (price == null || price == 0) {
					errorMsgs.put("price", "品項請勿空白");
				}
				if (price <= 0) {
					errorMsgs.put("price", "價格應大於零!");
				}
//				if (price > 1000) {
//					errorMsgs.put("price", "單價過高...請洽詢總務申請上架權限");
//				}
				
				//品項狀態例外處理
				Integer is_item = null;
				try {
					 is_item = Integer.valueOf(req.getParameter("is_item").trim());
				} catch (Exception e) {
					errorMsgs.put("is_item", "品項狀態請選擇下架或上架");
				}
				// 店家編號FK shop_id
				Integer shop_id = Integer.valueOf(req.getParameter("shop_id").trim());

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/menu/update_menu_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				MenuService menuService = new MenuService();
				//執行修改指定的menu_id物件
				MenuVO menuVO = menuService.updateMenuItem(menu_id, item, price, is_item, shop_id);
				req.setAttribute("menuVO", menuVO); // 資料庫update成功後,正確的的menuVO物件,存入req
				System.out.println("新值: "+ menuVO.toString());
				
				//再取得一次店家菜單物件集合,以顯示於店家菜單畫面
				List<MenuVO> menuList = menuService.getByShopId(shop_id);
				req.setAttribute("menuList", menuList);
				
				String url = "/menu/listMenuByShop.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listMenuByShop.jsp
				successView.forward(req, res);
		}
		
	}

}
