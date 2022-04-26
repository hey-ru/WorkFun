<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.menu.model.*"%>

<%
  MenuVO menuVO = (MenuVO) request.getAttribute("menuVO"); //listMenuByShop.java (Concroller) 存入req的menuVO物件 (包括幫忙取出的menuVO, 也包括輸入資料錯誤時的menuVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>菜單資料修改 - update_menu_input.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>菜單內容修改 - update_menu_input.jsp</h3>
		 <h4><a href=selectMenu_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/menu/selectmenubyshop" name="form1">
<table>
	<tr>
		<td>菜單編號:</td>
		<td>${param.menu_id}</td>
	</tr>
	<tr>
		<td>品項:</td>
		<td><input type="TEXT" name="item" size="45" 
			value="${param.item}" /></td><td>${errorMsgs.item}</td>
	</tr>
	<tr>
		<td>價格:</td>
		<td><input type="TEXT" name="price" size="45"	
			value="${param.price}" /></td><td>${errorMsgs.price}</td>
	</tr>
	<tr>
		<td>項目狀態:</td>
		<td><input type="TEXT" name="is_item" size="45"	
			value="${param.is_item}" /></td><td>${errorMsgs.is_item}</td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="menu_id" value="${param.menu_id}">
<input type="hidden" name="shop_id" value="${param.shop_id}">
<input type="hidden" name="is_item" value="${param.is_item}">
<input type="submit" value="送出修改"></FORM>


</body>

<script>
     
</script>
</html>