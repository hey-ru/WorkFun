package com.equipment.model;

import java.util.List;

public interface EquipmentDAO_interface {
	// �s�W����
	public void insert(EquipmentVO equipmentVO);

	// ��s����
	public void update(EquipmentVO equipmentVO);

	// �R������ by �����s��
	public void deleteByEqID(Integer equipmentId);

	// �R������ by �����W��
	public void deleteByEqName(String eqName);

	// �d�� by �����W��
	public EquipmentVO getByEqName(String eqName);
	
	// �d�� by �����s��
	public EquipmentVO getByEqId(Integer equipmentId);
	
	// �d�� by �������A
	public EquipmentVO getByEqStatus(Integer eqStatus);

	// �d�ߥ���
	public List<EquipmentVO> getALL();
}
