<%@page import="java.util.List"%>
<%@page import="com.booking.model.*"%>
<%@page import="com.emp.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%
EmpVO empVO = (EmpVO) (request.getSession().getAttribute("empVO"));
BookingService bookingSvc = new BookingService();
List<BookingVO> list = bookingSvc.getByEmpId(empVO.getEmpId());
pageContext.setAttribute("list", list);
%>


<html>
<head>
<%@ include file="/design/frontmetacss.jsp"%>

<style>
.col-sm-6 {
	flex: 0 0 auto;
	width: 100%;
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
				<div class="card-header py-3" style="background-color: #b0c4de">
					<div class="row">
						<div class="col-11" style="height: 20px; display: inline-block;">
							<h5>
								<strong>我的預約單</strong>
							</h5>
						</div>
						<div class="col-1" style="height: 20px; display: inline-block;">
							<a
								href="<%=request.getContextPath()%>/booking/frontBookingHome.jsp"><strong>回預約器材</strong></a>
						</div>
					</div>
				</div>

				<!-- ============== Card Body ============== -->
				<div class="card-body">
					<div class="table-responsive">
						<div id="dataTable_wrapper"
							class="dataTables_wrapper dt-bootstrap4">

							<div class="row">
								<div class="col-sm-6">
									<table class="table table-bordered dataTable" id="dataTable"
										width="100%" cellspacing="0" role="grid"
										aria-describedby="dataTable_info" style="width: 100%">

										<!-- ============== 表頭(自行增減修改) ============== -->
										<thead>
											<tr role=" row">
												<th class="sorting sorting_asc" tabindex="0"
													aria-controls="dataTable" rowspan="1" colspan="1"
													aria-sort="ascending"
													aria-label="Name: activate to sort column descending"
													style="width: 100px;">預約單編號</th>

												<th class="sorting sorting_asc" tabindex="0"
													aria-controls="dataTable" rowspan="1" colspan="1"
													aria-sort="ascending"
													aria-label="Name: activate to sort column descending"
													style="width: 80px;">員工編號</th>

												<th class="sorting sorting_asc" tabindex="0"
													aria-controls="dataTable" rowspan="1" colspan="1"
													aria-sort="ascending"
													aria-label="Name: activate to sort column descending"
													style="width: 80px;">器材名稱</th>

												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Position: activate to sort column ascending"
													style="width: 100px;">預約起訖日</th>

												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Position: activate to sort column ascending"
													style="width: 100px;">預約截止日</th>

												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Position: activate to sort column ascending"
													style="width: 80px;">預約狀態</th>

												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Position: activate to sort column ascending"
													style="width: 80px;">逾期天數</th>

												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Position: activate to sort column ascending"
													style="width: 80px;">逾期罰金</th>

												<!-- <th class="sorting" tabindex="0" aria-controls="dataTable" -->
												<!-- rowspan="1" colspan="1" -->
												<!-- aria-label="Office: activate to sort column ascending" -->
												<!-- style="width: 20px;">修改</th> -->
											</tr>

										</thead>


										<!-- ============== 表格內容(自行增減修改) ============== -->

										<c:forEach var="bookingVO" items="${list}">

											<tr>
												<td>${bookingVO.bookingId}</td>
												<td>${empVO.empId}</td>
												<td>${bookingVO.equipmentVO.eqName}</td>
												<td><fmt:formatDate value="${bookingVO.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
												<td><fmt:formatDate value="${bookingVO.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>

												<td><c:choose>
														<c:when test="${bookingVO.returnStatus == 0}">已歸還</c:when>
														<c:when test="${bookingVO.returnStatus == 1}">租借中</c:when>
														<c:when test="${bookingVO.returnStatus == 2}">未領取器材</c:when>
														<c:when test="${bookingVO.returnStatus == 3}">逾期歸還(需罰金)</c:when>
														<c:when test="${bookingVO.returnStatus == 4}">未歸還(需罰金)</c:when>
														<c:when test="${bookingVO.returnStatus == 5}">已登記預約</c:when>
													</c:choose></td>

												<%-- 											<td>${bookingVO.returnStatus}</td> --%>

												<td>${bookingVO.overdueDate}</td>
												<td>${bookingVO.overduePrice}</td>
											</tr>

										</c:forEach>

									</table>

								</div>
							</div>
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