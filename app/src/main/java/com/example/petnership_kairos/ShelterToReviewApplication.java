package com.example.petnership_kairos;

import android.content.Intent;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShelterToReviewApplication extends Fragment {

    String[] statusApp = {"In progress", "Rejected", "Approved"};

    Spinner statusAppTxt;

    Button saveApplication, cancelApplication;

    ArrayAdapter<String> statusAdapter;
    protected static String applicationStatus;

    private EditText mEditTextTo, mEditTextSubject, mEditTextMessage;
    private TextView tvAdoptionFormDate, tvAdopterName, tvAdopterEmail,
    tvAdopterMobile, tvAdopterAddress, tvPetType, tvBreed,
    tvPetName, tvPetAge, tvPetDesc;

    String applicationID, dateApplied, timeApplied, adopterID, adopterIntentions,
            petID, petType, petName, petBreed, petAge, petDescription, shelterID, shelterEmail;
    //DB REF
    DatabaseReference allPetsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("AllPets");
    DatabaseReference adoptersDBRef = FirebaseDatabase.getInstance().getReference("Adopters");
    DatabaseReference sheltersDBRef = FirebaseDatabase.getInstance().getReference("Shelters");
    DatabaseReference usersDBRef = FirebaseDatabase.getInstance().getReference("Users");

    //FirebaseAuth
    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;

    public static ShelterToReviewApplication newInstance() {
        return new ShelterToReviewApplication();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_shelter_to_review_application, container, false);

        mEditTextTo = view.findViewById(R.id.shelter_emailAddressOfAdopter);
        mEditTextSubject = view.findViewById(R.id.shelter_subjectEmailToAdopter);
        mEditTextMessage = view.findViewById(R.id.shelter_bodyEmailToAdopter);
        tvAdoptionFormDate = view.findViewById(R.id.adoptionForm_petDate);
        tvAdopterName = view.findViewById(R.id.adoptionForm_adopterName);
        tvAdopterEmail = view.findViewById(R.id.adoptionForm_email);
        tvAdopterMobile = view.findViewById(R.id.adoptionForm_mobile);
        tvAdopterAddress = view.findViewById(R.id.adoptionForm_address);
        tvPetType = view.findViewById(R.id.adoptionForm_petType);
        tvBreed = view.findViewById(R.id.adoptionForm_petBreed);
        tvPetName = view.findViewById(R.id.adoptionForm_petName);
        tvPetAge = view.findViewById(R.id.adoptionForm_petAge);
        tvPetDesc = view.findViewById(R.id.adoptionForm_petDescription);
        
        Button sendBtn = view.findViewById(R.id.shelter_sendEmail);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });

        saveApplication = view.findViewById(R.id.btn_save_adoptionForm);
        saveApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyCustomDialog submitDialog = new MyCustomDialog();
                submitDialog.show(getParentFragmentManager(), "My Fragment");
            }
        });

        cancelApplication = view.findViewById(R.id.btn_cancel_adoptionForm);
        cancelApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyCancelDialog cancelDialog = new MyCancelDialog();
                cancelDialog.show(getParentFragmentManager(), "My Fragment");
            }
        });

        return view;
    }
    

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Adoption Form Summary");
        populateTextViews();

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        shelterEmail = firebaseUser.getEmail();

        statusAppTxt = view.findViewById(R.id.shelter_adoption_application_status);
        ArrayAdapter<String> statusAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, statusApp);
        statusAppTxt.setAdapter(statusAdapter);

        statusAppTxt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                applicationStatus = adapterView.getItemAtPosition(i).toString();
                System.out.println("applicationStatus inside: " + applicationStatus);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //retrieve from db
        //show those in designated fields
    }
    
    private void populateTextViews(){

        usersDBRef.orderByChild("email").equalTo(shelterEmail).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot usersSnapshot) {
                if(usersSnapshot.exists()) {
                    for (DataSnapshot ds : usersSnapshot.getChildren()) {
                        shelterID = ds.getKey();
                    }


                    sheltersDBRef.child(shelterID).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild("ForReviewApplicants")){
                                sheltersDBRef.child(shelterID).child("ForReviewApplicants").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

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

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void sendMail() {
        String recipientList = mEditTextTo.getText().toString();
        String[] recipients = recipientList.split(",");

        String subject = mEditTextSubject.getText().toString();
        String message = mEditTextMessage.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client: "));

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}