package com.example.petnership_kairos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.UUID;

public class ShelterRegistration extends AppCompatActivity implements View.OnClickListener  {

    private EditText editTextBizName, editTextOwner, editTextEmail, editTextUsername,
            editTextPassword, editTextConfirmPassword, editTextWebsite, editTextContact, editTextStreet,
            editTextCity, editTextCountry;

    private Button submit, cancelBtn;
    private ImageButton backBtn;
    private FirebaseAuth mAuth;

    FirebaseAuth auth = FirebaseAuth.getInstance();
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    //DROPDOWNS
    Spinner ddProvince;
    private String shelterProvince;

    private String[] ddProvincesValues = {"Abra", "Agusan del Norte", "Agusan del Sur", "Aklan", "Albay", "Antique", "Apayao", "Aurora",
            "Basilan", "Bataan", "Batanes", "Batangas", "Benguet", "Biliran", "Bohol", "Bukidnon", "Bulacan", "Cagayan", "Camarines Norte",
            "Camarines Sur", "Camiguin", "Capiz", "Catanduanes", "Cavite", "Cebu", "Cotabato", "Davao de Oro (Compostela Valley)",
            "Davao del Norte", "Davao del Sur", "Davao Occidental", "Davao Oriental", "Dinagat Islands", "Eastern Samar",
            "Guimaras", "Ifugao", "Ilocos Norte", "Ilocos Sur", "Iloilo", "Isabela", "Kalinga", "La Union", "Laguna", "Lanao del Norte",
            "Lanao del Sur", "Leyte", "Maguindanao", "Marinduque", "Masbate", "Metro Manila", "Misamis Occidental", "Misamis Oriental",
            "Mountain Province", "Negros Occidental", "Negros Oriental", "Northern Samar", "Nueva Ecija", "Nueva Vizcaya", "Occidental Mindoro",
            "Oriental Mindoro", "Palawan", "Pampanga", "Pangasinan", "Quezon", "Quirino", "Rizal", "Romblon", "Samar", "Sarangani", "Siquijor",
            "Sorsogon", "South Cotabato", "Southern Leyte", "Sultan Kudarat", "Sulu", "Surigao del Norte", "Surigao del Sur", "Tarlac",
            "Tawi-Tawi", "Zambales", "Zamboanga del Norte", "Zamboanga del Sur", "Zamboanga Sibugay"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_registration);

        editTextBizName = findViewById(R.id.txt_biz_name_shelter);
        editTextOwner = findViewById(R.id.txt_biz_owner_shelter);
        editTextEmail = findViewById(R.id.txt_email_shelter);
        editTextUsername = findViewById(R.id.txt_username_shelter);
        editTextPassword = findViewById(R.id.txt_password_shelter);
        editTextConfirmPassword = findViewById(R.id.txt_confirmpassword_shelter);
        editTextWebsite = findViewById(R.id.txt_website_shelter);
        editTextContact = findViewById(R.id.txt_contact_shelter);
        editTextStreet = findViewById(R.id.txt_street_shelter);
        editTextCity = findViewById(R.id.txt_city_shelter);

