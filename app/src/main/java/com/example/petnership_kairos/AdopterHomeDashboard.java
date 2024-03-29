package com.example.petnership_kairos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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

import java.util.ArrayList;
// if nagka-error:
// public class AdopterHomeDashboard<cvApplicationHistory> extends Fragment {
public class AdopterHomeDashboard extends Fragment {



    private AdopterHomeDashboardViewModel mViewModel;

    DatabaseReference petsCatsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("Cats");
    DatabaseReference petsDogsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("Dogs");
    DatabaseReference allPetsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("AllPets");
    DatabaseReference sheltersDBRef = FirebaseDatabase.getInstance().getReference("Shelters");
    DatabaseReference adoptersDBRef = FirebaseDatabase.getInstance().getReference("Adopters");
    private String adopterEmail, adopterID, adopterImageName, adopterName, adopterContact, adopterAddress, petID, existingPetID, allPetsID;
    private ImageView ivAdopterImage, ivCvAdopterImage;
    private CardView cvAdopterInfo;
    private CardView cvApplicationHistory;
    private CardView cvAdoptPet;
    private CardView cvBrowseAnimals;
    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;
    // instance for firebase storage and StorageReference
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private String appliedToAdopt = "not yet";
    private ArrayList<String> existingPetIDs = new ArrayList<>();
    private ArrayList<String> allPetsIDs = new ArrayList<>();

    private ArrayList<String> diffPetsIDs = new ArrayList<>();



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

//        tvAdopterName = view.findViewById(R.id.adopterName);
//        tvAdopterContact = view.findViewById(R.id.adopterContact);
//        tvAdopterAddress = view.findViewById(R.id.adopterAddress);
        ivAdopterImage = view.findViewById(R.id.adopterImage);
        ivCvAdopterImage = view.findViewById(R.id.cvAdopterImage);
        cvAdopterInfo = view.findViewById(R.id.adopter_info_cv);
        cvAdoptPet =  view.findViewById(R.id.adopterIntBrowseShelter);
        cvBrowseAnimals = view.findViewById(R.id.adopterIntBrowseAnimal);

        createCopyOfAllPets();
        cvAdopterInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AdopterEditInfo.class);
                startActivity(intent);
            }
        });

        cvApplicationHistory = view.findViewById(R.id.adopterIntAppHistory);
        cvApplicationHistory.setOnClickListener(v -> {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                ApplicationHistoryFragment applicationHistory = new ApplicationHistoryFragment();
                transaction.replace(R.id.nav_host_fragment,applicationHistory);
                transaction.addToBackStack("applicationHistory");
                transaction.commit();
        });

        cvAdoptPet.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), StartOfQuestionnaire.class));
            getActivity().overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
        });

        cvBrowseAnimals.setOnClickListener(view12 -> {

            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            BrowseAnimals browseAnimals = new BrowseAnimals();
            transaction.replace(R.id.nav_host_fragment,browseAnimals);
            transaction.addToBackStack("browseAnimals");
            transaction.commit();
        });

        setUpProfilePic();


    }

    private void createCopyOfAllPets() {
        //first check if AdopterAllPets exists
            //if yes:
                //only append petID not in the list
            // if no: create copy
        //create a copy of AllPets node to adopter's node
        adoptersDBRef.orderByChild("email").equalTo(adopterEmail)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot adoptersDBsnapshot) {
                        if(adoptersDBsnapshot.exists()){
                            for(DataSnapshot ds : adoptersDBsnapshot.getChildren()) {
                                adopterID = ds.getKey();
                            }

                            if(adoptersDBsnapshot.child(adopterID).hasChild("AdopterAllPets")){
                                for(DataSnapshot petID: adoptersDBsnapshot.child(adopterID).child("AdopterAllPets").getChildren()){
                                    existingPetID = petID.getKey();
                                    existingPetIDs.add(existingPetID);
                                    if (!adoptersDBsnapshot.child(adopterID).child("AdopterAllPets")
                                            .child(existingPetID).hasChild("appliedToAdopt")){
                                        adoptersDBsnapshot.child(adopterID).child("AdopterAllPets")
                                                .child(existingPetID).child("appliedToAdopt").getRef().setValue("not yet");
                                    }
                                }

                                //ADD the updated pet
                                allPetsDBRef.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        for(DataSnapshot ds : snapshot.getChildren()) {
                                            allPetsID = ds.getKey();
                                            allPetsIDs.add(allPetsID);
                                        }
                                        allPetsIDs.removeAll(existingPetIDs);

                                        for(String petID: allPetsIDs){
                                            adoptersDBsnapshot.child(adopterID).child("AdopterAllPets").child(petID)
                                                    .getRef().setValue(snapshot.child(petID).getValue());
                                        }
                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }else{
                                allPetsDBRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        //Create copy to AdopterAllPets
                                        adoptersDBRef.child(adopterID).child("AdopterAllPets")
                                                .setValue(snapshot.getValue()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                    }
                                                });
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

    private void setUpProfilePic() {
        //retrieve imageName
        adoptersDBRef.orderByChild("email").equalTo(adopterEmail)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds : snapshot.getChildren()) {
                            adopterID = ds.getKey();
                        }

                        adoptersDBRef.child(adopterID).orderByKey().equalTo("imageName").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists()){
                                    adoptersDBRef.child(adopterID).child("imageName").
                                            addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    adopterImageName = String.valueOf(snapshot.getValue());
                                                    if(adopterImageName != null){
                                                        if(!adopterImageName.isEmpty()){
                                                            if(adopterImageName != ""){
                                                                //DISPLAY IMAGE TO IMAGE VIEW
                                                                storageReference.child("Adopters/").child(adopterImageName).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                    @Override
                                                                    public void onSuccess(Uri uri) {
                                                                        Glide.with(getActivity().getApplicationContext()).load(uri.toString()).into(ivCvAdopterImage);
                                                                        //    Glide.with(getActivity().getApplicationContext()).load(uri.toString()).into(ivAdopterImage);
                                                                    }
                                                                });
                                                            }
                                                        }
                                                    }
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {

                                                }
                                            });
                                }
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