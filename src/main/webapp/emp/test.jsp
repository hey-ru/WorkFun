<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>



</head>
<body>
<input type="button" placeholder="請輸入電子郵件" value="帳號驗證" onclick="checkAccount()" />
<div id="viewAccount"></div>
<h1><a value="${empVO.empName}" id="1"></a></h1>
<script>




function getPm() {
    
	let xhr = null;
	function createXHR() {
		if (window.XMLHttpRequest) {
			xhr = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			xhr = new ActiveXObject("Microsoft.XMLHTTP");
		}
		return xhr;
	}
	createXHR();

	xhr.onload = (e) => {
		if (xhr.status == 200) {
			document.getElementById("viewAccount").innerHTML = xhr.responseText;
		} else {
			alert(xhr.status);
		}
	};

	xhr.open("post", "/CGA101G3/empServlet");
	xhr.setRequestHeader(
		"content-type",
		"application/x-www-form-urlencoded"
	);

	let datasInfo = "accountRegister=" + document.getElementById("accountRegister").value;
	xhr.send(datasInfo);

}
</script>
</body>
</html>