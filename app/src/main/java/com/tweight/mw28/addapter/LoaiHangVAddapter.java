package com.tweight.mw28.addapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.tweight.mw28.DAO.LoaiHangDAO;
import com.tweight.mw28.R;
import com.tweight.mw28.model.LoaiHang;

import java.util.ArrayList;

public class LoaiHangVAddapter extends RecyclerView.Adapter<LoaiHangVAddapter.viewholder>{
    private ArrayList<LoaiHang> list = new ArrayList<>();
    private Context context;
    private LoaiHangDAO dao;

    public LoaiHangVAddapter(ArrayList<LoaiHang> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_loai_hang_vertical, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        dao = new LoaiHangDAO(context);
        LoaiHang loaiHang = list.get(position);

        holder.tvStt.setText(position + "");
        holder.tvTenLoai.setText(loaiHang.getTenloai());
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(context);
                dialog.setTitle("Thông báo");
                dialog.setMessage("Bạn có chắc muốn xóa?");
                dialog.setNeutralButton("Không", null);
                dialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String msg = "";
                        if (dao.removeLoaiHang(loaiHang.getId()) == 1) {
                            list.remove(loaiHang);
                            notifyDataSetChanged();
                            msg = "Đã xóa loại sản phẩm này.";
                        }
                        else
                            msg = "Lỗi không thể xóa !";
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class viewholder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tvTenLoai;
        TextView tvStt;
        ImageView btnEditLoai;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.loaiItemsCard);
            tvTenLoai = itemView.findViewById(R.id.tvTenLoai);
            tvStt = itemView.findViewById(R.id.tvStt);
            btnEditLoai = itemView.findViewById(R.id.btnEditLoai);
        }
    }
}
