<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.report.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	ReportVO repVO = (ReportVO) request.getAttribute("repVO");
	request.setAttribute("repVO",repVO);
	ReportVO repVO2 = (ReportVO) request.getAttribute("repVO2");
	request.setAttribute("repVO2", repVO2);
	
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
	<main style="height: 120vh;margin-top: 40px;">
		<FORM METHOD="post"
			ACTION="${pageContext.request.contextPath}/reportServlet"
			name="formUpdate" enctype="multipart/form-data">
			<div
				style="width: 900px; position: absolute; height: 630px; top: 45%; margin-top: -160px; margin-left: 14%;">
				<div class="input-group mb-3" style="margin-top: 0px;">
					<span class="input-group-text" id="basic-addon1">標題</span> <input
						type="TEXT" name="title" size="45" value="${repVO.title}" />
						<p style="color :red">${errorMsgs.title}</p>

				</div>

				<div class="input-group mb-3">
					<span class="input-group-text" id="basic-addon2">類型</span>
					 <span><c:if test="${repVO.report_type==0}">添購新品</c:if>
													<c:if test="${repVO.report_type==1}">損壞報修</c:if>
													<c:if test="${repVO.report_type==2}">軟硬體問題</c:if>
													<c:if test="${repVO.report_type==3}">其他</c:if></span>

					<span class="input-group-text" id="basic-addon2">處理狀況</span>
					<span>
					<c:if test="${repVO.status==0}">已發送</c:if>
					<c:if test="${repVO.status==1}">處理中</c:if>
					<c:if test="${repVO.status==2}">待確認</c:if>
					<c:if test="${repVO.status==3}">取消</c:if>
					<c:if test="${repVO.status==4}">已完成</c:if></span>

				</div>
				<div class="input-group mb-3">
					<span class="input-group-text" >回報建立時間</span>
					<span><fmt:formatDate value="${repVO.starttime}" pattern="yyyy-MM-dd HH:mm "/></span>
					<span class="input-group-text">回報最新編輯時間</span>
					<span><fmt:formatDate value="${repVO.updatetime}" pattern="yyyy-MM-dd HH:mm "/></span>
					<span class="input-group-text" >回報結束時間</span>
					<span><fmt:formatDate value="${repVO.endtime}" pattern="yyyy-MM-dd HH:mm "/></span>
				</div>
				<div class="input-group mb-3">
					<span class="input-group-text" id="basic-addon2">回報人</span> <span>${repVO.empVO1.empName}</span>
					<input type="hidden" name="reporter" value="${repVO.reporter}" />

					<span class="input-group-text" id="basic-addon2">處理人</span> <span>${repVO.empVO2.empName}</span>
					<input type="hidden" name="reporter" value="${repVO.handler}" />
				</div>
				<label for="basic-url" class="form-label">回報內容</label>
				<div class="input-group mb-3" style="height: 200px">
					<input type="TEXT" class="form-control" aria-label="With textarea"
						name="content" size="200" value="${repVO.content}" />
						<p style="color :red">${errorMsgs.content}</p>
				</div>

				<div class="input-group mb-3">
					<label class="input-group-text" for="inputGroupFile01">回報圖片</label>
					 <c:if test="${reportVO.report_image!=null}">
					<img src="<%=request.getContextPath()%>/util/DBGifReader?id_key=report_id&id=${repVO.report_id}&table=report&pic=report_image" style="width:100px; height:100px;">
					 </c:if> 
					<label class="input-group-text" for="inputGroupFile01">Upload</label> 
						<input type="file" value="${repVO.report_image}" name="report_image"
						accept="image/*" oninput="pic.src=window.URL.createObjectURL(this.files[0])"><img style="height:150px; width:150px" id="pic" /> 
						<input type="file" style="display: none;" name="report_image" value="update">
				</div>

				<c:forEach var="recVO" items="${repVO2.recVO}">
                            <div class="input-group">
                                <span class="input-group-text">處理訊息</span>
                                <span class="input-group-text"><fmt:formatDate value="${recVO.createtime}" pattern="yyyy-MM-dd HH:mm "/></span>
                                <textarea class="form-control" aria-label="With textarea">${recVO.comment}</textarea>
                            </div>
                            </c:forEach>
			</div>
			<input type="hidden" name="action" value="update"> 
			<input type="hidden" name="report_id" value="${repVO.report_id}">
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