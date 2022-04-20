package com.post.model;

import java.util.List;

public interface postDAO_interface {
	public void insert(postVO postVO);
    public void update(postVO postVO);
//    public void delete(Integer report_id);
    public postVO findByPrimaryKey(Integer post_id);
    public List<postVO> getAll();

}
