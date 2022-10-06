package com.example.petnership_kairos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class activity_catprofilequestionnaire extends AppCompatActivity implements View.OnClickListener {

    String[] popular_cats = {"Others", "Tonkinese", "Turkish Van", "Himalayan", "American Shorthair", "Chartreux", "Burmilla", "Russian Blue", "Nebelung", "Sphynx", "Ragamuffin", "Turkish Angora", "Burmese", "Norwegian Forest", "Abyssinian", "Snowshoe", "Birman", "Bombay", "Scottish Fold", "Persian", "British Shorthair", "Ragdoll", "Siberian", "Siamese", "Bengal", "Maine Coon"};

    AutoCompleteTextView autoCompleteCat;
    ArrayAdapter<String> catAdapterItems;

    public AppCompatButton modalSuccess, modalCancel;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catpetprofile);


        modalSuccess = (AppCompatButton) findViewById(R.id.submit_petprofile);
        modalSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();

            }
        });

        autoCompleteCat = findViewById(R.id.cat_auto_complete);
        catAdapterItems = new ArrayAdapter<String>(this, R.layout.popular_animals_list, popular_cats);
        autoCompleteCat.setAdapter(catAdapterItems);

        autoCompleteCat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String popular_cat = adapterView.getItemAtPosition(i).toString();

            }
        });
    }

    public void openDialog() {
        ModalSuccessDialog modalSuccessDiag = new ModalSuccessDialog();
        modalSuccessDiag.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()) {
            case R.id.submit_petprofile:
                i = new Intent(this, modal_success.class);
                startActivity(i);
                break;

            default:
                break;
        }
    }
}