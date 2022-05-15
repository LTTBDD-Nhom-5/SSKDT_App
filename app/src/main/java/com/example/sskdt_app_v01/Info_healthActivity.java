package com.example.sskdt_app_v01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Info_healthActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_health);

        Bundle bundle = getIntent().getExtras();
        String doc =  bundle.getString("uid");

        ConstraintLayout btn_return_info = findViewById(R.id.btn_return_info);
        btn_return_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Info_healthActivity.this,ListHealthDeclarationActivity.class);
                intent.putExtra("uid",doc);
                startActivity(intent);
            }
        });
        
    }
}
`