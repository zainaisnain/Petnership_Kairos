package com.example.petnership_kairos;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MyCancelDialog2 extends DialogFragment{

    Button btnBack, btnCancel;

    FirebaseAuth auth = FirebaseAuth.getInstance();
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_cancel_dialog, container, false);

//        Intent intent = new Intent(getActivity(), ShelterDashboard.class);
        btnBack = view.findViewById(R.id.buttonYes);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FRAGMENT to FRAGMENT
//                ShelterDashboard shelterDashboard = new ShelterDashboard();
//                shelterDashboard.userLogout();
//                startActivity(intent);
//                dismiss();
//                auth.getInstance().signOut();
                getParentFragmentManager().popBackStack();
//                Intent intent = new Intent(getActivity(),ShelterPerCatProfile.class);
//                startActivity(intent);
                dismiss();

            }
        });

        btnCancel = view.findViewById(R.id.buttonNo);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }
}


