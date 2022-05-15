package com.example.sskdt_app_v01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

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
import com.google.firebase.Timestamp;
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
    private String uid;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView none_item_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_health_declaration);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                uid= null;
            } else {
                uid = extras.getString("uid");
            }
        } else {
            uid= (String) savedInstanceState.getSerializable("uid");
        }

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
//        db.collection("HealthDeclarations").document("xghyhv0rRFGUxB6C6CkO")
//              .get()
//              .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
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
        none_item_list = findViewById(R.id.txt_none_decla_item);
        listView = findViewById(R.id.listLSKBYT);
        itemLSKBYTs = new ArrayList<ItemLSKBYT>();
        Task<QuerySnapshot> asd = db.collection("HealthDeclarations")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Log.d("TAG", "onComplete size: "+task.getResult().size());
                            if (task.getResult().size() > 0) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Map<String, Object> data = document.getData();
                                    Timestamp timestamp = (Timestamp) data.get("create_at");
                                    itemLSKBYTs.add(new ItemLSKBYT(document.getId() ,timestamp.toDate(), data.get("name").toString()));
                                    ListLAdapterSKBYT adapter = new ListLAdapterSKBYT(itemLSKBYTs, getApplicationContext());
                                    listView.setAdapter(adapter);

                                }
                            } else {
                                none_item_list.setText("Bạn chưa có bản khai báo nào");
                            }

                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("TAG", "onFailure: kkkkkk");
                    }
                });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("TAG", "onItemLongClick: "+itemLSKBYTs.toString());
                PopupMenu popupMenu = new PopupMenu(ListHealthDeclarationActivity.this, view);
                MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.delete_declar:
                                deleteHealtDeclar(position);
                                itemLSKBYTs.remove(position);
                                ListLAdapterSKBYT adapter = new ListLAdapterSKBYT(itemLSKBYTs, getApplicationContext());
                                listView.setAdapter(adapter);
                                if (listView.getCount() == 0) {
                                    none_item_list.setText("Bạn chưa có bản khai báo nào");
                                }
                                return true;
                            case R.id.edit_declar:
                                Intent intentEdit = new Intent(ListHealthDeclarationActivity.this , HealthDeclarationActivity.class);
                                intentEdit.putExtra("HealthDeclar", itemLSKBYTs.get(position).getId());
                                intentEdit.putExtra("editMode", true);
                                intentEdit.putExtra("uid",uid);
                                startActivity(intentEdit);
                                return true;
                            case R.id.show_detail_declar:
                                Intent intentShowDetail = new Intent(ListHealthDeclarationActivity.this , Detail_HealthActivity.class);
                                intentShowDetail.putExtra("HealthDeclar", itemLSKBYTs.get(position).getId());
                                intentShowDetail.putExtra("uid",uid);
                                startActivity(intentShowDetail);
                        }
                        return true;
                    }
                });
                return true;
            }
        });

        ConstraintLayout btn_return_lichsu = findViewById(R.id.btn_return_lichsu);
        btn_return_lichsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListHealthDeclarationActivity.this , HomeActivity.class);
                intent.putExtra("uid",uid);
                startActivity(intent);
            }
        });
    }

    private void deleteHealtDeclar(int i) {
        db.collection("HealthDeclarations")
                .document(itemLSKBYTs.get(i).getId())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                });
    }
}