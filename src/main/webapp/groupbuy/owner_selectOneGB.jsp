<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.groupbuy.model.*"%>
<%@ page import="com.groupbuylist.model.*"%>

<!DOCTYPE html>
<html lang="zh-TW">
<head>
<%@ include file="/design/frontmetacss.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />

<style>

.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
 .table-responsive {
    	overflow-x: hidden;
	}
	.table {
	width:70%;
	}
th,td{
	width:200px; 
 } 
</style>

</head>

<body>
	<div class="wrapper">

		<%@ include file="/design/frontheader.jsp"%>

		<!-- ====================== 內容開始 ====================== -->
		<main id="main" class="main">
			<div class="card shadow mb-4">
				<!-- ============== Card Header ============== -->
				<div class="card-header py-3" style="background-color: #FFCC99">
					<div class="row">
						<div class="col-9" style="height: 20px; display: inline-block;">
							<h5>
								<strong>查詢揪團</strong>
							</h5>
								
						</div>
						<div class="col-3" style="height: 20px; display: inline-block;">
							<a href="<%=request.getContextPath()%>/groupbuy/owner_selectGB.jsp"><strong>回查詢揪團</strong></a>
						</div>
					</div>
				</div>
				

				<!-- ============== Card Body ============== -->
				<div class="card-body">
					<div class="table-responsive ">
						<div id="dataTable_wrapper"
							class="dataTables_wrapper dt-bootstrap4">
						<div class="row">
						<FORM METHOD="post" class="col-6" style="padding:0px 20px;margin-bottom:30px;" ACTION="<%=request.getContextPath()%>/groupbuy/GroupBuyServlet" name="form1">
						<h4> ${groupBuyVO.gb_id} &nbsp;&nbsp;&nbsp; ${groupBuyVO.shop_name}</h4>
						
							<div class="row mb-3">
							<label for="arr_time" class="col-sm-2 col-form-label">到貨時間:</label>
							<div class="col-sm-9">
								<input name="arr_time" id="arr_time" type="text" class="form-control-plaintext" style="width:250px; border:1px solid gray; display:inline-block;" autocomplete="off" value="${groupBuyVO.arr_time}"/>
								<input type="submit" value="送出修改到貨時間" style="display:inline-block;margin-left:30px;">
							</div>
								</div>
							
							<input type="hidden" name="gb_id" value="${groupBuyVO.gb_id}">
							<input type="hidden" name="end_time" value="${groupBuyVO.end_time}">
							<input type="hidden" name="action" value="updateArrTime">
						</FORM>	
						<div class="col-2" style="height:40px;">
						<h4><span>總金額: </span><span> ${sumTotal}</span></h4>
		
						</div>
						<div class="col-2" style="height:40px;">
						<button type="button" id="showtable" class="btn btn-info">摺疊/展開</button>	
						</div>
						<c:if test="${closeGB =='close' && groupBuyVO.gb_status != 3}">
						<div class="col-1 mr-1" style="height:40px;" id="closeGB">
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/groupbuy/GroupBuyServlet">
							<input type="submit" class="btn btn-secondary" value="關閉揪團" id="closebtn" style="margin-bottom: 0px;">
							<input type="hidden" name="gb_status" value="3">
							<input type="hidden" name="gb_id" value="${groupBuyVO.gb_id}">
							<input type="hidden" name="front" value="front">
							<input type="hidden" name="action" value="updateGBStatus">					
						</FORM>							
						</div>
						</c:if>
						
						</div>

						<c:forEach var="GBbuyer" items="${GBbuyers}">
						<c:if test="${GBbuyer.total > 0}">
						<div class="card fw-bolder">							
							<div class="card-body bg-white text-dark">
								<div class="row">
								<div class="card-title col-3">
									<h5> ${GBbuyer.buyer} &nbsp;&nbsp;&nbsp; ${GBbuyer.buyer_name}</h5></div>
									 
									<div class="col-2 atotal">總金額: ${GBbuyer.total}</div>
									
									<form class="col-8 row" METHOD="post" ACTION="<%=request.getContextPath()%>/groupbuy/GroupBuyServlet">
									<div class="col-4"><label>付款狀況: </label>
										<input type="radio" name="is_pay" value="0" ${(GBbuyer.is_pay=="0")? 'checked':'' }> 未付款 
										<input type="radio" name="is_pay" value="1" ${(GBbuyer.is_pay=="1")? 'checked':'' }> 已付款 
									</div>
									<div class="col-4"><label>取貨狀況: </label>
										<input type="radio" name="is_pickup" value="0" ${(GBbuyer.is_pickup=="0")? 'checked':'' }> 未取貨 
										<input type="radio" name="is_pickup" value="1" ${(GBbuyer.is_pickup=="1")? 'checked':'' }> 已取貨
										</div>
										<input type="hidden" name="buyer" value="${GBbuyer.buyer}">
										<input type="hidden" name="gb_id" value="${groupBuyVO.gb_id}">
										<input type="hidden" name="action" value="updatePayPickUp">
									<div class="col-1 mb-2">
										<input type="submit" value="送出修改">
									</div>
									</form>
								</div>
									
								
								
									<table class="table table-bordered dataTable" id="dataTable"
										 role="grid" aria-describedby="dataTable_info" style="cellspacing:0;">
										<!-- ========================= 表頭 ========================= -->
										<thead>
											<tr role=" row">
												<th class="sorting sorting_asc" tabindex="0"
													aria-controls="dataTable" rowspan="1" colspan="1"
													aria-sort="ascending"
													aria-label="Name: activate to sort column descending"
													>商品</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Position: activate to sort column ascending"
													>數量</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Position: activate to sort column ascending"
													>金額</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Office: activate to sort column ascending"
													>備註</th>
											</tr>
										</thead>

										<!-- ========================= 表格內容 ========================= -->
										<tbody>
										
										<c:forEach var="groupBuyListVO" items="${groupBuyListVOs}">
										<c:if test="${(groupBuyListVO.buyer) == (GBbuyer.buyer) && (groupBuyListVO.qty) > 0}">
													<tr>
														<td>${groupBuyListVO.item}</td>
														<td>${groupBuyListVO.qty}</td>
														<td>${groupBuyListVO.price}</td>
														<td>${groupBuyListVO.remark}</td>
													</tr>
										</c:if>
										</c:forEach>			
										</tbody>
									</table>
									</div>
								</div>
								</c:if>								
							</c:forEach>
								
							

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

$(function(){
	$("#showtable").click(function(){
	$(".table").slideToggle("fast");
	});
	});
	
$("tbody tr").css("background-color", function(index) {
    return index%2==0?"rgba(211,211,211,0.5)":"";
});

        $.datetimepicker.setLocale('zh');
        $('#arr_time').datetimepicker({
    	       theme: '',              //theme: 'dark',
   	       timepicker:true,       //timepicker:true,
   	       step: 15,                //step: 60 (這是timepicker的預設間隔60分鐘)
   	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
   		   value: '${groupBuyVO.arr_time}', // value:   new Date(),
   		 	onShow:function(){
   		   this.setOptions({
   		    minDate:'${groupBuyVO.end_time}'? '${groupBuyVO.end_time}':0
   		   })
   		  }
  	});
        
       

         
 </script>
</body>

</html>