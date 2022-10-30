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
import androidx.cardview.widget.CardView;
import android.widget.Button;
import android.widget.ImageButton;

public class FragmentQuestionnairePart1 extends Fragment {

    public static FragmentQuestionnairePart1 newInstance() {
        return new FragmentQuestionnairePart1();
    }

    MCDMAnswersViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_questionnaire_part1, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Part 1 Information");


        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);

        Button proceedBtn = getView().findViewById(R.id.proceedToStart);
        proceedBtn.setOnClickListener(v -> {
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            if (mViewModel.getAnimalType().equals("Dog")) {
                FragmentDogQuestionnaire1 dog1Fragment = new FragmentDogQuestionnaire1();
                transaction.replace(R.id.nav_host_fragment,dog1Fragment);
                transaction.addToBackStack("dogQuestionnaire1");
                transaction.commit();

            }
            else {

                FragmentCatQuestionnaire1 cat1Fragment = new FragmentCatQuestionnaire1();
                transaction.replace(R.id.nav_host_fragment,cat1Fragment);
                transaction.addToBackStack("catQuestionnaire1");
                transaction.commit();
            }
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