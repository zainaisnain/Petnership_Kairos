package com.example.petnership_kairos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class ShelterRegistration extends AppCompatActivity implements View.OnClickListener  {

    private EditText editTextBizName, editTextOwner, editTextEmail, editTextUsername,
            editTextPassword, editTextConfirmPassword, editTextWebsite, editTextContact, editTextStreet,
            editTextCity, editTextProvince, editTextCountry, editTextTin;

    private Button submit, uploadBtn;
    private FirebaseAuth mAuth;


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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_registration);

        mAuth = FirebaseAuth.getInstance();

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
        editTextProvince = findViewById(R.id.txt_province_shelter);
        editTextCountry = findViewById(R.id.txt_country_shelter);
        editTextTin = findViewById(R.id.txt_tin_shelter);

        submit = findViewById(R.id.btn_submit_shelter);
        submit.setOnClickListener(this);

        //UPLOAD IMAGE
        imageView = findViewById(R.id.profile_pic_iv);
        uploadBtn = findViewById(R.id.upload_image_shelter_btn);

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
                            "Shelters/"
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
                                            .makeText(ShelterRegistration.this,
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
                                    .makeText(ShelterRegistration.this,
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
            case R.id.btn_submit_shelter:
                registerShelter();
                break;
        }
    }

    private void registerShelter() {
        String bizName = editTextBizName.getText().toString().trim();
        String owner = editTextOwner.getText().toString().trim();
        String email = editTextEmail.getText().toString();
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();
        String website = editTextWebsite.getText().toString().trim();
        String contact = editTextContact.getText().toString().trim();
        String street = editTextStreet.getText().toString().trim();
        String city = editTextCity.getText().toString().trim();
        String province = editTextProvince.getText().toString().trim();
        String country = editTextCountry.getText().toString().trim();
        String tin = editTextTin.getText().toString().trim();


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

        if(province.isEmpty()){
            editTextProvince.setError("Province Required.");
            editTextProvince.requestFocus();
            return;
        }

        if(country.isEmpty()){
            editTextCountry.setError("Country Required.");
            editTextCountry.requestFocus();
            return;
        }

        //TODO: add alert dialog after destination screen is made
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {

                    if(task.isSuccessful()){
                        Shelter shelter = new Shelter(bizName, owner, email, username, password,
                                website, contact, street, city, province, country, tin, imageName);

                        FirebaseDatabase.getInstance().getReference("Shelters")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(shelter).addOnCompleteListener(task1 -> {

                                    if(task1.isSuccessful()){
                                        Toast.makeText(ShelterRegistration.this, "Animal Shelter registered successfully!", Toast.LENGTH_LONG).show();
                                    }else {
                                        Toast.makeText(ShelterRegistration.this, "Failed to register Animal Shelter!", Toast.LENGTH_LONG).show();
                                    }

                                });

                        Toast.makeText(ShelterRegistration.this, "Animal Shelter registered successfully!", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(ShelterRegistration.this, "Failed to register. Try Again!", Toast.LENGTH_LONG).show();
                    }
                });

    }

}