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

public class ShelterListOfCatsFragment extends Fragment {
    DatabaseReference petsCatsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("Cats");
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;
    RecyclerView recyclerView;
    View view;
    private String shelterEmail, petID, petImageName;

    private ArrayList<String> petIDs = new ArrayList<>();
    private ArrayList<RegisteredCatData> ALregisteredCatData = new ArrayList<RegisteredCatData>();

    private String petName, petAge, petSex, petBreed;

    RegisteredCatData[] registeredCatData;

    private ImageButton backBtn;
    private ShelterListOfCatsViewModel mViewModel;

    public static ShelterListOfCatsFragment newInstance() {
        return new ShelterListOfCatsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shelter_list_of_cats, container, false);
        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        shelterEmail = firebaseUser.getEmail();
        recyclerView = view.findViewById(R.id.recyclerViewCats);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        withFirebase(recyclerView);
        backBtn = view.findViewById(R.id.btnBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                ShelterHomeDashboard shelterHomeDashboard = new ShelterHomeDashboard();
                transaction.replace(R.id.nav_host_fragment, shelterHomeDashboard);
                transaction.commit();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ShelterListOfCatsViewModel.class);
        // TODO: Use the ViewModel
    }

    private void withFirebase(RecyclerView recyclerView) {
        petsCatsDBRef.orderByChild("shelter").equalTo(shelterEmail)
                .addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {

            for(DataSnapshot ds : snapshot.getChildren()) {
                petID = ds.getKey();
                petIDs.add(petID);

                petImageName = String.valueOf(snapshot.child(petID).child("imageName").getValue());
                petName = String.valueOf(snapshot.child(petID).child("petName").getValue());
                petAge = String.valueOf(snapshot.child(petID).child("petAge").getValue());
                petSex = String.valueOf(snapshot.child(petID).child("petSex").getValue());
                petBreed = String.valueOf(snapshot.child(petID).child("q9").getValue());
                ALregisteredCatData.add( new RegisteredCatData(petID,petImageName, petName, petAge, petSex, petBreed));
            }
            registeredCatData = ALregisteredCatData.toArray(new RegisteredCatData[ALregisteredCatData.size()]);
            System.out.println("size nya: " + registeredCatData.length);
            for (RegisteredCatData element: registeredCatData) {
                System.out.println("zaina: "+element.getImageName());
            }
            RegisteredCatsAdapter registeredCatsAdapter = new RegisteredCatsAdapter(registeredCatData, ShelterListOfCatsFragment.this);
            recyclerView.setAdapter(registeredCatsAdapter);

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });
    }


}