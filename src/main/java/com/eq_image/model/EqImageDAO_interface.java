package com.eq_image.model;

import java.util.List;

public interface EqImageDAO_interface {

	// 新增照片
	public void insert(EqImageVO eqImageVO);

	// 更新照片
	public void update(EqImageVO eqImageVO);

	// 刪除照片
	public void deleteByEqImageId(Integer imageId);

	// 查詢 by imageId
	public EqImageVO getByImageId(Integer imageId);

	// 查詢全部
	public List<EqImageVO> getAll();
}
