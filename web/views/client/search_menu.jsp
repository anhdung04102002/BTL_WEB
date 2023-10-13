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
<style>
    
select.input-1,
input.input-1 {
    width: 95%;
    padding: 10px;
    font-size: 14px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
    margin-bottom: 10px;
}

input.search-btn1 {
    padding: 8px 20px;
    background-color: #333333;
    color: #ffffff;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

</style>
<body>
	
	<div class="container">
		<nav>
		<ul class="mcd-menu">
		<li>
		<form accept-charset="utf-8" method="post" action="search" name="">
                        <p>
                        <label for="tensp">Tên sản phẩm</label>
                        <br>
                        <input accept-charset="utf-8" type="text"  name="ten_san_pham" class="input-1">
                        </p>
                        <p>
                        <label for="theloai">Thể loại</label>
                        <br>
                        <select accept-charset="utf-8" name="ma_the_loai" class="input-1">
                        	<option value="" selected="selected">--Chọn thể loại--</option>
                        	<c:forEach items="${requestScope.categories}" var="c">
                        		<option accept-charset="utf-8"  value="${c.ma_the_loai}">${c.ten_the_loai}</option>
                        	</c:forEach>
                        		
                        </select>
                        </p>
                        <input class="search-btn1" type="submit" value="Tìm kiếm" name="timKiem">
                	</form>
                	</li>
                	<li style="color: red">${requestScope.err}</li>
                	</ul>
		</nav>
	</div>
</body>
</html>