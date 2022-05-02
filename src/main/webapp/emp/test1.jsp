<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.in_sec_nrd {
    width: 100%;
    float: left;
}
 
.art_loop_nrd {
    width: 100%;
    float: left;
    padding: 0 0 10px 0;
    color: #7b7b7b;
}
 
.art_loop_nrd a {
    color: #307d71;
}
 
.art_loop_nrd a:hover {
    text-decoration: underline;
    color: #e6352a;
}
 
.art_loop_nrd p {
    color: #2B2B2B !important;
}


</style>
</head>
<body>
<c:forEach begin="0" end="5" var="count">
<input type="text" value="${count}"> 

<c:if test="${count==3}"> 


 <c:set var="count" value="5">
</c:set> 
<input type="text" value="${count}"> 
 </c:if> 

</c:forEach>
</body>
</html>