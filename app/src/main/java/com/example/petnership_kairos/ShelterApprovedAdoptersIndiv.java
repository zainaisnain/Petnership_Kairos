package com.example.petnership_kairos;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ShelterApprovedAdoptersIndiv extends Fragment {

    String[] statusApp = {"Pending", "In progress", "Rejected", "Approved"};

    Spinner statusAppTxt;

    Button saveApplication, cancelApplication;

    ArrayAdapter<String> statusAdapter;
    protected static String applicationStatus;

    private EditText mEditTextTo, mEditTextSubject, mEditTextMessage;
    private TextView tvAdoptionFormDate, tvAdopterName, tvAdopterEmail,
            tvAdopterMobile, tvAdopterAddress, tvPetType, tvBreed,
            tvPetName, tvPetAge, tvPetDesc;

    String shelterEmail;
    String applicationID, dateApplied, timeApplied, adopterID, adopterName, adopterIntentions,
            petID, petType, petName, petBreed, petAge, petDescription, shelterID;
    String adopterEmail, adopterContact, adopterAddress;
    //DB REF
    DatabaseReference allPetsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("AllPets");
    DatabaseReference adoptersDBRef = FirebaseDatabase.getInstance().getReference("Adopters");
    DatabaseReference sheltersDBRef = FirebaseDatabase.getInstance().getReference("Shelters");
    DatabaseReference usersDBRef = FirebaseDatabase.getInstance().getReference("Users");

    //FirebaseAuth
    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;

    private EditText shelterReasonET;
    private String shelterReason;

    private ImageView adopterIV;
    private String adopterImageName;
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    public static ShelterApprovedAdoptersIndiv newInstance() {
        return new ShelterApprovedAdoptersIndiv();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_shelter_approved_adopters_indiv, container, false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Adoption Form Summary");

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        shelterEmail = firebaseUser.getEmail();

        adopterIV = view.findViewById(R.id.per_cat_image);

        statusAppTxt = view.findViewById(R.id.shelter_adoption_application_status);
        statusAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, statusApp);
        statusAppTxt.setAdapter(statusAdapter);

        statusAppTxt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                applicationStatus = adapterView.getItemAtPosition(i).toString();
                System.out.println("applicationStatus inside: " + applicationStatus);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mEditTextTo = view.findViewById(R.id.shelter_emailAddressOfAdopter);
        mEditTextSubject = view.findViewById(R.id.shelter_subjectEmailToAdopter);
        mEditTextMessage = view.findViewById(R.id.shelter_bodyEmailToAdopter);
        tvAdoptionFormDate = view.findViewById(R.id.adoptionForm_petDate);
        tvAdopterName = view.findViewById(R.id.adoptionForm_adopterName);
        tvAdopterEmail = view.findViewById(R.id.adoptionForm_shelter);
        tvAdopterMobile = view.findViewById(R.id.adoptionForm_shelter_reason);
        tvAdopterAddress = view.findViewById(R.id.adoptionForm_address);
        tvPetType = view.findViewById(R.id.adoptionForm_petType);
        tvBreed = view.findViewById(R.id.adoptionForm_petBreed);
        tvPetName = view.findViewById(R.id.adoptionForm_petName);
        tvPetAge = view.findViewById(R.id.adoptionForm_petAge);
        tvPetDesc = view.findViewById(R.id.adoptionForm_petDescription);

        applicationID = getArguments().getString("applicationID");
        adopterID = getArguments().getString("adopterID");
//        adopterName = getArguments().getString("adopterName");
//        petID  = getArguments().getString("petID");
//        petName= getArguments().getString("petName");
//        dateApplied= getArguments().getString("dateApplied");

        shelterReasonET = view.findViewById(R.id.reasonForStatus_shelter);

        populateTextViews();

        Button sendBtn = view.findViewById(R.id.shelter_sendEmail);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });

        saveApplication = view.findViewById(R.id.btn_save_adoptionForm);
        saveApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("OnClick applicationStatus == " + applicationStatus);
                shelterReason = shelterReasonET.getText().toString();

                if(applicationStatus.equals("Approved")){
                    markAsApprovedDB();
                }else{
                    //UPDATE DBS
                    updateApplicationStatusOnDBs();
                }

