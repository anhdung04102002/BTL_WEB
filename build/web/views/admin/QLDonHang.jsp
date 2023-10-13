<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/shop/css/qlsanpham.css">
    <link rel="stylesheet" href="/shop/css/qlsanpham_style.css">
    <title>Danh sách đơn hàng</title>
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
		
                        <li class="active">
                            <a href="/shop/admin/order" id="addcategory">
                                <i class='bx bxs-doughnut-chart'></i>
                                <span class="text">Quản lý đơn hàng</span>
                            </a>
                        </li>
			
			<li>
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
        <c:if test="${requestScope.err != null}">
            <script>
                alert("${requestScope.err}");
            </script>
        </c:if>
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
                <div div class="table-data">
                    <div class="order list_product">
                        <div class="head">
                            
                            <h3>Danh sách đơn hàng</h3>
                            <a href="/shop/admin/order?command=add">
                                <button style="padding: 10px" class="btn-download js-them">
                                    <i class='bx bxs-cloud-download'></i>
                                    <span class="text"> Thêm đơn hàng </span>
                                </button>
                            </a>
                            
                        </div>
                        <div class="head">
                            <form action="/shop/admin/order" method="get">
                                <div class="form-input">
                                    <input style="width: 25%" type="date" placeholder="Từ ngày..." name="fromDate" required>
                                    <input style="width: 25%" type="date" placeholder="Đến ngày..." name="toDate" required>
                                    <button type="submit" class="search-btn"><i class='bx bx-search'></i></button>
                                    <input type="hidden" value="search" name="command">
                                </div>
                            </form>
                            <form action="/shop/admin/order" method="get">
                                <div class="form-input">
                                    
                                    <select accept-charset="utf-8" name="trang_thai">
                                        <option accept-charset="utf-8"  value="" selected>Trạng thái</option>
                                        <option accept-charset="utf-8"  value="Đang chờ duyệt" >Đang chờ duyệt</option>
                                        <option accept-charset="utf-8"  value="Đã giao hàng" >Đã giao hàng</option>
                                        <option accept-charset="utf-8"  value="Đã hủy" >Đã hủy</option>
                                        
                                    </select>       
                                    
                                    <input type="hidden" value="filter" name="command">
                                    <button type="submit" class="search-btn"><i class='bx'>Lọc</i></button>
                                </div>
                            </form>
                         </div>
                        <table>
                            <thead>
                                <tr>
                                    <th style="text-align: center">Mã đơn hàng</th>
                                    <th style="text-align: center">Tên người mua</th>
                                    <th style="text-align: center">Tên sản phẩm</th>
                                    <th style="text-align: center">Hình ảnh</th>
                                    <th style="text-align: center">Số lượng</th>
                                    <th style="text-align: center">Thời gian mua</th>
                                    <th style="text-align: center">Thành tiền</th>
                                    <th style="text-align: center">Trạng thái</th>
                                    <th style="text-align: center">Sửa</th>
                                    <th style="text-align: center">Xóa</th>

                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="productMap" value="${requestScope.productMap}"></c:set>
                                <c:set var="userMap" value="${requestScope.userMap}"></c:set>
                                <c:forEach items="${requestScope.orders}" varStatus="loop" var="order">
                                    
                                    <tr>
                                        <td style="text-align: center; justify-content: center;">${order.order_id}</td>
                                        
                                        <td style="text-align: center">${userMap[order.user_id].username}</td>
                                        <td style="text-align: center">${productMap[order.ma_san_pham].ten_san_pham}</td>
                                        <td style="text-align: center"><img src="/shop/sanpham/${productMap[order.ma_san_pham].hinh_anh}"> </td>
                                        <td style="text-align: center">${order.so_luong}</td>
                                        <td style="text-align: center">${order.ngay_mua}</td>
                                        <td style="text-align: center">${order.thanh_tien}</td>
                                        <td style="text-align: center">${order.trang_thai}</td>
                                        <c:if test="${order.trang_thai == 'Đang chờ duyệt'}">
                                            <td><a style="padding: 5px;" class="delete" href="/shop/admin/order?command=update&order_id=${order.order_id}">Duyệt</a></td>
                                            <td><a style="padding: 5px" class="delete" href="/shop/admin/order?command=delete&order_id=${order.order_id}">Hủy</a></td>
                                        </c:if>
                                        <c:if test="${order.trang_thai == 'Đã giao hàng'}">
                                            <td><a style="padding: 5px; pointer-events: none; background-color: var(--red);" class="delete" href="/shop/admin/order?command=update&order_id=${order.order_id}">Duyệt</a></td>
                                            <td><a style="padding: 5px" class="delete" href="/shop/admin/order?command=delete&order_id=${order.order_id}">Hủy</a></td>
                                        </c:if>
                                        <c:if test="${order.trang_thai == 'Đã hủy'}">
                                            <td><a style="padding: 5px; pointer-events: none; background-color: var(--red);" class="delete" href="/shop/admin/order?command=update&order_id=${order.order_id}">Duyệt</a></td>
                                            <td><a style="padding: 5px; pointer-events: none; background-color: var(--red);" class="delete" href="/shop/admin/order?command=delete&order_id=${order.order_id}">Hủy</a></td>
                                        </c:if>
                                        

                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        
                    </div>
                </div>
            </main>
        </section>
    
    <script src="/shop/js/script.js"></script>
</body>

</html>