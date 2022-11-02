package com.example.petnership_kairos;

import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;

public class ShelterEditCatQuestionnaire extends Fragment implements View.OnClickListener {

    private CatPetProfileViewModel mViewModel;

    private Button submitBtn, cancelBtn;
    private AppCompatButton q1a1Btn, q1a2Btn, q1a3Btn, q2a1Btn, q2a2Btn, q2a3Btn, q3a1Btn, q3a2Btn, q3a3Btn,
            q4a1Btn, q4a2Btn, q4a3Btn, q5a1Btn, q5a2Btn, q5a3Btn, q6a1Btn, q6a2Btn, q6a3Btn,
            q7a1Btn, q7a2Btn, q7a3Btn, q8a1Btn, q8a2Btn, q8a3Btn;

    private int q1,q2,q3,q4,q5,q6,q7, q8;
    private String q9, shelter;
    private EditText etQ9;
    private EditText etOtherBreed;

    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;

    DatabaseReference petsCatsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("Cats");
    DatabaseReference allPetsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("AllPets");
    DatabaseReference sheltersDBRef = FirebaseDatabase.getInstance().getReference("Shelters");
    DatabaseReference usersDBRef = FirebaseDatabase.getInstance().getReference("Users");

    private String shelterUsername;

    private String petName, petAgeNum, petAgeDD, petAge, petSex, petStatus, petDesc, petID, imageName;

    //JANNEL
    String[] catBreed =
            {"Other", "Puspin", "Abyssinian", "American Curl", "American Shorthair",
                    "Bengal", "Birman", "Bombay", "British Shorthair", "Burmese",
                    "Burmilla", "Chartreux", "Exotic Shorthair", "Himalayan", "Maine Coon",
                    "Nebelung", "Norwegian Forest", "Persian", "Ragamuffin", "Ragdoll",
                    "Russian Blue", "Scottish Fold", "Siamese", "Siberian", "Snowshoe",
                    "Sphynx", "Tonkinese", "Turkish Angora", "Turkish Van"};

    Spinner catBreedTxt;

    ArrayAdapter<String> catBreedAdapter;
    protected static String catTypeBreed;


    public static ShelterEditCatQuestionnaire newInstance() {
        return new ShelterEditCatQuestionnaire();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shelter_cat_questionnaire, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Cat Pet Profile");

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        shelter = firebaseUser.getEmail();

        ShelterEditCat editCatInfo = new ShelterEditCat();
        petName = editCatInfo.petName;
        petAgeNum = editCatInfo.petAgeNum;
        petAgeDD = editCatInfo.petAgeDD;
        petAge = editCatInfo.petAge;
        petSex = editCatInfo.petSex;
        petStatus = editCatInfo.petStatus;
        petDesc = editCatInfo.petDesc;
        petID = editCatInfo.petID;
        imageName = editCatInfo.petImage;

        System.out.println("petID ShelterEditCatQuestionnaire()" + petID);

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
        catBreedTxt = view.findViewById(R.id.catType);
        //What dog breed
        catBreedAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, catBreed);
        catBreedTxt.setAdapter(catBreedAdapter);
        etOtherBreed = view.findViewById(R.id.cat_breed_other_et);
        etOtherBreed.setEnabled(false);


