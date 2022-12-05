package com.example.petnership_kairos;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

public class FragmentRecommendedPets extends Fragment {

    ConstraintLayout recommendedPet1, recommendedPet2, recommendedPet3;
    TextView tvPercentage1, tvName1, tvAge1, tvBreed1, tvSex1;
    TextView tvPercentage2, tvName2, tvAge2, tvBreed2, tvSex2;
    TextView tvPercentage3, tvName3, tvAge3, tvBreed3, tvSex3, tvConsistency, tvHelp;
    ImageView ivImage1, ivImage2, ivImage3;
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    public static FragmentRecommendedPets newInstance() {
        return new FragmentRecommendedPets();
    }

    MCDMAnswersViewModel mViewModel;
    MCDMAlternative[] topThree;

    @SuppressLint("MissingInflatedId")

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recommended_pets, container, false);


    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Recommendations");
        mViewModel = new ViewModelProvider(requireActivity()).get(MCDMAnswersViewModel.class);
        topThree = mViewModel.getTopThree();


        tvPercentage1 = view.findViewById(R.id.rec1_percent);
        tvPercentage2 = view.findViewById(R.id.rec2_percent);
        tvPercentage3 = view.findViewById(R.id.rec3_percent);
        tvName1 = view.findViewById(R.id.rec1_name);
        tvAge1 = view.findViewById(R.id.rec1_age);
        tvSex1 = view.findViewById(R.id.rec1_sex);
        tvName2 = view.findViewById(R.id.rec2_name);
        tvAge2 = view.findViewById(R.id.rec2_age);
        tvSex2 = view.findViewById(R.id.rec2_sex);
        tvName3 = view.findViewById(R.id.rec3_name);
        tvAge3 = view.findViewById(R.id.rec3_age);
        tvSex3 = view.findViewById(R.id.rec3_sex);
        tvHelp = view.findViewById(R.id.WhatIsThis);
        tvConsistency = view.findViewById(R.id.consistencyValue);

        // load images of all three pets

        if(mViewModel.getTopThree()[0].getImageName() != null) {
            storageReference.child("Pets/").child(topThree[0].getImageName()).getDownloadUrl()
                    .addOnSuccessListener(uri -> {
                        System.out.println("GETIMAGENAME1 ONSUCCESS" + mViewModel.getTopThree()[0].getImageName());
                        Glide.with(getActivity()).load(uri.toString()).into((ImageView) view.findViewById(R.id.rec1_image));
                    });
        }

        if(mViewModel.getTopThree()[1].getImageName() != null) {
            storageReference.child("Pets/").child(topThree[1].getImageName()).getDownloadUrl()
                    .addOnSuccessListener(uri -> {
                        System.out.println("GETIMAGENAME2 ONSUCCESS" + mViewModel.getTopThree()[0].getImageName());
                        Glide.with(getActivity()).load(uri.toString()).into((ImageView) view.findViewById(R.id.rec2_image));
                    });
        }

        if(mViewModel.getTopThree()[2].getImageName() != null) {
            storageReference.child("Pets/").child(topThree[2].getImageName()).getDownloadUrl()
                    .addOnSuccessListener(uri -> {
                        System.out.println("GETIMAGENAME3 ONSUCCESS" + mViewModel.getTopThree()[0].getImageName());
                        Glide.with(getActivity()).load(uri.toString()).into((ImageView) view.findViewById(R.id.rec3_image));
                    });
        }

        // change values
        tvPercentage1.setText(String.format(Locale.getDefault(), "%.2f%% Match", topThree[0].getCalculatedPerformanceScore()*100));
        tvPercentage2.setText(String.format(Locale.getDefault(), "%.2f%% Match", topThree[1].getCalculatedPerformanceScore()*100));
        tvPercentage3.setText(String.format(Locale.getDefault(), "%.2f%% Match", topThree[2].getCalculatedPerformanceScore()*100));
        tvName1.setText(topThree[0].getName());
        tvAge1.setText(topThree[0].getAge());
        tvSex1.setText(topThree[0].getSex());
        tvName2.setText(topThree[1].getName());
        tvAge2.setText(topThree[1].getAge());
        tvSex2.setText(topThree[1].getSex());
        tvName3.setText(topThree[2].getName());
        tvAge3.setText(topThree[2].getAge());
        tvSex3.setText(topThree[2].getSex());
        String cons =  mViewModel.getConsistencyRatio() <= 0.1 ? "Acceptable Consistency" : mViewModel.getConsistencyRatio() <= 0.5 ? "Mildly Inconsistent Choices" : "Very Inconsistent Choices";
        //tvConsistency.setText(String.format(Locale.getDefault(), "%.2f", (mViewModel.getConsistencyRatio()*100)));
        tvConsistency.setText(cons);
        if (cons.equals("Acceptable Consistency")) {
            tvConsistency.setTextColor(Color.GREEN);
        }
        else if (cons.equals("Mildly Inconsistent")) {
            tvConsistency.setTextColor(Color.MAGENTA);
        }
        else {
            tvConsistency.setTextColor(Color.RED);
        }
        tvHelp.setOnClickListener(view1 -> whatIsThis());


        // load card
        recommendedPet1 = view.findViewById(R.id.rec1_card);
        recommendedPet2 = view.findViewById(R.id.rec2_card);
        recommendedPet3 = view.findViewById(R.id.rec3_card);



        // onClickListeners
        recommendedPet1.setOnClickListener (v -> {
            mViewModel.setCurrentResultView(0);


            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            RecommendedPetIndiv recommendedPetIndiv= new RecommendedPetIndiv();
            transaction.replace(R.id.recommendedPets,recommendedPetIndiv);
            transaction.addToBackStack("recommended pet indiv");
            transaction.commit();
        });

        recommendedPet2.setOnClickListener (v -> {
            mViewModel.setCurrentResultView(1);
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            RecommendedPetIndiv recommendedPetIndiv= new RecommendedPetIndiv();
            transaction.replace(R.id.recommendedPets,recommendedPetIndiv);
            transaction.addToBackStack("recommended pet indiv");
            transaction.commit();
        });


        recommendedPet3.setOnClickListener (v -> {
            mViewModel.setCurrentResultView(2);
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            RecommendedPetIndiv recommendedPetIndiv= new RecommendedPetIndiv();
            transaction.replace(R.id.recommendedPets,recommendedPetIndiv);
            transaction.addToBackStack("recommended pet indiv");
            transaction.commit();
        });

    }

    private void whatIsThis() {
        WhatPopUp whatDialog = new WhatPopUp();
        whatDialog.show(getParentFragmentManager(), "What Popup");


    }

}