        //PROVINCES
        ddProvince = findViewById(R.id.dd_province_shelter);
        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, ddProvincesValues);
        ddProvince.setAdapter(provinceAdapter);
        ddProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                shelterProvince = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        editTextCountry = findViewById(R.id.txt_country_shelter);
        editTextCountry.setEnabled(false);

        submit = findViewById(R.id.btn_submit_shelter);
        submit.setOnClickListener(this);


        backBtn = (ImageButton) findViewById(R.id.btnBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShelterRegistration.this,ActivityUserType.class);
                startActivity(intent);

            }
        });
        cancelBtn = (Button) findViewById(R.id.shelterRegisterCancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showcancelDialog();
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_submit_shelter:
                registerShelter();
                break;
        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("provinces.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void showcancelDialog() {
        final Dialog cancelDialog  = new Dialog(this);
        cancelDialog.setContentView(R.layout.activity_cancel_dialog);
        cancelDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cancelDialog.show();
        Button yesBTN = (Button) cancelDialog.findViewById(R.id.buttonYes);
        yesBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(ShelterRegistration.this, LoginActivity.class));
                cancelDialog.dismiss();
            }
        });
        Button noBTN = (Button) cancelDialog.findViewById(R.id.buttonNo);
        noBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                cancelDialog.dismiss();
            }
        });
    }
    private void registerShelter() {
        String bizName = editTextBizName.getText().toString().trim();
        String owner = editTextOwner.getText().toString().trim();
        String email = editTextEmail.getText().toString().toLowerCase();
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();
        String website = editTextWebsite.getText().toString().trim();
        String contact = editTextContact.getText().toString().trim();
        String street = editTextStreet.getText().toString().trim();
        String city = editTextCity.getText().toString().trim();
        String country = "Philippines";

        int contactNumCount = 0;
        for(int i = 0; i < contact.length(); i++){
            if(contact.charAt(i) != ' ')
                contactNumCount++;
        }

        if(bizName.isEmpty()){
            editTextBizName.setError("Business Name Required.");
            editTextOwner.requestFocus();
            return;
        }

        if(owner.isEmpty()){
            editTextOwner.setError("Owner Required.");
            editTextOwner.requestFocus();
            return;
        }

        if(email.isEmpty()){
            editTextEmail.setError("Email Required.");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please provide valid email.");
            editTextEmail.requestFocus();
            return;
        }

        if(username.isEmpty()){
            editTextUsername.setError("Username Required.");
            editTextUsername.requestFocus();
            return;
        }else if(username.contains(".") || username.contains("#") || username.contains("$") || username.contains("[") ||username.contains("]")){
            editTextUsername.setError("Username must not contain '.', '#', '$', '[', or ']' ");
            editTextUsername.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Password is Required.");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6){
            editTextPassword.setError("Password should be at least 6 characters.");
            editTextPassword.requestFocus();
            return;
        }

        if(confirmPassword.isEmpty()){
            editTextConfirmPassword.setError("Please confirm password.");
            editTextConfirmPassword.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)){
            editTextConfirmPassword.setError("Password do not match.");
            editTextConfirmPassword.requestFocus();
            return;
        }

        if(website.isEmpty()){
            editTextWebsite.setError("Website Required.");
            editTextWebsite.requestFocus();
            return;
        }

        if(contact.isEmpty()){
            editTextContact.setError("Contact Number Required.");
            editTextContact.requestFocus();
            return;
        }else if (contactNumCount < 11 || contactNumCount > 11) {
            editTextContact.setError("Contact number must be 11 digits.");
            editTextContact.requestFocus();
            return;
        }


        if(street.isEmpty()){
            editTextStreet.setError("Street Address Required.");
            editTextStreet.requestFocus();
            return;
        }

        if(city.isEmpty()){
            editTextCity.setError("City Required.");
            editTextCity.requestFocus();
            return;
        }


        if(country.isEmpty()){
            editTextCountry.setError("Country Required.");
            editTextCountry.requestFocus();
            return;
        }

        mAuth = FirebaseAuth.getInstance();
        databaseReference.child("Shelters").child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Toast.makeText(ShelterRegistration.this, "Username already exists. Please pick a new username.", Toast.LENGTH_LONG).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(task -> {

                                if(task.isSuccessful()){
                                    mAuth.getCurrentUser().sendEmailVerification()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                       @Override
                                                                       public void onComplete(@NonNull Task<Void> task) {

                                                                           if(task.isSuccessful()){
                                                                               Shelter shelter = new Shelter(bizName, owner, email, username,
                                                                                       website, contact, street, city, shelterProvince, country);

                                                                               databaseReference.child("Shelters").child(username).setValue(shelter);

                                                                               User user = new User(email, username, "shelter", false);

                                                                               databaseReference.child("Users").child(username).setValue(user);

                                                                               startActivity(new Intent(ShelterRegistration.this, UserVerifyEmailDialog.class));
                                                                           }
                                                                       }
                                                                   });

                                }else {
                                    Toast.makeText(ShelterRegistration.this, "Failed to register. Try Again!", Toast.LENGTH_LONG).show();
                                }
                            });
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}