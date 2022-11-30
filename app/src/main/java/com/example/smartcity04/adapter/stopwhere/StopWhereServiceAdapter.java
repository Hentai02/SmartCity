package com.example.smartcity04.adapter.stopwhere;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity04.R;
import com.example.smartcity04.adapter.IconAndTitleViewHolder;
import com.example.smartcity04.pojo.IconAndTitleItem;
import com.example.smartcity04.pojo.stopwhere.ScoreInfo;
import com.example.smartcity04.ui.stopwhere.MyCarActivity;
import com.example.smartcity04.ui.stopwhere.ScoreBillActivity;
import com.example.smartcity04.ui.stopwhere.StopWhereWalletActivity;
import com.example.smartcity04.ui.stopwhere.StoreActivity;

import java.util.List;

public class StopWhereServiceAdapter extends RecyclerView.Adapter<IconAndTitleViewHolder> {
    private static final String LOG_TAG = StopWhereServiceAdapter.class.getSimpleName();
    private List<IconAndTitleItem> data;
    private Context mContext;

    public StopWhereServiceAdapter(List<IconAndTitleItem> dataSet,Context context){
        this.data = dataSet;
        this.mContext = context;
    }

    @NonNull
    @Override
    public IconAndTitleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service, parent, false);
        return new IconAndTitleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IconAndTitleViewHolder holder, int position) {
        IconAndTitleItem e = data.get(position);
        holder.mTitle.setText(e.title);
        holder.mIcon.setImageResource(e.id);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ScoreBillActivity.class);
                if (e.title.equals(mContext.getString(R.string.stop_where_my_car))){
                    intent = new Intent(mContext, MyCarActivity.class);
                }else if(e.title.equals(mContext.getString(R.string.stop_where_recharge))){
                    intent = new Intent(mContext, StopWhereWalletActivity.class);

                }else if(e.title.equals(mContext.getString(R.string.stop_where_store))){
                    intent = new Intent(mContext, StoreActivity.class);
                }else {
                    intent = new Intent(mContext, ScoreBillActivity.class);
                }
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
