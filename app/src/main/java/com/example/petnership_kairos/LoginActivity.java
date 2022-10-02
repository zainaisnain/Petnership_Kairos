package com.example.petnership_kairos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginv2);

        mAuth=FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null)
        {
            finish();
            return;
        }

        Button signBtn = findViewById(R.id.signupbutton);
        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToRegister();
            }
        });

        Button loginBtn = findViewById(R.id.loginbutton);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticateUser();
            }
        });
    }

    private void authenticateUser()
    {
        EditText etUserEmail = findViewById(R.id.authUserEmail);
        EditText etPassword = findViewById(R.id.authPassword);

        //fetch input values

        String useremail = etUserEmail.getText().toString();
        String password = etPassword.getText().toString();

        if(useremail.isEmpty() || password.isEmpty())
        {
            Toast.makeText(this,"Please fill all fields", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(useremail,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    //success redirect to dashboard
                    showMainActivity();
                }
                else
                {
                    //failed login
                    Toast.makeText(LoginActivity.this,"Authentication Failed.",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void showMainActivity()
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void switchToRegister()
    {
        Intent intent = new Intent(this,ActivityUserType.class);
        startActivity(intent);
        finish();
    }
}