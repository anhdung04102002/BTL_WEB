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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Cart;
import model.User;
import util.PasswordEncoder;

/**
 *
 * @author Windows 10 TIMT
 */
@WebServlet(name="RegisterController", urlPatterns={"/register"})
public class RegisterController extends HttpServlet {
   
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
            out.println("<title>Servlet RegisterController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterController at " + request.getContextPath () + "</h1>");
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
        String username = null;
        UserDAO userDAO = new UserDAOImpl();
        Cookie[] cookies = request.getCookies();
        if(cookies !=null)
        {
                for(Cookie cookie : cookies)
                {
                    if(cookie.getName().equals("username")) 
                        username = cookie.getValue();
                }
        }
        if (username != null || request.getSession().getAttribute("user") != null) {
            System.out.println(username + " or " + request.getSession().getAttribute("user"));
            if (username != null) {
                request.getSession().setAttribute("user", userDAO.getUser(username));
            }
            response.sendRedirect("/shop/home");
        }
        request.getRequestDispatcher("views/client/register.jsp").forward(request, response);
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
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String confirmedpassword = request.getParameter("confirmedpassword");
            java.sql.Date ngaysinh= null;

            try {
                    ngaysinh = new java.sql.Date((new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("ngaysinh"))).getTime());
            } catch (ParseException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
            }
            String gioitinh = request.getParameter("gioitinh");
            String email = request.getParameter("email");
            String sdt = request.getParameter("sdt");
            String diachi = request.getParameter("diachi");
//            System.out.println(username);

            String err = "";

            UserDAO userDAO = new UserDAOImpl();
            List<Cart> cart = new ArrayList<>();
            if (username.equals("") || password.equals("") || confirmedpassword.equals("") || email.equals("") || diachi.equals("") || sdt.equals("")) {
                    err += "Phải nhập đầy đủ thông tin!";
            } else {

                if (userDAO.checkUser(username) == true) {
                        err += "Tài khoản đã tồn tại!";
                }
                else if (!confirmedpassword.equals(password)) {
                    err += "Mật khẩu xác nhận không đúng";
                }
                else {
                        Pattern pattenObj = Pattern
                                        .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
                        Matcher matcherObj = pattenObj.matcher(email);
                        if (!matcherObj.matches()) {
                                err += "Email sai định dạng!";
                        }
                }
            }

            if (err.length() > 0) {
                    request.setAttribute("err", err);
            }
            if (err.length() == 0) {
                HttpSession session = request.getSession();
                session.setAttribute("cart", cart);
                password = PasswordEncoder.encode(password);
                userDAO.addUser(new User(0, username, password, ngaysinh, gioitinh, email, sdt, diachi, "2"));
                userDAO.login(username, password);
                Cookie loginCookie = new Cookie("username",username);
                //setting cookie to expiry in 30 mins
                loginCookie.setMaxAge(30*60);
                response.addCookie(loginCookie);
                User user = userDAO.getUser(username);
                session.setAttribute("user", user);
                response.sendRedirect("/shop/home");

            } else {
                    request.getRequestDispatcher("views/client/register.jsp").forward(request, response);
            }

        } catch (Exception e) {
                e.printStackTrace();
                request.getRequestDispatcher("views/client/register.jsp").forward(request, response);
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
