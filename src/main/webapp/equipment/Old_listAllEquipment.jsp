<%@page import="com.equipment.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%
EquipmentService equipSvc = new EquipmentService();
List<EquipmentVO> list = equipSvc.getAll();
pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有器材資料</title>

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
	width: 1100px;
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
		<tr>
			<td>
				<h3>所有器材資料</h3>
				<h4>
					<a href="eq_select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
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

	<table>
		<tr>
			<th>器材編號</th>
			<th>器材名稱</th>
			<th>金額</th>
			<th>狀態</th>
			<th>器材介紹</th>
			<th>器材規格</th>
			<th>圖片</th>
			<th>圖片</th>
			<th>圖片</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="equipmentVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${equipmentVO.equipmentId}</td>
				<td>${equipmentVO.eqName}</td>
				<td>${equipmentVO.price}</td>
				<td><c:choose>
						<c:when test="${equipmentVO.eqStatus == 0}">
											       	上架
											    </c:when>
						<c:when test="${equipmentVO.eqStatus == 1}">
											        未歸還器材
											    </c:when>
						<c:when test="${equipmentVO.eqStatus == 2}">
											        送修中
											    </c:when>
						<c:when test="${equipmentVO.eqStatus == 3}">
											        下架
											    </c:when>
					</c:choose></td>
				<td>${equipmentVO.introduction}</td>
				<td>${equipmentVO.spec}</td>
				<%-- 			<td>${empVO.deptno}-[${empVO.deptVO.dname}]</td> --%>

				<td><img
					src="<%=request.getContextPath()%>/util/DBGifReader?id_key=equipmentId&id=${equipmentVO.equipmentId}&table=equipment&pic=img1"
					style="max-height: 100%;"></td>
				<td><img
					src="<%=request.getContextPath()%>/util/DBGifReader?id_key=equipmentId&id=${equipmentVO.equipmentId}&table=equipment&pic=img2"
					style="max-height: 100%;"></td>
				<td><img
					src="<%=request.getContextPath()%>/util/DBGifReader?id_key=equipmentId&id=${equipmentVO.equipmentId}&table=equipment&pic=img3"
					style="max-height: 100%;"></td>

				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/equipment/equipment.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="equipmentId" value="${equipmentVO.equipmentId}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/equipment/equipment.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="equipmentId" value="${equipmentVO.equipmentId}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>