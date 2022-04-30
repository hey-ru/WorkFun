<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.menu.model.*"%>
<%@ page import="com.shop.model.*"%>

<%
MenuService menuService = new MenuService();
List<MenuVO> list = menuService.getAll();
pageContext.setAttribute("list", list);
%>

<html>
<head>
<%@ include file="/design/frontmetacss.jsp"%>
</head>

<body style="width:100vw;">
	<div class="wrapper">

		<%@ include file="/design/frontheader.jsp"%>

		<!-- ====================== 內容開始 ====================== -->
<main id="main" class="main">
	<div class="row">
				<!-- ============== Card Header ============== -->
				<div class="card-header py-3" style="background-color: #b0c4de">
					<div class="row">
						<div class="col-10" style="height: 20px; display: inline-block;">
							<h5>
								<strong>建立店家及菜單</strong>
							</h5>
						</div>
						<div class="col-2" style="height: 20px; display: inline-block;">
							<a href="<%=request.getContextPath()%>/shop/listAllShop.jsp"><strong>回店家列表</strong></a>
						</div>
					</div>
				</div>

				<!-- ============== Card Body ============== -->
				<!-- ======= 新增店家Form ======= -->
	<div class="col-md-7">
		<div class="card">
		<div class="card-body">
		<h5 class="card-title">店家資訊</h5>

							<!-- Multi Columns Form -->
		<FORM METHOD="post" style="padding:0px 20px;" enctype="multipart/form-data" ACTION="<%=request.getContextPath()%>/shop/ShopServlet" name="form1">
			<div class="row mb-3">
				<label for="inputText" class="col-sm-2 col-form-label">店家名稱<font color=red><b>*</b></font></label>
				<div class="col-sm-5">
					<input type="text"  name="shop_name" class="form-control"value="${param.shop_name}"/>
				</div>
				<div class="col-sm-4">${errorMsgs.shop_name}</div>
			</div>
			<div class="row mb-3">
				<label class="col-sm-2 col-form-label">店家類型</label>
				<div class="col-sm-3">
					<select class="form-select" name="shop_type" aria-label="Default select example">
						<option value="5" ${(param.shop_type=="")? 'selected':'' } >請選擇類型
						<option value="0" ${(param.shop_type==0)? 'selected':'' } >飲料
						<option value="1" ${(param.shop_type==1)? 'selected':'' } >中式
						<option value="2" ${(param.shop_type==2)? 'selected':'' } >異國
						<option value="3" ${(param.shop_type==3)? 'selected':'' } >小吃
						<option value="4" ${(param.shop_type==4)? 'selected':'' } >素食
						<option value="5" ${(param.shop_type==5)? 'selected':'' } >其他
					</select>
				</div>
				<div class="col-sm-6">${errorMsgs.shop_type}</div>
			</div>
			<div class="row mb-3">
				<label for="inputText" class="col-sm-2 col-form-label">地址</label>
				<div class="col-sm-3">
					<input class="js-demeter-tw-zipcode-selector" data-city="#city" data-dist="#dist" name="placecode" style="width:120px;" placeholder="請輸入郵遞區號" />
				</div>
				<div class="col-sm-6">
					<select id="city" name="city" placeholder="請選擇縣市"></select>
					<select id="dist" name="dist" placeholder="請選擇鄉鎮區"></select>
				</div>
			</div>
				<div class="row mb-3">
					<div class="col-sm-2"></div>
					<div class="col-sm-5">
						<input type="TEXT" name="address" class="form-control" placeholder="請輸入接續地址" value="${param.address}"/>
					</div>
					<div class="col-sm-4">${errorMsgs.address}</div>
				</div>
				<div class="row mb-3">
					<label for="inputText" class="col-sm-2 col-form-label">電話<font color=red><b>*</b></font></label>
					<div class="col-sm-5">
						<input type="text" class="form-control" name="tel" value="${param.tel}">
					</div>
					<div class="col-sm-4">${errorMsgs.tel}</div>
				</div>
				<div class="row mb-3">
					<label for="inputText" class="col-sm-2 col-form-label">網站</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" name="website" value="${param.website}">
					</div>
					<div class="col-sm-4">${errorMsgs.website}</div>			
				</div>
				<div class="row mb-3">
					<label for="inputText" class="col-sm-2 col-form-label">低消</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" name="min_amt" value="${param.min_amt}">
					</div>
					<div class="col-sm-4">${errorMsgs.min_amt}</div>
				</div>
				<div class="row mb-3">
					<label for="inputText" class="col-sm-2 col-form-label">圖片</label>
					<div class="col-sm-5">
						<input type="FILE" class="form-control" name="shop_img1" 
						oninput="pic1.src=window.URL.createObjectURL(this.files[0])" value="${param.shop_img1}">
					</div>
					<div class="col-sm-4">${errorMsgs.shop_img1}</div>
				</div>
				
				<div class="row mb-3">
					<label for="inputText" class="col-sm-2 col-form-label">圖片</label>
					<div class="col-sm-5">
						<input type="FILE" class="form-control" name="shop_img2" 
						oninput="pic2.src=window.URL.createObjectURL(this.files[0])" value="${param.shop_img2}">
					</div>
					<div class="col-sm-4">${errorMsgs.shop_img2}</div>
				</div>
				
				<div class="row mb-3">
					<label for="inputText" class="col-sm-2 col-form-label">圖片</label>
					<div class="col-sm-5">
						<input type="FILE" class="form-control" name="shop_img3" 
						oninput="pic3.src=window.URL.createObjectURL(this.files[0])" value="${param.shop_img3}">
					</div>
					<div class="col-sm-4">${errorMsgs.shop_img2}</div>
				</div>
				<div class="row mb-3">
					<div class="col-sm-4" style="height:150px;padding: 0px 5px;">
					<img id="pic1" style="max-width:100%;">
					</div>
					<div class="col-sm-4" style="height:150px;padding: 0px 5px;">
					<img id="pic2" style="max-width:100%;">
					</div>
					<div class="col-sm-3" style="height:150px;padding: 0px 5px;">
					<img id="pic3" style="max-width:100%;">
					</div>				
				</div>
