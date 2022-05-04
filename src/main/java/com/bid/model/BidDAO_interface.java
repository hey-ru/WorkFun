package com.bid.model;

import java.util.List;

public interface BidDAO_interface {
	public void insert(BidVO bidVO);
	public void insertBySecondHand(BidVO bidVO, java.sql.Connection con);
    public void update(BidVO bidVO);
    public BidVO getById(Integer bid_id);
    public List<BidVO> getAll();
}
