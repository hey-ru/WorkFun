<%@page import="com.emp.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.groupbuylist.model.*"%>
<%@ page import="com.groupbuy.model.*"%>

<%
//只能查詢個人參團紀錄
EmpVO empVO = (EmpVO) session.getAttribute("empVO");
GroupBuyListService gblistSvc = new GroupBuyListService();
List<GroupBuyListVO> list = gblistSvc.getMyGB(empVO.getEmpId());

HttpSession session1 = pageContext.getSession();
session1.setAttribute("mygblist", list);

int itemsPerPage = 6;
%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/design/frontmetacss.jsp"%>

<style>
.table-responsive {
	overflow-x: hidden;
}
</style>


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
					<div class="col-12">
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
									<th scope="col">各團狀態</th>
									<th scope="col"></th>
									<th scope="col"></th>
									<th scope="col"></th>
								</tr>
							</thead>

							<%@ include file="/design/page1.file"%>

							<!-- ========================= 表格內容 ========================= -->
							<tbody>
								<c:forEach var="mygb" items="${mygblist}" begin="<%=pageIndex%>"
									end="<%=pageIndex+rowsPerPage-1%>">
									 <c:if test="${mygb.total > 0}"> 
									<tr>
										<td>${mygb.gb_id}</td>
										<td>${mygb.groupBuyVO.shop_name}</td>
										<td>${mygb.total}</td>
										<td>${mygb.is_pay eq 0? "未付款":"已付款"}</td>
										<td>${mygb.is_pickup eq 0? "未取貨":"已取貨"}</td>
										<td><fmt:formatDate value="${mygb.groupBuyVO.start_time}"
												pattern="yyyy-MM-dd HH:mm" /></td>
										<td><fmt:formatDate value="${mygb.groupBuyVO.end_time}"
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
													value="訂單明細">
											</FORM>
										</td>
										<td>
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/groupbuylist/selectmygblistservlet"
												style="margin-bottom: 0px;">
												<input type="hidden" name="gb_id" value="${mygb.gb_id}">
												<input type="hidden" name="buyer" value="${empVO.empId}">
												<input type="hidden" name="action" value="updateMyGb">
												<input type="submit" class="btn btn-success btn-sm"
													value="編輯"
													${mygb.groupBuyVO.gb_status eq 0 ? '' : 'hidden="hidden"'} />
											</FORM>
										</td>
										<td>
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/groupbuylist/selectmygblistservlet"
												onSubmit="javascript:return window.confirm('確定不參加嗎？')">
												<input type="hidden" name="gb_id" value="${mygb.gb_id}">
												<input type="hidden" name="buyer" value="${empVO.empId}">
												<input type="hidden" name="action" value="deleteMyGb">

												<!-- 判斷截止時間是否小於現在時間,若是hidden button -->
												<!-- 																<input type="submit" class="btn btn-secondary btn-sm" value="退出揪團" -->
												<%-- 																${mygb.groupBuyVO.end_time lt now ? 'disabled="disabled"' : ''}/> --%>
												<%-- 																	<jsp:useBean id="now" class="java.util.Date" /> --%>
												<%-- 																	<c:out value="${mygb.groupBuyVO.end_time lt now}"/>  --%>
												<!-- 判斷截止時間是否為揪團中-->
												<input type="submit" class="btn btn-secondary btn-sm"
													value="退團"
													${mygb.groupBuyVO.gb_status eq 0 ? '' : 'hidden="hidden"'} />
											</FORM>
										</td>
									</tr>
									<!-- 揪團截止不能取消及編輯 -->
								</c:if>	
								</c:forEach>
							</tbody>
						</table>
					</div>

					<%@ include file="/design/page2.file"%>
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