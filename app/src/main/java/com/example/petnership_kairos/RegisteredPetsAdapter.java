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

public class RegisteredPetsAdapter extends RecyclerView.Adapter<RegisteredPetsAdapter.ViewHolder> {

    RegisteredPetData[] PetsData;
    ShelterListOfPetsFragment context;

    ImageView img1;

    DatabaseReference petsPetsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets");
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;

    private String shelterEmail, petID, petImageName;

    private ArrayList<String> petIDs = new ArrayList<>();

    public RegisteredPetsAdapter(RegisteredPetData[] PetsData, ShelterListOfPetsFragment activity){
        this.PetsData = PetsData;
        this.context = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_shelter_registered_pets,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        shelterEmail = firebaseUser.getEmail();


        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
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

        holder.cvPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ShelterPerPetProfile.class);
                intent.putExtra("PetID", registeredPetDataList.getPetID());
                view.getContext().startActivity(intent);
            }
        });

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

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivPetImage;
        TextView tvPetName, tvPetAge, tvPetSex, tvPetBreed;

        CardView cvPet;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPetImage = itemView.findViewById(R.id.pet_image);
            tvPetName = itemView.findViewById(R.id.per_pet_name_title);
            tvPetAge = itemView.findViewById(R.id.pet_age);
            tvPetSex = itemView.findViewById(R.id.pet_sex);
            tvPetBreed = itemView.findViewById(R.id.pet_breed);
            cvPet = itemView.findViewById(R.id.cvPet);

        }
    }
}