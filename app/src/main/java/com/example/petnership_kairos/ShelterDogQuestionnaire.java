package com.example.petnership_kairos;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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

    private TextView tvQ1, tvQ2, tvQ3, tvQ4, tvQ5, tvQ6, tvQ7, tvQ8, tvQ9, tvQ10, tvQ11;
    private EditText etOtherBreed;

    private int q1,q2,q3,q4,q5,q6,q7, q8, q9, q11;
    private String q10, shelter;

    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;

    DatabaseReference petsDogsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("Dogs");
    DatabaseReference allPetsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("AllPets");
    DatabaseReference sheltersDBRef = FirebaseDatabase.getInstance().getReference("Shelters");
    DatabaseReference usersDBRef = FirebaseDatabase.getInstance().getReference("Users");

    private String shelterID, petName, petAgeNum, petAgeDD, petAge, petSex, petStatus, petDesc, petID, petImage;
    private boolean petExact;

    //JANNEL
    String[] dogBreed =
            {"Other", "Aspin", "Airedale Terriers", "Akitas", "Alaskan Malamutes", "American Staffordshire Terriers", "Anatolian Shepherd Dogs", "Australian Cattle Dogs", "Australian Shepherds", "Basenjis", "Basset Hounds", "Beagles",
                    "Belgian Malinois", "Bernese Mountain Dogs", "Bichons Frises", "Biewer Terriers", "Bloodhounds", "Border Collies", "Border Terriers", "Boston Terriers", "Boxers", "Brittanys", "Brussels Griffons", "Bulldogs",
                    "Bullmastiffs", "Bull Terriers", "Cairn Terriers", "Cane Corso", "Cardigan Welsh Corgi", "Cavalier King Charles Spaniels", "Chihuahuas", "Chinese Crested", "Chinese Shar-Pei", "Chow Chows","Collies", "Coton de Tulear",
                    "Dachshunds", "Dalmatians", "Doberman Pinschers", "Dogo Argentinos", "Dogues de Bordeaux", "Fox Terriers (Wire)", "French Bulldogs", "German Shepherd", "Giant Schnauzers", "Great Danes", "Great Pyrenees",
                    "Greater Swiss Mountain Dogs", "Havanese", "Irish Wolfhounds", "Italian Greyhounds", "Keeshonden", "Lagotti Romagnoli", "Lhasa Apsos", "Miniature American Shepherds", "Maltese", "Mastiffs",
                    "Miniature Pinschers", "Miniature Schnauzers", "Newfoundlands", "Norwegian Elkhounds", "Old English Sheepdogs", "Papillons", "Pekingese", "Pembroke Welsh Corgis", "Pointers (German Shorthaired)", "Pointers (German Wirehaired)", "Poodles",
                    "Pomeranians", "Portuguese Water Dogs", "Pugs", "Retrievers (Chesapeake Bay)", "Rat Terriers", "Retrievers (Flat-Coated)", "Retrievers (Golden)", "Retrievers (Labrador)", "Retrievers (Nova Scotia Duck Tolling)", "Rhodesian Ridgebacks", "Rottweilers",
                    "Russell Terriers", "Samoyeds", "Scottish Terriers", "Setters (English)", "Setters (Irish)", "Shetland Sheepdogs", "Shiba Inu", "Shih Tzu", "Siberian Huskies", "Soft Coated Wheaten Terriers", "Spaniels (Boykin)", "Spaniels (Cocker)"," Spaniels (English Cocker)",
                    "Spaniels (English Springer)", "Staffordshire Bull Terriers", "Standard Schnauzers", "St. Bernards", "Vizslas", "Weimaraners", "West Highland White Terriers", "Whippets", "Wirehaired Pointing Griffons", "Yorkshire Terriers"};

    Spinner dogBreedTxt;

    ArrayAdapter<String> dogBreedAdapter;
    protected static String dogTypeBreed;

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
        petAgeNum = addDogInfo.petAgeNum;
        petExact = addDogInfo.petExact;
        petSex = addDogInfo.petSex;
        petStatus = addDogInfo.petStatus;
        petDesc = addDogInfo.petDesc;
        petID = addDogInfo.petID;
        petImage = addDogInfo.petImage;

        //Questions TextView
        tvQ1 = view.findViewById(R.id.q1_petprofile);
        tvQ2 = view.findViewById(R.id.q2_petprofile);
        tvQ3 = view.findViewById(R.id.q3_petprofile);
        tvQ4 = view.findViewById(R.id.q4_petprofile);
        tvQ5 = view.findViewById(R.id.q5_petprofile);
        tvQ6 = view.findViewById(R.id.q6_petprofile);
        tvQ7 = view.findViewById(R.id.q7_petprofile);
        tvQ8 = view.findViewById(R.id.q8_petprofile);
        tvQ9 = view.findViewById(R.id.q9_petprofile);
        tvQ10 = view.findViewById(R.id.q10_petprofile);
        tvQ11 = view.findViewById(R.id.q11_petprofile);

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
        dogBreedTxt = view.findViewById(R.id.dogType);
        etOtherBreed = view.findViewById(R.id.dog_breed_other_et);
        etOtherBreed.setEnabled(false);
        //What dog breed
        ArrayAdapter<String> dogBreedAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, dogBreed);
        dogBreedTxt.setAdapter(dogBreedAdapter);

        dogBreedTxt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                q10 = adapterView.getItemAtPosition(i).toString();
                if(q10.equals("Other")){
                    etOtherBreed.setEnabled(true);
                    System.out.println("q10 seton == " + q10);
                }else{
                    etOtherBreed.setText("");
                    q10 = adapterView.getItemAtPosition(i).toString();
                    etOtherBreed.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
                if(q10.equals("Other")){
                    q10 = etOtherBreed.getText().toString().trim().toLowerCase();
                }
                //Validate answers
                if(q1==0){
                    tvQ1.requestFocus();
                    Toast.makeText(getActivity(), "Please answer all questions", Toast.LENGTH_LONG).show();
                    return;
                }else if(q2==0){
                    tvQ2.requestFocus();
                    Toast.makeText(getActivity(), "Please answer all questions", Toast.LENGTH_LONG).show();
                    return;
                } else if(q3==0){
                    tvQ3.requestFocus();
                    Toast.makeText(getActivity(), "Please answer all questions", Toast.LENGTH_LONG).show();
                    return;
                }else if(q4==0){
                    tvQ4.requestFocus();
                    Toast.makeText(getActivity(), "Please answer all questions", Toast.LENGTH_LONG).show();
                    return;
                }else if(q5==0){
                    tvQ5.requestFocus();
                    Toast.makeText(getActivity(), "Please answer all questions", Toast.LENGTH_LONG).show();
                    return;
                }else if(q6==0){
                    tvQ6.requestFocus();
                    Toast.makeText(getActivity(), "Please answer all questions", Toast.LENGTH_LONG).show();
                    return;
                }else if(q7==0){
                    tvQ7.requestFocus();
                    Toast.makeText(getActivity(), "Please answer all questions", Toast.LENGTH_LONG).show();
                    return;
                }else if(q8==0){
                    tvQ8.requestFocus();
                    Toast.makeText(getActivity(), "Please answer all questions", Toast.LENGTH_LONG).show();
                    return;
                }else if(q9==0) {
                    tvQ9.requestFocus();
                    Toast.makeText(getActivity(), "Please answer all questions", Toast.LENGTH_LONG).show();
                    return;
                }else if(q10.isEmpty()){
                    tvQ10.requestFocus();
                    Toast.makeText(getActivity(), "Please set dog's breed.", Toast.LENGTH_LONG).show();
                    return;
                }else if(q11==0){
                    tvQ11.requestFocus();
                    Toast.makeText(getActivity(), "Please answer all questions", Toast.LENGTH_LONG).show();
                    return;
                }else{
                    DogPetProfileSummary dogPetProfileSummary = new DogPetProfileSummary();
                    Bundle bundle = new Bundle();
                    bundle.putInt("q1", q1);
                    bundle.putInt("q2", q2);
                    bundle.putInt("q3", q3);
                    bundle.putInt("q4", q4);
                    bundle.putInt("q5", q5);
                    bundle.putInt("q6", q6);
                    bundle.putInt("q7", q7);
                    bundle.putInt("q8", q8);
                    bundle.putInt("q9", q9);
                    bundle.putString("q10", q10);
                    bundle.putInt("q11", q11);
                    dogPetProfileSummary.setArguments(bundle);

                    addToDB();

//                    SavePetProfileDialog savePetProfileDialog = new SavePetProfileDialog();
//                    savePetProfileDialog.show(getParentFragmentManager(), "My Fragment");

                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.replace(R.id.nav_host_fragment, dogPetProfileSummary);
                    transaction.commit();
                }
            }
        });

        cancelBtn = view.findViewById(R.id.cancel_petprofile);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //FRAGMENT TO ACTIVITY
                MyCancelDialogQuestionnaire myCancelDialogQuestionnaire = new MyCancelDialogQuestionnaire();
                myCancelDialogQuestionnaire.show(getParentFragmentManager(), "My Fragment");
