package com.eq_image.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class EqImageService {
	private EqImageDAO_interface dao;

	public EqImageService() {
		dao = new EqImageJDBCDAO();
	}

	public EqImageVO addEqImage(Integer equipmentId, byte[] eqImage) {

		EqImageVO eqImageVO = new EqImageVO();
		eqImageVO.setEquipmentId(equipmentId);
		eqImageVO.setEqImage(eqImage);
		dao.insert(eqImageVO);

		return eqImageVO;
	}

	public EqImageVO updateEqImage(byte[] eqImage) {
		
		EqImageVO eqImageVO = new EqImageVO();
		eqImageVO.setEqImage(eqImage);
		dao.update(eqImageVO);
		
		return eqImageVO;
	}

	public void deleteByEqImageId(Integer imageId) {
		dao.deleteByEqImageId(imageId);
	}
	
	public EqImageVO getByImageId(Integer imageId) {
		return dao.getByImageId(imageId);
	}

	public List<EqImageVO> getAll(){
		return dao.getAll(); 
	}
//	public static void main(String[] args) throws Exception {
//		FileInputStream fis = new FileInputStream("C:\\Users\\Tibame_T14\\Desktop\\照片\\圖片1.jpg");
//		byte[] buffer = new byte[fis.available()];
//		fis.read(buffer);
//		fis.close();
//		EqImageService equipImageSvc = new EqImageService();
//		
//		byte[] image1 =buffer;
//		equipImageSvc.addEqImage(128, image1);
//	}
}
