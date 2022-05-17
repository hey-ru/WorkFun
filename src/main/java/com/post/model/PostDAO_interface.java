package com.post.model;

import java.util.List;

import com.post_comment.model.Post_CommentVO;
import com.post_image.model.Post_ImageVO;

public interface PostDAO_interface {
	public void insert(PostVO postVO);
    public void update(PostVO postVO);
//    public void delete(Integer report_id);
    public PostVO findByPrimaryKey(Integer post_id);
    public List<PostVO> getAll();
    public List<Post_ImageVO> getImage();
    public List<Post_CommentVO> getComment();

}
