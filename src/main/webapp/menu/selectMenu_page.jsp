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
<title>Menu: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM Menu: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Menu: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 	    <c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>



<%-- <a href="/CGA101G3/menu?shop_id=${menuVO.shop_id}&action=getmenu"><button type="button" value="getmenu" class="btn btn-info">查看菜單</button></a> --%>



<ul>
  <li><a href='listAllMenu.jsp'>List</a> all MenuItems  <br><br></li>
  
<!--   先寫死,測試用,之後放在店家 -->
  <li>
   	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/menu/selectmenubyshop">
        <b>輸入店家編號 (如101):</b>
        <input type="submit" value="查看菜單">
        <input type="hidden"  name="shop_id" value="101">
        <input type="hidden"  name="action" value="getmenu">
    </FORM>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/menu/selectmenubyshop">
        <b>輸入店家編號 (如101):</b>
        <input type="submit" value="查看菜單">
        <input type="hidden"  name="shop_id" value="107">
        <input type="hidden"  name="action" value="getmenu">
    </FORM>
    
<!--     動態寫法,結合店家資訊 -->
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/menu/selectmenubyshop">
        <b>輸入店家編號 (如101):</b>
        <input type="submit" value="查看菜單">
        <input type="hidden"  name="shop_id" value="{shopVO.shop_id}">
        <input type="hidden"  name="action" value="getmenu">
    </FORM>
    
  </li>
   
</ul>


<h3>菜單管理</h3>

<ul>
  <li><a href='addMenu.jsp'>Add</a> a new menu.</li>
</ul>

</body>
</html>