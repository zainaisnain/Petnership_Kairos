package com.example.petnership_kairos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
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


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

public class AdopterRegistration extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextFname, editTextLname, editTextEmail, editTextUsername,
            editTextPassword, editTextConfirmPassword, editTextContact, editTextStreet,
            editTextCity, editTextCountry, editTextGender, editTextBirthday;

    DatePickerDialog.OnDateSetListener setListener;
    String datePicked;

    private Button submit, uploadBtn;
    private FirebaseAuth mAuth;
    private ImageButton backBtn;

    // view for image view
    private ImageView imageView;

    // Uri indicates, where the image will be picked from
    private Uri filePath;
    private String imageName;

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


    String json_string;
    JSONObject jsonObj;
    JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopter_registration);

        mAuth = FirebaseAuth.getInstance();

        editTextFname = findViewById(R.id.txt_fname_adopter);
        editTextLname = findViewById(R.id.txt_lname_adopter);
        editTextEmail = findViewById(R.id.txt_email_adopter);
        editTextUsername = findViewById(R.id.txt_username_adopter);
        editTextPassword = findViewById(R.id.txt_password_adopter);
        editTextConfirmPassword = findViewById(R.id.txt_confirmpassword_adopter);
        editTextContact = findViewById(R.id.txt_contact_adopter);
        editTextStreet = findViewById(R.id.txt_street_adopter);
        editTextCity = findViewById(R.id.txt_city_adopter);

        //PROVINCES
        json_string= loadJSONFromAsset();
        ddProvince = findViewById(R.id.adopter_province_dd);
        ArrayList<String> provinces = new ArrayList<String>();
        {

            try {
                jsonObj =new JSONObject(json_string);
                jsonArray =jsonObj.getJSONArray("provinces");
                String province;
                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject jObj = jsonArray.getJSONObject(i);
                    province= jObj.getString("name");
                    provinces.add(province);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, provinces);

        Spinner ddProvince = (Spinner)findViewById(R.id.adopter_province_dd);
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
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        //BIRTHDAY
        editTextBirthday = findViewById(R.id.txt_birthday_adopter);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
//
//        setListener = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
//                month = month + 1;
//                String date = month + "/" + day + "/" + year;
//                editTextBirthday.setText(date);
//            }
//        };

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
                        }, year,month, day);
                datePickerDialog.show();
            }
        });

        submit = findViewById(R.id.btn_submit_adopter);
        submit.setOnClickListener(this);

        //UPLOAD IMAGE
        imageView = findViewById(R.id.profile_pic_iv);
        uploadBtn = findViewById(R.id.upload_image_btn);

        // get the Firebase  storage reference
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        // on pressing btnSelect SelectImage() is called
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SelectImage();
            }
        });

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                uploadImage();
            }
        });


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
                Intent intent = new Intent(AdopterRegistration.this,ActivityUserType.class);
                startActivity(intent);

            }
        });

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

    private void selectUserType()
    {
        Intent intent = new Intent(this,ActivityUserType.class);
        startActivity(intent);
        finish();

    }

    // Select Image method
    private void SelectImage()
    {
        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }

    // Override onActivityResult method
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data)
    {

        super.onActivityResult(requestCode,
                resultCode,
                data);

        // checking request code and result code
        // if request code is PICK_IMAGE_REQUEST and
        // resultCode is RESULT_OK
        // then set image in the image view
        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            filePath = data.getData();
            try {

                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePath);
                imageView.setImageBitmap(bitmap);
            }

            catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }


    // UploadImage method
    private void uploadImage()
    {
        imageName = UUID.randomUUID().toString();

        if (filePath != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            StorageReference ref
                    = storageReference
                    .child(
                            "Adopters/"
                                    + imageName);

            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {

                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    progressDialog.dismiss();
                                    Toast
                                            .makeText(AdopterRegistration.this,
                                                    "Image Uploaded!!",
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast
                                    .makeText(AdopterRegistration.this,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int)progress + "%");
                                }
                            });
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_submit_adopter:
                registerAdopter();
                break;
        }
    }

    private void registerAdopter() {
        String fname = editTextFname.getText().toString().trim();
        String lname = editTextLname.getText().toString().trim();
        String email = editTextEmail.getText().toString();
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();
        String contact = editTextContact.getText().toString().trim();
        String street = editTextStreet.getText().toString().trim();
        String city = editTextCity.getText().toString().trim();
//        String province = editTextProvince.getText().toString().trim();
        String country = "Philippines";
//        String gender = editTextGender.getText().toString().trim();
        String birthday = editTextBirthday.getText().toString().trim();
        String user_type = "adopter";


        if(fname.isEmpty()){
            editTextFname.setError("First Name Required.");
            editTextFname.requestFocus();
            return;
        }

        if(lname.isEmpty()){
            editTextLname.setError("Last Name Required.");
            editTextLname.requestFocus();
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

        if(contact.isEmpty()){
            editTextContact.setError("Contact Number Required.");
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

//        if(province.isEmpty()){
//            editTextProvince.setError("Province Required.");
//            editTextProvince.requestFocus();
//            return;
//        }

        if(country.isEmpty()){
            editTextCountry.setError("Country Required.");
            editTextCountry.requestFocus();
            return;
        }

        databaseReference.child("Adopters").child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Toast.makeText(AdopterRegistration.this, "Username already exists. Please pick a new username.", Toast.LENGTH_LONG).show();
                }else{
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(task -> {
                                if(task.isSuccessful()){
                                    mAuth.getCurrentUser().sendEmailVerification()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if(task.isSuccessful()){
                                                        Adopter adopter = new Adopter(fname, lname, email, username, password,
                                                                contact, street, city, adopterProvince, country, sex, birthday, imageName);

                                                        databaseReference.child("Adopters").child(username).setValue(adopter);

                                                        User user = new User(email, password, username, "adopter");

                                                        databaseReference.child("Users").child(username).setValue(user);

                                                        startActivity(new Intent(AdopterRegistration.this, UserVerifyEmailDialog.class));
                                                    }

                                                }
                                            });
                                }else {
                                    System.out.println(task.getException().getMessage());
                                    Toast.makeText(AdopterRegistration.this, "Failed to register. Try Again!", Toast.LENGTH_LONG).show();
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