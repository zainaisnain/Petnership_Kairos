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

public class RegisteredPetsAdapter extends RecyclerView.Adapter<RegisteredPetsAdapter.ViewHolder> {

    RegisteredPetData[] PetsData;
    Context context;

    ImageView img1;

    DatabaseReference petsPetsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets");
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;

    private String shelterEmail, petID, petImageName;

    private ArrayList<String> petIDs = new ArrayList<>();

    public RegisteredPetsAdapter(RegisteredPetData[] PetsData, ShelterListOfPets activity){
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

//        holder.ivPetImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                PerPetProfilePets yourfragmentobject = new PerPetProfilePets();
//                AppCompatActivity activity = (AppCompatActivity) view.getContext();
//                activity.getSupportFragmentManager().beginTransaction().
//                        replace(R.id.per_Pet_profile_frag, yourfragmentobject)
//                        .addToBackStack(null).commit();
//
//            }
//        });

        storageReference.child("Pets/").child(registeredPetDataList.getPetImageName()).getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Glide.with(context).load(uri.toString()).into((ImageView) holder.itemView.findViewById(R.id.pet_image));
                    }
                });

        System.out.println("petImageName OUTSIDE" + petImageName);

//        holder.ivPetImage.setImageResource(registeredPetDataList.getImageName());
        holder.tvPetName.setText("Name : " + registeredPetDataList.getPetName());
        holder.tvPetAge.setText("Age : " + registeredPetDataList.getPetAge());
        holder.tvPetSex.setText("Sex : " + registeredPetDataList.getPetSex());
        holder.tvPetBreed.setText("Breed : " + registeredPetDataList.getPetBreed());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, registeredPetDataList.getPetName(),Toast.LENGTH_SHORT).show();
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPetImage = itemView.findViewById(R.id.pet_image);
            tvPetName = itemView.findViewById(R.id.per_cat_name_title);
            tvPetAge = itemView.findViewById(R.id.pet_age);
            tvPetSex = itemView.findViewById(R.id.pet_sex);
            tvPetBreed = itemView.findViewById(R.id.pet_breed);

        }
    }
}
