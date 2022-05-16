package com.announcement.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.announcement.model.AnnouncementService;
import com.announcement.model.AnnouncementVO;
import com.announcement_mapping.model.Announcement_mappingService;
import com.announcement_mapping.model.Announcement_mappingVO;
import com.emp.model.EmpService;
import com.emp.model.EmpVO;

/**
 * Servlet implementation class AnnouncementServlet
 */
@MultipartConfig
@WebServlet("/announcementServlet")
public class AnnouncementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnnouncementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
	doPost(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
	
		String action = req.getParameter("action");
HttpSession session=req.getSession();
Map<String, String> errorMap=new LinkedHashMap<String, String>();
System.out.println(action);
if("insert".equals(action)) {
	String announcerString=req.getParameter("announcer");
	if(announcerString==null||announcerString.trim().length()==0 ) {
		errorMap.put("announcer", "請輸入公告建立者");
		
	}
	

	String announcement_title=req.getParameter("announcement_title");
	if(announcement_title==null||announcement_title.trim().length()==0 ) {
		errorMap.put("announcer", "請輸入公告標題");
		
	}
	String announcement_content=req.getParameter("announcement_content");
	if(announcement_content==null||announcement_content.trim().length()==0 ) {
		errorMap.put("announcer", "請輸入公告內容");
		
	}
	
	if(!errorMap.isEmpty()) {
		RequestDispatcher failureView = req
				.getRequestDispatcher("/announcement/addannouncement.jsp");
		failureView.forward(req, res);
		return;//程式中斷
		
	}
	String a=req.getParameter("test");
	System.out.println("1234"+a);
	
	AnnouncementVO announcementVO=new AnnouncementVO();
	Integer announcer=Integer.valueOf(announcerString);
	announcementVO.setAnnouncer(announcer);
	announcementVO.setAnnouncement_title(announcement_title);
	announcementVO.setAnnouncement_content(announcement_content);

	
	AnnouncementService annSvc=new AnnouncementService();
	List<Announcement_mappingVO> list=new ArrayList<Announcement_mappingVO>();

	
	Integer quantity=Integer.valueOf(req.getParameter("quantity")) ;
	if(quantity>0) {
		for(int i=1;i<=quantity;i++) {
		String name=String.valueOf(i);
		Part part=req.getPart(name);
		 byte[] bytes= getByteArrayFromPart(part);
		 Announcement_mappingVO announcement_mappingVO=new Announcement_mappingVO();
		 announcement_mappingVO.setAnnouncementImg(bytes);
	list.add(announcement_mappingVO);
			
		}
		
	
	}
	else {
		annSvc.insert(announcementVO);
		
		RequestDispatcher successView = req.getRequestDispatcher("/announcement/addannouncement.jsp"); 
		successView.forward(req, res);
		return;
	}
	
	
	annSvc.insertWithImg(announcementVO, list);
	
	
	
	
	RequestDispatcher successView = req.getRequestDispatcher("/announcement/addannouncement.jsp"); 
	successView.forward(req, res);
	return;
	
	
	
	
	
	
	
	
	
	

 

	
	
	
	
	
	
	
	
	
	
	
	
	
}

if("getOne".equals(action)) {
	
	Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
	req.setAttribute("errorMsgs", errorMsgs);
	
	
		/***************************1.接收請求參數****************************************/
		Integer announcement_id = Integer.valueOf(req.getParameter("announcement_id"));
		
		/***************************2.開始查詢資料****************************************/
		AnnouncementService annSvc=new AnnouncementService();
		AnnouncementVO announcementVO=annSvc.findByPrimaryKey(announcement_id);
	EmpService empService=new EmpService();
	
						
		/***************************3.查詢完成,準備轉交(Send the Success view)************/
		String param = "?announcement_id="  +announcementVO.getAnnouncement_id()+
				 "&announcer="+  announcementVO.getAnnouncer()+
				 "&announcer_name="  +empService.getOneEmp(announcementVO.getAnnouncer()).getEmpName()+
				       "&announcement_title="  +announcementVO.getAnnouncement_title()+
				       "&announcement_content="+announcementVO.getAnnouncement_content()+
				       "&announcement_status="+announcementVO.getAnnouncement_status();;
				    
				     
		String url = "/announcement/frontAnnouncement.jsp"+param;
		RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
		successView.forward(req, res);
	
	
	
	
	
	
	
}


if("getOne_For_Update".equals(action)) {
	
	Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
	req.setAttribute("errorMsgs", errorMsgs);
	
	
		/***************************1.接收請求參數****************************************/
		Integer announcement_id = Integer.valueOf(req.getParameter("announcement_id"));
		
		/***************************2.開始查詢資料****************************************/
		AnnouncementService annSvc=new AnnouncementService();
		AnnouncementVO announcementVO=annSvc.findByPrimaryKey(announcement_id);
	EmpService empService=new EmpService();
	
						
		/***************************3.查詢完成,準備轉交(Send the Success view)************/
		String param = "?announcement_id="  +announcementVO.getAnnouncement_id()+
				 "&announcer="+  announcementVO.getAnnouncer()+
				 "&announcer_name="  +empService.getOneEmp(announcementVO.getAnnouncer()).getEmpName()+
				       "&announcement_title="  +announcementVO.getAnnouncement_title()+
				       "&announcement_content="+announcementVO.getAnnouncement_content()+
				       "&announcement_status="+announcementVO.getAnnouncement_status();
				     
		String url = "/announcement/updateAnnouncement.jsp"+param;
		RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
		successView.forward(req, res);
	
	
	
	
	
	
	
}

if("update".equals(action)) {
	Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
	req.setAttribute("errorMsgs", errorMsgs);
	Integer announcement_id = Integer.valueOf(req.getParameter("announcement_id"));
	
	/***************************2.開始查詢資料****************************************/
	AnnouncementService annSvc=new AnnouncementService();
	

	String announcement_title=req.getParameter("announcement_title");
	if(announcement_title==null||announcement_title.trim().length()==0 ) {
		errorMap.put("announcer", "請輸入公告標題");
		
	}
	String announcement_content=req.getParameter("announcement_content");
	if(announcement_content==null||announcement_content.trim().length()==0 ) {
		errorMap.put("announcer", "請輸入公告內容");
		
	}
	
	
	if(!errorMap.isEmpty()) {
		RequestDispatcher failureView = req
				.getRequestDispatcher("/back/backmain.jsp");
		failureView.forward(req, res);
		return;//程式中斷
		
	}
	
	
	Integer oldquantity= Integer.valueOf(req.getParameter("oldquantity"));
	Integer quantity= Integer.valueOf(req.getParameter("quantity"));
	Integer totalImg=oldquantity+quantity;
	Announcement_mappingService annmapSvc=new Announcement_mappingService();

	AnnouncementVO announcementVO=new AnnouncementVO();
	
	announcementVO.setAnnouncement_status(Byte.valueOf(req.getParameter("announcement_status")));
	announcementVO.setAnnouncement_content(announcement_content);
	announcementVO.setAnnouncement_title(announcement_title);
	announcementVO.setAnnouncement_id(announcement_id);
	annSvc.update(announcementVO);
	
String[] imgNumArr=req.getParameterValues("$");

if(imgNumArr==null ||imgNumArr.length==0) {}
else {
	

	for(int i=0;i<imgNumArr.length;i++) {
		
		String filePoint=imgNumArr[i]+"$";
		Integer imgNum=Integer.valueOf(imgNumArr[i]);
		Announcement_mappingVO announcement_mappingVO= new Announcement_mappingVO();
		announcement_mappingVO.setAnnouncement_id(announcement_id);
		announcement_mappingVO.setAnnouncementImg_id(imgNum);
		if(req.getPart(filePoint).getSize()!=0) {
			announcement_mappingVO.setAnnouncementImg(getByteArrayFromPart(req.getPart(filePoint)));
			}
			annmapSvc.update(announcement_mappingVO);
	}
//	             List<Integer> list=(List<Integer>) req.getAttribute("list");
//	             System.out.println(list.size());
	             
//	             Iterator<Integer> iterator =list.iterator();
//	             while (iterator.hasNext()) {
//					Integer integer = (Integer) iterator.next();
//					String a=String.valueOf(integer);
//					System.out.println(a);
//					String set=a+"$";
//					
//					
//				}
}            
	
//oldquantity=oldquantity+10;
//for(int i=11;i<=oldquantity;i++) {
//	String aString=String.valueOf(i);
//	Integer imgId=i-10;
//	Announcement_mappingVO announcement_mappingVO= new Announcement_mappingVO();
//	announcement_mappingVO.setAnnouncement_id(announcement_id);
//	announcement_mappingVO.setAnnouncementImg_id(imgId);
//	
//	if(req.getPart(aString).getSize()!=0) {
//	announcement_mappingVO.setAnnouncementImg(getByteArrayFromPart(req.getPart(aString)));
//	}
//	annmapSvc.update(announcement_mappingVO);
//	
//	
//	
//	
//}
        for(int i=1;i<=quantity;i++) {
        	Announcement_mappingVO announcement_mappingVO= new Announcement_mappingVO();
        	announcement_mappingVO.setAnnouncement_id(announcement_id);
        	String aString=String.valueOf(i);
        	announcement_mappingVO.setAnnouncementImg(getByteArrayFromPart(req.getPart(aString)));
        	
        	annmapSvc.insert(announcement_mappingVO);
        } 
	

    	
    	RequestDispatcher successView = req.getRequestDispatcher("/announcement/addannouncement.jsp"); 
    	successView.forward(req, res);
    	return;




	
	
	
	
	
	
	
	
	
}

if("deleteImg".equals(action)) {

	
	Integer announcement_id = Integer.valueOf(req.getParameter("announcement_id"));
	
	Integer announcementImg_id = Integer.valueOf(req.getParameter("announcementImg_id"));
	
	/***************************2.開始查詢資料****************************************/
	Announcement_mappingService annmapSvc=new Announcement_mappingService();
	annmapSvc.delete(announcement_id, announcementImg_id);
	



	
	

    	
    	RequestDispatcher successView = req.getRequestDispatcher("/announcement/addannouncement.jsp"); 
    	successView.forward(req, res);
    	return;




	
	
	
	
	
	
	
	
	
}










		
		
		
	}
	public static byte[] uploadImage(Part part) throws IOException {
		InputStream ins= part.getInputStream();
		byte[] b = new byte[ins.available()];
		
		return b;
	}

	public static String getFileNameFromPart(Part part) {
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
