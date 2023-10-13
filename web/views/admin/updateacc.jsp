
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update account</title>
        <link rel="stylesheet" href="css/admin-user.css">
    </head>
    <body>   <c:set var="o" value="${requestScope.user}"/>

        <form action="admin-user-update" method="post">	
               <div style="display: flex" class="modalthem js-modaluser">
                   <div style="width: 900px ;" class="todo change_user change">

			<div class="head">
                                
				<h3>CẬP NHẬT TÀI KHOẢN</h3>
				<i class='bx bx-filter'></i>
				<input class="savesp" type="submit" title="Lưu thông tin" value="lưu thông tin" />
                                 <h3 style="color: red;">${requestScope.error}</h3>
			</div>
			<ul class="todo-list">
                               <li class="completed_list">
                                   <p style="display: none;">id<input class="addsp" name="id" type="text" value="${o.user_id}"></p>

				</li>
				<li class="completed_list">
                                    <p>Tên người dùng     <input class="addsp" name="name" type="text" value="${o.username}" readonly></p>

				</li>
				<li class="completed_list">
                                    <p style="display: none;">chức vụ <input class="addsp" name="role" type="text" value="${o.role}" readonly></p>

				</li>
				<li class="completed_list">
                                    <p>Email     <input class="addsp" name="email" type="text" value="${o.email}"></p>

				</li>
				<li class="completed_list">
                                    <p>Mật khẩu     <input class="addsp" name="password" type="password" value=""></p>

				</li>
			
				<li class="completed_list">
                                    <p>Ðịa chỉ     <input class="addsp" name="diachi" type="text" value="${o.diachi}"></p>

				</li>
				<li class="completed_list">
                                    <p>Ngày sinh     <input class="addsp" name="ngaysinh" type="date" value="${o.ngaysinh}"></p>

				</li>
                                <li class="completed_list">
                                    <p>Số điện thoại     <input class="addsp" name="sdt" type="text" value="${o.sdt}"></p>

				</li>
				<li class="completed_list">
                                    <p>Giới tính     <input type="text" value="${o.gioitinh}" list="exampleList"
					name="gender"> 
					<datalist id="exampleList">
					<option value="Nam">
					<option value="Nữ">
					</datalist><a href="#" class=" icon2 lock2"></a></p>
				</li>

			</ul>
                    <div class="footer js-modalclosethem"><a class="close " href="admin-user">close</a></div>
		</div>
	</div></form>
    </body>
</html>
