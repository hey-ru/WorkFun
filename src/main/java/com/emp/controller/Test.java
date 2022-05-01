package com.emp.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.EmpService;

/**
 * Servlet implementation class Test
 */
@WebServlet("/TestServelt")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	doPost(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
        // client 端向 Servlet 請求的編碼
	req.setCharacterEncoding("UTF-8");

	// 錯誤訊息用 Map 存放
        Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
	req.setAttribute("errorMsgs", errorMsgs);
        
	// 建立 service 的實體
	EmpService empSvc = new EmpService();
    
        // 宣告一個布林值
        Boolean boo;
		
        // response，設定回應的格式及編碼
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        
    /*************************** 判斷帳號是否存在 **********************/
        String accountRegister = req.getParameter("accountRegister");
        // 判斷 accountRegister 是否有值，有值再做判斷
        if (accountRegister == null || accountRegister.trim().length() == 0) {
	    errorMsgs.put("exist", "accountRegister使用者未輸入值");
            String json = new Gson().toJson(errorMsgs);
            res.getWriter().write(json);
	}else {
	    errorMsgs.put("exist", "accountRegister使用者有輸入值，所以要過 service 進入資料庫判斷有無此帳號");
			
            // 呼叫 service，第 37 行有宣告 service 實體，
            // 我的 getOneByAccount 的回傳值為 Boolean，所以用 Boolean 接，在第 40 行有宣告一個 Boolean
            boo = memberSvc.getOneByAccount(accountRegister);
			
            if(boo == true) {
	        errorMsgs.put("exist", "表示資料庫有此帳號");
	        String json = new Gson().toJson(errorMsgs);
                res.getWriter().write(json);
            }else {
	        errorMsgs.put("exist", "表示使用者輸入錯誤，資料庫查無資訊");
	        String json = new Gson().toJson(errorMsgs);
                res.getWriter().write(json);
            }
        }
    }
}
