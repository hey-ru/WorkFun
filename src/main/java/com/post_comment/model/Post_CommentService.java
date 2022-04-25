package com.post_comment.model;

import java.sql.Timestamp;
import java.util.List;

public class Post_CommentService {
private Post_CommentDAO_interface dao;
	
	public Post_CommentService() {
		dao = new Post_CommentDAO();
	}
	public Post_CommentVO addPost(Integer post_id,Integer emp_id,String post_comment) {

		Post_CommentVO post_commentVO = new Post_CommentVO();

		post_commentVO.setEmp_id(emp_id);
		post_commentVO.setPost_id(post_id);
		post_commentVO.setEmp_id(emp_id);
		post_commentVO.setPost_comment(post_comment);

		dao.insert(post_commentVO);

		return post_commentVO;
	}
	
	public Post_CommentVO updatePost(String post_comment,Timestamp updatetime) {

		Post_CommentVO post_commentVO = new Post_CommentVO();

		post_commentVO.setPost_comment(post_comment);
		post_commentVO.setComment_updatetime(updatetime);
		dao.update(post_commentVO);

		return post_commentVO;
	}
	
	public Post_CommentVO getOnePost(Integer post_comment_id) {
		return dao.findByPrimaryKey(post_comment_id);
	}

	public List<Post_CommentVO> getAll() {
		return dao.getAll();
	}
}
