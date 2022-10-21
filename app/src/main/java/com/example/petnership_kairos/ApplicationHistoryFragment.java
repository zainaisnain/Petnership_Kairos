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

public class ApplicationHistoryFragment extends Fragment {

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

        ApplicationHistoryData[] applicationHistoryData = new ApplicationHistoryData[]{
                new ApplicationHistoryData("Alpha","Denied",R.drawable.cat_dog),
                new ApplicationHistoryData("Bravo","Pending",R.drawable.cat_dog),
                new ApplicationHistoryData("Charlie","Approved",R.drawable.cat_dog)

        };

        ApplicationHistoryAdapter applicationHistoryAdapter = new ApplicationHistoryAdapter(applicationHistoryData,ApplicationHistoryFragment.this);
        recyclerView.setAdapter(applicationHistoryAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ApplicationHistoryViewModel.class);
        // TODO: Use the ViewModel
    }

}