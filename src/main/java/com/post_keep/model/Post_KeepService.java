package com.post_keep.model;

import java.util.List;

public class Post_KeepService {
	private Post_KeepDAO_interface dao;
	
	public Post_KeepService() {
		dao = new Post_KeepDAO();
	}
	
	public Post_KeepVO addPost_Keep(Integer post_id,Integer emp_id) {
		Post_KeepVO post_keepVO = new Post_KeepVO();

		post_keepVO.setEmp_id(emp_id);
		post_keepVO.setPost_id(post_id);
		dao.insert(post_keepVO);

		return post_keepVO;
	}
	
	public Post_KeepVO getOnePost(Integer post_id,Integer emp_id) {
		return dao.findByPrimaryKey(post_id,emp_id);
	}

	public List<Post_KeepVO> getAll() {
		return dao.getAll();
	}
	
	public void deletePost_Keep(Integer post_id,Integer emp_id) {
		dao.delete(post_id, emp_id);
	}
}
