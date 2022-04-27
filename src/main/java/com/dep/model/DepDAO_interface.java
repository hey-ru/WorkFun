package com.dep.model;

import java.util.*;


public interface DepDAO_interface {
          public void insert(DepVO depVO);
          public int update(DepVO depVO);
          public void delete(Integer depId);
          public DepVO findByPrimaryKey(Integer permissionVO);
          public List<DepVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
