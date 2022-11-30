package com.example.smartcity04.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity04.R;
import com.example.smartcity04.pojo.BillInfo;

import java.util.List;

public class BillInfoAdapter extends RecyclerView.Adapter<BillInfoAdapter.ViewHolder> {
    private List<BillInfo> data;
    private Context mContext;

    public BillInfoAdapter(List<BillInfo> data, Context context){
        this.data = data;
        this.mContext = context;
    }

    public void setData(List<BillInfo> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BillInfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_bill,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BillInfo e = data.get(position);
        holder.mEventTitle.setText(String.valueOf(e.getUserId()));
        holder.mChangeTime.setText(e.getChangeTime());
        if(e.getChangeType().equals("收入")) {
            //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.mChangeAmount.setTextColor(mContext.getColor(R.color.income));
            //}
            holder.mChangeAmount.setText(mContext.getString(R.string.bill_income, e.getChangeAmount()));
        }
        else{
            holder.mChangeAmount.setText(mContext.getString(R.string.bill_expenses,e.getChangeAmount()));
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView mEventIcon;
        public TextView mEventTitle;
        public TextView mChangeTime;
        public TextView mChangeAmount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mEventIcon = itemView.findViewById(R.id.iv_user_avatars);
            mEventTitle = itemView.findViewById(R.id.tv_event);
            mChangeTime = itemView.findViewById(R.id.tv_changeTime);
            mChangeAmount = itemView.findViewById(R.id.tv_changeAmount);
        }
    }
}
