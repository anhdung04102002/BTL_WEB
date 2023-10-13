package model;

public class Category {

    private int ma_the_loai;
    private String ten_the_loai;
    private String mo_ta;
    private String hinh_anh;

    public Category() {
    }

    public Category(int ma_the_loai, String ten_the_loai, String mo_ta, String hinh_anh) {
        this.ma_the_loai = ma_the_loai;
        this.ten_the_loai = ten_the_loai;
        this.mo_ta = mo_ta;
        this.hinh_anh = hinh_anh;
    }
//thêm một phương thức 3 đối để tạo mới danh mục mà không cần ma_the_loai(vì tự động tăng)
    public Category(String ten_the_loai, String mo_ta, String hinh_anh) {
        this.ten_the_loai = ten_the_loai;
        this.mo_ta = mo_ta;
        this.hinh_anh = hinh_anh;
    }

    public int getMa_the_loai() {
        return ma_the_loai;
    }

    public String getTen_the_loai() {
        return ten_the_loai;
    }

    public String getMo_ta() {
        return mo_ta;
    }

    public String getHinh_anh() {
        return hinh_anh;
    }

    public void setMa_the_loai(int ma_the_loai) {
        this.ma_the_loai = ma_the_loai;
    }

    public void setTen_the_loai(String ten_the_loai) {
        this.ten_the_loai = ten_the_loai;
    }

    public void setMo_ta(String mo_ta) {
        this.mo_ta = mo_ta;
    }

    public void setHinh_anh(String hinh_anh) {
        this.hinh_anh = hinh_anh;
    }

}
