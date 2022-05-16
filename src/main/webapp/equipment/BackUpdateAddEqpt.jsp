<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.equipment.model.*"%>

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

.col-lg-6 {
    max-width: 70%;
}

.card-body {
    padding: 10px;
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
						<li class="nav-item no-arrow pr-4"><a
							href="<%=request.getContextPath()%>/home/home.jsp"> <i
								class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
								Back Home
						</a></li>

					</ul>
				</nav>	
	
	
	<!-- ======= Header ======= -->
<main id="main" class="main">
		
		<section class="section">
			<div class="row">
				<div class="col-lg-6" style="width: 100%; margin:auto;">

					<div class="card">
						<div class="card-body">
							<h5 class="card-title">修改器材商品</h5>

							<!-- General Form Elements -->
							<form METHOD="post"
								ACTION="<%=request.getContextPath()%>/equipment/equipment.do"
								name="form1" enctype="multipart/form-data">

								<div class="row mb-3">
									<label for="inputText" class="col-sm-2 col-form-label">器材編號:<font
										color=red><b>*</b></font>
									</label>
									<div class="col-sm-10">
										${param.equipmentId} <input name="equipmentId" type="hidden"
											class="form-control" value="${param.equipmentId}">
									</div>
								</div>


								<div class="row mb-3">
									<label for="inputText" class="col-sm-2 col-form-label">器材名稱:
									</label>
									<div class="col-sm-10">
										<input name="eqName" type="text" class="form-control"
											autofocus value="${param.eqName}"><font color=#ff0000>${errorMsgs.eqName}</font>
									</div>
								</div>

								<div class="row mb-3">
									<label for="inputText" class="col-sm-2 col-form-label">金額:
									</label>
									<div class="col-sm-10">
										<input name="price" type="text" class="form-control"
											value="${param.price}"> <font color=#ff0000>${errorMsgs.price}</font>
									</div>
								</div>

								<div class="row mb-3">
									<label for="inputNumber" class="col-sm-2 col-form-label">器材狀態:
									</label>
									<div class="col-sm-10">
										<select name="eqStatus" id="">
<!-- 											<option value="" selected>請選擇狀態</option> -->
											<option value="0" ${(param.eqStatus==0)? 'selected':'' }>上架</option>
<%-- 											<option value="1" ${(param.eqStatus==1)? 'selected':'' }>未歸還器材</option> --%>
<%-- 											<option value="2" ${(param.eqStatus==2)? 'selected':'' }>送修中</option> --%>
											<option value="3" ${(param.eqStatus==3)? 'selected':'' }>下架</option>
										</select> <font color=#ff0000>${errorMsgs.eqStatus}</font>
									</div>
								</div>

								<!-- 								<div class="row mb-3"> -->
								<!-- 									<label for="inputText" class="col-sm-2 col-form-label">商品介紹: -->
								<!-- 									</label> -->
								<!-- 									<div class="col-sm-10"> -->
								<!-- 										<input name="introduction" type="text" class="form-control" -->
								<%-- 											value="${param.introduction}"> --%>
								<%-- 										${errorMsgs.introduction}<br> --%>
								<!-- 										<textarea name="text" class="form-control" cols="84" rows="10"></textarea> -->
								<!-- 									</div> -->
								<!-- 								</div> -->



								<div class="row mb-3">
									<label for="inputText" class="col-sm-2 col-form-label">器材介紹:
									</label>
									<div class="col-sm-10">
										<input name="spec1" type="hidden" id="spec1"
											class="form-control" value="${param.spec}" />
										<textarea name="spec" id="spec" rows="9" cols="64"
											class="form-control">${param.spec}</textarea>
										<font color=#ff0000>${errorMsgs.spec}</font>
									</div>
								</div>


								<div class="row mb-3">
									<label for="inputNumber" class="col-sm-2 col-form-label">上傳圖片:</label>
									<div class="col-sm-10">
										<input class="form-control" type=file name="img1"
											oninput="pic1.src=window.URL.createObjectURL(this.files[0])"
											id="formFile0" value="${param.img1}"> <img id="pic1"
											style="width: 150px" /> <br>${errorMsgs.img1}
									</div>
								</div>

								<div class="row mb-3">
									<label for="inputNumber" class="col-sm-2 col-form-label">上傳圖片:</label>
									<div class="col-sm-10">
										<input class="form-control" type=file name="img2"
											oninput="pic2.src=window.URL.createObjectURL(this.files[0])"
											id="formFile1" value="${param.img2}"> <img id="pic2"
											style="width: 150px" /> <br>${errorMsgs.img2}
									</div>
								</div>

								<div class="row mb-3">
									<label for="inputNumber" class="col-sm-2 col-form-label">上傳圖片:</label>
									<div class="col-sm-10">
										<input class="form-control" type=file name="img3"
											oninput="pic3.src=window.URL.createObjectURL(this.files[0])"
											id="formFile2" value="${param.image3}"> <img
											id="pic3" style="width: 150px" /> <br>${errorMsgs.img3}
									</div>
								</div>
								<div class="row mb-3">
									<label class="col-sm-2 col-form-label"></label>
									<div class="col-sm-10">
										<input type="hidden" name="action" value="update"> <input
											type="submit" class="btn btn-primary" value="送出修改">
									</div>
								</div>
							</form>

						</div>
						<div class="col-1"></div>
						<div class="col-4">
							<div style="height: 150px; padding: 5px;">
								<img
									src="<%=request.getContextPath()%>/util/DBGifReader?id_key=equipment_id&id=${param.equipmentId}&table=equipment&pic=img1"
									style="max-height: 100%;" id="oldpic1"> <img id="pic1"
									style="max-height: 100%;">
							</div>
							<div style="height: 150px; padding: 5px;">
								<img
									src="<%=request.getContextPath()%>/util/DBGifReader?id_key=equipment_id&id=${param.equipmentId}&table=equipment&pic=img2"
									style="max-height: 100%;" id="oldpic2"> <img id="pic2"
									style="max-height: 100%;">
							</div>
							<div style="height: 150px; padding: 5px;">
								<img
									src="<%=request.getContextPath()%>/util/DBGifReader?id_key=equipment_id&id=${param.equipmentId}&table=equipment&pic=img3"
									style="max-height: 100%;" id="oldpic3"> <img id="pic3"
									style="max-height: 100%;">
							</div>
						</div>

					</div>
				</div>

			</div>

		</section>
	</main>
	<!-- End of Main  -->
	<!-- ======= Footer ======= -->
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

	<script type="text/javascript">
	function changeText(){
		document.getElementById("spec").value=document.getElementById("spec1").value;
	}
</script>

</body>

</html>
