package com.example.petnership_kairos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.content.Intent;

import androidx.appcompat.widget.Toolbar;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdopterDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * variables
     */
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.adopter_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_adopter);

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
                    beginTransaction().replace(R.id.nav_host_fragment,new AdopterHomeDashboard()).commit();
        }

        /**
         * Hooks
         */
        drawerLayout = findViewById(R.id.drawer_layout_adopter);
        navigationView = findViewById(R.id.nav_view_adopter);
        toolbar = findViewById(R.id.main_toolbar);

        /**
         * Tool bar
         */
//        setSupportActionBar(toolbar);

        /**
         * Navigation Drawer Menu
         */
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.menu_open,R.string.menu_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
//        navigationView.setNavigationItemSelectedListener(this);
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
            super.onBackPressed();
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
            case R.id.nav_adoptHome:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                        new AdopterHomeDashboard()).commit();
                break;

            case R.id.nav_appHistory:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                        new PetProfileDogs()).commit();
//                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
//                        new ApplicationHistoryFragment()).commit();
                break;

            case R.id.nav_adoptAPet:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                        new AdoptionForm()).commit();
                break;

            case R.id.nav_browseAnimals:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                        new ApplicationForm()).commit();
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
//        FirebaseAuth.getInstance().signOut();
//        Intent intent = new Intent(this,LoginActivity.class);
//        startActivity(intent);
//        finish();
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