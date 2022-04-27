package com.post_comment.model;

import java.sql.Timestamp;

public class Post_CommentVO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer post_comment_id;
	private Integer post_id;
	private Integer emp_id;
	private String post_comment;
	private Timestamp comment_createtime;
	private Timestamp comment_updatetime;
	public Integer getPost_comment_id() {
		return post_comment_id;
	}
	public void setPost_comment_id(Integer post_comment_id) {
		this.post_comment_id = post_comment_id;
	}
	public Integer getPost_id() {
		return post_id;
	}
	public void setPost_id(Integer post_id) {
		this.post_id = post_id;
	}
	public Integer getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}
	public String getPost_comment() {
		return post_comment;
	}
	public void setPost_comment(String post_comment) {
		this.post_comment = post_comment;
	}
	public Timestamp getComment_createtime() {
		return comment_createtime;
	}
	public void setComment_createtime(Timestamp comment_createtime) {
		this.comment_createtime = comment_createtime;
	}
	public Timestamp getComment_updatetime() {
		return comment_updatetime;
	}
	public void setComment_updatetime(Timestamp comment_updatetime) {
		this.comment_updatetime = comment_updatetime;
	}

}
