<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.report.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
         <main style="height: 120vh; border:3px red solid;">
                        <div
                            style="border:3px blue solid;width:900px;position:absolute; height:630px; top:15%; right:5%;">
                            <div class="input-group mb-3" style="margin-top: 0px;">
                                <span class="input-group-text" id="basic-addon1">標題</span>
                                 <span style="border:3px lightyellow solid;">${reportVO.title}</span>
                                    
                            </div>

                            <div class="input-group mb-3">
                                <span class="input-group-text" id="basic-addon2">類型</span>
                                <span><c:if test="${reportVO.report_type==0}">添購新品</c:if>
													<c:if test="${reportVO.report_type==1}">損壞報修</c:if>
													<c:if test="${reportVO.report_type==2}">軟硬體問題</c:if>
													<c:if test="${reportVO.report_type==3}">其他</c:if></span>
                                <span class="input-group-text" id="basic-addon2">處理狀況</span>
                               <span><c:if test="${reportVO.status==0}">已發送</c:if>
													<c:if test="${reportVO.status==1}">處理中</c:if>
													<c:if test="${reportVO.status==2}">待確認</c:if>
													<c:if test="${reportVO.status==3}">取消</c:if>
													<c:if test="${reportVO.status==4}">已完成</c:if></span>
                              
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text" id="basic-addon2">回報建立時間</span>
                               <span><fmt:formatDate value="${reportVO.starttime}" pattern="yyyy-MM-dd HH:mm "/></span>
                                  
                                <span class="input-group-text" id="basic-addon2">回報最新編輯時間</span>
                              <span><fmt:formatDate value="${reportVO.updatetime}" pattern="yyyy-MM-dd HH:mm "/></span>  
                                <span class="input-group-text" id="basic-addon2">回報結束時間</span>
                              <span><fmt:formatDate value="${reportVO.endtime}" pattern="yyyy-MM-dd HH:mm "/></span>
                                
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text" id="basic-addon2">回報人</span>
                             <span>${reportVO.empVO1.empName}</span>
                                
                                <span class="input-group-text" id="basic-addon2">處理人</span>
                              <span>${reportVO.empVO2.empName}</span>
                                  
                            </div>
                            <label for="basic-url" class="form-label">回報內容</label>
                            <div class="input-group mb-3" style=" height:200px">
                                <textarea class="form-control" aria-label="With textarea">${reportVO.content}</textarea>
                            </div>

                            <div class="input-group mb-3">
                                <label class="input-group-text" for="inputGroupFile01">回報圖片</label>                              
                             <c:if test="${reportVO.report_image!=null}">
                              <img src="<%=request.getContextPath()%>/util/DBGifReader?id_key=report_id&id=${reportVO.report_id}&table=report&pic=report_image" style="width:100px; height:100px;">
                              </c:if>
                            </div>

                            <div class="input-group">
                                <span class="input-group-text">處理訊息</span>
                                <textarea class="form-control" aria-label="With textarea">Test</textarea>
                            </div>
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

</body>

</html>