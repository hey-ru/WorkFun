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
import com.shop.model.ShopService;
import com.shop.model.ShopVO;

@WebServlet("/menu/selectmenubyshop")
public class SelectMenuByShopServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);

		// 以店家搜尋菜單(上架), Session
		if ("getmenu".equals(action)) { // 來自selectMenu_page.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/*************************** 1.接收請求參數 ****************************************/
				Integer shop_id = Integer.valueOf(req.getParameter("shop_id"));

				/*************************** 2.開始查詢資料 *****************************************/
				MenuService menuService = new MenuService();
				List<MenuVO> menuList = menuService.getByShopId(shop_id);
				
				ShopService shopService = new ShopService();
				ShopVO shopVO =shopService.getOneShop(shop_id);
				System.out.println(shopVO.toString());
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				// 資料庫取出的menuVO物件集合,存入session(修改或新增回上一頁可取得)
				HttpSession session = req.getSession();
				session.setAttribute("menuList", menuList);
				session.setAttribute("shopVO", shopVO);

				
				String url = "/menu/listMenuByShop.jsp";
				// 成功轉交
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
		}

	}

}
