package com.announcement.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
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
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		Map<String, String> errorMap = new LinkedHashMap<String, String>();
		System.out.println(action);
		if ("insert".equals(action)) {
			String announcerString = req.getParameter("announcer");
			if (announcerString == null || announcerString.trim().length() == 0) {
				errorMap.put("announcer", "????????????????????????");

			}

			String announcement_title = req.getParameter("announcement_title");
			if (announcement_title == null || announcement_title.trim().length() == 0) {
				errorMap.put("announcer", "?????????????????????");

			}
			String announcement_content = req.getParameter("announcement_content");
			if (announcement_content == null || announcement_content.trim().length() == 0) {
				errorMap.put("announcer", "?????????????????????");

			}

			if (!errorMap.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/announcement/addannouncement.jsp");
				failureView.forward(req, res);
				return;// ????????????

			}
			String a = req.getParameter("test");
			System.out.println("1234" + a);

			AnnouncementVO announcementVO = new AnnouncementVO();
			Integer announcer = Integer.valueOf(announcerString);
			announcementVO.setAnnouncer(announcer);
			announcementVO.setAnnouncement_title(announcement_title);
			announcementVO.setAnnouncement_content(announcement_content);

			AnnouncementService annSvc = new AnnouncementService();
			List<Announcement_mappingVO> list = new ArrayList<Announcement_mappingVO>();

			Integer quantity = Integer.valueOf(req.getParameter("quantity"));
			if (quantity > 0) {
				for (int i = 1; i <= quantity; i++) {
					String name = String.valueOf(i);
					Part part = req.getPart(name);
					byte[] bytes = getByteArrayFromPart(part);
					Announcement_mappingVO announcement_mappingVO = new Announcement_mappingVO();
					announcement_mappingVO.setAnnouncementImg(bytes);
					list.add(announcement_mappingVO);

				}

			} else {
				annSvc.insert(announcementVO);

				RequestDispatcher successView = req.getRequestDispatcher("/announcement/listallannouncement.jsp");
				successView.forward(req, res);
				return;
			}

			annSvc.insertWithImg(announcementVO, list);

			RequestDispatcher successView = req.getRequestDispatcher("/announcement/listallannouncement.jsp");
			successView.forward(req, res);
			return;

		}

		if ("getOne".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.?????????????????? ****************************************/
			Integer announcement_id = Integer.valueOf(req.getParameter("announcement_id"));

			/*************************** 2.?????????????????? ****************************************/
			AnnouncementService annSvc = new AnnouncementService();
			AnnouncementVO announcementVO = annSvc.findByPrimaryKey(announcement_id);
			EmpService empService = new EmpService();

			/*************************** 3.????????????,????????????(Send the Success view) ************/
			String param = "?announcement_id=" + announcementVO.getAnnouncement_id() + "&announcer="
					+ announcementVO.getAnnouncer() + "&announcer_name="
					+ empService.getOneEmp(announcementVO.getAnnouncer()).getEmpName() + "&announcement_title="
					+ announcementVO.getAnnouncement_title() + "&announcement_content="
					+ announcementVO.getAnnouncement_content() + "&announcement_status="
					+ announcementVO.getAnnouncement_status();
			;

			String url = "/announcement/frontAnnouncement.jsp" + param;
			RequestDispatcher successView = req.getRequestDispatcher(url);// ???????????? update_emp_input.jsp
			successView.forward(req, res);

		}

		if ("getOne_For_Update".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.?????????????????? ****************************************/
			Integer announcement_id = Integer.valueOf(req.getParameter("announcement_id"));

			/*************************** 2.?????????????????? ****************************************/
			AnnouncementService annSvc = new AnnouncementService();
			AnnouncementVO announcementVO = annSvc.findByPrimaryKey(announcement_id);
			EmpService empService = new EmpService();

			/*************************** 3.????????????,????????????(Send the Success view) ************/
			String param = "?announcement_id=" + announcementVO.getAnnouncement_id() + "&announcer="
					+ announcementVO.getAnnouncer() + "&announcer_name="
					+ empService.getOneEmp(announcementVO.getAnnouncer()).getEmpName() + "&announcement_title="
					+ announcementVO.getAnnouncement_title() + "&announcement_content="
					+ announcementVO.getAnnouncement_content() + "&announcement_status="
					+ announcementVO.getAnnouncement_status();

			String url = "/announcement/updateAnnouncement.jsp" + param;
			RequestDispatcher successView = req.getRequestDispatcher(url);// ???????????? update_emp_input.jsp
			successView.forward(req, res);

		}

		if ("update".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			Integer announcement_id = Integer.valueOf(req.getParameter("announcement_id"));

			/*************************** 2.?????????????????? ****************************************/
			AnnouncementService annSvc = new AnnouncementService();

			String announcement_title = req.getParameter("announcement_title");
			if (announcement_title == null || announcement_title.trim().length() == 0) {
				errorMap.put("announcer", "?????????????????????");

			}
			String announcement_content = req.getParameter("announcement_content");
			if (announcement_content == null || announcement_content.trim().length() == 0) {
				errorMap.put("announcer", "?????????????????????");

			}

			if (!errorMap.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/announcement/updateAnnouncement.jsp");
				failureView.forward(req, res);
				return;// ????????????

			}

			Integer oldquantity = Integer.valueOf(req.getParameter("oldquantity"));
			Integer quantity = Integer.valueOf(req.getParameter("quantity"));
			Integer totalImg = oldquantity + quantity;
			Announcement_mappingService annmapSvc = new Announcement_mappingService();

			AnnouncementVO announcementVO = new AnnouncementVO();

			announcementVO.setAnnouncement_status(Byte.valueOf(req.getParameter("announcement_status")));
			announcementVO.setAnnouncement_content(announcement_content);
			announcementVO.setAnnouncement_title(announcement_title);
			announcementVO.setAnnouncement_id(announcement_id);
			annSvc.update(announcementVO);

			String[] imgNumArr = req.getParameterValues("$");

			if (imgNumArr == null || imgNumArr.length == 0) {
			} else {

				for (int i = 0; i < imgNumArr.length; i++) {

					String filePoint = imgNumArr[i] + "$";
					Integer imgNum = Integer.valueOf(imgNumArr[i]);
					Announcement_mappingVO announcement_mappingVO = new Announcement_mappingVO();
					announcement_mappingVO.setAnnouncement_id(announcement_id);
					announcement_mappingVO.setAnnouncementImg_id(imgNum);
					if (req.getPart(filePoint).getSize() != 0) {
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
			for (int i = 1; i <= quantity; i++) {
				Announcement_mappingVO announcement_mappingVO = new Announcement_mappingVO();
				announcement_mappingVO.setAnnouncement_id(announcement_id);
				String aString = String.valueOf(i);
				announcement_mappingVO.setAnnouncementImg(getByteArrayFromPart(req.getPart(aString)));

				annmapSvc.insert(announcement_mappingVO);
			}

			RequestDispatcher successView = req.getRequestDispatcher("/announcement/listallannouncement.jsp");
			successView.forward(req, res);
			return;

		}

		if ("deleteImg".equals(action)) {

			Integer announcement_id = Integer.valueOf(req.getParameter("announcement_id"));

			Integer announcementImg_id = Integer.valueOf(req.getParameter("announcementImg_id"));

			/*************************** 2.?????????????????? ****************************************/
			Announcement_mappingService annmapSvc = new Announcement_mappingService();
			annmapSvc.delete(announcement_id, announcementImg_id);

			AnnouncementService annSvc = new AnnouncementService();
			AnnouncementVO announcementVO = annSvc.findByPrimaryKey(announcement_id);
			EmpService empService = new EmpService();

			/*************************** 3.????????????,????????????(Send the Success view) ************/
			String param = "?announcement_id=" + announcementVO.getAnnouncement_id() + "&announcer="
					+ announcementVO.getAnnouncer() + "&announcer_name="
					+ empService.getOneEmp(announcementVO.getAnnouncer()).getEmpName() + "&announcement_title="
					+ announcementVO.getAnnouncement_title() + "&announcement_content="
					+ announcementVO.getAnnouncement_content() + "&announcement_status="
					+ announcementVO.getAnnouncement_status();

			String url = "/announcement/updateAnnouncement.jsp" + param;
			RequestDispatcher successView = req.getRequestDispatcher(url);// ???????????? update_emp_input.jsp
			successView.forward(req, res);

		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		if ("inserttest".equals(action)) {
			String announcerString = req.getParameter("announcer");
			if (announcerString == null || announcerString.trim().length() == 0) {
				errorMap.put("announcer", "????????????????????????");

			}

			String announcement_title = req.getParameter("announcement_title");
			if (announcement_title == null || announcement_title.trim().length() == 0) {
				errorMap.put("announcer", "?????????????????????");

			}
			String announcement_content = req.getParameter("announcement_content");
			if (announcement_content == null || announcement_content.trim().length() == 0) {
				errorMap.put("announcer", "?????????????????????");

			}

			if (!errorMap.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/announcement/addannouncement.jsp");
				failureView.forward(req, res);
				return;// ????????????

			}
		

			AnnouncementVO announcementVO = new AnnouncementVO();
			Integer announcer = Integer.valueOf(announcerString);
			announcementVO.setAnnouncer(announcer);
			announcementVO.setAnnouncement_title(announcement_title);
			announcementVO.setAnnouncement_content(announcement_content);

			
			
			Collection<Part> list = req.getParts();
			System.out.println(list);
			List<byte[]> picList = new ArrayList<byte[]>();
			InputStream is = null;
			BufferedInputStream bis = null;
			byte[] forumImg = null;
			
			AnnouncementService annSvc = new AnnouncementService();
			List<Announcement_mappingVO> listannmap = new ArrayList<Announcement_mappingVO>();
			
		
			System.out.println(list.size());
	
		for (Part part : list) {
			System.out.println(part);	
			is = part.getInputStream();
			bis = new BufferedInputStream(is);
			if (bis.available() >0) {
				forumImg = new byte[bis.available()];
				bis.read(forumImg);
				picList.add(forumImg);
			}
		
		}
		bis.close();
		is.close();
		System.out.println(listannmap.size());
		if(listannmap.size()==0) {
			
			annSvc.insert(announcementVO);
System.out.println(1);			
						RequestDispatcher successView = req.getRequestDispatcher("/announcement/listallannouncement.jsp");
							successView.forward(req, res);
						return;
		}
//		else {
//			
//			
//		}

	for(int i=0;i<picList.size();i++) {
		Announcement_mappingVO announcement_mappingVO = new Announcement_mappingVO();
	announcement_mappingVO.setAnnouncementImg(picList.get(i));
	System.out.println(picList.get(i));
	listannmap.add(announcement_mappingVO);
		
	}

	

			
			
			
			
			
			

//			Integer quantity = Integer.valueOf(req.getParameter("quantity"));
//			if (quantity > 0) {
//				for (int i = 1; i <= quantity; i++) {
//					String name = String.valueOf(i);
//					Part part = req.getPart(name);
//					byte[] bytes = getByteArrayFromPart(part);
//					Announcement_mappingVO announcement_mappingVO = new Announcement_mappingVO();
//					announcement_mappingVO.setAnnouncementImg(bytes);
//					list.add(announcement_mappingVO);
//
//				}
//
//			} else {
//				annSvc.insert(announcementVO);
//
//				RequestDispatcher successView = req.getRequestDispatcher("/announcement/listallannouncement.jsp");
//				successView.forward(req, res);
//				return;
//			}

			annSvc.insertWithImg(announcementVO, listannmap);

			RequestDispatcher successView = req.getRequestDispatcher("/announcement/listallannouncement.jsp");
			successView.forward(req, res);
			return;

		}
		
		
		
		
		

	}

	public static byte[] uploadImage(Part part) throws IOException {
		InputStream ins = part.getInputStream();
		byte[] b = new byte[ins.available()];

		return b;
	}

	public static String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		// System.out.println("header=" + header); // ?????????
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		// System.out.println("filename=" + filename); // ?????????
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
