package com.tweight.mw28.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tweight.mw28.Database.Database;
import com.tweight.mw28.model.ChuCuaHang;

import java.util.ArrayList;
import java.util.List;

public class
ChuCuaHangDAO {
    Database sqLite;
    SQLiteDatabase sqLiteDatabase;
    Context context;

    public ChuCuaHangDAO(Context context
    ) {
        this.sqLite = sqLite;
        sqLite = new Database(context);
        sqLiteDatabase = sqLite.getWritableDatabase();
    }
    //
    public int insert(ChuCuaHang chucuahang){
        ContentValues values = new ContentValues();
        values.put("ma_chu_cua_hang",chucuahang.getMa_chu_cua_hang());
        values.put("ho_ten",chucuahang.getHo_ten());
        values.put("so_dien_thoai",chucuahang.getSo_dien_thoai());
        values.put("tai_khoan",chucuahang.getTai_khoan());
        values.put("mat_khau",chucuahang.getMat_khau());
        values.put("level",chucuahang.getLevel());
        long kq = sqLiteDatabase.insert("CHU_CUA_HANG",null,values);
        if (kq <= 0){
            return -1;
        }
        return 1;
    }
    //
    public int update(ChuCuaHang chucuahang){
        ContentValues values = new ContentValues();
        values.put("ma_chu_cua_hang",chucuahang.getMa_chu_cua_hang());
        values.put("ho_ten",chucuahang.getHo_ten());
        values.put("so_dien_thoai",chucuahang.getSo_dien_thoai());
        values.put("tai_khoan",chucuahang.getTai_khoan());
        values.put("mat_khau",chucuahang.getMat_khau());
        values.put("level",chucuahang.getLevel());
        long kq = sqLiteDatabase.update("CHU_CUA_HANG",values,"ma_chu_cua_hang=?",new String[]{String.valueOf(chucuahang.getMa_chu_cua_hang())});
        if (kq <= 0){
            return -1;
        }
        return 1;
    }
    //
    public int delete(String id) {
        return sqLiteDatabase.delete("ChuCuaHang", "ma_chu_cua_hang=?", new String[]{id});
    }

    @SuppressLint("Range")
    public List<ChuCuaHang> getData(String sql, String ...selectionArgs){
        List<ChuCuaHang> chulist = new ArrayList<>();
        Cursor c = sqLiteDatabase.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            ChuCuaHang chucuahang = new ChuCuaHang();
            chucuahang.setMa_chu_cua_hang(Integer.parseInt(c.getString(c.getColumnIndex("id"))));
            chucuahang.setHo_ten(c.getString(c.getColumnIndex("hoten")));
            chucuahang.setSo_dien_thoai(c.getString(c.getColumnIndex("dienthoai")));
            chucuahang.setTai_khoan(c.getString(c.getColumnIndex("username")));
            chucuahang.setMat_khau(c.getString(c.getColumnIndex("password")));
            chucuahang.setLevel(c.getString(c.getColumnIndex("level")));
            chulist.add(chucuahang);
        }
        return chulist;
    }

    public ChuCuaHang getID (String id){
        String sql = "SELECT * FROM OWNER WHERE id=?";
        List<ChuCuaHang> chulist = getData(sql,id);
        return chulist.get(0);
    }
    public List<ChuCuaHang> getAll(){
        String sql = "SELECT * FROM OWNER";
        return getData(sql);
    }

    public int checklogin(String tendangnhap, String matkhau){
        List<ChuCuaHang> ls = getAll();
        for (ChuCuaHang c:ls){
            if (c.getTai_khoan().equals(tendangnhap) && c.getMat_khau().equals(matkhau)){
                return 1;
            }
        }
        return -1;
    }

    public int checktkdau(){
        List<ChuCuaHang> ls = getAll();
        if (ls.size() <= 0){
            return 1;
        }
        return -1;
    }



}