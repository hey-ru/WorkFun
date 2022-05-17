<%@page import="com.bid.model.BidVO"%>
<%@page import="java.io.PrintWriter"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.secondHand.model.*"%>

<%
Integer second_hand_id = Integer.valueOf(request.getParameter("second_hand_id"));

SecondHandService secondHandService = new SecondHandService();
SecondHandVO secondHandVO = secondHandService.getOneById(second_hand_id);
pageContext.setAttribute("secondHandVO", secondHandVO);

%>

<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/design/frontmetacss.jsp"%>

<style>
.portfolio-wrap {
	width: 300px;
	height: 400px;
	display: flex;
}

body {
	background-color: #DAE5FF;
}

.btn-primary.disabled, .btn-primary:disabled{
  background-color: #88B8EE;
}
</style>

</head>

<body onload="connect();" onunload="disconnect();">

 <!-- ======= Header ======= -->
 <%@ include file="/design/frontheader.jsp"%>
 <!-- End Header -->
 <!-- content 如果頁面要可以往下滑就改一下main的height值吧 -->
 	<main class="wrapper">
 		<div style="height: var(--header-height);"></div>
 		<section class="section profile">
			<div class="row">
				<div class="col-xl-6">

					<div class="card" style="height:100%; background-color: #F2EEE5;">
						<div
							class="card-body profile-card pt-4 d-flex flex-column align-items-center">
							
							<img
 								src="<%=request.getContextPath()%>/util/DBGifReader?pic=img1&table=second_hand&id_key=second_hand_id&id=${secondHandVO.second_hand_id}"
								alt="Profile" style="max-height: 420px;" id="showPic">
							
							<div class="row" style="margin-top: 10px;">
								<!-- Button trigger modal -->
								<div class="col-sm-3" style="height: 100px; width: 100px;">
									<button type="button" data-bs-toggle="modal"
										data-bs-target="#pic1" style="border: 0px" id="btn1">
										<img
											src="<%=request.getContextPath()%>/util/DBGifReader?pic=img1&table=second_hand&id_key=second_hand_id&id=${secondHandVO.second_hand_id}"
											style="max-height: 100%; max-width: 100%;">
									</button>
								</div>
								<!-- Button trigger modal end-->
								<!-- Button trigger modal -->
								<div class="col-sm-3" style="height: 100px; width: 100px;">
									<button type="button" data-bs-toggle="modal"
										data-bs-target="#pic2" style="border: 0px" id="btn2">
										<img
											src="<%=request.getContextPath()%>/util/DBGifReader?pic=img2&table=second_hand&id_key=second_hand_id&id=${secondHandVO.second_hand_id}"
											style="max-height: 100%; max-width: 100%;">
									</button>
								</div>
								<!-- Button trigger modal -->
								<div class="col-sm-3" style="height: 100px; width: 100px;">
									<button type="button" data-bs-toggle="modal"
										data-bs-target="#pic3" style="border: 0px" id="btn3">
										<img
											src="<%=request.getContextPath()%>/util/DBGifReader?pic=img3&table=second_hand&id_key=second_hand_id&id=${secondHandVO.second_hand_id}"
											style="max-height: 100%; max-width: 100%;">
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="col-xl-6">

					<div class="card" style="height:100%; background-color: #F2EEE5;">
						<div class="card-body pt-3">
							<div style="display:flex">
							<div class="col-lg-4 col-md-4" id="status" style="padding-left: 30px; font-size: 1.75rem; width: 200px">
								<c:choose>
									<c:when test="${secondHandVO.is_deal == 0}">
										競標尚未開始
									</c:when>
									<c:when test="${secondHandVO.is_deal == 1}">
										競標中
									</c:when>
									<c:when test="${secondHandVO.is_deal == 2}">
										已成交
									</c:when>
									<c:otherwise>
										流標
									</c:otherwise>
									</c:choose>
							</div>
