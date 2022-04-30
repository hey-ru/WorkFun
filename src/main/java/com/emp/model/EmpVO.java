package com.emp.model;

import java.sql.Date;




public class EmpVO {
	private Integer empId;
	private Integer depId;
	private String empName;
	private Date hiredate;
	private Date resigndate;
	private String phone;
	private String extension;
	private String empPassword;
	private String hobby;
	private String skill;
	private byte[] empProfile; 
	private String mail;
	private Date birthday;
	private Byte empStatus;
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public Integer getDepId() {
		return depId;
	}
	public void setDepId(Integer depId) {
		this.depId = depId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public Date getResigndate() {
		return resigndate;
	}
	public void setResigndate(Date resigndate) {
		this.resigndate = resigndate;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getEmpPassword() {
		return empPassword;
	}
	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public byte[] getEmpProfile() {
		return empProfile;
	}
	public void setEmpProfile(byte[] empProfile) {
		this.empProfile = empProfile;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Byte getEmpStatus() {
		return empStatus;
	}
	public void setEmpStatus(Byte empStatus) {
		this.empStatus = empStatus;
	}
	 public com.dep.model.DepVO getDeptVO() {
		    com.dep.model.DepService deptSvc = new com.dep.model.DepService();
		    com.dep.model.DepVO deptVO = deptSvc.getOneDept(depId);
		    return deptVO;
	    }
	

}



	
	