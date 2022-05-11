<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>

<%-- 萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位 --%>
<%-- 此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能--%>

<jsp:useBean id="listEmps_ByCompositeQuery" scope="request"
	type="java.util.List<EmpVO>" />
<!-- 於EL此行可省略 -->
<jsp:useBean id="depSvc" scope="page" class="com.dep.model.DepService" />

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
.img-fluid {
	max-width: 70px;
	height: auto;
}

#wrapper #content-wrapper {
	overflow-x: auto;
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
				<div class="container-fluid">
					<!-- 內容放這 -->



					<table class="table table-striped table-bordered table-hover"
						id="dataTables-example">
						<tr>
							<th>員工編號</th>
							<th>員工姓名</th>
							<th>部門</th>
							<th>雇用日期</th>
							<th>離職日期</th>
							<th>手機</th>
							<th>分機</th>
							<th>興趣</th>
							<th>專長</th>
							<th>頭貼</th>
							<th>信箱</th>
							<th>生日</th>
							<th>狀態</th>
							<th></th>
						</tr>
						<%@ include file="/back/page1_ByCompositeQuery.file"%>
						<c:forEach var="empVO" items="${listEmps_ByCompositeQuery}"
							begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
							<tr>
								<td>${empVO.empId}</td>
								<td>${empVO.empName}</td>
								<td>${empVO.depId}-[${empVO.depVO.depName}]</td>

								<td>${empVO.hiredate}</td>
								<td>${empVO.resigndate}</td>
								<td>${empVO.phone}</td>
								<td>${empVO.extension}</td>
								<td>${empVO.hobby}</td>

								<td>${empVO.skill}</td>
								<td style="width: 300px;"><img
									src="
									<%=request.getContextPath()%>/util/DBGifReader?pic=emp_profile&table=emp&id_key=emp_id&id=${empVO.empId}
									"
									class="img-fluid"></td>
								<td>${empVO.mail}</td>
								<td>${empVO.birthday}</td>



								<td>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/empServlet"
										style="margin-bottom: 0px;">
										<input type="submit" value="修改"> <input type="hidden"
											name="empId" value="${empVO.empId}"> <input
											type="hidden" name="requestURL"
											value="<%=request.getServletPath()%>">
										<!--送出本網頁的路徑給Controller-->
										<input type="hidden" name="whichPage" value="<%=whichPage%>">
										<!--送出當前是第幾頁給Controller-->
										<input type="hidden" name="action" value="getOne_For_Update">
									</FORM>
								</td>

							</tr>
						</c:forEach>
					</table>
					<%@ include file="/back/page2_ByCompositeQuery.file"%>

					<!-- /.container-fluid -->
				</div>
				<!-- End of Main Content -->
			</div>
			<!-- End of Content Wrapper -->
		</div>
		<!-- End of Page Wrapper -->
	</div>
	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<%@ include file="/design/backjs.jsp"%>

</body>

</html>