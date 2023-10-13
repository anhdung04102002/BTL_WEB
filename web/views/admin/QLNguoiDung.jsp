<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>Danh sách s?n ph?m</title>
</head>

<body>


    <!-- SIDEBAR -->
    <section id="sidebar">
        <a href="/shop/admin" class="brand">
            <i class='bx bxs-smile'></i>
            <span class="text">Trang ch?</span>
        </a>
        <ul class="side-menu top">
            <li class="">
                <a href="dashboard.html" id="thongke">
                    <i class='bx bxs-dashboard'></i>
                    <span class="text">Dashboard</span>
                </a>
            </li>
            <li>
                <a href="recent.html" id="thongke">
                    <i class='bx bxs-dashboard'></i>
                    <span class="text">Recent</span>
                </a>
            </li>
            <li class="active">
                <a href="danhsachsp.html" id="mystore">
                    <i class='bx bxs-shopping-bag-alt'></i>
                    <span class="text">Danh sách s?n ph?m</span>
                </a>

            </li>
            <li>
                <a href="danhmuc.html" id="addcategory">
                    <i class='bx bxs-doughnut-chart'></i>
                    <span class="text">danh m?c s?n ph?m</span>
                </a>
            </li>
            <li>
                <a href="#">
                    <i class='bx bxs-message-dots'></i>
                    <span class="text">Message</span>
                </a>
            </li>
            <li>
                <a href="account.html" id="tk">
                    <i class='bx bxs-group'></i>
                    <span class="text">account</span>
                </a>
            </li>
            <li>
                <a href="phanquyen.html" id="tk">
                    <i class='bx bxs-group'></i>
                    <span class="text">Phân quy?n</span>
                </a>
            </li>
            <li>
                <a href="#" id="mystore">
                    <i class='bx bxs-shopping-bag-alt'></i>
                    <span class="text">Quay l?i trang Home</span>
                </a>

            </li>
        </ul>

        <ul class="side-menu">
            <li>
                <a href="#" class="logout">
                    <i class='bx bxs-log-out-circle'></i>
                    <span class="text">Logout</span>
                </a>
            </li>
        </ul>
    </section>
    <section id="content">
        <!-- NAVBAR -->
        <nav>
            <i class='bx bx-menu'></i>
            <a href="#" class="nav-link">Categories</a>
            <form action="#">
                <div class="form-input">
                    <input type="search" placeholder="Search...">
                    <button type="submit" class="search-btn"><i class='bx bx-search'></i></button>
                </div>
            </form>
            <input type="checkbox" id="switch-mode" hidden>
            <label for="switch-mode" class="switch-mode"></label>
            <!-- <a href="#" class="notification">
					<i class='bx bxs-bell'></i>
					<span class="num">8</span>
				</a>
				<a href="#" class="profile">
					<img src="img/people.png">
				</a> -->
            <a href="#" class="btn-download">
                <i class='bx bxs-cloud-download'></i>
                <span class="text">thêm tài kho?n</span>
            </a>

        </nav>
        <main>
            <div div class="table-data">
                <div class="order list_product">
                    <div class="head">
                        <button class="btn-download js-them">
                            <i class='bx bxs-cloud-download'></i>
                            <span class="text">thêm s?n ph?m</span>
                        </button>
                        <h3>Danh m?c s?n ph?m</h3>
                        <i class='bx bx-search'></i>
                        <i class='bx bx-filter'></i>
                    </div>

                    <table>
                        <thead>
                            <tr>
                                <th>s?n ph?m</th>
                                <th>image</th>
                                <th>count</th>
                                <th>pice</th>
                                <th>s?a</th>
                                <th>xóa</th>

                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <img src="img/people.png">
                                    <p>Áo n?</p>
                                </td>
                                <td><img src="" alt=""></td>
                                <td>1000</td>
                                <td><span class="status completed">500$</span></td>
                                <td><button class="update js-sua">s?a</button></td>
                                <td><a class="delete" href="#">xóa</a></td>
                            </tr>
                            <tr>
                                <td>
                                    <img src="img/people.png">
                                    <p>Áo n?</p>
                                </td>
                                <td><img src="" alt=""></td>
                                <td>1000</td>
                                <td><span class="status completed">500$</span></td>
                                <td><button class="update js-sua">s?a</button></td>
                                <td><a class="delete" href="#">xóa</a></td>
                            </tr>
                            <tr>
                                <td>
                                    <img src="img/people.png">
                                    <p>Áo n?</p>
                                </td>
                                <td><img src="" alt=""></td>
                                <td>20</td>
                                <td><span class="status completed">500$</span></td>
                                <td><button class="update js-sua">s?a</button></td>
                                <td><a class="delete" href="#">xóa</a></td>
                            </tr>
                            <tr>
                                <td>
                                    <img src="img/people.png">
                                    <p>Áo n?</p>
                                </td>
                                <td><img src="" alt=""></td>
                                <td>50</td>
                                <td><span class="status completed">500$</span></td>
                                <td><button class="update js-sua">s?a</button></td>
                                <td><a class="delete" href="#">xóa</a></td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="pagination">
                        <ul>
                            <li><a href="#"><i class="fas fa-chevron-left"></i></a></li>
                            <li><a href="#">1</a></li>
                            <li><a href="#">2</a></li>
                            <li><a href="#">3</a></li>
                            <li><a href="#"><i class="fas fa-chevron-right"></i></a></li>
                            <!-- Thêm các nút phân trang khác vào ?ây -->
                        </ul>
                    </div>

                </div>
                <!-- <div class="todo addproduct">
                    <div class="head">
                        <h3>thêm s?n ph?m</h3>
                        <i class='bx bx-plus'></i>
                        <i class='bx bx-filter'></i>
                        <input class="savesp" type="submit" title="L?u s?n ph?m" value="l?u s?n ph?m" />
                    </div>
                    <ul class="todo-list">
                        <li class="completed">
                            <p>tên s?n ph?m <input class="addsp" type="text" placeholder="tên s?n ph?m..."></p>
                            <i class='bx bx-dots-vertical-rounded'></i>
                        </li>
                        <li class="completed">
                            <p>pice <input class="addsp" type="text" placeholder="giá..."></p>
                            <i class='bx bx-dots-vertical-rounded'></i>
                        </li>
                        <li class="not-completed">
                            <p>image<input class="addsp" type="file" placeholder=""></p>
                            <i class='bx bx-dots-vertical-rounded'></i>
                        </li>
                        <li class="completed">
                            <p>n?i dung <textarea class="text-sp" name="content" id="product-content"></textarea>
                            </p>
                            <i class='bx bx-dots-vertical-rounded'></i>
                        </li>
                        <li class="not-completed">
                            <p>s? l??ng <input class="addsp" type="text" placeholder="note..."></p>
                            <i class='bx bx-dots-vertical-rounded'></i>
                        </li>
                    </ul>

                </div> -->
            </div>
        </main>
    </section>
