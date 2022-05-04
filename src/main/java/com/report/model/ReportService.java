package com.report.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Part;


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
	
	public ReportVO updateReport(String title , Integer report_type,
			Integer reporter,Integer handler, String content,
			byte[] report_image ,Integer report_id) {

		ReportVO reportVO = new ReportVO();
		
		reportVO.setTitle(title);
		reportVO.setReport_type(report_type);
		reportVO.setReporter(reporter);
		reportVO.setHandler(handler);
		reportVO.setContent(content);
		reportVO.setReport_image(report_image);
		reportVO.setReport_id(report_id);
		dao.update(reportVO);

		return reportVO;
	}
	
	public ReportVO getOneReport(Integer report_id) {
		return dao.findByPrimaryKey(report_id);
	}

	public List<ReportVO> getAll() {
		return dao.getAll();
	}
	
	public List<ReportVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	
}
