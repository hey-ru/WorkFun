<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="java.util.*"%>

<jsp:useBean id="listByCompositeQuery" scope="request" type="java.util.List<ShopVO>" />
<%
String yourServlet = "/shop/ShopServlet"; 
int itemsPerPage = 10;
%>


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
        height:100px;
        }
  body {
	overflow-x:hidden;
}
    </style>
</head>

<body>
	<div class="wrapper">
		<!-- ======= Header ======= -->
		<%@ include file="/design/frontheader.jsp"%>

		<!-- ======= 內容開始 ======= -->
	<main id="main" class="main">
			<div class="card shadow">

				<!-- ============== Card Header ============== -->
				<div class="card-header py-3" style="background-color: #99CCCC">
					<div class="row">
						<div class="col-9" style="height: 20px; display: inline-block;">
							<h5>
								<strong>店家資訊</strong>
							</h5>
						</div>
						<div class="col-3" style="height: 20px; display: inline-block;">
							<a href="<%=request.getContextPath()%>/shop/listAllShop.jsp"><strong>回揪團列表</strong></a>
						</div>
					</div>
				</div>
						<div class="row">
							<div class="col-10" style="height: 60px; display: inline-block;">
								<form class="my-1"  METHOD="post" ACTION="<%=request.getContextPath()%>/shop/ShopServlet" >
									<div class="form-group col-2" style="display: inline-block;">
										<select class="form-select" id="exampleFormControlSelect1"
											style="border: gray solid 2px;" name="shop_type">
											<option value="">選擇店家類型</option>
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
											style="border: gray solid 2px;" name="shop_name">
									</div>
									<input type="hidden" name="action" value="listByCompositeQuery">
									<input type="hidden" name="is_disable" value="0">
									<button type="submit" class="btn btn-dark mb-2 mt-1 col"
										style="display: inline-block;">搜尋</button>

								</form>
							</div>
							<div class="col-2 mt-2" style="left: 0;">
						<a href='${pageContext.request.contextPath}/shop/addShop.jsp'><button
								type="button" class="btn btn-warning btn-lg">新增店家</button></a>
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
											style="width: 180px;">店家地址</th>
										<th class="sorting" tabindex="0" aria-controls="dataTable"
											rowspan="1" colspan="1"
											aria-label="Office: activate to sort column ascending"
											style="width: 90px;">店家電話</th>
										<th class="sorting" tabindex="0" aria-controls="dataTable"
											rowspan="1" colspan="1"
											aria-label="Age: activate to sort column ascending"
											style="width: 40px;">店家網站</th>
										<th class="sorting" tabindex="0" aria-controls="dataTable"
											rowspan="1" colspan="1"
											aria-label="Office: activate to sort column ascending"
											style="width: 40px;">低消金額</th>
										<th class="sorting" tabindex="0" aria-controls="dataTable"
											rowspan="1" colspan="1"
											aria-label="Salary: activate to sort column ascending"
											style="width: 50px;">照片</th>
<!-- 										<th class="sorting" tabindex="0" aria-controls="dataTable" -->
<!-- 											rowspan="1" colspan="1" -->
<!-- 											aria-label="Salary: activate to sort column ascending" -->
<!-- 											style="width: 50px;">照片2</th> -->
<!-- 										<th class="sorting" tabindex="0" aria-controls="dataTable" -->
<!-- 											rowspan="1" colspan="1" -->
<!-- 											aria-label="Salary: activate to sort column ascending" -->
<!-- 											style="width: 50px;">照片3</th> -->
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


									<%@ include file="/design/page1_ByCompositeQuery.file"%>
									<tbody>
		
									<c:forEach var="shopVO" items="${listByCompositeQuery}" begin="<%=pageIndex%>"
										end="<%=pageIndex+rowsPerPage-1%>">
										
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
											<td><a href="${shopVO.website}"  class="nav-link ${(shopVO.website==''||shopVO.website==null)? 'disabled':''}">link</a></td>
											<td>${shopVO.min_amt}</td>
											<td><img
												src="<%=request.getContextPath()%>/util/DBGifReader?id_key=shop_id&id=${shopVO.shop_id}&table=shop&pic=shop_img1"
												style="max-height: 100%;"></td>
<!-- 											<td><img -->
<%-- 												src="<%=request.getContextPath()%>/util/DBGifReader?id_key=shop_id&id=${shopVO.shop_id}&table=shop&pic=shop_img2" --%>
<!-- 												style="max-height: 100%;"></td> -->
<!-- 											<td><img -->
<%-- 												src="<%=request.getContextPath()%>/util/DBGifReader?id_key=shop_id&id=${shopVO.shop_id}&table=shop&pic=shop_img3" --%>
<!-- 												style="max-height: 100%;"></td> -->


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
									</c:forEach>
									</tbody>
								</table>
								<div style="display:inline-block; width:50px;"></div>
								<div style="display:inline-block; margin-bottom:10px;">
										<%@ include file="/design/page2_ByCompositeQuery.file"%>
								</div>
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
	
<script type="text/javascript">
$("tbody tr").css("background-color", function(index) {
    return index%2==0?"rgba(255,255,224,0.5)":"";
});

</script>



</body>

</html>