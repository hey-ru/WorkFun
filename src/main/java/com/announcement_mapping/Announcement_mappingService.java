package com.announcement_mapping;

import java.util.List;

public class Announcement_mappingService {
	private Announcement_mappingDAO_interface dao;
	
	private Announcement_mappingService() {
		dao=new Announcement_mappingDAO();
	}

	  public void insert(Announcement_mappingVO announcement_mappingVO)
	  {
		  dao.insert(announcement_mappingVO);
		  
	  }
	  
	  
	  
	  
	     public void update(Announcement_mappingVO announcement_mappingVO) {
	    	 dao.update(announcement_mappingVO);
	    	 
	     }
	     
	     public void delete(Integer announcement_id,Integer announcementImg_id) {
	    	 dao.delete(announcement_id, announcementImg_id);
	     }
	     public List<Announcement_mappingVO> findByPrimaryKey(Integer announcement_id){
	    	
	    	 return  dao.findByPrimaryKey(announcement_id);
	     }
	     public List<Announcement_mappingVO> getAll(){
	    	 return dao.getAll();
	     }
	
	
	
	
	
	
	
	
	
}
