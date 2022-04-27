<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.menu.model.*"%>

<%
    MenuService menuService = new MenuService();
    List<MenuVO> list = menuService.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>菜單新增 - update_menu_input.jsp</title>

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
		 <h3>菜單新增 - addShop.jsp</h3>
		 <h4><a href=selectMenu_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>




<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/menu/addmenubyshop">
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
	
	<jsp:useBean id="shopservice" scope="page" class="com.shop.model.ShopService" />
	<tr>
		<td>店家:<font color=red><b>*</b></font></td>
		<td><select size="1" name="shop_id">
			<c:forEach var="shopVO" items="${shopservice.all}">
				<option value="${shopVO.shop_id}" ${(param.shop_id==shopVO.shop_id)? 'selected':'' } >${shopVO.shop_name}
			</c:forEach>
		</select></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>


</body>

<script>
     
</script>
</html>