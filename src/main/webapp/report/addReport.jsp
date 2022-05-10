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
                <input readonly class="form-control" aria-label="Username" type="hidden"
                    aria-describedby="basic-addon1" name="reporter" value="${empVO.empId}">
                    <span>${empVO.empName}</span>

            </div>
            
            <div class="input-group mb-3" id="input-radio">
                <span class="input-group-text" id="basic-addon2">類型</span>
                
                <label><input id="in0" class ="rptype" type="radio" name="report_type" value="0" ${(param.report_type=="0")? 'checked':'' }> 添購新品 </label>
				<label><input id="in1" class ="rptype" type="radio" name="report_type" value="1" ${(param.report_type=="1")? 'checked':'' }> 損壞報修 </label>
				<label><input id="in2" class ="rptype" type="radio" name="report_type" value="2" ${(param.report_type=="2")? 'checked':'' }> 軟硬體問題 </label>
				<label><input id="in3" class ="rptype" type="radio" name="report_type" value="3" ${(param.report_type=="3")? 'checked':'' }> 其他 </label>
            </div>
            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon1">處理人</span>
               <input readonly type="hidden" class="form-control" aria-label="Username" id="inputhandler" name="handler"
                    aria-describedby="basic-addon1" value="${param.handler}">
                     <span  class="form-control" aria-label="Username" id="inputfake" 
                    aria-describedby="basic-addon1" ></span>
            </div>
            
            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon1">標題</span>
                <input type="text" class="form-control" aria-label="Username" name="title"
                    aria-describedby="basic-addon1" value="${param.title}">
                    <p style="color :red">${errorMsgs.title}</p>
            </div>


            <label for="basic-url" class="form-label">回報內容</label>
            <div class="input-group mb-3" style=" height:150px">
                <input id="cent" type="hidden" class="form-control" aria-label="Recipient's username" name="content1"
                    aria-describedby="basic-addon2" value="${param.content}">
                   <textarea name="content" rows="20" cols="50" id="tarea" style="height:150px;width:1000px"></textarea>
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
    <script>
    $('.rptype').click( function(e){
    	console.log(e.target.value);
	    if(e.target.value==0){
	    	$('#inputhandler').val('1019');
	    	$('#inputfake').text('Rora');
	    }else if(e.target.value==1){
	    	$('#inputhandler').val('1017');
	    	$('#inputfake').text('SAM');
	    }else if(e.target.value==2){
	        	$('#inputhandler').val('1016');
	        	$('#inputfake').text('AIDEN');
	    }else if(e.target.value==3){
	            	$('#inputhandler').val('1017');
	            	$('#inputfake').text('SAM');
	    }
    });
    
    </script>
     <script type="text/javascript">
                    function changeText(){
                    	document.getElementById("tarea").value=document.getElementById("cent").value;
                    }
                    
                    </script>
    
</body>
</html>