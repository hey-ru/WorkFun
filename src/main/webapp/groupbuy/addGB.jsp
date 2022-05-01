<%@page import="org.hibernate.annotations.ParamDef"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shop.model.*"%>
<%
ShopService shopSvc = new ShopService();
ShopVO shopVO = shopSvc.getOneShop(Integer.valueOf(request.getParameter("shop_id")));
pageContext.setAttribute("shopVO", shopVO);
%>

<html>
<head>
<%@ include file="/design/frontmetacss.jsp"%>
<style>
body{
overflow-x:hidden;
left:0;
}
</style>
</head>

<body style="width:100vw;">
	<div class="wrapper">

		<%@ include file="/design/frontheader.jsp"%>

		<!-- ====================== 內容開始 ====================== -->
		<div style="height:var(--header-height);"></div>
<main class="main">
	<div class="row">
				<!-- ============== Card Header ============== -->
				<div class="card-header py-3" style="background-color: #b0c4de">
					<div class="row">
						<div class="col-10" style="height: 20px; display: inline-block;">
							<h5>
								<strong>建立揪團單</strong>
							</h5>
						</div>
						<div class="col-1" style="height: 20px; display: inline-block;">
							<a href="<%=request.getContextPath()%>/shop/listAllShop.jsp"><strong>回店家列表</strong></a>
						</div>
					</div>
				</div>

				<!-- ============== Card Body ============== -->

	<div class="col-md-5">
		<div class="card" style="border:0px;">
		<div class="card-body">
		<h5 class="card-title">店家資訊</h5>
		<div style="padding:0px 20px;" >
			<div class="row mb-2">
				<label for="inputText" class="col-sm-4 col-form-label">店家名稱</label>
				<div class="col-sm-8">
					<input type="text"  name="shop_name" value="${shopVO.shop_name}" class="form-control-plaintext" readonly/>
				</div>
			</div>
			<div class="row mb-3">
				<label class="col-sm-4 col-form-label">店家類型</label>
				<div class="col-sm-8">
				<p style="padding-top:6px;">
				<c:choose>
				<c:when test="${shopVO.shop_type == 0}">飲料</c:when><c:when test="${shopVO.shop_type == 1}">中式</c:when>
				<c:when test="${shopVO.shop_type == 2}">異國</c:when><c:when test="${shopVO.shop_type == 3}">小吃</c:when>
				<c:when test="${shopVO.shop_type == 4}">素食</c:when><c:when test="${shopVO.shop_type == 5}">其他</c:when>
				</c:choose>
				</p>						
				</div>
			</div>
			<div class="row mb-3">
					<label for="inputText" class="col-sm-4 col-form-label">地址</label>
					<div class="col-sm-8">
						<input type="TEXT" name="address" value="${shopVO.address}"  class="form-control-plaintext" readonly/>
					</div>
				</div>
				<div class="row mb-3">
					<label for="inputText" class="col-sm-4 col-form-label">電話</label>
					<div class="col-sm-8">
						<input type="text" name="tel" value="${shopVO.tel}" class="form-control-plaintext" readonly>
					</div>
				</div>
				<div class="row mb-3">
					<label for="inputText" class="col-sm-4 col-form-label">網站</label>
					<div class="col-sm-5">
						<a href="${shopVO.website}"  class="nav-link ${(shopVO.website=='')? 'disabled':''}">link</a>
					</div>		
				</div>
				<div class="row mb-3">
					<label for="inputText" class="col-sm-4 col-form-label">低消</label>
					<div class="col-sm-8">
						<input type="text" name="min_amt" value="${shopVO.min_amt}" class="form-control-plaintext" readonly>
					</div>
				</div>
				<div class="row">
				<label for="inputText" class="col-sm-2">圖片</label>
				<!-- Button trigger modal -->	
					 <div class="col-sm-3" style="height:100px;width:100px;">
					 <button type="button" data-bs-toggle="modal" data-bs-target="#pic1" style="border:0px">
					<img src="<%=request.getContextPath()%>/util/DBGifReader?id_key=shop_id&id=${param.shop_id}&table=shop&pic=shop_img1" style="max-height:100%;max-width:100%;">
					</button>
					</div>
					<!-- Modal -->
					<div class="modal fade" id="pic1" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <div class="modal-dialog">
					    <div class="modal-content">
					      <div class="modal-body">
					        <img src="<%=request.getContextPath()%>/util/DBGifReader?id_key=shop_id&id=${param.shop_id}&table=shop&pic=shop_img1" style="max-height:100%;max-width:100%;">
					      </div>
					  </div>
					  </div>
					</div>
					<!-- Modal_end -->
					<!-- Button trigger modal -->	
					 <div class="col-sm-3" style="height:100px;width:100px;">
					 <button type="button" data-bs-toggle="modal" data-bs-target="#pic2" style="border:0px">
					<img src="<%=request.getContextPath()%>/util/DBGifReader?id_key=shop_id&id=${param.shop_id}&table=shop&pic=shop_img2" style="max-height:100%;max-width:100%;">
					</button>
					</div>
					<!-- Modal -->
					<div class="modal fade" id="pic2" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <div class="modal-dialog">
					    <div class="modal-content">
					      <div class="modal-body">
					        <img src="<%=request.getContextPath()%>/util/DBGifReader?id_key=shop_id&id=${param.shop_id}&table=shop&pic=shop_img2" style="max-height:100%;max-width:100%;">
					      </div>
					      </div>
					  </div>
					</div>
					<!-- Modal_end -->
					<!-- Button trigger modal -->	
					 <div class="col-sm-3" style="height:100px;width:100px;">
					 <button type="button" data-bs-toggle="modal" data-bs-target="#pic3" style="border:0px">
					<img src="<%=request.getContextPath()%>/util/DBGifReader?id_key=shop_id&id=${param.shop_id}&table=shop&pic=shop_img3" style="max-height:100%;max-width:100%;">
					</button>
					</div>
					<!-- Modal -->
					<div class="modal fade" id="pic3" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <div class="modal-dialog">
					    <div class="modal-content">
					       <div class="modal-body">
					        <img src="<%=request.getContextPath()%>/util/DBGifReader?id_key=shop_id&id=${param.shop_id}&table=shop&pic=shop_img3" style="max-height:100%;max-width:100%;">
					      </div>
					      </div>
					  </div>
					</div>
					<!-- Modal_end -->			
				</div>
				<input type="hidden" name="shop_id" value="${shopVO.shop_id}">
<!-- 				<input type="submit" value="送出新增">				 -->
		</div>			
		</div>
		</div>
	</div>
	<div style="width:90px;"></div>
	<div class="col-md-6">		
	<div class="card" style="background-color:lightyellow;">
		<div class="card-body">
		<h5 class="card-title">揪團表單</h5>
				<FORM METHOD="post" style="padding:0px 20px;" enctype="multipart/form-data" ACTION="<%=request.getContextPath()%>/groupbuy/GroupBuyServlet" name="form1">
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