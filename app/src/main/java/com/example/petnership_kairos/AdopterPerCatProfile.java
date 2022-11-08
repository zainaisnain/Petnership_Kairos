package com.example.petnership_kairos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

import java.util.Locale;

public class AdopterPerCatProfile extends AppCompatActivity {

    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;

    protected static String petID, petImageName, petName, petType, petBreed, petAge, petSex, petDescription, petShelter, shelterID;
    private String adopterBirthday, adopterEmail, adopterID , shelterEmail, adopterName, adopterContact, adopterAddress;
    private Double matchPercentage;
    private TextView tvPetTitle, tvPetName, tvPetBreed, tvPetAge, tvPetSex, tvPetDescription, tvMatch;
    private ImageView ivPetImage;
    private ImageButton backBtnUp;
    private Button adoptMeBtn, backBtn;

    DatabaseReference allPetsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("AllPets");
    DatabaseReference usersDBRef = FirebaseDatabase.getInstance().getReference("Users");
    DatabaseReference adoptersDBRef = FirebaseDatabase.getInstance().getReference("Adopters");
    DatabaseReference sheltersDBRef = FirebaseDatabase.getInstance().getReference("Shelters");
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    String appliedToAdopt;
    boolean answeredCatQuestionnaire;

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
        setContentView(R.layout.activity_adopter_per_cat_profile);

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        adopterEmail = firebaseUser.getEmail();

        petID = getIntent().getStringExtra("PetID");
        ivPetImage = findViewById(R.id.adopter_per_cat_image);
        tvPetTitle = findViewById(R.id.adopter_per_cat_name_title);
        tvPetName = findViewById(R.id.adopter_per_cat_name);
        tvPetBreed = findViewById(R.id.adopter_per_cat_breed);
        tvPetAge = findViewById(R.id.adopter_per_cat_age);
        tvPetSex = findViewById(R.id.adopter_per_cat_sex);
        tvPetDescription = findViewById(R.id.adopter_per_cat_description);
        tvMatch = findViewById(R.id.adopter_per_cat_compatibility);

        tvCatlvl1 = findViewById(R.id.adopter_catlevel1);
        tvCatlvl2 = findViewById(R.id.adopter_catlevel2);
        tvCatlvl3 = findViewById(R.id.adopter_catlevel3);
        tvCatlvl4 = findViewById(R.id.adopter_catlevel4a);
        tvCatlvl5 = findViewById(R.id.adopter_catlevel4b);
        tvCatlvl6 = findViewById(R.id.adopter_catlevel5);
        tvCatlvl7 = findViewById(R.id.adopter_catlevel6);
        tvCatlvl8 = findViewById(R.id.adopter_catlevel7);
        tvCatlvl9 = findViewById(R.id.adopter_catlevel8);

        backBtnUp = findViewById(R.id.adopter_per_cat_back_btn_up);
        backBtnUp.setOnClickListener(view -> onBackPressed());

