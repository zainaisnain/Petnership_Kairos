package com.example.petnership_kairos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ShelterPerPetProfile extends AppCompatActivity {

    protected static String petID, petImageName, petName, petBreed, petAge, petSex, petDescription;
    private TextView tvPetTitle, tvPetName, tvPetBreed, tvPetAge, tvPetSex, tvPetDescription;
    private ImageView ivPetImage, editDogInfoBtn;
    private ImageButton backBtn;
    DatabaseReference allPetsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("AllPets");
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_per_pet_profile);

        petID = getIntent().getStringExtra("PetID");
        ivPetImage = findViewById(R.id.per_pet_image);
        tvPetTitle = findViewById(R.id.per_pet_name_title);
        tvPetName = findViewById(R.id.per_pet_name);
        tvPetBreed = findViewById(R.id.per_pet_breed);
        tvPetAge = findViewById(R.id.per_pet_age);
        tvPetSex = findViewById(R.id.per_pet_sex);
        tvPetDescription = findViewById(R.id.per_pet_description);
        editDogInfoBtn = findViewById(R.id.edit_pet_info_btn);

        backBtn = findViewById(R.id.per_pet_back_btn);


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShelterPerPetProfile.this, ShelterListOfPetsFragment.class));
            }
        });

        setUpPetImage();
    }

    private void setUpPetImage() {
        allPetsDBRef.child(petID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                petImageName = (String) snapshot.child("imageName").getValue();

                System.out.println("PDP petImageName BEFORE STORAGE" + petImageName);
                storageReference.child("Pets/").child(petImageName).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Glide.with(ShelterPerPetProfile.this).load(uri.toString()).into(ivPetImage);
                    }
                });

                petName = (String) snapshot.child("petName").getValue();
                petBreed = (String) snapshot.child("q10").getValue();
                petAge = (String) snapshot.child("petAge").getValue();
                petSex = (String) snapshot.child("petSex").getValue();
                petDescription = (String) snapshot.child("petDesc").getValue();

                String petType = String.valueOf(snapshot.child(petID).child("petType").getValue());
                if(petType.equals("dog")){
                    petBreed = String.valueOf(snapshot.child(petID).child("q10").getValue());
                }else if(petType.equals("cat")){
                    petBreed = String.valueOf(snapshot.child(petID).child("q9").getValue());
                }

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
