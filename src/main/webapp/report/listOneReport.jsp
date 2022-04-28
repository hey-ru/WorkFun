<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.report.model.*"%>
<%
 ReportVO reportVO = (ReportVO) request.getAttribute("reportVO");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />

    <title>:: WorkFunlistOneReport ::</title>
    <!-- Favicons -->
    <link href="${pageContext.request.contextPath}/assets/img/wf.png" rel="icon" />
    <link href="${pageContext.request.contextPath}/assets/img/wf.png" rel="apple-touch-icon" />

    <!-- Google Fonts -->
    <link
        href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
        rel="stylesheet" />

    <!-- Vendor CSS Files -->
    <link href="${pageContext.request.contextPath}/assets/vendor/aos/aos.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet" />

    <!-- Template Main CSS File -->
    <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet" />
</head>

<body>
    <!-- ======= Header ======= -->
    <header id="header" class="fixed-top">
        <div class="container-fluid d-flex justify-content-between align-items-center">
            <!-- <h1 class="logo me-auto me-lg-0"><a href="">WorkFun</a></h1> -->
            <!-- 公司logo圖片 -->
            <a href="Home.html" class="logo"><img src="${pageContext.request.contextPath}/assets/img/workfun.gif" alt="" class="img-fluid"
                    width="230px"></a>

            <nav id="navbar" class="navbar order-last order-lg-0">
                <ul>
                    <!-- 修改跳頁 -->
                    <li>
                        <div class="dropdown">
                            <button class="dropbtn">揪團訂餐</button>
                            <div class="dropdown-content">
                                <a href="#">Link 1</a>
                                <a href="#">Link 2</a>
                                <a href="#">Link 3</a>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="dropdown">
                            <button class="dropbtn">二手競標</button>
                            <div class="dropdown-content">
                                <a href="#">Link 1</a>
                                <a href="#">Link 2</a>
                                <a href="#">Link 3</a>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="dropdown">
                            <button class="dropbtn">問題回報</button>
                            <div class="dropdown-content">
                                <a href="#">Link 1</a>
                                <a href="#">Link 2</a>
                                <a href="#">Link 3</a>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="dropdown">
                            <button class="dropbtn">硬體預約</button>
                            <div class="dropdown-content">
                                <a href="#">Link 1</a>
                                <a href="#">Link 2</a>
                                <a href="#">Link 3</a>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="dropdown">
                            <button class="dropbtn">塗鴉牆</button>
                            <div class="dropdown-content">
                                <a href="#">Link 1</a>
                                <a href="#">Link 2</a>
                                <a href="#">Link 3</a>
                            </div>
                        </div>
                    </li>
                </ul>
                <i class="bi bi-list mobile-nav-toggle"></i>
            </nav>
            <!-- .navbar -->

            <div class="navbar">
                <ul>
                    <li>
                        <div>
                            <a href=""><i class="bi bi-chat-dots" style="font-size: 25px; color: black"></i></a>
                        </div>
                    </li>
                    <li>
                        <div>
                            <a href=""><i class="bi bi-chat" style="font-size: 25px; color: black"></i></a>
                        </div>
                    </li>
                    <li>
                        <div class="dropdown">
                            <button class="dropbtn">
                                <i class="bi bi-bell" style="font-size: 25px"></i>
                            </button>
                            <div class="dropdown-content nav-item-right">
                                <a href="#">Link 1</a>
                                <a href="#">Link 2</a>
                                <a href="#">Link 3</a>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="dropdown">
                            <button class="dropbtn">
                                <i class="bi bi-person" style="font-size: 25px"></i>
                            </button>
                            <div class="dropdown-content nav-item-right">
                                <a href="#">Link 1</a>
                                <a href="#">Link 2</a>
                                <a href="#">Link 3</a>
                            </div>
                        </div>
                    </li>
                </ul>
                <!-- <a href="#" class="twitter"><i class="bi bi-twitter"></i></a>
                    <a href="#" class="facebook"><i class="bi bi-facebook"></i></a> -->
                <!-- <a href="#" class="instagram"><i class="bi bi-bell"></i></a>
                    <a href="#" class="linkedin"><i class="bi bi-person-fill"></i></i></a> -->
            </div>
        </div>
    </header>
    <!-- End Header -->

    <!-- content 如果頁面要可以往下滑就改一下main的height值吧 -->
         <main style="height: 120vh; border:3px red solid; margin-top:40px;">
                        <div
                            style="border:3px blue solid;width:900px;position:absolute; height:630px; top:45%; margin-top:-160px;margin-left: 14%;">
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
                               <span>${reportVO.starttime}</span>
                                  
                                <span class="input-group-text" id="basic-addon2">回報最新編輯時間</span>
                              <span>${reportVO.updatetime}</span>  
                                <span class="input-group-text" id="basic-addon2">回報結束時間</span>
                              <span>${reportVO.endtime}</span>
                                
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text" id="basic-addon2">回報人</span>
                             <span>${reportVO.reporter}</span>
                                
                                <span class="input-group-text" id="basic-addon2">處理人</span>
                              <span>${reportVO.handler}</span>
                                  
                            </div>
                            <label for="basic-url" class="form-label">回報內容</label>
                            <div class="input-group mb-3" style=" height:200px">
                                <textarea class="form-control" aria-label="With textarea">${reportVO.content}</textarea>
                            </div>

                            <div class="input-group mb-3">
                                <label class="input-group-text" for="inputGroupFile01">回報圖片</label>                              
                              <img src="<%=request.getContextPath()%>/util/DBGifReader?id_key=report_id&id=${reportVO.report_id}&table=report&pic=report_image" style="width:100px; height:100px;">
                            </div>

                            <div class="input-group">
                                <span class="input-group-text">處理訊息</span>
                                <textarea class="form-control" aria-label="With textarea">Test</textarea>
                            </div>
                        </div>
                    </main>
    <!-- ======= Footer ======= -->
    <footer id="footer">
        <div class="container">
            <div class="copyright">
                &copy; Copyright <strong><span>WorkFun</span></strong>. All Rights Reserved
            </div>
        </div>
    </footer>
    <!-- End  Footer -->

    <div id="preloader"></div>
    <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i
            class="bi bi-arrow-up-short"></i></a>

    <!-- Vendor JS Files -->
    <script src="${pageContext.request.contextPath}/assets/vendor/purecounter/purecounter.js"></script>
    <script src="${pageContext.request.contextPath}/assets/vendor/aos/aos.js"></script>
    <script src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/vendor/glightbox/js/glightbox.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/vendor/swiper/swiper-bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/vendor/waypoints/noframework.waypoints.js"></script>
    <script src="${pageContext.request.contextPath}/assets/vendor/php-email-form/validate.js"></script>

    <!-- Template Main JS File -->
    <script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</body>

</html>