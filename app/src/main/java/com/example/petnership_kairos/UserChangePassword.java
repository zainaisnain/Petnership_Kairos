package com.example.petnership_kairos;

import static android.service.controls.ControlsProviderService.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserChangePassword extends Fragment {

    private EditText currentPasswordET, newPasswordET, confirmNewPasswordET;
    private String currentPassword, newPassword, confirmNewPassword;

    private Button changePasswordBtn;
    private ImageButton backBtn;

    public UserChangePassword() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        System.out.println("user  === " + user);

        currentPasswordET = view.findViewById(R.id.user_current_password_et);
        newPasswordET = view.findViewById(R.id.user_new_password_et);
        confirmNewPasswordET = view.findViewById(R.id.user_confirm_new_password_et);
        backBtn = view.findViewById(R.id.btnBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyCancelDialogEdit cancelDialog2 = new MyCancelDialogEdit();
                cancelDialog2.show(getParentFragmentManager(), "My Fragment");
            }
        });
        changePasswordBtn = view.findViewById(R.id.user_change_password_btn);
        changePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPassword = currentPasswordET.getText().toString().trim();
                newPassword = newPasswordET.getText().toString().trim();
                confirmNewPassword = confirmNewPasswordET.getText().toString().trim();
                System.out.println("currentPassword == " + currentPassword);
                System.out.println("newPassword == " + newPassword);
                System.out.println("confirmNewPassword == " + confirmNewPassword);

                AuthCredential credential = EmailAuthProvider
                        .getCredential(user.getEmail(), currentPassword);

                user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            if(currentPassword.equals(newPassword)){
                                newPasswordET.setError("New password can't match current password.");
                                newPasswordET.requestFocus();
                                return;
                            }else if(newPassword.isEmpty()){
                                newPasswordET.setError("Password is Required.");
                                newPasswordET.requestFocus();
                                return;
                            } else if (confirmNewPassword.isEmpty()) {
                                confirmNewPasswordET.setError("Confirm Password is Required.");
                                confirmNewPasswordET.requestFocus();
                                return;
                            } else if (newPassword.length() < 6) {
                                newPasswordET.setError("Password should be at least 6 characters.");
                                newPasswordET.requestFocus();
                                return;
                            } else if (confirmNewPassword.length() < 6) {
                                confirmNewPasswordET.setError("Password should be at least 6 characters.");
                                confirmNewPasswordET.requestFocus();
                                return;
                            } else if (!newPassword.equals(confirmNewPassword)){
                                newPasswordET.setError("Password do not match.");
                                newPasswordET.requestFocus();
                                confirmNewPasswordET.setError("Passwords do not match.");
                                confirmNewPasswordET.requestFocus();
                                return;
                            } else if(newPassword.equals(confirmNewPassword)) {
                                user.updatePassword(newPassword)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    //TODO: INSERT DIALOG HERE
                                                    Log.d(TAG, "User password updated.");
                                                }
                                            }
                                        });
                            }
                        }else {
                            currentPasswordET.setError("Incorrect Current Password");
                            currentPasswordET.requestFocus();
                            return;
                        }
                    }
                });
            }
        });
    }

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_change_password, container, false);
    }
}