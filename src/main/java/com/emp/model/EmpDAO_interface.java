package com.emp.model;

import java.util.*;

public interface EmpDAO_interface {
          public int insert(EmpVO empVO);
          public int update(EmpVO empVO);
          public int delete(Integer empno);
          public EmpVO findByPrimaryKey(Integer empno);
          public List<EmpVO> getAllDAO();
      	EmpVO selectForLogin(Integer empId, String password);
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
