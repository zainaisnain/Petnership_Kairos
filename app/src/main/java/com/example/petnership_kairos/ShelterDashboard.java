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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ShelterDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * variables
     */
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Button logout, cancelLogout;
    Dialog dialog;
    static boolean atHome = true;
    String fragName;
    boolean popHome = false;

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
                    beginTransaction().replace(R.id.nav_host_fragment,new ShelterHomeDashboard()).addToBackStack("Shelter Home").commit();
        }

        /**
         * Hooks
         */
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.main_toolbar);


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
        FragmentManager fm = getSupportFragmentManager();
        int backCount = fm.getBackStackEntryCount();
        System.out.println("Backstack entry count: " + fm.getBackStackEntryCount());
        for (int i = 0; i < fm.getBackStackEntryCount(); i++) {
            System.out.println("--- " + fm.getBackStackEntryAt(i).getName());
        }
        if(drawerLayout.isDrawerOpen((GravityCompat.START)))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            int count = getSupportFragmentManager().getBackStackEntryCount();
            if (count <= 1) {
                MyLogoutDialog logoutDialog = new MyLogoutDialog();
                logoutDialog.show(getSupportFragmentManager(), "My Fragment");

            }
            else {
                    System.out.println("Popped");
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
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        FragmentManager fm = getSupportFragmentManager();
        int backCount = fm.getBackStackEntryCount();

        switch(item.getItemId())
        {

            case R.id.nav_home:

                if (fm.getBackStackEntryAt(backCount-1).getName().equalsIgnoreCase("Shelter Home")) break;
                fm.popBackStackImmediate(fm.getBackStackEntryAt(1).getName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);

                break;

            case R.id.nav_reg_pets:
                if (fm.getBackStackEntryAt(backCount-1).getName().equalsIgnoreCase("Shelter Register Pets")) break;

                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                ShelterRegisterPets shelterRegisterPets = new ShelterRegisterPets();
                transaction.replace(R.id.nav_host_fragment, shelterRegisterPets);
                transaction.addToBackStack("Shelter Register Pets");
                transaction.commit();
                atHome = false;
                break;

            case R.id.nav_reg_my_pets:
                if (fm.getBackStackEntryAt(backCount-1).getName().equalsIgnoreCase("Shelter Registered Pets")) break;

                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                ShelterListOfPetsFragment shelterRegisteredPets = new ShelterListOfPetsFragment();
                transaction.replace(R.id.nav_host_fragment, shelterRegisteredPets);
                transaction.addToBackStack("Shelter Registered Pets");
                transaction.commit();
                atHome = false;
                break;

            case R.id.nav_shelter_edit_info:

                startActivity(new Intent(ShelterDashboard.this, ShelterEditInfo.class));
                this.overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);

                atHome = false;
                break;

            case R.id.nav_shelter_change_password:
                FragmentTransaction transaction4 = getSupportFragmentManager().beginTransaction();
                UserChangePassword userChangePassword = new UserChangePassword();
                transaction4.replace(R.id.nav_host_fragment, userChangePassword);
                transaction4.commit();
                break;

            case R.id.nav_logout:
                MyLogoutDialog logoutDialog = new MyLogoutDialog();
                logoutDialog.show(getSupportFragmentManager(), "My Fragment");
//                userLogout();
//                startActivity(new Intent(ShelterDashboard.this, MyLogoutDialog.class));
//                userLogout();

//                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
//                        new ShelterHomeDashboard()).commit();
//                dialog = new Dialog(ShelterDashboard.this);
//                dialog.setContentView(R.layout.logout_dialog);
//                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//                dialog.setCancelable(false);
//                dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
//
//                logout = dialog.findViewById(R.id.buttonOk);
//                logout.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        userLogout();
//                    }
//                });
//
//                cancelLogout = dialog.findViewById(R.id.buttonCancel);
//                cancelLogout.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//                    }
//                });
                break;
        }

        System.out.println("END OF ONNAV Backstack entry count: " + fm.getBackStackEntryCount());
        for (int i = 0; i < fm.getBackStackEntryCount(); i++) {
            System.out.println("--- " + fm.getBackStackEntryAt(i).getName());
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void userLogout()
    {
//        MyLogoutDialog logoutDialog = new MyLogoutDialog();
//        logoutDialog.show(getSupportFragmentManager(), "My Fragment");

//        FirebaseAuth.getInstance().signOut();
////        startActivity(new Intent(getContext(), LoginActivity.class));
//        Intent intent = new Intent(this,LoginActivity.class);
////        Intent intent = new Intent(ShelterDashboard.getActivity(), LoginActivity.class);
//        startActivity(intent);
//        finish();
//        dialog = new Dialog(ShelterDashboard.this);
//        dialog.setContentView(R.layout.logout_dialog);
//        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//        dialog.setCancelable(false);
//        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
//
//        logout = dialog.findViewById(R.id.buttonOk);
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseAuth.getInstance().signOut();
//                Intent intent = new Intent(ShelterDashboard.this,LoginActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//        cancelLogout = dialog.findViewById(R.id.buttonCancel);
//        cancelLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });

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