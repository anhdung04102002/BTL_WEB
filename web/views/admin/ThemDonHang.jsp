<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/shop/css/login.css" rel='stylesheet' type='text/css' />
<title>Thêm đơn hàng</title>
</head>
<body>
        <c:set var="product" value="${requestScope.product}"></c:set>
	<div class="login-02">
		<div class="two-login  hvr-float-shadow">
			<div class="two-login-head">
				<img src="images/top-note.png" alt="" />
				<h2>Thêm đơn hàng</h2>
				<lable></lable>
			</div>
			<form action="/shop/admin/order" method="post">
				<li style="color: red">${requestScope.err}</li>
				
				
				
				Người mua
				<li>
                                    <select accept-charset="utf-8" name="user_id" id="danhMuc" required>
                                        <c:forEach items="${requestScope.userMap}" var="user">
                                            
                                           <option accept-charset="utf-8"  value="${user.key}">${user.value.username}</option>
                                            
                                        </c:forEach>
                                    </select><a href="#" class=" icon2 lock2"></a>
                                </li>
                                Sản phẩm
				<li>
                                    <select accept-charset="utf-8" name="ma_san_pham" id="danhMuc" required>
                                        <c:forEach items="${requestScope.productMap}" var="product">
                                            
                                           <option accept-charset="utf-8"  value="${product.key}">${product.value.ten_san_pham}</option>
                                            
                                        </c:forEach>
                                    </select><a href="#" class=" icon2 lock2"></a>
                                </li>
				Số lượng 
				<li><input type="number" value=""
					onfocus="this.value = '';"
					name="so_luong" min="0" required><a
					href="#" class=" icon2 lock2"></a></li>
                                Thời gian mua
				<li><input type="datetime-local" value=""
					onfocus="this.value = '';"
					name="ngay_mua" step="1" required><a
					href="#" class=" icon2 lock2"></a></li>
                                
                                Trạng thái
				<li>
                                    <select accept-charset="utf-8" name="trang_thai" id="danhMuc" required>
                                        
                                            
                                        <option accept-charset="utf-8"  value="Đang chờ duyệt">Đang chờ duyệt</option>
                                            
                                        <option accept-charset="utf-8"  value="Đã giao hàng">Đã giao hàng</option>
                                        
                                    </select><a href="#" class=" icon2 lock2"></a>
                                </li>
				<div class="submit two">
					<input type="submit" value="THÊM">
					<input type="hidden" value="0" name="order_id">
					<input type="hidden" value="add" name="command">
				</div>
				
				
				<h5>
					<a href="/shop/admin/order">Trở về</a>
				</h5>
			</form>
		</div>
	</div>
</body>
</html>