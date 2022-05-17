<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.report.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%

ReportService repSvc = new ReportService();
List<ReportVO> list = repSvc.getAll();
pageContext.setAttribute("list", list);
int itemsPerPage = 10;

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>backListAll</title>
<%@ include file="/design/backcss.jsp"%>
 <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
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
				<main>
						<!-- 從這裡開始 -->
						<div>
							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<h5 class="m-0 font-weight-bold text-info">
										<strong>回報LIST</strong>
									</h5>
								</div>
								<div class="card-body">
									<div>
										<div id="dataTable_wrapper"
											class="dataTables_wrapper dt-bootstrap4">

											<div class="row">
												<div class="col-sm-12">

													<input type="hidden" name="action" value="getOne">
													<table class="table table-bordered dataTable"
														id="dataTable"  role="grid"
														aria-describedby="dataTable_info" style="width: 100%;">
														<ul>  
  <li>   
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/report/backListHandler.jsp" name="form1">

       <b>處理人:</b>
       <select size="1" name="handler" >
          <option value="1016">AIDEN</option>
          <option value="1017">SAM</option>
          <option value="1019">Rora</option>
       </select>
                   
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="listByHandlerQuery">
     </FORM>
  </li>
</ul>
														<thead>

															<tr role="row">
																<th class="sorting sorting_asc" tabindex="0"
																	aria-controls="dataTable" rowspan="1" colspan="1"
																	aria-sort="ascending"
																	aria-label="Name: activate to sort column descending"
																	style="">回報標題</th>
																<th class="sorting sorting_asc" tabindex="0"
																	aria-controls="dataTable" rowspan="1" colspan="1"
																	aria-sort="ascending"
																	aria-label="Name: activate to sort column descending"
																	style="">類型</th>
																<th class="sorting" tabindex="0"
																	aria-controls="dataTable" rowspan="1" colspan="1"
																	aria-label="Position: activate to sort column ascending"
																	style="">回報時間</th>
																<th class="sorting" tabindex="0"
																	aria-controls="dataTable" rowspan="1" colspan="1"
																	aria-label="Office: activate to sort column ascending"
																	style="">最新處理時間</th>
																<th class="sorting" tabindex="0"
																	aria-controls="dataTable" rowspan="1" colspan="1"
																	aria-label="Start date: activate to sort column ascending"
																	style="">回報人</th>
																	<th class="sorting" tabindex="0"
																	aria-controls="dataTable" rowspan="1" colspan="1"
																	aria-label="Start date: activate to sort column ascending"
																	style="">處理人</th>	
																
																<th class="sorting" tabindex="0"
																	aria-controls="dataTable" rowspan="1" colspan="1"
																	aria-label="Salary: activate to sort column ascending"
																	style="">回報狀態</th>
																<th class="sorting" tabindex="0"
																	aria-controls="dataTable" rowspan="1" colspan="1"
																	aria-label="Salary: activate to sort column ascending"
																	style="">處理</th>
																<th class="sorting" tabindex="0"
																	aria-controls="dataTable" rowspan="1" colspan="1"
																	aria-label="Salary: activate to sort column ascending"
																	style="">轉發</th>
																<th class="sorting" tabindex="0"
																	aria-controls="dataTable" rowspan="1" colspan="1"
																	aria-label="Salary: activate to sort column ascending"
																	style="">完成</th>	
																	
															</tr>
														</thead>
														<tbody>
															<%@ include file="/design/page1.file"%>
															<c:forEach var="reportVO" items="${list}"
																begin="<%=pageIndex%>"
																end="<%=pageIndex+rowsPerPage-1%>">
																<tr class="odd">
																			
																	<td>${reportVO.title}</td>
																	<td class="sorting_1"><c:if
																			test="${reportVO.report_type==0}">添購新品</c:if> <c:if
																			test="${reportVO.report_type==1}">損壞報修</c:if> <c:if
																			test="${reportVO.report_type==2}">軟硬體問題</c:if> <c:if
																			test="${reportVO.report_type==3}">其他</c:if></td>
																	<td><fmt:formatDate value="${reportVO.starttime}"
																			pattern="yyyy-MM-dd HH:mm " /></td>
																	<td><fmt:formatDate value="${reportVO.updatetime}"
																	pattern="yyyy-MM-dd HH:mm " /></td>
																	<td>${reportVO.empVO1.empName}</td>
																	<td>${reportVO.empVO2.empName}</td>
																	<td><c:if test="${reportVO.status==0}">已發送</c:if>
																		<c:if test="${reportVO.status==1}">處理中</c:if> <c:if
																			test="${reportVO.status==2}">待確認</c:if> <c:if
																			test="${reportVO.status==3}">取消</c:if> <c:if
																			test="${reportVO.status==4}">已完成</c:if></td>
																			<c:if test="${empVO.empId==reportVO.handler}">
																			<c:if test="${reportVO.status!=4}">
																	<td><a
																		href="/CGA101G3/reportServlet?report_id=${reportVO.report_id}&action=getOne_forComment">
																			<button type="button" value="getOne_forComment"
																				class="btn btn-info">處理</button>
																	</a></td>
																	<td><a
																		href="/CGA101G3/reportServlet?report_id=${reportVO.report_id}&action=getOne_forModify">
																			<button type="button" value="getOne_forModify"
																				class="btn btn-info">轉發</button>
																	</a></td>
																	<td><a
																		href="/CGA101G3/reportServlet?report_id=${reportVO.report_id}&action=complete">
																			<button type="button" value="complete"
																				class="btn btn-info">完成</button>
																	</a></td>
														</c:if>
														</c:if>
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
    return index%2==0?"lightgray":"";
});
</script>
</body>

</html>