<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.menu.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
List<MenuVO> menuListDisable = (List<MenuVO>) request.getAttribute("menuListDisable"); 
%>


<html>
<head>
<title>店家的菜單資料 - listMenuByShop.jsp</title>

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
	width: 600px;
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

<table id="table-1">
	<tr>
		<td>
			<h3>所有店家菜單 - ListMenuByShopDisable.jsp</h3>
			<h4>
				<a href="<%=request.getContextPath()%>/shop/listAllShop.jsp">回店家列表</a>
			</h4>
		</td>
	</tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color: red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color: red">${message.key}:${message.value}</li>
		</c:forEach>
	</ul>
</c:if>


<!-- 以下是查詢完成後該店家現有的菜單項目, 含修改功能-->
<table>
	<tr>
		<th>編號</th>
		<th>品項</th>
		<th>價格</th>
		<th>狀態</th>
		<th>變更狀態</th>
	</tr>

	<c:forEach var="menu" items="${menuListDisable}">		
		<tr>
			<td>${menu.menu_id}</td>
			<td>${menu.item}</td>
			<td>${menu.price}</td>
			<td>
			<c:if test="${menu.is_item==1}"> <c:out value="上架"/> </c:if>
			<c:if test="${menu.is_item==0}"> <c:out value="下架"/> </c:if>
			</td>
			<td>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/menu/disablebyshopservlet"
					style="margin-bottom: 0px;">
					<input type="submit" value="上架"> 
					<input type="hidden" name="menu_id" value="${menu.menu_id}">
					<input type="hidden" name="shop_id" value="${menu.shop_id}">
					
					<input type="hidden" name="action" value="changeStatus">
				</FORM>
			</td>
		</tr>
	</c:forEach>
</table>


</body>
</html>