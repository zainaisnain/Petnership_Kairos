package com.example.petnership_kairos;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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

public class RegisteredCatsAdapter extends RecyclerView.Adapter<RegisteredCatsAdapter.ViewHolder> {

    RegisteredCatData[] petsData;
    Context context;

    DatabaseReference petsCatsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("Cats");
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;

    private String shelterEmail, petID, petImageName;

    private ArrayList<String> petIDs = new ArrayList<>();

    public RegisteredCatsAdapter(RegisteredCatData[] petsData, ShelterListOfCats activity){
        this.petsData = petsData;
        this.context = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_cats,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        shelterEmail = firebaseUser.getEmail();

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final RegisteredCatData registeredCatDataList = petsData[position];

        storageReference.child("Pets/").child(registeredCatDataList.getImageName()).getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        System.out.println("registeredCatDataList.getImageName() ::: " + registeredCatDataList.getImageName());
                        Glide.with(context).load(uri.toString()).into((ImageView) holder.itemView.findViewById(R.id.cat_image));
                    }
                });

        System.out.println("registeredCatDataList.getImageName() OUTSIDE ::: " + registeredCatDataList.getImageName());

        holder.cvCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PerCatProfile.class);
                intent.putExtra("petID", registeredCatDataList.getPetID());
                view.getContext().startActivity(intent);
            }
        });

//        holder.ivPetImage.setImageResource(registeredCatDataList.getImageName());
        holder.tvPetName.setText("Name : " + registeredCatDataList.getPetName());
        holder.tvPetAge.setText("Age : " + registeredCatDataList.getPetAge());
        holder.tvPetSex.setText("Sex : " + registeredCatDataList.getPetSex());
        holder.tvPetBreed.setText("Breed : " + registeredCatDataList.getPetBreed());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, registeredCatDataList.getPetName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return petsData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivPetImage;
        TextView tvPetName, tvPetAge, tvPetSex, tvPetBreed;
        CardView cvCat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cvCat = itemView.findViewById(R.id.cvCat);
            ivPetImage = itemView.findViewById(R.id.cat_image);
            tvPetName = itemView.findViewById(R.id.cat_name);
            tvPetAge = itemView.findViewById(R.id.cat_age);
            tvPetSex = itemView.findViewById(R.id.cat_sex);
            tvPetBreed = itemView.findViewById(R.id.cat_breed);

        }
    }
}
