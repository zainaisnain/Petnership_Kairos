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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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

public class ShelterEditInfo extends AppCompatActivity {

    private EditText editTextBizName, editTextOwner, editTextWebsite, editTextContact,
            editTextStreet, editTextCity, editTextCountry;
    private String bizName, owner, website, contact, street, city, country;

    private String shelterEmail, shelterUsername;
    private Button uploadEditBtn, submitBtn, uploadBtn, cancelBtn;
    private ImageButton backBtn;
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
    DatabaseReference usersDBRef = FirebaseDatabase.getInstance().getReference("Users");
    DatabaseReference sheltersDBRef = FirebaseDatabase.getInstance().getReference("Shelters");

    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;
    private Boolean imageUploaded = false;

    //DROPDOWNS
    Spinner ddProvince;
    private String shelterProvince;


    String json_string;
    JSONObject jsonObj;
    JSONArray jsonArray;
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
        setContentView(R.layout.activity_shelter_edit_info);

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        shelterEmail = firebaseUser.getEmail();


        editTextBizName = findViewById(R.id.txt_biz_name_shelter_edit);
        editTextOwner = findViewById(R.id.txt_biz_owner_shelter_edit);
        editTextWebsite = findViewById(R.id.txt_website_shelter_edit);
        editTextContact = findViewById(R.id.txt_contact_shelter_edit);
        editTextStreet = findViewById(R.id.txt_street_shelter_edit);
        editTextCity = findViewById(R.id.txt_city_shelter_edit);
        editTextCountry = findViewById(R.id.txt_country_shelter_edit);

        //PROVINCES
        ddProvince = findViewById(R.id.dd_province_shelter_edit);
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

        editTextCountry = findViewById(R.id.txt_country_shelter_edit);
        editTextCountry.setEnabled(false);

        // get the Firebase  storage reference
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        //GET EXISTING DATA FROM DATABASE
        getData();

        backBtn = (ImageButton) findViewById(R.id.btnBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyCancelDialogShelterEdit myCancelDialogShelterEdit = new MyCancelDialogShelterEdit();
                myCancelDialogShelterEdit.show(getSupportFragmentManager(), "My Fragment");
            }
        });
        cancelBtn = (Button) findViewById(R.id.btn_cancel_shelter_edit);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyCancelDialogShelterEdit myCancelDialogShelterEdit = new MyCancelDialogShelterEdit();
                myCancelDialogShelterEdit.show(getSupportFragmentManager(), "My Fragment");
            }
        });
        // on pressing btnSelect SelectImage() is called
        imageView = findViewById(R.id.profile_pic_iv_edit);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                imageUploaded = false;
                SelectImage();
            }
        });


        submitBtn = findViewById(R.id.btn_submit_shelter_edit);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bizName = editTextBizName.getText().toString().trim();
                owner = editTextOwner.getText().toString().trim();
                website = editTextWebsite.getText().toString().trim();
                contact = editTextContact.getText().toString().trim();
                street = editTextStreet.getText().toString().trim();
                city = editTextCity.getText().toString().trim();
                country = editTextCountry.getText().toString().trim();

                int contactNumCount = 0;
                for(int i = 0; i < contact.length(); i++){
                    if(contact.charAt(i) != ' ')
                        contactNumCount++;
                }

                if(bizName.isEmpty()){
                    editTextBizName.setError("Business Name is Required.");
                    editTextBizName.requestFocus();
                    return;
                }else if(owner.isEmpty()){
                    editTextOwner.setError("Owner name is Required.");
                    editTextOwner.requestFocus();
                    return;
                }else if(website.isEmpty()){
                    editTextWebsite.setError("Website Required.");
                    editTextWebsite.requestFocus();
                    return;
                }else if(contact.isEmpty()){
                    editTextContact.setError("Contact number is Required.");
                    editTextContact.requestFocus();
                    return;
                }else if (contactNumCount < 11 || contactNumCount > 11) {
                    editTextContact.setError("Contact number must be 11 digits.");
                    editTextContact.requestFocus();
                    return;
                }else if(street.isEmpty()){
                    editTextStreet.setError("Street address Required.");
                    editTextStreet.requestFocus();
                    return;
                }else if(city.isEmpty()){
                    editTextCity.setError("City is Required.");
                    editTextCity.requestFocus();
                    return;
                }else{
                    if(filePath != null){
                        uploadImage();
                        editShelterInfo();
                    }else{
                        editShelterInfo();
                        MySaveDialogShelter mySaveDialogShelter = new MySaveDialogShelter();
                        mySaveDialogShelter.show(getSupportFragmentManager(), "My Fragment");
                    }
                }

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
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                ShelterHomeDashboard shelterHomeDashboard = new ShelterHomeDashboard();
                fragmentTransaction.replace(R.id.sheltereditInfo, shelterHomeDashboard);
                fragmentTransaction.addToBackStack("shelterEditInfo");
                fragmentTransaction.commit();
                saveDialog.dismiss();

            }
        });
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
                    Intent intent = new Intent(ShelterEditInfo.this, ShelterDashboard.class);
                    startActivity(intent);
//                    onBackPressed();
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

    private void editShelterInfo() {
        usersDBRef.orderByChild("email").equalTo(shelterEmail)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        sheltersDBRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.hasChild(shelterUsername)){
                                    snapshot.child(shelterUsername).getRef().child("bizName").setValue(bizName);
                                    snapshot.child(shelterUsername).getRef().child("owner").setValue(owner);
                                    snapshot.child(shelterUsername).getRef().child("website").setValue(website);
                                    snapshot.child(shelterUsername).getRef().child("contact").setValue(contact);
                                    snapshot.child(shelterUsername).getRef().child("street").setValue(street);
                                    snapshot.child(shelterUsername).getRef().child("city").setValue(city);
                                    snapshot.child(shelterUsername).getRef().child("imageName").setValue(imageName);
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
        usersDBRef.orderByChild("email").equalTo(shelterEmail)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds : snapshot.getChildren()) {
                            shelterUsername = ds.getKey();
                        }

                        sheltersDBRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                editTextBizName.setText(String.valueOf(snapshot.child(shelterUsername).child("bizName").getValue()));
                                editTextOwner.setText(String.valueOf(snapshot.child(shelterUsername).child("owner").getValue()));
                                editTextWebsite.setText(String.valueOf(snapshot.child(shelterUsername).child("website").getValue()));
                                editTextContact.setText(String.valueOf(snapshot.child(shelterUsername).child("contact").getValue()));
                                editTextStreet.setText(String.valueOf(snapshot.child(shelterUsername).child("street").getValue()));
                                editTextCity.setText(String.valueOf(snapshot.child(shelterUsername).child("city").getValue()));
                                editTextCountry.setText(String.valueOf(snapshot.child(shelterUsername).child("country").getValue()));

                                imageName = String.valueOf(snapshot.child(shelterUsername).child("imageName").getValue());
                                System.out.println("imageName AdopterEditInfo" + imageName);
                                storageReference.child("Shelters/").child(imageName).getDownloadUrl()
                                        .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {
                                                Glide.with(ShelterEditInfo.this).load(uri.toString()).into(imageView);
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
                                    imageUploaded = true;

                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    progressDialog.dismiss();
                                    MySaveDialogShelter mySaveDialogShelter = new MySaveDialogShelter();
                                    mySaveDialogShelter.show(getSupportFragmentManager(), "My Fragment");
                                    Toast
                                            .makeText(ShelterEditInfo.this,
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
                                    .makeText(ShelterEditInfo.this,
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

}