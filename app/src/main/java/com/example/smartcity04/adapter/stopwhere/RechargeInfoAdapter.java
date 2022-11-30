package com.example.smartcity04.adapter.stopwhere;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartcity04.R;
import com.example.smartcity04.pojo.stopwhere.RechargeInfo;

import java.util.List;

public class RechargeInfoAdapter extends RecyclerView.Adapter<RechargeInfoAdapter.ViewHolder> {
    private static final String LOG_TAG = RechargeInfoAdapter.class.getSimpleName();
    private final List<RechargeInfo> data;
    private final Context mContext;

    public RechargeInfoAdapter(List<RechargeInfo> data,Context context){
        this.data = data;
        this.mContext = context;
    }


    @NonNull
    @Override
    public RechargeInfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_sw_recharge_info,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RechargeInfoAdapter.ViewHolder holder, int position) {
        RechargeInfo e = data.get(position);
        String payType = e.getPayType();
        if(payType != null){
            if(payType.equals("微信"))
                Glide.with(mContext).load(R.drawable.img_wechat).into(holder.mPayTypeIv);
            else if(payType.equals("支付宝"))
                Glide.with(mContext).load(R.drawable.img_alipay).into(holder.mPayTypeIv);
            else
                Glide.with(mContext).load(R.drawable.img_rmb).into(holder.mPayTypeIv);
        }else{
            Glide.with(mContext).load(R.drawable.img_rmb).into(holder.mPayTypeIv);
        }
        holder.mUserIdTv.setText(mContext.getString(R.string.uid_format,e.getUserId()));
        holder.mRechargeDateTv.setText(e.getRechargeDate());
        holder.mMoneyTv.setText(mContext.getString(R.string.stop_where_income,e.getMoney()));
    }

    @Override
    public int getItemCount() {
        if (data != null)
            return data.size();
        else
            return 0;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final ImageView mPayTypeIv;
        public final TextView mRechargeDateTv;
        public final TextView mMoneyTv;
        public final TextView mUserIdTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mPayTypeIv = itemView.findViewById(R.id.iv_payType);
            mRechargeDateTv = itemView.findViewById(R.id.tv_rechargeDate);
            mMoneyTv = itemView.findViewById(R.id.tv_money);
            mUserIdTv = itemView.findViewById(R.id.tv_userId);
        }
    }
}
