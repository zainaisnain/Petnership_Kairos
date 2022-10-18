package com.example.petnership_kairos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ApplicationHistory extends AppCompatActivity {

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

        ApplicationHistoryAdapter applicationHistoryAdapter = new ApplicationHistoryAdapter(applicationHistoryData,ApplicationHistory.this);
        recyclerView.setAdapter(applicationHistoryAdapter);

    }
}

