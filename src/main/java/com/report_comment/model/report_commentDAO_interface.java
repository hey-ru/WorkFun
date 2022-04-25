package com.report_comment.model;

import java.util.List;

public interface Report_CommentDAO_interface {
	public void insert(Report_CommentVO report_commentVO);
    public void update(Report_CommentVO report_commentVO);
//    public void delete(Integer report_id);
    public Report_CommentVO findByPrimaryKey(Integer report_comment_id);
    public List<Report_CommentVO> getAll();
}
