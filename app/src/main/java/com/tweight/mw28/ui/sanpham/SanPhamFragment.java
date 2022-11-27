package com.tweight.mw28.ui.sanpham;

import static android.content.ContentValues.TAG;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.tweight.mw28.DAO.LoaiHangDAO;
import com.tweight.mw28.DAO.SanPhamDAO;
import com.tweight.mw28.R;
import com.tweight.mw28.addapter.LoaiHangHAddapter;
import com.tweight.mw28.addapter.SanPhamAddapter;
import com.tweight.mw28.model.LoaiHang;
import com.tweight.mw28.model.SanPham;

import java.util.ArrayList;

public class SanPhamFragment extends Fragment {

    private SanPhamViewModel mViewModel;
    private LoaiHangDAO loaiHangDAO;
    private SanPhamDAO sanPhamDAO;
    private RecyclerView recyclerLoaiHang, recyclerSanPham;
    private ArrayList<LoaiHang> listLoaiHang = new ArrayList<>();
    private ArrayList<SanPham> listSanPham = new ArrayList<>();
    private Button btnThemSanPham;
    int giaban, soluong;

    public static SanPhamFragment newInstance() {
        return new SanPhamFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_san_pham, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SanPhamViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loaiHangDAO  =new LoaiHangDAO(getContext());
        sanPhamDAO = new SanPhamDAO(getContext());

        recyclerLoaiHang = view.findViewById(R.id.recyclerLoaiHang);
        recyclerSanPham = view.findViewById(R.id.recyclerSanPham);
        btnThemSanPham = view.findViewById(R.id.btnThemSanPham);

        btnThemSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_them_san_pham);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                TextInputLayout edTenSanPham = dialog.findViewById(R.id.edTenSanPham);
                DatePicker picker = dialog.findViewById(R.id.DPNgayNhapHang);
                TextInputLayout eddonvi = dialog.findViewById(R.id.edDonVi);
                TextInputLayout edgiaban = dialog.findViewById(R.id.edGiaBan);
                TextInputLayout edSoLuong = dialog.findViewById(R.id.edSoLuong);

                dialog.findViewById(R.id.btnCloseDialog).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                ArrayList<String> itemsLoai = new ArrayList<>();
                for (int i = 0 ; i < listLoaiHang.size(); i++) {
                    itemsLoai.add(listLoaiHang.get(i).getTenloai());
                }
                Spinner spinner= dialog.findViewById(R.id.spinerType);
                spinner.setAdapter(new ArrayAdapter(getContext(), android.R.layout.simple_dropdown_item_1line, itemsLoai));

                dialog.findViewById(R.id.btnLuuSanPham).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int maloai = listLoaiHang.get( spinner.getSelectedItemPosition()).getId();
                        String tensanpham = edTenSanPham.getEditText().getText().toString().trim();
                        String ngaynhap = picker.getDayOfMonth() + "/" + picker.getMonth() + "/" + picker.getYear();
                        String donvi = eddonvi.getEditText().getText().toString().trim();
                        try {
                            giaban = Integer.valueOf(edgiaban.getEditText().getText().toString().trim());
                            soluong = Integer.valueOf(edSoLuong.getEditText().getText().toString().trim());
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(dialog.getContext(), "Vui lòng không bỏ trống thông tin !", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (tensanpham.equals("") || ngaynhap.equals("") || donvi.equals("") || edgiaban.getEditText().getText().toString().trim().equals("") || edSoLuong.getEditText().getText().toString().trim().equals("")) {
                            Toast.makeText(dialog.getContext(), "Vui lòng nhập đầy đủ thông tin sản phẩm !", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (sanPhamDAO.addSanPham(maloai, tensanpham, soluong, ngaynhap, giaban, donvi) > 0) {
                            Toast.makeText(getContext(), "Thêm sản phẩm thành công !", Toast.LENGTH_SHORT).show();
                            fillSanPham();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(getContext(), "Lỗi không thể thêm sản phẩm !", Toast.LENGTH_SHORT).show();
                            return;
                        }

                    }
                });

                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        });

        listLoaiHang = loaiHangDAO.getLoaiHang();

        recyclerLoaiHang.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerLoaiHang.setAdapter(new LoaiHangHAddapter(getContext(), listLoaiHang));

        fillSanPham();

    }

    public void fillSanPham() {
        listSanPham = sanPhamDAO.getSanPham();
        recyclerSanPham.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerSanPham.setAdapter(new SanPhamAddapter(listSanPham, getContext()));
    }

}