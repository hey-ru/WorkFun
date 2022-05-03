<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.report.model.*"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />

    <title>:: WorkFun_addReport ::</title>
  <%@ include file="/design/frontmetacss.jsp"%>

</head>

<body>
    <!-- ======= Header ======= -->
    <%@ include file="/design/frontheader.jsp"%>
    <!-- End Header -->

    <!-- content 如果頁面要可以往下滑就改一下main的height值吧 -->
    
    <main style="height: 120vh; border:3px red solid; margin-top:80px;">
    <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/reportServlet" name="formAdd" enctype="multipart/form-data">
        <div
            style="border:3px blue solid;width:900px;position:absolute; height:650px; top:50%; margin-top:-175px;margin-left: 10%;">
             <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon1">回報者</span>
                <span class="form-control" aria-label="Username"
                    aria-describedby="basic-addon1">${empVO.empName}</span>
            </div>
            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon1">接收者</span>
                <input type="text" class="form-control" aria-label="Username" name="handler"
                    aria-describedby="basic-addon1" value="${param.handler}">
            </div>
            
            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon1">狀態</span>
                <input type="text" class="form-control" aria-label="Username" name="status"
                    aria-describedby="basic-addon1" value="${param.status}">
            </div>
            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon1">標題</span>
                <input type="text" class="form-control" aria-label="Username" name="title"
                    aria-describedby="basic-addon1" value="${param.title}">
                    <p style="color :red">${errorMsgs.title}</p>
            </div>

            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon2">類型</span>
                <label><input type="radio" name="report_type" value="0" ${(param.is_item=="0")? 'checked':'' }> 添購新品 </label>
				<label><input type="radio" name="report_type" value="1" ${(param.is_item=="1")? 'checked':'' }> 損壞報修 </label>
				<label><input type="radio" name="report_type" value="2" ${(param.is_item=="2")? 'checked':'' }> 軟硬體問題 </label>
				<label><input type="radio" name="report_type" value="3" ${(param.is_item=="3")? 'checked':'' }> 其他 </label>
            </div>

            <label for="basic-url" class="form-label">回報內容</label>
            <div class="input-group mb-3" style=" height:150px">
                <input type="text" class="form-control" aria-label="Recipient's username" name="content"
                    aria-describedby="basic-addon2" value="${param.content}">
                   <p style="color :red">${errorMsgs.content}</p>
            </div>

            <div class="input-group mb-3">
                <label class="input-group-text" for="inputGroupFile01">Upload</label>
                <input type="file" value="${param.report_image}" name="report_image" accept="image/*" oninput="pic.src=window.URL.createObjectURL(this.files[0])"><img style="height:150px; width:150px" id="pic" />
                <input type="text" style="display:none;" name="action" value="insert">
				<button type="submit" value="送出新增">送出</button>
            </div>
        </div>
        </FORM>
    </main>
    <!-- ======= Footer ======= -->
    	<%@ include file="/design/frontfooter.jsp"%>
    <!-- End  Footer -->

    <!-- Vendor JS Files -->
    <%@ include file="/design/frontjs.jsp"%>

    <!-- Template Main JS File -->
    <script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</body>
</html>