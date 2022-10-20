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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityUserType extends AppCompatActivity {

    private ImageButton backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_user_type);

        ImageButton adopterRegister = findViewById(R.id.adopterbutton);
        adopterRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adopterRegistration();
            }
        });

        ImageButton shelterRegister = findViewById(R.id.shelterbutton);
        shelterRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shelterRegistration();
            }
        });

        backBtn = (ImageButton) findViewById(R.id.btnBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityUserType.this,LoginActivity.class);
                startActivity(intent);

            }
        });
//
//        //redirect to login page
//        TextView returnLogin = findViewById(R.id.retLoginBtn);
//        returnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switchToLogin();
//            }
//        });

    }

    private void adopterRegistration()
    {
        Intent intent = new Intent(this,AdopterRegistration.class);
        startActivity(intent);
        finish();
    }

    private void shelterRegistration()
    {
        Intent intent = new Intent(this,ShelterRegistration.class);
        startActivity(intent);
        finish();
    }

}