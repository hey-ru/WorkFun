package com.groupbuylist.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.shop.model.ShopService;
import com.shop.model.ShopVO;

@WebServlet("/groupbuylist/selectmygblistservlet")
public class SelectMyGBListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=utf-8");
		String action = req.getParameter("action");
		System.out.println(action);

// 來自buyer_selectGB.jsp的請求
		
//		(1)查看個人參團明細
		if ("get_buyerlist".equals(action)) { 

			/*************************** 1.接收請求參數 ****************************************/
			Integer buyer = Integer.valueOf(req.getParameter("buyer"));
			Integer gb_id = Integer.valueOf(req.getParameter("gb_id"));
			System.out.println(buyer + gb_id);
			
			/***************************2.開始查詢個人訂單資料***************************************/
			GroupBuyListService gbListSvc = new GroupBuyListService();
			List<GroupBuyListVO> list  = gbListSvc.getOne(buyer, gb_id);
			
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			HttpSession session = req.getSession();
			session.setAttribute("buyerlist", list);
			System.out.println(list.toString());
			
			String url = "/groupbuylist/buyer_selectOneGB.jsp";
			// 成功轉交
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
//		(2)退出揪團
		if("deleteMyGb".equals(action)) {
			
			/*************************** 1.接收請求參數 ****************************************/
			Integer buyer = Integer.valueOf(req.getParameter("buyer"));
			Integer gb_id = Integer.valueOf(req.getParameter("gb_id"));
			
			/***************************2.開始刪除資料***************************************/
			GroupBuyListService gbListSvc = new GroupBuyListService();
			gbListSvc.deleteMyGb(buyer, gb_id);
			System.out.println("完成刪除");
			
			/***************************3.刪除完成,準備轉交(Send the Success view)***********/
			PrintWriter out = res.getWriter();
			out.print("<script type='text/javascript'>alert('取消成功!');</script>");
			
			RequestDispatcher successView = req.getRequestDispatcher("/groupbuylist/buyer_selectGB.jsp");// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
			
		}
		
//		(3)編輯參團-先查詢個人訂單明細
		if("updateMyGb".equals(action)) {
			
			/*************************** 1.接收請求參數 ****************************************/
			Integer buyer = Integer.valueOf(req.getParameter("buyer"));
			Integer gb_id = Integer.valueOf(req.getParameter("gb_id"));
			
			/***************************2.開始查詢個人訂單資料***************************************/
			GroupBuyListService gbListSvc = new GroupBuyListService();
			List<GroupBuyListVO> list  = gbListSvc.getOne(buyer, gb_id);
			
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ***************/
			req.setAttribute("buyerlist", list);
			
			//跳轉至該訂單畫面
			String url = "/groupbuylist/buyer_updateOneGB.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
//		(3-1)編輯參團-一次更新多筆
		if ("updateMany".equals(action)) { // 來自buyer_joinGB.jsp的請求

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			//取得參數
			String[] gbList_id = req.getParameterValues("gbList_id");
			String[] buyer = req.getParameterValues("buyer");
			String[] qty = req.getParameterValues("qty");
			String[] remark = req.getParameterValues("remark");
			
			GroupBuyListVO oldGBListVO;
			List<GroupBuyListVO> newOrderlist = new ArrayList<>();
			
			for (int i = 0; i < gbList_id.length; i++) {
				GroupBuyListService gbListSvc = new GroupBuyListService();
				oldGBListVO=gbListSvc.findByPrimaryKey(Integer.valueOf(gbList_id[i]));
				
					oldGBListVO.setGbList_id(Integer.valueOf(gbList_id[i]));
					oldGBListVO.setBuyer(Integer.valueOf(buyer[i]));
					oldGBListVO.setQty(Integer.valueOf(qty[i]));
					oldGBListVO.setRemark(remark[i]);

					newOrderlist.add(oldGBListVO);
			}
			
			/*************************** 2.開始新增資料 ***************************/
			GroupBuyListService gblistSvc = new GroupBuyListService();
			gblistSvc.updateMany(newOrderlist);

			System.out.println("newOrderlist"+newOrderlist.toString());
			System.out.println("訂單修改完成");

			/********************* 3.新增完成,準備轉交(Send the Success view) *************/
			String url = "/groupbuylist/buyer_selectGB.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		
		

//		(3)編輯參團-先查詢個人訂單明細
//		if("updateMyGb".equals(action)) {
//			
//			/*************************** 1.接收請求參數 ****************************************/
//			Integer buyer = Integer.valueOf(req.getParameter("buyer"));
//			Integer gb_id = Integer.valueOf(req.getParameter("gb_id"));
//			System.out.println(buyer + gb_id);
//			
//			/***************************2.開始查詢個人訂單資料***************************************/
//			GroupBuyListService gbListSvc = new GroupBuyListService();
//			List<GroupBuyListVO> list  = gbListSvc.getOne(buyer, gb_id);
//			
//			/*************************** 3.查詢完成,準備轉交(Send the Success view) ***************/
//			req.setAttribute("buyerlist", list);
//			System.out.println(list.toString());
//			
//			//跳轉至該訂單畫面
//			String url = "/groupbuylist/buyer_updateOneGB.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
//		}			
		
//		(3-1)編輯參團-針對訂單項目開始修改
//		if("updateItem".equals(action)) {
//		
//			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/***************************1.接收請求參數 - 輸入格式的錯誤處理****************************/
//			Integer buyer = Integer.valueOf(req.getParameter("buyer"));
//			Integer gbList_id = Integer.valueOf(req.getParameter("gbList_id"));
//			Integer gb_id = Integer.valueOf(req.getParameter("gb_id"));
//			
//			//數量
//			Integer qty = Integer.valueOf(req.getParameter("qty").trim());
//			//備註
//			String remark = req.getParameter("remark");
//			String remarkReg ="^[(\u4e00-\u9fa5)(\u0800-\u4e00)a-zA-Z0-9_\\(\\-\\)]*$";
//			if (!remark.trim().matches(remarkReg)) { // 正則(規)表示式(regular-expression)
//				errorMsgs.put("item", "品項名稱: 只能是中、日、英文字母、數字、_、-和()");
//			}
//			
//			/*************************** 2.開始修改資料 *****************************************/
//			GroupBuyListService gbListSvc = new GroupBuyListService();
//			GroupBuyListVO groupBuyListVO = gbListSvc.updateGbItem(qty, remark, buyer, gbList_id);
//			System.out.println(groupBuyListVO.toString());
//			System.out.println("修改完成");
//
//			/***************************3.修改完成,準備轉交(Send the Success view)*************/
//			//回修改畫面
//			List<GroupBuyListVO> list  = gbListSvc.getOne(buyer, gb_id);
//			req.setAttribute("buyerlist", list);
//			String url = "/groupbuylist/buyer_updateOneGB.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
//		}
		
//		(3-2)刪除項目
//		if("deleteItem".equals(action)) {
//			
//			/*************************** 1.接收請求參數 ****************************************/
//			Integer gbList_id = Integer.valueOf(req.getParameter("gbList_id"));
//			
//			/***************************2.開始刪除資料***************************************/
//			GroupBuyListService gbListSvc = new GroupBuyListService();
//			gbListSvc.deleteItem(gbList_id);
//			System.out.println("已刪除項目");
//			
//			/***************************3.刪除完成,準備轉交(Send the Success view)***********/
//			PrintWriter out = res.getWriter();
//			out.print("<script type='text/javascript'>alert('刪除成功!');</script>");
//			
//			RequestDispatcher successView = req.getRequestDispatcher("/groupbuylist/buyer_updateOneGB.jsp");// 刪除成功後,轉交回送出刪除的來源網頁
//			successView.forward(req, res);
//		}
		
		

	}

}
