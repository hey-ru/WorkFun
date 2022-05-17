package com.post.model;

import java.sql.Timestamp;
import java.util.List;
import com.post_comment.model.Post_CommentVO;
import com.post_image.model.Post_ImageVO;

public class PostVO implements java.io.Serializable{ 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer post_id;
	private Integer emp_id;
	private String post_title;
	private String post_content;
	private byte[]post_video;
	private Timestamp post_createtime;
	private Timestamp post_updatetime;
	private Integer is_disable;
	private List<Post_CommentVO> pcVO;
	private List<Post_ImageVO> piVO;
	
	public List<Post_CommentVO> getPcVO() {
		return pcVO;
	}
	public void setPcVO(List<Post_CommentVO> pcVO) {
		this.pcVO = pcVO;
	}
	public List<Post_ImageVO> getPiVO() {
		return piVO;
	}
	public void setPiVO(List<Post_ImageVO> piVO) {
		this.piVO = piVO;
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
	public String getPost_title() {
		return post_title;
	}
	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}
	public String getPost_content() {
		return post_content;
	}
	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}
	public byte[] getPost_video() {
		return post_video;
	}
	public void setPost_video(byte[] post_video) {
		this.post_video = post_video;
	}
	public Timestamp getPost_createtime() {
		return post_createtime;
	}
	public void setPost_createtime(Timestamp post_createtime) {
		this.post_createtime = post_createtime;
	}
	public Timestamp getPost_updatetime() {
		return post_updatetime;
	}
	public void setPost_updatetime(Timestamp post_updatetime) {
		this.post_updatetime = post_updatetime;
	}
	public Integer getIs_disable() {
		return is_disable;
	}
	public void setIs_disable(Integer is_disable) {
		this.is_disable = is_disable;
	}

}
