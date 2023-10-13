package dao;

import java.util.List;

import model.Category;

public interface CategoryDAO {

    // thêm danh mục mới
    public void addCategory(Category c);

    public void delCategory(int ma_the_loai);

    // danh sach
    public List<Category> getList();

    public Category getCategory(int ma_the_loai);

    public void updateCategory(Category c);

    public int soLuongCategory();

    public List<Category> listCategoryInPage(int index);

    public boolean checkCategory(String ten_the_loai);
    public List<Category> search(String ten_danh_muc,String mo_ta);
}
