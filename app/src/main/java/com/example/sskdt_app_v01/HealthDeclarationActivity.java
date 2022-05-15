package com.example.sskdt_app_v01;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.sskdt_app_v01.model.HealthDeclaration;
import com.example.sskdt_app_v01.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HealthDeclarationActivity extends AppCompatActivity {
    private EditText name,birth,phone,identification, email, city, district, ward, address;
    private RadioButton men, women, another, ans1, ans2,ans3,ans4, ans5;
    private FirebaseAuth mAuth;
    private Button btn_send_declaration;
    private CheckBox chk_khaiho;
    private String doc;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_declaration);
        ConstraintLayout btn_return_healthdeclaraton = findViewById(R.id.btn_return_healthdeclaraton);

        mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        chk_khaiho = findViewById(R.id.chk_khaiho);

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
        doc =  bundle.getString("uid");

        autoField();

        chk_khaiho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chk_khaiho.isChecked()){
                    phone.setEnabled(true);
                    name.setText("");
                    phone.setText("");
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    birth.setText("");
                    men.setChecked(true);
                    identification.setText("");
                    email.setText("");
                    city.setText("");
                    ward.setText("");
                    district.setText("");
                    address.setText("");
                }else
                    autoField();
            }
        });

        btn_return_healthdeclaraton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HealthDeclarationActivity.this, HomeActivity.class);
                intent.putExtra("uid",doc);
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
                boolean gernderRef = men.isChecked() ? false  : true;
                boolean ans1Ref = ans1.isChecked() ? true  : false;
                boolean ans2Ref = ans2.isChecked() ? true  : false;
                boolean ans3Ref = ans3.isChecked() ? true  : false;
                boolean ans4Ref = ans4.isChecked() ? true  : false;
                boolean ans5Ref = ans5.isChecked() ? true  : false;



                HealthDeclaration declarationRef =new HealthDeclaration( doc.toString().trim() ,
                        name.getText().toString().trim().toUpperCase(),birthRef,gernderRef,                                                                       
                        phone.getText().toString().trim(),
                        identification.getText().toString().trim(),
                        email.getText().toString().trim(),
                        city.getText().toString().trim(),
                        district.getText().toString().trim(),
                        ward.getText().toString().trim(),
                        address.getText().toString(),new Date(), ans1Ref,
                        ans2Ref, ans3Ref, ans4Ref,ans5Ref
                        );

                db.collection("HealthDeclarations").add(declarationRef)
                  .addOnSuccessListener(new OnSuccessListener<DocumentReference>(){
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());

                        Intent intent = new Intent(HealthDeclarationActivity.this,Info_healthActivity.class);
                        intent.putExtra("name",declarationRef.getName());
                        intent.putExtra("time" , new SimpleDateFormat("MM/dd/yyyy  HH:mm:ss").format(declarationRef.getCreate_at()));
                        intent.putExtra("uid",doc);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error adding document", e);
                    }
                });
            }
        });

    }
    public void autoField(){
        phone.setEnabled(false);
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
                                phone.setText("0"+document.getString("phone").substring(3));
                                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                birth.setText(document.getDate("birthday") == null ? "" : dateFormat.format(document.getDate("birthday")));
                                if(document.getBoolean("gender"))
                                    women.setChecked(true) ;
                                else men.setChecked(true);
                                identification.setText(document.getString("identification") == null ? "" : document.getString("identification") );
                                email.setText(document.getString("email") == null ? "" : document.getString("email") );
                                city.setText(document.getString("city") == null ? "" : document.getString("city") );
                                ward.setText(document.getString("ward") == null ? "" : document.getString("ward") );
                                district.setText(document.getString("district") == null ? "" : document.getString("district") );
                                address.setText(document.getString("address") == null ? "" : document.getString("address") );
                            } else {
                                Log.d("TAG", "No such document");
                            }
                        } else {
                            Log.d("TAG", "get failed with ", task.getException());
                        }
                    }
                });
    }

}
