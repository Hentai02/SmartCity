package com.example.smartcity04.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity04.R;
import com.example.smartcity04.pojo.IconAndTitleItem;

import java.util.List;

public class UserServicesItemAdapter extends RecyclerView.Adapter<IconAndTitleViewHolder> {
    private static final String LOG_TAG = UserServicesItemAdapter.class.getSimpleName();
    private List<IconAndTitleItem> data;

    public UserServicesItemAdapter(List<IconAndTitleItem> dataSet){
        this.data = dataSet;
    }

    @NonNull
    @Override
    public IconAndTitleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service, parent, false);
        return new IconAndTitleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IconAndTitleViewHolder holder, int position) {
        IconAndTitleItem item = data.get(position);
        holder.mTitle.setText(item.title);
        holder.mIcon.setImageResource(item.id);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
