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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class BrowseAnimalsAdapter extends RecyclerView.Adapter<BrowseAnimalsAdapter.ViewHolder>{

    RegisteredPetData[] PetsData;
    BrowseAnimals context;

    StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    public BrowseAnimalsAdapter(RegisteredPetData[] PetsData, BrowseAnimals activity){
        this.PetsData = PetsData;
        this.context = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.browseanimalscard, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder (@NonNull ViewHolder holder, int position){
        final RegisteredPetData registeredPetDataList = PetsData[position];

        storageReference.child("Pets/").child(registeredPetDataList.getImageName()).getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Glide.with(context).load(uri.toString()).into((ImageView) holder.itemView.findViewById(R.id.pet_image));
                    }
                });

        holder.tvPetName.setText("Name : " + registeredPetDataList.getPetName());
        holder.tvPetAge.setText("Age : " + registeredPetDataList.getPetAge());
        holder.tvPetSex.setText("Sex : " + registeredPetDataList.getPetSex());
        holder.tvPetBreed.setText("Breed : " + registeredPetDataList.getPetBreed());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), registeredPetDataList.getPetName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return PetsData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPetImage;
        TextView tvPetName, tvPetAge, tvPetSex, tvPetBreed;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPetImage = itemView.findViewById(R.id.pet_image);
            tvPetName = itemView.findViewById(R.id.per_pet_name_title);
            tvPetAge = itemView.findViewById(R.id.pet_age);
            tvPetSex = itemView.findViewById(R.id.pet_sex);
            tvPetBreed = itemView.findViewById(R.id.pet_breed);

        }
    }

}