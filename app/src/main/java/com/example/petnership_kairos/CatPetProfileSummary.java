package com.example.petnership_kairos;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class CatPetProfileSummary extends Fragment {

    private CatPetProfileSummaryViewModel mViewModel;

    private Button proceedBtn, backBtn;
    private TextView tvCatlvl1, tvCatlvl2, tvCatlvl3, tvCatlvl4, tvCatlvl5,
            tvCatlvl6, tvCatlvl7, tvCatlvl8, tvCatlvl9;

    private String[] popularityHigh = {"Maine Coon", "Bengal", "Siamese", "Siberian", "Ragdoll",
            "British Shorthair", "Persian", "Scottish Fold", "Bombay", "Birman"};
    private String[] popularityMedium = {"Snowshoe", "Abyssinian", "Norwegian Forest", "Burmese", "Turkish Angora",
            "Ragamuffin", "Sphynx", "Nebelung", "Russian Blue", "Burmilla", "Chartreux", "American Shorthair",
            "Himalayan", "Turkish Van", "Tonkinese"};
    public static CatPetProfileSummary newInstance() {
        return new CatPetProfileSummary();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cat_pet_profile_summary, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//
//        proceedBtn = view.findViewById(R.id.cat_summary_proceed_btn);
//        proceedBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getActivity(), SuccessfullyAddedPet.class));
//            }
//        });

        tvCatlvl1 = view.findViewById(R.id.catlevel1);
        tvCatlvl2 = view.findViewById(R.id.catlevel2);
        tvCatlvl3 = view.findViewById(R.id.catlevel3);
        tvCatlvl4 = view.findViewById(R.id.catlevel4a);
        tvCatlvl5 = view.findViewById(R.id.catlevel4b);
        tvCatlvl6 = view.findViewById(R.id.catlevel5);
        tvCatlvl7 = view.findViewById(R.id.catlevel6);
        tvCatlvl8 = view.findViewById(R.id.catlevel7);
        tvCatlvl9 = view.findViewById(R.id.catlevel8);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            int q1 = bundle.getInt("q1");
            int q2 = bundle.getInt("q2");
            int q3 = bundle.getInt("q3");
            int q4 = bundle.getInt("q4");
            int q5 = bundle.getInt("q5");
            int q6 = bundle.getInt("q6");
            int q7 = bundle.getInt("q7");
            int q8 = bundle.getInt("q8");
            String q9 = bundle.getString("q9");
            System.out.println("CatPetProfileSummary q1 == " + q1);
            System.out.println("CatPetProfileSummary q2 == " + q2);
            System.out.println("CatPetProfileSummary q3 == " + q3);
            System.out.println("CatPetProfileSummary q4 == " + q4);
            System.out.println("CatPetProfileSummary q5 == " + q5);
            System.out.println("CatPetProfileSummary q6 == " + q6);
            System.out.println("CatPetProfileSummary q7 == " + q7);
            System.out.println("CatPetProfileSummary q8 == " + q8);
            System.out.println("CatPetProfileSummary q9 == " + q9);

            if(q1 == 1){
                tvCatlvl1.setText("high");
            }else if(q1 == 2){
                tvCatlvl1.setText("medium");
            }else{
                tvCatlvl1.setText("low");
            }

            if(q2 == 1){
                tvCatlvl2.setText("high");
            }else if(q2 == 2){
                tvCatlvl2.setText("medium");
            }else{
                tvCatlvl2.setText("low");
            }

            if(q3 == 1){
                tvCatlvl3.setText("high");
            }else if(q3 == 2){
                tvCatlvl3.setText("medium");
            }else{
                tvCatlvl3.setText("low");
            }

            if(q4 == 1){
                tvCatlvl4.setText("high");
            }else if(q4 == 2){
                tvCatlvl4.setText("medium");
            }else{
                tvCatlvl4.setText("low");
            }

            if(q5 == 1){
                tvCatlvl5.setText("high");
            }else if(q5 == 2){
                tvCatlvl5.setText("medium");
            }else{
                tvCatlvl5.setText("low");
            }

            if(q6 == 1){
                tvCatlvl6.setText("high");
            }else if(q6 == 2){
                tvCatlvl6.setText("medium");
            }else{
                tvCatlvl6.setText("low");
            }

            if(q7 == 1){
                tvCatlvl7.setText("high");
            }else if(q7 == 2){
                tvCatlvl7.setText("medium");
            }else{
                tvCatlvl7.setText("low");
            }

            if(q8 == 1){
                tvCatlvl8.setText("high");
            }else if(q8 == 2){
                tvCatlvl8.setText("medium");
            }else{
                tvCatlvl8.setText("low");
            }

            //Q9 BREED POPULARITY
            //HIGH : TOP 1 - 10
            // MEDIUM : 11 - 25
            // LOW : NOT ON THE LIST
            for (String highBreed : popularityHigh) {
                if (highBreed.equals(q9)) {
                    tvCatlvl9.setText("High");
                }
            }

            for (String mediumBreed : popularityMedium) {
                if (mediumBreed.equals(q9)) {
                    tvCatlvl9.setText("Medium");
                }else{
                    tvCatlvl9.setText("Low");
                }
            }
        }

        backBtn = view.findViewById(R.id.cat_summary_back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShelterCatQuestionnaire shelterCatQuestionnaire = new ShelterCatQuestionnaire();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, shelterCatQuestionnaire);
                transaction.commit();
            }
        });

        proceedBtn = view.findViewById(R.id.cat_summary_proceed_btn);
        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SavePetProfileDialog savePetProfileDialog = new SavePetProfileDialog();
                savePetProfileDialog.show(getParentFragmentManager(), "My Fragment");
//                startActivity(new Intent(getActivity(), SuccessfullyAddedPet.class));
            }
        });
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CatPetProfileSummaryViewModel.class);
        // TODO: Use the ViewModel
    }

}