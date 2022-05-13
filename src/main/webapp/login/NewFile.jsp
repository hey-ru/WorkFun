<%@ page language="java" contentType="text/html; charset="utf-8"
%>
<% Integer a=0;
  Integer b;
  

 %>

<!DOCTYPE html PUBLIC "-//WC//DTD HTML . Transitional//EN" "http://www.w.org/TR/html/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB">
<title>File upload</title>
</head>
<body>
 ${a==b}
 <br>
 ${a==0}
 <br>
 ${b==0}
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