package com.example.petnership_kairos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public CardView card1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(R.style.Theme_Petnership_Kairos);
        setContentView(R.layout.activity_main);

        card1 = (CardView) findViewById(R.id.dogs);
//        card1 = (CardView) findViewById(R.id.dogs);

        card1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()) {
            case R.id.dogs:
                i = new Intent(this, activity_petprofile.class);
                startActivity(i);
                break;

            default:
                break;
        }
    }
}