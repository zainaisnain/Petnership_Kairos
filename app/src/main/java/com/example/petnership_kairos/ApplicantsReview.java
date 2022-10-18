package com.example.petnership_kairos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ApplicantsReview extends AppCompatActivity {
private ImageButton backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_applicantsreview);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewApplicants);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApplicantsReviewData[] applicantsreviewData = new ApplicantsReviewData[]{
                new ApplicantsReviewData("Juan Dela Cruz","Brownie",R.drawable.profile),
                new ApplicantsReviewData("Maria Dela Cruz","Beauty",R.drawable.profile),
                new ApplicantsReviewData("Jose Dela Cruz","Bruno",R.drawable.profile)

        };
        backBtn = (ImageButton) findViewById(R.id.btnBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ApplicantsReview.this,ShelterHomeDashboard.class);
                startActivity(intent);

            }
        });
        ApplicantsReviewAdapter applicantsReviewAdapter = new ApplicantsReviewAdapter(applicantsreviewData,ApplicantsReview.this);
        recyclerView.setAdapter(applicantsReviewAdapter);

    }
}

