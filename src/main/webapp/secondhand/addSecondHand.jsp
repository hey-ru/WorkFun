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
</style>

</head>

<body>

	<!-- ======= Header ======= -->
	<%@ include file="/design/frontheader.jsp"%>
	<!-- End Header -->
	
	<!-- content 如果頁面要可以往下滑就改一下main的height值吧 -->
	<main id="main" class="main">

		<section class="section">
			<div class="row">
				<div class="col-lg-6" style="width: 100%">

					<div class="card">
						<div class="card-body">
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
									<label for="inputText" class="col-sm-2 col-form-label">商品名稱</label>
									<div class="col-sm-10">
										<input name="name" type="text" class="form-control" value="${param.name}">
										${errorMsgs.name}<br>
									</div>
								</div>
								<div class="row mb-3">
									<label for="inputNumber" class="col-sm-2 col-form-label">起標價格</label>
									<div class="col-sm-10">
										<input name="bottom_price" type="number" class="form-control" value="${param.bottom_price}">
										${errorMsgs.bottom_price}<br>
									</div>
								</div>
								<div class="row mb-3">
									<label for="inputNumber" class="col-sm-2 col-form-label">直購金額</label>
									<div class="col-sm-10">
										<input name="top_price" type="number" class="form-control" value="${param.top_price}">
										${errorMsgs.top_price}<br>
									</div>
								</div>
								<div class="row mb-3">
									<label for="inputStartTime" class="col-sm-2 col-form-label">起標時間</label>
									<div class="col-sm-10">
										<input name="start_time" id="start_time" type="text"
											class="form-control" value="${param.start_time}"/>
											${errorMsgs.start_time}<br>
									</div>
								</div>
								<div class="row mb-3">
									<label for="inputEndTime" class="col-sm-2 col-form-label">結標時間</label>
									<div class="col-sm-10">
										<input name="end_time" id="end_time" type="text"
											class="form-control" value="${param.end_time}"/>
											${errorMsgs.end_time}<br>
									</div>
								</div>
								<div class="row mb-3">
									<label for="inputNumber" class="col-sm-2 col-form-label">File
										Upload</label>
									<div class="col-sm-10">
										<input class="form-control" type=file name="img1"
											oninput="pic1.src=window.URL.createObjectURL(this.files[0])"
											id="formFile0" value="${param.img1}"> <img id="pic1" style="width:500px"/>
											<br>${errorMsgs.img1}
									</div>
								</div>
								<div class="row mb-3">
									<label for="inputNumber" class="col-sm-2 col-form-label">File
										Upload</label>
									<div class="col-sm-10">
										<input class="form-control" type=file name="img2"
											oninput="pic2.src=window.URL.createObjectURL(this.files[0])"
											id="formFile1" value="${param.img2}"> <img id="pic2" style="width:500px"/>
											<br>${errorMsgs.img2}
									</div>
								</div>
								<div class="row mb-3">
									<label for="inputNumber" class="col-sm-2 col-form-label">File
										Upload</label>
									<div class="col-sm-10">
										<input class="form-control" type=file name="img3"
											oninput="pic3.src=window.URL.createObjectURL(this.files[0])"
											id="formFile2" value="${param.img3}"> <img id="pic3" style="width:500px"/>
											<br>${errorMsgs.img3}
									</div>
								</div>
								<div class="row mb-3">
									<label class="col-sm-2 col-form-label">Submit Button</label>
									<div class="col-sm-10">
										<input type="hidden" name="action" value="insert"> <input
											type="submit" class="btn btn-primary" value="Submit Form">
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
        $('#start_time').datetimepicker({
 	       theme: '',              //theme: 'dark',
	       timepicker:true,       //timepicker:true,
	       step: 15,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
		   value: '<%=start_time%>', // value:   new Date(),
		   onShow:function(){
			   this.setOptions({
				minDate:0,
				minTime:0,
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