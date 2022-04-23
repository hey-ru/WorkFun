package com.announcementImg.model;

import java.util.List;



public interface AnnouncementImgDAO_interface {
	  public void insert(AnnouncementImgVO announcementImgVO);
	     public int update(AnnouncementImgVO announcementImgVO);
	     public void delete(Integer announcementId,Integer announcementImgId);
	     public AnnouncementImgVO findByPrimaryKey(Integer announcementImgId);
	     public List<AnnouncementImgVO> getAll();
}
