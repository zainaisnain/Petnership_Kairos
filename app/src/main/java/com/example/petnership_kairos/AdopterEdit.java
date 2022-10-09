package com.example.petnership_kairos;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class AdopterEdit extends AppCompatActivity implements View.OnClickListener {

    // creating a variable for
    // our Firebase Database.
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();;

    // creating a variable for our
    // Database Reference for Firebase.
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    // variable for Text view.
    private TextView tvFname, tvLname, tvUsername, tvBirthday;

    private EditText etEmail, etPassword, etConfirmPassword, etContact,
            etSteet, etCity, etProvince, etCountry, etGender;

    private Button submitAdopterEditBtn, uploadEditBtn;

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
        setContentView(R.layout.activity_adopter_edit);


        // initializing our object class variables.
        tvFname = findViewById(R.id.txt_fname_adopter_edit);
        tvLname = findViewById(R.id.txt_lname_adopter_edit);
        etEmail = findViewById(R.id.txt_email_adopter_edit);
        tvUsername = findViewById(R.id.txt_username_adopter_edit);
        etPassword = findViewById(R.id.txt_password_adopter_edit);
        etConfirmPassword = findViewById(R.id.txt_confirmpassword_adopter_edit);
        etContact = findViewById(R.id.txt_contact_adopter_edit);
        etSteet = findViewById(R.id.txt_street_adopter_edit);
        etCity = findViewById(R.id.txt_city_adopter_edit);
        etProvince = findViewById(R.id.txt_province_adopter_edit);
        etCountry = findViewById(R.id.txt_country_adopter_edit);
        etGender = findViewById(R.id.txt_gender_adopter_edit);

        // calling method
        // for getting data.
        submitAdopterEditBtn = findViewById(R.id.btn_submit_adopter_edit);
        submitAdopterEditBtn.setOnClickListener(this);

        //UPLOAD IMAGE
        imageView = findViewById(R.id.iv_image_adopter_edit);
        uploadEditBtn = findViewById(R.id.upload_image_btn_edit);

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

        uploadEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                uploadImage();
            }
        });

        getdata();
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
                                            .makeText(AdopterEdit.this,
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
                                    .makeText(AdopterEdit.this,
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
            case R.id.btn_submit_adopter_edit:
                editAdopterInfo();
                break;
        }
    }


    private void editAdopterInfo() {

        String email = etEmail.getText().toString();
        databaseReference.child("Adopters").child("zainaisnain").child("email").setValue(email);

        String password = etPassword.getText().toString();
        databaseReference.child("Adopters").child("zainaisnain").child("password").setValue(password);

        String contact = etContact.getText().toString();
        databaseReference.child("Adopters").child("zainaisnain").child("contact").setValue(contact);

        String city = etCity.getText().toString();
        databaseReference.child("Adopters").child("zainaisnain").child("city").setValue(city);

        String province = etProvince.getText().toString();
        databaseReference.child("Adopters").child("zainaisnain").child("province").setValue(province);

        String country = etCountry.getText().toString();
        databaseReference.child("Adopters").child("zainaisnain").child("country").setValue(country);

        String gender = etGender.getText().toString();
        databaseReference.child("Adopters").child("zainaisnain").child("gender").setValue(gender);

        databaseReference.child("Adopters").child("zainaisnain").child("imageName").setValue(imageName);
    }


    private void getdata() {

        //FirebaseDatabase.getInstance().getReference().child("Adopters");
        // calling add value event listener method
        // for getting the values from database.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String fname = String.valueOf(snapshot.child("Adopters").child("zainaisnain").child("fname").getValue());
                System.out.println(fname);
                tvFname.setText(fname);

                String lname = String.valueOf(snapshot.child("Adopters").child("zainaisnain").child("lname").getValue());
                System.out.println(lname);
                tvLname.setText(lname);

                String username = String.valueOf(snapshot.child("Adopters").child("zainaisnain").child("username").getValue());
                tvUsername.setText(username);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                // this method is call to get the realtime
//                // updates in the data.
//                // this method is called when the data is
//                // changed in our Firebase console.
//                // below line is for getting the data from
//                // snapshot of our database.
//                String value = snapshot.getValue(String.class);
//
//                // after getting the value we are setting
//                // our value to our text view in below line.
//                retrieveTV.setText(value);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                // calling on cancelled method when we receive
//                // any error or we are not able to get the data.
//                Toast.makeText(AdopterEdit.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}