<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>

<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/design/frontmetacss.jsp"%>

<style>
.portfolio-wrap {
	width: 300px;
	height: 400px;
	display: flex
}

body {
	background-color: #DAE5FF;
}

.form-control {
	background-color: #E9EFFF;
}

.btn-primary.disabled, .btn-primary:disabled{
  background-color: #88B8EE;
}
</style>

</head>

<body>

	<!-- ======= Header ======= -->
	<%@ include file="/design/frontheader.jsp"%>
	<!-- End Header -->
	
	<!-- content 如果頁面要可以往下滑就改一下main的height值吧 -->
	<main class="wrapper">
		<div style="height: var(--header-height);"></div>
		<section class="section">
			<div class="row" style="margin-left: 5px;">
				<div class="col-lg-6" style="width: 98%">

					<div class="card" style="background-color: #DAE5FF;">
						<div class="card-body" style="width: 70%;margin: 0 auto;">
							<h5 class="card-title">新增二手商品</h5>
							
							<!-- General Form Elements -->
							<form METHOD="post" ACTION="<%=request.getContextPath()%>/secondhand/SecondHandServlet" name="form1" enctype="multipart/form-data">
								<div class="row mb-3">
									<label for="inputText" class="col-sm-2 col-form-label">拍賣人</label>
									<div class="col-sm-10">
									${empVO.empName}
									<input type="hidden" name="saler" value="${empVO.empId}">
									</div>
								</div>
								<div class="row mb-3">
									<label for="inputText" class="col-sm-2 col-form-label">商品名稱<font color=red><b>*</b></font></label>
									<div class="col-sm-10">
										<input name="name" type="text" id="name" required class="form-control" placeholder="最多20個字" value="${param.name}" maxlength="20">
										<span id="name_error" style="color:red;">${errorMsgs.name}</span><br>
									</div>
								</div>
								<div class="row mb-3">
									<label for="inputNumber" class="col-sm-2 col-form-label">起標價格<font color=red><b>*</b></font></label>
									<div class="col-sm-10">
										<input name="bottom_price" type="number" id="bottom_price" required class="form-control" min="0" value="${param.bottom_price}"
											oninput="if(value < 0){value = 0};
													 if(value > 0){value = Number(value)};
													 if(value > 10000000){value = 10000000}"
										>
										<span id="bottom_price_error" style="color:red;">${errorMsgs.bottom_price}</span><br>
									</div>
								</div>
								<div class="row mb-3">
									<label for="inputNumber" class="col-sm-2 col-form-label">直購金額<font color=red><b>*</b></font></label>
									<div class="col-sm-10">
										<input name="top_price" type="number" id="top_price" required class="form-control" value="${param.top_price}"
											oninput="if(value < 0){value = 0};
													 if(value > 0){value = Number(value)};
													 if(value > 100000000){value = 100000000}"
										>
										<span id="top_price_error" style="color:red;">${errorMsgs.top_price}</span><br>
									</div>
								</div>
								<div class="row mb-3">
									<label for="inputStartTime" class="col-sm-2 col-form-label">起標時間<font color=red><b>*</b></font></label>
									<div class="col-sm-10">
										<input name="start_time" required id="start_time" type="text" class="form-control" value="${param.start_time}"/>
											${errorMsgs.start_time}<br>
									</div>
								</div>
								<div class="row mb-3">
									<label for="inputEndTime" class="col-sm-2 col-form-label">結標時間<font color=red><b>*</b></font></label>
									<div class="col-sm-10">
										<input name="end_time" required id="end_time" type="text" class="form-control" value="${param.end_time}"/>
											${errorMsgs.end_time}<br>
									</div>
								</div>
								<div class="row mb-3">
									<label for="inputNumber" class="col-sm-2 col-form-label">商品圖片1</label>
									<div class="col-sm-10">
										<input class="form-control" type=file name="img1"
											oninput="pic1.src=window.URL.createObjectURL(this.files[0])"
											id="formFile0" value="${param.img1}"> <img id="pic1" style="width:500px"/>
											<br>${errorMsgs.img1}
									</div>
								</div>
								<div class="row mb-3">
									<label for="inputNumber" class="col-sm-2 col-form-label">商品圖片2</label>
									<div class="col-sm-10">
										<input class="form-control" type=file name="img2"
											oninput="pic2.src=window.URL.createObjectURL(this.files[0])"
											id="formFile1" value="${param.img2}"> <img id="pic2" style="width:500px"/>
											<br>${errorMsgs.img2}
									</div>
								</div>
								<div class="row mb-3">
									<label for="inputNumber" class="col-sm-2 col-form-label">商品圖片3</label>
									<div class="col-sm-10">
										<input class="form-control" type=file name="img3"
											oninput="pic3.src=window.URL.createObjectURL(this.files[0])"
											id="formFile2" value="${param.img3}"> <img id="pic3" style="width:500px"/>
											<br>${errorMsgs.img3}
									</div>
								</div>
								<div class="row mb-3">
									<label class="col-sm-2 col-form-label"></label>
									<div class="col-sm-10">
										<input type="hidden" name="action" value="insert"> <input
											type="submit" id="submit" class="btn btn-primary" value="新增商品">
									</div>
								</div>

							</form>

						</div>
					</div>

				</div>
			</div>
		</section>
	</main>
	<!-- End #main -->
	<!-- ======= Footer ======= -->
	<%@ include file="/design/frontfooter.jsp"%>
		
		<!-- End  Footer -->

	<!-- Vendor JS Files -->
	<%@ include file="/design/frontjs.jsp"%>

