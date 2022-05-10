package com.example.sskdt_app_v01;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sskdt_app_v01.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class VertifyActivity extends AppCompatActivity {
    private PhoneAuthProvider.ForceResendingToken token;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    private String vertifiedId;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vertify);
        firebaseAuth = FirebaseAuth.getInstance();

        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                signInWithAuthCredential(phoneAuthCredential);
                Log.d("TAG", "onVerificationCompleted: sucess");
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Log.d("TAG", "onVerificationFailed: fail");
            }

            @Override
            public void onCodeSent(@NonNull String verityId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(verityId, forceResendingToken);
                vertifiedId = verityId;
                token = forceResendingToken;
            }

            @Override
            public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
                super.onCodeAutoRetrievalTimeOut(s);
                vertifiedId = s;
            }
        };

        String phone;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                phone= null;
            } else {
                phone= extras.getString("phoneRegister");
            }
        } else {
            phone= (String) savedInstanceState.getSerializable("phoneRegister");
        }

        phoneVertification(phone);
        Button btnVerity = findViewById(R.id.btn_verity);
        EditText edtVerity = findViewById(R.id.verity_code);
        TextView note = findViewById(R.id.txt_vertify_note);
        btnVerity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Pattern.matches("[0-9]{6}",edtVerity.getText().toString())){
                    note.setText("* Mã xác thực không hợp lệ");
                }else{
                    note.setText("");
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(vertifiedId, edtVerity.getText().toString());
                    signInWithAuthCredential(credential);
                }
            }
        });
        TextView txtResendVerity = findViewById(R.id.txt_resend);

        txtResendVerity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resendPhoneVertification(phone, token);
            }
        });
    }

    private void signInWithAuthCredential(PhoneAuthCredential phoneAuthCredential) {
        firebaseAuth.signInWithCredential(phoneAuthCredential).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                Log.d("TAG", "onSuccess: is login");
                Bundle extras = getIntent().getExtras();
                User user = new User(extras.getString("nameRegister"), extras.getString("phoneRegister"), extras.getString("passwordRegister"));
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("Users") //add
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                                Intent intent = new Intent(VertifyActivity.this, HomeActivity.class);
                                intent.putExtra("uid", documentReference.getId());
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

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("TAG", "onFailure: "+ e);
            }
        });
    }

    private void resendPhoneVertification(String phone, PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(phone)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(callbacks)
                        .setForceResendingToken(token)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }


    private void phoneVertification(String phone) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(phone)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(callbacks)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
}

