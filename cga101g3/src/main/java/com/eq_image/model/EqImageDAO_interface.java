package com.eq_image.model;

import java.util.List;

public interface EqImageDAO_interface {
	
	public void insert(EqImageVO eqImageVO);
	
	// 查詢 by imageId
	public EqImageVO findByPrimaryKey(Integer imageId);
	
	public List<EqImageVO> getAll();
}
