package com.example.petnership_kairos;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class DogPetProfileSummary extends Fragment {

    private DogPetProfileSummaryViewModel mViewModel;

    private Button proceedBtn, backBtn;
    private TextView tvDoglvl1, tvDoglvl2, tvDoglvl3, tvDoglvl4, tvDoglvl5,
            tvDoglvl6, tvDoglvl7, tvDoglvl8, tvDoglvl9, tvDoglvl10, tvDoglvl11;

    private String[] popularityHigh =
            {"Retrievers (Labrador)", "French Bulldogs", "Retrievers (Golden)", "German Shepherd Dogs", "Poodles",
            "Bulldogs", "Beagles", "Rottweilers", "Pointers (German Shorthaired)", "Dachshunds",
            "Pembroke Welsh Corgis", "Australian Shepherds", "Yorkshire Terriers", "Boxers", "Cavalier King Charles Spaniels",
            "Doberman Pinschers", "Great Danes", "Miniature Schnauzers", "Siberian Huskies", "Bernese Mountain Dogs",
            "Cane Corso", "Shih Tzu", "Boston Terriers", "Pomeranians", "Havanese"};

    private String[] popularityMedium =
            {"Spaniels (English Springer)", "Brittanys", "Shetland Sheepdogs", "Spaniels (Cocker)", "Miniature American Shepherds",
            "Border Collies", "Vizslas", "Pugs", "Basset Hounds", "Mastiffs",
            "Belgian Malinois", "Chihuahuas", "Collies", "Maltese", "Weimaraners",
            "Rhodesian Ridgebacks", "Shiba Inu", "Spaniels (English Cocker)", "Portuguese Water Dogs", "Newfoundlands",
            "West Highland White Terriers", "Bichons Frises", "Retrievers (Chesapeake Bay)", "Dalmatians", "Bloodhounds",
            "Australian Cattle Dogs", "Akitas", "St. Bernards", "Papillons", "Samoyeds",
            "Bullmastiffs", "Whippets", "Scottish Terriers", "Pointers (German Wirehaired)", "Wirehaired Pointing Griffons",
            "Bull Terriers", "Airedale Terriers", "Great Pyrenees", "Chinese Shar-Pei", "Giant Schnauzers",
            "Soft Coated Wheaten Terriers", "Cardigan Welsh Corgis", "Alaskan Malamutes", "Old English Sheepdogs", "Dogues de Bordeaux",
            "Setters (Irish)", "Russell Terriers", "Italian Greyhounds", "Cairn Terriers", "Staffordshire Bull Terriers",
            "Miniature Pinschers", "Chinese Crested", "Greater Swiss Mountain Dogs", "Lagotti Romagnoli", "Chow Chows",
            "American Staffordshire Terriers", "Biewer Terriers", "Coton de Tulear", "Lhasa Apsos", "Irish Wolfhounds",
            "Rat Terriers", "Basenjis", "Anatolian Shepherd Dogs", "Dogo Argentinos", "Spaniels (Boykin)",
            "Border Terriers", "Retrievers (Nova Scotia Duck Tolling)", "Retrievers (Flat-Coated)", "Pekingese", "Keeshonden",
            "Standard Schnauzers", "Brussels Griffons", "Setters (English)", "Fox Terriers (Wire)", "Norwegian Elkhounds"};

    public static DogPetProfileSummary newInstance() {
        return new DogPetProfileSummary();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dog_pet_profile_summary, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvDoglvl1 = view.findViewById(R.id.doglevel4a2);
        tvDoglvl2 = view.findViewById(R.id.doglevel4b2);
        tvDoglvl3 = view.findViewById(R.id.doglevel4a3);
        tvDoglvl4 = view.findViewById(R.id.doglevel4b3);
        tvDoglvl5 = view.findViewById(R.id.doglevel3);
        tvDoglvl6 = view.findViewById(R.id.doglevel4);
        tvDoglvl7 = view.findViewById(R.id.doglevel5a);
        tvDoglvl8 = view.findViewById(R.id.doglevel5b);
        tvDoglvl9 = view.findViewById(R.id.doglevel5c);
        tvDoglvl10 = view.findViewById(R.id.doglevel6);
        tvDoglvl11 = view.findViewById(R.id.doglevel7);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            int q1 = bundle.getInt("q1");
            int q2 = bundle.getInt("q2");
            int q3 = bundle.getInt("q3");
            int q4 = bundle.getInt("q4");
            int q5 = bundle.getInt("q5");
            int q6 = bundle.getInt("q6");
            int q7 = bundle.getInt("q7");
            int q8 = bundle.getInt("q8");
            int q9 = bundle.getInt("q9");
            String q10 = bundle.getString("q10");
            int q11 = bundle.getInt("q11");

            if(q1 == 1){
                tvDoglvl1.setText("High");
            }else if(q1 == 2){
                tvDoglvl1.setText("Medium");
            }else{
                tvDoglvl1.setText("Low");
            }

            if(q2 == 1){
                tvDoglvl2.setText("High");
            }else if(q2 == 2){
                tvDoglvl2.setText("Medium");
            }else{
                tvDoglvl2.setText("Low");
            }

            if(q3 == 1){
                tvDoglvl3.setText("High");
            }else if(q3 == 2){
                tvDoglvl3.setText("Medium");
            }else{
                tvDoglvl3.setText("Low");
            }

            if(q4 == 1){
                tvDoglvl4.setText("High");
            }else if(q4 == 2){
                tvDoglvl4.setText("Medium");
            }else{
                tvDoglvl4.setText("Low");
            }

            if(q5 == 1){
                tvDoglvl5.setText("High");
            }else if(q5 == 2){
                tvDoglvl5.setText("Medium");
            }else{
                tvDoglvl5.setText("Low");
            }

            if(q6 == 1){
                tvDoglvl6.setText("High");
            }else if(q6 == 2){
                tvDoglvl6.setText("Medium");
            }else{
                tvDoglvl6.setText("Low");
            }

            if(q7 == 1){
                tvDoglvl7.setText("High");
            }else if(q7 == 2){
                tvDoglvl7.setText("Medium");
            }else{
                tvDoglvl7.setText("Low");
            }

            if(q8 == 1){
                tvDoglvl8.setText("High");
            }else if(q8 == 2){
                tvDoglvl8.setText("Medium");
            }else{
                tvDoglvl8.setText("Low");
            }

            if(q9 == 1){
                tvDoglvl9.setText("High");
            }else if(q9== 2){
                tvDoglvl9.setText("Medium");
            }else{
                tvDoglvl9.setText("Low");
            }

            //Q10 BREED POPULARITY
            //HIGH : TOP 1 - 10
            // MEDIUM : 11 - 25
            // LOW : NOT ON THE LIST
            for (String highBreed : popularityHigh) {
                if (highBreed.equals(q10)) {
                    tvDoglvl10.setText("High");
                }
            }

            for (String mediumBreed : popularityMedium) {
                if (mediumBreed.equals(q10)) {
                    tvDoglvl10.setText("Medium");
                }else{
                    tvDoglvl10.setText("Low");
                }
            }

            if(q11 == 1){
                tvDoglvl11.setText("High");
            }else if(q11 == 2){
                tvDoglvl11.setText("Medium");
            }else{
                tvDoglvl11.setText("Low");
            }
        }

        backBtn = view.findViewById(R.id.dog_summary_back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                getParentFragmentManager().popBackStack();
//                getActivity().onBackPressed();
                ShelterDogQuestionnaire shelterDogQuestionnaire = new ShelterDogQuestionnaire();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, shelterDogQuestionnaire);
                transaction.commit();
            }
        });

        proceedBtn = view.findViewById(R.id.dog_summary_proceed_btn);
        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SavePetProfileDialog savePetProfileDialog = new SavePetProfileDialog();
                savePetProfileDialog.show(getParentFragmentManager(), "My Fragment");
//                startActivity(new Intent(getActivity(), SuccessfullyAddedPet.class));
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DogPetProfileSummaryViewModel.class);
        // TODO: Use the ViewModel
    }

}