<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.announcement.model.*"%>
<%@ page import="java.util.*"%>
<%
AnnouncementService annSvc = new AnnouncementService();
List<AnnouncementVO> list = annSvc.getAll();
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html lang="zh-TW">

<head>
<%@ include file="/design/frontmetacss.jsp"%>
<style>
#hero {
	width: 100%;
	max-height: calc(100vh - var(--footer-height));
	background: url("<%=request.getContextPath()%>/home/img/homepic.png");
	background-size: cover;
	box-sizing: border-box;
}
</style>




</head>

<body>

	<!-- ======= Header ======= -->
	<%@ include file="/design/frontheader.jsp"%>
	<!-- End Header -->

	<!-- ======= Hero Section ======= -->
	<section id="hero">
	
		<div class="row">
	
			<div class="col-12" style="height: 1px;">
				<div class="container d-flex flex-column align-items-center"
					data-aos="zoom-in" data-aos-delay="100">
					<h1>Welcome to WorkFun</h1>
					<h2>Work together makes work fun!</h2>
				</div>

			</div>
		</div>
		<div id="sectionannouncement"
			style="margin-left: 100px; margin-top: 200px; width: 85vw; min-height: 40vh;">
			
			
			
			
			<div class="row" >
				<div class="col-6"></div>
				<div class="col-4" style="border-radius: 10px;">
					<!-- Advanced Tables -->

					<div class="table-responsive"
						style="font-family: Andale Mono, monospace;">
						<table class="table table-hover text-dark" id="dataTables-example">
							<tr>
								<th class="text-light" style="background-color: #97A5C0;"><h4
										style="text-align: center;">
										<strong> 📢  W o r k F u n 公 告 </strong>
									</h4></th>
							</tr>
							<div style="text-align: right;">
							<%@ include file="/home/page1.file"%>
							</div>
							<tbody>
								<c:forEach var="announcement" items="${list}"
									begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

									<tr>
										<td><a class="text-dark"
											href="<%=request.getContextPath()%>/announcementServlet?action=getOne&announcement_id=${announcement.announcement_id}">${announcement.announcement_title}</a>
											<div class="text-end">
												<fmt:formatDate value="${announcement.announcement_time}"
													pattern="yyyy-MM-dd HH:mm" />
											</div></td>

									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div>
<%-- 							<%@ include file="/home/page2.file"%> --%>
						</div>
					</div>

				</div>

				<!-- /.container-fluid -->
			</div>
		</div>
	</section>
	<!-- End Hero -->

	<!-- ======= Footer ======= -->
	<%@ include file="/design/frontfooter.jsp"%>


	<!-- ======= js ======= -->

	<%@ include file="/design/frontjs.jsp"%>
	<script type="text/javascript">
		$("tbody tr").css("background-color", function(index) {
			return index % 2 == 0 ? "#BECBD3" : "#DEE1CB";
		});
	</script>


	<script type="text/javascript">
		let rule1 = /^[(\u4e00-\u9fa5)(\u0800-\u4e00)a-zA-Z0-9_\s\\(\\-\\)]*$/;
		$("#title").blur(function() {
			if ($(this).val() == '') {
				$('#title_error').text('請填入標題!')
			} else if (rule1.test($(this).val())) {
				$('#title_error').text('')
			} else {
				$('#title_error').text('標題名稱:只能是中日英文字母、數字')
			}
		});
	</script>
</body>

</html>