package com.announcement.model;

import java.sql.Timestamp;

public class AnnouncementVO {
	private Integer announcement_id;
	private Integer announcer ;
	private String announcement_title;
	private String announcement_content ;
	private Timestamp announcement_time;
	private Byte announcement_status ;
	public Integer getAnnouncement_id() {
		return announcement_id;
	}
	public void setAnnouncement_id(Integer announcement_id) {
		this.announcement_id = announcement_id;
	}
	
	public Integer getAnnouncer() {
		return announcer;
	}
	public void setAnnouncer(Integer announcer) {
		this.announcer = announcer;
	}
	public String getAnnouncement_title() {
		return announcement_title;
	}
	public void setAnnouncement_title(String announcement_title) {
		this.announcement_title = announcement_title;
	}
	public String getAnnouncement_content() {
		return announcement_content;
	}
	public void setAnnouncement_content(String announcement_content) {
		this.announcement_content = announcement_content;
	}
	public Timestamp getAnnouncement_time() {
		return announcement_time;
	}
	public void setAnnouncement_time(Timestamp announcement_time) {
		this.announcement_time = announcement_time;
	}
	public Byte getAnnouncement_status() {
		return announcement_status;
	}
	public void setAnnouncement_status(Byte announcement_status) {
		this.announcement_status = announcement_status;
	}
	
	
}
