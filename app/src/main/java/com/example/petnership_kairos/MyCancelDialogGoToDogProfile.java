package com.example.petnership_kairos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

public class MyCancelDialogGoToDogProfile extends DialogFragment {

    Button btnOk, btnCancel;
//    private String petID;
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        petID = getArguments().getString("petID");
//    }

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_cancel_dialog, container, false);

        btnOk = view.findViewById(R.id.buttonYes);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //FRAGMENT to FRAGMENT
//                Intent dogIntent = new Intent(getActivity(), AdopterPerDogProfile.class);
//                Intent dogIntent = new Intent(view.getContext(), AdopterPerDogProfile.class);
//                dogIntent.putExtra("PetID", petID);
//                view.getContext().startActivity(dogIntent);
                getParentFragmentManager().popBackStack();
//                startActivity(dogIntent);
                dismiss();
//                Intent dogIntent = new Intent(view.getContext(), AdopterPerDogProfile.class);
//                dogIntent.putExtra("PetID", petID);
//                view.getContext().startActivity(dogIntent);
//                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
//                BrowseAnimals browseAnimals = new BrowseAnimals();
//                transaction.replace(R.id.adoptionForm_TAndC, browseAnimals);
//                transaction.commit();
//                dismiss();
            }
        });

        btnCancel = view.findViewById(R.id.buttonNo);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return view;
    }
}
