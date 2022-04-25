<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop.model.*"%>

<%
  ShopVO shopVO = (ShopVO) request.getAttribute("shopVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>員工資料修改 - update_emp_input.jsp</title>

<style>
  table#table-1 {
    width: 450px;
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
		 <h3>員工資料新增 - addEmp.jsp</h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<!-- 錯誤表列 -->
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message.value}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" enctype="multipart/form-data" ACTION="ShopServlet.do" name="form1">
<table>
	<tr>
		<td>店家名稱:</td>
		<td><input type="TEXT" name="shop_name" size="45" 
			 value="${param.shop_name}"/></td><td>${errorMsgs.shop_name}</td>
	</tr>
	<tr>
		<td>店家類型:</td>
		<td><input type="TEXT" name="shop_type" size="45"
			 value="${param.shop_type}"/></td><td>${errorMsgs.shop_type}</td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td>雇用日期:</td> -->
<%-- 		<td><input name="hiredate" id="f_date1" type="text"/></td><td>${errorMsgs.hiredate}</td> --%>
<!-- 	</tr> -->
	<tr>
		<td>地址:</td>
		<td><input type="TEXT" name="address" size="45"
			 value="${param.address}"/></td><td>${errorMsgs.address}</td>
	</tr>
	<tr>
		<td>電話:</td>
		<td><input type="TEXT" name="tel" size="45"
			 value="${param.tel}"/></td><td>${errorMsgs.tel}</td>
	</tr>
	<tr>
		<td>網站:</td>
		<td><input type="TEXT" name="website" size="45"
			 value="${param.website}"/></td><td>${errorMsgs.website}</td>
	</tr>
	<tr>
		<td>低消:</td>
		<td><input type="TEXT" name="min_amt" size="45"
			 value="${param.min_amt}"/></td><td>${errorMsgs.min_amt}</td>
	</tr>
	<tr>
		<td>圖片:</td>
		<td><input type="FILE" name="shop_img1" size="45" oninput="pic1.src=window.URL.createObjectURL(this.files[0])"
			 value="${param.shop_img1}"/></td><td>${errorMsgs.shop_img1}
			 </td>
	</tr>
	<tr>
		<td>圖片:</td>
		<td><input type="FILE" name="shop_img2" size="45" oninput="pic2.src=window.URL.createObjectURL(this.files[0])"
			 value="${param.shop_img2}"/>
			 </td><td>${errorMsgs.shop_img2}</td>
	</tr>
	<tr>
		<td>圖片:</td>
		<td><input type="FILE" name="shop_img3" size="45" oninput="pic3.src=window.URL.createObjectURL(this.files[0])"
			 value="${param.shop_img3}"/>
			 </td><td>${errorMsgs.shop_img3}</td>
	</tr>
	
	

<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 				<option value="${deptVO.deptno}" ${(param.deptno==deptVO.deptno)? 'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>
<img id="pic1" style="width:100px;">
<img id="pic2" style="width:100px;">
<img id="pic3" style="width:100px;">
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

