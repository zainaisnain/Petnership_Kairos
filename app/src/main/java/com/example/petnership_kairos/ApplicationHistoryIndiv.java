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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    String applicationID, dateApplied, adopterID, adopterName,
            petID, petName, petBreed, petAge, petDescription, shelterID,
            shelterReason, shelterBizName, applicationStatus;
    String adopterEmail, adopterContact, adopterAddress;

    private TextView petNameTV, petAgeTV, petBreedTV,
            applicationStatusTV, dateAppliedTV, shelterBizNameTV, shelterReasonTV;

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
        adopterEmail = firebaseUser.getEmail();

        applicationID = getArguments().getString("applicationID");
        petID = getArguments().getString("petID");
        petName = getArguments().getString("petName");
        applicationStatus = getArguments().getString("applicationStatus");
        dateApplied = getArguments().getString("applicantDateApplied");

        petNameTV = view.findViewById(R.id.per_pet_name);
        petNameTV.setText(petName);
        petAgeTV = view.findViewById(R.id.pet_age_value);
        petBreedTV = view.findViewById(R.id.per_cat_breed);
        applicationStatusTV = view.findViewById(R.id.adoptionForm_petStatus);
        applicationStatusTV.setText(applicationStatus);
        dateAppliedTV = view.findViewById(R.id.adoptionForm_petDate);
        dateAppliedTV.setText(dateApplied);
        shelterBizNameTV = view.findViewById(R.id.adoptionForm_shelter);
        shelterReasonTV = view.findViewById(R.id.adoptionForm_shelter_reason);

        populateTextViews();
    }

    private void populateTextViews(){
        adoptersDBRef.orderByChild("email").equalTo(adopterEmail).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    adopterID = ds.getKey();
                }
                adoptersDBRef.child(adopterID).child("ApplicationHistory").child(applicationID).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        snapshot.getRef().orderByKey().equalTo("shelterReason").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists()){
                                    adoptersDBRef.child(adopterID).child("ApplicationHistory").child(applicationID).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            shelterReason = (String) snapshot.child("shelterReason").getValue();
                                            shelterReasonTV.setText(shelterReason);
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        shelterID = (String) snapshot.child("shelterID").getValue();
                        sheltersDBRef.child(shelterID).child("bizName").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                shelterBizName = (String) snapshot.getValue();
                                shelterBizNameTV.setText(shelterBizName);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ApplicationHistoryIndivViewModel.class);
        // TODO: Use the ViewModel
    }

}