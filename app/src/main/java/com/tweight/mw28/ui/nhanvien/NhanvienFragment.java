package com.tweight.mw28.ui.nhanvien;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.tweight.mw28.R;
import com.tweight.mw28.model.NhanVien;
import com.tweight.mw28.DAO.NhanVienDAO;
import com.tweight.mw28.addapter.NhanvienAdapter;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class NhanvienFragment extends Fragment {
    ListView lv;
    ArrayList<NhanVien> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText id,hoten,username,password,gioitinh,diachi,dienthoai,level,ngaylam;
    Button btnsave;
    NhanVienDAO nvDao;
    NhanvienAdapter adapter;
    NhanVien item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_dsnhanvien, container, false);
        lv = view.findViewById(R.id.lv_nhanvien);
        fab = view.findViewById(R.id.tv_fab);
        nvDao = new NhanVienDAO(getContext());
        capnhat();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialogInsert(getContext());
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                opendialogUpdate(item);
            }
        });
        return view;
    }

    public void opendialogInsert(Context context) {
        dialog = new Dialog(context);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        dialog.setContentView(R.layout.dialog_themnv);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        window.setAttributes(windowAttributes);
        // ánh xạ
        id = dialog.findViewById(R.id.edt_tnv_id);
        hoten = dialog.findViewById(R.id.edt_tnv_hoten);
        username = dialog.findViewById(R.id.edt_tnv_username);
        password = dialog.findViewById(R.id.edt_tnv_password);
        gioitinh = dialog.findViewById(R.id.edt_tnv_gioitinh);
        diachi = dialog.findViewById(R.id.edt_tnv_diachi);
        dienthoai = dialog.findViewById(R.id.edt_tnv_dienthoai);
        ngaylam = dialog.findViewById(R.id.edt_tnv_ngaylam);
        level = dialog.findViewById(R.id.edt_tnv_level);

        btnsave = dialog.findViewById(R.id.btn_tnv_them);


        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVien item = new NhanVien();
                item.setId(Integer.valueOf(id.getText().toString()));
                item.setHoten(hoten.getText().toString());
                item.setUsername(username.getText().toString());
                item.setPassword(password.getText().toString());
                item.setGioitinh(gioitinh.getText().toString());
                item.setDiachi(diachi.getText().toString());
                item.setDienthoai(Integer.parseInt(dienthoai.getText().toString()));
                item.setNgaylam(ngaylam.getText().toString());
                item.setLevel(level.getText().toString());
                if (validate() > 0) {
                    int kq = nvDao.insert(item);
                    if (kq == 1) {
                        Toast.makeText(getContext(), "Thêm thành công!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Thêm không thành công!", Toast.LENGTH_SHORT).show();
                    }
                    capnhat();
                    dialog.cancel();

                }
            }
        });
        dialog.show();
    }

    public void opendialogUpdate(NhanVien nv) {
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_csnhanvien);

        hoten = dialog.findViewById(R.id.edt_csnv_hoten);
        level = dialog.findViewById(R.id.edt_csnv_level);
        diachi = dialog.findViewById(R.id.edt_csnv_diachi);
        dienthoai = dialog.findViewById(R.id.edt_csnv_dienthoai);
        password = dialog.findViewById(R.id.edt_csnv_password);

        btnsave = dialog.findViewById(R.id.btn_csnv_luu);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new NhanVien();
                item.setHoten(hoten.getText().toString());
                item.setLevel(level.getText().toString());
                item.setDiachi(diachi.getText().toString());
                item.setDienthoai(Integer.parseInt(dienthoai.getText().toString()));
                item.setPassword(password.getText().toString());
                if (validate() > 0) {
                    int kq = nvDao.update(item);
                    if (kq == 1) {
                        Toast.makeText(getContext(), "Update thành công!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Update không thành công!", Toast.LENGTH_SHORT).show();
                    }
                    capnhat();
                    dialog.dismiss();

                }
            }
        });
        dialog.show();
    }
    public void xoa(String id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Xoa?");
        builder.setCancelable(true);
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nvDao.delete(id);
                capnhat();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("NO",((dialog1, which) -> {dialog.cancel();}));
        AlertDialog alertDialog = builder.create();
        builder.show();
    }

    public int validate(){
        int check =1;
        if(hoten.getText().length()==0 || level.getText().length() ==0 || diachi.getText().length() == 0 || password.getText().length() == 0 ){
            Toast.makeText(getContext(), "Chưa nhập đủ thông tin !",Toast.LENGTH_SHORT).show();
        }
        return check;
    }
    public void capnhat(){
        list = (ArrayList<NhanVien>) nvDao.getAll();
        adapter = new NhanvienAdapter(getContext(),this,list);
        lv.setAdapter(adapter);
}
}

