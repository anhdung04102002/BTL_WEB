package dao;

import java.util.List;

import model.Product;

public interface ProductDAO {

	// thêm sản phẩm mới
	public void addProduct(Product p);
        public void deleteProduct(int ma_san_pham);
	// hiển thị danh sách sản phẩm
	public List<Product> getList();

	// lấy danh sách sản phẩm dựa vào thể loại
	public List<Product> getListByCategory(int ma_the_loai);
	
	public Product getProduct(int ma_san_pham);
	
	public List<Product> searchList(String ten_san_pham, int ma_the_loai);
        public void updateProduct(Product p);
        
}
