<%@ page language="java" contentType="text/html; charset=ISO--"
pageEncoding="utf-"%>
<!DOCTYPE html PUBLIC "-//WC//DTD HTML . Transitional//EN" "http://www.w.org/TR/html/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB">
<title>File upload</title>
</head>
<body>
<!-- // action="fileupload"對應web.xml中<servlet-mapping>中<url-pattern>的設定. -->
<form name="myform" action="UploadServlet" method="post"
enctype="multipart/form-data">
File:<br>
<input type="file" name="myfile"><br>
<br>
<input type="submit" name="submit" value="Commit">
</form>
</body>
</html> 