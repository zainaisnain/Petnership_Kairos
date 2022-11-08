package com.example.petnership_kairos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class UserVerifyEmailDialog extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button userVerifyBackBtn, resendVerificationBtn, btnOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_verify_email);
        mAuth = FirebaseAuth.getInstance();
        userVerifyBackBtn = findViewById(R.id.user_verify_dialog_home_btn);
        userVerifyBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserVerifyEmailDialog.this, LoginActivity.class));
            }
        });

        resendVerificationBtn = findViewById(R.id.user_resend_verify_btn);
        final Dialog dialogResentVerification  = new Dialog(this);
        resendVerificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            //Dialog
                            dialogResentVerification.setContentView(R.layout.activity_dialog_resent_verification);
                            dialogResentVerification.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            dialogResentVerification.show();

                            btnOK =  (Button) dialogResentVerification.findViewById(R.id.resent_buttonOk);
                            btnOK.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    startActivity(new Intent(UserVerifyEmailDialog.this, LoginActivity.class));
                                }
                            });
                        }

                    }
                });
            }
        });
    }
}