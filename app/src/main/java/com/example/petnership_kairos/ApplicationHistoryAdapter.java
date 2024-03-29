package com.example.petnership_kairos;

        import android.content.Context;
        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;
        import androidx.cardview.widget.CardView;
        import androidx.annotation.NonNull;
        import androidx.fragment.app.FragmentTransaction;
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

public class ApplicationHistoryAdapter extends RecyclerView.Adapter<ApplicationHistoryAdapter.ViewHolder> {

    ApplicationHistoryData[] applicationHistoryData;
    ApplicationHistoryFragment contextA;
    DatabaseReference allPetsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("AllPets");
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    String imageName;

    public ApplicationHistoryAdapter(ApplicationHistoryData[] applicationHistoryData, ApplicationHistoryFragment activity){
        this.applicationHistoryData = applicationHistoryData;
        this.contextA = activity;
    }
    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.application_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder (ViewHolder holder,int position){
        final ApplicationHistoryData ApplicationHistoryDataList = applicationHistoryData[position];
        holder.apphistoName.setText(ApplicationHistoryDataList.getPetName());
        holder.apphistoStatus.setText(ApplicationHistoryDataList.getApplicationStatus());
//        holder.apphistoImage.setImageResource(ApplicationHistoryDataList.getapphistoImage());

        String petID = ApplicationHistoryDataList.getPetID();

        allPetsDBRef.child(petID).orderByKey().equalTo("imageName").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    allPetsDBRef.child(petID).child("imageName").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            imageName = (String) snapshot.getValue();
                            System.out.println(ApplicationHistoryDataList.getPetName()+"'s imageName == " + imageName);
                            if(imageName != null){
                                if(!imageName.isEmpty()){
                                    if(imageName != ""){
                                        //DISPLAY IMAGE TO IMAGE VIEW
                                        storageReference.child("Pets/").child(imageName).getDownloadUrl()
                                                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                    @Override
                                                    public void onSuccess(Uri uri) {
                                                        Glide.with(contextA).load(uri.toString()).into((ImageView) holder.itemView.findViewById(R.id.adoptee_image));
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
        holder.appHistoCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApplicationHistoryIndiv applicationHistoryIndiv = new ApplicationHistoryIndiv();
                Bundle bundle = new Bundle();
                bundle.putString("applicationID", ApplicationHistoryDataList.getApplicationID());
                bundle.putString("shelterID", ApplicationHistoryDataList.getShelterID());
                bundle.putString("petID", ApplicationHistoryDataList.getPetID());
                bundle.putString("petName", ApplicationHistoryDataList.getPetName());
                bundle.putString("petBreed", ApplicationHistoryDataList.getBreed());
                bundle.putString("petBirthday", ApplicationHistoryDataList.getBirthday());
                bundle.putString("petDesc", ApplicationHistoryDataList.getDesc());
                bundle.putString("applicationStatus", ApplicationHistoryDataList.getApplicationStatus());
                bundle.putString("applicantDateApplied",ApplicationHistoryDataList.getApplicantDateApplied());
                applicationHistoryIndiv.setArguments(bundle);


               ((contextA.getActivity())).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.appication_history_frag, applicationHistoryIndiv, "ApplicationHistoryIndiv").addToBackStack(null).commit();


            }
        });

        holder.setIsRecyclable(false);

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(), ApplicationHistoryDataList.getPetName(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return applicationHistoryData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView apphistoImage;
        TextView apphistoName;
        TextView apphistoStatus;
        CardView appHistoCV;
        public ViewHolder(View itemView) {
            super(itemView);
            appHistoCV = itemView.findViewById(R.id.applicant_cv);
            apphistoImage = itemView.findViewById(R.id.adoptee_image);
            apphistoName = itemView.findViewById(R.id.adoptee_name);
            apphistoStatus = itemView.findViewById(R.id.adoptee_status);
        }
    }


}