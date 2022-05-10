<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="com.groupbuylist.model.*"%>
<%@ page import="com.groupbuy.model.*"%>

<%
//查詢個人單筆明細
List<GroupBuyListVO> list = (List<GroupBuyListVO>) request.getAttribute("buyerlist");

// int itemsPerPage = 10;
int orderNumber = 1;
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
								<strong>參團明細</strong>
							</h5>
						</div>
						<div class="col-1" style="height: 20px; display: inline-block;">
							<a
								href="<%=request.getContextPath()%>/groupbuylist/buyer_selectGB.jsp?buyer=${empVO.empId}"><strong>回查詢參團</strong></a>
						</div>
					</div>
				</div>
			</div>
			<!-- ============== Card Body ============== -->
			<div class="card-body">
				<div class="col-sm-6">

					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/groupbuylist/selectmygblistservlet">
						<table class="table table-bordered dataTable" id="dataTable"
							role="grid" aria-describedby="dataTable_info"
							style="cellspacing: 0; width: 100%">

							<!-- ========================= 表頭 ========================= -->
							<thead>
								<tr role=" row">
									<th class="sorting" tabindex="0" aria-controls="dataTable"
										rowspan="1" colspan="1"
										aria-label="Office: activate to sort column ascending">編號</th>

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

								</tr>
							</thead>

							<!-- ========================= 表格內容 ========================= -->

							<c:forEach var="blist" items="${buyerlist}">

								<input type="hidden" name="gbList_id" value="${blist.gbList_id}">
								<input type="hidden" name="gb_id" size="5"
									value="${blist.gb_id}">
								<input type="hidden" name="buyer" size="5"
									value="${blist.buyer}">
								<input type="hidden" name="buyer_name" size="5"
									value="${hidden.buyer_name}">
								<input type="hidden" name="menu_id" size="5"
									value="${blist.menu_id}" />
								<tr>
									<td><%=orderNumber++%></td>
									<td>${blist.item}<input type="hidden" name="item"
										value="${blist.item}" /></td>
									<!-- 單價 -->
									<td>${blist.price}<input type="hidden" name="price"
										value="${blist.price}" /></td>
									<!-- 數量 -->
									<td><input type="number" required min="0" max="100"
										name="qty" value="${blist.qty}"> <!-- 備註 -->
									<td><input type="text" name="remark"
										pattern="^[(\u4e00-\u9fa5)(\u0800-\u4e00)a-zA-Z0-9_\\(\\-\\)]*$"
										size="15" value="${blist.remark}"></td>
								</tr>
							</c:forEach>
						</table>
						<!-- 編輯品項 -->
						<input type="hidden" name="action" value="updateMany"> <input
							type="submit" class="btn btn-success btn-sm" value="修改完畢! 送出訂單">
					</FORM>


				</div>
			</div>
		</main>
		<!-- ======= 內容結束 ======= -->



	</div>
	<!-- ======= Footer ======= -->
	<%@ include file="/design/frontfooter.jsp"%>
	<!-- ======= js ======= -->
	<%@ include file="/design/frontjs.jsp"%>

	<script>
		// 監聽輸入框
		$(document).ready(function() {
			$("input").focus(function() {
				$(this).css("background-color", "ffd9e6");
			});
			$("input").blur(function() {
				$(this).css("background-color", "#ffffff");
			});
		});
	</script>

</body>

</html>