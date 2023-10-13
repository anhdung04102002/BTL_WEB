/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.UserDAOImpl;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.User;
import util.PasswordEncoder;

/**
 *
 * @author HP
 */
@WebServlet(urlPatterns = {"/admin-user-add"})
public class addaccController extends HttpServlet {
   
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
            out.println("<title>Servlet testadd</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet testadd at " + request.getContextPath () + "</h1>");
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
       request.getRequestDispatcher("/views/admin/addacc.jsp").forward(request, response);
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
//        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            password = PasswordEncoder.encode(password);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(addaccController.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.sql.Date ngaysinh = null;

        try {
            ngaysinh = new java.sql.Date((new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("ngaysinh"))).getTime());
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        String sdt = request.getParameter("sdt");
        String diachi = request.getParameter("diachi");
        String gioitinh = request.getParameter("gender");
//        String role = request.getParameter("role");
        String err = "";
//        User user=new User(id, name, password, ngaysinhSqlDate, gioitinh, email, sdt, diachi, role);
        UserDAOImpl dao = new UserDAOImpl();
//            int uid;
            try {
//                uid = Integer.parseInt(id);

//                User u = dao.getUserid(uid);

                if (name.equals("") || password.equals("") || email.equals("") || diachi.equals("") || sdt.equals("") || ngaysinh.equals("") || gioitinh.equals("")) {
                    err += "Phải nhập đầy đủ thông tin!";
                    request.setAttribute("error", err);
                    request.getRequestDispatcher("/views/admin/addacc.jsp").forward(request, response);
                } else {
                   
                    if (dao.checkUser(name) == true) {
                        err += "Tài khoản đã tồn tại!";
                        request.setAttribute("error", err);
                        request.getRequestDispatcher("/views/admin/addacc.jsp").forward(request, response);
                    } else if (dao.checkEmail(email) == true) {
                        err += "email đã tồn tại!";
                        request.setAttribute("error", err);
                        request.getRequestDispatcher("/views/admin/addacc.jsp").forward(request, response);
                    
                    } 
                    else if(dao.checkPhone(sdt)==true){err += "sdt đã tồn tại!";
                        request.setAttribute("error", err);
                        request.getRequestDispatcher("/views/admin/addacc.jsp").forward(request, response);
                    }
                    else {
                        Pattern pattenObj = Pattern
                                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
                        Matcher matcherObj = pattenObj.matcher(email);
                        if (!matcherObj.matches()) {
                            err += "Email sai định dạng!";
                            request.setAttribute("error", err);
                            request.getRequestDispatcher("/views/admin/addacc.jsp").forward(request, response);
                        }
                    }
                }
                if (!name.equals("") && !password.equals("") && !email.equals("") && !diachi.equals("") && !sdt.equals("") && !ngaysinh.equals("") && !gioitinh.equals("")) {

                    User user = new User( 0,name, password, ngaysinh, gioitinh, email, sdt, diachi, "2");
                    dao.addUser(user);
                    response.sendRedirect("admin-user");

                } else {
//                    request.setAttribute("error", uid + "id da ton tai");
                    request.getRequestDispatcher("/views/admin/addacc.jsp").forward(request, response);
                }

            } catch (Exception e) {
                System.out.println(e);
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
