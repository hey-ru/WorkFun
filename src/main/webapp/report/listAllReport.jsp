<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.report.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
ReportService repSvc = new ReportService();
List<ReportVO> list = repSvc.getAll();
pageContext.setAttribute("list", list);
int itemsPerPage = 10;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>所有回報資料 listAllReport</title>
<%@ include file="/design/frontmetacss.jsp"%>

<style>
 *{
      box-sizing: border-box;  /*預設值content-box*/

      }
#findreporter{
	display: inline-block;
}

</style>

</head>

<body>
	<!-- ======= Header ======= -->
	<%@ include file="/design/frontheader.jsp"%>
	<!-- End Header -->

	<!-- content 如果頁面要可以往下滑就改一下main的height值吧 -->
	<main style="height: 130vh;">
		<div style="height: 90px;"></div>
		<!-- 從這裡開始 -->
		<!-- table -->
		<div>
			<div class="card shadow mb-4">
				<div class="card-header py-3">
					<h5 class="m-0 font-weight-bold text-info">
						<strong>回報LIST</strong>
					</h5>
				</div>
				<div class="card-body">
					<div class="table-responsive">
						<div id="dataTable_wrapper"
							class="dataTables_wrapper dt-bootstrap4">

							<div class="row">
								<%-- 萬用複合查詢-以下欄位-可隨意增減 --%>
<ul>  
  <li>   
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/reportServlet" name="form1">
     	<jsp:useBean id="reportSvc1" scope="page"
									class="com.report.model.ReportService" />
       <b>選擇類型:</b>
       <select size="1" name="report_type" >
          <option value="0">添購新品</option>
          <option value="1">損壞報修</option>
          <option value="2">軟硬體問題</option>
          <option value="3">其他</option> 
       </select>
           
        <b>選擇回報狀態:</b>
       <select size="1" name="status" >
       	  <option value="">顯示全部</option>
          <option value="0">已發送</option>
          <option value="1">處理中</option>
          <option value="2">待確認</option>
          <option value="3">取消</option>
          <option value="4">已完成</option>
       </select>	        
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="listByCompositeQuery">
     </FORM>
  </li>
</ul>
						</div>

						<div class="row">
							<div class="col-sm-12">
								
									<input type="hidden" name="action" value="getOne">
									<table class="table table-bordered dataTable" id="dataTable"
										width="100%" cellspacing="0" role="grid"
										aria-describedby="dataTable_info" style="width: 100%;">
										<thead>

											<tr role="row">
												<th class="sorting sorting_asc" tabindex="0"
													aria-controls="dataTable" rowspan="1" colspan="1"
													aria-sort="ascending"
													aria-label="Name: activate to sort column descending"
													style="">回報標題</th>
												<th class="sorting sorting_asc" tabindex="0"
													aria-controls="dataTable" rowspan="1" colspan="1"
													aria-sort="ascending"
													aria-label="Name: activate to sort column descending"
													style="">類型</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Position: activate to sort column ascending"
													style="">回報時間</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Office: activate to sort column ascending"
													style="">回報人</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Start date: activate to sort column ascending"
													style="">處理人</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Salary: activate to sort column ascending"
													style="">回報狀態</th>
											</tr>
										</thead>
										<thead>
								<%@ include file="/design/page1.file"%>
											<c:forEach var="reportVO" items="${list}" begin="<%=pageIndex%>"
										end="<%=pageIndex+rowsPerPage-1%>">
												<tr class="odd">
													<td>${reportVO.title}</td>
													<td class="sorting_1"><c:if test="${reportVO.report_type==0}">添購新品</c:if>
													<c:if test="${reportVO.report_type==1}">損壞報修</c:if>
													<c:if test="${reportVO.report_type==2}">軟硬體問題</c:if>
													<c:if test="${reportVO.report_type==3}">其他</c:if></td>
													<td><fmt:formatDate value="${reportVO.starttime}" pattern="yyyy-MM-dd HH:mm "/></td>
													<td>${reportVO.empVO1.empName}</td>
													<td>${reportVO.empVO2.empName}</td>
													<td><c:if test="${reportVO.status==0}">已發送</c:if>
													<c:if test="${reportVO.status==1}">處理中</c:if>
													<c:if test="${reportVO.status==2}">待確認</c:if>
													<c:if test="${reportVO.status==3}">取消</c:if>
													<c:if test="${reportVO.status==4}">已完成</c:if></td>
													<td><a href="/CGA101G3/reportServlet?report_id=${reportVO.report_id}&action=getOne">
													<button type="button" value="getOneReport" class="btn btn-info">查閱此回報</button></a></td>
													<td><a href="/CGA101G3/reportServlet?report_id=${reportVO.report_id}&action=getOne_forUpdate">
													<button type="button" value="getOne_forUpdate" class="btn btn-info">更新此回報</button></a></td>
												</tr>
											</c:forEach>

										</thead>
									</table>
								<%@ include file="/design/page2.file"%>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>
	</main>
	<!-- ======= Footer ======= -->
	<%@ include file="/design/frontfooter.jsp"%>
	<!-- End  Footer -->

	<!-- Vendor JS Files -->
	<%@ include file="/design/frontjs.jsp"%>

</body>
</html>