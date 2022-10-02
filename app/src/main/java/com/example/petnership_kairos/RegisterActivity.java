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

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        //check if user is logged in
        mAuth=FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null)
        {
            finish();
            return;
        }

        Button register = findViewById(R.id.btnRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        //redirect to login page
        TextView returnLogin = findViewById(R.id.retLoginBtn);
        returnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToLogin();
            }
        });

    }

    //function to capture all user input fields
    public void registerUser()
    {
        EditText etUsername = findViewById(R.id.inputUsername);
        EditText etEmail = findViewById(R.id.inputEmail);
        EditText etPassword = findViewById(R.id.inputPassword);
        EditText etConfirmPassword = findViewById(R.id.inputConfirmPassword);

        //fetch input values
        String username = etUsername.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (username.isEmpty()||email.isEmpty()||password.isEmpty())
        {
            Toast.makeText(RegisterActivity.this,"Please fill all fields.",Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    //success registration insert to firebase database
                    User user = new User(username,email);
                    FirebaseDatabase.getInstance().getReference("users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    switchToLogin();
                                }
                            });
                }
                else
                {
                    //failed login
                    Toast.makeText(RegisterActivity.this,"Authentication Failed.",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

//    private void showMainActivity()
//    {
//        Intent intent = new Intent(this,MainActivity.class);
//        startActivity(intent);
//        finish();
//    }

    private void switchToLogin()
    {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

}