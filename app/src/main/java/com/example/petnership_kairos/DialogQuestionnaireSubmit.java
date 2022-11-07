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
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;

public class DialogQuestionnaireSubmit extends DialogFragment{

    MCDM mainAlgo;
    MCDMAnswersViewModel mViewModel;
    Button buttonSubmitYes, buttonSubmitNo;
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_dialog_questionnaire_submit, container, false);
        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);

        // begin data fetch
        mainAlgo = new MCDM();
        int animalType = mViewModel.getAnimalType().equalsIgnoreCase("Dog") ? 1 : 2;
        mainAlgo.fetchAlternatives(getContext(), animalType);

        buttonSubmitYes = view.findViewById(R.id.buttonSubmitYes);
        buttonSubmitYes.setOnClickListener(v -> {


            mViewModel.setFinishedAnswering(true);
            mainAlgo.beginMCDM();

            // change screen
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            FragmentRecommendedPets recPets = new FragmentRecommendedPets();
            transaction.replace(R.id.nav_host_fragment,recPets);
            transaction.addToBackStack("recommendedPets");
            transaction.commit();

            dismiss();
//
        });

        buttonSubmitNo = view.findViewById(R.id.buttonSubmitNo);
        buttonSubmitNo.setOnClickListener(v -> dismiss());
        return view;
    }
}


