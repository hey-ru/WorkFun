package com.report.model;

import java.util.*;

public class QueryReport {

	public static String get_aCondition_For_myDB(String columnName, String value) {

		String aCondition = null;

		if ("report_id".equals(columnName) || "reporter".equals(columnName) || "handler".equals(columnName) || 
			"status".equals(columnName) || "report_image".equals(columnName) || "report_type".equals(columnName)) 
			aCondition = columnName + "=" + value;
		else if ("content".equals(columnName) || "title".equals(columnName))
			aCondition = columnName + " like '%" + value + "%'";
		else if ("starttime".equals(columnName) ||
				"updatetime".equals(columnName) ||	"endtime".equals(columnName))                       
			aCondition = columnName + "=" + "'"+ value +"'";                          
		
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

				System.out.println("有送出查詢資料的欄位數count = = " + count);
			}
		}
		
		return whereCondition.toString();
	}
}
