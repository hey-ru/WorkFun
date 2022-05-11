package com.announcement_mapping;

import java.util.List;



public interface Announcement_mappingDAO_interface {
	  public void insert(Announcement_mappingVO announcement_mappingVO);
	     public int update(Announcement_mappingVO announcement_mappingVO);
	     public void delete(Integer announcement_id,Integer announcementImg_id);
	     public List<Announcement_mappingVO> findByPrimaryKey(Integer announcement_id);
	     public List<Announcement_mappingVO> getAll();
}
