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

public class ShelterRegisterPets extends Fragment {

    ImageButton btn1,btn2;
    private ShelterRegisterPetsViewModel mViewModel;

    public static ShelterRegisterPets newInstance() {
        return new ShelterRegisterPets();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shelter_register_pets, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Choose Pet");

        btn1 = view.findViewById(R.id.dogButton);
        btn2 = view.findViewById(R.id.catButton);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
//                DogPetProfile DogPetProfile = new DogPetProfile();
//                transaction.replace(R.id.nav_host_fragment,DogPetProfile);
                AddDog addDog = new AddDog();
                transaction.replace(R.id.nav_host_fragment,addDog);
                transaction.commit();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                AddCat AddCatInfo = new AddCat();
                transaction.replace(R.id.nav_host_fragment,AddCatInfo);
                transaction.commit();
            }
        });


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ShelterRegisterPetsViewModel.class);
        // TODO: Use the ViewModel
    }

}