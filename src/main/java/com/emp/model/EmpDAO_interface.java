package com.emp.model;

import java.sql.Connection;
import java.util.*;

public interface EmpDAO_interface {
          public int insert(EmpVO empVO);
          public int insert(EmpVO empVO,Connection oneConnection);
          public int update(EmpVO empVO);
          public int update(EmpVO empVO,Connection oneConnection);
          public int delete(Integer empno);
          public int delete(Integer empno,Connection oneConnection);
          public EmpVO findByPrimaryKey(Integer empno);
          public EmpVO findByPrimaryKey(Integer empno,Connection oneConnection);
          public List<EmpVO> getAllDAO(Map<String, String[]> map);
          public List<EmpVO> getAllDAO();
          public List<EmpVO> getAllDAO(Connection oneConnection);
      	EmpVO selectForLogin(Integer empId, String password);
     	EmpVO selectForLogin(Integer empId, String password,Connection oneConnection);
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
