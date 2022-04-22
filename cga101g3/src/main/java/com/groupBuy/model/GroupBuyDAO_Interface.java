package com.groupBuy.model;

import java.util.*;

public interface GroupBuyDAO_Interface {
    public void insert(GroupBuyVO groupBuyVO);
    public void update(GroupBuyVO groupBuyVO);
    
    
    public GroupBuyVO findByPrimaryKey(Integer gb_id);
    public List<GroupBuyVO> getAll();
}

//前台:增改查
//後台:查詢修改狀態