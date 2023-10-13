package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Product;

public class ProductDAOImpl implements ProductDAO {

	@Override
	public void addProduct(Product p) {
            Connection con = null;
            try {
                con = new DBConnect().getConnection();
            } catch (Exception ex) {
                Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            String sql = "insert into product value(?,?,?,?,?,?,?, ?, ?, ?)";
            PreparedStatement ps;
            try {
                    ps = (PreparedStatement) con.prepareStatement(sql);
                    ps.setInt(1, p.getMa_san_pham());
                    ps.setInt(2, p.getMa_the_loai());
                    ps.setString(3, p.getTen_san_pham());
                    ps.setString(4, p.getHinh_anh());
                    ps.setInt(5, p.getGia_ban());
                    ps.setString(6, p.getHang_san_xuat());
                    ps.setString(7, p.getThong_tin());
                    ps.setInt(8, p.getSo_luong_kho());
                    ps.setInt(9, p.getSo_luong_ban());
                    ps.setInt(10, p.getHien_thi());
                    ps.executeUpdate();
                    System.out.println("lưu thành công");
                    con.close();
            } catch (SQLException e) {
                System.out.println("lỗi" + e);
                    e.printStackTrace();
            }
	}

	@Override
	public List<Product> getList() {
		Connection con = null;
            try {
                con = new DBConnect().getConnection();
            } catch (Exception ex) {
                Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
		String sql = "select * from product";
		List<Product> list = new ArrayList<Product>();
		try {
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int ma_san_pham = rs.getInt("ma_san_pham");
				int ma_the_loai = rs.getInt("ma_the_loai");
				String ten_san_pham = rs.getString("ten_san_pham");
				String hinh_anh = rs.getString("hinh_anh");
				Integer gia_ban = rs.getInt("gia_ban");
				String hang_san_xuat = rs.getString("hang_san_xuat");
				String thong_tin = rs.getString("thong_tin");
                                int so_luong_kho = rs.getInt("so_luong_kho");
                                int so_luong_ban = rs.getInt("so_luong_ban");
                                int hien_thi = rs.getInt("hien_thi");
				list.add(new Product(ma_san_pham, ma_the_loai, ten_san_pham,
						hinh_anh, gia_ban, hang_san_xuat, thong_tin, so_luong_kho, so_luong_ban, hien_thi));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Product> getListByCategory(int id) {
		try {
                    Connection con = new DBConnect().getConnection();
                    String sql = "select * from product where ma_the_loai='" + id + "'";
                    List<Product> list = new ArrayList<Product>();
                    try {
                        PreparedStatement ps = (PreparedStatement) con
                                .prepareStatement(sql);
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            int ma_san_pham = rs.getInt("ma_san_pham");
                            int ma_the_loai = rs.getInt("ma_the_loai");
                            String ten_san_pham = rs.getString("ten_san_pham");
                            String hinh_anh = rs.getString("hinh_anh");
                            Integer gia_ban = rs.getInt("gia_ban");
                            String hang_san_xuat = rs.getString("hang_san_xuat");
                            String thong_tin = rs.getString("thong_tin");
                            int so_luong_kho = rs.getInt("so_luong_kho");
                            int so_luong_ban = rs.getInt("so_luong_ban");
                            int hien_thi = rs.getInt("hien_thi");
                            list.add(new Product(ma_san_pham, ma_the_loai, ten_san_pham,
                                    hinh_anh, gia_ban, hang_san_xuat, thong_tin, so_luong_kho, so_luong_ban, hien_thi));
                        }
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return list;
                } catch (Exception ex) {
			Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
            return null;
	}

	@Override
	public Product getProduct(int id) {
		try {
                    Connection con = new DBConnect().getConnection();
                    String sql = "select * from product where ma_san_pham='" + id + "'";
                    Product p = null;
                    try {
                        PreparedStatement ps = (PreparedStatement) con
                                .prepareStatement(sql);
                        ResultSet rs = ps.executeQuery();
//                        System.out.println(rs);
                        while (rs.next()) {
                            int ma_san_pham = rs.getInt("ma_san_pham");
                            int ma_the_loai = rs.getInt("ma_the_loai");
                            String ten_san_pham = rs.getString("ten_san_pham");
                            String hinh_anh = rs.getString("hinh_anh");
                            int gia_ban = rs.getInt("gia_ban");
                            String hang_san_xuat = rs.getString("hang_san_xuat");
                            String thong_tin = rs.getString("thong_tin");
                            int so_luong_kho = rs.getInt("so_luong_kho");
                            int so_luong_ban = rs.getInt("so_luong_ban");
                            int hien_thi = rs.getInt("hien_thi");
                            System.out.println(ma_san_pham);
                            p = new Product(ma_san_pham, ma_the_loai, ten_san_pham,
                                    hinh_anh, gia_ban, hang_san_xuat, thong_tin, so_luong_kho, so_luong_ban, hien_thi);
                        }
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return p;
                } catch (Exception ex) {
			Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
            return null;
	}

	public static void main(String[] args) {
		Product p = new Product(0, 1, "S6", "da", 500000 ,"","", 0, 0, 1);
		ProductDAOImpl productDAO = new ProductDAOImpl();
		// productDAO.addProduct(p);
		// System.out.println(productDAO.getList());
		System.out.println(productDAO.getListByCategory(1));
	}

	@Override
	public List<Product> searchList(String ten_san_pham, int ma_the_loai) {
		try {
                    Connection con = new DBConnect().getConnection();
                    String sql=null;
                    if(!ten_san_pham.equals("") && ma_the_loai != 0){
                        sql = "SELECT * FROM product WHERE ten_san_pham like N'%"+ ten_san_pham +"%' AND ma_the_loai="+ma_the_loai;
                    }else{
                        if(ten_san_pham.equals("")){
                            sql = "SELECT * FROM product WHERE ma_the_loai=" +ma_the_loai;
                        }else{
                            if(ma_the_loai==0){
                                sql = "SELECT * FROM product WHERE ten_san_pham like N'%"+ ten_san_pham +"%'";
                            }
                        }
                    }
                    List<Product> list = new ArrayList<Product>();
                    try {
                        PreparedStatement ps = (PreparedStatement) con
                                .prepareStatement(sql);
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            int ma_san_pham = rs.getInt("ma_san_pham");
                            int ma_the_loai1 = rs.getInt("ma_the_loai");
                            ten_san_pham = rs.getString("ten_san_pham");
                            String hinh_anh = rs.getString("hinh_anh");
                            int gia_ban = rs.getInt("gia_ban");
                            String hang_san_xuat = rs.getString("hang_san_xuat");
                            String thong_tin = rs.getString("thong_tin");
                            int so_luong_kho = rs.getInt("so_luong_kho");
                            int so_luong_ban = rs.getInt("so_luong_ban");
                            int hien_thi = rs.getInt("hien_thi");
                            list.add(new Product(ma_san_pham, ma_the_loai1, ten_san_pham,
                                    hinh_anh, gia_ban, hang_san_xuat, thong_tin, so_luong_kho, so_luong_ban, hien_thi));
                        }
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return list;
                    
                    
                } catch (Exception ex) {
			Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
            return null;
		
	
	}

    @Override
    public void updateProduct(Product p) {
        Connection con = null;
        try {
            con = new DBConnect().getConnection();
        } catch (Exception ex) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "UPDATE\n" +
                    "    `product`\n" +
                    "SET\n" +
                    "    `ma_the_loai` = ?,\n" +
                    "    `ten_san_pham` = ?,\n" +
                    "    `hinh_anh` = ?,\n" +
                    "    `gia_ban` = ?,\n" +
                    "    `hang_san_xuat` = ?,\n" +
                    "    `thong_tin` = ?,\n" +
                    "    `so_luong_kho` = ?,\n" +
                    "    `so_luong_ban` = ?,\n" +
                    "    `hien_thi` = ?\n" +
                    "WHERE ma_san_pham = ?";
        PreparedStatement ps;
        try {
                ps = (PreparedStatement) con.prepareStatement(sql);
                ps.setInt(1, p.getMa_the_loai());
                ps.setString(2, p.getTen_san_pham());
                ps.setString(3, p.getHinh_anh());
                ps.setInt(4, p.getGia_ban());
                ps.setString(5, p.getHang_san_xuat());
                ps.setString(6, p.getThong_tin());
                ps.setInt(7, p.getSo_luong_kho());
                ps.setInt(8, p.getSo_luong_ban());
                ps.setInt(9, p.getHien_thi());
                ps.setInt(10, p.getMa_san_pham());
                ps.executeUpdate();
                con.close();
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(int ma_san_pham) {
        Connection con = null;
        try {
            con = new DBConnect().getConnection();
        } catch (Exception ex) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "DELETE FROM `product` WHERE ma_san_pham = ?";
        PreparedStatement ps;
        try {
                ps = (PreparedStatement) con.prepareStatement(sql);
                ps.setInt(1, ma_san_pham);
                ps.executeUpdate();
                con.close();
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
}
