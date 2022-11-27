package com.tweight.mw28.ui.loaihang;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tweight.mw28.DAO.LoaiHangDAO;
import com.tweight.mw28.R;
import com.tweight.mw28.addapter.LoaiHangVAddapter;
import com.tweight.mw28.addapter.SanPhamAddapter;
import com.tweight.mw28.model.LoaiHang;

import java.util.ArrayList;

public class LoaiHangFragment extends Fragment {

    private LoaiHangViewModel mViewModel;
    private RecyclerView recycler;
    private LoaiHangDAO dao;
    private ArrayList<LoaiHang> list = new ArrayList<>();

    public static LoaiHangFragment newInstance() {
        return new LoaiHangFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_loai_hang, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoaiHangViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycler = view.findViewById(R.id.recyclerLoaiHangV);
        dao = new LoaiHangDAO(getContext());
        fillLoaiHang();
    }

    public void fillLoaiHang() {
        list = dao.getLoaiHang();
        recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recycler.setAdapter(new LoaiHangVAddapter(list, getContext()));
    }
}