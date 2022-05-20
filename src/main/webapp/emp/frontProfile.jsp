x<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <!DOCTYPE html>
        <html lang="zh-TW">

        <head>
            <%@ include file="/design/frontmetacss.jsp" %>


        </head>

        <body>
            <div class="wrapper">
                <!-- ======= Header ======= -->
                <%@ include file="/design/frontheader.jsp" %>

                    <!-- ======= 內容開始 ======= -->
	<div style="margin-left: 350px;margin-top:90px">
			<section class="section profile">


				<div class="col-xl-8">

					<div class="card">
						<div class="card-body pt-3">
							<!-- Bordered Tabs -->
							<ul class="nav nav-tabs nav-tabs-bordered">

								<li class="nav-item">
									<button class="nav-link active" data-bs-toggle="tab"
										data-bs-target="#profile-overview">個人資料</button>
								</li>

								<li class="nav-item">
									<button class="nav-link" data-bs-toggle="tab"
										data-bs-target="#profile-edit">修改個人資料</button>
								</li>

							

								<li class="nav-item">
									<button class="nav-link" data-bs-toggle="tab"
										data-bs-target="#profile-change-password">修改密碼</button>
								</li>

							</ul>
							<div class="tab-content pt-2">

								<div class="tab-pane fade show active profile-overview"
									id="profile-overview" style="height: max-content;font-weight:bold; font-family: Andale Mono, monospace;">
									<h5 class="card-title">關於我</h5>
									<p >員工編號:${empVO.empId}</p>
