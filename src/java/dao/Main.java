/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import model.Order;
import model.Product;
import util.RandomString;

/**
 *
 * @author Windows 10 TIMT
 */
public class Main {
    public static void main(String[] args) throws IOException {
//        ProductDAO productDAO = new ProductDAOImpl();
//        Product p = new Product();
//        p.setMa_san_pham(0);
//        p.setMa_the_loai(2);
//        p.setTen_san_pham("test");
//        p.setHinh_anh("new.jpg");
//        p.setGia_ban(100000);
//        p.setHang_san_xuat("hãng");
//        p.setThong_tin("thông tin");
//        p.setSo_luong_kho(30);
//        p.setSo_luong_ban(1);
//        p.setHien_thi(1);
//        productDAO.addProduct(p);
//        List<Product> products = productDAO.getList();
//        System.out.println(products.size());
//        for (Product product : products) {
//            System.out.println(product.getTen_san_pham());
//        }
//        System.out.println(System.getProperty("user.dir"));
//        URL url = new URL("http://localhost:8081/shop/api/product");
////        System.out.println(url);
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestProperty("Accept", "application/json");
//        connection.setRequestMethod("POST");
//        connection.setDoOutput(true);
//        Product product = new Product(0, 1, "test", "test.jpg", 10, "test", "test", 0, 0, 1);
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.writeValue(connection.getOutputStream(), product);
//        System.out.println("viet thanh cong");
//        if (connection.getResponseCode() != 200) {
//            System.out.println("loi " + connection.getResponseCode());
//            throw new RuntimeException("Failed : HTTP Error code : "
//                    + connection.getResponseCode());
//        }
//        Product product1 = objectMapper.readValue(connection.getInputStream(), Product.class);
//        System.out.println(product1.getMa_san_pham() + " " + product1.getTen_san_pham());
<<<<<<< HEAD
//        OrderDAO orderDAO = new OrderDAOImpl();
//        Order order = orderDAO.getOrderByOrder_id(8);
//        System.out.println(order.getThanh_tien());
        for (int i = 0; i < 10; i++) {
            System.out.println(RandomString.rand(10));
        }
=======
        OrderDAO orderDAO = new OrderDAOImpl();
        Order order = orderDAO.getOrderByOrder_id(8);
        System.out.println(order.getThanh_tien());
>>>>>>> 28a0da9dee07f49a67e9abde61520c936320e33b
    }
}
