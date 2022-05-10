<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>

<%-- �U�νƦX�d��-�i�ѫȤ��select_page.jsp�H�N�W�����Q�d�ߪ���� --%>
<%-- �����u�@���ƦX�d�߮ɤ����G�m�ߡA�i���ݭn�A�W�[�����B�e�X�ק�B�R�����\��--%>

<jsp:useBean id="listEmps_ByCompositeQuery" scope="request" type="java.util.List<EmpVO>" /> <!-- ��EL����i�ٲ� -->
<jsp:useBean id="depSvc" scope="page" class="com.dep.model.DepService" />


<html>
<head><title>�ƦX�d�� - listEmps_ByCompositeQuery.jsp</title>

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
	width: 800px;
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

<h4>
���U�νƦX�d��  - �i�ѫȤ�� select_page.jsp �H�N�W�����Q�d�ߪ����<br>
�������@���ƦX�d�߮ɤ����G�m�ߡA<font color=red>�w�W�[�����B�e�X�ק�B�R�����\��</font></h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ����u��� - listAllEmp.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>


<table>
	<tr>
		<th>���u�s��</th>
		<th>���u�m�W</th>
		<th>����</th>
		<th>���Τ��</th>
		<th>��¾���</th>
		<th>���</th>
		<th>����</th>
		<th>����</th>
		<th>�M��</th>
		<th>�Y�K</th>
		<th>�H�c</th>
		<th>�ͤ�</th>
		<th>���A</th>
		<th></th>
	</tr>
	<%@ include file="/back/page1_ByCompositeQuery.file" %>
	<c:forEach var="empVO" items="${listEmps_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
				<td>${empVO.empId}</td>
			<td>${empVO.empName}</td>
		 <td>${empVO.depId}-[${empVO.depVO.depName}]</td> 

			<td>${empVO.hiredate}</td>
				<td>${empVO.resigndate}</td>
			<td>${empVO.phone}</td>
			<td>${empVO.extension}</td> 
				<td>${empVO.hobby}</td>
				
						<td>${empVO.skill}</td>
							<td style="width:300px;"><img 
												src="
									<%=request.getContextPath()%>/util/DBGifReader?pic=emp_profile&table=emp&id_key=emp_id&id=${empVO.empId}
									"
												class="img-fluid"
											></td>
								<td>${empVO.mail}</td>
									<td>${empVO.birthday}</td>
										<td>${empVO.empStatus}</td>
                
		
						<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/empServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�"> 
			     <input type="hidden" name="empId"      value="${empVO.empId}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller-->
			     <input type="hidden" name="action"	    value="getOne_For_Update"></FORM>
			</td>
			
		</tr>
	</c:forEach>
</table>
<%@ include file="/back/page2_ByCompositeQuery.file" %>

<br>�����������|:<br><b>
   <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> </b>

</body>
</html>