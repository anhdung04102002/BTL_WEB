<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/shop/css/login.css" rel='stylesheet' type='text/css' />
<title>Đăng nhập hệ thống</title>
</head>
<body>
	
	
	<div class="login-01">
		<div class="one-login  hvr-float-shadow">
			<div class="one-login-head">
				<img src="images/top-lock.png" alt="" />
				<h1>LOGIN</h1>
				
				<span></span>
			</div>
			<form action="/shop/admin/login" method="post">
				
				<li style="color: red">${requestScope.err}</li>
				Tên người dùng
				<li><input type="text" class="text" value=""
					name="username"><a href="#" class=" icon user"></a></li>
				Mật khẩu
				<li><input type="password" value=""
					name="password"><a href="#" class=" icon lock"></a></li>
				
				<div class="submit">
					<input type="submit" value="SIGN IN">
				</div>
				
			</form>
		</div>
	</div>

</body>
</html>