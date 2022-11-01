package com.example.petnership_kairos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdopterNotAnsweredQuestionnaire extends AppCompatActivity {

    private Button startAnsweringBtn, cancelAnsweringBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopter_not_answered_questionnaire);

        startAnsweringBtn = findViewById(R.id.start_answering);
        cancelAnsweringBtn = findViewById(R.id.cancel_answering);

        startAnsweringBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdopterNotAnsweredQuestionnaire.this, StartOfQuestionnaire.class));
            }
        });

        cancelAnsweringBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BrowseAnimals browseAnimals = new BrowseAnimals();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.adopter_not_answered, browseAnimals);
                transaction.commit();
            }
        });
    }
}