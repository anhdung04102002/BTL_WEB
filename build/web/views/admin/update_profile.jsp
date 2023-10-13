<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/shop/css/login.css" rel='stylesheet' type='text/css' />
<title>Thay đổi hồ sơ</title>
</head>
<body>
        <c:set var="admin" value="${sessionScope.admin}"/>
	<div class="login-02">
		<div class="two-login  hvr-float-shadow">
			<div class="two-login-head">
				<img src="images/top-note.png" alt="" />
				<h2>Cập nhật thông tin</h2>
				<lable></lable>
			</div>
			<form action="/shop/admin/update_profile" method="post">
				<li style="color: red">${requestScope.err}</li>
				Tên đăng nhập
				<li><input type="text" class="text"
					value="${admin.username}" readonly name="username"><a
					href="#" class=" icon2 admin2"></a></li>
				
				Ngày sinh
				<li><input type="date" value="${admin.ngaysinh}"
					onfocus="this.value = '';"
					onblur="if (this.value == '') {this.value = '${admin.ngaysinh}';}"
					name="ngaysinh"><a href="#" class=" icon2 lock2"></a></li>
				Giới tính
				<li><input type="text" value="${admin.gioitinh}"
					list="exampleList" onfocus="this.value = '';"
					onblur="if (this.value == '') {this.value = '${admin.gioitinh}';}"
					name="gioitinh"> <datalist id="exampleList">
					<option value="Nam">
					<option value="Nữ">
					</datalist><a href="#" class=" icon2 lock2"></a></li>
				Email
				<li><input type="text" value="${admin.email}" onfocus="this.value = '';"
					onblur="if (this.value == '') {this.value = '${admin.email}';}" name="email"><a
					href="#" class=" icon2 lock2"></a></li>
				Số điện thoại
				<li><input type="text" value="${admin.sdt}"
					onfocus="this.value = '';"
					onblur="if (this.value == '') {this.value = '${admin.sdt}';}" name="sdt"><a
					href="#" class=" icon2 lock2"></a></li>
				Địa chỉ
				<li><input type="text" value="${admin.diachi}"
					onfocus="this.value = '';"
					onblur="if (this.value == '') {this.value = '${admin.diachi}';}"
					name="diachi"><a href="#" class=" icon2 lock2"></a></li>

				
				<div class="submit two">
					<input type="submit" value="CẬP NHẬT">
					<input type="hidden" value="${admin.user_id}" name="user_id">
				</div>
				<h5>
					<a href="/shop/admin/update_password">Thay đổi mật khẩu</a>
				</h5>
				<br>
				<h5>
					<a href="/shop/admin">Trở về</a>
				</h5>
			</form>
		</div>
	</div>
</body>
</html>