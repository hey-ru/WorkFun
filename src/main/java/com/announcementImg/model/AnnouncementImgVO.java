package com.announcementImg.model;

import java.sql.Timestamp;

public class AnnouncementImgVO {
	private Integer announcementImgId;
	private Integer announcementId ;
	private byte[] announcementImg;
	private Timestamp announcementUptime ;
	public Integer getAnnouncementImgId() {
		return announcementImgId;
	}
	public void setAnnouncementImgId(Integer imgId) {
		this.announcementImgId = imgId;
	}
	public Integer getAnnouncementId() {
		return announcementId;
	}
	public void setAnnouncementId(Integer announcementId) {
		this.announcementId = announcementId;
	}
	public byte[] getAnnouncementImg() {
		return announcementImg;
	}
	public void setAnnouncementImg(byte[] announcementImg) {
		this.announcementImg = announcementImg;
	}
	public Timestamp getAnnouncementUptime() {
		return announcementUptime;
	}
	public void setAnnouncementUptime(Timestamp announcementUptime) {
		this.announcementUptime = announcementUptime;
	}
	
}
