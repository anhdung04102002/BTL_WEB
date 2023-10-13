<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Chi tiết sản phẩm</title>
<link rel="stylesheet" href="css/detail.css" />
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
    <c:set var="product" value="${requestScope.product}"/>
	<div id="main">
		<div id="head">
			<img src="images/banner.jpg" width="1057px" height="200px" />
		</div>
		<jsp:include page="include/header.jsp"></jsp:include>
                <c:set var="product" value="${requestScope.product}"/>
		<div id="content">
			
			<div class="left-1">
				<img src="sanpham/${product.hinh_anh}"  width="200px" height="200px" />
			</div>
			
			<div class="left-2">
				<table>
					
					
                                        
                                    <tr class="row1">
                                            <td class="col2" colspan="2"
                                                    style="padding: 10px; color: blue; font-size: 15px; text-transform: uppercase; text-align: center; font-weight: bold">${product.ten_san_pham}</td>
                                    </tr>
                                    <tr class="row2">
                                            <td class="col1">Hãng sản xuất:</td>
                                            <td class="col2">${product.hang_san_xuat}</td>
                                    </tr>

                                    <tr class="row2">
                                            <td class="col1">Giá bán:</td>
                                            <td class="col2">${product.gia_ban} VNĐ</td>
                                    </tr>
                                    <tr class="row2">
                                            <td class="col1">Mô tả:</td>
                                            <td class="col2">${product.thong_tin}</td>
                                    </tr>
                                    <tr class="row2">
                                        <td class="col1" style="color: green;">Luợt mua:</td>
                                            <td class="col2" style="color: green;">${product.so_luong_ban}</td>
                                    </tr>
					
					
				</table>
			</div>
			<h1 style="padding: 10px; color: red; font-size: 15px; text-transform: uppercase; text-align: center; font-weight: bold">${requestScope.err}</h1>
			<c:set var="user" value="${sessionScope.user}"></c:set>
			<c:if test="${user != null}">
				<div
					style="margin-left: auto; margin-right: auto; text-align: center; margin-top: 10px; padding: 10px; clear: both;">
					
					<form action="cart" method="post">
						<input type="number" min="1" value="1" name="soluong"/>
						<input type="hidden" value="${product.ma_san_pham}" name="ma_san_pham"/>
						<input type="hidden" value="setCart" name="command"/>
						<input type="submit" value="Thêm vào giỏ hàng">
					</form>	
				</div>
			</c:if>
			<c:if test="${user == null}">
				<div
					style="margin-left: auto; margin-right: auto; text-align: center; margin-top: 10px; padding: 10px; clear: both;">
					<a href="login">Đăng nhập để thêm vào giỏ hàng</a>
			</div>
			</c:if>
			

		</div>
		<div id="footer"><jsp:include page="include/footer.jsp"></jsp:include></div>
	</div>
</body>
</html>
