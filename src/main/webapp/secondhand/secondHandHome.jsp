<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.secondHand.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
SecondHandService secondHandSvc = new SecondHandService();
List<SecondHandVO> list = secondHandSvc.getAll();
pageContext.setAttribute("list", list);
%>

<%-- <% SecondHandVO secondHand = new SecondHandVO(); --%>
// secondHand.s
<%-- %>> --%>


<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>:: WorkFun ::</title>

<!-- Favicons -->
<link href="${pageContext.request.contextPath}/assets/img/wf.png"
	rel="icon">
<link href="${pageContext.request.contextPath}/assets/img/wf.png"
	rel=" apple-touch-icon">

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link
	href="${pageContext.request.contextPath}/assets/vendor/aos/aos.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/assets/vendor/boxicons/css/boxicons.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/assets/vendor/glightbox/css/glightbox.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/assets/vendor/swiper/swiper-bundle.min.css"
	rel="stylesheet">

<!-- Template Main CSS File -->
<link href="${pageContext.request.contextPath}/assets/css/style.css"
	rel="stylesheet">

<style>
.portfolio-wrap {
	width: 300px;
	height: 400px;
	display: flex
}
</style>

</head>

<body>

	<!-- ======= Header ======= -->
	<header id="header" class="fixed-top">
		<div
			class="container-fluid d-flex justify-content-between align-items-center">
			<a href="${pageContext.request.contextPath}/home/Home.html"
				class="logo"><img
				src="${pageContext.request.contextPath}/assets/img/workfun.gif"
				alt="" class="img-fluid" style="width: 250px;"></a>

			<nav id="navbar" class="navbar order-last order-lg-0">
				<ul>
					<!-- 修改跳頁 -->
					<li>
						<div class="dropdown">
							<button class="dropbtn">揪團訂餐</button>
							<div class="dropdown-content">
								<a href="${pageContext.request.contextPath}/listAllShop.jsp">瀏覽揪團</a>
								<a
									href="${pageContext.request.contextPath}/old_groupBuy/shoplist.html">我要揪團</a>
								<a href="#">查詢揪團</a>
							</div>
						</div>
					</li>
					<li>
						<div class="dropdown">
							<button class="dropbtn">二手競標</button>
							<div class="dropdown-content">
								<a href="secondHandHome.jsp">競標首頁</a> <a
									href="addSecondHand.jsp">新增競標</a> <a href="#">購買記錄</a>
							</div>
						</div>
					</li>
					<li>
						<div class="dropdown">
							<button class="dropbtn">問題回報</button>
							<div class="dropdown-content">
								<a href="#">Link 1</a> <a href="#">Link 2</a> <a href="#">Link
									3</a>
							</div>
						</div>
					</li>
					<li>
						<div class="dropdown">
							<button class="dropbtn">器材預約</button>
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

				<nav class="navbar navbar-light bg-light"
					style="margin-left: 120px;"></nav>

				<!-- 右邊icon -->
				<div class="navbar">
					<ul>
						<li><a href=""> <i class="bi bi-chat-dots"
								style="font-size: 25px; color: black;"></i>
						</a></li>
						<li><a href=""><i class="bi bi-chat"
								style="font-size: 25px; color: black;"></i></a></li>
						<li>
							<div class="dropdown">
								<i class="bi bi-bell" style="font-size: 25px"></i>
								<div class="dropdown-content nav-item-right">
									<a href="#">訊息一</a> <a href="#">訊息二</a> <a href="#">訊息三</a>
								</div>
							</div>
						</li>

						<!-- 個人頁面dropdown -->
						<li class="nav-item dropdown pe-3"><a
							class="nav-link nav-profile d-flex align-items-center pe-0 ; width:2px"
							href="#" data-bs-toggle="dropdown"> <img
								src="${pageContext.request.contextPath}/assets/img/wu.jpeg"
								alt="Profile" class="rounded-circle" style="width: 50px;">
						</a> <!-- End Profile Iamge Icon -->

							<ul
								class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile show"
								style="position: absolute; inset: 0px 0px auto auto; margin: 15px; transform: translate3d(-16px, 54px, 0px);"
								data-popper-placement="bottom-end">
								<li class="dropdown-header">
									<h6>Peter Wu</h6> <span>Java Consultant</span>
								</li>
								<li>
									<hr class="dropdown-divider">
								</li>
								<li><a class="dropdown-item d-flex align-items-center"
									href="#"> <i class="bi bi-person"></i> <span>My
											Profile</span>
								</a></li>
								<li>
									<hr class="dropdown-divider">
								</li>
								<li><a class="dropdown-item d-flex align-items-center"
									href="#"> <i class="bi bi-gear"></i> <span>Account
											Settings</span>
								</a></li>
								<li>
									<hr class="dropdown-divider">
								</li>
								<li><a class="dropdown-item d-flex align-items-center"
									href="#"> <i class="bi bi-box-arrow-right"></i> <span>Sign
											Out</span>
								</a></li>

							</ul></li>
						<!-- End Profile Nav -->

					</ul>
				</div>
		</div>

	</header>
	<!-- End Header -->

	<!-- content 如果頁面要可以往下滑就改一下main的height值吧 -->
	<main id="main" style="height: 200vh;">

		<section id="portfolio" class="portfolio">
			<div class="container" data-aos="fade-up">

				<div class="section-title">
					<h2>二手商品總覽</h2>

				</div>

				<div class="row" style="justify-content: end;">
					<div class="col-10"
						style="height: 60px; display: inline-block; text-align: right;">
						<form class="my-1">
							<%@ include file="/design/page1.file"%>
							<div class="form-group col-2" style="display: inline-block;">
								<jsp:useBean id="secondHandSvc1" scope="page"
									class="com.secondHand.model.SecondHandService" />
								<select class="form-control" id="exampleFormControlSelect1"
									style="border: gray solid 2px;" name="is_deal">
									<c:forEach var="secondHandVO" items="${secondHandSvc1.all}">
										<option value="${secondHandVO.is_deal}"
											${(param.deptno==deptVO.deptno)? 'selected':'' }>${deptVO.deptno}
									</c:forEach>


									<option>選擇類型</option>
									<option>競標中</option>
									<option>已成交</option>
									<option>顯示全部</option>
								</select>
							</div>
							<div class="form-group col-3" style="display: inline-block">
								<input type="text" class="form-control"
									id="exampleFormControlInput1" placeholder="輸入關鍵字"
									style="border: gray solid 2px;">
							</div>
							<button type="submit" class="btn btn-primary mb-2 mt-1 col"
								style="display: inline-block;">搜尋</button>

						</form>
					</div>
				</div>

				<div class="row portfolio-container" data-aos="fade-up"
					data-aos-delay="200">
					<c:forEach var="secondHandVO" items="${list}"
						begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<div class="col-lg-4 col-md-6 portfolio-item filter-card">
							<div class="portfolio-wrap">
								<%-- 下面是base64寫法 --%>
								<img src="data:image/png;base64, ${secondHandVO.img1}"
									<%-- 上面是base64寫法 --%>
									class="img-fluid"
									alt"" style="max-height: 100%; max-width: 100%; width: auto; height: auto; position: absolute; top: 0; bottom: 0; left: 0; right: 0; margin: auto;">
								<div class="portfolio-info">
									<h4>${secondHandVO.name}</h4>
									<p>[競標截止時間 ${secondHandVO.end_time}]</p>
									<div class="portfolio-links">
										<a href="${pageContext.request.contextPath}/groupbuy/buy.html"
											class="portfolio-details-lightbox"
											data-glightbox="type: external" title="參與競標"> <i
											class="bx bx-link"></i></a>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>

				<%@ include file="/design/page2.file"%>
		</section>
	</main>
	<!-- End #main -->
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
	<script
		src="${pageContext.request.contextPath}/assets/vendor/purecounter/purecounter.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/vendor/aos/aos.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/vendor/glightbox/js/glightbox.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/vendor/swiper/swiper-bundle.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/vendor/waypoints/noframework.waypoints.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/vendor/php-email-form/validate.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/js/jquery/jquery.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
		integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
		crossorigin="anonymous"></script>

	<!-- Template Main JS File -->
	<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>

</body>

</html>