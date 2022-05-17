<%@page import="com.report_comment.model.Report_CommentVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.report.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%ReportVO repVO = (ReportVO) request.getAttribute("repVO"); 
	request.setAttribute("repVO", repVO);
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

    <title>BackForwardReport</title>

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

                    <!-- 內容放這 -->
  <!-- content 如果頁面要可以往下滑就改一下main的height值吧 -->
  		
         <main style="height: 150vh">
                       
                        <div
                            style="width:900px;position:absolute; height:630px; top:15%; right:5%;">
                            <div class="input-group mb-3">
                                <span class="input-group-text" id="basic-addon1">標題</span>
                                 <span>${repVO.title}</span>
                                    
                            </div>
	<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/reportCommentServlet" name="formUpdate" enctype="multipart/form-data">
                            <div class="input-group mb-3">
                                 <div class="input-group mb-3" id="input-radio">
               					 <span class="input-group-text" id="basic-addon2">類型</span>
               					 <span><c:if test="${repVO.report_type==0}">添購新品			</c:if>
													<c:if test="${repVO.report_type==1}">損壞報修		</c:if>
													<c:if test="${repVO.report_type==2}">軟硬體問題		</c:if>
													<c:if test="${repVO.report_type==3}">其他		</c:if></span>
               					 <label><input id="in0" class ="rptype" type="radio" name="report_type" value="0" ${(repVO.report_type=="0")? 'checked':'' }> 添購新品 </label>
								 <label><input id="in1" class ="rptype" type="radio" name="report_type" value="1" ${(repVO.report_type=="1")? 'checked':'' }> 損壞報修 </label>
							 	 <label><input id="in2" class ="rptype" type="radio" name="report_type" value="2" ${(repVO.report_type=="2")? 'checked':'' }> 軟硬體問題 </label>
							 	 <label><input id="in3" class ="rptype" type="radio" name="report_type" value="3" ${(repVO.report_type=="3")? 'checked':'' }> 其他 </label>
            				</div>
                                <span class="input-group-text" id="basic-addon2">處理狀況</span>
                               <span><c:if test="${repVO.status==0}">已發送</c:if>
													<c:if test="${repVO.status==1}">處理中</c:if>
													<c:if test="${repVO.status==2}">待確認</c:if>
													<c:if test="${repVO.status==3}">取消</c:if>
													<c:if test="${repVO.status==4}">已完成</c:if></span>
                              <input class="form-control"  type="hidden"
                   						 aria-describedby="basic-addon1" name="status" value="${1}">
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text" id="basic-addon2">回報建立時間</span>
                               <span><fmt:formatDate value="${repVO.starttime}" pattern="yyyy-MM-dd HH:mm "/></span>
                                  
                                <span class="input-group-text" id="basic-addon2">回報最新編輯時間</span>
                              <span><fmt:formatDate value="${repVO.updatetime}" pattern="yyyy-MM-dd HH:mm "/></span>  
                                <span class="input-group-text" id="basic-addon2">回報結束時間</span>
                              <span><fmt:formatDate value="${repVO.endtime}" pattern="yyyy-MM-dd HH:mm "/></span>
                                
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text" id="basic-addon2">回報人</span>
                             <span>${repVO.empVO1.empName}</span>
                                
                                <span class="input-group-text" id="basic-addon2">處理人</span>
                              <span>${repVO.empVO2.empName}</span>
               					<input type="hidden" class="form-control" aria-label="Username" id="inputhandler" name="handler"
                    				aria-describedby="basic-addon1" value="${repVO.handler}">
                            </div>
                            <label for="basic-url" class="form-label">回報內容</label>
                            <div class="input-group mb-3" style=" height:200px">
                                <textarea readonly class="form-control" aria-label="With textarea">${repVO.content}</textarea>
                            </div>

                            <div class="input-group mb-3">
                                <label class="input-group-text" for="inputGroupFile01">回報圖片</label>                              
                             <c:if test="${repVO.report_image!=null}">
                              <img src="<%=request.getContextPath()%>/util/DBGifReader?id_key=report_id&id=${repVO.report_id}&table=report&pic=report_image" style="width:100px; height:100px;">
                              </c:if>
                            </div>
                            <c:forEach var="recVO" items="${repVO.recVO}">
                            <div class="input-group">
                                <span class="input-group-text">處理訊息</span>
                                <span class="input-group-text"><fmt:formatDate value="${recVO.createtime}" pattern="yyyy-MM-dd HH:mm "/></span>
                                <textarea class="form-control" aria-label="With textarea">${recVO.comment}</textarea>
                             <c:if test="${recVO.report_comment_image!=null}">
                                <label class="input-group-text" for="inputGroupFile01">處理圖片</label>                              
                              <img src="<%=request.getContextPath()%>/util/DBGifReader?id_key=report_comment_id&id=${recVO.report_comment_id}&table=report_comment&pic=report_comment_image" style="width:150px; height:150px;">
                              </c:if>
                            </div>
                            </c:forEach>
                            
                                <div class="input-group">
								 <input class="form-control" aria-label="Username" type="hidden"
                   						 aria-describedby="basic-addon1" name="report_id" value="${repVO.report_id}">
                                <span class="input-group-text">處理訊息</span>
                            	<input class="form-control" aria-label="Username" type="text" required
                    						aria-describedby="basic-addon1" name="comment" value="${recVO.comment}">
                    						<p style="color :red">${errorMsgs.comment}</p>
                    			</div>
                    		<div class="input-group">       				
                				<input type="text" style="display:none;" name="action" value="update">
								<button type="submit" value="送出修改/轉發" style="margin-top:20px; border:red 1px solid; width:100px; height:100px;">送出</button>
                            </div> 
                            </FORM>
                        </div>
                    </main>

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
$('.rptype').click( function(e){
	console.log(e.target.value);
    if(e.target.value==0){
    	$('#inputhandler').val('1019');
    }else if(e.target.value==1){
    	$('#inputhandler').val('1017');
    }else if(e.target.value==2){
        	$('#inputhandler').val('1016');
    }else if(e.target.value==3){
            	$('#inputhandler').val('1017');
    }
});
</script>

</body>

</html>