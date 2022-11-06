package com.example.petnership_kairos;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ShelterApprovedAdoptersAdapter extends RecyclerView.Adapter<ShelterApprovedAdoptersAdapter.ViewHolder> {

    DatabaseReference adoptersDBRef = FirebaseDatabase.getInstance().getReference("Adopters");
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private ShelterApprovedAdopters context;
    ShelterApprovedAdoptersData[] shelterApprovedAdoptersData;
    String imageName;

    public ShelterApprovedAdoptersAdapter(ShelterApprovedAdoptersData[] shelterApprovedAdoptersData, ShelterApprovedAdopters activity){
        this.shelterApprovedAdoptersData = shelterApprovedAdoptersData;
        this.context = activity;
    }
    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.application_forreview_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder (ViewHolder holder,int position){
        final ShelterApprovedAdoptersData shelterApprovedAdoptersDataList = shelterApprovedAdoptersData[position];
        holder.applicantName.setText(shelterApprovedAdoptersDataList.getApplicantName());
        holder.applicantPet.setText(shelterApprovedAdoptersDataList.getApplicantPetName());

        String adopterID = shelterApprovedAdoptersDataList.getApplicantID();

        adoptersDBRef.child(adopterID).orderByKey().equalTo("imageName").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    adoptersDBRef.child(adopterID).child("imageName").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            imageName = (String) snapshot.getValue();
                            if(imageName != null){
                                if(!imageName.isEmpty()){
                                    if(imageName != ""){
                                        //DISPLAY IMAGE TO IMAGE VIEW
                                        storageReference.child("Adopters/").child(imageName).getDownloadUrl()
                                                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                    @Override
                                                    public void onSuccess(Uri uri) {
                                                        Glide.with(context).load(uri.toString()).into((ImageView) holder.itemView.findViewById(R.id.applicant_image));
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


        holder.cvApplicant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShelterApprovedAdoptersIndiv shelterApprovedAdoptersIndiv = new ShelterApprovedAdoptersIndiv();
                Bundle bundle = new Bundle();
                bundle.putString("applicationID", shelterApprovedAdoptersDataList.getApplicationID());
                bundle.putString("adopterID", shelterApprovedAdoptersDataList.getApplicantID());
                bundle.putString("adopterName", shelterApprovedAdoptersDataList.getApplicantName());
                bundle.putString("petID", shelterApprovedAdoptersDataList.getApplicantPetID());
                bundle.putString("petName", shelterApprovedAdoptersDataList.getApplicantPetName());
                bundle.putString("dateApplied", shelterApprovedAdoptersDataList.getApplicantDateApplied());
                shelterApprovedAdoptersIndiv.setArguments(bundle);

                ((context.getActivity())).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.shelter_approved_adopters_frag, shelterApprovedAdoptersIndiv,"ShelterApprovedAdoptersIndiv").addToBackStack(null).commit();
            }
        });
        holder.setIsRecyclable(false);

        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return shelterApprovedAdoptersData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView applicantImage;
        TextView applicantName;
        TextView applicantPet;

        CardView cvApplicant;

        public ViewHolder(View itemView) {
            super(itemView);
            cvApplicant = itemView.findViewById(R.id.applicant_cv);
            applicantImage = itemView.findViewById(R.id.applicant_image);
            applicantName = itemView.findViewById(R.id.applicant_name);
            applicantPet = itemView.findViewById(R.id.applicant_pet);
        }
    }
}