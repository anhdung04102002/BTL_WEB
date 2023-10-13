/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.UserDAOImpl;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import util.PasswordEncoder;

/**
 *
 * @author HP
 */
@WebServlet(urlPatterns = {"/admin-user-update"})

public class updateaccController extends HttpServlet {
   
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
            out.println("<title>Servlet updateaccController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet updateaccController at " + request.getContextPath () + "</h1>");
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
       response.setContentType("text/html;charset=UTF-8");
       String uid=request.getParameter("uid");
       int id;
        UserDAOImpl dao=new UserDAOImpl();
        try {
            id=Integer.parseInt(uid);
            User user=dao.getUserByUserid(id);
            request.setAttribute("user", user);
            request.getRequestDispatcher("/views/admin/updateacc.jsp").forward(request, response);
        } catch (Exception e) {
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
        response.setContentType("text/html;charset=UTF-8");
         String id=request.getParameter("id");
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        java.sql.Date ngaysinh= null;

            try {
                    ngaysinh = new java.sql.Date((new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("ngaysinh"))).getTime());
            } catch (ParseException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
            }
       String sdt=request.getParameter("sdt");
       String diachi=request.getParameter("diachi");
       String gioitinh=request.getParameter("gender");
       String role=request.getParameter("role");
       int uid=Integer.parseInt(id);
       UserDAOImpl dao =new UserDAOImpl();
        try {
            password = PasswordEncoder.encode(password);
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(updateaccController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(password.equals("")){
            password=dao.getUserByUserid(uid).getPassword();
        }
//        User user=new User(id, name, password, ngaysinhSqlDate, gioitinh, email, sdt, diachi, role);
        
       String err = "";
        try {
        uid = Integer.parseInt(id);
        // Kiểm tra tên, email và số điện thoại trước khi cập nhật
        if (email.equals("") || diachi.equals("") || sdt.equals("") || ngaysinh.equals("") || gioitinh.equals("")) {
                    err += "Phải nhập đầy đủ thông tin!";
                    User user =new User();
                    user=dao.getUserByUserid(uid);
                    request.setAttribute("error", err);
                    request.setAttribute("user",user);
                    request.getRequestDispatcher("/views/admin/updateacc.jsp").forward(request, response);
                }
        else{User user = new User(uid, name, password, ngaysinh, gioitinh, email, sdt, diachi, role);
        dao.updateUser(user);
        response.sendRedirect("admin-user");}
        
    } catch (Exception e) {
        System.out.println(e);
         response.sendRedirect(request.getRequestURI());
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
