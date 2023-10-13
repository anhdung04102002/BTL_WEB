/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Category;
import model.Product;
import model.User;


/**
 *
 * @author Windows 10 TIMT
 */
@WebServlet(name="QLSanPhamController", urlPatterns={"/admin-product"})
@MultipartConfig
public class QLSanPhamController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private List<Product> products = new ArrayList<>();
    private Map<Integer, Category> map = new HashMap<>();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet QLSanPhamController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet QLSanPhamController at " + request.getContextPath () + "</h1>");
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
                int ma_san_pham = Integer.parseInt(request.getParameter("ma_san_pham"));
                URL url1 = new URL("http://localhost:8081/shop/api/product/" + ma_san_pham);
    //            System.out.println(url1);
                HttpURLConnection connection1 = (HttpURLConnection) url1.openConnection();
                connection1.setRequestProperty("Accept", "application/json");
                connection1.setRequestMethod("DELETE");
                if (connection1.getResponseCode() == 200) {
                    ObjectMapper objectMapper1 = new ObjectMapper();
                    Product product = objectMapper1.readValue(connection1.getInputStream(), Product.class);
//                    System.out.println(product.getMa_san_pham());
                    connection1.disconnect();
                    String path = getServletContext().getRealPath("/");
                    String filepath = path + "sanpham" + File.separator + product.getHinh_anh();
                    File file = new File(filepath);
                    file.delete();
//                    request.getRequestDispatcher("views/admin/QLSanPham.jsp").forward(request, response);
                    response.sendRedirect("/shop/admin-product");
                    
                }
                
            }
            else if (command.equals("update")) {
                int ma_san_pham = Integer.parseInt(request.getParameter("ma_san_pham"));
                URL url1 = new URL("http://localhost:8081/shop/api/product/" + ma_san_pham);
    //            System.out.println(url1);
                HttpURLConnection connection1 = (HttpURLConnection) url1.openConnection();
                connection1.setRequestProperty("Accept", "application/json");
                connection1.setRequestMethod("GET");
                if (connection1.getResponseCode() == 200) {
                    ObjectMapper objectMapper1 = new ObjectMapper();
                    Product product = objectMapper1.readValue(connection1.getInputStream(), Product.class);
//                    System.out.println(product.getMa_san_pham());
//                    System.out.println(product.getTen_san_pham());
                    request.setAttribute("product", product);
                    connection1.disconnect();
                    request.setAttribute("categories", map);
//                    response.sendRedirect("/shop/admin-product");
                    
                }
                request.getRequestDispatcher("views/admin/SuaSanPham.jsp").forward(request, response);
            }
            else if (command.equals("search")) {
                String keyword = request.getParameter("keyword");
                List<Product> temp = new ArrayList<>();
                for (Product product : products) {
                    if (product.getTen_san_pham().contains(keyword)) {
                        temp.add(product);
                        System.out.println(product.getTen_san_pham());
                    }
                }
                request.setAttribute("products", temp);
                request.setAttribute("categories", map);
                request.getRequestDispatcher("/views/admin/QLSanPham.jsp").forward(request, response);
            }
            else if (command.equals("filter")) {
                List<Product> temp = new ArrayList<>();
                if (!request.getParameter("ma_the_loai").isBlank()) {
                    int ma_the_loai = Integer.parseInt(request.getParameter("ma_the_loai"));
                    for (Product product : products) {
                        if (product.getMa_the_loai() == ma_the_loai) {
                            temp.add(product);
                            System.out.println(product.getTen_san_pham());
                        }
                    }
                }
                else{
                    temp = products;
                }
                int orderBy = Integer.parseInt(request.getParameter("orderBy"));
                if (orderBy == 0) {
                    temp.sort(new Comparator<Product>(){
                        @Override
                        public int compare(Product o1, Product o2) {
                            return o1.getGia_ban() - o2.getGia_ban();
                        }
                        
                    });
                } else {
                    temp.sort(new Comparator<Product>(){
                        @Override
                        public int compare(Product o1, Product o2) {
                            return o2.getGia_ban() - o1.getGia_ban();
                        }
                        
                    });
                }
                request.setAttribute("products", temp);
                request.setAttribute("categories", map);
                request.getRequestDispatcher("/views/admin/QLSanPham.jsp").forward(request, response);
            }
        }
        
        else{
        
        
            URL url = new URL("http://localhost:8081/shop/api/product");
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
            products = objectMapper.readValue(connection.getInputStream(), new TypeReference<List<Product>>(){});
    //        for (Product product : products) {
    //            System.out.println(product.getTen_san_pham());
    //        }

            request.setAttribute("products", products);
            connection.disconnect();
            url = new URL("http://localhost:8081/shop/api/category");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() != 200) {
                System.out.println("loi " + connection.getResponseCode());
                throw new RuntimeException("Failed : HTTP Error code : "
                        + connection.getResponseCode());
            }
            List<Category> categories = objectMapper.readValue(connection.getInputStream(), new TypeReference<List<Category>>(){});

            for (Category category : categories) {
                map.put(category.getMa_the_loai(), category);
            }
            request.setAttribute("categories", map);
            connection.disconnect();
            RequestDispatcher rd = request.getRequestDispatcher("views/admin/QLSanPham.jsp");
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
        
        int ma_san_pham = Integer.parseInt(request.getParameter("ma_san_pham"));
        String ten_san_pham = request.getParameter("ten_san_pham");
        int gia_ban = Integer.parseInt(request.getParameter("gia_ban"));
        int ma_the_loai = Integer.parseInt(request.getParameter("ma_the_loai"));
        String hang_san_xuat = request.getParameter("hang_san_xuat");
        String thong_tin = request.getParameter("thong_tin");
        int so_luong_kho = Integer.parseInt(request.getParameter("so_luong_kho"));
