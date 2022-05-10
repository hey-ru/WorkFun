<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>
<%
	    EmpService empSvc = new EmpService();
	    List<EmpVO> list = empSvc.getAll();
	    pageContext.setAttribute("list",list);
	%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/design/backcss.jsp"%>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>WorkFunBack</title>

<style>
.img-fluid {
    max-width: 70px;
    height: auto;
}

</style>

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    
    <div id="wrapper">

        <!-- Sidebar -->
<%@ include file="/design/backSidebar.jsp"%>
		<!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-dark topbar mb-4 static-top shadow">

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav bg-dark ml-auto">

                        <!-- Nav Item - User Information -->
                        <li class="nav-item no-arrow">
                         <a href="<%=request.getContextPath()%>/home/home.jsp"> <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                Back Home</a>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- 內容放這 -->
	
	   <div id="page-inner">
	  <jsp:useBean id="depSvc" scope="page" class="com.dep.model.DepService" />
 <li>   
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/empServlet" name="form1">
        <b><font color=blue>萬用複合查詢:</font></b> <br>
       <!--  <b>輸入員工編號:</b>
        <input type="text" name="empId" value=""><br> -->
           
     <!--   <b>輸入員工姓名:</b>
       <input type="text" name="empName" value=""><br> -->
            <b>輸入員工分機:</b>
       <input type="text" name="extension" value=""><br>
       
       
      <!--  <b>輸入員工職位:</b>
       <input type="text" name="" value="PRESIDENT"><br> -->
    
       <b>選擇部門:</b>
       <select size="1" name="depId" >
          <option value="">
         <c:forEach var="depVO" items="${depSvc.all}" > 
          <option value="${depVO.depId}">${depVO.depName}
         </c:forEach>   
       </select><br>
           
       <b>雇用日期:</b>
	   <input name="hiredate" id="f_date1" type="text"> 
		        
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="listEmps_ByCompositeQuery">
     </FORM>
  </li>
 
 

                        <div class="row">
                            <div class="col-md-12">
                                <!-- Advanced Tables -->
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        Advanced Tables
                                    </div>
                                    <div class="panel-body">
                                        <div class="table-responsive">
                                            <table class="table table-striped table-bordered table-hover"
                                                id="dataTables-example">
                                               <tr>
		<th>員工編號</th>
		<th>員工姓名</th>
		<th>部門</th>
		<th>雇用日期</th>
		<th>離職日期</th>
		<th>手機</th>
		<th>分機</th>
		<th>興趣</th>
		<th>專長</th>
		<th>頭貼</th>
		<th>信箱</th>
		<th>生日</th>
		<th>狀態</th>
		<th></th>
		<!-- <th></th> -->
		
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="empVOSearch" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${empVOSearch.empId}</td>
			<td>${empVOSearch.empName}</td>
			<td>${empVOSearch.depId}-[${empVOSearch.depVO.depName}]</td>

			<td>${empVOSearch.hiredate}</td>
				<td>${empVOSearch.resigndate}</td>
			<td>${empVOSearch.phone}</td>
			<td>${empVOSearch.extension}</td> 
				<td>${empVOSearch.hobby}</td>
				
						<td>${empVOSearch.skill}</td>
							<td style="width:300px;"><img 
												src="
									<%=request.getContextPath()%>/util/DBGifReader?pic=emp_profile&table=emp&id_key=emp_id&id=${empVOSearch.empId}
									"
												class="img-fluid"
											></td>
								<td>${empVOSearch.mail}</td>
									<td>${empVOSearch.birthday}</td>
										<td>${empVOSearch.empStatus}</td>
										
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/empServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="empId"  value="${empVOSearch.empId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<%-- <td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/empServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="empId"  value="${empVO.empId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td> --%>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
</div>
</div>
</div>
</div>

                    <!-- /.container-fluid -->
                </div>
                <!-- End of Main Content -->
            </div>
            <!-- End of Content Wrapper -->
        </div>
        <!-- End of Page Wrapper -->
	</div>
        <!-- Scroll to Top Button-->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>
     
<%@ include file="/design/backjs.jsp"%>

</body>

</html>