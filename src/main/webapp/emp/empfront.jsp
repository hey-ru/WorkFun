<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>

<%//如果有錯，先看修改按鈕 %>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>:: WorkFun ::</title>


<link href="<%=request.getContextPath()%>/assets/img/wf.png" rel="icon">
<link href="<%=request.getContextPath()%>/assets/img/wf.png"
	rel=" apple-touch-icon">

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="<%=request.getContextPath()%>/assets/vendor/aos/aos.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/assets/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/assets/vendor/boxicons/css/boxicons.min.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/assets/vendor/glightbox/css/glightbox.min.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/assets/vendor/swiper/swiper-bundle.min.css"
	rel="stylesheet">

<!-- Template Main CSS File -->
<link href="<%=request.getContextPath()%>/assets/css/style.css"
	rel="stylesheet">






<!-- <link href="../assets1/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="../assets1/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="../assets1/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
    <link href="../assets1/vendor/quill/quill.snow.css" rel="stylesheet">
    <link href="../assets1/vendor/quill/quill.bubble.css" rel="stylesheet">
    <link href="../assets1/vendor/remixicon/remixicon.css" rel="stylesheet">
    <link href="../assets1/vendor/simple-datatables/style.css" rel="stylesheet">
    <link href="../assets1/css/style.css" rel="stylesheet"> -->





</head>

