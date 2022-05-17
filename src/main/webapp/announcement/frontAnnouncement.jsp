<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="com.announcement.model.*"%>
<%@ page import="com.announcement_mapping.model.*"%>
<%@page import="java.util.List"%>
<%
Integer announcement_id = Integer.valueOf(request.getParameter("announcement_id"));
Announcement_mappingService annmapSvc = new Announcement_mappingService();

List<Integer> list = annmapSvc.findByPrimaryKey(announcement_id);
pageContext.setAttribute("list", list);
Integer oldquantity = list.size();
request.setAttribute("oldquantity", oldquantity);
%>
<!DOCTYPE html>
<html lang="zh-TW">

<head>
<%@ include file="/design/frontmetacss.jsp"%>
<style>
#mainleft {
	margin-top: 200px;
	/* 	margin-right: auto; */
	/* 	margin-left: 300px; */
	background-color: #DDCFC2;
	padding: 30px;
	border-radius: 10px;
}

#card-title {
	font-family: fantasy;
}

/* 購物車 start */
table {
	background-color: #fff;
	width: 100%;
	border-radius: 5px;
}

thead tr {
	background-color: #BFC2CB;
}

thead td {
	padding: 7px;
}

tbody td {
	padding: 7px;
}

.selectAll {
	padding: 10px;
	color: white;
	background-color: #2A4C65;
	text-decoration: none;
	border-radius: 4px;
}
</style>
</head>

<body style="background-color: #D6DCDB;">
	<div class="wrapper">
		<!-- ======= Header ======= -->
		<%@ include file="/design/frontheader.jsp"%>

		<!-- ======= 內容開始 ======= -->
		<div id="mainleft" class="col-6"
			style="height: max-content; font-weight: bold; font-family: Andale Mono, monospace; margin-left: 480px;">
			<h1 class="card-title"
				style="text-align: center; background-color: #DBD2C9;">
				<strong>${param.announcement_title}</strong>
			</h1>
			<h4>‍發布者: ${param.announcer_name}</h4>
			<jsp:useBean id="now" class="java.util.Date" />
			<h4>
				內容:<a style="word-break: break-all">${param.announcement_content}</a>
				<br>
			</h4>

			<!-- Slides with fade transition -->
			<div id="carouselExampleFade" class="carousel slide carousel-fade"
				data-bs-ride="carousel">

				<div class="carousel-inner">



					<c:forEach var="announcement_mapping" items="${list}">
						<c:choose>
							<c:when test="${list.get(0)==announcement_mapping}">
								<div class="carousel-item active">
									<img style="width: 200px; height: 200px"
										src="
									<%=request.getContextPath()%>/servlet/com.announcement.controller.DBGifReader?announcementImg_id=${announcement_mapping}&announcement_id=${param.announcement_id}
									"
										class="img-fluid">
								</div>




							</c:when>
							<c:otherwise>
								<div class="carousel-item ">
									<img style="width: 200px; height: 200px"
										src="
									<%=request.getContextPath()%>/servlet/com.announcement.controller.DBGifReader?announcementImg_id=${announcement_mapping}&announcement_id=${param.announcement_id}
									"
										class="img-fluid">
								</div>



							</c:otherwise>



						</c:choose>

					</c:forEach>




				</div>

				<button class="carousel-control-prev" type="button"
					data-bs-target="#carouselExampleFade" data-bs-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Previous</span>
				</button>
				<button class="carousel-control-next" type="button"
					data-bs-target="#carouselExampleFade" data-bs-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Next</span>
				</button>
			</div>
		</div>



		<!-- ======= 內容結束 ======= -->

	</div>
	<!-- ======= Footer ======= -->
	<%@ include file="/design/frontfooter.jsp"%>
	<!-- ======= js ======= -->
	<%@ include file="/design/frontjs.jsp"%>

</body>

</html>