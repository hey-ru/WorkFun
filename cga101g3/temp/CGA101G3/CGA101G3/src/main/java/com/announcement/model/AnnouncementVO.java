package com.announcement.model;

import java.sql.Timestamp;

public class AnnouncementVO {
	private Integer announcementId;
	private Integer announcerId ;
	private String announcementTitle;
	private String announcementContent ;
	private Timestamp announcementTime;
	private Byte announcementStatus ;
	public Integer getAnnouncementId() {
		return announcementId;
	}
	public void setAnnouncementId(Integer announcementId) {
		this.announcementId = announcementId;
	}
	public Integer getAnnouncerId() {
		return announcerId;
	}
	public void setAnnouncerId(Integer announcementerId) {
		this.announcerId = announcementerId;
	}
	public String getAnnouncementTitle() {
		return announcementTitle;
	}
	public void setAnnouncementTitle(String announcementTitle) {
		this.announcementTitle = announcementTitle;
	}
	public String getAnnouncementContent() {
		return announcementContent;
	}
	public void setAnnouncementContent(String announcementContent) {
		this.announcementContent = announcementContent;
	}
	public Timestamp getAnnouncementTime() {
		return announcementTime;
	}
	public void setAnnouncementTime(Timestamp announcementTime) {
		this.announcementTime = announcementTime;
	}
	public Byte getAnnouncementStatus() {
		return announcementStatus;
	}
	public void setAnnouncementStatus(Byte announcementStatus) {
		this.announcementStatus = announcementStatus;
	}
	
}
