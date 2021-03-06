package com.report_comment.model;

import java.util.List;

import com.report.model.ReportVO;

public interface Report_CommentDAO_interface {
	public void insert(Report_CommentVO report_commentVO);
//    public void delete(Integer report_id);
    public Report_CommentVO findByPrimaryKey(Integer report_comment_id);
    public List<Report_CommentVO> getAll();
    
    public void changeType(ReportVO reportVO);
    public void forward(Report_CommentVO report_CommentVO);
}
