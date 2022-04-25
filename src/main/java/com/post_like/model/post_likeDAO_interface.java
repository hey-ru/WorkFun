package com.post_like.model;

import java.util.List;

public interface Post_LikeDAO_interface {
	public void insert(Post_LikeVO post_likeVO);
    public void delete(Integer post_id,Integer emp_id);
    public Post_LikeVO findByPrimaryKey(Integer post_id , Integer emp_id);
    public List<Post_LikeVO> getAll();
}
