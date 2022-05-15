<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.groupbuy.model.*"%>

<%
GroupBuyService groupBuySvc = new GroupBuyService();
List<GroupBuyVO> list = groupBuySvc.getNowAll();
pageContext.setAttribute("list", list);
int itemsPerPage = 10;
%>

<!DOCTYPE html>
<html lang="zh-TW">

<head>
<%@ include file="/design/frontmetacss.jsp"%>
<style>
.portfolio-wrap {
	width: 330px;
	height: 300px;
	display: flex
}

.section-title h2 {
	margin-bottom: 0px;
}
</style>
</head>

<body>
	<div class="wrapper">
		<!-- ======= Header ======= -->
		<%@ include file="/design/frontheader.jsp"%>

		<!-- ======= 內容開始 ======= -->
		<div style="height: var(--header-height);"></div>
		<main>

			<section id="portfolio" class="portfolio" style="background-color: #F4EBE4;">
				<div class="container" data-aos="fade-up">
					<div class="section-title">
						<h2>🍽️ 今天我想來點...</h2>
						<p></p>
					</div>
						<div class="row">
							<div class="col-9" style="height: 60px; display: inline-block;">
								<form class="my-1" METHOD="post" ACTION="<%=request.getContextPath()%>/groupbuy/GroupBuyServlet">
									<div class="form-group col-2" style="display: inline-block;">
										<select class="form-select" id="exampleFormControlSelect1"
											style="border: gray solid 2px;" name="shop_type">
											<option value="">選擇類型</option>
											<option value="0">飲料</option>
											<option value="1">中式</option>
											<option value="2">異國</option>
											<option value="3">小吃</option>
											<option value="4">素食</option>
											<option value="5">其他</option>
										</select>
									</div>
									<div class="form-group col-3" style="display: inline-block">
										<input type="text" class="form-control"
											id="exampleFormControlInput1" placeholder="輸入店名"
											style="border: gray solid 2px;" name="shop_name">
									</div>
									<input type="hidden" name="action" value="listByCompositeQuery">
<!-- 									傳遞揪團中參數 -->
									<input type="hidden" name="gb_status" value="0">
									<button type="submit" class="btn btn-success mb-2 mt-1 col"
										style="display: inline-block;">搜 尋 🔍</button>
								</form>
							</div>
							<div class="col-2 mt-2" style="text-align: right;">
							<a class="btn btn btn-lg" style="background-color: #E6C786; color:white;" href="<%=request.getContextPath()%>/shop/listAllShop.jsp" role="button"><strong>選店家開團</strong></a>
							</div>
						</div>

						<%@ include file="/design/page1.file"%>


					<div class="row portfolio-container" data-aos="fade-up"
						data-aos-delay="200">
						<c:forEach var="groupBuyVO" items="${list}" begin="<%=pageIndex%>"
							end="<%=pageIndex+rowsPerPage-1%>">
							<div class="col-lg-4 col-md-6 portfolio-item filter-card">
								<div class="portfolio-wrap" style="border-radius: 15px;">
									<div
										style="color: white; padding: 5px; z-index: 99; background:#66828E; height: 100px; width: 330px;">
										<h5>${groupBuyVO.gb_id}
											<strong>${groupBuyVO.shop_name}</strong>
										</h5>
										<p>截止: <fmt:formatDate value="${groupBuyVO.end_time}" pattern="yyyy-MM-dd HH:mm"/></p>
										<p>團主: ${groupBuyVO.empVO.empName}</p>
									</div>
									<img
										src="<%=request.getContextPath()%>/util/DBGifReader?pic=shop_img1&table=shop&id_key=shop_id&id=${groupBuyVO.shop_id}"
										class="img-fluid"
										alt="" style="height: 200px; width: 330px;position: absolute; bottom: 0; left: 0; right: 0; margin: auto;">
									<div class="portfolio-info">
										<div class="portfolio-links">
										
										
										<!-- ======= 參團者加入,填寫揪團單 ======= -->
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/groupbuy/GroupBuyServlet"
												style="margin-bottom: 0px;">
												<input type="submit" class="btn btn" style="background-color:#8D8FA4; color:white;" value="手刀加入👉"> 
												<input type="hidden" name="gb_id" value="${groupBuyVO.gb_id}">
												<input type="hidden" name="shop_id" value="${groupBuyVO.shop_id}">
												<input type="hidden" name="action" value="showGB">
											</FORM>

										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>

					<%@ include file="/design/page2.file"%>
				</div>
			</section>
		</main>
		<!-- End #main -->
		<!-- ======= 內容結束 ======= -->

	</div>
	<!-- ======= Footer ======= -->
	<%@ include file="/design/frontfooter.jsp"%>
	<!-- ======= js ======= -->
	<%@ include file="/design/frontjs.jsp"%>

</body>

</html>