<%@page import="com.shop.model.ShopService"%>
<%@page import="com.shop.model.ShopVO"%>
<%@page import="com.menu.model.MenuService"%>
<%@page import="com.menu.model.MenuVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="com.groupbuylist.model.*"%>
<%@ page import="com.groupbuy.model.*"%>

<%
//查詢個人單筆明細
List<GroupBuyListVO> list = (List<GroupBuyListVO>) session.getAttribute("buyerlist");

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

th {
	vertical-align: middle;
	text-align: center;
}

td {
	vertical-align: middle;
	text-align: center;
}

.table-responsive {
	overflow-x: hidden;
}
</style>

</head>

<body>

	<div class="wrapper">

		<%@ include file="/design/frontheader.jsp"%>

		;
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

			<div class="card">
				<div class="card-body">
					<h5 class="card-title">
						<strong>${groupBuyVO.shop_name}</strong><br> <br> 團主:
						${groupBuyVO.empVO.empName} &nbsp 分機:
						${groupBuyVO.empVO.extension}<br>
					</h5>

					<div class="col-6" style="display: inline-block;">
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/groupbuylist/selectmygblistservlet">
							<table class="table table-hover">
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

								<!-- ========================= 表格內容 ========================= -->


								<c:forEach var="blist" items="${buyerlist}">
									<input type="hidden" name="gb_id" value="${blist.gb_id}">
									<input type="hidden" name="gbList_id"
										value="${blist.gbList_id}">
									<input type="hidden" name="buyer" value="${blist.buyer}">

									<tr class="order" data-price="${blist.price}">
										<td><%=orderNumber++%></td>
										<td>${blist.item}</td>
										<!-- 單價 -->
										<td>${blist.price}</td>
										<!-- 數量 -->
										<td><input type="number" class="quantity" required
											min="0" max="100" name="qty" value="${blist.qty}"> <!-- 小計 -->
										<td>$<span id="total">${blist.price*blist.qty}</span></td>
										<!-- 備註 -->
										<td><input type="text" name="remark"
											pattern="^[(\u4e00-\u9fa5)(\u0800-\u4e00)a-zA-Z0-9_+\s\\(\\-\\)\\]*$"
											size="20" value="${blist.remark}"></td>
										<!-- [刪除請求] -->
										<td><a
											href="<%=request.getContextPath()%>/groupbuylist/selectmygblistservlet
									?action=deleteItem&gbList_id=${blist.gbList_id}&gb_id=${blist.gb_id}&buyer=${blist.buyer}"
											style="font-size: 30px;" title="刪除此項"><i
												class="bi bi-trash"></i></a></td>
									</tr>
									<c:set var="sum" value="${sum+(blist.price)*(blist.qty)}"></c:set>
								</c:forEach>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td><strong>總金額$${sum}</strong></td>
									<td></td>
									<td></td>
								</tr>
							</table>
							<div></div>
							<!-- [修改請求] -->
							<div style="text-align: right;">
								<input type="hidden" name="action" value="updateMany"> <input
									type="submit" class="btn btn-success"
									onclick="javascript:return window.alert('已送出修改')" value="送出訂單">
							</div>
						</FORM>
					</div>
					<div class="col-4" style="display: inline-block;">
						<%-- 												<img src="<%=request.getContextPath()%>/shop/images/sticker.png"> --%>

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
		// 	動態顯示計算金額
		$(document).ready(
				function() {
					$('.order').on(
							'keyup',
							'.quantity',
							function() {
								var price = +$(this).closest('.order').data(
										'price');
								var quantity = +$(this).val();
								$(this).closest('.order').find('#total').text(
										price * quantity);
							});
					$('.order').on(
							'click',
							'.quantity',
							function() {
								var price = +$(this).closest('.order').data(
										'price');
								var quantity = +$(this).val();
								$(this).closest('.order').find('#total').text(
										price * quantity);
							});
				});

		// 監聽輸入框
		$(document).ready(function() {
			$("input").focus(function() {
				$(this).css("background-color", "#C9C9C9");
			});
			$("input").blur(function() {
				$(this).css("background-color", "#ffffff");
			});
		});
	</script>

</body>

</html>