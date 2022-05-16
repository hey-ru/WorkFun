<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="java.util.*"%>

<jsp:useBean id="listByCompositeQuery" scope="request" type="java.util.List<ShopVO>" />
<%
String yourServlet = "/shop/ShopServlet"; 

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
th {
	vertical-align: middle;
	text-align: center;
}

td {
	vertical-align: middle;
	text-align: center;
}

.table-responsive {
	overflow-x: hidden;
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
					class="navbar navbar-expand navbar-light bg-dark topbar static-top shadow">

					<!-- Topbar Navbar -->
					<ul class="navbar-nav bg-dark ml-auto">

						<!-- Nav Item - User Information -->
						<li class="nav-item no-arrow pr-3"><a
							href="<%=request.getContextPath()%>/home/home.jsp"> <i
								class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
								Back Home
						</a></li>

					</ul>

				</nav>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid pl-0">

					<!-- ============== 內容開始 ============== -->
					<main id="main" class="main">
						<div class="card shadow">

							<!-- ============== Card Header ============== -->
							<div class="card-header py-3" style="background-color: #E8E7D2">
								<div class="row">
									<div class="col-11"
										style="height: 20px; display: inline-block;">
										<h5>
											<strong>店家與菜單管理</strong>
										</h5>
									</div>
								</div>
							</div>
							<div class="card-body">
								<div class="row">
									<div
										style="height: 50px; display: inline-block; TEXT-ALIGN: end;">
										<form method="post"
											ACTION="<%=request.getContextPath()%>/shop/ShopServlet">
											<div class="form-groupf" style="display: inline-block">
												<input type="text" class="form-control"
													id="exampleFormControlInput1" placeholder="輸入店名"
													style="border: gray solid 2px;" name="shop_name">
											</div>
											<input type="hidden" name="action"
												value="listByCompositeQueryBack">
											<button type="submit" class="btn btn-dark"
												style="display: inline-block;">搜尋</button>
										</form>
									</div>
								</div>


								<div class="row">
									<div class="col-sm-12">
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
														aria-label="Position: activate to sort column ascending">名稱</th>
													<th class="sorting" tabindex="0" aria-controls="dataTable"
														rowspan="1" colspan="1"
														aria-label="Position: activate to sort column ascending">類
														型</th>
													<th class="sorting" tabindex="0" aria-controls="dataTable"
														rowspan="1" colspan="1"
														aria-label="Position: activate to sort column ascending">地址</th>
													<th class="sorting" tabindex="0" aria-controls="dataTable"
														rowspan="1" colspan="1"
														aria-label="Office: activate to sort column ascending">電話</th>
													<th class="sorting" tabindex="0" aria-controls="dataTable"
														rowspan="1" colspan="1"
														aria-label="Age: activate to sort column ascending">更新時間</th>
													<th class="sorting" tabindex="0" aria-controls="dataTable"
														rowspan="1" colspan="1"
														aria-label="Age: activate to sort column ascending">狀
														態</th>
													<th class="sorting" tabindex="0" aria-controls="dataTable"
														rowspan="1" colspan="1"
														aria-label="Age: activate to sort column ascending">變更狀態</th>
													<th class="sorting" tabindex="0" aria-controls="dataTable"
														rowspan="1" colspan="1"
														aria-label="Salary: activate to sort column ascending">菜單維護</th>
												</tr>
											</thead>


											<%@ include file="/design/page1_ByCompositeQuery.file"%>
											<tbody>

												<c:forEach var="shopVO" items="${listByCompositeQuery}"
													begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

													<tr>
														<td>${shopVO.shop_id}</td>
														<td>${shopVO.shop_name}</td>
														<td><c:choose>
																<c:when test="${shopVO.shop_type == 0}">
											       	飲料
											    </c:when>
																<c:when test="${shopVO.shop_type == 1}">
											        中式
											    </c:when>
																<c:when test="${shopVO.shop_type == 2}">
											        異國
											    </c:when>
																<c:when test="${shopVO.shop_type == 3}">
											        小吃
											    </c:when>
																<c:when test="${shopVO.shop_type == 4}">
											        素食
											    </c:when>
																<c:when test="${shopVO.shop_type == 5}">
											        其他
											    </c:when>
															</c:choose></td>
														<td>${shopVO.address}</td>
														<td>${shopVO.tel}</td>
														<td><fmt:formatDate value="${shopVO.shop_upd}"
																pattern="yyyy-MM-dd HH:mm" /></td>

														<td>${shopVO.is_disable eq 1 ? "已下架":"上架中"}</td>
														<td>
															<FORM METHOD="post"
																ACTION="<%=request.getContextPath()%>/shop/ShopServlet"
																style="margin-bottom: 0px;">
																<input type="submit"
																	value=${shopVO.is_disable eq 1 ? "重新上架":" 下架 "}>
																<input type="hidden" name="shop_id"
																	value="${shopVO.shop_id}"> <input type="hidden"
																	name="is_disable" value="${shopVO.is_disable}">
																<input type="hidden" name="action"
																	value="updateShopStatus">
															</FORM>
														</td>



														<td>
															<FORM METHOD="post"
																ACTION="<%=request.getContextPath()%>/menu/disablebyshopservlet"
																style="margin-bottom: 0px;">
																<button class="btn">
																	<i class="fa fa-bars"></i> Menu
																</button>

																<input type="hidden" name="shop_id"
																	value="${shopVO.shop_id}"> <input type="hidden"
																	name="action" value="getmenu_disable">
															</FORM>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										<div style="display: inline-block; width: 50px;"></div>
										<div style="display: inline-block; margin-bottom: 10px;">
											<%@ include file="/groupbuy/page2_ByCompositeQuery.file"%>
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