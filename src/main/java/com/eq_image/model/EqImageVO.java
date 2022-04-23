package com.eq_image.model;

import java.io.Serializable;
import java.util.Arrays;

public class EqImageVO implements Serializable {
	private Integer imageId;
	private Integer equipmentId;
	private byte[] eqImage;

	@Override
	public String toString() {
		return "EqImageVO [imageId=" + imageId + ", equipmentId=" + equipmentId + ", eqImage="
				+ Arrays.toString(eqImage) + "]";
	}

	public Integer getImageId() {
		return imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	public Integer getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	public byte[] getEqImage() {
		return eqImage;
	}

	public void setEqImage(byte[] eqImage) {
		this.eqImage = eqImage;
	}
}
