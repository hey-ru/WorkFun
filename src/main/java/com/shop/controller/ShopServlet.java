package com.shop.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.groupbuylist.model.GroupBuyListService;
import com.menu.model.MenuService;
import com.secondHand.model.SecondHandService;
import com.secondHand.model.SecondHandVO;
import com.shop.model.*;

import lombok.val;


@WebServlet("/shop/ShopServlet")
@MultipartConfig
public class ShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		res.setContentType("text/html; charset=UTF-8");
		
		//練習用 
		if ("getOne_For_Display".equals(action)) { 

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("shop_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.put("shop_id","請輸入店家編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/shop/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer shop_id = null;
				try {
					shop_id = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.put("shop_id","店家編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/shop/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ShopService shopSvc = new ShopService();
				ShopVO shopVO = shopSvc.getOneShop(shop_id);
				if (shopVO == null) {
					errorMsgs.put("shop_id","查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/shop/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("shopVO", shopVO); // 資料庫取出的empVO物件,存入req
				String url = "/shop/listOneShop.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
		}
		
		// 編輯  來自listAllShop.jsp listOneShop.jsp的請求
		if ("getOne_For_Update".equals(action)) { 

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer shop_id = Integer.valueOf(req.getParameter("shop_id"));
				
				/***************************2.開始查詢資料****************************************/
				ShopService shopSvc = new ShopService();
				ShopVO shopVO = shopSvc.getOneShop(shop_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				String param = "?shop_id="  +shopVO.getShop_id()+
						       "&shop_name="  +shopVO.getShop_name()+
						       "&shop_type=" +shopVO.getShop_type()+
						       "&address="+shopVO.getAddress()+
						       "&tel="    +shopVO.getTel()+
						       "&website="   +shopVO.getWebsite()+
						       "&min_amt=" +shopVO.getMin_amt()+
						       "&shop_img1=" +shopVO.getShop_img1()+						       
						       "&shop_img2=" +shopVO.getShop_img2()+
						       "&shop_img3=" +shopVO.getShop_img3();
				String url = "/shop/update_shop_input.jsp"+param;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
		}
		
		//送出編輯 update_shop_input.jsp
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer shop_id = Integer.valueOf(req.getParameter("shop_id").trim());
				
				String shop_name = req.getParameter("shop_name");
				String shop_nameReg ="^[(\u4e00-\u9fa5)(\u0800-\u4e00)a-zA-Z0-9_\\s\\(\\-\\)]*$";
				if (shop_name == null || shop_name.trim().length() == 0) {
					errorMsgs.put("shop_name","店家名稱: 請勿空白");
				} else if(!shop_name.trim().matches(shop_nameReg)) {
					errorMsgs.put("shop_name","店家名稱:只能是中日英文字母、數字_-和()符號");
	            }
				
				Integer shop_type = Integer.valueOf(req.getParameter("shop_type").trim());
				//(0: 飲料, 1: 中式 2: 異國, 3: 小吃, 4: 素食 5:其他 )
				
				String placecode = req.getParameter("placecode").trim();
				String city = req.getParameter("city");
				String dist = req.getParameter("dist");
				String addressend = req.getParameter("addressend");
				String addressReg ="^[(\u4e00-\u9fa5)a-zA-Z0-9_\\s\\-]*$";
				if ((addressend.trim().length() != 0) && !(addressend.trim().matches(addressReg))) { 
					errorMsgs.put("address","地址: 只能是中、英文字母、數字和_-");
	            }
				String address = "";
				if (addressend.trim().length() != 0) {
					String addressend1 = addressend.replace(city, "");
					String addressend2 = addressend1.replace(dist, "");				
					address = placecode+city+dist+addressend2;
				}
				
				String tel = req.getParameter("tel");
				String telReg = "(09\\d{2}-?(\\d{3}-?\\d{3}))|((\\d{2,3}|\\(\\d{2,3}\\))-?\\d{3,4}-?\\d{3,4})";
				if (tel == null || tel.trim().length() == 0) {
					errorMsgs.put("tel","電話:請至少留一個電話或手機號碼");
				} else if(!tel.trim().matches(telReg)) { 
					errorMsgs.put("tel","電話:請再確認一下號碼");
	            }
				
				String website = req.getParameter("website");
				
				Integer min_amt = 0;
				String min_amtstr = req.getParameter("min_amt").trim();
				if(min_amtstr.trim().length() != 0) {
					try {
						min_amt = Integer.valueOf(min_amtstr);
					} catch (NumberFormatException e) {
						errorMsgs.put("min_amt","外送低消請填數字");
					}
				}
				
				ShopVO oldshopVO = new ShopService().getOneShop(shop_id);
							
				byte[] shop_img1 = oldshopVO.getShop_img1();
				byte[] shop_img2 = oldshopVO.getShop_img2();
				byte[] shop_img3 = oldshopVO.getShop_img3();
				
				Part pic1 = req.getPart("shop_img1");
				String filename1 = getFileNameFromPart(pic1);
					if (filename1!= null && pic1.getContentType()!=null) {
						shop_img1 = getByteArrayFromPart(pic1);
					}
				
		        Part pic2 = req.getPart("shop_img2");
				String filename2 = getFileNameFromPart(pic2);
					if (filename2!= null && pic2.getContentType()!=null) {
						shop_img2 = getByteArrayFromPart(pic2);
					}
				
				Part pic3 = req.getPart("shop_img3");
				String filename3 = getFileNameFromPart(pic3);
					if (filename3!= null && pic3.getContentType()!=null) {
						shop_img3 = getByteArrayFromPart(pic3);
					}
							
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/shop/update_shop_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ShopService shopSvc = new ShopService();
				ShopVO shopVO = shopSvc.updateShop(shop_id, shop_name, shop_type, address, tel, website,
						min_amt, shop_img1, shop_img2, shop_img3);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("shopVO", shopVO); // 資料庫update成功後,正確的的shopVO物件,存入req
				String url = "/shop/listOneShop.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneShop.jsp
				successView.forward(req, res);

		}
		
		//新增店家
        if ("insert".equals(action)) {   
			
        	Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
	
				String shop_name = req.getParameter("shop_name");
				String shop_nameReg ="^[(\u4e00-\u9fa5)(\u0800-\u4e00)a-zA-Z0-9_\\s\\(\\-\\)]*$";
				if (shop_name == null || shop_name.trim().length() == 0) {
					errorMsgs.put("shop_name","店家名稱: 請勿空白");
				} else if(!shop_name.trim().matches(shop_nameReg)) {
					errorMsgs.put("shop_name","店家名稱:只能是中日英文字母、數字_-和()符號");
	            }
				
				Integer shop_type = Integer.valueOf(req.getParameter("shop_type").trim());
				//(0: 飲料, 1: 中式 2: 異國, 3: 小吃, 4: 素食 5:其他 )
				
				String placecode = req.getParameter("placecode").trim();
				String city = req.getParameter("city");
				String dist = req.getParameter("dist");
				String addressend = req.getParameter("address").trim();
				String addressReg ="^[(\u4e00-\u9fa5)a-zA-Z0-9_\\s\\-]*$";
				if ((addressend.trim().length() != 0) && !(addressend.trim().matches(addressReg))) { 
					errorMsgs.put("address","地址: 只能是中、英文字母、數字和_-");
	            }
				String address = "";
				if (addressend.trim().length() != 0) {
				address = placecode+city+dist+addressend;
				}
				
				String tel = req.getParameter("tel");
				String telReg = "(09\\d{2}-?(\\d{3}-?\\d{3}))|((\\d{2,3}|\\(\\d{2,3}\\))-?\\d{3,4}-?\\d{3,4})";
				if (tel == null || tel.trim().length() == 0) {
					errorMsgs.put("tel","電話:請至少留一個電話或手機號碼");
				} else if(!tel.trim().matches(telReg)) { 
					errorMsgs.put("tel","電話:請再確認一下號碼");
	            }
				
				String website = req.getParameter("website");
				
				Integer min_amt = 0;
				String min_amtstr = req.getParameter("min_amt").trim();
				if(min_amtstr.trim().length() != 0) {
					try {
						min_amt = Integer.valueOf(min_amtstr);
					} catch (NumberFormatException e) {
						errorMsgs.put("min_amt","外送低消請填數字");
					}
				}
				
				byte[] shop_img1 = null;
				byte[] shop_img2 = null;
				byte[] shop_img3 = null;
				
				Part pic1 = req.getPart("shop_img1");
				String filename1 = getFileNameFromPart(pic1);
					if (filename1!= null && pic1.getContentType()!=null) {
						shop_img1 = getByteArrayFromPart(pic1);
					}
				
		        Part pic2 = req.getPart("shop_img2");
				String filename2 = getFileNameFromPart(pic2);
					if (filename2!= null && pic2.getContentType()!=null) {
						shop_img2 = getByteArrayFromPart(pic2);
					}
				
				Part pic3 = req.getPart("shop_img3");
				String filename3 = getFileNameFromPart(pic3);
					if (filename3!= null && pic3.getContentType()!=null) {
						shop_img3 = getByteArrayFromPart(pic3);
					}
							

				ShopVO shopVO = new ShopVO();
				shopVO.setShop_name(shop_name);
				shopVO.setShop_type(shop_type);
				shopVO.setAddress(address);
				shopVO.setTel(tel);
				shopVO.setWebsite(website);
				shopVO.setMin_amt(min_amt);
				shopVO.setShop_img1(shop_img1);
				shopVO.setShop_img2(shop_img2);
				shopVO.setShop_img3(shop_img3);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/shop/addShop.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ShopService shopSvc = new ShopService();
				Integer shop_id = shopSvc.addShop(shop_name, shop_type, address, tel, website,
						min_amt, shop_img1, shop_img2, shop_img3);
				
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				
				String url = "/menu/addMenu.jsp?shop_id="+ shop_id;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交AddMenu.jsp
				successView.forward(req, res);				
				
}
        
        	//複合查詢listAllShop
        	if ("listByCompositeQuery".equals(action)) { 
    			List<String> errorMsgs = new LinkedList<String>();
    			// Store this set in the request scope, in case we need to
    			// send the ErrorPage view.
    			req.setAttribute("errorMsgs", errorMsgs);

    				
    				/***************************1.將輸入資料轉為Map**********************************/ 
    				//採用Map<String,String[]> getParameterMap()的方法 
    				//注意:an immutable java.util.Map 
    				//Map<String, String[]> map = req.getParameterMap();
    			
    				HttpSession session = req.getSession();
    				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
    				
    				// 以下的 if 區塊只對第一次執行時有效
    				if (req.getParameter("whichPage") == null){
    					Map<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
    					session.setAttribute("map",map1);
    					map = map1;
    				} 
    				
    				/***************************2.開始複合查詢***************************************/
    				ShopService shopSvc = new ShopService();
    				List<ShopVO> list  = shopSvc.getAll(map);
    				
    				/***************************3.查詢完成,準備轉交(Send the Success view)************/
    				req.setAttribute("listByCompositeQuery", list); // 資料庫取出的list物件,存入request
//    				session.setAttribute("listByCompositeQuery", list);
    				RequestDispatcher successView = req.getRequestDispatcher("/shop/listShopCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
    				successView.forward(req, res);
    		}
        	
        	//複合查詢listAllShop
        	if ("listByCompositeQueryBack".equals(action)) { 
    			List<String> errorMsgs = new LinkedList<String>();
    			// Store this set in the request scope, in case we need to
    			// send the ErrorPage view.
    			req.setAttribute("errorMsgs", errorMsgs);

    				
    				/***************************1.將輸入資料轉為Map**********************************/ 
    				//採用Map<String,String[]> getParameterMap()的方法 
    				//注意:an immutable java.util.Map 
    				//Map<String, String[]> map = req.getParameterMap();
    			
    				HttpSession session = req.getSession();
    				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
    				
    				// 以下的 if 區塊只對第一次執行時有效
    				if (req.getParameter("whichPage") == null){
    					Map<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
    					session.setAttribute("map",map1);
    					map = map1;
    				} 
    				
    				/***************************2.開始複合查詢***************************************/
    				ShopService shopSvc = new ShopService();
    				List<ShopVO> list  = shopSvc.getAll(map);
    				System.out.println(list.toString());
    				
    				/***************************3.查詢完成,準備轉交(Send the Success view)************/
    				req.setAttribute("listByCompositeQuery", list); // 資料庫取出的list物件,存入request
    				RequestDispatcher successView = req.getRequestDispatcher("/shop/backmain_ShopCQ.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
    				successView.forward(req, res);
    		}
		
//        	[後台]修改店家狀態:上下架
        	if("updateShopStatus".equals(action)) {
    			
    			/*************************** 1.接收請求參數 ****************************************/
        		Integer is_disable = Integer.valueOf(req.getParameter("is_disable"));
    			Integer shop_id = Integer.valueOf(req.getParameter("shop_id"));
    			
    			/***************************2.開始變更資料***************************************/
    			ShopService shopSvc = new ShopService();
    			shopSvc.updateShopStatus(is_disable, shop_id);
    			System.out.println("完成上下架");
    			
    			/***************************3.變更完成,準備轉交(Send the Success view)***********/
    			RequestDispatcher successView = req.getRequestDispatcher("/shop/backmain_Shop.jsp");// 刪除成功後,轉交回送出刪除的來源網頁
    			successView.forward(req, res);
    			
    		}
	
	}

	
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		//System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		//System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
	public static byte[] getByteArrayFromPart(Part part) throws IOException {
		InputStream in = part.getInputStream();
		byte[] buffer = new byte[in.available()];
		in.read(buffer);
		in.close();
		return buffer;
	}

}
