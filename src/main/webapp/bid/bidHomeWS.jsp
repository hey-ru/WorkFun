<%@page import="com.bid.model.BidVO"%>
<%@page import="java.io.PrintWriter"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.secondHand.model.*"%>

<%
// Integer second_hand_id = Integer.valueOf(request.getParameter("second_hand_id"));
Integer second_hand_id = 1001;

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

html {
    overflow: -moz-hidden-unscrollable;
    height: 100%;
}

body::-webkit-scrollbar {
    display: none;
}

body {
    -ms-overflow-style: none;
    height: 100%;
	width: calc(100vw + 18px);
	overflow: auto;
}
</style>

</head>

<body onload="connect();" onunload="disconnect();" style="height: auto">

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

<!-- 							<div> -->
<!-- 								<img -->
<%-- 								src="<%=request.getContextPath()%>/util/DBGifReader?pic=img1&table=second_hand&id_key=second_hand_id&id=${secondHandVO.second_hand_id}" --%>
<!-- 								alt="Profile" style="max-width:100%; max-height:100%" id="showPic"> -->
<!-- 							</div> -->
							
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
										<div class="col-lg-9 col-md-8" id="bidder">
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
										<div class="col-lg-9 col-md-8" id="currentPrice" onchange="changePrice(this.id)">${secondHandVO.bidVO.bidder==0?secondHandVO.bottom_price:secondHandVO.bidVO.price}</div>
									</div>

								</div>

							</div>
							<!-- End Bordered Tabs -->

							<c:if test="${secondHandVO.bidVO.bidder == empVO.empId}">
								<br>您已經是最高出價者<br>
							</c:if>
							<c:if test="${secondHandVO.bidVO.bidder != empVO.empId}">
								<div class="form-group col-3" style="display: inline-block">
									<div class="form-group col-3" style="display: inline-block">
										<input type="number" class="form-control"
 											id="inputPrice" placeholder="輸入競標金額"
											style="border: gray solid 2px;  width:150px;" name="price" onkeydown="if (event.keyCode == 13) sendPrice();"
 											<c:if test="${secondHandVO.bidVO.bidder==0}">
 												oninput="if(value >= ${secondHandVO.top_price}){value = ${secondHandVO.top_price}};
 												if(value <= Number(document.getElementById('currentPrice').innerHTML)){value = Number(document.getElementById('currentPrice').innerHTML)}"
 												
 												value="${secondHandVO.bottom_price}"
 											</c:if>
 											<c:if test="${secondHandVO.bidVO.bidder!=0}">
	 											oninput="if(value >= ${secondHandVO.top_price}){value = ${secondHandVO.top_price}};
	 											if(value <= Number(document.getElementById('currentPrice').innerHTML)){value = Number(document.getElementById('currentPrice').innerHTML)+1}"
	 											
	 											value="${secondHandVO.bidVO.price+1}"
 											</c:if>
										>
									</div>
									<input type="submit" class="btn btn-primary mb-2 mt-1 col" id="sendPrice" style="display: inline-block;" value="送出" onclick="sendPrice();"></input>
								</div>
							</c:if>
							
<%-- 							${empVO.empId} --%>

							<form class="my-1" METHOD="post" ACTION="<%=request.getContextPath()%>/bid/BidServlet"
								name="form1">
								<input type="hidden" name="bid_id" value="${secondHandVO.bidVO.bid_id}">
								<input type="hidden" name="top_price" value="${secondHandVO.top_price}">
								<input type="hidden" name="bidder" value="${empVO.empId}">
								<input type="hidden" name="second_hand_id" value="${secondHandVO.second_hand_id}">
								<input type="submit" class="btn btn-primary mb-2 mt-1 col" id="buyItem" style="display: inline-block;" value="我要直購">
								<input type="hidden" name="action" value="updateWithTopPrice">
							</form>
<%-- 							${param.second_hand_id} --%>
<%-- 							<%=request.getParameter("second_hand_id")%> --%>
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

	function changePrice() {
		alert("有東西嗎");
	}
	
	var MyPoint = "/BidWS/${empVO.empId}/${empVO.empName}/${secondHandVO.bidVO.bid_id}";
	var host = window.location.host; //localhost:8081/
	var path = window.location.pathname; //WebSocketChatWeb
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
						//ws://localhost:8081/CGA101G3/BidWS/${empVO.empId}/${empVO.empName}/${secondHandVO.bidVO.bid_id}

	/* var statusOutput = document.getElementById("statusOutput"); */
	var webSocket;

	function connect() {
		// create a websocket
		webSocket = new WebSocket(endPointURL);

		webSocket.onopen = function(event) {
// 			updateStatus("WebSocket Connected");
			document.getElementById('sendPrice').disabled = false;
			document.getElementById('buyItem').disabled = false;
// 			document.getElementById('connect').disabled = true;
// 			document.getElementById('disconnect').disabled = false;
		};

		webSocket.onmessage = function(event) { //前端收到資料
// 			var messagesArea = document.getElementById("messagesArea");
// 			var jsonObj = JSON.parse(event.data);
// 			var message = jsonObj.userName + ": " + jsonObj.message + "\r\n";
// 			messagesArea.value = messagesArea.value + message;
// 			messagesArea.scrollTop = messagesArea.scrollHeight;
			
			var currentPrice = document.getElementById("currentPrice");
			var jsonObj = JSON.parse(event.data);
			currentPrice.innerHTML = jsonObj.price;
			inputPrice.value = (Number(jsonObj.price) + 1).toString();
			bidder.innerHTML = jsonObj.userName;
			
			
		};

		webSocket.onclose = function(event) {
// 			updateStatus("WebSocket Disconnected");
		};
	}

	var inputPrice = document.getElementById("inputPrice");
	inputPrice.focus();
	
	function sendPrice() { //前端推送資料
		var price = inputPrice.value.trim();
		if (price === "") {
			alert("請輸入價格");
			inputPrice.focus();
			return;
		} else {
			var jsonObj = {
				"userId" : ${empVO.empId},
				"userName" : "${empVO.empName}",
				"bidId" : ${secondHandVO.bidVO.bid_id},
				"price" : price
			};
			webSocket.send(JSON.stringify(jsonObj));
// 			inputPrice.value = document.getElementById("currentPrice").value.trim() + 1;
			inputPrice.value = (Number(inputPrice.value)+1).toString();
			inputPrice.focus();
		}
	}

	function disconnect() {
		webSocket.close();
// 		document.getElementById('sendMessage').disabled = true;
// 		document.getElementById('connect').disabled = false;
// 		document.getElementById('disconnect').disabled = true;
		document.getElementById('sendPrice').disabled = true;
		document.getElementById('buyItem').disabled = true;
	}
	</script>
</body>

</html>