package com.post_image.model;

import java.util.List;

public interface post_imageDAO_interface {
	public void insert(post_imageVO post_imageVO);
    public void update(post_imageVO post_imageVO);
//    public void delete(Integer post_image_id);
    public post_imageVO findByPrimaryKey(Integer post_image_id);
    public List<post_imageVO> getAll();
}
