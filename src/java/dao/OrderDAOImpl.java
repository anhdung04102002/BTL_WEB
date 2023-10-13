package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Category;
import model.Order;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public void addOrder(Order h) {
        Connection con = null;
        try {
            con = new DBConnect().getConnection();
        } catch (Exception ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "insert into `order` value(?,?,?,?,?,?, ?)";
        PreparedStatement ps;

        try {
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, h.getOrder_id());
            ps.setInt(2, h.getUser_id());
            ps.setInt(3, h.getMa_san_pham());
            ps.setTimestamp(4, h.getNgay_mua());
            ps.setInt(5, h.getSo_luong());
            ps.setInt(6, h.getThanh_tien());
            ps.setString(7, h.getTrang_thai());
            ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Order> getList(int id) {
        Connection con = null;
        try {
            con = new DBConnect().getConnection();
        } catch (Exception ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select * from `order` where user_id=" + id + "";
        List<Order> list = new ArrayList<Order>();
        try {
            PreparedStatement ps = (PreparedStatement) con
                    .prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int order_id = rs.getInt("order_id");
                int user_id = rs.getInt("user_id");
                int ma_san_pham = rs.getInt("ma_san_pham");
                Timestamp ngay_mua = rs.getTimestamp("ngay_mua");
                int so_luong = rs.getInt("so_luong");
                int thanh_tien = rs.getInt("thanh_tien");
                String trang_thai = rs.getString("trang_thai");
                list.add(new Order(order_id, user_id, ma_san_pham, ngay_mua, so_luong, thanh_tien, trang_thai));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Order> getListByStatement(String state) {
        try {
            Connection con = new DBConnect().getConnection();
            String sql = "select * from order where trang_thai='" + state + "'";
            List<Order> list = new ArrayList<Order>();
            try {
                PreparedStatement ps = (PreparedStatement) con
                        .prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int order_id = rs.getInt("order_id");
                    int user_id = rs.getInt("user_id");
                    int ma_san_pham = rs.getInt("ma_san_pham");
                    Timestamp ngay_mua = rs.getTimestamp("ngay_mua");
                    int so_luong = rs.getInt("so_luong");
                    int thanh_tien = rs.getInt("thanh_tien");
                    String trang_thai = rs.getString("trang_thai");
                    list.add(new Order(order_id, user_id, ma_san_pham, ngay_mua, so_luong, thanh_tien, trang_thai));
                }
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return list;
        } catch (Exception ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Order> getListByDate(Timestamp date_order) {
        try {
            Connection con = new DBConnect().getConnection();
            String sql = "select * from `order` where ngay_mua ='" + date_order + "' ";
            List<Order> list = new ArrayList<Order>();
            try {
                PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int order_id = rs.getInt("order_id");
                    int user_id = rs.getInt("user_id");
                    int ma_san_pham = rs.getInt("ma_san_pham");
                    Timestamp ngay_mua = rs.getTimestamp("ngay_mua");
                    int so_luong = rs.getInt("so_luong");
                    int thanh_tien = rs.getInt("thanh_tien");
                    String trang_thai = rs.getString("trang_thai");
                    list.add(new Order(order_id, user_id, ma_san_pham, ngay_mua, so_luong, thanh_tien, trang_thai));
                }
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return list;
        } catch (Exception ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Order> getList() {
        Connection con = null;
        try {
            con = new DBConnect().getConnection();
        } catch (Exception ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select * from `order`";
        List<Order> list = new ArrayList<Order>();
        try {
            PreparedStatement ps = (PreparedStatement) con
                    .prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int order_id = rs.getInt("order_id");
                int user_id = rs.getInt("user_id");
                int ma_san_pham = rs.getInt("ma_san_pham");
                Timestamp ngay_mua = rs.getTimestamp("ngay_mua");
                int so_luong = rs.getInt("so_luong");
                int thanh_tien = rs.getInt("thanh_tien");
                String trang_thai = rs.getString("trang_thai");
                list.add(new Order(order_id, user_id, ma_san_pham, ngay_mua, so_luong, thanh_tien, trang_thai));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void updateOrder(Order o) {
        Connection con = null;
        try {
            con = new DBConnect().getConnection();
        } catch (Exception ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);

        }
        String sql = "UPDATE" +" `order`" +
                        "SET" +
                        "    " +
                        "    `user_id` = ?," +
                        "    `ma_san_pham` = ?," +
                        "    `ngay_mua` = ?," +
                        "    `so_luong` = ?," +
                        "    `thanh_tien` = ?," +
                        "    `trang_thai` = ?" +
                        "WHERE `order_id` = ?";

        PreparedStatement ps;
        try {
            ps = (PreparedStatement) con.prepareStatement(sql);
            
            ps.setInt(1, o.getUser_id());
            ps.setInt(2, o.getMa_san_pham());
            ps.setTimestamp(3, o.getNgay_mua());
            ps.setInt(4, o.getSo_luong());
            ps.setInt(5, o.getThanh_tien());
            ps.setString(6, o.getTrang_thai());
            ps.setInt(7, o.getOrder_id());
            ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(int orderId) {
        Connection con = null;
        try {
            con = new DBConnect().getConnection();

        } catch (Exception ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "delete from `order` WHERE `order_id` = ?";
        PreparedStatement ps;
        try {
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, orderId);
            ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public List<Order> findAllByTrangThai(String status) {
        Connection con = null;
            try {
                con = new DBConnect().getConnection();
            } catch (Exception ex) {
                Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
		String sql = "select * from `order` where trang_thai='"+ status +"'";
		List<Order> list = new ArrayList<Order>();
		try {
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int order_id = rs.getInt("order_id");
				int user_id = rs.getInt("user_id");
				int ma_san_pham = rs.getInt("ma_san_pham");
				Timestamp ngay_mua = rs.getTimestamp("ngay_mua");
				int so_luong = rs.getInt("so_luong");
				int thanh_tien = rs.getInt("thanh_tien");
                                String trang_thai = rs.getString("trang_thai");
				list.add(new Order(order_id, user_id, ma_san_pham, ngay_mua, so_luong, thanh_tien, trang_thai));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
<<<<<<< HEAD
    }
    @Override
    public List<Category> getTenTheLoaiCategories(String tentheloai) {
        Connection con = null;
            try {
                con = new DBConnect().getConnection();
            } catch (Exception ex) {
                Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
		String sql = "select * from `category` where ten_the_loai='"+ tentheloai +"'";
		List<Category> list = new ArrayList<Category>();
		try {
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
				int ma_the_loai = rs.getInt("ma_the_loai");
				String ten_the_loai = rs.getString("ten_the_loai");
				String mo_ta = rs.getString("mo_ta");
				String hinh_anh = rs.getString("hinh_anh");
				list.add(new Category(ma_the_loai,ten_the_loai, mo_ta, hinh_anh ));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
    }

    @Override
=======
<<<<<<< HEAD
    }
=======
	}

    @Override
    public List<Order> findAllByTrangThai(String status) {
        Connection con = null;
            try {
                con = new DBConnect().getConnection();
            } catch (Exception ex) {
                Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
		String sql = "select * from `order` where trang_thai='"+ status +"'";
		List<Order> list = new ArrayList<Order>();
		try {
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int order_id = rs.getInt("order_id");
				int user_id = rs.getInt("user_id");
				int ma_san_pham = rs.getInt("ma_san_pham");
				Timestamp ngay_mua = rs.getTimestamp("ngay_mua");
				int so_luong = rs.getInt("so_luong");
				int thanh_tien = rs.getInt("thanh_tien");
                                String trang_thai = rs.getString("trang_thai");
				list.add(new Order(order_id, user_id, ma_san_pham, ngay_mua, so_luong, thanh_tien, trang_thai));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
    }
    
    
>>>>>>> afba4a65b1b2fc7c969577b7bd7ecb26bad8e076
    @Override
    public List<Category> getTenTheLoaiCategories(String tentheloai) {
        Connection con = null;
            try {
                con = new DBConnect().getConnection();
            } catch (Exception ex) {
                Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
		String sql = "select * from `category` where ten_the_loai='"+ tentheloai +"'";
		List<Category> list = new ArrayList<Category>();
		try {
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
				int ma_the_loai = rs.getInt("ma_the_loai");
				String ten_the_loai = rs.getString("ten_the_loai");
				String mo_ta = rs.getString("mo_ta");
				String hinh_anh = rs.getString("hinh_anh");
				list.add(new Category(ma_the_loai,ten_the_loai, mo_ta, hinh_anh ));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
    }
<<<<<<< HEAD
=======
	
>>>>>>> afba4a65b1b2fc7c969577b7bd7ecb26bad8e076

    @Override
>>>>>>> 28a0da9dee07f49a67e9abde61520c936320e33b
    public Order getOrderByOrder_id(int order_id) {
        Order order = null;
        try {
            Connection con = new DBConnect().getConnection();
            String sql = "SELECT * FROM `order` WHERE order_id = " + order_id;
            
            try {
                PreparedStatement ps = (PreparedStatement) con
                        .prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
//                        System.out.println(rs);
                while (rs.next()) {
                    int user_id = rs.getInt("user_id");
                    int ma_san_pham = rs.getInt("ma_san_pham");
                    Timestamp ngay_mua = rs.getTimestamp("ngay_mua");
                    int so_luong = rs.getInt("so_luong");
                    int thanh_tien = rs.getInt("thanh_tien");
                    String trang_thai = rs.getString("trang_thai");
                    order = new Order(order_id, user_id, ma_san_pham, ngay_mua, so_luong, thanh_tien, trang_thai);
                }
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
        } catch (Exception ex) {
                Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return order;
    }
}
