package com.example.sskdt_app_v01;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;

public class Detail_HealthActivity extends AppCompatActivity {

    private EditText name;
    private EditText birthday;
    private boolean gender; // man = false, women = true
    private EditText phone;
    private EditText identification;
    private EditText email;
    private EditText city;
    private EditText district;
    private EditText ward;
    private EditText address;
    private String editHealthId;
    private ImageButton btnDelete;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_health);

        mapping();

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                editHealthId = null;
            } else {
                editHealthId = extras.getString("HealthDeclar");
            }
        } else {
            editHealthId = (String) savedInstanceState.getSerializable("HealthDeclar");
        }

        Log.d("TAG", "editHealthId: " +editHealthId);

        db.collection("HealthDeclarations").document(editHealthId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Log.d("TAG", "onSuccess: ");
                name.setText(documentSnapshot.get("name").toString());
                birthday.setText(formatter.format(documentSnapshot.getDate("birthday")));
                phone.setText(documentSnapshot.get("phone").toString());
                identification.setText(documentSnapshot.get("identification").toString());
                email.setText(documentSnapshot.get("email").toString());
                city.setText(documentSnapshot.get("city").toString());
                district.setText(documentSnapshot.get("district").toString());
                ward.setText(documentSnapshot.get("ward").toString());
                address.setText(documentSnapshot.get("address").toString());

                RadioButton radioButtonMan = (RadioButton) findViewById(R.id.rad_detail_men);
                RadioButton radioButtonWomen = (RadioButton) findViewById(R.id.rad_detail_men);

                checkBoxChecking(documentSnapshot.getBoolean("gender"), radioButtonMan, radioButtonWomen);


                RadioButton radioButtonAns1_n = (RadioButton) findViewById(R.id.rad_que_01_n);
                RadioButton radioButtonAns2_n = (RadioButton) findViewById(R.id.rad_que_02_n);
                RadioButton radioButtonAns3_n = (RadioButton) findViewById(R.id.rad_que_03_n);
                RadioButton radioButtonAns4_n = (RadioButton) findViewById(R.id.rad_que_04_n);
                RadioButton radioButtonAns5_n = (RadioButton) findViewById(R.id.rad_que_05_n);

                RadioButton radioButtonAns1_y = (RadioButton) findViewById(R.id.rad_que_01_y);
                RadioButton radioButtonAns2_y = (RadioButton) findViewById(R.id.rad_que_02_y);
                RadioButton radioButtonAns3_y = (RadioButton) findViewById(R.id.rad_que_03_y);
                RadioButton radioButtonAns4_y = (RadioButton) findViewById(R.id.rad_que_04_y);
                RadioButton radioButtonAns5_y = (RadioButton) findViewById(R.id.rad_que_05_y);


                checkBoxChecking(documentSnapshot.getBoolean("ans_01"), radioButtonAns1_n, radioButtonAns1_y);
                checkBoxChecking(documentSnapshot.getBoolean("ans_02"), radioButtonAns2_n, radioButtonAns2_y);
                checkBoxChecking(documentSnapshot.getBoolean("ans_03"), radioButtonAns3_n, radioButtonAns3_y);
                checkBoxChecking(documentSnapshot.getBoolean("ans_04"), radioButtonAns4_n, radioButtonAns4_y);
                checkBoxChecking(documentSnapshot.getBoolean("ans_05"), radioButtonAns5_n, radioButtonAns5_y);


            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("HealthDeclarations")
                        .document(editHealthId)
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Intent intent = new Intent(Detail_HealthActivity.this, ListHealthDeclarationActivity.class);
                                startActivity(intent);
                            }
                        });
            }
        });
    }

    private void checkBoxChecking(boolean condition, RadioButton rad_false, RadioButton rad_true) {
        if (!condition) {
            rad_false.setChecked(true);
        } else {
            rad_true.setChecked(true);
        }
    }


    private void mapping() {
        name = findViewById(R.id.txt_detail_name);
        birthday = findViewById(R.id.txt_detail_date);
        phone = findViewById(R.id.txt_detail_phone);
        identification = findViewById(R.id.txt_detail_cccd);
        email = findViewById(R.id.txt_detail_email);
        city = findViewById(R.id.txt_detail_city);
        district = findViewById(R.id.txt_detail_district);
        ward = findViewById(R.id.txt_detail_ward);
        address = findViewById(R.id.txt_detail_address);
        btnDelete = findViewById(R.id.btn_detail_delete);
    }
}