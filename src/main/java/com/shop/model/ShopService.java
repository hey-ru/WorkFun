package com.shop.model;

import java.util.List;
import java.util.Map;


public class ShopService {

	private ShopDAO_interface dao;

	public ShopService() {
		dao = new ShopDAO();
	}

	public Integer addShop(String shop_name, Integer shop_type, String address, String tel, String website,
			Integer min_amt, byte[] shop_img1, byte[] shop_img2, byte[] shop_img3) {

		ShopVO shopVO = new ShopVO();

		shopVO.setShop_name(shop_name);
		shopVO.setShop_type(shop_type);
		shopVO.setAddress(address);
		shopVO.setTel(tel);
		shopVO.setWebsite(website);
		shopVO.setMin_amt(min_amt);
		shopVO.setShop_img1(shop_img1);
		shopVO.setShop_img2(shop_img2);
		shopVO.setShop_img3(shop_img3);
		
		Integer shop_id = dao.insert(shopVO);

		return shop_id;
	}

	public ShopVO updateShop(Integer shop_id, String shop_name, Integer shop_type, String address, String tel,
			String website, Integer min_amt, byte[] shop_img1, byte[] shop_img2, byte[] shop_img3) {
		ShopVO shopVO = new ShopVO();

		shopVO.setShop_id(shop_id);
		shopVO.setShop_name(shop_name);
		shopVO.setShop_type(shop_type);
		shopVO.setAddress(address);
		shopVO.setTel(tel);
		shopVO.setWebsite(website);
		shopVO.setMin_amt(min_amt);
		shopVO.setShop_img1(shop_img1);
		shopVO.setShop_img2(shop_img2);
		shopVO.setShop_img3(shop_img3);
		dao.update(shopVO);

		return shopVO;
	}

	public ShopVO updateShopStatus(Integer is_disable, Integer shop_id) {
		ShopVO shopVO = new ShopVO();
		
		if(is_disable==0) {
			shopVO.setIs_disable(1);

		}else if(is_disable==1){
			shopVO.setIs_disable(0);
		};
		
		shopVO.setShop_id(shop_id);
		dao.updateShopStatus(shopVO);

		return shopVO;
	}
	
	
	public ShopVO getOneShop(Integer shop_id) {
		return dao.findByPrimaryKey(shop_id);
	}

	public List<ShopVO> getAll() {
		return dao.getAll();
	}
	
	public List<ShopVO> getFRONTAll() {
		return dao.getNOWAll();
	}
	
//	public List<ShopVO> getShopByName(String shop_name) {
//		return dao.findByShopName(shop_name);
//	}
//	
//	public List<ShopVO> getShopByType(Integer shop_type) {
//		return dao.findByShopType(shop_type);
//	}

	public List<ShopVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}

}
