package com.example.petnership_kairos;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

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

import org.apache.commons.text.WordUtils;

import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

public class AddCat extends Fragment {

    private EditText etPetName, etPetAge, etPetDescription, etPetBirthday;
    private Button proceedBtn, uploadBtn, backBtn;
    private AppCompatRadioButton rbYesBirthday, rbNoBirthday;
    String datePicked;

    private ImageButton backBtn2;
    protected static String petName, petAge, petAgeNum, petAgeDD, petSex, petStatus, petDesc, petID, petImage;

    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;
    // view for image view
    private ImageView ivPetInfo;

    // Uri indicates, where the image will be picked from
    private Uri filePath;
    private String imageName;

    // request code
    private final int PICK_IMAGE_REQUEST = 22;

    // instance for firebase storage and StorageReference
    FirebaseStorage storage;
    StorageReference storageReference;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    private boolean imageUploaded=false;

    //DROPDOWN AGE
    Spinner ddAge;
    String[] ddAgeValues = {" Week(s)", " Month(s)", " Year(s)"};

    //DROPDOWN SEX
    Spinner ddSex;
    String[] ddSexValues = {"Female", "Male"};

    //DROPDOWN STATUS
    Spinner ddStatus;
    String[] ddStatusValues = {"Available", "Not Available"};

    public AddCat() {
        // Required empty public constructor
    }

    public static AddCat newInstance() { return new AddCat(); }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_pet, container, false);
        backBtn = view.findViewById(R.id.petinfo_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //FRAGMENT to FRAGMENT
                showDialog();
            }

            private void showDialog() {
                BackDialog backDialog = new BackDialog();
                backDialog.show(getParentFragmentManager(), "Back Dialog");

            }
        });

        backBtn2 = view.findViewById(R.id.btnBack);
        backBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackDialog backDialog = new BackDialog();
                backDialog.show(getParentFragmentManager(), "Back Dialog");
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Cat Information");

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();

        //NAME
        etPetName = view.findViewById(R.id.per_pet_name_title);

        //AGE
//        etPetAge = view.findViewById(R.id.pet_age_et);
//        ddAge = view.findViewById(R.id.pet_age_dd);
//        ArrayAdapter<String> ageAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, ddAgeValues);
//        ddAge.setAdapter(ageAdapter);
        //BIRTHDAY
        rbYesBirthday = view.findViewById(R.id.shelter_yesBirthday);
        rbNoBirthday = view.findViewById(R.id.shelter_noBirthday);
//        ddAge = view.findViewById(R.id.pet_age_dd);
//        ArrayAdapter<String> ageAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, ddAgeValues);
//        ddAge.setAdapter(ageAdapter);

        etPetBirthday = view.findViewById(R.id.txt_birthday_pet_edit);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        etPetBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog
                        (getActivity(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                month = month + 1;
                                datePicked = month + "/" + day + "/" + year;
                                etPetBirthday.setText(datePicked);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        //SEX
        ddSex = view.findViewById(R.id.pet_sex_dd);
        ArrayAdapter<String> sexAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, ddSexValues);
        ddSex.setAdapter(sexAdapter);

        //STATUS
        ddStatus = view.findViewById(R.id.shelter_adoption_application_status);
        ArrayAdapter<String> statusAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, ddStatusValues);
        ddStatus.setAdapter(statusAdapter);

        //DESCRIPTION
        etPetDescription = view.findViewById(R.id.pet_desc);

        //Set value for Dropdown Age
//        ddAge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                petAgeDD = adapterView.getItemAtPosition(i).toString();
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {}
//        });

        //Set value for Dropdown Sex
        ddSex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                petSex = adapterView.getItemAtPosition(i).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        //Set value for Dropdown Status
        ddStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                petStatus = adapterView.getItemAtPosition(i).toString();
                System.out.println("petStatus inside: " + petStatus);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        System.out.println("petStatus outside: " + petStatus);


        //UPLOAD IMAGE
        ivPetInfo = view.findViewById(R.id.pet_info_profile_pic_iv);
        ivPetInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImage();
            }
        });

        // get the Firebase  storage reference
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        proceedBtn = view.findViewById(R.id.petinfo_proceed);
        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                petName = WordUtils.capitalize(etPetName.getText().toString().trim());
                petAgeNum = etPetBirthday.getText().toString().trim();
//                petAge = petAgeNum + petAgeDD;
                petDesc = etPetDescription.getText().toString().trim();

                petID = databaseReference.child("Pets").push().getKey();
                System.out.println("PET ID == " + petID);

                if(petName.isEmpty()){
                    etPetName.setError("Pet Name is Required.");
                    etPetName.requestFocus();
                    return;
                }else if(rbYesBirthday.isActivated() && petAgeNum.isEmpty()) {
                    System.out.println("BDAY? == " + petID);
                        etPetBirthday.setError("Pet Birthday is required.");
                        etPetBirthday.requestFocus();
                        return;
                }else if(petDesc.isEmpty()){
                    etPetDescription.setError("Pet Description Required.");
                    etPetDescription.requestFocus();
                    return;
                }else{
                    if(filePath != null){
                        etPetBirthday.setError(null);
                        etPetBirthday.clearFocus();
                        uploadImage();
                        addPet();
                    }else{
                        etPetBirthday.setError(null);
                        etPetBirthday.clearFocus();
                        addPet();
                    }

                    Toast.makeText(getActivity(), "Proceed to Questionnaire", Toast.LENGTH_LONG).show();
                }
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
    public void onActivityResult(int requestCode,
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
                && resultCode == getActivity().RESULT_OK
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
                                getActivity().getContentResolver(),
                                filePath);
                ivPetInfo.setImageBitmap(bitmap);
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
        petImage = UUID.randomUUID().toString();
        if (filePath != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog
                    = new ProgressDialog(getActivity());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            StorageReference ref
                    = storageReference
                    .child(
                            "Pets/"
                                    + petImage);

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
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast
                                    .makeText(getActivity(),
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

    private void addPet(){
            databaseReference.child("Pets").child(petID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        Toast.makeText(getActivity(), "Pet already exists. Please pick a new username.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getActivity(), "Proceed now to questionnaire!", Toast.LENGTH_LONG).show();

                        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                        ShelterCatQuestionnaire ShelterCatQuestionnaire = new ShelterCatQuestionnaire();
                        transaction.replace(R.id.nav_host_fragment, ShelterCatQuestionnaire);
                        transaction.addToBackStack("addCat");
                        transaction.commit();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

