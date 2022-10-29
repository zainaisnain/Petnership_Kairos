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

import java.util.ArrayList;

public class ApplicationHistoryFragment extends Fragment {
    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;

    DatabaseReference usersDBRef = FirebaseDatabase.getInstance().getReference("Users");
    DatabaseReference adoptersDBRef = FirebaseDatabase.getInstance().getReference("Adopters");
    DatabaseReference sheltersDBRef = FirebaseDatabase.getInstance().getReference("Shelters");
    String shelterID, shelterEmail, adopterID, adopterEmail, adopterName, petID, petName, applicationID, dateApplied, applicationStatus;

    ApplicationHistoryData[] applicationHistoryData;
    private ArrayList<ApplicationHistoryData> ALApplicationHistoryData = new ArrayList<>();
    private ApplicationHistoryViewModel mViewModel;
    View view;
    RecyclerView recyclerView;
    ImageButton backBtn;
    public static ApplicationHistoryFragment newInstance() {
        return new ApplicationHistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_application_history, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        adopterEmail = firebaseUser.getEmail();

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

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewApplications);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getFromDB(recyclerView);
    }

    private void getFromDB(RecyclerView recyclerView){
        adoptersDBRef.orderByChild("email").equalTo(adopterEmail).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot adopterSnapshot) {
                if(adopterSnapshot.exists()){
                    for(DataSnapshot ds : adopterSnapshot.getChildren()) {
                        adopterID = ds.getKey();
                    }

                    if(adopterSnapshot.child(adopterID).hasChild("ApplicationHistory")){
                        adoptersDBRef.child(adopterID).child("ApplicationHistory").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                for(DataSnapshot ds : snapshot.getChildren()) {
                                    applicationID = ds.getKey();

                                    shelterID = (String) snapshot.child(applicationID).child("shelterID").getValue();
                                    petID = (String) snapshot.child(applicationID).child("petID").getValue();
                                    petName = (String) snapshot.child(applicationID).child("petName").getValue();
                                    applicationStatus = (String) snapshot.child(applicationID).child("applicationStatus").getValue();
                                    dateApplied = (String) snapshot.child(applicationID).child("dateApplied").getValue();

                                    ALApplicationHistoryData.add(new ApplicationHistoryData(applicationID, shelterID, petID, petName, applicationStatus, dateApplied));
                                }
                                applicationHistoryData = ALApplicationHistoryData.toArray(new ApplicationHistoryData[ALApplicationHistoryData.size()]);
                                ApplicationHistoryAdapter applicationHistoryAdapter = new ApplicationHistoryAdapter(applicationHistoryData, ApplicationHistoryFragment.this);
                                recyclerView.setAdapter(applicationHistoryAdapter);
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ApplicationHistoryViewModel.class);
        // TODO: Use the ViewModel
    }

}