<p >大頭照 <img style="width:200px;height:200px"
												src="
									<%=request.getContextPath()%>/util/DBGifReader?pic=emp_profile&table=emp&id_key=emp_id&id=${empVO.empId}
									"
												class="img-fluid"
											></p>
									

						
										<div class="row">
									<div >姓名：${empVO.empName}</div>
									
										
									</div>
									<div class="row">
									<div class="col-lg-9 col-md-8">手機：${empVO.phone}</div>
									
										
									</div>

									
									<div class="row">
									<div class="col-lg-9 col-md-8">分機：${empVO.extension}</div>
									
										
									</div>

									<div class="row">
									<div class="col-lg-9 col-md-8">興趣：${empVO.hobby}</div>
										
										
									</div>
									
									<div class="row">
									<div class="col-lg-9 col-md-8">專長：${empVO.skill}</div>
										
										
									</div>
									<div class="row">
									<div class="col-lg-9 col-md-8">生日：${empVO.birthday}</div>
								
										
									</div>

									<div class="row">
									<div class="col-lg-9 col-md-8">信箱：${empVO.mail}</div>
									
										
									</div>


									

							

								</div>

								<div class="tab-pane fade profile-edit pt-3" id="profile-edit">

									<!-- Profile Edit Form -->
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/empServlet" name="form1"
							enctype="multipart/form-data">
										<div class="row mb-3">
											<label for="profileImage"
												class="col-md-4 col-lg-3 col-form-label">大頭照
										</label> <img style="width:200px;height:200px"
												src="
									<%=request.getContextPath()%>/util/DBGifReader?pic=emp_profile&table=emp&id_key=emp_id&id=${empVO.empId}
									"
												class="img-fluid"
												
											>
											<div class="row mb-3">
											
											<div class="col-md-8 col-lg-9">
												<input type="hidden" name="empId" size="45"
													value="${empVO.empId}" class="form-control"
													aria-label="Recipient's username"
													aria-describedby="basic-addon2">
													<%-- <input
										type="hidden" name="hiredate" size="45" id="f_date2"
										value="${empVO.hiredate}" class="form-control"
										aria-label="Recipient's username"
										aria-describedby="basic-addon2"> --%>
											
										
											</div>
										</div>


											<div class="col-md-8 col-lg-9">
											
												<input type="file" name="empProfile" size="45"
												 class="form-control"
													id="profile">
													<img id="profileimg">
												<div class="pt-2">
													
												</div>
											</div>
										</div>

										<div class="row mb-3">
											<label for="fullName"
												class="col-md-4 col-lg-3 col-form-label">名字</label>
											<div class="col-md-8 col-lg-9">
												<input type="TEXT" name="empName" size="45"
													value="${empVO.empName}" class="form-control"
													aria-label="Username" aria-describedby="basic-addon1">${errorMsgs.empName}
											</div>
											<jsp:useBean id="depSvc" scope="page"
												class="com.dep.model.DepService" />
										</div>
										<div class="row mb-3">
											<label for="Email" class="col-md-4 col-lg-3 col-form-label">部門</label>
											<div class="col-md-8 col-lg-9">
												<select size="1" name="depId" class="input-group-text"
													id="basic-addon3">
													<c:forEach var="depVO" items="${depSvc.all}">
														<option value="${depVO.depId}"
															${(empVO.depId==depVO.depId)? 'selected':'' }>${depVO.depName}
													</c:forEach>

												</select>
											</div>
										</div>


										<div class="row mb-3">
											<label for="about" class="col-md-4 col-lg-3 col-form-label">手機</label>
											<div class="col-md-8 col-lg-9">
												<input type="TEXT" name="phone" size="45"
													value="${empVO.phone}" class="form-control" placeholder=""
													aria-label="Recipient's username"
													aria-describedby="basic-addon2">
													<font color="red">	${errorMsgs.phone}${errorMsgs.dupphone}</font>
											</div>
										</div>

										<div class="row mb-3">
											<label for="company" class="col-md-4 col-lg-3 col-form-label">分機</label>
											<div class="col-md-8 col-lg-9">
												<input type="TEXT" name="extension" size="45"
													value="${empVO.extension}" class="form-control"
													 aria-label="Recipient's username"
													aria-describedby="basic-addon2">
														<font color="red">	${errorMsgs.extension}${errorMsgs.dupextension}</font>
											</div>
										</div>

										<div class="row mb-3">
											<label for="Job" class="col-md-4 col-lg-3 col-form-label">興趣</label>
											<div class="col-md-8 col-lg-9">
												<input type="TEXT" name="hobby" size="45"
													value="${empVO.hobby}" class="form-control" 
													aria-label="Recipient's username"
													aria-describedby="basic-addon2">
											</div>
										</div>



										<div class="row mb-3">
											<label for="Address" class="col-md-4 col-lg-3 col-form-label">專長</label>
											<div class="col-md-8 col-lg-9">
												<input type="TEXT" name="skill" size="45"
													value="${empVO.skill}" class="form-control"
													aria-label="Recipient's username"
													aria-describedby="basic-addon2">
											</div>
										</div>

										<div class="row mb-3">
											<label for="Phone" class="col-md-4 col-lg-3 col-form-label">信箱</label>
											<div class="col-md-8 col-lg-9">
												<input type="TEXT" name="mail" size="45"
													value="${empVO.mail}" class="form-control"
													aria-label="Recipient's username"
													aria-describedby="basic-addon2">
												<font color="red">${errorMsgs.dupmail}${errorMsgs.mailcheck}</font>	
											</div>
										</div>

										<div class="row mb-3">
											<label for="Email" class="col-md-4 col-lg-3 col-form-label">生日</label>
											<div class="col-md-8 col-lg-9">
												<input type="TEXT" name="birthday" size="45"
													value="${empVO.birthday}" class="form-control"
													id="f_date2"
													aria-label="Recipient's username"
													aria-describedby="basic-addon2">
											</div>
										</div>
										








										<div class="text-center">
											<input type="hidden" name="action" value="updateFront">
											<button type="submit" class="btn btn-outline-dark"
												id="basic-addon2">確認修改</button>
										</div>
									</form>
									<!-- End Profile Edit Form -->

								</div>

								<div class="tab-pane fade pt-3" id="profile-settings">

									<!-- Settings Form -->
									
									<!-- End settings Form -->

								</div>

								<div class="tab-pane fade pt-3" id="profile-change-password">
									<!-- Change Password Form -->
									 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/empServlet" name="form1"
							enctype="multipart/form-data">

										<div class="row mb-3">
											<label for="currentPassword"
												class="col-md-4 col-lg-3 col-form-label">目前密碼</label>
											<div class="col-md-8 col-lg-9">
												<input name="nowpassword" type="password" class="form-control"
													id="currentPassword">
													<font color="red">${errorMsgs.	nowpassword }</font>
											</div>
										</div>

										<div class="row mb-3">
											<label for="newPassword"
												class="col-md-4 col-lg-3 col-form-label">新密碼</label>
											<div class="col-md-8 col-lg-9">
												<input name="newpassword1" type="password"
													class="form-control" id="newPassword">
											</div>
											
										
										</div>

										<div class="row mb-3">
											<label for="renewPassword"
												class="col-md-4 col-lg-3 col-form-label">再次輸入新密碼</label>
											<div class="col-md-8 col-lg-9">
												<input name="newpassword2" type="password"
													class="form-control" id="renewPassword">
													<font color="red">	${errorMsgs.checkPassword}</font>
											</div>
												
										</div>
									

										<div class="text-center">
											<button type="submit" class="btn btn-outline-dark">確認修改密碼</button>
										</div>
										<input type="hidden" name="action" value="frontchangepassword">
									</form>
									<!-- End Change Password Form -->

								</div>

							</div>
							<!-- End Bordered Tabs -->

						</div>
					</div>
				</div>

			</section>
		</div>


                    <!-- ======= 內容結束 ======= -->

            </div>
            <!-- ======= Footer ======= -->
            <%@ include file="/design/frontfooter.jsp" %>
                <!-- ======= js ======= -->
                <%@ include file="/design/frontjs.jsp" %>

<script type="text/javascript">
document.getElementById("profile").onchange = addImg;
function addImg(e) {
	 let url = URL.createObjectURL(e.target.files[0])
	let profileimg=document.getElementById("profileimg");
    profileimg.setAttribute("src",url);
  profileimg.setAttribute("width", 200)
                profileimg.setAttribute("height", 200)
	
}
</script>
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
$('#f_date2').datetimepicker({
      theme: '',              //theme: 'dark',
     timepicker:false,       //timepicker:true,
     step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
     format:'Y-m-d'         //format:'Y-m-d H:i:s',
	    // value:   new Date(),
     //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
     //startDate:	            '2017/07/10',  // 起始日
     //minDate:               '-1970-01-01', // 去除今日(不含)之前
     //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
  });
</script>






        </body>

        </html>