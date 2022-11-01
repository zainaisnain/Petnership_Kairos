package com.example.petnership_kairos;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class FragmentQuestionnaireChooseAnimal extends Fragment {

    public static FragmentQuestionnaireChooseAnimal newInstance() {
        return new FragmentQuestionnaireChooseAnimal();
    }

    MCDMAnswersViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_questionnaire_choose_animal, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Adopter Questionnaire - Choose Animal");
        ImageButton adoptCat = getView().findViewById(R.id.adoptChoiceCat);
        adoptCat.setOnClickListener(v -> {


            mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);
            mViewModel.setAnimalType("Cat");

            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            FragmentSection1 part1Fragment = new FragmentSection1();
            transaction.replace(R.id.nav_host_fragment,part1Fragment);
            transaction.addToBackStack("questionnairePart1");
            transaction.commit();
        });

        ImageButton adoptDog = getView().findViewById(R.id.adoptChoiceDog);
        adoptDog.setOnClickListener(v -> {
            mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);
            mViewModel.setAnimalType("Dog");
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            FragmentSection1 part1Fragment = new FragmentSection1();
            transaction.replace(R.id.nav_host_fragment,part1Fragment);
            transaction.addToBackStack("questionnairePart1");
            transaction.commit();
        });
        ImageButton backBtn = getView().findViewById(R.id.btnBack);
        backBtn.setOnClickListener(view1 -> {

            getParentFragmentManager().popBackStack();
        });

    }

    // TODO: Add  Progress Dialog Box


}