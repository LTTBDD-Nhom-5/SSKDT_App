package com.example.sskdt_app_v01;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class registerActivity extends AppCompatActivity {
    private PhoneAuthProvider.ForceResendingToken token;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    private String vertifiedId;
    private FirebaseAuth firebaseAuth;
    private Button btnRegister;
    private TextView phoneRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        btnRegister = findViewById(R.id.btn_register);
        phoneRegister = findViewById(R.id.txt_register_phone);

        // name {không bỏ trống}
        // phone {phải 10 số}
        // pass {8 kí tự trở lên

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = "+84"+phoneRegister.getText().toString().trim().substring(1);
                if (TextUtils.isEmpty(phone)) {
                    Log.d("TAG", "phone register: no value ");
                } else {
                    Intent intent = new Intent(registerActivity.this, VertifyActivity.class);
                    intent.putExtra("phone", phone);
                    startActivity(intent);

                }


                Log.d("TAG", "onClick: "+ phone);

            }
        });
    }


}
