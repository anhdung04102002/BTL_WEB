package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */


import dao.OrderDAO;
import dao.OrderDAOImpl;
import dao.ProductDAO;
import dao.ProductDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.DetailOrder;
import model.Order;
import model.User;

/**
 *
 * @author Windows 10 TIMT
 */
@WebServlet(urlPatterns={"/history"})
public class HistoryController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HistoryController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HistoryController at " + request.getContextPath () + "</h1>");
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
        OrderDAO orderDAO = new OrderDAOImpl();
        ProductDAO productDAO = new ProductDAOImpl();
        System.out.println(request.getSession().getAttribute("user"));
        if (request.getSession().getAttribute("user") != null) {
            
            User user = (User) request.getSession().getAttribute("user");
            List<Order> orders = orderDAO.getList(user.getUser_id());
            List<DetailOrder> histories = new ArrayList<>();
            for (Order order : orders) {
                histories.add(new DetailOrder(order.getOrder_id(), order.getUser_id(), productDAO.getProduct(order.getMa_san_pham()), order.getNgay_mua(), order.getSo_luong(), order.getThanh_tien(), order.getTrang_thai()));
            }
            request.setAttribute("histories", histories);
            request.getRequestDispatcher("views/client/history.jsp").forward(request, response);
        }
        else{
            response.sendRedirect("/shop/login");
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
        processRequest(request, response);
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
