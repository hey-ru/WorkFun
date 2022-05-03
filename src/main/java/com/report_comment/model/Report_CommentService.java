package com.report_comment.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.Part;

public class Report_CommentService {
private Report_CommentDAO_interface dao;
	
	public Report_CommentService() {
		dao = new Report_CommentDAO();
	}
	
	public byte[] Image(Part part) throws IOException {
		InputStream ins= part.getInputStream();
		byte[] b = new byte[ins.available()];
		ins.read(b);
		
		return b;
	}
	public Report_CommentVO addReport_Comment(Integer report_id,String comment,byte[] report_comment_image) {

		Report_CommentVO report_commentVO = new Report_CommentVO();

		report_commentVO.setReport_id(report_id);
		report_commentVO.setComment(comment);
		report_commentVO.setReport_comment_image(report_comment_image);

		dao.insert(report_commentVO);

		return report_commentVO;
	}
	
	public Report_CommentVO updateReport_Comment(String comment,byte[] report_comment_image) {

		Report_CommentVO report_commentVO = new Report_CommentVO();

		report_commentVO.setComment(comment);
		report_commentVO.setReport_comment_image(report_comment_image);
		dao.update(report_commentVO);

		return report_commentVO;
	}
	
	public Report_CommentVO getOneReport(Integer report_comment_id) {
		return dao.findByPrimaryKey(report_comment_id);
	}

	public List<Report_CommentVO> getAll() {
		return dao.getAll();
	}
}
