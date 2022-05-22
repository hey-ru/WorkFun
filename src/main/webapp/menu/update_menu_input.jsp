<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.menu.model.*"%>

<%

%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/design/frontmetacss.jsp"%>

</head>

<body>

	<%@ include file="/design/frontheader.jsp"%>

	<!-- ====================== 內容開始 ====================== -->
	<main id="main" class="main" style="height: 50vh;">
		<div style="background-color: #DBD4C6;">
			<!-- ============== Card Header ============== -->
			<div class="card-header py-3" style="background-color: #b0c4de">
				<div class="row">
					<div class="col-11" style="height: 20px; display: inline-block;">
						<h5>
							<strong>修改店家菜單</strong>
						</h5>
					</div>
					<div class="col-1" style="height: 20px; display: inline-block;">
						<a href="<%=request.getContextPath()%>/menu/listMenuByShop.jsp"><strong>回上頁</strong></a>
					</div>
				</div>
			</div>
			<!-- ============== Card Body ============== -->

			<div class="col-lg-6">
				<div class="card"
					style="background-color: #DBD4C6; ALIGN-ITEMS: CENTER;">
					<div class="card-body">

						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/menu/updatemenubyshop"
							name="form1">
							<table class="table table-borderless">
								<tr>
									<td><strong>店 家 :</strong></td>
									<td><strong>${shopVO.shop_name}</strong></td>
								</tr>
								<tr>
									<td><strong>品 項 :</strong></td>
									<td><input type="text" required name="item" size="30"
										id="item"
										pattern="^[(\u4e00-\u9fa5)(\u0800-\u4e00)a-zA-Z0-9_+\s\\(\\-\\)\\]*$"
										value="${param.item}" /> <br>
										<p style="color: red;">
											<strong>${errorMsgs.item}</strong>
										</p></td>
									<td><strong><span id="itemError"
											style="color: red;"></span></strong></td>
								</tr>
								<tr>
									<td><strong>價 格 :</strong></td>
									<td><input type="number" required name="price" size="45"
										min="1" value="${param.price}" /> <br>
										<p style="color: red;">
											<strong>${errorMsgs.price}</strong>
										</p></td>
								</tr>
								<tr>
									<td><strong>狀態 :</strong></td>
									<td><label> <input type="radio" name="is_item"
											value="1" ${(param.is_item=="1")? 'checked':'' }> 上架
									</label> <label><input type="radio" name="is_item" value="0"
											${(param.is_item=="0")? 'checked':'' }> 下架 </label></td>
								</tr>

							</table>
							<div style="text-align: end;">
								<input type="hidden" name="action" value="update"> <input
									type="hidden" name="menu_id" value="${param.menu_id}">
								<input type="hidden" name="shop_id" value="${param.shop_id}">
								<input type="submit" value="確定修改" class="btn btn-dark ">
							</div>
						</FORM>
					</div>
				</div>
			</div>
		</div>
	</main>

	<!-- ======= 內容結束 ======= -->
	<!-- ======= Footer ======= -->
	<%-- 	<%@ include file="/design/frontfooter.jsp"%> --%>
	<!-- ======= js ======= -->
	<%@ include file="/design/frontjs.jsp"%>

	<script>
		$("#item").blur(function() {
			if ($(this).val() == '' || $(this).val() == null) {
				$('#itemError').text('未輸入品項名稱!')
			} else if (reg.test($(this).val())) {
				$('#itemError').text('')
			} else {
				$('#itemError').text('輸入格式錯誤😵 格式:中、日、英文、數字、空格() + - _')
			}
		});
	</script>

</body>

</html>