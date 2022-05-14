<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.menu.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
List<MenuVO> list = (List<MenuVO>) request.getAttribute("menuList");
// int itemsPerPage = 10; //設定每頁頁數

int menuNumber = 1;
%>

<html>
<head>
<%@ include file="/design/frontmetacss.jsp"%>
<style>

.main {
	overflow-x: hidden;
}

  th{
        vertical-align: middle;
            text-align: center;
        }
        td{
        vertical-align: middle;
            text-align: center;
        }

     .table table-dark {
    	overflow-x:hidden;
		}


</style>

</head>

<body>
	<div class="wrapper">

		<%@ include file="/design/frontheader.jsp"%>

		<!-- ====================== 內容開始 ====================== -->
		<main id="main" class="main">
			<div class="card shadow mb-4">
				<!-- ============== Card Header ============== -->
				<div class="card-header py-3" style="background-color: #b0c4de">
					<div class="row">
						<div class="col-11" style="height: 20px; display: inline-block;">
							<h5>
								<strong>查詢店家菜單</strong>
							</h5>
						</div>
						<div class="col-1" style="height: 20px; display: inline-block;">
							<a href="<%=request.getContextPath()%>/shop/listAllShop.jsp"><strong>回店家列表</strong></a>
						</div>
					</div>
				</div>

				<!-- ============== Card Body ============== -->
				<!-- 新增菜單請求 -->
						<div class="col-1">
							<a
								href="${pageContext.request.contextPath}/menu/addMenu.jsp?shop_id=${param.shop_id}">
								<button type="button" class="btn btn-info">新增項目</button>
							</a>
						</div>
				
				
				<div class="card" style="width: 700px; align-self: center;margin: 10px; color:white; background-color: gray;">
					<div class="card-header" style="text-align: center; margin: 20px;" > <h3>${param.shop_name} M e n u</h3> </div>

					<div class="card-body row">
						<div class="col-12" >
							<table class="table table-dark" >
								<!-- ========================= 表頭 ========================= -->
								<thead>
									<tr>
										<th scope="col">項次</th>
										<th scope="col">品項</th>
										<th scope="col">價格</th>
										<th scope="col">狀態</th>
										<th scope="col"></th>

									</tr>
								</thead>

								<c:forEach var="menu" items="${menuList}">
									<tr>
										<td><%=menuNumber++%></td>
										<td><c:out value="${menu.item}" /></td>
										<td><c:out value="${menu.price}" /></td>
										<td><c:if test="${menu.is_item==1}">
												<c:out value="上架中" />
											</c:if> <c:if test="${menu.is_item==0}">
												<c:out value="已下架" />
											</c:if></td>
										<td>
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/menu/updatemenubyshop">
												<input type="submit" class="btn btn-secondary btn-sm" 
												data-toggle="modal" data-target="#exampleModalCenter" value="編輯">
												<input type="hidden" name="menu_id" value="${menu.menu_id}">
												<input type="hidden" name="action"
													value="getMenuItem_For_Update">
											</FORM>
										</td>
									</tr>
								</c:forEach>
							</table>
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
</body>

</html>