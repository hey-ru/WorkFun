package com.notification.model;

import java.util.*;

public interface NotificationDAO_interface {
          public void insert(NotificationVO notificationVO);
//          public int update(PermissionMappingVO permissionMappingVO);
          
          public void delete(Integer notificationId);
          public NotificationVO findByPrimaryKey(Integer notificationId);
          public List<NotificationVO> getAll();
//          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<NotificationVO> getAll(Map<String, String[]> map); 
}
