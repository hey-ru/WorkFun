<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.groupbuylist.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	GroupBuyListService gbListSvc = new GroupBuyListService();
    List<GroupBuyListVO> list = gbListSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有參團資料 - listAllGbList.jsp</title>

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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有參團資料 - listAllGbList.jsp</h3>
		 <h4><a href="selectGbList_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message.key} : ${message.value}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>參團編號</th>
		<th>揪團編號</th>
		<th>參團者</th>
		<th>品項</th>
		<th>價格</th>
		<th>數量</th>
		<th>金額</th>
		<th>備註</th>
		<th>付款狀態</th>
		<th>取貨狀態</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="groupBuyListVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${groupBuyListVO.gbList_id}</td>
			<td>${groupBuyListVO.gb_id}</td>
			<td>${groupBuyListVO.buyer_name}</td>
			<td>${groupBuyListVO.item}</td>
			<td>${groupBuyListVO.price}</td>
			<td>${groupBuyListVO.qty}</td> 
			<td>${groupBuyListVO.price*groupBuyListVO.qty}</td>
			<td>${groupBuyListVO.remark}</td>
			<td>${groupBuyListVO.is_pay}</td>
			<td>${groupBuyListVO.is_pickup}</td>
<%-- 			-[${groupBuyListVO.groupBuyVO.shop_name}] --%>
<%-- 			<td>${groupBuyListVO.deptno}-[${empVO.deptVO.dname}]</td> --%>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/groupBuyList/groupBuyList.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="gbList_id"  value="${groupBuyListVO.gbList_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
		</tr>
	</c:forEach>
	
	
</table>
<%@ include file="page2.file" %>

</body>
</html>