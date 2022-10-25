package com.example.petnership_kairos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class SuccessfullyEditedShelterInfo extends AppCompatActivity {
    private Button homeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successfully_edited_shelter_info);
        homeBtn = findViewById(R.id.success_edited_info_home_btn);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShelterHomeDashboard shelterHomeDashboard = new ShelterHomeDashboard();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.success_edited_shelter, shelterHomeDashboard);
                transaction.commit();
            }
        });

    }
}