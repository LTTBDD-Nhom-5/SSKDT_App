package com.example.sskdt_app_v01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.base.Joiner;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    
    private TextView btnRegisterPage, note;
    private Button btnLogin;
    private EditText txt_login_phone, txt_login_password;
    private FirebaseAuth mAuth;
    private ImageButton btnshow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        onStart();
        btnLogin = findViewById(R.id.btn_login);
        btnRegisterPage = findViewById(R.id.btn_return_register);
        txt_login_password = findViewById(R.id.txt_login_password);
        txt_login_phone = findViewById(R.id.txt_login_phone);
        note = findViewById(R.id.txt_login_note);
        btnshow = findViewById(R.id.btn_login_showpass);
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unlockPass(txt_login_password,btnshow);
            }
        });

        // EVENT
        btnRegisterPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, registerActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txt_login_phone.getText().toString().trim().equals("")){
                    note.setText("* Số điện thoại không được trống");
                    return ;
                }else if(txt_login_password.getText().toString().trim().equals("")){
                    note.setText("* Mật khẩu không được để trống");
                    return ;
                }
                //filter
                String txtPhone = "+84" + txt_login_phone.getText().toString().trim().substring(1);
                String txtPassword =  txt_login_password.getText().toString().trim();
                db.collection("Users")
                    .whereEqualTo("phone",txtPhone)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + document.getData());
                                String pass = document.getString("password").toString();
                                String id = document.getId().toString();
                                if(!txtPassword.equals(pass)){
                                    txt_login_password.setText("");
                                    note.setText("* Số điện thoại hoặc mật khẩu không chính xác");
                                    Log.d("TAG", " Số điện thoại hoặc mật khẩu không chính xác ");
                                }
                                else{
                                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                    intent.putExtra("uid",document.getId());
                                    startActivity(intent);
                                    txt_login_password.setText("");
                                    note.setText("");
                                }
                                break;
                            }
                        } else {
                            txt_login_password.setText("");
                            note.setText("* Số điện thoại hoặc mật khẩu không chính xác");
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
            intent.putExtra("phone", currentUser.getPhoneNumber());
            Log.d("TAG", " Số điện thoại: " + currentUser.getPhoneNumber());
            startActivity(intent);
        }
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

}