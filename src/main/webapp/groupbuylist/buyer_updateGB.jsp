<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.menu.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
List<MenuVO> menuList = (List<MenuVO>) request.getAttribute("menuList");
%>

<html>
<head>
<%@ include file="/design/frontmetacss.jsp"%>


<style>

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
								<strong>填寫揪團單</strong>
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
								<div class="col-sm-6">
									<!-- Table with hoverable rows -->
									<table class="table table-hover" style="text-align: center;">
										<thead>
											<tr>
												<th scope="col">編號</th>
												<th scope="col">品項</th>
												<th scope="col">單價</th>
												<th scope="col">數量</th>
												<th scope="col">備註</th>
											</tr>
										</thead>

										<c:forEach var="menu" items="${menuList}">

											<tr>
												<td><c:out value="${menu.menu_id}" /></td>
												<td><c:out value="${menu.item}" /></td>
												<td><c:out value="${menu.price}" /></td>
												<!-- 	
												<td><input type="text" name="qty" size="3" value=></td> -->
												<!-- 數量增減 -->
												<td>
													<div class="product-qty">
														<button id="decrement">
															<ion-icon name="remove-outline"></ion-icon>
														</button>
														<span id="quantity">0</span>
														<button id="increment">
															<ion-icon name="add-outline"></ion-icon>
														</button>
													</div>
												</td>


												<td><input type="text" name="remark" size="15" value=></td>

												
												<!-- 編輯品項 -->
<!-- 												<td> -->
<!-- 													<FORM METHOD="post" -->
<%-- 														ACTION="<%=request.getContextPath()%>/groupbuylist/JoinGB"> --%>
<!-- 														<input type="submit" class="btn btn-success btn-sm" -->
<!-- 															value="編輯"> <input type="hidden" name="gblist_id" -->
<%-- 															value="${groupbuylist.gblist_id}"> <input --%>
<!-- 															type="hidden" name="action" value="updateitem"> -->
<!-- 													</FORM> -->
<!-- 												</td> -->
												<!-- 刪除品項 -->
<!-- 												<td> -->
<!-- 													<FORM METHOD="post" -->
<%-- 														ACTION="<%=request.getContextPath()%>/groupbuylist/joinGB"> --%>
<!-- 														<input type="submit" class="btn btn-secondary btn-sm" -->
<!-- 															value="移除"> <input type="hidden" name="gblist_id" -->
<%-- 															value="${groupbuylist.gblist_id}"> <input --%>
<!-- 															type="hidden" name="action" value="deleteitem"> -->
<!-- 													</FORM> -->
<!-- 												</td> -->
											</tr>
										</c:forEach>
									</table>
								<div class="row">
								<div class="col-sm-2">
								<!-- 加入品項 -->
													<FORM METHOD="post"
														ACTION="<%=request.getContextPath()%>/groupbuylist/JoinGB">
														<input type="submit" class="btn btn-warning btn-sm" value="下單">
														<input type="hidden" name="gblist_id" value="${groupbuylist.gblist_id}"> 
														<input type="hidden" name="action" value="insertAll">
													</FORM>
								
								</div>
								</div>
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


	<script>
		'use strict';

		const decrementBtn = document.querySelectorAll('#decrement');
		const quantityElem = document.querySelectorAll('#quantity');
		const incrementBtn = document.querySelectorAll('#increment');

		// loop: for add event on multiple `increment` & `decrement` button
		for (let i = 0; i < incrementBtn.length; i++) {

			incrementBtn[i].addEventListener('click',function() {
				let increment = Number(this.previousElementSibling.textContent);
				increment++;
				this.previousElementSibling.textContent = increment;
			});

			decrementBtn[i].addEventListener('click', function() {
				let decrement = Number(this.nextElementSibling.textContent);
				decrement <= 0 ? 0 : decrement--;
				this.nextElementSibling.textContent = decrement;
			});

		}

	</script>


</body>


</html>