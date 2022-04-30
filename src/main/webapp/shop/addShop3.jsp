<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop.model.*"%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>店家資料新增 - addShop.jsp</title>

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
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>員工資料新增 - addShop.jsp</h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/shop/select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<!-- 錯誤表列 -->
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
		<td>店家名稱:</td>
		<td><input type="TEXT" name="shop_name" size="45" 
			 value="${param.shop_name}"/></td><td>${errorMsgs.shop_name}</td>
	</tr>
	<tr>
		<td>店家類型:</td>	 
		<td><select size="1" name="shop_type" size="45">
				<option value="5" ${(param.shop_type=="")? 'selected':'' } >請選擇類型
				<option value="0" ${(param.shop_type==0)? 'selected':'' } >飲料
				<option value="1" ${(param.shop_type==1)? 'selected':'' } >中式
				<option value="2" ${(param.shop_type==2)? 'selected':'' } >異國
				<option value="3" ${(param.shop_type==3)? 'selected':'' } >小吃
				<option value="4" ${(param.shop_type==4)? 'selected':'' } >素食
				<option value="5" ${(param.shop_type==5)? 'selected':'' } >其他
		</select></td><td>${errorMsgs.shop_type}</td>
	</tr>
	<tr>
		<td>地址:</td><td>
		<input class="js-demeter-tw-zipcode-selector" name="placecode" data-city="#city3" data-dist="#dist3" placeholder="請輸入郵遞區號" value="" >
		<select id="city3" name="city" placeholder="請選擇縣市" style="width:20px"></select>
		<select id="dist3" name="dist" placeholder="請選擇鄉鎮區" style="width:20px"></select>
		<input type="TEXT" name="add" size="45" placeholder="請輸入地址"
			 value=""/></td><td>${errorMsgs.address}</td>
		<td><input type="hidden" name="address" size="45" value="${param.address}"/> </td>
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
			 value="${param.shop_img1}"/></td><td>${errorMsgs.shop_img1}
			 </td>
	</tr>
	<tr>
		<td>圖片:</td>
		<td><input type="FILE" name="shop_img2" size="45" oninput="pic2.src=window.URL.createObjectURL(this.files[0])"
			 value="${param.shop_img2}"/>
			 </td><td>${errorMsgs.shop_img2}</td>
	</tr>
	<tr>
		<td>圖片:</td>
		<td><input type="FILE" name="shop_img3" size="45" oninput="pic3.src=window.URL.createObjectURL(this.files[0])"
			 value="${param.shop_img3}"/>
			 </td><td>${errorMsgs.shop_img3}</td>
	</tr>


</table>
<img id="pic1" style="width:100px;">
<img id="pic2" style="width:100px;">
<img id="pic3" style="width:100px;">
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

</html>