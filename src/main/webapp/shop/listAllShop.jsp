<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shop.model.*"%>

<%
ShopService shopSvc = new ShopService();
List<ShopVO> list = shopSvc.getAll();
pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有餐廳資料 - listAllShop.jsp</title>

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
	width: autp;
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>

	<h4>此頁練習採用 EL 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有餐廳資料 - listAllShop.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<!-- 錯誤表列 -->
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>shop_id編號</th>
			<th>shop_name</th>
			<th>shop_type</th>
			<th>address</th>
			<th>tel</th>
			<th>website</th>
			<th>min_amt</th>
			<!-- 		<th>照片</th> -->
			<!-- 		<th>照片</th> -->
			<!-- 		<th>照片</th> -->
			<th>菜單</th>
			<!-- 		<th>修改</th> -->
			<!-- 		<th>刪除</th> -->
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="shopVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${shopVO.shop_id}</td>
				<td>${shopVO.shop_name}</td>
				<td>${shopVO.shop_type}</td>
				<td>${shopVO.address}</td>
				<td>${shopVO.tel}</td>
				<td><a href="${shopVO.website}">link</a></td>
				<td>${shopVO.min_amt}</td>
				<%-- 			<td>${shopVO.shop_img1}</td> --%>
				<%-- 			<td><img src="data:image/jpg;base64,${shopVO.shop_img2}" style="max-width: 100%;"></td> --%>
				<%-- 			<td><img src="data:image/jpg;base64,${shopVO.shop_img3}" style="max-width: 100%;"></td> --%>
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/menu/selectmenubyshop">
						<input type="submit" value="查看菜單"> 
						<input type="hidden" name="shop_id" value="${shopVO.shop_id}"> 
						<input type="hidden" name="action" value="getmenu">
					</FORM>
				</td>
<!-- 				<td> -->
<!-- 					<FORM METHOD="post" -->
<%-- 						ACTION="<%=request.getContextPath()%>/emp/emp.do" --%>
<!-- 						style="margin-bottom: 0px;"> -->
<!-- 						<input type="submit" value="修改"> <input type="hidden" -->
<%-- 							name="empno" value="${empVO.empno}"> <input type="hidden" --%>
<!-- 							name="action" value="getOne_For_Update"> -->
<!-- 					</FORM> -->
<!-- 				</td> -->
				<!-- 			<td> -->
				<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" style="margin-bottom: 0px;"> --%>
				<!-- 			     <input type="submit" value="刪除"> -->
				<%-- 			     <input type="hidden" name="empno"  value="${empVO.empno}"> --%>
				<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
				<!-- 			</td> -->
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>