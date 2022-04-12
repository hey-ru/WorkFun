package com.report_comment.model;

import java.util.List;

public interface report_commentDAO_interface {
	public void insert(report_commentVO report_commentVO);
    public void update(report_commentVO report_commentVO);
//    public void delete(Integer report_id);
    public report_commentVO findByPrimaryKey(Integer report_comment_id);
    public List<report_commentVO> getAll();
}
