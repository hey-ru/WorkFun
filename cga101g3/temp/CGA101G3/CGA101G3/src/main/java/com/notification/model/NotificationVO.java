package com.notification.model;

import com.mysql.cj.jdbc.Clob;

public class NotificationVO {
	
	private Integer notificationId;
	private Integer empId;
	private String content;
	
	public Integer getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}





}
	

	

