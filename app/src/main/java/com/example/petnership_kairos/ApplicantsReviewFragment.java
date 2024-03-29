package com.example.petnership_kairos;

import androidx.fragment.app.FragmentTransaction;

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

public class ApplicantsReviewFragment extends Fragment {
    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;
    DatabaseReference usersDBRef = FirebaseDatabase.getInstance().getReference("Users");
    DatabaseReference adoptersDBRef = FirebaseDatabase.getInstance().getReference("Adopters");
    DatabaseReference sheltersDBRef = FirebaseDatabase.getInstance().getReference("Shelters");
    String shelterID, petBirthday, shelterEmail, adopterID, adopterEmail, adopterName, petID, petName, applicationID, dateApplied, applicationStatus;
    ApplicantsReviewData[] applicantsReviewData;
    private ArrayList<ApplicantsReviewData> ALApplicantsReviewData = new ArrayList<ApplicantsReviewData>();

    private ApplicantsReviewViewModel mViewModel;
    private ImageButton backBtn;
    public static ApplicantsReviewFragment newInstance() {
        return new ApplicantsReviewFragment();

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_applicants_review, container, false);

        return view;

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        shelterEmail = firebaseUser.getEmail();

        System.out.println("Entered ApplicantsReviewFragment");
        backBtn = view.findViewById(R.id.btnBack);
        backBtn.setOnClickListener(view1 -> getParentFragmentManager().popBackStack());
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewApplicants);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getFromDB(recyclerView);
    }

    private void getFromDB(RecyclerView recyclerView){
        System.out.println("enterdgetFromDB");
        System.out.println("shelterEmail == " + shelterEmail);
        sheltersDBRef.orderByChild("email").equalTo(shelterEmail).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot sheltersSnapshot) {
                System.out.println("entered onDataChange");
                System.out.println("sheltersSnapshot  == " + sheltersSnapshot);
                if(sheltersSnapshot.exists()){
                    for(DataSnapshot ds : sheltersSnapshot.getChildren()) {
                        shelterID = ds.getKey();
                    }

                    System.out.println("shelterID ARF === " + shelterID);
                    if(sheltersSnapshot.child(shelterID).hasChild("ForReviewApplicants")){
                        sheltersDBRef.child(shelterID).child("ForReviewApplicants").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                for(DataSnapshot ds : snapshot.getChildren()) {
                                    applicationID = ds.getKey();
                                    System.out.println("applicationID ARF === " + applicationID);

                                    adopterID = (String) snapshot.child(applicationID).child("adopterID").getValue();
                                    adopterName = (String) snapshot.child(applicationID).child("adopterName").getValue();
                                    System.out.println("adopterName ApplicantsReviewFrag getFromDB == " + adopterName);
                                    petID = (String) snapshot.child(applicationID).child("petID").getValue();
                                    petName = (String) snapshot.child(applicationID).child("petName").getValue();
                                    petBirthday = (String) snapshot.child(applicationID).child("petAge").getValue();
                                    applicationStatus = (String) snapshot.child(applicationID).child("applicationStatus").getValue();
                                    dateApplied = (String) snapshot.child(applicationID).child("dateApplied").getValue();
                                    if (applicationStatus.equalsIgnoreCase("Rejected")) {
                                        continue;
                                    }

                                    ALApplicantsReviewData.add( new ApplicantsReviewData(applicationID, adopterID, adopterName, petID, petName, applicationStatus, dateApplied));
                                }

                                applicantsReviewData = ALApplicantsReviewData.toArray(new ApplicantsReviewData[ALApplicantsReviewData.size()]);
                                ApplicantsReviewAdapter applicantsReviewAdapter = new ApplicantsReviewAdapter(applicantsReviewData,ApplicantsReviewFragment.this);
                                recyclerView.setAdapter(applicantsReviewAdapter);
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
}