<%@page import="com.equipment.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%
EquipmentService equipSvc = new EquipmentService();
List<EquipmentVO> list = equipSvc.getAll();
pageContext.setAttribute("list", list);
int itemsPerPage = 10;
%>

<!DOCTYPE html>
<html lang="zh-TW">

<head>
<%@ include file="/design/frontmetacss.jsp"%>

<style>
th {
	vertical-align: middle;
	text-align: center;
	height: 50px;
}

td {
	vertical-align: middle;
	text-align: center;
	height: 130px;
}

.table-responsive {
	overflow-x: visible;
}
</style>
</head>

<body>
	<div class="wrapper">
		<!-- ======= Header ======= -->



		<!-- ======= 內容開始 ======= -->


		<div class="row">
			<div class="col-sm-12">
				<table class="table table-bordered dataTable" id="dataTable"
					width="100%" cellspacing="0" role="grid"
					aria-describedby="dataTable_info" style="width: 100%;">
					<thead>
						<tr role=" row">
							<th class="sorting sorting_asc" tabindex="0"
								aria-controls="dataTable" rowspan="1" colspan="1"
								aria-sort="ascending"
								aria-label="Name: activate to sort column descending"
								style="width: 100px;">器材編號</th>

							<th class="sorting" tabindex="0" aria-controls="dataTable"
								rowspan="1" colspan="1"
								aria-label="Position: activate to sort column ascending"
								style="width: 60px;">器材名稱</th>

							<th class="sorting" tabindex="0" aria-controls="dataTable"
								rowspan="1" colspan="1"
								aria-label="Position: activate to sort column ascending"
								style="width: 160px;">金額</th>

							<th class="sorting" tabindex="0" aria-controls="dataTable"
								rowspan="1" colspan="1"
								aria-label="Office: activate to sort column ascending"
								style="width: 80px;">狀態</th>

							<th class="sorting" tabindex="0" aria-controls="dataTable"
								rowspan="1" colspan="1"
								aria-label="Age: activate to sort column ascending"
								style="width: 50px;">器材介紹</th>

							<th class="sorting" tabindex="0" aria-controls="dataTable"
								rowspan="1" colspan="1"
								aria-label="Office: activate to sort column ascending"
								style="width: 50px;">器材規格</th>

							<th class="sorting" tabindex="0" aria-controls="dataTable"
								rowspan="1" colspan="1"
								aria-label="Salary: activate to sort column ascending"
								style="width: 50px;">圖片1</th>

							<th class="sorting" tabindex="0" aria-controls="dataTable"
								rowspan="1" colspan="1"
								aria-label="Salary: activate to sort column ascending"
								style="width: 50px;">圖片2</th>

							<th class="sorting" tabindex="0" aria-controls="dataTable"
								rowspan="1" colspan="1"
								aria-label="Salary: activate to sort column ascending"
								style="width: 50px;">圖片3</th>

							<th class="sorting" tabindex="0" aria-controls="dataTable"
								rowspan="1" colspan="1"
								aria-label="Salary: activate to sort column ascending"
								style="width: 50px;">修改</th>

						</tr>

					</thead>


					<%@ include file="/design/page1.file"%>
					<tbody>

						<c:forEach var="shopVO" items="${list}" begin="<%=pageIndex%>"
							end="<%=pageIndex+rowsPerPage-1%>">

							<tr>
								<td>${equipmentVO.equipmentId}</td>

								<td>${equipmentVO.eqName}</td>

								<td>${equipmentVO.price}</td>

								<td><c:choose>
										<c:when test="${equipmentVO.eqStatus == 0}">
											       	上架
											    </c:when>
										<c:when test="${equipmentVO.eqStatus == 1}">
											        未歸還器材
											    </c:when>
										<c:when test="${equipmentVO.eqStatus == 2}">
											        送修中
											    </c:when>
										<c:when test="${equipmentVO.eqStatus == 3}">
											        下架
											    </c:when>
									</c:choose></td>
									
									<td>${equipmentVO.introduction}</td>
									
									<td>${equipmentVO.spec}</td>
								
								<td><img
									src="<%=request.getContextPath()%>/util/DBGifReader?id_key=equipmentId&id=${equipmentVO.equipmentId}&table=equipment&pic=img1"
									style="max-height: 100%;"></td>
								
								<td><img
									src="<%=request.getContextPath()%>/util/DBGifReader?id_key=equipmentId&id=${equipmentVO.equipmentId}&table=equipment&pic=img2"
									style="max-height: 100%;"></td>
								
								<td><img
									src="<%=request.getContextPath()%>/util/DBGifReader?id_key=equipmentId&id=${equipmentVO.equipmentId}&table=equipment&pic=img3"
									style="max-height: 100%;"></td>

								<td>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/equipment/equipment.do"
										style="margin-bottom: 0px;">
										<input type="submit" value="修改"> <input type="hidden"
											name="equipmentId" value="${equipmentVO.equipmentId}"> <input
											type="hidden" name="action" value="getOne_For_Update">
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

				<%@ include file="/design/page2.file"%>

			</div>
		</div>

	</div>
	</div>
	</div>
	</div>

	<!-- ======= 內容結束 ======= -->
</body>
</html>