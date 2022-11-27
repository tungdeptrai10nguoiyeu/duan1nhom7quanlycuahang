package com.tweight.mw28.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tweight.mw28.Database.Database;
import com.tweight.mw28.model.LoaiHang;

import java.util.ArrayList;

public class LoaiHangDAO {
    Database database;
    SQLiteDatabase read, write;

    public LoaiHangDAO(Context context) {
        database = new Database(context);
        read = database.getReadableDatabase();
        write = database.getWritableDatabase();
    }

    public ArrayList<LoaiHang> getLoaiHang() {
        ArrayList<LoaiHang> list = new ArrayList<>();

        Cursor cursor = read.rawQuery("SELECT * FROM LOAIHANG", null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                list.add(new LoaiHang(cursor.getInt(0), cursor.getString(1)));
            } while (cursor.moveToNext());
        }


        return list;
    }

    public int removeLoaiHang(int id) {
        return write.delete("LOAIHANG", "id = ?", new String[]{String.valueOf(id)});
    }
}
