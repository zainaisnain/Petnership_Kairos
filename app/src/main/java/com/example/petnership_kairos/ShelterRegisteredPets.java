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
        petsDBRef.orderByChild("shelter").equalTo(shelterEmail)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        System.out.println("snapshot === " + snapshot);
//                        for(DataSnapshot ds : snapshot.getChildren()) {
//                            petID = ds.getKey();
//                            System.out.println("petID inside " + petID);
//                            System.out.println("ds getChildren: " + ds);
//                        }

                        System.out.println("petID " + petID);
//                        petsDBRef.child(shelterUsername).child("imageName").
//                                addListenerForSingleValueEvent(new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                        shelterImageName = String.valueOf(snapshot.getValue());
//                                        System.out.println("shelterImageName1 == " + shelterImageName);
//
//                                        //DISPLAY IMAGE TO IMAGE VIEW
//                                        storageReference.child("Shelters/").child(shelterImageName).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                            @Override
//                                            public void onSuccess(Uri uri) {
//                                                Glide.with(getActivity().getApplicationContext()).load(uri.toString()).into(ivShelterImage);
//                                            }
//                                        });
//
//                                    }
//
//                                    @Override
//                                    public void onCancelled(@NonNull DatabaseError error) {
//
//                                    }
//                                });


//                        petsDBRef.child(petID).child("imageName").
//                                addListenerForSingleValueEvent(new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                        shelterImageName = String.valueOf(snapshot.getValue());
//                                        System.out.println("shelterImageName1 == " + shelterImageName);
//
//                                        //DISPLAY IMAGE TO IMAGE VIEW
//                                        storageReference.child("Shelters/").child(shelterImageName).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                            @Override
//                                            public void onSuccess(Uri uri) {
//                                                Glide.with(getActivity().getApplicationContext()).load(uri.toString()).into(ivShelterImage);
//                                            }
//                                        });
//
//                                    }
//
//                                    @Override
//                                    public void onCancelled(@NonNull DatabaseError error) {
//
//                                    }
//                                });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}