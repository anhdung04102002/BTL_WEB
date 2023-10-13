/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Category;
import model.User;

import dao.CategoryDAO;
import dao.CategoryDAOImpl;
import jakarta.servlet.annotation.WebServlet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hoang Anh Dung
 */
@WebServlet(urlPatterns = {"/api/category"})
public class CategoryApi extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CategoryApi</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CategoryApi at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private boolean isValidCategory(Category category) {
        if (category == null) {
            return false;
        }

        // Kiểm tra các điều kiện hợp lệ khác của đối tượng Category
        if (category.getTen_the_loai() == null || category.getTen_the_loai().isEmpty()) {
            return false;
        }

        // Kiểm tra các ràng buộc khác (tuỳ thuộc vào yêu cầu của ứng dụng)
        return true;
    }

    private boolean isValidCategoryId(int categoryId) {
        // Kiểm tra các điều kiện hợp lệ của ID
        if (categoryId <= 0) {
            return false;
        }

        // Kiểm tra các ràng buộc khác (tuỳ thuộc vào yêu cầu của ứng dụng)
        return true;
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
        try {
            CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
            request.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            List<Category> categorys = categoryDAO.getList();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(response.getWriter(), categorys);
        } catch (Exception ex) {
            Logger.getLogger(CategoryApi.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
            request.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");

            // Đọc dữ liệu JSON được gửi từ client
            ObjectMapper objectMapper = new ObjectMapper();
            Category newCategory = objectMapper.readValue(request.getReader(), Category.class);

            // Thực hiện thêm mới category
            categoryDAO.addCategory(newCategory);
            List<Category> categorys = categoryDAO.getList();
//            objectMapper.writeValue(response.getWriter(), categorys);
            // Gửi phản hồi về client
            String jsonResponse = objectMapper.writeValueAsString(categorys);
            response.getWriter().write(jsonResponse);
        } catch (Exception ex) {
            Logger.getLogger(CategoryApi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
            request.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");

            // Đọc dữ liệu JSON được gửi từ client
            ObjectMapper objectMapper = new ObjectMapper();
            Category updatedCategory = objectMapper.readValue(request.getReader(), Category.class);

            // Kiểm tra tính hợp lệ của dữ liệu và xử lý
            if (isValidCategory(updatedCategory)) {
                // Thực hiện cập nhật category
                categoryDAO.updateCategory(updatedCategory);

                // Gửi phản hồi về client
                String jsonResponse = objectMapper.writeValueAsString(updatedCategory);
                response.getWriter().write(jsonResponse);
            } else {
                // Trường hợp dữ liệu không hợp lệ, gửi phản hồi lỗi về client
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("cập nhật không thành công");
            }
        } catch (Exception ex) {
            Logger.getLogger(CategoryApi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   protected void doDelete(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    try {
        CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        // Lấy ID của category từ yêu cầu DELETE
        int categoryId = Integer.parseInt(request.getParameter("ma_the_loai"));

        // Kiểm tra tính hợp lệ của ID và xử lý
        if (isValidCategoryId(categoryId)) {
            // Thực hiện xóa category
            categoryDAO.delCategory(categoryId);

            // Gửi phản hồi thành công về client
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Xóa thành công");
        } else {
            // Trường hợp ID không hợp lệ, gửi phản hồi lỗi về client
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Mã thể loại không hợp lệ");
        }
    } catch (Exception ex) {
        Logger.getLogger(CategoryApi.class.getName()).log(Level.SEVERE, null, ex);
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
