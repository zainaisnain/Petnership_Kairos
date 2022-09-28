package com.example.petnership_kairos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class activity_userprofile extends AppCompatActivity implements View.OnClickListener {

    public CardView p1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollable);

        p1 = (CardView) findViewById(R.id.pet1);
//        card1 = (CardView) findViewById(R.id.dogs);

        p1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()) {
            case R.id.pet1:
                i = new Intent(this, PerPetProfile.class);
                startActivity(i);
                break;

            default:
                break;
        }
    }
}