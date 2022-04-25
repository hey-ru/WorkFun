package com.post_keep.model;

import java.util.List;

public interface Post_KeepDAO_interface {
	public void insert(Post_KeepVO post_keepVO);
    public void delete(Integer post_id,Integer emp_id);
    public Post_KeepVO findByPrimaryKey(Integer post_id , Integer emp_id);
    public List<Post_KeepVO> getAll();
}
