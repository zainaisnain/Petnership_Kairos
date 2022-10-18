package com.example.petnership_kairos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
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
/*
List of pets -> per cat card view profile
* */
public class PerCatProfile extends AppCompatActivity {

    protected static String petID, petImageName, petName, petBreed, petAge, petSex, petDescription;
    private TextView tvPetTitle, tvPetName, tvPetBreed, tvPetAge, tvPetSex, tvPetDescription;
    private ImageView ivPetImage,editCatInfoBtn;
    DatabaseReference petsCatsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("Cats");
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_cat_profile);

        petID = getIntent().getStringExtra("petID");
        ivPetImage = findViewById(R.id.per_cat_image);
        tvPetTitle = findViewById(R.id.per_pet_name_title);
        tvPetName = findViewById(R.id.per_cat_name);
        tvPetBreed = findViewById(R.id.per_cat_breed);
        tvPetAge = findViewById(R.id.per_cat_age);
        tvPetSex = findViewById(R.id.per_cat_sex);
        tvPetDescription = findViewById(R.id.per_cat_description);
        editCatInfoBtn = findViewById(R.id.edit_cat_info_btn);

        System.out.println("petID inside PerCatProfile : " + petID);

        editCatInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                getSupportFragmentManager().
//                        beginTransaction().replace(R.id.nav_host_fragment,new ShelterEditCat()).commit();

                Fragment fragment = new ShelterEditCat();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.per_cat_profile_container, fragment).addToBackStack(null).commit();
            }
        });
        setUpPetImage();
    }

    private void setUpPetImage() {
        petsCatsDBRef.child(petID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                petImageName = (String) snapshot.child("imageName").getValue();

                storageReference.child("Pets/").child(petImageName).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Glide.with(PerCatProfile.this).load(uri.toString()).into(ivPetImage);
                    }
                });

                petName = (String) snapshot.child("petName").getValue();
                petBreed = (String) snapshot.child("q9").getValue();
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