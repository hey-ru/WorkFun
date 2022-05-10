<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.menu.model.*"%>

<%
MenuService menuService = new MenuService();
List<MenuVO> list = menuService.getAll();
pageContext.setAttribute("list", list);

pageContext.getAttribute("shop_id");
%>

<html>
<head>
<%@ include file="/design/frontmetacss.jsp"%>


<style>

.form-elements {
			margin-top: 10px;
		}

		#frm-add-data .form-group {
			margin-left: 13px;
		}
</style>

</head>

<body>

	<%@ include file="/design/frontheader.jsp"%>

	<!-- ====================== 內容開始 ====================== -->
	<main id="main" class="main" style="height: 50vh;">
		<div class="card shadow mb-4">
			<!-- ============== Card Header ============== -->
			<div class="card-header py-3" style="background-color: #b0c4de">
				<div class="row">
					<div class="col-11" style="height: 20px; display: inline-block;">
						<h5>
							<strong>新增店家菜單</strong>
						</h5>
					</div>
					<div class="col-1" style="height: 20px; display: inline-block;">
						<a href="<%=request.getContextPath()%>/menu/listMenuByShop.jsp"><strong>回上頁</strong></a>
					</div>
				</div>
			</div>
			<!-- ============== Card Body ============== -->
			<div class="col-lg-6">
				<div class="card">
					<div class="card-body">



						<%-- 						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/menu/addmenubyshop"> --%>
						<!-- 							<table class="table table-hover" style="text-align: center;"> -->
						<!-- 								<thead> -->
						<!-- 									<tr> -->
						<!-- 										<th>店家ID</th> -->
						<!-- 										<th>品項</th> -->
						<!-- 										<th>價格</th> -->
						<!-- 									</tr> -->
						<!-- 								</thead> -->
						<!-- 								<tbody> -->
						<!-- 								<tr> -->
						<!-- 									<td><input type="text" name="shop" -->
						<%-- 										value="${param.shop_id}"></td> --%>
						<%-- 									<td><input type="text" name="item" value="${param.item}" /></td> --%>
						<%-- 									<td><input type="number" name="price" value="${param.price}" /></td> --%>
						<!-- 								</tr> -->
						<!-- 								<tr> -->
						<!-- 									<td><input type="text" name="shop_id" -->
						<%-- 										value="${param.shop_id}"></td> --%>
						<%-- 									<td><input type="text" name="item" value="${param.item}" /></td> --%>
						<%-- 									<td><input type="number" name="price" value="${param.price}" /></td> --%>
						<!-- 								</tr> -->
						<!-- 								</tbody> -->
						<!-- 							</table> -->

						<!-- 							<input type="hidden" name="action" value="insert">  -->
						<!-- 							<input type="submit" value="新增"> -->
						<!-- 						</form> -->
						<!-- 						================================================================================ -->
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/menu/addmenubyshop">
						<div class="container">

<!-- 							<form class="form-inline" id="frm-add-data" action="javascript:void(0)"> -->

								<div class="field_wrapper">
									<div>
										<div class="form-group">
<!-- 											<label for="shop_id">店家:</label> -->
											<input type="hidden" class="form-control"                   name="shop_id" value="${param.shop_id}">
										</div>				
										<div class="form-group">
											<label for="item">品項:</label>
											<input type="text" class="form-control" placeholder="輸入名稱" name="item" value="${param.item}">
										</div>
										<div class="form-group">
											<label for="price">金額:</label> 
											<input type="number" class="form-control" placeholder="輸入金額" name="price" value="${param.price}">
										</div>
										<div class="form-group">
											<a href="javascript:void(0);" class="add_button"
												title="Add field">+ Add More</a>
										</div>
									</div>
								</div>
								<input type="hidden" name="action" value="insert"> 
													<input type="submit" value="新增">
<!-- 							</form> -->
						</div>
</form>




					</div>
				</div>
			</div>
		</div>
	</main>

	<!-- 			
	<%-- 	<jsp:useBean id="shopservice" scope="page" class="com.shop.model.ShopService" /> --%>
	<!-- 	<tr> -->
	<!-- 		<td>店家:<font color=red><b>*</b></font></td> -->
	<!-- 		<td><select size="1" name="shop_id"> -->
	<%-- 			<c:forEach var="shopVO" items="${shopservice.all}"> --%>
	<%-- 				<option value="${shopVO.shop_id}" ${(param.shop_id==shopVO.shop_id)? 'selected':'' } >${shopVO.shop_name} --%>
	<%-- 			</c:forEach> --%>
	<!-- 		</select></td> -->
	<!-- 	</tr> -->


	<!-- ======= 內容結束 ======= -->
	<!-- ======= Footer ======= -->
	<%@ include file="/design/frontfooter.jsp"%>
	<!-- ======= js ======= -->
	<%@ include file="/design/frontjs.jsp"%>

	<script type="text/javascript">

		$(document).ready(function () {

			var maxField = 100; // Total 100 product fields we add

			var addButton = $('.add_button'); // Add more button selector

			var wrapper = $('.field_wrapper'); // Input fields wrapper

			var fieldHTML = `<div class="form-elements">
					<div class="form-group">

					<input type="hidden" class="form-control"                   name="shop_id" value="${param.shop_id}">
					</div>	
					<div class="form-group">
							<label for="item">品項:</label>
					<input type="text" class="form-control" placeholder="輸入名稱" name="item" value="${param.item}">
					</div>
					<div class="form-group">
					<label for="price">金額:</label>
					<input type="number" class="form-control" placeholder="輸入金額" name="price" value="${param.price}">
					</div>
					<div class="form-group">
					<a href="javascript:void(0);" class="remove_button" title="Add field">Remove</a>
					</div>
				</div>`; //New input field html 

			var x = 1; //Initial field counter is 1

			$(addButton).click(function () {
				//Check maximum number of input fields
				if (x < maxField) {
					x++; //Increment field counter
					$(wrapper).append(fieldHTML);
				}
			});

			//Once remove button is clicked
			$(wrapper).on('click', '.remove_button', function (e) {
				e.preventDefault();
				$(this).parent().closest(".form-elements").remove();
				x--; //Decrement field counter
			});
		});
	</script>
</body>

</html>