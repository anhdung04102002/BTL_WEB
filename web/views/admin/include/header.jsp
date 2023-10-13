<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<meta charset="UTF-8">
<title></title>

<link rel="stylesheet" href="/shop/css/style.css" />
<link rel="stylesheet" href="/shop/css/menu.css" />


</head>
<body>
    
    
        
	<c:set var="user" value="${sessionScope.user}"></c:set>
	<c:if test="${user != null}">
		<div id="head-link">
			<div id='menungang'>
				<ul>
					<li class='last'><a href="home"><span>Trang
								chủ</span></a></li>
					<li class='last'><a href="history"><span>Lịch sử</span></a></li>
					<li class='last'><a href="cart?command=showCart&index="><span>Giỏ hàng</span></a></li>
					<li class='last'><a href="search"><span>Tìm kiếm</span></a></li>                        &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;
                                        <li class='last' style="float: right;"><a href="update_profile">${user.username}</a></li>
					<li class='last' style="float: right;"><a href="/shop/logout"><span>Đăng
								xuất</span></a></li>
                                        
				</ul>
			</div>
		</div>
	</c:if>	
	<c:if test="${user == null}">
		<div id="head-link">
			<div id='menungang'>
				<ul>
					<li class='last'><a href="home"><span>Trang chủ</span></a></li>
					<li class='last'><a href="history"><span>Lịch sử</span></a></li>
					<li class='last'><a href="search"><span>Tìm kiếm</span></a></li>                            &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
					<li class='last' style="float: right;"><button onclick="window.location.href='register'">Đăng ký</button></li>
                                        <li class='last' style="float: right;"><button onclick="window.location.href='login'">Đăng nhập</button></li>

				</ul>
			</div>
		</div>
	</c:if>
</body>
</html>