package com.post_like.model;

import java.util.List;

public class Post_LikeService {
private Post_LikeDAO_interface dao;
	
	public Post_LikeService() {
		dao = new Post_LikeDAO();
	}
	
	public Post_LikeVO addPost_Like(Integer post_id,Integer emp_id) {
		Post_LikeVO post_likeVO = new Post_LikeVO();

		post_likeVO.setEmp_id(emp_id);
		post_likeVO.setPost_id(post_id);
		dao.insert(post_likeVO);

		return post_likeVO;
	}
	
	public Post_LikeVO getOnePost_Like(Integer post_id,Integer emp_id) {
		return dao.findByPrimaryKey(post_id,emp_id);
	}

	public List<Post_LikeVO> getAll() {
		return dao.getAll();
	}
	
	public void deletePost_Like(Integer post_id,Integer emp_id) {
		dao.delete(post_id, emp_id);
	}
}
