package com.example.petnership_kairos;

import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.ViewModelProvider;

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

public class ShelterDogQuestionnaire extends Fragment implements View.OnClickListener{

    private DogPetProfileViewModel mViewModel;

    private Button submitBtn, cancelBtn;
    private AppCompatButton q1a1Btn, q1a2Btn, q1a3Btn, q2a1Btn, q2a2Btn, q2a3Btn, q3a1Btn, q3a2Btn, q3a3Btn,
            q4a1Btn, q4a2Btn, q4a3Btn, q5a1Btn, q5a2Btn, q5a3Btn, q6a1Btn, q6a2Btn, q6a3Btn,
            q7a1Btn, q7a2Btn, q7a3Btn, q8a1Btn, q8a2Btn, q8a3Btn,
            q9a1Btn, q9a2Btn, q9a3Btn, q11a1Btn, q11a2Btn, q11a3Btn;

    private int q1,q2,q3,q4,q5,q6,q7, q8, q9, q11;
    private String q10, shelter;
    private EditText etQ10;

    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;

    DatabaseReference petsDogsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("Dogs");
    DatabaseReference sheltersDBRef = FirebaseDatabase.getInstance().getReference("Shelters");
    DatabaseReference usersDBRef = FirebaseDatabase.getInstance().getReference("Users");

    private String shelterUsername, petName, petAge, petSex, petStatus, petDesc, petID, petImage;

