package com.emp.model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Part;

public class EmpService {

	private EmpDAO_interface dao;

	public EmpService() {
		dao = new EmpDAO();
		//dao = new EmpJDBCDAO();
	}
	  public List<EmpVO> selectByEmpName(String empName){
		  return dao.selectByEmpName(empName);
	  }
	  public List<EmpVO> selectByExtension(String extension){
		  return dao.selectByExtension(extension);
	  }

	public EmpVO addEmp(EmpVO empVO) {

	
		dao.insert(empVO);

		return empVO;
	}

	public void addEmp(EmpVO empVO,Connection con) {

	
		dao.insert(empVO,con);

	
	}
    public Integer selectMail(String mail) {
    	
    	
    	return dao.selectMail(mail);
    }
  public Integer selectMail(String mail,Integer empId) {
    	
    	
    	return dao.selectMail(mail,empId);
    }
  public Integer selectExtension(String extension) {
  	
  	
  	return dao.selectExtension(extension);
  }
public Integer selectExtension(String extension,Integer empId) {
  	
  	
  	return dao.selectExtension(extension,empId);
  }
	
	public byte[] uploadImage(Part part) throws IOException {
		InputStream ins= part.getInputStream();
		byte[] b = new byte[ins.available()];
		
		return b;
	}


	public EmpVO login(String mail, String password ) {
		EmpVO empVO=dao.selectForLogin(mail, password);
		return empVO;
		
		
	}
	public EmpVO login(String mail, String password,Connection con ) {
		EmpVO empVO=dao.selectForLogin(mail, password);
		return empVO;
		
		
	}
	
	public int updateEmp(EmpVO empVO) {

		
		dao.update(empVO);


		return 1;
	}
public int updateEmp(EmpVO empVO,Connection con) {

		
		dao.update(empVO);


		return 1;
	}

	public void deleteEmp(Integer empId) {
		dao.delete(empId);
	}
	public void deleteEmp(Integer empId,Connection con) {
		dao.delete(empId);
	}
	public EmpVO getbymailandbirthday(String mail,java.sql.Date birthday) {
		
		return dao.findbymailandbirthday(mail, birthday);
	}
	
	
	public EmpVO getOneEmp(Integer empId) {
		return dao.findByPrimaryKey(empId);
	}
	public EmpVO getOneEmp(Integer empId,Connection connection) {
		return dao.findByPrimaryKey(empId);
	}

	public List<EmpVO> getAll() {
		return dao.getAllDAO();
	}
	public List<EmpVO> getAll(Connection con) {
		return dao.getAllDAO();
	}
	
	
	
	
	
	
	
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		//System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		//System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
	
	public  byte[] getByteArrayFromPart(Part part) throws IOException {
		InputStream in = part.getInputStream();
		byte[] buffer = new byte[in.available()];
		in.read(buffer);
		in.close();
		return buffer;
	}
	public List<EmpVO> getAll(Map<String, String[]> map) {
		return dao.getAllDAO(map);
	}
	

	public List<EmpVO> getAllDAOFront(Map<String, String[]> map){
		return dao.getAllDAOFront(map);
	}
	
//	public static void main(String[] args) {
//		EmpService empSvc = new EmpService();
//	    List<EmpVO> list = empSvc.getAll();
//	    for (EmpVO empVO : list) {
//			System.out.println(empVO.getEmpId());
//			System.out.println(empVO.getEmpName());
//			System.out.println(empVO.getBirthday());
//		}
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
