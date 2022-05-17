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
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>PostAll</title>
    <meta content="" name="description">
    <meta content="" name="keywords">


    <!-- Vendor CSS Files -->
    <link href="Flexor/assets/vendor/aos/aos.css" rel="stylesheet">
    <link href="Flexor/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="Flexor/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="Flexor/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
    <link href="Flexor/assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
    <link href="Flexor/assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

    <!-- Template Main CSS File -->
    <link href="Flexor/assets/css/style.css" rel="stylesheet">
    <%@ include file="/design/frontmetacss.jsp"%>
</head>

<body>
<!-- ======= Header ======= -->
	<%@ include file="/design/frontheader.jsp"%>
	<!-- End Header -->
    <main id="main">
     
	<c:forEach var="poVO" items="${list}">
        <!-- ======= Blog Single Section ======= -->
        <section id="blog" class="blog">
            <div class="container" data-aos="fade-up">

                <div class="row">

                    <div class="col-lg-8 entries">

                        <article class="entry entry-single">

                            <div class="entry-img">
                                <img src="Flexor/assets/img/blog/blog-1.jpg" alt="" class="img-fluid">
                            </div>

                            <h2 class="entry-title">
                                <span>${poVO.post_title}</span>
                            </h2>

                            <div class="entry-meta">
                                <ul>
                                    <li class="d-flex align-items-center"><i class="bi bi-person"></i>${poVO.emp_id}</li>
                                    <li class="d-flex align-items-center"><i class="bi bi-clock"></i>
                                    <time><fmt:formatDate type="both" dateStyle="medium" timeStyle="medium" value="${poVO.post_createtime}" /></time>
                                    </li>
                                    <li class="d-flex align-items-center"><i class="bi bi-chat-dots"></i> <a
                                            href="blog-single.html">12 Comments</a></li>
                                </ul>
                            </div>

                            <div class="entry-content">
                                <p>
                                   ${poVO.post_content}
                                </p>
                            </div>

                        </article><!-- End blog entry -->

                        <div class="blog-comments">

                            <h4 class="comments-count">4 Comments</h4>

                            <div id="comment-1" class="comment">
                                <div class="d-flex">
                                    <div class="comment-img"><img src="Flexor/assets/img/blog/comments-1.jpg" alt="">
                                    </div>
                                    <div>
                                        <h5><span>${poVO.emp_id}</span><i
                                                    class="bi bi-reply-fill"></i> Reply</h5>
                                        <time datetime="2020-01-01">01 Jan, 2020</time>
                                        <p>
                                            Et rerum totam nisi. Molestiae vel quam dolorum vel voluptatem et et. Est ad
                                            aut sapiente quis molestiae est qui cum soluta.
                                            Vero aut rerum vel. Rerum quos laboriosam placeat ex qui. Sint qui facilis
                                            et.
                                        </p>
                                    </div>
                                </div>
                            </div><!-- End comment #1 -->

                            <div id="comment-2" class="comment">
                                <div class="d-flex">
                                    <div class="comment-img"><img src="Flexor/assets/img/blog/comments-2.jpg" alt="">
                                    </div>
                                    <div>
                                        <h5><a href="">Aron Alvarado</a> <a href="#" class="reply"><i
                                                    class="bi bi-reply-fill"></i> Reply</a></h5>
                                        <time datetime="2020-01-01">01 Jan, 2020</time>
                                        <p>
                                            Ipsam tempora sequi voluptatem quis sapiente non. Autem itaque eveniet
                                            saepe. Officiis illo ut beatae.
                                        </p>
                                    </div>
                                </div>

                                <div id="comment-reply-1" class="comment comment-reply">
                                    <div class="d-flex">
                                        <div class="comment-img"><img src="Flexor/assets/img/blog/comments-3.jpg"
                                                alt=""></div>
                                        <div>
                                            <h5><a href="">Lynda Small</a> <a href="#" class="reply"><i
                                                        class="bi bi-reply-fill"></i> Reply</a></h5>
                                            <time datetime="2020-01-01">01 Jan, 2020</time>
                                            <p>
                                                Enim ipsa eum fugiat fuga repellat. Commodi quo quo dicta. Est ullam
                                                aspernatur ut vitae quia mollitia id non. Qui ad quas nostrum rerum sed
                                                necessitatibus aut est. Eum officiis sed repellat maxime vero nisi
                                                natus. Amet nesciunt nesciunt qui illum omnis est et dolor recusandae.
                                        </div>
                                    </div>

                                    <div id="comment-reply-2" class="comment comment-reply">
                                        <div class="d-flex">
                                            <div class="comment-img"><img src="Flexor/assets/img/blog/comments-4.jpg"
                                                    alt="">
                                            </div>
                                            <div>
                                                <h5><a href="">Sianna Ramsay</a> <a href="#" class="reply"><i
                                                            class="bi bi-reply-fill"></i> Reply</a></h5>
                                                <time datetime="2020-01-01">01 Jan, 2020</time>
                                                <p>
                                                    Et dignissimos impedit nulla et quo distinctio ex nemo. Omnis quia
                                                    dolores cupiditate et. Ut unde qui eligendi sapiente omnis ullam.
                                                    Placeat porro est commodi est officiis voluptas repellat quisquam
                                                    possimus. Perferendis id consectetur necessitatibus.
                                                </p>
                                            </div>
                                        </div>

                                    </div><!-- End comment reply #2-->

                                </div><!-- End comment reply #1-->

                            </div><!-- End comment #2-->

                            </div>

                        </div><!-- End blog comments -->

                    </div><!-- End blog entries list -->

                </div>

        </section><!-- End Blog Single Section -->
	</c:forEach>
    </main><!-- End #main -->

    <!-- ======= Footer ======= -->

    <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i
            class="bi bi-arrow-up-short"></i></a>

    <!-- Vendor JS Files -->
    <script src="Flexor/assets/vendor/aos/aos.js"></script>
    <script src="Flexor/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="Flexor/assets/vendor/glightbox/js/glightbox.min.js"></script>
    <script src="Flexor/assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
    <script src="Flexor/assets/vendor/swiper/swiper-bundle.min.js"></script>
    <script src="Flexor/assets/vendor/php-email-form/validate.js"></script>

    <!-- Template Main JS File -->
    <script src="Flexor/assets/js/main.js"></script>
    <!-- ======= Footer ======= -->
	<%@ include file="/design/frontfooter.jsp"%>
	<!-- End  Footer -->
	<%@ include file="/design/frontjs.jsp"%>

</body>

</html>