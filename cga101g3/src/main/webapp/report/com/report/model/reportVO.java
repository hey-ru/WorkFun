package com.report.model;

import java.sql.Date;

public class reportVO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer report_id;
	private Integer reporter;
	private Integer handler;
	private Date starttime;
	private Date updatetime;
	private Date endtime;
	private String content;
	private Integer status;
	private byte[] report_image;
	private Integer report_type;
	
	public Integer getReport_id() {
		return report_id;
	}
	public void setReport_id(Integer report_id) {
		this.report_id = report_id;
	}
	public Integer getReporter() {
		return reporter;
	}
	public void setReporter(Integer reporter) {
		this.reporter = reporter;
	}
	public Integer getHandler() {
		return handler;
	}
	public void setHandler(Integer handler) {
		this.handler = handler;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public byte[] getReport_image() {
		return report_image;
	}
	public void setReport_image(byte[] report_image) {
		this.report_image = report_image;
	}
	public Integer getReport_type() {
		return report_type;
	}
	public void setReport_type(Integer report_type) {
		this.report_type = report_type;
	}
}
