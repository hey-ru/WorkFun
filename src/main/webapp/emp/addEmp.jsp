<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
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
<link href="assets/css/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="assets/css/sb-admin-2.min.css" rel="stylesheet">




<!-- <link href="assets4profile/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet"> -->
<!-- <link href="assets4profile/bootstrap-icons/bootstrap-icons.css" rel="stylesheet"> -->
<!-- <link href="assets4profile/boxicons/css/boxicons.min.css" rel="stylesheet"> -->
<!-- <link href="assets4profile/quill/quill.snow.css" rel="stylesheet">
    <link href="assets4profile/quill/quill.bubble.css" rel="stylesheet">
    <link href="assets4profile/remixicon/remixicon.css" rel="stylesheet">
    <link href="assets4profile/simple-datatables/style.css" rel="stylesheet"> -->

<!-- Template Main CSS File -->
<!-- <link href="assets4profile/css/style.css" rel="stylesheet"> -->




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
				href="backmain.jsp">
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
						<a class="collapse-item" href="addEmp.jsp">新增帳號</a> <a
							class="collapse-item" href="listAllEmp.jsp">顯示帳號</a> <a
							class="collapse-item" href="listAllEmp.jsp">查詢帳號</a> <a
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

					<main style="height: 120vh; margin-top: 40px;">
						<div>
							<input type="search" class="light-table-filter"
								data-table="order-table" placeholder="請輸入關鍵字"
								style="margin-left: 78%; border: 3px yellow solid;">
						</div>
						<FORM METHOD="post" ACTION="emp.do" name="form1"
							enctype="multipart/form-data">
							<div
								style="border: 3px blue solid; width: 900px; position: absolute; height: 600px; top: 45%; margin-top: -200px; margin-left: 5%;">
								<div class="input-group mb-3" style="margin-top: 0px;">
									<span class="input-group-text" id="xx">員工姓名</span> <input
										type="TEXT" name="empName" size="45" value="${param.empName}"
										class="form-control" aria-label="Username"
										aria-describedby="basic-addon1">${errorMsgs.empName}
								</div>
								<jsp:useBean id="deptSvc" scope="page"
									class="com.dep.model.DepService" />
								<div class="input-group mb-3">
									<span class="input-group-text" id="basic-addon2">部門</span> <select
										size="1" name="depId" class="input-group-text"
										id="basic-addon3">
										<c:forEach var="deptVO" items="${deptSvc.all}">
											<option value="${deptVO.depId}"
												${(param.depId==deptVO.depId)? 'selected':'' }>${deptVO.depName}
										</c:forEach>

									</select> <span class="input-group-text" id="basic-addon2">雇用日期</span> <input
										name="hiredate" id="f_date1" type="text" class="form-control"
										value="${param.hiredate}">


								</div>

								<div class="input-group mb-3">
									<span class="input-group-text" id="basic-addon2">手機</span> <input
										type="TEXT" name="phone" size="45" value="${param.phone}"
										class="form-control" placeholder=""
										aria-label="Recipient's username"
										aria-describedby="basic-addon2"> <span
										class="input-group-text" id="basic-addon2">分機</span> <input
										type="TEXT" name="extension" size="45"
										value="${param.extension}" class="form-control" placeholder=""
										aria-label="Recipient's username"
										aria-describedby="basic-addon2"> <span
										class="input-group-text" id="basic-addon2">興趣</span> <input
										type="TEXT" name="hobby" size="45" value="${param.hobby}"
										class="form-control" placeholder=""
										aria-label="Recipient's username"
										aria-describedby="basic-addon2">

								</div>
								<div class="input-group mb-3">
									<span class="input-group-text" id="basic-addon2">專長</span> <input
										type="TEXT" name="skill" size="45" value="${param.skill}"
										class="form-control" aria-label="Recipient's username"
										aria-describedby="basic-addon2"> <span
										class="input-group-text" id="basic-addon2">信箱</span> <input
										type="TEXT" name="mail" size="45" value="${param.mail}"
										class="form-control" aria-label="Recipient's username"
										aria-describedby="basic-addon2">
								</div>
								<div class="input-group mb-3">
									<span class="input-group-text" id="basic-addon2">生日</span> <input
										type="TEXT" name="birthday" size="45"
										value="${param.birthday}" class="form-control"
										aria-label="Recipient's username"
										aria-describedby="basic-addon2">

								</div>


								<div class="input-group mb-3">
									<label class="input-group-text" for="inputGroupFile01">大頭照</label>
									<input type="file" name="empProfile" size="45"
										value="${param.empProfile}" class="form-control"
										id="inputGroupFile01">
								</div>


								<div class="input-group mb-3">

									<input type="hidden" name="action" value="insert"> <input
										type="submit" value="送出新增" class="input-group-text"
										id="basic-addon2">
								</div>
						</FORM>
</body>
</div>
</main>



</div>
<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->

</div>
<!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<a class="scroll-to-top rounded" href="#page-top"> <i
	class="fas fa-angle-up"></i>
</a>

<!-- Bootstrap core JavaScript-->
<script src="assets/js/jquery/jquery.min.js"></script>
<script src="assets/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="assets/js/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="assets/js/sb-admin-2.min.js"></script>


</body>

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
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=hiredate%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>