    public static ShelterDogQuestionnaire newInstance() {
        return new ShelterDogQuestionnaire();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shelter_dog_questionnaire, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Dog Pet Profile");

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        shelter = firebaseUser.getEmail();

        AddDog addDogInfo = new AddDog();
        petName = addDogInfo.petName;
        petAge = addDogInfo.petAge;
        petSex = addDogInfo.petSex;
        petStatus = addDogInfo.petStatus;
        petDesc = addDogInfo.petDesc;
        petID = addDogInfo.petID;
        petImage = addDogInfo.petImage;

        //Q1
        q1a1Btn = view.findViewById(R.id.q1ans1_petprofile);
        q1a1Btn.setOnClickListener((View.OnClickListener) this);
        q1a2Btn = view.findViewById(R.id.q1ans2_petprofile);
        q1a2Btn.setOnClickListener((View.OnClickListener) this);
        q1a3Btn = view.findViewById(R.id.q1ans3_petprofile);
        q1a3Btn.setOnClickListener((View.OnClickListener) this);

        //Q2
        q2a1Btn = view.findViewById(R.id.q2ans1_petprofile);
        q2a1Btn.setOnClickListener((View.OnClickListener) this);
        q2a2Btn = view.findViewById(R.id.q2ans2_petprofile);
        q2a2Btn.setOnClickListener((View.OnClickListener) this);
        q2a3Btn = view.findViewById(R.id.q2ans3_petprofile);
        q2a3Btn.setOnClickListener((View.OnClickListener) this);

        //Q3
        q3a1Btn = view.findViewById(R.id.q3ans1_petprofile);
        q3a1Btn.setOnClickListener((View.OnClickListener) this);
        q3a2Btn = view.findViewById(R.id.q3ans2_petprofile);
        q3a2Btn.setOnClickListener((View.OnClickListener) this);
        q3a3Btn = view.findViewById(R.id.q3ans3_petprofile);
        q3a3Btn.setOnClickListener((View.OnClickListener) this);

        //Q4
        q4a1Btn = view.findViewById(R.id.q4ans1_petprofile);
        q4a1Btn.setOnClickListener((View.OnClickListener) this);
        q4a2Btn = view.findViewById(R.id.q4ans2_petprofile);
        q4a2Btn.setOnClickListener((View.OnClickListener) this);
        q4a3Btn = view.findViewById(R.id.q4ans3_petprofile);
        q4a3Btn.setOnClickListener((View.OnClickListener) this);

        //Q5
        q5a1Btn = view.findViewById(R.id.q5ans1_petprofile);
        q5a1Btn.setOnClickListener((View.OnClickListener) this);
        q5a2Btn = view.findViewById(R.id.q5ans2_petprofile);
        q5a2Btn.setOnClickListener((View.OnClickListener) this);
        q5a3Btn = view.findViewById(R.id.q5ans3_petprofile);
        q5a3Btn.setOnClickListener((View.OnClickListener) this);

        //Q6
        q6a1Btn = view.findViewById(R.id.q6ans1_petprofile);
        q6a1Btn.setOnClickListener((View.OnClickListener) this);
        q6a2Btn = view.findViewById(R.id.q6ans2_petprofile);
        q6a2Btn.setOnClickListener((View.OnClickListener) this);
        q6a3Btn = view.findViewById(R.id.q6ans3_petprofile);
        q6a3Btn.setOnClickListener((View.OnClickListener) this);

        //Q7
        q7a1Btn = view.findViewById(R.id.q7ans1_petprofile);
        q7a1Btn.setOnClickListener((View.OnClickListener) this);
        q7a2Btn = view.findViewById(R.id.q7ans2_petprofile);
        q7a2Btn.setOnClickListener((View.OnClickListener) this);
        q7a3Btn = view.findViewById(R.id.q7ans3_petprofile);
        q7a3Btn.setOnClickListener((View.OnClickListener) this);

        //Q8
        q8a1Btn = view.findViewById(R.id.q8ans1_petprofile);
        q8a1Btn.setOnClickListener((View.OnClickListener) this);
        q8a2Btn = view.findViewById(R.id.q8ans2_petprofile);
        q8a2Btn.setOnClickListener((View.OnClickListener) this);
        q8a3Btn = view.findViewById(R.id.q8ans3_petprofile);
        q8a3Btn.setOnClickListener((View.OnClickListener) this);

        //Q9
        q9a1Btn = view.findViewById(R.id.q9ans1_petprofile);
        q9a1Btn.setOnClickListener((View.OnClickListener) this);
        q9a2Btn = view.findViewById(R.id.q9ans2_petprofile);
        q9a2Btn.setOnClickListener((View.OnClickListener) this);
        q9a3Btn = view.findViewById(R.id.q9ans3_petprofile);
        q9a3Btn.setOnClickListener((View.OnClickListener) this);

        //Q10
        etQ10 = view.findViewById(R.id.q10ans_petprofile);

        //Q11
        q11a1Btn = view.findViewById(R.id.q11ans1_petprofile);
        q11a1Btn.setOnClickListener((View.OnClickListener) this);
        q11a2Btn = view.findViewById(R.id.q11ans2_petprofile);
        q11a2Btn.setOnClickListener((View.OnClickListener) this);
        q11a3Btn = view.findViewById(R.id.q11ans3_petprofile);
        q11a3Btn.setOnClickListener((View.OnClickListener) this);

        submitBtn = view.findViewById(R.id.submit_petprofile);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q10 = etQ10.getText().toString().trim();
                System.out.println("q10 ==== " + q10);
                DogAnswers dogAnswers = new DogAnswers(shelter,petName, petAge, petSex, petStatus, petDesc,petImage,petID, q1,q2,q3,q4,q5,
                        q6,q7,q8,q9,q10,q11);
                petsDogsDBRef.child(petID).setValue(dogAnswers);
                addToShelterDB();
                Toast.makeText(getActivity(), "Added pet successfully!", Toast.LENGTH_LONG).show();
            }
        });

        cancelBtn = view.findViewById(R.id.cancel_petprofile);
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
            case R.id.q1ans1_petprofile:
                q1 = 1;
                break;
            case R.id.q1ans2_petprofile:
                q1 = 2;
                break;
            case R.id.q1ans3_petprofile:
                q1 = 3;
                break;
            case R.id.q2ans1_petprofile:
                q2 =1;
                break;
            case R.id.q2ans2_petprofile:
                q2 = 2;
                break;
            case R.id.q2ans3_petprofile:
                q2=3;
                break;
            case R.id.q3ans1_petprofile:
                q3 = 1;
                break;
            case R.id.q3ans2_petprofile:
                q3 = 2;
                break;
            case R.id.q3ans3_petprofile:
                q3 = 3;
                break;
            case R.id.q4ans1_petprofile:
                q4 =1;
                break;
            case R.id.q4ans2_petprofile:
                q4 = 2;
                break;
            case R.id.q4ans3_petprofile:
                q4=3;
                break;
            case R.id.q5ans1_petprofile:
                q5 = 1;
                break;
            case R.id.q5ans2_petprofile:
                q5 = 2;
                break;
            case R.id.q5ans3_petprofile:
                q5 = 3;
                break;
            case R.id.q6ans1_petprofile:
                q6 =1;
                break;
            case R.id.q6ans2_petprofile:
                q6 = 2;
                break;
            case R.id.q6ans3_petprofile:
                q6=3;
                break;
            case R.id.q7ans1_petprofile:
                q7 = 1;
                break;
            case R.id.q7ans2_petprofile:
                q7 = 2;
                break;
            case R.id.q7ans3_petprofile:
                q7 = 3;
                break;
            case R.id.q8ans1_petprofile:
                q8 =1;
                break;
            case R.id.q8ans2_petprofile:
                q8 = 2;
                break;
            case R.id.q8ans3_petprofile:
                q8=3;
                break;
            case R.id.q9ans1_petprofile:
                q9 = 1;
                break;
            case R.id.q9ans2_petprofile:
                q9 = 2;
                break;
            case R.id.q9ans3_petprofile:
                q9 = 3;
                break;
            case R.id.q11ans1_petprofile:
                q11 =1;
                break;
            case R.id.q11ans2_petprofile:
                q11 = 2;
                break;
            case R.id.q11ans3_petprofile:
                q11=3;
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
                                    Pet pet = new Pet(petName, petAge, petSex, petStatus, petDesc, petImage, petID);
                                    snapshot.child(shelterUsername).child("Dogs").child(petID).getRef().setValue(pet);
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DogPetProfileViewModel.class);
        // TODO: Use the ViewModel
    }

}