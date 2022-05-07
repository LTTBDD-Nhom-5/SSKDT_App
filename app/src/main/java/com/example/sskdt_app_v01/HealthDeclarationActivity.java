package com.example.sskdt_app_v01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class HealthDeclarationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_declaration);
        ConstraintLayout btn_return_healthdeclaraton = findViewById(R.id.btn_return_healthdeclaraton);
        btn_return_healthdeclaraton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HealthDeclarationActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
