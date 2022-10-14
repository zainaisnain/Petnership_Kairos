package com.example.petnership_kairos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class PerDogProfile extends AppCompatActivity {

    private String petID, petImageName, petName, petBreed, petAge, petSex, petDescription;
    private TextView tvPetTitle, tvPetName, tvPetBreed, tvPetAge, tvPetSex, tvPetDescription;
    private ImageView ivPetImage;
    DatabaseReference petsDogsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("Dogs");
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_dog_profile);

        petID = getIntent().getStringExtra("petID");
        ivPetImage = findViewById(R.id.per_dog_image);
        tvPetTitle = findViewById(R.id.per_dog_name_title);
        tvPetName = findViewById(R.id.per_dog_name);
        tvPetBreed = findViewById(R.id.per_dog_breed);
        tvPetAge = findViewById(R.id.per_dog_age);
        tvPetSex = findViewById(R.id.per_dog_sex);
        tvPetDescription = findViewById(R.id.per_dog_description);

        System.out.println("petID inside PerdogProfile : " + petID);

        setUpPetImage();
    }

    private void setUpPetImage() {
        petsDogsDBRef.child(petID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                petImageName = (String) snapshot.child("imageName").getValue();

                System.out.println("PDP petImageName BEFORE STORAGE" + petImageName);
                storageReference.child("Pets/").child(petImageName).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Glide.with(PerDogProfile.this).load(uri.toString()).into(ivPetImage);
                    }
                });

                petName = (String) snapshot.child("petName").getValue();
                petBreed = (String) snapshot.child("q10").getValue();
                petAge = (String) snapshot.child("petAge").getValue();
                petSex = (String) snapshot.child("petSex").getValue();
                petDescription = (String) snapshot.child("petDesc").getValue();

                tvPetTitle.setText(petName + "'s Profile");
                tvPetName.setText(petName);
                tvPetBreed.setText(petBreed);
                tvPetAge.setText(petAge);
                tvPetSex.setText(petSex);
                tvPetDescription.setText(petDescription);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}