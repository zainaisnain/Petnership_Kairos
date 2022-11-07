package com.example.petnership_kairos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;

public class MySaveDialogAdoptionForm extends DialogFragment {

    Button btnOk;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_submit_dialog, container, false);

        btnOk = view.findViewById(R.id.buttonOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //FRAGMENT to FRAGMENT
                Intent intent = new Intent(getActivity(), AdopterDashboard.class);
                startActivity(intent);
//                dismiss();
//                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
//                BrowseAnimals browseAnimals = new BrowseAnimals();
//                transaction.replace(R.id.adoptionForm_TAndC, browseAnimals);
//                transaction.commit();
//                getParentFragmentManager().popBackStack();
//                setCancelable(false);
                dismiss();
            }
        });
        return view;
    }
}
