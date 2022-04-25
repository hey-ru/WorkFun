package com.post.model;

import java.util.List;

public interface PostDAO_interface {
	public void insert(PostVO postVO);
    public void update(PostVO postVO);
//    public void delete(Integer report_id);
    public PostVO findByPrimaryKey(Integer post_id);
    public List<PostVO> getAll();

}
