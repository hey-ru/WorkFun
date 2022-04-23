<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listMenu.jsp'>List</a> all MenuItems  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="menu.do" >
        <b>輸入店家編號 (如101):</b>
        <input type="text" name="shop_id">
        <input type="hidden" name="action" value="getmenu">
        <input type="submit" value="查看菜單">
    </FORM>
  </li>

  <jsp:useBean id="ms" scope="page" class="com.menu.model.MenuService" />
   
</ul>


<h3>員工管理</h3>

<ul>
  <li><a href='addEmp.jsp'>Add</a> a new Emp.</li>
</ul>

</body>
</html>