<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Login</title>

    <!-- Custom fonts for this template-->
    <link href="<%=request.getContextPath()%>/assets4login/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<%=request.getContextPath()%>/assets4login/css/sb-admin-2.min.css" rel="stylesheet">
 	<style>
 	.bg-login-image {
    background: url(<%=request.getContextPath()%>/assets4login/img/dog2.jpeg);
    
}
 	</style>
</head>

<body class="bg-gradient-primary">

    <div class="container">

        <!-- Outer Row -->
        <div class="row justify-content-center">

            <div class="col-xl-10 col-lg-12 col-md-9">

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="row">
                            <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
                            <div class="col-lg-6">
                                <div class="p-5">
                                    <div class="text-center">
                                    <br>
                                    <br>
                                    <br>
                                        <h1 class="h4 text-gray-900 mb-4">重新設定密碼</h1>
                                    </div>
                                    <br>
                                   <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/empServlet" name="form1"
							enctype="multipart/form-data">
                                        <div class="form-group">
                                        
                                           <input
										type="password" name="newpassword1" size="45" 
										class="form-control" aria-label="Username" 
										aria-describedby="basic-addon1">
										<div>
										<br>
										<br>
										</div>
										  <input
										type="password" name="newpassword2" size="45" 
										class="form-control" aria-label="Username" 
										aria-describedby="basic-addon1">
										 <input type="hidden" name="empId" value="${param.empId}"> 
										 <font color="red"> ${errorMsgs.password} ${errorMsgs.checkPassword}${errorMsgs.comparepassword}</font>
                                        </div>   
                                     
                                        
                                    <br>
                                     
                                     <br>
                                     
                                    <br>
                                       <input
										type="submit" value="修改密碼" class="input-group-text bg-info"
										id="basic-addon2" style="width:345px; color:white; justify-content: center;">
                                    
                                  
                                       
                                        
                                        <input type="hidden" name="action" value="forgotchangepassword"> 
                                    </FORM>
                                
                                    
                   
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>

    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="<%=request.getContextPath()%>/assets4login/vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="<%=request.getContextPath()%>/assets4login/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="<%=request.getContextPath()%>/assets4login/js/sb-admin-2.min.js"></script>

</body>

</html>