/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;
import dao.CategoryDAO;
import dao.CategoryDAOImpl;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hoang Anh Dung
 */
@WebServlet(name = "DanhMucSuaServlet", urlPatterns = {"/suadanhmuc"})
@MultipartConfig
public class DanhMucSuaServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        String id_raw = request.getParameter("ma_the_loai");
////        String hinhanh = request.getParameter("hinhanh");
//
//        int id;
//        CategoryDAOImpl cdb = new CategoryDAOImpl();
//        try {
//            //không chia làm doGet với doPost nữa mà gộp lại(chưa tìm được lỗi sai)
//            id = Integer.parseInt(id_raw);
//            Category c = cdb.getCategory(id);
//            request.setAttribute("category", c);
//            String ten = request.getParameter("ten_the_loai");
//            String mo_ta = request.getParameter("mo_ta");
//            String hinh_anh = request.getParameter("hinh_anh");
////            Category cnew = new Category(id, ten, mo_ta, hinh_anh);
////            cdb.updateCategory(cnew);
//            request.getRequestDispatcher("listdanhmuc").forward(request, response);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String id_raw = request.getParameter("ma_the_loai");
        String ten = request.getParameter("ten_the_loai");
        String ten_cu = request.getParameter("ten_the_loai_cu");
        String mo_ta = request.getParameter("mo_ta");
        String hinh_anh = request.getParameter("hinh_anh");
        int id;
        CategoryDAOImpl cdb = new CategoryDAOImpl();
        String err = "Trùng tên danh mục";
        try {
            id = Integer.parseInt(id_raw);
            Part filePart = request.getPart("hinhanh");
            String fileName = filePart.getSubmittedFileName();
            String folderPath = getServletContext().getRealPath("/");
            if (filePart != null && !fileName.isEmpty()) {
                // Xử lý lưu file và cập nhật dữ liệu
                String filePath = folderPath + "images" + File.separator + fileName;
                try ( InputStream inputStream = filePart.getInputStream();  OutputStream outputStream = new FileOutputStream(filePath)) {
                    byte[] buffer = new byte[4096];
                    int bytesRead = -1;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
                if (ten.equals(ten_cu)) {
                    // Không cần kiểm tra trùng tên, tiếp tục cập nhật danh mục
                    Category cnew = new Category(id, ten, mo_ta, fileName);
                    cdb.updateCategory(cnew);
                    response.sendRedirect("listdanhmuc");
                } else {
                    // Kiểm tra trùng tên
                    if (cdb.checkCtegory(ten)) {
                        request.setAttribute("err", err);
                        request.getRequestDispatcher("iddanhmuc").forward(request, response);
                    } else {
                        // Không bị trùng tên, tiếp tục cập nhật danh mục
                        Category cnew = new Category(id, ten, mo_ta, fileName);
                        cdb.updateCategory(cnew);
                        response.sendRedirect("listdanhmuc");
                    }
                }
            } else { // trường hợp k sửa ảnh
                if (ten.equals(ten_cu)) {
                    // Không cần kiểm tra trùng tên, tiếp tục cập nhật danh mục
                    Category cnew = new Category(id, ten, mo_ta, hinh_anh);
                    cdb.updateCategory(cnew);
                    response.sendRedirect("listdanhmuc");
                } else {
                    // Kiểm tra trùng tên
                    if (cdb.checkCtegory(ten)) {
                        request.setAttribute("err", err);
                        request.getRequestDispatcher("iddanhmuc").forward(request, response);
                    } else {
                        // Không bị trùng tên, tiếp tục cập nhật danh mục
                        Category cnew = new Category(id, ten, mo_ta, hinh_anh);
                        cdb.updateCategory(cnew);
                        response.sendRedirect("listdanhmuc");
                    }
                }
            }
            response.sendRedirect("listdanhmuc");
        } catch (NumberFormatException e) {
            System.out.println(e);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
