package com.post_report.model;

import java.util.*;

public interface post_reportDAO_interface {
	public void insert(post_reportVO post_reportVO);
    public void update(post_reportVO post_reportVO);
//    public void delete(Integer post_report_id);
    public post_reportVO findByPrimaryKey(Integer post_report_id);
    public List<post_reportVO> getAll();
}
