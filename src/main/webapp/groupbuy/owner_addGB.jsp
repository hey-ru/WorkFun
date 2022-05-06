<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shop.model.*"%>
<%
ShopService shopSvc = new ShopService();
ShopVO shopVO = shopSvc.getOneShop(Integer.valueOf(request.getParameter("shop_id")));
pageContext.setAttribute("shopVO", shopVO);
%>

<html>
<head>
<%@ include file="/design/frontmetacss.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />

<style>
body{
overflow-x:hidden;
left:0;
}
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>


</head>

<body style="width:100vw;">
	<div class="wrapper">

		<%@ include file="/design/frontheader.jsp"%>

		<!-- ====================== 內容開始 ====================== -->
		<div style="height:var(--header-height);"></div>
<main class="main">
	<div class="row">
				<!-- ============== Card Header ============== -->
				<div class="card-header py-3" style="background-color: #b0c4de">
					<div class="row">
						<div class="col-10" style="height: 20px; display: inline-block;">
							<h5>
								<strong>建立揪團單</strong>
							</h5>
						</div>
						<div class="col-1" style="height: 20px; display: inline-block;">
							<a href="<%=request.getContextPath()%>/shop/listAllShop.jsp"><strong>回店家列表</strong></a>
						</div>
					</div>
				</div>

				<!-- ============== Card Body ============== -->

	<div class="col-md-5">
		<div class="card" style="border:0px;">
		<div class="card-body">
		<h5 class="card-title">店家資訊</h5>
		<div style="padding:0px 20px;" >
			<div class="row mb-2">
				<label for="inputText" class="col-sm-4 col-form-label">店家名稱</label>
				<div class="col-sm-8">
					<input type="text"  name="shop_name" value="${shopVO.shop_name}" class="form-control-plaintext" readonly/>
				</div>
			</div>
			<div class="row mb-3">
				<label class="col-sm-4 col-form-label">店家類型</label>
				<div class="col-sm-8">
				<p style="padding-top:6px;">
				<c:choose>
				<c:when test="${shopVO.shop_type == 0}">飲料</c:when><c:when test="${shopVO.shop_type == 1}">中式</c:when>
				<c:when test="${shopVO.shop_type == 2}">異國</c:when><c:when test="${shopVO.shop_type == 3}">小吃</c:when>
				<c:when test="${shopVO.shop_type == 4}">素食</c:when><c:when test="${shopVO.shop_type == 5}">其他</c:when>
				</c:choose>
				</p>						
				</div>
			</div>
			<div class="row mb-3">
					<label for="inputText" class="col-sm-4 col-form-label">地址</label>
					<div class="col-sm-8">
						<input type="TEXT" name="address" value="${shopVO.address}"  class="form-control-plaintext" readonly/>
					</div>
				</div>
				<div class="row mb-3">
					<label for="inputText" class="col-sm-4 col-form-label">電話</label>
					<div class="col-sm-8">
						<input type="text" name="tel" value="${shopVO.tel}" class="form-control-plaintext" readonly>
					</div>
				</div>
				<div class="row mb-3">
					<label for="inputText" class="col-sm-4 col-form-label">網站</label>
					<div class="col-sm-5">
						<a href="${shopVO.website}"  class="nav-link ${(shopVO.website=='')? 'disabled':''}">link</a>
					</div>		
				</div>
				<div class="row mb-3">
					<label for="inputText" class="col-sm-4 col-form-label">低消</label>
					<div class="col-sm-8">
						<input type="text" name="min_amt" value="${shopVO.min_amt}" class="form-control-plaintext" readonly>
					</div>
				</div>
				<div class="row">
				<label for="inputText" class="col-sm-2">圖片</label>
				<!-- Button trigger modal -->	
					 <div class="col-sm-3" style="height:100px;width:100px;">
					 <button type="button" data-bs-toggle="modal" data-bs-target="#pic1" style="border:0px">
					<img src="<%=request.getContextPath()%>/util/DBGifReader?id_key=shop_id&id=${shopVO.shop_id}&table=shop&pic=shop_img1" style="max-height:100%;max-width:100%;">
					</button>
					</div>
					<!-- Modal -->
					<div class="modal fade" id="pic1" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <div class="modal-dialog">
					    <div class="modal-content">
					      <div class="modal-body">
					        <img src="<%=request.getContextPath()%>/util/DBGifReader?id_key=shop_id&id=${shopVO.shop_id}&table=shop&pic=shop_img1" style="max-height:100%;max-width:100%;">
					      </div>
					  </div>
					  </div>
					</div>
					<!-- Modal_end -->
					<!-- Button trigger modal -->	
					 <div class="col-sm-3" style="height:100px;width:100px;">
					 <button type="button" data-bs-toggle="modal" data-bs-target="#pic2" style="border:0px">
					<img src="<%=request.getContextPath()%>/util/DBGifReader?id_key=shop_id&id=${shopVO.shop_id}&table=shop&pic=shop_img2" style="max-height:100%;max-width:100%;">
					</button>
					</div>
					<!-- Modal -->
					<div class="modal fade" id="pic2" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <div class="modal-dialog">
					    <div class="modal-content">
					      <div class="modal-body">
					        <img src="<%=request.getContextPath()%>/util/DBGifReader?id_key=shop_id&id=${shopVO.shop_id}&table=shop&pic=shop_img2" style="max-height:100%;max-width:100%;">
					      </div>
					      </div>
					  </div>
					</div>
					<!-- Modal_end -->
					<!-- Button trigger modal -->	
					 <div class="col-sm-3" style="height:100px;width:100px;">
					 <button type="button" data-bs-toggle="modal" data-bs-target="#pic3" style="border:0px">
					<img src="<%=request.getContextPath()%>/util/DBGifReader?id_key=shop_id&id=${shopVO.shop_id}&table=shop&pic=shop_img3" style="max-height:100%;max-width:100%;">
					</button>
					</div>
					<!-- Modal -->
					<div class="modal fade" id="pic3" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <div class="modal-dialog">
					    <div class="modal-content">
					       <div class="modal-body">
					        <img src="<%=request.getContextPath()%>/util/DBGifReader?id_key=shop_id&id=${shopVO.shop_id}&table=shop&pic=shop_img3" style="max-height:100%;max-width:100%;">
					      </div>
					      </div>
					  </div>
					</div>
					<!-- Modal_end -->			
				</div>
				<input type="hidden" name="shop_id" value="${shopVO.shop_id}">
