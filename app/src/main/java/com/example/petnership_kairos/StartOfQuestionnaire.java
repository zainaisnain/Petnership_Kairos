package com.example.petnership_kairos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartOfQuestionnaire extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * variables
     */
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_adopter);


//        if(currUser == null)
//        {
//            Intent intent = new Intent(this,LoginActivity.class);
//            startActivity(intent);
//            finish();
//            return;
//        }

        /**
         * Set Questionnaire Welcoem page fragment as default page
         */
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                    new FragmentQuestionnaireWelcome()).addToBackStack("questionnaireHomeFragment").commit();

        }

        /**
         * Hooks
         */
        drawerLayout = findViewById(R.id.drawer_layout_adopter);
        navigationView = findViewById(R.id.nav_view_adopter);
        toolbar = findViewById(R.id.main_toolbar);


        /**
         * Navigation Drawer Menu
         */
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.menu_open,R.string.menu_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_adoptHome);

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

                DialogQuestionnaireCancel checkCancel = new DialogQuestionnaireCancel();
                checkCancel.show(getSupportFragmentManager(), "Questionnaire Cancel");

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
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch(item.getItemId())
        {

            case R.id.nav_adoptHome:
                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                AdopterHomeDashboard adopterHome = new AdopterHomeDashboard();
                transaction.replace(R.id.nav_host_fragment,adopterHome);
                transaction.commit();
                break;

            case R.id.nav_appHistory:
                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                ApplicationHistoryFragment applicationHistory = new ApplicationHistoryFragment();
                transaction.replace(R.id.nav_host_fragment,applicationHistory);
                transaction.commit();
                break;

            case R.id.nav_adoptAPet:
                startActivity(new Intent(this, StartOfQuestionnaire.class));
                this.overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
                break;

            case R.id.nav_browseAnimals:
                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                BrowseAnimals browseAnimals = new BrowseAnimals();
                transaction.replace(R.id.nav_host_fragment,browseAnimals);
                transaction.commit();
                break;

            case R.id.nav_logout:
                MyLogoutDialog logoutDialog = new MyLogoutDialog();
                logoutDialog.show(getSupportFragmentManager(), "My Fragment");
//                userLogout();
                break;


        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void userLogout()
    {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

}