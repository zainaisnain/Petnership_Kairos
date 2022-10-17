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
import android.widget.Toast;

public class ShelterToReviewApplications extends Fragment {

    String[] statusApp = {"In progress", "Rejected", "Approved"};

    AutoCompleteTextView statusAppTxt;

    ArrayAdapter<String> adapterItems;

    private EditText mEditTextTo, mEditTextSubject, mEditTextMessage;

    private ShelterToReviewApplicationsViewModel mViewModel;

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

        Button sendBtn = view.findViewById(R.id.shelter_sendEmail);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });

        statusAppTxt = view.findViewById(R.id.auto_complete);
//        adapterItems = new ArrayAdapter<String>(MainActivity, R.layout.list_item, statusApp);

        statusAppTxt.setAdapter(adapterItems);
        statusAppTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String item = parent.getItemAtPosition();
            }
        });

        return view;
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
        mViewModel = new ViewModelProvider(this).get(ShelterToReviewApplicationsViewModel.class);
        // TODO: Use the ViewModel
    }

}