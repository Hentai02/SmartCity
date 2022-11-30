package com.example.smartcity04.adapter.stopwhere;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity04.R;
import com.example.smartcity04.pojo.stopwhere.StopCarRecord;

import java.util.List;

public class StopCarRecordAdapter extends RecyclerView.Adapter<StopCarRecordAdapter.ViewHolder> {

    private final List<StopCarRecord> data;
    private final Context mContext;

    public StopCarRecordAdapter(List<StopCarRecord> data, Context context){
        this.data = data;
        this.mContext = context;
    }

    @NonNull
    @Override
    public StopCarRecordAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stop_car_record,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StopCarRecordAdapter.ViewHolder holder, int position) {
        StopCarRecord e = data.get(position);
        holder.mPlateNumberTv.setText(e.getPlateNumber());
        holder.mParkNameTv.setText(e.getParkName());
        holder.mEntryTimeTv.setText(mContext.getString(R.string.entryTime_record_format,e.getEntryTime()));
        holder.mOutTimeTv.setText(mContext.getString(R.string.outTime_record_format,e.getOutTime()));
        holder.mAddressTv.setText(mContext.getString(R.string.park_address_format,e.getAddress()));
        holder.mMonetaryTv.setText(mContext.getString(R.string.monetary_record_format,e.getMonetary()));
        holder.mParkNoTv.setText(mContext.getString(R.string.parkNo_record_format,e.getParkNo()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mPlateNumberTv;
        public TextView mParkNameTv;
        public TextView mEntryTimeTv;
        public TextView mOutTimeTv;
        public TextView mAddressTv;
        public TextView mMonetaryTv;
        public TextView mParkNoTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mPlateNumberTv = itemView.findViewById(R.id.tv_record_plateNumber);
            mParkNameTv = itemView.findViewById(R.id.tv_record_parkName);
            mEntryTimeTv = itemView.findViewById(R.id.tv_record_entryTime);
            mOutTimeTv = itemView.findViewById(R.id.tv_record_outTime);
            mAddressTv = itemView.findViewById(R.id.tv_record_address);
            mMonetaryTv = itemView.findViewById(R.id.tv_record_monetary);
            mParkNoTv = itemView.findViewById(R.id.tv_record_parkNo);
        }
    }
}
