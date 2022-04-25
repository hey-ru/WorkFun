package com.post_report.model;

import java.util.List;

public class Post_ReportService {
		private Post_ReportDAO_interface dao;
		
		public Post_ReportService() {
			dao = new Post_ReportDAO();
		}
	
		public Post_ReportVO addPost_Report(Integer post_id,Integer emp_id,String reason,
				Integer status) {

			Post_ReportVO post_reportVO = new Post_ReportVO();

			post_reportVO.setPost_id(post_id);
			post_reportVO.setEmp_id(emp_id);
			post_reportVO.setReason(reason);
			post_reportVO.setStatus(status);
			dao.insert(post_reportVO);

			return post_reportVO;
		}
		
		public Post_ReportVO updatePost_Report(String reason,
				Integer status) {

			Post_ReportVO post_reportVO = new Post_ReportVO();

			post_reportVO.setReason(reason);
			post_reportVO.setStatus(status);
			dao.insert(post_reportVO);

			return post_reportVO;
		}
		
		public Post_ReportVO getOnePost_Report(Integer post_report_id) {
			return dao.findByPrimaryKey(post_report_id);
		}

		public List<Post_ReportVO> getAll() {
			return dao.getAll();
		}
	}