//                startActivity(new Intent(getActivity(), ShelterDashboard.class));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //Q1
            case R.id.q1ans1_petprofile:
                q1a1Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q1a2Btn.setBackgroundColor(Color.GRAY);
                q1a3Btn.setBackgroundColor(Color.GRAY);
                q1 = 1;
                break;
            case R.id.q1ans2_petprofile:
                q1a1Btn.setBackgroundColor(Color.GRAY);
                q1a2Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q1a3Btn.setBackgroundColor(Color.GRAY);
                q1 = 2;
                break;
            case R.id.q1ans3_petprofile:
                q1a1Btn.setBackgroundColor(Color.GRAY);
                q1a2Btn.setBackgroundColor(Color.GRAY);
                q1a3Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q1 = 3;
                break;

            //Q2
            case R.id.q2ans1_petprofile:
                q2a1Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q2a2Btn.setBackgroundColor(Color.GRAY);
                q2a3Btn.setBackgroundColor(Color.GRAY);
                q2 =1;
                break;
            case R.id.q2ans2_petprofile:
                q2a1Btn.setBackgroundColor(Color.GRAY);
                q2a2Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q2a3Btn.setBackgroundColor(Color.GRAY);
                q2 = 2;
                break;
            case R.id.q2ans3_petprofile:
                q2a1Btn.setBackgroundColor(Color.GRAY);
                q2a2Btn.setBackgroundColor(Color.GRAY);
                q2a3Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q2=3;
                break;

            //Q3
            case R.id.q3ans1_petprofile:
                q3a1Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q3a2Btn.setBackgroundColor(Color.GRAY);
                q3a3Btn.setBackgroundColor(Color.GRAY);
                q3 = 1;
                break;
            case R.id.q3ans2_petprofile:
                q3a1Btn.setBackgroundColor(Color.GRAY);
                q3a2Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q3a3Btn.setBackgroundColor(Color.GRAY);
                q3 = 2;
                break;
            case R.id.q3ans3_petprofile:
                q3a1Btn.setBackgroundColor(Color.GRAY);
                q3a2Btn.setBackgroundColor(Color.GRAY);
                q3a3Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q3 = 3;
                break;

            //Q4
            case R.id.q4ans1_petprofile:
                q4a1Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q4a2Btn.setBackgroundColor(Color.GRAY);
                q4a3Btn.setBackgroundColor(Color.GRAY);
                q4 =1;
                break;
            case R.id.q4ans2_petprofile:
                q4a1Btn.setBackgroundColor(Color.GRAY);
                q4a2Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q4a3Btn.setBackgroundColor(Color.GRAY);
                q4 = 2;
                break;
            case R.id.q4ans3_petprofile:
                q4a1Btn.setBackgroundColor(Color.GRAY);
                q4a2Btn.setBackgroundColor(Color.GRAY);
                q4a3Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q4=3;
                break;

            //Q5
            case R.id.q5ans1_petprofile:
                q5a1Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q5a2Btn.setBackgroundColor(Color.GRAY);
                q5a3Btn.setBackgroundColor(Color.GRAY);
                q5 = 1;
                break;
            case R.id.q5ans2_petprofile:
                q5a1Btn.setBackgroundColor(Color.GRAY);
                q5a2Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q5a3Btn.setBackgroundColor(Color.GRAY);
                q5 = 2;
                break;
            case R.id.q5ans3_petprofile:
                q5a1Btn.setBackgroundColor(Color.GRAY);
                q5a2Btn.setBackgroundColor(Color.GRAY);
                q5a3Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q5 = 3;
                break;

            //Q6
            case R.id.q6ans1_petprofile:
                q6a1Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q6a2Btn.setBackgroundColor(Color.GRAY);
                q6a3Btn.setBackgroundColor(Color.GRAY);
                q6 =1;
                break;
            case R.id.q6ans2_petprofile:
                q6a1Btn.setBackgroundColor(Color.GRAY);
                q6a2Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q6a3Btn.setBackgroundColor(Color.GRAY);
                q6 = 2;
                break;
            case R.id.q6ans3_petprofile:
                q6a1Btn.setBackgroundColor(Color.GRAY);
                q6a2Btn.setBackgroundColor(Color.GRAY);
                q6a3Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q6=3;
                break;

            //Q7
            case R.id.q7ans1_petprofile:
                q7a1Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q7a2Btn.setBackgroundColor(Color.GRAY);
                q7a3Btn.setBackgroundColor(Color.GRAY);
                q7 = 1;
                break;
            case R.id.q7ans2_petprofile:
                q7a1Btn.setBackgroundColor(Color.GRAY);
                q7a2Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q7a3Btn.setBackgroundColor(Color.GRAY);
                q7 = 2;
                break;
            case R.id.q7ans3_petprofile:
                q7a1Btn.setBackgroundColor(Color.GRAY);
                q7a2Btn.setBackgroundColor(Color.GRAY);
                q7a3Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q7 = 3;
                break;

            //Q8
            case R.id.q8ans1_petprofile:
                q8a1Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q8a2Btn.setBackgroundColor(Color.GRAY);
                q8a3Btn.setBackgroundColor(Color.GRAY);
                q8 =1;
                break;
            case R.id.q8ans2_petprofile:
                q8a1Btn.setBackgroundColor(Color.GRAY);
                q8a2Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q8a3Btn.setBackgroundColor(Color.GRAY);
                q8 = 2;
                break;
            case R.id.q8ans3_petprofile:
                q8a1Btn.setBackgroundColor(Color.GRAY);
                q8a2Btn.setBackgroundColor(Color.GRAY);
                q8a3Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q8=3;
                break;

            //Q9
            case R.id.q9ans1_petprofile:
                q9a1Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q9a2Btn.setBackgroundColor(Color.GRAY);
                q9a3Btn.setBackgroundColor(Color.GRAY);
                q9 = 1;
                break;
            case R.id.q9ans2_petprofile:
                q9a1Btn.setBackgroundColor(Color.GRAY);
                q9a2Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q9a3Btn.setBackgroundColor(Color.GRAY);
                q9 = 2;
                break;
            case R.id.q9ans3_petprofile:
                q9a1Btn.setBackgroundColor(Color.GRAY);
                q9a2Btn.setBackgroundColor(Color.GRAY);
                q9a3Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q9 = 3;
                break;

            //Q11
            case R.id.q11ans1_petprofile:
                q11a1Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q11a2Btn.setBackgroundColor(Color.GRAY);
                q11a3Btn.setBackgroundColor(Color.GRAY);
                q11 =1;
                break;
            case R.id.q11ans2_petprofile:
                q11a1Btn.setBackgroundColor(Color.GRAY);
                q11a2Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q11a3Btn.setBackgroundColor(Color.GRAY);
                q11 = 2;
                break;
            case R.id.q11ans3_petprofile:
                q11a1Btn.setBackgroundColor(Color.GRAY);
                q11a2Btn.setBackgroundColor(Color.GRAY);
                q11a3Btn.setBackgroundColor(R.drawable.round_lightpurple);
                q11=3;
                break;
        }
    }

    public void addToDB(){
        System.out.println("shelter --- " + shelter);
        usersDBRef.orderByChild("email").equalTo(shelter)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds : snapshot.getChildren()) {
                            shelterID = ds.getKey();
                        }

                        String petType = "dog";
                        DogAnswers dogAnswers = new DogAnswers(shelterID,petName, petAgeNum, petExact, petSex, petStatus, petDesc,petImage,petID, q1,q2,q3,q4,q5,
                                q6,q7,q8,q9,q10,q11,petType);
                        petsDogsDBRef.child(petID).setValue(dogAnswers);
                        allPetsDBRef.child(petID).setValue(dogAnswers);

                        sheltersDBRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.hasChild(shelterID)){
                                    Pet pet = new Pet(petName, petAgeNum, petExact, petSex, petStatus, petDesc, petImage, petID);
                                    snapshot.child(shelterID).child("Dogs").child(petID).getRef().setValue(pet);
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