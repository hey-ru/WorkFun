<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.equipment.model.*"%>
<%@ page import="java.util.*"%>

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

<%@ include file="/design/frontmetacss.jsp"%>

<style>
th {
	vertical-align: middle;
	text-align: center;
}

td {
	vertical-align: middle;
	text-align: center;
	height: 130px;
}

.table-responsive {
	overflow-x: visible;
}

.wrapper {
	overflow-x: hidden;
}

.page1 {
	position: relative;
	left: 30px;
	top: 15px;
}

</style>

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
				<nav class="navbar navbar-expand navbar-light bg-dark topbar mb-4 static-top shadow">

					<!-- Topbar Navbar -->
					<ul class="navbar-nav bg-dark ml-auto">

						<!-- Nav Item - User Information -->
						<li class="nav-item no-arrow pr-4"><a
							href="<%=request.getContextPath()%>/home/home.jsp"> <i
								class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
								Back Home</a>
						</li>

					</ul>

				</nav>
				<!-- End of Topbar -->
		<!-- ======= Header ======= -->
<%-- 		<%@ include file="/design/frontheader.jsp"%> --%>

		<!-- ======= 內容開始 ======= -->
		<main id="main" class="main">
			<div class="card shadow mb-4">
				<!--                    <div style="height: var(--header-height); margin-bottom:10px;"></div> -->
				<!-- ============== Card Header ============== -->
				<div class="card-header py-3" style="background-color: #dcdcdc">
					<div class="row">
												<div class="col-11" style="height: 20px; display: inline-block;">
<!-- 													<h5> -->
<!-- 														<strong>店家資訊</strong> -->
<!-- 													</h5> -->
												</div>
						<div class="col-1" style="height: 20px; display: inline-block;">
							<a
								href="<%=request.getContextPath()%>/equipment/backEquipmentHome.jsp"><strong>回器材列表</strong></a>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-sm-12">
						<table class="table table-bordered dataTable" id="dataTable"
							role="grid" aria-describedby="dataTable_info"
							style="width: 100%; cellspacing: 0;">
							<thead>
								<tr role=" row">

									<th class="sorting sorting_asc" tabindex="0"
										aria-controls="dataTable" rowspan="1" colspan="1"
										aria-sort="ascending"
										aria-label="Name: activate to sort column descending"
										>器材編號</th>

									<th class="sorting" tabindex="0" aria-controls="dataTable"
										rowspan="1" colspan="1"
										aria-label="Position: activate to sort column ascending"
										>器材名稱</th>

									<th class="sorting" tabindex="0" aria-controls="dataTable"
										rowspan="1" colspan="1"
										aria-label="Position: activate to sort column ascending"
										>金額</th>

									<th class="sorting" tabindex="0" aria-controls="dataTable"
										rowspan="1" colspan="1"
										aria-label="Office: activate to sort column ascending"
										>狀態</th>

<!-- 									<th class="sorting" tabindex="0" aria-controls="dataTable" -->
<!-- 										rowspan="1" colspan="1" -->
<!-- 										aria-label="Age: activate to sort column ascending" -->
<!-- 										style="width: 200px;">器材介紹</th> -->

									<th class="sorting" tabindex="0" aria-controls="dataTable"
										rowspan="1" colspan="1"
										aria-label="Office: activate to sort column ascending"
										>詳細規格</th>

									<th class="sorting" tabindex="0" aria-controls="dataTable"
										rowspan="1" colspan="1"
										aria-label="Salary: activate to sort column ascending"
										>圖片1</th>

									<th class="sorting" tabindex="0" aria-controls="dataTable"
										rowspan="1" colspan="1"
										aria-label="Salary: activate to sort column ascending"
										>圖片2</th>

									<th class="sorting" tabindex="0" aria-controls="dataTable"
										rowspan="1" colspan="1"
										aria-label="Salary: activate to sort column ascending"
										>圖片3</th>

									<th class="sorting" tabindex="0" aria-controls="dataTable"
										rowspan="1" colspan="1"
										aria-label="Salary: activate to sort column ascending"
										></th>

								</tr>

							</thead>

<%-- 							<%@ include file="/design/page1.file"%> --%>

							<tbody>

								<tr>
									<%-- 											<td>${shopVO.shop_id}</td> --%>
									<td>${equipmentVO.equipmentId}</td>

									<td>${equipmentVO.eqName}</td>

									<td>${equipmentVO.price}</td>

									<td><c:choose>
											<c:when test="${equipmentVO.eqStatus == 0}">
											       	上架
											    </c:when>
											<c:when test="${equipmentVO.eqStatus == 1}">
											        未歸還器材
											    </c:when>
											<c:when test="${equipmentVO.eqStatus == 2}">
											        送修中
											    </c:when>
											<c:when test="${equipmentVO.eqStatus == 3}">
											        下架
											    </c:when>
										</c:choose></td>

<%-- 									<td>${equipmentVO.introduction}</td> --%>

									<td>${equipmentVO.spec}</td>

									<td><img
										src="<%=request.getContextPath()%>/util/DBGifReader?id_key=equipment_id&id=${equipmentVO.equipmentId}&table=equipment&pic=img1"
										style="max-height: 100%;"></td>

									<td><img
										src="<%=request.getContextPath()%>/util/DBGifReader?id_key=equipment_id&id=${equipmentVO.equipmentId}&table=equipment&pic=img2"
										style="max-height: 100%;"></td>

									<td><img
										src="<%=request.getContextPath()%>/util/DBGifReader?id_key=equipment_id&id=${equipmentVO.equipmentId}&table=equipment&pic=img3"
										style="max-height: 100%;"></td>


									<td>
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/equipment/equipment.do"
											style="margin-bottom: 0px;">
											<input type="submit" value="修改" class="btn btn-dark mb-2 mt-1 col" style="background-color: #007FFF; color: #FFFFFF; font-weight: bold;"> <input type="hidden"
												name="equipmentId" value="${equipmentVO.equipmentId}"> <input
												type="hidden" name="action" value="getOne_For_Update">
										</FORM>
									</td>
								
									<!-- 			<td> -->
									<%-- <FORM METHOD="post"
                                                                                ACTION="<%=request.getContextPath()%>/emp/emp.do"
                                                                                style="margin-bottom: 0px;"> --%>
									<!-- 			     <input type="submit" value="刪除"> -->
									<%-- <input type="hidden" name="empno"
                                                                                    value="${empVO.empno}"> --%>
									<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
									<!-- 			</td> -->
								</tr>
							</tbody>
						</table>


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

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

<%@ include file="/design/backjs.jsp"%>

<script type="text/javascript">
// 		$("tbody tr").css("background-color", function(index) {
// 			return index % 2 == 0 ? "#dcdcdc" : "";
// 		});
</script>

</body>

</html>