package com.post_report.model;

import java.util.*;

public interface Post_ReportDAO_interface { 
	public void insert(Post_ReportVO post_reportVO);
    public void update(Post_ReportVO post_reportVO);
//    public void delete(Integer post_report_id);
    public Post_ReportVO findByPrimaryKey(Integer post_report_id);
    public List<Post_ReportVO> getAll();
}
