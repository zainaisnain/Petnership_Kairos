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
import android.widget.Button;

public class RecommendedPetIndiv extends Fragment {

    Button toAdopt, notForMe;

    private RecommendedPetIndivViewModel mViewModel;

    public static RecommendedPetIndiv newInstance() {
        return new RecommendedPetIndiv();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommended_pet_indiv, container, false);
        toAdopt = view.findViewById(R.id.recommendedPet_toAdopt);
        toAdopt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdoptionForm adoptionForm = new AdoptionForm();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.recommendedPetIndivFrag, adoptionForm).commit();
            }

        });

        notForMe = view.findViewById(R.id.recommendedPet_notToAdopt);
        notForMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TO-DO
//                RecommendedPets recommendedPets = new RecommendedPets();
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.recommendedPetIndivFrag, recommendedPets).commit();
            }

        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Recommended Pets");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RecommendedPetIndivViewModel.class);
        // TODO: Use the ViewModel
    }

}
