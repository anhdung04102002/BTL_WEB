<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--c:url trỏ đường dẫn tuyệt đối không bị mất css và js khi gửi yêu cầu đến server-->
<link rel="stylesheet" type="text/css" href="<c:url value='/css/admin_category.css' />">

<!DOCTYPE html>

<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!--             Boxicons 
                     Các thẻ khác -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <!--                     My CSS -->
        <!--        <link rel="stylesheet" href="style.css">-->
        <title>Quản lý danh muc</title>
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
                        <li class="active">
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
			
			<li>
				<a href="/shop/admin" id="tk">
					<i class='bx bxs-group'></i>
					<span class="text">Quản lý người dùng</span>
				</a>
			</li>
                        <c:if test="${sessionScope.admin.role == 1}">
                            <li>
				<a href="/shop/admin" id="tk">
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
            <!-- NAVBAR -->
            <main>
                <div class="table-data">
                    <div class="order">
                        <div class="head">                          
                            <!--KHÔNG ĐIỀU HƯỚNG THẲNG ĐẾN TRANG JSP MÀ NÊN THÊM 1 SERVLET ĐỂ ĐIỀU HƯỚNG TRANG ĐÓ ĐẾN JSP-->
                            <a href="hienthiadd" class="btn-download js-them">Thêm danh mục </a>
                            <h3>Danh mục</h3>
                            <form action = "timkiemdanhmuc" method="GET">
                                <button type="submit"   <i class='bx bx-search'></button> 
                                <input name = "search" class="savesp" type="text"></i>                 
                            </form>

                        </div>
                        <table>
                            <thead>
                                <tr>
                                    <th>Loại sản phẩm</th>
                                    <th>Mô tả</th>
                                    <th>Icon cho danh mục</th>
                                    <th>Sửa</th>
                                    <th>Xóa</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- trường hợp không có phân trang
                                <c:forEach items="${data}" var = "c">
                                    <c:set var ="ten" value = "${c.ten_the_loai}"/>
                                  <tr>
                                        <td>${ten}</td>                                     
                                        <td><a  href="iddanhmuc?ma_the_loai=${c.ma_the_loai}" class="update js-sua" >sửa</a></td>                                                                      
                                        <td><a class="delete" href="#" onclick="doDeleteCategory('${c.ma_the_loai}')" >xóa</a></td>
                                    </tr>
                                </c:forEach>
                                -->
                                <!-- có phân trang -->
                                <c:forEach items="${listA}" var = "c">
                                    <c:set var ="ten" value = "${c.ten_the_loai}"/>
                                    <tr>
                                        <td style="padding: 8px; display: table-cell;" >${ten}</td>   
                                        <td >${c.mo_ta}</td> 
                                        <td style=display: inline-block;"><img src="images/${c.hinh_anh}"  alt="Ảnh" style="object-fit: contain"/></td>                                                                                                                                        
                                        <td><a  href="iddanhmuc?ma_the_loai=${c.ma_the_loai}" class="update js-sua" >sửa</a></td>                                                                      
                                        <td><a class="delete" href="#" onclick="doDeleteCategory('${c.ma_the_loai}')" >xóa</a></td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                        <!--Phân trang-->

                        <div class="pagination"> 
<!--                            <a href="listdanhmuc?index=${tag - 1}">Trang trước</a>-->
                            <c:forEach begin ="1" end = "${endP}" var = "i">                             
                                <a class = "${tag == i ? "duocchon":""}" href="listdanhmuc?index=${i}">${i}</a>
                            </c:forEach> 
<!--                            <a href="listdanhmuc?index=${tag + 1}">Trang sau</a>-->
                            <!--                            <ul>
                                                            <li><a href="#"><i class="fas fa-chevron-left"></i></a></li>
                                                            <li><a href="#">1</a></li>
                                                            <li><a href="#">2</a></li>
                                                            <li><a href="#">3</a></li>
                                                            <li><a href="#"><i class="fas fa-chevron-right"></i></a></li>
                                                            Thêm các nút phân trang khác vào đây 
                                                        </ul>-->
                        </div>
                    </div>
                </div>
            </main>
        </section>       
        <!--            <script src="script.js"></script>-->
        <script src="<c:url value='/js/script_category.js' />"></script>
        <!--css thì ở đầu còn js thì ở cuối-->
    </body>
</html>
