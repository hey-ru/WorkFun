<%@page import="com.booking.model.BookingVO"%>
<%@page import="com.booking.model.BookingService"%>
<%@page import="com.equipment.model.EquipmentVO"%>
<%@page import="com.equipment.model.EquipmentService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>

<%
Integer equipmentId = Integer.valueOf(request.getParameter("equipmentId"));

EquipmentService equipmentSvc = new EquipmentService();
EquipmentVO equipmentVO = equipmentSvc.getByEqId(equipmentId);
pageContext.setAttribute("equipmentVO", equipmentVO);
%>


<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/design/frontmetacss.jsp"%>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
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


</head>

<body style="height: auto">

	<!-- ======= Header ======= -->
	<%@ include file="/design/frontheader.jsp"%>
	<!-- End Header -->
	<!-- content 如果頁面要可以往下滑就改一下main的height值吧 -->
	<main id="main">

		<section class="section profile">
			<div class="row">
				<div class="col-xl-6">

					<div class="card">
						<div
							class="card-body profile-card pt-4 d-flex flex-column align-items-center">

							<img
								src="<%=request.getContextPath()%>/util/DBGifReader?pic=img1&table=equipment&id_key=equipment_id&id=${equipmentVO.equipmentId}"
								alt="Profile" style="width: 365px;" id="showPic">
							<div class="row" style="margin-top: 10px;">
								<!-- Button trigger modal -->
								<div class="col-sm-3" style="height: 100px; width: 100px;">
									<button type="button" data-bs-toggle="modal"
										data-bs-target="#pic1" style="border: 0px" id="btn1">
										<img
											src="<%=request.getContextPath()%>/util/DBGifReader?pic=img1&table=equipment&id_key=equipment_id&id=${equipmentVO.equipmentId}"
											style="max-height: 100%; max-width: 100%;">
									</button>
								</div>
								<!-- Button trigger modal end-->
								<!-- Button trigger modal -->
								<div class="col-sm-3" style="height: 100px; width: 100px;">
									<button type="button" data-bs-toggle="modal"
										data-bs-target="#pic2" style="border: 0px" id="btn2">
										<img
											src="<%=request.getContextPath()%>/util/DBGifReader?pic=img2&table=equipment&id_key=equipment_id&id=${equipmentVO.equipmentId}"
											style="max-height: 100%; max-width: 100%;">
									</button>
								</div>

								<!-- Button trigger modal -->

								<div class="col-sm-3" style="height: 100px; width: 100px;">
									<button type="button" data-bs-toggle="modal"
										data-bs-target="#pic3" style="border: 0px" id="btn3">
										<img
											src="<%=request.getContextPath()%>/util/DBGifReader?pic=img2&table=equipment&id_key=equipment_id&id=${equipmentVO.equipmentId}"
											style="max-height: 100%; max-width: 100%;">
									</button>
								</div>

							</div>
						</div>
					</div>
				</div>

				<div class="col-xl-6">

					<div class="card">
						<div class="card-body pt-3">

							<div class="tab-content pt-2">

								<h2 class="card-title">${equipmentVO.eqName}</h2>
								<p class="col-lg-3 col-md-4 label"></p>

								<h5 class="card-title"></h5>
								<p class="small fst-italic">${equipmentVO.spec}</p>

								<h5 class="card-title">
									<span style="text-decoration: line-through">逾期總罰金</span>
								</h5>
								<p class="small fst-italic">
									<font color="#FF0000">$${equipmentVO.price}</font>
								</p>

							</div>
							<form class="my-1" method="post"
								action="<%=request.getContextPath()%>/booking/booking.do" name="form1">
								<label for="from">租借日 : </label> 
								<input type="text" id="f_date1" name="startDate" autocomplete="off" value="${param.startDate}"> <br><br>
								<label for="from">歸還日 : </label>
								<input type="text" id="f_date2" name="endDate" autocomplete="off" value="${param.endDate}">

								<input type="hidden" name="equipmentId" value="${equipmentVO.equipmentId}"> 
								
								<input type="hidden" name="empId" value="${empVO.empId}"> 
								
								<input type="hidden" name="startDate" value="${bookingVO.startDate}">
								
								<input type="hidden" name="endDate" value="${bookingVO.endDate}">
								
								<input type="hidden" name="returnStatus" value="${bookingVO.returnStatus}"> 
								<input type="hidden" name="action" value="insert"> 
								<input type="submit" class="btn btn-primary mb-2 mt-1 col" style="display: inline-block;" value="我要預約">
							</form>

						</div>
						<!-- End Bordered Tabs -->
					</div>
				</div>
			</div>

		</section>
	</main>
	<!-- End #main -->
	<!-- ======= Footer ======= -->
	<%-- 	<%@ include file="/design/frontfooter.jsp"%> --%>

	<!-- End  Footer -->

	<!-- Vendor JS Files -->

	<%-- 	<%@ include file="/design/frontjs.jsp"%> --%>

	<script>
	
	$("#btn1").click(function(){
		$("#showPic").attr('src','<%=request.getContextPath()%>/util/DBGifReader?pic=img1&table=equipment&id_key=equipment_id&id=${equipmentVO.equipmentId}')
	});
	$("#btn2").click(function(){
		$("#showPic").attr('src','<%=request.getContextPath()%>/util/DBGifReader?pic=img2&table=equipment&id_key=equipment_id&id=${equipmentVO.equipmentId}')
	});
	$("#btn3").click(function(){
		$("#showPic").attr('src','<%=request.getContextPath()%>/util/DBGifReader?pic=img3&table=equipment&id_key=equipment_id&id=${equipmentVO.equipmentId}')
	});
	
	<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->
	
//       1.以下為某一天之前的日期無法選擇
         var somedate1 = new Date();
         $('#f_date1').datetimepicker({
        	 step: 1,
        	 format:'Y-m-d H:i:s',
             beforeShowDay: function(date) {
            	 
           	  if (  date.getYear() <  somedate1.getYear() || 
    		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
    		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
                 ) {
                      return [false, ""]
                 }
                 return [true, ""];
         }});
         
         var somedate1 = new Date();
         $('#f_date2').datetimepicker({
        	 format:'Y-m-d H:i:s',
             beforeShowDay: function(date) {
           	  if (  date.getYear() <  somedate1.getYear() || 
    		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
    		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
                 ) {
                      return [false, ""]
                 }
                 return [true, ""];
         }});


		

	</script>
</body>
</html>