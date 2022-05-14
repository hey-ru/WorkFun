<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.menu.model.*"%>
<%@ page import="com.groupbuylist.model.*"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%

List<MenuVO> menuList = (List<MenuVO>) session.getAttribute("menuList");

int orderNumber = 1;
%>

<html>
<head>
<%@ include file="/design/frontmetacss.jsp"%>


<style>
#mainleft {
	margin-top: 20px;
/* 	margin-right: auto; */
	margin-left: auto;
	background-color: #DDCFC2;
	padding: 30px;
	border-radius: 10px;
}
#mainarea {
	max-width: 700px;
	margin-right: auto;
	margin-left: auto;
	margin-top: 20px;
	background-color: #586D80;
	padding: 20px;
	border-radius: 10px;
}

#card-title{
 font-family: fantasy;
}

/* 購物車 start */
table {
	background-color: #fff;
	width: 100%;
	border-radius: 5px;
}

thead tr {
	background-color: #BFC2CB;
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
	background-color: #2A4C65;
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
		<main id="main" class="main" style="background-color: #EFF0EA;">
			<div class="row">
			<!-- ====================== 左 ====================== -->
			<div id="mainleft" class="col-4" style="height: max-content;font-weight:bold; font-family: Andale Mono, monospace;">
              <h3 class="card-title" style="text-align: center; background-color: #DBD2C9;"><strong>${groupBuyVO.shop_name}</strong></h3>
              <p>💁‍♂️團主: ${groupBuyVO.empVO.empName} &nbsp 📞分機: ${groupBuyVO.empVO.extension}<br>
              <jsp:useBean id="now" class="java.util.Date" />
              截止時間: <fmt:formatDate value="${groupBuyVO.end_time}" pattern="yyyy-MM-dd HH:mm"/> <br>
             到貨時間: <fmt:formatDate value="${groupBuyVO.arr_time}" pattern="yyyy-MM-dd HH:mm"/></p>
              <!-- Slides with fade transition -->
              <div id="carouselExampleFade" class="carousel slide carousel-fade" data-bs-ride="carousel">

			  <div class="carousel-inner">
                  <div class="carousel-item active">
                  <img src="<%=request.getContextPath()%>/util/DBGifReader?pic=shop_img1&table=shop&id_key=shop_id&id=${groupBuyVO.shop_id}"
                  class="d-block w-100">
                </div>

                <div class="carousel-item">
                  <img src="<%=request.getContextPath()%>/util/DBGifReader?pic=shop_img2&table=shop&id_key=shop_id&id=${groupBuyVO.shop_id}"
                  class="d-block w-100">
                </div>

                 <div class="carousel-item">
                  <img src="<%=request.getContextPath()%>/util/DBGifReader?pic=shop_img3&table=shop&id_key=shop_id&id=${groupBuyVO.shop_id}"
                  class="d-block w-100">
                </div>
              </div>

              <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="prev">
                  <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                  <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="next">
                  <span class="carousel-control-next-icon" aria-hidden="true"></span>
                  <span class="visually-hidden">Next</span>
                </button>
            </div>
		</div>
				
	<!-- ====================== 右 ====================== -->
				<div id="mainarea" class="col-8" style="display: inline-block;">
					<div>
						<!-- ============== Card Header ============== -->
						<div class="card-header py-3"
							style="background-color: #BFC2CB; margin: 15;">
							<div class="row">
								<div class="col-4" style="height: 20px; display: inline-block;">
									<h3>
										<strong>填寫訂購單</strong>
									</h3>
								</div>
								<div class="col-5" style="height: 20px; display: inline-block; text-align:center;">
										<h5 style="color:red;">${errorMsgs.msgQty} ${errorMsgs.remark}</h5>
								</div>
								<div class="col-3" style="height: 20px; display: inline-block;text-align: end;">
									<a href="<%=request.getContextPath()%>/groupbuy/gbHome.jsp"><strong>瀏覽其他團</strong></a>
								</div>
							</div>
						</div>

						<!-- ============== Card Body ============== -->

						<div class="card-body">
							<div class="col-sm-12">

								<FORM METHOD="post" id="form1" name="form1"
									ACTION="<%=request.getContextPath()%>/groupbuylist/addGBList">
									<table class="table table-hover" style="text-align: center;background-color: #BFC2CB">
										<thead>
											<tr>
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

												<td><%=orderNumber++%><input type="hidden"
													name="menu_id" value="${menu.menu_id}" /></td>
												<td>${menu.item}<input type="hidden" name="item"
													value="${menu.item}" /></td>
												<!-- 單價 -->
												<td>${menu.price}<input type="hidden" name="price"
													value="${menu.price}" /></td>
												<!-- 數量 -->
												<td><input type="number" class="quantity"
													required="required" id="qty" min="0" max="100" name="qty"
													value="0"></td>
												<!-- 小計 -->
												<td>$<span id="total">0</span></td>
												<!-- "備註: 只能是中、日、英文字母、數字、_、-、+和()" -->
												<td><input type="text" name="remark"
													pattern="^[(\u4e00-\u9fa5)(\u0800-\u4e00)a-zA-Z0-9_+\\(\\-\\)\\]*$"
													size="15" value="${param.remark}"></td>
											</tr>

										</c:forEach>
									</table>
									<input type="hidden" name="gb_id" value="${groupBuyVO.gb_id}">
									<input type="hidden" name="buyer" value="${empVO.empId}">
									<input type="hidden" name="buyer_name" value="${empVO.empName}">
									<input type="hidden" name="action" value="insert2GBlist">

									<div style="TEXT-ALIGN-LAST: CENTER;">
										<input type="submit" name="button" id="button"
											class="selectAll" value="火速下單 ヽ(●´∀`●)ﾉ">
									</div>
								</FORM>

							</div>
						</div>

						<!-- ============== Card Body ============== -->
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
	

	
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

	<script>
	

	
		//未填寫數量alert
		// 		$(document).ready(function() {
		// 			$("#button").click(function() {
		// 				if ($("#qty").val() == 0) {
		// 					Swal.fire({
		// 						  icon: 'warning',
		// 						  title: '請先選擇數量再送出',
		// 						});
		// // 					alert("你尚未填寫數量");
		// 					eval("document.form1['qty'].focus()");
		// 				} else {
		// 					document.form1.submit();
		// 				}
		// 			})
		// 		})

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
				$(this).css("background-color", "#FFCF78");
			});
			$("input").blur(function() {
				$(this).css("background-color", "#ffffff");
			});
		});
	</script>

</body>


</html>