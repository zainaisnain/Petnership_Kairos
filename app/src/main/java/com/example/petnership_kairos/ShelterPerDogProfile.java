package com.example.petnership_kairos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ShelterPerDogProfile extends AppCompatActivity {
    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;

    protected static String petID, petImageName, petName, petBreed, petAge, petSex, petDescription;
    private TextView tvPetTitle, tvPetName, tvPetBreed, tvPetAge, tvPetSex, tvPetDescription;
    private ImageView ivPetImage, editDogInfoBtn;
    private ImageButton backBtn;
    private String shelterEmail, shelterID;
    DatabaseReference petsDogsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("Dogs");
    DatabaseReference allPetsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("AllPets");
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    private TextView tvDoglvl1, tvDoglvl2, tvDoglvl3, tvDoglvl4, tvDoglvl5,
            tvDoglvl6, tvDoglvl7, tvDoglvl8, tvDoglvl9, tvDoglvl10, tvDoglvl11;

    private String[] popularityHigh =
            {"Retrievers (Labrador)", "French Bulldogs", "Retrievers (Golden)", "German Shepherd Dogs", "Poodles",
                    "Bulldogs", "Beagles", "Rottweilers", "Pointers (German Shorthaired)", "Dachshunds",
                    "Pembroke Welsh Corgis", "Australian Shepherds", "Yorkshire Terriers", "Boxers", "Cavalier King Charles Spaniels",
                    "Doberman Pinschers", "Great Danes", "Miniature Schnauzers", "Siberian Huskies", "Bernese Mountain Dogs",
                    "Cane Corso", "Shih Tzu", "Boston Terriers", "Pomeranians", "Havanese"};

    private String[] popularityMedium =
            {"Spaniels (English Springer)", "Brittanys", "Shetland Sheepdogs", "Spaniels (Cocker)", "Miniature American Shepherds",
                    "Border Collies", "Vizslas", "Pugs", "Basset Hounds", "Mastiffs",
                    "Belgian Malinois", "Chihuahuas", "Collies", "Maltese", "Weimaraners",
                    "Rhodesian Ridgebacks", "Shiba Inu", "Spaniels (English Cocker)", "Portuguese Water Dogs", "Newfoundlands",
                    "West Highland White Terriers", "Bichons Frises", "Retrievers (Chesapeake Bay)", "Dalmatians", "Bloodhounds",
                    "Australian Cattle Dogs", "Akitas", "St. Bernards", "Papillons", "Samoyeds",
                    "Bullmastiffs", "Whippets", "Scottish Terriers", "Pointers (German Wirehaired)", "Wirehaired Pointing Griffons",
                    "Bull Terriers", "Airedale Terriers", "Great Pyrenees", "Chinese Shar-Pei", "Giant Schnauzers",
                    "Soft Coated Wheaten Terriers", "Cardigan Welsh Corgis", "Alaskan Malamutes", "Old English Sheepdogs", "Dogues de Bordeaux",
                    "Setters (Irish)", "Russell Terriers", "Italian Greyhounds", "Cairn Terriers", "Staffordshire Bull Terriers",
                    "Miniature Pinschers", "Chinese Crested", "Greater Swiss Mountain Dogs", "Lagotti Romagnoli", "Chow Chows",
                    "American Staffordshire Terriers", "Biewer Terriers", "Coton de Tulear", "Lhasa Apsos", "Irish Wolfhounds",
                    "Rat Terriers", "Basenjis", "Anatolian Shepherd Dogs", "Dogo Argentinos", "Spaniels (Boykin)",
                    "Border Terriers", "Retrievers (Nova Scotia Duck Tolling)", "Retrievers (Flat-Coated)", "Pekingese", "Keeshonden",
                    "Standard Schnauzers", "Brussels Griffons", "Setters (English)", "Fox Terriers (Wire)", "Norwegian Elkhounds"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_per_dog_profile);

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        shelterEmail = firebaseUser.getEmail();

        petID = getIntent().getStringExtra("dogPetID");
        ivPetImage = findViewById(R.id.per_dog_image);
        tvPetTitle = findViewById(R.id.per_dog_name_title);
        tvPetName = findViewById(R.id.per_dog_name);
        tvPetBreed = findViewById(R.id.per_dog_breed);
        tvPetAge = findViewById(R.id.per_dog_age);
        tvPetSex = findViewById(R.id.per_dog_sex);
        tvPetDescription = findViewById(R.id.per_dog_description);
        editDogInfoBtn = findViewById(R.id.edit_dog_info_btn);

        tvDoglvl1 = findViewById(R.id.shelter_doglevel4a2);
        tvDoglvl2 = findViewById(R.id.shelter_doglevel4b2);
        tvDoglvl3 = findViewById(R.id.shelter_doglevel4a3);
        tvDoglvl4 = findViewById(R.id.shelter_doglevel4b3);
        tvDoglvl5 = findViewById(R.id.shelter_doglevel3);
        tvDoglvl6 = findViewById(R.id.shelter_doglevel4);
        tvDoglvl7 = findViewById(R.id.shelter_doglevel5a);
        tvDoglvl8 = findViewById(R.id.shelter_doglevel5b);
        tvDoglvl9 = findViewById(R.id.shelter_doglevel5c);
        tvDoglvl10 = findViewById(R.id.shelter_doglevel6);
        tvDoglvl11 = findViewById(R.id.shelter_doglevel7);

        backBtn = findViewById(R.id.per_dog_back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        editDogInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                getSupportFragmentManager().
//                        beginTransaction().replace(R.id.nav_host_fragment,new ShelterEditCat()).commit();

                Fragment fragment = new ShelterEditDog();

                Bundle bundle = new Bundle();
                bundle.putString("petID", petID);
                fragment.setArguments(bundle);

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.per_dog_profile_container, fragment).addToBackStack(null).commit();
            }
        });

        setUpPetImage();
        setUpSummary();
    }

    private void setUpPetImage() {
        petsDogsDBRef.child(petID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                petsDogsDBRef.orderByKey().equalTo("imageName").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            petImageName = (String) snapshot.child("imageName").getValue();
                            storageReference.child("Pets/").child(petImageName).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Glide.with(ShelterPerDogProfile.this).load(uri.toString()).into(ivPetImage);
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

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
    private void setUpSummary(){
        allPetsDBRef.child(petID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int q1 = Math.toIntExact((Long) snapshot.child("q1").getValue());
                System.out.println("AdopterPerDogProfile q1 === " + q1);
                int q2 = Math.toIntExact((Long) snapshot.child("q2").getValue());
                int q3 = Math.toIntExact((Long) snapshot.child("q3").getValue());
                int q4 = Math.toIntExact((Long) snapshot.child("q4").getValue());
                int q5 = Math.toIntExact((Long) snapshot.child("q5").getValue());
                int q6 = Math.toIntExact((Long) snapshot.child("q6").getValue());
                int q7 = Math.toIntExact((Long) snapshot.child("q7").getValue());
                int q8 = Math.toIntExact((Long) snapshot.child("q8").getValue());
                int q9 = Math.toIntExact((Long) snapshot.child("q9").getValue());
                String q10 = (String) snapshot.child("q10").getValue();
                int q11 = Math.toIntExact((Long) snapshot.child("q11").getValue());

                if(q1 == 1){
                    tvDoglvl1.setText("High");
                }else if(q1 == 2){
                    tvDoglvl1.setText("Medium");
                }else{
                    tvDoglvl1.setText("Low");
                }

                if(q2 == 1){
                    tvDoglvl2.setText("High");
                }else if(q2 == 2){
                    tvDoglvl2.setText("Medium");
                }else{
                    tvDoglvl2.setText("Low");
                }

                if(q3 == 1){
                    tvDoglvl3.setText("High");
                }else if(q3 == 2){
                    tvDoglvl3.setText("Medium");
                }else{
                    tvDoglvl3.setText("Low");
                }

                if(q4 == 1){
                    tvDoglvl4.setText("High");
                }else if(q4 == 2){
                    tvDoglvl4.setText("Medium");
                }else{
                    tvDoglvl4.setText("Low");
                }

                if(q5 == 1){
                    tvDoglvl5.setText("High");
                }else if(q5 == 2){
                    tvDoglvl5.setText("Medium");
                }else{
                    tvDoglvl5.setText("Low");
                }

                if(q6 == 1){
                    tvDoglvl6.setText("High");
                }else if(q6 == 2){
                    tvDoglvl6.setText("Medium");
                }else{
                    tvDoglvl6.setText("Low");
                }

                if(q7 == 1){
                    tvDoglvl7.setText("High");
                }else if(q7 == 2){
                    tvDoglvl7.setText("Medium");
                }else{
                    tvDoglvl7.setText("Low");
                }

                if(q8 == 1){
                    tvDoglvl8.setText("High");
                }else if(q8 == 2){
                    tvDoglvl8.setText("Medium");
                }else{
                    tvDoglvl8.setText("Low");
                }

                if(q9 == 1){
                    tvDoglvl9.setText("High");
                }else if(q9== 2){
                    tvDoglvl9.setText("Medium");
                }else{
                    tvDoglvl9.setText("Low");
                }

                //Q10 BREED POPULARITY
                //HIGH : TOP 1 - 10
                // MEDIUM : 11 - 25
                // LOW : NOT ON THE LIST
                for (String highBreed : popularityHigh) {
                    if (highBreed.equals(q10)) {
                        tvDoglvl10.setText("High");
                    }
                }

                for (String mediumBreed : popularityMedium) {
                    if (mediumBreed.equals(q10)) {
                        tvDoglvl10.setText("Medium");
                    }else{
                        tvDoglvl10.setText("Low");
                    }
                }

                if(q11 == 1){
                    tvDoglvl11.setText("High");
                }else if(q11 == 2){
                    tvDoglvl11.setText("Medium");
                }else{
                    tvDoglvl11.setText("Low");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}