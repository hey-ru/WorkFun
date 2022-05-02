package com.permissionmapping.model;

import java.sql.Connection;
import java.util.List;


public class PermissionMappingService {

	private PermissionMappingDAO_interface dao;

	public PermissionMappingService() {
		dao = new PermissionMappingDAO();
	}

	public void addpmId2emp(Integer empId,Integer permissionId) {

		
		dao.insert(empId,permissionId);
		
	}
	public void deleteEmpPm(Integer empId,Integer permissionId) {
		dao.delete(empId, permissionId);
	}
	public List<Integer> getOneEmpPermissions(Integer empId) {
		return dao.findByPrimaryKey(empId);
		
		
	}
	
	public List<PermissionMappingVO> getAll(Connection oneConnection) {
		return dao.getAllDAO();
	}

}
