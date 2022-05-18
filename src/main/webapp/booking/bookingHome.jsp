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

BookingService bookingService = new BookingService();
List<BookingVO> list = bookingService.getBanDate(equipmentId);
pageContext.setAttribute("list", list);

EquipmentService equipmentSvc = new EquipmentService();
EquipmentVO equipmentVO = equipmentSvc.getByEqId(equipmentId);
pageContext.setAttribute("equipmentVO", equipmentVO);
%>


<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/design/frontmetacss.jsp"%>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}

.card-title {
    margin-bottom: 40px;
}

.col-sm-5 {
    margin-bottom: 20px;
}

</style>


</head>

<body style="height: auto">

	<!-- ======= Header ======= -->
	<%@ include file="/design/frontheader.jsp"%>
	<!-- End Header -->
	<!-- content 如果頁面要可以往下滑就改一下main的height值吧 -->
	<main id="main">

		<section class="section profile" style="padding: 10px 30px;">
			<div class="row">
				<div class="col-xl-6">

					<div class="card" style="border: 0;">
						<div
							class="card-body profile-card pt-4 d-flex flex-column align-items-center">

							<img
								src="<%=request.getContextPath()%>/util/DBGifReader?pic=img1&table=equipment&id_key=equipment_id&id=${equipmentVO.equipmentId}"
								alt="Profile" style="width: 450px;" id="showPic">
							<div class="row" style="margin-top: 10px;">
								<!-- Button trigger modal -->
								<div class="col-sm-3" style="height: 150px; width: 150px;">
									<button type="button" data-bs-toggle="modal"
										data-bs-target="#pic1" style="border: 0px" id="btn1">
										<img
											src="<%=request.getContextPath()%>/util/DBGifReader?pic=img1&table=equipment&id_key=equipment_id&id=${equipmentVO.equipmentId}"
											style="max-height: 100%; max-width: 100%;">
									</button>
								</div>
								<!-- Button trigger modal end-->
								<!-- Button trigger modal -->
								<div class="col-sm-3" style="height: 150px; width: 150px;">
									<button type="button" data-bs-toggle="modal"
										data-bs-target="#pic2" style="border: 0px" id="btn2">
										<img
											src="<%=request.getContextPath()%>/util/DBGifReader?pic=img2&table=equipment&id_key=equipment_id&id=${equipmentVO.equipmentId}"
											style="max-height: 100%; max-width: 100%;">
									</button>
								</div>

								<!-- Button trigger modal -->

								<div class="col-sm-3" style="height: 150px; width: 150px;">
									<button type="button" data-bs-toggle="modal"
										data-bs-target="#pic3" style="border: 0px" id="btn3">
										<img
											src="<%=request.getContextPath()%>/util/DBGifReader?pic=img3&table=equipment&id_key=equipment_id&id=${equipmentVO.equipmentId}"
											style="max-height: 100%; max-width: 100%;">
									</button>
								</div>

							</div>
						</div>
					</div>
				</div>

				<div class="col-xl-6">

					<div class="card" style="border: 0;">
						<div class="card-body pt-3">

							<div class="tab-content pt-2">


								<%-- 								<c:forEach var="bookingVO" items="${list}"> --%>
								<%-- 								<fmt:formatDate value="${bookingVO.endDate}" --%>
								<%-- 														pattern="yyyy-MM-dd" /> --%>
								<%-- 								</c:forEach> --%>

								<h2 class="card-title">${equipmentVO.eqName}</h2>
								<p class="col-lg-3 col-md-4 label"></p>


								<h4 class="card-title"><font color="#0000C6">${equipmentVO.spec}</font></h4>
								<p class="small fst-italic"></p>
								<h6 class="card-title">
									🚨 <span style="text-decoration: line-through">器材金額</span>
									(逾期罰金一天為商品20%，最多至60%) 🚨
								</h6>
								<p class="small fst-italic" style="margin-top:-30px;">   <font color="#FF0000">$${equipmentVO.price}</font>
								</p>

							</div>
							<form class="my-1" method="post" id="form"
								action="<%=request.getContextPath()%>/booking/booking.do"
								name="form1">
								<label for="start_time" class="col-sm-2 col-form-label">起始日
									: </label>
								<div class="col-sm-5">
									<input name="startDate" id="start_time" type="text"
										class="form-control" autocomplete="off"
										value="${param.startDate}" required />
								</div>

								<div class="col-sm-4">${errorMsgs.start_date}</div>

								<label for="end_time" class="col-sm-2 col-form-label">歸還日
									: </label>
								<div class="col-sm-5">
									<input name="endDate" id="end_time" type="text"
										autocomplete="off" class="form-control"
										value="${param.endDate}" required />
								</div>
								<div class="col-sm-5">
									<input type="submit" class="btn btn-primary mb-2 mt-1 col" style="display: inline-block; font-weight:bold;" value="點我預約" id="reservationBtn" >
									<input type="hidden" name="equipmentId" value="${equipmentVO.equipmentId}"> 
									<input type="hidden" name="empId" value="${empVO.empId}"> 
									<input type="hidden" name="startDate" value="${bookingVO.startDate}">
									<input type="hidden" name="endDate" value="${bookingVO.endDate}"> 
									<input type="hidden" name="returnStatus" value="${bookingVO.returnStatus}">
									<input type="hidden" name="action" value="insert"> 
									
								</div>
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

	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
	<script
		src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

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
	
    $('#reservationBtn').click(function(){
	    if($("#start_time").val() === '' || $("#end_time").val() === '' ){
	    	Swal.fire({
	    		 icon: 'error',
	    		  title: '殘念',
	    		  text: '請選擇正確的日期 ! ',	    		  
	    	});
	    	return false;
	    }else{

    	Swal.fire({
	    	  title: '預約成功 ! ',
	    	  text: '記得取貨哦 ! 喵喵喵喵喵喵 ~  ',
	    	  imageUrl: '<%=request.getContextPath()%>/booking/images/giphy.gif',
	    	  imageWidth: 300,
	    	  imageHeight: 300,
	    	  timer: 4000,
	    	  imageAlt: 'Custom image'
    	});
    	}
    });
    

	<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->
     
