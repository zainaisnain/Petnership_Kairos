package com.example.petnership_kairos;

import androidx.lifecycle.ViewModelProvider;

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

public class AdoptionForm extends Fragment implements View.OnClickListener{

    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;
    private String adopterEmail, adopterID, appliedToAdopt, timeStamp;

    protected static String petID, petImageName, petName, petBreed, petAge, petSex,
            petDescription, petShelter, shelterID;

    DatabaseReference usersDBRef = FirebaseDatabase.getInstance().getReference("Users");
    DatabaseReference adoptersDBRef = FirebaseDatabase.getInstance().getReference("Adopters");
    DatabaseReference sheltersDBRef = FirebaseDatabase.getInstance().getReference("Shelters");
    Button submitForm, cancelForm;
    CheckBox agreeCb;
    EditText intentionsTv;

    String adopterIntentions;
    boolean adopterAgreed;
    private AdoptionFormViewModel mViewModel;

    public static AdoptionForm newInstance() {
        return new AdoptionForm();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_adoption_form, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Adoption Form");

        //Get adopter's infos
        //get pet infos
        //store that in db upon submit
        petID = getArguments().getString("petID");
        petImageName = getArguments().getString("petImageName");
        petName = getArguments().getString("petName");
        petBreed = getArguments().getString("petBreed");
        petAge = getArguments().getString("petAge");
        petSex = getArguments().getString("petSex");
        petDescription = getArguments().getString("petDescription");
        petShelter = getArguments().getString("petShelter");
        shelterID = getArguments().getString("shelterID");
        adopterID = getArguments().getString("adopterID");
        adopterEmail = getArguments().getString("adopterEmail");
        timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        System.out.println("timeStamp == " +  timeStamp);

        agreeCb = view.findViewById(R.id.agree_terms_adoption_cb);
        agreeCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(agreeCb.isChecked()){
                    adopterAgreed = true;
                    System.out.println("adopterAgreed == " + adopterAgreed);
                }else{
                    adopterAgreed = false;
                    System.out.println("adopterAgreed == " + adopterAgreed);
                }
            }
        });


        intentionsTv = view.findViewById(R.id.intentions_adopter);

        //BUTTONS
        submitForm = view.findViewById(R.id.btn_submit_adopter);
        submitForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    updateDBs();

                    //add to db
                    MyCustomDialog submitDialog = new MyCustomDialog();
                    submitDialog.show(getParentFragmentManager(), "My Fragment");
                }
            }
        });

        cancelForm = view.findViewById(R.id.adopterRegisterCancel);
        cancelForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyCancelDialog cancelDialog = new MyCancelDialog();
                cancelDialog.show(getParentFragmentManager(), "My Fragment");
            }
        });

    }

    private void updateDBs() {
        adoptersDBRef.child(adopterID).child("AdopterAllPets").child(petID)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        snapshot.child("appliedToAdopt").getRef().setValue(appliedToAdopt);

                        ForReviewApplicantsInfo forReviewApplicantsInfo = new ForReviewApplicantsInfo(adopterID, adopterEmail, petID, timeStamp);
                        //insert to designated shelter's db
                        sheltersDBRef.child("ForReviewApplicants").setValue(forReviewApplicantsInfo);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    @Override
    public void onClick(View v) {}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AdoptionFormViewModel.class);
        // TODO: Use the ViewModel
    }


}