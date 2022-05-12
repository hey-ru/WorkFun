package com.emp.model;

import java.sql.Connection;
import java.sql.Date;
import java.util.*;

public interface EmpDAO_interface {
          public int insert(EmpVO empVO);
          public int insert(EmpVO empVO,Connection con);
          public int update(EmpVO empVO);
          public int update(EmpVO empVO,Connection con);
          public int delete(Integer empno);
          public int delete(Integer empno,Connection con);
          public EmpVO findByPrimaryKey(Integer empno);
          public EmpVO findByPrimaryKey(Integer empno,Connection con);
          public List<EmpVO> getAllDAO(Map<String, String[]> map);
          public List<EmpVO> getAllDAO();
          public List<EmpVO> getAllDAO(Connection con);
      	EmpVO selectForLogin(String mail, String password);
     	EmpVO selectForLogin(String mail, String password,Connection con);
        public EmpVO findbymailandbirthday(String mail,java.sql.Date birthday);
        public EmpVO findbymailandbirthday(String mail,java.sql.Date birthday,Connection con);
     	
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
