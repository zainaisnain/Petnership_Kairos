package com.example.petnership_kairos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class AdoptionForm extends Fragment implements View.OnClickListener{

    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;
    private String adopterEmail, adopterUsername;

    DatabaseReference usersDBRef = FirebaseDatabase.getInstance().getReference("Users");
    DatabaseReference adoptersDBRef = FirebaseDatabase.getInstance().getReference("Adopters");
    AppCompatRadioButton rbDog, rbCat;
    Button submitForm, cancelForm;

    private AdoptionFormViewModel mViewModel;

    public static AdoptionForm newInstance() {
        return new AdoptionForm();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_adoption_form, container, false);

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        adopterEmail = firebaseUser.getEmail();

        rbDog = view.findViewById(R.id.dogClicked);
        rbCat = view.findViewById(R.id.catClicked);

        submitForm = view.findViewById(R.id.btn_submit_adopter);
        submitForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyCustomDialog submitDialog = new MyCustomDialog();
                submitDialog.show(getParentFragmentManager(), "My Fragment");
            }
        });

        cancelForm = view.findViewById(R.id.adopterRegisterCancel);
        cancelForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyCancelDialog cancelDialog = new MyCancelDialog();
                cancelDialog.show(getParentFragmentManager(), "My Fragment");
            }
        });


        rbDog.setOnClickListener(this);
        rbCat.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Adoption Form");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dogClicked:
                rbDog.setTextColor(Color.BLACK);
                rbCat.setTextColor(Color.WHITE);
                break;
            case R.id.catClicked:
                rbCat.setTextColor(Color.BLACK);
                rbDog.setTextColor(Color.WHITE);
                break;
        }
    }

    public void onRadioButtonClicked (View view) {
        boolean isSelected = ((AppCompatRadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.dogClicked:
                if (isSelected) {
                    rbDog.setTextColor(Color.BLACK);
                }
                break;
            case R.id.catClicked:
                if (isSelected) {
                    rbCat.setTextColor(Color.BLACK);
                }
                break;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AdoptionFormViewModel.class);
        // TODO: Use the ViewModel
    }


}