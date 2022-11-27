package com.tweight.mw28.DAO;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.tweight.mw28.Database.Database;
import com.tweight.mw28.model.NhanVien;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {
    private SQLiteDatabase sqLiteDatabase;
    Database sqLite;
    Context context;

    public NhanVienDAO(Context context) {
        this.context = context;
        sqLite = new Database(context);
        sqLiteDatabase = sqLite.getWritableDatabase();
    }

    public int insert(NhanVien nv){
        ContentValues values = new ContentValues();
        values.put("id",nv.getId());
        values.put("hoten",nv.getHoten());
        values.put("username",nv.getUsername());
        values.put("password",nv.getPassword());
        values.put("gioitinh",nv.getGioitinh());
        values.put("diachi",nv.getDiachi());
        values.put("dienthoai",nv.getDienthoai());
        values.put("ngaylam",nv.getNgaylam());
        values.put("level",nv.getLevel());
        long kq = sqLiteDatabase.insert("NHANVIEN",null,values);
        if (kq <= 0){
            return -1;
        }
        return 1;
    }

    public int update(NhanVien nv){
        ContentValues values = new ContentValues();
        values.put("hoten",nv.getHoten());
        values.put("level",nv.getLevel());
        values.put("diachi",nv.getDiachi());
        values.put("dienthoai",nv.getDienthoai());
        values.put("password",nv.getPassword());
        long kq = sqLiteDatabase.update("NHANVIEN",values,"id = ?",new String[]{String.valueOf(nv.getId())});
        if (kq <= 0){
            return -1;
        }
        return 1;
    }

    public int delete(String id){
        return sqLiteDatabase.delete("NHANVIEN","id = ?",new String[]{id});
    }

    @SuppressLint("Range")
    public List<NhanVien> getData(String sql, String...selectionArgs){
        List<NhanVien> list = new ArrayList<>();
        Cursor c = sqLiteDatabase.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            NhanVien nv = new NhanVien();
            nv.setId(Integer.parseInt(c.getString(c.getColumnIndex("id"))));
            nv.setHoten(c.getString(c.getColumnIndex("hoten")));
            nv.setGioitinh(c.getString(c.getColumnIndex("gioitinh")));
            nv.setNgaylam(c.getString(c.getColumnIndex("ngaylam")));
            nv.setLevel(c.getString(c.getColumnIndex("level")));
            list.add(nv);
        }
        return list;
    }

    public List<NhanVien> getAll(){
        String sql = "SELECT id, hoten, gioitinh, level, ngaylam FROM NHANVIEN ";
        return getData(sql);
    }

    public NhanVien getID(String id){
        String sql = "SELECT id, hoten, gioitinh, level, ngaylam FROM NHANVIEN WHERE id=?";
        List<NhanVien> nv = getData(sql);
        return nv.get(0);
    }
}