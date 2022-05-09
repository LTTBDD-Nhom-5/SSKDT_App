package com.example.sskdt_app_v01.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;


import com.example.sskdt_app_v01.ForgotPasswordActivity;
import com.example.sskdt_app_v01.HomeActivity;
import com.example.sskdt_app_v01.InfoUserActivity;
import com.example.sskdt_app_v01.MainActivity;
import com.example.sskdt_app_v01.R;
import com.example.sskdt_app_v01.adapter.ListAdapterUser;
import com.example.sskdt_app_v01.item.ItemUser;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;

public class CaNhanFragment extends Fragment {
    StorageReference storageReference;
    ListView listView;
    ImageView imageView;
    ArrayList<ItemUser> itemUsers;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ca_nhan, container, false);

        listView = view.findViewById(R.id.listViewUser);

        imageView = view.findViewById(R.id.image_ca_nhan_fragment);


        ProgressDialog progressDialog = new ProgressDialog(view.getContext());
        progressDialog.setMessage("Loading image ...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        storageReference = FirebaseStorage.


        itemUsers = new ArrayList<ItemUser>();
        itemUsers.add(new ItemUser(R.drawable.ic_user_person, "Thông tin cá nhân"));
        itemUsers.add(new ItemUser(R.drawable.ic_user_security, "Đổi mật khẩu"));
        itemUsers.add(new ItemUser(R.drawable.ic_user_infomation,"Giới thiệu"));
        itemUsers.add(new ItemUser(R.drawable.ic_user_setting,"Cài đặt"));
        itemUsers.add(new ItemUser(R.drawable.ic_user_logout,"Đăng xuất"));


        ListAdapterUser adapter	= new ListAdapterUser(itemUsers, view.getContext());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0){
                    Intent intent = new Intent(view.getContext(), InfoUserActivity.class);
                    startActivity(intent);
                } else if(i == 1){
                    Intent intent = new Intent(view.getContext(), ForgotPasswordActivity.class);
                    startActivity(intent);
                } else if(i == 4){
                    Intent intent = new Intent(view.getContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        return view;
    }
}