<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.groupbuy.model.*"%>
<%@ page import="com.groupbuylist.model.*"%>

<% GroupBuyListService groupBuyListSvc =new GroupBuyListService(); %>
<!DOCTYPE html>
<html lang="zh-TW">
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
								<strong>查詢揪團</strong>
							</h5>
						</div>
						<div class="col-1" style="height: 20px; display: inline-block;">
							<a href="<%=request.getContextPath()%>/groupbuy/gbHome.jsp"><strong>回揪團主頁</strong></a>
						</div>
					</div>
				</div>

				<!-- ============== Card Body ============== -->
				<div class="card-body">
					<div class="table-responsive">
						<div id="dataTable_wrapper"
							class="dataTables_wrapper dt-bootstrap4">

							<div class="row">
								<div class="col-sm-12">
								
								<c:forEach var="GBbuyer" items="${GBbuyers}">
								
								<div>${GBbuyer.buyer}
									${GBbuyer.buyer_name}
									${GBbuyer.total}
									${GBbuyer.is_pay== 0? '未付款':'已付款'}
									${GBbuyer.is_pickup== 0? '未取貨':'已取貨'}
								</div>
								
								<%groupBuyListSvc.getMyGB(Integer.valueOf(requst.getParameter("GBbuyer.buyer"))); %>
								
									<table class="table table-bordered dataTable" id="dataTable"
										width="100%" cellspacing="0" role="grid"
										aria-describedby="dataTable_info" style="width: 100%">
										<!-- ========================= 表頭 ========================= -->
										<thead>
											<tr role=" row">
												<th class="sorting sorting_asc" tabindex="0"
													aria-controls="dataTable" rowspan="1" colspan="1"
													aria-sort="ascending"
													aria-label="Name: activate to sort column descending"
													style="width: 20px;">商品</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Position: activate to sort column ascending"
													style="width: 20px;">數量</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Position: activate to sort column ascending"
													style="width: 20px;">金額</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Office: activate to sort column ascending"
													style="width: 20px;">備註</th>
											</tr>
										</thead>

										<!-- ========================= 表格內容 ========================= -->
										<tbody>
													<tr>
														<td>${groupBuylistVO.item}</td>
														<td>${groupBuylistVO.qty}</td>
														<td>${groupBuylistVO.price}</td>
														<td>${groupBuylistVO.remark}</td>
													</tr>
										</tbody>
									</table>
									</c:forEach>
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