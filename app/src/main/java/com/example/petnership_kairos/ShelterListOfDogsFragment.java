package com.example.petnership_kairos;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.content.Intent;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ShelterListOfDogsFragment extends Fragment {

    private ShelterListOfDogsViewModel mViewModel;
    DatabaseReference petsDogsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("Dogs");
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;
    RecyclerView recyclerView;
    View view;
    private String shelterEmail, petID, petImageName;

    private ArrayList<String> petIDs = new ArrayList<>();
    private ArrayList<RegisteredDogData> ALregisteredDogData = new ArrayList<RegisteredDogData>();

    private String petName, petAge, petSex, petBreed;

    RegisteredDogData[] registeredDogData;

    private ImageButton backBtn;

    DatabaseReference usersDBRef = FirebaseDatabase.getInstance().getReference("Users");
    private String shelterID;
    public static ShelterListOfDogsFragment newInstance() {
        return new ShelterListOfDogsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shelter_list_of_dogs, container, false);
        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        shelterEmail = firebaseUser.getEmail();
        recyclerView = view.findViewById(R.id.recyclerViewDogs);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        withFirebase(recyclerView);

        backBtn = view.findViewById(R.id.btnBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStack();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ShelterListOfDogsViewModel.class);
        // TODO: Use the ViewModel
    }

    private void withFirebase(RecyclerView recyclerView) {

        usersDBRef.orderByChild("email").equalTo(shelterEmail).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    shelterID = ds.getKey();
                }

                petsDogsDBRef.orderByChild("shelter").equalTo(shelterID)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot ds : snapshot.getChildren()) {
                                    petID = ds.getKey();
                                    petIDs.add(petID);

                                    petImageName = String.valueOf(snapshot.child(petID).child("imageName").getValue());
                                    petName = String.valueOf(snapshot.child(petID).child("petName").getValue());
                                    petAge = String.valueOf(snapshot.child(petID).child("petAgeNum").getValue());
                                    petSex = String.valueOf(snapshot.child(petID).child("petSex").getValue());
                                    petBreed = String.valueOf(snapshot.child(petID).child("q10").getValue());
                                    ALregisteredDogData.add( new RegisteredDogData(petID, petImageName, petName, petAge, petSex, petBreed));
                                }
                                registeredDogData = ALregisteredDogData.toArray(new RegisteredDogData[ALregisteredDogData.size()]);
//                        for (RegisteredDogData element: registeredDogData) {
//                            System.out.println("zaina: "+element.getImageName());
//                        }

                                RegisteredDogsAdapter registeredDogsAdapter = new RegisteredDogsAdapter(registeredDogData, ShelterListOfDogsFragment.this);
                                recyclerView.setAdapter(registeredDogsAdapter);

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