package com.example.petnership_kairos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ApplicationHistory extends AppCompatActivity {

    private ImageButton backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_history);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewApplications);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApplicationHistoryData[] applicationHistoryData = new ApplicationHistoryData[]{
                new ApplicationHistoryData("Alpha","Denied",R.drawable.cat_dog),
                new ApplicationHistoryData("Bravo","Pending",R.drawable.cat_dog),
                new ApplicationHistoryData("Charlie","Approved",R.drawable.cat_dog)

        };
        backBtn = (ImageButton) findViewById(R.id.btnBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ApplicationHistory.this,AdopterHomeDashboard.class);
                startActivity(intent);

            }
        });
        ApplicationHistoryAdapter applicationHistoryAdapter = new ApplicationHistoryAdapter(applicationHistoryData,ApplicationHistory.this);
        recyclerView.setAdapter(applicationHistoryAdapter);

    }
}

