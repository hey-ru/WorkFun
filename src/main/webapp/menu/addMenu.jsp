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

/* body { */
/*     overflow:hidden; */
/* } */
			td,
			th {
				width: 120px;
				text-align: center;
			}
			
			.odd {
				background-color: lightsalmon;
			}
			
			#addBox {
				display: none;
				width: 320px;
				float: left;
				background-color: lightslategrey;
			}
			
			#updBox {
				display: none;
				width: 320px;
				float: left;
				background-color: lightslategrey;
			}
			
			#addBox table,#updBox table{
				margin: 0 auto;
			}
			
			#addBox th,#updBox th{

				color: black;
			}
		</style>

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

				<!-- 新增BUTTON -->
					<input type="button" value="新增一筆" id="add" />
					<div class="list">
						<FORM >
							<table class="table table-hover" style="text-align: center;">
								<thead>
								<tr>
										<th>ID</th>
										<th>品項</th>
										<th>價格</th>
										<th></th>
								</tr>
								</thead>
								<tbody>
									<tr>
									    <th>範例</th>
										<td>珍珠奶茶</td>
										<td>55</td>
										<td>
<!-- 											<a href="#">修改</a> -->
<!-- 											<a href="#">刪除</a> -->
										</td>
									</tr>
								</tbody>
							</table>
						</FORM>
						</div>
						
						<div id="addBox">
						<form METHOD="post" ACTION="<%=request.getContextPath()%>/menu/addmenubyshop">
							<table>
								<tr>
									<th colspan="2">新增菜單</th>
								</tr>
								<tr>
									<td>品項:</td>
									<td><input type="text" name="item"
											value="${param.item}" /></td>
								</tr>
								<tr>
									<td>金額:</td>
									<td><input type="number" name="price"
											value="${param.price}" /></td>
								</tr>
								<tr>
									<td colspan="2">
										  <input type="hidden" name="action" value="insert"> 
										   <input type="hidden" name="shop_id" value="${param.shop_id}"> 
											<input type="submit" value="確定">
<!-- 											<input type="reset" value="新增一個" /> -->
											<input type="reset" value="取消" />
									</td>
								</tr>
							</table>
						</form>
					</div>



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

	<script>
			$(function() {
   
   
				// 顏色
				$(".list tr:odd").addClass("odd");

				// 多選框的點選操作
				// 預設樣式
				$(".list th:first").append("<label for='check' id='show'>全選</label>");
				$("#check").click(function() {
   
   
					if(this.checked) {
   
   
						$("#show").replaceWith("<label for='check' id='show'>已全選</label>");
						$("tbody :checkbox").prop("checked", true);
					} else {
   
   
						$("tbody :checkbox").prop("checked", false);
						$("#show").replaceWith("<label for='check' id='show'>已取消全選</label>");
					}

				});

				// 點選新增時顯示新增列表
				$("input:button:first").click(function() {
   
   
					$("#addBox").slideDown(1000);
				});

				// 點取消新增時隱藏新增列表
				$("#addBox input:reset:last-child").click(function() {
   
   
					$("#addBox").slideUp(1000);
				});

				// 增加  點選事件
				$("#addBox input:reset:first-child").click(function() {
   
   
					$(".list tr:odd").removeClass("odd"); // 新增前去除顏色
					// 先複製一份tbody下的tr
					var trDom = $(".list tbody tr:first").clone(true);
					$(".list tbody").append(trDom); // 寫入到tbody內

					// 將輸入的內容寫入到列表
					var textDom = $("#addBox input:text"); // 獲取文字框
					var tdDom = $(".list tbody tr:last td");
					for(var i = 0; i < textDom.length; i++) {
   
   
						var content = textDom.eq(i).val();
						if(content == ""){
   
   
							tdDom.parents("tr").remove();// 如果輸入的有空值 刪除剛才新增的內容
							$(".list tr:odd").addClass("odd"); // 新增顏色
							alert("請輸入完整");
							return;// 提示輸入完整並結束程式
						}
						tdDom.eq(i + 1).text(content);
					}

					$(".list tr:odd").addClass("odd"); // 新增後新增顏色
				});

				// 刪除操作
				$(".list tbody td a:last-child").click(function() {
   
   
					var isDel = confirm("確定真的要刪除嗎?");
					if(isDel) {
   
   
						if($(".list tr").length > 2) {
   
   
							$(".list tr:odd").removeClass("odd"); // 刪除前去除顏色
							$(this).parents("tr").remove();
							$(".list tr:odd").addClass("odd"); // 刪除後新增顏色
						} else {
   
   
							alert("最少保留一條資料");
						}
					}
				});

				// 修改點選事件
				$(".list tbody td a:first-child").click(function() {
   
   
					// 點選修改時顯示修改列表
					$("#updBox").slideDown(1000);
					// 取消事件 --> 取消上一次修改的點選事件
					$("#updBox input:reset:first-child").off("click");
					var trDom = $(this).parents("tr");// 修改當前的tr
					update(trDom); // 繫結修改事件
				});
				
				// 點選取消修改之後也隱藏修改列表
				$("#updBox input:reset:last-child").click(function(){
   
   
					$("#updBox").slideUp(1000);
				});
				
			});

			// 修改操作
			function update(trDom) {
   
   
				$("#updBox input:reset:first-child").click(function() {
   
   
					// 設定修改點選事件
					var textDom = $("#updBox input:text"); // 獲取文字框
					for(var i = 0; i < textDom.length; i++) {
   
   
						// 將文字框的內容替換到修改的地方-->trDom
						var content = textDom.eq(i).val();
						if(content == ""){
   
   
							alert("請輸入完整");
							return; // 提示輸入完整並結束程式
						}
						trDom.find("td").eq(i + 1).text(content);// 修改tr下的td內的內容
					}
					// 修改完畢之後隱藏修改列表
					$("#updBox").slideUp(1000);
				});
			}
		</script>
		<script src="js/jquery-3.3.1.js"></script>
</body>

</html>