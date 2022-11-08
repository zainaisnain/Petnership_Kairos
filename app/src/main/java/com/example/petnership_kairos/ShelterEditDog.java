package com.example.petnership_kairos;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
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

import org.apache.commons.text.WordUtils;

import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

public class ShelterEditDog extends Fragment {

    private EditText etPetName, etPetAge, etPetSex, etPetDescription, etPetBirthday;

    private Button proceedBtn, uploadBtn, back;
    private AppCompatRadioButton rbYesBirthday, rbNoBirthday;
    private ImageButton backBtn;
    String datePicked;

    protected static String petName, petAgeNum, petSex, petStatus, petDesc, petID, petImage;
    protected static boolean petExact;

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
    DatabaseReference petsDogsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("Dogs");

    private boolean imageUploaded = true;

    //DROPDOWN AGE
    Spinner ddAge;
    String[] ddAgeValues = {" Week(s)", " Month(s)", " Year(s)"};
    ArrayAdapter<String> ageAdapter;

    //DROPDOWN SEX
    Spinner ddSex;
    String[] ddSexValues = {"Female", "Male"};
    ArrayAdapter<String> sexAdapter;

    //DROPDOWN STATUS
    Spinner ddStatus;
    String[] ddStatusValues = {"Available", "Not Available"};
    ArrayAdapter<String> statusAdapter;

    public ShelterEditDog() {}

    public static ShelterEditDog newInstance() {return new ShelterEditDog();}

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_pet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Edit Dog Information");

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();

        ShelterPerDogProfile pdp = new ShelterPerDogProfile();
        petID = pdp.petID;

        //NAME
        etPetName = view.findViewById(R.id.per_pet_name_title);

        //AGE
//        etPetAge = view.findViewById(R.id.pet_age_et);
//        ddAge = view.findViewById(R.id.pet_age_dd);
//        ageAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, ddAgeValues);
//        ddAge.setAdapter(ageAdapter);

        //SEX
        ddSex = view.findViewById(R.id.pet_sex_dd);
        sexAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, ddSexValues);
        ddSex.setAdapter(sexAdapter);

        rbYesBirthday = view.findViewById(R.id.shelter_yesBirthday);
        rbNoBirthday = view.findViewById(R.id.shelter_noBirthday);

        rbYesBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbNoBirthday.setBackgroundColor(Color.GRAY);
                rbYesBirthday.setBackgroundColor(R.drawable.round_lightpurple);
            }
        });

        rbNoBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbYesBirthday.setBackgroundColor(Color.GRAY);
                rbNoBirthday.setBackgroundColor(R.drawable.round_lightpurple);
            }
        });
        //STATUS
        ddStatus = view.findViewById(R.id.shelter_adoption_application_status);
        statusAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, ddStatusValues);
        ddStatus.setAdapter(statusAdapter);

        //DESCRIPTION
        etPetDescription = view.findViewById(R.id.pet_desc);

        //Set value for Dropdown Age
        //TODO: CHANGE TO BIRHTDAY
//        ddAge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                petAgeDD = adapterView.getItemAtPosition(i).toString();
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {}
//        });
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
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        back = view.findViewById(R.id.petinfo_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
////                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
//                MyCancelDialog2 cancelDialog2 = new MyCancelDialog2();
//                transaction.replace(R.id.add_pet_frag, cancelDialog2);
//                transaction.addToBackStack("currentCatProfile");
//                transaction.commit();
//                MyCancelDialog2 cancelDialog2 = new MyCancelDialog2();
//                cancelDialog2.show(getParentFragmentManager(), "My Fragment");
//                transaction.replace(R.id.add_pet_frag, cancelDialog2);
//                transaction.addToBackStack("currentCatProfile");
//                transaction.commit();

//                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
//                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
//                getParentFragmentManager().beginTransaction().addToBackStack("currentCatProfile").commit();
                MyCancelDialogEdit myCancelDialogEdit = new MyCancelDialogEdit();
                myCancelDialogEdit.show(getParentFragmentManager(), "My Fragment");
//                transaction.replace(R.id.add_pet_frag,cancelDialog2);

//                transaction.commit();
//                MyCancelDialog2 cancelDialog2 = new MyCancelDialog2();
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.add_pet_frag, cancelDialog2).commit();
            }

        });

