-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 17, 2023 lúc 05:27 PM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `shopping`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `ma_the_loai` int(11) NOT NULL,
  `ten_the_loai` varchar(255) DEFAULT NULL,
  `mo_ta` varchar(255) DEFAULT NULL,
  `hinh_anh` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`ma_the_loai`, `ten_the_loai`, `mo_ta`, `hinh_anh`) VALUES
(1, 'New', 'Quần áo mới thiết kế', '1.png'),
(2, 'Sale', 'Quần áo giảm giá', '2.png'),
(3, 'Style', 'Quần áo dạo phố', '3.png'),
(4, 'Nam', 'Thời trang nam', '4.png'),
(5, 'Nữ', 'Thời trang nữ', '5.png'),
(6, 'Dép', 'Dép thời trang', '6.png'),
(7, 'Nón', 'Nón thời trang', '7.png');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `order`
--

CREATE TABLE `order` (
  `order_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `ma_san_pham` int(11) DEFAULT NULL,
  `ngay_mua` timestamp NULL DEFAULT NULL,
  `so_luong` int(11) DEFAULT NULL,
  `thanh_tien` int(11) DEFAULT NULL,
  `trang_thai` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `order`
--

INSERT INTO `order` (`order_id`, `user_id`, `ma_san_pham`, `ngay_mua`, `so_luong`, `thanh_tien`, `trang_thai`) VALUES
(8, 13, 2, '2015-05-17 07:55:17', 1, 520000, 'Đã giao hàng'),
(9, 13, 1, '2023-04-27 13:37:16', 1, 100000, 'Đã giao hàng'),
(10, 15, 1, '2023-04-28 02:58:07', 3, 300000, 'Đã giao hàng'),
(11, 13, 4, '2023-05-17 13:36:22', 1, 520000, 'Đang chờ duyệt'),
(12, 13, 5, '2023-05-17 13:40:06', 1, 520000, 'Đang chờ duyệt');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

CREATE TABLE `product` (
  `ma_san_pham` int(11) NOT NULL,
  `ma_the_loai` int(11) DEFAULT NULL,
  `ten_san_pham` varchar(255) DEFAULT NULL,
  `hinh_anh` varchar(255) DEFAULT NULL,
  `gia_ban` double DEFAULT NULL,
  `hang_san_xuat` varchar(255) DEFAULT NULL,
  `thong_tin` varchar(255) DEFAULT NULL,
  `so_luong_kho` int(11) DEFAULT 0,
  `so_luong_ban` int(11) DEFAULT 0,
  `hien_thi` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`ma_san_pham`, `ma_the_loai`, `ten_san_pham`, `hinh_anh`, `gia_ban`, `hang_san_xuat`, `thong_tin`, `so_luong_kho`, `so_luong_ban`, `hien_thi`) VALUES
(1, 1, 'Áo khoát Adachi mới', 'new.jpg', 100000, 'Oron', 'Đang cập nhật', 0, 0, 1),
(2, 2, 'Áo sale', 'nu1.jpg', 520000, 'Omo', 'Đang cập nhật', 0, 0, 1),
(3, 3, 'Áo no style', 'nostyle.jpg', 520000, 'Mabu', 'Đang cập nhật', 0, 0, 1),
(4, 4, 'Áo nam', 'nam.jpg', 520000, 'Lv', 'Đang cập nhật', 0, 0, 1),
(5, 5, 'Áo nữ 2', 'nu2.jpg', 520000, 'Romano', 'Đang cập nhật', 0, 0, 1),
(6, 6, 'Dép', 'dep.jpg', 520000, 'Bitis', 'Đang cập nhật', 0, 0, 1),
(7, 7, 'Nón', 'non.jpg', 520000, 'Goku', 'Đang cập nhật', 0, 0, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `ngaysinh` date DEFAULT NULL,
  `gioitinh` varchar(10) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `sdt` varchar(20) DEFAULT NULL,
  `diachi` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`user_id`, `username`, `password`, `ngaysinh`, `gioitinh`, `email`, `sdt`, `diachi`, `role`) VALUES
(13, 'test', '91f9baf979865cc7d167866e57980075beacc8cd7e80d1c0ac4a15f7f9f5ae27', '2015-05-04', 'Nam', 'test@gmail.com', '0962491151', 'Hà Nội', '2'),
(14, 'user2', '6d24c2fef807883f49ade1e462177655cd74ae84c968e47add2dd9b57ea5da9b', '2015-05-11', 'Nam', 'user2@gmail.com', '0123456', 'Ninh Bình', '2'),
(15, 'anhdnp', '5dc7baa15b8c5f6fb79c0424b4157b19140ca6ad5007595f2f51191ce51b30ed', '2002-12-29', 'Nam', 'anh12310b1111@gmail.com', '0164989498', 'Hà Nội', '2'),
(16, 'user1', 'c74e8acc4d7e71b22d013c05dcbe397c811249d666610de66a66189a7f90d756', '2002-12-29', 'Nam', 'anh12310b1111@gmail.com', '0123456', 'Hà Nội', '2'),
(18, 'user3', 'dc3ebb3f34f11528322b9552d78a5831ca8237d744ebd92977f56f5dc26375e6', '2002-02-21', 'Nam', 'user3@gmail.com', '123', 'hà nội', '2'),
(19, 'user4', '67647785844e77ba91ee96d916e067f9c6c0bc849a1f177e323b68883e6ae7c1', '2002-12-29', 'Nam', 'user4@gmail.com', '123', 'hà nội', '2'),
(20, 'user5', 'b1d90bc654161ab6be60a77fdb54e40bae1af74d1b7178329e9877441fd8842f', '2002-02-21', 'Nam', 'user5@gmail.com', '987', 'hà nội', '2'),
(21, 'user6', 'efd18b006ab7d7becf24c53c58bb31696c182803eef999cd6fa3f98a17ec78cd', '2002-02-22', 'Nam', 'user6@gmail.com', '765', 'hà nội', '2'),
(22, 'user7', 'ed34c8eab8f4c6b3aba4585505c131a940726308b17c6e65aa8cf4b60c8a98f0', '2003-03-03', 'Nữ', 'u3@gmail.com', '963', 'hà nội', '2'),
(23, 'user8', '26b8f0c6004a8c9c69ca80dc399fe871d6d1b66a44736ac50802d9a045fcecaa', '2002-04-04', 'Nam', 'u4@gmail.com', '987', 'hà nội', '2'),
(24, 'user9', 'c7b8560f9edfd5b5bffae4ab33aa6e25d1ae1592dc4c41d1e9cec6f9b8446a2d', '2001-12-29', 'Nam', 'user9@gmail.com', '987654', 'hà nội', '2'),
(25, 'user10', '26d12b0d41d17ba91033c58c8d57cc26ced55c0460b94fbd9125d24a9353725b', '2001-11-11', 'Nữ', 'u11@gmail.com', '123', 'hà nội', '2'),
(26, 'user11', '55218e3218281b466c3e01cc3465da6bf090b4a15586c1de13864aaff93277e6', '2010-11-11', 'Nam', 'u11@gmail.com', '123', 'hà nội', '2'),
(27, 'user12', 'c9741daea4d58de631ed7fd418696fb2db719f4ebddbf215d5bb34ed34a3a6ac', '2001-01-01', 'Nam', 'user1@gmail.com', '098764', 'hà nội', '2'),
(12, 'admin', 'admin', '2002-12-29', 'Nam', 'admin@gmail.com', '0321645979', 'Hà Nội', '1');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`ma_the_loai`);

--
-- Chỉ mục cho bảng `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `fk_history_product` (`ma_san_pham`) USING BTREE,
  ADD KEY `fk_history_user` (`user_id`) USING BTREE;

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`ma_san_pham`),
  ADD KEY `fk_product_category` (`ma_the_loai`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `category`
--
ALTER TABLE `category`
  MODIFY `ma_the_loai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `order`
--
ALTER TABLE `order`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT cho bảng `product`
--
ALTER TABLE `product`
  MODIFY `ma_san_pham` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
