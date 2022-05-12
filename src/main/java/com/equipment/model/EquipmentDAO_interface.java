package com.equipment.model;

import java.util.List;
import java.util.Map;

import com.secondHand.model.SecondHandVO;

public interface EquipmentDAO_interface {
	// 新增器材
	public void insert(EquipmentVO equipmentVO);

	// 更新器材
	public int update(EquipmentVO equipmentVO);

	// 刪除器材 by 器材編號
	public void deleteByEqID(Integer equipmentId);

	// 刪除器材 by 器材名稱
	public void deleteByEqName(String eqName);

	// 查詢 by 器材名稱
	public List<EquipmentVO> getAllByEqName(String eqName);

	// 查詢 by 器材編號
	public EquipmentVO getByEqId(Integer equipmentId);

	// 查詢 by 器材狀態
	public EquipmentVO getByEqStatus(Integer eqStatus);

	// 查詢全部
	public List<EquipmentVO> getALL();

	// 最後一筆資料
	public EquipmentVO getLast();

	// 萬用查詢
	public List<EquipmentVO> getAllQuery(Map<String, String[]> map);

	
	// 查詢那個器材被預約區間
	public List<EquipmentVO> getEqBookingDate(Integer equipmentId);

}