<!-- 				<input type="hidden" name="action" value="insert"> -->
<!-- 				<input type="submit" value="送出新增"> -->
								
		</FORM>
							<!-- End Multi Columns Form -->
		</div>
		</div>
	</div>

				<!-- ====================== 菜單 ====================== -->
				<!-- ======= 新增菜單Form ======= -->
	<div class="col-md-4">
		<div class="card">
		<div class="card-body">
							<h5 class="card-title">上傳菜單</h5>

							<FORM method="post" enctype="multipart/form-data"
								action="<%=request.getContextPath()%>/menu/uploadcsvservlet" name="form2">

								<input type="file" class="form-control" name="csvfile"
									accept=".csv" value="${param.shop_id}"> 									
								<input type="hidden" name="action" value="uploadcsv">
								<!-- 						<input type="hidden" name="shop_id" value="102">  -->
								<!-- 						<input type="submit" value="上傳菜單"> -->
							</FORM>



							<!-- Table with hoverable rows -->
							<!-- 							<table class="table table-hover" style="text-align: center;"> -->
							<!-- 								<thead> -->
							<!-- 									<tr> -->
							<!-- 										<th scope="col" width="200px">編號</th> -->
							<!-- 										<th scope="col" width="500px">品項</th> -->
							<!-- 										<th scope="col" width="200px">單價</th> -->
							<!-- 										<th scope="col" width="500px"></th> -->
							<!-- 									</tr> -->
							<!-- 								</thead> -->
							<!-- 								<tbody> -->
							<!-- 									<tr> -->
							<!-- 										<th scope="row">1</th> -->
							<!-- 										<td><input type="text" name="remark" size="10" value=></td> -->
							<!-- 										<td><input type="text" name="remark" size="10" value=></td> -->
							<!-- 										<td><input type="submit" class="btn btn-secondary btn-sm" -->
							<!-- 											value="刪除"></td> -->
							<!-- 									</tr> -->
							<!-- 								</tbody> -->
							<!-- 							</table> -->
							<!-- End Table with hoverable rows -->
			</div>
			</div>
		</div>
	</div>
			<!-- 提交區 -->
			<div class="text-center">
				<input type="hidden" name="action" value="insertShopMenu"> <input
					type="submit" class="btn btn-primary btn-sm" onclick="submitForms()" value="送出新增">
			</div>

</main>
		<!-- ======= 內容結束 ======= -->
	</div>
	<!-- ======= Footer ======= -->
	<%@ include file="/design/frontfooter.jsp"%>
	<!-- ======= js ======= -->
	<%@ include file="/design/frontjs.jsp"%>
	
	<script>
	submitForms = function(){
    document.forms["form1"].submit();
    document.forms["form2"].submit();
	}
	</script>
	
</body>

</html>