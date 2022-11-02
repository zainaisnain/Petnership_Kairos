package com.example.petnership_kairos;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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

public class ShelterEditCat extends Fragment {

    private EditText etPetName, etPetAge, etPetSex, etPetDescription;
    private Button proceedBtn, uploadBtn, back, backBtn;
    protected static String petName, petAgeNum, petAgeDD, petAge, petSex, petStatus, petDesc, petID, petImage;
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
    DatabaseReference petsCatsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("Cats");

    private boolean imageUploaded=true;

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

    public ShelterEditCat() {
        // Required empty public constructor
    }

    public static ShelterEditCat newInstance() { return new ShelterEditCat(); }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_pet, container, false);

        return view;

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Edit Cat Information");

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();

        ShelterPerCatProfile pcp = new ShelterPerCatProfile();
        petID = pcp.petID;

        //NAME
        etPetName = view.findViewById(R.id.per_pet_name_title);

        //AGE
        etPetAge = view.findViewById(R.id.pet_age_et);
        ddAge = view.findViewById(R.id.pet_age_dd);
        ageAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, ddAgeValues);
        ddAge.setAdapter(ageAdapter);

        //SEX
        ddSex = view.findViewById(R.id.pet_sex_dd);
        sexAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, ddSexValues);
        ddSex.setAdapter(sexAdapter);

        //STATUS
        ddStatus = view.findViewById(R.id.shelter_adoption_application_status);
        statusAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, ddStatusValues);
        ddStatus.setAdapter(statusAdapter);

        //DESCRIPTION
        etPetDescription = view.findViewById(R.id.pet_desc);

        //Set value for Dropdown Age
        ddAge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                petAgeDD = adapterView.getItemAtPosition(i).toString();
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
                MyCancelDialogEdit cancelDialog2 = new MyCancelDialogEdit();
                cancelDialog2.show(getParentFragmentManager(), "My Fragment");
//                transaction.replace(R.id.add_pet_frag,cancelDialog2);

//                transaction.commit();
//                MyCancelDialog2 cancelDialog2 = new MyCancelDialog2();
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.add_pet_frag, cancelDialog2).commit();
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
                System.out.println("petStatus inside: " + petStatus);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        System.out.println("petStatus outside: " + petStatus);

//        backBtn = view.findViewById(R.id.petinfo_back);
//        backBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
//                ShelterListOfCatsFragment shelterRegisteredCats = new ShelterListOfCatsFragment();
//                transaction.replace(R.id.nav_host_fragment,shelterRegisteredCats);
//                transaction.commit();
//            }
//        });

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

        getdata();

        proceedBtn = view.findViewById(R.id.petinfo_proceed);
        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                petName = etPetName.getText().toString().trim();
                petAgeNum = etPetAge.getText().toString().trim();
                petAge = petAgeNum + petAgeDD;
                petDesc = etPetDescription.getText().toString().trim();
                petImage = imageName;


                System.out.println("PET ID == " + petID);

                if(petName.isEmpty()){
                    etPetName.setError("Pet Name is Required.");
                    etPetName.requestFocus();
                    return;
                }else if(petAgeNum.isEmpty()){
                    etPetAge.setError("Pet Age Required.");
                    etPetAge.requestFocus();
                    return;
                }else if(petDesc.isEmpty()){
                    etPetDescription.setError("Pet Description Required.");
                    etPetDescription.requestFocus();
                    return;
                }else{
                    uploadImage();
                    editPetInfo();
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
        imageName = UUID.randomUUID().toString();
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

    private void getdata() {

        //FirebaseDatabase.getInstance().getReference().child("Adopters");
        // calling add value event listener method
        // for getting the values from database.
        System.out.println("petID getData()" + petID);
        petsCatsDBRef.child(petID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                petName = String.valueOf(snapshot.child("petName").getValue());
                System.out.println("petName (ShelterEditCat getData)" + petName);
                etPetName.setText(petName);

                petAge = String.valueOf(snapshot.child("petAge").getValue());
                petAgeNum = (String) snapshot.child("petAgeNum").getValue();
                etPetAge.setText(petAgeNum);

                petAgeDD = (String) snapshot.child("petAgeDD").getValue();
                int agePosition = ageAdapter.getPosition(petAgeDD);
                ddAge.setSelection(agePosition);

                petSex = (String) snapshot.child("petSex").getValue();
                int sexPosition = sexAdapter.getPosition(petSex);
                ddSex.setSelection(sexPosition);

                petStatus = (String) snapshot.child("petStatus").getValue();
                int statusPosition = statusAdapter.getPosition(petStatus);
                ddStatus.setSelection(statusPosition);

                petDesc = String.valueOf(snapshot.child("petDesc").getValue());
                etPetDescription.setText(petDesc);

                imageName = String.valueOf(snapshot.child("imageName").getValue());
                storageReference.child("Pets/").child(imageName).getDownloadUrl()
                        .addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Glide.with(getContext()).load(uri.toString()).into(ivPetInfo);
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
        ShelterEditCatQuestionnaire shelterEditCatQuestionnaire = new ShelterEditCatQuestionnaire();
        transaction.replace(R.id.add_pet_frag, shelterEditCatQuestionnaire);
        transaction.commit();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}
