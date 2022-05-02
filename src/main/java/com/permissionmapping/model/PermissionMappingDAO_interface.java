package com.permissionmapping.model;

import java.util.*;

public interface PermissionMappingDAO_interface {
          public void insert(Integer empId,Integer permissionId);
//          public int update(PermissionMappingVO permissionMappingVO);
          public void delete(Integer empId,Integer permissionId);
          public List<Integer> findByPrimaryKey(Integer empno);
          public List<PermissionMappingVO> getAllDAO();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
