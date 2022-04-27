package com.emp.model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.Part;

public class EmpService {

	private EmpDAO_interface dao;

	public EmpService() {
		dao = new EmpDAO();
	}

	public EmpVO addEmp(EmpVO empVO) {

	
		dao.insert(empVO);

		return empVO;
	}
	
	public byte[] uploadImage(Part part) throws IOException {
		InputStream ins= part.getInputStream();
		byte[] b = new byte[ins.available()];
		
		return b;
	}

	public int updateEmp(EmpVO empVO) {

		
		dao.update(empVO);
	EmpVO empempVO1=dao.findByPrimaryKey(empVO.getEmpId());

		return 1;
	}

	public void deleteEmp(Integer empno) {
		dao.delete(empno);
	}

	public EmpVO getOneEmp(Integer empno) {
		return dao.findByPrimaryKey(empno);
	}

	public List<EmpVO> getAll() {
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
	
	
	
	public static void main(String[] args) {
		EmpService empSvc = new EmpService();
	    List<EmpVO> list = empSvc.getAll();
	    for (EmpVO empVO : list) {
			System.out.println(empVO.getEmpId());
			System.out.println(empVO.getEmpName());
			System.out.println(empVO.getBirthday());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
