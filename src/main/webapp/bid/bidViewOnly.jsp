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
								src="<%=request.getContextPath()%>/util/DBGifReader?pic=img1&table=second_hand&id_key=second_hand_id&id=${secondHandVO.second_hand_id}"
								alt="Profile" style="width: 365px;" id="showPic">
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
								<!-- Button trigger modal end-->
							</div>
						</div>
					</div>
				</div>

				<div class="col-xl-6">

					<div class="card">
						<div class="card-body pt-3">
							<!-- Bordered Tabs -->
							<ul class="nav nav-tabs nav-tabs-bordered">

								<li class="nav-item">
									<button class="nav-link active" data-bs-toggle="tab"
										data-bs-target="#profile-overview">Overview</button>
								</li>

							</ul>
							<div class="tab-content pt-2">

								<div class="tab-pane fade show active profile-overview"
									id="profile-overview">
									<h5 class="card-title">競標商品</h5>
									<p class="col-lg-3 col-md-4 label">${secondHandVO.name}</p>

									<h5 class="card-title">拍賣人</h5>
									<p class="small fst-italic">${secondHandVO.empVO1.empName}</p>

									<div class="row">
										<div class="col-lg-3 col-md-4 label">起標價</div>
										<div class="col-lg-9 col-md-8">${secondHandVO.bottom_price}</div>
									</div>

									<div class="row">
										<div class="col-lg-3 col-md-4 label">直購價</div>
										<div class="col-lg-9 col-md-8">${secondHandVO.top_price}</div>
									</div>

									<div class="row">
										<div class="col-lg-3 col-md-4 label">競標時間</div>
										<div class="col-lg-9 col-md-8">${secondHandVO.start_time}
											~ ${secondHandVO.end_time}</div>
									</div>

									<div class="row">
										<div class="col-lg-3 col-md-4 label">當前最高出價人</div>
										<div class="col-lg-9 col-md-8">
										<c:if test="${secondHandVO.bidVO.bidder==0}">
											尚未有人出價
										</c:if>
										<c:if test="${secondHandVO.bidVO.bidder!=0}">
											${secondHandVO.empVO2.empName}
										</c:if>
										</div>
									</div>

									<div class="row">
										<div class="col-lg-3 col-md-4 label">當前價格</div>
										<div class="col-lg-9 col-md-8">${secondHandVO.bidVO.bidder==0?secondHandVO.bottom_price:secondHandVO.bidVO.price}</div>
									</div>

								</div>

							</div>
							<!-- End Bordered Tabs -->
							
							<c:if test="${secondHandVO.bidVO.bidder == empVO.empId}">
								<div class="col-lg-9 col-md-4 label">恭喜你得標,請與 ${secondHandVO.empVO2.empName} 聯絡</div>
							</c:if>
							
							bidViewOnly

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