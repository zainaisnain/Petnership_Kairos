package com.example.petnership_kairos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class AdopterPerPetProfile extends AppCompatActivity {

    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;

    protected static String petID, petImageName, petName, petType, petBreed, petAge, petSex, petDescription, petShelter, shelterID;
    private String adopterEmail, adopterID , shelterEmail, adopterName, adopterContact, adopterAddress;
    private TextView tvPetTitle, tvPetName, tvPetBreed, tvPetAge, tvPetSex, tvPetDescription;
    private ImageView ivPetImage;
    private ImageButton backBtn;
    private Button adoptMeBtn, notForMeBtn;

    DatabaseReference allPetsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("AllPets");
    DatabaseReference usersDBRef = FirebaseDatabase.getInstance().getReference("Users");
    DatabaseReference adoptersDBRef = FirebaseDatabase.getInstance().getReference("Adopters");
    DatabaseReference sheltersDBRef = FirebaseDatabase.getInstance().getReference("Shelters");
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    String appliedToAdopt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopter_per_pet_profile);

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        adopterEmail = firebaseUser.getEmail();

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

        adoptMeBtn = findViewById(R.id.adopter_per_pet_adopt_me_btn);
        adoptMeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //pass the pet's info to next screen (fragment)
                Bundle bundle = new Bundle();
                bundle.putString("petID", petID);
                bundle.putString("petType", petType);
                bundle.putString("petImageName", petImageName);
                bundle.putString("petName", petName);
                bundle.putString("petBreed", petBreed);
                bundle.putString("petAge", petAge);
                bundle.putString("petSex", petSex);
                bundle.putString("petDescription", petDescription);
                bundle.putString("petShelter", petShelter);
                bundle.putString("shelterID", shelterID);
                bundle.putString("shelterEmail", shelterEmail);
                bundle.putString("adopterID", adopterID);
                bundle.putString("adopterEmail", adopterEmail);
                bundle.putString("adopterName", adopterName);
                bundle.putString("adopterContact", adopterContact);
                bundle.putString("adopterAddress", adopterAddress);



                AdoptionForm adoptionForm = new AdoptionForm();
                adoptionForm.setArguments(bundle);

                //Go to next screen
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.adopter_per_pet_profile_container, adoptionForm);
                transaction.commit();
            }
        });

        notForMeBtn = findViewById(R.id.adopter_per_pet_not_for_me_btn);
        notForMeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appliedToAdopt = "false";
                adoptersDBRef.child(adopterID).child("AdopterAllPets").child(petID)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                snapshot.child("appliedToAdopt").getRef().setValue(appliedToAdopt);

                                BrowseAnimals browseAnimals = new BrowseAnimals();
                                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                                transaction.replace(R.id.adopter_per_pet_profile_container, browseAnimals);
                                transaction.commit();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
            }
        });
        setUpPetImage();
    }

    private void setUpPetImage() {

        adoptersDBRef.orderByChild("email").equalTo(adopterEmail)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists()) {
                                    for (DataSnapshot ds : snapshot.getChildren()) {
                                        adopterID = ds.getKey();
                                    }

                                    adoptersDBRef.child(adopterID).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            String fname = (String) snapshot.child("fname").getValue();
                                            String lname = (String) snapshot.child("lname").getValue();
                                            adopterName = fname + " " + lname;
                                            adopterContact = (String) snapshot.child("contact").getValue();
                                            String street = (String) snapshot.child("street").getValue();
                                            String city = (String) snapshot.child("city").getValue();
                                            String province = (String) snapshot.child("province").getValue();
                                            String country = (String) snapshot.child("country").getValue();
                                            adopterAddress = street + ", " + city + ", " + province + ", " + country;
                                            System.out.println("adopterName   == " + adopterName);
                                            System.out.println("adopterContact   == " + adopterContact);
                                            System.out.println("adopterAddress   == " + adopterAddress);
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                }



                                adoptersDBRef.child(adopterID).child("AdopterAllPets").child(petID)
                                        .addListenerForSingleValueEvent(new ValueEventListener() {
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
                                                petAge = (String) snapshot.child("petAge").getValue();
                                                petSex = (String) snapshot.child("petSex").getValue();
                                                petDescription = (String) snapshot.child("petDesc").getValue();
                                                shelterEmail = (String) snapshot.child("shelter").getValue();

                                                petType = (String) snapshot.child("petType").getValue();
                                                if(petType.equals("dog")){
                                                    petBreed = (String) snapshot.child("q10").getValue();
                                                }else if(petType.equals("cat")){
                                                    petBreed = (String) snapshot.child("q9").getValue();
                                                }

                                                tvPetTitle.setText(petName + "'s Profile");
                                                tvPetName.setText(petName);
                                                tvPetBreed.setText(petBreed);
                                                tvPetAge.setText(petAge);
                                                tvPetSex.setText(petSex);
                                                tvPetDescription.setText(petDescription);

                                                System.out.println("shelterEmail == " + shelterEmail);
                                                sheltersDBRef.orderByChild("email").equalTo(shelterEmail)
                                                        .addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                if(snapshot.exists()) {
                                                                    for (DataSnapshot ds : snapshot.getChildren()) {
                                                                        shelterID = ds.getKey();
                                                                    }

                                                                    //get shelter's name
                                                                    sheltersDBRef.child(shelterID).addListenerForSingleValueEvent(new ValueEventListener() {
                                                                        @Override
                                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                            petShelter = String.valueOf(snapshot.child("bizName").getValue());
                                                                            System.out.println("petShelter inside dbref " + petShelter);
                                                                        }

                                                                        @Override
                                                                        public void onCancelled(@NonNull DatabaseError error) {

                                                                        }
                                                                    });
                                                                }
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {

                                                            }
                                                        });

                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                            }
                                        });
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


