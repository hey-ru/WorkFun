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
    
    <main style="height: 120vh; margin-top:80px;">
    <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/reportServlet" name="formAdd" enctype="multipart/form-data">
        <div
            style="width:900px;position:absolute; height:650px; top:50%; margin-top:-175px;margin-left: 13%;">
             <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon1">回報者</span>
                <input readonly class="form-control" aria-label="Username" type="hidden"
                    aria-describedby="basic-addon1" name="reporter" value="${empVO.empId}">
                    <span style="margin-left:30px">${empVO.empName}</span>

            </div>
            
            <div class="input-group mb-3" id="input-radio">
                <span class="input-group-text" id="basic-addon2">類型</span>
                
                <label style="margin-left:30px"><input id="in0" class ="rptype" type="radio" name="report_type" value="0" ${(param.report_type=="0")? 'checked':'' } CHECKED> 添購新品 </label>
				<label><input id="in1" class ="rptype" type="radio" name="report_type" value="1" ${(param.report_type=="1")? 'checked':'' }> 損壞報修 </label>
				<label><input id="in2" class ="rptype" type="radio" name="report_type" value="2" ${(param.report_type=="2")? 'checked':'' }> 軟硬體問題 </label>
				<label><input id="in3" class ="rptype" type="radio" name="report_type" value="3" ${(param.report_type=="3")? 'checked':'' }> 其他 </label>
            </div>
            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon1">處理人</span>
               <input readonly type="hidden" class="form-control" aria-label="Username" id="inputhandler" name="handler"
                    aria-describedby="basic-addon1" value= "1019">
                     <span  class="form-control" aria-label="Username" id="inputfake" 
                    aria-describedby="basic-addon1" >Rora</span>
            </div>
            
            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon1">標題</span><font color=red><b>*</b></font>
                <input type="text" class="form-control" aria-label="Username" required name="title" id="title"
                    aria-describedby="basic-addon1" value="${param.title}">
                    
                    <span style="color :red" id="title_error">${errorMsgs.title}</span>
            </div>


            <label for="basic-url" class="form-label">回報內容</label><font color=red><b>*</b></font>
            <div class="input-group mb-3" style=" height:150px">
                <input id="cent" type="hidden" class="form-control" aria-label="Recipient's username" name="content1"
                    aria-describedby="basic-addon2" value="${param.content}">
                   <textarea required name="content" rows="20" cols="50" required id="tarea" style="height:150px;width:1000px"></textarea>
                   <span style="color :red" id="content_error">${errorMsgs.content}</span>
                   
            </div>
            <div style="height:10px">
            
            </div>
            <div class="input-group mb-3">
                <label class="input-group-text" for="inputGroupFile01">Upload</label>
                <input id="imgInp" type="file" value="${param.report_image}" name="report_image" accept="image/*" oninput="pic.src=window.URL.createObjectURL(this.files[0])">
               	<img id="blah" src="#" alt="your image" style="width:220px;height:220px" />
                <input type="text" style="display:none;" name="action" value="insert">
				<button type="submit" value="送出新增" style=" border:red 1px solid; width:100px;height:100px">送出</button>
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
    imgInp.onchange = evt => {
    	  const [file] = imgInp.files
    	  if (file) {
    	    blah.src = URL.createObjectURL(file)
    	  }
    	}
    
    
    </script>
    
  	<script type="text/javascript">
    
    let rule1=/^[(\u4e00-\u9fa5)(\u0800-\u4e00)a-zA-Z0-9_\s\\(\\-\\)]*$/;
    $("#title").blur(function(){
  	  if($(this).val() == ''){
  		  $('#title_error').text('請填入標題!')
  	  }else if(rule1.test($(this).val())){
            $('#title_error').text('')
        }else{
            $('#title_error').text('標題名稱:只能是中英文字母、數字')
        }
    });

    let rule2=/^[(\u4e00-\u9fa5)(\u0800-\u4e00)a-zA-Z0-9_\s\\(\\-\\)\u3002|\uff1f|\uff01|\uff0c|\u3001|\uff1b|\uff1a|\u201c|\u201d|\u2018|\u2019|\uff08|\uff09|\u300a|\u300b|\u3008|\u3009|\u3010|\u3011|\u300e|\u300f|\u300c|\u300d|\ufe43|\ufe44|\u3014|\u3015|\u2026|\u2014|\uff5e|\ufe4f|\uffe5]*$/;
    $("#tarea").blur(function(){
    	  if($(this).val() == ''){
      		  $('#content_error').text('請填入回報內容!')
      	  }else if(rule2.test($(this).val())){
                $('#content_error').text('')
            }else{
                $('#content_error').text('回報內容:只能是中英文字母、數字、全型標點符號')
            }
        });
</script>
    
    
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