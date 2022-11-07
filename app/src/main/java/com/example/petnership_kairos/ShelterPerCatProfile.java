package com.example.petnership_kairos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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
/*
List of pets -> per cat card view profile
* */
public class ShelterPerCatProfile extends AppCompatActivity {
    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;

    protected static String petID, petImageName, petName, petBreed, petAge, petSex, petDescription;
    private TextView tvPetTitle, tvPetName, tvPetBreed, tvPetAge, tvPetSex, tvPetDescription;
    private ImageView ivPetImage,editCatInfoBtn, deleteCatBtn;

    private String shelterEmail, shelterID;
    private ImageButton backBtn;
    DatabaseReference petsCatsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("Cats");
    DatabaseReference allPetsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("AllPets");
    DatabaseReference usersDBRef = FirebaseDatabase.getInstance().getReference("Users");
    DatabaseReference adoptersDBRef = FirebaseDatabase.getInstance().getReference("Adopters");
    DatabaseReference sheltersDBRef = FirebaseDatabase.getInstance().getReference("Shelters");
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    //SUMMARY
    private TextView tvCatlvl1, tvCatlvl2, tvCatlvl3, tvCatlvl4, tvCatlvl5,
            tvCatlvl6, tvCatlvl7, tvCatlvl8, tvCatlvl9;

    private String[] popularityHigh = {"Maine Coon", "Bengal", "Siamese", "Siberian", "Ragdoll",
            "British Shorthair", "Persian", "Scottish Fold", "Bombay", "Birman"};
    private String[] popularityMedium = {"Snowshoe", "Abyssinian", "Norwegian Forest", "Burmese", "Turkish Angora",
            "Ragamuffin", "Sphynx", "Nebelung", "Russian Blue", "Burmilla", "Chartreux", "American Shorthair",
            "Himalayan", "Turkish Van", "Tonkinese"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_per_cat_profile);

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        shelterEmail = firebaseUser.getEmail();

        petID = getIntent().getStringExtra("petID");
        ivPetImage = findViewById(R.id.per_cat_image);
        tvPetTitle = findViewById(R.id.per_pet_name_title);
        tvPetName = findViewById(R.id.pet_name_value);
        tvPetBreed = findViewById(R.id.per_cat_breed);
        tvPetAge = findViewById(R.id.per_cat_age);
        tvPetSex = findViewById(R.id.per_cat_sex);
        tvPetDescription = findViewById(R.id.pet_description_value);
        editCatInfoBtn = findViewById(R.id.edit_cat_info_btn);
        backBtn = findViewById(R.id.per_cat_back_btn);

        tvCatlvl1 = findViewById(R.id.shelter_catlevel1);
        tvCatlvl2 = findViewById(R.id.shelter_catlevel2);
        tvCatlvl3 = findViewById(R.id.shelter_catlevel3);
        tvCatlvl4 = findViewById(R.id.shelter_catlevel4a);
        tvCatlvl5 = findViewById(R.id.shelter_catlevel4b);
        tvCatlvl6 = findViewById(R.id.shelter_catlevel5);
        tvCatlvl7 = findViewById(R.id.shelter_catlevel6);
        tvCatlvl8 = findViewById(R.id.shelter_catlevel7);
        tvCatlvl9 = findViewById(R.id.shelter_catlevel8);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        deleteCatBtn = findViewById(R.id.delete_cat_info_btn);
        deleteCatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //first show dialog if they want to delete pet
                //if yes
                //are you sure? (need pa ba)
                //then delete
                deletePet();
            }
        });

        editCatInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                getSupportFragmentManager().
