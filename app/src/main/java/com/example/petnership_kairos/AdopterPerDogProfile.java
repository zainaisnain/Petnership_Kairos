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

public class AdopterPerDogProfile extends AppCompatActivity {

    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;

    protected static String petID, petImageName, petName, petType, petBreed, petAge, petSex, petDescription, petShelter, shelterID;
    private String adopterEmail, adopterID , shelterEmail, adopterName, adopterContact, adopterAddress, adopterBirthday;
    private TextView tvPetTitle, tvPetName, tvPetBreed, tvPetAge, tvPetSex, tvPetDescription;
    private ImageView ivPetImage;
    private ImageButton backBtnUp;
    private Button adoptMeBtn, backBtn;

    DatabaseReference allPetsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("AllPets");
    DatabaseReference usersDBRef = FirebaseDatabase.getInstance().getReference("Users");
    DatabaseReference adoptersDBRef = FirebaseDatabase.getInstance().getReference("Adopters");
    DatabaseReference sheltersDBRef = FirebaseDatabase.getInstance().getReference("Shelters");
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    String appliedToAdopt;
    boolean answeredDogQuestionnaire;

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
        setContentView(R.layout.activity_adopter_per_dog_profile);
        System.out.println("ENTERED AdopterPerDogProfile");

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        adopterEmail = firebaseUser.getEmail();

        petID = getIntent().getStringExtra("PetID");
        ivPetImage = findViewById(R.id.adopter_per_dog_image);
        tvPetTitle = findViewById(R.id.adopter_per_dog_name_title);
        tvPetName = findViewById(R.id.adopter_per_dog_name);
        tvPetBreed = findViewById(R.id.adopter_per_dog_breed);
        tvPetAge = findViewById(R.id.adopter_per_dog_age);
        tvPetSex = findViewById(R.id.adopter_per_dog_sex);
        tvPetDescription = findViewById(R.id.adopter_per_dog_description);

        tvDoglvl1 = findViewById(R.id.adopter_doglevel4a2);
        tvDoglvl2 = findViewById(R.id.adopter_doglevel4b2);
        tvDoglvl3 = findViewById(R.id.adopter_doglevel4a3);
        tvDoglvl4 = findViewById(R.id.adopter_doglevel4b3);
        tvDoglvl5 = findViewById(R.id.adopter_doglevel3);
        tvDoglvl6 = findViewById(R.id.adopter_doglevel4);
        tvDoglvl7 = findViewById(R.id.adopter_doglevel5a);
        tvDoglvl8 = findViewById(R.id.adopter_doglevel5b);
        tvDoglvl9 = findViewById(R.id.adopter_doglevel5c);
        tvDoglvl10 = findViewById(R.id.adopter_doglevel6);
        tvDoglvl11 = findViewById(R.id.adopter_doglevel7);

        backBtnUp = findViewById(R.id.btnBack);
        backBtnUp.setOnClickListener(view -> onBackPressed());


        adoptMeBtn = findViewById(R.id.adopter_per_dog_adopt_me_btn);

        backBtn = findViewById(R.id.adopter_per_dog_back_btn);
        backBtn.setOnClickListener(view -> onBackPressed());

        checkIfAdopterAnsweredDogQuestionnaire();
        setUpPetImage();
        setUpSummary();
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        String topTag = "";
        if (count > 0) {
            MyCancelDialogGoToDogProfile myCancelDialogGoToDogProfile = new MyCancelDialogGoToDogProfile();
            myCancelDialogGoToDogProfile.show(getSupportFragmentManager(), "Dog");

       }
        else {
            finishAfterTransition();
            overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
        }

    }
    private void checkIfAdopterAnsweredDogQuestionnaire(){
        adoptersDBRef.orderByChild("email").equalTo(adopterEmail)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()) {
                            for (DataSnapshot ds : snapshot.getChildren()) {
                                adopterID = ds.getKey();
                            }
                            adoptersDBRef.child(adopterID).child("answeredDogQuestionnaire").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    answeredDogQuestionnaire = (boolean) snapshot.getValue();
                                    System.out.println("answeredDogQuestionnaire ==== " + answeredDogQuestionnaire);
                                    if(answeredDogQuestionnaire){
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

                                            TermsAndConditions termsAndConditions = new TermsAndConditions();
                                            termsAndConditions.setArguments(bundle);

                                            //Go to next screen
                                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                                            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                                            transaction.replace(R.id.adopter_per_dog_profile_container, termsAndConditions);
                                            transaction.addToBackStack("Adopter Pet Dog");
                                            transaction.commit();
                                        });

                                    }else{
                                        adoptMeBtn.setOnClickListener(view -> startActivity(new Intent(AdopterPerDogProfile.this, AdopterNotAnsweredQuestionnaire.class)));
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
                                            adopterBirthday = (String) snapshot.child("birthday").getValue();
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
                                                                                Glide.with(AdopterPerDogProfile.this).load(uri.toString()).into(ivPetImage);
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