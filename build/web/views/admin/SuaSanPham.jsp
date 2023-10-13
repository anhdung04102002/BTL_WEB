<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/login.css" rel='stylesheet' type='text/css' />
<title>Sửa sản phẩm</title>
</head>
<body>
        <c:set var="product" value="${requestScope.product}"></c:set>
	<div class="login-02">
		<div class="two-login  hvr-float-shadow">
			<div class="two-login-head">
				<img src="images/top-note.png" alt="" />
				<h2>Cập nhật thông tin</h2>
				<lable></lable>
			</div>
			<form action="admin-product" method="post" enctype="multipart/form-data">
				<li style="color: red">${requestScope.err}</li>
				Tên sản phẩm
				<li><input type="text" class="text"
					value="${product.ten_san_pham}" name="ten_san_pham" required><a
					href="#" class=" icon2 user2"></a></li>
				
				
				Thể loại
				<li>
                                    <select accept-charset="utf-8" name="ma_the_loai" id="danhMuc" required>
					<c:forEach items="${categories}" var="category">
                                            <c:if test="${product.ma_the_loai == category.key}">
                                                <option accept-charset="utf-8"  value="${category.key}" selected>${category.value.ten_the_loai}</option>
                                            </c:if> 
                                            <c:if test="${product.ma_the_loai != category.key}">
                                                <option accept-charset="utf-8"  value="${category.key}">${category.value.ten_the_loai}</option>
                                            </c:if> 
                                        </c:forEach>
                                    </select><a href="#" class=" icon2 lock2"></a></li>
				Hình ảnh
				<li><input type="file" onfocus="this.value = '';"
					 name="hinh_anh"><a
					href="#" class=" icon2 lock2"></a></li>
				Giá
				<li><input type="number" value="${product.gia_ban}"
					onfocus="this.value = '';"
					name="gia_ban" min="0" required><a
					href="#" class=" icon2 lock2"></a></li>
				Hãng sản xuất
                                <li><input type="text" value="${product.hang_san_xuat}"
					onfocus="this.value = '';"
					onblur="if (this.value == '') {this.value = '${product.hang_san_xuat}';}"
					name="hang_san_xuat" required><a href="#" class=" icon2 lock2"></a></li>
				Thông tin
<<<<<<< HEAD
                                <li><input type="text" value="${product.thong_tin}"
=======
                                <li><input type="text" value="${product.hang_san_xuat}"
>>>>>>> 28a0da9dee07f49a67e9abde61520c936320e33b
					onfocus="this.value = '';"
					onblur="if (this.value == '') {this.value = '${product.thong_tin}';}"
					name="thong_tin" required><a href="#" class=" icon2 lock2"></a></li>

				Số lượng kho
				<li><input type="number" value="${product.so_luong_kho}"
					onfocus="this.value = '';"
					name="so_luong_kho" min="0" required><a
					href="#" class=" icon2 lock2"></a></li>
                                Số lượng bán
				<li><input type="number" value="${product.so_luong_ban}"
					onfocus="this.value = '';"
					name="so_luong_ban" min="0" required><a
					href="#" class=" icon2 lock2"></a></li>
                                Hiển thị
				<li>
                                    <c:if test="${product.hien_thi == 1}">
                                        <input type="checkbox" value="${product.hien_thi}"
					onfocus="this.value = '';"
                                        name="hien_thi" checked><a
					href="#" class=" icon2 lock2"></a>
                                    </c:if>
                                    <c:if test="${product.hien_thi == 0}">
                                        <input type="checkbox" value="${product.hien_thi}"
					onfocus="this.value = '';"
                                        name="hien_thi"><a
					href="#" class=" icon2 lock2"></a>
                                    </c:if>    
                                </li>
                                
				<div class="submit two">
					<input type="submit" value="CẬP NHẬT">
					<input type="hidden" value="${product.ma_san_pham}" name="ma_san_pham">
					<input type="hidden" value="update" name="command">
				</div>
				
				
				<h5>
					<a href="/shop/admin-product">Trở về</a>
				</h5>
			</form>
		</div>
	</div>
</body>
</html>