<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div>
<form METHOD="post" ACTION="selectmenubyshop" >

	<input id="menubtn" type="submit" name="action" value="查看菜單" onclick="callServlet();" />

</form>
</div>


<script type="text/javascript">
function callservlet() {

    var servletname=document.seemenu.action.value;

    if(servletname== "SelectMenuByShopServlet")
        {
        alert("NO value..");
        return false;
        }
    else
        {
        alert("value"+SelectMenuByShopServlet);
        document.location.href="selectmenubyshop";
        return false;
        }
}

</script>

</body>
</html>