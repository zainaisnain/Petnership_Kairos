package com.example.petnership_kairos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ApplicantsReview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicants_review);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApplicantsReviewData[] applicantsreviewData = new ApplicantsReviewData[]{
                new ApplicantsReviewData("Juan Dela Cruz","Brownie",R.drawable.profile),
                new ApplicantsReviewData("Maria Dela Cruz","Beauty",R.drawable.profile),
                new ApplicantsReviewData("Jose Dela Cruz","Bruno",R.drawable.profile)

        };

        ApplicantsReviewAdapter applicantsReviewAdapter = new ApplicantsReviewAdapter(applicantsreviewData,ApplicantsReview.this);
        recyclerView.setAdapter(applicantsReviewAdapter);

    }
}

