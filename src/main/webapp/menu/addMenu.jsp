<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.menu.model.*"%>
<%
MenuService menuService = new MenuService();
List<MenuVO> list = menuService.getAll();
pageContext.setAttribute("list", list);

pageContext.getAttribute("shop_id");
%>

<html>

<head>
<%@ include file="/design/frontmetacss.jsp"%>


<style>
.form-elements {
	margin-top: 10px;
}

#frm-add-data .form-group {
	margin-left: 13px;
}
</style>

</head>

<body style="background-color: #EBD6D6">

	<%@ include file="/design/frontheader.jsp"%>

	<!-- ====================== 內容開始 ====================== -->
	<main id="main" class="main" style="height: 300vh">
		<!-- 		<div class="card shadow mb-4"> -->
		<!-- ============== Card Header ============== -->
		<div class="card-header py-3" style="background-color: #b0c4de">
			<div class="row">
				<div class="col-11" style="height: 20px; display: inline-block;">
					<h5>
						<strong>新增店家菜單</strong>
					</h5>
				</div>
				<div class="col-1" style="height: 20px; display: inline-block;">
					<a href="<%=request.getContextPath()%>/menu/listMenuByShop.jsp"><strong>回上頁</strong></a>
				</div>
			</div>
		</div>
		<!-- ============== Card Body ============== -->
		<div class="col-lg-12">
			<div class="card"
				style="background-color: #EBD6D6; ALIGN-ITEMS: CENTER;">

				<p style="color: red;">
					<strong>${errorMsgs.price}${errorMsgs.item}</strong>
					<span id="itemError" style="color:red;"></span>
				</p>

				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/menu/addmenubyshop">
					<div class="card-body">
						<div class="container">

							<div class="form-group" style="float: right;">
								<a href="javascript:void(0);" class="add_button"
									style="font-size: 40px;" title="新增一項"><i
									class="bi bi-plus-square-fill bi--4xl"></i></a>
							</div>
							<div class="field_wrapper ">
								<div style="DISPLAY: INLINE-FLEX;">
									<div class="form-group" style="text-align: center;">
										<label for=" item"><strong>品 項 <strong></label>
										<input type="text" required size=30px id="item"
											pattern="^[(\u4e00-\u9fa5)(\u0800-\u4e00)a-zA-Z0-9_+\s\\(\\-\\)\\]*$"
											class="form-control" placeholder="輸入名稱" name="item" id="item" value=""/>
									</div>
									<div class="form-group" style="text-align: center;">
										<label for=" price">金 額 </label> 
										<input type="number" required size=30px
											class="form-control" placeholder="數字" name="price" min="1" max="1000"
											value="">
									</div>
									<div class="form-group">
										<span id="span_itemName"></span>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div style="text-align: end; padding: 30;">
						<input type="hidden" class="form-control" name="shop_id"
							value="${param.shop_id}"> <input type="hidden"
							name="action" value="insertMany"> <input type="submit"
							class="btn btn-dark" value="送出新增">
					</div>
				</form>
			</div>
		</div>
		</div>
	</main>
	<!-- ======= 內容結束 ======= -->
	<!-- ======= Footer ======= -->
	<!-- <%@ include file="/design/frontfooter.jsp" %> -->
	<!-- ======= js ======= -->
	<%@ include file="/design/frontjs.jsp"%>
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	
2
	<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>

	<script type="text/javascript">
	
//jQuery品項輸入驗證	
 let reg=/^[(\u4e00-\u9fa5)(\u0800-\u4e00)a-zA-Z0-9_\s\\(\\-\\)]*$/;
	         
$("#item").blur(function(){
	        	  if($(this).val() == ''  ||  $(this).val()==null){
	        		  $('#itemError').text('未輸入品項名稱!')
	        	  }else if(reg.test($(this).val())){
	                  $('#itemError').text('')
	                  
	              }
	        	  else{
	                  $('#itemError').text('輸入格式錯誤😵 格式:中、日、英文、數字、空格() + - _')
	              }
	          });
	          
	

	
	
//點擊新增一組輸入框
                                    $(document).ready(function () {
                                        var maxField = 100; // 最多只能新增100項
                                        var addButton = $('.add_button'); // 新增的按鈕
                                        var wrapper = $('.field_wrapper'); // 新增範圍
                                        var fieldHTML =
                                            `<div class="form-elements">
                                        <div style="DISPLAY: INLINE-FLEX;">
                                            <div class="form-group">
                                                    <label for="item"></label>
                                                    <input type="text" required size=30px id="item"
                                                    pattern="^[a-zA-Z0-9_+\s\\(\\-\\)\\]*$"
                                                    class="form-control" placeholder="輸入名稱" name="item"
                                                    value="">
                                            </div>
                                            <div class="form-group">
                                                    <label for="price"></label>
                                                    <input type="number" required size=20px
                                                    class="form-control" placeholder="數字" name="price" min="1" max="1000" 
                                                    value="">
                                            </div>
                                            <div class="form-group">
                                                    <a href="javascript:void(0);" class="remove_button" style="font-size:30px;" title="移除此項"><i class="bi bi-trash"></i></a>
                                            </div>
                                        </div>	
                                        </div>`;

                                        //初始化至少有一組輸入框
                                        var x = 0;
                                        $(addButton).click(function () {
                                            if (x < maxField) {
                                                x++;
                                                $(wrapper).append(fieldHTML);
                                            }
                                        });
                                        //刪除按鈕
                                        $(wrapper).on('click', '.remove_button', function (e) {
                                            e.preventDefault();
                                            $(this).parent().closest(".form-elements").remove();
                                            x--; //Decrement field counter
                                        });
                                    });


                                    //SweetAlert
                                    $(document).ready(function () {
                                        Swal.fire({
                                            icon: 'info',
                                            title: '   幫 這 間 店 家 新 增 菜 單  \n 🥗🍿🍜🍕🍣🍩🍦🥤☕',
                                        });
                                    });

                                </script>
</body>

</html>