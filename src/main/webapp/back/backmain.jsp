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
        <ul class="navbar-nav bg-dark sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
         	<a class="sidebar-brand d-flex align-items-center justify-content-center"
				href="backmain.jsp">
				<div class="sidebar-brand-text mx-3">
					<h2 class="font-weight-bold">WorkFun</h2>
				</div>
			</a>

            <!-- Divider -->
                      
            <hr class="sidebar-divider my-0">
                     
          <!--   <div style="height: 1px;">
            </div> -->
	<c:forEach var="permissionIds" items="${empPm}">
				<c:if test="${permissionIds==2}" var="condition">
            <!-- Nav Item - Pages Collapse Menu第一項 -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
                    aria-expanded="true" aria-controls="collapseTwo">
                    <i class="fas fa-fw fa-cog"></i>
                    <span>部門群組管理</span>
                </a>
                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <!-- <h6 class="collapse-header">Custom Components:</h6> -->
                        <a class="collapse-item" href="button1s.html">新增部門</a>
                        <a class="collapse-item" href="cards.html">修改部門</a>
                        <a class="collapse-item" href="cards.html">查詢部門</a>
                        <a class="collapse-item" href="cards.html">刪除部門</a>
                    </div>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseThree"
                    aria-expanded="true" aria-controls="collapseTwo">
                    <i class="fas fa-fw fa-cog"></i>
                    <span>員工帳號管理</span>
                </a>
                <div id="collapseThree" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <!-- <h6 class="collapse-header">Custom Components:</h6> -->
                <a class="collapse-item" href="<%=request.getContextPath()%>/back/addEmp.jsp">新增員工帳號</a>
                        <a class="collapse-item" href="<%=request.getContextPath()%>/back/listAllEmp.jsp">全部員工帳號</a>
        
                     
                    </div>
                </div>
            </li>
            
              <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseThree1"
                    aria-expanded="true" aria-controls="collapseTwo">
                    <i class="fas fa-fw fa-cog"></i>
                    <span>員工權限管理</span>
                </a>
                <div id="collapseThree1" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <!-- <h6 class="collapse-header">Custom Components:</h6> -->
                <a class="collapse-item"  href="<%=request.getContextPath()%>/back/permission.jsp">修改員工權限</a>
                   
       
                     
                    </div>
                </div>
            </li>
                    
                  </c:if> 
				</c:forEach>	
                    
            <c:forEach var="permissionIds" items="${empPm}">
				<c:if test="${permissionIds==4}" var="condition">
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseFive"
                    aria-expanded="true" aria-controls="collapseTwo">
                    <i class="fas fa-fw fa-cog"></i>
                    <span>回報管理</span>
                </a>
                <div id="collapseFive" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <!-- <h6 class="collapse-header">Custom Components:</h6> -->

                        <a class="collapse-item" href="cards.html">查詢任務</a>
                        <a class="collapse-item" href="cards.html">刪除任務</a>
                    </div>
                </div>
            </li>
                  </c:if> 
				</c:forEach>	
                        
            <c:forEach var="permissionIds" items="${empPm}">
				<c:if test="${permissionIds==3}" var="condition">
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseSeven"
                    aria-expanded="true" aria-controls="collapseTwo">
                    <i class="fas fa-fw fa-cog"></i>
                    <span>預約管理</span>
                </a>
                <div id="collapseSeven" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <!-- <h6 class="collapse-header">Custom Components:</h6> -->
                        <a class="collapse-item" href="buttons.html">查詢硬體</a>
                        <a class="collapse-item" href="cards.html">新增硬體</a>
                        <a class="collapse-item" href="cards.html">刪除硬體</a>
                        <a class="collapse-item" href="cards.html">修改硬體狀態</a>
                        <a class="collapse-item" href="cards.html">查詢預約紀錄</a>
                        <a class="collapse-item" href="cards.html">違規處理</a>

                    </div>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseSex"
                    aria-expanded="true" aria-controls="collapseTwo">
                    <i class="fas fa-fw fa-cog"></i>
                    <span>揪團店家管理</span>
                </a>
                <div id="collapseSex" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <!-- <h6 class="collapse-header">Custom Components:</h6> -->
                        <a class="collapse-item" href="buttons.html">下架店家菜單</a>
                        <a class="collapse-item" href="cards.html">下架揪團</a>
                    </div>
                </div>
            </li>
            
                  </c:if> 
				</c:forEach>	
            
             
            <c:forEach var="permissionIds" items="${empPm}">
				<c:if test="${permissionIds==1}" var="condition">
            
            
               <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseFour"
                    aria-expanded="true" aria-controls="collapseTwo">
                    <i class="fas fa-fw fa-cog"></i>
                    <span>公告管理</span>
                </a>
                <div id="collapseFour" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <!-- <h6 class="collapse-header">Custom Components:</h6> -->
                        <a class="collapse-item" href="buttons.html">新增公告</a>
                        <a class="collapse-item" href="cards.html">修改公告</a>
                        <a class="collapse-item" href="cards.html">查詢公告</a>
                        <a class="collapse-item" href="cards.html">刪除公告</a>
                    </div>
                </div>
            </li>
                    
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseEight"
                    aria-expanded="true" aria-controls="collapseTwo">
                    <i class="fas fa-fw fa-cog"></i>
                    <span>塗鴉牆管理</span>
                </a>
                <div id="collapseEight" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <!-- <h6 class="collapse-header">Custom Components:</h6> -->

                        <a class="collapse-item" href="cards.html">查詢貼文</a>
                        <a class="collapse-item" href="cards.html">刪除貼文</a>
                    </div>
                </div>
            </li>
            
               </c:if> 
				</c:forEach>	
            
        </ul>
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

</body>

</html>