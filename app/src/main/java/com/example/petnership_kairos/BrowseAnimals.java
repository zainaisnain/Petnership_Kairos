package com.example.petnership_kairos;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BrowseAnimals extends Fragment {

    private BrowseAnimalsViewModel mViewModel;
    private ImageButton backBtn;
    DatabaseReference allPetsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("AllPets");

    private String petID;
    private String petImageName, petName, petAge, petSex, petBreed;
    RegisteredPetData[] registeredPetData;
    private ArrayList<String> petIDs = new ArrayList<>();
    private ArrayList<RegisteredPetData> ALregisteredPetData = new ArrayList<RegisteredPetData>();
    public static BrowseAnimals newInstance() {

        return new BrowseAnimals();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_browse_animals, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewBrowseAnimals);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        withFirebase(recyclerView);

        backBtn = view.findViewById(R.id.btnBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                AdopterHomeDashboard adopterHomeDashboard = new AdopterHomeDashboard();
                transaction.replace(R.id.nav_host_fragment, adopterHomeDashboard);
                transaction.commit();
            }
        });

        return view;
    }

    private void withFirebase(RecyclerView recyclerView) {
        //WITH ALLPETS CHILD
        //might need to add petType to differentiate between cats and dogs for breed type
        allPetsDBRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds : snapshot.getChildren()) {
                            petID = ds.getKey();
                            petIDs.add(petID);

                            petImageName = String.valueOf(snapshot.child(petID).child("imageName").getValue());
                            petName = String.valueOf(snapshot.child(petID).child("petName").getValue());
                            petAge = String.valueOf(snapshot.child(petID).child("petAge").getValue());
                            petSex = String.valueOf(snapshot.child(petID).child("petSex").getValue());

                            String petType = String.valueOf(snapshot.child(petID).child("petType").getValue());
                            if(petType.equals("dog")){
                                petBreed = String.valueOf(snapshot.child(petID).child("q10").getValue());
                            }else if(petType.equals("cat")){
                                petBreed = String.valueOf(snapshot.child(petID).child("q9").getValue());
                            }
//                                    petBreed = String.valueOf(snapshot.child(petID).child("q9").getValue());
                            ALregisteredPetData.add( new RegisteredPetData(petID, petImageName, petName, petAge, petSex, petBreed));
                        }

                        registeredPetData = ALregisteredPetData.toArray(new RegisteredPetData[ALregisteredPetData.size()]);
                        BrowseAnimalsAdapter browseAnimalsAdapter = new BrowseAnimalsAdapter(registeredPetData, BrowseAnimals.this);
                        recyclerView.setAdapter(browseAnimalsAdapter);

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


