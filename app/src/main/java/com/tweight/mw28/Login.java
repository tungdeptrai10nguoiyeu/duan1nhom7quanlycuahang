package com.tweight.mw28;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.tweight.mw28.DAO.ChuCuaHangDAO;
import com.tweight.mw28.model.ChuCuaHang;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {
    EditText username,password;
    Button btnsingin;
    ChuCuaHang chucuahang;
    List<ChuCuaHang> list = new ArrayList<>();
    ChuCuaHangDAO chuDao;
// code dao
    // tao code dao
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.edttendangnhap);
        password = findViewById(R.id.edtmatkhau);
        btnsingin = findViewById(R.id.btnlogin);
        chuDao = new ChuCuaHangDAO(this);

        if (chuDao.checktkdau() == 1){
            chucuahang = new ChuCuaHang();
            chucuahang.setMa_chu_cua_hang(Integer.parseInt("01"));
            chucuahang.setHo_ten("Nguyễn Xuân Tùng");
            chucuahang.setSo_dien_thoai("0395257193");
            chucuahang.setTai_khoan("tung");
            chucuahang.setMat_khau("123");
            chucuahang.setLevel("01");
            chuDao.insert(chucuahang);
        }


        //
        btnsingin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checklogin();
            }
        });
        //
    }

    //
    private void checklogin(){
        String tendangnhap = username.getText().toString();
        String matkhau = password.getText().toString();
        if (tendangnhap.isEmpty() || matkhau.isEmpty()){
            Toast.makeText(this,"Chưa điền tên đăng nhập hoặc mật khẩu!",Toast.LENGTH_SHORT).show();
        } else {
            if (chuDao.checklogin(tendangnhap,matkhau) > 0){
                Toast.makeText(this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("user",tendangnhap);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this,"Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    protected  void onStart() {

        super.onStart();
    }
}
