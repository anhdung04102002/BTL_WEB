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
import java.util.ArrayList;
import java.util.List;
import model.Cart;
import model.User;
import util.PasswordEncoder;

/**
 *
 * @author Windows 10 TIMT
 */
@WebServlet(name="LoginController", urlPatterns={"/login"})
public class LoginController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private UserDAOImpl userDAO = new UserDAOImpl();
    private List<Cart> cart = new ArrayList<Cart>();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginController at " + request.getContextPath () + "</h1>");
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
            if (username != null) {
                request.getSession().setAttribute("user", userDAO.getUser(username));
            }
    
            response.sendRedirect("/shop/home");
        }
        request.getRequestDispatcher("views/client/login.jsp").forward(request, response);
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
            String password = PasswordEncoder.encode(request.getParameter("password"));
            String remember = request.getParameter("checkbox");
//            System.out.println(request.getParameter("checkbox"));
//            System.out.println(remember);
            String err = "";
            if (username.equals("") || password.equals("")) {
                    err += "Phải nhập đầy đủ thông tin!";
            } else {
                    if (userDAO.login(username, password) == false) {
                            err += "Tên đăng nhập hoặc mật khẩu không chính xác!";
                    }
//                    System.out.println(userDAO.login(username, password));
            }

            if (err.length() > 0) {
                    request.setAttribute("err", err);
            }
            
            if (err.length() == 0) {
                    User user = userDAO.getUser(username);
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    session.setAttribute("cart", cart);

                    if (remember.equals("on")) {
                        Cookie loginCookie = new Cookie("username",username);
                        //setting cookie to expiry in 30 mins
                        loginCookie.setMaxAge(30*60);
                        response.addCookie(loginCookie);

                    }
                    response.sendRedirect("/shop/home");
//                    request.getRequestDispatcher("views/client/index.jsp").forward(request, response);

            } else {
                    request.getRequestDispatcher("views/client/login.jsp").forward(request, response);
            }

        } catch (Exception e) {
                e.printStackTrace();
                request.getRequestDispatcher("views/client/login.jsp").forward(request, response);
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
