package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Category;

public class CategoryDAOImpl {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Category> getAll() {
        String query = "SELECT * FROM `category`";
        List<Category> list = new ArrayList<>();
        try {
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void addCategory(Category c) throws Exception {
        conn = new DBConnect().getConnection();
        String sql = "INSERT INTO `category`(`ten_the_loai`, `mo_ta`, `hinh_anh`) VALUES (?,?,?)";
        PreparedStatement ps;
        try {
            ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setString(1, c.getTen_the_loai());
            ps.setString(2, c.getMo_ta());
            ps.setString(3, c.getHinh_anh());
            ps.executeUpdate();
            rs = ps.executeQuery();//rs LÀ LỆNH XUẤT BẢNG NÊN CHỈ XUẤT HIỆN SAU KHI ĐÃ UPDATE HOẶC các lệnh truy ván
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
<<<<<<< HEAD
    }
=======
<<<<<<< HEAD
    }
=======
                System.out.println("");
	}
	public void delCategory(int ma_the_loai) {
             try {
                 conn = new DBConnect().getConnection();
             } catch (Exception ex) {
                 Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
             }
		String sql = "delete from `category` where ma_the_loai='" + ma_the_loai
				+ "'";
		try {
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
>>>>>>> afba4a65b1b2fc7c969577b7bd7ecb26bad8e076
>>>>>>> 28a0da9dee07f49a67e9abde61520c936320e33b

    public List<Category> getList() throws Exception {
        conn = new DBConnect().getConnection();
        String sql = "select * from category";
        List<Category> list = new ArrayList<Category>();
        try {
            PreparedStatement ps = (PreparedStatement) conn
                    .prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ma_the_loai = rs.getInt("ma_the_loai");
                String ten_the_loai = rs.getString("ten_the_loai");
                String mo_ta = rs.getString("mo_ta");
                String hinh_anh = rs.getString("hinh_anh");
                list.add(new Category(ma_the_loai, ten_the_loai, mo_ta, hinh_anh));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) throws Exception, Exception, Exception {
        CategoryDAOImpl dao = new CategoryDAOImpl();
        List<Category> list = dao.getAll();
        list = dao.search("new", "new");
        for (Category x : list) {
            System.out.println(x);
        }
        
        
        System.out.println("");
    }

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 28a0da9dee07f49a67e9abde61520c936320e33b
    public void delCategory(int ma_the_loai) {
        try {
            try {
                conn = new DBConnect().getConnection();
            } catch (Exception ex) {
                Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            String sql = "DELETE FROM category WHERE ma_the_loai=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ma_the_loai);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
<<<<<<< HEAD
=======
=======
	public Category getCategory(int id) throws Exception {
		conn = new DBConnect().getConnection();
                                    String sql = "select * from `category` where `ma_the_loai`=" + id;
		Category c = new Category();
		try {
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int ma_the_loai = rs.getInt("ma_the_loai");
				String ten_the_loai = rs.getString("ten_the_loai");
				String mo_ta = rs.getString("mo_ta");
				String hinh_anh = rs.getString("hinh_anh");
				c = new Category(ma_the_loai, ten_the_loai, mo_ta, hinh_anh);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
>>>>>>> afba4a65b1b2fc7c969577b7bd7ecb26bad8e076
>>>>>>> 28a0da9dee07f49a67e9abde61520c936320e33b

    public Category getCategory(int id) throws Exception {
        conn = new DBConnect().getConnection();
        String sql = "SELECT * FROM `category` where ma_the_loai = ?";
        try {
            PreparedStatement ps = (PreparedStatement) conn
                    .prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category c = new Category(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateCategory(Category c) throws Exception {
        conn = new DBConnect().getConnection();
        String sql = "update category set ten_the_loai=?, mo_ta=?, hinh_anh = ? where ma_the_loai=?";
        try {
            PreparedStatement ps = (PreparedStatement) conn
                    .prepareStatement(sql);
            ps.setString(1, c.getTen_the_loai());
            ps.setString(2, c.getMo_ta());
            ps.setString(3, c.getHinh_anh());
            ps.setInt(4, c.getMa_the_loai());
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int soLuongCategory() throws Exception {
        conn = new DBConnect().getConnection();
        String query = "SELECT COUNT(*) FROM category;";
        try {
            PreparedStatement ps = (PreparedStatement) conn
                    .prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                // vì bảng result chỉ có 1 kết quả 
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public List<Category> listCategoryInPage(int index) throws Exception {
        conn = new DBConnect().getConnection();
        List<Category> list = new ArrayList<>();
        try {

            String query = "SELECT * FROM category LIMIT 4 OFFSET ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, (index - 1) * 4);
            rs = ps.executeQuery();
            while (rs.next()) {
                int ma_the_loai = rs.getInt("ma_the_loai");
                String ten_the_loai = rs.getString("ten_the_loai");
                String mo_ta = rs.getString("mo_ta");
                String hinh_anh = rs.getString("hinh_anh");
                list.add(new Category(ma_the_loai, ten_the_loai, mo_ta, hinh_anh));
            }
        } catch (Exception ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean checkCtegory(String ten_the_loai) throws SQLException, Exception {
        conn = new DBConnect().getConnection();
        String sql = "SELECT * FROM `category` WHERE ten_the_loai = ?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, ten_the_loai);
        rs = ps.executeQuery();
        if (rs.next()) {
            return true;
        }
        return false;
    }

    public List<Category> search(String name, String mota) throws Exception {
    conn = new DBConnect().getConnection();
    List<Category> list = new ArrayList<>();
    String sql = "SELECT * FROM category WHERE ten_the_loai LIKE ? OR mo_ta LIKE ?";
    ps = conn.prepareStatement(sql);
    ps.setString(1, "%" + name + "%");
    ps.setString(2, "%" + mota + "%");
    rs = ps.executeQuery(); 
    while (rs.next()) {
        int ma_the_loai = rs.getInt("ma_the_loai");
        String ten_the_loai = rs.getString("ten_the_loai");
        String mo_ta = rs.getString("mo_ta");
        String hinh_anh = rs.getString("hinh_anh");
        list.add(new Category(ma_the_loai, ten_the_loai, mo_ta, hinh_anh));
    }
    return list;
}
}
