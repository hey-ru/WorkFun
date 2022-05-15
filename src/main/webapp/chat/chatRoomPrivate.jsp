<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="zh-TW">

<head>
<%@ include file="/design/frontmetacss.jsp"%>

<link href="${pageContext.request.contextPath}/chat/chat.css" rel="stylesheet">
</head>

<body>
	<div class="wrapper">
		<!-- ======= Header ======= -->
		<%@ include file="/design/frontheader.jsp"%>

		<!-- ======= 內容開始 ======= -->
		<div style="height: var(--header-height);"></div>


		<div class="container">

			<!--       上面標題 -->
			<h3 class=" text-center">WorkFun首家線上私聊上線啦！！！</h3>


			<div class="messaging">
				<div class="inbox_msg">

					<!--      左邊 -->

					<!--       	搜尋 -->
					<div class="inbox_people">
						<div class="headind_srch">
							<div class="recent_heading">
								<h4>Recent</h4>
							</div>
							<div class="srch_bar">
								<div class="stylish-input-group">
									<input type="text" class="search-bar" placeholder="Search">
									<span class="input-group-addon">
										<button type="button">
											<i class="bi bi-search" aria-hidden="true"></i>
										</button>
									</span>
								</div>
							</div>
						</div>

						<!--   人或聊天室...   -->

						<div class="inbox_chat">
							<div class="chat_list active_chat">
								<div class="chat_people">

									<div class="chat_ib">
										<h5>
											Sunil Rajput <span class="chat_date">Dec 25</span>
										</h5>
										<p>Test, which is a new approach to have all solutions
											astrology under one roof.</p>
									</div>
								</div>
							</div>
							<div class="chat_list">
								<div class="chat_people">

									<div class="chat_ib">
										<h5>
											Sunil Rajput <span class="chat_date">Dec 25</span>
										</h5>
										<p>Test, which is a new approach to have all solutions
											astrology under one roof.</p>
									</div>
								</div>
							</div>
						</div>
					</div>


					<!--      右邊 -->
					<!--      聊天內容 -->
					<div class="mesgs">
						<div class="msg_history">


							<!--      收到 -->
							<div class="incoming_msg">
								<div class="received_msg">
									<div class="received_withd_msg">
										<p>Test which is a new approach to have all solutions</p>
										<span class="time_date"> 11:01 AM | June 9</span>
									</div>
								</div>
							</div>

							<!--      傳出去 -->
							<div class="outgoing_msg">
								<div class="sent_msg">
									<p>Test which is a new approach to have all solutions</p>
									<span class="time_date"> 11:01 AM | June 9</span>
								</div>
							</div>

							<!--     收到 -->
							<div class="incoming_msg">
								<div class="received_msg">
									<div class="received_withd_msg">
										<p>We work directly with our designers and suppliers, and
											sell direct to you, which means quality, exclusive products,
											at a price anyone can afford.</p>
										<span class="time_date"> 11:01 AM | Today</span>
									</div>
								</div>
							</div>


						</div>

						<!--   輸入聊天內容的地方 -->
						<div class="type_msg">
							<div class="input_msg_write">
								<input type="text" class="write_msg"
									placeholder="Type a message" />
								<button class="msg_send_btn" type="button">
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
		var MyPoint = "/FriendWS/${userName}";
		var host = window.location.host;
		var path = window.location.pathname;
		var webCtx = path.substring(0, path.indexOf('/', 1));
		var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
	
		var statusOutput = document.getElementById("statusOutput");
		var messagesArea = document.getElementById("messagesArea");
		var self = '${userName}';
		var webSocket;
	
		function connect() {
			// create a websocket
			webSocket = new WebSocket(endPointURL);
	
			webSocket.onopen = function(event) {
				console.log("Connect Success!");
				document.getElementById('sendMessage').disabled = false;
				document.getElementById('connect').disabled = true;
				document.getElementById('disconnect').disabled = false;
			};
	
			webSocket.onmessage = function(event) {
				var jsonObj = JSON.parse(event.data);
				if ("open" === jsonObj.type) {
					refreshFriendList(jsonObj);
				} else if ("history" === jsonObj.type) {
					messagesArea.innerHTML = '';
					var ul = document.createElement('ul');
					ul.id = "area";
					messagesArea.appendChild(ul);
					// 這行的jsonObj.message是從redis撈出跟好友的歷史訊息，再parse成JSON格式處理
					var messages = JSON.parse(jsonObj.message);
					for (var i = 0; i < messages.length; i++) {
						var historyData = JSON.parse(messages[i]);
						var showMsg = historyData.message;
						var li = document.createElement('li');
						// 根據發送者是自己還是對方來給予不同的class名, 以達到訊息左右區分
						historyData.sender === self ? li.className += 'me' : li.className += 'friend';
						li.innerHTML = showMsg;
						ul.appendChild(li);
					}
					messagesArea.scrollTop = messagesArea.scrollHeight;
				} else if ("chat" === jsonObj.type) {
					var li = document.createElement('li');
					jsonObj.sender === self ? li.className += 'me' : li.className += 'friend';
					li.innerHTML = jsonObj.message;
					console.log(li);
					document.getElementById("area").appendChild(li);
					messagesArea.scrollTop = messagesArea.scrollHeight;
				} else if ("close" === jsonObj.type) {
					refreshFriendList(jsonObj);
				}
				
			};
	
			webSocket.onclose = function(event) {
				console.log("Disconnected!");
			};
		}
		
		function sendMessage() {
			var inputMessage = document.getElementById("message");
			var friend = statusOutput.textContent;
			var message = inputMessage.value.trim();
	
			if (message === "") {
				alert("Input a message");
				inputMessage.focus();
			} else if (friend === "") {
				alert("Choose a friend");
			} else {
				var jsonObj = {
					"type" : "chat",
					"sender" : self,
					"receiver" : friend,
					"message" : message
				};
				webSocket.send(JSON.stringify(jsonObj));
				inputMessage.value = "";
				inputMessage.focus();
			}
		}
		
		// 有好友上線或離線就更新列表
		function refreshFriendList(jsonObj) {
			var friends = jsonObj.users;
			var row = document.getElementById("row");
			row.innerHTML = '';
			for (var i = 0; i < friends.length; i++) {
				if (friends[i] === self) { continue; }
				row.innerHTML +='<div id=' + i + ' class="column" name="friendName" value=' + friends[i] + ' ><h2>' + friends[i] + '</h2></div>';
			}
			addListener();
		}
		// 註冊列表點擊事件並抓取好友名字以取得歷史訊息
		function addListener() {
			var container = document.getElementById("row");
			container.addEventListener("click", function(e) {
				var friend = e.srcElement.textContent;
				updateFriendName(friend);
				var jsonObj = {
						"type" : "history",
						"sender" : self,
						"receiver" : friend,
						"message" : ""
					};
				webSocket.send(JSON.stringify(jsonObj));
			});
		}
		
		function disconnect() {
			webSocket.close();
			document.getElementById('sendMessage').disabled = true;
			document.getElementById('connect').disabled = false;
			document.getElementById('disconnect').disabled = true;
		}
		
		function updateFriendName(name) {
			statusOutput.innerHTML = name;
		}
	</script>

</body>

</html>