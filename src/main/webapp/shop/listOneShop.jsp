<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.emp.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<html>
<head>
<title>���u��� - listOneEmp.jsp</title>

<style>
  table#table-1 {
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
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>���a��� - ListOneShop.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>shop_id</th>
			<th>shop_name</th>
			<th>shop_type</th>
			<th>address</th>
			<th>tel</th>
			<th>website</th>
			<th>min_amt</th>
			<th>�Ӥ�</th>
			<th>�Ӥ�</th>
			<th>�Ӥ�</th>
			<th>���</th>
			<th>�ק�</th>
			<!-- 		<th>�R��</th> -->
	</tr>
				<tr>
				<td>${shopVO.shop_id}</td>
				<td>${shopVO.shop_name}</td>
				<td>${shopVO.shop_type}</td>
				<td>${shopVO.address}</td>
				<td>${shopVO.tel}</td>
				<td><a href="${shopVO.website}">link</a></td>
				<td>${shopVO.min_amt}</td>
				<td><img src="<%=request.getContextPath()%>/util/DBGifReader?id_key=shop_id&id=${shopVO.shop_id}&table=shop&pic=shop_img1" style="width: 100px;"></td>
				<td><img src="<%=request.getContextPath()%>/util/DBGifReader?id_key=shop_id&id=${shopVO.shop_id}&table=shop&pic=shop_img2" style="width: 100px;"></td>
				<td><img src="<%=request.getContextPath()%>/util/DBGifReader?id_key=shop_id&id=${shopVO.shop_id}&table=shop&pic=shop_img3" style="width: 100px;"></td>
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/menu/selectmenubyshop">
						<input type="submit" value="�d�ݵ��" style="margin-bottom: 0px;"> 
						<input type="hidden" name="shop_id" value="${shopVO.shop_id}"> 
						<input type="hidden" name="action" value="getmenu">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/shop/ShopServlet"
						style="margin-bottom: 0px;">
						<input type="submit" value="�ק�"> 
						<input type="hidden" name="shop_id" value="${shopVO.shop_id}"> 
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="�R��"> -->
<%-- 			     <input type="hidden" name="empno"  value="${empVO.empno}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
			</tr>
</table>

</body>
</html>