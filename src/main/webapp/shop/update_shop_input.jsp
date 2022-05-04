<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.shop.model.*"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

        <!DOCTYPE html>
        <html lang="zh-TW">

        <head>
            <%@ include file="/design/frontmetacss.jsp" %>
	 <style>
     th{
     width:80px;
     }
     main{
     margin: 0;
     }
    </style>
    

        </head>

        <body>
            <div class="wrapper" style="overflow:hidden;">
                <!-- ======= Header ======= -->
                <%@ include file="/design/frontheader.jsp" %>

                    <!-- ======= 內容開始 ======= -->

		<main id="main" class="main">
			<div class="card" style="border:0px;">
<!--                    <div style="height: var(--header-height); margin-bottom:10px;"></div> -->
				<!-- ============== Card Header ============== -->
				<div class="card-header py-3" style="background-color: #b0c4de">
					<div class="row">
						<div class="col-11" style="height: 20px; display: inline-block;">
							<h5>
								<strong>修改店家資訊</strong>
							</h5>
						</div>
						<div class="col-1" style="height: 20px; display: inline-block;">
							<a href="<%=request.getContextPath()%>/shop/listAllShop.jsp"><strong>回店家列表</strong></a>
						</div>
					</div>
				</div>

    <div class="row" style="margin:0;">               
   <div class="col-6">                
   <FORM METHOD="post" style="padding:0px 20px;" enctype="multipart/form-data" ACTION="<%=request.getContextPath()%>/shop/ShopServlet" name="form1">
<br>
<table>
	<tr>
		<th>店家編號:<font color=red><b>*</b></font></th>
		<td>${param.shop_id}</td>
	</tr>
	<tr>
		<th>店家名稱:<font color=red><b>*</b></font></th>
		<td><input type="TEXT" name="shop_name" size="45" 
			 value="${param.shop_name}"/></td><td>${errorMsgs.shop_name}</td>
	</tr>
	<tr>
		<th>店家類型: </th>	 
		<td><select size="1" name="shop_type" size="45">
				<option value="0" ${(param.shop_type==0)? 'selected':'' } >飲料
				<option value="1" ${(param.shop_type==1)? 'selected':'' } >中式
				<option value="2" ${(param.shop_type==2)? 'selected':'' } >異國
				<option value="3" ${(param.shop_type==3)? 'selected':'' } >小吃
				<option value="4" ${(param.shop_type==4)? 'selected':'' } >素食
				<option value="5" ${(param.shop_type==5)? 'selected':'' } >其他
		</select></td><td>${errorMsgs.shop_type}</td>
	</tr>
	<tr>
		<th>地址: </th>
		<td>
		<c:set var = "address" value = "${param.address}" />
		<c:set var = "placecode" value = "${fn:substring(address, 0, 3)}" />
		<c:set var = "addressend" value = "${fn:substring(address, 3, -1)}" />
		<input class="js-demeter-tw-zipcode-selector" data-city="#city" data-dist="#dist" id="placecode" name="placecode" style="width:120px;" placeholder="請輸入郵遞區號" value="${placecode}" />
		<select id="city" name="city" placeholder="請選擇縣市"></select>
		<select id="dist" name="dist" placeholder="請選擇鄉鎮區"></select><br>
		<input type="TEXT" id="addressend" name="addressend" size="45" placeholder="請輸入接續地址"
			 value="${addressend}"/></td><td>${errorMsgs.address}</td>
	</tr>
	<tr>
		<th>電話:<font color=red><b>*</b></font></th>
		<td><input type="TEXT" name="tel" size="45"
			 value="${param.tel}"/></td><td>${errorMsgs.tel}</td>
	</tr>
	<tr>
		<th>網站: </th>
		<td><input type="TEXT" name="website" size="45"
			 value="${param.website}"/></td><td>${errorMsgs.website}</td>
	</tr>
	<tr>
		<th>低消: </th>
		<td><input type="TEXT" name="min_amt" size="45"
			 value="${param.min_amt}"/></td><td>${errorMsgs.min_amt}</td>
	</tr>
	<tr>
		<th>圖片:</th>
		<td><input type="FILE" name="shop_img1" size="45" oninput="pic1.src=window.URL.createObjectURL(this.files[0])"
			 id ="shop_img1" value="${param.shop_img1}"/></td><td>${errorMsgs.shop_img1}
			 </td>
	</tr>
	<tr>
		<th>圖片:</th>
		<td><input type="FILE" name="shop_img2" size="45" oninput="pic2.src=window.URL.createObjectURL(this.files[0])"
			id ="shop_img2" value="${param.shop_img2}"/>
			 </td><td>${errorMsgs.shop_img2}</td>
	</tr>
	<tr>
		<th>圖片:</th>
		<td><input type="FILE" name="shop_img3" size="45" oninput="pic3.src=window.URL.createObjectURL(this.files[0])"
			 id ="shop_img3" value="${param.shop_img3}"/>
			 </td><td>${errorMsgs.shop_img3}</td>
	</tr>
</table>
<br><br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="shop_id" value="${param.shop_id}">
<input type="submit" value="送出修改">
</FORM> 
</div>
<div class="col-1"></div>
<div class="col-4">
<div style="height:150px;padding:5px;">
<img src="<%=request.getContextPath()%>/util/DBGifReader?id_key=shop_id&id=${param.shop_id}&table=shop&pic=shop_img1" style="max-height:100%;" id="oldpic1">
<img id="pic1" style="max-height:100%;">
</div>
<div style="height:150px;padding:5px;">
<img src="<%=request.getContextPath()%>/util/DBGifReader?id_key=shop_id&id=${param.shop_id}&table=shop&pic=shop_img2" style="max-height:100%;" id="oldpic2">
<img id="pic2" style="max-height:100%;">
</div>
<div style="height:150px;padding:5px;">
<img src="<%=request.getContextPath()%>/util/DBGifReader?id_key=shop_id&id=${param.shop_id}&table=shop&pic=shop_img3" style="max-height:100%;" id="oldpic3">
<img id="pic3" style="max-height:100%;">
</div>
</div>




 </div>   
                
 </div>     
 </main>        

                    <!-- ======= 內容結束 ======= -->

            </div>
            <!-- ======= Footer ======= -->
            <%@ include file="/design/frontfooter.jsp" %>
                <!-- ======= js ======= -->
                <%@ include file="/design/frontjs.jsp" %>

        </body>
        <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	
		$('#shop_img1').change( function() {  
			$("#oldpic1").hide(); 
		}); 
		$('#shop_img2').change( function() {  
			$("#oldpic2").hide(); 
		});
		$('#shop_img3').change( function() {  
			$("#oldpic3").hide(); 
		});
		
		$('#placecode').blur( function() {  
			$('#addressend').val(''); 
		}); 
		$('#city').change( function() {  
			$('#addressend').val(''); 
		});
			

</script>

        </html>