        catBreedTxt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                q9 = adapterView.getItemAtPosition(i).toString();
                if(q9.equals("Other")){
                    etOtherBreed.setEnabled(true);

                    System.out.println("q9 seton== " + q9);
                }else{
                    etOtherBreed.setText("");
                    q9 = adapterView.getItemAtPosition(i).toString();
                    etOtherBreed.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        getData();

        submitBtn = view.findViewById(R.id.submit_catprofile);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(q9.equals("Other")){
                    q9 = etOtherBreed.getText().toString().trim().toLowerCase();
                }
                if(q9.isEmpty()){
                    etOtherBreed.requestFocus();
                    Toast.makeText(getActivity(), "Please set cat's breed.", Toast.LENGTH_LONG).show();
                    return;
                }else {
                    CatPetProfileSummary catPetProfileSummary = new CatPetProfileSummary();
                    Bundle bundle = new Bundle();
                    bundle.putInt("q1", q1);
                    bundle.putInt("q2", q2);
                    bundle.putInt("q3", q3);
                    bundle.putInt("q4", q4);
                    bundle.putInt("q5", q5);
                    bundle.putInt("q6", q6);
                    bundle.putInt("q7", q7);
                    bundle.putInt("q8", q8);
                    bundle.putString("q9", q9);
                    catPetProfileSummary.setArguments(bundle);

                    addToShelterDB();
                    startActivity(new Intent(getActivity(), SuccessfullyEditedPet.class));
                }
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

    private void getData() {
        petsCatsDBRef.child(petID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                q1 = (int) snapshot.child("q1").getValue(Integer.class);
                if(q1==1){
                    q1a1Btn.setBackgroundColor(R.drawable.round_lightpurple);
                    q1a2Btn.setBackgroundColor(Color.GRAY);
                    q1a3Btn.setBackgroundColor(Color.GRAY);
                }else if(q1==2){
                    q1a1Btn.setBackgroundColor(Color.GRAY);
                    q1a2Btn.setBackgroundColor(R.drawable.round_lightpurple);
                    q1a3Btn.setBackgroundColor(Color.GRAY);
                }else{
                    q1a1Btn.setBackgroundColor(Color.GRAY);
                    q1a2Btn.setBackgroundColor(Color.GRAY);
                    q1a3Btn.setBackgroundColor(R.drawable.round_lightpurple);
                }

                q2 = (int) snapshot.child("q2").getValue(Integer.class);
                if(q2==1){
                    q2a1Btn.setBackgroundColor(R.drawable.round_lightpurple);
                    q2a2Btn.setBackgroundColor(Color.GRAY);
                    q2a3Btn.setBackgroundColor(Color.GRAY);
                }else if(q2==2){
                    q2a1Btn.setBackgroundColor(Color.GRAY);
                    q2a2Btn.setBackgroundColor(R.drawable.round_lightpurple);
                    q2a3Btn.setBackgroundColor(Color.GRAY);
                }else{
                    q2a1Btn.setBackgroundColor(Color.GRAY);
                    q2a2Btn.setBackgroundColor(Color.GRAY);
                    q2a3Btn.setBackgroundColor(R.drawable.round_lightpurple);
                }

                q3 = (int) snapshot.child("q3").getValue(Integer.class);
                if(q3==1){
                    q3a1Btn.setBackgroundColor(R.drawable.round_lightpurple);
                    q3a2Btn.setBackgroundColor(Color.GRAY);
                    q3a3Btn.setBackgroundColor(Color.GRAY);
                }else if(q3==2){
                    q3a1Btn.setBackgroundColor(Color.GRAY);
                    q3a2Btn.setBackgroundColor(R.drawable.round_lightpurple);
                    q3a3Btn.setBackgroundColor(Color.GRAY);
                }else{
                    q3a1Btn.setBackgroundColor(Color.GRAY);
                    q3a2Btn.setBackgroundColor(Color.GRAY);
                    q3a3Btn.setBackgroundColor(R.drawable.round_lightpurple);
                }

                q4 = (int) snapshot.child("q4").getValue(Integer.class);
                if(q4==1){
                    q4a1Btn.setBackgroundColor(R.drawable.round_lightpurple);
                    q4a2Btn.setBackgroundColor(Color.GRAY);
                    q4a3Btn.setBackgroundColor(Color.GRAY);
                }else if(q4==2){
                    q4a1Btn.setBackgroundColor(Color.GRAY);
                    q4a2Btn.setBackgroundColor(R.drawable.round_lightpurple);
                    q4a3Btn.setBackgroundColor(Color.GRAY);
                }else{
                    q4a1Btn.setBackgroundColor(Color.GRAY);
                    q4a2Btn.setBackgroundColor(Color.GRAY);
                    q4a3Btn.setBackgroundColor(R.drawable.round_lightpurple);
                }

                q5 = (int) snapshot.child("q5").getValue(Integer.class);
                if(q5==1){
                    q5a1Btn.setBackgroundColor(R.drawable.round_lightpurple);
                    q5a2Btn.setBackgroundColor(Color.GRAY);
                    q5a3Btn.setBackgroundColor(Color.GRAY);
                }else if(q1==2){
                    q5a1Btn.setBackgroundColor(Color.GRAY);
                    q5a2Btn.setBackgroundColor(R.drawable.round_lightpurple);
                    q5a3Btn.setBackgroundColor(Color.GRAY);
                }else{
                    q5a1Btn.setBackgroundColor(Color.GRAY);
                    q5a2Btn.setBackgroundColor(Color.GRAY);
                    q5a3Btn.setBackgroundColor(R.drawable.round_lightpurple);
                }

                q6 = (int) snapshot.child("q6").getValue(Integer.class);
                if(q6==1){
                    q6a1Btn.setBackgroundColor(R.drawable.round_lightpurple);
                    q6a2Btn.setBackgroundColor(Color.GRAY);
                    q6a3Btn.setBackgroundColor(Color.GRAY);
                }else if(q1==2){
                    q6a1Btn.setBackgroundColor(Color.GRAY);
                    q6a2Btn.setBackgroundColor(R.drawable.round_lightpurple);
                    q6a3Btn.setBackgroundColor(Color.GRAY);
                }else{
                    q6a1Btn.setBackgroundColor(Color.GRAY);
                    q6a2Btn.setBackgroundColor(Color.GRAY);
                    q6a3Btn.setBackgroundColor(R.drawable.round_lightpurple);
                }

                q7 = (int) snapshot.child("q7").getValue(Integer.class);
                if(q7==1){
                    q7a1Btn.setBackgroundColor(R.drawable.round_lightpurple);
                    q7a2Btn.setBackgroundColor(Color.GRAY);
                    q7a3Btn.setBackgroundColor(Color.GRAY);
                }else if(q1==2){
                    q7a1Btn.setBackgroundColor(Color.GRAY);
                    q7a2Btn.setBackgroundColor(R.drawable.round_lightpurple);
                    q7a3Btn.setBackgroundColor(Color.GRAY);
                }else{
                    q7a1Btn.setBackgroundColor(Color.GRAY);
                    q7a2Btn.setBackgroundColor(Color.GRAY);
                    q7a3Btn.setBackgroundColor(R.drawable.round_lightpurple);
                }

                q8 = (int) snapshot.child("q8").getValue(Integer.class);
                if(q8==1){
                    q8a1Btn.setBackgroundColor(R.drawable.round_lightpurple);
                    q8a2Btn.setBackgroundColor(Color.GRAY);
                    q8a3Btn.setBackgroundColor(Color.GRAY);
                }else if(q1==2){
                    q8a1Btn.setBackgroundColor(Color.GRAY);
                    q8a2Btn.setBackgroundColor(R.drawable.round_lightpurple);
                    q8a3Btn.setBackgroundColor(Color.GRAY);
                }else{
                    q8a1Btn.setBackgroundColor(Color.GRAY);
                    q8a2Btn.setBackgroundColor(Color.GRAY);
                    q8a3Btn.setBackgroundColor(R.drawable.round_lightpurple);
                }

                q9 = (String) snapshot.child("q9").getValue();

                if(!Arrays.asList(catBreed).contains(q9)){
                    etOtherBreed.setText(q9);
                }else{
                    int q9Position = catBreedAdapter.getPosition(q9);
                    catBreedTxt.setSelection(q9Position);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //Q1
            case R.id.q1ans1_catprofile:
                q1a1Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q1a2Btn.setBackgroundColor(Color.GRAY);
                q1a3Btn.setBackgroundColor(Color.GRAY);
                q1 = 1;
                break;
            case R.id.q1ans2_catprofile:
                q1a1Btn.setBackgroundColor(Color.GRAY);
                q1a2Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q1a3Btn.setBackgroundColor(Color.GRAY);
                q1 = 2;
                break;
            case R.id.q1ans3_catprofile:
                q1a1Btn.setBackgroundColor(Color.GRAY);
                q1a2Btn.setBackgroundColor(Color.GRAY);
                q1a3Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q1 = 3;
                break;
            //Q2
            case R.id.q2ans1_catprofile:
                q2a1Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q2a2Btn.setBackgroundColor(Color.GRAY);
                q2a3Btn.setBackgroundColor(Color.GRAY);
                q2 =1;
                break;
            case R.id.q2ans2_catprofile:
                q2a1Btn.setBackgroundColor(Color.GRAY);
                q2a2Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q2a3Btn.setBackgroundColor(Color.GRAY);
                q2 = 2;
                break;
            case R.id.q2ans3_catprofile:
                q2a1Btn.setBackgroundColor(Color.GRAY);
                q2a2Btn.setBackgroundColor(Color.GRAY);
                q2a3Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q2=3;
            //Q3
            case R.id.q3ans1_catprofile:
                q3a1Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q3a2Btn.setBackgroundColor(Color.GRAY);
                q3a3Btn.setBackgroundColor(Color.GRAY);
                q3 = 1;
                break;
            case R.id.q3ans2_catprofile:
                q3a1Btn.setBackgroundColor(Color.GRAY);
                q3a2Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q3a3Btn.setBackgroundColor(Color.GRAY);
                q3 = 2;
                break;
            case R.id.q3ans3_catprofile:
                q3a1Btn.setBackgroundColor(Color.GRAY);
                q3a2Btn.setBackgroundColor(Color.GRAY);
                q3a3Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q3 = 3;
                break;
            //Q4
            case R.id.q4ans1_catprofile:
                q4a1Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q4a2Btn.setBackgroundColor(Color.GRAY);
                q4a3Btn.setBackgroundColor(Color.GRAY);
                q4 =1;
                break;
            case R.id.q4ans2_catprofile:
                q4a1Btn.setBackgroundColor(Color.GRAY);
                q4a2Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q4a3Btn.setBackgroundColor(Color.GRAY);
                q4 = 2;
                break;
            case R.id.q4ans3_catprofile:
                q4a1Btn.setBackgroundColor(Color.GRAY);
                q4a2Btn.setBackgroundColor(Color.GRAY);
                q4a3Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q4=3;
                break;
            //Q5
            case R.id.q5ans1_catprofile:
                q5a1Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q5a2Btn.setBackgroundColor(Color.GRAY);
                q5a3Btn.setBackgroundColor(Color.GRAY);
                q5 = 1;
                break;
            case R.id.q5ans2_catprofile:
                q5a1Btn.setBackgroundColor(Color.GRAY);
                q5a2Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q5a3Btn.setBackgroundColor(Color.GRAY);
                q5 = 2;
                break;
            case R.id.q5ans3_catprofile:
                q5a1Btn.setBackgroundColor(Color.GRAY);
                q5a2Btn.setBackgroundColor(Color.GRAY);
                q5a3Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q5 = 3;
                break;
            //Q6
            case R.id.q6ans1_catprofile:
                q6a1Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q6a2Btn.setBackgroundColor(Color.GRAY);
                q6a3Btn.setBackgroundColor(Color.GRAY);
                q6 =1;
                break;
            case R.id.q6ans2_catprofile:
                q6a1Btn.setBackgroundColor(Color.GRAY);
                q6a2Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q6a3Btn.setBackgroundColor(Color.GRAY);
                q6 = 2;
                break;
            case R.id.q6ans3_catprofile:
                q6a1Btn.setBackgroundColor(Color.GRAY);
                q6a2Btn.setBackgroundColor(Color.GRAY);
                q6a3Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q6=3;
                break;
            //Q7
            case R.id.q7ans1_catprofile:
                q7a1Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q7a2Btn.setBackgroundColor(Color.GRAY);
                q7a3Btn.setBackgroundColor(Color.GRAY);
                q7 = 1;
                break;
            case R.id.q7ans2_catprofile:
                q7a1Btn.setBackgroundColor(Color.GRAY);
                q7a2Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q7a3Btn.setBackgroundColor(Color.GRAY);
                q7 = 2;
                break;
            case R.id.q7ans3_catprofile:
                q7a1Btn.setBackgroundColor(Color.GRAY);
                q7a2Btn.setBackgroundColor(Color.GRAY);
                q7a3Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q7 = 3;
                break;
            //Q8
            case R.id.q8ans1_catprofile:
                q8a1Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q8a2Btn.setBackgroundColor(Color.GRAY);
                q8a3Btn.setBackgroundColor(Color.GRAY);
                q8 =1;
                break;
            case R.id.q8ans2_catprofile:
                q8a1Btn.setBackgroundColor(Color.GRAY);
                q8a2Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q8a3Btn.setBackgroundColor(Color.GRAY);
                q8 = 2;
                break;
            case R.id.q8ans3_catprofile:
                q8a1Btn.setBackgroundColor(Color.GRAY);
                q8a2Btn.setBackgroundColor(Color.GRAY);
                q8a3Btn.setBackgroundColor(R.drawable.round_lightpurple);
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

                        String petType = "cat";
                        CatAnswers catAnswers = new CatAnswers(shelter, petName, petAgeNum, petAgeDD, petAge, petSex, petStatus,
                                petDesc, imageName,petID, q1,q2,q3,q4,q5,
                                q6,q7,q8,q9, petType);
                        petsCatsDBRef.child(petID).setValue(catAnswers);
                        allPetsDBRef.child(petID).setValue(catAnswers);
                        System.out.println("shelterUsername (ShelterEditCatQuestionnaire) --- " + shelterUsername);

                        sheltersDBRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.hasChild(shelterUsername)){
                                    Pet pet = new Pet(petName, petAgeNum, petAgeDD, petAge, petSex, petStatus, petDesc, imageName, petID);
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