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
		<div class="card shadow mb-4">
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
							<div class="card">
								<div class="card-body">

									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/menu/updatemenubyshop"
										name="form1">
										<table class="table table-borderless">
											<tr>
												<td><strong>品項 :</strong></td>
												<td><input type="TEXT" name="item" size="45"
													value="${param.item}" /></td>
												<td>${errorMsgs.item}</td>
											</tr>
											<tr>
												<td><strong>價格 :</strong></td>
												<td><input type="TEXT" name="price" size="45"
													value="${param.price}" /></td>
												<td>${errorMsgs.price}</td>
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
										<input type="hidden" name="action" value="update">
										<input type="hidden" name="menu_id" value="${param.menu_id}">
										<input type="hidden" name="shop_id" value="${param.shop_id}">
										<input type="submit" value="送出修改" class="btn btn-dark ">
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
	</script>

</body>

</html>