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

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_questionnaire_choose_animal, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Adopter Questionnaire - Choose Animal");
        ImageButton adoptCat = (ImageButton)getView().findViewById(R.id.adoptChoiceCat);
        adoptCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                FragmentCatQuestionnaire1 cat1Fragment = new FragmentCatQuestionnaire1();
                transaction.replace(R.id.nav_host_fragment,cat1Fragment);
                transaction.addToBackStack("catQuestionnaire1");
                transaction.commit();
            }
        });

        ImageButton adoptDog = (ImageButton)getView().findViewById(R.id.adoptChoiceDog);
        adoptDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                FragmentDogQuestionnaire1 dog1Fragment = new FragmentDogQuestionnaire1();
                transaction.replace(R.id.nav_host_fragment,dog1Fragment);
                transaction.addToBackStack("dogQuestionnaire1");
                transaction.commit();
            }
        });

    }

    // TODO: Add  Progress Dialog Box
    private void catQuestionnaire()
    {
    }

    private void dogQuestionnaire()
    {



    }


}