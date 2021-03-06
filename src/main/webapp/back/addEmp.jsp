<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

			<main style="height: 120vh; margin-top: -300px;">
			
						
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/empServlet" name="form1"
							enctype="multipart/form-data">
							<div
								style=" width: 900px;  height: 600px; top: 45%; margin-top: 350px; margin-left: 5%;">
								<div><h1>新增員工帳號</h1></div>
								<div class="input-group mb-3" style="margin-top: 0px;">
								
									<span class="input-group-text" id="xx">員工姓名</span> <input
										type="TEXT" name="empName" size="45" value="${param.empName}"
										class="form-control" aria-label="Username"
										aria-describedby="basic-addon1" required="required">
											<font color="red">	${errorMsgs.empName}</font>
								</div>
							
<jsp:useBean id="depSvc" scope="page"
									class="com.dep.model.DepService" />
								<div class="input-group mb-3">
									<span class="input-group-text" id="basic-addon2">部門</span> <select
										size="1" name="depId" class="input-group-text"
										id="basic-addon3">
										<c:forEach var="depVO" items="${depSvc.all}">
											<option value="${depVO.depId}"
												${(param.depId==depVO.depId)? 'selected':'' }>${depVO.depName}
										</c:forEach>

									</select> <span class="input-group-text" id="basic-addon2">雇用日期</span> <input
										name="hiredate" id="f_date1" type="text" class="form-control"
										value="${param.hiredate}" required="required">


								</div>

								<div class="input-group mb-3">
									<span class="input-group-text" id="basic-addon2">手機</span> <input
										type="TEXT" name="phone" size="45" value="${param.phone}"
										class="form-control" required="required"
										aria-label="Recipient's username"
										aria-describedby="basic-addon2"> 
										
									
										<span
										class="input-group-text" id="basic-addon2">分機</span> <input
										type="TEXT" name="extension" size="45"
										value="${param.extension}" class="form-control" placeholder=""
										aria-label="Recipient's username" required="required"
										aria-describedby="basic-addon2"> <span
										class="input-group-text" id="basic-addon2">興趣</span> <input
										type="TEXT" name="hobby" size="45" value="${param.hobby}"
										class="form-control" placeholder=""
										aria-label="Recipient's username"
										aria-describedby="basic-addon2">
										<font color="red">	${errorMsgs.extension}${errorMsgs.dupextension}${errorMsgs.dupphone}</font>

								</div>
								<div class="input-group mb-3">
									<span class="input-group-text" id="basic-addon2">專長</span> <input
										type="TEXT" name="skill" size="45" value="${param.skill}"
										class="form-control" aria-label="Recipient's username"
										aria-describedby="basic-addon2"> <span
										class="input-group-text" id="basic-addon2">信箱</span> <input
										type="TEXT" name="mail" size="45" value="${param.mail}"
										class="form-control" aria-label="Recipient's username"
										aria-describedby="basic-addon2" required="required">
										<font color="red">${errorMsgs.dupmail}${errorMsgs.mailcheck}</font>
									
								</div>
								
								
								<div class="input-group mb-3">
									<span class="input-group-text" >生日</span> <input
									id="f_date2"
										type="TEXT" name="birthday" size="45"
										value="${param.birthday}" class="form-control"
										aria-label="Recipient's username"
										aria-describedby="basic-addon2" required="required">

								</div>


								<div class="input-group mb-3">
									<label class="input-group-text" for="inputGroupFile01">大頭照</label>
									
											<div class="col-md-8 col-lg-9">
											
											
												
												<div class="pt-2">
													
												</div>
											</div>
									
								</div>
								<div>	<input type="file" name="empProfile" size="45"
												 class="form-control-plaintext"
													id="profile" required="required">
													
														<img id="profileimg">
													<br></div>
							
	

								<div class="input-group mb-3">

									<input type="hidden" name="action" value="insert"> <input
										type="submit" value="送出新增"  class="btn btn-outline-dark"
										id="basic-addon2" style="margin:auto">
								</div>
								</div>
						</FORM>
						</main>
						</div>
</body>


                    <!-- /.container-fluid -->
                </div>
                <!-- End of Main Content -->
            </div>
            <!-- End of Content Wrapper -->
        </div>
        <!-- End of Page Wrapper -->
	</div>
        <!-- Scroll to Top Button-->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>
     
<%@ include file="/design/backjs.jsp"%>



<% 
  java.sql.Date hiredate = null;
  try {
	    hiredate = java.sql.Date.valueOf(request.getParameter("hiredate").trim());
   } catch (Exception e) {
	    hiredate = new java.sql.Date(System.currentTimeMillis());
   }
%>

<% 
  java.sql.Date birthday = null;
  try {
	  birthday = java.sql.Date.valueOf(request.getParameter("birthday").trim());
   } catch (Exception e) {
	   birthday = new java.sql.Date(System.currentTimeMillis());
   }
%>
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
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
 	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=hiredate%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           minDate:               '<%=hiredate%>', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });

        $('#f_date2').datetimepicker({
  	       theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=birthday%>', // value:   new Date(),
            //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
            //startDate:	            '2017/07/10',  // 起始日
            maxDate:               '<%=birthday%>', // 去除今日(不含)之前
            //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
         });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
<script>
document.getElementById("profile").onchange = addImg;
function addImg(e) {
	 let url = URL.createObjectURL(e.target.files[0])
	let profileimg=document.getElementById("profileimg");
    profileimg.setAttribute("src",url);
  profileimg.setAttribute("width", 200)
                profileimg.setAttribute("length", 200)
	
}


</script>
</body>

</html>