//                MySaveDialogShelterToReview mySaveDialogShelterToReview = new MySaveDialogShelterToReview();
//                mySaveDialogShelterToReview.show(getParentFragmentManager(), "My Fragment");
//                MyCustomDialog submitDialog = new MyCustomDialog();
//                submitDialog.show(getParentFragmentManager(), "My Fragment");
            }
        });

        cancelApplication = view.findViewById(R.id.btn_cancel_adoptionForm);
        cancelApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyCancelDialogShelter myCancelDialogShelter = new MyCancelDialogShelter();
                myCancelDialogShelter.show(getParentFragmentManager(), "My Fragment");
            }
        });


        //retrieve from db
        //show those in designated fields
    }

    private void populateTextViews(){
        adoptersDBRef.child(adopterID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adopterEmail = (String) snapshot.child("email").getValue();
                adopterContact = (String) snapshot.child("contact").getValue();
                String street  = (String) snapshot.child("street").getValue();
                String city = (String) snapshot.child("city").getValue();
                String province = (String) snapshot.child("province").getValue();
                String country = (String) snapshot.child("country").getValue();
                adopterAddress = street + ", " +  city + ", " + province + ", " + country;

                tvAdopterEmail.setText(adopterEmail);
                tvAdopterMobile.setText(adopterContact);
                tvAdopterAddress.setText(adopterAddress);

                //check if there's image
                adoptersDBRef.child(adopterID).orderByKey().equalTo("imageName").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            adoptersDBRef.child(adopterID).child("imageName").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    adopterImageName = String.valueOf(snapshot.getValue());
                                    if(adopterImageName != null){
                                        if(!adopterImageName.isEmpty()){
                                            if(adopterImageName != ""){
                                                //DISPLAY IMAGE TO IMAGE VIEW
                                                storageReference.child("Adopters/").child(adopterImageName).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                    @Override
                                                    public void onSuccess(Uri uri) {
                                                        Glide.with(getActivity().getApplicationContext()).load(uri.toString()).into(adopterIV);
                                                    }
                                                });
                                            }
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                usersDBRef.orderByChild("email").equalTo(shelterEmail).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot usersSnapshot) {
                        if(usersSnapshot.exists()) {
                            for (DataSnapshot ds : usersSnapshot.getChildren()) {
                                shelterID = ds.getKey();
                            }
                            sheltersDBRef.child(shelterID).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if(snapshot.hasChild("ApprovedAdopters")){
                                        sheltersDBRef.child(shelterID).child("ApprovedAdopters").addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                applicationStatus = (String) snapshot.child(applicationID).child("applicationStatus").getValue();
                                                int statusPosition = statusAdapter.getPosition(applicationStatus);
                                                statusAppTxt.setSelection(statusPosition);

                                                sheltersDBRef.child(shelterID).child("ApprovedAdopters").child(applicationID)
                                                        .orderByKey().equalTo("shelterReason").addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                if(snapshot.exists()){
                                                                    shelterReason = (String) snapshot.child("shelterReason").getValue();
                                                                    shelterReasonET.setText(shelterReason);
                                                                }
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {

                                                            }
                                                        });

                                                dateApplied = (String) snapshot.child(applicationID).child("dateApplied").getValue();
                                                adopterID = (String) snapshot.child(applicationID).child("adopterID").getValue();
                                                adopterName = (String) snapshot.child(applicationID).child("adopterName").getValue();

                                                petType = (String) snapshot.child(applicationID).child("petType").getValue();
                                                petName = (String) snapshot.child(applicationID).child("petName").getValue();
                                                petBreed = (String) snapshot.child(applicationID).child("petBreed").getValue();
                                                petAge = (String) snapshot.child(applicationID).child("petAge").getValue();
                                                petDescription = (String) snapshot.child(applicationID).child("petDescription").getValue();

                                                tvAdoptionFormDate.setText(dateApplied);
                                                tvAdopterName.setText(adopterName);
                                                tvPetType.setText(petType);
                                                tvPetName.setText(petName);
                                                tvBreed.setText(petBreed);
                                                tvPetAge.setText(petAge);
                                                tvPetDesc.setText(petDescription);
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

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
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

    }

    private void markAsApprovedDB(){
//        DatabaseReference fromPath = sheltersDBRef.child(sID).child("ForReviewApplicants").child(applicationID)
//        DatabaseReference toPath = sheltersDBRef.child(sID).child("ApprovedAdopters").child(applicationID);

        //first get shelterID from adoptersDB
        adoptersDBRef.child(adopterID).child("ApplicationHistory").child(applicationID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String sID = (String) dataSnapshot.child("shelterID").getValue();
                System.out.println("sID == " + sID);
                System.out.println("applicationID == " + applicationID);


                sheltersDBRef.child(sID).child("ForReviewApplicants").orderByKey().equalTo(applicationID).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            sheltersDBRef.child(sID).child("ForReviewApplicants").child(applicationID).addListenerForSingleValueEvent(new ValueEventListener() {
                                // Now "DataSnapshot" holds the key and the value at the "fromPath".
                                // Let's move it to the "toPath". This operation duplicates the
                                // key/value pair at the "fromPath" to the "toPath".
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    sheltersDBRef.child(sID).child("ApprovedAdopters").child(dataSnapshot.getKey())
                                            .setValue(dataSnapshot.getValue(), new DatabaseReference.CompletionListener() {
                                                @Override
                                                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                                    if (databaseError == null) {
                                                        Log.i(TAG, "onComplete: success");
                                                        sheltersDBRef.child(sID).child("ApprovedAdopters").child(applicationID).addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                snapshot.child("applicationStatus").getRef().setValue("Approved");
                                                                //delete from ForReviewApplicants
                                                                sheltersDBRef.child(sID).child("ForReviewApplicants").child(applicationID).removeValue();
                                                                //Update applicationStatus in adoptersDB
                                                                adoptersDBRef.child(adopterID).child("ApplicationHistory").child(applicationID)
                                                                        .child("applicationStatus").setValue(applicationStatus);

                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {

                                                            }
                                                        });
                                                        // In order to complete the move, we are going to erase
                                                        // the original copy by assigning null as its value.
//                                            sheltersDBRef.child(sID).child("ForReviewApplicants").child(applicationID).setValue(null);
                                                    }
                                                    else {
                                                        Log.e(TAG, "onComplete: failure:" + databaseError.getMessage() + ": "
                                                                + databaseError.getDetails());
                                                    }
                                                }
                                            });
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    Log.e(TAG, "onCancelled: " + databaseError.getMessage() + ": "
                                            + databaseError.getDetails());
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "onCancelled: " + databaseError.getMessage() + ": "
                        + databaseError.getDetails());
            }
        });
    }

    private void updateApplicationStatusOnDBs(){
        //move from approved to Application History

        adoptersDBRef.child(adopterID).child("ApplicationHistory").child(applicationID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                snapshot.child("applicationStatus").getRef().setValue(applicationStatus);
                //insert reason
                snapshot.child("shelterReason").getRef().setValue(shelterReason);
                String sID = (String) snapshot.child("shelterID").getValue();

                sheltersDBRef.child(sID).child("ApprovedAdopters").child(applicationID).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        sheltersDBRef.child(sID).child("ForReviewApplicants").child(snapshot.getKey())
                                .setValue(snapshot.getValue(), new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                        if(error == null){
                                            sheltersDBRef.child(sID).child("ForReviewApplicants").child(applicationID).addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    snapshot.child("applicationStatus").getRef().setValue(applicationStatus);
                                                    //delete from ApprovedAdopters
                                                    sheltersDBRef.child(sID).child("ApprovedAdopters").child(applicationID).removeValue();
                                                    //Update applicationStatus in adoptersDB
                                                    adoptersDBRef.child(adopterID).child("ApplicationHistory").child(applicationID)
                                                            .child("applicationStatus").setValue(applicationStatus);
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {

                                                }
                                            });
                                        }else{
                                            Log.e(TAG, "onComplete: failure:" + error.getMessage() + ": "
                                                    + error.getDetails());
                                        }
                                    }
                                });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                sheltersDBRef.child(sID).child("ForReviewApplicants").child(applicationID).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        snapshot.child("applicationStatus").getRef().setValue(applicationStatus);
                        snapshot.child("shelterReason").getRef().setValue(shelterReason);
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
    private void sendMail() {
        String recipientList = mEditTextTo.getText().toString();
        String[] recipients = recipientList.split(",");

        String subject = mEditTextSubject.getText().toString();
        String message = mEditTextMessage.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client: "));

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}