//        allPetsDBRef.child(petID).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                petImageName = (String) snapshot.child("imageName").getValue();
//
//                storageReference.child("Pets/").child(petImageName).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                    @Override
//                    public void onSuccess(Uri uri) {
//                        Glide.with(AdopterPerPetProfile.this).load(uri.toString()).into(ivPetImage);
//                    }
//                });
//
//                petName = (String) snapshot.child("petName").getValue();
////                petBreed = (String) snapshot.child("q10").getValue();
//                petAge = (String) snapshot.child("petAge").getValue();
//                petSex = (String) snapshot.child("petSex").getValue();
//                petDescription = (String) snapshot.child("petDesc").getValue();
//                String shelterEmail = (String) snapshot.child("shelter").getValue();
//
//                String petType = (String) snapshot.child("petType").getValue();
//                if(petType.equals("dog")){
//                    petBreed = (String) snapshot.child("q10").getValue();
//                }else if(petType.equals("cat")){
//                    petBreed = (String) snapshot.child("q9").getValue();
//                }
//
//                tvPetTitle.setText(petName + "'s Profile");
//                tvPetName.setText(petName);
//                tvPetBreed.setText(petBreed);
//                tvPetAge.setText(petAge);
//                tvPetSex.setText(petSex);
//                tvPetDescription.setText(petDescription);
//
//                System.out.println("shelterEmail == " + shelterEmail);
//                sheltersDBRef.orderByChild("email").equalTo(shelterEmail)
//                        .addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                if(snapshot.exists()) {
//                                    for (DataSnapshot ds : snapshot.getChildren()) {
//                                        shelterID = ds.getKey();
//                                    }
//
//                                    //get shelter's name
//                                    sheltersDBRef.child(shelterID).addListenerForSingleValueEvent(new ValueEventListener() {
//                                        @Override
//                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                            petShelter = String.valueOf(snapshot.child("bizName").getValue());
//                                            System.out.println("petShelter inside dbref " + petShelter);
//                                        }
//
//                                        @Override
//                                        public void onCancelled(@NonNull DatabaseError error) {
//
//                                        }
//                                    });
//                                }
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError error) {
//
//                            }
//                        });
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
    }
}