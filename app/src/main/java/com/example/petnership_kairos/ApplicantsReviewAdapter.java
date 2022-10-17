package com.example.petnership_kairos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class ApplicantsReviewAdapter extends RecyclerView.Adapter<ApplicantsReviewAdapter.ViewHolder> {

    ApplicantsReviewData[] applicantsReviewData;
    Context context;

    public ApplicantsReviewAdapter(ApplicantsReviewData[] applicantsReviewData, ApplicantsReview activity){
        this.applicantsReviewData = applicantsReviewData;
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
        final ApplicantsReviewData ApplicantsReviewDataList = applicantsReviewData[position];
        holder.applicantName.setText(ApplicantsReviewDataList.getapplicantName());
        holder.applicantPet.setText(ApplicantsReviewDataList.getapplicantPet());
        holder.applicantImage.setImageResource(ApplicantsReviewDataList.getapplicantImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ApplicantsReviewDataList.getapplicantName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return applicantsReviewData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView applicantImage;
        TextView applicantName;
        TextView applicantPet;

        public ViewHolder(View itemView) {
            super(itemView);
            applicantImage = itemView.findViewById(R.id.applicant_image);
            applicantName = itemView.findViewById(R.id.applicant_name);
            applicantPet = itemView.findViewById(R.id.applicant_pet);

        }
    }


}