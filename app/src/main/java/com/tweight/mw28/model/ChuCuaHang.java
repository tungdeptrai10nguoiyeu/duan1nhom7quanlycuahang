package com.tweight.mw28.model;


public class ChuCuaHang {
    private int ma_chu_cua_hang;
    private String ho_ten;
    private String so_dien_thoai;
    private String tai_khoan;
    private String mat_khau;
    private String level;

    public ChuCuaHang(){

    }

    public ChuCuaHang(int ma_chu_cua_hang, String ho_ten, String so_dien_thoai, String tai_khoan, String mat_khau, String level) {
        this.ma_chu_cua_hang = ma_chu_cua_hang;
        this.ho_ten = ho_ten;
        this.so_dien_thoai = so_dien_thoai;
        this.tai_khoan = tai_khoan;
        this.mat_khau = mat_khau;
        this.level = level;
    }

    public int getMa_chu_cua_hang() {
        return ma_chu_cua_hang;
    }

    public void setMa_chu_cua_hang(int ma_chu_cua_hang) {
        this.ma_chu_cua_hang = ma_chu_cua_hang;
    }

    public String getHo_ten() {
        return ho_ten;
    }

    public void setHo_ten(String ho_ten) {
        this.ho_ten = ho_ten;
    }

    public String getSo_dien_thoai() {
        return so_dien_thoai;
    }

    public void setSo_dien_thoai(String so_dien_thoai) {
        this.so_dien_thoai = so_dien_thoai;
    }

    public String getTai_khoan() {
        return tai_khoan;
    }

    public void setTai_khoan(String tai_khoan) {
        this.tai_khoan = tai_khoan;
    }

    public String getMat_khau() {
        return mat_khau;
    }

    public void setMat_khau(String mat_khau) {
        this.mat_khau = mat_khau;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}