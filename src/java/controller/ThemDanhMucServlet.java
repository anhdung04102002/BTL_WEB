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
import jakarta.servlet.annotation.MultipartConfig;
import dao.CategoryDAO;
import dao.CategoryDAOImpl;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import java.io.*;

/**
 *
 * @author Hoang Anh Dung
 */
@WebServlet(name = "ThemDanhMucServlet", urlPatterns = {"/themdanhmuc"})
@MultipartConfig
public class ThemDanhMucServlet extends HttpServlet {

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html;charset=UTF-8");
//        String hinh_anh = request.getParameter("hinh_anh");
//        Path path = Paths.get(hinh_anh);
//        String ten_tep = path.getFileName().toString();
//        String filePath = "/images/";
//        String ten_the_loai = request.getParameter("ten_the_loai");
//        String mo_ta = request.getParameter("mo_ta");
////        String hinh_anh = request.getParameter("hinh_anh");
//// Sao chép tệp ảnh vào thư mục trong dự án của bạn
////        Files.copy(Paths.get(hinh_anh), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
//        File sourceFile = new File(hinh_anh);
//        Path destinationPath = Paths.get(getServletContext().getRealPath("/") + "images/" + sourceFile.getName());
//        Files.copy(sourceFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
//// Sử dụng đường dẫn tuyệt đối để lưu vào cơ sở dữ liệu hoặc thực hiện các xử lý khác
//        CategoryDAOImpl cdb = new CategoryDAOImpl();
//        try {
//            Category cnew = new Category(ten_the_loai, mo_ta, hinh_anh);
//            cdb.addCategory(cnew);
//            response.sendRedirect("listdanhmuc");
////            request.getRequestDispatcher("listdanhmuc").forward(request, response);
//        } catch (Exception ex) {
//            Logger.getLogger(ThemDanhMucServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String ten_the_loai = request.getParameter("ten_the_loai");
        String mo_ta = request.getParameter("mo_ta");

        Part filePart = request.getPart("hinh_anh");
        String fileName = filePart.getSubmittedFileName();

        // Đường dẫn thư mục trong project để lưu trữ file ảnh
        String folderPath = getServletContext().getRealPath("/");

        // Tạo đường dẫn hoàn chỉnh cho file ảnh trong thư mục của project
        String filePath = folderPath + "images" + File.separator + fileName;
        // đọc đường dẫn từ client
        InputStream inputStream = filePart.getInputStream();
        // tạo đối tượng để nếu có ghi thì lưu vào outputstream
        OutputStream outputStream = new FileOutputStream(filePath);

        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        outputStream.close();
        inputStream.close();

        CategoryDAOImpl cdb = new CategoryDAOImpl();
        try {
            Category cnew = new Category(ten_the_loai, mo_ta, fileName);
            String err = "Tên danh mục bị trùng ,hãy nhập lại";
            if (cdb.checkCtegory(ten_the_loai) == true) {
                request.setAttribute("err", err);
                request.getRequestDispatcher("views/admin/ThemDanhMuc.jsp").forward(request, response);
            } else {
                cdb.addCategory(cnew);
                response.sendRedirect("listdanhmuc");
            }
        } catch (Exception ex) {
            Logger.getLogger(ThemDanhMucServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
