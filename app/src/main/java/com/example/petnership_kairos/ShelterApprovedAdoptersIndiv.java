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

public class ShelterApprovedAdoptersIndiv extends Fragment {

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

        applicationID = getArguments().getString("applicationID");
        adopterID = getArguments().getString("adopterID");
        System.out.println("adopterID == " + adopterID);
//        adopterName = getArguments().getString("adopterName");
        petID  = getArguments().getString("petID");
//        petName= getArguments().getString("petName");
//        dateApplied= getArguments().getString("dateApplied");

        //IDs
        tvPetName = view.findViewById(R.id.pet_name_shelter_approved);
        tvPetType = view.findViewById(R.id.pet_type_shelter_approved);
        tvPetAge = view.findViewById(R.id.pet_age_shelter_approved);
        tvPetSex = view.findViewById(R.id.pet_sex_shelter_approved);
        tvPetBreed = view.findViewById(R.id.pet_breed_shelter_approved);
        tvPetMatch = view.findViewById(R.id.pet_match_value_shelter_approved);
        tvDateApplied = view.findViewById(R.id.date_applied_shelter_approved);
        tvAdopterFullName = view.findViewById(R.id.adopter_name_shelter_approved);
        tvAdopterBirthday = view.findViewById(R.id.adopter_birthday_shelter_approved);
        tvAdopterEmail = view.findViewById(R.id.adopter_email_shelter_approved);
        tvAdopterContact = view.findViewById(R.id.adopter_contact_shelter_approved);
        tvAdopterAddress = view.findViewById(R.id.adopter_address_shelter_approved);
        tvAdopterOwnRent = view.findViewById(R.id.adopter_own_rent_shelter_approved);
        tvAdopterRentPolicy = view.findViewById(R.id.adopter_rent_yes_shelter_approved);
        tvAdopterYard = view.findViewById(R.id.adopter_yard_shelter_approved);
        tvAdopterYardFence = view.findViewById(R.id.adopter_yard_yes_shelter_approved);
        tvAdopterChildren = view.findViewById(R.id.adopter_children_shelter_approved);
        tvAdopterChildrenDetails = view.findViewById(R.id.adopter_children_yes_shelter_approved);
        tvAdopterWork = view.findViewById(R.id.adopter_work_shelter_approved);
        tvAdopterCompany = view.findViewById(R.id.adopter_work_company_shelter_approved);
        tvAdopterPosition = view.findViewById(R.id.adopter_work_position_shelter_approved);
        tvAdopterOwnPets = view.findViewById(R.id.adopter_own_pets_shelter_approved);
        tvAdopterOwnPetNames = view.findViewById(R.id.adopter_pet_names_shelter_approved);
        tvAdopterOwnPetBreeds = view.findViewById(R.id.adopter_pet_breeds_shelter_approved);
        tvAdopterSurrender = view.findViewById(R.id.adopter_surrender_shelter_approved);
        tvAdopterPetVet = view.findViewById(R.id.adopter_vet_shelter_approved);
        tvAdopterConvicted = view.findViewById(R.id.adopter_convicted_shelter_approved);
        tvAdopterConvictedDetails = view.findViewById(R.id.adopter_convicted_yes_shelter_approved);
        tvAdopterHoursAlone = view.findViewById(R.id.adopter_alone_hours_shelter_approved);
        tvAdopterEmergency = view.findViewById(R.id.adopter_emergency_shelter_approved);
        tvAdopterCrate = view.findViewById(R.id.adopter_crate_shelter_approved);
        tvAdopterReferences = view.findViewById(R.id.adopter_references_shelter_approved);
        tvAdopterIntentions = view.findViewById(R.id.adopter_intentions_shelter_approved);
        etShelterReason = view.findViewById(R.id.shelter_reason_for_status_shelter_approved);

        ivAdopter = view.findViewById(R.id.per_adopter_image);
        ivPet = view.findViewById(R.id.pet_image_shelter_approved);


        ddApplicationStatus = view.findViewById(R.id.app_status_dd_shelter_approved);
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

        populateTextViews();

        saveApplication = view.findViewById(R.id.save_btn_shelter_approved);
        saveApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("OnClick applicationStatus == " + applicationStatus);
                shelterReason = etShelterReason.getText().toString();

                if(!applicationStatus.equals("Approved")){
                    //UPDATE DBS
                    updateApplicationStatusOnDBs();
                }

                MySaveDialogShelterToReview mySaveDialogShelterToReview = new MySaveDialogShelterToReview();
                mySaveDialogShelterToReview.show(getParentFragmentManager(), "My Fragment");
                MyCustomDialog submitDialog = new MyCustomDialog();
                submitDialog.show(getParentFragmentManager(), "My Fragment");
            }
        });

        backBtn = view.findViewById(R.id.shelter_approved_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyCancelDialogShelter myCancelDialogShelter = new MyCancelDialogShelter();
                myCancelDialogShelter.show(getParentFragmentManager(), "My Fragment");
            }
        });
        cancelApplication = view.findViewById(R.id.cancel_btn_shelter_approved);
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
                                            tvPetAge.setText((String) snapshot.child("petAge").getValue());
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

                                    if(snapshot.hasChild("ApprovedAdopters")){
                                        sheltersDBRef.child(shelterID).child("ApprovedAdopters").child(applicationID).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                applicationStatus = (String) snapshot.child("applicationStatus").getValue();
                                                int statusPosition = applicationStatusAdapter.getPosition(applicationStatus);
                                                ddApplicationStatus.setSelection(statusPosition);

                                                //Get shelter reason if there was any
                                                sheltersDBRef.child(shelterID).child("ApprovedAdopters").child(applicationID)
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

    private void updateApplicationStatusOnDBs(){
        //        DatabaseReference fromPath = sheltersDBRef.child(sID).child("ApprovedAdopters").child(applicationID);
        //        DatabaseReference toPath = sheltersDBRef.child(sID).child("ForReviewApplicants").child(applicationID)


        //first get shelterID from adoptersDB
        adoptersDBRef.child(adopterID).child("ApplicationHistory").child(applicationID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String sID = (String) dataSnapshot.child("shelterID").getValue();
                System.out.println("sID == " + sID);
                System.out.println("applicationID == " + applicationID);

                sheltersDBRef.child(sID).child("ApprovedAdopters").child(applicationID).addListenerForSingleValueEvent(new ValueEventListener() {
                    // Now "DataSnapshot" holds the key and the value at the "fromPath".
                    // Let's move it to the "toPath". This operation duplicates the
                    // key/value pair at the "fromPath" to the "toPath".
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        sheltersDBRef.child(sID).child("ForReviewApplicants").child(dataSnapshot.getKey())
                                .setValue(dataSnapshot.getValue(), new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                        if (databaseError == null) {
                                            Log.i(TAG, "onComplete: success");
                                            sheltersDBRef.child(sID).child("ForReviewApplicants").child(applicationID).addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    snapshot.child("applicationStatus").getRef().setValue(applicationStatus);
                                                    snapshot.child("shelterReason").getRef().setValue(shelterReason);
                                                    //delete from ForReviewApplicants
                                                    sheltersDBRef.child(sID).child("ApprovedAdopters").child(applicationID).removeValue();
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

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "onCancelled: " + databaseError.getMessage() + ": "
                        + databaseError.getDetails());
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}