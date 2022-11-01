package com.example.petnership_kairos;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ShelterRegisteredPets extends Fragment {

    private ShelterRegisteredPetsViewModel mViewModel;
    DatabaseReference petsDBRef = FirebaseDatabase.getInstance().getReference("Pets");
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;


    private String shelterEmail, petID;

    DatabaseReference allPetsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("AllPets");
    DatabaseReference petsCatsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("Cats");
    DatabaseReference petsDogsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("Dogs");

    public static ShelterRegisteredPets newInstance() {
        return new ShelterRegisteredPets();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shelter_registered_pets, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Registered Pets");

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        shelterEmail = firebaseUser.getEmail();

        setUpPetPic();
    }

    private void setUpPetPic() {
        //retrieve imageName
        System.out.println("shelterEmail === " + shelterEmail);
        allPetsDBRef.orderByChild("shelter").equalTo(shelterEmail)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        System.out.println("snapshot === " + snapshot);
                        System.out.println("petID " + petID);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}