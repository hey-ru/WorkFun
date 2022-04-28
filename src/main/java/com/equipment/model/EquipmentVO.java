package com.equipment.model;

import java.io.Serializable;

public class EquipmentVO implements Serializable {
	private Integer equipmentId;
	private String eqName;
	private Integer price;
	private Integer eqStatus;
	private String introduction;
	private String spec;
	
	public Integer getEqId() {
		return equipmentId;
	}

	@Override
	public String toString() {
		return "EquipmentVO [equipmentId=" + equipmentId + ", eqName=" + eqName + ", price=" + price + ", eqStatus="
				+ eqStatus + ", introduction=" + introduction + ", spec=" + spec + "]";
	}

	public Integer getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getEqName() {
		return eqName;
	}

	public void setEqName(String eqName) {
		this.eqName = eqName;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getEqStatus() {
		return eqStatus;
	}

	public void setEqStatus(Integer eqStatus) {
		this.eqStatus = eqStatus;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}
}
