<%@page import="com.emp.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.groupbuylist.model.*"%>
<%@ page import="com.groupbuy.model.*"%>

<jsp:useBean id="listByCompositeQuery" scope="request" type="java.util.List<GroupBuyListVO>" />

<%
String yourServlet = "/groupbuylist/selectmygblistservlet";
int itemsPerPage = 6;

//只能查詢個人參團紀錄
EmpVO empVO = (EmpVO) session.getAttribute("empVO");

//查詢出參團人所有參團
GroupBuyListService gblistSvc = new GroupBuyListService();
List<GroupBuyListVO> list = gblistSvc.getMyGB(empVO.getEmpId());

// HttpSession session1 = pageContext.getSession();
// session1.setAttribute("mygblist", list);

%>

<!DOCTYPE html>
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
								<strong>查詢參團</strong>
							</h5>
						</div>
						<div class="col-1" style="height: 20px; display: inline-block;">
							<a href="<%=request.getContextPath()%>/groupbuy/gbHome.jsp"><strong>回揪團主頁</strong></a>
						</div>
					</div>
				</div>

				<!-- ============== Card Body ============== -->
				<div class="card-body">
						<div class="row">
							<div class="col-11 row" style="height: 60px; display: inline-block;">
							<div class = col-8>
							<ul class="nav nav-tabs">
								  <li class="nav-item">
								    <a class="nav-link" 
								    href="<%=request.getContextPath()%>/groupbuylist/buyer_selectGB.jsp">所有參團</a>
								  </li>
<!-- 								  <li class="nav-item"> -->
<!-- 								    <a class="nav-link" -->
<%-- 								     href="<%=request.getContextPath()%>/groupbuylist/selectmygblistservlet?action=listByCompositeQueryBack&buyer=${empVO.empId}&g.gb_status=0">揪團中</a> --%>
<!-- 								  </li> -->
								  <li class="nav-item">
								    <a class="nav-link" 
								    href="<%=request.getContextPath()%>/groupbuylist/selectmygblistservlet?action=listByCompositeQueryBack&buyer=${empVO.empId}&g.gb_status=0">歷史訂單</a>
								  </li>
								</ul>
								</div>
								</div>
<!-- 							<div class="col-11 row" style="height: 60px; display: inline-block;"> -->
							
