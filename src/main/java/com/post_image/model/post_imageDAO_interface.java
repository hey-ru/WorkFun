package com.post_image.model;

import java.util.List;

public interface Post_ImageDAO_interface {
	public void insert(Post_ImageVO post_imageVO);
    public void update(Post_ImageVO post_imageVO);
//    public void delete(Integer post_image_id);
    public Post_ImageVO findByPrimaryKey(Integer post_image_id);
    public List<Post_ImageVO> getAll();
}
