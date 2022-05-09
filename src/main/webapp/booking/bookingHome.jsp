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

<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>




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
							    <label for="from">起訖日 : </label>
							    <input type="text" id="from" name="from">
							    <label for="to"> 到 </label>
							    <input type="text" id="to" name="to">

							<form class="my-1" method="post" action="<%=request.getContextPath()%>/booking/booking.do" name="form1">
								<input type="hidden" name="equipmentId" value="${equipmentVO.equipmentId}"> 
								<input type="hidden" name="empId" value="${empVO.empId}"> 
								<input type="hidden" name="startDate" value="${bookingVO.startDate}">
								<input type="hidden" name="endDate" value="${bookingVO.endDate}">
								<input type="hidden" name="returnStatus" value="${bookingVO.return_status}"> 
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
	

	$(function () {
        var dateFormat = "mm/dd/yy",
            from = $("#from").datepicker({
                    defaultDate: "+1w",
                    changeMonth: true,
                    numberOfMonths: 1
                })
                .on("change", function () {
                    to.datepicker("option", "minDate", getDate(this));
                }),
            to = $("#to").datepicker({
                defaultDate: "+1w",
                changeMonth: true,
                numberOfMonths: 1
           		})
                .on("change", function () {
                    from.datepicker("option", "maxDate", getDate(this));
                });

        function getDate(element) {
            var date;
            try {
                date = $.datepicker.parseDate(dateFormat, element.value);
            } catch (error) {
                date = null;
            }

            return date;
        }
    });
		

	</script>
</body>
</html>