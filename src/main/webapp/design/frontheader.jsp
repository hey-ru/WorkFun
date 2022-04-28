<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <!-- ======= Header ======= -->
    <header id="header" class="fixed-top">
        <div class="container-fluid d-flex justify-content-between align-items-center">
            <a href="<%=request.getContextPath()%>/home/home.jsp" class="logo"><img src="<%=request.getContextPath()%>/home/img/workfun.gif" alt="" class="img-fluid"
                    style="width: 250px;"></a>

            <nav id="navbar" class="navbar order-last order-lg-0">
                <ul>
                    <!-- 修改跳頁 -->
                    <li>
                        <div class="dropdown">
                            <button class="dropbtn">揪團訂餐</button>
                            <div class="dropdown-content">
                                <a href="#"> 主頁 </a>
                                <a href="<%=request.getContextPath()%>/shop/listAllShop">查詢店家</a>
                                <a href="#">查詢揪團</a>
                                <a href="#">查詢參團</a>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="dropdown">
                        <button class="dropbtn">二手競標</button>
							<div class="dropdown-content">
								<a href="<%=request.getContextPath()%>/secondhand/secondHandHome.jsp">競標首頁</a> <a
									href="<%=request.getContextPath()%>/secondhand/addSecondHand.jsp">新增競標</a> <a href="#">購買記錄</a>
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
                            <button class="dropbtn">器材預約</button>
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
                <div style="margin-left: 120px;"> </div>
                <!-- 左邊icon -->
                <div class="navbar">
                    <ul>
                        <li>
                            <a href="">
                                <i class="bi bi-chat-dots" style="font-size:25px; color: black;"></i>
                            </a>
                        </li>
                        <li>
                            <a href=""><i class="bi bi-chat" style="font-size:25px; color: black;"></i></a>
                        </li>
                        <li>
                            <div class="dropdown">
                                <i class="bi bi-bell" style="font-size:25px"></i>
                                <div class="dropdown-content nav-item-right">
                                    <a href="#">訊息一</a>
                                    <a href="#">訊息二</a>
                                    <a href="#">訊息三</a>
                                </div>
                            </div>
                        </li>

                        <!-- 個人頁面dropdown -->
                        <li class="nav-item dropdown pe-3">

                            <a class="nav-link nav-profile d-flex align-items-center pe-0 ; width:2px" href="#"
                                data-bs-toggle="dropdown">
                                <img src="<%=request.getContextPath()%>/assets/img/wu.jpeg" alt="Profile" class="rounded-circle"
                                    style="width: 50px;">
                            </a><!-- End Profile Iamge Icon -->

                            <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile show"
                                style="position: absolute; inset: 0px 0px auto auto; margin: 15px; transform: translate3d(-16px, 54px, 0px);"
                                data-popper-placement="bottom-end">
                                <li class="dropdown-header">
                                    <h6>Peter Wu</h6>
                                    <span>Java Consultant</span>
                                </li>
                                <li>
                                    <hr class="dropdown-divider">
                                </li>
                                <li>
                                    <a class="dropdown-item d-flex align-items-center" href="<%=request.getContextPath()%>/emp/empfront.jsp">
                                        <i class="bi bi-person"></i>
                                        <span>My Profile</span>
                                    </a>
                                </li>
                                <li>
                                    <hr class="dropdown-divider">
                                </li>
                                <li>
                                    <a class="dropdown-item d-flex align-items-center" href="#">
                                        <i class="bi bi-gear"></i>
                                        <span>Account Settings</span>
                                    </a>
                                </li>
                                <li>
                                    <hr class="dropdown-divider">
                                </li>
                                <li>
                                    <a class="dropdown-item d-flex align-items-center" href="#">
                                        <i class="bi bi-box-arrow-right"></i>
                                        <span>Sign Out</span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <!-- End Profile Nav -->
                    </ul>
                </div>
            </nav>
        </div>

    </header><!-- End Header -->