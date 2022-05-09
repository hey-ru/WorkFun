/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *     所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */


package com.util;

import java.util.*;

public class jdbcUtil_CompositeQuery {

	public static String get_aCondition_For_myDB(String columnName, String value) {

		String aCondition = null;

//		if ("is_deal".equals(columnName) || "sal".equals(columnName) || "comm".equals(columnName) || "deptno".equals(columnName)) // 原本樣子
		if ("is_deal".equals(columnName) || "shop_type".equals(columnName) || "gb_status".equals(columnName) || "is_disable".equals(columnName) || "buyer".equals(columnName)) // 用於其他
			aCondition = columnName + "=" + value;
//		else if ("name".equals(columnName) || "job".equals(columnName)) // 原本樣子
		else if ("name".equals(columnName)) // 用於varchar
			aCondition = columnName + " like '%" + value + "%'";
		else if ("shop_name".equals(columnName)) // 用於shop
			aCondition = " s." + columnName + " like '%" + value + "%'";
		else if ("hiredate".equals(columnName))                          // 用於date
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

//	public static void main(String argv[]) {
//
//		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
//		Map<String, String[]> map = new TreeMap<String, String[]>();
//		map.put("empno", new String[] { "7001" });
//		map.put("ename", new String[] { "KING" });
//		map.put("job", new String[] { "PRESIDENT" });
//		map.put("hiredate", new String[] { "1981-11-17" });
//		map.put("sal", new String[] { "5000.5" });
//		map.put("comm", new String[] { "0.0" });
//		map.put("deptno", new String[] { "10" });
//		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的key
//
//		String finalSQL = "select * from emp2 "
//				          + jdbcUtil_CompositeQuery.get_WhereCondition(map)
//				          + "order by empno";
//		System.out.println("●●finalSQL = " + finalSQL);
//
//	}
}