//                        beginTransaction().replace(R.id.nav_host_fragment,new ShelterEditCat()).commit();

                Fragment fragment = new ShelterEditCat();

                Bundle bundle = new Bundle();
                bundle.putString("petID", petID);
                fragment.setArguments(bundle);

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.per_cat_profile_container, fragment).addToBackStack(null).commit();
            }
        });
        setUpPetImage();
        setUpSummary();
    }

    private void deletePet() {
        usersDBRef.orderByChild("email").equalTo(shelterEmail).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    shelterID = ds.getKey();
                }

                System.out.println("shelterID == " + shelterID);


                //check if it's in forReviewApplicants
                sheltersDBRef.child(shelterID).orderByKey().equalTo("ForReviewApplicants").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            sheltersDBRef.child(shelterID).child("ForReviewApplicants").orderByChild("petID").equalTo(petID).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if(snapshot.exists()){
                                        System.out.println("PET IS UNDER APPLICANT REVIEW. CANT DELETE PET.");
                                    }else{
                                        //check if it's in ApprovedAdopters
                                        sheltersDBRef.child(shelterID).orderByKey().equalTo("ApprovedAdopters").addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if(snapshot.exists()){
                                                    sheltersDBRef.child(shelterID).child("ApprovedAdopters").orderByChild("petID").equalTo(petID).addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                            if(snapshot.exists()){
                                                                System.out.println("PET HAS approved adopter. CANT DELETE PET.");
                                                            }else{
                                                                //delete pet
                                                                System.out.println("CAN NOW DELETE PET");
//                                                                allPetsDBRef.child(petID).removeValue();
//                                                                petsCatsDBRef.child(petID).removeValue();
//                                                                sheltersDBRef.child(shelterID).child("Cats").addListenerForSingleValueEvent(new ValueEventListener() {
//                                                                    @Override
//                                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                                                        snapshot.child(petID).getRef().removeValue();
//                                                                    }
//                                                                    @Override
//                                                                    public void onCancelled(@NonNull DatabaseError error) {}});
                                                            }
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError error) {}});

                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {}});

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}});

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}});


    }

    private void setUpPetImage() {
        petsCatsDBRef.child(petID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                petsCatsDBRef.child(petID).orderByKey().equalTo("imageName").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            petImageName = (String) snapshot.child("imageName").getValue();
                            if(petImageName != null){
                                if(!petImageName.isEmpty()){
                                    if(petImageName != ""){
                                        //DISPLAY IMAGE TO IMAGE VIEW
                                        storageReference.child("Pets/").child(petImageName).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {
                                                Glide.with(ShelterPerCatProfile.this).load(uri.toString()).into(ivPetImage);
                                            }
                                        });
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

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
                //TODO: Change to petBirthday (Add field to Firebase)
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
        System.out.println("petID ShelterPerCat ==== " + petID);
        allPetsDBRef.child(petID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int q1 = Math.toIntExact((Long) snapshot.child("q1").getValue());
                int q2 = Math.toIntExact((Long) snapshot.child("q2").getValue());
                int q3 = Math.toIntExact((Long) snapshot.child("q3").getValue());
                int q4 = Math.toIntExact((Long) snapshot.child("q4").getValue());
                int q5 = Math.toIntExact((Long) snapshot.child("q5").getValue());
                int q6 = Math.toIntExact((Long) snapshot.child("q6").getValue());
                int q7 = Math.toIntExact((Long) snapshot.child("q7").getValue());
                int q8 = Math.toIntExact((Long) snapshot.child("q8").getValue());
                String q9 = (String) snapshot.child("q9").getValue();

                if(q1 == 1){
                    tvCatlvl1.setText("High");
                }else if(q1 == 2){
                    tvCatlvl1.setText("Medium");
                }else{
                    tvCatlvl1.setText("Low");
                }

                if(q2 == 1){
                    tvCatlvl2.setText("High");
                }else if(q2 == 2){
                    tvCatlvl2.setText("Medium");
                }else{
                    tvCatlvl2.setText("Low");
                }

                if(q3 == 1){
                    tvCatlvl3.setText("High");
                }else if(q3 == 2){
                    tvCatlvl3.setText("Medium");
                }else{
                    tvCatlvl3.setText("Low");
                }

                if(q4 == 1){
                    tvCatlvl4.setText("High");
                }else if(q4 == 2){
                    tvCatlvl4.setText("Medium");
                }else{
                    tvCatlvl4.setText("Low");
                }

                if(q5 == 1){
                    tvCatlvl5.setText("High");
                }else if(q5 == 2){
                    tvCatlvl5.setText("Medium");
                }else{
                    tvCatlvl5.setText("Low");
                }

                if(q6 == 1){
                    tvCatlvl6.setText("High");
                }else if(q6 == 2){
                    tvCatlvl6.setText("Medium");
                }else{
                    tvCatlvl6.setText("Low");
                }

                if(q7 == 1){
                    tvCatlvl7.setText("High");
                }else if(q7 == 2){
                    tvCatlvl7.setText("Medium");
                }else{
                    tvCatlvl7.setText("Low");
                }

                if(q8 == 1){
                    tvCatlvl8.setText("High");
                }else if(q8 == 2){
                    tvCatlvl8.setText("Medium");
                }else{
                    tvCatlvl8.setText("Low");
                }

                //Q9 BREED POPULARITY
                //HIGH : TOP 1 - 10
                // MEDIUM : 11 - 25
                // LOW : NOT ON THE LIST
                for (String highBreed : popularityHigh) {
                    if (highBreed.equals(q9)) {
                        tvCatlvl9.setText("High");
                    }
                }

                for (String mediumBreed : popularityMedium) {
                    if (mediumBreed.equals(q9)) {
                        tvCatlvl9.setText("Medium");
                    }else{
                        tvCatlvl9.setText("Low");
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}