package com.example.petnership_kairos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class ActiveAdoptersAdapter extends RecyclerView.Adapter<ActiveAdoptersAdapter.ViewHolder> {

    private ActiveAdopters contextA;
    Context context;
    ActiveAdoptersData[] ActiveAdoptersData;

    public ActiveAdoptersAdapter(ActiveAdoptersData[] ActiveAdoptersData, ActiveAdopters activity){
        this.ActiveAdoptersData = ActiveAdoptersData;
        this.contextA = activity;
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
        final ActiveAdoptersData ActiveAdoptersDataList = ActiveAdoptersData[position];
        holder.applicantName.setText(ActiveAdoptersDataList.getapplicantName());
        holder.applicantImage.setImageResource(ActiveAdoptersDataList.getapplicantImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ActiveAdoptersDataList.getapplicantName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return ActiveAdoptersData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView applicantImage;
        TextView applicantName;

        public ViewHolder(View itemView) {
            super(itemView);
            applicantImage = itemView.findViewById(R.id.applicant_image);
            applicantName = itemView.findViewById(R.id.applicant_name);

        }
    }


}