<!--thêm-->
<form action="">
    <div class="modalthem js_modal">
		<div class="todo change_product change">
			<div class="head">
				<h3>thêm s?n ph?m</h3>
				<i class='bx bx-filter'></i>
				<input class="savesp" type="submit" title="L?u s?n ph?m" value="l?u s?n ph?m" />
			</div>
			<ul class="todo-list">
				<li class="completed_list">
					<p>tên s?n ph?m <input class="addsp" type="text" placeholder="tên s?n ph?m..."></p>

				</li>
				<li class="completed_list">
					<p>giá s?n ph?m <input class="addsp" type="text" placeholder="giá..."></p>

				</li>
				<li class="completed_list">
					<p>?nh s?n ph?m <input class="addsp" type="file" placeholder=""></p>or link<input type="url">

				</li>
				<li class="completed_list">
					<p>n?i dung <textarea class="text-sp" name="content" id="product-content"></textarea>
					</p>

				</li>
				<li class="completed_list">
					<p>s? l??ng <input class="addsp" type="text" placeholder="note..."></p>
				</li>

			</ul>
			<div class="footer js-modalclosethem"><a class="close ">close</a></div>
		</div>
	</div>
</form>
    <!-- s?a s?n ph?m -->
	<form action="">
        <div class="modalsua js_modal">
            <div class="todo change_product change">
                <div class="head">
                    <h3>s?a s?n ph?m</h3>
                    <i class='bx bx-filter'></i>
                    <input class="savesp" type="submit" title="L?u s?n ph?m" value="l?u s?n ph?m" />
                </div>
                <ul class="todo-list">
                    <li class="completed_list">
                        <p>tên s?n ph?m <input class="addsp" type="text" placeholder="tên s?n ph?m..."></p>
    
                    </li>
                    <li class="completed_list">
                        <p>giá s?n ph?m <input class="addsp" type="text" placeholder="giá..."></p>
    
                    </li>
                    <li class="completed_list">
                        <p>?nh s?n ph?m <input class="addsp" type="file" placeholder=""></p>or link<input type="url">
    
                    </li>
                    <li class="completed_list">
                        <p>n?i dung <textarea class="text-sp" name="content" id="product-content"></textarea>
                        </p>
    
                    </li>
                    <li class="completed_list">
                        <p>s? l??ng <input class="addsp" type="text" placeholder="note..."></p>
                    </li>
    
                </ul>
                <div class="footer js-modalclosesua"><a class="close ">close</a></div>
            </div>
        </div>
    </form>
    <script src="script.js"></script>
</body>

</html>