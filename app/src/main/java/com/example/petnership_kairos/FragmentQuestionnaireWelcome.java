package com.example.petnership_kairos;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.cardview.widget.CardView;
import android.widget.Button;
import android.widget.ImageButton;

public class FragmentQuestionnaireWelcome extends Fragment {

    public static FragmentQuestionnaireWelcome newInstance() {
        return new FragmentQuestionnaireWelcome();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_questionnaire_welcome, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Adopter Questionnaire");
        Button proceedBtn = getView().findViewById(R.id.proceedToChooseAnimal);
        proceedBtn.setOnClickListener(v -> {
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            FragmentQuestionnaireChooseAnimal chooseFragment = new FragmentQuestionnaireChooseAnimal();
            transaction.replace(R.id.nav_host_fragment, chooseFragment);
            transaction.addToBackStack("chooseAnimal");
            transaction.commit();
        });
        ImageButton backBtn = getView().findViewById(R.id.btnBack);
        backBtn.setOnClickListener(view1 -> {
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
            AdopterHomeDashboard adopterHomeDashboard = new AdopterHomeDashboard();
            transaction.replace(R.id.nav_host_fragment, adopterHomeDashboard);
            transaction.commit();
        });


    }


}