<!-- 				<input type="submit" value="送出新增">				 -->
		</div>			
		</div>
		</div>
	</div>
	<div style="width:90px;"></div>
	<div class="col-md-6">		
	<div class="card" style="background-color:lightyellow;height:100%;">
		<div class="card-body">
		<h5 class="card-title">揪團表單</h5>
				<FORM METHOD="post" style="padding:0px 20px;" ACTION="<%=request.getContextPath()%>/groupbuy/GroupBuyServlet" name="form1">
			<div class="row mb-3">
				<label for="inputText" class="col-sm-2 col-form-label">主揪</label>
				<div class="col-sm-5">
					<input type="text"  name="gb_ownername" class="form-control-plaintext"value="${empVO.empName}" readonly/>
				</div>
				<div class="col-sm-4"></div>
			</div>
				<div class="row mb-3">
					<label for="start_time" class="col-sm-2 col-form-label">開始時間</label>
					<div class="col-sm-5">
						<input name="start_time" id="start_time" type="text" class="form-control-plaintext" readonly autocomplete="off" value="${param.start_time}"/>
					</div>
					<div class="col-sm-4">${errorMsgs.start_time}</div>
				</div>
				<div class="row mb-3">
					<label for="end_time" class="col-sm-2 col-form-label">結束時間</label>
					<div class="col-sm-5">
						<input name="end_time" id="end_time" type="text" class="form-control" autocomplete="off" value="${param.end_time}"/>
					</div>
					<div class="col-sm-4">${errorMsgs.end_time}</div>
				</div>
				<div class="row mb-3">
					<label for="arr_time" class="col-sm-2 col-form-label">到貨時間</label>
					<div class="col-sm-5">
						<input name="arr_time" id="arr_time" type="text" class="form-control" autocomplete="off" value="${param.arr_time}"/>
					</div>
					<div class="col-sm-4">${errorMsgs.arr_time}</div>
				</div>
				<div class="row mb-3">
				<label for="inputText" class="col-sm-2 col-form-label">低消</label>
				<div class="col-sm-5">
					<input type="text"  name="min_amt" class="form-control" autocomplete="off" value="${param.min_amt}"/>
				</div>
				<div class="col-sm-4">${errorMsgs.min_amt}</div>
			</div>
					
					<input type="hidden" name="gb_owner" value="${empVO.empId}">
					<input type="hidden" name="gb_status" value="${param.gb_status}">
					<input type="hidden" name="shop_id" value="${shopVO.shop_id}">
					<input type="hidden" name="shop_name" value="${shopVO.shop_name}">
					<input type="hidden" name="action" value="insert">
					<input type="submit" value="送出新增">
								
		</FORM>
		</div>

</div>
</div>
</div>
	
</main>
		<!-- ======= 內容結束 ======= -->
	</div>
	<!-- ======= Footer ======= -->
	<%@ include file="/design/frontfooter.jsp"%>
	<!-- ======= js ======= -->
	<%@ include file="/design/frontjs.jsp"%>
	

<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<script>
        $.datetimepicker.setLocale('zh');
        $('#start_time').datetimepicker({
 	       theme: '',              //theme: 'dark',
	       timepicker:true,       //timepicker:true,
	       step: 15,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
		   value:   new Date(),
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
 		   //value: '${end_time}', // value:   new Date(),
 		  onShow:function(){
 			   this.setOptions({
 			    minDate:$('#start_time').val()?$('#start_time').val():false,
 			    maxDate:$('#arr_time').val()?$('#arr_time').val():false,
 			   })
 			  }
	});
        $('#arr_time').datetimepicker({
   	       theme: '',              //theme: 'dark',
  	       timepicker:true,       //timepicker:true,
  	       step: 15,                //step: 60 (這是timepicker的預設間隔60分鐘)
  	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
  		   //value: '${arr_time}', // value:   new Date(),
  		 	onShow:function(){
  		   this.setOptions({
  		    minDate:$('#end_time').val()?$('#end_time').val():false
  		   })
  		  }
 	});

        
</script>
	
</body>



</html>