<!-- 							<h5 class="card-title" style="padding-left: 30px; font-size: 1.75rem; width: 200px">當前價格</h5> -->
								<div class="col-lg-4 col-md-4 label" style="text-align: end;font-size:1.75rem">當前價格：</div>
								<div class="col-lg-4 col-md-4" style="font-size:1.75rem" id="currentPrice">${secondHandVO.bidVO.bidder == 0 ? secondHandVO.bottom_price : secondHandVO.bidVO.price}元</div>
							
							</div>
							<hr size="1" noshade="noshade" style="border:2px #cb9595 solid;"/>
							<div class="tab-content pt-2" style="padding-left: 30px;">

								<div class="tab-pane fade show active profile-overview"
									id="profile-overview">
									<h5 class="card-title">拍賣人</h5>
									<p class="small fst-italic" style="font-size: 1.5rem; font-weight: 900;">${secondHandVO.empVO1.empName}</p>

									<h5 class="card-title">競標商品</h5>
									<p class="col-lg-3 col-md-4 label">${secondHandVO.name}</p>

									<div class="row" style="margin-top:40px; margin-bottom:10px;">
										<div class="col-lg-4 col-md-4 label">起標價</div>
										<div class="col-lg-8 col-md-8">${secondHandVO.bottom_price}元</div>
									</div>

									<div class="row" style="margin-bottom:10px;">
										<div class="col-lg-4 col-md-4 label">直購價</div>
										<div class="col-lg-8 col-md-8" id="topPrice">${secondHandVO.top_price}元</div>
									</div>

									<div class="row" style="margin-bottom:5px;">
										<div class="col-lg-4 col-md-4 label">競標時間</div>
										<div class="col-lg-8 col-md-8">${secondHandVO.start_time.toString().substring(0, 16)}
											~ ${secondHandVO.end_time.toString().substring(0, 16)}</div>
									</div>

									<div class="row">
										<div class="col-lg-4 col-md-4 label" style="display: flex;align-items: center;">當前最高出價人</div>
<!-- 										下面這段因為innerHTML裡面換行,空格都會算入,所以將他不排版接成一行 -->
										<div class="col-lg-8 col-md-8" id="bidder" style="font-weight: 900;font-size: 30px;"><c:if test="${secondHandVO.bidVO.bidder == 0}">尚未有人出價</c:if><c:if test="${secondHandVO.bidVO.bidder != 0}">${secondHandVO.empVO2.empName}</c:if></div>
									</div>

<!-- 									<div class="row"> -->
<!-- 										<div class="col-lg-4 col-md-4 label">當前價格</div> -->
<%-- 										<div class="col-lg-8 col-md-8" id="currentPrice">${secondHandVO.bidVO.bidder == 0 ? secondHandVO.bottom_price : secondHandVO.bidVO.price}</div> --%>
<!-- 									</div> -->

								</div>

							</div>
							<!-- End Bordered Tabs -->
							
							<div id="notification" style="padding-left: 30px;">
								<c:if test="${secondHandVO.is_deal == 2}">
									<c:if test="${empVO.empId == secondHandVO.bidVO.bidder}">恭喜您得標 請與<span style="font-size: 24px;font-weight: 600;">${secondHandVO.empVO1.empName}</span>聯絡<br>手機 ： ${secondHandVO.empVO1.phone}<br>分機 ： ${secondHandVO.empVO1.extension}</c:if>
									<c:if test="${empVO.empId != secondHandVO.bidVO.bidder}">此商品已經被<span style="font-size: 24px;font-weight: 600;">${secondHandVO.empVO2.empName}</span>買走了</c:if>
								</c:if>
							</div>

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
	<script>
	$("#btn1").click(function(){
		$("#showPic").attr('src','<%=request.getContextPath()%>/util/DBGifReader?pic=img1&table=second_hand&id_key=second_hand_id&id=${secondHandVO.second_hand_id}')
	});
	$("#btn2").click(function(){
		$("#showPic").attr('src','<%=request.getContextPath()%>/util/DBGifReader?pic=img2&table=second_hand&id_key=second_hand_id&id=${secondHandVO.second_hand_id}')
	});
	$("#btn3").click(function(){
		$("#showPic").attr('src','<%=request.getContextPath()%>/util/DBGifReader?pic=img3&table=second_hand&id_key=second_hand_id&id=${secondHandVO.second_hand_id}')
	});
	</script>
</body>

</html>