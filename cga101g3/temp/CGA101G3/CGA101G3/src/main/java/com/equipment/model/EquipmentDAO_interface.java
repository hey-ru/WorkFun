package com.equipment.model;

import java.util.List;

public interface EquipmentDAO_interface {
	// 新增器材
	public void insert(EquipmentVO equipmentVO);

	// 更新器材
	public void update(EquipmentVO equipmentVO);

	// 刪除器材 by 器材編號
	public void deleteByEqID(Integer equipmentId);

	// 刪除器材 by 器材名稱
	public void deleteByEqName(String eqName);

	// 查詢 by 器材名稱
	public EquipmentVO getByEqName(String eqName);
	
	// 查詢 by 器材編號
	public EquipmentVO getByEqId(Integer equipmentId);
	
	// 查詢 by 器材狀態
	public EquipmentVO getByEqStatus(Integer eqStatus);

	// 查詢全部
	public List<EquipmentVO> getALL();
}
