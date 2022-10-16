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


public class ShelterListOfCats extends AppCompatActivity {

    DatabaseReference petsCatsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("Cats");
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;

    private String shelterEmail, petID, petImageName;

    private ArrayList<String> petIDs = new ArrayList<>();
    private ArrayList<RegisteredCatData> ALregisteredCatData = new ArrayList<RegisteredCatData>();

    private String petName, petAge, petSex, petBreed;

    RegisteredCatData[] registeredCatData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_list_of_cats);

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        shelterEmail = firebaseUser.getEmail();
        withFirebase();

    }

    private void withFirebase() {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewCats);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        petsCatsDBRef.orderByChild("shelter").equalTo(shelterEmail)
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
                            petBreed = String.valueOf(snapshot.child(petID).child("q9").getValue());
                            ALregisteredCatData.add( new RegisteredCatData(petID,petImageName, petName, petAge, petSex, petBreed));
                        }
                        registeredCatData = ALregisteredCatData.toArray(new RegisteredCatData[ALregisteredCatData.size()]);
                        System.out.println("size nya: " + registeredCatData.length);
                        for (RegisteredCatData element: registeredCatData) {
                            System.out.println("zaina: "+element.getImageName());
                        }
                        RegisteredCatsAdapter registeredCatsAdapter = new RegisteredCatsAdapter(registeredCatData, ShelterListOfCats.this);
                        recyclerView.setAdapter(registeredCatsAdapter);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}