<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>WorkFunBack</title>

<!-- Custom fonts for this template-->
<link
	href="<%=request.getContextPath()%>/assets/css/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="<%=request.getContextPath()%>/assets/css/sb-admin-2.min.css"
	rel="stylesheet">

<style>
.img-fluid {
	max-width: 50px;
	height: auto;
}
</style>
</head>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<ul class="navbar-nav bg-dark sidebar sidebar-dark accordion"
			id="accordionSidebar">

			<!-- Sidebar - Brand -->
			<a
				class="sidebar-brand d-flex align-items-center justify-content-center"
				href="back.html">
				<div class="sidebar-brand-text mx-3">
					<h2 class="font-weight-bold">WorkFun</h2>
				</div>
			</a>

			<!-- Divider -->
			<hr class="sidebar-divider my-0">
			<div style="height: 1px;"></div>

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
						<a class="collapse-item"
							href="<%=request.getContextPath()%>/emp/addEmp.jsp">新增員工帳號</a> <a
							class="collapse-item"
							href="<%=request.getContextPath()%>/emp/listAllEmp.jsp">全部員工帳號</a>


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
			</li>
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
			</li>
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
				</div></li>
			<!-- Nav Item - Pages Collapse Menu第三項 -->
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
				</div></li>

			<!-- Nav Item - Tables第四項 -->
			<li class="nav-item"><a class="nav-link" href="tables.html">
					<i class="fas fa-fw fa-table"></i> <span>Tables</span>
			</a></li>

			<!-- Divider -->
			<hr class="sidebar-divider d-none d-md-block">

			<!-- Sidebar Toggler (Sidebar) -->
			<div class="text-center d-none d-md-inline">
				<button class="rounded-circle border-0" id="sidebarToggle"></button>
			</div>
		</ul>
		<!-- End of Sidebar -->

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

					<!-- Topbar Search 搜尋列開始 -->

					<!-- <form
                        class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                        <div class="input-group">
                            <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..."
                                aria-label="Search" aria-describedby="basic-addon2">
                            <div class="input-group-append">
                                <button class="btn btn-primary" type="button">
                                    <i class="fas fa-search fa-sm"></i>
                                </button>
                            </div>
                        </div>
                    </form> -->
					<!-- Topbar Search 搜尋列結束 -->

					<!-- Topbar Navbar -->
					<ul class="navbar-nav bg-dark ml-auto">

						<!-- Nav Item - Search Dropdown (Visible Only XS) 縮小螢幕時搜尋 -->
						<!-- <li class="nav-item dropdown no-arrow d-sm-none">
                            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-search fa-fw"></i>
                            </a>
                            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                                aria-labelledby="searchDropdown">
                                <form class="form-inline mr-auto w-100 navbar-search">
                                    <div class="input-group">
                                        <input type="text" class="form-control bg-light border-0 small"
                                            placeholder="Search for..." aria-label="Search"
                                            aria-describedby="basic-addon2">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="button">
                                                <i class="fas fa-search fa-sm"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </li> -->



						<!-- Nav Item - User Information -->
						<li class="nav-item no-arrow"><a href="#"> <i
								class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
								Logout
						</a></li>

					</ul>

				</nav>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">
					<!-- 內容放這 -->


					<jsp:useBean id="permissionSvc" scope="page"
						class="com.permission.model.PermissionService" />
					<div class="input-group mb-3">
						<table size="1" name="depId" class="input-group-text"
							id="basic-addon3">

							<tr>
								<c:forEach var="permissionVO" items="${permissionSvc.all}">

									<td>${permissionVO.permissionName}</td>



								</c:forEach>
							</tr>


							<tr>

						 	<c:forEach var="permissionVO" items="${permissionSvc.all}"> 
							
									<c:forEach var="empPm" items="${empPm}">
										<%-- <c:if test="${permissionVO.permissionId==empPm}">
											<td><input type="checkbox" checked></td>

										</c:if> --%>
										
											<%-- <c:forEach var="empPm" items="${empPm}"> --%>
										<%-- <c:if test="${permissionVO.permissionId==empPm}">
											<td><input type="checkbox" checked></td>

										</c:if> --%>
											for<input type="checkbox" ${(empPm==1)? 'checked' : ''}>      
											   for<input type="checkbox" ${(empPm==2)? 'checked' : ''}>            
											                    for<input type="checkbox" ${(empPm==3)? 'checked' : ''}>           
											                                   for<input type="checkbox" ${(empPm==4)? 'checked' : ''}>
											                                   
										
										
<%-- 
										<c:if test="${permissionVO.permissionId!=empPm }">

											<td><input type="checkbox"></td>
										</c:if> --%>

									</c:forEach>
							 	</c:forEach> 
							</tr>
						</table>

				<%-- 		 	<c:forEach var="empPm" items="${empPm}">
		<a>${empPm}</a>	
			
			
		</c:forEach>  --%>







						<%-- 	<td><input type="checkbox" value="${permissionVO.permissionName}" name="permissionName" checked></td> --%>



						<%-- 	<td><input type="checkbox" value="${permissionVO.permissionName}" name="permissionName"></td> --%>





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

		<!-- Bootstrap core JavaScript-->
		<script
			src="<%=request.getContextPath()%>/assets/js/jquery/jquery.min.js"></script>
		<script
			src="<%=request.getContextPath()%>/assets/js/bootstrap.bundle.min.js"></script>

		<!-- Core plugin JavaScript-->
		<script
			src="<%=request.getContextPath()%>/assets/js/jquery-easing/jquery.easing.min.js"></script>

		<!-- Custom scripts for all pages-->
		<script
			src="<%=request.getContextPath()%>/assets/js/sb-admin-2.min.js"></script>
</body>
<script type="text/javascript"></script>
</html>