<body>

	<!-- ======= Header ======= -->
	<header id="header" class="fixed-top">
		<div
			class="container-fluid d-flex justify-content-between align-items-center">
			<a href="./home/Home.html" class="logo"><img
				src="../assets/img/workfun.gif" alt="" class="img-fluid"
				style="width: 250px;"></a>

			<nav id="navbar" class="navbar order-last order-lg-0">
				<ul>
					<!-- 修改跳頁 -->
					<li>
						<div class="dropdown">
							<button class="dropbtn">揪團訂餐</button>
							<div class="dropdown-content">
								<a href="../webapp/groupbuy/gb_home_1.html"> 主頁 </a> <a
									href="../webapp/groupbuy/shoplist.html">我要揪團</a> <a href="#">查詢揪團</a>
								<a href="#">查詢參團</a>
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
				<div style="margin-left: 120px;"></div>
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
									href="users-profile.html"> <i class="bi bi-person"></i> <span>My
											Profile</span>
								</a></li>
								<li>
									<hr class="dropdown-divider">
								</li>
								<li><a class="dropdown-item d-flex align-items-center"
									href="users-profile.html"> <i class="bi bi-gear"></i> <span>Account
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
			</nav>
		</div>

	</header>
	<!-- End Header -->
	<!-- content 如果頁面要可以往下滑就改一下main的height值吧 -->
	<main style="height: 155vh;">
		<div style="height: 90px;"></div>
		<!-- 從這裡開始 -->
		<div style="margin-left: 350px;">
			<section class="section profile">


				<div class="col-xl-8">

					<div class="card">
						<div class="card-body pt-3">
							<!-- Bordered Tabs -->
							<ul class="nav nav-tabs nav-tabs-bordered">

								<li class="nav-item">
									<button class="nav-link active" data-bs-toggle="tab"
										data-bs-target="#profile-overview">Overview</button>
								</li>

								<li class="nav-item">
									<button class="nav-link" data-bs-toggle="tab"
										data-bs-target="#profile-edit">Edit Profile</button>
								</li>

								<li class="nav-item">
									<button class="nav-link" data-bs-toggle="tab"
										data-bs-target="#profile-settings">Settings</button>
								</li>

								<li class="nav-item">
									<button class="nav-link" data-bs-toggle="tab"
										data-bs-target="#profile-change-password">Change
										Password</button>
								</li>

							</ul>
							<div class="tab-content pt-2">

								<div class="tab-pane fade show active profile-overview"
									id="profile-overview">
									<h5 class="card-title">About</h5>
									<p class="small fst-italic">${empVO.empId}</p>

									<h5 class="card-title"><img style="width:200px;length:200px"
												src="
									<%=request.getContextPath()%>/util/DBGifReader?pic=emp_profile&table=emp&id_key=emp_id&id=${empVO.empId}
									"
												class="img-fluid"
											></h5>

									<div class="row">
										<div class="col-lg-3 col-md-4 label ">${empVO.empName}</div>
										<div class="col-lg-9 col-md-8">${empVO.phone}</div>
									</div>

									<div class="row">
										<div class="col-lg-3 col-md-4 label">${empVO.extension}</div>
										<div class="col-lg-9 col-md-8">${empVO.hobby}</div>
									</div>

									<div class="row">
										<div class="col-lg-3 col-md-4 label">${empVO.skill}</div>
										<div class="col-lg-9 col-md-8">${empVO.birthday}</div>
									</div>

									<div class="row">
										<div class="col-lg-3 col-md-4 label">${empVO.mail}</div>
										<div class="col-lg-9 col-md-8">USA</div>
									</div>

									<div class="row">
										<div class="col-lg-3 col-md-4 label">Address</div>
										<div class="col-lg-9 col-md-8">A108 Adam Street, New
											York, NY 535022</div>
									</div>

									<div class="row">
										<div class="col-lg-3 col-md-4 label">Phone</div>
										<div class="col-lg-9 col-md-8">(436) 486-3538 x29071</div>
									</div>

									<div class="row">
										<div class="col-lg-3 col-md-4 label">Email</div>
										<div class="col-lg-9 col-md-8">${errorMsgs.empId}${errorMsgs.empName}${errorMsgs.depId}${errorMsgs.hiredate}${errorMsgs.phone}${errorMsgs.extension}</div>
									</div>

								</div>

								<div class="tab-pane fade profile-edit pt-3" id="profile-edit">

									<!-- Profile Edit Form -->
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/empServlet" name="form1"
							enctype="multipart/form-data">
										<div class="row mb-3">
											<label for="profileImage"
												class="col-md-4 col-lg-3 col-form-label">Profile
												Image</label> <img style="width:200px;length:200px"
												src="
									<%=request.getContextPath()%>/util/DBGifReader?pic=emp_profile&table=emp&id_key=emp_id&id=${empVO.empId}
									"
												class="img-fluid"
												
											>
											<div class="row mb-3">
											<label for="about" class="col-md-4 col-lg-3 col-form-label">編號</label>
											<div class="col-md-8 col-lg-9">
												<input type="hidden" name="empId" size="45"
													value="${empVO.empId}" class="form-control"
													aria-label="Recipient's username"
													aria-describedby="basic-addon2">
													<input
										type="hidden" name="hiredate" size="45" id="f_date2"
										value="${empVO.hiredate}" class="form-control"
										aria-label="Recipient's username"
										aria-describedby="basic-addon2">
											<input
										type="hidden" name="where" size="45" id="f_date2"
										value="<%=request.getContextPath()%>/emp/" class="form-control"
										aria-label="Recipient's username"
										aria-describedby="basic-addon2">
										
											</div>
										</div>


											<div class="col-md-8 col-lg-9">
											
												<input type="file" name="empProfile" size="45"
												 class="form-control"
													id="inputGroupFile01">
												<div class="pt-2">
													<a href="#" class="btn btn-primary btn-sm"
														title="Upload new profile image"><i
														class="bi bi-upload"></i></a> <a href="#"
														class="btn btn-danger btn-sm"
														title="Remove my profile image"><i class="bi bi-trash"></i></a>
												</div>
											</div>
										</div>

										<div class="row mb-3">
											<label for="fullName"
												class="col-md-4 col-lg-3 col-form-label">名字</label>
											<div class="col-md-8 col-lg-9">
												<input type="TEXT" name="empName" size="45"
													value="${empVO.empName}" class="form-control"
													aria-label="Username" aria-describedby="basic-addon1">${errorMsgs.empName}
											</div>
											<jsp:useBean id="deptSvc" scope="page"
												class="com.dep.model.DepService" />
										</div>
										<div class="row mb-3">
											<label for="Email" class="col-md-4 col-lg-3 col-form-label">部門</label>
											<div class="col-md-8 col-lg-9">
												<select size="1" name="depId" class="input-group-text"
													id="basic-addon3">
													<c:forEach var="deptVO" items="${deptSvc.all}">
														<option value="${deptVO.depId}"
															${(empVO.depId==deptVO.depId)? 'selected':'' }>${deptVO.depName}
													</c:forEach>

												</select>
											</div>
										</div>


										<div class="row mb-3">
											<label for="about" class="col-md-4 col-lg-3 col-form-label">手機</label>
											<div class="col-md-8 col-lg-9">
												<input type="TEXT" name="phone" size="45"
													value="${empVO.phone}" class="form-control" placeholder=""
													aria-label="Recipient's username"
													aria-describedby="basic-addon2">
											</div>
										</div>

										<div class="row mb-3">
											<label for="company" class="col-md-4 col-lg-3 col-form-label">分機</label>
											<div class="col-md-8 col-lg-9">
												<input type="TEXT" name="extension" size="45"
													value="${empVO.extension}" class="form-control"
													 aria-label="Recipient's username"
													aria-describedby="basic-addon2">
											</div>
										</div>

										<div class="row mb-3">
											<label for="Job" class="col-md-4 col-lg-3 col-form-label">興趣</label>
											<div class="col-md-8 col-lg-9">
												<input type="TEXT" name="hobby" size="45"
													value="${empVO.hobby}" class="form-control" 
													aria-label="Recipient's username"
													aria-describedby="basic-addon2">
											</div>
										</div>



										<div class="row mb-3">
											<label for="Address" class="col-md-4 col-lg-3 col-form-label">專長</label>
											<div class="col-md-8 col-lg-9">
												<input type="TEXT" name="skill" size="45"
													value="${empVO.skill}" class="form-control"
													aria-label="Recipient's username"
													aria-describedby="basic-addon2">
											</div>
										</div>

										<div class="row mb-3">
											<label for="Phone" class="col-md-4 col-lg-3 col-form-label">信箱</label>
											<div class="col-md-8 col-lg-9">
												<input type="TEXT" name="mail" size="45"
													value="${empVO.mail}" class="form-control"
													aria-label="Recipient's username"
													aria-describedby="basic-addon2">
											</div>
										</div>

										<div class="row mb-3">
											<label for="Email" class="col-md-4 col-lg-3 col-form-label">生日</label>
											<div class="col-md-8 col-lg-9">
												<input type="TEXT" name="birthday" size="45"
													value="${empVO.birthday}" class="form-control"
													aria-label="Recipient's username"
													aria-describedby="basic-addon2">
											</div>
										</div>








										<div class="text-center">
											<input type="hidden" name="action" value="update">
											<button type="submit" class="input-group-text"
												id="basic-addon2">Save Changes</button>
										</div>
									</form>
									<!-- End Profile Edit Form -->

								</div>

								<div class="tab-pane fade pt-3" id="profile-settings">

									<!-- Settings Form -->
									<form>

										<div class="row mb-3">
											<label for="fullName"
												class="col-md-4 col-lg-3 col-form-label">Email
												Notifications</label>
											<div class="col-md-8 col-lg-9">
												<div class="form-check">
													<input class="form-check-input" type="checkbox"
														id="changesMade" checked> <label
														class="form-check-label" for="changesMade">
														Changes made to your account </label>
												</div>
												<div class="form-check">
													<input class="form-check-input" type="checkbox"
														id="newProducts" checked> <label
														class="form-check-label" for="newProducts">
														Information on new products and services </label>
												</div>
												<div class="form-check">
													<input class="form-check-input" type="checkbox"
														id="proOffers"> <label class="form-check-label"
														for="proOffers"> Marketing and promo offers </label>
												</div>
												<div class="form-check">
													<input class="form-check-input" type="checkbox"
														id="securityNotify" checked disabled> <label
														class="form-check-label" for="securityNotify">
														Security alerts </label>
												</div>
											</div>
										</div>

										<div class="text-center">
											<button type="submit" class="btn btn-primary">Save
												Changes</button>
										</div>
									</form>
									<!-- End settings Form -->

								</div>

								<div class="tab-pane fade pt-3" id="profile-change-password">
									<!-- Change Password Form -->
									<form>

										<div class="row mb-3">
											<label for="currentPassword"
												class="col-md-4 col-lg-3 col-form-label">Current
												Password</label>
											<div class="col-md-8 col-lg-9">
												<input name="password" type="password" class="form-control"
													id="currentPassword">
											</div>
										</div>

										<div class="row mb-3">
											<label for="newPassword"
												class="col-md-4 col-lg-3 col-form-label">New
												Password</label>
											<div class="col-md-8 col-lg-9">
												<input name="newpassword" type="password"
													class="form-control" id="newPassword">
											</div>
										</div>

										<div class="row mb-3">
											<label for="renewPassword"
												class="col-md-4 col-lg-3 col-form-label">Re-enter
												New Password</label>
											<div class="col-md-8 col-lg-9">
												<input name="renewpassword" type="password"
													class="form-control" id="renewPassword">
											</div>
										</div>

										<div class="text-center">
											<button type="submit" class="btn btn-primary">Change
												Password</button>
										</div>
									</form>
									<!-- End Change Password Form -->

								</div>

							</div>
							<!-- End Bordered Tabs -->

						</div>
					</div>
				</div>

			</section>
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
	<script
		src="<%=request.getContextPath()%>/assets/vendor/purecounter/purecounter.js"></script>
	<script src="<%=request.getContextPath()%>/assets/vendor/aos/aos.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/vendor/glightbox/js/glightbox.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/vendor/swiper/swiper-bundle.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/vendor/waypoints/noframework.waypoints.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/vendor/php-email-form/validate.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/jquery/jquery.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
		integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
		crossorigin="anonymous"></script>

	<!-- Template Main JS File -->
	<script src="<%=request.getContextPath()%>/assets/js/main.js"></script>

</body>

</html>