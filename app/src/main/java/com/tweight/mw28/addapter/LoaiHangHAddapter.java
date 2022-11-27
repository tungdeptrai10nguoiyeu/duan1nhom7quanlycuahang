package com.tweight.mw28.addapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tweight.mw28.R;
import com.tweight.mw28.model.LoaiHang;

import java.util.ArrayList;

public class LoaiHangHAddapter extends RecyclerView.Adapter<LoaiHangHAddapter.viewHolder> {
    private Context context;
    private ArrayList<LoaiHang> list = new ArrayList<>();

    public LoaiHangHAddapter(Context context, ArrayList<LoaiHang> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_loai_hang_h, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        LoaiHang loaiHang = list.get(position);

        holder.ChonLoaiHang.setText(loaiHang.getTenloai());
        holder.ChonLoaiHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        Button ChonLoaiHang;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            ChonLoaiHang = itemView.findViewById(R.id.ChonLoaiHang);
        }
    }
}
