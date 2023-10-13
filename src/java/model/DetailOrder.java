/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author Windows 10 TIMT
 */
public class DetailOrder {
    private int order_id;
    private int user_id;
    private Product product;
    private Timestamp ngay_mua;
    private int so_luong;
    private int thanh_tien;
    private String trang_thai;

    public DetailOrder() {
    }

    public DetailOrder(int order_id, int user_id, Product product, Timestamp ngay_mua, int so_luong, int thanh_tien, String trang_thai) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.product = product;
        this.ngay_mua = ngay_mua;
        this.so_luong = so_luong;
        this.thanh_tien = thanh_tien;
        this.trang_thai = trang_thai;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setNgay_mua(Timestamp ngay_mua) {
        this.ngay_mua = ngay_mua;
    }

    public void setSo_luong(int so_luong) {
        this.so_luong = so_luong;
    }

    public void setThanh_tien(int thanh_tien) {
        this.thanh_tien = thanh_tien;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    public int getOrder_id() {
        return order_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public Product getProduct() {
        return product;
    }

    public Timestamp getNgay_mua() {
        return ngay_mua;
    }

    public int getSo_luong() {
        return so_luong;
    }

    public int getThanh_tien() {
        return thanh_tien;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

}
