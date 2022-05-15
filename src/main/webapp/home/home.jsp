<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.announcement.model.*"%>
	<%@ page import="java.util.*"%>
<%
	    AnnouncementService annSvc = new AnnouncementService();
	    List<AnnouncementVO> list = annSvc.getAll();
	    pageContext.setAttribute("list",list);
	%>
<!DOCTYPE html>
<html lang="zh-TW">

<head>
     <%@ include file="/design/frontmetacss.jsp" %>
    <style>
        #hero {
            width: 100%;
            max-height: calc(100vh - var(--footer-height));
            background: url("<%=request.getContextPath()%>/home/img/homepic.png") ;
            background-size: cover;
            opacity: 0.7;
            box-sizing: border-box;
             z-index: -2;
        }
        
    </style>




</head>

<body>
   
    <!-- ======= Header ======= -->
    <%@ include file="/design/frontheader.jsp" %>
    <!-- End Header -->

    <!-- ======= Hero Section ======= -->
    <section id="hero" class="d-flex align-items-center ">
        <div class="container d-flex flex-column align-items-center" data-aos="zoom-in" data-aos-delay="100">
            <h1>Welcome to WorkFun</h1>
            <h2>Work together makes work fun!</h2>
         
        </div>
    </section><!-- End Hero -->

    <!-- ======= Footer ======= -->
         <%@ include file="/design/frontfooter.jsp" %>
         
       
    <!-- ======= js ======= -->
        <%@ include file="/design/frontjs.jsp" %>
         
    
    <div 
								style="border: 3px blue solid; width: 400px;  height: 200px; margin-top:-300px;margin-left:500px;z-index：1000;">
    
                        <div class="row">
                            <div class="col-md-12">
                                <!-- Advanced Tables -->
                                <div class="panel panel-default">
                                   
                                    <div class="panel-body">
                                        <div class="table-responsive">
                                        
                                      
                                        
                                        
                                            <table class="table table-striped table-bordered table-hover"
                                                id="dataTables-example" >
                                               <tr  >
                                            
	
		<th style="padding:0"; >Tibame大事紀</th>
		
		
	
		<!-- <th></th> -->
		
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="announcement" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
		
			
			<td ><a href="<%=request.getContextPath()%>/announcementServlet?action=getOne&announcement_id=${announcement.announcement_id}">${announcement.announcement_title}</a></td>
			

						
							
										
			
		
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
    </div>
    

    
</body>

</html>