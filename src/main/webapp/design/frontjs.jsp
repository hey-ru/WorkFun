<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


        <!-- Vendor JS Files -->
        <script src="${pageContext.request.contextPath}/assets/vendor/purecounter/purecounter.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendor/aos/aos.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendor/glightbox/js/glightbox.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendor/swiper/swiper-bundle.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendor/waypoints/noframework.waypoints.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/jquery/jquery.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
            crossorigin="anonymous"></script>
            
        
      <!-- ionicon link -->
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
		<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
            

        <!-- Template Main JS File -->
        <script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
        <!-- 地址 -->
        <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://demeter.5fpro.com/tw/zipcode-selector.js"></script>
<!--         SweetAlert -->
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script type="text/javascript">
		let checkextension = /^\d{4}$/;
		$("#extension").blur(function() {
			if ($(this).val() == '') {
				$('#extension_error').text('請勿空白!')
			} else if (checkextension.test($(this).val())) {
				$('#extension_error').text('')
			} else {
				$('#extension_error').text('請輸入四個數字')
			}
		});
	</script>
	 <script type="text/javascript">
		
		$("#selectname").blur(function() {
			if ($(this).val() == '') {
				$('#selectname_error').text('請勿空白!')
			} 
		});
	</script>