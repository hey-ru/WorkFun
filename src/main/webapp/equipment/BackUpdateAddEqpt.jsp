<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.equipment.model.*"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">




<title>修改器材商品</title>

<!-- Favicons -->

<link href="${pageContext.request.contextPath}/assets/img/wf.png"
	rel="icon">
<link href="${pageContext.request.contextPath}/assets/img/wf.png"
	rel=" apple-touch-icon">

<!-- Google Fonts -->

<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link
	href="${pageContext.request.contextPath}/assets/vendor/aos/aos.css"
	rel="stylesheet">

<link
	href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<link
	href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">

<link
	href="${pageContext.request.contextPath}/assets/vendor/boxicons/css/boxicons.min.css"
	rel="stylesheet">

<link
	href="${pageContext.request.contextPath}/assets/vendor/glightbox/css/glightbox.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/assets/vendor/swiper/swiper-bundle.min.css"
	rel="stylesheet">

<!-- Template Main CSS File -->

<link href="${pageContext.request.contextPath}/equipment/eqpt.css"
	rel="stylesheet">

</head>

<body>
	<script type="text/javascript">
	function changeText(){
		document.getElementById("spec").value=document.getElementById("spec1").value;
	}
</script>

	<!-- ======= Header ======= -->

	<!-- End Header -->
	<!-- content 如果頁面要可以往下滑就改一下main的height值吧 -->
<!-- 	<main id="main" class="main"> -->
<%-- 	<c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>

		<section class="section">
			<div class="row">
				<div class="col-lg-6" style="width: 70%; margin: 60px auto;">

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
											<option value="" selected>請選擇狀態</option>
											<option value="0" ${(param.eqStatus==0)? 'selected':'' }>上架</option>
											<option value="1" ${(param.eqStatus==1)? 'selected':'' }>未歸還器材</option>
											<option value="2" ${(param.eqStatus==2)? 'selected':'' }>送修中</option>
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
									<label for="inputText" class="col-sm-2 col-form-label">詳細規格:
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

	<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

	<%
	java.sql.Timestamp start_time = null;
	try {
		start_time = java.sql.Timestamp.valueOf(request.getParameter("start_time").trim());
	} catch (Exception e) {
		start_time = new java.sql.Timestamp(((long) (System.currentTimeMillis()) / 60000) * 60000
		- ((((long) (System.currentTimeMillis()) / 60000) * 60000) % 900000) + 900000);
	}
	%>
	<%
	java.sql.Timestamp end_time = null;
	try {
		end_time = java.sql.Timestamp.valueOf(request.getParameter("end_time").trim());
	} catch (Exception e) {
		end_time = new java.sql.Timestamp(((long) (System.currentTimeMillis()) / 60000) * 60000
		- ((((long) (System.currentTimeMillis()) / 60000) * 60000) % 900000) + 1800000);
	}
	%>
	<link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
	<script
		src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

	<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

	<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
 	       theme: '',              //theme: 'dark',
	       timepicker:true,       //timepicker:true,
	       step: 15,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
		   value: '<%=start_time%>', // value:   new Date(),
        });
        $('#f_date2').datetimepicker({
  	       theme: '',              //theme: 'dark',
 	       timepicker:true,       //timepicker:true,
 	       step: 15,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
 		   value: '<%=end_time%>
		', // value:   new Date(),
		});
	</script>
</html>