<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="com.groupbuylist.model.*"%>
<%@ page import="com.groupbuy.model.*"%>

<%
//查詢個人單筆明細
List<GroupBuyListVO> list = (List<GroupBuyListVO>) session.getAttribute("buyerlist");

int itemsPerPage = 10;

int orderNumber = 1;
%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/design/frontmetacss.jsp"%>

<style>


th {
	vertical-align: middle;
	text-align: center;
}

td {
	vertical-align: middle;
	text-align: center;
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
								<strong>參團購買明細</strong>
							</h5>
						</div>
						<div class="col-1" style="height: 20px; display: inline-block;">
							<a
								href="<%=request.getContextPath()%>/groupbuylist/buyer_selectGB.jsp?buyer=${empVO.empId}"><strong>回查詢參團</strong></a>
						</div>
					</div>
				</div>

				<!-- ============== Card Body ============== -->
				<div class="row">
				<div class="card-header py-3">
					<h5 class="card-title">
						<strong>${groupBuyVO.shop_name}</strong>
					</h5>
						<h6>‍團主: ${groupBuyVO.empVO.empName} &nbsp 分機:
						${groupBuyVO.empVO.extension}</h6>
				</div>
				<div class="card-body">
					<div class="col-6">
						<table class="table">

							<!-- ========================= 表頭 ========================= -->
							<thead>
								<tr>
								<th scope="col">編號</th>
								<th scope="col">品項</th>
								<th scope="col">單價</th>
								<th scope="col">數量</th>
								<th scope="col">小計</th>
								<th scope="col">備註</th>
								<th scope="col"></th>
								</tr>
							</thead>

							<%-- 										<%@ include file="/design/page1.file"%> --%>

							<!-- ========================= 表格內容 ========================= -->
							<tbody>

								<c:forEach var="blist" items="${buyerlist}">
									<tr>
										<td><%=orderNumber++%></td>
										<td>${blist.item}</td>
										<td>${blist.price}</td>
										<td>${blist.qty}</td>
										<td>${blist.price*blist.qty}</td>
										<td>${blist.remark}</td>
										
										<c:set var="sum" value="${sum+(blist.price)*(blist.qty)}"></c:set>
									</tr>
								</c:forEach>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td><strong>總金額</strong></td>
									<td><strong>$${sum}</strong></td>
									<td></td>
								</tr>
							</tbody>
						</table>
					</div>
					<%-- 									<%@ include file="/design/page2.file"%> --%>
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