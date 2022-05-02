<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>器材 : Home</title>

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
   <tr><td><h3>器材 : Home</h3></td></tr>
</table>


<h3>器材查詢:</h3>

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
  <li><a href='listAllEquipment.jsp'>查看全部器材(修改 / 刪除)</a><br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/equipment/equipment.do" >
        <b>輸入器材編號(100起): </b>
        <input type="text" name="equipmentId" value="${param.equipmentId}"><font color=red>${errorMsgs.equipmentId}</font>
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>
  
   <li>
    <FORM METHOD="post" ACTION="equipment.do" >
        <b>輸入器材名稱: </b>
        <input type="text" name="eqName" value="${param.eqName}"><font color=red>${errorMsgs.eqName}</font>
        <input type="hidden" name="action" value="getOne_For_eqName">
        <input type="submit" value="送出">
    </FORM>
  </li>
  
  <jsp:useBean id="equipSvc" scope="page" class="com.equipment.model.EquipmentService" />
   
  <li>
     <FORM METHOD="post" ACTION="equipment.do" >
       <b>選擇器材狀態:</b>
       <select size="1" name="eqStatus" value="${equipmentVO.eqStatus}">
<%-- 			<c:forEach var="equipmentVO" items="${equipSvc.all}" > --%>
<%--        	<option value="${equipmentVO.eqStatus}">${(equipmentVO.eqStatus==0)? "器材損壞/下架" : (equipmentVO.eqStatus==1)? "現貨" : (equipmentVO.eqStatus==2)? "預約未歸還" : "送維修中"} --%>
         		<option>選擇狀態</option>
         		<option value="eqStatus = 0">上架</option>
         		<option value="eqStatus = 1">預約未歸還</option>
         		<option value="eqStatus = 2">送修中</option>
         		<option value="eqStatus = 3">下架</option>				
<%--          	</c:forEach>    --%>
       	</select>
       <input type="hidden" name="action" value="getOne_For_eqStatus">
       <input type="submit" value="送出">
    </FORM>
  </li>
</ul>
  
  
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="emp.do" > -->
<!--        <b>選擇員工姓名:</b> -->
<!--        <select size="1" name="empno"> -->
<%--          <c:forEach var="empVO" items="${empSvc.all}" >  --%>
<%--           <option value="${empVO.empno}">${empVO.ename} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="送出"> -->
<!--      </FORM> -->
<!--   </li> -->
<!-- </ul> -->


<h3>器材管理:</h3>

<ul>
  <li><a href='backAddEqpt.jsp'>器材(新增)</a></li>
</ul>

</body>
</html>