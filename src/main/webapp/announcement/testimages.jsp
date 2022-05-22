<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ page import="com.announcement.model.*"%>
		<%@ page import="com.announcement_mapping.model.*"%>
	
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
<style>
.borderg{
border: 1px solid #d1d3e2;
color: #6e707e;
padding:0.375rem 0.75rem;

}
</style>

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

                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- 內容放這 -->
<main style="height: 120vh; margin-top: 40px;">
						
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/announcementServlet" name="form1"
							enctype="multipart/form-data">
							<div
								style=" width: 900px; position: absolute; height: 600px; top: 45%; margin-top: -200px; margin-left: 5%;">
										<div><h1>新增公告</h1></div>
								<div class="input-group mb-3" style="margin-top: 0px;">
									<span class="input-group-text" id="xx">發布者</span> 
										<a class="borderg">${empVO.empName}</a>
									
									<input
										type="hidden" name="announcer" size="45" value="${empVO.empId}"
										class="form-control" aria-label="Username"
										aria-describedby="basic-addon1" >${errorMsgs.announcer}
								</div>
								
							
								<div class="input-group mb-3">
								<span class="input-group-text" id="basic-addon2">公告標題</span> <input
										type="TEXT" name="announcement_title" size="45" value="${param.titile}"
										 required="required" class="borderg"
										> 
										

							
   <input type="file" id="multiimg" accept="image/gif, image/jpeg, image/png" multiple="multiple" >
   <div id="imgs" style="width:100%; height: 300px; overflow:scroll;">
       <p>沒有圖片</p>
   </div>




								</div>

								<div class="input-group mb-3">
									<span class="input-group-text" id="basic-addon2">公告內容</span> 
									<textarea name="announcement_content" rows="15" cols="30" style="width:826px" class="borderg">${param.content}</textarea>
									
									
							

								</div>
								
									<label class="input-group-text" for="inputGroupFile01" style="width:90px">新增圖片</label>
									<br>
								<div class="input-group mb-3" id="55">
								
								
								<%-- 	<input type="file" name="empProfile" size="45"
										value="${param.empProfile}" class="form-control"
										id="inputGroupFile01" > --%>
									
										
								</div>
							


								


								<div class="input-group mb-3">
							
	
									<input type="hidden" name="action" value="inserttest"> 
									<input
										type="submit" value="建立公告" class="btn btn-outline-dark"
										id="basic-addon2" style="margin:auto">
								</div>
								</div>
						</FORM>
						</main>
						</div>
</body>






                    <!-- /.container-fluid -->
            
                <!-- End of Main Content -->
       
            <!-- End of Content Wrapper -->
       
        <!-- End of Page Wrapper -->

        <!-- Scroll to Top Button-->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>
     
<%@ include file="/design/backjs.jsp"%>




<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>



<script>
/* document.getElementById("profile").onchange = addImg;
function addImg(e) {
	 let url = URL.createObjectURL(e.target.files[0])
	let profileimg=document.getElementById("profileimg");
    profileimg.setAttribute("src",url);
  profileimg.setAttribute("width", 200)
                profileimg.setAtt	ribute("length", 200)
	
} */


</script>
<script>
$("#multiimg").change(function(){
  $("#imgs").html(""); // 清除預覽
  readURL(this);
});

function readURL(input){
  if (input.files && input.files.length >= 0) {
    for(var i = 0; i < input.files.length; i ++){
      var reader = new FileReader();
      reader.onload = function (e) {
        var img = $("<img width='300' height='200'>").attr('src', e.target.result);
        $("#imgs").append(img);
      }
      reader.readAsDataURL(input.files[i]);
    }
  }else{
     var noPictures = $("<p>沒有圖片</p>");
     $("#imgs").append(noPictures);
  }
}
</script>


</body>

</html>