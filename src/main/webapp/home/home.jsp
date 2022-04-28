<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-TW">

<head>
     <%@ include file="/design/frontmetacss.jsp" %>
    <style>
        #hero {
            width: 100%;
            max-height: calc(100vh - var(--footer-height));
            background: url("<%=request.getContextPath()%>/home/img/homepic.png") top right;
            background-size: cover;
            opacity: 0.7;
            box-sizing: border-box;
        }
        
    </style>

</head>

<body>

    <!-- ======= Header ======= -->
    <%@ include file="/design/frontheader.jsp" %>
    <!-- End Header -->

    <!-- ======= Hero Section ======= -->
    <section id="hero" class="d-flex align-items-center">
        <div class="container d-flex flex-column align-items-center" data-aos="zoom-in" data-aos-delay="100">
            <h1>Welcome to WorkFun</h1>
            <h2>Work together makes work fun!</h2>
        </div>
    </section><!-- End Hero -->

    <!-- ======= Footer ======= -->
         <%@ include file="/design/frontfooter.jsp" %>
    <!-- ======= js ======= -->
        <%@ include file="/design/frontjs.jsp" %>
    
</body>

</html>