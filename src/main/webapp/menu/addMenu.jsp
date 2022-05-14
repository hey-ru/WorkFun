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
	<main id="main" class="main" style="height: 300vh">
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
				<div class="card" style="background-color: #EBD6D6;">

					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/menu/addmenubyshop">
						<div class="card-body">
							<div class="container">

								<div class="form-group" style="float: right;">
									<a href="javascript:void(0);" class="add_button"
										style="font-size: 40px;" title="新增一項"><i
										class="bi bi-plus-square-fill bi--4xl"></i></a>
								</div>
								<div class="field_wrapper ">
									<div style="DISPLAY: INLINE-FLEX;">
										<div class="form-group"
											style="text-align: center;>
										<!-- "品項:只能是中、日、英文字母、數字、_、-、+空格和()" -->
											<label for="item"><strong>品 項 <strong></label>
											<input type="text" required size=30px
												pattern="^[(\u4e00-\u9fa5)(\u0800-\u4e00)a-zA-Z0-9_+\s\\(\\-\\)\\]*$"
												class="form-control" placeholder="輸入名稱" name="item"
												value="${param.item}">
										</div>
										<div class="form-group"
											style="text-align: center;>
											<label for="price">金 額 </label> 
											<input type="number" required
												class="form-control" placeholder="輸入金額" name="price" min="1"
												value="${param.price}">
										</div>
										<div class="form-group">
											${errorMsgs.item}
											${errorMsgs.price}
										</div>
									</div>
								</div>
							</div>
						</div>
						<div style="text-align: end; padding: 30;">
							<input type="hidden" class="form-control" name="shop_id"
								value="${param.shop_id}"> <input type="hidden"
								name="action" value="insert"> <input type="submit"
								class="btn btn-dark" value="送出新增">
						</div>
					</form>
				</div>
			</div>
			
			
			
			</div>
		</div>
	</main>
	


											<!-- ======= 內容結束 ======= -->
											<!-- ======= Footer ======= -->
											<%@ include file="/design/frontfooter.jsp"%>
											<!-- ======= js ======= -->
											<%@ include file="/design/frontjs.jsp"%>
											<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>


											<script type="text/javascript">
	
		$(document).ready(function () {

			var maxField = 100; // Total 100 product fields we add

			var addButton = $('.add_button'); // Add more button selector

			var wrapper = $('.field_wrapper'); // Input fields wrapper

			var fieldHTML = `<div class="form-elements">
				<div style="DISPLAY: INLINE-FLEX;">
					<div class="form-group">
							<label for="item"></label>
					<input type="text" required
					pattern="^[(\u4e00-\u9fa5)(\u0800-\u4e00)a-zA-Z0-9_+\s\\(\\-\\)\\]*$"
					class="form-control" placeholder="輸入名稱" name="item" size=30px value="${param.item}">
					</div>
					<div class="form-group">
							<label for="price"></label>
					<input type="number" required
					class="form-control" placeholder="輸入金額" name="price" min="1" value="${param.price}">
					</div>
					<div class="form-group">
					<a href="javascript:void(0);" class="remove_button" style="font-size:30px;" title="移除此項"><i class="bi bi-trash"></i></a>
					</div>
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
				$(this).parent().closest("
											.form-elements").remove();
				x--; //Decrement field
											counter
			});
		});
		
		$(document).ready(function
											() {
		Swal.fire({
			  icon: 'info',
			  title: '   幫 這
											間 店 家 新 增 菜單  \n 🥗🍿🍜🍕🍣🍩🍦🥤☕',
			});
		});
		
	
											</script>

</body>

</html>