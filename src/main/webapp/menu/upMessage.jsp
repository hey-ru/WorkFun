<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>
<html>
<head>
<title>Message</title>

<style >
table, th, td {
  border: 1px solid black;
  
}
</style>

</head>

<body>
	<table>
   <%=request.getAttribute("Message")%>
	</table>
<h2><%=request.getAttribute("Message2")%></h2>
</body>

</html> 
























</body>
</html>