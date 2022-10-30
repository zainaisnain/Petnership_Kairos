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
        setContentView(R.layout.activity_drawer);


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
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.main_toolbar);

        /**
         * Tool bar
         */
        setSupportActionBar(toolbar);

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
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
                AdopterHomeDashboard adopterHomeDashboard = new AdopterHomeDashboard();
                transaction.replace(R.id.nav_host_fragment, adopterHomeDashboard);
                transaction.commit();
                //super.onBackPressed();
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
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                        new home()).commit();
                break;

            case R.id.nav_reg_my_pets:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                        new PetProfileDogs()).commit();
                break;

            case R.id.nav_logout:
                userLogout();
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