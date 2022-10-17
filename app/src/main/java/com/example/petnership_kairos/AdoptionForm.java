package com.example.petnership_kairos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;

public class AdoptionForm extends Fragment implements View.OnClickListener{

    AppCompatRadioButton rbDog, rbCat, rbSmall, rbMedium, rbLarge, rbXLarge;
    RadioGroup rgPetType;

    private AdoptionFormViewModel mViewModel;

    public static AdoptionForm newInstance() {
        return new AdoptionForm();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_adoption_form, container, false);

//        rgPetType = view.findViewById(R.id.radioGroup_petType);
        rbDog = view.findViewById(R.id.dogClicked);
        rbCat = view.findViewById(R.id.catClicked);

//        rbSmall = view.findViewById(R.id.smallClicked);
//        rbMedium = view.findViewById(R.id.mediumClicked);
//        rbLarge = view.findViewById(R.id.largeClicked);
//        rbXLarge = view.findViewById(R.id.xLargeClicked);

        rbDog.setOnClickListener(this);
        rbCat.setOnClickListener(this);

//        rbSmall.setOnClickListener(this);
//        rbMedium.setOnClickListener(this);
//        rbLarge.setOnClickListener(this);
//        rbXLarge.setOnClickListener(this);

        return view;
        }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Adoption Form");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dogClicked:
                rbDog.setTextColor(Color.BLACK);
                rbCat.setTextColor(Color.WHITE);
                break;
            case R.id.catClicked:
                rbCat.setTextColor(Color.BLACK);
                rbDog.setTextColor(Color.WHITE);
                break;
//            case R.id.smallClicked:
//                rbSmall.setTextColor(Color.BLACK);
//                rbMedium.setTextColor(Color.WHITE);
//                rbLarge.setTextColor(Color.WHITE);
//                rbXLarge.setTextColor(Color.WHITE);
//                break;
//            case R.id.mediumClicked:
//                rbSmall.setTextColor(Color.WHITE);
//                rbMedium.setTextColor(Color.BLACK);
//                rbLarge.setTextColor(Color.WHITE);
//                rbXLarge.setTextColor(Color.WHITE);
//                break;
//            case R.id.largeClicked:
//                rbSmall.setTextColor(Color.WHITE);
//                rbMedium.setTextColor(Color.WHITE);
//                rbLarge.setTextColor(Color.BLACK);
//                rbXLarge.setTextColor(Color.WHITE);
//                break;
//            case R.id.xLargeClicked:
//                rbSmall.setTextColor(Color.WHITE);
//                rbMedium.setTextColor(Color.WHITE);
//                rbLarge.setTextColor(Color.WHITE);
//                rbXLarge.setTextColor(Color.BLACK);
//                break;
        }
    }

    public void onRadioButtonClicked (View view) {
        boolean isSelected = ((AppCompatRadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.dogClicked:
                if (isSelected) {
                    rbDog.setTextColor(Color.BLACK);
                }
                break;
            case R.id.catClicked:
                if (isSelected) {
                    rbCat.setTextColor(Color.BLACK);
                }
                break;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AdoptionFormViewModel.class);
        // TODO: Use the ViewModel
    }


}