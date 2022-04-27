package com.post_comment.model;

import java.util.List;

public interface Post_CommentDAO_interface { 
	public void insert(Post_CommentVO post_commentVO);
    public void update(Post_CommentVO post_commentVO);
//    public void delete(Integer report_id);
    public Post_CommentVO findByPrimaryKey(Integer post_comment_id);
    public List<Post_CommentVO> getAll();
}
