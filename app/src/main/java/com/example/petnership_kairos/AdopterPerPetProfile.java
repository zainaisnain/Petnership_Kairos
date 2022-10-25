package com.example.petnership_kairos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class AdopterPerPetProfile extends Fragment {
    View view;
    protected static String petID, petImageName, petName, petBreed, petAge, petSex, petDescription;

    private TextView tvPetTitle, tvPetName, tvPetBreed, tvPetAge, tvPetSex, tvPetDescription;
    private ImageView ivPetImage;
    private ImageButton backBtn;

    DatabaseReference allPetsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("AllPets");
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    public AdopterPerPetProfile newInstance() {return new AdopterPerPetProfile();}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_adopter_per_pet_profile, container, false);
        petID = view.getIntent().getStringExtra("PetID");
        ivPetImage = view.findViewById(R.id.adopter_per_pet_image);
        tvPetTitle = view.findViewById(R.id.adopter_per_pet_name_title);
        tvPetName = view.findViewById(R.id.adopter_per_pet_name);
        tvPetBreed = view.findViewById(R.id.adopter_per_pet_breed);
        tvPetAge = view.findViewById(R.id.adopter_per_pet_age);
        tvPetSex = view.findViewById(R.id.adopter_per_pet_sex);
        tvPetDescription = view.findViewById(R.id.adopter_per_pet_description);

        backBtn = view.findViewById(R.id.adopter_per_pet_back_btn);


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(AdopterPerPetProfileAct.this, BrowseAnimals.class));
            }
        });

        setUpPetImage();
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

}