package com.example.smartcity04.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity04.R;

public class IconAndTitleViewHolder extends RecyclerView.ViewHolder {

    public final ImageView mIcon;
    public final TextView mTitle;

    public IconAndTitleViewHolder(@NonNull View itemView) {
        super(itemView);
        mIcon = itemView.findViewById(R.id.iv_item_icon);
        mTitle = itemView.findViewById(R.id.tv_item_title);
    }
}
