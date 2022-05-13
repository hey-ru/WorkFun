<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.post.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
PostService poSvc = new PostService();
List<PostVO> list = poSvc.getAll();
pageContext.setAttribute("list", list);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <!-- Favicons -->
    <link href="${pageContext.request.contextPath}/assets/img/favicon.png" rel="icon">
    <link href="${pageContext.request.contextPath}/assets/img/apple-touch-icon.png" rel="apple-touch-icon">
    <!-- Vendor CSS Files -->
    <link href="${pageContext.request.contextPath}/assets/vendor/aos/aos.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

    <!-- Template Main CSS File -->
    <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">
</head>
<body>
<!-- ======= Header ======= -->
	<%@ include file="/design/frontheader.jsp"%>
	<!-- End Header -->
	<!-- content 如果頁面要可以往下滑就改一下main的height值吧 -->
	<main id="main">

	 	<c:forEach var="poVO" items="${list}">
	 		
	 		<h2>${poVO.post_title}</h2>
	 		      <div class="entry-meta">
	 		      <div class="entry-img">
                                <img src="Flexor/assets/img/blog/blog-1.jpg" alt="" class="img-fluid">
                            </div>
                                <ul>
                                    <li class="d-flex align-items-center"><i class="bi bi-person"></i>${poVO.emp_id}</li>
                                    <li class="d-flex align-items-center"><i class="bi bi-clock"></i><time datetime="2020-01-01">${poVO.post_createtime}</time>
                                    </li>
                                    <li class="d-flex align-items-center"><i class="bi bi-chat-dots"></i>10 Comments</li>
                                </ul>
                            </div>
                             <div class="entry-content">
                                <p>
                                    ${poVO.post_content}
                                </p>
                            </div>
	 	
	</c:forEach>
			
	</main>
	<!-- ======= Footer ======= -->
	<%@ include file="/design/frontfooter.jsp"%>
	<!-- End  Footer -->
	<%@ include file="/design/frontjs.jsp"%>
</body>
</html>