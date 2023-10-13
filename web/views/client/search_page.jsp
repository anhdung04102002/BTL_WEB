<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Trang chủ</title>
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/menu.css" />
<link rel="stylesheet" href="css/product.css" />
</head>
<body>

	<div id="main">
		<div id="head">
			<img src="images/banner.jpg" width="1057px" height="200px" />
		</div>

		<jsp:include page="include/header.jsp"></jsp:include>
		<div id="content">
			<div id="left"><jsp:include page="search_menu.jsp"></jsp:include></div>
			<div id="right">
				
				
				
				<c:if test="${requestScope.products != null}">
					
				
					<div id="site-wrapper" style="float: left">
						<ul class="products homepage">
						
							<c:forEach items="${requestScope.products}" var="product">
								<li class="preorder"><span class="tagimg"> </span> <a
									href="product?ma_san_pham=${product.ma_san_pham}"> <img
										src="sanpham/${product.hinh_anh}" width=" 250px" height="250px" />
										<h3>${product.ten_san_pham}</h3>
										<h4>
											Giá:
											${product.gia_ban}
											VNĐ
										</h4> <span class="textkm">Khuyến mãi trị giá đến <strong>500.000₫</strong>
									</span>
										<p class="info">
											<span>Hãng sx: ${product.hang_san_xuat}
											</span> <span>Giá: ${product.gia_ban} VNĐ
											</span> <span>Thông tin: ${product.thong_tin }
										</p>
								</a></li>
							</c:forEach>
	
							
						</ul>
					</div>
				</c:if>
			</div>
		</div>
		<div id="footer"><jsp:include page="include/footer.jsp"></jsp:include></div>
	</div>

</body>
</html>