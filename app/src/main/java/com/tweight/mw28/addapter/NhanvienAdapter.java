package com.tweight.mw28.addapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tweight.mw28.ui.nhanvien.NhanvienFragment;
import com.tweight.mw28.R;
import com.tweight.mw28.model.NhanVien;
import java.util.ArrayList;

public class NhanvienAdapter extends ArrayAdapter<NhanVien>{
    Context context;
    NhanvienFragment fragment;
    ArrayList<NhanVien> list;
    TextView ma_nhan_vien;
    TextView ho_ten,level,gioi_tinh,ngay_vao_lam;

    public NhanvienAdapter(@NonNull Context context, NhanvienFragment fragment, ArrayList<NhanVien> list){
        super(context,0,list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_nhanvien,null);
        }
        final  NhanVien item = list.get(position);
        if(item != null){
            ho_ten = v.findViewById(R.id.item_id_nv);
            ho_ten.setText("ID: "+item.getId());

            ma_nhan_vien = v.findViewById(R.id.item_hoten_nv);
            ma_nhan_vien.setText("Name: "+item.getHoten());

            level = v.findViewById(R.id.item_gioitinh_nv);
            level.setText("Gioi tinh: "+item.getGioitinh());

            gioi_tinh = v.findViewById(R.id.item_level_nv);
            gioi_tinh.setText("Level: "+item.getLevel());

            ngay_vao_lam = v.findViewById(R.id.item_ngaylam_nv);
            ngay_vao_lam.setText("Ngày vào làm: "+item.getNgaylam());
        }
        return v;

    }

}