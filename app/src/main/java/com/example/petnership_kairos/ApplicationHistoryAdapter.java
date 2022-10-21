package com.example.petnership_kairos;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.recyclerview.widget.RecyclerView;

public class ApplicationHistoryAdapter extends RecyclerView.Adapter<ApplicationHistoryAdapter.ViewHolder> {

    ApplicationHistoryData[] applicationHistoryData;
    ApplicationHistoryFragment context;

    public ApplicationHistoryAdapter(ApplicationHistoryData[] applicationHistoryData, ApplicationHistoryFragment activity){
        this.applicationHistoryData = applicationHistoryData;
        this.context = activity;
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
        holder.apphistoName.setText(ApplicationHistoryDataList.getapphistoName());
        holder.apphistoStatus.setText(ApplicationHistoryDataList.getapphistoStatus());
        holder.apphistoImage.setImageResource(ApplicationHistoryDataList.getapphistoImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), ApplicationHistoryDataList.getapphistoName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return applicationHistoryData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView apphistoImage;
        TextView apphistoName;
        TextView apphistoStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            apphistoImage = itemView.findViewById(R.id.adoptee_image);
            apphistoName = itemView.findViewById(R.id.adoptee_name);
            apphistoStatus = itemView.findViewById(R.id.adoptee_status);

        }
    }


}