<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Shop: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM Shop: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Shop: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message.value}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>

<ul>
  <li><a href='listAllShop.jsp'>List</a> all Shops.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="emp.do" >
        <b>輸入店家編號 (如101):</b>
        <input type="text" name="shop_id"><font color=red>${errorMsgs.empno}</font>
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="shopSvc" scope="page" class="com.shop.model.ShopService" />
   
  <li>
     <FORM METHOD="post" ACTION="/shop/ShopServlet" >
       <b>選擇店家編號:</b>
       <select size="1" name="shop_id">
         <c:forEach var="shopVO" items="${shopSvc.all}" > 
          <option value="${shopVO.shop_id}">${shopVO.shop_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="/shop/ShopServlet" >
       <b>選擇店家姓名:</b>
       <select size="1" name="shop_id">
         <c:forEach var="shopVO" items="${shopSvc.all}" > 
          <option value="${shopVO.shop_id}">${shopVO.shop_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>店家管理</h3>

<ul>
  <li><a href='addShop.jsp'>Add</a> a new Shop.</li>
</ul>

</body>
</html>