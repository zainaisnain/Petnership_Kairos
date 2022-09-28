package com.example.petnership_kairos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public CardView card1;
    public FloatingActionButton b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(R.style.Theme_Petnership_Kairos);
        setContentView(R.layout.activity_main);

        card1 = (CardView) findViewById(R.id.dogs);
        b1 = (FloatingActionButton) findViewById(R.id.fabAdd);

        card1.setOnClickListener(this);
        b1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()) {
            case R.id.dogs:
                i = new Intent(this, activity_userprofile.class);
                startActivity(i);
                break;

            case R.id.fabAdd:
                i = new Intent(this, activity_petprofilequestionnaire.class);
                startActivity(i);
                break;

            default:
                break;
        }
    }
}