package com.groupBuy.model;

import java.util.*;

public interface GroupBuyDAO_Interface {
    public void insert(GroupBuyVO groupBuyVO);
    public void update(GroupBuyVO groupBuyVO);
    
    
    public GroupBuyVO findByPrimaryKey(Integer gb_id);
    public List<GroupBuyVO> getAll();
}

//�e�x:�W��d
//��x:�d�߭ק窱�A