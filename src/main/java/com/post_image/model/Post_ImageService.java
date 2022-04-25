package com.post_image.model;

import java.util.List;

public class Post_ImageService {
	private Post_ImageDAO_interface dao;
	
	public Post_ImageService() {
		dao = new Post_ImageDAO();
	}
	
	public Post_ImageVO addPost(Integer post_id, byte[] image) {

		Post_ImageVO post_imageVO = new Post_ImageVO();

		post_imageVO.setPost_id(post_id);
		post_imageVO.setImage(image);

		dao.insert(post_imageVO);

		return post_imageVO;
	}
	
	public Post_ImageVO updatePost(byte[] image) {

		Post_ImageVO post_imageVO = new Post_ImageVO();

		post_imageVO.setImage(image);
		dao.update(post_imageVO);

		return post_imageVO;
	}
	
	public Post_ImageVO getOnePost(Integer post_image_id) {
		return dao.findByPrimaryKey(post_image_id);
	}

	public List<Post_ImageVO> getAll() {
		return dao.getAll();
	}
}
