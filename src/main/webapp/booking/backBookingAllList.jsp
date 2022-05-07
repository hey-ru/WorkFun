<%@page import="java.util.List"%>
<%@page import="com.booking.model.BookingService"%>
<%@page import="com.booking.model.BookingVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
BookingService bookingSvc = new BookingService();
List<BookingVO> list = bookingSvc.getAll();
pageContext.setAttribute("list", list);
int itemsPerPage = 10;
%>

<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/design/backcss.jsp"%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>WorkFunBack</title>

<style>
.col-sm-6 {
	flex: 0 0 100%;
	max-width: 100%;
}
</style>

</head>

<body id="page-top">

	<!-- Page Wrapper -->

	<div id="wrapper">

		<!-- Sidebar -->
		<%@ include file="/design/backSidebar.jsp"%>
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<nav
					class="navbar navbar-expand navbar-light bg-dark topbar mb-4 static-top shadow">

					<!-- Topbar Navbar -->
					<ul class="navbar-nav bg-dark ml-auto">

						<!-- Nav Item - User Information -->
						<li class="nav-item no-arrow"><a
							href="<%=request.getContextPath()%>/home/home.jsp"> <i
								class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
								Back Home
						</a></li>

					</ul>

				</nav>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<!-- 				<div class="container-fluid"> -->
				<!-- 					內容放這 -->

				<!-- 					<div class="row"> -->
				<!-- 						<div class="col-10" style="height: 60px; display: inline-block;"> -->
				<!-- 							<form class="my-1"> -->

				<!-- 								<div class="form-group col-2" style="display: inline-block;"> -->
				<%-- 									<jsp:useBean id="booking1" scope="page" --%>
				<%-- 										class="com.booking.model.BookingService" /> --%>
				<!-- 									<select class="form-control" id="exampleFormControlSelect1" -->
				<!-- 										style="border: gray solid 2px;" name="returnStatus"> -->
				<!-- 										<option value="5">已登記預約</option> -->

				<!-- 										<option value="0">已歸還</option> -->
				<!-- 										<option value="1">租借中</option> -->
				<!-- 										<option value="2">未領取器材</option> -->
				<!-- 										<option value="3">逾期歸還</option> -->
				<!-- 										<option value="4">未歸還</option> -->
				<!-- 									</select> -->
				<!-- 								</div> -->

				<!-- 								<div class="form-group col-3" style="display: inline-block"> -->
				<!-- 									<input type="text" class="form-control" -->
				<!-- 										id="exampleFormControlInput1" placeholder="輸入預約單編號" -->
				<!-- 										style="border: gray solid 2px;" name="bookingId" -->
				<%-- 										value="${param.bookingId}"> --%>
				<!-- 								</div> -->

				<%-- 								<input type="hidden" name="action" value="listSecondHands_ByCompositeQuery"> --%>
				<!-- 								<input type="hidden" name="action" value="listByCompositeQuery"> -->
				<!-- 								<input type="submit" class="btn btn-primary mb-2 mt-1 col" -->
				<!-- 									style="display: inline-block;" value="搜尋"></input> -->
				<!-- 							</form> -->
				<!-- 						</div> -->
				<!-- 					</div> -->
				<!-- 				</div> -->


				<div class="card-body">
					<div class="table-responsive">
						<div id="dataTable_wrapper"
							class="dataTables_wrapper dt-bootstrap4">

							<div class="row">
								<div class="col-sm-6">
									<table class="table table-bordered dataTable" id="dataTable"
										role="grid" cellspacing="0" aria-describedby="dataTable_info"
										style="width: 100%;">

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

											</tr>

										</thead>

										<%@ include file="/design/page1.file"%>
										<tbody>
											<c:forEach var="bookingVO" items="${list}"
												begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

												<tr>
													<td>${bookingVO.bookingId}</td>
													<td>${bookingVO.empId}</td>
													<td>${bookingVO.equipmentVO.eqName}</td>
													<td>${bookingVO.startDate}</td>
													<td>${bookingVO.endDate}</td>

													<%-- 													<td><c:choose> --%>
													<%-- 															<c:when test="${bookingVO.returnStatus == 5}">已登記預約</c:when> --%>
													<%-- 															<c:when test="${bookingVO.returnStatus == 0}">已歸還</c:when> --%>
													<%-- 															<c:when test="${bookingVO.returnStatus == 1}">租借中</c:when> --%>
													<%-- 															<c:when test="${bookingVO.returnStatus == 2}">未領取器材</c:when> --%>
													<%-- 															<c:when test="${bookingVO.returnStatus == 3}">逾期歸還(需罰金)</c:when> --%>
													<%-- 															<c:when test="${bookingVO.returnStatus == 4}">未歸還(需罰金)</c:when> --%>
													<%-- 														</c:choose></td> --%>

													<form method="post"
														action="<%=request.getContextPath()%>/booking/booking.do">

														<td><label for="inputNumber"
															class="col-sm-2 col-form-label"></label>
															<div class="col-sm-10">
																<select name="returnStatus" id="">
																	<option value="0"
																		${(bookingVO.returnStatus==0)? 'selected':'' }>已歸還</option>
																	<option value="1"
																		${(bookingVO.returnStatus==1)? 'selected':'' }>租借中</option>
																	<option value="2"
																		${(bookingVO.returnStatus==2)? 'selected':'' }>未領取器材</option>
																	<option value="3"
																		${(bookingVO.returnStatus==3)? 'selected':'' }>逾期歸還(需罰金)</option>
																	<option value="4"
																		${(bookingVO.returnStatus==4)? 'selected':'' }>未歸還(需罰金)</option>
																	<option value="5"
																		${(bookingVO.returnStatus==5)? 'selected':'' }>已登記預約</option>
																</select> ${errorMsgs.returnStatus}
															</div></td>


														<td>${bookingVO.overdueDate}</td>
														<td>${bookingVO.overduePrice}</td>


														<td>
															<div class="row mb-3">
																<input type="hidden" name="action"
																	value="updateReturnStatus"> <input
																	type="hidden" name="bookingId"
																	value="${bookingVO.bookingId}"> <input
																	type="hidden" name="returnStatus"
																	value="${bookingVO.returnStatus}"> <input
																	type="submit" value="儲存"
																	class="btn btn-dark mb-2 mt-1 col"
																	style="background-color: #007FFF; color: #FFFFFF; font-weight: bold;">

															</div>
														</td>
													</form>

												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- End of Main Content -->
		</div>
		<!-- End of Content Wrapper -->
	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<%@ include file="/design/backjs.jsp"%>
</body>

</html>