package com.permission.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.Part;

import com.emp.model.EmpDAO;
import com.emp.model.EmpDAO_interface;
import com.emp.model.EmpVO;

public class PermissionService {
	private PermissionDAO_interface dao;

	public PermissionService() {
		dao = new PermissionDAO();
	}

	public PermissionVO addPermission(PermissionVO permissionVO) {

	
		dao.insert(permissionVO);

		return permissionVO;
	}
	


	
	
	public int updateEmp(PermissionVO permissionVO) {

		
		dao.update(permissionVO);


		return 1;
	}

	public void deleteEmp(Integer permissionId) {
		dao.delete(permissionId);
	}

	public PermissionVO getOnePermission(Integer permissionId) {
		return dao.findByPrimaryKey(permissionId);
	}

	public List<PermissionVO> getAll() {
		return dao.getAllDAO();
	}
}
