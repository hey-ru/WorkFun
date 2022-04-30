package com.permissionmaaping.model;

import java.sql.Connection;
import java.util.List;


public class PermissionMappingService {

	private PermissionMappingDAO_interface dao;

	public PermissionMappingService() {
		dao = new PermissionMappingDAO();
	}

	public void addpmId2emp(PermissionMappingVO pm) {

		
		dao.insert(pm);

		
	}
	public List<PermissionMappingVO> getOneEmpPermissions(Integer empId) {
		return dao.findByPrimaryKey(empId);
		
		
	}
	
	public List<PermissionMappingVO> getAll(Connection oneConnection) {
		return dao.getAllDAO();
	}

}
