package com.groupbuylist.controller;

import java.io.IOException;
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

		if ("insert2GBlist".equals(action)) { // 來自buyer_joinGB.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			// 備註例外處理
//				String[] remark = req.getParameterValues("remark");
//				String remarkReg ="^[(\u4e00-\u9fa5)(\u0800-\u4e00)a-zA-Z0-9_\\(\\-\\)]*$";
//				if (!remark.split.trim.matches(remarkReg)) { // 正則(規)表示式(regular-expression)
//					errorMsgs.put("remark", "備註: 只能是中、日、英文字母、數字、_、-和()");
//				}

			GroupBuyListVO groupBuyListVO;
			List<GroupBuyListVO> orderlist = new ArrayList<>();

			String[] gb_id = req.getParameterValues("gb_id");
			String[] buyer = req.getParameterValues("buyer");
			String[] buyer_name = req.getParameterValues("buyer_name");
			String[] menu_id = req.getParameterValues("menu_id");
			String[] item = req.getParameterValues("item");
			String[] price = req.getParameterValues("price");
			String[] qty = req.getParameterValues("qty");
			String[] remark = req.getParameterValues("remark");

			for (int i = 0; i < gb_id.length; i++) {
					if (!qty[i].equals("0")) {
						groupBuyListVO = new GroupBuyListVO();
						groupBuyListVO.setGb_id(Integer.valueOf(gb_id[i]));
						groupBuyListVO.setBuyer(Integer.valueOf(buyer[i]));
						groupBuyListVO.setBuyer_name(buyer_name[i]);
						groupBuyListVO.setMenu_id(Integer.valueOf(menu_id[i]));
						groupBuyListVO.setItem(item[i]);
						groupBuyListVO.setPrice(Integer.valueOf(price[i]));
						groupBuyListVO.setQty(Integer.valueOf(qty[i]));
						groupBuyListVO.setRemark(remark[i]);

						orderlist.add(groupBuyListVO);
				}
					System.out.println(!qty[i].equals("0"));
					System.out.println(qty[i]);
			}

//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/groupbuylist/buyer_joinGB.jsp");
//					failureView.forward(req, res);
//					return; //程式中斷
//				}

			/***************************
			 * 2-2.開始新增資料
			 *****************************************/
			GroupBuyListService gblistSvc = new GroupBuyListService();
			gblistSvc.insertMany(orderlist);

			System.out.println(orderlist.toString());
			System.out.println("新增完成");

			/*************************** 3.新增完成,準備轉交(Send the Success view) *************/
			String url = "/groupbuy/gbHome.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listMenuByShop.jsp
			successView.forward(req, res);
			System.out.println(gb_id + "參團新增成功!");
		}

	}

}
