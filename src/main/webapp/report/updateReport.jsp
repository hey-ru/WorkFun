<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.report.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	ReportVO reportVO = (ReportVO) request.getAttribute("reportVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport" />

<title>:: WorkFun_updateReport ::</title>
<%@ include file="/design/frontmetacss.jsp"%>
</head>

<body>
	<!-- ======= Header ======= -->
	<%@ include file="/design/frontheader.jsp"%>
	<!-- End Header -->

	<!-- content 如果頁面要可以往下滑就改一下main的height值吧 -->
	<main style="height: 120vh; border: 3px red solid; margin-top: 40px;">
		<FORM METHOD="post"
			ACTION="${pageContext.request.contextPath}/reportServlet"
			name="formUpdate" enctype="multipart/form-data">
			<div
				style="border: 3px blue solid; width: 900px; position: absolute; height: 630px; top: 45%; margin-top: -160px; margin-left: 14%;">
				<div class="input-group mb-3" style="margin-top: 0px;">
					<span class="input-group-text" id="basic-addon1">標題</span> <input
						type="TEXT" name="title" size="45" value="${param.title}" />

				</div>

				<div class="input-group mb-3">
					<span class="input-group-text" id="basic-addon2">類型</span> <input
						type="TEXT" name="report_type" size="45" value="${param.report_type}" />

					<span class="input-group-text" id="basic-addon2">處理狀況</span>
					<span>
					<c:if test="${param.status==0}">已發送</c:if>
					<c:if test="${param.status==1}">處理中</c:if>
					<c:if test="${param.status==2}">待確認</c:if>
					<c:if test="${param.status==3}">取消</c:if>
					<c:if test="${param.status==4}">已完成</c:if></span>

				</div>
				<div class="input-group mb-3">
					<span class="input-group-text" >回報建立時間</span>
					<span>${param.starttime}</span>
					<span class="input-group-text">回報最新編輯時間</span>
					<span>${param.updatetime}</span>
					<span class="input-group-text" >回報結束時間</span>
					<span>${param.endtime}</span>
				</div>
				<div class="input-group mb-3">
					<span class="input-group-text" id="basic-addon2">回報人</span> <input name="reporter" value="${param.reporter}" />

					<span class="input-group-text" id="basic-addon2">處理人</span> <input name="handler" value="${param.handler}" />

				</div>
				<label for="basic-url" class="form-label">回報內容</label>
				<div class="input-group mb-3" style="height: 200px">
					<input type="TEXT" class="form-control" aria-label="With textarea"
						name="content" size="200" value="${param.content}" />
				</div>

				<div class="input-group mb-3">
					<label class="input-group-text" for="inputGroupFile01">回報圖片</label>
					<img src="<%=request.getContextPath()%>/util/DBGifReader?id_key=report_id&id=${param.report_id}&table=report&pic=report_image" style="width:100px; height:100px;"> 
					<label class="input-group-text" for="inputGroupFile01">Upload</label> 
						<input type="file" value="${param.report_image}" name="report_image"
						accept="image/*" oninput="pic.src=window.URL.createObjectURL(this.files[0])"><img style="height:150px; width:150px" id="pic" /> 
						<input type="file" style="display: none;" name="report_image" value="update">
				</div>

				<div class="input-group">
					<span class="input-group-text">處理訊息</span>
					<textarea class="form-control" aria-label="With textarea">Test</textarea>
				</div>
			</div>
			<input type="hidden" name="action" value="update"> 
			<input type="hidden" name="report_id" value="${param.report_id}">
			<button type="submit" value="送出修改" style="margin-top: 600px;">送出修改</button>
		</FORM>
	</main>
	<!-- ======= Footer ======= -->
		<%@ include file="/design/frontfooter.jsp"%>
	<!-- End  Footer -->

	<!-- Vendor JS Files -->
	<%@ include file="/design/frontjs.jsp"%>
	<!-- Template Main JS File -->
	<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</body>

</html>