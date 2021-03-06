package com.report.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Part;

import com.report_comment.model.Report_CommentVO;


public class ReportService{
	private ReportDAO_interface dao;
	
	public ReportService() {
		dao = new ReportJDBCDAO();
	}
	
	public byte[] Image(Part part) throws IOException {
		InputStream ins= part.getInputStream();
		byte[] b = new byte[ins.available()];
		ins.read(b);
		
		return b;
	}
	

	public ReportVO addReport(Integer reporter, Integer handler, String content,
			 byte[] report_image, Integer report_type , String title) {

		ReportVO reportVO = new ReportVO();

		reportVO.setReporter(reporter);
		reportVO.setHandler(handler);
		reportVO.setContent(content);
		reportVO.setReport_image(report_image);
		reportVO.setReport_type(report_type);
		reportVO.setTitle(title);
		dao.insert(reportVO);

		return reportVO;
	}
	
	public ReportVO updateReport(String title ,String content,
			byte[] report_image ,Integer report_id) {

		ReportVO reportVO = new ReportVO();
		
		reportVO.setTitle(title);
		reportVO.setContent(content);
		reportVO.setReport_image(report_image);
		reportVO.setReport_id(report_id);
		dao.update(reportVO);

		return reportVO;
	}
	
	public ReportVO getOneReport(Integer report_id) {
		return dao.findByPrimaryKey(report_id);
	}
	
	public ReportVO comment(Integer report_id) {
		
		ReportVO reportVO = new ReportVO();
		List<Report_CommentVO> list = dao.TestComment(report_id);
		reportVO.setRecVO(list);
		return reportVO;
	}

	public List<ReportVO> getAll() {
		return dao.getAll();
	}
	
	public List<ReportVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	
	public List<ReportVO> getHandler(Integer handler){
		return dao.getHandler(handler);
	}
	
	public List<ReportVO> getOwn(Integer reporter){
		return dao.getOwnReport(reporter);
	}
		
	public ReportVO getComment(Integer report_id) {
		ReportVO  reportVO = dao.findReport(report_id);
		
		List<Report_CommentVO> list = dao.TestComment(report_id);
		reportVO.setRecVO(list);
		
			return reportVO;
		}
	
	public ReportVO complete(Integer report_id) {

		ReportVO reportVO = new ReportVO();
		
		reportVO.setReport_id(report_id);
		dao.complete(reportVO);

		return reportVO;
	}
	
	public ReportVO reject(Integer report_id) {

		ReportVO reportVO = new ReportVO();
		
		reportVO.setReport_id(report_id);
		dao.reject(reportVO);

		return reportVO;
	}
	
	public ReportVO cancel(Integer report_id) {

		ReportVO reportVO = new ReportVO();
		
		reportVO.setReport_id(report_id);
		dao.cancel(reportVO);

		return reportVO;
	}
	
	public ReportVO allCompleted(Integer report_id) {

		ReportVO reportVO = new ReportVO();
		
		reportVO.setReport_id(report_id);
		dao.allCompleted(reportVO);

		return reportVO;
	}
	
}
