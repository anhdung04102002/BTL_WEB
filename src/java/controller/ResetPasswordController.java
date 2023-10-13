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
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import util.Email;
import util.PasswordEncoder;
import util.RandomString;

/**
 *
 * @author Windows 10 TIMT
 */
@WebServlet(name="ResetPasswordController", urlPatterns={"/reset-password"})
public class ResetPasswordController extends HttpServlet {
   
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
            out.println("<title>Servlet ResetPasswordController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResetPasswordController at " + request.getContextPath () + "</h1>");
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
        request.getRequestDispatcher("views/client/resetpassword.jsp").forward(request, response);
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
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        UserDAO userDAO = new UserDAOImpl();
        String err = "";
        if (username.equals("") && email.equals("")) {
                err += "Phải nhập đầy đủ thông tin!";
        } else {
            if (userDAO.checkUser(username) == false) {
                    err += "Tên đăng nhập không tồn tại!";
            }
            else {
                User user = userDAO.findUserByEmail(email);
                if (user == null) {
                    err += "Email không trùng với bất kì tài khoản nào";
                }
                else if (!user.getUsername().equals(username)) {
                    System.out.println(user.getUsername());
                    err += "Email không đúng";
                }
            }
        }

        if (err.length() > 0) {
            request.setAttribute("err", err);
            request.getRequestDispatcher("views/client/resetpassword.jsp").forward(request, response);

        }
        if (err.length() == 0) {
            
            try {
                User user = userDAO.getUser(username);
                String newpassword = RandomString.rand(6);
                user.setPassword(PasswordEncoder.encode(newpassword));
                userDAO.updateUser(user);
                
                String subject = "Khôi phục mật khẩu";
                String content = "";
                content += "<i>Khôi phục mật khẩu</i><br/>";
                content += "<p>Tài khoản của người dùng <strong>" + user.getUsername() + "</strong></p>";
                content += "<p>đã được khôi phục. Mật khẩu mới là: <strong>" + newpassword + "</strong></p>";
                
                Email.send(user.getEmail(), subject, content);
                response.sendRedirect("/shop/login");
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ResetPasswordController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
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
