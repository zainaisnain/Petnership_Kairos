package com.example.petnership_kairos;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;

public class MySaveDialogEdit extends DialogFragment {

    Button btnBack, btnCancel;

    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.save_dialog, container, false);
        btnBack = view.findViewById(R.id.buttonOk);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ShelterDashboard.class);
                startActivity(intent);
               // getParentFragmentManager().popBackStack();
                //getActivity().recreate();
                setCancelable(false);
                dismiss();
            }
        });

  /*
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FRAGMENT to ACTIVITY
//                Fragment frg = null;
//                frg = getParentFragmentManager().findFragmentById()
//                Intent intent = new Intent (getActivity(), ShelterPerDogProfile.class);
//                onDetach();
//                startActivity(intent);
//                dismiss();
//                    onBackPressed();
//                CatPetProfileSummary catPetProfileSummary = new CatPetProfileSummary();
//                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
//                transaction.commit();

                Intent intent = new Intent(getActivity(),ShelterPerDogProfile.class);
                startActivity(intent);
                //getParentFragmentManager().popBackStack();
                setCancelable(false);
                dismiss();

//                transaction.replace(R.id.shelter_edit_cat_frag, catPetProfileSummary);


//                ShelterPerCatProfile shelterPerCatProfile = new ShelterPerCatProfile();
//                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
//                transaction.replace(R.id.shelter_edit_cat_frag, shelterPerCatProfile);
//                transaction.commit();
//                Intent intent = new Intent(getActivity(), ShelterPerCatProfile.class);
//                startActivity(intent);
            }
        }); */
        return view;
    }

//    public void onBackPressed() {
//        FragmentManager fm = getParentFragmentManager();
//        int backCount = fm.getBackStackEntryCount();
//        System.out.println("Backstack entry count: " + fm.getBackStackEntryCount());
//        for (int i = 0; i < fm.getBackStackEntryCount(); i++) {
//            System.out.println("--- " + fm.getBackStackEntryAt(i).getName());
//        }
//
//        int count = getParentFragmentManager().getBackStackEntryCount();
//        if (count <= 1) {
//            MyLogoutDialog logoutDialog = new MyLogoutDialog();
//            logoutDialog.show(getParentFragmentManager(), "My Fragment");
//
//        }
//        else {
//            System.out.println("Popped");
//            getParentFragmentManager().popBackStack();
//        }
//    }

}


