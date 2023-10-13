package dao;

import java.util.List;
import model.User;

public interface UserDAO {
	
	public void addUser(User u);
	
	public boolean checkUser(String username);
	
	public boolean login(String username, String password);
	
	public void updateUser(User u);
	
	public User getUser(String username);
        public User getUserByUserid(int user_id);
        public List<User> getUsers();
<<<<<<< HEAD
=======
//        các them dc ben duoi
>>>>>>> 28a0da9dee07f49a67e9abde61520c936320e33b
        public List<User> getUsersAndMod();
        public List<User> getlist();
        public void delUser(String ma_user);
        public int getToTalaccount();
        public User getUserid(int id);
        public boolean checkEmail(String email);
        public void updaterole(String role);
<<<<<<< HEAD
        public boolean checkPhone(String phone);
        public List<User> paging(int index);
        public boolean loginAdminAndMod(String username, String password);
        public List<User> getAllUsers();
        public User findUserByEmail(String email);
=======
         public boolean checkPhone(String phone);
       public List<User> paging(int index);
        public boolean loginAdminAndMod(String username, String password);
        public List<User> getAllUsers();
>>>>>>> 28a0da9dee07f49a67e9abde61520c936320e33b
}
