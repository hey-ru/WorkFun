package com.groupBuyList.model;

import java.util.*;

public interface GroupBuyListDAO_Interface {
    public void insert(GroupBuyListVO groupBuyListVO);
    public void update(GroupBuyListVO groupBuyListVO);
    public void delete(Integer gbList_id); //刪除單筆項目
    public void delete(Integer buyer, Integer gb_id); //退出揪團(等join完成再寫)
    
    
    public GroupBuyListVO findByPrimaryKey(Integer gbList_id);
    
    public List<GroupBuyListVO> findMyGb();//查詢我的單筆明細
    public List<GroupBuyListVO> getAll();
}

//前台:參團 查詢 截止前可新增修改刪除品項數量