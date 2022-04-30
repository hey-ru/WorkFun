<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
										data-bs-target="#profile-overview">Overview</button>
								</li>

								<li class="nav-item">
									<button class="nav-link" data-bs-toggle="tab"
										data-bs-target="#profile-edit">Edit Profile</button>
								</li>

								<li class="nav-item">
									<button class="nav-link" data-bs-toggle="tab"
										data-bs-target="#profile-settings">Settings</button>
								</li>

								<li class="nav-item">
									<button class="nav-link" data-bs-toggle="tab"
										data-bs-target="#profile-change-password">Change
										Password</button>
								</li>

							</ul>
							<div class="tab-content pt-2">

								<div class="tab-pane fade show active profile-overview"
									id="profile-overview">
									<h5 class="card-title">About</h5>
									<p class="small fst-italic">${empVO.empId}</p>

									<h5 class="card-title"><img style="width:200px;length:200px"
												src="
									<%=request.getContextPath()%>/util/DBGifReader?pic=emp_profile&table=emp&id_key=emp_id&id=${empVO.empId}
									"
												class="img-fluid"
											></h5>

									<div class="row">
										<div class="col-lg-3 col-md-4 label ">${empVO.empName}</div>
										<div class="col-lg-9 col-md-8">${empVO.phone}</div>
									</div>

									<div class="row">
										<div class="col-lg-3 col-md-4 label">${empVO.extension}</div>
										<div class="col-lg-9 col-md-8">${empVO.hobby}</div>
									</div>

									<div class="row">
										<div class="col-lg-3 col-md-4 label">${empVO.skill}</div>
										<div class="col-lg-9 col-md-8">${empVO.birthday}</div>
									</div>

									<div class="row">
										<div class="col-lg-3 col-md-4 label">${empVO.mail}</div>
										<div class="col-lg-9 col-md-8">USA</div>
									</div>

									<div class="row">
										<div class="col-lg-3 col-md-4 label">Address</div>
										<div class="col-lg-9 col-md-8">A108 Adam Street, New
											York, NY 535022</div>
									</div>

									<div class="row">
										<div class="col-lg-3 col-md-4 label">Phone</div>
										<div class="col-lg-9 col-md-8">(436) 486-3538 x29071</div>
									</div>

									<div class="row">
										<div class="col-lg-3 col-md-4 label">Email</div>
										<div class="col-lg-9 col-md-8">${errorMsgs.empId}${errorMsgs.empName}${errorMsgs.depId}${errorMsgs.hiredate}${errorMsgs.phone}${errorMsgs.extension}</div>
									</div>

								</div>

								<div class="tab-pane fade profile-edit pt-3" id="profile-edit">

									<!-- Profile Edit Form -->
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/empServlet" name="form1"
							enctype="multipart/form-data">
										<div class="row mb-3">
											<label for="profileImage"
												class="col-md-4 col-lg-3 col-form-label">Profile
												Image</label> <img style="width:200px;length:200px"
												src="
									<%=request.getContextPath()%>/util/DBGifReader?pic=emp_profile&table=emp&id_key=emp_id&id=${empVO.empId}
									"
												class="img-fluid"
												
											>
											<div class="row mb-3">
											<label for="about" class="col-md-4 col-lg-3 col-form-label">編號</label>
											<div class="col-md-8 col-lg-9">
												<input type="hidden" name="empId" size="45"
													value="${empVO.empId}" class="form-control"
													aria-label="Recipient's username"
													aria-describedby="basic-addon2">
													<input
										type="hidden" name="hiredate" size="45" id="f_date2"
										value="${empVO.hiredate}" class="form-control"
										aria-label="Recipient's username"
										aria-describedby="basic-addon2">
											<input
										type="hidden" name="where" size="45" id="f_date2"
										value="<%=request.getContextPath()%>/emp/" class="form-control"
										aria-label="Recipient's username"
										aria-describedby="basic-addon2">
										
											</div>
										</div>


											<div class="col-md-8 col-lg-9">
											
												<input type="file" name="empProfile" size="45"
												 class="form-control"
													id="inputGroupFile01">
												<div class="pt-2">
													<a href="#" class="btn btn-primary btn-sm"
														title="Upload new profile image"><i
														class="bi bi-upload"></i></a> <a href="#"
														class="btn btn-danger btn-sm"
														title="Remove my profile image"><i class="bi bi-trash"></i></a>
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
											<jsp:useBean id="deptSvc" scope="page"
												class="com.dep.model.DepService" />
										</div>
										<div class="row mb-3">
											<label for="Email" class="col-md-4 col-lg-3 col-form-label">部門</label>
											<div class="col-md-8 col-lg-9">
												<select size="1" name="depId" class="input-group-text"
													id="basic-addon3">
													<c:forEach var="deptVO" items="${deptSvc.all}">
														<option value="${deptVO.depId}"
															${(empVO.depId==deptVO.depId)? 'selected':'' }>${deptVO.depName}
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
											</div>
										</div>

										<div class="row mb-3">
											<label for="company" class="col-md-4 col-lg-3 col-form-label">分機</label>
											<div class="col-md-8 col-lg-9">
												<input type="TEXT" name="extension" size="45"
													value="${empVO.extension}" class="form-control"
													 aria-label="Recipient's username"
													aria-describedby="basic-addon2">
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
											</div>
										</div>

										<div class="row mb-3">
											<label for="Email" class="col-md-4 col-lg-3 col-form-label">生日</label>
											<div class="col-md-8 col-lg-9">
												<input type="TEXT" name="birthday" size="45"
													value="${empVO.birthday}" class="form-control"
													aria-label="Recipient's username"
													aria-describedby="basic-addon2">
											</div>
										</div>








										<div class="text-center">
											<input type="hidden" name="action" value="updateFront">
											<button type="submit" class="input-group-text"
												id="basic-addon2">Save Changes</button>
										</div>
									</form>
									<!-- End Profile Edit Form -->

								</div>

								<div class="tab-pane fade pt-3" id="profile-settings">

									<!-- Settings Form -->
									<form>

										<div class="row mb-3">
											<label for="fullName"
												class="col-md-4 col-lg-3 col-form-label">Email
												Notifications</label>
											<div class="col-md-8 col-lg-9">
												<div class="form-check">
													<input class="form-check-input" type="checkbox"
														id="changesMade" checked> <label
														class="form-check-label" for="changesMade">
														Changes made to your 	 </label>
												</div>
												<div class="form-check">
													<input class="form-check-input" type="checkbox"
														id="newProducts" checked> <label
														class="form-check-label" for="newProducts">
														Information on new products and services </label>
												</div>
												<div class="form-check">
													<input class="form-check-input" type="checkbox"
														id="proOffers"> <label class="form-check-label"
														for="proOffers"> Marketing and promo offers </label>
												</div>
												<div class="form-check">
													<input class="form-check-input" type="checkbox"
														id="securityNotify" checked disabled> <label
														class="form-check-label" for="securityNotify">
														Security alerts </label>
												</div>
											</div>
										</div>

										<div class="text-center">
											<button type="submit" class="btn btn-primary">Save
												Changes</button>
										</div>
									</form>
									<!-- End settings Form -->

								</div>

								<div class="tab-pane fade pt-3" id="profile-change-password">
									<!-- Change Password Form -->
									<form>

										<div class="row mb-3">
											<label for="currentPassword"
												class="col-md-4 col-lg-3 col-form-label">Current
												Password</label>
											<div class="col-md-8 col-lg-9">
												<input name="password" type="password" class="form-control"
													id="currentPassword">
											</div>
										</div>

										<div class="row mb-3">
											<label for="newPassword"
												class="col-md-4 col-lg-3 col-form-label">New
												Password</label>
											<div class="col-md-8 col-lg-9">
												<input name="newpassword" type="password"
													class="form-control" id="newPassword">
											</div>
										</div>

										<div class="row mb-3">
											<label for="renewPassword"
												class="col-md-4 col-lg-3 col-form-label">Re-enter
												New Password</label>
											<div class="col-md-8 col-lg-9">
												<input name="renewpassword" type="password"
													class="form-control" id="renewPassword">
											</div>
										</div>

										<div class="text-center">
											<button type="submit" class="btn btn-primary">Change
												Password</button>
										</div>
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

        </body>

        </html>