</body>


<!-- start_time = new java.sql.Timestamp(((long)(System.currentTimeMillis())/60000)*60000-((((long)(System.currentTimeMillis())/60000)*60000)%900000)+900000); -->

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  long currentTimeMillis = ((System.currentTimeMillis())/60000)*60000 - ((((System.currentTimeMillis())/60000)*60000)%900000);
  long min = 60*1000;
  java.sql.Timestamp start_time = null;
  try {
	    start_time = java.sql.Timestamp.valueOf(request.getParameter("start_time").trim());
   } catch (Exception e) {
	    start_time = new java.sql.Timestamp(currentTimeMillis+min*15);
   }
%>
<% 
  java.sql.Timestamp end_time = null;
  try {
	    end_time = java.sql.Timestamp.valueOf(request.getParameter("end_time").trim());
   } catch (Exception e) {
	    end_time = new java.sql.Timestamp(currentTimeMillis+min*15*13);
   }
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
	
	

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
// 	js正則前後要加/
	let nameReg = /^[(\u4e00-\u9fa5)(a-zA-Z0-9_)(\s)]{1,20}$/;
	
	$("#name").blur(function(){
  	  if($(this).val().trim() == ""){
  		  $('#name_error').text('請填入商品名稱!');
  		checkSubmit();
  	  }else if(nameReg.test($(this).val())){
            $('#name_error').text("");
            checkSubmit();
        }else{
            $('#name_error').text('請輸入20字內的中、英文或數字');
            checkSubmit();
        }
    });
	
	$("#bottom_price").blur(function(){
		if($(this).val().trim() <= 0) {
			$("#bottom_price_error").text("請填入大於0或正確的金額!");
			checkSubmit();
		}else if (Number($(this).val().trim()) > 10000000) {
			$("#bottom_price_error").text("請填入小於10,000,000的金額!");
			checkSubmit();
		}else {
			$("#bottom_price_error").text("");
			checkSubmit();
			if(Number($("#bottom_price").val().trim()) >= Number($("#top_price").val().trim())) {
				$("#top_price_error").text("請填入大於起標價格的金額!");
				checkSubmit();
			}else if (Number($("#bottom_price").val().trim()) > 100000000) {
				$("#top_price_error").text("請填入小於100,000,000的金額!");
				checkSubmit();
			}else {
				$("#top_price_error").text("");
				checkSubmit();
			}
		}
	});
	
	$("#top_price").blur(function(){
		if ($("#bottom_price").val().trim() > 0) {
			if(Number($("#bottom_price").val().trim()) >= Number($("#top_price").val().trim())) {
				$("#top_price_error").text('請填入大於起標價格的金額!');
				checkSubmit();
			}else if (Number($("#bottom_price").val().trim()) > 100000000) {
				$("#top_price_error").text('請填入小於100,000,000的金額!');
				checkSubmit();
			}else {
				$("#top_price_error").text('');
				checkSubmit();
			}
		}else {
			$('#top_price_error').text('請填入大於起標價格的金額!');
			checkSubmit();
		}
	});
	
	submitDisable();
	
	function submitDisable() {
		document.getElementById("submit").disabled = true;
	}
	
	function submitEnable() {
		document.getElementById("submit").disabled = false;
	}
	
	function checkSubmit() {
		if (document.getElementById("name_error").innerHTML === "" && document.getElementById("bottom_price_error").innerHTML === "" && document.getElementById("top_price_error").innerHTML === "" && $("#name").val().trim() != "" && $("#bottom_price").val().trim() != "" && $("#top_price").val().trim() != "") {
			submitEnable();
		} else {
			submitDisable();
		}
	}

	$.datetimepicker.setLocale('zh');
	$('#start_time').datetimepicker({
		theme: '',              //theme: 'dark',
		timepicker:true,       //timepicker:true,
		step: 15,                //step: 60 (這是timepicker的預設間隔60分鐘)
		format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
		value: '<%=start_time%>', // value:   new Date(),
		onShow:function(){
			this.setOptions({
				minDate:0,
				maxDate:$('#end_time').val()?$('#end_time').val():false
			})
		}
	});
	$('#end_time').datetimepicker({
		theme: '',              //theme: 'dark',
		timepicker:true,       //timepicker:true,
		step: 15,                //step: 60 (這是timepicker的預設間隔60分鐘)
		format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
		value: '<%=end_time%>', // value:   new Date(),
		onShow:function(){
			this.setOptions({
				minDate:$('#start_time').val()?$('#start_time').val():false
			})
		}
	});
</script>

</html>