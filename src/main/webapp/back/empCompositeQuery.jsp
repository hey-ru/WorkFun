<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>

<%-- 萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位 --%>
<%-- 此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能--%>

<jsp:useBean id="listEmps_ByCompositeQuery" scope="request" type="java.util.List<EmpVO>" /> <!-- 於EL此行可省略 -->
<jsp:useBean id="depSvc" scope="page" class="com.dep.model.DepService" />


<html>
<head><title>複合查詢 - listEmps_ByCompositeQuery.jsp</title>

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

<h4>
☆萬用複合查詢  - 可由客戶端 select_page.jsp 隨意增減任何想查詢的欄位<br>
☆此頁作為複合查詢時之結果練習，<font color=red>已增加分頁、送出修改、刪除之功能</font></h4>
<table id="table-1">
	<tr><td>
		 <h3>所有員工資料 - listAllEmp.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>


<table>
	<tr>
		<th>員工編號</th>
		<th>員工姓名</th>
		<th>部門</th>
		<th>雇用日期</th>
		<th>離職日期</th>
		<th>手機</th>
		<th>分機</th>
		<th>興趣</th>
		<th>專長</th>
		<th>頭貼</th>
		<th>信箱</th>
		<th>生日</th>
		<th>狀態</th>
		<th></th>
	</tr>
	<%@ include file="/back/page1_ByCompositeQuery.file" %>
	<c:forEach var="empVO" items="${listEmps_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
				<td>${empVO.empId}</td>
			<td>${empVO.empName}</td>
		 <td>${empVO.depId}-[${empVO.depVO.depName}]</td> 

			<td>${empVO.hiredate}</td>
				<td>${empVO.resigndate}</td>
			<td>${empVO.phone}</td>
			<td>${empVO.extension}</td> 
				<td>${empVO.hobby}</td>
				
						<td>${empVO.skill}</td>
							<td style="width:300px;"><img 
												src="
									<%=request.getContextPath()%>/util/DBGifReader?pic=emp_profile&table=emp&id_key=emp_id&id=${empVO.empId}
									"
												class="img-fluid"
											></td>
								<td>${empVO.mail}</td>
									<td>${empVO.birthday}</td>
										<td>${empVO.empStatus}</td>
                
		
						<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/empServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="修改"> 
			     <input type="hidden" name="empId"      value="${empVO.empId}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"	    value="getOne_For_Update"></FORM>
			</td>
			
		</tr>
	</c:forEach>
</table>
<%@ include file="/back/page2_ByCompositeQuery.file" %>

<br>本網頁的路徑:<br><b>
   <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> </b>

</body>
</html>