package com.example.petnership_kairos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserVerifyEmailDialog extends AppCompatActivity {

    private Button userVerifyBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_verify_email);

        userVerifyBackBtn = findViewById(R.id.user_verify_dialog_home_btn);
        userVerifyBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserVerifyEmailDialog.this, LoginActivity.class));
            }
        });
    }
}