        adoptMeBtn = findViewById(R.id.adopter_per_cat_adopt_me_btn);
//        adoptMeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                TermsAndConditions termsAndConditions= new TermsAndConditions();
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.adopter_per_cat_profile_container, termsAndConditions);
//                transaction.commit();
//            }
//        });

        backBtn = findViewById(R.id.adopter_per_cat_back_btn);
        backBtn.setOnClickListener(view -> onBackPressed());

        checkIfAdopterAnsweredCatQuestionnaire();
        setUpPetImage();
        setUpSummary();
    }
    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count > 0) {
            MyCancelDialogGoToDogProfile myCancelDialogGoToDogProfile = new MyCancelDialogGoToDogProfile();
            myCancelDialogGoToDogProfile.show(getSupportFragmentManager(), "Cat");

        }
        else {
            finishAfterTransition();
            overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
        }

    }

    private void checkIfAdopterAnsweredCatQuestionnaire(){
        adoptersDBRef.orderByChild("email").equalTo(adopterEmail)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()) {
                            for (DataSnapshot ds : snapshot.getChildren()) {
                                adopterID = ds.getKey();
                            }
                            adoptersDBRef.child(adopterID).child("answeredCatQuestionnaire").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    answeredCatQuestionnaire = (boolean) snapshot.getValue();
                                    if(answeredCatQuestionnaire){
                                        adoptMeBtn.setEnabled(true);
                                        adoptMeBtn.setOnClickListener(view -> {

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
                                            bundle.putString("adopterBirthday", adopterBirthday);
                                            bundle.putDouble("match", matchPercentage);

                                            TermsAndConditions termsAndConditions = new TermsAndConditions();
                                            termsAndConditions.setArguments(bundle);

                                            //Go to next screen
                                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                                            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                                            transaction.replace(R.id.adopter_per_cat_profile_container, termsAndConditions);
                                            transaction.addToBackStack("Adopter Pet Cat");
                                            transaction.commit();
                                        });
                                    }else{
                                        adoptMeBtn.setEnabled(true);
                                        adoptMeBtn.setOnClickListener(view -> startActivity(new Intent(AdopterPerCatProfile.this, AdopterNotAnsweredQuestionnaire.class)));

                                    }
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


                        allPetsDBRef.child(petID).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot2) {

                                petImageName = (String) snapshot2.child("imageName").getValue();

                                if(petImageName != null){
                                    if(!petImageName.isEmpty()){
                                        if(petImageName != ""){
                                            //DISPLAY IMAGE TO IMAGE VIEW
                                            storageReference.child("Pets/").child(petImageName).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                @Override
                                                public void onSuccess(Uri uri) {
                                                    Glide.with(AdopterPerCatProfile.this).load(uri.toString()).into(ivPetImage);
                                                }
                                            });
                                        }
                                    }
                                }

                                petName = String.valueOf(snapshot2.child("petName").getValue());
                                petAge = String.valueOf(snapshot2.child("petAge").getValue());
                                petSex = String.valueOf(snapshot2.child("petSex").getValue());
                                petDescription = String.valueOf(snapshot2.child("petDesc").getValue());
                                shelterID = (String) snapshot2.child("shelter").getValue();
                                petType = (String) snapshot2.child("petType").getValue();
                                System.out.println("-petImageName: " + petImageName);
                                System.out.println("-petName " + petName);
                                System.out.println("-petAge: " + petAge);
                                System.out.println("-petSex: " + petSex);
                                petBreed = (String) snapshot2.child("q9").getValue();
                                adoptersDBRef.child(adopterID).child("AdopterAllPets").child(petID).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot2) {
                                        System.out.println("Snapshot: " + snapshot2.getKey());
                                        matchPercentage = (Double) snapshot2.child("MatchPercentage").getValue();
                                        System.out.println("Match: " + matchPercentage);


                                        if (matchPercentage <= 0 ) {
                                            tvMatch.setText("N/A");
                                        }
                                        else {
                                            tvMatch.setText(String.format(Locale.getDefault(), "%.2f%% Match", matchPercentage*100));
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                                tvPetTitle.setText(petName + "'s Profile");
                                tvPetName.setText(petName);
                                tvPetBreed.setText(petBreed);
                                //TODO: Change to petBirthday (Add field to Firebase)
                                tvPetAge.setText(petAge);
                                tvPetSex.setText(petSex);
                                tvPetDescription.setText(petDescription);

                                sheltersDBRef.child(shelterID).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        shelterEmail = (String) snapshot.child("email").getValue();
                                        petShelter = (String) snapshot.child("bizName").getValue();
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


/*

                        adoptersDBRef.child(adopterID).child("AdopterAllPets").child(petID)
                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        snapshot.getRef().orderByKey().equalTo("imageName").addListenerForSingleValueEvent(new ValueEventListener() {
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
                                                                        Glide.with(AdopterPerCatProfile.this).load(uri.toString()).into(ivPetImage);
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
                                        petAge = (String) snapshot.child("petAge").getValue();
                                        petSex = (String) snapshot.child("petSex").getValue();
                                        petDescription = (String) snapshot.child("petDesc").getValue();
                                        shelterID = (String) snapshot.child("shelter").getValue();

                                        petType = (String) snapshot.child("petType").getValue();
                                        if(petType.equals("dog")){
                                            petBreed = (String) snapshot.child("q10").getValue();
                                        }else if(petType.equals("cat")){
                                            petBreed = (String) snapshot.child("q9").getValue();
                                        }

                                        tvPetTitle.setText(petName + "'s Profile");
                                        tvPetName.setText(petName);
                                        tvPetBreed.setText(petBreed);
                                        //TODO: Change to petBirthday (Add field to Firebase)
                                        tvPetAge.setText(petAge);
                                        tvPetSex.setText(petSex);
                                        tvPetDescription.setText(petDescription);

                                        sheltersDBRef.child(shelterID).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                shelterEmail = (String) snapshot.child("email").getValue();
                                                petShelter = (String) snapshot.child("bizName").getValue();
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
                        */
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