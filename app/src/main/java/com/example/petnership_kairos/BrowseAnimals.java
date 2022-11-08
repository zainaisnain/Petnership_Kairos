package com.example.petnership_kairos;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BrowseAnimals extends Fragment {

    private BrowseAnimalsViewModel mViewModel;
    private ImageButton backBtn;
    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;
    DatabaseReference allPetsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("AllPets");
    DatabaseReference petsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets");
    DatabaseReference catsPetsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("Cats");
    DatabaseReference dogsPetsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("Dogs");
    DatabaseReference adoptersDBRef = FirebaseDatabase.getInstance().getReference("Adopters");
    private String adopterEmail, adopterID;
    private Double petMatch;
    private String petID, petImageName, petName, petAge, petSex, petBreed, petStatus;
    RegisteredPetData[] registeredPetData;
    private ArrayList<String> petIDs = new ArrayList<>();
    private ArrayList<RegisteredPetData> ALregisteredPetData = new ArrayList<RegisteredPetData>();

    boolean AdopterAllPetsUpdated;
    public static BrowseAnimals newInstance() {

        return new BrowseAnimals();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_browse_animals, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        adopterEmail = firebaseUser.getEmail();

//        //update appliedToAdopt to "not yet"
//        updateAppliedToAdopt();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewBrowseAnimals);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        withFirebase(recyclerView);


        backBtn = view.findViewById(R.id.btnBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStack();
            }
        });
    }

    private void withFirebase(RecyclerView recyclerView) {

        adoptersDBRef.orderByChild("email").equalTo(adopterEmail)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            for(DataSnapshot ds : snapshot.getChildren()) {
                                adopterID = ds.getKey();
                            }



                            allPetsDBRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot2) {
                                    for (DataSnapshot ds2 : snapshot2.getChildren()) {
                                        petID = ds2.getKey();
                                        petIDs.add(petID);
                                        System.out.println("petID: " + petID);
                                        System.out.println("snapshot: " + snapshot.getValue());
                                        String valAppliedToAdopt = String.valueOf(snapshot.child(adopterID).child("AdopterAllPets").child(petID).child("appliedToAdopt").getValue());

                                        petMatch = (Double) snapshot.child(adopterID).child("AdopterAllPets").child(petID).child("MatchPercentage").getValue();
                                        if(petMatch == null) {
                                            petMatch = 0.0;
                                        }

                                        System.out.println("-applied: " + valAppliedToAdopt);
                                        if (valAppliedToAdopt.equalsIgnoreCase("not yet") || valAppliedToAdopt.equalsIgnoreCase("false")) {
                                            String status = String.valueOf(ds2.child("petStatus").getValue());
                                            System.out.println("-status: " + status);
                                            if (status.equalsIgnoreCase("Available")){
                                                petImageName = String.valueOf(ds2.child("imageName").getValue());
                                                petName = String.valueOf(ds2.child("petName").getValue());
                                                petAge = String.valueOf(ds2.child("petAgeNum").getValue());
                                                petSex = String.valueOf(ds2.child("petSex").getValue());
                                                System.out.println("-petImageName: " + petImageName);
                                                System.out.println("-petName " + petName);
                                                System.out.println("-petAge: " + petAge);
                                                System.out.println("-petSex: " + petSex);

                                                String petType = String.valueOf(ds2.child("petType").getValue());
                                                System.out.println("-petType: " + petType);
                                                if(petType.equals("dog")){
                                                    petBreed = String.valueOf(ds2.child("q10").getValue());
                                                }else if(petType.equals("cat")){
                                                    petBreed = String.valueOf(ds2.child("q9").getValue());
                                                }
                                                System.out.println("-petBreed: " + petBreed);

                                                ALregisteredPetData.add(new RegisteredPetData(petID, petType, petImageName, petName, petAge, petSex, petBreed, petMatch));
                                            }


                                            for(RegisteredPetData data: ALregisteredPetData){
                                                System.out.println("data == " + data);
                                            }
                                            System.out.println(ALregisteredPetData);
                                        }

                                    }

                                    registeredPetData = ALregisteredPetData.toArray(new RegisteredPetData[ALregisteredPetData.size()]);
                                    BrowseAnimalsAdapter browseAnimalsAdapter = new BrowseAnimalsAdapter(registeredPetData, BrowseAnimals.this, getActivity());
                                    recyclerView.setAdapter(browseAnimalsAdapter);

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
/*
                            //WORKING
                            adoptersDBRef.child(adopterID).child("AdopterAllPets")
                                    .orderByChild("petStatus").equalTo("Available")
                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            for(DataSnapshot ds : snapshot.getChildren()) {
                                                petID = ds.getKey();
                                                petIDs.add(petID);

                                                String valAppliedToAdopt = (String) snapshot.child(petID).child("appliedToAdopt").getValue();

                                                if(valAppliedToAdopt.equals("not yet") || valAppliedToAdopt.equals("false") ){

                                                    petImageName = String.valueOf(snapshot.child(petID).child("imageName").getValue());
                                                    petName = String.valueOf(snapshot.child(petID).child("petName").getValue());
                                                    System.out.println(petName + "'s imageName == " + petImageName);
                                                    petAge = String.valueOf(snapshot.child(petID).child("petAge").getValue());
                                                    petSex = String.valueOf(snapshot.child(petID).child("petSex").getValue());

                                                    String petType = String.valueOf(snapshot.child(petID).child("petType").getValue());
                                                    if(petType.equals("dog")){
                                                        petBreed = String.valueOf(snapshot.child(petID).child("q10").getValue());
                                                    }else if(petType.equals("cat")){
                                                        petBreed = String.valueOf(snapshot.child(petID).child("q9").getValue());
                                                    }
                                                    ALregisteredPetData.add(new RegisteredPetData(petID, petType, petImageName, petName, petAge, petSex, petBreed));
                                                }
                                            }

                                            registeredPetData = ALregisteredPetData.toArray(new RegisteredPetData[ALregisteredPetData.size()]);
                                            BrowseAnimalsAdapter browseAnimalsAdapter = new BrowseAnimalsAdapter(registeredPetData, BrowseAnimals.this, getActivity());
                                            recyclerView.setAdapter(browseAnimalsAdapter);

                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });


 */
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BrowseAnimalsViewModel.class);
        // TODO: Use the ViewModel
    }

}


