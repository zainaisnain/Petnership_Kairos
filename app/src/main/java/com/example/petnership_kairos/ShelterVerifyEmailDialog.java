package com.example.petnership_kairos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ShelterVerifyEmailDialog extends AppCompatActivity {

    private Button shelterVerifyBackBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_verify_email_dialog);

        shelterVerifyBackBtn = findViewById(R.id.shelter_verify_dialog_home_btn);
        shelterVerifyBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShelterVerifyEmailDialog.this, LoginActivity.class));
            }
        });
    }
}