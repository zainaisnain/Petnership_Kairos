package com.example.petnership_kairos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;
import org.apache.commons.*;
public class AdopterRegistration extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextFname, editTextLname, editTextEmail,
            editTextPassword, editTextConfirmPassword, editTextContact, editTextStreet,
            editTextCity, editTextCountry, editTextGender, editTextBirthday;

    DatePickerDialog.OnDateSetListener setListener;
    String datePicked;

    private Button submit, uploadBtn, cancelBtn;
    private FirebaseAuth mAuth;
    private ImageButton backBtn;

    // Uri indicates, where the image will be picked from
    private Uri filePath;
    private String imageName = "";

    // request code
    private final int PICK_IMAGE_REQUEST = 22;

    // instance for firebase storage and StorageReference
    FirebaseStorage storage;
    StorageReference storageReference;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    //DROPDOWNS
    Spinner ddSex, ddProvince;
    String[] ddSexValues = {"Female", "Male"};

    String sex, adopterProvince;

    String adopterID;
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
        setContentView(R.layout.activity_adopter_registration);

        mAuth = FirebaseAuth.getInstance();

        editTextFname = findViewById(R.id.txt_fname_adopter);
        editTextLname = findViewById(R.id.txt_lname_adopter);
        editTextEmail = findViewById(R.id.txt_email_adopter);
        editTextPassword = findViewById(R.id.txt_password_adopter);
        editTextConfirmPassword = findViewById(R.id.txt_confirmpassword_adopter);
        editTextContact = findViewById(R.id.txt_contact_adopter);
        editTextStreet = findViewById(R.id.txt_street_adopter);
        editTextCity = findViewById(R.id.txt_city_adopter);

        //PROVINCES
        ddProvince = findViewById(R.id.adopter_province_dd);
        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, ddProvincesValues);
        ddProvince.setAdapter(provinceAdapter);
        ddProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                adopterProvince = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        editTextCountry = findViewById(R.id.txt_country_adopter);
        editTextCountry.setEnabled(false);

        ddSex = findViewById(R.id.adopter_sex_dd);
        ArrayAdapter<String> sexAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, ddSexValues);
        ddSex.setAdapter(sexAdapter);

        ddSex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sex = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //BIRTHDAY
        editTextBirthday = findViewById(R.id.txt_birthday_adopter);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        editTextBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog
                        (AdopterRegistration.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                month = month + 1;
                                datePicked = month + "/" + day + "/" + year;
                                editTextBirthday.setText(datePicked);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        submit = findViewById(R.id.btn_submit_adopter);
        submit.setOnClickListener(this);


        // get the Firebase  storage reference
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();


        //return user type selection
        Button userType = findViewById(R.id.adopterRegisterCancel);
        userType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectUserType();
            }
        });

        backBtn = (ImageButton) findViewById(R.id.btnBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showcancelDialog();

            }
        });

        cancelBtn = (Button) findViewById(R.id.adopterRegisterCancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showcancelDialog();
            }
        });

    }

    private void selectUserType() {
        Intent intent = new Intent(this, ActivityUserType.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit_adopter:
                registerAdopter();
                break;
        }
    }

    private void showcancelDialog() {
        final Dialog cancelDialog = new Dialog(this);
        cancelDialog.setContentView(R.layout.activity_cancel_dialog);
        cancelDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cancelDialog.show();
        Button yesBTN = (Button) cancelDialog.findViewById(R.id.buttonYes);
        yesBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(AdopterRegistration.this, ActivityUserType.class));
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

    private void registerAdopter() {
        String fname = WordUtils.capitalizeFully(editTextFname.getText().toString().trim());
        String lname = WordUtils.capitalizeFully(editTextLname.getText().toString().trim());
        String email = editTextEmail.getText().toString().toLowerCase();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();
        String contact = editTextContact.getText().toString().trim();
        String street = WordUtils.capitalizeFully(editTextStreet.getText().toString().trim());
        String city = WordUtils.capitalizeFully(editTextCity.getText().toString().trim());
        String country = "Philippines";
        String birthday = editTextBirthday.getText().toString().trim();
        String user_type = "adopter";

        System.out.println(fname);
        System.out.println(lname);
        System.out.println(street);
        System.out.println(city);

        int contactNumCount = 0;
        for (int i = 0; i < contact.length(); i++) {
            if (contact.charAt(i) != ' ')
                contactNumCount++;
        }


        if (fname.isEmpty()) {
            editTextFname.setError("First Name Required.");
            editTextFname.requestFocus();
            return;
        }

        if (lname.isEmpty()) {
            editTextLname.setError("Last Name Required.");
            editTextLname.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail.setError("Email Required.");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please provide valid email.");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is Required.");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Password should be at least 6 characters.");
            editTextPassword.requestFocus();
            return;
        }

        if (confirmPassword.isEmpty()) {
            editTextConfirmPassword.setError("Please confirm password.");
            editTextConfirmPassword.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)) {
            editTextConfirmPassword.setError("Password do not match.");
            editTextConfirmPassword.requestFocus();
            return;
        }

        if (contact.isEmpty()) {
            editTextContact.setError("Contact Number Required.");
            editTextContact.requestFocus();
            return;
        } else if (contactNumCount < 11 || contactNumCount > 11) {
            editTextContact.setError("Contact number must be 11 digits.");
            editTextContact.requestFocus();
            return;
        }

        if (street.isEmpty()) {
            editTextStreet.setError("Street Address Required.");
            editTextStreet.requestFocus();
            return;
        }

        if (city.isEmpty()) {
            editTextCity.setError("City Required.");
            editTextCity.requestFocus();
            return;
        }

        if (country.isEmpty()) {
            editTextCountry.setError("Country Required.");
            editTextCountry.requestFocus();
            return;
        }

        adopterID = databaseReference.child("Adopters").push().getKey();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        mAuth.getCurrentUser().sendEmailVerification()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            boolean answeredCatQuestionnaire = false;
                                            boolean answeredDogQuestionnaire = false;
                                            Adopter adopter = new Adopter(adopterID, fname, lname, email,
                                                    contact, street, city, adopterProvince, country, sex, birthday, imageName, answeredCatQuestionnaire, answeredDogQuestionnaire);

                                            databaseReference.child("Adopters").child(adopterID).setValue(adopter);


                                            User user = new User(adopterID, email, "adopter", true, true);

                                            databaseReference.child("Users").child(adopterID).setValue(user);

                                            startActivity(new Intent(AdopterRegistration.this, UserVerifyEmailDialog.class));
                                        }

                                    }
                                });
                    } else {
                        System.out.println(task.getException().getMessage());
                        Toast.makeText(AdopterRegistration.this, "Failed to register. Try Again!", Toast.LENGTH_LONG).show();
                    }
                });

    }
    @Override
    public void onBackPressed () {
        showcancelDialog();

    }
}