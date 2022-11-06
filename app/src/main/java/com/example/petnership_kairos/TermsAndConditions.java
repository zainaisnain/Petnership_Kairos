package com.example.petnership_kairos;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TermsAndConditions extends Fragment implements View.OnClickListener{

    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;
    private String adopterID, adopterName, adopterEmail, adopterContact, adopterAddress,
            petID, petType, petName, petBreed, petAge, petDescription, dateApplied, timeApplied, applicationStatus;

    protected static String petImageName, petSex, petShelter, shelterID, shelterEmail;

    DatabaseReference usersDBRef = FirebaseDatabase.getInstance().getReference("Users");
    DatabaseReference adoptersDBRef = FirebaseDatabase.getInstance().getReference("Adopters");
    DatabaseReference sheltersDBRef = FirebaseDatabase.getInstance().getReference("Shelters");
    Button submitForm, cancelForm;
    ImageButton backBtn;
    CheckBox agreeCb;
    EditText intentionsTv;

    private AppCompatRadioButton hasPet, hasNoPet, ownHome, rentHome, hasWork, hasNoWork;

    String adopterIntentions, appliedToAdopt;
    boolean adopterAgreed;
    private TermsAndConditionsViewModel mViewModel;

    public static TermsAndConditions newInstance() {
        return new TermsAndConditions();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_terms_and_conditions, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Adoption Form");

        //radio buttons
        hasPet = view.findViewById(R.id.adopter_yesPet);
        hasPet.setOnClickListener((View.OnClickListener) this);

        hasNoPet = view.findViewById(R.id.adopter_noPet);
        hasNoPet.setOnClickListener((View.OnClickListener) this);

        ownHome = view.findViewById(R.id.adopter_ownHome);
        ownHome.setOnClickListener((View.OnClickListener) this);

        hasWork = view.findViewById(R.id.adopter_yesWork);
        hasWork.setOnClickListener((View.OnClickListener) this);

        hasNoWork = view.findViewById(R.id.adopter_noWork);
        hasNoWork.setOnClickListener((View.OnClickListener) this);

        //Get adopter's infos
        //get pet infos
        //store that in db upon submit
        petID = getArguments().getString("petID");
        petType = getArguments().getString("petType");
        petImageName = getArguments().getString("petImageName");
        petName = getArguments().getString("petName");
        petBreed = getArguments().getString("petBreed");
        petAge = getArguments().getString("petAge");
        petSex = getArguments().getString("petSex");
        petDescription = getArguments().getString("petDescription");
        petShelter = getArguments().getString("petShelter");
        shelterID = getArguments().getString("shelterID");
        shelterEmail = getArguments().getString("shelterEmail");
        adopterID = getArguments().getString("adopterID");
        adopterName = getArguments().getString("adopterName");
        adopterEmail = getArguments().getString("adopterEmail");
        adopterContact = getArguments().getString("adopterContact");
        adopterAddress = getArguments().getString("adopterAddress");
        dateApplied = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
        timeApplied = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());

        agreeCb = view.findViewById(R.id.agree_terms_adoption_cb);
        agreeCb.setOnClickListener(view1 -> {
            if(agreeCb.isChecked()){
                adopterAgreed = true;
            }else{
                adopterAgreed = false;
            }
        });


        intentionsTv = view.findViewById(R.id.intentions_adopter);

        backBtn = view.findViewById(R.id.btnBack);
        backBtn.setOnClickListener(v -> {
//                MyCancelDialogAdoptionForm myCancelDialogAdoptionForm = new MyCancelDialogAdoptionForm();
//                myCancelDialogAdoptionForm.show(getParentFragmentManager(), "My Fragment");
            MyCancelDialogGoToDogProfile myCancelDialogGoToDogProfile = new MyCancelDialogGoToDogProfile();
            myCancelDialogGoToDogProfile.show(getActivity().getSupportFragmentManager(), "My Fragment");
        });

//        backBtn = view.findViewById(R.id.btnBack);
//        backBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(petType.equals("dog")){
//                    Intent dogIntent = new Intent(view.getContext(), AdopterPerDogProfile.class);
//                    dogIntent.putExtra("PetID", petID);
//                    view.getContext().startActivity(dogIntent);
//                }else{
//                    Intent catIntent = new Intent(view.getContext(), AdopterPerCatProfile.class);
//                    catIntent.putExtra("PetID", petID);
//                    view.getContext().startActivity(catIntent);
//                }
//            }
//        });


        //BUTTONS
        submitForm = view.findViewById(R.id.btn_submit_adopter);
        submitForm.setOnClickListener(v -> {
            adopterIntentions = intentionsTv.getText().toString();
            if(!adopterAgreed){
                Toast.makeText(getContext(),"Please agree to terms and conditions.",Toast.LENGTH_LONG).show();
                return;
            }else if (adopterIntentions.isEmpty()){
                intentionsTv.setError("Please fill this up.");
                intentionsTv.requestFocus();
                return;
            }else{

                //update AdopterAllPets
                appliedToAdopt = "true";
                intentionsTv = view.findViewById(R.id.intentions_adopter);
                adopterIntentions = intentionsTv.getText().toString();
                System.out.println("adopterIntentions === " + adopterIntentions);
                System.out.println("dateApplied == " +  dateApplied);
                System.out.println("timeApplied == " +  timeApplied);

                updateDBs();

                MySaveDialogAdoptionForm mySaveDialogAdoptionForm = new MySaveDialogAdoptionForm();
                mySaveDialogAdoptionForm.setCancelable(false);
                mySaveDialogAdoptionForm.show(getParentFragmentManager(), "My Fragment");
            }
        });

        cancelForm = view.findViewById(R.id.adopterRegisterCancel);
        cancelForm.setOnClickListener(v -> {
            MyCancelDialogGoToDogProfile myCancelDialogGoToDogProfile = new MyCancelDialogGoToDogProfile();
            myCancelDialogGoToDogProfile.show(getActivity().getSupportFragmentManager(), "My Fragment");
        });

    }

    private void updateDBs() {
        adoptersDBRef.child(adopterID).child("AdopterAllPets").child(petID)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        snapshot.child("appliedToAdopt").getRef().setValue(appliedToAdopt);
                        applicationStatus = "Pending";
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                        String applicationID = reference.push().getKey();
                        System.out.println("applicationID === " + applicationID);

                        ForReviewApplicantsInfo forReviewApplicantsInfo = new ForReviewApplicantsInfo
                                (applicationID, dateApplied, timeApplied, adopterID, adopterName, adopterIntentions,
                                        petID, petType, petName, petBreed, petAge, petDescription, shelterID, applicationStatus);
                        //insert to designated shelter's db
                        sheltersDBRef.child(shelterID).child("ForReviewApplicants").child(applicationID).setValue(forReviewApplicantsInfo);
                        //add to adopters application history
                        adoptersDBRef.child(adopterID).child("ApplicationHistory").child(applicationID).setValue(forReviewApplicantsInfo);
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
            case R.id.adopter_yesPet:
                hasPet.setBackgroundColor(R.drawable.round_lightpurple);
                hasNoPet.setBackgroundColor(Color.GRAY);
                break;
            case R.id.adopter_noPet:
                hasNoPet.setBackgroundColor(R.drawable.round_lightpurple);
                hasNoPet.setBackgroundColor(Color.GRAY);
                break;
            case R.id.adopter_ownHome:
                ownHome.setBackgroundColor(R.drawable.round_lightpurple);
                rentHome.setBackgroundColor(Color.GRAY);
                break;
            case R.id.adopter_rentHome:
                rentHome.setBackgroundColor(R.drawable.round_lightpurple);
                ownHome.setBackgroundColor(Color.GRAY);
                break;
            case R.id.adopter_yesWork:
                hasWork.setBackgroundColor(R.drawable.round_lightpurple);
                hasNoWork.setBackgroundColor(Color.GRAY);
                break;
            case R.id.adopter_noWork:
                hasNoWork.setBackgroundColor(R.drawable.round_lightpurple);
                hasWork.setBackgroundColor(Color.GRAY);
                break;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TermsAndConditionsViewModel.class);
        // TODO: Use the ViewModel
    }


}