package com.example.petnership_kairos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class BrowseAnimalsAdapter extends RecyclerView.Adapter<BrowseAnimalsAdapter.ViewHolder>{
    
    private BrowseAnimals contextA;
    Context context;
    BrowseAnimalsData[] BrowseAnimalsData;

    public BrowseAnimalsAdapter(BrowseAnimalsData[] BrowseAnimalsData, BrowseAnimals activity){
        this.BrowseAnimalsData = BrowseAnimalsData;
        this.contextA = activity;
    }
    @Override
    public BrowseAnimalsAdapter.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.browseanimalscard, parent, false);
        BrowseAnimalsAdapter.ViewHolder viewHolder = new BrowseAnimalsAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder (BrowseAnimalsAdapter.ViewHolder holder, int position){
        final BrowseAnimalsData BrowseAnimalsDataList = BrowseAnimalsData[position];
        holder.animalName.setText(BrowseAnimalsDataList.getanimalName());
        holder.animalAge.setText(BrowseAnimalsDataList.getanimalAge());
        holder.animalSex.setText(BrowseAnimalsDataList.getanimalSex());
        holder.animalBreed.setText(BrowseAnimalsDataList.getanimalBreed());
        holder.animalImage.setImageResource(BrowseAnimalsDataList.getanimalImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, BrowseAnimalsDataList.getanimalName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return BrowseAnimalsData.length;
    }

public class ViewHolder extends RecyclerView.ViewHolder {
    ImageView animalImage;
    TextView animalName;
    TextView animalAge;
    TextView animalSex;
    TextView animalBreed;

    public ViewHolder(View itemView) {
        super(itemView);
        animalImage = itemView.findViewById(R.id.pet_image);
        animalName = itemView.findViewById(R.id.per_pet_name_title);
        animalAge = itemView.findViewById(R.id.pet_age);
        animalSex = itemView.findViewById(R.id.pet_sex);
        animalBreed = itemView.findViewById(R.id.pet_breed);


    }
}


}