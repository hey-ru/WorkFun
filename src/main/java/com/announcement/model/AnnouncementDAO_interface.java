package com.announcement.model;

import java.util.List;


import com.announcement_mapping.model.Announcement_mappingVO;


public interface AnnouncementDAO_interface {
	   public void insert(AnnouncementVO announcementVO);
     public int update(AnnouncementVO announcementVO);
     public void delete(Integer announcementId);
     public AnnouncementVO findByPrimaryKey(Integer announcementId);
     public List<AnnouncementVO> getAll();
     public void insertWithImg(AnnouncementVO announcementVO, List<Announcement_mappingVO> list);
     public List<AnnouncementVO> getAllWithImg(Integer announcement_id);
 	public int updateWithImg(AnnouncementVO announcementVO, List<Announcement_mappingVO> list);
	public List<AnnouncementVO> getAllSelectByStatus() ;
     //萬用複合查詢(傳入參數型態Map)(回傳 List)
//   public List<EmpVO> getAll(Map<String, String[]> map); 
}
