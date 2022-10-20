package com.example.petnership_kairos;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
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

public class ApplicantsReviewFragment extends Fragment {

    private ApplicantsReviewViewModel mViewModel;
    private ImageButton backBtn;
    public static ApplicantsReviewFragment newInstance() {
        return new ApplicantsReviewFragment();

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_applicants_review, container, false);
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
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewApplicants);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ApplicantsReviewData[] applicantsreviewData = new ApplicantsReviewData[]{
                new ApplicantsReviewData("Juan Dela Cruz","Brownie",R.drawable.profile),
                new ApplicantsReviewData("Maria Dela Cruz","Beauty",R.drawable.profile),
                new ApplicantsReviewData("Jose Dela Cruz","Bruno",R.drawable.profile)

        };

        ApplicantsReviewAdapter applicantsReviewAdapter = new ApplicantsReviewAdapter(applicantsreviewData,ApplicantsReviewFragment.this);
        recyclerView.setAdapter(applicantsReviewAdapter);

        return view;

    }

}