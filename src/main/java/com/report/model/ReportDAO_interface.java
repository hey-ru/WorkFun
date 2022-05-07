package com.report.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.report_comment.model.Report_CommentVO;

public interface ReportDAO_interface {
	
    public void insert(ReportVO reportVO);
    public void insertComment(ReportVO reportVO);
    public void update(ReportVO reportVO);
//    public void delete(Integer report_id);
    public List<ReportVO> find(Integer handler,Integer status,Integer report_type);
    public ReportVO findByPrimaryKey(Integer report_id);
    public List<ReportVO> getAll();
    public List<ReportVO> getAll(Map<String, String[]> map); 
    public List<ReportVO> getHandler(Integer handler);
    public void handleComment(ReportVO reportVO);
    public ReportVO findReport(Integer report_id);
    public List<Report_CommentVO> TestComment(Integer report_id);
    public List<ReportVO> handleReport(Integer report_id);
}
