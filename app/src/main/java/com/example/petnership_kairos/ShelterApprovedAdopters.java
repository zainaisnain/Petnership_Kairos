package com.example.petnership_kairos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class  ShelterApprovedAdopters extends Fragment {
    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;
    DatabaseReference usersDBRef = FirebaseDatabase.getInstance().getReference("Users");
    DatabaseReference adoptersDBRef = FirebaseDatabase.getInstance().getReference("Adopters");
    DatabaseReference sheltersDBRef = FirebaseDatabase.getInstance().getReference("Shelters");
    String shelterID, shelterEmail, adopterID, adopterEmail, adopterName, petID, petName, applicationID, dateApplied, applicationStatus;

    ShelterApprovedAdoptersData[] shelterApprovedAdoptersData;
    private ArrayList<ShelterApprovedAdoptersData> ALShelterApprovedAdoptersData = new ArrayList<ShelterApprovedAdoptersData>();

    private ImageButton backBtn;

    public ShelterApprovedAdopters newInstance() {
        // Required empty public constructor
        return new ShelterApprovedAdopters();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        shelterEmail = firebaseUser.getEmail();

        backBtn = view.findViewById(R.id.btnBack);
        backBtn.setOnClickListener(view1 -> getParentFragmentManager().popBackStack());

        System.out.println("Entered ShelterApprovedAdopters");

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewApprovedAdopters);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getFromDB(recyclerView);
    }

    private void getFromDB(RecyclerView recyclerView){
        sheltersDBRef.orderByChild("email").equalTo(shelterEmail).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot sheltersSnapshot) {
                if(sheltersSnapshot.exists()){
                    for(DataSnapshot ds : sheltersSnapshot.getChildren()) {
                        shelterID = ds.getKey();
                    }

                    if(sheltersSnapshot.child(shelterID).hasChild("ApprovedAdopters")){
                        sheltersDBRef.child(shelterID).child("ApprovedAdopters").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot ds : snapshot.getChildren()) {
                                    applicationID = ds.getKey();
                                    System.out.println("inside heloo == " + applicationID);
                                    adopterID = (String) snapshot.child(applicationID).child("adopterID").getValue();
                                    System.out.println("inside ShelterApprovedAdopters AdopterID == " + adopterID);
                                    adopterName = (String) snapshot.child(applicationID).child("adopterName").getValue();
                                    System.out.println("adopterName ApplicantsReviewFrag getFromDB == " + adopterName);
                                    petID = (String) snapshot.child(applicationID).child("petID").getValue();
                                    petName = (String) snapshot.child(applicationID).child("petName").getValue();
                                    applicationStatus = (String) snapshot.child(applicationID).child("applicationStatus").getValue();
                                    dateApplied = (String) snapshot.child(applicationID).child("dateApplied").getValue();

                                    ALShelterApprovedAdoptersData.add( new ShelterApprovedAdoptersData(applicationID, adopterID, adopterName, petID, petName, applicationStatus, dateApplied));
                                }

                                shelterApprovedAdoptersData = ALShelterApprovedAdoptersData.toArray(new ShelterApprovedAdoptersData[ALShelterApprovedAdoptersData.size()]);
                                ShelterApprovedAdoptersAdapter shelterApprovedAdoptersAdapter = new ShelterApprovedAdoptersAdapter(shelterApprovedAdoptersData,ShelterApprovedAdopters.this);
                                recyclerView.setAdapter(shelterApprovedAdoptersAdapter);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shelter_approved_adopters, container, false);
    }

}