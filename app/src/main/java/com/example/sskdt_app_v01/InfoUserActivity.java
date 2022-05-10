package com.example.sskdt_app_v01;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.sskdt_app_v01.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class InfoUserActivity extends AppCompatActivity {
    ImageView imgUser;
    Uri imageUri;
    FirebaseStorage storage;
    StorageReference storageRef;
    private FirebaseAuth mAuth;
    private EditText name,date,tel,cccd,email,city,ward, district, address;
    private RadioButton nam, nu;
    private Button btn_change_info;
    private String pass, tell;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_user);
        mAuth = FirebaseAuth.getInstance();
        ConstraintLayout btn_return_info =  findViewById(R.id.btn_return_info);

        name = findViewById(R.id.txt_info_name);
        tel = findViewById(R.id.txt_info_phone);
        date = findViewById(R.id.txt_info_date);
        cccd  = findViewById(R.id.txt_info_cccd);
        email = findViewById(R.id.txt_info_email);
        city  = findViewById(R.id.txt_info_city);
        ward = findViewById(R.id.txt_info_ward);
        district = findViewById(R.id.txt_info_district);
        address = findViewById(R.id.txt_info_address);

        nam = findViewById(R.id.rad_infor_men);
        nu = findViewById(R.id.rad_info_women);

        btn_change_info = findViewById(R.id.btn_change_info);



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
                        pass = document.getString("password").toString();
                        tell = document.getString("phone").toString();
                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        date.setText(document.getDate("birthday") == null ? "" : dateFormat.format(document.getDate("birthday")));
                        if(document.getBoolean("gender"))
                            nu.setChecked(true) ;
                        else nam.setChecked(true);
                        cccd.setText(document.getString("identification") == null ? "" : document.getString("identification") );
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


        storage = FirebaseStorage.getInstance();
        imgUser = findViewById(R.id.info_user_image);


        btn_return_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoUserActivity.this,HomeActivity.class);
                intent.putExtra("uid",doc);
                startActivity(intent);
            }
        });

        imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedImage();
            }
        });

        btn_change_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtname = name.getText().toString().trim().toUpperCase();
                Date txtdate = null;
                try {
                    txtdate= new SimpleDateFormat("dd/MM/yyyy").parse(date.getText().toString().trim());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                boolean gioiTinh = nam.isChecked() ? false : true;
                String cccd_2 = cccd.getText().toString().trim();

                User u = new User(
                        txtname, txtdate, gioiTinh,tell,
                        cccd_2,email.getText().toString().trim(),
                        city.getText().toString().trim(),
                        district.getText().toString().trim(),
                        ward.getText().toString().trim(),
                        address.getText().toString().trim(), null, pass );
                db.collection("Users").document(doc)
                        .set(u);
                Intent intent = new Intent(InfoUserActivity.this,HomeActivity.class);
                intent.putExtra("uid",doc);
                startActivity(intent);
            }
        });
    }

    private void uploadImage() {
        if(imageUri != null)
        {
            Log.d("TAG", "uploadImage: begin upload");
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            storageRef = storage.getReference("images/" + UUID.randomUUID());

            storageRef.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Log.d("TAG", "onSuccess: upload success" );
                            try {
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                                imgUser.setImageBitmap(bitmap);
                                String URL_Image = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                                Log.d("TAG", "URL_Image: "+ storageRef.getPath());

                            }
                            catch (IOException e)
                            {
                                e.printStackTrace();
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Log.d("TAG", "onFailure: upload fail");
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }

    private void selectedImage() {
        Intent intent = new Intent();
        intent.setType("image/");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && data != null && data.getData() != null) {
            imageUri = data.getData();
            uploadImage();

        }
    }
}
