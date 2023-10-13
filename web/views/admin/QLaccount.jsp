<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Boxicons -->
        <!-- Các thẻ khác -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <!-- My CSS -->

        <link rel="stylesheet" href="css/admin-user.css">
       <link rel="stylesheet" href="css/phantranguser.css">

        <title>Quản lý tài khoản</title>
    </head>

    <body>


        <!-- SIDEBAR -->
        <section id="sidebar">
		<a href="/shop/admin" class="brand">
			<i class='bx bxs-smile'></i>
			<span class="text">Trang quản trị</span>
		</a>
		<ul class="side-menu top">
			<li>
				<a href="/shop/admin" id="thongke">
					<i class='bx bxs-dashboard'></i>
					<span class="text">Trang chủ</span>
				</a>
			</li>
                        <li>
				<a href="/shop/listdanhmuc" >
					<i class='bx bx-category'></i>
					<span class="text">Quản lý danh mục</span>
				</a>
			</li>
			<li >
				<a href="/shop/admin-product" id="mystore">
					<i class='bx bxs-shopping-bag-alt'></i>
					<span class="text">Quản lý sản phẩm</span>
				</a>

			</li>
		
                        <li>
                            <a href="/shop/admin/order" id="addcategory">
                                <i class='bx bxs-doughnut-chart'></i>
                                <span class="text">Quản lý đơn hàng</span>
                            </a>
                        </li>
			
			<li  class="active">
				<a href="/shop/admin-user" id="tk">
					<i class='bx bxs-group'></i>
					<span class="text">Quản lý người dùng</span>
				</a>
			</li>
                        <c:if test="${sessionScope.admin.role == 1}">
                            <li>
				<a href="/shop/phanquyen" id="tk">
					<i class='bx bx-lock-alt'></i>
					<span class="text">Phân quyền</span>
				</a>
                            </li>
                            
                        </c:if>
		</ul>
        
		<ul class="side-menu">
			<li>
				<a href="/shop/admin/logout" class="logout">
					<i class='bx bxs-log-out-circle'></i>
					<span class="text">Logout</span>
				</a>
			</li>
		</ul>
	</section>
        <!-- SIDEBAR -->



        <!-- CONTENT -->
        <section id="content">
            <!-- NAVBAR -->
            <nav>
                <i class='bx bx-menu'></i>
                <a href="/shop/admin" class="nav-link">Trang chủ</a>
                <form action="#">
                    <div class="form-input">
                        <input type="search" placeholder="Search...">
                        <button type="submit" class="search-btn"><i class='bx bx-search'></i></button>
                    </div>
                </form>
                <input type="checkbox" id="switch-mode" hidden>
                <label for="switch-mode" class="switch-mode"></label>
                
                <a href="/shop/admin/update_profile" class="btn-download">
                    <i class='bx bxs-user-account'></i>
                    <span class="text">Xin chào: ${sessionScope.admin.username}</span>
                </a>

            </nav>
            <main>

                <div class="table-data">
                    <div class="order">

                        <a href="admin-user-add" class="btn-download js-them">
                            <i class='bx bxs-cloud-download'></i>
                            <span class="text">Thêm tài khoản</span>
                        </a>
                        <div class="head">
                            <h3>QUẢN LÝ TÀI KHOẢN</h3>
                            <form action="searchacc" method="get">
                                <div class="form-input">
                                    <input style="width: 200px" class="savesp" type="search" placeholder="Nhập tên user..." name="keyword">
                                    <button style="width: 25%" type="submit" class="search-btn delete"><i class='bx'><i class='bx bx-search'></i></button>
                                    <input type="hidden" value="search" name="command">
                                </div>
                            </form>

                            <i class='bx bx-filter'></i>
                        </div>

                        <table class="table" style="width: 1000px;">

                            <thead>
                                <tr>
                                    <th><p>Người Dùng</p></th>

                                    <th><p>email</p> </th>
                                    <!--<th><p>password</p> </th>-->
                                    <th><p>Số điện thoại</p></th>

                                    <th><p>Giới tính</p> </th>
                                    <th><p>Ngày sinh</p> </th>
                                    <th><p>Ðịa chỉ</p> </th>
                                    <th>Chức vụ</th>
                                    <th><p>sửa</p> </th>
                                    <th><p>xóa</p> </th>
                                </tr>
                            </thead>
                            <tbody>


                                <c:forEach items="${users}" var="o">
                                    <tr>

                                        <td>
                                            ${o.username}</td>
                                        <td> <small>${o.email}</small></td>
                                        <!--<td>${o.password}</td>-->
                                        <td>${o.sdt}</td>
                                        <td>${o.gioitinh}</td>
                                        <td>${o.ngaysinh}</td>
                                        <td>${o.diachi}</td>
                                        <td><c:if test="${o.role==2}">
                                                user
                                            </c:if>
                                            <c:if test="${o.role==1}">
                                                admin
                                            </c:if>
                                            <c:if test="${o.role==3}">
                                                mod
                                            </c:if>
                                        </td>
                                        <td><c:if test="${o.role!=1}"><a style="padding: 5px" class="delete" href="admin-user-update?uid=${o.user_id}">Sửa</a></c:if></td>
                                        <td><c:if test="${o.role!=1}"><a style="padding: 5px" class="delete" href="#" onclick="showDel('${o.user_id}')">xóa</a></c:if></td>

                                        </tr>
                                </c:forEach>

                            </tbody>

                        </table>

                        <div class="pagination">
                            <i class="fas fa-chevron-left"></i>
                            <c:forEach begin="1" end="${requestScope.endP}" var="i">                    
                                <a class="${tag==i?"phantranguser":""}" href="admin-user?index=${i}"> ${i} </a>
                                <!-- Thêm các nút phân trang khác vào đây -->
                            </c:forEach>
                            <i class="fas fa-chevron-right"></i>
                        </div>
                        <!--<a href="#"><i class="fas fa-chevron-right"></i></a>-->
                    </div>
                </div>
            </main>
        </section>
    </body>
    <script src="js/script.js"></script>
    <script>
                                        function showDel(id) {
                                            var option = confirm('bạn thật sự muốn xóa???');
                                            if (option === true) {
                                                window.location.href = 'delete?uid=' + id;
                                            }

                                        }
    </script>
</html>
