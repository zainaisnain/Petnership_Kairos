package com.example.petnership_kairos;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import java.util.Calendar;
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

    private String adopterEmail, adopterID;

    //DROPDOWNS
    Spinner ddSex, ddProvince;
    String[] ddSexValues = {"Female", "Male"};

    String sex, adopterProvince, datePicked;

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
        setContentView(R.layout.activity_adopter_edit_info);


        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        adopterEmail = firebaseUser.getEmail();

        // initializing our object class variables.
        etFname = findViewById(R.id.txt_fname_adopter_edit);
        etLname = findViewById(R.id.txt_lname_adopter_edit);
        etContact = findViewById(R.id.txt_contact_adopter_edit);
        etStreet = findViewById(R.id.txt_street_adopter_edit);
        etCity = findViewById(R.id.txt_city_adopter_edit);
        //PROVINCES
        ddProvince = findViewById(R.id.adopter_province_dd_edit);
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

        etCountry = findViewById(R.id.txt_country_adopter_edit);
        etCountry.setEnabled(false);

        ddSex = findViewById(R.id.adopter_sex_dd_edit);
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

        etBirthday = findViewById(R.id.txt_birthday_adopter_edit);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        etBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog
                        (AdopterEditInfo.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                month = month + 1;
                                datePicked = month + "/" + day + "/" + year;
                                etBirthday.setText(datePicked);
                            }
                        }, year,month, day);
                datePickerDialog.show();
            }
        });

        //UPLOAD IMAGE
        imageView = findViewById(R.id.iv_image_adopter_edit);

        // get the Firebase  storage reference
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        backBtn = (ImageButton) findViewById(R.id.btnBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyCancelDialogAdopterEdit myCancelDialogAdopterEdit = new MyCancelDialogAdopterEdit();
                myCancelDialogAdopterEdit.show(getSupportFragmentManager(), "My Fragment");
            }
        });

        cancelBtn = (Button) findViewById(R.id.cancelBTN);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyCancelDialogAdopterEdit myCancelDialogAdopterEdit = new MyCancelDialogAdopterEdit();
                myCancelDialogAdopterEdit.show(getSupportFragmentManager(), "My Fragment");
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
                country=etCountry.getText().toString().trim();

                int contactNumCount = 0;
                for(int i = 0; i < contact.length(); i++){
                    if(contact.charAt(i) != ' ')
                        contactNumCount++;
                }

                if(contact.isEmpty()){
                    etContact.setError("Contact is Required.");
                    etContact.requestFocus();
                    return;
                }else if (contactNumCount < 11 || contactNumCount > 11) {
                    etContact.setError("Contact number must be 11 digits.");
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
                }else{
                    if(filePath != null){
                        uploadImage();
                        editAdopterInfo();
                    }else{
                        editAdopterInfo();
                    }
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
        Button yesBTN = (Button) cancelDialog.findViewById(R.id.buttonYes);
        yesBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                onBackPressed();
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
                AdopterHomeDashboard adopterHomeDashboard = new AdopterHomeDashboard();
                fragmentTransaction.replace(R.id.adopterEdit, adopterHomeDashboard).commit();
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
                                    MySaveDialogAdopter mySaveDialogAdopter = new MySaveDialogAdopter();
                                    mySaveDialogAdopter.show(getSupportFragmentManager(), "My Fragment");
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
                                if(snapshot.hasChild(adopterID)){
                                    String contact = etContact.getText().toString();
                                    snapshot.child(adopterID).getRef().child("contact").setValue(contact);

                                    String city = etCity.getText().toString();
                                    snapshot.child(adopterID).getRef().child("city").setValue(city);

                                    String country = etCountry.getText().toString();
                                    snapshot.child(adopterID).getRef().child("country").setValue(country);

                                    snapshot.child(adopterID).getRef().child("imageName").setValue(imageName);
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
                    adopterID = ds.getKey();
                }
                adoptersDBRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String fname = String.valueOf(snapshot.child(adopterID).child("fname").getValue());
                        System.out.println(fname);
                        etFname.setText(fname);
                        etFname.setEnabled(false);

                        String lname = String.valueOf(snapshot.child(adopterID).child("lname").getValue());
                        System.out.println(lname);
                        etLname.setText(lname);
                        etLname.setEnabled(false);

                        etContact.setText(String.valueOf(snapshot.child(adopterID).child("contact").getValue()));
                        etStreet.setText(String.valueOf(snapshot.child(adopterID).child("street").getValue()));
                        System.out.println("city === "+ snapshot.child(adopterID).child("city").getValue());
                        etCity.setText(String.valueOf(snapshot.child(adopterID).child("city").getValue()));
                        etCountry.setText(String.valueOf(snapshot.child(adopterID).child("country").getValue()));
                        etBirthday.setText(String.valueOf(snapshot.child(adopterID).child("birthday").getValue()));

                        adoptersDBRef.child(adopterID).orderByKey().equalTo("imageName").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists()){
                                    imageName = String.valueOf(snapshot.child(adopterID).child("imageName").getValue());
                                    System.out.println("imageName AdopterEditInfo" + imageName);
                                    if(imageName != null){
                                        if(!imageName.isEmpty()){
                                            if(imageName != ""){
                                                //DISPLAY IMAGE TO IMAGE VIEW
                                                storageReference.child("Adopters/").child(imageName).getDownloadUrl()
                                                        .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                            @Override
                                                            public void onSuccess(Uri uri) {
                                                                Glide.with(AdopterEditInfo.this).load(uri.toString()).into(imageView);
                                                            }
                                                        });
                                            }
                                        }
                                    }
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

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}