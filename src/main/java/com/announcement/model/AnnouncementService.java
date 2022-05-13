package com.announcement.model;

import java.util.List;

import com.announcement_mapping.model.Announcement_mappingVO;

public class AnnouncementService {
private AnnouncementDAO_interface dao;
public  AnnouncementService() {
	// TODO Auto-generated constructor stub
	dao=new AnnouncementJDBCDAO();
	//dao=new AnnouncementDAO();
}
public void insert(AnnouncementVO announcementVO) {
	dao.insert(announcementVO);
}
public void update(AnnouncementVO announcementVO) {
	dao.update(announcementVO);
}
public void delete(Integer announcementId) {
	dao.delete(announcementId);
}
public AnnouncementVO findByPrimaryKey(Integer announcementId) {
	return dao.findByPrimaryKey(announcementId);
}
public List<AnnouncementVO> getAll(){
	
	return dao.getAll();
}

public void insertWithImg(AnnouncementVO announcementVO, List<Announcement_mappingVO> list) {
	dao.insertWithImg(announcementVO, list);
	
}
public int updateWithImg(AnnouncementVO announcementVO, List<Announcement_mappingVO> list) {
	
	return 1;
}


}
