package com.groupbuy.model;

import java.util.*;

import com.groupbuylist.model.*;


public interface GroupBuyDAO_interface {

	 public void insert(GroupBuyVO groupBuyVO);
     public void updateArrTime(GroupBuyVO groupBuyVO);
     public void updateGBStatusBygbId(Integer gb_id,Integer gb_status);
     public GroupBuyVO findByPrimaryKey(Integer gb_id);
     public List<GroupBuyVO> getAll();
     public List<GroupBuyVO> getMyGBAll(Integer gb_owner);
     public List<GroupBuyVO> getNowAll();
     public Set<GroupBuyListVO> getGBListBygbid(Integer gb_id);
     public Set<GroupBuyListVO> getBuyerBygbid(Integer gb_id);
     
}
