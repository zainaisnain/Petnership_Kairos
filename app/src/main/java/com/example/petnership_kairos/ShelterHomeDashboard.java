package com.example.petnership_kairos;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ShelterHomeDashboard extends Fragment {

    CardView card1,card2;
    FloatingActionButton fabAddBtn;

    private ShelterHomeDashboardViewModel mViewModel;

    public static ShelterHomeDashboard newInstance() {
        return new ShelterHomeDashboard();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shelter_home_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Shelter Home");
        card1 = view.findViewById(R.id.dogs);
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                PetDogs PetDogs = new PetDogs();
                transaction.replace(R.id.nav_host_fragment,PetDogs);
                transaction.commit();
            }
        });

        card2 = view.findViewById(R.id.cats);
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                PetCats PetCats = new PetCats();
                transaction.replace(R.id.nav_host_fragment,PetCats);
                transaction.commit();
            }
        });

        fabAddBtn = view.findViewById(R.id.fabAdd);
        fabAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                ShelterRegisterPets ShelterRegisterPets = new ShelterRegisterPets();
                transaction.replace(R.id.nav_host_fragment,ShelterRegisterPets);
                transaction.commit();
            }
        });

    }
}