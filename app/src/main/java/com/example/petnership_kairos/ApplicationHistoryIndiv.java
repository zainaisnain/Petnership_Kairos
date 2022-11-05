package com.example.petnership_kairos;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ApplicationHistoryIndiv extends Fragment {

    private ApplicationHistoryIndivViewModel mViewModel;
    ImageButton backBtnUp;
    Button backBtnDown;

    //FirebaseAuth
    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;

    DatabaseReference allPetsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("AllPets");
    DatabaseReference adoptersDBRef = FirebaseDatabase.getInstance().getReference("Adopters");
    DatabaseReference sheltersDBRef = FirebaseDatabase.getInstance().getReference("Shelters");
    DatabaseReference usersDBRef = FirebaseDatabase.getInstance().getReference("Users");

    String shelterEmail;
    String applicationID, dateApplied, timeApplied, adopterID, adopterName, adopterIntentions,
            petID, petType, petName, petBreed, petAge, petDescription, shelterID;
    String adopterEmail, adopterContact, adopterAddress;

    private TextView tvAdoptionFormDate, tvAdopterName, tvAdopterEmail,
            tvAdopterMobile, tvAdopterAddress, tvPetType, tvBreed,
            tvPetName, tvPetAge, tvPetDesc;

    public static ApplicationHistoryIndiv newInstance() {
        return new ApplicationHistoryIndiv();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_application_history_indiv, container, false);
        backBtnUp = view.findViewById(R.id.btnBack);
        backBtnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStack();
            }
        });
        backBtnDown = view.findViewById(R.id.btn_back_adopter);
        backBtnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStack();
            }
        });


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity();
        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        shelterEmail = firebaseUser.getEmail();
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ApplicationHistoryIndivViewModel.class);
        // TODO: Use the ViewModel
    }

}