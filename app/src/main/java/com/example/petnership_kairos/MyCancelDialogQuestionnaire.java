package com.example.petnership_kairos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.auth.FirebaseAuth;

public class MyCancelDialogQuestionnaire extends DialogFragment{

    Button btnBack, btnCancel;

    FirebaseAuth auth = FirebaseAuth.getInstance();
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_cancel_dialog, container, false);

//        Intent intent = new Intent(getActivity(), ShelterDashboard.class);
        btnBack = view.findViewById(R.id.buttonYes);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShelterDashboard.class);
                startActivity(intent);
                dismiss();
//                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
//                ShelterDashboard shelterDashboard = new ShelterDashboard();
//                transaction.replace(R.id.user_change_email, shelterDashboard);
//                transaction.commit();
//                getParentFragmentManager().popBackStack();
                dismiss();
//
            }
        });

        btnCancel = view.findViewById(R.id.buttonNo);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }
}


