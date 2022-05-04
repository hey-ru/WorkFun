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
<title>backListAll</title>
<!-- Custom fonts for this template-->
<link
	href="${pageContext.request.contextPath}/assets/css/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link
	href="${pageContext.request.contextPath}/assets/css/sb-admin-2.min.css"
	rel="stylesheet">

</head>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<ul class="navbar-nav bg-dark sidebar sidebar-dark accordion"
			id="accordionSidebar">

			<!-- Nav Item - Pages Collapse Menu第一項 -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseTwo"
				aria-expanded="true" aria-controls="collapseTwo"> <i
					class="fas fa-fw fa-cog"></i> <span>部門群組管理</span>
			</a>
				<div id="collapseTwo" class="collapse" aria-labelledby="headingTwo"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<!-- <h6 class="collapse-header">Custom Components:</h6> -->
						<a class="collapse-item" href="button1s.html">新增部門</a> <a
							class="collapse-item" href="cards.html">修改部門</a> <a
							class="collapse-item" href="cards.html">查詢部門</a> <a
							class="collapse-item" href="cards.html">刪除部門</a>
					</div>
				</div></li>
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseThree"
				aria-expanded="true" aria-controls="collapseTwo"> <i
					class="fas fa-fw fa-cog"></i> <span>員工帳號管理</span>
			</a>
				<div id="collapseThree" class="collapse"
					aria-labelledby="headingTwo" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<!-- <h6 class="collapse-header">Custom Components:</h6> -->
						<a class="collapse-item" href="buttons.html">新增帳號</a> <a
							class="collapse-item" href="cards.html">修改帳號</a> <a
							class="collapse-item" href="cards.html">查詢帳號</a> <a
							class="collapse-item" href="cards.html">刪除帳號</a>
					</div>
				</div></li>
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseFour"
				aria-expanded="true" aria-controls="collapseTwo"> <i
					class="fas fa-fw fa-cog"></i> <span>公告管理</span>
			</a>
				<div id="collapseFour" class="collapse" aria-labelledby="headingTwo"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<!-- <h6 class="collapse-header">Custom Components:</h6> -->
						<a class="collapse-item" href="buttons.html">新增公告</a> <a
							class="collapse-item" href="cards.html">修改公告</a> <a
							class="collapse-item" href="cards.html">查詢公告</a> <a
							class="collapse-item" href="cards.html">刪除公告</a>
					</div>
				</div></li>
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseFive"
				aria-expanded="true" aria-controls="collapseTwo"> <i
					class="fas fa-fw fa-cog"></i> <span>回報管理</span>
			</a>
				<div id="collapseFive" class="collapse" aria-labelledby="headingTwo"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<!-- <h6 class="collapse-header">Custom Components:</h6> -->


						<a class="collapse-item" href="cards.html">查詢任務</a> <a
							class="collapse-item" href="cards.html">刪除任務</a>
					</div>
				</div></li>
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseSeven"
				aria-expanded="true" aria-controls="collapseTwo"> <i
					class="fas fa-fw fa-cog"></i> <span>預約管理</span>
			</a>
				<div id="collapseSeven" class="collapse"
					aria-labelledby="headingTwo" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<!-- <h6 class="collapse-header">Custom Components:</h6> -->
						<a class="collapse-item" href="buttons.html">查詢硬體</a> <a
							class="collapse-item" href="cards.html">新增硬體</a> <a
							class="collapse-item" href="cards.html">刪除硬體</a> <a
							class="collapse-item" href="cards.html">修改硬體狀態</a> <a
							class="collapse-item" href="cards.html">查詢預約紀錄</a> <a
							class="collapse-item" href="cards.html">違規處理</a>

					</div>
				</div></li>
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseSex"
				aria-expanded="true" aria-controls="collapseTwo"> <i
					class="fas fa-fw fa-cog"></i> <span>揪團店家管理</span>
			</a>
				<div id="collapseSex" class="collapse" aria-labelledby="headingTwo"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<!-- <h6 class="collapse-header">Custom Components:</h6> -->
						<a class="collapse-item" href="buttons.html">新增店家</a> <a
							class="collapse-item" href="cards.html">修改店家</a> <a
							class="collapse-item" href="cards.html">刪除店家</a>
					</div>
				</div></li>
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseEight"
				aria-expanded="true" aria-controls="collapseTwo"> <i
					class="fas fa-fw fa-cog"></i> <span>塗鴉牆管理</span>
			</a>
				<div id="collapseEight" class="collapse"
					aria-labelledby="headingTwo" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<!-- <h6 class="collapse-header">Custom Components:</h6> -->

						<a class="collapse-item" href="cards.html">查詢貼文</a> <a
							class="collapse-item" href="cards.html">刪除貼文</a>
					</div>
				</div></li>
			<!-- Nav Item - Utilities Collapse Menu第二項 -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseUtilities"
				aria-expanded="true" aria-controls="collapseUtilities"> <i
					class="fas fa-fw fa-wrench"></i> <span>Utilities</span>
			</a>
				<div id="collapseUtilities" class="collapse"
					aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">Custom Utilities:</h6>
						<a class="collapse-item" href="utilities-color.html">Colors</a> <a
							class="collapse-item" href="utilities-border.html">Borders</a> <a
							class="collapse-item" href="utilities-animation.html">Animations</a>
						<a class="collapse-item" href="utilities-other.html">Other</a>
					</div>
				</div> <!-- Nav Item - Pages Collapse Menu第三項 -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapsePages"
				aria-expanded="true" aria-controls="collapsePages"> <i
					class="fas fa-fw fa-folder"></i> <span>Pages</span>
			</a>
				<div id="collapsePages" class="collapse"
					aria-labelledby="headingPages" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">Login Screens:</h6>
						<a class="collapse-item" href="login.html">Login</a> <a
							class="collapse-item" href="register.html">Register</a> <a
							class="collapse-item" href="forgot-password.html">Forgot
							Password</a>
						<div class="collapse-divider"></div>
						<h6 class="collapse-header">Other Pages:</h6>
						<a class="collapse-item" href="404.html">404 Page</a> <a
							class="collapse-item" href="blank.html">Blank Page</a>
					</div>
				</div> <!-- Nav Item - Tables第四項 -->
			<li class="nav-item"><a class="nav-link" href="tables.html">
					<i class="fas fa-fw fa-table"></i> <span>Tables</span>
			</a></li>

		</ul>

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<nav
					class="navbar navbar-expand navbar-light bg-dark topbar mb-4 static-top shadow">

					<!-- Sidebar Toggle (Topbar) -->

					<button id="sidebarToggleTop"
						class="btn btn-link d-md-none rounded-circle mr-3">
						<i class="fa fa-bars"></i>
					</button>


					<!-- Topbar Search 搜尋列結束 -->

					<!-- Topbar Navbar -->
					<ul class="navbar-nav bg-dark ml-auto">

						<!-- Nav Item - User Information -->
						<li class="nav-item no-arrow"><a href="#"> <i
								class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
								Logout
						</a></li>

					</ul>

				</nav>
				<!-- End of Topbar -->

					<!-- content 如果頁面要可以往下滑就改一下main的height值吧 -->
					<main style="height: 130vh;">
						<!-- 從這裡開始 -->
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
												<div class="col-sm-12">

													<input type="hidden" name="action" value="getOne">
													<table class="table table-bordered dataTable"
														id="dataTable"  role="grid"
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
																<th class="sorting" tabindex="0"
																	aria-controls="dataTable" rowspan="1" colspan="1"
																	aria-label="Position: activate to sort column ascending"
																	style="">回報時間</th>
																<th class="sorting" tabindex="0"
																	aria-controls="dataTable" rowspan="1" colspan="1"
																	aria-label="Office: activate to sort column ascending"
																	style="">最新處理時間</th>
																<th class="sorting" tabindex="0"
																	aria-controls="dataTable" rowspan="1" colspan="1"
																	aria-label="Start date: activate to sort column ascending"
																	style="">回報人</th>
																<th class="sorting" tabindex="0"
																	aria-controls="dataTable" rowspan="1" colspan="1"
																	aria-label="Salary: activate to sort column ascending"
																	style="">回報狀態</th>
															</tr>
														</thead>
														<thead>
															<%@ include file="/design/page1.file"%>
															<c:forEach var="reportVO" items="${list}"
																begin="<%=pageIndex%>"
																end="<%=pageIndex+rowsPerPage-1%>">
																<tr class="odd">
																	<td>${reportVO.title}</td>
																	<td class="sorting_1"><c:if
																			test="${reportVO.report_type==0}">添購新品</c:if> <c:if
																			test="${reportVO.report_type==1}">損壞報修</c:if> <c:if
																			test="${reportVO.report_type==2}">軟硬體問題</c:if> <c:if
																			test="${reportVO.report_type==3}">其他</c:if></td>
																	<td><fmt:formatDate value="${reportVO.starttime}"
																			pattern="yyyy-MM-dd HH:mm " /></td>
																	<td><fmt:formatDate value="${reportVO.updatetime}"
																	pattern="yyyy-MM-dd HH:mm " /></td>
																	<td>${reportVO.reporter}</td>
																	<td><c:if test="${reportVO.status==0}">已發送</c:if>
																		<c:if test="${reportVO.status==1}">處理中</c:if> <c:if
																			test="${reportVO.status==2}">待確認</c:if> <c:if
																			test="${reportVO.status==3}">取消</c:if> <c:if
																			test="${reportVO.status==4}">已完成</c:if></td>
																	<td><a
																		href="/CGA101G3/reportCommentServlet?report_id=${reportVO.report_id}&action=getOne_forUpdate">
																			<button type="button" value="getOne_forUpdate"
																				class="btn btn-info">處理此回報</button>
																	</a></td>
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

				</div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->

		</div>
		<!-- End of Content Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Bootstrap core JavaScript-->
	<script
		src="${pageContext.request.contextPath}/assets/js/jquery/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script
		src="${pageContext.request.contextPath}/assets/js/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script
		src="${pageContext.request.contextPath}/assets/js/sb-admin-2.min.js"></script>



</body>

</html>