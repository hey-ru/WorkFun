<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.menu.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
MenuService menuService = new MenuService();
List<MenuVO> list = menuService.getAll();
pageContext.setAttribute("list", list);
%>

<html>
<head>
<%@ include file="/design/frontmetacss.jsp"%>
</head>

<body>
	<div class="wrapper">

		<%@ include file="/design/frontheader.jsp"%>

		<!-- ====================== 內容開始 ====================== -->
		<main id="main" class="main">
				<div class="row">
						<!-- ============== Card Header ============== -->
						<div class="card-header py-3" style="background-color:#b0c4de">
							<div class="row">
								<div class="col-11" style="height: 20px; display: inline-block;">
									<h5>
										<strong>建立店家及菜單</strong>
									</h5>
								</div>
								<div class="col-1" style="height: 20px; display: inline-block;">
									<a href="<%=request.getContextPath()%>/shop/select_page.jsp"><strong>回店家列表</strong></a>
								</div>
							</div>
						</div>

						<!-- ============== Card Body ============== -->
						<!-- ======= 新增店家Form ======= -->
						<div class="col-lg-6">
							<div class="card">
								<div class="card-body">
									<h5 class="card-title">店家資訊</h5>

									<!-- Multi Columns Form -->
									<form >
										<div class="row mb-3">
											<label for="inputText" class="col-sm-2 col-form-label">店家名稱</label>
											<div class="col-sm-10">
												<input type="text" class="form-control">
											</div>
										</div>
										<div class="row mb-3">
											<label class="col-sm-2 col-form-label">店家類型</label>
											<div class="col-sm-10">
												<select class="form-select"
													aria-label="Default select example">
													<option selected>選擇店家類型</option>
													<option value="1">飲料</option>
													<option value="2">中式</option>
													<option value="3">異國</option>
													<option value="4">小吃</option>
													<option value="5">素食</option>
													<option value="6">其他</option>
												</select>
											</div>
										</div>
										<div class="row mb-3">
											<label for="inputText" class="col-sm-2 col-form-label">地址</label>
											<div class="col-sm-10">
												<input type="text" class="form-control">
											</div>
										</div>
										<div class="row mb-3">
											<label for="inputText" class="col-sm-2 col-form-label">電話</label>
											<div class="col-sm-10">
												<input type="text" class="form-control">
											</div>
										</div>
										<div class="row mb-3">
											<label for="inputText" class="col-sm-2 col-form-label">網站</label>
											<div class="col-sm-10">
												<input type="text" class="form-control">
											</div>
										</div>
										<div class="row mb-3">
											<label for="inputText" class="col-sm-2 col-form-label">低消</label>
											<div class="col-sm-10">
												<input type="text" class="form-control">
											</div>
										</div>
										<div class="row mb-3">
											<label for="inputText" class="col-sm-2 col-form-label">圖片</label>
											<div class="col-sm-10">
												<input type="FILE" class="form-control">
											</div>
										</div>
										<div class="row mb-3">
											<label for="inputText" class="col-sm-2 col-form-label">圖片</label>
											<div class="col-sm-10">
												<input type="FILE" class="form-control">
											</div>
										</div>
										<div class="row mb-3">
											<label for="inputText" class="col-sm-2 col-form-label">圖片</label>
											<div class="col-sm-10">
												<input type="FILE" class="form-control">
											</div>
										</div>
									</form>
									<!-- End Multi Columns Form -->
								</div>
							</div>
						</div>

						<!-- 					<img id="pic1" style="width: 100px;"> <img id="pic2" -->
						<!-- 						style="width: 100px;"> <img id="pic3" style="width: 100px;"> -->


						<!-- ====================== 菜單 ====================== -->
						<!-- ======= 新增菜單Form ======= -->
						<div class="col-lg-5">
							<div class="card">
								<div class="card-body">
									<h5 class="card-title">菜單資訊</h5>
									<!-- Table with hoverable rows -->
									<table class="table table-hover" style="text-align: center;">
										<thead>
											<tr>
												<th scope="col" width="200px">編號</th>
												<th scope="col" width="500px">品項</th>
												<th scope="col" width="200px">單價</th>
												<th scope="col" width="500px"></th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<th scope="row">1</th>
												<td><input type="text" name="remark" size="10" value=></td>
												<td><input type="text" name="remark" size="10" value=></td>
												<td><input type="submit"
													class="btn btn-secondary btn-sm" value="刪除"></td>
											</tr>
											<tr>
												<th scope="row">2</th>
												<td><input type="text" name="remark" size="10" value=></td>
												<td><input type="text" name="remark" size="10" value=></td>
												<td><input type="submit"
													class="btn btn-secondary btn-sm" value="刪除"></td>
											</tr>
											<tr>
												<th scope="row">3</th>
												<td><input type="text" name="remark" size="10" value=></td>
												<td><input type="text" name="remark" size="10" value=></td>
												<td><input type="submit"
													class="btn btn-secondary btn-sm" value="刪除"></td>
											</tr>
											<tr>
												<th scope="row">4</th>
												<td><input type="text" name="remark" size="10" value=></td>
												<td><input type="text" name="remark" size="10" value=></td>
												<td><input type="submit"
													class="btn btn-secondary btn-sm" value="刪除"></td>
											</tr>
											<tr>
												<th scope="row">5</th>
												<td><input type="text" name="remark" size="10" value=></td>
												<td><input type="text" name="remark" size="10" value=></td>
												<td><input type="submit"
													class="btn btn-secondary btn-sm" value="刪除"></td>
											</tr>
											<tr>
												<th scope="row">6</th>
												<td><input type="text" name="remark" size="10" value=></td>
												<td><input type="text" name="remark" size="10" value=></td>
												<td><input type="submit"
													class="btn btn-secondary btn-sm" value="刪除"></td>
											</tr>
											<tr>
												<th scope="row">7</th>
												<td><input type="text" name="remark" size="10" value=></td>
												<td><input type="text" name="remark" size="10" value=></td>
												<td><input type="submit"
													class="btn btn-secondary btn-sm" value="刪除"></td>
											</tr>
											<tr>
												<th scope="row">8</th>
												<td><input type="text" name="remark" size="10" value=></td>
												<td><input type="text" name="remark" size="10" value=></td>
												<td><input type="submit"
													class="btn btn-secondary btn-sm" value="刪除"></td>
											</tr>
											<tr>
												<th scope="row">9</th>
												<td><input type="text" name="remark" size="10" value=></td>
												<td><input type="text" name="remark" size="10" value=></td>
												<td><input type="submit"
													class="btn btn-secondary btn-sm" value="刪除"></td>
											</tr>
											<tr>
												<th scope="row">10</th>
												<td><input type="text" name="remark" size="10" value=></td>
												<td><input type="text" name="remark" size="10" value=></td>
												<td><input type="submit"
													class="btn btn-secondary btn-sm" value="刪除"></td>
											</tr>
										</tbody>
									</table>
									<!-- End Table with hoverable rows -->
								</div>
							</div>
						</div>
					</div>
					<!-- 提交區 -->
					<div class="text-center">
						<input type="hidden" name="action" value="insert"> <input
							type="submit" class="btn btn-primary btn-sm" value="送出新增">
					</div>
		</main>
		<!-- ======= 內容結束 ======= -->
	</div>
	<!-- ======= Footer ======= -->
	<%@ include file="/design/frontfooter.jsp"%>
	<!-- ======= js ======= -->
	<%@ include file="/design/frontjs.jsp"%>
</body>

</html>