//  	======================================================================
 		
	
	
     let startDate = [
        <c:forEach items="${list}" var="bookingVO" >
       new Date('<fmt:formatDate value="${bookingVO.startDate}"
			pattern="yyyy-MM-dd"/>'),
        </c:forEach>
    ];
    
    console.log(startDate);

	let endDate = [
        <c:forEach items="${list}" var="bookingVO" >
      new Date('<fmt:formatDate value="${bookingVO.endDate}"
			pattern="yyyy-MM-dd"/>'),
        </c:forEach>
    ];
	
	console.log(endDate);



function getDatesInRange(a, b) {

    const dates = [];

	let aTime = a.getTime();
	let bTime = b.getTime();
	if(bTime < aTime) { // swap
		let tmp = aTime;
		aTime = bTime;
		bTime = tmp;
	}
	
    let aDate = new Date(aTime);
    let bDate = new Date(bTime);

    while (aTime <= bTime) {
    	let time = new Date(aTime);
    	let month = time.getMonth() + 1;
    	let day =  time.getDate();
    	if(month < 10) {
    		month = ('0' + month);
    	}
    	if(day < 10){
    		day = '0' + day;
    	}
    	console.log("getDate() = "+time.getDate());
    	let timeStr = time.getFullYear() + '/' + month + '/' + day;
    	dates.push(timeStr);
        aTime += 1000 * 60 * 60 * 24;
//         aDate.setDate(aDate.getDate() + 1);
//         aTime = aDate.getTime();
    }
    console.log("dates = " + dates);
    return dates;
}

const set = new Set();
for(let i = 0; i < startDate.length; i++) {
	let arr = getDatesInRange(startDate[i], endDate[i]);
	console.log("arr = "+arr);
	for(let j = 0; j < arr.length; j++) {
		set.add(arr[j]);
	}
}

const disabledDates = Array.from(set); // set -> array
console.log(disabledDates);

console.log("===========");
let today = new Date();
let month = today.getMonth() + 1;
if(month < 10) {
	month = ('0' + month);
}
let timeStr = today.getFullYear() + '/' + month + '/' + today.getDate();
console.log(timeStr);
console.log("===========");

$.datetimepicker.setLocale('zh');
$('#start_time').datetimepicker({
      theme: '',              //theme: 'dark',
      format:'Y-m-d',         //format:'Y-m-d H:i:s',
	  disabledDates: disabledDates, // 去除特定不含
	  minDate: timeStr,
      timepicker:false,
	   onShow:function(){
		   this.setOptions({
			   
			maxDate:$('#end_time').val()?$('#end_time').val():false
		   })
	   }
});

$('#end_time').datetimepicker({
      theme: '',              //theme: 'dark',
      format:'Y-m-d',         //format:'Y-m-d H:i:s',
      timepicker:false,
	  disabledDates: disabledDates,// 去除特定不含
	  minDate: timeStr,
	  timepicker:false,   
	  onShow:function(){
		   this.setOptions({
			
		    minDate:$('#start_time').val()?$('#start_time').val():false,
		   })
		  }
});


// <====================================================>

// let start = new Date(Date.parse($('#start_time').val()) + 24*60*60*1000);
// let startMonth = start.getMonth() +1;
// let startDate = start.getDate();
// if (startMonth < 10)
// 	startMonth = "0" + startMonth;
// if (startDate < 10)
// 	startDate = "0" + startDate;
// let startval = start.getFullYear() + "/" + startMonth + "/" + startDate;

// $('#start_time').blur(e => {
//     $('#end_time').attr({
//         'min': startval
// //         'max': lastday   //可設可不設(區間)
//     })
// })

// <====================================================>





// console.log(startDate);
// console.log(endDate);

// let j = 0;
// for (let i of startDate) {
//     for (j; j < endDate.length; j++) {
//         console.log(getDatesInRange(new Date(i), new Date(endDate[j])));
//         break;
//     }
//     ++j;
// }

// let year;
// // 帶入[0]
// let date = new Date(Date.parse(startDate[0]));

// // 取出年 yyyy
// year = date.getUTCFullYear();

// // 取出月 mm (月從 0 開始)
// let month;
// month = date.getUTCMonth() +1;

// // 取出日 dd
// let day;
// day = date.getUTCDate();


// let disabledDates = [];

// let startDay;  
// for(let i= 0 ; i < startDate.length ; i++){
// 	  let year = new Date(Date.parse(startDate[i])).getUTCFullYear();
// 	  let month = new Date(Date.parse(startDate[i])).getUTCMonth() +1;
// 	  let day = new Date(Date.parse(startDate[i])).getUTCDate();
// 	  startDay = year+'-'+month+'-'+day;
// 		console.log(startDay);
// // 	  disabledDates.append(today);
// // 	  console.log(today1);
// 	}
// let startDay1 = new Date(startDay);

// let endDay;
// for(let i= 0 ; i < endDate.length ; i++){
// 	  let year = new Date(Date.parse(endDate[i])).getUTCFullYear();
// 	  let month = new Date(Date.parse(endDate[i])).getUTCMonth() +1;
// 	  let day = new Date(Date.parse(endDate[i])).getUTCDate();
// 	  endDay = year+'-'+month+'-'+day;
// 	  console.log(endDay);
// // 	  disabledDates.splice(today2);
// // 	  console.log(disabledDates.splice(today2));
// 	} 

// let endDay1 = new Date(endDay);
	
// // function getDatesInRange(startDate, endDate) {
// // 	  const date = new Date(startDate.getTime());

// // 	  const dates = [];
	  
// // 	  	let year;
		
// // 		// 取出年 yyyy
// // 		year = date.getUTCFullYear();
		
// // 		// 取出月 mm (月從 0 開始)
// // 		let month;
// // 		month = date.getUTCMonth() +1;
		
// // 		// 取出日 dd
// // 		let day;
// // 		day = date.getUTCDate();
		  
// // 		let allDay = year+'-'+month+'-'+day;

// // 	  while (date <= endDate) {
// // 	    dates.push(allDay);
// // 	    date.setDate(date.getDate() + 1);
// // 	  }

// // 	  return dates;
// // 	}
	
// // 	console.log(getDatesInRange(startDay1, endDay1));


// // let today = year+'/'+month+'/'+day;

         
	</script>

</body>
</html>