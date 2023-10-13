package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Category;
import model.User;

public class UserDAOImpl implements UserDAO {

    @Override
    public void addUser(User u) {
        Connection con = null;
        try {
            con = new DBConnect().getConnection();
        } catch (Exception ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "insert into user value(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, u.getUser_id());
            ps.setString(2, u.getUsername());
            ps.setString(3, u.getPassword());
            ps.setDate(4, u.getNgaysinh());
            ps.setString(5, u.getGioitinh());
            ps.setString(6, u.getEmail());
            ps.setString(7, u.getSdt());
            ps.setString(8, u.getDiachi());
            ps.setString(9, u.getRole());
            ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkUser(String username) {
        Connection con = null;
        try {
            con = new DBConnect().getConnection();
        } catch (Exception ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select * from user where username='" + username + "'";
        PreparedStatement ps;
        try {
            ps = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                con.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        UserDAOImpl dao = new UserDAOImpl();
        // dao.addUser(new User(0, "admin", "12345", "admin", "1"));
//		System.out.println(dao.checkUser("admin"));
        System.out.println(dao.login("admin", "12345"));
    }

    @Override
    public boolean login(String username, String password) {
        Connection con = null;
        try {
            con = new DBConnect().getConnection();
        } catch (Exception ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select * from user where username='" + username
                + "' and password='" + password + "'";
        PreparedStatement ps;
        try {
            ps = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                con.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void updateUser(User u) {
        Connection con = null;
        try {
            con = new DBConnect().getConnection();
        } catch (Exception ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "update user set username=?, password=?, ngaysinh=?, gioitinh=?, email=?, sdt=?, diachi=?, role=? where user_id=?";
        try {
            PreparedStatement ps = (PreparedStatement) con
                    .prepareStatement(sql);
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setDate(3, u.getNgaysinh());
            ps.setString(4, u.getGioitinh());
            ps.setString(5, u.getEmail());
            ps.setString(6, u.getSdt());
            ps.setString(7, u.getDiachi());
            ps.setString(8, u.getRole());
            ps.setInt(9, u.getUser_id());
            ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public User getUser(String name) {
        Connection con = null;
        try {
            con = new DBConnect().getConnection();
        } catch (Exception ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select * from user where username='" + name + "'";
        User u = new User();
        try {
            PreparedStatement ps = (PreparedStatement) con
                    .prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int user_id = rs.getInt("user_id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                Date ngaysinh = rs.getDate("ngaysinh");
                String gioitinh = rs.getString("gioitinh");
                String email = rs.getString("email");
                String sdt = rs.getString("sdt");
                String diachi = rs.getString("diachi");
                String role = rs.getString("role");
                u = new User(user_id, username, password, ngaysinh, gioitinh, email, sdt, diachi, role);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public User getUserByUserid(int id) {
        Connection con = null;
        try {
            con = new DBConnect().getConnection();
        } catch (Exception ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select * from user where user_id='" + id + "'";
        User u = new User();
        try {
            PreparedStatement ps = (PreparedStatement) con
                    .prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int user_id = rs.getInt("user_id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                Date ngaysinh = rs.getDate("ngaysinh");
                String gioitinh = rs.getString("gioitinh");
                String email = rs.getString("email");
                String sdt = rs.getString("sdt");
                String diachi = rs.getString("diachi");
                String role = rs.getString("role");
                u = new User(user_id, username, password, ngaysinh, gioitinh, email, sdt, diachi, role);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public List<User> getUsers() {
        List<User> list = new ArrayList<User>();
        try {
            Connection conn = new DBConnect().getConnection();
            String sql = "select * from user where role = 2";

            try {
                PreparedStatement ps = (PreparedStatement) conn
                        .prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int user_id = rs.getInt("user_id");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    Date ngaysinh = rs.getDate("ngaysinh");
                    String gioitinh = rs.getString("gioitinh");
                    String email = rs.getString("email");
                    String sdt = rs.getString("sdt");
                    String diachi = rs.getString("diachi");
                    String role = rs.getString("role");
                    list.add(new User(user_id, username, password, ngaysinh, gioitinh, email, sdt, diachi, role));
                }
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (Exception ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<User> getUsersAndMod() {
        List<User> list = new ArrayList<User>();
        try {
            Connection conn = new DBConnect().getConnection();
            String sql = "select * from user where role = 2 or 3";

            try {
                PreparedStatement ps = (PreparedStatement) conn
                        .prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int user_id = rs.getInt("user_id");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    Date ngaysinh = rs.getDate("ngaysinh");
                    String gioitinh = rs.getString("gioitinh");
                    String email = rs.getString("email");
                    String sdt = rs.getString("sdt");
                    String diachi = rs.getString("diachi");
                    String role = rs.getString("role");
                    list.add(new User(user_id, username, password, ngaysinh, gioitinh, email, sdt, diachi, role));
                }
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (Exception ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<User> getlist() {

        List<User> list = new ArrayList<>();
        try {
            Connection conn = new DBConnect().getConnection();
            String sql = "select * from user";
            try {
                PreparedStatement ps = (PreparedStatement) conn
                        .prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int user_id = rs.getInt("user_id");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    Date ngaysinh = rs.getDate("ngaysinh");
                    String gioitinh = rs.getString("gioitinh");
                    String email = rs.getString("email");
                    String sdt = rs.getString("sdt");
                    String diachi = rs.getString("diachi");
                    String role = rs.getString("role");
                    list.add(new User(user_id, username, password, ngaysinh, gioitinh, email, sdt, diachi, role));
                }
                conn.close();
            } catch (SQLException e) {
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void delUser(String user_id) {
        try {
            Connection conn = new DBConnect().getConnection();

            String sql = "delete from user\n" + "where user_id = ? ";
            try {
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
                ps.setString(1, user_id);
                ps.executeUpdate();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int getToTalaccount() {
        try {
            Connection conn = new DBConnect().getConnection();
            String sql = "select count(*) from user";
            try {
                PreparedStatement ps = (PreparedStatement) conn
                        .prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (Exception e) {
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public User getUserid(int id) {
        Connection con = null;
        try {
            con = new DBConnect().getConnection();
        } catch (Exception ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select * from user where user_id='" + id + "'";

        try {
            PreparedStatement ps = (PreparedStatement) con
                    .prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                int user_id = rs.getInt("user_id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                Date ngaysinh = rs.getDate("ngaysinh");
                String gioitinh = rs.getString("gioitinh");
                String email = rs.getString("email");
                String sdt = rs.getString("sdt");
                String diachi = rs.getString("diachi");
                String role = rs.getString("role");
                u = new User(user_id, username, password, ngaysinh, gioitinh, email, sdt, diachi, role);
                return u;
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkEmail(String email) {
        Connection con = null;
        try {
            con = new DBConnect().getConnection();
        } catch (Exception ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select * from user where email='" + email + "'";
        PreparedStatement ps;
        try {
            ps = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                con.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updaterole(String role) {
        try {
            Connection conn = new DBConnect().getConnection();

            String sql = "update from user\n" + "where role = ? ";
            try {
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
                ps.setString(1, role);
                ps.executeUpdate();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean checkPhone(String sdt) {
        Connection con = null;
        try {
            con = new DBConnect().getConnection();
        } catch (Exception ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select * from user where email='" + sdt + "'";
        PreparedStatement ps;
        try {
            ps = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                con.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
   
   public List<User> paging(int index) {
        List<User> list = new ArrayList<User>();
        try {
            Connection conn = new DBConnect().getConnection();
            String sql = "SELECT * FROM user LIMIT 4 OFFSET ?";

            try {
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
                 ps.setInt(1, (index-1)*4);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int user_id = rs.getInt("user_id");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    Date ngaysinh = rs.getDate("ngaysinh");
                    String gioitinh = rs.getString("gioitinh");
                    String email = rs.getString("email");
                    String sdt = rs.getString("sdt");
                    String diachi = rs.getString("diachi");
                    String role = rs.getString("role");
                    list.add(new User(user_id, username, password, ngaysinh, gioitinh, email, sdt, diachi, role));
                }
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (Exception ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
  	@Override
    public boolean loginAdminAndMod(String username, String password) {
        Connection con = null;
        try {
            con = new DBConnect().getConnection();
        } catch (Exception ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
            String sql = "SELECT * FROM `user` WHERE username = '" + username + "' and password = '" + password + "' AND (role = 1 or role = 3)";
            PreparedStatement ps;
            try {
                    ps = (PreparedStatement) con.prepareStatement(sql);
                    
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            con.close();
                            return true;
                    }
            } catch (SQLException e) {
                    e.printStackTrace();
            }
            return false;
    }
    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<User>();
        try {
            Connection conn = new DBConnect().getConnection();
            String sql = "select * from user";
            
            try {
                PreparedStatement ps = (PreparedStatement) conn
                        .prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int user_id= rs.getInt("user_id");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    Date ngaysinh = rs.getDate("ngaysinh");
                    String gioitinh = rs.getString("gioitinh");
                    String email = rs.getString("email");
                    String sdt = rs.getString("sdt");
                    String diachi = rs.getString("diachi");
                    String role = rs.getString("role");
                    list.add(new User(user_id, username, password, ngaysinh, gioitinh, email, sdt, diachi, role));
                }
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
        } catch (Exception ex) {
                Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
<<<<<<< HEAD
   @Override
   public User findUserByEmail(String email) {
        Connection con = null;
        User user = null;
        try {
            con = new DBConnect().getConnection();
        } catch (Exception ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select * from user where email='" + email + "'";
        PreparedStatement ps;
        try {
            ps = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int user_id = rs.getInt("user_id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                Date ngaysinh = rs.getDate("ngaysinh");
                String gioitinh = rs.getString("gioitinh");
                
                String sdt = rs.getString("sdt");
                String diachi = rs.getString("diachi");
                String role = rs.getString("role");
                user = new User(user_id, username, password, ngaysinh, gioitinh, email, sdt, diachi, role);
                con.close();
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
=======
   
>>>>>>> 28a0da9dee07f49a67e9abde61520c936320e33b
}
