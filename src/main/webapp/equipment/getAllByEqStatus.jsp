<%@page import="com.equipment.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<html>
<head>
<title>器材名稱查詢</title>

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
		<tr>
			<td>
				<h3>器材名稱查詢</h3>
				<h4>
					<a href="eq_select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message.key}:${message.value}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>器材編號</th>
			<th>器材名稱</th>
			<th>金額</th>
			<th>狀態</th>
			<th>器材介紹</th>
			<th>器材規格</th>
			<!-- 		<th>圖片</th> -->
		</tr>
<%-- 		<%@ include file="/page1.file"%> --%>
		<c:forEach var="equipmentVO" items="${list}">
			
			<tr>
				<td>${equipmentVO.equipmentId}</td>
				<td>${equipmentVO.eqName}</td>
				<td>${equipmentVO.price}</td>
				<td>${equipmentVO.eqStatus}</td>
				<td>${equipmentVO.introduction}</td>
				<td>${equipmentVO.spec}</td>
			</tr>
		</c:forEach>
	</table>
<%-- 	<%@ include file="/page2.file"%> --%>
</body>
</html>