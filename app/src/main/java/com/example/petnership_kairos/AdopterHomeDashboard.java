package com.example.petnership_kairos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

public class AdopterHomeDashboard extends Fragment {

    private AdopterHomeDashboardViewModel mViewModel;

    DatabaseReference petsCatsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("Cats");
    DatabaseReference petsDogsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("Dogs");
    DatabaseReference petsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets");
    DatabaseReference sheltersDBRef = FirebaseDatabase.getInstance().getReference("Shelters");
    DatabaseReference adoptersDBRef = FirebaseDatabase.getInstance().getReference("Adopters");

    private int numOfCats, numOfDogs, catsCount, dogsCount;
    private TextView tvAdopterName, tvAdopterContact, tvAdopterAddress;
    private String adopterEmail, adopterUsername, adopterImageName, adopterName, adopterContact, adopterAddress;
    private ImageView ivAdopterImage, ivCvAdopterImage;
    private CardView cvAdopterInfo, cvApplicationHistory, cvAdoptPet, cvBrowseAnimals;

    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;
    // instance for firebase storage and StorageReference
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();


    public static AdopterHomeDashboard newInstance() {
        return new AdopterHomeDashboard();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_adopter_home_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Adopter Home");

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        adopterEmail = firebaseUser.getEmail();

        tvAdopterName = view.findViewById(R.id.adopterName);
        tvAdopterContact = view.findViewById(R.id.adopterContact);
        tvAdopterAddress = view.findViewById(R.id.adopterAddress);
        ivAdopterImage = view.findViewById(R.id.adopterImage);
        ivCvAdopterImage = view.findViewById(R.id.cvAdopterImage);
        cvAdopterInfo = view.findViewById(R.id.adopter_info_cv);

        cvAdopterInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AdopterEdit.class);
                startActivity(intent);
            }
        });

        cvApplicationHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ApplicationHistory.class);
                startActivity(intent);
            }
        });

        cvAdoptPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AdopterEdit.class);
                startActivity(intent);
            }
        });

        cvBrowseAnimals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AdopterEdit.class);
                startActivity(intent);
            }
        });

        setUpProfilePic();


    }

    private void setUpProfilePic() {
        //retrieve imageName
        adoptersDBRef.orderByChild("email").equalTo(adopterEmail)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                System.out.println("snapshot setUpProfilePic === " + snapshot);
                        for(DataSnapshot ds : snapshot.getChildren()) {
                            adopterUsername = ds.getKey();
                        }

                        adopterName = snapshot.child(adopterUsername).child("fname").getValue() + " "
                                + snapshot.child(adopterUsername).child("lname").getValue();

                        adopterContact = (String) snapshot.child(adopterUsername).child("contact").getValue();
                        adopterAddress = snapshot.child(adopterUsername).child("street").getValue() + ", "
                                + snapshot.child(adopterUsername).child("city").getValue() + ", " +
                                snapshot.child(adopterUsername).child("province").getValue() + ", " +
                                snapshot.child(adopterUsername).child("country").getValue();

                        tvAdopterName.setText("Name: "+adopterName);
                        tvAdopterContact.setText("Contact: "+adopterContact);
                        tvAdopterAddress.setText("Address: "+adopterAddress);

//                System.out.println("shelterUsername " + shelterUsername);

                        adoptersDBRef.child(adopterUsername).child("imageName").
                                addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        adopterImageName = String.valueOf(snapshot.getValue());
//                                System.out.println("shelterImageName1 == " + shelterImageName);

                                        //DISPLAY IMAGE TO IMAGE VIEW
                                        storageReference.child("Adopters/").child(adopterImageName).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {
                                                Glide.with(getActivity().getApplicationContext()).load(uri.toString()).into(ivCvAdopterImage);
                                                Glide.with(getActivity().getApplicationContext()).load(uri.toString()).into(ivAdopterImage);
                                            }
                                        });

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

}