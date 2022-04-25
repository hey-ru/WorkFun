package com.post.model;

import java.sql.Timestamp;
import java.util.List;

public class PostService {
	private PostDAO_interface dao;
	
	public PostService() {
		dao = new PostDAO();
	}
	
	public PostVO addPost(Integer emp_id,String post_title,String post_content,
			byte[]post_video,Integer is_disable) {

		PostVO postVO = new PostVO();

		postVO.setEmp_id(emp_id);
		postVO.setPost_title(post_title);
		postVO.setPost_content(post_content);
		postVO.setPost_video(post_video);
		postVO.setIs_disable(is_disable);
		dao.insert(postVO);

		return postVO;
	}
	
	public PostVO updatePost(String post_title,String post_content,
			byte[]post_video,Integer is_disable,Timestamp updatetime) {

		PostVO postVO = new PostVO();

		postVO.setPost_title(post_title);
		postVO.setPost_content(post_content);
		postVO.setPost_video(post_video);
		postVO.setIs_disable(is_disable);
		postVO.setPost_updatetime(updatetime);
		dao.update(postVO);

		return postVO;
	}
	
	public PostVO getOnePost(Integer post_id) {
		return dao.findByPrimaryKey(post_id);
	}

	public List<PostVO> getAll() {
		return dao.getAll();
	}
	
}
