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
import model.Order;
import model.User;

import dao.OrderDAO;
import dao.OrderDAOImpl;
import jakarta.servlet.annotation.WebServlet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * 
 */
@WebServlet(urlPatterns = {"/api/order"})
public class OrderApi extends HttpServlet {

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
            out.println("<title>Servlet OrderApi</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderApi at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private boolean isValidOrder(Order order) {
        if (order == null) {
            return false;
        }

        // Kiểm tra các điều kiện hợp lệ khác của đối tượng Order
//        if (order.getUser_id()== null || order.getUser_id().isEmpty()) {
//            return false;
//        }

        // Kiểm tra các ràng buộc khác (tuỳ thuộc vào yêu cầu của ứng dụng)
        return true;
    }

    private boolean isValidOrderId(int orderId) {
        // Kiểm tra các điều kiện hợp lệ của ID
        if (orderId <= 0) {
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
            OrderDAOImpl orderDAO = new OrderDAOImpl();
            request.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            List<Order> orders = orderDAO.getList();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(response.getWriter(), orders);
        } catch (Exception ex) {
            Logger.getLogger(OrderApi.class.getName()).log(Level.SEVERE, null, ex);
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
            OrderDAOImpl orderDAO = new OrderDAOImpl();
            request.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");

            // Đọc dữ liệu JSON được gửi từ client
            ObjectMapper objectMapper = new ObjectMapper();
            Order newOrder = objectMapper.readValue(request.getReader(), Order.class);

            // Thực hiện thêm mới order
            orderDAO.addOrder(newOrder);
            List<Order> orders = orderDAO.getList();
//            objectMapper.writeValue(response.getWriter(), orders);
            // Gửi phản hồi về client
            String jsonResponse = objectMapper.writeValueAsString(orders);
            response.getWriter().write(jsonResponse);
        } catch (Exception ex) {
            Logger.getLogger(OrderApi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            OrderDAOImpl orderDAO = new OrderDAOImpl();
            request.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");

            // Đọc dữ liệu JSON được gửi từ client
            ObjectMapper objectMapper = new ObjectMapper();
            Order updatedOrder = objectMapper.readValue(request.getReader(), Order.class);

            // Kiểm tra tính hợp lệ của dữ liệu và xử lý
            if (isValidOrder(updatedOrder)) {
                // Thực hiện cập nhật order
                orderDAO.updateOrder(updatedOrder);

                // Gửi phản hồi về client
                String jsonResponse = objectMapper.writeValueAsString(updatedOrder);
                response.getWriter().write(jsonResponse);
            } else {
                // Trường hợp dữ liệu không hợp lệ, gửi phản hồi lỗi về client
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("cập nhật không thành công");
            }
        } catch (Exception ex) {
            Logger.getLogger(OrderApi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   protected void doDelete(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    try {
        OrderDAOImpl orderDAO = new OrderDAOImpl();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        // Lấy ID của order từ yêu cầu DELETE
        int orderId = Integer.parseInt(request.getParameter("order_id"));

        // Kiểm tra tính hợp lệ của ID và xử lý
        if (isValidOrderId(orderId)) {
            // Thực hiện xóa order
            orderDAO.deleteOrder(orderId);

            // Gửi phản hồi thành công về client
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Xóa thành công");
        } else {
            // Trường hợp ID không hợp lệ, gửi phản hồi lỗi về client
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Mã hóa đơn không hợp lệ");
        }
    } catch (Exception ex) {
        Logger.getLogger(OrderApi.class.getName()).log(Level.SEVERE, null, ex);
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