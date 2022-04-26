<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.menu.model.*"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
ArrayList<MenuVO> menuList = (ArrayList<MenuVO>) request.getAttribute("menuList"); //MenuServlet.java(Concroller)
%>

<%
// MenuService menuSvc = new MenuService();
// List<MenuVO> menulist = menuSvc.getByShopId(shop_id);
// pageContext.setAttribute("menulist", menulist);
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
	<tr><td>
		 <h3>店家菜單 - ListMenuByShop.jsp</h3>
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
		<th>價格</th>
		<th>狀態</th>
		<th></th>
	</tr>
	
		
	<c:forEach var="menu" items="${menuList}" >	
	<tr>
		<td>
            <c:out value="${menu.menu_id}" />
        </td>
        <td>
            <c:out value="${menu.item}" />
        </td>
        <td>
            <c:out value="${menu.price}" />
        </td>
         <td>
            <c:out value="${menu.is_item}" />
        </td>
        <td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/menu/selectmenubyshop" style="margin-bottom: 0px;">
			     <input type="submit" value="編輯">
			     <input type="hidden" name="menu_id"  value="${menu.menu_id}">
			     <input type="hidden" name="action"	value="getMenuItem_For_Update">
			</FORM>
		</td>		
	</tr>
	</c:forEach>
	
	
</table>

</body>
</html>