package com.example.sskdt_app_v01.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.example.sskdt_app_v01.R;
import com.example.sskdt_app_v01.adapter.ListAdapterUser;
import com.example.sskdt_app_v01.item.ItemUser;

import java.util.ArrayList;

public class CaNhanFragment extends Fragment {

    ListView listView;
    ArrayList<ItemUser> itemUsers;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ca_nhan, container, false);

        listView = view.findViewById(R.id.listViewUser);
        itemUsers = new ArrayList<ItemUser>();
        itemUsers.add(new ItemUser(R.drawable.ic_user_person, "Thông tin cá nhân"));
        itemUsers.add(new ItemUser(R.drawable.ic_user_security, "Đổi mật khẩu"));
        itemUsers.add(new ItemUser(R.drawable.ic_user_infomation,"Giới thiệu"));
        itemUsers.add(new ItemUser(R.drawable.ic_user_setting,"Cài đặt"));
        itemUsers.add(new ItemUser(R.drawable.ic_user_logout,"Đăng xuất"));


        ListAdapterUser adapter	= new ListAdapterUser(itemUsers, view.getContext());
        listView.setAdapter(adapter);
        return view;
    }
}