package com.post_comment.model;

import java.util.List;

public interface post_commentDAO_interface {
	public void insert(post_commentVO post_commentVO);
    public void update(post_commentVO post_commentVO);
//    public void delete(Integer report_id);
    public post_commentVO findByPrimaryKey(Integer post_comment_id);
    public List<post_commentVO> getAll();
}