<%-- 								<form class="my-1 col-6" METHOD="post" ACTION="<%=request.getContextPath()%>/groupbuy/GroupBuyServlet" name="formsearch"> --%>
<!-- 									<div class="form-groupf" style="display: inline-block"> -->
<!-- 										<input type="text" class="form-control" -->
<!-- 											id="gb_id" placeholder="輸入揪團編號" -->
<!-- 											style="border: gray solid 2px;" name="gb_id"> -->
<!-- 									</div> -->
<!-- 									<input type="hidden" name="action" value="listByCompositeQueryBack"> -->
<!-- 									<button type="submit" class="btn btn-dark mb-2 mt-1 col-2" -->
<!-- 										style="display: inline-block;">搜尋</button> -->
<!-- 								</form> -->
<!-- 							</div> -->
<!-- 						</div> -->

					
						<table class="table table-striped">
							<!-- ========================= 表頭 ========================= -->
							<thead>
								<tr>
									<th scope="col">我的參團</th>
									<th scope="col">店家</th>
									<th scope="col">總額</th>
									<th scope="col">付款狀態</th>
									<th scope="col">取貨狀態</th>
									<th scope="col">開始時間</th>
									<th scope="col">結束時間</th>
									<th scope="col">取貨時間</th>
									<th scope="col">各團狀態</th>
									<th scope="col"></th>
									<th scope="col"></th>
									<th scope="col"></th>
								</tr>
							</thead>

							<%@ include file="/design/page1_ByCompositeQuery.file"%>

							<!-- ========================= 表格內容 ========================= -->
							<tbody>
								<c:forEach var="mygb" items="${listByCompositeQuery}" begin="<%=pageIndex%>"
									end="<%=pageIndex+rowsPerPage-1%>">
									<c:if test="${(mygb.total) > 0}">
										<tr>
											<td>${mygb.gb_id}</td>
											<td>${mygb.groupBuyVO.shop_name}</td>
											<td>${mygb.total}</td>
											<td>${mygb.is_pay eq 0? "未付款":"已付款"}</td>
											<td>${mygb.is_pickup eq 0? "未取貨":"已取貨"}</td>
											<td><fmt:formatDate
													value="${mygb.groupBuyVO.start_time}"
													pattern="yyyy-MM-dd HH:mm" /></td>
											<td><fmt:formatDate value="${mygb.groupBuyVO.end_time}"
													pattern="yyyy-MM-dd HH:mm" /></td>
											<td><fmt:formatDate value="${mygb.groupBuyVO.arr_time}"
													pattern="yyyy-MM-dd HH:mm" /></td>		
											<td><c:choose>
													<c:when test="${mygb.groupBuyVO.gb_status == 0}">
														       	揪團中
														    </c:when>
													<c:when test="${mygb.groupBuyVO.gb_status == 1}">
														        取消
														    </c:when>
													<c:when test="${mygb.groupBuyVO.gb_status == 2}">
														        揪團結束
														    </c:when>
													<c:when test="${mygb.groupBuyVO.gb_status == 3}">
														        揪團關閉
														    </c:when>
												</c:choose></td>
											<td>
												<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/groupbuylist/selectmygblistservlet"
													style="margin-bottom: 0px;">
													<input type="hidden" name="gb_id" value="${mygb.gb_id}">
													<input type="hidden" name="buyer" value="${empVO.empId}">
													<input type="hidden" name="action" value="get_buyerlist">
													<input type="submit" class="btn btn-info btn-sm"
														value="明細"
														${mygb.groupBuyVO.gb_status gt 0 ? '' : 'hidden="hidden"'}>
												</FORM>
											</td>
<!-- 											<td> -->
<!-- 												<FORM METHOD="post" -->
<%-- 													ACTION="<%=request.getContextPath()%>/groupbuylist/selectmygblistservlet" --%>
<!-- 													style="margin-bottom: 0px;"> -->
<!-- 													<input type="hidden" name="shop_id" -->
<%-- 														value="${mygb.groupBuyVO.shop_id}"> <input --%>
<%-- 														type="hidden" name="gb_id" value="${mygb.gb_id}"> --%>
<%-- 													<input type="hidden" name="buyer" value="${empVO.empId}"> --%>
<!-- 													<input type="hidden" name="action" value="updateMyGb"> -->
<!-- 													<input type="submit" class="btn btn-success btn-sm" -->
<!-- 														value="編輯" -->
<%-- 														${mygb.groupBuyVO.gb_status eq 0 ? '' : 'hidden="hidden"'} /> --%>
<!-- 												</FORM> -->
<!-- 											</td> -->
<!-- 											<td> -->
<!-- 												<FORM METHOD="post" id="delete-confirm" -->
<!-- 													onclick="myAlertFunction(event)" -->
<%-- 													ACTION="<%=request.getContextPath()%>/groupbuylist/selectmygblistservlet" --%>
<!-- 													onSubmit="javascript:return window.sweetAlert('確定不參加嗎？')"> -->
<%-- 													<input type="hidden" name="gb_id" value="${mygb.gb_id}"> --%>
<%-- 													<input type="hidden" name="buyer" value="${empVO.empId}"> --%>
<!-- 													<input type="hidden" name="action" value="deleteMyGb"> -->

<!-- 												</FORM> -->
<!-- 											</td> -->
										</tr>
										<!-- 揪團截止不能取消及編輯 -->
									</c:if>
								</c:forEach>
							</tbody>
						</table>
					</div>
						<%@ include file="/design/page2_ByCompositeQuery.file"%>

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