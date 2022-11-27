package com.tweight.mw28.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tweight.mw28.Database.Database;
import com.tweight.mw28.model.SanPham;

import java.util.ArrayList;

public class SanPhamDAO {
    Database database;
    SQLiteDatabase read, write;

    public SanPhamDAO(Context context) {
        database = new Database(context);
        read = database.getReadableDatabase();
        write = database.getWritableDatabase();
    }

    public ArrayList<SanPham> getSanPham() {
        ArrayList<SanPham> list = new ArrayList<>();

        Cursor cursor = read.rawQuery("SELECT * FROM SANPHAM", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                list.add(
                        new SanPham(
                                cursor.getInt(0),
                                cursor.getInt(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getInt(4),
                                cursor.getInt(5),
                                cursor.getString(6),
                                cursor.getString(7)
                        ));
            } while (cursor.moveToNext());
        }

        return list;
    }

    public int removeSanPham(int id) {
        return write.delete("SANPHAM", "id = ?", new String[]{String.valueOf(id)});
    }

    public long addSanPham(int maloai, String tensanpham, int soluong, String ngaynhap, int giaban, String donvi) {
        ContentValues values = new ContentValues();
        values.put("maloai", maloai);
        values.put("tensanpham", tensanpham);
        values.put("ngaynhap", ngaynhap);
        values.put("soluong", soluong);
        values.put("giaban", giaban);
        values.put("donvi", donvi);
        values.put("anh", "");
        return write.insert("SANPHAM", null, values);
    }

    public int updateSanPham(int maloai, String tensanpham, int soluong, String ngaynhap, int giaban, String donvi, int id) {
        ContentValues values = new ContentValues();
        values.put("maloai", maloai);
        values.put("tensanpham", tensanpham);
        values.put("ngaynhap", ngaynhap);
        values.put("soluong", soluong);
        values.put("giaban", giaban);
        values.put("donvi", donvi);
        values.put("anh", "");
        return write.update("SANPHAM", values, "id = ?", new String[]{String.valueOf(id)});
    }
}
