package com.example.petnership_kairos;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;

public class MyCancelDialogGoToDogProfile extends DialogFragment{

    Button btnBack, btnCancel;

    FirebaseAuth auth = FirebaseAuth.getInstance();
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.cancel2_dialog, container, false);

        btnBack = view.findViewById(R.id.buttonOk);
        btnBack.setOnClickListener(v -> {
            //FRAGMENT to ACTIVITY
//                getChildFragmentManager().popBackStack();
            getParentFragmentManager().popBackStack();
            dismiss();
//                Fragment fragment = new ShelterEditDog();
//
//                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
//                transaction.replace(R.id.adoptionForm_TAndC, fragment).addToBackStack(null).commit();

        });

        btnCancel = view.findViewById(R.id.buttonCancel);
        btnCancel.setOnClickListener(v -> dismiss());
        return view;
    }
}


