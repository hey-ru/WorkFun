<%@page import="com.emp.model.EmpVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.List"%>
<%@ page import="com.groupbuy.model.*"%>
<%@ page import="com.groupbuylist.model.*"%>

<%
EmpVO empVO = (EmpVO)session.getAttribute("empVO");
GroupBuyService gbSvc = new GroupBuyService();
List<GroupBuyVO> list = gbSvc.getMyGBAll(empVO.getEmpId());
pageContext.setAttribute("list", list);

// int itemsPerPage = 10;
%>

<!DOCTYPE html>
<html lang="zh-TW">
<head>
<%@ include file="/design/frontmetacss.jsp"%>
    <style>
        .table-responsive {
    	overflow-x: hidden;
	}
    </style>

</head>

<body>
	<div class="wrapper">

		<%@ include file="/design/frontheader.jsp"%>

		<!-- ====================== 內容開始 ====================== -->
		<main id="main" class="main">
			<div class="card shadow mb-4">
				<!-- ============== Card Header ============== -->
				<div class="card-header py-3" style="background-color: #FFCC99">
					<div class="row">
						<div class="col-9" style="height: 20px; display: inline-block;">
							<h5>
								<strong>查詢揪團</strong>
							</h5>
						</div>
						<div class="col-3" style="height: 20px; display: inline-block;">
							<a href="<%=request.getContextPath()%>/groupbuy/gbHome.jsp"><strong>回揪團主頁</strong></a>
						</div>
					</div>
				</div>

				<!-- ============== Card Body ============== -->
				<div class="card-body">
					<div class="table-responsive">
					
					<div class="col-11 row" style="height: 60px; display: inline-block;">
							<div class = col-8>
							<ul class="nav nav-tabs">
								  <li class="nav-item">
								    <a class="nav-link text-info" 
								    href="<%=request.getContextPath()%>/groupbuy/GroupBuyOwnerServlet?action=listByCompositeQuery&gb_owner=${empVO.empId}">ALL</a>
								  </li>
								  <li class="nav-item">
								    <a class="nav-link text-info"
								     href="<%=request.getContextPath()%>/groupbuy/GroupBuyOwnerServlet?action=listByCompositeQuery&gb_status=0&gb_owner=${empVO.empId}">揪團中</a>
								  </li>
								  <li class="nav-item">
								    <a class="nav-link text-info" href="<%=request.getContextPath()%>/groupbuy/GroupBuyOwnerServlet?action=listByCompositeQuery&gb_status=2&gb_owner=${empVO.empId}">截止</a>
								  </li>
								  <li class="nav-item">
								    <a class="nav-link text-info" href="<%=request.getContextPath()%>/groupbuy/GroupBuyOwnerServlet?action=listByCompositeQuery&gb_status=3&gb_owner=${empVO.empId}">揪團關閉</a>
								  </li>								  
								</ul>
								</div>
								</div>
						<div id="dataTable_wrapper"
							class="dataTables_wrapper dt-bootstrap4">

							<div class="row">
								<div class="col-sm-12">
									<table class="table table-bordered dataTable" id="dataTable"
										width="100%" cellspacing="0" role="grid"
										aria-describedby="dataTable_info" style="width: 100%">
										<!-- ========================= 表頭 ========================= -->
										<thead>
											<tr role=" row">
												<th class="sorting sorting_asc" tabindex="0"
													aria-controls="dataTable" rowspan="1" colspan="1"
													aria-sort="ascending"
													  
													>揪團編號</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													 
													>店家名稱</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													 
													>開始時間</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													 
													>結束時間</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													 
													>到貨時間</th>
<!-- 												<th class="sorting" tabindex="0" aria-controls="dataTable" -->
<!-- 													rowspan="1" colspan="1" -->
<!-- 													  -->
<!-- 													>低消限制</th> -->
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													 
													>狀態</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													 
													style="width: 50px;"></th>
<!-- 												<th class="sorting" tabindex="0" aria-controls="dataTable" -->
<!-- 													rowspan="1" colspan="1" -->
<!-- 													  -->
<!-- 													style="width: 50px;"></th> -->
											</tr>
										</thead>


<%-- 											<%@ include file="/design/page1.file"%> --%>

										<!-- ========================= 表格內容 ========================= -->
										<tbody>
										<c:forEach var="groupBuyVO" items="${list}">
<%-- 											<c:forEach var="groupBuyVO" items="${list}" begin="<%=pageIndex%>" --%>
<%-- 										end="<%=pageIndex+rowsPerPage-1%>"> --%>
										<c:if test="${groupBuyVO.gb_status != 1}">
													<tr>
														<td>${groupBuyVO.gb_id}</td>
														<td>${groupBuyVO.shop_name}</td>
														<td><fmt:formatDate value="${groupBuyVO.start_time}" pattern="yyyy-MM-dd HH:mm"/>
														</td>
														<td><fmt:formatDate value="${groupBuyVO.end_time}" pattern="yyyy-MM-dd HH:mm"/>
														</td>
														<td><fmt:formatDate value="${groupBuyVO.arr_time}" pattern="yyyy-MM-dd HH:mm"/>
														</td>
<%-- 														<td>${groupBuyVO.min_amt}</td> --%>
														<td><c:choose>
														    <c:when test="${groupBuyVO.gb_status == 0}">
														       	揪團中
														    </c:when>
														    <c:when test="${groupBuyVO.gb_status == 1}">
														       	取消
														    </c:when>
														    <c:when test="${groupBuyVO.gb_status == 2}">
														       	揪團截止
														    </c:when>
														    <c:when test="${groupBuyVO.gb_status == 3}">
														       	揪團關閉
														    </c:when>
														</c:choose>
														</td>
														<td>
														<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/groupbuy/GroupBuyServlet"
																style="margin-bottom: 0px;">
														<input type="submit" class="btn btn-info" value="詳細" style="margin-bottom: 0px;"> 
														<input type="hidden" name="gb_id" value="${groupBuyVO.gb_id}">
														<input type="hidden" name="action" value="getOne_For_Display">										
														</FORM>															
														</td>

													</tr>
													</c:if>

												</c:forEach>
										</tbody>
									</table>
								</div>
							</div>

<%-- 							<%@ include file="/design/page2.file"%> --%>



						</div>
					</div>
				</div>
			</div>
		</main>
		<!-- ======= 內容結束 ======= -->

	</div>
	<!-- ======= Footer ======= -->
	<%@ include file="/design/frontfooter.jsp"%>
	<!-- ======= js ======= -->
	<%@ include file="/design/frontjs.jsp"%>
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>

</body>


</html>