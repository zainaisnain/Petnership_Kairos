package com.example.petnership_kairos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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

public class ShelterListOfPets extends AppCompatActivity {

    DatabaseReference petsDogsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("Dogs");
    DatabaseReference petsCatsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("Cats");
    DatabaseReference petsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets");
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;

    private String shelterEmail, petID, petImageName;

    private ArrayList<String> petIDs = new ArrayList<>();
    private ArrayList<RegisteredPetData> ALregisteredPetData = new ArrayList<RegisteredPetData>();

    private String petName, petAge, petSex, petBreed;

    RegisteredPetData[] registeredPetData;
    RegisteredPetData[] registeredCatData;
    RegisteredPetData[] registeredDogData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_list_of_pets);

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        shelterEmail = firebaseUser.getEmail();
        withFirebase();

    }

    private void withFirebase() {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewPets);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        petsCatsDBRef.orderByChild("shelter").equalTo(shelterEmail)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for(DataSnapshot ds : snapshot.getChildren()) {
                            petID = ds.getKey();
                            petIDs.add(petID);

                            petImageName = String.valueOf(snapshot.child(petID).child("petImage").getValue());
                            petName = String.valueOf(snapshot.child(petID).child("petName").getValue());
                            petAge = String.valueOf(snapshot.child(petID).child("petAge").getValue());
                            petSex = String.valueOf(snapshot.child(petID).child("petSex").getValue());
                            petBreed = String.valueOf(snapshot.child(petID).child("q9").getValue());
//                            System.out.println(petName);
                            ALregisteredPetData.add( new RegisteredPetData(petImageName, petName, petAge, petSex, petBreed));
                        }
                        registeredCatData = ALregisteredPetData.toArray(new RegisteredPetData[ALregisteredPetData.size()]);
//                        RegisteredPetsAdapter registeredPetsAdapter = new RegisteredPetsAdapter(registeredPetData, ShelterListOfPets.this);
//                        recyclerView.setAdapter(registeredPetsAdapter);
//                        for (RegisteredPetData element: registeredCatData) {
//                            System.out.println("zaina: "+element.getPetImageName());
//                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

//        petsDogsDBRef.orderByChild("shelter").equalTo(shelterEmail)
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                        for(DataSnapshot ds : snapshot.getChildren()) {
//                            petID = ds.getKey();
//                            petIDs.add(petID);
//
//                            petImageName = String.valueOf(snapshot.child(petID).child("petImage").getValue());
//                            petName = String.valueOf(snapshot.child(petID).child("petName").getValue());
//                            petAge = String.valueOf(snapshot.child(petID).child("petAge").getValue());
//                            petSex = String.valueOf(snapshot.child(petID).child("petSex").getValue());
//                            petBreed = String.valueOf(snapshot.child(petID).child("q10").getValue());
//                            ALregisteredPetData.add( new RegisteredPetData(petImageName, petName, petAge, petSex, petBreed));
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });

//        registeredPetData = ALregisteredPetData.toArray(new RegisteredPetData[ALregisteredPetData.size()]);
//        for (RegisteredPetData element: registeredPetData) {
//            System.out.println("zaina: "+element.getPetImageName());
//        }
//        RegisteredPetsAdapter registeredPetsAdapter = new RegisteredPetsAdapter(registeredPetData, ShelterListOfPets.this);
//        recyclerView.setAdapter(registeredPetsAdapter);
    }
}