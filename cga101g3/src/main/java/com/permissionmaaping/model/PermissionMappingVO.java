package com.permissionmaaping.model;

import java.sql.Date;

public class PermissionMappingVO implements java.io.Serializable{
	private Integer empId;
	private Integer permissionId ;
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public Integer getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}
	
}