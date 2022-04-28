<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.report.model.*"%>
<%
ReportService repSvc = new ReportService();
List<ReportVO> list = repSvc.getAll();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>所有回報資料 listAllReport</title>

<!-- Favicons -->
<link href="${pageContext.request.contextPath}/assets/img/wf.png" rel="icon" />
<link href="${pageContext.request.contextPath}/assets/img/wf.png" rel="apple-touch-icon" />

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet" />

<!-- Vendor CSS Files -->
<link href="${pageContext.request.contextPath}/assets/vendor/aos/aos.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet" />
<!--     <link href="${pageContext.request.contextPath}/assets/vendor/boxicsdfdsfdsfons/css/boxicons.min.css" rel="stylesheet" /> -->
<link href="${pageContext.request.contextPath}/assets/vendor/glightbox/css/glightbox.min.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/assets/vendor/swiper/swiper-bundle.min.css"
	rel="stylesheet" />

<!-- Template Main CSS File -->
<link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet" />
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
	<header id="header" class="fixed-top">
		<div
			class="container-fluid d-flex justify-content-between align-items-center">
			<!-- <h1 class="logo me-auto me-lg-0"><a href="">WorkFun</a></h1> -->
			<!-- 公司logo圖片 -->
			<a href="Home.html" class="logo"><img
				src="${pageContext.request.contextPath}/assets/img/workfun.gif" alt="" class="img-fluid"
				width="230px"></a>

			<nav id="navbar" class="navbar order-last order-lg-0">
				<ul>
					<!-- 修改跳頁 -->
					<li>
						<div class="dropdown">
							<button class="dropbtn">揪團訂餐</button>
							<div class="dropdown-content">
								<a href="#">我要揪團</a> <a href="#">查詢揪團</a> <a href="#">查詢參團</a>
							</div>
						</div>
					</li>
					<li>
						<div class="dropdown">
							<button class="dropbtn">二手競標</button>
							<div class="dropdown-content">
								<a href="#">Link 1</a> <a href="#">Link 2</a> <a href="#">Link
									3</a>
							</div>
						</div>
					</li>
					<li>
						<div class="dropdown">
							<button class="dropbtn">問題回報</button>
							<div class="dropdown-content">
								<a href="/CGA101G3/report/addReport.jsp">新增回報</a> <a href="#">Link 2</a> <a href="#">Link
									3</a>
							</div>
						</div>
					</li>
					<li>
						<div class="dropdown">
							<button class="dropbtn">硬體預約</button>
							<div class="dropdown-content">
								<a href="#">Link 1</a> <a href="#">Link 2</a> <a href="#">Link
									3</a>
							</div>
						</div>
					</li>
					<li>
						<div class="dropdown">
							<button class="dropbtn">塗鴉牆</button>
							<div class="dropdown-content">
								<a href="#">Link 1</a> <a href="#">Link 2</a> <a href="#">Link
									3</a>
							</div>
						</div>
					</li>
				</ul>
				<i class="bi bi-list mobile-nav-toggle"></i>
			</nav>
			<!-- .navbar -->

			<div class="navbar">
				<ul>
					<li>
						<div>
							<a href=""><i class="bi bi-chat-dots"
								style="font-size: 25px; color: black"></i></a>
						</div>
					</li>
					<li>
						<div>
							<a href=""><i class="bi bi-chat"
								style="font-size: 25px; color: black"></i></a>
						</div>
					</li>
					<li>
						<div class="dropdown">
							<button class="dropbtn">
								<i class="bi bi-bell" style="font-size: 25px"></i>
							</button>
							<div class="dropdown-content nav-item-right">
								<a href="#">Link 1</a> <a href="#">Link 2</a> <a href="#">Link
									3</a>
							</div>
						</div>
					</li>
					<li>
						<div class="dropdown">
							<button class="dropbtn">
								<i class="bi bi-person" style="font-size: 25px"></i>
							</button>
							<div class="dropdown-content nav-item-right">
								<a href="#">Link 1</a> <a href="#">Link 2</a> <a href="#">Link
									3</a>
							</div>
						</div>
					</li>
				</ul>
				<!-- <a href="#" class="twitter"><i class="bi bi-twitter"></i></a>
                <a href="#" class="facebook"><i class="bi bi-facebook"></i></a> -->
				<!-- <a href="#" class="instagram"><i class="bi bi-bell"></i></a>
                <a href="#" class="linkedin"><i class="bi bi-person-fill"></i></i></a> -->
			</div>
		</div>
	</header>
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
								<div class="col-10" style="height: 60px; display: inline-block;">
						
										<div class="form-group col-2" style="display: inline-block;">
											<select class="form-control" id="exampleFormControlSelect1"
												style="border: gray solid 2px;">
												<option>選擇類型</option>
												<option>添購新品</option>
												<option>損壞報修</option>
												<option>軟硬體問題</option>
												<option>其他</option>
											</select>
										</div>
										<div id="findrepoter" style="display: inline-block;">
											<FORM METHOD="post" ACTION="/CGA101G3/report">
												<b>選擇員工編號:</b> <select size="1" name="report_id">
													<c:forEach var="repVO" items="${list}">
														<option value="${repVO.reporter}">${repVO.reporter}
													</c:forEach>
												</select> 
												<input type="hidden" name="action"value="getOne_For_Display"> 
													<input type="submit"value="送出">
											</FORM>
								</div>
							</div>
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
<%@ include file="/design/page2.file"%>
											<c:forEach var="reportVO" items="${list}">
												<tr class="odd">
													<td>${reportVO.title}</td>
													<td class="sorting_1">${reportVO.report_type}</td>
													<td>${reportVO.starttime}</td>
													<td>${reportVO.reporter}</td>
													<td>${reportVO.handler}</td>
													<td>${reportVO.status}</td>
													<td><a href="/CGA101G3/reportServlet?report_id=${reportVO.report_id}&action=getOne"><button
																type="button" value="getOneReport" class="btn btn-info">查閱此回報</button></a></td>
												</tr>
											</c:forEach>

										</thead>
									</table>
								
							</div>
						</div>

						<div class="col-12">
							<div class="dataTables_paginate paging_simple_numbers"
								id="dataTable_paginate">
								<ul class="pagination justify-content-center">
									<li class="paginate_button page-item previous disabled"
										id="dataTable_previous"><a href="#"
										aria-controls="dataTable" data-dt-idx="0" tabindex="0"
										class="page-link">Previous</a></li>
									<li class="paginate_button page-item active"><a href="#"
										aria-controls="dataTable" data-dt-idx="1" tabindex="0"
										class="page-link">1</a></li>
									<li class="paginate_button page-item "><a href="#"
										aria-controls="dataTable" data-dt-idx="2" tabindex="0"
										class="page-link">2</a></li>
									<li class="paginate_button page-item "><a href="#"
										aria-controls="dataTable" data-dt-idx="3" tabindex="0"
										class="page-link">3</a></li>
									<li class="paginate_button page-item "><a href="#"
										aria-controls="dataTable" data-dt-idx="4" tabindex="0"
										class="page-link">4</a></li>
									<li class="paginate_button page-item "><a href="#"
										aria-controls="dataTable" data-dt-idx="5" tabindex="0"
										class="page-link">5</a></li>
									<li class="paginate_button page-item "><a href="#"
										aria-controls="dataTable" data-dt-idx="6" tabindex="0"
										class="page-link">6</a></li>
									<li class="paginate_button page-item next" id="dataTable_next"><a
										href="#" aria-controls="dataTable" data-dt-idx="7"
										tabindex="0" class="page-link">Next</a></li>
								</ul>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
		</div>
	</main>
	<!-- ======= Footer ======= -->
	<footer id="footer">
		<div class="container">
			<div class="copyright">
				&copy; Copyright <strong><span>WorkFun</span></strong>. All Rights
				Reserved
			</div>
		</div>
	</footer>
	<!-- End  Footer -->

	<div id="preloader"></div>
	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<!-- Vendor JS Files -->
	<script src="${pageContext.request.contextPath}/assets/vendor/purecounter/purecounter.js"></script>
	<script src="${pageContext.request.contextPath}/assets/vendor/aos/aos.js"></script>
	<script src="${pageContext.request.contextPath}/assets/vendor/glightbox/js/glightbox.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/vendor/swiper/swiper-bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/vendor/waypoints/noframework.waypoints.js"></script>
	<script src="${pageContext.request.contextPath}/assets/vendor/php-email-form/validate.js"></script>
	<script src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/jquery/jquery.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
		integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
		crossorigin="anonymous"></script>

	<!-- Template Main JS File -->
	<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>


</body>
</html>