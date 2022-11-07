package com.example.petnership_kairos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class RecommendedPets extends AppCompatActivity {

    Button recommendedPet1, recommendedPet2, recommendedPet3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended_pets);

        recommendedPet1 = findViewById(R.id.viewPet1);
        recommendedPet1.setOnClickListener (v -> {
            RecommendedPetIndiv recommendedPetIndiv= new RecommendedPetIndiv();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, recommendedPetIndiv);
            transaction.commit();
        });

        recommendedPet2 = findViewById(R.id.viewPet2);
        recommendedPet2.setOnClickListener (v -> {
            RecommendedPetIndiv recommendedPetIndiv= new RecommendedPetIndiv();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, recommendedPetIndiv);
            transaction.commit();
        });

        recommendedPet3 = findViewById(R.id.viewPet3);
        recommendedPet3.setOnClickListener (v -> {
            RecommendedPetIndiv recommendedPetIndiv= new RecommendedPetIndiv();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, recommendedPetIndiv);
            transaction.commit();
        });

    }

}