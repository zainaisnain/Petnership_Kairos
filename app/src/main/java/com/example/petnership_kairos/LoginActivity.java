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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText etUserEmail, etUserPassword;
    private String username = "";
    private Button loginBtn, signUpBtn;
    private String email, password;
    private TextView forgotPwdBtn;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginv2);

        etUserEmail =findViewById(R.id.loginusername);
        etUserPassword =findViewById(R.id.loginpassword);


        mAuth=FirebaseAuth.getInstance();

        forgotPwdBtn = findViewById(R.id.loginforgot);
        forgotPwdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgotPassword.class));
            }
        });

        loginBtn = findViewById(R.id.loginbutton);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticateUser();
            }
        });

        signUpBtn = findViewById(R.id.signupbutton);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToRegister();
            }
        });


    }

    private void authenticateUser()
    {

        //fetch input values
        email = etUserEmail.getText().toString();
        password = etUserPassword.getText().toString();

        if(email.isEmpty() || password.isEmpty())
        {
            Toast.makeText(this,"Please fill all fields", Toast.LENGTH_LONG).show();
            return;
        }

        databaseReference.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //first locate the parent (in this case, also the username) of the email
                for(DataSnapshot ds : snapshot.getChildren()) {
                    username = ds.getKey();
                }

                //sign in
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            if (mAuth.getCurrentUser().isEmailVerified()){
                                databaseReference.child(username).child("userType")
                                        .addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                String userType = snapshot.getValue(String.class);

                                                if(userType.equals("adopter")){
                                                    startActivity(new Intent(LoginActivity.this, AdopterDashboard.class));
                                                }else if(userType.equals("shelter")) {
                                                    startActivity(new Intent(LoginActivity.this, ShelterDashboard.class));
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                                System.out.println("on cancelled");
                                            }
                                        });
                            }else{
                                startActivity(new Intent(LoginActivity.this, UserVerifyEmailDialog.class));
                            }
                        }
                        else
                        {
                            //failed login
                            Toast.makeText(LoginActivity.this,"Authentication Failed.",Toast.LENGTH_LONG).show();
                        }
                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }

    private void showMainActivity()
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void switchToRegister()
    {
        Intent intent = new Intent(this,ActivityUserType.class);
        startActivity(intent);
        finish();
    }

}