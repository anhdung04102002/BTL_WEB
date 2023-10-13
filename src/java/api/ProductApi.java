/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.ProductDAO;
import dao.ProductDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Product;

/**
 *
 * @author Windows 10 TIMT
 */
@WebServlet(name="ProductApi", urlPatterns={"/api/product/*"})
public class ProductApi extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
//        System.out.println("put api");
        ObjectMapper objectMapper = new ObjectMapper();
        Product product = objectMapper.readValue(req.getReader(), Product.class);
        ProductDAO productDAO = new ProductDAOImpl();
        productDAO.updateProduct(product);
        objectMapper.writeValue(resp.getWriter(), product);
    } 

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductApi</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductApi at " + request.getContextPath () + "</h1>");
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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        
        String path = request.getPathInfo();
//        System.out.println(path);
        if (path == null || path.equals("/")) {
            ProductDAO productDAO = new ProductDAOImpl();
            List<Product> products = productDAO.getList();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(response.getWriter(), products);
        }
        else{
            String[] pathVariable = path.split("/");
//            System.out.println("." + path);
//            System.out.println("size: " + pathVariable.length);
//            for (String string : pathVariable) {
//                System.out.println(string + ", ");
//            }
            if (pathVariable.length == 2) {
                try {
                    int ma_san_pham = Integer.parseInt(pathVariable[1]);
                    
                    ProductDAO productDAO = new ProductDAOImpl();
                    Product product = productDAO.getProduct(ma_san_pham);
                    if (product != null) {
                        ObjectMapper objectMapper = new ObjectMapper();
//                        System.out.println(product.getMa_san_pham());
                        objectMapper.writeValue(response.getWriter(), product);
                    }
                    else{
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        response.getWriter().write("Khong co san pham voi ma do");
                    }
                } catch (NumberFormatException e) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write("Ma san pham khong hop le");
                    System.out.println(e);
                }
            }
            else{
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Sai url");
            }
        }
    } 

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        
        String path = req.getPathInfo();
//        System.out.println(path);
        if (path == null || path.equals("/")) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Path khong co ma san pham");
        }
        else{
            
            String[] pathVariable = path.split("/");
//            System.out.println("." + path);
//            System.out.println("size: " + pathVariable.length);
//            for (String string : pathVariable) {
//                System.out.println(string + ", ");
//            }
            if (pathVariable.length == 2) {
                try {
                    int ma_san_pham = Integer.parseInt(pathVariable[1]);
                    
                    ProductDAO productDAO = new ProductDAOImpl();
                    Product product = productDAO.getProduct(ma_san_pham);
                    if (product != null) {
                        ObjectMapper objectMapper = new ObjectMapper();
//                        System.out.println(product.getMa_san_pham());
                        productDAO.deleteProduct(ma_san_pham);
                        objectMapper.writeValue(resp.getWriter(), product);
                    }
                    else{
                        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        resp.getWriter().write("Khong co san pham do");
                    }
                    
                } catch (NumberFormatException e) {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    resp.getWriter().write("Sai ma san pham can xoa");
                    System.out.println(e);
                }
            }
            else{
                System.out.println("sai ma san pham");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("Sai ma san pham can xoa");
            }

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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        Product product = objectMapper.readValue(request.getReader(), Product.class);
        ProductDAO productDAO = new ProductDAOImpl();
        productDAO.addProduct(product);
        
        
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
