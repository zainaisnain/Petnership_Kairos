package com.example.petnership_kairos;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ShelterHomeDashboard extends Fragment {

    CardView card1, card2,card3, card4, card5;
    ExtendedFloatingActionButton fabAddBtn;

    private ShelterHomeDashboardViewModel mViewModel;

    DatabaseReference petsCatsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("Cats");
    DatabaseReference petsDogsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("Dogs");
    DatabaseReference petsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets");
    DatabaseReference allPetsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("AllPets");
    DatabaseReference sheltersDBRef = FirebaseDatabase.getInstance().getReference("Shelters");
    DatabaseReference adoptersDBRef = FirebaseDatabase.getInstance().getReference("Adopters");

    private int numOfCats, numOfDogs, catsCount, dogsCount, numOfAdopters, adoptersCount, numOfForReview = 0, forReviewCount, numOfAproved, numOfPets;
    private TextView tvNumOfPets, tvNumOfDogs, tvNumOfCats, tvNumOfApprovedAdopters, tvNumOfForReview;
    private String shelterEmail, shelterImageName;
    private ImageView ivShelterImage;

    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;
    // instance for firebase storage and StorageReference
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    DatabaseReference usersDBRef = FirebaseDatabase.getInstance().getReference("Users");
    private String shelterID;
    public static ShelterHomeDashboard newInstance() {
        return new ShelterHomeDashboard();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shelter_home_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Shelter Dashboard");

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        shelterEmail = firebaseUser.getEmail();

        tvNumOfPets = view.findViewById(R.id.num_reg_pets_shelter);
        tvNumOfDogs = view.findViewById(R.id.num_reg_dogs_shelter);
        tvNumOfCats = view.findViewById(R.id.num_reg_cats_shelter);
        tvNumOfApprovedAdopters =  view.findViewById(R.id.num_of_approved_adopters);
        tvNumOfForReview =  view.findViewById(R.id.num_of_for_review);
        ivShelterImage = view.findViewById(R.id.adopterImage);

        card1 = view.findViewById(R.id.registeredPets);
        card1.setOnClickListener(v -> {
            card1.setCardBackgroundColor(card1.getResources().getColor(R.color.mid_light_purple));
            FragmentManager fm = getParentFragmentManager();

            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            ShelterListOfPetsFragment shelterRegisteredPets = new ShelterListOfPetsFragment();
            transaction.replace(R.id.nav_host_fragment, shelterRegisteredPets);
            transaction.addToBackStack("Shelter Registered Pets");
            transaction.commit();
        });

        card2 = view.findViewById(R.id.dogs);
        card2.setOnClickListener(v -> {
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            ShelterListOfDogsFragment PetDogs = new ShelterListOfDogsFragment();
            transaction.replace(R.id.nav_host_fragment,PetDogs);
            transaction.addToBackStack("Shelter Dogs");
            transaction.commit();
            ShelterDashboard.atHome = false;
        });

        card3 = view.findViewById(R.id.cats);
        card3.setOnClickListener(v -> {
           FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
           ShelterListOfCatsFragment PetCats = new ShelterListOfCatsFragment();
           transaction.replace(R.id.nav_host_fragment,PetCats);
            transaction.addToBackStack("Shelter Cats");
           transaction.commit();
            ShelterDashboard.atHome = false;
        });

        card4 = view.findViewById(R.id.approvedAdopters);
        card4.setOnClickListener(v -> {
            FragmentTransaction activeAdoptersTransaction = getParentFragmentManager().beginTransaction();
            activeAdoptersTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            ShelterApprovedAdopters shelterApprovedAdopters = new ShelterApprovedAdopters();
            activeAdoptersTransaction.replace(R.id.nav_host_fragment, shelterApprovedAdopters);
            activeAdoptersTransaction.addToBackStack("Shelter Approved Adopters");
            activeAdoptersTransaction.commit();
        });

        card5 = view.findViewById(R.id.toReview);
        card5.setOnClickListener(v -> {
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            ApplicantsReviewFragment appreviewfrag = new ApplicantsReviewFragment();
            transaction.replace(R.id.nav_host_fragment, appreviewfrag);
            transaction.addToBackStack("Shelter Review Applications");
            transaction.commit();
//                startActivity(new Intent(getActivity(), ShelterToReviewApplication.class));
        });

        fabAddBtn = view.findViewById(R.id.fabAdd);
        fabAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                ShelterRegisterPets ShelterRegisterPets = new ShelterRegisterPets();
                transaction.replace(R.id.nav_host_fragment,ShelterRegisterPets);
                transaction.addToBackStack("Shelter Add Pet");
                transaction.commit();
                ShelterDashboard.atHome = false;
            }
        });

        setUpProfilePic();
        getNumOfRegPets();
        getNumOfApprovedAdopters();
        getNumOfForReview();
    }

    private void setUpProfilePic() {
        sheltersDBRef.orderByChild("email").equalTo(shelterEmail)
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    shelterID = ds.getKey();
                }

                //CHECK IF IMAGENAME EXISTS
                sheltersDBRef.child(shelterID).orderByKey().equalTo("imageName").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            sheltersDBRef.child(shelterID).child("imageName").
                                    addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            shelterImageName = String.valueOf(snapshot.getValue());
