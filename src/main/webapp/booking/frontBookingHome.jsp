<%@page import="com.equipment.model.EquipmentVO"%>
<%@page import="com.equipment.model.EquipmentService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.booking.model.*"%>

<%
EquipmentService equipmentSvc = new EquipmentService();
List<EquipmentVO> list = equipmentSvc.getAll();
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

.section-title h2 {
	margin-bottom: 0px;
}
</style>

</head>
<body>

	<!-- ======= Header ======= -->

	<%@ include file="/design/frontheader.jsp"%>

	<!-- End Header -->

	<!-- content 如果頁面要可以往下滑就改一下main的height值吧 -->

	<!-- ======= 內容開始 ======= -->

	<div style="height: var(- -header-height);"></div>
	<main id="main">

		<section id="portfolio" class="portfolio">
			<div class="container" data-aos="fade-up">
				<div class="section-title">
					<h2>租 大於 買 = z 大於 b</h2>
					<p></p>
				</div>

				<div class="row" style="justify-content: end;">
					<div class="col-9"
						style="height: 60px; display: inline-block; text-align: right;">
						<form class="my-1" METHOD="post"
							ACTION="<%=request.getContextPath()%>/equipment/equipment.do"
							name="form1">
							<%
							int itemsPerPage = 9;
							%>
							<%@ include file="/design/page1.file"%>

							<!-- 							<div class="form-group col-2" style="display: inline-block;"> -->
							<%-- 								<jsp:useBean id="secondHandSvc1" scope="page" --%>
							<%-- 									class="com.secondHand.model.SecondHandService" /> --%>
							<!-- 								<select class="form-control" id="exampleFormControlSelect1" -->
							<!-- 									style="border: gray solid 2px;" name="is_deal"> -->
							<!-- 									<option>選擇類型</option> -->
							<!-- 									<option name="is_deal" value="0">競標中</option> -->
							<!-- 									<option name="is_deal" value="1">已成交</option> -->
							<!-- 									<option>顯示全部</option> -->
							<!-- 								</select> -->
							<!-- 							</div> -->

							<!-- 							<div class="form-group col-3" style="display: inline-block"> -->
							<!-- 								<input type="text" class="form-control" -->
							<!-- 									id="exampleFormControlInput1" placeholder="輸入名稱" -->
							<%-- 									style="border: gray solid 2px;" name="name" value="${param.name}"> --%>
							<!-- 							</div> -->
							<%-- 							<input type="hidden" name="action" value="listSecondHands_ByCompositeQuery"> --%>
							<!-- 							<input type="hidden" name="action" value="listSecondHandsByName"> -->
							<!-- 							<input type="submit" class="btn btn-primary mb-2 mt-1 col" -->
							<!-- 								style="display: inline-block;" value="搜尋"></input> -->

							<!-- 							<div class="form-group col-2" style="display: inline-block;"> -->
							<%-- 								<jsp:useBean id="secondHandSvc1" scope="page" --%>
							<%-- <%-- 									class="com.secondHand.model.SecondHandService" /> --%>
							<!-- 								<select class="form-control" id="exampleFormControlSelect1" -->
							<!-- <!-- 									style="border: gray solid 2px;" name="is_deal"> -->
							<!-- <!-- 									<option value="0">競標中</option> -->
							<!-- <!-- 									<option value="1">已成交</option> -->
							<!-- <!-- 									<option value="">顯示全部</option> -->
							<!-- <!-- 								</select> -->
							<!-- <!-- 							</div> -->

							<div class="form-group col-3" style="display: inline-block">
								<input type="text" class="form-control"
									id="exampleFormControlInput1" placeholder="輸入器材名稱"
									style="border: gray solid 2px;" name="eqName"
									value="${param.eqName}">
							</div>
							<%-- <input type="hidden" name="action" value="listSecondHands_ByCompositeQuery"> --%>

							<input type="hidden" name="action" value="listByCompositeQuery">
							<input type="submit" class="btn btn-primary mb-2 mt-1 col"
								style="display: inline-block;" value="搜尋"></input>
						</form>
					</div>
				</div>

				<div class="row portfolio-container" data-aos="fade-up"
					data-aos-delay="200">
					<c:forEach var="equipmentVO" items="${list}" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1%>">
						<div class="col-lg-4 col-md-6 portfolio-item filter-card">
							<div class="portfolio-wrap">
								<div
									style="color: white; padding: 5px; z-index: 99; background: rgba(0, 255, 204, 0.4); height: 50px; width: 300px;">
									<h5>
										<strong>${equipmentVO.eqName}</strong>
									</h5>
								</div>
								<img
									src="
									<%=request.getContextPath()%>/util/DBGifReader?pic=img1&table=equipment&id_key=equipment_id&id=${equipmentVO.equipmentId}
									"
									class="img-fluid"
									alt"" style="max-height: 100%; max-width: 100%; width: auto; height: auto; position: absolute; bottom: 0; left: 0; right: 0; margin: auto;">
								
								<div class="portfolio-info">
									<div class="portfolio-links"></div>

									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/booking/booking.do"
										style="margin-bottom: 0px;">
										<input type="submit" class="btn btn-outline-warning"
											value="點我預約"> <input type="hidden" name="bookingId"
											value="${booking.bookingId}"> <input type="hidden"
											name="shop_id" value="${groupBuyVO.shop_id}">
										<%-- 												<input type="hidden" name="buyer" value="${empVO.empId}"> --%>
										<%-- 												<input type="hidden" name="buyer_name" value="${empVO.empName}"> --%>
										<input type="hidden" name="action" value="showGB">
									</FORM>

								</div>
							</div>
						</div>
					</c:forEach>
				</div>
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