package com.example.petnership_kairos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class ShelterListOfDogs extends AppCompatActivity {

    DatabaseReference petsDogsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("Dogs");
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;

    private String shelterEmail, petID, petImageName;

    private ArrayList<String> petIDs = new ArrayList<>();
    private ArrayList<RegisteredDogData> ALregisteredDogData = new ArrayList<RegisteredDogData>();

    private String petName, petAge, petSex, petBreed;

    RegisteredDogData[] registeredDogData;

    private ImageButton backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_list_of_dogs);

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        shelterEmail = firebaseUser.getEmail();
        withFirebase();

        backBtn = (ImageButton) findViewById(R.id.btnBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShelterListOfDogs.this,ShelterHomeDashboard.class);
                startActivity(intent);

            }
        });
    }

    private void withFirebase() {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewDogs);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        petsDogsDBRef.orderByChild("shelter").equalTo(shelterEmail)
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
                            petBreed = String.valueOf(snapshot.child(petID).child("q10").getValue());
                            ALregisteredDogData.add( new RegisteredDogData(petID, petImageName, petName, petAge, petSex, petBreed));
                        }
                        registeredDogData = ALregisteredDogData.toArray(new RegisteredDogData[ALregisteredDogData.size()]);
//                        for (RegisteredDogData element: registeredDogData) {
//                            System.out.println("zaina: "+element.getImageName());
//                        }

                       RegisteredDogsAdapter registeredDogsAdapter = new RegisteredDogsAdapter(registeredDogData, ShelterListOfDogs.this);
                       recyclerView.setAdapter(registeredDogsAdapter);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}