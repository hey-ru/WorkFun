package com.post_report.model;

public class post_reportVO {
	
	private Integer post_report_id;
	private Integer post_id;
	private Integer emp_id;
	private String reason;
	private Integer status;
	public Integer getPost_report_id() {
		return post_report_id;
	}
	public void setPost_report_id(Integer post_report_id) {
		this.post_report_id = post_report_id;
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
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

}
