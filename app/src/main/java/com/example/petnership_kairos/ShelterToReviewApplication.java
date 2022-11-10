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
import android.widget.ImageButton;
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

import java.util.Locale;

public class ShelterToReviewApplication extends Fragment {

    String[] applicationStatusValues = {"Pending", "In progress", "Rejected", "Approved"};

    Spinner ddApplicationStatus;

    Button saveApplication, cancelApplication;

    ArrayAdapter<String> applicationStatusAdapter;
    protected static String applicationStatus;
    ImageButton backBtn;
    private EditText mEditTextTo, mEditTextSubject, mEditTextMessage;
    private TextView tvPetName, tvPetType, tvPetAge, tvPetSex, tvPetBreed, tvPetMatch,
            tvDateApplied, tvAdopterFullName, tvAdopterBirthday, tvAdopterEmail, tvAdopterContact, tvAdopterAddress,
            tvAdopterOwnRent, tvAdopterRentPolicy, tvAdopterYard, tvAdopterYardFence, tvAdopterChildren, tvAdopterChildrenDetails,
            tvAdopterWork, tvAdopterCompany, tvAdopterPosition,
            tvAdopterOwnPets, tvAdopterOwnPetNames, tvAdopterOwnPetBreeds, tvAdopterSurrender, tvAdopterPetVet, tvAdopterConvicted, tvAdopterConvictedDetails,
            tvAdopterHoursAlone, tvAdopterEmergency, tvAdopterCrate,
            tvAdopterReferences, tvAdopterIntentions;

    String shelterEmail;
    String applicationID, dateApplied, timeApplied, adopterID, adopterName, adopterIntentions,
    petID, petType, petName, petBreed, petAge, petDescription, shelterID;
    String adopterEmail, adopterContact, adopterAddress;
    //DB REFs
    DatabaseReference allPetsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("AllPets");
    DatabaseReference petsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets");
    DatabaseReference adoptersDBRef = FirebaseDatabase.getInstance().getReference("Adopters");
    DatabaseReference sheltersDBRef = FirebaseDatabase.getInstance().getReference("Shelters");
    DatabaseReference usersDBRef = FirebaseDatabase.getInstance().getReference("Users");

    //FirebaseAuth
    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;

    private EditText etShelterReason;
    private String shelterReason;

    private ImageView ivAdopter, ivPet;
    private String adopterImageName;
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    public static ShelterToReviewApplication newInstance() {
        return new ShelterToReviewApplication();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_shelter_to_review_application, container, false);
        return view;
    }
    

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Adoption Form Summary");

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        shelterEmail = firebaseUser.getEmail();

        applicationID = getArguments().getString("applicationID");
        adopterID = getArguments().getString("adopterID");
//        adopterName = getArguments().getString("adopterName");
        petID  = getArguments().getString("petID");
