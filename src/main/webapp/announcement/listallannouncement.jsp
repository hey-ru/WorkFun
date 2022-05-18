<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ page import="java.util.*"%>
<%@ page import="com.announcement.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	    AnnouncementService annSvc = new AnnouncementService();
	    List<AnnouncementVO> list = annSvc.getAll();
	    pageContext.setAttribute("list",list);
	%>
<!DOCTYPE html>
<html lang="en">

<head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
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
.table td, .table th {
vertical-align:middle;
}
.ellipsis {
overflow:hidden;
white-space: nowrap;
text-overflow: ellipsis;
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
	 

 

                        <div class="row">
                            <div class="col-md-12">
                                <!-- Advanced Tables -->
                                <div class="panel panel-default">
                                  
                                    <div class="panel-body">
                                        <div class="table-responsive">
                                            <table class="table table-striped table-bordered table-hover"
                                                id="dataTables-example">
                                               <tr>
                                               <th>公告編號</th>
		<th>發布者</th>
		<th>公告標題</th>
		<th>公告內容</th>
		<th>公告發布日期</th>
		<th>公告狀態</th>
		
		<th></th>
		<!-- <th></th> -->
		
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="announcement" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
		<td>${announcement.announcement_id}</td>
			<td>${announcement.empVO.empName}</td>
			<td style="width:150px;">${announcement.announcement_title}</td>
			

			<td class="JQellipsis" style="width:300px;">${announcement.announcement_content}</td>
				<td>		<fmt:formatDate value="${announcement.announcement_time}"
													pattern="yyyy-MM-dd HH:mm" /></td>
		
				<c:choose>
				<c:when test="${announcement.announcement_status==1}">
						<td>公開</td>
						</c:when>
							<%-- <td style="width:300px;"><img 
												src="
									<%=request.getContextPath()%>/util/DBGifReader?pic=emp_profile&table=emp&id_key=emp_id&id=${empVOSearch.empId}
									"
												class="img-fluid"
											></td> --%>
										<c:otherwise>
										<td>非公開</td>
										</c:otherwise>	
											
							</c:choose>
										
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/announcementServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="修改" class="btn btn-outline-dark">
			      <input type="hidden" name="announcement_id"  value="${announcement.announcement_id}"> 
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
<script>
$(function(){
    var len = 50; // 超過50個字以"..."取代
    $(".JQellipsis").each(function(i){
        if($(this).text().length>len){
            $(this).attr("title",$(this).text());
            var text=$(this).text().substring(0,len-1)+"...";
            $(this).text(text);
        }
    });
});
</script>
</body>

</html>