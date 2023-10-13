<%-- 
    Document   : addacc
    Created on : May 30, 2023, 7:17:44 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/admin-user.css">
    </head>
    <body>
        <form action="admin-user-add" method="post">	
               <div style="display: flex" class="modalthem js-modaluser">
                   <div style="width: 900px ;" class="todo change_user change">

			<div class="head">
                            <h3 style="color: red;">${requestScope.error}</h3>
				<h3>Thêm tài khoản</h3>
				<i class='bx bx-filter'></i>
				<input class="savesp" type="submit" title="Lưu thông tin" value="lưu thông tin" />
			</div>
			<ul class="todo-list">
                               <li class="completed_list">
                                    <p style="display: none;">id<input class="addsp" name="id" type="text" placeholder="id..."></p>

				</li>
				<li class="completed_list">
                                    <p>Tên Người Dùng    <input class="addsp" name="name" type="text" placeholder="tên user..."></p>

				</li>
				<li class="completed_list">
                                    <p style="display: none">chức vụ <input class="addsp" name="role" type="text" placeholder="chi co the la 2..." value="2" readonly></p>

				</li>
				<li class="completed_list">
                                    <p>Email     <input class="addsp" name="email" type="text" placeholder="email..."></p>

				</li>
				<li class="completed_list">
                                    <p>Mật khẩu     <input class="addsp" name="password" type="password" placeholder="password..."></p>

				</li>
			
				<li class="completed_list">
                                    <p>Ðịa chỉ     <input class="addsp" name="diachi" type="text" placeholder="place..."></p>

				</li>
				<li class="completed_list">
                                    <p>Ngày sinh     <input class="addsp" name="ngaysinh" type="date" placeholder="bday..."></p>
                                    
				</li>
                                <li class="completed_list">
                                    <p>Số điện thoại     <input class="addsp" name="sdt" type="text" placeholder="phone..."></p>

				</li>
				<li class="completed_list">
                                    <!--<p>giới tính <input class="addsp" name="gender" type="text" placeholder="giới tính..."></p>-->
                                    <p>Giới tính     <input type="text" value="" list="exampleList"
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
