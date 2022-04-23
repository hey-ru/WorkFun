package com.post_like.model;

import java.util.List;

public interface post_likeDAO_interface {
	public void insert(post_likeVO post_likeVO);
    public void update(post_likeVO post_likeVO);
//    public void delete(Integer post_id,Integer emp_id);
    public post_likeVO findByPrimaryKey(Integer post_id , Integer emp_id);
    public List<post_likeVO> getAll();
}
