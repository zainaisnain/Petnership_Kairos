package com.example.petnership_kairos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class UpdateEmail extends AppCompatActivity {
    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;
    private ProgressBar progressBar;
    private String userOldEmail, userNewEmail, userPwd;
    private Button updateEmailBtn;
    private EditText newEmailET, pwdET;
    private TextView authenticateInfoTV;
    private String username = "";

    DatabaseReference usersDBRef = FirebaseDatabase.getInstance().getReference("Users");
    DatabaseReference adoptersDBRef = FirebaseDatabase.getInstance().getReference("Adopters");
    DatabaseReference sheltersDBRef = FirebaseDatabase.getInstance().getReference("Shelters");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_email);

        pwdET = findViewById(R.id.update_email_pass_et);
        newEmailET = findViewById(R.id.update_new_email_et);
        authenticateInfoTV = findViewById(R.id.authenticate_info_tv);
        updateEmailBtn = findViewById(R.id.update_email_proceed_btn);

        Button btnBack = findViewById(R.id.update_email_back_btn);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
//                MyCancelDialogEmail shelterHomeDashboard = new MyCancelDialogEmail();
//                transaction.replace(R.id.user_change_email,shelterHomeDashboard);
//                transaction.addToBackStack("Adopter Home");
//                transaction.commit();
                MyCancelDialogEmail myCancelDialogEmail = new MyCancelDialogEmail();
                myCancelDialogEmail.show(getSupportFragmentManager(), "My Fragment");
            }
        });

        updateEmailBtn.setEnabled(false);
        newEmailET.setEnabled(false);

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();

        //Set old email on TV
        userOldEmail = firebaseUser.getEmail();
        TextView oldEmailTV = findViewById(R.id.update_current_email_tv);
        oldEmailTV.setText(userOldEmail);

        if(firebaseUser.equals("")){
            Toast.makeText(UpdateEmail.this, "User details not available", Toast.LENGTH_LONG);
        }else{
            reAuthenticate(firebaseUser);
        }
    }

    private void reAuthenticate(FirebaseUser firebaseUser) {
        Button authenticateBtn = findViewById(R.id.authenticate_update_btn);
        authenticateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userPwd = pwdET.getText().toString().trim();

                if(TextUtils.isEmpty(userPwd)){
                    Toast.makeText(UpdateEmail.this, "Please enter your password.", Toast.LENGTH_LONG);
                    pwdET.setError("Please enter your password.");
                    pwdET.requestFocus();
                }else{
                    AuthCredential credential = EmailAuthProvider.getCredential(userOldEmail, userPwd);

                    firebaseUser.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task){
                            if(task.isSuccessful()){
                                {
                                    EmailAuthenticatedDialog emailAuthenticatedDialog = new EmailAuthenticatedDialog();
                                    emailAuthenticatedDialog.show(getSupportFragmentManager(), "My Fragment");
//                                    Toast.makeText(UpdateEmail.this, "Password has been verified." +
//                                            "You can enter new desired email now.", Toast.LENGTH_LONG).show();

                                    authenticateInfoTV.setText("You are now authenticated. Please proceed to set your new email.");

                                    newEmailET.setEnabled(true);
                                    pwdET.setEnabled(false);
                                    authenticateBtn.setEnabled(false);
                                    updateEmailBtn.setEnabled(true);

                                    updateEmailBtn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            userNewEmail = newEmailET.getText().toString().trim();
                                            if(TextUtils.isEmpty(userNewEmail)){
                                                newEmailET.setError("Please enter your new email.");
                                                newEmailET.requestFocus();
                                            }else if(!Patterns.EMAIL_ADDRESS.matcher(userNewEmail).matches()){
                                                newEmailET.setError("Please enter a valid email.");
                                                newEmailET.requestFocus();
                                            }else if(userOldEmail.matches(userNewEmail)) {
                                                newEmailET.setError("Please enter new email.");
                                                newEmailET.requestFocus();
                                            }else{
//                                                progressBar.setVisibility(View.VISIBLE);
                                                updateEmail(firebaseUser);
                                            }
                                        }
                                    });
                                }
                            }else{
                                try{
                                    throw task.getException();
                                }catch (Exception e){
                                    Toast.makeText(UpdateEmail.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }
            }
        });
    }

    private void updateEmail(FirebaseUser firebaseUser) {
        updateEmailBtn = findViewById(R.id.update_email_proceed_btn);
//        updateEmailBtn.setEnabled(false);
        updateEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseUser.updateEmail(userNewEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isComplete()){
                            //Verify email
                            firebaseUser.sendEmailVerification();
                            updateDB();
                            EmailChangeSavedDialog emailChangeSavedDialog = new EmailChangeSavedDialog();
                            emailChangeSavedDialog.show(getSupportFragmentManager(), "My Fragment");
//                            Toast.makeText(UpdateEmail.this, "Email has been updated.",Toast.LENGTH_SHORT).show();
                        }else{
                            try{
                                throw task.getException();
                            }catch (Exception e){
                                Toast.makeText(UpdateEmail.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });
            }
        });

    }

    private void updateDB(){
        usersDBRef.orderByChild("email").equalTo(userOldEmail).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    username = ds.getKey();
                }
                System.out.println("Username --- " + username);
                usersDBRef.child(username).child("email").setValue(userNewEmail);

                //check if user exists in adopters table,
                //if yes, update that email
                adoptersDBRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(username)){
                            snapshot.child(username).child("email").getRef().setValue(userNewEmail);
                        }else{
                            System.out.println("no child in ADOPTERS.... ");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                sheltersDBRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(username)){
                            snapshot.child(username).child("email").getRef().setValue(userNewEmail);
                        }else{
                            System.out.println("no child in SHELTER.... ");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}