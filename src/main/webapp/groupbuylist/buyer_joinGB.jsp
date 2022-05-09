<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.menu.model.*"%>
<%@ page import="com.groupbuylist.model.*"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
List<MenuVO> menuList = (List<MenuVO>) session.getAttribute("menuList");

int orderNumber = 1;
%>

<html>
<head>
<%@ include file="/design/frontmetacss.jsp"%>


<style>
main {
  max-width: 700px;
  margin-right: auto;
  margin-left: auto;
  margin-top: 30px;
  background-color: #AAC789;
  padding: 30px;
  border-radius: 10px;
}

/* 購物車 start */

table {
  background-color: #fff;
  width: 100%;
  border-radius: 5px;
}

thead tr {
  background-color: #BADBB2;
}

thead td {
  padding: 7px;
}

tbody td {
  padding: 7px;
}

.selectAll {
  padding: 10px;
  color: white;
  background-color: #2B735B;
  text-decoration: none;
  border-radius: 4px;
}

/* 購物車 end */

</style>

</head>

<body>
	<div class="wrapper">

		<%@ include file="/design/frontheader.jsp"%>

		<!-- ====================== 內容開始 ====================== -->
		<main id="main" class="main">
			<div >
				<!-- ============== Card Header ============== -->
				<div class="card-header py-3" style="background-color: #BADBB2">
					<div class="row">
						<div class="col-9" style="height: 20px; display: inline-block;">
							<h3>
								<strong>填寫訂單</strong>
							</h3>
						</div>
						<div class="col-3" style="height: 20px; display: inline-block;">
							<a href="<%=request.getContextPath()%>/groupbuy/gbHome.jsp"><strong>瀏覽其他團</strong></a>
						</div>
					</div>
				</div>

				<!-- ============== Card Body ============== -->
				<div class="card-body">
<!-- 					<div class="table-responsive"> -->
<!-- 						<div id="dataTable_wrapper" -->
<!-- 							class="dataTables_wrapper dt-bootstrap4"> -->

<!-- 							<div class="row"> -->
								<div class="col-sm-12">
								
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/groupbuylist/addGBList">
										<table class="table table-hover" style="text-align: center;">
											<thead>
												<tr>
													<th scope="col"></th>
													<th scope="col"></th>
													<th scope="col"></th>
													<th scope="col">編號</th>
													<th scope="col">品項</th>
													<th scope="col">單價</th>
													<th scope="col">數量</th>
													<th scope="col">小計</th>
													<th scope="col">備註</th>
												</tr>
											</thead>

											<c:forEach var="menu" items="${menuList}">

												<tr class="order" data-price="${menu.price}">
													<td><input type="hidden" name="gb_id"
														value="${groupBuyVO.gb_id}"></td>
													<td><input type="hidden" name="buyer"
														value="${empVO.empId}"></td>
													<td><input type="hidden" name="buyer_name"
														value="${empVO.empName}"></td>
													<td><%=orderNumber++%><input type="hidden"
														name="menu_id" value="${menu.menu_id}" /></td>
													<td>${menu.item}<input type="hidden" name="item"
														value="${menu.item}" /></td>
													<!-- 單價 -->
													<td>${menu.price}<input type="hidden" name="price"
														value="${menu.price}" /></td>
													<!-- 數量 -->
													<td><input type="number" class="quantity" min="0"
														max="10" name="qty" value="0"></td>
													<!-- 金額 -->
													<td>$<span id="total">0</span></td>

													<td><input type="text" name="remark" size="15"
														value="${param.remark}"></td>
												</tr>

											</c:forEach>
										</table>
										<input type="hidden" name="action" value="insert2GBlist">
										<input type="submit" class="selectAll" value="火速下單ヽ(●´∀`●)ﾉ">
									</FORM>
									
<!-- 								</div> -->
<!-- 							</div> -->
							
							
							
<!-- 						</div> -->
					</div>
				</div>
				<!-- ============== Card Body ============== -->
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
				$(this).css("background-color", "ffd9e6");
			});
			$("input").blur(function() {
				$(this).css("background-color", "#ffffff");
			});
		});
	</script>

</body>


</html>