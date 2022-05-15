package com.example.sskdt_app_v01;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.sskdt_app_v01.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ForgotPasswordActivity extends AppCompatActivity {

    private TextView txt_old_pass, txt_new_pass, txt_confirm_pass, note;
    private ImageButton show_01, show_02, show_03;
    private Button btn_change_password;
    private ConstraintLayout btnReturnCaNhan;
    private String old_pass, new_pass, confirm_pass;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        txt_confirm_pass = findViewById(R.id.txt_confirm_pass);
        txt_new_pass = findViewById(R.id.txt_new_pass);
        txt_old_pass = findViewById(R.id.txt_old_pass);
        show_01 =  findViewById(R.id.btn_forgotpass_show_01);
        show_02 =  findViewById(R.id.btn_forgotpass_show_02);
        show_03 =  findViewById(R.id.btn_forgotpass_show_03);
        btn_change_password = findViewById(R.id.btn_change_password);
        btnReturnCaNhan = findViewById(R.id.btn_return_canhan);
        note = findViewById(R.id.txt_change_note);
        Bundle bundle = getIntent().getExtras();
        String doc =  bundle.getString("uid");


        btn_change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new_pass = txt_new_pass.getText().toString();
                confirm_pass = txt_confirm_pass.getText().toString();
                old_pass = txt_old_pass.getText().toString();
                if(old_pass.isEmpty()){
                    note.setText("Mật khẩu cũ không được trống");
                    return;
                }else if(new_pass.isEmpty()){
                    note.setText("Mật khẩu mới không được trống");
                    return;
                } else if(confirm_pass.isEmpty()){
                    note.setText("Nhập lại mật khẩu không được trống");
                    return;
                } else if(!new_pass.equals(confirm_pass)){
                    note.setText("Mật khẩu và nhập lại mật khẩu phải trùng nhau");
                    return;
                }else
                    note.setText("");

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("Users").document(doc).get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        Log.d("TAG", "DocumentSnapshot data: " + document.getData());

//                                        User u = new User(
//                                                txtname, txtdate, gioiTinh,tell,
//                                                cccd_2,email.getText().toString().trim(),
//                                                city.getText().toString().trim(),
//                                                district.getText().toString().trim(),
//                                                ward.getText().toString().trim(),
//                                                address.getText().toString().trim(), null, pass );

                                        User u = new User(
                                                document.getString("name"),
                                                document.getDate("birthday"),
                                                document.getBoolean("gender"),
                                                document.getString("phone"),
                                                document.getString("identification"),
                                                document.getString("email"),
                                                document.getString("city"),
                                                document.getString("district"),
                                                document.getString("ward"),
                                                document.getString("address"), null, document.getString("password") );
                                        if(old_pass.equals(u.getPassword())){
                                            u.setPassword(new_pass);
                                            db.collection("Users").document(doc).set(u);
                                            Intent intent = new Intent(ForgotPasswordActivity.this, HomeActivity.class);
                                            intent.putExtra("uid",doc);
                                            startActivity(intent);
                                        }else{
                                            note.setText("Mật khẩu cũ không chính xác");
                                        }
                                    } else {
                                        Log.d("TAG", "No such document");
                                    }
                                } else {
                                    Log.d("TAG", "get failed with ", task.getException());
                                }
                            }
                        });
            }
        });

        show_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unlockPass(txt_new_pass,show_02);
            }
        });

        show_03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unlockPass(txt_confirm_pass,show_03);
            }
        });
        show_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unlockPass(txt_old_pass,show_01);
            }
        });

       btnReturnCaNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgotPasswordActivity.this, HomeActivity.class);
                intent.putExtra("uid",doc);
                startActivity(intent);
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
}
