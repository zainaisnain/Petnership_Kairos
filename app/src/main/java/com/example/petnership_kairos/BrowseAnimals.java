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

public class BrowseAnimals extends Fragment {

    private BrowseAnimalsViewModel mViewModel;
    private ImageButton backBtn;
    public static BrowseAnimals newInstance() {

        return new BrowseAnimals();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_browse_animals, container, false);
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
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewBrowseAnimals);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        BrowseAnimalsData[] browseAnimalsData = new BrowseAnimalsData[]{
                new BrowseAnimalsData("Brownie", "13 years", "Male", "Aspin", R.drawable.cat_dog),
                new BrowseAnimalsData("Beauty", "10 years", "Female", "Aspin", R.drawable.cat_dog),
                new BrowseAnimalsData("Bruno", "8 years", "Male", "Aspin", R.drawable.cat_dog)

        };

        BrowseAnimalsAdapter browseAnimalsAdapter = new BrowseAnimalsAdapter(browseAnimalsData, BrowseAnimals.this);
        recyclerView.setAdapter(browseAnimalsAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BrowseAnimalsViewModel.class);
        // TODO: Use the ViewModel
    }

}


