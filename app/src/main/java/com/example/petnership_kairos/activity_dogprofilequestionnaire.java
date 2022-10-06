package com.example.petnership_kairos;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class activity_dogprofilequestionnaire extends AppCompatActivity implements View.OnClickListener {

    String[] popular_dogs =
            {"Others", "Retrievers (Labrador)", "French Bulldogs", "Retrievers (Golden)", "German Shepherd", "Poodles", "Bulldogs", "Beagles", "Rottweilers", "Pointers (German Shorthaired)", "Dachshunds", "Pembroke Welsh Corgis", "Australian Shepherds", "Yorkshire Terriers", "Boxers", "Cavalier King Charles Spaniels", "Doberman Pinschers", "Great Danes",
              "Miniature Schnauzers", "Siberian Huskies", "Bernese Mountain Dogs", "Cane Corso", "Shih Tzu", "Boston Terriers", "Pomeranians", "Havanese", "Spaniels (English Springer)", "Brittanys", "Shetland Sheepdogs", "Spaniels (Cocker)", "Miniature American Shepherds", "Border Collies", "Vizslas", "Pugs", "Basset Hounds", "Mastiffs", "Belgian Malinois",
               "Chihuahuas", "Collies", "Maltese", "Weimaraners", "Rhodesian Ridgebacks", "Shiba Inu", "Spaniels (English Cocker)", "Portuguese Water Dogs", "Newfoundlands", "West Highland White Terriers", "Bichons Frises", "Retrievers (Chesapeake Bay)", "Dalmatians", "Bloodhounds", "Australian Cattle Dogs", "Akitas", "St. Bernards", "Papillons", "Samoyeds",
               "Bullmastiffs", "Whippets", "Scottish Terriers", "Pointers (German Wirehaired)", "Wirehaired Pointing Griffons", "Bull Terriers", "Airedale Terriers", "Great Pyrenees", "Chinese Shar-Pei", "Giant Schnauzers", "Soft Coated Wheaten Terriers", "Cardigan", "Welsh Corgi", "Alaskan Malamutes", "Old English Sheepdogs", "Dogues de Bordeaux", "Setters (Irish)",
               "Russell Terriers", "Italian Greyhounds", "Cairn Terriers", "Staffordshire Bull Terriers", "Miniature Pinschers", "Chinese Crested", "Greater Swiss Mountain Dogs", "Lagotti Romagnoli", "Chow Chows", "American Staffordshire Terriers", "Biewer Terriers", "Coton de Tulear", "Lhasa Apsos", "Irish Wolfhounds", "Rat Terriers", "Basenjis", "Anatolian Shepherd Dogs",
               "Dogo Argentinos", "Spaniels (Boykin)", "Border Terriers", "Retrievers (Nova Scotia Duck Tolling)", "Retrievers (Flat-Coated)", "Pekingese", "Keeshonden", "Standard Schnauzers", "Brussels Griffons", "Setters (English)", "Fox Terriers (Wire)", "Norwegian Elkhounds"};

    AutoCompleteTextView autoCompleteDog;
    ArrayAdapter<String> dogAdapterItems;

    public AppCompatButton modalSuccess, modalCancel;
    Dialog dialog;

    android.widget.ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogpetprofile);

        btnBack = findViewById(R.id.btnBackDog);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        modalSuccess = (AppCompatButton) findViewById(R.id.submit_petprofile);

        modalSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });


        autoCompleteDog = findViewById(R.id.auto_complete);
        dogAdapterItems = new ArrayAdapter<String>(this, R.layout.popular_animals_list, popular_dogs);
        autoCompleteDog.setAdapter(dogAdapterItems);

        autoCompleteDog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String popular_dog = adapterView.getItemAtPosition(i).toString();

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