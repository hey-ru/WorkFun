<%@page import="com.emp.model.EmpService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.groupbuy.model.*"%>

<%
GroupBuyService groupBuySvc = new GroupBuyService();
List<GroupBuyVO> list = groupBuySvc.getAll();
pageContext.setAttribute("list", list);
EmpService empSvc = new EmpService();
int itemsPerPage = 10;
%>

<!DOCTYPE html>
<html lang="zh-TW">

<head>
<%@ include file="/design/backcss.jsp"%>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>WorkFunBack</title>
     <style>
        th{
        vertical-align: middle;
            text-align: center;
        }
        td{
        vertical-align: middle;
            text-align: center;
        }

     .table-responsive {
    	overflow-x:hidden;
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
                <nav class="navbar navbar-expand navbar-light bg-dark topbar static-top shadow">

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav bg-dark ml-auto">

                        <!-- Nav Item - User Information -->
                        <li class="nav-item no-arrow pr-4">
                         <a href="<%=request.getContextPath()%>/home/home.jsp"> <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                Back Home</a>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid pl-0">
                    <!-- 內容放這 -->
                  <main id="main" class="main">
			<div class="card shadow">
				<!-- ============== Card Header ============== -->
				<div class="card-header py-3" style="background-color: #b0c4de">
					<div class="row">
						<div class="col-11" style="height: 20px; display: inline-block;">
							<h5>
								<strong>揪團管理</strong>
							</h5>
						</div>
					</div>
				</div>

				<!-- ============== Card Body ============== -->
				<div class="card-body">
					<div class="row">
							<div class="col-11 row" style="height: 60px; display: inline-block;">
							<div class = col-8>
							<ul class="nav nav-tabs">
								  <li class="nav-item">
								    <a class="nav-link  text-info" 
								    href="<%=request.getContextPath()%>/groupbuy/GroupBuyServlet?action=listByCompositeQueryBack">ALL</a>
								  </li>
								  <li class="nav-item">
								    <a class="nav-link  text-info"
								     href="<%=request.getContextPath()%>/groupbuy/GroupBuyServlet?action=listByCompositeQueryBack&gb_status=0">揪團中</a>
								  </li>
								  <li class="nav-item">
								    <a class="nav-link  text-info" href="<%=request.getContextPath()%>/groupbuy/GroupBuyServlet?action=listByCompositeQueryBack&gb_status=2">截止</a>
								  </li>
								  <li class="nav-item">
								    <a class="nav-link  text-info" href="<%=request.getContextPath()%>/groupbuy/GroupBuyServlet?action=listByCompositeQueryBack&gb_status=3">關閉</a>
								  </li>
								  <li class="nav-item">
								    <a class="nav-link  text-info" href="<%=request.getContextPath()%>/groupbuy/GroupBuyServlet?action=listByCompositeQueryBack&gb_status=1">取消</a>
								  </li>								  
								</ul>
								</div>
								</div>
							<div class="col-11 row" style="height: 60px; display: inline-block;">
							
								<form class="my-1 col-6" METHOD="post" ACTION="<%=request.getContextPath()%>/groupbuy/GroupBuyServlet" name="formsearch">
									<div class="form-groupf" style="display: inline-block">
										<input type="text" class="form-control"
											id="gb_id" placeholder="輸入揪團編號"
											style="border: gray solid 2px;" name="gb_id">
									</div>
									<input type="hidden" name="action" value="listByCompositeQueryBack">
									<button type="submit" class="btn btn-dark mb-2 mt-1 col-2"
										style="display: inline-block;">搜尋</button>
								</form>
							</div>
						</div>

				
					<div class="table-responsive">
						<div id="dataTable_wrapper"
							class="dataTables_wrapper dt-bootstrap4">

							<div class="row">
								<div class="col-sm-12">
									<table class="table table-bordered dataTable" id="dataTable" role="grid"										
										aria-describedby="dataTable_info" style="width: 100%;cellspacing:0;">

										<!-- ============== 表頭(自行增減修改) ============== -->
										<thead>
											<tr role=" row">
												<th class="sorting sorting_asc" tabindex="0"
													aria-controls="dataTable" rowspan="1" colspan="1"
													aria-sort="ascending"
													aria-label="Name: activate to sort column descending"
													>編號</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Position: activate to sort column ascending"
													>店名</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Position: activate to sort column ascending"
													>揪團者</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Position: activate to sort column ascending"
													>開始時間</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Position: activate to sort column ascending"
													>結束時間</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1"
													aria-label="Office: activate to sort column ascending"
													>變更揪團狀態</th>
											</tr>
										</thead>
										
										<%@ include file="/design/page1.file"%>				

										<!-- ============== 表格內容(自行增減修改) ============== -->
										<tbody>
											<c:forEach var="groupBuyVO" items="${list}" begin="<%=pageIndex%>"
										end="<%=pageIndex+rowsPerPage-1%>">																												
													<tr>
														<td>${groupBuyVO.gb_id}</td>
														<td>${groupBuyVO.shop_name}</td>
														<td>${groupBuyVO.gb_owner}<br>${groupBuyVO.empVO.empName}</td>
														<td><fmt:formatDate value="${groupBuyVO.start_time}" pattern="yyyy-MM-dd HH:mm"/>
														</td>
														<td><fmt:formatDate value="${groupBuyVO.end_time}" pattern="yyyy-MM-dd HH:mm"/>
														</td>
<%-- 														<td><fmt:formatDate value="${groupBuyVO.arr_time}" pattern="yyyy-MM-dd HH:mm"/> --%>
<!-- 														</td> -->
<%-- 														<td>${groupBuyVO.min_amt}</td> --%>
														
														<td>
														<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/groupbuy/GroupBuyServlet">
															<select size="1" name="gb_status" size="45" style= "margin-right:20px;">
																	<option value="0" ${(groupBuyVO.gb_status == 0)? 'selected':'' } >揪團中
																	<option value="1" ${(groupBuyVO.gb_status == 1)? 'selected':'' } >取消
																	<option value="2" ${(groupBuyVO.gb_status == 2)? 'selected':'' } >揪團截止
																	<option value="3" ${(groupBuyVO.gb_status == 3)? 'selected':'' } >揪團關閉
															</select>
															
																												
														<input type="submit" class="btn btn-info" value="確認修改" style="margin-bottom: 0px;"> 
														<input type="hidden" name="gb_id" value="${groupBuyVO.gb_id}">
														<input type="hidden" name="action" value="updateGBStatus">										
														</FORM>															
														</td>
														
													</tr>
													
												</c:forEach>
										</tbody>
									</table>
									<%@ include file="/design/page2.file"%>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</main>  
                    
                    
                    
                    
                    
                    
                    


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

<script type="text/javascript">
$("tbody tr").css("background-color", function(index) {
    return index%2==0?"rgba(211,211,211,0.4)":"";
});

</script>

</body>

</html>