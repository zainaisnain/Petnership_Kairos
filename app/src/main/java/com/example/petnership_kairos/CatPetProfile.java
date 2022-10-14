package com.example.petnership_kairos;

import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CatPetProfile extends Fragment implements View.OnClickListener {

    private CatPetProfileViewModel mViewModel;

    private Button submitBtn, cancelBtn;
    private AppCompatButton q1a1Btn, q1a2Btn, q1a3Btn, q2a1Btn, q2a2Btn, q2a3Btn, q3a1Btn, q3a2Btn, q3a3Btn,
            q4a1Btn, q4a2Btn, q4a3Btn, q5a1Btn, q5a2Btn, q5a3Btn, q6a1Btn, q6a2Btn, q6a3Btn,
            q7a1Btn, q7a2Btn, q7a3Btn, q8a1Btn, q8a2Btn, q8a3Btn;

    private int q1,q2,q3,q4,q5,q6,q7, q8;
    private String q9, shelter;
    private EditText etQ9;

    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;

    DatabaseReference petsCatsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("Cats");
    DatabaseReference sheltersDBRef = FirebaseDatabase.getInstance().getReference("Shelters");
    DatabaseReference usersDBRef = FirebaseDatabase.getInstance().getReference("Users");

    private String shelterUsername;

    private String petName, petAge, petSex, petDesc, petID, imageName;

    public static CatPetProfile newInstance() {
        return new CatPetProfile();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cat_pet_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Cat Pet Profile");

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        shelter = firebaseUser.getEmail();

        AddCat addCatInfo = new AddCat();
        petName = addCatInfo.petName;
        petAge = addCatInfo.petAge;
        petSex = addCatInfo.petSex;
        petDesc = addCatInfo.petDesc;
        petID = addCatInfo.petID;
        imageName = addCatInfo.petImage;

        //Q1
        q1a1Btn = view.findViewById(R.id.q1ans1_catprofile);
        q1a1Btn.setOnClickListener((View.OnClickListener) this);
        q1a2Btn = view.findViewById(R.id.q1ans2_catprofile);
        q1a2Btn.setOnClickListener((View.OnClickListener) this);
        q1a3Btn = view.findViewById(R.id.q1ans3_catprofile);
        q1a3Btn.setOnClickListener((View.OnClickListener) this);

        //Q2
        q2a1Btn = view.findViewById(R.id.q2ans1_catprofile);
        q2a1Btn.setOnClickListener((View.OnClickListener) this);
        q2a2Btn = view.findViewById(R.id.q2ans2_catprofile);
        q2a2Btn.setOnClickListener((View.OnClickListener) this);
        q2a3Btn = view.findViewById(R.id.q2ans3_catprofile);
        q2a3Btn.setOnClickListener((View.OnClickListener) this);

        //Q3
        q3a1Btn = view.findViewById(R.id.q3ans1_catprofile);
        q3a1Btn.setOnClickListener((View.OnClickListener) this);
        q3a2Btn = view.findViewById(R.id.q3ans2_catprofile);
        q3a2Btn.setOnClickListener((View.OnClickListener) this);
        q3a3Btn = view.findViewById(R.id.q3ans3_catprofile);
        q3a3Btn.setOnClickListener((View.OnClickListener) this);

        //Q4
        q4a1Btn = view.findViewById(R.id.q4ans1_catprofile);
        q4a1Btn.setOnClickListener((View.OnClickListener) this);
        q4a2Btn = view.findViewById(R.id.q4ans2_catprofile);
        q4a2Btn.setOnClickListener((View.OnClickListener) this);
        q4a3Btn = view.findViewById(R.id.q4ans3_catprofile);
        q4a3Btn.setOnClickListener((View.OnClickListener) this);

        //Q5
        q5a1Btn = view.findViewById(R.id.q5ans1_catprofile);
        q5a1Btn.setOnClickListener((View.OnClickListener) this);
        q5a2Btn = view.findViewById(R.id.q5ans2_catprofile);
        q5a2Btn.setOnClickListener((View.OnClickListener) this);
        q5a3Btn = view.findViewById(R.id.q5ans3_catprofile);
        q5a3Btn.setOnClickListener((View.OnClickListener) this);

        //Q6
        q6a1Btn = view.findViewById(R.id.q6ans1_catprofile);
        q6a1Btn.setOnClickListener((View.OnClickListener) this);
        q6a2Btn = view.findViewById(R.id.q6ans2_catprofile);
        q6a2Btn.setOnClickListener((View.OnClickListener) this);
        q6a3Btn = view.findViewById(R.id.q6ans3_catprofile);
        q6a3Btn.setOnClickListener((View.OnClickListener) this);

        //Q7
        q7a1Btn = view.findViewById(R.id.q7ans1_catprofile);
        q7a1Btn.setOnClickListener((View.OnClickListener) this);
        q7a2Btn = view.findViewById(R.id.q7ans2_catprofile);
        q7a2Btn.setOnClickListener((View.OnClickListener) this);
        q7a3Btn = view.findViewById(R.id.q7ans3_catprofile);
        q7a3Btn.setOnClickListener((View.OnClickListener) this);

        //Q8
        q8a1Btn = view.findViewById(R.id.q8ans1_catprofile);
        q8a1Btn.setOnClickListener((View.OnClickListener) this);
        q8a2Btn = view.findViewById(R.id.q8ans2_catprofile);
        q8a2Btn.setOnClickListener((View.OnClickListener) this);
        q8a3Btn = view.findViewById(R.id.q8ans3_catprofile);
        q8a3Btn.setOnClickListener((View.OnClickListener) this);

        //Q9
        etQ9 = view.findViewById(R.id.q9ans_catprofile);

        submitBtn = view.findViewById(R.id.submit_catprofile);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q9 = etQ9.getText().toString().trim();
                CatAnswers catAnswers = new CatAnswers(shelter,petName, petAge, petSex, petDesc, imageName,petID, q1,q2,q3,q4,q5,
                        q6,q7,q8,q9);
                petsCatsDBRef.child(petID).setValue(catAnswers);
                addToShelterDB();
                Toast.makeText(getActivity(), "Added pet successfully!", Toast.LENGTH_LONG).show();
            }
        });

        cancelBtn = view.findViewById(R.id.cancel_catprofile);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ShelterDashboard.class));
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.q1ans1_catprofile:
                q1 = 1;
                break;
            case R.id.q1ans2_catprofile:
                q1 = 2;
                break;
            case R.id.q1ans3_catprofile:
                q1 = 3;
                break;
            case R.id.q2ans1_catprofile:
                q2 =1;
                break;
            case R.id.q2ans2_catprofile:
                q2 = 2;
                break;
            case R.id.q2ans3_catprofile:
                q2=3;
                break;
            case R.id.q3ans1_catprofile:
                q3 = 1;
                break;
            case R.id.q3ans2_catprofile:
                q3 = 2;
                break;
            case R.id.q3ans3_catprofile:
                q3 = 3;
                break;
            case R.id.q4ans1_catprofile:
                q4 =1;
                break;
            case R.id.q4ans2_catprofile:
                q4 = 2;
                break;
            case R.id.q4ans3_catprofile:
                q4=3;
                break;
            case R.id.q5ans1_catprofile:
                q5 = 1;
                break;
            case R.id.q5ans2_catprofile:
                q5 = 2;
                break;
            case R.id.q5ans3_catprofile:
                q5 = 3;
                break;
            case R.id.q6ans1_catprofile:
                q6 =1;
                break;
            case R.id.q6ans2_catprofile:
                q6 = 2;
                break;
            case R.id.q6ans3_catprofile:
                q6=3;
                break;
            case R.id.q7ans1_catprofile:
                q7 = 1;
                break;
            case R.id.q7ans2_catprofile:
                q7 = 2;
                break;
            case R.id.q7ans3_catprofile:
                q7 = 3;
                break;
            case R.id.q8ans1_catprofile:
                q8 =1;
                break;
            case R.id.q8ans2_catprofile:
                q8 = 2;
                break;
            case R.id.q8ans3_catprofile:
                q8=3;
                break;
        }
    }

    public void addToShelterDB(){
        System.out.println("shelter --- " + shelter);
        usersDBRef.orderByChild("email").equalTo(shelter)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds : snapshot.getChildren()) {
                            shelterUsername = ds.getKey();
                        }

                        System.out.println("shelterUsername --- " + shelterUsername);

                        sheltersDBRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.hasChild(shelterUsername)){
                                    Pet pet = new Pet(petName, petAge, petSex, petDesc, imageName, petID);
                                    snapshot.child(shelterUsername).child("Cats").child(petID).getRef().setValue(pet);
                                }else{
                                    System.out.println("no child in SHELTER.... ");
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

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(CatPetProfileViewModel.class);
//        // TODO: Use the ViewModel
//    }

}