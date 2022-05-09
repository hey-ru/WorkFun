<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="java.util.*"%>

<%
ShopService shopSvc = new ShopService();
List<ShopVO> list = shopSvc.getAll();
pageContext.setAttribute("list", list);
int itemsPerPage = 10;
%>

<!DOCTYPE html>
<html lang="zh-TW">

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
/* th { */
/* 	vertical-align: middle; */
/* 	text-align: center; */
/* 	height: 50px; */
/* } */

/* td { */
/* 	vertical-align: middle; */
/* 	text-align: center; */
/* 	height: 100px; */
/* } */

/* .table-responsive { */
/* 	overflow-x:; */
/* } */
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

					<!-- ============== 內容開始 ============== -->
					<main id="main" class="main">
						<div class="card shadow">

							<!-- ============== Card Header ============== -->
							<div class="card-header py-3" style="background-color: #E8E7D2">
								<div class="row">
									<div class="col-6"
										style="height: 20px; display: inline-block;">
										<h5>
											<strong>[後台] 菜單未上架明細</strong>
										</h5>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-sm-6">
									<table class="table table-bordered dataTable" id="dataTable"
										role="grid" aria-describedby="dataTable_info"
										style="width: 100%; cellspacing: 0;">
										<thead>
											<tr role=" row">
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Position: activate to sort column ascending">編號</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Position: activate to sort column ascending">品項</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Position: activate to sort column ascending">價格</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Office: activate to sort column ascending">狀態</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Age: activate to sort column ascending">變更狀態</th>
											</tr>
										</thead>

<%-- 										<%@ include file="/design/page1.file"%> --%>
										<tbody>

											<c:forEach var="menu" items="${menuListDisable}">
												<tr>
													<td>${menu.menu_id}</td>
													<td>${menu.item}</td>
													<td>${menu.price}</td>
													<td><c:if test="${menu.is_item==1}">
															<c:out value="上架" />
														</c:if> <c:if test="${menu.is_item==0}">
															<c:out value="下架" />
														</c:if></td>
													<td>
														<FORM METHOD="post"
															ACTION="<%=request.getContextPath()%>/menu/disablebyshopservlet"
															style="margin-bottom: 0px;">
															<input type="submit" value="上架"> <input
																type="hidden" name="menu_id" value="${menu.menu_id}">
															<input type="hidden" name="shop_id"
																value="${menu.shop_id}"> <input type="hidden"
																name="action" value="changeStatus">
														</FORM>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<div style="display: inline-block; width: 50px;"></div>
									<div style="display: inline-block; margin-bottom: 10px;">
<%-- 										<%@ include file="/design/page2.file"%> --%>
									</div>
								</div>
							</div>

						</div>
					</main>

					<!-- ======= 內容結束 ======= -->

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