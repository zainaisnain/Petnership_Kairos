package com.example.petnership_kairos;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class PetDogs extends Fragment {

    ImageView img1;
    private PetDogsViewModel mViewModel;

    public static PetDogs newInstance() {
        return new PetDogs();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dogs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Dogs");
        img1 = view.findViewById(R.id.petProfile);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                PerPetProfileDogs PerPetProfileDogs = new PerPetProfileDogs();
                transaction.replace(R.id.nav_host_fragment, PerPetProfileDogs);
                transaction.commit();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PetDogsViewModel.class);
        // TODO: Use the ViewModel
    }

}