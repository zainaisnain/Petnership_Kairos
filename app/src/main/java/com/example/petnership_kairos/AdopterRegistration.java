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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class AdopterRegistration extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextFname, editTextLname, editTextEmail, editTextUsername,
            editTextPassword, editTextConfirmPassword, editTextContact, editTextStreet,
            editTextCity, editTextProvince, editTextCountry, editTextGender, editTextBirthday;

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
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();




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
        editTextProvince = findViewById(R.id.txt_province_adopter);
        editTextCountry = findViewById(R.id.txt_country_adopter);
        editTextGender = findViewById(R.id.txt_gender_adopter);
        editTextBirthday = findViewById(R.id.txt_birthday_adopter);

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
        String province = editTextProvince.getText().toString().trim();
        String country = editTextCountry.getText().toString().trim();
        String gender = editTextGender.getText().toString().trim();
        String birthday = editTextBirthday.getText().toString().trim();

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

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {

                    if(task.isSuccessful()){
                        Adopter adopter = new Adopter(fname, lname, email, username, password,
                                contact, street, city, province, country, gender, birthday, imageName);

                        databaseReference.child("Adopters").child(username).setValue(adopter);

                        Toast.makeText(AdopterRegistration.this, "Adopter registered successfully!", Toast.LENGTH_LONG).show();
                    }else {
                        System.out.println(task.getException().getMessage());
//                        System.out.println(task.getException().getErrorCode());

                        Toast.makeText(AdopterRegistration.this, "Failed to register. Try Again!", Toast.LENGTH_LONG).show();
                    }
                });
    }
}