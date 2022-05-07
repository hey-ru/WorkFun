package com.equipment.model;

import java.util.List;

public class EquipmentService {
	private EquipmentDAO_interface dao;

	public EquipmentService() {
		dao = new EquipmentJDBCDAO();
	}

	public EquipmentVO addEquipment(String eqName, Integer price, Integer eqStatus, String spec, byte[] img1,
			byte[] img2, byte[] img3) {

		EquipmentVO equipmentVO = new EquipmentVO();

		equipmentVO.setEqName(eqName);
		equipmentVO.setPrice(price);
		equipmentVO.setEqStatus(eqStatus);
		equipmentVO.setSpec(spec);
		equipmentVO.setImg1(img1);
		equipmentVO.setImg2(img2);
		equipmentVO.setImg3(img3);
		dao.insert(equipmentVO);

		return equipmentVO;
	}

	public EquipmentVO updatEquipment(Integer equipmentId, String eqName, Integer price, Integer eqStatus,
			 String spec, byte[] img1, byte[] img2, byte[] img3) {

		EquipmentVO equipmentVO = new EquipmentVO();

		equipmentVO.setEquipmentId(equipmentId);
		equipmentVO.setEqName(eqName);
		equipmentVO.setPrice(price);
		equipmentVO.setEqStatus(eqStatus);
		equipmentVO.setSpec(spec);
		equipmentVO.setImg1(img1);
		equipmentVO.setImg2(img2);
		equipmentVO.setImg3(img3);
		dao.update(equipmentVO);

		return equipmentVO;
	}

	public void deleteByEqId(Integer equipmentId) {
		dao.deleteByEqID(equipmentId);
	}

	public void deleteByEqName(String eqName) {
		dao.deleteByEqName(eqName);
	}

	public List<EquipmentVO> getAllByEqName(String eqName) {
		return dao.getAllByEqName(eqName);
	}

	public EquipmentVO getByEqId(Integer equipmentId) {
		return dao.getByEqId(equipmentId);
	}

	public EquipmentVO getByEqstatus(Integer eqStatus) {
		return dao.getByEqStatus(eqStatus);
	}

	public List<EquipmentVO> getAll() {
		return dao.getALL();
	}

	public EquipmentVO getLast() {
		return dao.getLast();
	}
}
