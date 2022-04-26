<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop.model.*"%>

<%
  // EmpVO empVO = (EmpVO) request.getAttribute("empVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>���u��ƭק� - update_emp_input.jsp</title>

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
		 <h3>���a��ƭק� - update_shop_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��ƭק�:</h3>

<%-- ���~��C --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">�Эץ��H�U���~:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message.value}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>

<FORM METHOD="post" ACTION="emp.do" name="form1">
<table>
    <tr>
		<td>���a�s��:<font color=red><b>*</b></font></td>
		<td>${param.shop_id}</td>
	</tr>
	<tr>
		<td>���a�W��:</td>
		<td><input type="TEXT" name="shop_name" size="45" 
			 value="${param.shop_name}"/></td><td>${errorMsgs.shop_name}</td>
	</tr>
	<tr>
		<td>���a����:</td>
		<td><input type="TEXT" name="shop_type" size="45"
			 value="${param.shop_type}"/></td><td>${errorMsgs.shop_type}</td>
	</tr>
	<tr>
		<td>�a�}:</td>
		<td><input type="TEXT" name="address" size="45"
			 value="${param.address}"/></td><td>${errorMsgs.address}</td>
	</tr>
	<tr>
		<td>�q��:</td>
		<td><input type="TEXT" name="tel" size="45"
			 value="${param.tel}"/></td><td>${errorMsgs.tel}</td>
	</tr>
	<tr>
		<td>����:</td>
		<td><input type="TEXT" name="website" size="45"
			 value="${param.website}"/></td><td>${errorMsgs.website}</td>
	</tr>
	<tr>
		<td>�C��:</td>
		<td><input type="TEXT" name="min_amt" size="45"
			 value="${param.min_amt}"/></td><td>${errorMsgs.min_amt}</td>
	</tr>
	<tr>
		<td>�Ϥ�:</td>
		<td><input type="FILE" name="shop_img1" size="45" oninput="pic1.src=window.URL.createObjectURL(this.files[0])"
			 value="${param.shop_img1}"/></td><td>${errorMsgs.shop_img1}
			 </td>
	</tr>
	<tr>
		<td>�Ϥ�:</td>
		<td><input type="FILE" name="shop_img2" size="45" oninput="pic2.src=window.URL.createObjectURL(this.files[0])"
			 value="${param.shop_img2}"/>
			 </td><td>${errorMsgs.shop_img2}</td>
	</tr>
	<tr>
		<td>�Ϥ�:</td>
		<td><input type="FILE" name="shop_img3" size="45" oninput="pic3.src=window.URL.createObjectURL(this.files[0])"
			 value="${param.shop_img3}"/>
			 </td><td>${errorMsgs.shop_img3}</td>
	</tr>


</table>
<img id="pic1" style="width:100px;">
<img id="pic2" style="width:100px;">
<img id="pic3" style="width:100px;">
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="empno" value="${param.shop_id}">
<input type="submit" value="�e�X�ק�"></FORM>
</body>


</html>