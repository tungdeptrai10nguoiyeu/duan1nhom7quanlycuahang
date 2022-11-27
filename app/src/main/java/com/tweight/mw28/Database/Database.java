package com.tweight.mw28.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context) {
        super(context, "database.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String dbOwner = "CREATE TABLE OWNER(id INTEGER PRIMARY KEY AUTOINCREMENT, hoten TEXT, dienthoai INTEGER, username text, password text, level TEXT)";
        db.execSQL(dbOwner);
        String dbStaff = "CREATE TABLE NHANVIEN(id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT, hoten TEXT, gioitinh TEXT, diachi TEXT, dienthoai INTEGER, ngaylam TEXT, level text)";
        db.execSQL(dbStaff);
        String dbType = "CREATE TABLE LOAIHANG(id INTEGER PRIMARY KEY AUTOINCREMENT, tenloai TEXT)";
        db.execSQL(dbType);
        String dbProduct = "CREATE TABLE SANPHAM(id INTEGER PRIMARY KEY AUTOINCREMENT, maloai INTEGER REFERENCES LOAIHANG(id), tensanpham TEXT, ngaynhap TEXT, soluong INTEGER, giaban INTEGER, donvi TEXT, anh TEXT)";
        db.execSQL(dbProduct);
        String dbReceipt = "CREATE TABLE HOADON(id INTEGER PRIMARY KEY AUTOINCREMENT, manhanvien INTEGER REFERENCES NHANVIEN(id), masanpham INTEGER REFERENCES SANPHAM(id), soluong INTEGER, ngayxuat TEXT)";
        db.execSQL(dbReceipt);

        // Insert data

        db.execSQL("INSERT INTO HOADON VALUES(0, 0, 0, 2, '11/07/2022')");
        db.execSQL("INSERT INTO SANPHAM VALUES(0, 0, 'Choco Pie matcha', '21/12/2006', 30, 25000, 'Hộp', '')");
        db.execSQL("INSERT INTO LOAIHANG VALUES(0, 'Bánh kẹo'), (1, 'Dầu gội')");
        db.execSQL("INSERT INTO OWNER VALUES (0, 'Nhóm 7', '0123456789', 'admin123', 'admin123', 'admin')");
        db.execSQL("INSERT INTO NHANVIEN VALUES(0, 'staff1', 'staff1', 'Lê Long', 'female', 'Hà Nội', '0987888665', '12/12/2012', 'staff')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
