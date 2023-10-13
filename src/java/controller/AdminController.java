/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.CategoryDAO;
import dao.CategoryDAOImpl;
import dao.OrderDAOImpl;
import dao.ProductDAO;
import dao.ProductDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import model.Order;
import model.Product;
import model.User;

/**
 *
 * @author Windows 10 TIMT
 */
@WebServlet(name="AdminController", urlPatterns={"/admin"})
public class AdminController extends HttpServlet {
   
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
            out.println("<title>Servlet AdminController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminController at " + request.getContextPath () + "</h1>");
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
        OrderDAOImpl orderDAO = new OrderDAOImpl();
        List<Order> orders = orderDAO.findAllByTrangThai("Đã giao hàng");
        Map<Integer, Integer> tkTheoThang = new HashMap<>();
        long tongDoanhThu = 0;
        for (int i = 1; i < 13; i++) {
            tkTheoThang.put(i, 0);
        }
        for (Order order : orders) {
            tongDoanhThu += order.getThanh_tien();
            LocalDateTime localDateTime = order.getNgay_mua().toLocalDateTime();
            int month = localDateTime.getMonthValue();
            int year = localDateTime.getYear();
<<<<<<< HEAD
//            System.out.println(month);
//            System.out.println(year);
=======
            System.out.println(month);
            System.out.println(year);
>>>>>>> 28a0da9dee07f49a67e9abde61520c936320e33b
            if (year == 2023) {
                tkTheoThang.put(month, tkTheoThang.get(month) + order.getThanh_tien());
            }
        }
<<<<<<< HEAD
//        for (Integer integer : tkTheoThang.keySet()) {
//            System.out.println(integer + " " + tkTheoThang.get(integer));
//        }
=======
        for (Integer integer : tkTheoThang.keySet()) {
            System.out.println(integer + " " + tkTheoThang.get(integer));
        }
>>>>>>> 28a0da9dee07f49a67e9abde61520c936320e33b
       

       
                  

<<<<<<< HEAD
        CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
        SortedSet<Map.Entry<Category, Long>> doanhThuTheoDanhMuc = new TreeSet<>((entry1, entry2) -> {
            if (entry1.getValue().equals(entry2.getValue())) {
                return entry1.getKey().getTen_the_loai().compareTo(entry2.getKey().getTen_the_loai());
            } else {
                return entry2.getValue().compareTo(entry1.getValue());
            }
        });

        List<Category> categories = categoryDAO.getAll();
        for (Category categorie : categories) {
            doanhThuTheoDanhMuc.add(new AbstractMap.SimpleEntry<>(categorie, 0L));
        }

        for (Order orderr : orders) {
            int ma_san_pham = orderr.getMa_san_pham();
            ProductDAO productDAO = new ProductDAOImpl();
            Product product = productDAO.getProduct(ma_san_pham);
        //    System.out.println("Ten san pham" + product);

            try {
                Category category = categoryDAO.getCategory(product.getMa_the_loai());
                if (category != null) {
                    for (Map.Entry<Category, Long> entry : doanhThuTheoDanhMuc) {
                        if (entry.getKey().getMa_the_loai() == category.getMa_the_loai()) {
                            doanhThuTheoDanhMuc.remove(entry);
                            doanhThuTheoDanhMuc.add(new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue() + orderr.getThanh_tien()));
                            break;
                        }
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        for (Map.Entry<Category, Long> entry : doanhThuTheoDanhMuc) {
        //    System.out.println(entry.getKey().getTen_the_loai() + " " + entry.getValue());
        }

        request.setAttribute("doanhThuTheoDanhMuc", doanhThuTheoDanhMuc);
=======
CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
SortedSet<Map.Entry<Category, Long>> doanhThuTheoDanhMuc = new TreeSet<>((entry1, entry2) -> {
    if (entry1.getValue().equals(entry2.getValue())) {
        return entry1.getKey().getTen_the_loai().compareTo(entry2.getKey().getTen_the_loai());
    } else {
        return entry2.getValue().compareTo(entry1.getValue());
    }
});

List<Category> categories = categoryDAO.getAll();
for (Category categorie : categories) {
    doanhThuTheoDanhMuc.add(new AbstractMap.SimpleEntry<>(categorie, 0L));
}

for (Order orderr : orders) {
    int ma_san_pham = orderr.getMa_san_pham();
    ProductDAO productDAO = new ProductDAOImpl();
    Product product = productDAO.getProduct(ma_san_pham);
    System.out.println("Ten san pham" + product);

    try {
        Category category = categoryDAO.getCategory(product.getMa_the_loai());
        if (category != null) {
            for (Map.Entry<Category, Long> entry : doanhThuTheoDanhMuc) {
                if (entry.getKey().getMa_the_loai() == category.getMa_the_loai()) {
                    doanhThuTheoDanhMuc.remove(entry);
                    doanhThuTheoDanhMuc.add(new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue() + orderr.getThanh_tien()));
                    break;
                }
            }
        }
    } catch (Exception ex) {
        Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
    }
}

for (Map.Entry<Category, Long> entry : doanhThuTheoDanhMuc) {
    System.out.println(entry.getKey().getTen_the_loai() + " " + entry.getValue());
}

request.setAttribute("doanhThuTheoDanhMuc", doanhThuTheoDanhMuc);
>>>>>>> 28a0da9dee07f49a67e9abde61520c936320e33b



  

        List<Order> orderss = orderDAO.findAllByTrangThai("Đang chờ duyệt");
    
        int tongSoDonHangDangChoDuyet = orderss.size();
       
        int tongSoDonHangDaGiaoHang = orders.size();
        
         UserDAOImpl userDAO = new UserDAOImpl();
        List<User> users = userDAO.getUsers();
    
        int tongSoNguoiDung = users.size();

        request.setAttribute("tongSoNguoiDung", tongSoNguoiDung);
        request.setAttribute("tongSoDonHangDangChoDuyet", tongSoDonHangDangChoDuyet);
        request.setAttribute("tongSoDonHangDaGiaoHang", tongSoDonHangDaGiaoHang);
        request.setAttribute("tkTheoThang", tkTheoThang);
        request.setAttribute("tongDoanhThu", tongDoanhThu);
        request.getRequestDispatcher("views/admin/index.jsp").forward(request, response);
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
        processRequest(request, response);
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
