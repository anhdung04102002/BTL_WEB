/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.OrderDAO;
import dao.OrderDAOImpl;
import dao.ProductDAO;
import dao.ProductDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
<<<<<<< HEAD
import java.text.ParseException;
import java.text.SimpleDateFormat;
=======
>>>>>>> 28a0da9dee07f49a67e9abde61520c936320e33b
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
<<<<<<< HEAD
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
=======
import java.util.HashMap;
import java.util.List;
import java.util.Map;
>>>>>>> 28a0da9dee07f49a67e9abde61520c936320e33b
import model.Category;
import model.Order;
import model.Product;
import model.User;
import util.Email;

/**
 *
 * @author Windows 10 TIMT
 */
@WebServlet(name="QLDonHangController", urlPatterns={"/admin/order"})
public class QLDonHangController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private List<Order> orders;
    Map<Integer, User> userMap = new HashMap<>();
    Map<Integer, Product> productMap = new HashMap<>();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet QLDonHangController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet QLDonHangController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String command = request.getParameter("command");
//        System.out.println(command);
        if (command != null) {
            if (command.equals("delete")) {
                OrderDAO orderDAO = new OrderDAOImpl();
                int order_id = Integer.parseInt(request.getParameter("order_id"));
                Order order = orderDAO.getOrderByOrder_id(order_id);
                if (order != null) {
                    String trang_thai = order.getTrang_thai();
                    Product product = productMap.get(order.getMa_san_pham());
                    if (trang_thai.equals("Đã giao hàng")) {
                        ProductDAO productDAO = new ProductDAOImpl();
                        
                        product.setSo_luong_kho(product.getSo_luong_kho() + order.getSo_luong());
                        product.setSo_luong_ban(product.getSo_luong_ban() - order.getSo_luong());
                        productDAO.updateProduct(product);
                        
                    }
                    order.setTrang_thai("Đã hủy");
                    orderDAO.updateOrder(order);
                    User user = userMap.get(order.getUser_id());
                    String subject = "Đơn hàng bị hủy";
                    String content = "";
                    content += "<i>Đơn hàng bị hủy</i><br/>";
                    content += "<p>Đơn hàng của người dùng <strong>" + user.getUsername() + "</strong></p>";
                    content += "<p><strong>Thông tin chi tiết: </strong></p>";
                    content += "<p>Tên sản phẩm: " + product.getTen_san_pham() + "</p>";
                    content += "<p>Giá bán: " + product.getGia_ban()+ "</p>";
                    content += "<p>Mô tả: " + product.getThong_tin()+ "</p>";
                    content += "<p>Số lượng:  " + order.getSo_luong() + "</p>";
                    content += "<p>Thời gian mua:  " + order.getNgay_mua().toString() + "</p>";
                    content += "<p>Thành tiền:  " + order.getThanh_tien() + "</p>";
                    Email.send(user.getEmail(), subject, content);
                    response.sendRedirect("/shop/admin/order");
                }
            }
            else if (command.equals("add")) {
                request.setAttribute("userMap", userMap);
                request.setAttribute("productMap", productMap);
                request.getRequestDispatcher("/views/admin/ThemDonHang.jsp").forward(request, response);
            }
            else if (command.equals("update")) {
                int order_id = Integer.parseInt(request.getParameter("order_id"));
                OrderDAO orderDAO = new OrderDAOImpl();
                Order order = orderDAO.getOrderByOrder_id(order_id);
                ProductDAO productDAO = new ProductDAOImpl();
                Product product = productDAO.getProduct(order.getMa_san_pham());
                String err = "";
                if (product.getSo_luong_kho() < order.getSo_luong()) {
                    err += "Không đủ hàng";
                    
                }   
                if (err.length() == 0) {
                    product.setSo_luong_kho(product.getSo_luong_kho() - order.getSo_luong());
                    product.setSo_luong_ban(product.getSo_luong_ban() + order.getSo_luong());
                    productDAO.updateProduct(product);
                    order.setTrang_thai("Đã giao hàng");
                    orderDAO.updateOrder(order);
                    User user = userMap.get(order.getUser_id());
                    String subject = "Đơn hàng được duyệt";
                    String content = "";
                    content += "<i>Đơn hàng được duyệt</i><br/>";
                    content += "<p>Đơn hàng của người dùng <strong>" + user.getUsername() + "</strong></p>";
                    content += "<p><strong>Thông tin chi tiết: </strong></p>";
                    content += "<p>Tên sản phẩm: " + product.getTen_san_pham() + "</p>";
                    content += "<p>Giá bán: " + product.getGia_ban()+ "</p>";
                    content += "<p>Mô tả: " + product.getThong_tin()+ "</p>";
                    content += "<p>Số lượng:  " + order.getSo_luong() + "</p>";
                    content += "<p>Thời gian mua:  " + order.getNgay_mua().toString() + "</p>";
                    content += "<p>Thành tiền:  " + order.getThanh_tien() + "</p>";
                    Email.send(user.getEmail(), subject, content);
                    response.sendRedirect("/shop/admin/order");
                }
                else{
                    request.setAttribute("err", err);
                    request.setAttribute("orders", orders);
                    request.setAttribute("userMap", userMap);
                    request.setAttribute("productMap", productMap);
                    RequestDispatcher rd = request.getRequestDispatcher("/views/admin/QLDonHang.jsp");
                    rd.forward(request, response);
                    
                }
            }
<<<<<<< HEAD
            else if (command.equals("search")) {
                try {
                    List<Order> temp = new ArrayList<>();
                    Date fromDate = new Date((new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("fromDate"))).getTime());
                    Date toDate = new Date((new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("toDate"))).getTime());
                    for (Order order : orders) {
                        if (order.getNgay_mua().compareTo(fromDate) >= 0 && order.getNgay_mua().compareTo(toDate) <= 0) {
                            temp.add(order);
                        }
                    }
                    
                    request.setAttribute("userMap", userMap);
                    request.setAttribute("productMap", productMap);
                    request.setAttribute("orders", temp);
                    request.getRequestDispatcher("/views/admin/QLDonHang.jsp").forward(request, response);
                } catch (ParseException ex) {
                    Logger.getLogger(QLDonHangController.class.getName()).log(Level.SEVERE, null, ex);
                    response.sendRedirect("/shop/admin/order");
                }
            }
            else if (command.equals("filter")) {
                String trang_thai = request.getParameter("trang_thai");
                if (trang_thai.equals("")) {
                    response.sendRedirect("/shop/admin/order");
                }
                else{
                    List<Order> temp = new ArrayList<>();
                    for (Order order : orders) {
                        if (order.getTrang_thai().equals(trang_thai)) {
                            temp.add(order);
                        }
                    }
                    request.setAttribute("userMap", userMap);
                    request.setAttribute("productMap", productMap);
                    request.setAttribute("orders", temp);
                    request.getRequestDispatcher("/views/admin/QLDonHang.jsp").forward(request, response);
                }
=======
//            else if (command.equals("search")) {
//                String keyword = request.getParameter("keyword");
//                List<Product> temp = new ArrayList<>();
//                for (Product product : products) {
//                    if (product.getTen_san_pham().contains(keyword)) {
//                        temp.add(product);
//                        System.out.println(product.getTen_san_pham());
//                    }
//                }
//                request.setAttribute("products", temp);
//                request.setAttribute("categories", map);
//                request.getRequestDispatcher("/views/admin/QLSanPham.jsp").forward(request, response);
//            }
            else if (command.equals("filter")) {
                
                
                request.getRequestDispatcher("/views/admin/QLDonHang.jsp").forward(request, response);
>>>>>>> 28a0da9dee07f49a67e9abde61520c936320e33b
            }
        }
        
        else{
        
        
            URL url = new URL("http://localhost:8081/shop/api/order");
    //        System.out.println(url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() != 200) {
                System.out.println("loi " + connection.getResponseCode());
                throw new RuntimeException("Failed : HTTP Error code : "
                        + connection.getResponseCode());
            }
            ObjectMapper objectMapper = new ObjectMapper();
            orders = objectMapper.readValue(connection.getInputStream(), new TypeReference<List<Order>>(){});
            request.setAttribute("orders", orders);
            connection.disconnect();
            UserDAO userDAO = new UserDAOImpl();
            List<User> users = userDAO.getUsers();
            for (User user : users) {
                userMap.put(user.getUser_id(), user);
            }
            request.setAttribute("userMap", userMap);
            
            ProductDAO productDAO = new ProductDAOImpl();
            List<Product> products = productDAO.getList();
            for (Product product : products) {
                productMap.put(product.getMa_san_pham(), product);
            }
            request.setAttribute("productMap", productMap);
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/QLDonHang.jsp");
            rd.forward(request, response);
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        ProductDAO productDAO = new ProductDAOImpl();
        OrderDAO orderDAO = new OrderDAOImpl();
        int order_id = Integer.parseInt(request.getParameter("order_id"));
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        int ma_san_pham = Integer.parseInt(request.getParameter("ma_san_pham"));
        int so_luong = Integer.parseInt(request.getParameter("so_luong"));
