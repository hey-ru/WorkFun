<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ page import="com.emp.model.*"%>
	<%@ page import="com.announcement.model.*"%>
	<%@ page import="com.announcement_mapping.model.*"%>
	<%
	Integer announcement_id=Integer.valueOf(request.getParameter("announcement_id")) ;  
	    Announcement_mappingService annmapSvc = new Announcement_mappingService();
	
	    	List<Integer> list = annmapSvc.findByPrimaryKey(announcement_id);
	    pageContext.setAttribute("list",list);
	    Integer oldquantity=list.size();
	    request.setAttribute("oldquantity", oldquantity);
	%>
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
                    <!-- ???????????? -->
<main style="height: 120vh; margin-top: 230px;">
						
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/announcementServlet" name="form1"
							enctype="multipart/form-data">
							<div
								style=" width: 900px; height: 600px; top: 45%; margin-top: -200px; margin-left: 5%;">
										<div><h1>????????????</h1></div>
								<div class="input-group mb-3" style="margin-top: 0px;">
									<span class="input-group-text" id="xx">?????????</span> 		<input
										type="text" name="announcer" size="45" value="${empVO.empName}"
										readonly class="form-control-plaintext" id="staticEmail">
								
							 <input
										type="hidden" name="announcer" size="45" value="${param.announcer}"
										class="form-control" aria-label="Username"
										aria-describedby="basic-addon1" >
								</div>

							
							

								<div class="input-group mb-3">
									<span class="input-group-text" id="basic-addon2">????????????</span> <input 
										type="TEXT" name="announcement_title" size="45" value="${param.announcement_title}"
										 required="required" class="borderg"
										> 

								</div>
								
								<div class="input-group mb-3">
									<span class="input-group-text" id="basic-addon2">????????????</span> 
									<textarea name="announcement_content" rows="15" cols="30" class="borderg" style="width:826px">${param.announcement_content}</textarea>
									
									
							

								</div>
								
								
								
								
								
								
								
								<c:forEach var="announcement_mapping" items="${list}" >
									<div class="input-group mb-3">

					<img style="width:200px;height:200px"
												src="
									<%=request.getContextPath()%>/servlet/com.announcement.controller.DBGifReader?announcementImg_id=${announcement_mapping}&announcement_id=${param.announcement_id}
									"
												class="img-fluid"
												
												
											>	
										
											<input id="profile" type="file" name="${announcement_mapping}$" >
											<img id="profileimg">
												<input type="hidden" name="$" value="${announcement_mapping}" >	
										
									</div>
								
								</c:forEach>
								<label class="input-group-text" for="inputGroupFile01" style="width:90px">????????????</label>
								
									<div class="input-group mb-3" id="55">
									
									
									
									
								
								<%-- 	<input type="file" name="empProfile" size="45"
										value="${param.empProfile}" class="form-control"
										id="inputGroupFile01" > --%>
										<input  id="quantity" name="quantity" type="hidden">
											<input value="${oldquantity}" name="oldquantity" type="hidden">
								</div>
							
								<div class="input-group mb-3">
	<input type="hidden" name="announcement_id" value="${param.announcement_id}"> 
									<input type="radio" name="announcement_status" value="1" ${param.announcement_status==1 ? "checked" : ""}> <label>??????</label>
										<input type="radio" name="announcement_status" value="2" ${param.announcement_status==2 ? "checked" : ""}><label>?????????</label>
										
										
										
										
										
										
										
								</div>
								


								


								<div class="input-group mb-3">
	<input type="hidden" name="announcement_id" value="${param.announcement_id}"> 
									<input type="hidden" name="action" value="update"> <input
										type="submit" value="????????????" class="btn btn-outline-dark"
										id="basic-addon2" style="margin:auto">
								</div>
								</div>
						</FORM>
							
							<div style=" width: 300px;  height: 600px; margin-top: 40px;margin-left: 540px ">
								<c:forEach var="announcement_mapping" items="${list}" >
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/announcementServlet" name="form1"
							enctype="multipart/form-data">
						
									<input type="hidden" name="announcementImg_id" value="${announcement_mapping}" >	
								<input
										type="submit" value="??????" class="btn btn-outline-dark"
										id="basic-addon2" style="z-index:100;position: relative;">
											<input type="hidden" name="announcement_id" value="${param.announcement_id}"> 
											<input type="hidden" name="action" value="deleteImg"> 
									</FORM>
									<br>
									<br>
									<br>
									<br>
									<br>
									<br>
									<br>
									<br>
									
							</c:forEach>
							</div>
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





<script>

$('#55').css('display', 'initial');

let quantity = document.getElementById("quantity")
let body = document.getElementById("55")
let inputnum = 1;
quantity.setAttribute("value", inputnum-1)
let input = document.createElement("input");
let img = document.createElement("img");
let br = document.createElement("br");
let div = document.createElement("div");
div.setAttribute("width",10)
        div.setAttribute("height",10)
input.setAttribute("class", "new")
    input.setAttribute("name", inputnum)
input.setAttribute("id", inputnum)
input.setAttribute("type", "file")
  input.setAttribute("width", 245)
input.setAttribute("accept", "image/*")
img.setAttribute("width", 200)
img.setAttribute("height", 200)
body.append(input)
body.append(img)
body.append(br)
document.getElementById(inputnum).onchange = addImg;
function addImg(e) {
    let br1 = document.createElement("br");
    let url = URL.createObjectURL(e.target.files[0])
       let div1 = document.createElement("div");
        div1.setAttribute("width",10)
        div1.setAttribute("height",10)
     
    if (e.target.getAttribute("class") === "new") {
        inputnum++;
        e.target.nextSibling.setAttribute("src", url);
        e.target.removeAttribute("class");
        input = document.createElement("input");
        img = document.createElement("img");
        input.setAttribute("id", inputnum)
           input.setAttribute("width", 245)
        input.setAttribute("class", "new")
        input.setAttribute("type", "file")
            input.setAttribute("name", inputnum)
        input.setAttribute("accept", "image/*")
        
        img.setAttribute("width", 200)
        img.setAttribute("height", 200)
        body.append(input)
        body.append(img)
 body.append(br1)
      
        quantity.setAttribute("value", inputnum - 1)
    }
    else {
        let url = URL.createObjectURL(e.target.files[0])
        e.target.nextSibling.setAttribute("src", url);

    }
    document.getElementById(inputnum).onchange = addImg;
}

    </script>
   <!--  <script>
document.getElementById("profile").onchange = addImg;
function addImg(e) {
	 let url = URL.createObjectURL(e.target.files[0])
	let profileimg=document.getElementById("profileimg");
    profileimg.setAttribute("src",url);
  profileimg.setAttribute("width", 200)
                profileimg.setAttribute("height", 200)
	
}


</script> -->



</body>

</html>