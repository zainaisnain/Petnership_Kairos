package com.example.petnership_kairos;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
    DatabaseReference sheltersDBRef = FirebaseDatabase.getInstance().getReference("Shelters");
    DatabaseReference adoptersDBRef = FirebaseDatabase.getInstance().getReference("Adopters");

    private int numOfCats, numOfDogs, catsCount, dogsCount, numOfAdopters, adoptersCount, numOfForReview, forReviewCount;
    private TextView tvNumOfPets, tvNumOfDogs, tvNumOfCats, tvNumOfAdopters, tvNumOfForReview;
    private String shelterEmail, shelterUsername, shelterImageName;
    private ImageView ivShelterImage;

    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;
    // instance for firebase storage and StorageReference
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();

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
        getActivity();

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        shelterEmail = firebaseUser.getEmail();

        tvNumOfPets = view.findViewById(R.id.num_reg_pets_shelter);
        tvNumOfDogs = view.findViewById(R.id.num_reg_dogs_shelter);
        tvNumOfCats = view.findViewById(R.id.num_reg_cats_shelter);
        tvNumOfAdopters =  view.findViewById(R.id.num_of_active_adopters);
        tvNumOfForReview =  view.findViewById(R.id.num_of_for_review);
        ivShelterImage = view.findViewById(R.id.adopterImage);

        card1 = view.findViewById(R.id.registeredPets);
        card1.setOnClickListener(v -> {
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

        card4 = view.findViewById(R.id.activeAdopters);
//        card4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
//        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
//                ActiveAdopters activeadptrs = new ActiveAdopters();
//                transaction.replace(R.id.nav_host_fragment, activeadptrs);
//                transaction.commit();
//            }
//        });

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
        getNumOfAdopters();
        getNumOfForReview();
    }

    private void setUpProfilePic() {
        //retrieve imageName
        sheltersDBRef.orderByChild("email").equalTo(shelterEmail)
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                System.out.println("snapshot setUpProfilePic === " + snapshot);
                for(DataSnapshot ds : snapshot.getChildren()) {
                    shelterUsername = ds.getKey();
                }

//                System.out.println("shelterUsername " + shelterUsername);

                sheltersDBRef.child(shelterUsername).child("imageName").
                        addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                shelterImageName = String.valueOf(snapshot.getValue());
//                                System.out.println("shelterImageName1 == " + shelterImageName);

                                //DISPLAY IMAGE TO IMAGE VIEW
                                storageReference.child("Shelters/").child(shelterImageName).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        Glide.with(getActivity().getApplicationContext()).load(uri.toString()).into(ivShelterImage);
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
    private void getNumOfRegPets() {
        //TODO: parang something wrong dito. pets ba to under current shelter
        petsDBRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int sum = catsCount+dogsCount;
                if (sum > 0){
                    String total = String.valueOf(sum);
                    tvNumOfPets.setText(total);
                }else{
                    tvNumOfPets.setText("0");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        petsCatsDBRef.orderByChild("shelter").equalTo(shelterEmail).addListenerForSingleValueEvent(new ValueEventListener() {
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

        petsDogsDBRef.orderByChild("shelter").equalTo(shelterEmail).addListenerForSingleValueEvent(new ValueEventListener() {
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

    private void getNumOfAdopters(){
        adoptersDBRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                numOfAdopters = (int) snapshot.getChildrenCount();
                if(numOfAdopters>0){
                    adoptersCount = numOfAdopters;
                    String adopters = String.valueOf(numOfAdopters);
                    tvNumOfAdopters.setText(adopters);
                }else{
                    tvNumOfAdopters.setText("0");
                }
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
                    shelterUsername = ds.getKey();
                }

                sheltersDBRef.child(shelterUsername).child("ForReviewApplicants").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        numOfForReview = (int) snapshot.getChildrenCount();
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

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}