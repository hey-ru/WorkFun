package com.equipment.model;

import java.io.Serializable;
import java.util.Arrays;

public class EquipmentVO implements Serializable {
	private Integer equipmentId;
	private String eqName;
	private Integer price;
	private Integer eqStatus;
	private String introduction;
	private String spec;
	private byte[] img1;
	private byte[] img2;
	private byte[] img3;

	@Override
	public String toString() {
		return "EquipmentVO [equipmentId=" + equipmentId + ", eqName=" + eqName + ", price=" + price + ", eqStatus="
				+ eqStatus + ", introduction=" + introduction + ", spec=" + spec + ", img1=" + Arrays.toString(img1)
				+ ", img2=" + Arrays.toString(img2) + ", img3=" + Arrays.toString(img3) + "]";
	}

	public byte[] getImg1() {
		return img1;
	}

	public void setImg1(byte[] img1) {
		this.img1 = img1;
	}

	public byte[] getImg2() {
		return img2;
	}

	public void setImg2(byte[] img2) {
		this.img2 = img2;
	}

	public byte[] getImg3() {
		return img3;
	}

	public void setImg3(byte[] img3) {
		this.img3 = img3;
	}

	public Integer getEqId() {
		return equipmentId;
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