//        System.out.println(request.getParameter("ngay_mua"));
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("ngay_mua"), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        
        Timestamp ngay_mua = new Timestamp(dateTime.atZone(ZoneId.of("Asia/Ho_Chi_Minh")).toInstant().toEpochMilli());
//        System.out.println(dateTime.toLocalTime());
        String err = "";
        String trang_thai = request.getParameter("trang_thai");
        String command = request.getParameter("command");
        if (command.equals("add")) {
            Product product = productDAO.getProduct(ma_san_pham);
            if (trang_thai.equals("Đã giao hàng")) {
                
                if (product.getSo_luong_kho() < so_luong) {
                    err += "Không đủ hàng";
                    request.setAttribute("userMap", userMap);
                    request.setAttribute("productMap", productMap);
                    request.setAttribute("err", err);
                    request.getRequestDispatcher("/views/admin/ThemDonHang.jsp").forward(request, response);
                }   
                if (err.length() == 0) {
                    product.setSo_luong_kho(product.getSo_luong_kho() - so_luong);
                    product.setSo_luong_ban(product.getSo_luong_ban() + so_luong);
                    productDAO.updateProduct(product);
                }
                
            }
            if (err.length() == 0) {
//                System.out.println("them don hang");
                Order order = new Order(order_id, user_id, ma_san_pham, ngay_mua, so_luong, product.getGia_ban() * so_luong, trang_thai);
                URL url = new URL("http://localhost:8081/shop/api/order");
    //                System.out.println(url);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("Accept", "application/json");
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);

                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.writeValue(connection.getOutputStream(), order);
    //                System.out.println("viet thanh cong");
                if (connection.getResponseCode() != 200) {
                    System.out.println("loi " + connection.getResponseCode());

                    throw new RuntimeException("Failed : HTTP Error code : "
                            + connection.getResponseCode());
                }
                
            }
            
            
        }        
        response.sendRedirect("/shop/admin/order");
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