//        System.out.println(request.getParameter("so_luong_kho"));
        int so_luong_ban = Integer.parseInt(request.getParameter("so_luong_ban"));
        int hien_thi = (request.getParameter("hien_thi") != null)? 1:0;
        
        String command = request.getParameter("command");
        if (command.equals("add")) {
            //lưu file
            Part filePart = request.getPart("hinh_anh");
            String fileName = filePart.getSubmittedFileName();
            String path = getServletContext().getRealPath("/");
            String filepath = path + "sanpham" + File.separator + fileName;
//                System.out.println(filepath);
            InputStream inputStream = filePart.getInputStream();
            File file =  new File(filepath);
            OutputStream outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            inputStream.close();
            //lưu file
            URL url = new URL("http://localhost:8081/shop/api/product");
//                System.out.println(url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            Product product = new Product(ma_san_pham, ma_the_loai, ten_san_pham, fileName, gia_ban, hang_san_xuat, thong_tin, so_luong_kho, so_luong_ban, hien_thi);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(connection.getOutputStream(), product);
//                System.out.println("viet thanh cong");
            if (connection.getResponseCode() != 200) {
                System.out.println("loi " + connection.getResponseCode());
                if (file.delete()) {
                    System.out.println("xoa thanh cong");
                } else {
                    System.out.println("xoa that bai");
                }
                throw new RuntimeException("Failed : HTTP Error code : "
                        + connection.getResponseCode());
            }
//            response.sendRedirect("/shop/admin-product");
            
        }
        else if (command.equals("update")) {
            
            URL url1 = new URL("http://localhost:8081/shop/api/product/" + ma_san_pham);
//            System.out.println(url1);
            HttpURLConnection connection1 = (HttpURLConnection) url1.openConnection();
            connection1.setRequestProperty("Accept", "application/json");
            connection1.setRequestMethod("GET");
            Product product = new Product();
            if (connection1.getResponseCode() == 200) {
                ObjectMapper objectMapper1 = new ObjectMapper();
                product = objectMapper1.readValue(connection1.getInputStream(), Product.class);
//                    System.out.println(product.getMa_san_pham());
//                System.out.println(product.getTen_san_pham());
                request.setAttribute("product", product);
                connection1.disconnect();
                request.setAttribute("categories", map);
//                    response.sendRedirect("/shop/admin-product");

            }
            
            
            Part filePart = request.getPart("hinh_anh");
//            System.out.println(filePart.getSubmittedFileName());
            String fileName = filePart.getSubmittedFileName();
            if (!fileName.isEmpty()) {
                String path = getServletContext().getRealPath("/");
                String filepath = path + "sanpham" + File.separator + fileName;
                
                //xoa file anh cu
                File fileCu = new File(path + "sanpham" + File.separator + fileName);
                fileCu.delete();
                //luu file moi
//                    System.out.println(filepath);
                InputStream inputStream = filePart.getInputStream();
                File file =  new File(filepath);
                OutputStream outputStream = new FileOutputStream(file);
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.close();
                inputStream.close();
            }
            else{
                fileName = product.getHinh_anh();
            }
            URL url = new URL("http://localhost:8081/shop/api/product");
//                System.out.println(url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);
            product.setTen_san_pham(ten_san_pham);
            product.setMa_the_loai(ma_the_loai);
            product.setHinh_anh(fileName);
            product.setGia_ban(gia_ban);
            product.setHang_san_xuat(hang_san_xuat);
            product.setThong_tin(thong_tin);
            product.setSo_luong_kho(so_luong_kho);
            product.setSo_luong_ban(so_luong_ban);
            product.setHien_thi(hien_thi);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(connection.getOutputStream(), product);
//                System.out.println("viet thanh cong");
            if (connection.getResponseCode() != 200) {
                System.out.println("loi " + connection.getResponseCode());
                throw new RuntimeException("Failed : HTTP Error code : "
                        + connection.getResponseCode());
            }
        }
        
        response.sendRedirect("/shop/admin-product");
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
