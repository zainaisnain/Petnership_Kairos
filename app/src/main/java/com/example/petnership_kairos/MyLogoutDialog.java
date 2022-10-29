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

public class MyLogoutDialog extends DialogFragment {

    Button logout, cancelLogout;

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.logout_dialog, container, false);

        logout = view.findViewById(R.id.buttonOk);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //FRAGMENT to FRAGMENT
                ShelterDashboard shelterDashboard = new ShelterDashboard();
                shelterDashboard.userLogout();
//                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
//                AdopterHomeDashboard adopterHomeDashboard = new AdopterHomeDashboard();
//                transaction.replace(R.id.adoptionFormFrag, adopterHomeDashboard);
//                transaction.commit();
            }
        });

        cancelLogout = view.findViewById(R.id.buttonCancel);
        cancelLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }
}
