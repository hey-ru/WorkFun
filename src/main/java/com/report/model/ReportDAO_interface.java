package com.report.model;

import java.util.List;
import java.util.Map;

public interface ReportDAO_interface {
	
    public void insert(ReportVO reportVO);
    public void update(ReportVO reportVO);
//    public void delete(Integer report_id);
    public List<ReportVO> find(Integer handler,Integer status,Integer report_type);
    public ReportVO findByPrimaryKey(Integer report_id);
    public List<ReportVO> getAll();
    public List<ReportVO> getAll(Map<String, String[]> map); 
}
