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
                        <li class="nav-item no-arrow">
                         <a href="<%=request.getContextPath()%>/home/home.jsp"> <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                Back Home</a>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                    <!-- 內容放這 -->
  <!-- content 如果頁面要可以往下滑就改一下main的height值吧 -->
  		
         <main style="height: 120vh">
                       
                            <div class="input-group mb-3" style="margin-top: 0px;">
                                <span class="input-group-text" id="basic-addon1">標題</span>
                                 <span>${repVO.title}</span>
                                    
                            </div>

                            <div class="input-group mb-3">
                                <span class="input-group-text" id="basic-addon2">類型</span>
                                <span><c:if test="${repVO.report_type==0}">添購新品</c:if>
													<c:if test="${repVO.report_type==1}">損壞報修</c:if>
													<c:if test="${repVO.report_type==2}">軟硬體問題</c:if>
													<c:if test="${repVO.report_type==3}">其他</c:if></span>
                                <span class="input-group-text" id="basic-addon2">處理狀況</span>
                               <span><c:if test="${repVO.status==0}">已發送</c:if>
													<c:if test="${repVO.status==1}">處理中</c:if>
													<c:if test="${repVO.status==2}">待確認</c:if>
													<c:if test="${repVO.status==3}">取消</c:if>
													<c:if test="${repVO.status==4}">已完成</c:if></span>
                              
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
                                  
                            </div>
                            <label for="basic-url" class="form-label">回報內容</label>
                            <div class="input-group mb-3" style=" height:200px">
                                <textarea readonly class="form-control" aria-label="With textarea">${repVO.content}</textarea>
                            </div>

                            <div class="input-group mb-3">
                                <label class="input-group-text" for="inputGroupFile01">回報圖片</label>                              
                             <c:if test="${repVO.report_image!=null}">
                              <img src="<%=request.getContextPath()%>/util/DBGifReader?id_key=report_id&id=${repVO.report_id}&table=report&pic=report_image" style="width:220px; height:220px;">
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
                            <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/reportCommentServlet" name="formAdd" enctype="multipart/form-data">
                                <div class="input-group">
								 <input class="form-control" aria-label="Username" type="hidden"
                   						 aria-describedby="basic-addon1" name="report_id" value="${repVO.report_id}">
                                <span class="input-group-text">處理訊息</span>
                            	<input class="form-control" aria-label="Username" type="text" id="comment" required
                    						aria-describedby="basic-addon1" name="comment" value="${recVO.comment}">
                    						<p style="color :red" id="comment_error">${errorMsgs.comment}</p>	
                    			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
                    					<script type="text/javascript">
    
										    let rule1=/^[(\u4e00-\u9fa5)(\u0800-\u4e00)a-zA-Z0-9_\s\\(\\-\\)\u3002|\uff1f|\uff01|\uff0c|\u3001|\uff1b|\uff1a|\u201c|\u201d|\u2018|\u2019|\uff08|\uff09|\u300a|\u300b|\u3008|\u3009|\u3010|\u3011|\u300e|\u300f|\u300c|\u300d|\ufe43|\ufe44|\u3014|\u3015|\u2026|\u2014|\uff5e|\ufe4f|\uffe5]*$/;
										    $("#comment").blur(function(){
										  	  if($(this).val() == ''){
										  		  $('#comment_error').text('請填入處理訊息!')
										  	  }else if(rule1.test($(this).val())){
										            $('#comment_error').text('')
										        }else{
										            $('#comment_error').text('處理訊息:只能是中英文字母、數字、全型標點符號')
										        }
										    });	
          								</script>   						
                    			</div>
                    		<div class="input-group mb-3">
                 	 			<label class="input-group-text" for="inputGroupFile01">Upload</label>
                				<input id="imgInp" type="file" value="${recVO.report_comment_image}" name="report_comment_image" accept="image/*" oninput="pic.src=window.URL.createObjectURL(this.files[0])">
                				<img id="blah" src="#" alt="your image" style="width:250px;height:250px" />
                				<input type="text" style="display:none;" name="action" value="insert">
								<button type="submit" value="送出新增">送出</button>
                            </div> 
                            </FORM>
                       
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
    imgInp.onchange = evt => {
    	  const [file] = imgInp.files
    	  if (file) {
    	    blah.src = URL.createObjectURL(file)
    	  }
    	}
    
    
    </script>
</body>

</html>