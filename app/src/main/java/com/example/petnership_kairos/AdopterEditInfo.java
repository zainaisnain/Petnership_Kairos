package com.example.petnership_kairos;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

import java.io.IOException;
import java.util.UUID;

public class AdopterEditInfo extends AppCompatActivity {

    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;
    // creating a variable for
    // our Firebase Database.
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();;

    // creating a variable for our
    // Database Reference for Firebase.
    DatabaseReference usersDBRef = FirebaseDatabase.getInstance().getReference("Users");
    DatabaseReference adoptersDBRef = FirebaseDatabase.getInstance().getReference("Adopters");

    private EditText etFname, etLname, etUsername, etContact,
            etStreet, etCity, etProvince, etCountry, etGender,  etBirthday;

    private String contact,
            street, city, province, country, gender, birthday;

    private Button submitAdopterEditBtn, uploadEditBtn, cancelBtn;
    private ImageButton backBtn;
    private ImageView imageView;

    // Uri indicates, where the image will be picked from
    private Uri filePath;
    private String imageName;

    // request code
    private final int PICK_IMAGE_REQUEST = 22;

    private boolean imageUploaded=true;

    // instance for firebase storage and StorageReference
    FirebaseStorage storage;
    StorageReference storageReference;

    private String adopterEmail, adopterUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopter_edit_info);


        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        adopterEmail = firebaseUser.getEmail();

        // initializing our object class variables.
        etFname = findViewById(R.id.txt_fname_adopter_edit);
        etLname = findViewById(R.id.txt_lname_adopter_edit);
        etUsername = findViewById(R.id.txt_username_adopter_edit);
        etContact = findViewById(R.id.txt_contact_adopter_edit);
        etStreet = findViewById(R.id.txt_street_adopter_edit);
        etCity = findViewById(R.id.txt_city_adopter_edit);
        etProvince = findViewById(R.id.txt_province_adopter_edit);
        etCountry = findViewById(R.id.txt_country_adopter_edit);
        etGender = findViewById(R.id.txt_gender_adopter_edit);
        etBirthday = findViewById(R.id.txt_birthday_adopter_edit);

        //UPLOAD IMAGE
        imageView = findViewById(R.id.iv_image_adopter_edit);

        // get the Firebase  storage reference
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        backBtn = (ImageButton) findViewById(R.id.btnBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.adopterEdit, new AdopterHomeDashboard()).commit();
            }
        });

        cancelBtn = (Button) findViewById(R.id.cancelBTN);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showcancelDialog();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.adopterEdit, new AdopterHomeDashboard()).commit();
            }
        });

        // on pressing btnSelect SelectImage() is called
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                imageUploaded = false;
                SelectImage();
            }
        });

        // calling method
        // for getting data.
        submitAdopterEditBtn = findViewById(R.id.btn_submit_adopter_edit);
        submitAdopterEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contact=etContact.getText().toString().trim();
                street=etStreet.getText().toString().trim();
                city=etCity.getText().toString().trim();
                province=etProvince.getText().toString().trim();
                country=etCountry.getText().toString().trim();
                gender=etGender.getText().toString().trim();
                birthday=etBirthday.getText().toString().trim();

                if(contact.isEmpty()){
                    etContact.setError("Contact is Required.");
                    etContact.requestFocus();
                    return;
                }else if(street.isEmpty()) {
                    etStreet.setError("Street is Required.");
                    etStreet.requestFocus();
                    return;
                }else if(city.isEmpty()){
                    etCity.setError("City is Required.");
                    etCity.requestFocus();
                    return;
                }else if(province.isEmpty()){
                    etProvince.setError("Province is Required.");
                    etProvince.requestFocus();
                    return;
                }else if(country.isEmpty()){
                    etCountry.setError("Country is Required.");
                    etCountry.requestFocus();
                    return;
                }else if(gender.isEmpty()){
                    etGender.setError("Gender is Required.");
                    etGender.requestFocus();
                    return;
                }else if(birthday.isEmpty()){
                    etBirthday.setError("Birthday is Required.");
                    etBirthday.requestFocus();
                    return;
                }else if(!imageUploaded){
                    Toast.makeText(AdopterEditInfo.this, "Please upload picture", Toast.LENGTH_LONG).show();
                }else{
                    uploadImage();
                    editAdopterInfo();
                    showsaveDialog();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.adopterEdit, new AdopterHomeDashboard()).commit();
                }
            }

        });

        getData();
    }

    private void showcancelDialog() {
        final Dialog cancelDialog  = new Dialog(this);
        cancelDialog.setContentView(R.layout.activity_cancel_dialog);
        cancelDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cancelDialog.show();
        Button okBTN = (Button) cancelDialog.findViewById(R.id.buttonOk);
        okBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                cancelDialog.dismiss();

            }
        });
    }

    private void showsaveDialog() {
        final Dialog saveDialog  = new Dialog(this);
        saveDialog.setContentView(R.layout.activity_save_dialog);
        saveDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        saveDialog.show();
        Button okBTN = (Button) saveDialog.findViewById(R.id.buttonOk);
        okBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                saveDialog.dismiss();

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
                                    imageUploaded=true;
                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    progressDialog.dismiss();
                                    Toast
                                            .makeText(AdopterEditInfo.this,
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
                                    .makeText(AdopterEditInfo.this,
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


    private void editAdopterInfo() {
        usersDBRef.orderByChild("email").equalTo(adopterEmail)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        adoptersDBRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.hasChild(adopterUsername)){
                                    String contact = etContact.getText().toString();
                                    snapshot.child(adopterUsername).getRef().child("contact").setValue(contact);

                                    String city = etCity.getText().toString();
                                    snapshot.child(adopterUsername).getRef().child("city").setValue(city);

                                    String province = etProvince.getText().toString();
                                    snapshot.child(adopterUsername).getRef().child("province").setValue(province);

                                    String country = etCountry.getText().toString();
                                    snapshot.child(adopterUsername).getRef().child("country").setValue(country);

                                    String gender = etGender.getText().toString();
                                    snapshot.child(adopterUsername).getRef().child("gender").setValue(gender);

                                    snapshot.child(adopterUsername).getRef().child("imageName").setValue(imageName);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }


    private void getData() {

        //FirebaseDatabase.getInstance().getReference().child("Adopters");
        // calling add value event listener method
        // for getting the values from database.
        usersDBRef.orderByChild("email").equalTo(adopterEmail)
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    adopterUsername = ds.getKey();
                }
                adoptersDBRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String fname = String.valueOf(snapshot.child(adopterUsername).child("fname").getValue());
                        System.out.println(fname);
                        etFname.setText(fname);
                        etFname.setEnabled(false);

                        String lname = String.valueOf(snapshot.child(adopterUsername).child("lname").getValue());
                        System.out.println(lname);
                        etLname.setText(lname);
                        etLname.setEnabled(false);

                        String username = String.valueOf(snapshot.child(adopterUsername).child("username").getValue());
                        etUsername.setText(username);
                        etUsername.setEnabled(false);

                        etContact.setText(String.valueOf(snapshot.child(adopterUsername).child("contact").getValue()));
                        etStreet.setText(String.valueOf(snapshot.child(adopterUsername).child("street").getValue()));
                        etCity.setText(String.valueOf(snapshot.child(adopterUsername).child("city").getValue()));
                        etProvince.setText(String.valueOf(snapshot.child(adopterUsername).child("province").getValue()));
                        etCountry.setText(String.valueOf(snapshot.child(adopterUsername).child("country").getValue()));
                        etGender.setText(String.valueOf(snapshot.child(adopterUsername).child("gender").getValue()));
                        etBirthday.setText(String.valueOf(snapshot.child(adopterUsername).child("birthday").getValue()));


                        imageName = String.valueOf(snapshot.child(adopterUsername).child("imageName").getValue());
                        System.out.println("imageName AdopterEditInfo" + imageName);
                        storageReference.child("Adopters/").child(imageName).getDownloadUrl()
                                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        Glide.with(AdopterEditInfo.this).load(uri.toString()).into(imageView);
                                    }
                                });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}