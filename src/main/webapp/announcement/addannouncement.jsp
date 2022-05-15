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
								style="border: 3px blue solid; width: 900px; position: absolute; height: 600px; top: 45%; margin-top: -200px; margin-left: 5%;">
								<div class="input-group mb-3" style="margin-top: 0px;">
									<span class="input-group-text" id="xx">發布者</span> <a
										
									>${empVO.empName}</a>
									<input
										type="hidden" name="announcer" size="45" value="${empVO.empId}"
										class="form-control" aria-label="Username"
										aria-describedby="basic-addon1" >${errorMsgs.announcer}
								</div>
								
							
								<div class="input-group mb-3">
								<span class="input-group-text" id="basic-addon2">公告標題</span> <input
										type="TEXT" name="announcement_title" size="45" value="${param.titile}"
										 required="required"
										> 

							


								</div>

								<div class="input-group mb-3">
									<span class="input-group-text" id="basic-addon2">公告內容</span> 
									<textarea name="announcement_content" rows="3" cols="20">${param.content}</textarea>
									
									
							

								</div>
								<div class="input-group mb-3" id="55">
									<label class="input-group-text" for="inputGroupFile01">圖片</label>
								
								<%-- 	<input type="file" name="empProfile" size="45"
										value="${param.empProfile}" class="form-control"
										id="inputGroupFile01" > --%>
										<input  id="quantity" name="quantity" type="hidden">
										
								</div>
							


								


								<div class="input-group mb-3">
	
									<input type="hidden" name="action" value="insert"> 
									<input
										type="submit" value="建立公告" class="input-group-text"
										id="basic-addon2">
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
                profileimg.setAttribute("length", 200)
	
} */


</script>
<script>
        let quantity = document.getElementById("quantity")
        let body = document.getElementById("55")
        let inputnum = 1;
        quantity.setAttribute("value", inputnum-1)
        let input = document.createElement("input");
        let img = document.createElement("img");
        input.setAttribute("class", "new")
            input.setAttribute("name", inputnum)
        input.setAttribute("id", inputnum)
        input.setAttribute("type", "file")
        input.setAttribute("accept", "image/*")
        img.setAttribute("width", 200)
        img.setAttribute("length", 200)
        body.append(input)
        body.append(img)
        document.getElementById(inputnum).onchange = addImg;
        function addImg(e) {
            let url = URL.createObjectURL(e.target.files[0])
            if (e.target.getAttribute("class") === "new") {
                inputnum++;
                e.target.nextSibling.setAttribute("src", url);
                e.target.removeAttribute("class");
                input = document.createElement("input");
                img = document.createElement("img");
                input.setAttribute("id", inputnum)
                input.setAttribute("class", "new")
                input.setAttribute("type", "file")
                    input.setAttribute("name", inputnum)
                input.setAttribute("accept", "image/*")
                img.setAttribute("width", 200)
                img.setAttribute("length", 200)
                body.append(input)
                body.append(img)
                quantity.setAttribute("value", inputnum - 1)
            }
            else {
                let url = URL.createObjectURL(e.target.files[0])
                e.target.nextSibling.setAttribute("src", url);

            }
            document.getElementById(inputnum).onchange = addImg;
        }

    </script>


</body>

</html>