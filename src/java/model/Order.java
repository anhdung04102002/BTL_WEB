package model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class Order {
	private int order_id;
	private int user_id;
	private int ma_san_pham;
	private Timestamp ngay_mua;
	private int so_luong;
	private int thanh_tien;
	private String trang_thai;
	public Order() {
	}

        public Order(int order_id, int user_id, int ma_san_pham, Timestamp ngay_mua, int so_luong, int thanh_tien, String trang_thai) {
            this.order_id = order_id;
            this.user_id = user_id;
            this.ma_san_pham = ma_san_pham;
            this.ngay_mua = ngay_mua;
            this.so_luong = so_luong;
            this.thanh_tien = thanh_tien;   
            this.trang_thai = trang_thai;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }
        
        public String getTrang_thai() {
            return trang_thai;
        }

        public void setTrang_thai(String trang_thai) {
            this.trang_thai = trang_thai;
        }
        
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getMa_san_pham() {
		return ma_san_pham;
	}

	public void setMa_san_pham(int ma_san_pham) {
		this.ma_san_pham = ma_san_pham;
	}

	public Timestamp getNgay_mua() {
		return ngay_mua;
	}

	public void setNgay_mua(Timestamp ngay_mua) {
		this.ngay_mua = ngay_mua;
	}

	public int getSo_luong() {
		return so_luong;
	}

	public void setSo_luong(int so_luong) {
		this.so_luong = so_luong;
	}

        public void setThanh_tien(int thanh_tien) {
            this.thanh_tien = thanh_tien;
        }
	
	public int getThanh_tien() {
		return thanh_tien;
	}
       	
}
