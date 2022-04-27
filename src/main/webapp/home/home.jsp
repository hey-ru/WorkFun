<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>:: WorkFun ::</title>

    <!-- Favicons -->
    <link href="<%=request.getContextPath()%>/assets/img/wf.png" rel="icon">
    <link href="<%=request.getContextPath()%>/img/wf.png" rel=" apple-touch-icon">

    <!-- Google Fonts -->
    <link
        href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
        rel="stylesheet">

    <!-- Vendor CSS Files -->
    <link href="<%=request.getContextPath()%>/assets/vendor/aos/aos.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

    <!-- Template Main CSS File -->
    <link href="<%=request.getContextPath()%>/assets/css/style.css" rel="stylesheet">
    <style>
        #hero {
            width: 100%;
            height: 100vh;
            background: url("<%=request.getContextPath()%>/home/img/homepic.png") top right;
            background-size: cover;
            opacity: 0.7;
            box-sizing: border-box;
        }
    </style>

</head>

<body>

    <!-- ======= Header ======= -->
    <header id="header" class="fixed-top">
        <div class="container-fluid d-flex justify-content-between align-items-center">
            <a href="<%=request.getContextPath()%>/home/Home.html" class="logo"><img src="../assets/img/workfun.gif" alt="" class="img-fluid"
                    style="width: 250px;"></a>

            <nav id="navbar" class="navbar order-last order-lg-0">
                <ul>
                    <!-- 修改跳頁 -->
                    <li>
                        <div class="dropdown">
                            <button class="dropbtn">揪團訂餐</button>
                            <div class="dropdown-content">
                                <a href="<%=request.getContextPath()%>/groupbuy/gb_home_1.html"> 主頁 </a>
                                <a href="<%=request.getContextPath()%>/groupbuy/shoplist.html">我要揪團</a>
                                <a href="#">查詢揪團</a>
                                <a href="#">查詢參團</a>
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
                                    <a class="dropdown-item d-flex align-items-center" href="#">
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

    <!-- ======= Hero Section ======= -->
    <section id="hero" class="d-flex align-items-center">
        <div class="container d-flex flex-column align-items-center" data-aos="zoom-in" data-aos-delay="100">
            <h1>Welcome to WorkFun</h1>
            <h2>Work together makes work fun!</h2>
        </div>
    </section><!-- End Hero -->

    <!-- ======= Footer ======= -->
    <footer id="footer">
        <div class="container">
            <div class="copyright">
                &copy; Copyright <strong><span>WorkFun</span></strong>. All Rights Reserved
            </div>
        </div>
    </footer><!-- End  Footer -->

    <div id="preloader"></div>
    <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i
            class="bi bi-arrow-up-short"></i></a>

    <!-- Vendor JS Files -->
    <script src="<%=request.getContextPath()%>/assets/vendor/purecounter/purecounter.js"></script>
    <script src="<%=request.getContextPath()%>/assets/vendor/aos/aos.js"></script>
    <script src="<%=request.getContextPath()%>/assets/vendor/glightbox/js/glightbox.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/vendor/swiper/swiper-bundle.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/vendor/waypoints/noframework.waypoints.js"></script>
    <script src="<%=request.getContextPath()%>/assets/vendor/php-email-form/validate.js"></script>
    <script src="<%=request.getContextPath()%>/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/js/jquery/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>

    <!-- Template Main JS File -->
    <script src="<%=request.getContextPath()%>/assets/js/main.js"></script>
</body>

</html>