package com.example.sskdt_app_v01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.sskdt_app_v01.adapter.GridAdapterHome;
import com.example.sskdt_app_v01.adapter.ListLAdapterSKBYT;
import com.example.sskdt_app_v01.item.ItemHome;
import com.example.sskdt_app_v01.item.ItemLSKBYT;
import com.example.sskdt_app_v01.model.HealthDeclaration;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ListHealthDeclarationActivity extends AppCompatActivity {

    List<ItemLSKBYT> itemLSKBYTs;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_health_declaration);

//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        HealthDeclaration healthDeclaration = new HealthDeclaration("2GscXtFxm7Z1C3UvjaER", "2GscXtFxm7Z1C3UvjaER", new Date(), true, false, true,false,true);
//        db.collection("HealthDeclarations") //add
//                .add(healthDeclaration)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w("TAG", "Error adding document", e);
//                    }
//                });
//          //getList
//        db.collection("HealthDeclarations")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d("TAG", document.getId() + " => " + document.getData());
//                            }
//                        } else {
//                            Log.w("TAG", "Error getting documents.", task.getException());
//                        }
//                    }
//                });
//          //get by ducument
//        db.collection("HealthDeclarations").document("xghyhv0rRFGUxB6C6CkO").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
////                SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
////                sfd.format();
////                HealthDeclaration h1 = (HealthDeclaration) documentSnapshot.getData().get(HealthDeclaration.class);
//                Log.d("TAG", "onSuccess: "+ documentSnapshot.getDate("timestamp").getHours());
//            }
//        });
//          //update
//        db.collection("HealthDeclarations")
//                .document("xghyhv0rRFGUxB6C6CkO")
//                .set(new HealthDeclaration("111111", new Date()));

// delete tương tự update chuyển .set thành .delete

//        //filter
//        db.collection("HealthDeclarations").whereEqualTo("nameUser", "asdasd").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if(task.isSuccessful()){
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        Log.d("TAG", "onComplete: "+ document.getId());
//                    }
//
//                }
//            }
//        });

        listView = findViewById(R.id.listLSKBYT);
        itemLSKBYTs = new ArrayList<ItemLSKBYT>();
        itemLSKBYTs.add(new ItemLSKBYT(new Date(), "Nguyen Nhat Quang"));


        ListLAdapterSKBYT adapter = new ListLAdapterSKBYT(itemLSKBYTs, this);
        listView.setAdapter(adapter);

        ConstraintLayout btn_return_lichsu = findViewById(R.id.btn_return_lichsu);
        btn_return_lichsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListHealthDeclarationActivity.this , HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}