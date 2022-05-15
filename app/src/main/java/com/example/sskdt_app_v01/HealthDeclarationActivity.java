package com.example.sskdt_app_v01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.sskdt_app_v01.model.HealthDeclaration;
import com.example.sskdt_app_v01.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HealthDeclarationActivity extends AppCompatActivity {
    private EditText name,birth,phone,identification, email, city, district, ward, address;
    private RadioButton men, women, another, ans1, ans2,ans3,ans4, ans5;
    private FirebaseAuth mAuth;
    private Button btn_send_declaration;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_declaration);
        ConstraintLayout btn_return_healthdeclaraton = findViewById(R.id.btn_return_healthdeclaraton);

        mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        name = findViewById(R.id.txt_decla_name);
        birth = findViewById(R.id.txt_decla_date);
        phone = findViewById(R.id.txt_decla_phone);
        identification = findViewById(R.id.txt_decla_cccd);
        email  = findViewById(R.id.txt_decla_email);
        city  = findViewById(R.id.txt_decla_city);
        ward = findViewById(R.id.txt_decla_ward);
        district = findViewById(R.id.txt_decla_district);
        address = findViewById(R.id.txt_decla_address);

        men = findViewById(R.id.rad_decla_men);
        women = findViewById(R.id.rad_decla_women);
        another = findViewById(R.id.rad_decla_more);
        ans1= findViewById(R.id.rad_ans_01_y);
        ans2= findViewById(R.id.rad_ans_02_y);
        ans3= findViewById(R.id.rad_ans_03_y);
        ans4= findViewById(R.id.rad_ans_04_y);
        ans5= findViewById(R.id.rad_ans_05_y);

        btn_send_declaration = findViewById(R.id.btn_send_decla);

        Bundle bundle = getIntent().getExtras();
        String doc =  bundle.getString("uid");

        btn_return_healthdeclaraton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HealthDeclarationActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        btn_send_declaration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date birthRef = null;
                try {
                    birthRef= new SimpleDateFormat("dd/MM/yyyy").parse(birth.getText().toString().trim());

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                boolean gender = men.isChecked() ?false : true;
                boolean ans1Ref = ans1.isChecked() ? false : true;
                boolean ans2Ref = ans2.isChecked() ? false : true;
                boolean ans3Ref = ans3.isChecked() ? false : true;
                boolean ans4Ref = ans4.isChecked() ? false : true;
                boolean ans5Ref = ans5.isChecked() ? false : true;



                HealthDeclaration declarationRef =new HealthDeclaration( "z23sded7678a9sdx978k" ,
                        name.getText().toString().trim().toUpperCase(),birthRef,gender,
                        phone.getText().toString().trim(),
                        identification.getText().toString().trim(),
                        email.getText().toString().trim(),
                        city.getText().toString().trim(),
                        district.getText().toString().trim(),
                        ward.getText().toString().trim(),
                        address.getText().toString(),new Date(), ans1Ref,
                        ans2Ref, ans3Ref, ans4Ref,ans5Ref
                        );
                db.collection("HealthDeclarations").document(doc)
                        .set(declarationRef);
                Intent intent = new Intent(HealthDeclarationActivity.this,HomeActivity.class);
                intent.putExtra("uid",doc);
                startActivity(intent);
            }
        });
    }
}
