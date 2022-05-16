<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Sidebar -->
        <ul class="navbar-nav bg-dark sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
         	<a class="sidebar-brand d-flex align-items-center justify-content-center"
				href="<%=request.getContextPath()%>/back/backmain.jsp">
				<div class="sidebar-brand-text mx-3">
					<h2 class="font-weight-bold" href="<%=request.getContextPath()%>/back/backmain.jsp">WorkFun</h2>
				</div>
			</a>

            <!-- Divider -->
                      
            <hr class="sidebar-divider my-0">
                     
          <!--   <div style="height: 1px;">
            </div> -->
            
            
            
                      
	<c:forEach var="permissionIds" items="${empPm}">
				<c:if test="${permissionIds==1}" var="condition">
             <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseAnn"
                    aria-expanded="true" aria-controls="#collapseAnn">
                    <i class="fas fa-fw fa-cog"></i>
                    <span>公告管理</span>
                </a>
                <div id="collapseAnn" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <!-- <h6 class="collapse-header">Custom Components:</h6> -->
                        <a class="collapse-item" href="<%=request.getContextPath()%>/announcement/addannouncement.jsp">新增公告</a>
                       
                        <a class="collapse-item" href="<%=request.getContextPath()%>/announcement/listallannouncement.jsp">全部公告</a>
                     
                    </div>
                </div>
            </li>
             </c:if> 
				</c:forEach>
            
            
            
            
            
            
            
	<c:forEach var="permissionIds" items="${empPm}">
				<c:if test="${permissionIds==2}" var="condition">
            <!-- Nav Item - Pages Collapse Menu第一項 -->
          <!--   <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
                    aria-expanded="true" aria-controls="collapseTwo">
                    <i class="fas fa-fw fa-cog"></i>
                    <span>部門群組管理</span>
                </a>
                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Custom Components:</h6>
                        <a class="collapse-item" href="button1s.html">新增部門</a>
                        <a class="collapse-item" href="cards.html">修改部門</a>
                        <a class="collapse-item" href="cards.html">查詢部門</a>
                        <a class="collapse-item" href="cards.html">刪除部門</a>
                    </div>
                </div>
            </li> -->
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

                        <a class="collapse-item" href="<%=request.getContextPath()%>/report/backListAll.jsp">查詢回報</a>
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
                        <a class="collapse-item" href="<%=request.getContextPath()%>/equipment/backEquipmentHome.jsp">查詢器材總覽</a>
<!--                         <a class="collapse-item" href="cards.html">新增硬體</a> -->
<!--                         <a class="collapse-item" href="cards.html">刪除硬體</a> -->
<!--                         <a class="collapse-item" href="cards.html">修改硬體狀態</a> -->
                        <a class="collapse-item" href="<%=request.getContextPath()%>/booking/backBookingAllList.jsp">查詢預約紀錄</a>
<!--                         <a class="collapse-item" href="cards.html">違規處理</a> -->

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
                        <a class="collapse-item" href="<%=request.getContextPath()%>/shop/backmain_Shop.jsp">店家與菜單管理</a>
                        <a class="collapse-item" href="<%=request.getContextPath()%>/groupbuy/gbBack.jsp">揪團管理</a>
                    </div>
                </div>
            </li>
            
                  </c:if> 
				</c:forEach>	
            
             
            
        </ul>
        <!-- End of Sidebar -->