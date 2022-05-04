<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.groupbuy.model.*"%>

<%
GroupBuyService groupBuySvc = new GroupBuyService();
List<GroupBuyVO> list = groupBuySvc.getNowAll();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html lang="zh-TW">

<head>
<%@ include file="/design/frontmetacss.jsp"%>
<style>
.portfolio-wrap {
	width: 300px;
	height: 400px;
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

			<section id="portfolio" class="portfolio">
				<div class="container" data-aos="fade-up">
					<div class="section-title">
						<h2>🍽️ 今天我想來點...</h2>
						<p></p>
					</div>


					<div class="row" style="justify-content: end;">
						<div class="col-9"
							style="height: 60px; display: inline-block; text-align: right;">
							<form class="my-1" METHOD="post"
								ACTION="<%=request.getContextPath()%>/groupbuy/GroupBuyServlet"
								name="form1">
								<%
								int itemsPerPage = 9;
								%>
								<%@ include file="/design/page1.file"%>
								<div class="form-group col-2" style="display: inline-block;">
									<jsp:useBean id="groupBuysvc1" scope="page"
										class="com.groupbuy.model.GroupBuyService" />
									<select class="form-control" id="exampleFormControlSelect1"
										style="border: gray solid 2px;" name="is_deal">
										<option>選擇類型</option>
										<option value="is_deal = 0">競標中</option>
										<option value="is_deal = 1">已成交</option>
										<option>顯示全部</option>
									</select>
								</div>
								<div class="form-group col-3" style="display: inline-block">
									<input type="text" class="form-control"
										id="exampleFormControlInput1" placeholder="輸入名稱"
										style="border: gray solid 2px;" name="name"
										value="${param.name}">
								</div>
								<input type="hidden" name="action"
									value="listSecondHands_ByCompositeQuery"> <input
									type="hidden" name="action" value="listSecondHandsByName">
								<input type="submit" class="btn btn-primary mb-2 mt-1 col"
									style="display: inline-block;" value="搜尋"></input>
							</form>
						</div>
						<div class="col-1"></div>
						<div class="col-2">
						<a class="btn btn-success" href="<%=request.getContextPath()%>/shop/listAllShop.jsp" role="button">選店家開團</a>
						</div>
					</div>

					<div class="row portfolio-container" data-aos="fade-up"
						data-aos-delay="200">
						<c:forEach var="groupBuyVO" items="${list}" begin="<%=pageIndex%>"
							end="<%=pageIndex+rowsPerPage-1%>">
							<div class="col-lg-4 col-md-6 portfolio-item filter-card">
								<div class="portfolio-wrap">
									<div
										style="color: white; padding: 5px; z-index: 99; background: rgba(0, 255, 204, 0.4); height: 100px; width: 300px;">
										<h5>${groupBuyVO.gb_id}
											<strong>${groupBuyVO.shop_name}</strong>
										</h5>
										<p>[揪團截止時間 ${groupBuyVO.end_time}]</p>
									</div>
									<img
										src="<%=request.getContextPath()%>/util/DBGifReader?pic=shop_img1&table=shop&id_key=shop_id&id=${groupBuyVO.shop_id}"
										class="img-fluid"
										alt"" style="max-height: 100%; max-width: 100%; width: auto; height: auto; position: absolute; bottom: 0; left: 0; right: 0; margin: auto;">
									<div class="portfolio-info">
										<div class="portfolio-links">
										
										
										<!-- ======= 參團者加入,填寫揪團單 ======= -->
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/groupbuy/GroupBuyServlet"
												style="margin-bottom: 0px;">
												<input type="submit" class="btn btn-outline-warning" value="加入"> 
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