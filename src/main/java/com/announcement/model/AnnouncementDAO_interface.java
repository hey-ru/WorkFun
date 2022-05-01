package com.announcement.model;

import java.util.List;
import java.util.Set;

import com.permissionmapping.model.PermissionMappingVO;

public interface AnnouncementDAO_interface {
	   public void insert(AnnouncementVO announcementVO);
     public int update(AnnouncementVO announcementVO);
     public void delete(Integer announcementId);
     public AnnouncementVO findByPrimaryKey(Integer announcementId);
     public List<AnnouncementVO> getAll();
     //萬用複合查詢(傳入參數型態Map)(回傳 List)
//   public List<EmpVO> getAll(Map<String, String[]> map); 
}
