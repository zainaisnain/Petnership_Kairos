package com.example.petnership_kairos;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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
    ShelterListOfDogsFragment context;
    ImageView img1;

    DatabaseReference petsDogsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("Dogs");
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;
    private String shelterEmail, petID, petImageName;

    private ArrayList<String> petIDs = new ArrayList<>();

    public RegisteredDogsAdapter(RegisteredDogData[] dogsData, ShelterListOfDogsFragment context){
        this.dogsData = dogsData;
        this.context = context;
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

        System.out.println("registeredDogDataList.getImageName()) TOP STORAGEREF" + registeredDogDataList.getImageName());
        storageReference.child("Pets/").child(registeredDogDataList.getImageName()).getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        System.out.println("registeredDogDataList.getImageName()) ONSUCCES" + registeredDogDataList.getImageName());
                        Glide.with(context).load(uri.toString()).into((ImageView) holder.itemView.findViewById(R.id.dog_image));
                    }
                });

        System.out.println("petImageName OUTSIDE" + petImageName);

        holder.cvDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ShelterPerDogProfile.class);
                intent.putExtra("dogPetID", registeredDogDataList.getPetID());
                view.getContext().startActivity(intent);
            }
        });

        holder.tvPetName.setText("Name : " + registeredDogDataList.getPetName());
        holder.tvPetAge.setText("Age : " + registeredDogDataList.getPetAge());
        holder.tvPetSex.setText("Sex : " + registeredDogDataList.getPetSex());
        holder.tvPetBreed.setText("Breed : " + registeredDogDataList.getPetBreed());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), registeredDogDataList.getPetName(),Toast.LENGTH_SHORT).show();
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
        CardView cvDog;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cvDog = itemView.findViewById(R.id.cvDog);
            ivPetImage = itemView.findViewById(R.id.dog_image);
            tvPetName = itemView.findViewById(R.id.dog_name);
            tvPetAge = itemView.findViewById(R.id.dog_age);
            tvPetSex = itemView.findViewById(R.id.dog_sex);
            tvPetBreed = itemView.findViewById(R.id.dog_breed);

        }
    }
}