//        petName= getArguments().getString("petName");
//        dateApplied= getArguments().getString("dateApplied");

        //IDs
        tvPetName = view.findViewById(R.id.pet_name_shelter_to_review);
        tvPetType = view.findViewById(R.id.pet_type_shelter_to_review);
        tvPetAge = view.findViewById(R.id.pet_age_shelter_to_review);
        tvPetSex = view.findViewById(R.id.pet_sex_shelter_to_review);
        tvPetBreed = view.findViewById(R.id.pet_breed_shelter_to_review);
        tvPetMatch = view.findViewById(R.id.pet_match_value_shelter_to_review);
        tvDateApplied = view.findViewById(R.id.date_applied_shelter_to_review);
        tvAdopterFullName = view.findViewById(R.id.adopter_name_shelter_to_review);
        tvAdopterBirthday = view.findViewById(R.id.adopter_birthday_shelter_to_review);
        tvAdopterEmail = view.findViewById(R.id.adopter_email_shelter_to_review);
        tvAdopterContact = view.findViewById(R.id.adopter_contact_shelter_to_review);
        tvAdopterAddress = view.findViewById(R.id.adopter_address_shelter_to_review);
        tvAdopterOwnRent = view.findViewById(R.id.adopter_own_rent_shelter_to_review);
        tvAdopterRentPolicy = view.findViewById(R.id.adopter_rent_yes_shelter_to_review);
        tvAdopterYard = view.findViewById(R.id.adopter_yard_shelter_to_review);
        tvAdopterYardFence = view.findViewById(R.id.adopter_yard_yes_shelter_to_review);
        tvAdopterChildren = view.findViewById(R.id.adopter_children_shelter_to_review);
        tvAdopterChildrenDetails = view.findViewById(R.id.adopter_children_yes_shelter_to_review);
        tvAdopterWork = view.findViewById(R.id.adopter_work_shelter_to_review);
        tvAdopterCompany = view.findViewById(R.id.adopter_work_company_shelter_to_review);
        tvAdopterPosition = view.findViewById(R.id.adopter_work_position_shelter_to_review);
        tvAdopterOwnPets = view.findViewById(R.id.adopter_own_pets_shelter_to_review);
        tvAdopterOwnPetNames = view.findViewById(R.id.adopter_pet_names_shelter_to_review);
        tvAdopterOwnPetBreeds = view.findViewById(R.id.adopter_pet_breeds_shelter_to_review);
        tvAdopterSurrender = view.findViewById(R.id.adopter_surrender_shelter_to_review);
        tvAdopterPetVet = view.findViewById(R.id.adopter_vet_shelter_to_review);
        tvAdopterConvicted = view.findViewById(R.id.adopter_convicted_shelter_to_review);
        tvAdopterConvictedDetails = view.findViewById(R.id.adopter_convicted_yes_shelter_to_review);
        tvAdopterHoursAlone = view.findViewById(R.id.adopter_alone_hours_shelter_to_review);
        tvAdopterEmergency = view.findViewById(R.id.adopter_emergency_shelter_to_review);
        tvAdopterCrate = view.findViewById(R.id.adopter_crate_shelter_to_review);
        tvAdopterReferences = view.findViewById(R.id.adopter_references_shelter_to_review);
        tvAdopterIntentions = view.findViewById(R.id.adopter_intentions_shelter_to_review);
        etShelterReason = view.findViewById(R.id.shelter_reason_for_status_shelter_to_review);

        ivAdopter = view.findViewById(R.id.per_adopter_image);
        ivPet = view.findViewById(R.id.pet_image_shelter_to_review);


        ddApplicationStatus = view.findViewById(R.id.app_status_dd_shelter_to_review);
        applicationStatusAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, applicationStatusValues);
        ddApplicationStatus.setAdapter(applicationStatusAdapter);
        ddApplicationStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                applicationStatus = adapterView.getItemAtPosition(i).toString();
                System.out.println("applicationStatus inside: " + applicationStatus);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //EMAIL STUFF
        mEditTextTo = view.findViewById(R.id.shelter_emailAddressOfAdopter);
        mEditTextSubject = view.findViewById(R.id.shelter_subjectEmailToAdopter);
        mEditTextMessage = view.findViewById(R.id.shelter_bodyEmailToAdopter);

        populateTextViews();

        Button sendBtn = view.findViewById(R.id.shelter_sendEmail);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });

        saveApplication = view.findViewById(R.id.save_btn_shelter_to_review);
        saveApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("OnClick applicationStatus == " + applicationStatus);
                shelterReason = etShelterReason.getText().toString();

                if(applicationStatus.equals("Approved")){
                    markAsApprovedDB();
                }else{
                    //UPDATE DBS
                    updateApplicationStatusOnDBs();
                }

                MySaveDialogShelterToReview mySaveDialogShelterToReview = new MySaveDialogShelterToReview();
                mySaveDialogShelterToReview.show(getParentFragmentManager(), "My Fragment");
            }
        });

        backBtn = view.findViewById(R.id.shelter_to_review_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyCancelDialogShelter myCancelDialogShelter = new MyCancelDialogShelter();
                myCancelDialogShelter.show(getParentFragmentManager(), "My Fragment");
            }
        });
        cancelApplication = view.findViewById(R.id.cancel_btn_shelter_to_review);
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
                String fname = (String) snapshot.child("fname").getValue();
                String lname = (String) snapshot.child("lname").getValue();
                tvAdopterFullName.setText(fname + " " + lname);
                tvAdopterBirthday.setText((String) snapshot.child("birthday").getValue());
                tvAdopterEmail.setText((String) snapshot.child("email").getValue());
                tvAdopterContact.setText((String) snapshot.child("contact").getValue());

                String street  = (String) snapshot.child("street").getValue();
                String city = (String) snapshot.child("city").getValue();
                String province = (String) snapshot.child("province").getValue();
                String country = (String) snapshot.child("country").getValue();
                adopterAddress = street + ", " +  city + ", " + province + ", " + country;
                tvAdopterAddress.setText(adopterAddress);

                adoptersDBRef.child(adopterID).child("AdopterAllPets").child(petID).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.child("MatchPercentage").getValue() != null) {
                            double match = (double) snapshot.child("MatchPercentage").getValue();
                            tvPetMatch.setText(String.format(Locale.getDefault(), "%.2f%%", match*100));

                        }
                        else {
                            tvPetMatch.setText("N/A");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

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
                                                        Glide.with(getActivity().getApplicationContext()).load(uri.toString()).into(ivAdopter);
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


                //Get other values from shelter's db
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
                                    allPetsDBRef.child(petID).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            tvPetName.setText((String) snapshot.child("petName").getValue());
                                            petType = (String) snapshot.child("petType").getValue();
                                            tvPetType.setText(petType);
                                            tvPetAge.setText((String) snapshot.child("petAgeNum").getValue());
                                            tvPetSex.setText((String) snapshot.child("petSex").getValue());
                                            if(petType.equals("cat") || petType.equals("Cat")){
                                                tvPetBreed.setText((String) snapshot.child("q9").getValue());
                                            }else{
                                                tvPetBreed.setText((String) snapshot.child("q10").getValue());
                                            }

                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });

                                    if(snapshot.hasChild("ForReviewApplicants")){
                                        sheltersDBRef.child(shelterID).child("ForReviewApplicants").child(applicationID).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                applicationStatus = (String) snapshot.child("applicationStatus").getValue();
                                                int statusPosition = applicationStatusAdapter.getPosition(applicationStatus);
                                                ddApplicationStatus.setSelection(statusPosition);

                                                //Get shelter reason if there was any
                                                sheltersDBRef.child(shelterID).child("ForReviewApplicants").child(applicationID)
                                                        .orderByKey().equalTo("shelterReason").addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        if(snapshot.exists()){
                                                            shelterReason = (String) snapshot.child("shelterReason").getValue();
                                                            etShelterReason.setText(shelterReason);
                                                        }
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {

                                                    }
                                                });

                                                dateApplied = (String) snapshot.child("dateApplied").getValue();
                                                tvDateApplied.setText(dateApplied);

                                                tvAdopterOwnRent.setText((String) snapshot.child("ownOrRentHome").getValue());
                                                tvAdopterRentPolicy.setText((String) snapshot.child("rentPetPolicy").getValue());
                                                tvAdopterYard.setText((String) snapshot.child("hasYard").getValue());
                                                tvAdopterYardFence.setText((String) snapshot.child("yardHasFence").getValue());
                                                tvAdopterChildren.setText((String) snapshot.child("hasChildren").getValue());
                                                tvAdopterChildrenDetails.setText((String) snapshot.child("childrenDetails").getValue());
                                                tvAdopterWork.setText((String) snapshot.child("hasWork").getValue());
                                                tvAdopterCompany.setText((String) snapshot.child("workCompany").getValue());
                                                tvAdopterPosition.setText((String) snapshot.child("workPosition").getValue());
                                                tvAdopterOwnPets.setText((String) snapshot.child("hasPet").getValue());
                                                tvAdopterOwnPetNames.setText((String) snapshot.child("petNames").getValue());
                                                tvAdopterOwnPetBreeds.setText((String) snapshot.child("petBreeds").getValue());
                                                tvAdopterSurrender.setText((String) snapshot.child("hasSurrendered").getValue());
                                                tvAdopterPetVet.setText((String) snapshot.child("petVet").getValue());
                                                tvAdopterConvicted.setText((String) snapshot.child("isConvicted").getValue());
                                                tvAdopterConvictedDetails.setText((String) snapshot.child("convictedDetails").getValue());
                                                tvAdopterHoursAlone.setText((String) snapshot.child("hoursAlone").getValue());
                                                tvAdopterEmergency.setText((String) snapshot.child("emergency").getValue());
                                                tvAdopterCrate.setText((String) snapshot.child("willCratePet").getValue());
                                                tvAdopterReferences.setText((String) snapshot.child("references").getValue());
                                                tvAdopterIntentions.setText((String) snapshot.child("adopterIntentions").getValue());
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
                                                    snapshot.child("applicationStatus").getRef().setValue(applicationStatus);
                                                    //delete from ForReviewApplicants
                                                    sheltersDBRef.child(sID).child("ForReviewApplicants").child(applicationID).removeValue();
                                                    //Update applicationStatus in adoptersDB
                                                    adoptersDBRef.child(adopterID).child("ApplicationHistory").child(applicationID)
                                                            .child("applicationStatus").setValue(applicationStatus);
                                                    //update in pets
                                                    allPetsDBRef.child(petID).child("petStatus").setValue("Not Available");
                                                    if(petType.equalsIgnoreCase("cat")) {
                                                        petsDBRef.child("Cats").child(petID).child("petStatus").setValue("Not Available");
                                                    }
                                                    else {

                                                        petsDBRef.child("Dogs").child(petID).child("petStatus").setValue("Not Available");
                                                    }

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

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "onCancelled: " + databaseError.getMessage() + ": "
                        + databaseError.getDetails());
            }
        });
    }

    private void updateApplicationStatusOnDBs(){
        adoptersDBRef.child(adopterID).child("ApplicationHistory").child(applicationID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                snapshot.child("applicationStatus").getRef().setValue(applicationStatus);
                //insert reason
                snapshot.child("shelterReason").getRef().setValue(shelterReason);

                //update in pets
                if(!applicationStatus.equals("Approved")) {

                    allPetsDBRef.child(petID).child("petStatus").setValue("Available");
                    if(petType.equalsIgnoreCase("cat")) {
                        petsDBRef.child("Cats").child(petID).child("petStatus").setValue("Available");
                    }
                    else {

                        petsDBRef.child("Dogs").child(petID).child("petStatus").setValue("Available");
                    }
                }

                String sID = (String) snapshot.child("shelterID").getValue();
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