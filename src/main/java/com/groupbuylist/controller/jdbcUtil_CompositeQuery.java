/*
/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *     所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */

package com.groupbuylist.controller;

import java.util.*;

public class jdbcUtil_CompositeQuery {

	public static String get_aCondition_For_myDB(String columnName, String value) {

		String aCondition = null;

		if ("gbList_id".equals(columnName) || "gb_id".equals(columnName) || "buyer".equals(columnName) || "menu_id".equals(columnName) 
			 || "price".equals(columnName) || "qty".equals(columnName) || "total".equals(columnName) || "is_pay".equals(columnName) || "is_pickup".equals(columnName)
			 || "shop_id".equals(columnName) || "gb_owner".equals(columnName) || "gb_status".equals(columnName) || "min_amt".equals(columnName)) // 用於其他
			aCondition = columnName + "=" + value;
		else if ("buyer_name".equals(columnName) || "item".equals(columnName) || "remark".equals(columnName) || "shop_name".equals(columnName)) // 用於varchar
			aCondition = columnName + " like '%" + value + "%'";
		else if ("gbList_upd".equals(columnName) || "start_time".equals(columnName) || "end_time".equals(columnName) || "arr_time".equals(columnName))                          // 用於date
			aCondition = columnName + "=" + "'"+ value +"'";                          //for 其它DB  的 date
//		    aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";  //for Oracle 的 date
		
		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_myDB(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		
		return whereCondition.toString();
	}

	public static void main(String argv[]) {

		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("gbList_id", new String[] { "10001" });
		map.put("gb_id", new String[] { "1005" });
		map.put("buyer", new String[] { "1029" });
		map.put("buyer_name", new String[] { "胖叔叔" });
		map.put("menu_id", new String[] { "1020" });
		map.put("item", new String[] { "鴨肉麵" });
		map.put("price", new String[] { "45" });
		map.put("qty", new String[] { "2" });
		map.put("total", new String[] { "100" });
		map.put("remark", new String[] { "不" });
		map.put("is_pay", new String[] { "0" });
		map.put("is_pickup", new String[] { "0" });
		map.put("gbList_upd", new String[] { "2022-05-01" });
		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的key

		String finalSQL = "select * from groupbuylist "
				          + jdbcUtil_CompositeQuery.get_WhereCondition(map)
				          + "order by gbList_id";
		System.out.println("●●finalSQL = " + finalSQL);

	}
}
