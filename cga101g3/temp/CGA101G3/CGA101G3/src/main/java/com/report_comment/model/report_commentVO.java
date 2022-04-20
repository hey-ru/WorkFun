package com.report_comment.model;

import java.sql.Timestamp;

public class report_commentVO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer report_comment_id;
	private Integer report_id;
	private String comment;
	private Timestamp createtime;
	private byte[] report_comment_image;
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public Integer getReport_comment_id() {
		return report_comment_id;
	}
	public void setReport_comment_id(Integer report_comment_id) {
		this.report_comment_id = report_comment_id;
	}
	public Integer getReport_id() {
		return report_id;
	}
	public void setReport_id(Integer report_id) {
		this.report_id = report_id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	public byte[] getReport_comment_image() {
		return report_comment_image;
	}
	public void setReport_comment_image(byte[] report_comment_image) {
		this.report_comment_image = report_comment_image;
	}
}
