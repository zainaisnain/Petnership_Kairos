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
import android.widget.TextView;

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

    private TextView tvDoglvl1, tvDoglvl2, tvDoglvl3, tvDoglvl4, tvDoglvl5,
            tvDoglvl6, tvDoglvl7, tvDoglvl8, tvDoglvl9, tvDoglvl10, tvDoglvl11;

    private String[] popularityHigh =
            {"Retrievers (Labrador)", "French Bulldogs", "Retrievers (Golden)", "German Shepherd Dogs", "Poodles",
                    "Bulldogs", "Beagles", "Rottweilers", "Pointers (German Shorthaired)", "Dachshunds",
                    "Pembroke Welsh Corgis", "Australian Shepherds", "Yorkshire Terriers", "Boxers", "Cavalier King Charles Spaniels",
                    "Doberman Pinschers", "Great Danes", "Miniature Schnauzers", "Siberian Huskies", "Bernese Mountain Dogs",
                    "Cane Corso", "Shih Tzu", "Boston Terriers", "Pomeranians", "Havanese"};

    private String[] popularityMedium =
            {"Spaniels (English Springer)", "Brittanys", "Shetland Sheepdogs", "Spaniels (Cocker)", "Miniature American Shepherds",
                    "Border Collies", "Vizslas", "Pugs", "Basset Hounds", "Mastiffs",
                    "Belgian Malinois", "Chihuahuas", "Collies", "Maltese", "Weimaraners",
                    "Rhodesian Ridgebacks", "Shiba Inu", "Spaniels (English Cocker)", "Portuguese Water Dogs", "Newfoundlands",
                    "West Highland White Terriers", "Bichons Frises", "Retrievers (Chesapeake Bay)", "Dalmatians", "Bloodhounds",
                    "Australian Cattle Dogs", "Akitas", "St. Bernards", "Papillons", "Samoyeds",
                    "Bullmastiffs", "Whippets", "Scottish Terriers", "Pointers (German Wirehaired)", "Wirehaired Pointing Griffons",
                    "Bull Terriers", "Airedale Terriers", "Great Pyrenees", "Chinese Shar-Pei", "Giant Schnauzers",
                    "Soft Coated Wheaten Terriers", "Cardigan Welsh Corgis", "Alaskan Malamutes", "Old English Sheepdogs", "Dogues de Bordeaux",
                    "Setters (Irish)", "Russell Terriers", "Italian Greyhounds", "Cairn Terriers", "Staffordshire Bull Terriers",
                    "Miniature Pinschers", "Chinese Crested", "Greater Swiss Mountain Dogs", "Lagotti Romagnoli", "Chow Chows",
                    "American Staffordshire Terriers", "Biewer Terriers", "Coton de Tulear", "Lhasa Apsos", "Irish Wolfhounds",
                    "Rat Terriers", "Basenjis", "Anatolian Shepherd Dogs", "Dogo Argentinos", "Spaniels (Boykin)",
                    "Border Terriers", "Retrievers (Nova Scotia Duck Tolling)", "Retrievers (Flat-Coated)", "Pekingese", "Keeshonden",
                    "Standard Schnauzers", "Brussels Griffons", "Setters (English)", "Fox Terriers (Wire)", "Norwegian Elkhounds"};

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

        tvDoglvl1 = view.findViewById(R.id.doglevel4a2);
        tvDoglvl2 = view.findViewById(R.id.doglevel4b2);
        tvDoglvl3 = view.findViewById(R.id.doglevel4a3);
        tvDoglvl4 = view.findViewById(R.id.doglevel4b3);
        tvDoglvl5 = view.findViewById(R.id.doglevel3);
        tvDoglvl6 = view.findViewById(R.id.doglevel4);
        tvDoglvl7 = view.findViewById(R.id.doglevel5a);
        tvDoglvl8 = view.findViewById(R.id.doglevel5b);
        tvDoglvl9 = view.findViewById(R.id.doglevel5c);
        tvDoglvl10 = view.findViewById(R.id.doglevel6);
        tvDoglvl11 = view.findViewById(R.id.doglevel7);

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