package com.example.petnership_kairos;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class ApplicationHistoryIndiv extends Fragment {

    private ApplicationHistoryIndivViewModel mViewModel;
    ImageButton backBtnUp;
    Button backBtnDown;
    public static ApplicationHistoryIndiv newInstance() {
        return new ApplicationHistoryIndiv();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_application_history_indiv, container, false);
        backBtnUp = view.findViewById(R.id.btnBack);
        backBtnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStack();
            }
        });
        backBtnDown = view.findViewById(R.id.btn_back_adopter);
        backBtnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStack();
            }
        });


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity();

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ApplicationHistoryIndivViewModel.class);
        // TODO: Use the ViewModel
    }

}