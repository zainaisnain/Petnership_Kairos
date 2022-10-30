package com.example.petnership_kairos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.content.Intent;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;

public class StartOfQuestionnaire extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * variables
     */
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Button logout, cancelLogout;
    Dialog dialog;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.shelter_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currUser = auth.getCurrentUser();

//        if(currUser == null)
//        {
//            Intent intent = new Intent(this,LoginActivity.class);
//            startActivity(intent);
//            finish();
//            return;
//        }



        /**
         * Set home fragment as default page
         */
        if(savedInstanceState == null) {
            getSupportFragmentManager().
                    beginTransaction().replace(R.id.nav_host_fragment,new ShelterHomeDashboard()).commit();
        }

        /**
         * Hooks
         */
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.main_toolbar);

        /**
         * Tool bar
         */
        // setSupportActionBar(toolbar);

        /**
         * Navigation Drawer Menu
         */
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.menu_open,R.string.menu_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
    }


    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen((GravityCompat.START)))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            //TODO: FIX ONBACKPRESSED
            int count = getSupportFragmentManager().getBackStackEntryCount();
            if (count <= 1) {
                super.onBackPressed();
            } else {
                getSupportFragmentManager().popBackStack();
            }
        }

    }

    /**
     *
     * Navigation fragments
     */

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {

            case R.id.nav_reg_pets:
                FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                ShelterRegisterPets shelterRegisterPets = new ShelterRegisterPets();
                transaction1.replace(R.id.shelterDashboardFrag, shelterRegisterPets);
                transaction1.commit();
//                getSupportFragmentManager().beginTransaction().replace(R.id.shelter_dashboard_frag,
//                        new ShelterRegisterPets()).commit();
                break;

            case R.id.nav_reg_my_pets:
                getSupportFragmentManager().beginTransaction().replace(R.id.shelterDashboardFrag,
                        new ShelterListOfPetsFragment()).commit();
                break;

            case R.id.nav_shelter_edit_info:
                startActivity(new Intent(StartOfQuestionnaire.this, ShelterEditInfo.class));
                break;

            case R.id.nav_logout:
//                startActivity(new Intent(ShelterDashboard.this, MyLogoutDialog.class));
                userLogout();
//                getSupportFragmentManager().beginTransaction().replace(R.id.shelter_dashboard_frag,
//                        new MyLogoutDialog()).commit();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void userLogout()
    {
//        FirebaseAuth.getInstance().signOut();
//        Intent intent = new Intent(ShelterDashboard.this,LoginActivity.class);
//        startActivity(intent);
//        finish();
        dialog = new Dialog(StartOfQuestionnaire.this);
        dialog.setContentView(R.layout.logout_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        logout = dialog.findViewById(R.id.buttonOk);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(StartOfQuestionnaire.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        cancelLogout = dialog.findViewById(R.id.buttonCancel);
        cancelLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }


//    @Override
//    public void onClick(View view) {
//        Intent i;
//
//        switch (view.getId()) {
//            case R.id.dogs:
//                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
//                        new register()).commit();
//                break;
//
//            default:
//                break;
//        }
//    }
}