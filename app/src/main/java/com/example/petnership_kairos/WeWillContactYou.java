package com.example.petnership_kairos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WeWillContactYou extends AppCompatActivity {
    Button homeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_we_will_contact_you);
        homeBtn = findViewById(R.id.user_will_contact_dialog_home_btn);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WeWillContactYou.this, LoginActivity.class));
            }
        });
    }
}