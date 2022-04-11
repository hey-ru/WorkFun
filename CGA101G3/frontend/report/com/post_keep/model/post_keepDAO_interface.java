package com.post_keep.model;

import java.util.List;

public interface post_keepDAO_interface {
	public void insert(post_keepVO post_keepVO);
    public void update(post_keepVO post_keepVO);
//    public void delete(Integer post_id,Integer emp_id);
    public post_keepVO findByPrimaryKey(Integer post_id , Integer emp_id);
    public List<post_keepVO> getAll();
}
