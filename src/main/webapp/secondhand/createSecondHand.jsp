<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>:: WorkFun ::</title>

<!-- Favicons -->
<link href="${pageContext.request.contextPath}/assets/img/wf.png" rel="icon">
<link href="${pageContext.request.contextPath}/assets/img/wf.png" rel=" apple-touch-icon">

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="${pageContext.request.contextPath}/assets/vendor/aos/aos.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/vendor/boxicons/css/boxicons.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/vendor/glightbox/css/glightbox.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/vendor/swiper/swiper-bundle.min.css"
	rel="stylesheet">

<!-- Template Main CSS File -->
<link href="${pageContext.request.contextPath}/old_groupBuy/style_gb.css" rel="stylesheet">

</head>

<body>

	<!-- ======= Header ======= -->
	<header id="header" class="fixed-top">
		<div
			class="container-fluid d-flex justify-content-between align-items-center">
			<a href="${pageContext.request.contextPath}/home/Home.html" class="logo"><img
				src="${pageContext.request.contextPath}/assets/img/workfun.gif" alt="" class="img-fluid"
				style="width: 250px;"></a>

			<nav id="navbar" class="navbar order-last order-lg-0">
				<ul>
					<!-- 修改跳頁 -->
					<li>
						<div class="dropdown">
							<button class="dropbtn">揪團訂餐</button>
							<div class="dropdown-content">
								<a href="./gbhome.html">瀏覽揪團</a> <a href="./shoplist.html">我要揪團</a>
								<a href="#">查詢揪團</a> <a href="#">查詢參團</a>

							</div>
						</div>
					</li>
					<li>
						<div class="dropdown">
							<button class="dropbtn">二手競標</button>
							<div class="dropdown-content">
								<a href="secondHandHome.jsp">競標首頁</a> <a
									href="createSecondHand.jsp">新增競標</a> <a href="#">購買記錄</a>
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

				<!-- 左邊icon -->
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
								src="${pageContext.request.contextPath}/assets/img/wu.jpeg" alt="Profile" class="rounded-circle"
								style="width: 50px;">
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
	<main id="main" class="main">

		<section class="section">
			<div class="row">
				<div class="col-lg-6" style="width: 100%">

					<div class="card">
						<div class="card-body">
							<h5 class="card-title">新增二手商品</h5>
							
							<!-- General Form Elements -->
							<form METHOD="post" ACTION="<%=request.getContextPath()%>/secondhand/SecondHandServlet" name="form1" enctype="multipart/form/data">
								<div class="row mb-3">
									<label for="inputText" class="col-sm-2 col-form-label">拍賣人id</label>
									<div class="col-sm-10">
										<input name="saler" type="text" class="form-control" value="${param.saler}">
									${errorMsgs.saler}<br>
									</div>
								</div>
								<div class="row mb-3">
									<label for="inputText" class="col-sm-2 col-form-label">商品名稱</label>
									<div class="col-sm-10">
										<input name="name" type="text" class="form-control" value="${param.name}">
										${errorMsgs.name}<br>
									</div>
								</div>
								<div class="row mb-3">
									<label for="inputNumber" class="col-sm-2 col-form-label">起標價格</label>
									<div class="col-sm-10">
										<input name="bottom_price" type="number" class="form-control" value="${param.bottom_price}">
										${errorMsgs.bottom_price}<br>
									</div>
								</div>
								<div class="row mb-3">
									<label for="inputNumber" class="col-sm-2 col-form-label">直購金額</label>
									<div class="col-sm-10">
										<input name="top_price" type="number" class="form-control" value="${param.top_price}">
										${errorMsgs.top_price}<br>
									</div>
								</div>
								<div class="row mb-3">
									<label for="inputStartTime" class="col-sm-2 col-form-label">起標時間</label>
									<div class="col-sm-10">
										<input name="start_time" id="f_date1" type="text"
											class="form-control" value="${param.start_time}"/>
											${errorMsgs.start_time}<br>
									</div>
								</div>
								<div class="row mb-3">
									<label for="inputEndTime" class="col-sm-2 col-form-label">結標時間</label>
									<div class="col-sm-10">
										<input name="end_time" id="f_date2" type="text"
											class="form-control" value="${param.end_time}"/>
											${errorMsgs.end_time}<br>
									</div>
								</div>
								<div class="row mb-3">
									<label for="inputNumber" class="col-sm-2 col-form-label">File
										Upload</label>
									<div class="col-sm-10">
										<input class="form-control" type=file
											oninput="pic1.src=window.URL.createObjectURL(this.files[0])"
											id="formFile0"> <img id="pic1" />
									</div>
								</div>
								<div class="row mb-3">
									<label for="inputNumber" class="col-sm-2 col-form-label">File
										Upload</label>
									<div class="col-sm-10">
										<input class="form-control" type=file
											oninput="pic2.src=window.URL.createObjectURL(this.files[0])"
											id="formFile1"> <img id="pic2" />
									</div>
								</div>
								<div class="row mb-3">
									<label for="inputNumber" class="col-sm-2 col-form-label">File
										Upload</label>
									<div class="col-sm-10">
										<input class="form-control" type=file
											oninput="pic3.src=window.URL.createObjectURL(this.files[0])"
											id="formFile2"> <img id="pic3" />
									</div>
								</div>
								<div class="row mb-3">
									<label class="col-sm-2 col-form-label">Submit Button</label>
									<div class="col-sm-10">
										<input type="hidden" name="action" value="insert"> <input
											type="submit" class="btn btn-primary" value="Submit Form">
									</div>
								</div>

							</form>

						</div>
					</div>

				</div>
			</div>
		</section>
	</main>
	<!-- End of Main  -->
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
	<script src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
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




<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Timestamp start_time = null;
  try {
	    start_time = java.sql.Timestamp.valueOf(request.getParameter("start_time").trim());
   } catch (Exception e) {
	    start_time = new java.sql.Timestamp(System.currentTimeMillis());
   }
%>
<% 
  java.sql.Timestamp end_time = null;
  try {
	    end_time = java.sql.Timestamp.valueOf(request.getParameter("end_time").trim());
   } catch (Exception e) {
	    end_time = new java.sql.Timestamp(System.currentTimeMillis()+1000*60*15);
   }
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
 	       theme: '',              //theme: 'dark',
	       timepicker:true,       //timepicker:true,
	       step: 15,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i',         //format:'Y-m-d H:i:s',
		   value: '<%=start_time%>', // value:   new Date(),
        });
        $('#f_date2').datetimepicker({
  	       theme: '',              //theme: 'dark',
 	       timepicker:true,       //timepicker:true,
 	       step: 15,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d H:i',         //format:'Y-m-d H:i:s',
 		   value: '<%=end_time%>', // value:   new Date(),
	});
</script>

</html>