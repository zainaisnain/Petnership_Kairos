package com.example.petnership_kairos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

public class MyCancelDialogShelter extends DialogFragment {

    Button btnOk;

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.cancel_dialog, container, false);

        btnOk = view.findViewById(R.id.buttonOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //FRAGMENT to FRAGMENT
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                ShelterToReviewApplication shelterToReviewApplication = new ShelterToReviewApplication();
                transaction.replace(R.id.to_review_application_shelter, shelterToReviewApplication);
                transaction.commit();
                dismiss();
            }
        });
        return view;
    }
}
