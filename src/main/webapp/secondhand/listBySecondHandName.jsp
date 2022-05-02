<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.secondHand.model.*"%>

<%
List<SecondHandVO> list = (List<SecondHandVO>) request.getAttribute("list");
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/design/frontmetacss.jsp"%>

<style>
.portfolio-wrap {
	width: 300px;
	height: 400px;
	display: flex
}
</style>

</head>

<body>

	<!-- ======= Header ======= -->
	<%@ include file="/design/frontheader.jsp"%>
	<!-- End Header -->

	<!-- content 如果頁面要可以往下滑就改一下main的height值吧 -->
	<main id="main">

		<section id="portfolio" class="portfolio">
			<div class="container" data-aos="fade-up">

				<div class="section-title">
					<h2>二手商品總覽</h2>

				</div>

				<div class="row" style="justify-content: end;">
					<div class="col-10"
						style="height: 60px; display: inline-block; text-align: right;">
						<form class="my-1">
							<%
							int itemsPerPage = 9;
							%>
							<%@ include file="/design/page1.file"%>
							<div class="form-group col-2" style="display: inline-block;">
								<jsp:useBean id="secondHandSvc1" scope="page"
									class="com.secondHand.model.SecondHandService" />
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
									id="exampleFormControlInput1" placeholder="輸入關鍵字"
									style="border: gray solid 2px;" name="name">
							</div>
							<input type="submit" class="btn btn-primary mb-2 mt-1 col"
								style="display: inline-block;" value="搜尋"></input>
								<%-- <input type="hidden" name="action" value="listSecondHands_ByCompositeQuery"> --%>
								<input type="hidden" name="action" value="listSecondHandsByName">

						</form>
					</div>
				</div>

				<div class="row portfolio-container" data-aos="fade-up"
					data-aos-delay="200">
					<c:forEach var="secondHandVO" items="${list}"
						begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<div class="col-lg-4 col-md-6 portfolio-item filter-card">
							<div class="portfolio-wrap">
								<%-- 下面是base64寫法 --%>
								<%-- <img src="data:image/png;base64, ${secondHandVO.img1}" --%>
								<%-- 上面是base64寫法 --%>
								<img
									src="
									<%=request.getContextPath()%>/util/DBGifReader?pic=img1&table=second_hand&id_key=second_hand_id&id=${secondHandVO.second_hand_id}
									"
									class="img-fluid"
									alt"" style="max-height: 100%; max-width: 100%; width: auto; height: auto; position: absolute; top: 0; bottom: 0; left: 0; right: 0; margin: auto;">
								<div class="portfolio-info">
									<h4>${secondHandVO.name}</h4>
									<p>[競標截止時間 ${secondHandVO.end_time}]</p>
									<div class="portfolio-links">
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/secondhand/SecondHandServlet"
											style="margin-bottom: 0px;">
											<input type="submit" value="修改"> <input type="hidden"
												name="second_hand_id" value="${secondHandVO.second_hand_id}">
											<input type="hidden" name="action" value="getOneForUpdate">
										</FORM>
										<a
											href="${pageContext.request.contextPath}/secondhand/updateSecondHandMini.jsp"
											class="portfolio-details-lightbox"
											data-glightbox="type: external" title="參與競標"> <i
											class="bx bx-link"></i>工事中
										</a>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>

				<%@ include file="/design/page2.file"%>
		</section>
	</main>
	<!-- End #main -->
	<!-- ======= Footer ======= -->
	<%@ include file="/design/frontfooter.jsp"%>
		
		<!-- End  Footer -->

	<!-- Vendor JS Files -->
	<%@ include file="/design/frontjs.jsp"%>

</body>

</html>