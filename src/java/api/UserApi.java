/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.UserDAO;
import dao.UserDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.User;

/**
 *
 * @author Windows 10 TIMT
 */
@WebServlet(urlPatterns = {"/api/user"})
public class UserApi extends HttpServlet{
    private UserDAOImpl userDAO = new UserDAOImpl();
     private boolean isValidUser (User user ) {
        if (user  == null) {
            return false;
        }
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            return false;
        }

        // Kiểm tra các ràng buộc khác (tuỳ thuộc vào yêu cầu của ứng dụng)
        return true;
     }
      private boolean isValidUserId(String userId) {
        // Kiểm tra các điều kiện hợp lệ của ID
        int a =Integer.parseInt(userId);
        if (a <= 0) {
            return false;
        }

        // Kiểm tra các ràng buộc khác (tuỳ thuộc vào yêu cầu của ứng dụng)
        return true;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        List<User> users = userDAO.getUsersAndMod();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(resp.getWriter(), users);
    }
      protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          try {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        User updateuser  = objectMapper.readValue(req.getReader(), User.class);
        
        if(isValidUser(updateuser)){
            userDAO.updateUser(updateuser);
            String jsonResponse = objectMapper.writeValueAsString(updateuser);
            resp.getWriter().write(jsonResponse);
        }
        else {
                // Trường hợp dữ liệu không hợp lệ, gửi phản hồi lỗi về client
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("cập nhật không thành công");
            }}
          catch (Exception e) {
         } 
    }
       @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("application/json");

            // Đọc dữ liệu JSON được gửi từ client
            ObjectMapper objectMapper = new ObjectMapper();
            User newuser  = objectMapper.readValue(req.getReader(), User.class);

            // Thực hiện thêm mới user
             userDAO.addUser(newuser);
            List<User> users = userDAO.getlist();
//            objectMapper.writeValue(response.getWriter(), categorys);
            // Gửi phản hồi về client
            String jsonResponse = objectMapper.writeValueAsString(users);
            resp.getWriter().write(jsonResponse);
        } catch (Exception ex) {
        }
    }

     protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    try {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        // Lấy ID của category từ yêu cầu DELETE
        String userId =req.getParameter("user_id");

        // Kiểm tra tính hợp lệ của ID và xử lý
        if (isValidUserId(userId)) {
            // Thực hiện xóa category
            userDAO.delUser(userId);

            // Gửi phản hồi thành công về client
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("Xóa thành công");
        } else {
            // Trường hợp ID không hợp lệ, gửi phản hồi lỗi về client
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Mã thể loại không hợp lệ");
        }
    } catch (Exception ex) {
    }
}
}
