/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.ProductDAO;
import dao.ProductDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Cart;

/**
 *
 * @author Windows 10 TIMT
 */
@WebServlet(name="CartController", urlPatterns={"/cart"})
public class CartController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private List<Cart> carts = new ArrayList<>();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CartController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartController at " + request.getContextPath () + "</h1>");
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
        HttpSession session = request.getSession();
        String command = request.getParameter("command");
        String index = request.getParameter("index");
        System.out.println("get cart: " + command + index);
//		for (Cart cart : carts) {
//			System.out.println(cart.getP().getTenSanPham() + " " + cart.getQuantity());
//		}
        if (session.getAttribute("carts") != null) {
            carts = (List<Cart>) session.getAttribute("carts");
        }
        if (command.equals("deleteCart")) {
            int id = Integer.parseInt(index);
            Cart cart = carts.get(id);
            if (cart.getQuantity() > 1) {
                cart.setQuantity(cart.getQuantity() - 1);
                carts.set(id, cart);
            }
            else{
                command = "removeCart";
            }
        }
        if (command.equals("addCart")) {
            System.out.println(index + ",");
            int id = Integer.parseInt(index);
            Cart cart = carts.get(id);
            cart.setQuantity(cart.getQuantity() + 1);
            carts.set(id, cart);
        }
        if (command.equals("removeCart")) {
            int id = Integer.parseInt(index);
            carts.remove(id);
        }
        
        session.setAttribute("carts", carts);
        request.getRequestDispatcher("views/client/cart.jsp").forward(request, response);
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
        int soluong = Integer.parseInt(request.getParameter("soluong"));
        int ma_san_pham = Integer.parseInt(request.getParameter("ma_san_pham"));
        ProductDAO productDAO = new ProductDAOImpl();
        carts.add(new Cart(productDAO.getProduct(ma_san_pham), soluong));
        HttpSession session = request.getSession();
        session.setAttribute("carts", carts);
        request.getRequestDispatcher("views/client/cart.jsp").forward(request, response);
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
