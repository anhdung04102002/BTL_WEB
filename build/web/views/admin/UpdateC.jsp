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
        <c:set var="d" value="${requestScope.category}" />
        <form action="suadanhmuc" method="post" enctype="multipart/form-data">
            <div class="modalsua js-modal">
                <div class="todo change">
                    <div class="head">
                        <h3 style="color: red">${requestScope.err}</h3>
                        <h3>Sửa danh mục</h3>
                        <i class='bx bx-filter'></i>
                        <input class="savesp" type="submit" value="Lưu thông tin" />
                    </div>
                    <ul class="todo-list">
                        <li class="completed_list">
                            <p>Mã thể loại <input class="addsp" type="number" readonly name="ma_the_loai" value="${d.ma_the_loai}" /></p>
                        </li>
                        <li class="completed_list">
                            <p>Tên danh mục <input class="addsp" type="text" placeholder="Điền thông tin tên danh mục..." name="ten_the_loai" value="${d.ten_the_loai}" required=""/></p>
                            <input class="addsp" type="hidden" placeholder="Điền thông tin tên danh mục..." name="ten_the_loai_cu" value="${d.ten_the_loai}" required=""/>
                        </li>
                        <li class="completed_list">
                            <p>Mô tả <input class="addsp" type="text" name="mo_ta" value="${d.mo_ta}" placeholder="Viết mô tả..." required=""/></p>
                        </li>
                        <li class="completed_list">
                            <p>Hình ảnh</p>
<!--                            <img src="<c:url value='/images/${d.hinh_anh}'/>" alt="Ảnh hiện tại" width="100" height="100">-->
                            <input class="addsp" type="file"  name="hinhanh" >     
                            <!--nếu choose file trống thì chọn cái get dữ liệu mặc định phía dưới để gửi lên server-->
                            <input  type = "hidden" name="hinh_anh" type ="text" value="${d.hinh_anh}" />

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
