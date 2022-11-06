package com.example.petnership_kairos;

import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
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
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TermsAndConditions extends Fragment implements View.OnClickListener{

    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;
    private String adopterID, adopterName, adopterEmail, adopterContact, adopterAddress, adopterBirthday,
            petID, petType, petName, petBreed, petAge, petDescription, dateApplied, timeApplied, applicationStatus;
    private double matchPercentage = 0.0;
    private String petPolicy, yard, children, company, position, petNames, petBreeds, vet, convicted, hours, emergency, crate, references;
    private String ownOrRentHome, hasYard, hasChildren, hasWork, hasPet, hasSurrendered, isConvicted;
    private TextView tvPetName, tvPetAge, tvPetSex, tvPetBreed, tvMatch, tvAdopterName, tvAdopterBirthday, tvAdopterEmail, tvAdopterContact, tvAdopterAddress;
    private EditText etPetPolicy, etYard, etChildren, etCompany, etPosition, etPetNames, etPetBreeds, etVet, etConvicted, etHours, etEmergency, etCrate, etReferences, etIntentions;

    protected static String petImageName, petSex, petShelter, shelterID, shelterEmail;

    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    DatabaseReference usersDBRef = FirebaseDatabase.getInstance().getReference("Users");
    DatabaseReference adoptersDBRef = FirebaseDatabase.getInstance().getReference("Adopters");
    DatabaseReference sheltersDBRef = FirebaseDatabase.getInstance().getReference("Shelters");
    Button submitForm, cancelForm;
    ImageButton backBtn;
    CheckBox agreeCb;
    ImageView ivImage;
    RadioGroup rgHome;

    private AppCompatRadioButton rbYesPet, rbNoPet, rbOwnHome, rbRentHome, rbYesWork, rbNoWork, rbYesYard, rbNoYard, rbYesChildren, rbNoChildren, rbYesSurrender, rbNoSurrender, rbYesConvicted, rbNoConvicted;

    String adopterIntentions, appliedToAdopt;
    boolean adopterAgreed = false;

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
        adopterBirthday = getArguments().getString("adopterBirthday");
        adopterAddress = getArguments().getString("adopterAddress");

        //radio buttons
        rbYesPet = view.findViewById(R.id.adoption_answer_yesPet);
        rbYesPet.setOnClickListener(this);
        rbNoPet = view.findViewById(R.id.adoption_answer_noPet);
        rbNoPet.setOnClickListener(this);

        rbOwnHome = view.findViewById(R.id.adoption_home_ownHome);
        rbOwnHome.setOnClickListener(this);
        rbRentHome = view.findViewById(R.id.adoption_home_rentHome);
        rbRentHome.setOnClickListener(this);

        rbYesWork = view.findViewById(R.id.adoption_answer_yesWork);
        rbYesWork.setOnClickListener(this);
        rbNoWork = view.findViewById(R.id.adoption_answer_noWork);
        rbNoWork.setOnClickListener(this);

        rbYesYard = view.findViewById(R.id.adoption_home_yesYard);
        rbYesYard.setOnClickListener(this);
        rbNoYard = view.findViewById(R.id.adoption_home_noYard);
        rbNoYard.setOnClickListener(this);

        rbYesChildren = view.findViewById(R.id.adoption_home_yesChildren);
        rbYesChildren.setOnClickListener(this);
        rbNoChildren = view.findViewById(R.id.adoption_home_noChildren);
        rbNoChildren.setOnClickListener(this);

        rbYesSurrender = view.findViewById(R.id.adoption_answer_yesSurrender);
        rbYesSurrender.setOnClickListener(this);
        rbNoSurrender = view.findViewById(R.id.adoption_answer_noSurrender);
        rbNoSurrender.setOnClickListener(this);

        rbYesConvicted = view.findViewById(R.id.adoption_answer_yesConvicted);
        rbYesConvicted.setOnClickListener(this);
        rbNoConvicted = view.findViewById(R.id.adoption_answer_noConvicted);
        rbNoConvicted.setOnClickListener(this);

        // Radio Group
        rgHome = view.findViewById(R.id.radio_home);

        // grab textviews
        tvPetName = view.findViewById(R.id.adoption_pet_name_value);
        tvPetAge = view.findViewById(R.id.adoption_pet_age_value);
        tvPetSex = view.findViewById(R.id.adoption_pet_sex_value);
        tvPetBreed = view.findViewById(R.id.adoption_pet_breed_value);
        tvMatch = view.findViewById(R.id.adoption_pet_match_value);
        tvAdopterName = view.findViewById(R.id.adoption_name_value);
        tvAdopterBirthday = view.findViewById(R.id.adoption_birthday_value);
        tvAdopterEmail = view.findViewById(R.id.adoption_email_value);
        tvAdopterContact = view.findViewById(R.id.adoption_contact_value);
        tvAdopterAddress = view.findViewById(R.id.adoption_address_value);

        // grab edittext
        etPetPolicy = view.findViewById(R.id.adoption_home_ifyespetpolicy);
        etYard = view.findViewById(R.id.adoption_home_ifyesyard);
        etChildren = view.findViewById(R.id.adoption_home_ifyeschildren);
        etCompany = view.findViewById(R.id.adoption_work_company);
        etPosition = view.findViewById(R.id.adoption_work_position);
        etPetNames = view.findViewById(R.id.adoption_pet_names);
        etPetBreeds = view.findViewById(R.id.adoption_pet_breeds);
        etVet = view.findViewById(R.id.adoption_pet_vet);
        etConvicted = view.findViewById(R.id.adoption_pet_ifyesconvicted);
        etHours = view.findViewById(R.id.adoption_lifestyle_hours);
        etEmergency = view.findViewById(R.id.adoption_lifestyle_emergency);
        etCrate = view.findViewById(R.id.adoption_lifestyle_crate);
        etReferences = view.findViewById(R.id.adoption_references_answer);
        etIntentions = view.findViewById(R.id.intentions_adopter);

        // ImageView
        ivImage = view.findViewById(R.id.adoption_image);

        if(petImageName != null) {
            storageReference.child("Pets/").child(petImageName).getDownloadUrl()
                    .addOnSuccessListener(uri -> {
                        Glide.with(getActivity()).load(uri.toString()).into(ivImage);
                    });
        }

        // set text views
        tvPetName.setText(petName);
        tvPetAge.setText(petAge);
        tvPetSex.setText(petSex);
        tvPetBreed.setText(petBreed);
        tvMatch.setText("44.00"); // TODO: FIX MATCH
        tvAdopterName.setText(adopterName);
        tvAdopterBirthday.setText(adopterBirthday);
        tvAdopterEmail.setText(adopterEmail);
        tvAdopterContact.setText(adopterContact);
        tvAdopterAddress.setText(adopterAddress);



        agreeCb = view.findViewById(R.id.agree_terms_adoption_cb);
        agreeCb.setOnClickListener(view1 -> {
            if(agreeCb.isChecked()){
                adopterAgreed = true;
            }else{
                adopterAgreed = false;
            }
        });


        etIntentions = view.findViewById(R.id.intentions_adopter);

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

            petPolicy = etPetPolicy.getText().toString();
            yard = etYard.getText().toString();
            children = etChildren.getText().toString();
            company = etCompany.getText().toString();
            position = etPosition.getText().toString();
            petBreeds = etPetBreeds.getText().toString();
            petNames = etPetNames.getText().toString();
            vet = etVet.getText().toString();
            convicted = etConvicted.getText().toString();
            hours = etHours.getText().toString();
            emergency = etEmergency.getText().toString();
            crate = etCrate.getText().toString();
            references = etReferences.getText().toString();
            adopterIntentions = etIntentions.getText().toString();

            clearErrorAndFocus();
            validateInputs();
        });

        cancelForm = view.findViewById(R.id.adopterRegisterCancel);
        cancelForm.setOnClickListener(v -> {
            MyCancelDialogGoToDogProfile myCancelDialogGoToDogProfile = new MyCancelDialogGoToDogProfile();
            myCancelDialogGoToDogProfile.show(getActivity().getSupportFragmentManager(), "My Fragment");

        });

    }
    private void validateInputs() {
        boolean validated = true;
        if(!rbOwnHome.isActivated() && !rbRentHome.isActivated()){
            rbOwnHome.setError("Please select an option");
            rbOwnHome.setFocusable(true);
            rbOwnHome.requestFocus();
            rbRentHome.setError("Please select an option");
            rbRentHome.setFocusable(true);
            rbRentHome.requestFocus();

            validated = false;
        }
        if(!rbYesYard.isActivated() && !rbNoYard.isActivated()){

            rbYesYard.setError("Please select an option");
            rbYesYard.requestFocus();
            rbNoYard.setError("Please select an option");
            rbNoYard.requestFocus();
            validated = false;
        }if(!rbYesChildren.isActivated() && !rbNoChildren.isActivated()){


            rbYesChildren.setError("Please select an option");
            rbYesChildren.requestFocus();
            rbNoChildren.setError("Please select an option");
            rbNoChildren.requestFocus();
            validated = false;
        }
        if(!rbYesWork.isActivated() && !rbNoWork.isActivated()){


            rbYesWork.setError("Please select an option");
            rbYesWork.requestFocus();
            rbNoWork.setError("Please select an option");
            rbNoWork.requestFocus();validated = false;
        }
        if(!rbYesPet.isActivated() && !rbNoPet.isActivated()){


            rbYesPet.setError("Please select an option");
            rbYesPet.requestFocus();
            rbNoPet.setError("Please select an option");
            rbNoPet.requestFocus();
            validated = false;
        }
        if(!rbYesSurrender.isActivated() && !rbNoSurrender.isActivated()){


            rbYesSurrender.setError("Please select an option");
            rbYesSurrender.requestFocus();
            rbNoSurrender.setError("Please select an option");
            rbNoSurrender.requestFocus();
            validated = false;
        }
        if(!rbYesConvicted.isActivated() && !rbNoConvicted.isActivated()){


            rbYesConvicted.setError("Please select an option");
            rbYesConvicted.requestFocus();
            rbNoConvicted.setError("Please select an option");
            rbNoConvicted.requestFocus();
            validated = false;
        }
        if(rbRentHome.isActivated() && petPolicy.isEmpty()) {
            etPetPolicy.setError("Please provide details");
            etPetPolicy.requestFocus();
            validated = false;
        }
        if(rbYesYard.isActivated() && yard.isEmpty()) {
            etYard.setError("Please provide details");
            etYard.requestFocus();
            validated = false;
        }
        if(rbYesChildren.isActivated() && children.isEmpty()) {
            etChildren.setError("Please provide details");
            etChildren.requestFocus();
            validated = false;
        }
        if(rbYesWork.isActivated() && (company.isEmpty() || position.isEmpty())) {
            if(company.isEmpty()) {
                etCompany.setError("Please provide details");
                etCompany.requestFocus();
            }if(position.isEmpty()) {
                etPosition.setError("Please provide details");
                etPosition.requestFocus();
            }
            validated = false;

        }
        if(rbYesPet.isActivated() && (petNames.isEmpty() || petBreeds.isEmpty() || vet.isEmpty())) {
            if(petNames.isEmpty()) {
                etPetNames.setError("Please provide details");
                etPetNames.requestFocus();
            }if(petBreeds.isEmpty()) {
                etPetBreeds.setError("Please provide details");
                etPetBreeds.requestFocus();
            }if(vet.isEmpty()) {
                etVet.setError("Please provide details");
                etVet.requestFocus();
            }
            validated = false;

        }
        if(rbYesConvicted.isActivated() && convicted.isEmpty()) {
            etConvicted.setError("Please provide details");
            etConvicted.requestFocus();
            validated = false;
        }
        if(hours.isEmpty()) {
            etHours.setError("Required field");
            etHours.requestFocus();
            validated = false;
        }if(emergency.isEmpty()) {
            etEmergency.setError("Required field");
            etEmergency.requestFocus();
            validated = false;
        }if(crate.isEmpty()) {
            etCrate.setError("Required field");
            etCrate.requestFocus();
            validated = false;
        }if(references.isEmpty()) {
            etReferences.setError("Required field");
            etReferences.requestFocus();
            validated = false;
        }
        if (adopterIntentions.isEmpty()){
            etIntentions.setError("Required field");
            etIntentions.requestFocus();
            validated = false;
        }

        if(!adopterAgreed){
            agreeCb.setError("Please agree to the terms and conditions");
            agreeCb.requestFocus();
            validated = false;
        }
        if(validated){

            //update AdopterAllPets

            appliedToAdopt = "Yes";

            updateDBs();

            MySaveDialogAdoptionForm mySaveDialogAdoptionForm = new MySaveDialogAdoptionForm();
            mySaveDialogAdoptionForm.setCancelable(false);
            mySaveDialogAdoptionForm.show(getParentFragmentManager(), "My Fragment");
        }
        else {
            Toast.makeText(getContext(),"Please answer all required fields",Toast.LENGTH_LONG).show();

        }
    }
    private void clearErrorAndFocus() {
        if (rbOwnHome.isActivated() || rbRentHome.isActivated()) {
            rbOwnHome.setError(null);
            rbOwnHome.clearFocus();
            rbRentHome.setError(null);
            rbRentHome.clearFocus();
        }
        if (rbYesYard.isActivated() || rbNoYard.isActivated()) {

            rbYesYard.setError(null);
            rbYesYard.clearFocus();
            rbNoYard.setError(null);
            rbNoYard.clearFocus();
        }
        if (rbYesChildren.isActivated() || rbNoChildren.isActivated()) {


            rbYesChildren.setError(null);
            rbYesChildren.clearFocus();
            rbNoChildren.setError(null);
            rbNoChildren.clearFocus();
        }
        if (rbYesWork.isActivated() || rbNoWork.isActivated()) {


            rbYesWork.setError(null);
            rbYesWork.clearFocus();
            rbNoWork.setError(null);
            rbNoWork.clearFocus();
        }
        if (rbYesPet.isActivated() || rbNoPet.isActivated()) {


            rbYesPet.setError(null);
            rbYesPet.clearFocus();
            rbNoPet.setError(null);
            rbNoPet.clearFocus();
        }
        if (rbYesSurrender.isActivated() || rbNoSurrender.isActivated()) {


            rbYesSurrender.setError(null);
            rbYesSurrender.clearFocus();
            rbNoSurrender.setError(null);
            rbNoSurrender.clearFocus();
        }
        if (rbYesConvicted.isActivated() || rbNoConvicted.isActivated()) {

            rbYesConvicted.setError(null);
            rbYesConvicted.clearFocus();
            rbNoConvicted.setError(null);
            rbNoConvicted.clearFocus();
        }
        if ((rbRentHome.isActivated() && !petPolicy.isEmpty()) || rbOwnHome.isActivated()) {
            etPetPolicy.setError(null);
            etPetPolicy.clearFocus();
        }
        if ((rbYesYard.isActivated() && !yard.isEmpty()) || rbNoYard.isActivated()) {
            etYard.setError(null);
            etYard.clearFocus();
        }
        if ((rbYesChildren.isActivated() && !children.isEmpty()) || rbNoChildren.isActivated()) {
            etChildren.setError(null);
            etChildren.clearFocus();
        }
        if ((rbYesWork.isActivated() && (!company.isEmpty() || !position.isEmpty())) || rbNoWork.isActivated()) {
            if (!company.isEmpty()) {
                etCompany.setError(null);
                etCompany.clearFocus();
            }
            if (!position.isEmpty()) {
                etPosition.setError(null);
                etPosition.clearFocus();
            }

        }
        if ((rbYesPet.isActivated() && (!petNames.isEmpty() || !petBreeds.isEmpty() || !vet.isEmpty())) || rbNoPet.isActivated()) {
            if (!petNames.isEmpty()) {
                etPetNames.setError(null);
                etPetNames.clearFocus();
            }
            if (!petBreeds.isEmpty()) {
                etPetBreeds.setError(null);
                etPetBreeds.clearFocus();
            }
            if (!vet.isEmpty()) {
                etVet.setError(null);
                etVet.clearFocus();
            }

        }
        if ((rbYesConvicted.isActivated() && !convicted.isEmpty()) || rbNoConvicted.isActivated()) {
            etConvicted.setError(null);
            etConvicted.clearFocus();
        }
        if (adopterAgreed) {

            agreeCb.setError(null);
            agreeCb.clearFocus();
        }
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

                        // get date and time
                        dateApplied = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
                        timeApplied = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
                        System.out.println("applicationID === " + applicationID);

                        ownOrRentHome = (rbRentHome.isActivated() ? "Rented" : "Owned");
                        hasYard = (rbYesYard.isActivated() ? "Yes" : "No");
                        hasChildren = (rbYesChildren.isActivated() ? "Yes" : "No");
                        hasWork = (rbYesWork.isActivated() ? "Yes" : "No");
                        hasPet = (rbYesPet.isActivated() ? "Yes" : "No");
                        hasSurrendered = (rbYesSurrender.isActivated() ? "Yes" : "No");
                        isConvicted = (rbYesConvicted.isActivated() ? "Yes" : "No");

                        ForReviewApplicantsInfo forReviewApplicantsInfo = new ForReviewApplicantsInfo
                                (appliedToAdopt, applicationID, dateApplied, timeApplied, adopterID, adopterName, ownOrRentHome, petPolicy, hasYard, yard, hasChildren, children, hasWork, company, position, hasPet, petNames, petBreeds, hasSurrendered, vet, isConvicted, convicted, hours, emergency, crate, references, adopterIntentions, petID, petType, petName, petBreed, petAge, petDescription, shelterID, applicationStatus);
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
            case R.id.adoption_answer_yesPet:
                rbYesPet.setBackgroundColor(R.drawable.round_lightpurple);
                rbNoPet.setBackgroundColor(Color.GRAY);
                rbYesPet.setActivated(true);
                rbNoPet.setActivated(false);
                break;
            case R.id.adoption_answer_noPet:
                rbNoPet.setBackgroundColor(R.drawable.round_lightpurple);
                rbYesPet.setBackgroundColor(Color.GRAY);
                rbNoPet.setActivated(true);
                rbYesPet.setActivated(false);
                break;
            case R.id.adoption_home_ownHome:
                rbOwnHome.setBackgroundColor(R.drawable.round_lightpurple);
                rbRentHome.setBackgroundColor(Color.GRAY);
                rbOwnHome.setActivated(true);
                rbRentHome.setActivated(false);
                break;
            case R.id.adoption_home_rentHome:
                rbRentHome.setBackgroundColor(R.drawable.round_lightpurple);
                rbOwnHome.setBackgroundColor(Color.GRAY);
                rbRentHome.setActivated(true);
                rbOwnHome.setActivated(false);
                break;
            case R.id.adoption_answer_yesWork:
                rbYesWork.setBackgroundColor(R.drawable.round_lightpurple);
                rbNoWork.setBackgroundColor(Color.GRAY);
                rbYesWork.setActivated(true);
                rbNoWork.setActivated(false);
                break;
            case R.id.adoption_answer_noWork:
                rbNoWork.setBackgroundColor(R.drawable.round_lightpurple);
                rbYesWork.setBackgroundColor(Color.GRAY);
                rbNoWork.setActivated(true);
                rbYesWork.setActivated(false);
                break;
            case R.id.adoption_home_yesYard:
                rbYesYard.setBackgroundColor(R.drawable.round_lightpurple);
                rbNoYard.setBackgroundColor(Color.GRAY);
                rbYesYard.setActivated(true);
                rbNoYard.setActivated(false);
                break;
            case R.id.adoption_home_noYard:
                rbNoYard.setBackgroundColor(R.drawable.round_lightpurple);
                rbYesYard.setBackgroundColor(Color.GRAY);
                rbNoYard.setActivated(true);
                rbYesYard.setActivated(false);
                break;
            case R.id.adoption_home_yesChildren:
                rbYesChildren.setBackgroundColor(R.drawable.round_lightpurple);
                rbNoChildren.setBackgroundColor(Color.GRAY);
                rbYesChildren.setActivated(true);
                rbNoChildren.setActivated(false);
                break;
            case R.id.adoption_home_noChildren:
                rbNoChildren.setBackgroundColor(R.drawable.round_lightpurple);
                rbYesChildren.setBackgroundColor(Color.GRAY);
                rbNoChildren.setActivated(true);
                rbYesChildren.setActivated(false);
                break;
            case R.id.adoption_answer_yesSurrender:
                rbYesSurrender.setBackgroundColor(R.drawable.round_lightpurple);
                rbNoSurrender.setBackgroundColor(Color.GRAY);
                rbYesSurrender.setActivated(true);
                rbNoSurrender.setActivated(false);
                break;
            case R.id.adoption_answer_noSurrender:
                rbNoSurrender.setBackgroundColor(R.drawable.round_lightpurple);
                rbYesSurrender.setBackgroundColor(Color.GRAY);
                rbNoSurrender.setActivated(true);
                rbYesSurrender.setActivated(false);
                break;
            case R.id.adoption_answer_yesConvicted:
                rbYesConvicted.setBackgroundColor(R.drawable.round_lightpurple);
                rbNoConvicted.setBackgroundColor(Color.GRAY);
                rbYesConvicted.setActivated(true);
                rbNoConvicted.setActivated(false);
                break;
            case R.id.adoption_answer_noConvicted:
                rbNoConvicted.setBackgroundColor(R.drawable.round_lightpurple);
                rbYesConvicted.setBackgroundColor(Color.GRAY);
                rbNoConvicted.setActivated(true);
                rbYesConvicted.setActivated(false);
                break;
        }
    }



}