package com.example.sskdt_app_v01;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class InfoUserActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText name,date,tel,cccd,emai,city,ward, district, address;
    private CheckBox nam, nu;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_user);
        mAuth = FirebaseAuth.getInstance();
        ConstraintLayout btn_return_info =  findViewById(R.id.btn_return_info);
        name = findViewById(R.id.txt_info_name);
        tel = findViewById(R.id.txt_info_phone);
        Bundle bundle = getIntent().getExtras();
        String doc =  bundle.getString("uid");

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Users").document(doc).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("TAG", "DocumentSnapshot data: " + document.getData());
                        name.setText(document.getString("name"));
                        tel.setText("0"+document.getString("phone").substring(3));
                    } else {
                        Log.d("TAG", "No such document");
                    }
                } else {
                    Log.d("TAG", "get failed with ", task.getException());
                }
            }
        });

        btn_return_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoUserActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
