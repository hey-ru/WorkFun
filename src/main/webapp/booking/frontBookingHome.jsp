<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.booking.model.*"%>

<%
BookingService bookingSvc = new BookingService();
List<BookingVO> list = bookingSvc.getAll();
pageContext.setAttribute("list", list);
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
	background: transparent !important;
}

.portfolio {
	background: transparent !important;
}

.submitbtn {
	margin-top: 10px;
	background-color: transparent;
	border: 2px solid #3399ff;
	color: #3399ff;
	border-radius: 10%;
	padding: 5px 10px;
	font-weight: bold;
}

.submitbtn:hover {
	background-color: #3399ff;
	border: 2px solid #3399ff;
	color: white;
	font-weight: bold;
}
</style>

</head>
<body>

	<!-- ======= Header ======= -->

	<%@ include file="/design/frontheader.jsp"%>

	<!-- End Header -->

	<!-- content 如果頁面要可以往下滑就改一下main的height值吧 -->

	<section id="portfolio" class="portfolio">
		<div class="container" data-aos="fade-up">

			<div class="section-title">
				<h2>預約器材總攬</h2>
			</div>

			<div class="row" style="justfify-content: end;">
				<div class="col-10"
					style="height: 60px; display: inline-block; text-aligh: right;">
					<form class="my-1" METHOD="post"
						ACTTON="<%=request.getContextPath()%>/booking/booking.do"
						name="form1"></form>
					<%
					int itemsPerPage = 8;
					%>
					<%@ include file="/design/page1.file"%>


				<div class="form-group col-2" style="display: inline-block";>
				<jsp:useBean id="bookingSvc1" scope="page" class="com.booking.model.booking.do"/>
				<select class="form-control" id="exampleFormControlSelect1" style="border: gray solid 2px;" name="returnStatus">
				<option value="0"
				
				
				
				
				</select>






</body>
</html>