package dao;

import java.sql.Timestamp;
import java.util.List;
import model.Category;

import model.Order;

public interface OrderDAO {
	
	//thêm mới một lịch sử mua hàng.
	public void addOrder(Order h);
	public List<Order> getList();
	//lọc lịch sử của khách hàng.
	public List<Order> getList(int user_id);
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 28a0da9dee07f49a67e9abde61520c936320e33b
        public void updateOrder(Order o);
        public void deleteOrder(int orderId);
        public List<Order> getListByDate(Timestamp date_order);
        public List<Order> getListByStatement(String state);
        public List<Order> findAllByTrangThai(String trang_thai);
        public List<Category> getTenTheLoaiCategories(String tentheloai);
        public Order getOrderByOrder_id(int order_id);
<<<<<<< HEAD
=======
=======
                   
                   public List<Order> findAllByTrangThai(String trang_thai);
                    
                   public List<Category> getTenTheLoaiCategories(String ten_the_loai); 
        
 
>>>>>>> afba4a65b1b2fc7c969577b7bd7ecb26bad8e076
>>>>>>> 28a0da9dee07f49a67e9abde61520c936320e33b
}
