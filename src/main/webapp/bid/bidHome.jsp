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
								<!-- 								Modal -->
								<!-- 								<div class="modal fade" id="pic3" tabindex="-1" -->
								<!-- 									aria-labelledby="exampleModalLabel" aria-hidden="true"> -->
								<!-- 									<div class="modal-dialog"> -->
								<!-- 										<div class="modal-content"> -->
								<!-- 											<div class="modal-body"> -->
								<!-- 												<img -->
								<%-- 													src="<%=request.getContextPath()%>/util/DBGifReader?pic=img3&table=second_hand&id_key=second_hand_id&id=${secondHandVO.second_hand_id}" --%>
								<!-- 													style="max-height: 100%; max-width: 100%;"> -->
								<!-- 											</div> -->
								<!-- 										</div> -->
								<!-- 									</div> -->
								<!-- 								</div> -->
								<!-- 								Modal_end -->
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
									<p class="small fst-italic">${secondHandVO.empVO.empName}</p>

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
											${secondHandVO.bidVO.bidder}
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

							<%-- 
							<form class="my-1" METHOD="post"
								ACTION="<%=request.getContextPath()%>/bid/BidServlet"
								name="form1">
								<div class="form-group col-3" style="display: inline-block">
									<input type="text" class="form-control"
										id="exampleFormControlInput1" placeholder="輸入競標金額"
										style="border: gray solid 2px;" name="name"
										value="${secondHandVO.bidVO.price+1}
 										<c:if test="${secondHandVO.bidVO.bidder==0}">${secondHandVO.bidVO.price}</c:if>
 										<c:if test="${secondHandVO.bidVO.bidder!=0}">${secondHandVO.bidVO.price+1}</c:if>">
								</div>
								<input type="hidden" name="action" value="update"> <input
									type="submit" class="btn btn-primary mb-2 mt-1 col"
									style="display: inline-block;" value="送出"></input>
							</form>
--%>

							<form class="my-1" METHOD="post"
								ACTION="<%=request.getContextPath()%>/bid/BidServlet"
								name="form1">
								<div class="form-group col-3" style="display: inline-block">
									<input type="text" class="form-control"
										id="exampleFormControlInput1" placeholder="輸入競標金額"
										style="border: gray solid 2px;" name="price"
										<c:if test="${secondHandVO.bidVO.bidder==0}">
										value="${secondHandVO.bottom_price}"
										</c:if>
										<c:if test="${secondHandVO.bidVO.bidder!=0}">
										value="${secondHandVO.bidVO.price+1}"
										</c:if>>
								</div>
								<input type="hidden" name="action" value="update">
								<input type="hidden" name="bid_id" value="${secondHandVO.bidVO.bid_id}">
								<input type="hidden" name="top_price" value="${secondHandVO.top_price}">
								<input type="hidden" name="bidder" value="${empVO.empId}">
								<input type="submit" class="btn btn-primary mb-2 mt-1 col"
									style="display: inline-block;" value="送出"></input>
							</form>

							${empVO.empId}

							<form class="my-1" METHOD="post"
								ACTION="<%=request.getContextPath()%>/bid/BidServlet"
								name="form1">
								<input type="hidden" name="action" value="updateWithTopPrice">
								<input type="submit" class="btn btn-primary mb-2 mt-1 col"
									style="display: inline-block;" value="我要直購"> 
								<input type="hidden" name="bid_id" value="secondHandVO.bidVO.bid_id"></input>
							</form>
							${param.second_hand_id}
							<%=request.getParameter("second_hand_id")%>
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