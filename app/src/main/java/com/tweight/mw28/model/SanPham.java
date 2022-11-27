package com.tweight.mw28.model;

public class SanPham {
    private int id, maloai;
    private String tensanpham, ngaynhap;
    private int soluong, giaban;
    private String donvi, anh;

    public SanPham(int id, int maloai, String tensanpham, String ngaynhap, int soluong, int giaban, String donvi, String anh) {
        this.id = id;
        this.maloai = maloai;
        this.tensanpham = tensanpham;
        this.ngaynhap = ngaynhap;
        this.soluong = soluong;
        this.giaban = giaban;
        this.donvi = donvi;
        this.anh = anh;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public String getNgaynhap() {
        return ngaynhap;
    }

    public void setNgaynhap(String ngaynhap) {
        this.ngaynhap = ngaynhap;
    }

    public int getGiaban() {
        return giaban;
    }

    public void setGiaban(int giaban) {
        this.giaban = giaban;
    }

    public String getDonvi() {
        return donvi;
    }

    public void setDonvi(String donvi) {
        this.donvi = donvi;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }
}
