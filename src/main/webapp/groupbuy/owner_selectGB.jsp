<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.groupbuy.model.*"%>
<%@ page import="com.groupbuylist.model.*"%>

<%
//只能查詢個人參團紀錄
Integer gb_owner = Integer.valueOf(request.getParameter("gb_owner"));
GroupBuyService gbSvc = new GroupBuyService();
List<GroupBuyVO> list = gbSvc.getMyGBAll(gb_owner);
pageContext.setAttribute("list", list);

int itemsPerPage = 6;
%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/design/frontmetacss.jsp"%>

</head>

<body>
	<div class="wrapper">

		<%@ include file="/design/frontheader.jsp"%>

		<!-- ====================== 內容開始 ====================== -->
		<main id="main" class="main">
			<div class="card shadow mb-4">
				<!-- ============== Card Header ============== -->
				<div class="card-header py-3" style="background-color: #b0c4de">
					<div class="row">
						<div class="col-11" style="height: 20px; display: inline-block;">
							<h5>
								<strong>查詢揪團</strong>
							</h5>
						</div>
						<div class="col-1" style="height: 20px; display: inline-block;">
							<a href="<%=request.getContextPath()%>/groupbuy/gbHome.jsp"><strong>回揪團主頁</strong></a>
						</div>
					</div>
				</div>

				<!-- ============== Card Body ============== -->
				<div class="card-body">
					<div class="table-responsive">
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
													aria-label="Name: activate to sort column descending"
													style="width: 50px;">揪團編號</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Position: activate to sort column ascending"
													style="width: 50px;">店家名稱</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Position: activate to sort column ascending"
													style="width: 50px;">開始時間</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Office: activate to sort column ascending"
													style="width: 50px;">結束時間</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Office: activate to sort column ascending"
													style="width: 50px;">到貨時間</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Office: activate to sort column ascending"
													style="width: 50px;">低消限制</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Salary: activate to sort column ascending"
													style="width: 50px;">狀態</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Salary: activate to sort column ascending"
													style="width: 50px;"></th>
<!-- 												<th class="sorting" tabindex="0" aria-controls="dataTable" -->
<!-- 													rowspan="1" colspan="1" -->
<!-- 													aria-label="Salary: activate to sort column ascending" -->
<!-- 													style="width: 50px;"></th> -->
											</tr>
										</thead>


											<%@ include file="/design/page1.file"%>

										<!-- ========================= 表格內容 ========================= -->
										<tbody>
											<c:forEach var="groupBuyVO" items="${list}" begin="<%=pageIndex%>"
										end="<%=pageIndex+rowsPerPage-1%>">
													<tr>
														<td><c:out value="${groupBuyVO.gb_id}" /></td>
														<td><c:out value="${groupBuyVO.shop_name}" /></td>
														<td><c:out value="${groupBuyVO.start_time}" /></td>
														<td><c:out value="${groupBuyVO.end_time}" /></td>
														<td><c:out value="${groupBuyVO.arr_time}" /></td>
														<td><c:out value="${groupBuyVO.min_amt}" /></td>
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
														    <c:when test="${groupBuyVO.gb_status == 2}">
														       	揪團關閉
														    </c:when>
														</c:choose>
														</td>
<%-- 														<td><c:out value="${groupBuyVO.gb_status}" /></td> --%>
														<td><a href="buyer_3_updateGb.html"><button
																	type="button" class="btn btn-success btn-sm">詳細</button></a></td>
<!-- 														<td><input type="submit" -->
<!-- 															class="btn btn-secondary btn-sm" value="退出"></td> -->
													</tr>
													<!-- 揪團截止不能取消及編輯 -->
												</c:forEach>
										</tbody>
									</table>
								</div>
							</div>

							<%@ include file="/groupbuy/page2.file"%>



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
</body>

</html>