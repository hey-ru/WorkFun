<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
	
	<%
	    EmpService empSvc = new EmpService();
	    List<EmpVO> list = empSvc.getAll();
	    pageContext.setAttribute("list",list);
	%>
	
<!DOCTYPE html>
<html lang="zh-TW">

<head>
<%@ include file="/design/backcss.jsp"%>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>WorkFunBack</title>

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
                        <li class="nav-item no-arrow pr-4">
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

				<jsp:useBean id="permissionSvc" scope="page" class="com.permission.model.PermissionService" />
									<jsp:useBean id="permissionMappingSvc" scope="page" class="com.permissionmapping.model.PermissionMappingService" />
  <%-- <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/empServlet" >
        <b>輸入員工編號 :</b>
        <input type="text" name="empId" value="${param.empId}"><font color=red>${errorMsgs.empId}</font>
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM> --%>


                        <div class="row">
                            <div class="col-md-12">
                                <!-- Advanced Tables -->
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                     
                                    </div>
                                    <div class="panel-body">
                                        <div class="table-responsive">
                                            <table class="table table-striped table-bordered table-hover"
                                                id="dataTables-example">
                                               <tr>
                   <td>員工姓名</td> 
                        
	<c:forEach var="permissionVO" items="${permissionSvc.all}">                      
		<th>${permissionVO.permissionName}</th>
	
	
			</c:forEach>
		<th></th>
		
		
		
	</tr>
	<%@ include file="page1.file" %> 
	
	
	<c:forEach var="empVOSearch" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	
		
	 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/empServlet" style="margin-bottom: 0px;">
		<tr>
		<td>${empVOSearch.empName}</td>
		
	<c:forEach var="permissionVO" items="${permissionSvc.all}">          
			<td>  <input type="checkbox" name="permissionId" value="${permissionVO.permissionId}" ${  permissionMappingSvc.getOneEmpPermissions(empVOSearch.empId).toString().indexOf(permissionVO.permissionId.toString())!=-1 ? "checked":""  }> 
			</td>
	
					</c:forEach>
										
			
			<td>
			   <input type="hidden" name="empId"  value="${empVOSearch.empId}">
			     <input type="submit" value="修改" class="btn btn-outline-dark">
			   
			     <input type="hidden" name="action"	value="changePermission">
			  
			</td>
			
		
		</tr>
		   </FORM>
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