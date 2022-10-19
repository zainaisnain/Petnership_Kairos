package com.example.petnership_kairos;

import androidx.lifecycle.ViewModelProvider;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;

public class ShelterToReviewApplications extends Fragment {

    String[] statusApp = {"In progress", "Rejected", "Approved"};

    Spinner statusAppTxt;

    ArrayAdapter<String> statusAdapter;
    protected static String petStatus;

    private EditText mEditTextTo, mEditTextSubject, mEditTextMessage;
    private TextView tvAdoptionFormDate, tvAdopterName, tvAdopterEmail,
    tvAdopterMobile, tvAdopterAddress, tvPetType, tvBreed,
    tvPetName, tvPetAge, tvPetDesc;
    //DB REF

    //FirebaseAuth


    public static ShelterToReviewApplications newInstance() {
        return new ShelterToReviewApplications();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_shelter_to_review_applications, container, false);

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

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Adoption Form Summary");



        statusAppTxt = view.findViewById(R.id.pet_status);
        ArrayAdapter<String> statusAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, statusApp);
        statusAppTxt.setAdapter(statusAdapter);

        statusAppTxt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                petStatus = adapterView.getItemAtPosition(i).toString();
                System.out.println("petStatus inside: " + petStatus);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //retrieve from db
        //show those in designated fields
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