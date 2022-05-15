<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.equipment.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>


<jsp:useBean id="listByCompositeQuery" scope="request"
	type="java.util.List<EquipmentVO>" />
<%
String yourServlet = "/equipment/equipment.do";
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
	height: 50px;
}

td {
	vertical-align: middle;
	text-align: center;
	height: 130px;
}

.table-responsive {
	overflow-x: visible;
}

.row {
	margin-top: 20px;
}

.col-sm-12 {
	margin: auto;
	padding: 0px 40px;
}

.my-1 {
	margin: auto;
	padding: 0px 25px;
}

.col-10 {
	width: 80%;
}

.mt-2 {
	padding-right: 20px;
	margin: auto;
	margin-right: 35px;
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



				<!-- ======= 內容開始 ======= -->



				<div class="row">
					<div class="col-10" style="height: 60px; display: inline-block;">
						<form class="my-1" METHOD="post"
							ACTION="<%=request.getContextPath()%>/equipment/equipment.do"
							name="form1">

							<jsp:useBean id="equipmentSvc1" scope="page"
								class="com.equipment.model.EquipmentService" />
<!-- 							<div class="form-group col-2" style="display: inline-block;"> -->

<!-- 								<select class="form-control" id="exampleFormControlSelect1" -->
<!-- 									style="border: gray solid 2px;" name="eq_status"> -->
<!-- 									<option value="">選擇器材狀態</option> -->
<!-- 									<option value="0">上架</option> -->
<!-- 									<option value="1">未歸還的器材</option> -->
<!-- 									<option value="2">送修中</option> -->
<!-- 									<option value="3">下架</option> -->
<!-- 								</select> -->
<!-- 							</div> -->

							<div class="form-group col-3" style="display: inline-block">
								<input type="text" class="form-control"
									id="exampleformControlInput1" placeholder="搜尋器材名稱"
									style="border: gray solid 2px;" name="eq_name"
									value="${param.eqName}">
							</div>
							<input type="hidden" name="action" value="listByCompositeQuery">
							<button type="submit" class="btn btn-dark mb-2 mt-1 col"
								style="display: inline-block; width: 100px;">搜尋</button>
						</form>
					</div>
				</div>

				<div class="col-2 mt-2" style="left: 0;">

					<a
						href='${pageContext.request.contextPath}/equipment/backAddEqpt.jsp'><button
							type="button" class="btn btn-warning btn-lg"
							style="background-color: #36BF36; color: #FFFFFF; font-weight: bold; width: 200px;">新增器材</button></a>
				</div>

			</div>

			<div class="page1">
				<%@ include file="/design/page1_ByCompositeQuery.file"%>
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
									style="width: 50px;">器材編號</th>

								<th class="sorting" tabindex="0" aria-controls="dataTable"
									rowspan="1" colspan="1"
									aria-label="Position: activate to sort column ascending"
									style="width: 100px;">器材名稱</th>

								<th class="sorting" tabindex="0" aria-controls="dataTable"
									rowspan="1" colspan="1"
									aria-label="Position: activate to sort column ascending"
									style="width: 30px;">金額</th>

								<th class="sorting" tabindex="0" aria-controls="dataTable"
									rowspan="1" colspan="1"
									aria-label="Office: activate to sort column ascending"
									style="width: 100px;">狀態</th>

								<th class="sorting" tabindex="0" aria-controls="dataTable"
									rowspan="1" colspan="1"
									aria-label="Age: activate to sort column ascending"
									style="width: 200px;">器材介紹</th>

								<!-- 									<th class="sorting" tabindex="0" aria-controls="dataTable" -->
								<!-- 										rowspan="1" colspan="1" -->
								<!-- 										aria-label="Office: activate to sort column ascending" -->
								<!-- 										style="width: 200px;">器材規格</th> -->

								<th class="sorting" tabindex="0" aria-controls="dataTable"
									rowspan="1" colspan="1"
									aria-label="Salary: activate to sort column ascending"
									style="width: 100px;">圖片</th>

								<!-- 									<th class="sorting" tabindex="0" aria-controls="dataTable" -->
								<!-- 										rowspan="1" colspan="1" -->
								<!-- 										aria-label="Salary: activate to sort column ascending" -->
								<!-- 										style="width: 50px;">圖片2</th> -->

								<!-- 									<th class="sorting" tabindex="0" aria-controls="dataTable" -->
								<!-- 										rowspan="1" colspan="1" -->
								<!-- 										aria-label="Salary: activate to sort column ascending" -->
								<!-- 										style="width: 50px;">圖片3</th> -->

								<th class="sorting" tabindex="0" aria-controls="dataTable"
									rowspan="1" colspan="1"
									aria-label="Salary: activate to sort column ascending"
									style="width: 50px;"></th>

							</tr>

						</thead>


						<tbody>

							<c:forEach var="equipmentVO" items="${listByCompositeQuery}"
								begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

								<tr>
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

									<%-- 										<td>${equipmentVO.introduction}</td> --%>

									<td>${equipmentVO.spec}</td>

									<td><img
										src="<%=request.getContextPath()%>/util/DBGifReader?id_key=equipment_id&id=${equipmentVO.equipmentId}&table=equipment&pic=img1"
										style="max-height: 100%;"></td>

									<!-- 										<td><img -->
									<%-- 											src="<%=request.getContextPath()%>/util/DBGifReader?id_key=equipment_id&id=${equipmentVO.equipmentId}&table=equipment&pic=img2" --%>
									<!-- 											style="max-height: 100%;"></td> -->

									<!-- 										<td><img -->
									<%-- 											src="<%=request.getContextPath()%>/util/DBGifReader?id_key=equipment_id&id=${equipmentVO.equipmentId}&table=equipment&pic=img3" --%>
									<!-- 											style="max-height: 100%;"></td> -->

									<td>
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/equipment/equipment.do"
											style="margin-bottom: 0px;">
											<input type="submit" value="修改"
												class="btn btn-dark mb-2 mt-1 col"
												style="background-color: #007FFF; color: #FFFFFF; font-weight: bold;">
											<input type="hidden" name="equipmentId"
												value="${equipmentVO.equipmentId}"> <input
												type="hidden" name="action" value="getOne_For_Update">
										</FORM>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<%@ include file="/design/page2_ByCompositeQuery.file"%>
				</div>
			</div>

			<!-- /.container-fluid -->
		</div>
		<!-- End of Main Content -->
	</div>



	<!-- End of Content Wrapper -->


	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<%@ include file="/design/backjs.jsp"%>


	<script type="text/javascript">
		$("tbody tr").css("background-color", function(index) {
			return index % 2 == 0 ? "#dcdcdc" : "";
		});
	</script>

</body>

</html>