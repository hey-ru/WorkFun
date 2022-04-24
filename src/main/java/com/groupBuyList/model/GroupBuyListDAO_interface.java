package com.groupBuyList.model;

import java.util.*;

public interface GroupBuyListDAO_interface {
    public void insertItem(GroupBuyListVO groupBuyListVO); //新增參團單筆項目
    public void updateItem(GroupBuyListVO groupBuyListVO); //修改單筆項目
    public void deleteItem(Integer gbList_id); //刪除單筆項目
    public void deleteMyGb(Integer buyer, Integer gb_id); //退出揪團
    
    
    public List<GroupBuyListVO> getMyGb(Integer buyer, Integer gb_id);//查詢我的單筆明細
    public List<GroupBuyListVO> getAll(); 
    public GroupBuyListVO findByPrimaryKey(Integer gb_id); 
}

//前台:查詢新增刪除參團, 截止前可新增修改刪除品項及備註