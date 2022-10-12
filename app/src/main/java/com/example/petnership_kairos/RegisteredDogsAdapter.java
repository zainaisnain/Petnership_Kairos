package com.example.petnership_kairos;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class RegisteredDogsAdapter extends RecyclerView.Adapter<RegisteredDogsAdapter.ViewHolder> {

    RegisteredDogData[] dogsData;
    Context context;

    ImageView img1;

    DatabaseReference petsDogsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("Dogs");
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;

    private String shelterEmail, petID, petImageName;

    private ArrayList<String> petIDs = new ArrayList<>();

    public RegisteredDogsAdapter(RegisteredDogData[] dogsData, ShelterListOfDogs activity){
        this.dogsData = dogsData;
        this.context = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_dogs,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        shelterEmail = firebaseUser.getEmail();


        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final RegisteredDogData registeredDogDataList = dogsData[position];

//        holder.ivPetImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                PerPetProfileDogs yourfragmentobject = new PerPetProfileDogs();
//                AppCompatActivity activity = (AppCompatActivity) view.getContext();
//                activity.getSupportFragmentManager().beginTransaction().
//                        replace(R.id.per_dog_profile_frag, yourfragmentobject)
//                        .addToBackStack(null).commit();
//
//            }
//        });

        storageReference.child("Pets/").child(registeredDogDataList.getDogImageName()).getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Glide.with(context).load(uri.toString()).into((ImageView) holder.itemView.findViewById(R.id.dog_image));
                    }
                });

        System.out.println("petImageName OUTSIDE" + petImageName);

//        holder.ivPetImage.setImageResource(registeredDogDataList.getImageName());
        holder.tvPetName.setText("Name : " + registeredDogDataList.getDogName());
        holder.tvPetAge.setText("Age : " + registeredDogDataList.getDogAge());
        holder.tvPetSex.setText("Sex : " + registeredDogDataList.getDogSex());
        holder.tvPetBreed.setText("Breed : " + registeredDogDataList.getDogBreed());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, registeredDogDataList.getDogName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dogsData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivPetImage;
        TextView tvPetName, tvPetAge, tvPetSex, tvPetBreed;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPetImage = itemView.findViewById(R.id.dog_image);
            tvPetName = itemView.findViewById(R.id.dog_name);
            tvPetAge = itemView.findViewById(R.id.dog_age);
            tvPetSex = itemView.findViewById(R.id.dog_sex);
            tvPetBreed = itemView.findViewById(R.id.dog_breed);

        }
    }
}
