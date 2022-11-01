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

public class ActiveAdopters extends Fragment {

    private ActiveAdoptersViewModel mViewModel;
    private ImageButton backBtn;
    public static ActiveAdopters newInstance() {
        return new ActiveAdopters();

    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_active_adopters, container, false);
        backBtn = view.findViewById(R.id.btnBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                ShelterHomeDashboard shelterHomeDashboard = new ShelterHomeDashboard();
                transaction.replace(R.id.active_adopters_frag, shelterHomeDashboard);
                transaction.commit();
            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewAdopters);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ActiveAdoptersData[] activeAdoptersData = new ActiveAdoptersData[]{
                new ActiveAdoptersData("Krysch Atienza", R.drawable.profile),
                new ActiveAdoptersData("Jannel Lacbain", R.drawable.profile),
                new ActiveAdoptersData("Tristan Bonus", R.drawable.profile),
                new ActiveAdoptersData("Zaina Isnain", R.drawable.profile)

        };

        ActiveAdoptersAdapter activeAdoptersAdapter = new ActiveAdoptersAdapter(activeAdoptersData, ActiveAdopters.this);
        recyclerView.setAdapter(activeAdoptersAdapter);

        return view;
    }


    }