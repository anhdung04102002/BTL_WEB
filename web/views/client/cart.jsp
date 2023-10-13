<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Giỏ hàng</title>
<link rel="stylesheet" href="css/cart.css" />
<link rel="stylesheet" href="css/menu.css" />
<link rel="stylesheet" href="css/product.css" />



<style type="text/css">
#main {
	width: 1060px;
	padding: 0;
	margin-left: auto;
	margin-right: auto;
}

#head {
	height: 200px;
	background-color: #F5F5F5;
	border: 1px solid #CDCDCD;
	margin-bottom: 5px;
	margin-top: 5px;
}

#head-link {
	height: 50px;
	line-height: 30px;
	border: 1px solid #CDCDCD;
	background-color: #F5F5F5;
	margin-bottom: 5px;
	clear: both;
}

#content {
	width: 1060px;
	min-height: 430px;
	border: 1px solid #CDCDCD;
	float: left;
	margin-bottom: 5px;
	clear: both;
}

#footer {
	height: 50px;
	clear: both;
	border: 1px solid #CDCDCD;
	background-color: #F8F8FF;
	margin-bottom: 5px;
}

</style>
</head>
<body>
	
	<div id="main">
		<div id="head">
			<img src="images/banner.jpg" width="1057px" height="200px" />
		</div>
		<jsp:include page="include/header.jsp"></jsp:include>
		<div id="content">
			

			<div class="shopping-cart">
                            
                            <div class = "container" style = "display: flex;
  justify-content: space-between;
 margin-bottom:20px;margin-left: -13px;"  >
                                
                                    
                                    ,<div class = "col-md-2 big-header" style="font-size:22px">Hình ảnh</div>                                  
                                    ,<div class = "col-md-3 big-header" style="font-size:22px">Sản phẩm</div>
                                    ,<div class = "col-md-2 big-header" style="font-size:22px">Giá Bán</div>
                                    ,<div class = "col-md-2 big-header"style="font-size:22px;margin-left:26px">Số lượng</div>
                                    ,<div class = "col-md-2 big-header"style="font-size:22px;margin-right: 2px;"   >Thành tiền </div>                                     
                                 
                         
                                
                            </div>
				<c:forEach items="${sessionScope.carts}" var="cart" varStatus="loop">
					<div class="product" style = " display: flex;
  justify-content: space-between;
  align-items: center"">
						<div class="product-image">
							<img src="sanpham/${cart.p.hinh_anh}">
						</div>
						<div class="product-details">
							<div class="product-title" style="font-size:18px;text-alight:center">
								${cart.p.ten_san_pham}
							</div>
							<p class="product-description"></p>
						</div>
						<div class="product-price"  style="font-size:18px;padding-right:90px"
  ">
							${cart.p.gia_ban} VNĐ
						</div>
						<div class="product-quantity cart_quantity_button" >
							<a class="cart_quantity_up" href="cart?command=deleteCart&index=${loop.index}"> - </a>
							<input class="cart_quantity_input" type="number" value="${cart.quantity}" disabled="disabled">
                                                        <a class="cart_quantity_up" href="cart?command=addCart&index=${loop.index}"> + </a>
							
						</div>
						<div class="product-line-price" style="text-align: right;font-size: 17px;
    padding-left: 42px;">${cart.quantity * cart.p.gia_ban}
							VNĐ
						
							<a   href="cart?command=removeCart&index=${loop.index}"><img src="images/delete.png">
                                                          </a>  
						</div>
						
					</div>
				</c:forEach>
				
				
				
				
				<a class="checkout" href="history" style="text-decoration: none;">Lịch sử</a>
				<a class="checkout" href="cart/confirm" style="text-decoration: none;">Xác nhận</a>
				
				
				
			</div>

		</div>
		<div id="footer"><jsp:include page="include/footer.jsp"></jsp:include></div>
	</div>
</body>
</html>