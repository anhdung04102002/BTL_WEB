<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Menu dọc</title>
<link rel="stylesheet" href="css/category.css" />
</head>
<body>
	
	<div class="container">
		<nav>
		<ul class="mcd-menu">
                    
			<c:forEach var="c" items="${requestScope.categories}">
				<li><a href="home?ma_the_loai=${c.ma_the_loai}"> <i class="fa fa-home"><img
						src="images/${c.hinh_anh}"></i>
					<strong>${c.ten_the_loai}</strong> <small>${c.mo_ta}</small>
				</a></li>
			</c:forEach>
			
		</ul>
		</nav>
	</div>
</body>
</html>