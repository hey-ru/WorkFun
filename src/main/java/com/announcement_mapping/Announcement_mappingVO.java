package com.announcement_mapping;

public class Announcement_mappingVO {
	private Integer announcement_id;
	private Integer announcementImg_id ;
	public Integer getAnnouncement_id() {
		return announcement_id;
	}
	public void setAnnouncement_id(Integer announcement_id) {
		this.announcement_id = announcement_id;
	}
	public Integer getAnnouncementImg_id() {
		return announcementImg_id;
	}
	public void setAnnouncementImg_id(Integer announcementImg_id) {
		this.announcementImg_id = announcementImg_id;
	}
	public byte[] getAnnouncementImg() {
		return announcementImg;
	}
	public void setAnnouncementImg(byte[] announcementImg) {
		this.announcementImg = announcementImg;
	}
	private byte[] announcementImg;
}
