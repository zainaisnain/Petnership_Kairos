package com.example.petnership_kairos;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

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

import com.example.petnership_kairos.R;
import com.example.petnership_kairos.RegisteredPetData;
import com.example.petnership_kairos.RegisteredPetsAdapter;
import com.example.petnership_kairos.ShelterHomeDashboard;
import com.example.petnership_kairos.ShelterListOfPetsFragment;
import com.example.petnership_kairos.ShelterListOfPetsViewModel;
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

public class ShelterListOfPetsFragment extends Fragment {
    DatabaseReference allPetsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("AllPets");
    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;

    private String shelterEmail, petID, petImageName;

    private ArrayList<String> petIDs = new ArrayList<>();
    private ArrayList<RegisteredPetData> ALregisteredPetData = new ArrayList<RegisteredPetData>();

    private String petName, petAge, petSex, petBreed;

    RegisteredPetData[] registeredPetData;


    private ImageButton backBtn;

    View view;
    RecyclerView recyclerView;

    private ShelterListOfPetsViewModel mViewModel;

    public static ShelterListOfPetsFragment newInstance() {
        return new ShelterListOfPetsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shelter_list_of_pets, container, false);

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        shelterEmail = firebaseUser.getEmail();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewPets);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        withFirebase(recyclerView);


        backBtn = (ImageButton) view.findViewById(R.id.btnBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                ShelterHomeDashboard shelterHomeDashboard = new ShelterHomeDashboard();
                transaction.replace(R.id.nav_host_fragment, shelterHomeDashboard);
                transaction.commit();

            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ShelterListOfPetsViewModel.class);
        // TODO: Use the ViewModel
    }
    private void withFirebase(RecyclerView recyclerView) {

        //WITH ALLPETS CHILD
        //might need to add petType to differentiate between cats and dogs for breed type
        allPetsDBRef.orderByChild("shelter").equalTo(shelterEmail)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot ds : snapshot.getChildren()) {
                                    petID = ds.getKey();
                                    petIDs.add(petID);

                                    petImageName = String.valueOf(snapshot.child(petID).child("imageName").getValue());
                                    petName = String.valueOf(snapshot.child(petID).child("petName").getValue());
                                    petAge = String.valueOf(snapshot.child(petID).child("petAge").getValue());
                                    petSex = String.valueOf(snapshot.child(petID).child("petSex").getValue());

                                    String petType = String.valueOf(snapshot.child(petID).child("petType").getValue());
                                    if(petType.equals("dog")){
                                        petBreed = String.valueOf(snapshot.child(petID).child("q10").getValue());
                                    }else if(petType.equals("cat")){
                                        petBreed = String.valueOf(snapshot.child(petID).child("q9").getValue());
                                    }

//                                    petBreed = String.valueOf(snapshot.child(petID).child("q9").getValue());
                                    ALregisteredPetData.add( new RegisteredPetData(petID, petType, petImageName, petName, petAge, petSex, petBreed));
                                }

                                registeredPetData = ALregisteredPetData.toArray(new RegisteredPetData[ALregisteredPetData.size()]);
                                RegisteredPetsAdapter registeredPetsAdapter = new RegisteredPetsAdapter(registeredPetData, ShelterListOfPetsFragment.this);
                                recyclerView.setAdapter(registeredPetsAdapter);

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
//        petsCatsDBRef.orderByChild("shelter").equalTo(shelterEmail)
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                        for(DataSnapshot ds : snapshot.getChildren()) {
//                            petID = ds.getKey();
//                            petIDs.add(petID);
//
//                            petImageName = String.valueOf(snapshot.child(petID).child("imageName").getValue());
//                            petName = String.valueOf(snapshot.child(petID).child("petName").getValue());
//                            petAge = String.valueOf(snapshot.child(petID).child("petAge").getValue());
//                            petSex = String.valueOf(snapshot.child(petID).child("petSex").getValue());
//                            petBreed = String.valueOf(snapshot.child(petID).child("q9").getValue());
////                            System.out.println(petName);
//                            ALregisteredPetData.add( new RegisteredPetData(petImageName, petName, petAge, petSex, petBreed));
//                        }
//                        registeredCatData = ALregisteredPetData.toArray(new RegisteredPetData[ALregisteredPetData.size()]);
//                        RegisteredPetsAdapter registeredPetsAdapter = new RegisteredPetsAdapter(registeredCatData, ShelterListOfPetsFragment.this);
//                        recyclerView.setAdapter(registeredPetsAdapter);
//                    }
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//
//        petsDogsDBRef.orderByChild("shelter").equalTo(shelterEmail)
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                        for(DataSnapshot ds : snapshot.getChildren()) {
//                            petID = ds.getKey();
//                            petIDs.add(petID);
//
//                            petImageName = String.valueOf(snapshot.child(petID).child("imageName").getValue());
//                            petName = String.valueOf(snapshot.child(petID).child("petName").getValue());
//                            petAge = String.valueOf(snapshot.child(petID).child("petAge").getValue());
//                            petSex = String.valueOf(snapshot.child(petID).child("petSex").getValue());
//                            petBreed = String.valueOf(snapshot.child(petID).child("q10").getValue());
//                            ALregisteredPetData.add( new RegisteredPetData(petImageName, petName, petAge, petSex, petBreed));
//                        }
//                        registeredDogData = ALregisteredPetData.toArray(new RegisteredPetData[ALregisteredPetData.size()]);
//                        RegisteredPetsAdapter registeredPetsAdapter = new RegisteredPetsAdapter(registeredDogData, ShelterListOfPetsFragment.this);
//                        recyclerView.setAdapter(registeredPetsAdapter);
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
    }
}

