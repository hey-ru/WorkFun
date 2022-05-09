package com.report_comment.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.Part;

import com.report.model.ReportVO;

public class Report_CommentService {
private Report_CommentDAO_interface dao;
	
	public Report_CommentService() {
		dao = new Report_CommentJDBCDAO();
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
	
	public Report_CommentVO getOneReport(Integer report_comment_id) {
		return dao.findByPrimaryKey(report_comment_id);
	}

	public List<Report_CommentVO> getAll() {
		return dao.getAll();
	}
	
	public Report_CommentVO forward(Integer report_id,String comment) {

		Report_CommentVO report_commentVO = new Report_CommentVO();

		report_commentVO.setReport_id(report_id);
		report_commentVO.setComment(comment);
		dao.forward(report_commentVO);

		return report_commentVO;
	}
	
	public ReportVO changeType(Integer handler,Integer report_type , Integer status,Integer report_id) {

		ReportVO repVO = new ReportVO();
		
		repVO.setHandler(handler);
		repVO.setReport_type(report_type);
		repVO.setStatus(status);
		repVO.setReport_id(report_id);
		dao.changeType(repVO);

		return repVO;
	}
}
