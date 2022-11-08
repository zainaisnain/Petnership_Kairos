package com.example.petnership_kairos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
    MCDMAnswersViewModel mViewModel;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_adopter);

        mViewModel = new ViewModelProvider(this).get(MCDMAnswersViewModel.class);

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
        navigationView.setCheckedItem(R.id.nav_adoptAPet);

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

            } else if (getSupportFragmentManager().getBackStackEntryAt(count-1).getName().equalsIgnoreCase("recommendedPets")) {

                DialogQuestionnaireCancelAfterRecommendations checkCancelAfter = new DialogQuestionnaireCancelAfterRecommendations();
                checkCancelAfter.show(getSupportFragmentManager(), "Cancel");
            }else if (getSupportFragmentManager().getBackStackEntryAt(count-1).getName().equalsIgnoreCase("recommendation to adoption")) {

                MyCancelDialogGoToDogProfile myCancelDialogGoToDogProfile = new MyCancelDialogGoToDogProfile();
                myCancelDialogGoToDogProfile.show(getSupportFragmentManager(), "My Fragment");
            }
            else {
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

                if (mViewModel.getFinishedAnswering()) {

                    DialogQuestionnaireCancelAfterRecommendations checkCancelAfter = new DialogQuestionnaireCancelAfterRecommendations();
                    checkCancelAfter.show(getSupportFragmentManager(), "Cancel Home");
                }
                else {

                    DialogQuestionnaireCancel checkCancelHome = new DialogQuestionnaireCancel();
                    checkCancelHome.show(getSupportFragmentManager(), "Cancel Home");

                }
                /*transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                AdopterHomeDashboard adopterHome = new AdopterHomeDashboard();
                transaction.replace(R.id.nav_host_fragment,adopterHome);
                transaction.commit();

                 */
                break;

            case R.id.nav_appHistory:

                if (mViewModel.getFinishedAnswering()) {

                    DialogQuestionnaireCancelAfterRecommendations checkCancelAfter = new DialogQuestionnaireCancelAfterRecommendations();
                    checkCancelAfter.show(getSupportFragmentManager(), "Cancel History");
                }
                else {

                    DialogQuestionnaireCancel checkCancelHistory = new DialogQuestionnaireCancel();
                    checkCancelHistory.show(getSupportFragmentManager(), "Cancel History");
                }
                break;

            case R.id.nav_adoptAPet:
      //          getSupportFragmentManager().popBackStackImmediate("x", );
                Toast.makeText(this, "You're already in this screen!", Toast.LENGTH_SHORT).show();

                break;

            case R.id.nav_browseAnimals:

                if (mViewModel.getFinishedAnswering()) {

                    DialogQuestionnaireCancelAfterRecommendations checkCancelAfter = new DialogQuestionnaireCancelAfterRecommendations();
                    checkCancelAfter.show(getSupportFragmentManager(), "Cancel Browse");
                }
                else {
                    DialogQuestionnaireCancel checkCancelBrowse = new DialogQuestionnaireCancel();
                    checkCancelBrowse.show(getSupportFragmentManager(), "Cancel Browse");
                }

                break;


            case R.id.nav_adopter_update_email:

                if (mViewModel.getFinishedAnswering()) {

                    DialogQuestionnaireCancelAfterRecommendations checkCancelAfter = new DialogQuestionnaireCancelAfterRecommendations();
                    checkCancelAfter.show(getSupportFragmentManager(), "Cancel Update Email");
                }
                else {
                    DialogQuestionnaireCancel checkCancelUpdateEmail = new DialogQuestionnaireCancel();
                    checkCancelUpdateEmail.show(getSupportFragmentManager(), "Cancel Update Email");
                }
                break;

            case R.id.nav_adopter_change_password:

                if (mViewModel.getFinishedAnswering()) {

                    DialogQuestionnaireCancelAfterRecommendations checkCancelAfter = new DialogQuestionnaireCancelAfterRecommendations();
                    checkCancelAfter.show(getSupportFragmentManager(), "Cancel Change Password");
                }
                else {
                    DialogQuestionnaireCancel checkCancelChangePassword = new DialogQuestionnaireCancel();
                    checkCancelChangePassword.show(getSupportFragmentManager(), "Cancel Change Password");
                }
                break;

            case R.id.nav_logout:

                if (mViewModel.getFinishedAnswering()) {

                    DialogQuestionnaireCancelAfterRecommendations checkCancelAfter = new DialogQuestionnaireCancelAfterRecommendations();
                    checkCancelAfter.show(getSupportFragmentManager(), "Cancel Out");
                }
                else {
                    DialogQuestionnaireCancel checkCancelOut = new DialogQuestionnaireCancel();
                    checkCancelOut.show(getSupportFragmentManager(), "Cancel Out");
                }
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