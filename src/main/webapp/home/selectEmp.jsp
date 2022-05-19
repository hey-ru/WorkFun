<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>
<jsp:useBean id="listEmps_ByCompositeQuery" scope="request"
	type="java.util.List<EmpVO>" />
<%
	
	 List<EmpVO> list=(List<EmpVO>)request.getAttribute("list");
	%>

<html>
<head>
<%@ include file="/design/frontmetacss.jsp"%>

</head>

<body>
	<div class="wrapper">

		<%@ include file="/design/frontheader.jsp"%>

		<!-- ====================== 內容開始 ====================== -->
		<main id="main" class="main">
			<div class="card shadow mb-4">
				<!-- ============== Card Header ============== -->
				<div class="card-header py-3" style="background-color: #b0c4de">
					<div class="row">
						<div class="col-11" style="height: 20px; display: inline-block;">
							<h5>
								<strong>查詢結果</strong>
							</h5>
						</div>
						<div class="col-1" style="height: 20px; display: inline-block;">
							<a href="<%=request.getContextPath()%>/home/home.jsp"><strong>回首頁</strong></a>
						</div>
					</div>
				</div>

				<!-- ============== Card Body ============== -->
				<div class="card-body">
					<div class="table-responsive">
						<div id="dataTable_wrapper"
							class="dataTables_wrapper dt-bootstrap4">

							<div class="row">
								<div class="col-sm-6">
									<table class="table table-bordered dataTable" id="dataTable"
										width="100%" cellspacing="0" role="grid"
										aria-describedby="dataTable_info" style="width: 100%">

										<!-- ============== 表頭(自行增減修改) ============== -->
										<thead>
											<tr role=" row">
												<th class="sorting sorting_asc" tabindex="0"
													aria-controls="dataTable" rowspan="1" colspan="1"
													aria-sort="ascending"
													aria-label="Name: activate to sort column descending"
													style="width: 30px;">姓名</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Position: activate to sort column ascending"
													style="width: 100px;">分機</th>
											<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Position: activate to sort column ascending"
													style="width: 100px;">部門</th>
											</tr>
										</thead>



										<!-- ============== 表格內容(自行增減修改) ============== -->
								<%@ include file="/back/page1_ByCompositeQuery.file"%>
						<c:forEach var="empVO" items="${listEmps_ByCompositeQuery}"
							begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
							<tr>
								<td align="center"  style="width:90px;">${empVO.empName}</td>
			<td style="width:60px;">${empVO.depVO.depName}</td>

			<td style="width:90px;">${empVO.hiredate}</td>
				<td style="width:90px;">${empVO.resigndate}</td>
			<td>${empVO.phone}</td>
			<td>${empVO.extension}</td> 
							<td style="width:150px; "><img 
												src="
									<%=request.getContextPath()%>/util/DBGifReader?pic=emp_profile&table=emp&id_key=emp_id&id=${empVO.empId}
									"
												class="img-fluid"
											></td>
								<td>${empVO.mail}</td>
									<td>${empVO.birthday}</td>
									<c:choose>
									<c:when test="${empVO.empStatus==2}">
										<td>離職</td>
										</c:when>
										<c:otherwise>
											<td>在職</td>
										</c:otherwise>
										
										
										
		</c:choose>


								<td>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/empServlet"
										style="margin-bottom: 0px;">
										<input type="submit" value="修改" class="btn btn-outline-dark"> <input type="hidden"
											name="empId" value="${empVO.empId}"> <input
											type="hidden" name="requestURL"
											value="<%=request.getServletPath()%>">
										<!--送出本網頁的路徑給Controller-->
										<input type="hidden" name="whichPage" value="<%=whichPage%>">
										<!--送出當前是第幾頁給Controller-->
										<input type="hidden" name="action" value="getOne_For_Update">
									</FORM>
								</td>

							</tr>
						</c:forEach>
					</table>
					<%@ include file="/back/page2_ByCompositeQuery.file"%>
									</table>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</main>
		<!-- ======= 內容結束 ======= -->

	</div>
	<!-- ======= Footer ======= -->
	<%@ include file="/design/frontfooter.jsp"%>
	<!-- ======= js ======= -->
	<%@ include file="/design/frontjs.jsp"%>
</body>

</html>