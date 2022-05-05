package com.example.sskdt_app_v01.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;


import com.example.sskdt_app_v01.R;
import com.example.sskdt_app_v01.adapter.GridAdapterHome;
import com.example.sskdt_app_v01.item.ItemHome;

import java.util.ArrayList;
import java.util.List;

public class TrangChuFragment extends Fragment {
    GridView gridView;
    List<ItemHome> itemHomes;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trang_chu, container, false);

        gridView = view.findViewById(R.id.gridview_home);
        itemHomes = new ArrayList<ItemHome>();
        itemHomes.add(new ItemHome(R.drawable.vacine_03, "Hộ chiếu vắc-xin"));
        itemHomes.add(new ItemHome(R.drawable.vacine_03, "Đăng ký tiêm chủng"));
        itemHomes.add(new ItemHome(R.drawable.hen,"Đặt hẹn khám"));
        itemHomes.add(new ItemHome(R.drawable.file,"Hồ sơ sức khỏe"));
        itemHomes.add(new ItemHome(R.drawable.feedback,"Phản ánh tiêm chủng"));
        itemHomes.add(new ItemHome(R.drawable.dotdotdot,"Xem thêm"));


        GridAdapterHome adapter	=	new GridAdapterHome(itemHomes, view.getContext());
        gridView.setAdapter(adapter);
        return view;
    }
}