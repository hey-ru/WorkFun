<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.menu.model.*"%>

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
					
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/menu/addmenubyshop">
							<table class="table table-hover" style="text-align: center;">
								<tr>
									<td>店家名稱:</td>
									<td>${param.shop_id}</td>
								</tr>
								<tr>
									<td>品項:</td>
									<td><input type="TEXT" name="item" size="45"
										value="${param.item}" /></td>
									<td>${errorMsgs.item}</td>
								</tr>
								<tr>
									<td>價格:</td>
									<td><input type="TEXT" name="price" size="45"
										value="${param.price}" /></td>
									<td>${errorMsgs.price}</td>
								</tr>
							</table>
							<br> <input type="hidden" name="action" value="insert">
							<input type="hidden" name="shop_id" value="${param.shop_id}">
							<input type="submit" value="送出新增">
						</FORM>
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
</body>

</html>