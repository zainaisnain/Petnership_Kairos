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
import android.widget.ImageView;

public class PetCats extends Fragment {

    ImageView img1;
    private PetCatsViewModel mViewModel;

    public static PetCats newInstance() {
        return new PetCats();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //TO-DO: edit fragment_pet_dog to fragment_pet_cat; need to run the app
        return inflater.inflate(R.layout.fragment_cat_pet_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Cats");
        img1 = view.findViewById(R.id.petProfile);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                PerPetProfileCats PerPetProfileCats = new PerPetProfileCats();
                transaction.replace(R.id.nav_host_fragment, PerPetProfileCats);
                transaction.commit();
            }
        });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PetCatsViewModel.class);
        // TODO: Use the ViewModel
    }

}