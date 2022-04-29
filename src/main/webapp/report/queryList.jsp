<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- 萬用複合查詢-以下欄位-可隨意增減 --%>
							<ul>
								<li>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/reportServlet"
										name="form1">
										<b>回報狀態:</b> <select size="1" name="status">
										 <option value="">
										<c:forEach var="reportVO" items="${list}">
											<option value="${reportVO.status}">
												</c:forEach>
										</select>

										<b>選擇類型:</b> <select size="1" name="report_type">
										 <option value="">
										<c:forEach var="reportVO" items="${list}">
											<option value="${reportVO.report_type}">
												</c:forEach>
										</select>  <input type="submit" value="送出">
										<input type="hidden" name="action"
											value="QueryAll">
									</FORM>
								</li>
							</ul>
</body>
</html>