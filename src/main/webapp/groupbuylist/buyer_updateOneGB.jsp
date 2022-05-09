<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="com.groupbuylist.model.*"%>
<%@ page import="com.groupbuy.model.*"%>

<%
//查詢個人單筆明細
List<GroupBuyListVO> list = (List<GroupBuyListVO>) request.getAttribute("buyerlist");

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
								<strong>參團明細</strong>
							</h5>
						</div>
						<div class="col-1" style="height: 20px; display: inline-block;">
							<a href="<%=request.getContextPath()%>/groupbuylist/buyer_selectGB.jsp?buyer=${empVO.empId}"><strong>回查詢參團</strong></a>
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
										<!-- ========================= 表頭 ========================= -->
										<thead>
											<tr role=" row">
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Position: activate to sort column ascending">編號</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Position: activate to sort column ascending">品項</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Position: activate to sort column ascending">單價</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Office: activate to sort column ascending">數量</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Office: activate to sort column ascending">備註</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Office: activate to sort column ascending"></th>
											</tr>
										</thead>


<%-- 										<%@ include file="/design/page1.file"%> --%>

										<!-- ========================= 表格內容 ========================= -->
										<tbody>
											<c:forEach var="blist" items="${buyerlist}">
											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/groupbuylist/selectmygblistservlet">
												<tr>
													<td>${blist.gbList_id}</td>
													<td>${blist.item}</td>
													<td>${blist.price}</td>
													<td><input type="number" min="0" max="10" 
       													name="qty" value="${blist.qty}"></td>
													<td><input type="text" name="remark" size="15"
														value="${blist.remark}"></td>
												<!-- 編輯品項 -->
													<td>
													<input type="submit" class="btn btn-success btn-sm" value="送出修改"> 
													<input type="hidden" name="buyer" value="${empVO.empId}">
													<input type="hidden" name="gbList_id" value="${blist.gbList_id}">
													<input type="hidden" name="gb_id" value="${blist.gb_id}">
													<input type="hidden" name="action" value="updateItem">
													</td>
												</tr>
											</FORM>
											</c:forEach>
										</tbody>
											
											
									</table>

								</div>
							</div>

<%-- 										<%@ include file="/design/page2.file"%> --%>

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