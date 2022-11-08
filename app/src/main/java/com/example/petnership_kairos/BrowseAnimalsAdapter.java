package com.example.petnership_kairos;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Locale;

public class BrowseAnimalsAdapter extends RecyclerView.Adapter<BrowseAnimalsAdapter.ViewHolder>{

    RegisteredPetData[] PetsData;
    BrowseAnimals context;
    Activity mActivity;

    StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    public BrowseAnimalsAdapter(RegisteredPetData[] PetsData, BrowseAnimals activity, Activity mActivity){
        this.PetsData = PetsData;
        this.context = activity;
        this.mActivity = mActivity;
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

        if(registeredPetDataList.getImageName() != null){
            if(!registeredPetDataList.getImageName().isEmpty()){
                if(registeredPetDataList.getImageName() != ""){
                    //DISPLAY IMAGE TO IMAGE VIEW
                    storageReference.child("Pets/").child(registeredPetDataList.getImageName()).getDownloadUrl()
                            .addOnSuccessListener(uri -> Glide.with(context).load(uri.toString()).into((ImageView) holder.itemView.findViewById(R.id.pet_image)));
                }
            }
        }

        holder.tvPetName.setText(registeredPetDataList.getPetName());
        holder.tvPetAge.setText( registeredPetDataList.getPetAge());
        holder.tvPetSex.setText(registeredPetDataList.getPetSex());
        holder.tvPetBreed.setText(registeredPetDataList.getPetBreed());
        String petType = registeredPetDataList.getPetType();
        if (registeredPetDataList.getPetMatch() == 0.0) {
            holder.tvMatch.setText("N/A");

        }
        else {
            holder.tvMatch.setText(String.format(Locale.getDefault(), "%.2f%% Match", registeredPetDataList.getPetMatch()*100));
        }

        holder.cvAnimal.setOnClickListener(view -> {

            if(petType.equals("dog")){
                Intent dogIntent = new Intent(view.getContext(), AdopterPerDogProfile.class);
                dogIntent.putExtra("PetID", registeredPetDataList.getPetID());
                view.getContext().startActivity(dogIntent);
                mActivity.overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
            }else{
                Intent catIntent = new Intent(view.getContext(), AdopterPerCatProfile.class);
                catIntent.putExtra("PetID", registeredPetDataList.getPetID());
                view.getContext().startActivity(catIntent);
                mActivity.overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
            }

        });

        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return PetsData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPetImage;
        TextView tvPetName, tvPetAge, tvPetSex, tvPetBreed, tvMatch;
        CardView cvAnimal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPetImage = itemView.findViewById(R.id.pet_image);
            tvPetName = itemView.findViewById(R.id.per_pet_name_title);
            tvPetAge = itemView.findViewById(R.id.pet_birthday);
            tvPetSex = itemView.findViewById(R.id.pet_sex);
            tvPetBreed = itemView.findViewById(R.id.pet_breed);
            tvMatch = itemView.findViewById(R.id.pet_compatibility);
            cvAnimal = itemView.findViewById(R.id.cvAnimal);

        }
    }

}