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
                    <main style="height: 120vh; margin-top: 40px;">
						
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/announcementServlet" name="form1"
							enctype="multipart/form-data">
							<div
								style=" width: 900px; position: absolute; height: 600px; top: 45%; margin-top: -200px; margin-left: 5%;">
								<div class="input-group mb-3" style="margin-top: 0px;">
									<span class="input-group-text" id="xx">發布者</span> <a
										
										class="form-control" aria-label="Username"
										aria-describedby="basic-addon1">${param.announcer_name}</a>
								
									<span class="input-group-text" id="xx">發布者</span> <input
										type="hidden" name="announcer" size="45" value="${param.announcer}"
										class="form-control" aria-label="Username"
										aria-describedby="basic-addon1" required="required">
								</div>

							
							

								<div class="input-group mb-3">
									<span class="input-group-text" id="basic-addon2">公告標題</span> <input
										type="TEXT" name="announcement_title" size="45" value="${param.announcement_title}"
										class="form-control" required="required"
										aria-label="Recipient's username"
										aria-describedby="basic-addon2"> 

								</div>
								<div class="input-group mb-3">
									<span class="input-group-text" id="basic-addon2">公告內容</span> <input
										type="TEXT" name="announcement_content" size="45" value="${param.announcement_content}"
										class="form-control" aria-label="Recipient's username"
										aria-describedby="basic-addon2"> 
								</div>
								
								<c:forEach var="announcement_mapping" items="${list}" >
圖片${announcement_mapping}
					<img style="width:200px;height:200px"
												src="
									<%=request.getContextPath()%>/servlet/com.announcement.controller.DBGifReader?announcementImg_id=${announcement_mapping}&announcement_id=${param.announcement_id}
									"
												class="img-fluid"
												
												
											>	
										
										
								
								
								</c:forEach>
								
							
							


								


								<div class="input-group mb-3">
	<input type="hidden" name="announcement_id" value="${param.announcement_id}"> 
									<input type="hidden" name="action" value="update"> <input
										type="submit" value="修改" class="input-group-text"
										id="basic-addon2">
								</div>
								</div>
						</FORM>
							
							<div>
						
							</div>
								
						
						
						
						</main>
                    
                    

                    <!-- ======= 內容結束 ======= -->

            </div>
            <!-- ======= Footer ======= -->
            <%@ include file="/design/frontfooter.jsp" %>
                <!-- ======= js ======= -->
                <%@ include file="/design/frontjs.jsp" %>

        </body>

        </html>