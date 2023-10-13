<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/login.css" rel='stylesheet' type='text/css' />
<title>Thay đổi mật khẩu</title>
</head>
<body>
        <c:set var="u" value="${sessionScope.user}"></c:set>
	<div class="login-02">
		<div class="two-login  hvr-float-shadow">
			<div class="two-login-head">
				<img src="images/top-note.png" alt="" />
				<h2>Cập nhật mật khẩu</h2>
				<lable></lable>
			</div>
			<form action="update_password" method="post">
				<li style="color: red">${requestScope.err}</li>
                                
				Mật khẩu cũ
				<li><input type="password" value="" 
					name="oldpassword"><a href="#" class=" icon2 lock2"></a></li>
				Mật khẩu mới
				<li><input type="password" value="" 
					name="password"><a href="#" class=" icon2 lock2"></a></li>
				Xác nhận mật khẩu
				<li><input type="password" value="" 
					name="confirmedpassword"><a href="#" class=" icon2 lock2"></a></li>
				<div class="submit two">
                                        <input type="hidden" value="${u.user_id}" name="user_id">
					<input type="submit" value="CẬP NHẬT">
					
				</div>
				<h5>
					<a href="home">Trang chủ</a>
				</h5>
			</form>
		</div>
	</div>
</body>
</html>