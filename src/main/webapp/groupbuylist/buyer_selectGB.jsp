<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="com.groupbuylist.model.*"%>
<%@ page import="com.groupbuy.model.*"%>

<%
//只能查詢個人參團紀錄
Integer buyer = Integer.valueOf(request.getParameter("buyer"));
GroupBuyListService gblistSvc = new GroupBuyListService();
List<GroupBuyListVO> mygblist = gblistSvc.getMyGB(buyer);
pageContext.setAttribute("mygblist", mygblist);

int itemsPerPage = 10;
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
					<div class="table-responsive">
						<div id="dataTable_wrapper"
							class="dataTables_wrapper dt-bootstrap4">

							<div class="row">
								<div class="col-sm-12">
									<table class="table table-bordered dataTable" id="dataTable"
										width="100%" cellspacing="0" role="grid"
										aria-describedby="dataTable_info" style="width: 100%">
										<!-- ========================= 表頭 ========================= -->
										<thead>
											<tr role=" row">
												<th class="sorting" tabindex="0" aria-controls="dataTable" 
													rowspan="1" colspan="1" 
													aria-label="Name: activate to sort column descending">參團編號</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Name: activate to sort column ascending">店家名稱</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Name: activate to sort column ascending"
													>總金額</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Name: activate to sort column ascending"
													>付款狀態</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Name: activate to sort column ascending"
													>取貨狀態</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Name: activate to sort column ascending"
													>開始時間</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Name: activate to sort column ascending"
													>結束時間</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Name: activate to sort column ascending"
													>團狀態</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Name: activate to sort column ascending"
													></th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Name: activate to sort column ascending"
													></th>
											</tr>
										</thead>


										<%-- 										<%@ include file="/design/page1.file"%> --%>

										<!-- ========================= 表格內容 ========================= -->
										<tbody>
									<!-- 用groupbuylist取出 gbList_id,price,qty,is_pay,is_pickup -->
											<c:forEach var="mygb" items="${mygblist}">
													<tr>
														<td>${mygb.gbList_id}</td>
														<td>${mygb.groupBuyVO.shop_name}</td>
														<td>${mygb.price*mygb.qty}</td>
														<td>${mygb.is_pay eq 0? "未付款":"已付款"}</td>
														<td>${mygb.is_pickup eq 0? "未取貨":"已取貨"}</td>
														<td>${mygb.groupBuyVO.start_time}</td>
														<td>${mygb.groupBuyVO.end_time}</td>
														<td>${mygb.groupBuyVO.gb_status}</td>
														<td><a href="buyer_3_updateGb.html"><button
																	type="button" class="btn btn-success btn-sm">編輯</button></a></td>
														<td><input type="submit"
															class="btn btn-secondary btn-sm" value="退出"></td>
													</tr>
													<!-- 揪團截止不能取消及編輯 -->
												</c:forEach>
										</tbody>
									</table>
								</div>
							</div>

<%-- 							<%@ include file="/design/page2.file"%> --%>



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