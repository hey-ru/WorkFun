package com.permission.model;

import java.util.*;

public interface PermissionDAO_interface {
          public void insert(PermissionVO permissionVO);
          public int update(PermissionVO permissionVO);
          public void delete(Integer permissionVO);
          public String findByPrimaryKey(Integer permissionVO);
          public List<PermissionVO> getAllDAO();
      	public List<String> getAllPermissionNameDAO();
    		// TODO Auto-generated method stub

    		
    		
    	
    		
    	
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
