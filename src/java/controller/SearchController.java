/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.CategoryDAO;
import dao.CategoryDAOImpl;
import dao.ProductDAO;
import dao.ProductDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import model.Product;

/**
 *
 * @author Windows 10 TIMT
 */
@WebServlet(name="SearchController", urlPatterns={"/search"})
public class SearchController extends HttpServlet {
   
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
            out.println("<title>Servlet SearchController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchController at " + request.getContextPath () + "</h1>");
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
        
        CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
        List<Category> categories = categoryDAO.getAll();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("views/client/search_page.jsp").forward(request, response);
        
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
        String ten_san_pham= request.getParameter("ten_san_pham");
        String ma_the_loai_string= request.getParameter("ma_the_loai");
        String err="";
        CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
        List<Category> categories = categoryDAO.getAll();
        request.setAttribute("categories", categories);
        System.out.println(ten_san_pham + " " + ma_the_loai_string);
        if(ten_san_pham.equals("") && ma_the_loai_string.equals("")){
                err+="Phải nhập ít nhất 1 thông tin tìm kiếm";
        }
        
        if (err.length() > 0) {
            request.setAttribute("err", err);            
            request.getRequestDispatcher("views/client/search_page.jsp").forward(request, response);
        }
        int ma_the_loai = 0;
        if (!ma_the_loai_string.equals("")) {
            ma_the_loai = Integer.parseInt(ma_the_loai_string);
        }
        ProductDAO productDAO = new ProductDAOImpl();
        List<Product> products = productDAO.searchList(ten_san_pham, ma_the_loai);
        request.setAttribute("products", products);
        request.getRequestDispatcher("views/client/search_page.jsp").forward(request, response);
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
