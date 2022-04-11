package com.report.model;

import java.util.*;

public interface reportDAO_interface {
	
    public void insert(reportVO reportVO);
    public void update(reportVO reportVO);
//    public void delete(Integer report_id);
    public reportVO findByPrimaryKey(Integer report_id);
    public List<reportVO> getAll();

}
