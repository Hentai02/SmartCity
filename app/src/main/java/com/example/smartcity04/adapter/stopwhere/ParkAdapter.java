package com.example.smartcity04.adapter.stopwhere;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartcity04.R;
import com.example.smartcity04.pojo.stopwhere.ParkInfo;

import java.util.List;

public class ParkAdapter extends RecyclerView.Adapter<ParkAdapter.ViewHolder> {

    private List<ParkInfo> data;
    private Context mContext;

    public ParkAdapter(List<ParkInfo> data, Context context){
        this.data = data;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ParkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_park,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParkAdapter.ViewHolder holder, int position) {
        ParkInfo e = data.get(position);
//        if (e.getImgUrl().length()>0)
//            Glide.with(holder.itemView).load(SmBasicApi.BASE_URI + e.getImgUrl()).into(holder.mImgUrlIv);
//        else
        // https://i.loli.net/2021/11/11/NGsqCREIbXh6vPK.png
            Glide.with(holder.itemView).load(R.drawable.img_park_lot).into(holder.mImgUrlIv);
        holder.mParkNameTv.setText(e.getParkName());
        holder.mVacancyTv.setText(mContext.getString(R.string.park_vacancy_format,e.getVacancy()));
        holder.mDistanceTv.setText(mContext.getString(R.string.park_distance_format,e.getDistance()));
        holder.mAddressTv.setText(mContext.getString(R.string.park_address_format,e.getAddress()));
        holder.mPriceCapsTv.setText(mContext.getString(R.string.park_priceCaps_format,e.getPriceCaps()));
        holder.mRatesTv.setText(mContext.getString(R.string.park_rates_format,e.getRates()));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final ImageView mImgUrlIv;
        public final TextView mParkNameTv;
        public final TextView mPriceCapsTv;
        public final TextView mAddressTv;
        public final TextView mDistanceTv;
        public final TextView mVacancyTv;
        public final TextView mRatesTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImgUrlIv = itemView.findViewById(R.id.iv_imgUrl);
            mParkNameTv = itemView.findViewById(R.id.tv_parkName);
            mPriceCapsTv = itemView.findViewById(R.id.tv_priceCaps);
            mAddressTv = itemView.findViewById(R.id.tv_address);
            mDistanceTv = itemView.findViewById(R.id.tv_distance);
            mVacancyTv = itemView.findViewById(R.id.tv_vacancy);
            mRatesTv = itemView.findViewById(R.id.tv_rates);
        }
    }
}
