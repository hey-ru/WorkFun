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

@WebServlet("/menu/disablebyshopservlet")
public class DisableByShopServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);

		// 以店家搜尋菜單(下架)
		if ("getmenu_disable".equals(action)) { // 來自selectMenu_page.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/*************************** 1.接收請求參數 ****************************************/
				Integer shop_id = Integer.valueOf(req.getParameter("shop_id"));
				/*************************** 2.開始查詢資料 *****************************************/
				MenuService menuService = new MenuService();
				List<MenuVO> menuListDisable = menuService.getByShopIdDisable(shop_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				// 資料庫取出的menuVO物件集合,存入req
				HttpSession session = req.getSession();
				session.setAttribute("menuListDisable", menuListDisable);
				System.out.println(menuListDisable.toString());
				String url = "/back/backmain_Shop_DisableMenu.jsp";
				// 成功轉交
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("點擊修改,成功轉交");
		}
		
		// 後台變更狀態(由下架改成上架)
				if ("changeStatus".equals(action)) { // 來自selectMenu_page.jsp的請求

					Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
					req.setAttribute("errorMsgs", errorMsgs);

						/*************************** 1.接收請求參數 ****************************************/
						Integer menu_id = Integer.valueOf(req.getParameter("menu_id"));
						Integer shop_id = Integer.valueOf(req.getParameter("shop_id"));

						/*************************** 2.開始修改資料 *****************************************/
						MenuService menuService = new MenuService();
						MenuVO menu = menuService.updateStatus(menu_id, shop_id);

						/*************************** 3.變更狀態完成,準備轉交(Send the Success view) ************/
						// 資料庫取出的menuVO物件集合,存入req
						req.setAttribute("menu", menu);
						// 再次取得下架清單
						List<MenuVO> menuListDisable = menuService.getByShopIdDisable(shop_id);
						req.setAttribute("menuListDisable", menuListDisable);
						
						String url = "/back/backmain_Shop_DisableMenu.jsp";
						// 成功轉交
						RequestDispatcher successView = req.getRequestDispatcher(url);
						successView.forward(req, res);
				}
	

	}

}
