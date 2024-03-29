package com.example.petnership_kairos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.auth.FirebaseAuth;

public class MyCancelDialogEdit extends DialogFragment{

    Button btnBack, btnCancel;

    FirebaseAuth auth = FirebaseAuth.getInstance();
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_cancel_dialog, container, false);

        btnBack = view.findViewById(R.id.buttonYes);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FRAGMENT to ACTIVITY
                getParentFragmentManager().popBackStack();
                setCancelable(false);
                dismiss();

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


