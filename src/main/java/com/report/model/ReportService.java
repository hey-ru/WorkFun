package com.report.model;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.Part;

public class ReportService {
	private ReportDAO_interface dao;
	
	public ReportService() {
		dao = new ReportJDBCDAO();
	}
	public byte[] Image(Part part) throws IOException {
		InputStream ins= part.getInputStream();
		byte[] b = new byte[ins.available()];
		
		return b;
	}
	
	public ReportVO addReport(Integer reporter, Integer handler, String content,
			Integer status, byte[] report_image, Integer report_type , String title) {

		ReportVO reportVO = new ReportVO();

		reportVO.setReporter(reporter);
		reportVO.setHandler(handler);
		reportVO.setContent(content);
		reportVO.setStatus(status);
		reportVO.setReport_image(report_image);
		reportVO.setReport_type(report_type);
		reportVO.setTitle(title);
		dao.insert(reportVO);

		return reportVO;
	}
	
	public ReportVO updateReport(Integer handler, String content,Timestamp updatetime,Timestamp endtime,
			Integer status, byte[] report_image, Integer report_type , String title) {

		ReportVO reportVO = new ReportVO();

		reportVO.setHandler(handler);
		reportVO.setContent(content);
		reportVO.setUpdatetime(updatetime);
		reportVO.setEndtime(endtime);
		reportVO.setStatus(status);
		reportVO.setReport_image(report_image);
		reportVO.setReport_type(report_type);
		reportVO.setTitle(title);
		dao.update(reportVO);

		return reportVO;
	}
	
	public ReportVO getOneReport(Integer report_id) {
		return dao.findByPrimaryKey(report_id);
	}

	public List<ReportVO> getAll() {
		return dao.getAll();
	}
}