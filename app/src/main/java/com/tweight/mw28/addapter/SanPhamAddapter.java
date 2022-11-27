package com.tweight.mw28.addapter;

import static java.security.AccessController.getContext;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;
import com.tweight.mw28.DAO.LoaiHangDAO;
import com.tweight.mw28.DAO.SanPhamDAO;
import com.tweight.mw28.R;
import com.tweight.mw28.model.LoaiHang;
import com.tweight.mw28.model.SanPham;

import java.util.ArrayList;

public class SanPhamAddapter extends RecyclerView.Adapter < SanPhamAddapter.viewholder > {
    private ArrayList < SanPham > list;
    private ArrayList < LoaiHang > listLoaiHang = new ArrayList < > ();
    private Context context;
    private SanPhamDAO sanPhamDAO;
    int soluong,
            giaban;

    public SanPhamAddapter(ArrayList < SanPham > list, Context context) {
        this.list = list;
        this.context = context;
        sanPhamDAO = new SanPhamDAO(context);
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_san_pham, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, @SuppressLint("RecyclerView") int posi) {
        SanPham sanPham = list.get(posi);
        if (sanPham.getAnh().equals("")) {
            holder.imAnh.setImageResource(R.drawable.error);
        }

        holder.tvTenSanPham.setText(sanPham.getTensanpham());

        holder.tvTenSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, posi + "", Toast.LENGTH_SHORT).show();
            }
        });

        holder.tvGiaBan.setText(sanPham.getGiaban() + " VNĐ");
        holder.imMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(context, v);
                popup.getMenuInflater().inflate(R.menu.san_pham_options_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.option_xoa) {
                            MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(context);
                            dialog.setTitle("Thông báo");
                            dialog.setMessage("Bạn có chắc muốn xóa?");
                            dialog.setNeutralButton("Không", null);
                            dialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String msg = "";

                                    if (sanPhamDAO.removeSanPham(sanPham.getId()) == 1) {
                                        list.remove(sanPham);
                                        notifyDataSetChanged();
                                        msg = "Xóa thành công !";
                                    } else {
                                        msg = "Lỗi không thể xóa !";
                                    }

                                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

                                }
                            });
                            dialog.show();
                        }
                        if (item.getItemId() == R.id.option_sua) {
                            // --- Main content --- //
                            Dialog dialog = new Dialog(context);
                            dialog.setContentView(R.layout.dialog_them_san_pham);
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            TextInputLayout edTenSanPham = dialog.findViewById(R.id.edTenSanPham);
                            DatePicker picker = dialog.findViewById(R.id.DPNgayNhapHang);
                            TextInputLayout eddonvi = dialog.findViewById(R.id.edDonVi);
                            TextInputLayout edgiaban = dialog.findViewById(R.id.edGiaBan);
                            TextInputLayout edSoLuong = dialog.findViewById(R.id.edSoLuong);
                            Spinner spinner = dialog.findViewById(R.id.spinerType);

                            edTenSanPham.getEditText().setText(sanPham.getTensanpham());
                            eddonvi.getEditText().setText(sanPham.getDonvi());
                            edgiaban.getEditText().setText(sanPham.getGiaban() + "");
                            edSoLuong.getEditText().setText(sanPham.getSoluong() + "");

                            LoaiHangDAO loaiHangDAO = new LoaiHangDAO(context);

                            listLoaiHang = loaiHangDAO.getLoaiHang();

                            dialog.findViewById(R.id.btnCloseDialog).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });
                            ArrayList < String > itemsLoai = new ArrayList < > ();
                            for (int i = 0; i < listLoaiHang.size(); i++) {
                                itemsLoai.add(listLoaiHang.get(i).getTenloai());
                            }

                            spinner.setAdapter(new ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line, itemsLoai));

                            for (int i = 0; i < listLoaiHang.size(); i++) {
                                if (sanPham.getMaloai() == listLoaiHang.get(i).getId()) {
                                    spinner.setSelection(i);
                                    break;
                                }
                            }

                            dialog.findViewById(R.id.btnLuuSanPham).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int maloai = listLoaiHang.get(spinner.getSelectedItemPosition()).getId();
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

                                    if (sanPhamDAO.updateSanPham(maloai, tensanpham, soluong, ngaynhap, giaban, donvi, sanPham.getId()) == 1) {
                                        Toast.makeText(context, "Sửa sản phẩm thành công !", Toast.LENGTH_SHORT).show();
                                        sanPham.setMaloai(maloai);
                                        sanPham.setTensanpham(tensanpham);
                                        sanPham.setSoluong(soluong);
                                        sanPham.setGiaban(giaban);
                                        sanPham.setDonvi(donvi);
                                        sanPham.setNgaynhap(ngaynhap);
                                        notifyDataSetChanged();
                                        dialog.dismiss();
                                    } else {
                                        Toast.makeText(context, "Lỗi không thể sửa sản phẩm !", Toast.LENGTH_SHORT).show();
                                        return;
                                    }

                                }
                            });

                            dialog.show();
                            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            // --- Main content --- //

                        }
                        return false;
                    }
                });

                popup.setOnDismissListener(new PopupMenu.OnDismissListener() {
                    @Override
                    public void onDismiss(PopupMenu menu) {

                    }
                });
                // Show the popup menu.
                popup.show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class viewholder extends RecyclerView.ViewHolder {
        private ImageView imAnh, imMore;
        private TextView tvTenSanPham, tvGiaBan;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            imAnh = itemView.findViewById(R.id.imAnh);
            imMore = itemView.findViewById(R.id.imMoreOptions);

            tvTenSanPham = itemView.findViewById(R.id.tvTenSanPham);
            tvGiaBan = itemView.findViewById(R.id.tvGiaBan);
        }
    }
}