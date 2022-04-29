<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.equipment.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<html>
<head>
<title>器材資料</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 1100px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>器材資料</h3>
		 <h4><a href="eq_select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>器材編號</th>
		<th>器材名稱</th>
		<th>金額</th>
		<th>器材狀態</th>
		<th>器材介紹</th>
		<th>詳細規格</th>
		<th>器材圖片</th>
	</tr>
	<tr>
<!-- 		pageScope.equipmentVO.getEquipmentId() 與下相同 -->
		<td>${equipmentVO.equipmentId}</td> 
		<td>${equipmentVO.eqName}</td>
		<td>${equipmentVO.price}</td>
		<td>${equipmentVO.eqStatus}</td>
		<td>${equipmentVO.introduction}</td>
		<td>${equipmentVO.spec}</td>
<%-- 		<td>${empVO.deptno}-[${empVO.deptVO.dname}]</td> --%>
	</tr>
</table>

</body>
</html>