package com.dep.model;

import java.util.List;
import java.util.Set;
import com.emp.model.EmpVO;
import com.dep.model.DepVO;

public class DepService {

	private DepDAO_interface dao;

	public DepService() {
		dao = new DepJDBCDAO();
		
	}
	public void insertDep(DepVO depVO) {
	dao.insert(depVO);
		System.out.println("成功");
	}

	public List<DepVO> getAll() {
		return dao.getAll();
	}

	public DepVO getOneDept(Integer deptno) {
		return dao.findByPrimaryKey(deptno);
	}
	public void updateDep(DepVO depVO) {
		dao.update(depVO);
	}

//	public Set<EmpVO> getEmpsByDeptno(Integer deptno) {
//		return dao.getEmpsByDeptno(deptno);
//	}

	public void deleteDept(Integer deptno) {
		dao.delete(deptno);
	}
	
	public static void main(String[] args) {
		DepService depser=new DepService();
//		DepVO dep=new DepVO();
//		dep.setDepName("品品");
//		depser.insertDep(dep);
//		
//		depser.getAll();
		
		
		
		DepVO dep1=depser.getOneDept(11);
		
		System.out.println(dep1.getDepName());
	
//		List<DepVO> list = depser.getAll();
//		for (DepVO aEmp : list) {
//			System.out.print(aEmp.getDepId() + ",");
//			System.out.print(aEmp.getDepName() + ",");
//			
//			System.out.println();
//		}
		
		depser.deleteDept(19);
		
		
		
	}
}
