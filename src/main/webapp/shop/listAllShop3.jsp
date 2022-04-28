<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <%@ page import="java.util.*"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="java.util.*"%>

<%
ShopService shopSvc = new ShopService();
List<ShopVO> list = shopSvc.getAll();
pageContext.setAttribute("list", list);
int itemsPerPage = 10;
%>
        <!DOCTYPE html>
        <html lang="zh-TW">

        <head>
            <%@ include file="/design/frontmetacss.jsp" %>


        </head>

        <body>
            <div class="wrapper">
                <!-- ======= Header ======= -->
                <%@ include file="/design/frontheader.jsp" %>

                    <!-- ======= 內容開始 ======= -->
<table>
		<tr>
			<th>shop_id</th>
			<th>shop_name</th>
			<th>shop_type</th>
			<th>address</th>
			<th>tel</th>
			<th>website</th>
			<th>min_amt</th>
			<th>照片</th>
			<th>照片</th>
			<th>照片</th>
			<th>菜單</th>
			<th>修改</th>
		</tr>
		<%@ include file="/design/page1.file"%>
		
		<c:forEach var="shopVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${shopVO.shop_id}</td>
				<td>${shopVO.shop_name}</td>
				<td>${shopVO.shop_type}</td>
				<td>${shopVO.address}</td>
				<td>${shopVO.tel}</td>
				<td><a href="${shopVO.website}">link</a></td>
				<td>${shopVO.min_amt}</td>
				<td><img src="<%=request.getContextPath()%>/util/DBGifReader?id_key=shop_id&id=${shopVO.shop_id}&table=shop&pic=shop_img1" style="width: 100px;"></td>
				<td><img src="<%=request.getContextPath()%>/util/DBGifReader?id_key=shop_id&id=${shopVO.shop_id}&table=shop&pic=shop_img2" style="width: 100px;"></td>
				<td><img src="<%=request.getContextPath()%>/util/DBGifReader?id_key=shop_id&id=${shopVO.shop_id}&table=shop&pic=shop_img3" style="width: 100px;"></td>
				
				
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/menu/selectmenubyshop"
						style="margin-bottom: 0px;">
						<input type="submit" value="查看菜單" style="margin-bottom: 0px;"> 
						<input type="hidden" name="shop_id" value="${shopVO.shop_id}"> 
						<input type="hidden" name="action" value="getmenu">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/shop/ShopServlet"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> 
						<input type="hidden" name="shop_id" value="${shopVO.shop_id}"> 
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="empno"  value="${empVO.empno}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
			</tr>
		</c:forEach>
	</table>
	<%@ include file="/design/page2.file"%>


                    <!-- ======= 內容結束 ======= -->

            </div>
            <!-- ======= Footer ======= -->
            <%@ include file="/design/frontfooter.jsp" %>
                <!-- ======= js ======= -->
                <%@ include file="/design/frontjs.jsp" %>

        </body>

        </html>