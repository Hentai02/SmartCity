package com.example.smartcity04.adapter.stopwhere;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity04.R;
import com.example.smartcity04.pojo.stopwhere.ScoreInfo;

import java.util.List;

public class ScoreBillAdapter extends RecyclerView.Adapter<ScoreBillAdapter.ViewHolder> {
    private final List<ScoreInfo> data;
    private final Context mContext;

    public ScoreBillAdapter(List<ScoreInfo> data,Context context){
        this.data = data;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bill,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ScoreInfo e = data.get(position);
        holder.mEventIcon.setImageResource(R.drawable.img_rmb);
        holder.mChangeAmount.setText(e.getScore());
        holder.mEventTitle.setText(e.getEvent());
        holder.mChangeTime.setText(e.getChangeDate());
    }

    @Override
    public int getItemCount() {
        if(data != null)
            return data.size();
        else
            return 0;
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
