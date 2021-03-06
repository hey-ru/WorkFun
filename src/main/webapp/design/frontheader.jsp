<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <!-- ======= Header ======= -->
    <header id="header" class="fixed-top">
        <div class="container-fluid d-flex justify-content-between">
            <a href="<%=request.getContextPath()%>/home/home.jsp" class="logo"><img src="<%=request.getContextPath()%>/assets/img/workfun.gif" alt="" class="img-fluid"
                    style="width: 250px;"></a>
            

            <nav id="navbar" class="navbar order-lg-0">
                <ul class="navbar-left">
                    <!-- 修改跳頁 -->
                    <li>
                        <div class="dropdown">
                            <button class="dropbtn">揪團訂餐</button>
                            <div class="dropdown-content">
                                <a href="<%=request.getContextPath()%>/groupbuy/gbHome.jsp"> 瀏覽揪團 </a>
                                <a href="<%=request.getContextPath()%>/shop/listAllShop.jsp">查詢店家</a>
                                <a href="<%=request.getContextPath()%>/groupbuy/owner_selectGB.jsp">查詢揪團</a>
                                <a href="<%=request.getContextPath()%>/groupbuylist/buyer_selectGB.jsp">查詢參團</a>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="dropdown">
                        <button class="dropbtn">二手競標</button>
							<div class="dropdown-content">
								<a href="<%=request.getContextPath()%>/secondhand/secondHandHome.jsp">競標首頁</a>
								<a href="<%=request.getContextPath()%>/secondhand/addSecondHand.jsp">新增競標</a>
<%-- 								<a href="<%=request.getContextPath()%>/secondhand/SecondHandServlet?action=listSecondHandsBySaler&saler=${empVO.empId}">我的競標</a> --%>
								<a href="<%=request.getContextPath()%>/secondhand/listByMySaled.jsp">我的競標</a>
								<a href="<%=request.getContextPath()%>/secondhand/listByMyWon.jsp">我的得標</a>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="dropdown">
                            <button class="dropbtn">問題回報</button>
                            <div class="dropdown-content">
                                <a href="<%=request.getContextPath()%>/report/listAllReport.jsp">回報首頁</a>
                                <a href="<%=request.getContextPath()%>/report/addReport.jsp">新增回報</a>
                                <a href="<%=request.getContextPath()%>/report/listOwnReport.jsp">查看自己回報</a>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="dropdown">
                            <button class="dropbtn">器材預約</button>
                            <div class="dropdown-content">
                                <a href="<%=request.getContextPath()%>/booking/frontBookingHome.jsp">器材首頁</a>
                                <a href="<%=request.getContextPath()%>/booking/bookingList.jsp">瀏覽預約單</a>
                            </div>
                        </div>
                    </li>
                 
                </ul>
                </nav>

                            <div style="margin-top:20px">   <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/empServlet" name="form1"
							enctype="multipart/form-data">
                               <input type="text" placeholder="輸入分機查詢" name="extension" style="width:125px" id="extension"> 
                     <span id="extension_error" style="color:red"></span>
                               <input type="hidden" name="action" value="selectByExtension">
                               </FORM>

                               </div>
                                       
                               <div style="margin-top:20px">
                                  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/empServlet" name="form1"
							enctype="multipart/form-data">
                                 <input type="text"placeholder="輸入姓名查詢" name="empName" style="width:125px" id="selectname">
                                    <input type="hidden" name="action" value="selectByEmpName">
                                     <span id="selectname_error" style="color:red"></span>
                                 </FORM> 
                                    
                            </div>
                   
                        <!-- 個人頁面dropdown -->
                        <nav class="navbar order-last">
                        <ul class="navbar-right">
             
                        <li> 
                            <div class="dropdown">
                                <i class="bi bi-chat-dots" style="font-size:25px"></i>
                                <div class="dropdown-content nav-item-right">
                                    <a href="<%=request.getContextPath()%>/chat/chatRoomPublic.jsp">聊天囉</a>
<!--                                     <a href="#">聊天二</a> -->
<!--                                     <a href="#">聊天三</a> -->
                                </div>
                            </div>
                        </li>
                      
                        
                        <li class="nav-item dropdown pe-3">

                            <a class="nav-link nav-profile d-flex align-items-center pe-0 ; width:2px" href="#"
                                data-bs-toggle="dropdown">
                                <img src="
									<%=request.getContextPath()%>/util/DBGifReader?pic=emp_profile&table=emp&id_key=emp_id&id=${empVO.empId}" alt="Profile" class="rounded-circle"
                                    style="width: 50px;">
                            </a><!-- End Profile Iamge Icon -->

                            <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile show"
                                style="position: absolute; inset: 0px 0px auto auto; margin: 15px; transform: translate3d(-16px, 54px, 0px);"
                                data-popper-placement="bottom-end">
                                <li class="dropdown-header">
                                    <h6>${empVO.empName }</h6>
                                    <span></span><!-- 可輸入 -->
                                </li>
                                <li>
                                    <hr class="dropdown-divider">
                                </li>
                                <li>
                                    <a class="dropdown-item d-flex align-items-center" href="<%=request.getContextPath()%>/emp/frontProfile.jsp">
                                        <i class="bi bi-person"></i>
                                        <span>個人資料</span>
                                    </a>
                                </li>
                                <li>
                                    <hr class="dropdown-divider">
                                </li>
                                <li>
                                    <a class="dropdown-item d-flex align-items-center" href="<%=request.getContextPath()%>/back/backmain.jsp" >
                                        <i class="bi bi-gear"></i>
                                        <span>後台</span>
                                    </a>
                                </li>
                                <li>
                                    <hr class="dropdown-divider">
                                </li>
                                <li>
                                    <a class="dropdown-item d-flex align-items-center" href="<%=request.getContextPath()%>/empServlet?action=logout">
                                        <i class="bi bi-box-arrow-right"></i>
                                        <span>登出</span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <!-- End Profile Nav -->
                    </ul>
                    </nav>
                 <i class="bi bi-list mobile-nav-toggle"></i>
                
        </div>

    </header>