package com.example.petnership_kairos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class RecommendedPetIndiv extends Fragment {

    Button adoptBtn, notForMeBtn;
    ImageButton backBtn;
    MCDMAnswersViewModel mViewModel;
    MCDMAlternative currentAlternative;
    private String adopterEmail, adopterID , shelterEmail, adopterName, adopterContact, adopterAddress, adopterBirthday;
    protected static String petID, petImageName, petName, petType, petBreed, petAge, petSex, petDescription, petShelter, shelterID;
    double match;
    // database declarations
    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;
    DatabaseReference allPetsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("AllPets");
    DatabaseReference usersDBRef = FirebaseDatabase.getInstance().getReference("Users");
    DatabaseReference adoptersDBRef = FirebaseDatabase.getInstance().getReference("Adopters");
    DatabaseReference sheltersDBRef = FirebaseDatabase.getInstance().getReference("Shelters");
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();


    // View Declarations
    TextView tvName, tvAge, tvBreed, tvSex, tvMatch, tvDescription, tvTitle;
    TextView tvAttrib1, tvAttrib2, tvAttrib3, tvAttrib4, tvAttrib5, tvAttrib6, tvAttrib7, tvAttrib8, tvAttrib9, tvAttrib10, tvAttrib11;
    ImageView ivImage;

    public static RecommendedPetIndiv newInstance() {
        return new RecommendedPetIndiv();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);
        currentAlternative = mViewModel.getCurrentAlternative();
        View view;
        if (mViewModel.getAnimalType().equalsIgnoreCase("Dog")) {

            view = inflater.inflate(R.layout.fragment_recommended_pet_indiv_dog, container, false);
        }
        else {
            view = inflater.inflate(R.layout.fragment_recommended_pet_indiv_cat, container, false);
        }

        // find Views
        tvTitle = view.findViewById(R.id.indiv_pet_title);
        ivImage = view.findViewById(R.id.indiv_pet_image);

        tvName = view.findViewById(R.id.pet_name_value);
        tvAge = view.findViewById(R.id.pet_age_value);
        tvBreed = view.findViewById(R.id.pet_breed_value);
        tvSex = view.findViewById(R.id.pet_sex_value);
        tvMatch = view.findViewById(R.id.pet_match_value);
        tvDescription = view.findViewById(R.id.pet_description_value);




        if (mViewModel.getAnimalType().equalsIgnoreCase("Dog")) {
            tvAttrib1 = view.findViewById(R.id.pet_dog_summary_value_1);
            tvAttrib2 = view.findViewById(R.id.pet_dog_summary_value_2);
            tvAttrib3 = view.findViewById(R.id.pet_dog_summary_value_3);
            tvAttrib4 = view.findViewById(R.id.pet_dog_summary_value_4);
            tvAttrib5 = view.findViewById(R.id.pet_dog_summary_value_5);
            tvAttrib6 = view.findViewById(R.id.pet_dog_summary_value_6);
            tvAttrib7 = view.findViewById(R.id.pet_dog_summary_value_7);
            tvAttrib8 = view.findViewById(R.id.pet_dog_summary_value_8);
            tvAttrib9 = view.findViewById(R.id.pet_dog_summary_value_9);
            tvAttrib10 = view.findViewById(R.id.pet_dog_summary_value_10);
            tvAttrib11 = view.findViewById(R.id.pet_dog_summary_value_11);
        }
        else {
            tvAttrib1 = view.findViewById(R.id.pet_cat_summary_value_1);
            tvAttrib2 = view.findViewById(R.id.pet_cat_summary_value_2);
            tvAttrib3 = view.findViewById(R.id.pet_cat_summary_value_3);
            tvAttrib4 = view.findViewById(R.id.pet_cat_summary_value_4);
            tvAttrib5 = view.findViewById(R.id.pet_cat_summary_value_5);
            tvAttrib6 = view.findViewById(R.id.pet_cat_summary_value_6);
            tvAttrib7 = view.findViewById(R.id.pet_cat_summary_value_7);
            tvAttrib8 = view.findViewById(R.id.pet_cat_summary_value_8);
            tvAttrib9 = view.findViewById(R.id.pet_cat_summary_value_9);


        }

        // load image
        if(mViewModel.getTopThree()[0].getImageName() != null) {
            storageReference.child("Pets/").child(currentAlternative.getImageName()).getDownloadUrl()
                    .addOnSuccessListener(uri -> {
                        System.out.println("Successful image grab" + mViewModel.getTopThree()[0].getImageName());
                        Glide.with(getActivity()).load(uri.toString()).into((ImageView) view.findViewById(R.id.indiv_pet_image));
                    });
        }

        // change values
        tvName.setText(currentAlternative.getName());
        tvAge.setText(currentAlternative.getAge());
        tvBreed.setText(currentAlternative.getBreed());
        tvSex.setText(currentAlternative.getSex());
        tvDescription.setText(currentAlternative.getDesc());
        tvMatch.setText(String.format(Locale.getDefault(), "%.2f%% Match", currentAlternative.getCalculatedPerformanceScore()*100));

        tvAttrib1.setText(currentAlternative.getCriteriaValues().get(0).intensityLevelToString());
        tvAttrib2.setText(currentAlternative.getCriteriaValues().get(1).intensityLevelToString());
        tvAttrib3.setText(currentAlternative.getCriteriaValues().get(2).intensityLevelToString());
        tvAttrib4.setText(currentAlternative.getCriteriaValues().get(3).intensityLevelToString());
        tvAttrib5.setText(currentAlternative.getCriteriaValues().get(4).intensityLevelToString());
        tvAttrib6.setText(currentAlternative.getCriteriaValues().get(5).intensityLevelToString());
        tvAttrib7.setText(currentAlternative.getCriteriaValues().get(6).intensityLevelToString());
        tvAttrib8.setText(currentAlternative.getCriteriaValues().get(7).intensityLevelToString());
        tvAttrib9.setText(currentAlternative.getCriteriaValues().get(8).intensityLevelToString());

        if (mViewModel.getAnimalType().equalsIgnoreCase("Dog")) {
            tvAttrib10.setText(currentAlternative.getCriteriaValues().get(9).intensityLevelToString());
            tvAttrib11.setText(currentAlternative.getCriteriaValues().get(10).intensityLevelToString());
        }


        // save values

        petID = currentAlternative.getId();
        petType = currentAlternative.getType();
        petImageName = currentAlternative.getImageName();
        petName = currentAlternative.getName();
        petBreed = currentAlternative.getBreed();
        petAge = currentAlternative.getAge();
        petSex = currentAlternative.getSex();
        petDescription = currentAlternative.getDesc();

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        adopterEmail = firebaseUser.getEmail();
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
                            adoptersDBRef.child(adopterID).child("AdopterAllPets").child(petID)
                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                                            shelterID = (String) snapshot.child("shelter").getValue();

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

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        adoptBtn = view.findViewById(R.id.recommendedPet_toAdopt);
        adoptBtn.setOnClickListener(v -> {


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
            bundle.putDouble("match", currentAlternative.getCalculatedPerformanceScore());

            TermsAndConditions termsAndConditions = new TermsAndConditions();
            termsAndConditions.setArguments(bundle);

            //Go to next screen
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            transaction.replace(R.id.recommendedPets, termsAndConditions);
            transaction.addToBackStack("recommendation to adoption");
            transaction.commit();

        });

        notForMeBtn = view.findViewById(R.id.recommendedPet_notToAdopt);
        notForMeBtn.setOnClickListener(v -> {
            getParentFragmentManager().popBackStack();
        });
        backBtn = view.findViewById(R.id.btnBack);
        backBtn.setOnClickListener(view12 -> {

            getParentFragmentManager().popBackStack();
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Recommended Pets");
    }



}