//                                System.out.println("shelterImageName1 == " + shelterImageName);

                                            //DISPLAY IMAGE TO IMAGE VIEW
                                            if(shelterImageName != null){
                                                if(!shelterImageName.isEmpty()){
                                                    if(shelterImageName != ""){
                                                        //DISPLAY IMAGE TO IMAGE VIEW
                                                        storageReference.child("Shelters/").child(shelterImageName).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                            @Override
                                                            public void onSuccess(Uri uri) {
                                                                Glide.with(getActivity().getApplicationContext()).load(uri.toString()).into(ivShelterImage);
                                                            }
                                                        });
                                                    }
                                                }
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

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void getNumOfRegPets() {


//        //SUM OF ALL PETS
//        int sum = catsCount+dogsCount;
//        if (sum > 0){
//            String total = String.valueOf(sum);
//            tvNumOfPets.setText(total);
//        }else{
//            tvNumOfPets.setText("0");
//        }

        usersDBRef.orderByChild("email").equalTo(shelterEmail).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    shelterID = ds.getKey();
                }

                allPetsDBRef.orderByChild("shelter").equalTo(shelterID).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        numOfPets = (int) snapshot.getChildrenCount();
                        if(numOfPets>0){
                            String pets = String.valueOf(numOfPets);
                            tvNumOfPets.setText(pets);
                        }else{
                            tvNumOfPets.setText("0");
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

        usersDBRef.orderByChild("email").equalTo(shelterEmail).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    shelterID = ds.getKey();
                }
                petsCatsDBRef.orderByChild("shelter").equalTo(shelterID)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        numOfCats = (int) snapshot.getChildrenCount();
                        if(numOfCats>0){
                            String cats = String.valueOf(numOfCats);
                            catsCount = numOfCats;
                            tvNumOfCats.setText(cats + " cats");
                        }else{
                            tvNumOfCats.setText("0 cats");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                petsDogsDBRef.orderByChild("shelter").equalTo(shelterID)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                numOfDogs = (int) snapshot.getChildrenCount();
                                if(numOfDogs>0){
                                    dogsCount = numOfDogs;
                                    String dogs = String.valueOf(numOfDogs);
                                    tvNumOfDogs.setText(dogs + " dogs");
                                }else{
                                    tvNumOfDogs.setText("0 dogs");
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

    private void getNumOfApprovedAdopters(){
        sheltersDBRef.orderByChild("email").equalTo(shelterEmail).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    shelterID = ds.getKey();
                }

                sheltersDBRef.child(shelterID).orderByKey().equalTo("ApprovedAdopters").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            sheltersDBRef.child(shelterID).child("ApprovedAdopters").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    numOfAproved = (int) snapshot.getChildrenCount();
                                    System.out.println("numOfAproved == " + numOfAproved);
                                    if(numOfAproved>0){
                                        String approved = String.valueOf(numOfAproved);
                                        System.out.println("approved == " + approved);
                                        tvNumOfApprovedAdopters.setText(approved);
                                    }else{
                                        tvNumOfApprovedAdopters.setText("0");
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

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getNumOfForReview(){
        sheltersDBRef.orderByChild("email").equalTo(shelterEmail).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    shelterID = ds.getKey();
                }

                sheltersDBRef.child(shelterID).orderByKey().equalTo("ForReviewApplicants").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            sheltersDBRef.child(shelterID).child("ForReviewApplicants").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for (DataSnapshot ds2 : snapshot.getChildren()) {
                                        if(((String)ds2.child("applicationStatus").getValue()).equalsIgnoreCase("In Progress") |
                                                ((String)ds2.child("applicationStatus").getValue()).equalsIgnoreCase("Pending")) {

                                            numOfForReview++;
                                        }
                                    }
                                    if(numOfForReview>0){
                                        String forReview = String.valueOf(numOfForReview);
                                        forReviewCount = numOfForReview;
                                        tvNumOfForReview.setText(forReview);
                                    }else{
                                        tvNumOfForReview.setText("0");
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

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}