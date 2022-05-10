package com.example.sskdt_app_v01.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;


import com.example.sskdt_app_v01.HealthDeclarationActivity;
import com.example.sskdt_app_v01.HomeActivity;
import com.example.sskdt_app_v01.ListHealthDeclarationActivity;
import com.example.sskdt_app_v01.R;
import com.example.sskdt_app_v01.adapter.GridAdapterHome;
import com.example.sskdt_app_v01.item.ItemHome;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class TrangChuFragment extends Fragment {
    GridView gridView;
    List<ItemHome> itemHomes;
    TextView user_name;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trang_chu, container, false);

        Bundle bundle = getActivity().getIntent().getExtras();
        String doc = bundle.getString("uid");
        gridView = view.findViewById(R.id.gridview_home);
        user_name = view.findViewById(R.id.user_name);
        itemHomes = new ArrayList<ItemHome>();
        itemHomes.add(new ItemHome(R.drawable.vacine_03, "Hộ chiếu vắc-xin"));
        itemHomes.add(new ItemHome(R.drawable.vacine_03, "Đăng ký tiêm chủng"));
        itemHomes.add(new ItemHome(R.drawable.hen,"Đặt hẹn khám"));
        itemHomes.add(new ItemHome(R.drawable.file,"Hồ sơ sức khỏe"));
        itemHomes.add(new ItemHome(R.drawable.feedback,"Phản ánh tiêm chủng"));
        itemHomes.add(new ItemHome(R.drawable.dotdotdot,"Xem thêm"));


        GridAdapterHome adapter	=	new GridAdapterHome(itemHomes, view.getContext());
        gridView.setAdapter(adapter);


        ConstraintLayout btnKhaiBaoYTe = view.findViewById(R.id.header_home_khaibao);
        btnKhaiBaoYTe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), HealthDeclarationActivity.class);
                intent.putExtra("uid",doc) ;
                startActivity(intent);
            }
        });
        ConstraintLayout btnLichsuKhaiBao = view.findViewById(R.id.header_home_tuvan);
        btnLichsuKhaiBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ListHealthDeclarationActivity.class);
                intent.putExtra("uid",doc) ;
                startActivity(intent);
            }
        });

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Users").document(doc).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Log.d("TAG", "DocumentSnapshot data: " + document.getData());
                                user_name.setText(document.getString("name"));
                            } else {
                                Log.d("TAG", "No such document");
                            }
                        } else {
                            Log.d("TAG", "get failed with ", task.getException());
                        }
                    }
                });

        return view;
    }
}