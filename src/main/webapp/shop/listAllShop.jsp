<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.*"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="java.util.*"%>

<%
ShopService shopSvc = new ShopService();
List<ShopVO> list = shopSvc.getAll();
pageContext.setAttribute("list", list);
int itemsPerPage = 10;
%>
<!DOCTYPE html>
<html lang="zh-TW">

<head>
<%@ include file="/design/frontmetacss.jsp"%>
    <style>
        table {
            vertical-align: middle;
            text-align: center;
        }
    </style>
</head>

<body>
	<div class="wrapper">
		<!-- ======= Header ======= -->
		<%@ include file="/design/frontheader.jsp"%>

		<!-- ======= 內容開始 ======= -->
		<div class="card shadow mb-4">
			<div class="card-header py-3">
				<h6>
					<strong>查詢店家</strong>
				</h6>
			</div>
			<div class="card-body">
				<div class="table-responsive">
					<div id="dataTable_wrapper"
						class="dataTables_wrapper dt-bootstrap4">

						<div class="row">
							<div class="col-10" style="height: 60px; display: inline-block;">
								<form class="my-1">
									<div class="form-group col-2" style="display: inline-block;">
										<select class="form-control" id="exampleFormControlSelect1"
											style="border: gray solid 2px;">
											<option selected>選擇店家類型</option>
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
											style="border: gray solid 2px;">
									</div>
									<button type="submit" class="btn btn-dark mb-2 mt-1 col"
										style="display: inline-block;">搜尋</button>

								</form>
							</div>
							<div class="col-2 mt-2" style="left: 0;">
								<a  href='${pageContext.request.contextPath}/shop/addShop.jsp'><button type="button"
										class="btn btn-warning btn-lg">新增店家</button></a>
							</div>
						</div>

						<div class="row">
							<div class="col-sm-12">
								<table class="table table-bordered dataTable" id="dataTable"
									width="100%" cellspacing="0" role="grid"
									aria-describedby="dataTable_info" style="width: 100%;">

									<tr role=" row">
										<th class="sorting sorting_asc" tabindex="0"
											aria-controls="dataTable" rowspan="1" colspan="1"
											aria-sort="ascending"
											aria-label="Name: activate to sort column descending"
											style="width: 50px;">店家名稱</th>
										<th class="sorting" tabindex="0" aria-controls="dataTable"
											rowspan="1" colspan="1"
											aria-label="Position: activate to sort column ascending"
											style="width: 50px;">店家類型</th>
										<th class="sorting" tabindex="0" aria-controls="dataTable"
											rowspan="1" colspan="1"
											aria-label="Position: activate to sort column ascending"
											style="width: 150px;">店家地址</th>
										<th class="sorting" tabindex="0" aria-controls="dataTable"
											rowspan="1" colspan="1"
											aria-label="Office: activate to sort column ascending"
											style="width: 50px;">店家電話</th>
										<th class="sorting" tabindex="0" aria-controls="dataTable"
											rowspan="1" colspan="1"
											aria-label="Age: activate to sort column ascending"
											style="width: 30px;">店家網站</th>
										<th class="sorting" tabindex="0" aria-controls="dataTable"
											rowspan="1" colspan="1"
											aria-label="Office: activate to sort column ascending"
											style="width: 50px;">低消金額</th>
										<th class="sorting" tabindex="0" aria-controls="dataTable"
											rowspan="1" colspan="1"
											aria-label="Salary: activate to sort column ascending"
											style="width: 60px;">照片1</th>
										<th class="sorting" tabindex="0" aria-controls="dataTable"
											rowspan="1" colspan="1"
											aria-label="Salary: activate to sort column ascending"
											style="width: 60px;">照片2</th>
										<th class="sorting" tabindex="0" aria-controls="dataTable"
											rowspan="1" colspan="1"
											aria-label="Salary: activate to sort column ascending"
											style="width: 60px;">照片3</th>
										<th class="sorting" tabindex="0" aria-controls="dataTable"
											rowspan="1" colspan="1"
											aria-label="Salary: activate to sort column ascending"
											style="width: 60px;">菜單</th>
										<th class="sorting" tabindex="0" aria-controls="dataTable"
											rowspan="1" colspan="1"
											aria-label="Salary: activate to sort column ascending"
											style="width: 60px;">修改</th>
									</tr>


									<%@ include file="/design/page1.file"%>
		
									<c:forEach var="shopVO" items="${list}" begin="<%=pageIndex%>"
										end="<%=pageIndex+rowsPerPage-1%>">



										<tr>
<%-- 											<td>${shopVO.shop_id}</td> --%>
											<td>${shopVO.shop_name}</td>
											<td>${shopVO.shop_type}</td>
											<td>${shopVO.address}</td>
											<td>${shopVO.tel}</td>
											<td><a href="${shopVO.website}">link</a></td>
											<td>${shopVO.min_amt}</td>
											<td><img
												src="<%=request.getContextPath()%>/util/DBGifReader?id_key=shop_id&id=${shopVO.shop_id}&table=shop&pic=shop_img1"
												style="width: 100px;"></td>
											<td><img
												src="<%=request.getContextPath()%>/util/DBGifReader?id_key=shop_id&id=${shopVO.shop_id}&table=shop&pic=shop_img2"
												style="width: 100px;"></td>
											<td><img
												src="<%=request.getContextPath()%>/util/DBGifReader?id_key=shop_id&id=${shopVO.shop_id}&table=shop&pic=shop_img3"
												style="width: 100px;"></td>


											<td>
												<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/menu/selectmenubyshop"
													style="margin-bottom: 0px;">
													<input type="submit" value="查看菜單"
														style="margin-bottom: 0px;"> <input type="hidden"
														name="shop_id" value="${shopVO.shop_id}"> <input
														type="hidden" name="action" value="getmenu">
												</FORM>
											</td>
											<td>
												<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/shop/ShopServlet"
													style="margin-bottom: 0px;">
													<input type="submit" value="修改"> <input
														type="hidden" name="shop_id" value="${shopVO.shop_id}">
													<input type="hidden" name="action"
														value="getOne_For_Update">
												</FORM>
											</td>
											<!-- 			<td> -->
											<%-- <FORM METHOD="post"
                                                                                ACTION="<%=request.getContextPath()%>/emp/emp.do"
                                                                                style="margin-bottom: 0px;"> --%>
											<!-- 			     <input type="submit" value="刪除"> -->
											<%-- <input type="hidden" name="empno"
                                                                                    value="${empVO.empno}"> --%>
											<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
											<!-- 			</td> -->
										</tr>
									</c:forEach>
								</table>
										<%@ include file="/design/page2.file"%>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>

		<!-- ======= 內容結束 ======= -->

	</div>
	<!-- ======= Footer ======= -->
	<%@ include file="/design/frontfooter.jsp"%>
	<!-- ======= js ======= -->
	<%@ include file="/design/frontjs.jsp"%>

</body>

</html>