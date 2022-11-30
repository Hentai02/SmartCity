package com.example.smartcity04.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.smartcity04.R;
import com.example.smartcity04.ui.stopwhere.StopWhereActivity;
import com.example.smartcity04.pojo.IconAndTitleItem;

import java.util.List;

public class ServiceItemAdapter extends RecyclerView.Adapter<IconAndTitleViewHolder> {

    private List<IconAndTitleItem> data;
    private final Context mContext;
    private static final String LOG_TAG = ServiceItemAdapter.class.getSimpleName();

    public ServiceItemAdapter(List<IconAndTitleItem> data, Context context){
        this.data = data;
        this.mContext = context;
    }

    @NonNull
    @Override
    public IconAndTitleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_service,parent,false);
        return new IconAndTitleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IconAndTitleViewHolder holder, int position) {
        IconAndTitleItem e = data.get(position);
        holder.mIcon.setImageResource(e.id);
        holder.mTitle.setText(e.title);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceClicked(e);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private void serviceClicked(IconAndTitleItem e){
        if(e.title.equals(mContext.getString(R.string.service_stop_where))){
            Intent intent = new Intent(mContext, StopWhereActivity.class);
            mContext.startActivity(intent);

        }else if(e.title.equals(mContext.getString(R.string.service_city_subway))){

        }else if(e.title.equals(mContext.getString(R.string.service_takeaway_order))){

        }else if(e.title.equals(mContext.getString(R.string.service_wisdom_traffic))){

        }else if(e.title.equals(mContext.getString(R.string.service_find_job))){

        }else if(e.title.equals(mContext.getString(R.string.service_find_house))){

        }else if(e.title.equals(mContext.getString(R.string.service_event_manage))){

        }else if(e.title.equals(mContext.getString(R.string.service_living_expenses))){

        }else if(e.title.equals(mContext.getString(R.string.service_look_movie))){

        }else{
            Log.d(LOG_TAG,"更多...");
        }
    }

}
