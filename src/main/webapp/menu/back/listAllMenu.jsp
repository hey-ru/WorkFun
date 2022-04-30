<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.menu.model.*"%>

<%
    MenuService menuService = new MenuService();
    List<MenuVO> list = menuService.getAll();
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="menus" scope="page" class="com.menu.model.MenuService" />

<html>
<head>
<title>菜單資料 - listMenu.jsp</title>

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
	width: 800px;
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
		 <h3>所有菜單資料 - listMenu.jsp</h3>
		 <h4><a href="selectMenu_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>菜單編號</th>
		<th>品項</th>
		<th>單價</th>
		<th></th>
		
	
	</tr>
	<%@ include file="/design/page1.file"%> 
	<c:forEach var="menuVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${menuVO.menu_id}</td>
			<td>${menuVO.item}</td>
			<td>${menuVO.price}</td>
			<td>${menuVO.is_item}</td>
			
		</tr>
	</c:forEach>
</table>
<%@ include file="/design/page2.file"%>

</body>
</html>