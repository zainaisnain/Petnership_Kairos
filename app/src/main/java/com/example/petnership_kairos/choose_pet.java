package com.example.petnership_kairos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.FirebaseDatabase;

public class choose_pet extends AppCompatActivity {

    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosepet);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ImageButton dogRegister = findViewById(R.id.dogButton);
        dogRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogRegistration();
            }
        });

        ImageButton catRegister = findViewById(R.id.catButton);
        catRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catRegistration();
            }
        });
    }

    public void dogRegistration() {
        Intent intent = new Intent(this,activity_dogprofilequestionnaire.class);
        startActivity(intent);
        finish();
    }

    public void catRegistration() {
        Intent intent = new Intent(this,activity_catprofilequestionnaire.class);
        startActivity(intent);
        finish();
    }

}