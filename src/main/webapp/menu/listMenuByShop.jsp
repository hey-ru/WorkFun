<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.menu.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
List<MenuVO> list = (List<MenuVO>) request.getAttribute("menuList");
// int itemsPerPage = 10; //設定每頁頁數
%>

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
								<strong>查詢店家菜單</strong>
							</h5>
						</div>
						<div class="col-1" style="height: 20px; display: inline-block;">
							<a href="<%=request.getContextPath()%>/shop/listAllShop.jsp"><strong>回店家列表</strong></a>
						</div>
					</div>
				</div>
				
				<!-- 新增菜單請求 -->
				<div style="text-align:right; margin:10px">
					<a
						href="${pageContext.request.contextPath}/menu/addMenu.jsp?shop_id=${param.shop_id}">
						<button type="button" class="btn btn-info btn">新增項目</button>
					</a>
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
										<thead>
											<tr role=" row">
												<th class="sorting sorting_asc" tabindex="0"
													aria-controls="dataTable" rowspan="1" colspan="1"
													aria-sort="ascending"
													aria-label="Name: activate to sort column descending"
													style="width: 30px;">編號</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Position: activate to sort column ascending"
													style="width: 100px;">品項</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Position: activate to sort column ascending"
													style="width: 30px;">價格</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Office: activate to sort column ascending"
													style="width: 30px;">狀態</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Office: activate to sort column ascending"
													style="width: 20px;"></th>
											</tr>
										</thead>


<%-- 														<%@ include file="/design/page1.file"%> --%>
<%-- 															<c:forEach var="menu" items="${menuList}" begin="<%=pageIndex%>" --%>
<%-- 																				end="<%=pageIndex+rowsPerPage-1%>"> --%>


										<c:forEach var="menu" items="${menuList}">

											<tr>
												<td><c:out value="${menu.menu_id}" /></td>
												<td><c:out value="${menu.item}" /></td>
												<td><c:out value="${menu.price}" /></td>
												<td><c:if test="${menu.is_item==1}">
														<c:out value="上架" />
													</c:if> <c:if test="${menu.is_item==0}">
														<c:out value="下架" />
													</c:if></td>
												<td>
													<FORM METHOD="post"
														ACTION="<%=request.getContextPath()%>/menu/updatemenubyshop">
														<input type="submit" class="btn btn-success btn-sm"
															value="編輯"> 
														<input type="hidden" name="menu_id"
															value="${menu.menu_id}"> 
														<input type="hidden"
															name="action" value="getMenuItem_For_Update">
													</FORM>
												</td>
											</tr>
										</c:forEach>
									</table>


									<%-- <%@ include file="/design/page2.file"%> --%>

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