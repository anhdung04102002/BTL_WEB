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
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.User;

/**
 *
 * @author Windows 10 TIMT
 */
@WebServlet(name="AdminUpdateProfile", urlPatterns={"/admin/update_profile"})
public class AdminUpdateProfile extends HttpServlet {
   
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
            out.println("<title>Servlet AdminUpdateProfile</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminUpdateProfile at " + request.getContextPath () + "</h1>");
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
        request.getRequestDispatcher("/views/admin/update_profile.jsp").forward(request, response);
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
        int user_id = Integer.parseInt(request.getParameter("user_id"));
//        System.out.println(user_id);
        String username = request.getParameter("username");
//        System.out.println(username);
        String ngaysinh = request.getParameter("ngaysinh");
//        System.out.println(ngaysinh);
        String gioitinh = request.getParameter("gioitinh");
//        System.out.println(gioitinh);
        String email = request.getParameter("email");
//        System.out.println(email);
        String sdt = request.getParameter("sdt");
//        System.out.println(sdt);
        String diachi = request.getParameter("diachi");
//        System.out.println(diachi);
        Date birthdayDate = null;
        
        String err = "";
        try {
            
                birthdayDate  = new Date((new SimpleDateFormat("yyyy-MM-dd").parse(ngaysinh)).getTime());
        } catch (ParseException e1) {
                // TODO Auto-generated catch block
                err += "Ngày sinh không hợp lệ\n";
                request.setAttribute("err", err);
                
        }




        Pattern pattenObj = Pattern
                        .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcherObj = pattenObj.matcher(email);
        if (!matcherObj.matches()) {
                err += "Email sai định dạng!\n";
        }



        if (err.length() > 0) {
//			request.setAttribute("err", err);
                request.setAttribute("err", err);
                System.out.println(err);
                request.getRequestDispatcher("views/admin/update_profile.jsp").forward(request, response);
        }

        try {
                if (err.length() == 0) {
                        HttpSession session = request.getSession();
//				session.setAttribute("cart", cart);
                        UserDAO userDAO = new UserDAOImpl();
                        User admin = userDAO.getUserByUserid(user_id);
                        admin.setGioitinh(gioitinh);
                        admin.setEmail(email);
                        admin.setSdt(sdt);
                        admin.setNgaysinh(birthdayDate);
                        admin.setDiachi(diachi);
                        userDAO.updateUser(admin);
                        session.setAttribute("admin", admin);


                } else {
                        request.getRequestDispatcher("views/admin/update_profile.jsp").forward(request, response);
                }

        } catch (Exception e) {
                request.getRequestDispatcher("views/admin/update_profile.jsp").forward(request, response);
        }
        response.sendRedirect("/shop/admin");
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
