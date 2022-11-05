package com.example.petnership_kairos;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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

import java.util.ArrayList;

public class PetCats extends Fragment {

    ImageView img1;
    private PetCatsViewModel mViewModel;

    DatabaseReference petsCatsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("Cats");
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;

    public static Activity myActivity;
    private String shelterEmail, petID, petImageName;

    private ArrayList<String> petIDs = new ArrayList<>();

    public static PetCats newInstance() {
        return new PetCats();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //TO-DO: edit fragment_pet_dog to fragment_pet_cat; need to run the app
        return inflater.inflate(R.layout.fragment_shelter_cat_questionnaire, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Cats");
        img1 = view.findViewById(R.id.cat_image);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                PerPetProfileCats PerPetProfileCats = new PerPetProfileCats();
                transaction.replace(R.id.nav_host_fragment, PerPetProfileCats);
                transaction.commit();
            }
        });

        //retrieve from list of cats from database
        //retrieve imageName and name of each pet

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        shelterEmail = firebaseUser.getEmail();

        setUpPetPic();


        //***STORE
        storeData();

    }

    private void storeData(){

    }
    private void setUpPetPic() {
        //retrieve imageName
        petsCatsDBRef.orderByChild("shelter").equalTo(shelterEmail)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds : snapshot.getChildren()) {
                            petID = ds.getKey();
                            System.out.println("petID inside " + petID);
                            petIDs.add(petID);

                            petImageName = String.valueOf(snapshot.child(petID).child("imageName").getValue());
                            System.out.println("petImageName INSIDE" + petImageName);
                        }

                        snapshot.getRef().orderByKey().equalTo("imageName").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists()){
                                    if(petImageName != null){
                                        if(!petImageName.isEmpty()){
                                            if(petImageName != ""){
                                                //DISPLAY IMAGE TO IMAGE VIEW
                                                storageReference.child("Pets/").child(petImageName).getDownloadUrl()
                                                        .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                            @Override
                                                            public void onSuccess(Uri uri) {
                                                                Glide.with(getActivity().getApplicationContext()).load(uri.toString()).into(img1);
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PetCatsViewModel.class);
        // TODO: Use the ViewModel
    }

}