/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.UserDAO;
import dao.UserDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import util.PasswordEncoder;

/**
 *
 * @author Windows 10 TIMT
 */
@WebServlet(name="UpdatePasswordController", urlPatterns={"/update_password"})
public class UpdatePasswordController extends HttpServlet {
   
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
            out.println("<title>Servlet UpdatePasswordController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdatePasswordController at " + request.getContextPath () + "</h1>");
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
        request.getRequestDispatcher("views/client/update_password.jsp").forward(request, response);
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
        

        try {
            String password = request.getParameter("password");
            String oldpassword = request.getParameter("oldpassword");
            String confirmedpassword = request.getParameter("confirmedpassword");
//            System.out.println(oldpassword + " " + password + " " + confirmedpassword);
//            System.out.println(request.getSession().getAttribute("user"));
//            System.out.println(request.getParameter(("user_id")) + ",");
            int user_id = Integer.parseInt(request.getParameter("user_id"));
            
            String err = "";
            if (oldpassword.equals("") || password.equals("") || confirmedpassword.equals("")) {
                    err += "Điền đầy đủ thông tin";
                    request.setAttribute("err", err);
                    request.getRequestDispatcher("views/client/update_password").forward(request, response);
            }
            password = PasswordEncoder.encode(password);
            oldpassword = PasswordEncoder.encode(oldpassword);
            confirmedpassword = PasswordEncoder.encode(confirmedpassword);
            UserDAO userDAO = new UserDAOImpl();
            User user = userDAO.getUserByUserid(user_id);
            if (!user.getPassword().equals(oldpassword)) {
                    err += "Mật khẩu không đúng";
            }

            if (!password.equals(confirmedpassword)) {
                    err += "Mật khẩu xác nhận không trùng khớp";
            }
            if (password.equals(oldpassword)) {
                    err += "Không đặt mật khẩu giống mật khẩu cũ";
            }
            if (err.length() > 0) {
//                    System.out.println(user.getPassword());
//                    System.out.println(oldpassword);
//                    System.out.println(password);
//                    System.out.println(confirmedpassword);
                    request.setAttribute("err", err);

                    System.out.println(err);
                    request.getRequestDispatcher("views/client/update_password.jsp").forward(request, response);
            }
            if (err.length() == 0) {
                    HttpSession session = request.getSession();
//				session.setAttribute("cart", cart);

                    user.setPassword(password);
                    userDAO.updateUser(user);
                    response.sendRedirect("/shop/home");


            } else {
                    request.getRequestDispatcher("views/client/update_password.jsp").forward(request, response);
            }

        } catch (Exception e) {

                request.getRequestDispatcher("views/client/update_password.jsp").forward(request, response);
        }
        
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
