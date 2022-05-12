<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>:: WorkFun ::</title>

        <!-- Favicons -->
        <link href="${pageContext.request.contextPath}/assets/img/wf.png" rel="icon">
        <link href="${pageContext.request.contextPath}/assets/img/wf.png" rel=" apple-touch-icon">

        <!-- Google Fonts -->
        <link
            href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
            rel="stylesheet">

        <!-- Vendor CSS Files -->
        <link href="${pageContext.request.contextPath}/assets/vendor/aos/aos.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css"
            rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

        <!-- Template Main CSS File -->
        <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">
        
        <!-- final css -->
            <style>
                    html {
                        --header-height: 80px;
                        --footer-height: 50px;
                    }

                    html,
                    body {
                        height: 100%
                    }

                    header {
                        height: var(--header-height);
                    }
                    .headerdiv  {
                        height: var(--header-height);
                    }

                    .wrapper {
                        min-height: calc(100% - var(--footer-height));
                    }

                    footer {
                        height: var(--footer-height);
                    }
                    html {
					    overflow: -moz-hidden-unscrollable;
					    height: 100%;
					}
					
					body::-webkit-scrollbar {
					    display: none;
					}
					
					body {
					    -ms-overflow-style: none;
					    height: 100%;
					 width: calc(100vw + 18px);
					 overflow: auto;
					}
                </style>