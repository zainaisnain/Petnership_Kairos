package com.example.petnership_kairos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
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

public class AdopterPerPetProfile extends AppCompatActivity {

    protected static String petID, petImageName, petName, petBreed, petAge, petSex, petDescription;

    private TextView tvPetTitle, tvPetName, tvPetBreed, tvPetAge, tvPetSex, tvPetDescription;
    private ImageView ivPetImage;
    private ImageButton backBtn;

    DatabaseReference allPetsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("AllPets");
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopter_per_pet_profile);

        petID = getIntent().getStringExtra("PetID");
        ivPetImage = findViewById(R.id.adopter_per_pet_image);
        tvPetTitle = findViewById(R.id.adopter_per_pet_name_title);
        tvPetName = findViewById(R.id.adopter_per_pet_name);
        tvPetBreed = findViewById(R.id.adopter_per_pet_breed);
        tvPetAge = findViewById(R.id.adopter_per_pet_age);
        tvPetSex = findViewById(R.id.adopter_per_pet_sex);
        tvPetDescription = findViewById(R.id.adopter_per_pet_description);

        backBtn = findViewById(R.id.adopter_per_pet_back_btn);


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BrowseAnimals browseAnimals= new BrowseAnimals();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.adopter_per_pet_profile_container, browseAnimals);
                transaction.commit();
            }
        });

        setUpPetImage();
    }

    private void setUpPetImage() {
        allPetsDBRef.child(petID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                petImageName = (String) snapshot.child("imageName").getValue();

                storageReference.child("Pets/").child(petImageName).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Glide.with(AdopterPerPetProfile.this).load(uri.toString()).into(ivPetImage);
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