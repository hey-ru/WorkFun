<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html lang="zh-TW">

<head>
<%@ include file="/design/frontmetacss.jsp"%>
    <style>
        th{
        vertical-align: middle;
            text-align: center;
        height:50px;
        }
        td{
        vertical-align: middle;
            text-align: center;
        height:130px;
        }
        .table-responsive {
    	overflow-x: visible;
}
    </style>
</head>

<body>
	<div class="wrapper">
		<!-- ======= Header ======= -->
		<%@ include file="/design/frontheader.jsp"%>

		<!-- ======= 內容開始 ======= -->
		<main id="main" class="main">
			<div class="card shadow mb-4">
<!--                    <div style="height: var(--header-height); margin-bottom:10px;"></div> -->
				<!-- ============== Card Header ============== -->
				<div class="card-header py-3" style="background-color: #99CCCC">
					<div class="row">
						<div class="col-9" style="height: 20px; display: inline-block;">
							<h5>
								<strong>店家資訊</strong>
							</h5>
						</div>
						<div class="col-3" style="height: 20px; display: inline-block;">
							<a href="<%=request.getContextPath()%>/shop/listAllShop.jsp"><strong>回店家列表</strong></a>
						</div>
					</div>
				</div>

						<div class="row">
							<div class="col-sm-12">
								<table class="table table-bordered dataTable" id="dataTable" role="grid"
									aria-describedby="dataTable_info" style="width: 100%;cellspacing:0;">
									<thead>
									<tr role=" row">
										<th class="sorting sorting_asc" tabindex="0"
											aria-controls="dataTable" rowspan="1" colspan="1"
											aria-sort="ascending"
											aria-label="Name: activate to sort column descending"
											style="width: 100px;">名稱</th>
										<th class="sorting" tabindex="0" aria-controls="dataTable"
											rowspan="1" colspan="1"
											aria-label="Position: activate to sort column ascending"
											style="width: 60px;">類型</th>
										<th class="sorting" tabindex="0" aria-controls="dataTable"
											rowspan="1" colspan="1"
											aria-label="Position: activate to sort column ascending"
											style="width: 160px;">店家地址</th>
										<th class="sorting" tabindex="0" aria-controls="dataTable"
											rowspan="1" colspan="1"
											aria-label="Office: activate to sort column ascending"
											style="width: 80px;">店家電話</th>
										<th class="sorting" tabindex="0" aria-controls="dataTable"
											rowspan="1" colspan="1"
											aria-label="Age: activate to sort column ascending"
											style="width: 50px;">店家網站</th>
<!-- 										<th class="sorting" tabindex="0" aria-controls="dataTable" -->
<!-- 											rowspan="1" colspan="1" -->
<!-- 											aria-label="Office: activate to sort column ascending" -->
<!-- 											style="width: 50px;">低消金額</th> -->
										<th class="sorting" tabindex="0" aria-controls="dataTable"
											rowspan="1" colspan="1"
											aria-label="Salary: activate to sort column ascending"
											style="width: 50px;">照片1</th>
										<th class="sorting" tabindex="0" aria-controls="dataTable"
											rowspan="1" colspan="1"
											aria-label="Salary: activate to sort column ascending"
											style="width: 50px;">照片2</th>
										<th class="sorting" tabindex="0" aria-controls="dataTable"
											rowspan="1" colspan="1"
											aria-label="Salary: activate to sort column ascending"
											style="width: 50px;">照片3</th>
										<th class="sorting" tabindex="0" aria-controls="dataTable"
											rowspan="1" colspan="1"
											aria-label="Salary: activate to sort column ascending"
											style="width: 50px;">菜單</th>
										<th class="sorting" tabindex="0" aria-controls="dataTable"
											rowspan="1" colspan="1"
											aria-label="Salary: activate to sort column ascending"
											style="width: 50px;">修改</th>
										<th class="sorting" tabindex="0" aria-controls="dataTable"
											rowspan="1" colspan="1"
											aria-label="Salary: activate to sort column ascending"
											style="width: 50px;">揪團</th>
									</tr>
									
									</thead>

									<tbody>
										
										<tr>
<%-- 											<td>${shopVO.shop_id}</td> --%>
											<td>${shopVO.shop_name}</td>
											<td>
											<c:choose>
											    <c:when test="${shopVO.shop_type == 0}">
											       	飲料
											    </c:when>
											    <c:when test="${shopVO.shop_type == 1}">
											        中式
											    </c:when>
											    <c:when test="${shopVO.shop_type == 2}">
											        異國
											    </c:when>
											    <c:when test="${shopVO.shop_type == 3}">
											        小吃
											    </c:when>
											    <c:when test="${shopVO.shop_type == 4}">
											        素食
											    </c:when>
											    <c:when test="${shopVO.shop_type == 5}">
											        其他
											    </c:when>
											</c:choose>
											</td>
											<td>${shopVO.address}</td>
											<td>${shopVO.tel}</td>
											<td><a href="${shopVO.website}">link</a></td>
<%-- 											<td>${shopVO.min_amt}</td> --%>
											<td><img
												src="<%=request.getContextPath()%>/util/DBGifReader?id_key=shop_id&id=${shopVO.shop_id}&table=shop&pic=shop_img1"
												style="max-height: 100%;"></td>
											<td><img
												src="<%=request.getContextPath()%>/util/DBGifReader?id_key=shop_id&id=${shopVO.shop_id}&table=shop&pic=shop_img2"
												style="max-height: 100%;"></td>
											<td><img
												src="<%=request.getContextPath()%>/util/DBGifReader?id_key=shop_id&id=${shopVO.shop_id}&table=shop&pic=shop_img3"
												style="max-height: 100%;"></td>


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
											<td>
												<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/groupbuy/AddGBServlet"
												style="margin-bottom: 0px;">
												<input type="submit" class="btn-info btn-lg" value="揪這家">
												<input type="hidden" name="shop_id"
													value="${shopVO.shop_id}">
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
									</tbody>
								</table>

								
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

</body>

</html>