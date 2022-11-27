package com.tweight.mw28.model;

public class NhanVien {
    private Integer id;
    private String username;
    private String password;
    private String hoten;
    private String gioitinh;
    private String diachi;
    private Integer dienthoai;
    private String ngaylam;
    private String level;

    public NhanVien(){

    }

    public NhanVien(int id, String username, String password, String hoten, String gioitinh, String diachi, int dienthoai, String ngaylam, String level) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.diachi = diachi;
        this.dienthoai = dienthoai;
        this.ngaylam = ngaylam;
        this.level = level;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public Integer getDienthoai() {
        return dienthoai;
    }

    public void setDienthoai(int dienthoai) {
        this.dienthoai = dienthoai;
    }

    public String getNgaylam() {
        return ngaylam;
    }

    public void setNgaylam(String ngaylam) {
        this.ngaylam = ngaylam;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
