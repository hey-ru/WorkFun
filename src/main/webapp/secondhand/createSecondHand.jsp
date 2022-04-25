<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>

<%
  // EmpVO empVO = (EmpVO) request.getAttribute("empVO");
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>:: WorkFun ::</title>

<!-- Favicons -->
<link href="../assets/img/wf.png" rel="icon">
<link href="../assets/img/wf.png" rel=" apple-touch-icon">

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="../assets/vendor/aos/aos.css" rel="stylesheet">
<link href="../assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="../assets/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="../assets/vendor/boxicons/css/boxicons.min.css"
	rel="stylesheet">
<link href="../assets/vendor/glightbox/css/glightbox.min.css"
	rel="stylesheet">
<link href="../assets/vendor/swiper/swiper-bundle.min.css"
	rel="stylesheet">

<!-- Template Main CSS File -->
<link href="../old_groupBuy/style_gb.css" rel="stylesheet">

</head>

<body>

	<!-- ======= Header ======= -->
	<header id="header" class="fixed-top">
		<div
			class="container-fluid d-flex justify-content-between align-items-center">
			<a href="../home/Home.html" class="logo"><img
				src="../assets/img/workfun.gif" alt="" class="img-fluid"
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
								<a href="./secondHandHome.jsp">競標首頁</a> <a
									href="./createSecondHand.html">新增競標</a> <a href="#">購買記錄</a>
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
								src="../assets/img/wu.jpeg" alt="Profile" class="rounded-circle"
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
							<form>
								<div class="row mb-3">
									<label for="inputName" class="col-sm-2 col-form-label">商品名稱</label>
									<div class="col-sm-10">
										<input type="text" class="form-control">
									</div>
								</div>
								<div class="row mb-3">
									<label for="inputBottomPrice" class="col-sm-2 col-form-label">起標價格</label>
									<div class="col-sm-10">
										<input type="number" class="form-control">
									</div>
								</div>
								<div class="row mb-3">
									<label for="inputTopPrice" class="col-sm-2 col-form-label">直購金額</label>
									<div class="col-sm-10">
										<input type="number" class="form-control">
									</div>
								</div>
								<div class="row mb-3">
									<label for="inputStartTime" class="col-sm-2 col-form-label">起標時間</label>
									<div class="col-sm-10">
										<input name="hiredate" id="f_date1" type="text" class="form-control"/>
									</div>
								</div>
								<div class="row mb-3">
									<label for="inputEndTime" class="col-sm-2 col-form-label">結標時間</label>
									<div class="col-sm-10">
										<input name="hiredate" id="f_date2" type="text" class="form-control"/>
									</div>
								</div>
								<div class="row mb-3">
									<label for="inputNumber" class="col-sm-2 col-form-label">File
										Upload</label>
									<div class="col-sm-10">
										<input class="form-control" type=file
											oninput="pic0.src=window.URL.createObjectURL(this.files[0])"
											id="formFile0"> <img id="pic0" />
									</div>
								</div>
								<div class="row mb-3">
									<label for="inputNumber" class="col-sm-2 col-form-label">File
										Upload</label>
									<div class="col-sm-10">
										<input class="form-control" type=file
											oninput="pic1.src=window.URL.createObjectURL(this.files[0])"
											id="formFile1"> <img id="pic1" />
									</div>
								</div>
								<div class="row mb-3">
									<label for="inputNumber" class="col-sm-2 col-form-label">File
										Upload</label>
									<div class="col-sm-10">
										<input class="form-control" type=file
											oninput="pic2.src=window.URL.createObjectURL(this.files[0])"
											id="formFile2"> <img id="pic2" />
									</div>
								</div>
								<div class="row mb-3">
									<label for="inputTime" class="col-sm-2 col-form-label">Time</label>
									<div class="col-sm-10">
										<input type="time" class="form-control">
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
							<!-- End General Form Elements -->



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
	<script src="../assets/vendor/purecounter/purecounter.js"></script>
	<script src="../assets/vendor/aos/aos.js"></script>
	<script src="../assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="../assets/vendor/glightbox/js/glightbox.min.js"></script>
	<script src="../assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
	<script src="../assets/vendor/swiper/swiper-bundle.min.js"></script>
	<script src="../assets/vendor/waypoints/noframework.waypoints.js"></script>
	<script src="../assets/vendor/php-email-form/validate.js"></script>
	<script src="../assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="../assets/js/jquery/jquery.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
		integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
		crossorigin="anonymous"></script>

	<!-- Template Main JS File -->
	<script src="../assets/js/main.js"></script>

</body>




<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Date hiredate = null;
  try {
	    hiredate = java.sql.Date.valueOf(request.getParameter("hiredate").trim());
   } catch (Exception e) {
	    hiredate = new java.sql.Date(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
 	       theme: '',              //theme: 'dark',
	       timepicker:true,       //timepicker:true,
	       step: 15,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i',         //format:'Y-m-d H:i:s',
		   value: '<%=hiredate%>', // value:   new Date(),
        });
        $('#f_date2').datetimepicker({
  	       theme: '',              //theme: 'dark',
 	       timepicker:true,       //timepicker:true,
 	       step: 15,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d H:i',         //format:'Y-m-d H:i:s',
 		   value: '<%=hiredate%>', // value:   new Date(),
         });
</script>

</html>