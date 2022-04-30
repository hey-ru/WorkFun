<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop.model.*"%>

<%
  // EmpVO empVO = (EmpVO) request.getAttribute("empVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>員工資料修改 - update_emp_input.jsp</title>

<style>
  table#table-1 {
    width: 450px;
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
  div{
  width: 200px;
  display:inline-block;
  }
  img{
  max-width: 200px;
  max-height: 150px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>店家資料修改 - update_shop_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/shop/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message.value}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>

<FORM METHOD="post" enctype="multipart/form-data" ACTION="<%=request.getContextPath()%>/shop/ShopServlet" name="form1">
<table>
    <tr>
		<td>店家編號:<font color=red><b>*</b></font></td>
		<td>${param.shop_id}</td>
	</tr>
	<tr>
		<td>店家名稱:</td>
		<td><input type="TEXT" name="shop_name" size="45" 
			 value="${param.shop_name}"/></td><td>${errorMsgs.shop_name}</td>
	</tr>
		<tr>
		<td>店家類型:</td>	 
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
		<td>地址: </td><td>
		<input class="js-demeter-tw-zipcode-selector" data-city="#city" data-dist="#dist" name="placecode" style="width:120px;" placeholder="請輸入郵遞區號" />
		<select id="city" name="city" placeholder="請選擇縣市"></select>
		<select id="dist" name="dist" placeholder="請選擇鄉鎮區"></select><br>
		<input type="TEXT" name="address" size="45" placeholder="請輸入接續地址"
			 value="${param.address}"/></td><td>${errorMsgs.address}</td>
	</tr>
	<tr>
		<td>電話:</td>
		<td><input type="TEXT" name="tel" size="45"
			 value="${param.tel}"/></td><td>${errorMsgs.tel}</td>
	</tr>
	<tr>
		<td>網站:</td>
		<td><input type="TEXT" name="website" size="45"
			 value="${param.website}"/></td><td>${errorMsgs.website}</td>
	</tr>
	<tr>
		<td>低消:</td>
		<td><input type="TEXT" name="min_amt" size="45"
			 value="${param.min_amt}"/></td><td>${errorMsgs.min_amt}</td>
	</tr>
	<tr>
		<td>圖片:</td>
		<td><input type="FILE" name="shop_img1" size="45" oninput="pic1.src=window.URL.createObjectURL(this.files[0])"
			 id ="shop_img1" value="${param.shop_img1}"/></td><td>${errorMsgs.shop_img1}
			 </td>
	</tr>
	<tr>
		<td>圖片:</td>
		<td><input type="FILE" name="shop_img2" size="45" oninput="pic2.src=window.URL.createObjectURL(this.files[0])"
			id ="shop_img2" value="${param.shop_img2}"/>
			 </td><td>${errorMsgs.shop_img2}</td>
	</tr>
	<tr>
		<td>圖片:</td>
		<td><input type="FILE" name="shop_img3" size="45" oninput="pic3.src=window.URL.createObjectURL(this.files[0])"
			 id ="shop_img3" value="${param.shop_img3}"/>
			 </td><td>${errorMsgs.shop_img3}</td>
	</tr>
	</table>
	<div>
	<img src="<%=request.getContextPath()%>/util/DBGifReader?id_key=shop_id&id=${param.shop_id}&table=shop&pic=shop_img1" id="oldpic1">
	<img id="pic1">
	</div>
	<div>
	<img src="<%=request.getContextPath()%>/util/DBGifReader?id_key=shop_id&id=${param.shop_id}&table=shop&pic=shop_img2" id="oldpic2">
	<img id="pic2">
	</div>
	<div>
	<img src="<%=request.getContextPath()%>/util/DBGifReader?id_key=shop_id&id=${param.shop_id}&table=shop&pic=shop_img3" id="oldpic3">
	<img id="pic3">
	</div>



<br>
<br>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="shop_id" value="${param.shop_id}">
<input type="submit" value="送出修改"></FORM>
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
			

</script>

</html>