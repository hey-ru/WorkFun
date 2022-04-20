package com.groupBuyList.model;

import java.util.*;

public interface GroupBuyListDAO_Interface {
    public void insert(GroupBuyListVO groupBuyListVO);
    public void update(GroupBuyListVO groupBuyListVO);
    public void delete(Integer gbList_id); //�R���浧����
    public void delete(Integer buyer, Integer gb_id); //�h�X����(��join�����A�g)
    
    
    public GroupBuyListVO findByPrimaryKey(Integer gbList_id);
    
    public List<GroupBuyListVO> findMyGb();//�d�ߧڪ��浧����
    public List<GroupBuyListVO> getAll();
}

//�e�x:�ѹ� �d�� �I��e�i�s�W�ק�R���~���ƶq