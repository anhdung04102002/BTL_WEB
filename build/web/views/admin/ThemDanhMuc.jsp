<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/cssCategory.css' />">
</head>

<body>
     
    <form action="themdanhmuc" method="post" enctype="multipart/form-data">
        <div class="modalsua js-modal">
            <div class="todo change">
                <div class="head">
                  <h3 style = "color: red">${requestScope.err}</h3>
                    <h3>Thêm danh mục</h3>
                    <i class='bx bx-filter'></i>
                    <input class="savesp" type="submit" value="Lưu thông tin" />
                </div>
                <ul class="todo-list">
                    <li class="completed_list">
                        <p>Tên danh mục <input class="addsp" type="text" required="" placeholder="Điền thông tin tên danh mục..." name="ten_the_loai"  /></p>
                    </li>
                    <li class="completed_list">
                        <p>Mô tả <input class="addsp" type="text" name="mo_ta" required="" placeholder="Viết mô tả..." /></p>
                    </li>
                    <li class="completed_list">
                    <p>Hình ảnh <input class="addsp" type="file" name="hinh_anh" required=""/></p>
                </li>
                </ul>
                <div class="footer js-modalclosesua">
                    <a class="close" onclick="closePage()">close</a>
                </div>
            </div>
        </div>
    </form>

    <script>
        function closePage() {
            window.location.href = "listdanhmuc";
        }
    </script>
</body>
</html>
