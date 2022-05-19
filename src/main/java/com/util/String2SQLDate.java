package com.util;

import java.sql.Date;
import java.text.SimpleDateFormat;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;

public class String2SQLDate {
public static java.sql.Date strToDate(String strDate) {
	String str=strDate;
	SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
	java.util.Date d=null;
	try {
		d=format.parse(str);
	} catch (Exception e) {
		e.printStackTrace();
		// TODO: handle exception
	}
	java.sql.Date date=new java.sql.Date(d.getTime()); 

	return date;
	
	
}

//public static void main(String[] args) {
//	String string="1993-   06-19";
//	String aString=string.replaceAll("[\\pP\\p{Punct}]",""); //清除符號
//	//String aString=string.replaceAll(" +",""); //清除空白
////	String aString=string.replaceAll(" ",""); //清除空白
//	System.out.println(aString);
	
	
	
	
	
//}





}
