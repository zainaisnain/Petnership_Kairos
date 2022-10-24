package com.example.petnership_kairos;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class FragmentRecommendedPets extends Fragment {

    ImageButton popup10;
    Button recommendedPet1, recommendedPet2, recommendedPet3;

    public static FragmentRecommendedPets newInstance() {
        return new FragmentRecommendedPets();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommended_pets, container, false);

        recommendedPet1 = view.findViewById(R.id.viewPet1);
        recommendedPet1.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecommendedPetIndiv recommendedPetIndiv= new RecommendedPetIndiv();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, recommendedPetIndiv).commit();
            }
        });

        recommendedPet2 = view.findViewById(R.id.viewPet2);
        recommendedPet2.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecommendedPetIndiv recommendedPetIndiv= new RecommendedPetIndiv();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, recommendedPetIndiv).commit();
            }
        });

        recommendedPet3 = view.findViewById(R.id.viewPet3);
        recommendedPet3.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecommendedPetIndiv recommendedPetIndiv= new RecommendedPetIndiv();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, recommendedPetIndiv).commit();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Recommendations");
    }


}