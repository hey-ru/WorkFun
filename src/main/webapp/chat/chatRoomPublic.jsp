<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="zh-TW">

<head>
<%@ include file="/design/frontmetacss.jsp"%>

<link href="${pageContext.request.contextPath}/chat/chatRoomPublic.css" rel="stylesheet">
</head>

<body onload="connect();" onunload="disconnect();">
	<div class="wrapper">
		<!-- ======= Header ======= -->
		<%@ include file="/design/frontheader.jsp"%>

		<!-- ======= 內容開始 ======= -->
		<div style="height: var(--header-height);"></div>


		<div class="container">

			<!--       上面標題 -->
			<h3 class=" text-center">WorkFun首家線上群聊上線啦！！！</h3>


			<div class="messaging">
				<div class="inbox_msg">

					<!--      右邊 -->
					<!--      聊天內容 -->
					<div class="mesgs">
						<div id="msg_history" class="msg_history">

<!-- 							以下範例結構 -->
							<!--      收到 -->
<!-- 							<div class="incoming_msg"> -->
<!-- 								<div class="received_msg"> -->
<!-- 									<div class="received_withd_msg"> -->
<!-- 										<p>Test which is a new approach to have all solutions</p> -->
<!-- 										<span class="received_time_date"> 11:01 AM | June 9</span> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->

<!-- 							     傳出去 -->
<!-- 							<div class="outgoing_msg"> -->
<!-- 								<div class="sent_msg"> -->
<!-- 									<p>Test which is a new approach to have all solutions</p> -->
<!-- 									<span class="sent_time_date"> 11:01 AM | June 9</span> -->
<!-- 								</div> -->
<!-- 							</div> -->

<!-- 							    收到 -->
<!-- 							<div class="incoming_msg"> -->
<!-- 								<div class="received_msg"> -->
<!-- 									<div class="received_withd_msg"> -->
<!-- 										<p>We work directly with our designers and suppliers, and -->
<!-- 											sell direct to you, which means quality, exclusive products, -->
<!-- 											at a price anyone can afford.</p> -->
<!-- 										<span class="received_time_date"> 11:01 AM | Today</span> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->


						</div>

						<!--   輸入聊天內容的地方 -->
						<div class="type_msg">
							<div class="input_msg_write">
								<input type="text" id="message" class="write_msg" placeholder="Type a message" onkeydown="if (event.keyCode == 13) sendMessage();"/>
								<button class="msg_send_btn" type="button" onclick="sendMessage();">
									<i class="bi bi-send-fill" aria-hidden="true"></i>
								</button>
							</div>
						</div>


					</div>

				</div>

			</div>
		</div>

		<!-- ======= 內容結束 ======= -->

	</div>
	<!-- ======= Footer ======= -->
	<%@ include file="/design/frontfooter.jsp"%>
	<!-- ======= js ======= -->
	<%@ include file="/design/frontjs.jsp"%>
	
	<script>
		var MyPoint = "/Public/10001/${empVO.empId}/${empVO.empName}";
		var host = window.location.host; //localhost:8081/
		var path = window.location.pathname; //WebSocketChatWeb
		var webCtx = path.substring(0, path.indexOf('/', 1));
		var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
						 //ws://localhost:8081/WebSocketChatWeb/Public/10001/${empVO.empId}/${empVO.empName}
	
		var webSocket;
	
		function connect() {
			// create a websocket
			webSocket = new WebSocket(endPointURL);
	
			webSocket.onopen = function(event) {
			};
	
			webSocket.onmessage = function(event) { //前端收到資料
				var msg_history = document.getElementById("msg_history");
				var jsonObj = JSON.parse(event.data);
				var message = jsonObj.message;
				
				var msg_history = document.getElementById("msg_history");
				var context = document.createElement("p");
				var date = document.createElement("span");
				
				if(${empVO.empId} != jsonObj.userId) {
//	 				別人傳的訊息

					context.innerHTML ="<b font-weight:900; style='font-size: 18px;''>" + jsonObj.userName + "</b> <br>" + message;

					var received_withd_msg = document.createElement("div");
					var received_msg = document.createElement("div");
					var incoming_msg = document.createElement("div");
					
					received_withd_msg.className = "received_withd_msg";
					received_msg.className = "received_msg";
					incoming_msg.className = "incoming_msg";
					
					context.append(date);
					received_withd_msg.appendChild(context);
					received_msg.appendChild(received_withd_msg);
					incoming_msg.appendChild(received_msg);
					msg_history.appendChild(incoming_msg);
					
					
				} else {
//	 				自己傳的訊息

					context.innerHTML = message;

					var sent_msg = document.createElement("div");
					var outgoing_msg = document.createElement("div");
					
					sent_msg.className = "sent_msg";
					outgoing_msg.className = "outgoing_msg";
					
					context.append(date);
					sent_msg.appendChild(context);
					outgoing_msg.appendChild(sent_msg);
					msg_history.appendChild(outgoing_msg);
				}
				
				msg_history.scrollTop = msg_history.scrollHeight;
			};
	
			webSocket.onclose = function(event) {
			};
		}

		var inputMessage = document.getElementById("message");
		inputMessage.focus();
	
		function sendMessage() { //前端推送資料

			var message = inputMessage.value.trim();
	
			if (message === "") {
// 				alert("Input a message");
				inputMessage.focus();
			} else {
				var jsonObj = {
					"chatRoomId" : "10001",
					"userId" : ${empVO.empId},
					"userName" : "${empVO.empName}",
					"message" : message
				};
				webSocket.send(JSON.stringify(jsonObj));
				inputMessage.value = "";
				inputMessage.focus();
			}
		}
	
		function disconnect() {
			webSocket.close();
		}
	
	</script>

</body>

</html>