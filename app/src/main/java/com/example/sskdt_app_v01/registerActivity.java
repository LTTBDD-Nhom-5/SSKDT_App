package com.example.sskdt_app_v01;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sskdt_app_v01.model.User;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class registerActivity extends AppCompatActivity {
    private PhoneAuthProvider.ForceResendingToken token;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    private String vertifiedId;
    private FirebaseAuth firebaseAuth;
    private Button btnRegister;
    private ImageButton eye01, eye02;
    private TextView phoneRegister, nameRegister, passwordRegister, rePasswordRegister, note, return_login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        btnRegister = findViewById(R.id.btn_register);
        phoneRegister = findViewById(R.id.txt_register_phone);
        nameRegister = findViewById(R.id.txt_register_name);
        passwordRegister = findViewById(R.id.txt_register_password);
        rePasswordRegister = findViewById(R.id.txt_register_re_password);
        return_login = findViewById(R.id.btn_return_login);
        eye01 = findViewById(R.id.btn_eye_01);
        eye02 = findViewById(R.id.btn_eye_02);
        note = findViewById(R.id.txt_register_note);

        return_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(registerActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        eye01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unlockPass(passwordRegister,eye01);
            }
        });
        eye02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unlockPass(rePasswordRegister,eye02);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkValue()) {
                    String name = nameRegister.getText().toString().trim().toUpperCase();
                    String phone = "+84" + phoneRegister.getText().toString().trim().substring(1);
                    String password = passwordRegister.getText().toString().trim();
                    Intent intent = new Intent(registerActivity.this, VertifyActivity.class);
                    intent.putExtra("nameRegister", name);
                    intent.putExtra("phoneRegister", phone);
                    intent.putExtra("passwordRegister", password);

                    Log.d("TAG", name + " " + phone + " " + password);
                    startActivity(intent);
                }
            }
        });
    }

    public void unlockPass(TextView edt, ImageButton imageButton){
        if(edt.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
            imageButton.setImageResource(R.drawable.eye_02);
            edt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        else{
            imageButton.setImageResource(R.drawable.eye);
            edt.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    private boolean checkValue() {
        if(nameRegister.getText().toString().trim().equals("")){
            note.setText("* Họ và tên không được trống");
            return false;
        }else if(phoneRegister.getText().toString().trim().equals("")){
            note.setText("* Số điện thoại không được trống");
            return false;
        }else if(!Pattern.matches("[0-9]{10}",phoneRegister.getText().toString().trim())){
            note.setText("* Số điện thoại gồm 10 kí tự số");
            return false;
        }else if(passwordRegister.getText().toString().trim().equals("")){
            note.setText("* Mật khẩu không được để trống");
            return false;
        }else if(passwordRegister.getText().toString().trim().length() < 6){
            note.setText("* Mật khẩu phải từ 6 kí tự");
            return false;
        }else if(rePasswordRegister.getText().toString().trim().equals("")){
            note.setText("* Nhập lại mật khẩu không được để trống");
            return false;
        }else if(!rePasswordRegister.getText().toString().trim().equals(passwordRegister.getText().toString().trim())){
            note.setText("* Mật khẩu và nhập lại mật khẩu phải trùng nhau");
            return false;
        }
        note.setText("");
        return true;
    }


}