//        backBtn = view.findViewById(R.id.petinfo_back);
//        backBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
//                ShelterListOfDogsFragment shelterRegisteredDogs = new ShelterListOfDogsFragment();
//                transaction.replace(R.id.nav_host_fragment,shelterRegisteredDogs);
//                transaction.commit();
//            }
//        });

        backBtn = view.findViewById(R.id.btnBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyCancelDialogEdit cancelDialog2 = new MyCancelDialogEdit();
                cancelDialog2.show(getParentFragmentManager(), "My Fragment");
            }
        });


        //UPLOAD IMAGE
        ivPetInfo = view.findViewById(R.id.pet_info_profile_pic_iv);
        ivPetInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageUploaded=false;
                SelectImage();
            }
        });

        // get the Firebase  storage reference
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        getData();

        proceedBtn = view.findViewById(R.id.petinfo_proceed);
        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                petName = WordUtils.capitalize(etPetName.getText().toString().trim());
                petAgeNum = etPetBirthday.getText().toString().trim();
//                petAgeNum = epetAgeNumtPetAge.getText().toString().trim();
//                petAge =  + petAgeDD;
                petDesc = etPetDescription.getText().toString().trim();


                if (rbYesBirthday.isChecked()) {
                    petExact = true;
                }
                else {
                    petExact = false;

                }


                if(petName.isEmpty()){
                    etPetName.setError("Pet Name is Required.");
                    etPetName.requestFocus();
                    return;
                }else if(petAgeNum.isEmpty()){
                    etPetBirthday.setError("Pet Birthday is required. You may estimate if the exact date is unknown.");
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
                        editPetInfo();
                    }else{
                        etPetBirthday.setError(null);
                        etPetBirthday.clearFocus();
                        editPetInfo();
                    }
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
//        imageName = UUID.randomUUID().toString();
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
                                    imageUploaded = true;
                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    progressDialog.dismiss();
                                    //MySaveDialogShelter mySaveDialogShelter = new MySaveDialogShelter();
                                    //mySaveDialogShelter.show(getParentFragmentManager(), "My Fragment");
                                    Toast
                                            .makeText(getActivity(),
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

    private void getData() {
        System.out.println("petID getData()" + petID);
        petsDogsDBRef.child(petID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                petName = String.valueOf(snapshot.child("petName").getValue());
                System.out.println("petName (ShelterEditDog getData)" + petName);
                etPetName.setText(petName);

                petAgeNum = String.valueOf(snapshot.child("petAgeNum").getValue());
                etPetBirthday.setText(petAgeNum);

                petExact = (boolean) snapshot.child("petExact").getValue();
                if (petExact) {
                    rbYesBirthday.setChecked(true);
                    rbNoBirthday.setChecked(false);
                    rbNoBirthday.setBackgroundColor(Color.GRAY);
                }
                else {
                    rbYesBirthday.setChecked(false);
                    rbNoBirthday.setChecked(true);
                    rbYesBirthday.setBackgroundColor(Color.GRAY);

                }

//                petAgeNum = (String) snapshot.child("petAgeNum").getValue();
//                etPetAge.setText(petAgeNum);

//                petAgeDD = (String) snapshot.child("petAgeDD").getValue();
//                int agePosition = ageAdapter.getPosition(petAgeDD);
//                ddAge.setSelection(agePosition);

                petSex = (String) snapshot.child("petSex").getValue();
                int sexPosition = sexAdapter.getPosition(petSex);
                ddSex.setSelection(sexPosition);

                petStatus = (String) snapshot.child("petStatus").getValue();
                int statusPosition = statusAdapter.getPosition(petStatus);
                ddStatus.setSelection(statusPosition);

                petDesc = String.valueOf(snapshot.child("petDesc").getValue());
                etPetDescription.setText(petDesc);

                petsDogsDBRef.child(petID).orderByKey().equalTo("imageName").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            petImage = String.valueOf(snapshot.child("imageName").getValue());
                            if(petImage != null){
                                if(!petImage.isEmpty()){
                                    if(petImage != ""){
                                        //DISPLAY IMAGE TO IMAGE VIEW
                                        storageReference.child("Pets/").child(petImage).getDownloadUrl()
                                                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                    @Override
                                                    public void onSuccess(Uri uri) {
                                                        Glide.with(getActivity()).load(uri.toString()).into(ivPetInfo);
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

    private void editPetInfo(){
        Toast.makeText(getActivity(), "Proceed now to questionnaire!", Toast.LENGTH_LONG).show();

        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        ShelterEditDogQuestionnaire shelterEditDogQuestionnaire = new ShelterEditDogQuestionnaire();
        transaction.replace(R.id.add_pet_frag, shelterEditDogQuestionnaire);
        transaction.commit();
    }
}
