<%@page import="com.shop.model.ShopVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.menu.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
List<MenuVO> menuList = (List<MenuVO>) session.getAttribute("menuList");

int menuNumber = 1;
%>

<html class="glightbox-open">
<head>
<%@ include file="/design/frontmetacss.jsp"%>
<style>
.main {
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

.table table-dark {
	overflow-x: hidden;
}
</style>

</head>

<body>
	<div class="wrapper">
		<%@ include file="/design/frontheader.jsp"%>

		<!-- ====================== 內容開始 ====================== -->
		<main id="main" class="main">
			<!-- ======= Portfolio Section ======= -->

			<div class="card shadow mb-4">
				<!-- ============== Card Header ============== -->
				<div class="card-header py-3" style="background-color: #b0c4de">
					<div class="row" style="justify-content: center;">
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


				<section id="portfolio" class="portfolio"
					style="background-color: #DCDDDF;">
					<div class="container" data-aos="fade-up">

						<!-- ============== Card Body ============== -->
						<div class="row" style="place-content: center;">
							<!-- ======= 上傳菜單csv檔 ======= -->
							<div class="col-2" style="display: inline-block;">
<!-- 								<FORM method="post" enctype="multipart/form-data" -->
<%-- 									action="<%=request.getContextPath()%>/menu/uploadcsvservlet"> --%>
<!-- 									<input type="file" name="csvfile" accept=".csv" class="form-control" id="formFile" -->
<%-- 										value="${shopVO.shop_id}"> <input type="hidden" --%>
<!-- 										name="action" value="uploadcsv"> <input type="hidden" -->
<%-- 										name="shop_id" value="${shopVO.shop_id}"> <input --%>
<!-- 										type="submit" class="btn btn-primary btn-sm"value="上傳CSV檔"> -->
<!-- 								</FORM> -->
							</div>

<!-- 							<div class="col-1" style="display: inline-block;"></div> -->
							<div
								style="width: 750px; align-self: center; color: white; background-color: #818C98; display: inline-block;">
								<div class="card-header"
									style="text-align: center; margin: 20px;">
									<h4>${shopVO.shop_name}&nbsp;Menu</h4>
								</div>

								<table class="table table-hover table-dark">
									<!-- ========================= 表頭 ========================= -->
									<thead>
										<tr>
											<th scope="col">項次</th>
											<th scope="col">品項</th>
											<th scope="col">價格</th>
											<th scope="col">狀態</th>
											<th scope="col"></th>

										</tr>
									</thead>

									<c:forEach var="menu" items="${menuList}">
										<tr>
											<td><%=menuNumber++%></td>
											<td><c:out value="${menu.item}" /></td>
											<td><c:out value="${menu.price}" /></td>
											<td><c:if test="${menu.is_item==1}">
													<c:out value="上架中" />
												</c:if> <c:if test="${menu.is_item==0}">
													<c:out value="已下架" />
												</c:if></td>
											<td>
												<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/menu/updatemenubyshop">
													<input type="submit" class="btn btn-secondary btn-sm"
														value="編輯"> <input type="hidden" name="menu_id"
														value="${menu.menu_id}"> <input type="hidden"
														name="shop_id" value="${menu.shop_id}"> <input
														type="hidden" name="action" value="getMenuItem_For_Update">
												</FORM>
											</td>
										</tr>
									</c:forEach>
								</table>
							</div>
							<!-- ======= 新增菜單 ======= -->
							<div class="col-2" style="display: inline-block;">
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/menu/addmenubyshop"
									style="margin-bottom: 0px;">
									<input type="submit" class="btn btn btn-lg"
										style="background-color: #A392A2; color: white;"
										value="我要新增🍴"> <input type="hidden" name="gb_id"
										value="${groupBuyVO.gb_id}"> <input type="hidden"
										name="shop_id" value="${shopVO.shop_id}"> <input
										type="hidden" name="action" value="getShop_For_AddMenu">
								</FORM>
							</div>


						</div>
					</div>
				</section>
				<!-- End Portfolio Section -->
			</div>
		</main>
		<!-- ======= 內容結束 ======= -->

	</div>
	<!-- ======= Footer ======= -->
	<%@ include file="/design/frontfooter.jsp"%>
	<!-- ======= js ======= -->
	<%@ include file="/design/frontjs.jsp"%>
	<script>
		
	</script>
</body>

</html>